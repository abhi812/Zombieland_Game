import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Displays score and current wave on player death
 * 
 * @author Abhishek Kumar 
 * @version May 4 2018
 */
public class ScoreBoard extends Actor
{
    public static final float FONT_SIZE = 48;
    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;
    
    /**
     * Create a score board with dummy result for testing.
     */
    public ScoreBoard()
    {
        
    }

    /**
     * Create a score board for the final result.
     */
    public ScoreBoard(int score, int wave)
    {
        makeImage("Game Over", "Score: ", score, "Level: ", wave);
    }

    /**
     * Make the score board image.
     */
    private void makeImage(String title, String prefixOne, int score, String prefixTwo, int wave)
    {
        GreenfootImage image = new GreenfootImage(WIDTH, HEIGHT);

        image.setColor(new Color(255,255,255, 128));
        image.fillRect(0, 0, WIDTH, HEIGHT);
        image.setColor(new Color(0, 0, 0, 128));
        image.fillRect(5, 5, WIDTH-10, HEIGHT-10);
        Font font = image.getFont();
        font = font.deriveFont(FONT_SIZE);
        image.setFont(font);
        image.setColor(Color.WHITE);
        image.drawString(title, 60, 100);
        image.drawString(prefixOne + score, 60, 200);
        image.drawString(prefixTwo + wave, 60, 300);
        setImage(image);
    }
}
