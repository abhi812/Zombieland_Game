import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Handles details of the player's character. Has a movement speed and reload speed, 
 * and accepts keyboard and mouse input for control
 * 
 * @author Chris Harding 
 * @version May 4 2018
 */
public class Survivor extends Actor
{
    private int charSpeed = 2;  //Not final, possibility of adding powerups that increase this
    private int reloadDelay = 20; //Not final, possiblity of adding powerups that reduce this 
    private int reloadDelayCount;
    private GreenfootSound gunShot = new GreenfootSound("shoot02.mp3");
    /*
     * shoot02.mp3 created by Taira Komori
     * Downloaded from https://freesound.org/people/Taira%20Komori/sounds/215438/
     */
    
    public Survivor()
    {
        //Image is too large, scale down to a proper size for our game arena
        GreenfootImage image = getImage();
        image.scale(image.getWidth()-200, image.getHeight() - 150);
        setImage(image);
        reloadDelayCount = 20;
    }
    
    public int getCharSpeed()
    {
        return charSpeed;
    }
    
    public void setCharSpeed(int charSpeed)
    {
        this.charSpeed = charSpeed;
    }
    
    public void increaseCharSpeed()
    {
        charSpeed ++;
    }
    
    public void act() 
    {
        checkKeys();// Add your action code here.
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(null != mouse)
        {
            turnTowards(mouse.getX(),mouse.getY());
            turn(-4);
        }
        checkAttack();
        reloadDelayCount++;
    }    
    
    private void checkKeys()
    {
        if (Greenfoot.mousePressed(null)) 
        {
            fire();
        }
        
        int dx=0, dy=0; //WASD and Arrow keys should do the same thing
        if (Greenfoot.isKeyDown("w")) dy-=charSpeed;
        if (Greenfoot.isKeyDown("up")) dy-=charSpeed;
        if (Greenfoot.isKeyDown("s")) dy+=charSpeed;
        if (Greenfoot.isKeyDown("down")) dy+=charSpeed;
        if (Greenfoot.isKeyDown("d")) dx+=charSpeed;
        if (Greenfoot.isKeyDown("right")) dx+=charSpeed;
        if (Greenfoot.isKeyDown("a")) dx-=charSpeed;
        if (Greenfoot.isKeyDown("left")) dx-=charSpeed;
        if (dx != 0 || dy != 0) setLocation(getX()+dx, getY()+dy);
    }
    
        /**
     * Fire a bullet if the gun is ready.
     */
    public void fire() 
    {
        if (reloadDelayCount >= reloadDelay)
        {
            int bulletOffsetAngle = getRotation();
            gunShot.play();
            Bullet bullet = new Bullet(bulletOffsetAngle);
            //some trig to make the bullet spawn at the "gun" regardless of facing direction
            double offset_r = Math.toRadians(getRotation());
            double offset_nx = 50 * Math.cos(offset_r);
            double offset_ny = 50 * Math.sin(offset_r);
            getWorld().addObject(bullet, (int)offset_nx + getX(),(int)offset_ny + getY());
            bullet.move(20);
            reloadDelayCount = 0;
        }
    }
    
    public void checkAttack()
    {
        Zombie z = (Zombie) getOneIntersectingObject(Zombie.class);
        if(z!=null)
        {
            MyWorld world = (MyWorld) getWorld();
            world.removeObject(this);
            Greenfoot.playSound("manDead.wav");
            world.gameOver();
        }
    }
}
