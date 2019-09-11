import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Handle bullet travel and zombie interaction
 * 
 * @author Abhishek Kumar 
 * @version May 4 2018
 */
public class Bullet extends Actor
{
    /**
     * Act - do whatever the Bullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int life = 30;
   
    public Bullet() 
    {
        
    }
    public Bullet (int rotation)
    {
        setRotation(rotation);  //set the rotation same as survivor
    }
    
    public void act() 
    {
        if (life<=0)
        {
           getWorld().removeObject(this);   //remove the bullet after a certain amount of time
        }
        else
        {
            life--;                  //devrese bullet life
            move(20);                // move bullet
            checkZombieHit(); 
        }

    }  
    
    public void checkZombieHit()
    {
       Zombie zombie = (Zombie) getOneIntersectingObject(Zombie.class); //check if bullet sees any zombie
       if( zombie!=null)
       {
           
           MyWorld world = (MyWorld) getWorld();
           world.addScore(50);                               //add score 50 every tim zombie is killed by bullet
           Greenfoot.playSound("zombieDeath.mp3");           //play zombie dead sound
           getWorld().removeObject(zombie);                  
           world.removeObject(this);                         //removes zombie from the world
           
       }
    }
}
