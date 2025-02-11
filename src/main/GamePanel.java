//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package main;

import ai.PathFinder;
import data.SaveLoad;
import entity.Entity;
import entity.Player;
//import object.SuperObject;
import tile.TileManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = 48;
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize*maxScreenCol;
    public final int screeHeight = tileSize*maxScreenRow;

    //World map parameters
    public final int maxWorldCol=50;
    public final int maxWorldRow=50;
    public final int maxMap=3;
    public int currentMap=0;
    //FOR FULL SCREEN
    int ScreenWidth2=screenWidth;
    int ScreenHeigh2=screeHeight;
    BufferedImage tempScreen;
    Graphics2D g2;
    public boolean fullScreenOn = false;

    int FPS = 60;

    public TileManager tileM= new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    //public MouseListener ml = new MouseListener(this);
    Sound music=new Sound();
    Sound se=new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter= new AssetSetter(this);
    public UI ui= new UI(this);
    public EventHandler eHandler=new EventHandler(this);
    Config config=new Config(this);
    public PathFinder pFinder=new PathFinder(this);
    Thread gameThread;
    SaveLoad saveLoad=new SaveLoad(this,"database.db");

    //Entity and object
    public Player player=Player.getInstance(this,keyH);
    public Entity obj[][]=new Entity[maxMap][20];
    public Entity npc[][]=new Entity[maxMap][10];
    public Entity monster[][]=new Entity[maxMap][60];
    public ArrayList<Entity> projectileList=new ArrayList<>();
    ArrayList<Entity> entityList=new ArrayList<>();

    //Game state //STATE DESIGN PATTERN
    public int gameState;
    public final int titleState=0;
    public final int playState=1;
    public final int pauseState=2;
    public final int dialogueState=3;
    public final int optionState=4;
    public final int gameOverState=5;
    public final int gameWonState=6;


    public GamePanel() {
        this.setPreferredSize(new Dimension(768, 576));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(this.keyH);
        this.setFocusable(true);
    }

    public void setupGame()
    {
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        playMusic(0);
        gameState=titleState;

        tempScreen= new BufferedImage(screenWidth, screeHeight, BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D) tempScreen.getGraphics();

        setFullScreen();

    }

    public void resetGame(boolean restart)
    {
        player.setDefaultPositions();
        player.restoreLifeAndEnergy();
        aSetter.setNPC();
        aSetter.setMonster();

        if(restart==true) {
            player.setDefaultValues();
            aSetter.setObject();
            player.hasSoul=0;
            currentMap=0;
        }
    }

    public void setFullScreen()
    {
        //Get local screen devie
        GraphicsEnvironment ge= GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd=ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(Main.window);

        //Get full screen width and height
        ScreenHeigh2=Main.window.getHeight();
        ScreenWidth2=Main.window.getWidth();
    }

    public void startGameThread() {
        this.gameThread = new Thread(this);
        this.gameThread.start();
    }

    public void run() {
        double drawInterval = (double)(1000000000 / this.FPS);
        double delta = 0.0;
        long lastTime = System.nanoTime();
        long timer = 0L;
        int drawCount = 0;

        while(this.gameThread != null) {
            long currentTime = System.nanoTime();
            delta += (double)(currentTime - lastTime) / drawInterval;
            timer += currentTime - lastTime;
            lastTime = currentTime;
            if (delta >= 1.0) {
                this.update();
                drawToTempScreen(g2);//draw everything to the buffered image
                drawToScreen();//draw the buffered image to the screen
                --delta;
                ++drawCount;
            }

            if (timer >= 1000000000L) {
                System.out.println("FPS" + drawCount);
                drawCount = 0;
                timer = 0L;
            }
        }

    }

    public void update() {

        if(gameState==playState)
        {
            this.player.update();

            for(int i=0;i<npc[1].length;i++)
            {
                if(npc[currentMap][i]!=null)
                {
                    npc[currentMap][i].update();
                }
            }
            for(int i=0;i<monster[1].length;i++)
            {
                if(monster[currentMap][i]!=null)
                {
                    if(monster[currentMap][i].alive==true && monster[currentMap][i].dying==false)
                    {
                        monster[currentMap][i].update();
                    }
                    if(monster[currentMap][i].alive==false)
                    {
                        monster[currentMap][i].checkDrop();
                        monster[currentMap][i] = null;
                    }
                }
            }
            for(int i=0;i<projectileList.size();i++)
            {
                if(projectileList.get(i)!=null)
                {
                    if(projectileList.get(i).alive==true)
                    {
                        projectileList.get(i).update();
                    }
                    if(projectileList.get(i).alive==false)
                    {
                        projectileList.remove(i);
                    }
                }
            }

        }
        if(gameState==pauseState)
        {

        }
    }

    public void drawToTempScreen(Graphics g) {
        //Debug
        long drawStart=0;
        if(keyH.checkDrawTime==true)
        {
            drawStart = System.nanoTime();
        }

        //TITLE SCREEN
        if(gameState==titleState)
        {
            ui.draw(g2);
        }

        //OTHERS
        else {
            //TILE
            tileM.draw(g2);


            //ADD ENTITIES TO THE LIST
            entityList.add(player);
            for(int i = 0 ;i<npc[1].length;i++)
            {
                if(npc[currentMap][i]!=null)
                {
                    entityList.add(npc[currentMap][i]);
                }
            }

            for(int i = 0 ;i<obj[1].length;i++)
            {
                if(obj[currentMap][i]!=null)
                {
                    entityList.add(obj[currentMap][i]);
                }
            }

            for(int i = 0 ;i<monster[1].length;i++)
            {
                if(monster[currentMap][i]!=null)
                {
                    entityList.add(monster[currentMap][i]);
                }
            }
            for(int i = 0 ;i<projectileList.size();i++)
            {
                if(projectileList.get(i)!=null)
                {
                    entityList.add(projectileList.get(i));
                }
            }

            //SORT
            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {
                    int result = Integer.compare(e1.worldY, e2.worldY);
                    return result;
                }
            });

            //DRAW ENTITIES
            for(int i=0;i<entityList.size();i++)
            {
                entityList.get(i).draw(g2);
            }

            //EMPTY ENTITY LIST
            entityList.clear();



            //UI
            ui.draw(g2);
        }

        //Debug
        if(keyH.checkDrawTime==true)
        {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.drawString("Draw time: " + passed, 10, 400);
            System.out.println("Draw time: " + passed);
        }


    }


    public void drawToScreen() {
        Graphics g = getGraphics();
        g.drawImage(tempScreen, 0, 0,  ScreenWidth2,ScreenHeigh2,null);
        g.dispose();
    }

    public void playMusic(int i)
    {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic()
    {
        music.stop();
    }

    public void playSE(int i)
    {
        se.setFile(i);
        se.play();

    }
}
