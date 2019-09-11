import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Zombie enemy that chases the player, checks how many are alive and triggers a spawning of more, 
 * and has animation frames.  
 * 
 * @author Abhishek Kumar, with contributions by Chris Harding 
 * @version May 4 2018
 */
public class Zombie extends Actor
{
    private int animationFrame = 0;
    private boolean animationToggle = true;
    
    public Zombie()
    {
        //Image is too large, scale down to a proper size for our game arena
        GreenfootImage image = getImage();
        image.scale(image.getWidth()-210, image.getHeight() - 240);
        setImage(image);
    }
    
    /**
     * Act - do whatever the Zombie wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        checkZombie();
        chasePlayer();
        animate();
        move(1);
        
    }    
    
    public void checkZombie()
    {
        int number = getWorld().numberOfObjects(); //add set of zombies if they are sufficiently low
        if(number < 5)
        {
            MyWorld world = (MyWorld) getWorld();
            world.increaseWave();
            world.addZombie();
        }
    }
    

    
    public void chasePlayer()
    {
        if (getWorld().getObjects(Survivor.class).isEmpty()) return; // skips following if the Survivor is not in world
        Actor survivor = (Actor)getWorld().getObjects(Survivor.class).get(0); // gets reference to survivor
        turnTowards(survivor.getX(), survivor.getY()); // turn toward survivor
    }
    
    public void animate()
    {
        GreenfootImage frame = new GreenfootImage("skeleton-move_" + animationFrame / 3 + ".png");
        frame.scale(frame.getWidth()-210, frame.getHeight() - 240);
        setImage(frame);
        if (animationToggle) animationFrame ++;
        else animationFrame --;
        if (animationFrame == 0) animationToggle = true;
        if (animationFrame == 36) animationToggle = false;
    }
}
