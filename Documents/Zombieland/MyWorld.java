import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Simple world for the zombie horde. Contains score and wave trackers/displays 
 * and zombie spawning methods
 * 
 * @author Chris Harding and Abhishek Kumar 
 * @version May 4 2018
 */
public class MyWorld extends World
{
    private int wave = 0;
    private int score = 0;
    GreenfootSound worldSound = new GreenfootSound("zombieWorld.wav");
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    
    public MyWorld()
    {    
        super(1200, 900, 1); 
        Survivor playerChar = new Survivor();
        addObject(playerChar, 600, 450);
        addZombie();   
        showScore();
        showWave();
    }
    
    public void act()
    {
        worldSound.play();
    }
    
    public void increaseWave()
    {
        wave++;
        showWave();
        addScore(500); //add score for each level up or increase in zombie wave
    }
    
    public int getWave()
    {
        return wave;
    }
    
    public void showWave()
    {
        showText("Level: " + wave, 80, 80);  //level up the game every time wave increases
    }
    
    public void addScore(int points)
    {
        score = score + points;
        showScore();
    }
    
    public void showScore()
    {
        showText("Score: "+score, 80,50);
    }
    
    public void gameOver()
    {
        Greenfoot.stop();
        showEndMessage();
        worldSound.stop();
    }
    
    public void showEndMessage()
    {
        addObject(new ScoreBoard(score, wave), 600,450);        
    }
    
    public void addZombie()
    {   
        for (int i = 0; i < (((wave-1) * 5) + 10); i++)
        {
            int checkValueOne = Greenfoot.getRandomNumber(2);
            int checkValueTwo = Greenfoot.getRandomNumber(2);
            int spawnLocationVert = Greenfoot.getRandomNumber(getHeight());
            int spawnLocationHoriz = Greenfoot.getRandomNumber(getWidth());
            if (checkValueOne == 0) 
                {
                    if (checkValueTwo == 0)
                    {
                        addObject(new Zombie(), spawnLocationHoriz, 0);
                    }
                    else//false spawns bottom
                    {
                        addObject(new Zombie(), spawnLocationHoriz, getHeight());
                    }
                }
            else //false spawns left or right
                {
                    if (checkValueTwo == 0)
                    {
                        addObject(new Zombie(), 0, spawnLocationVert);
                    }
                    else
                    {
                        addObject(new Zombie(), getWidth(), spawnLocationVert);
                    }
                }
        }
        
    }
}
