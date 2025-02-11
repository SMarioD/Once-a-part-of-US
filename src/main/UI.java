package main;

import entity.Entity;
import object.Obj_EnergyCrystal;
import object.Obj_Heart;
import object.Obj_Key;
import object.Obj_Soul;
//import object.SuperObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font maruMonica,purisaB;
    BufferedImage heart_full,heart_half,heart_blank, crystal_full,crystal_blank;
    BufferedImage keyImage,soulImage;
    public boolean messageOn=false;
    public String message="";
    int messageCounter=0;
    public boolean gameFinished=false;
    public String currentDialogue="";
    public int commandNum=0;
    int subState=0;

    double playTime;
    DecimalFormat dFormat=new DecimalFormat("#0.00");


    public UI(GamePanel gp)
    {
        this.gp=gp;

        try{
            InputStream is=getClass().getResourceAsStream("/font/MP16REG.ttf");
            maruMonica=Font.createFont(Font.TRUETYPE_FONT,is);
            is=getClass().getResourceAsStream("/font/Purisa Bold.ttf");
            purisaB=Font.createFont(Font.TRUETYPE_FONT,is);
        }catch (FontFormatException e)
        {
            e.printStackTrace();
        }catch (IOException e)
        {
            e.printStackTrace();
        }

        //CREATE HUD OBJECT
        Entity heart= new Obj_Heart(gp);
        heart_full=heart.image;
        heart_half=heart.image2;
        heart_blank=heart.image3;
        Entity crystal=new Obj_EnergyCrystal(gp);
        crystal_full=crystal.image;
        crystal_blank=crystal.image2;


        //Obj_Key key= new Obj_Key(gp);
        //keyImage=key.image;
        Obj_Soul soul= new Obj_Soul(gp);
        soulImage=soul.right1;
    }
    public void showMessage(String text)
    {
        message=text;
        messageOn=true;
    }
    public void draw(Graphics2D g2){
        this.g2=g2;

        g2.setFont(maruMonica);
        g2.setColor(Color.white);

        //TITLE STATE
        if(gp.gameState==gp.titleState)
        {
            drawTitleScreen();
        }

        //PLAY STATE
        if(gp.gameState==gp.playState)
        {
            drawPlayerLife();
            drawMonsterLife();
            g2.setFont(new Font("Arial",Font.PLAIN,40));
            g2.setColor(new Color(0,0,139));
            g2.drawImage(soulImage,gp.tileSize/2,gp.tileSize*3,gp.tileSize,gp.tileSize,null);
            g2.drawString(" "+gp.player.hasSoul,84,190);

        }
        //PAUSE STATE
        if(gp.gameState==gp.pauseState)
        {
            drawPlayerLife();
            g2.setFont(new Font("Arial",Font.PLAIN,40));
            g2.setColor(new Color(0,0,139));
            g2.drawImage(soulImage,gp.tileSize/2,gp.tileSize*3,gp.tileSize,gp.tileSize,null);
            g2.drawString(" "+gp.player.hasSoul,84,190);

            g2.setColor(Color.white);
            drawPauseScreen();
        }
        //DIALOGUE STATE
        if(gp.gameState==gp.dialogueState)
        {
            drawPlayerLife();
            g2.setFont(new Font("Arial",Font.PLAIN,40));
            g2.setColor(new Color(0,0,139));
            g2.drawImage(soulImage,gp.tileSize/2,gp.tileSize*3,gp.tileSize,gp.tileSize,null);
            g2.drawString(" "+gp.player.hasSoul,84,190);

            drawDialogScreen();
        }

        //OPTIONS STATE
        if(gp.gameState==gp.optionState)
        {
            drawOptionsScreen();
        }

        //GAME OVER STATE
        if(gp.gameState==gp.gameOverState)
        {
            drawGameOverScreen();
        }

        //GAME WON STATE
        if(gp.gameState==gp.gameWonState)
        {
            drawGameWonScreen();
        }
    }

    public void drawPlayerLife()
    {

        int x=gp.tileSize/2;
        int y=gp.tileSize/2;
        int i=0;

        //DRAW MAX LIFE
        while(i<gp.player.maxLife/2)
        {
            g2.drawImage(heart_blank,x,y,null);
            i++;
            x+=gp.tileSize;
        }

        //RESET
         x=gp.tileSize/2;
         y=gp.tileSize/2;
         i=0;

         //DRAW CURRENT LIFE
        while (i<gp.player.life)
        {
            g2.drawImage(heart_half,x,y,null);
            i++;
            if(i<gp.player.life)
            {
                g2.drawImage(heart_full,x,y,null);
            }
            i++;
            x+=gp.tileSize;
        }

        //DRAW MAX ENERGY
        x=gp.tileSize/2-5;
        y=(int)(gp.tileSize*1.8);
        i=0;
        while (i<gp.player.maxEnergy)
        {
            g2.drawImage(crystal_blank,x,y,null);
            i++;
            x+=35;

        }

        //DRAW MANA
        x=gp.tileSize/2-5;
        y=(int)(gp.tileSize*1.8);
        i=0;
        while (i<gp.player.energy)
        {
            g2.drawImage(crystal_full,x,y,null);
            i++;
            x+=35;
        }

    }
    public void verifyNotNull(InputStream i) throws ExistentBackgrundException {
        if((i=getClass().getResourceAsStream("/menu/Menu.png"))==null)
        {
            throw new ExistentBackgrundException("menu draw failed");
        }
        else{
            System.out.println("Good to go");
        }
    }

    public void drawTitleScreen()
    {

        // Load the original image
        BufferedImage originalImage = null;
        UtilityTool uTool = new UtilityTool();
        InputStream is;
        try {

            is=getClass().getResourceAsStream("/menu/Menu.png");

            if((is=getClass().getResourceAsStream("/menu/Menu.png"))!=null)
            {
                verifyNotNull(is);
                originalImage = ImageIO.read(is);
            }
            else{
                System.out.println("menu draw with success");
                System.exit(0);
            }
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            System.exit(1);
        }


        // Scale the image to the screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
        BufferedImage scaledImage = uTool.scaleImage(originalImage, screenWidth, screenHeight);

        // Draw the scaled image
        g2.drawImage(scaledImage, -96, 0, null);
        //TITLE NAME
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,86F));
        String text="Once a part of US";
        int x=getXforCenterText(text);
        int y=gp.tileSize*3;

        //SHADOW
        g2.setColor(Color.gray);
        g2.drawString(text,x+5,y+5);


        g2.setColor(Color.white);
        g2.drawString(text,x,y);


        //MENU
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));

        text="NEW GAME";
        x=getXforCenterText(text);
        y+=gp.tileSize*3.5;
        g2.drawString(text,x,y);
        if(commandNum==0)
        {
            g2.drawString(">",x-gp.tileSize,y);
        }

        text="LOAD GAME";
        x=getXforCenterText(text);
        y+=gp.tileSize;
        g2.drawString(text,x,y);
        if(commandNum==1)
        {
            g2.drawString(">",x-gp.tileSize,y);
        }

        text="QUIT";
        x=getXforCenterText(text);
        y+=gp.tileSize;
        g2.drawString(text,x,y);
        if(commandNum==2)
        {
            g2.drawString(">",x-gp.tileSize,y);
        }
    }

    public void drawPauseScreen()
    {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
        String text="PAUSED";
        int x=getXforCenterText(text);
        int y=gp.screeHeight/2;
        g2.drawString(text,x,y);
    }

    public void drawMonsterLife()
    {
        for(int i=0;i<gp.monster[1].length;i++)
        {

            Entity monster=gp.monster[gp.currentMap][i];
            if(monster!=null && monster.inCamera()==true)
            {

                if(monster.hpBarOn==true && monster.boss==false) {

                    double oneScale=(double) gp.tileSize/monster.maxLife;
                    double hpBarValue=oneScale*monster.life;

                    g2.setColor(new Color(35,35,35));
                    g2.fillRect(monster.getScreenX()-1,monster.getScreenY()-16,gp.tileSize+2,12);

                    g2.setColor(new Color(255, 0, 30));
                    g2.fillRect(monster.getScreenX(), monster.getScreenY() - 15, (int)hpBarValue, 10);

                    monster.hpBarCounter++;

                    if(monster.hpBarCounter >600)
                    {
                        monster.hpBarCounter=0;
                        monster.hpBarOn=false;
                    }
                }
                else if (monster.boss==true)
                {
                    double oneScale=(double) gp.tileSize*8/monster.maxLife;
                    double hpBarValue=oneScale*monster.life;

                    int x= gp.screenWidth/2- gp.tileSize*4;
                    int y= gp.tileSize*10;

                    g2.setColor(new Color(35,35,35));
                    g2.fillRect(x-1,y-1,gp.tileSize*8+2,22);

                    g2.setColor(new Color(255, 0, 30));
                    g2.fillRect(x,y, (int)hpBarValue, 20);

                    g2.setFont(g2.getFont().deriveFont(Font.BOLD,24F));
                    g2.setColor(Color.white);
                    g2.drawString(monster.name,x+4,y-10);
                }
            }
        }

    }
    public void drawDialogScreen()
    {
        //Window
        int x=gp.tileSize*2;
        int y=gp.tileSize/2;
        int width=gp.screenWidth-(gp.tileSize*3);
        int height=gp.tileSize*4;

        drawSubWindow(x,y,width,height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,32F));
        x+=gp.tileSize;
        y+=gp.tileSize;

        for(String line:currentDialogue.split("\n"))
        {
           g2.drawString(line,x,y);
            y+=40;
        }


    }

    public void drawGameOverScreen()
    {
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0,0,gp.screenWidth,gp.screeHeight);

        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,110f));

        text="Game Over";
        //Shadow
        g2.setColor(Color.black);
        x=getXforCenterText(text);
        y=gp.tileSize*4;
        g2.drawString(text,x,y);
        //Main
        g2.setColor(Color.white);
        g2.drawString(text,x-4,y-4);

        //Retry
        g2.setFont(g2.getFont().deriveFont(50f));
        text="Retry";
        x=getXforCenterText(text);
        y+=gp.tileSize*4;
        g2.drawString(text,x,y);
        if(commandNum==0)
        {
            g2.drawString(">",x-40,y);
        }

        //Back to the title screen
        text="Quit";
        x=getXforCenterText(text);
        y+=55;
        g2.drawString(text,x,y);
        if(commandNum==1)
        {
            g2.drawString(">",x-40,y);
        }



    }

    public void drawGameWonScreen()
    {
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0,0,gp.screenWidth,gp.screeHeight);

        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,110f));

        text="Game Won!!!";
        //Shadow
        g2.setColor(Color.black);
        x=getXforCenterText(text);
        y=gp.tileSize*4;
        g2.drawString(text,x,y);
        //Main
        g2.setColor(Color.white);
        g2.drawString(text,x-4,y-4);

        //Retry
        g2.setFont(g2.getFont().deriveFont(50f));
        text="Retry";
        x=getXforCenterText(text);
        y+=gp.tileSize*4;
        g2.drawString(text,x,y);
        g2.drawString(">",x-40,y);
    }

    public void drawOptionsScreen(){

        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        //SUB WINDOW
        int frameX=gp.tileSize*6;
        int frameY=gp.tileSize;
        int frameWidth=gp.tileSize*8;
        int frameHeight=gp.tileSize*10;
        drawSubWindow(frameX,frameY,frameWidth,frameHeight);

        switch (subState){
            case 0: option_top(frameX,frameY);
                break;
            case 1: option_fullScreenNotificaiton(frameX,frameY);
                break;
            case 2: options_control(frameX,frameY);
                break;
            case 3: option_save(frameX,frameY);
                break;
            case 4: options_endGameConfirmation(frameX,frameY);
                break;
        }

        gp.keyH.enterPressed=false;

    }

    public void option_top(int frameX,int frameY) {
        int textX;
        int textY;

        //TITLE
        String text="Options";
        textX= getXforCenterText(text);
        textY=frameY+gp.tileSize;
        g2.drawString(text,textX,textY);

        //Full Screen ON/OFF
        textX=frameX+gp.tileSize;
        textY+=gp.tileSize*2;
        g2.drawString("Full Screen",textX,textY);
        if(commandNum==0)
        {
            g2.drawString(">",textX-25,textY);
            if(gp.keyH.enterPressed==true)
            {
                if(gp.fullScreenOn==false)
                {
                    gp.fullScreenOn=true;
                }
                else if(gp.fullScreenOn==true)
                {
                    gp.fullScreenOn=false;
                }
                subState=1;
            }

        }

        //MUSIC
        textY+=gp.tileSize;
        g2.drawString("Music",textX,textY);
        if(commandNum==1)
        {
            g2.drawString(">",textX-25,textY);
        }

        //SE
        textY+=gp.tileSize;
        g2.drawString("SE",textX,textY);
        if(commandNum==2)
        {
            g2.drawString(">",textX-25,textY);
        }

        //CONTROL
        textY+=gp.tileSize;
        g2.drawString("Controls",textX,textY);
        if(commandNum==3)
        {
            g2.drawString(">",textX-25,textY);
            if(gp.keyH.enterPressed==true)
            {
                subState=2;
                commandNum=0;
            }
        }


        //SAVE Game
        textY+=gp.tileSize;
        g2.drawString("Save Game",textX,textY);
        if(commandNum==4)
        {
            g2.drawString(">",textX-25,textY);
            if(gp.keyH.enterPressed==true)
            {
                subState=3;
                commandNum=0;
            }
        }

        //END GAME
        textY+=gp.tileSize;
        g2.drawString("End Game",textX,textY);
        if(commandNum==5)
        {
            g2.drawString(">",textX-25,textY);
            if(gp.keyH.enterPressed==true)
            {
                subState=4;
                commandNum=0;
            }
        }

        //Back
        textY+=gp.tileSize;
        g2.drawString("Back",textX,textY);
        if(commandNum==6)
        {
            g2.drawString(">",textX-25,textY);
            if(gp.keyH.enterPressed==true)
            {
                gp.gameState= gp.playState;
                commandNum=0;
            }
        }

        //FULL SCREEN CHECK BOX
        textX=frameX+(int)(gp.tileSize*4.5);
        textY=frameY+gp.tileSize*2+24;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textX,textY,24,24);
        if(gp.fullScreenOn==true)
        {
            g2.fillRect(textX,textY,24,24);
        }

        //MUSIC VOLUME
        textY+=gp.tileSize;
        g2.drawRect(textX,textY,120,24);
        int volumeWidth=24*gp.music.volumeScale;
        g2.fillRect(textX,textY,volumeWidth,24);

        //SE VOLUME
        textY+=gp.tileSize;
        g2.drawRect(textX,textY,120,24);
        int seWidth=24*gp.se.volumeScale;
        g2.fillRect(textX,textY,seWidth,24);

        gp.config.saveConfig();

    }

    public void option_fullScreenNotificaiton(int frameX,int frameY)
    {
        int textX=frameX+gp.tileSize;
        int textY=frameY+gp.tileSize*3;

        currentDialogue="The change will take \neffect after restarting \nthe game.";

        for(String line:currentDialogue.split("\n"))
        {
            g2.drawString(line,textX,textY);
            textY+=40;
        }

        //BACK
        textY=frameY+gp.tileSize*9;
        g2.drawString("Back",textX,textY);
        if(commandNum==0)
        {
            g2.drawString(">",textX-25,textY);
            if(gp.keyH.enterPressed==true)
            {
                subState=0;
            }
        }

    }

    public void option_save(int frameX,int frameY)
    {
        int textX=frameX+gp.tileSize;
        int textY=frameY+gp.tileSize*3;

        currentDialogue="    SAVE SUCCESFUL\n      Your game has\nbeen save succesfully.";

        for(String line:currentDialogue.split("\n"))
        {
            g2.drawString(line,textX,textY);
            textY+=40;
        }

        //BACK
        textY=frameY+gp.tileSize*9;
        g2.drawString("Back",textX,textY);
        if(commandNum==0)
        {
            g2.drawString(">",textX-25,textY);
            if(gp.keyH.enterPressed==true)
            {
                subState=0;
                gp.saveLoad.save();
            }
        }
    }

    public void options_control(int frameX,int frameY)
    {
        int textX;
        int textY;

        //TITLE
        String text="Control";
        textX=getXforCenterText(text);
        textY=frameY+gp.tileSize;
        g2.drawString(text,textX,textY);

        textX=frameX+gp.tileSize;
        textY+=gp.tileSize;
        g2.drawString("Move",textX,textY);textY+=gp.tileSize;
        g2.drawString("Confirm/Interact",textX,textY);textY+=gp.tileSize;
        g2.drawString("Attack",textX,textY);textY+=gp.tileSize;
        g2.drawString("Throw rock",textX,textY);textY+=gp.tileSize;
        g2.drawString("Pause",textX,textY);textY+=gp.tileSize;
        g2.drawString("Options",textX,textY);textY+=gp.tileSize;
        //g2.drawString("Back",textX,textY);

        textX= frameX+gp.tileSize*6;
        textY=frameY+gp.tileSize*2;
        g2.drawString("WASD",textX,textY);textY=textY+gp.tileSize;
        g2.drawString("ENTER",textX,textY);textY=textY+gp.tileSize;
        g2.drawString("J",textX,textY);textY=textY+gp.tileSize;
        g2.drawString("K",textX,textY);textY=textY+gp.tileSize;
        g2.drawString("P",textX,textY);textY=textY+gp.tileSize;
        g2.drawString("ESC",textX,textY);textY=textY+gp.tileSize;

        //BACK
        textX=frameX+gp.tileSize;
        textY=frameY+gp.tileSize*9;
        g2.drawString("Back",textX,textY);

        if(commandNum==0)
        {
            g2.drawString(">",textX-25,textY);
            if(gp.keyH.enterPressed==true)
            {
                subState=0;
                commandNum=3;
            }
        }
    }

    public void options_endGameConfirmation(int frameX,int frameY)
    {
        int textX=frameX+gp.tileSize;
        int textY=frameY+gp.tileSize*3;

        currentDialogue="Quit the game and\nreturn to the title\nscreen?";

        for(String line:currentDialogue.split("\n"))
        {
            g2.drawString(line,textX,textY);
            textY+=40;
        }

        //YES

        String text="Yes";
        textX=getXforCenterText(text);
        textY=textY+gp.tileSize*3;
        g2.drawString(text,textX,textY);
        if(commandNum==0)
        {
            g2.drawString(">",textX-25,textY);
            if(gp.keyH.enterPressed==true)
            {
                subState=0;
                gp.gameState=gp.titleState;
            }
        }

        //NO
        text="No";
        textX=getXforCenterText(text);
        textY=textY+gp.tileSize;
        g2.drawString(text,textX,textY);
        if(commandNum==1)
        {
            g2.drawString(">",textX-25,textY);
            if(gp.keyH.enterPressed==true)
            {
                subState=0;
                commandNum=4;
            }
        }
    }

    public void drawSubWindow(int x,int y,int width,int height)
    {
            Color c=new Color(0,0,0,210);
            g2.setColor(c);
            g2.fillRoundRect(x,y,width,height,35,35);

            c=new Color(255,255,255);
            g2.setColor(c);
            g2.setStroke(new BasicStroke(5));
            g2.drawRoundRect(x+5,y+5,width-10,height-10,25,25);
    }

    public int getXforCenterText(String text)
    {
        int length=(int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x=gp.screenWidth/2-length/2;
        return x;
    }
}
