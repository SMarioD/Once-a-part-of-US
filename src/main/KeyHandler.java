
package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

public class KeyHandler implements KeyListener {
    GamePanel gp;
    public boolean upPressed;
    public boolean downPressed;
    public boolean leftPressed;
    public boolean rightPressed;
    public boolean enterPressed;
    public boolean jPressed;
    public boolean shotKeyPressed;

    //Debug
    boolean checkDrawTime=false;

    public KeyHandler(GamePanel gp)
    {
        this.gp=gp;
    }


    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        //TITLE STATE
        if(gp.gameState==gp.titleState)
        {
            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if(gp.ui.commandNum<0)
                {
                    gp.ui.commandNum=2;
                }
            }

            if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if(gp.ui.commandNum>2)
                {
                    gp.ui.commandNum=0;
                }
            }
            if (code == KeyEvent.VK_ENTER)
            {
                if(gp.ui.commandNum==0)
                {
                    gp.resetGame(true);
                    gp.gameState=gp.playState;
                }
                if(gp.ui.commandNum==1)
                {
                    gp.saveLoad.load();
                    gp.gameState=gp.playState;
                }
                if(gp.ui.commandNum==2)
                {
                    System.exit(0);
                }

            }
        }

        //PLAY STATE
        if(gp.gameState == gp.playState)
        {
            if (code == KeyEvent.VK_W) {
                this.upPressed = true;
            }

            if (code == KeyEvent.VK_S) {
                this.downPressed = true;
            }

            if (code == KeyEvent.VK_A) {
                this.leftPressed = true;
            }

            if (code == KeyEvent.VK_D) {
                this.rightPressed = true;
            }
            if (code == KeyEvent.VK_P) {
                gp.gameState = gp.pauseState;
            }
            if (code == KeyEvent.VK_ENTER) {
                enterPressed=true;
            }
            if (code == KeyEvent.VK_J) {
                this.jPressed=true;
            }
            if (code == KeyEvent.VK_K) {
                shotKeyPressed=true;
            }if (code == KeyEvent.VK_ESCAPE) {
                gp.gameState=gp.optionState;
            }

            //Debug
            if (code == KeyEvent.VK_T) {
                if (checkDrawTime == false) {
                    checkDrawTime = true;
                } else if (checkDrawTime == true) {
                    checkDrawTime = false;
                }
            }
        }

        //PAUSE STATE
        else if(gp.gameState==gp.pauseState)
        {
            if (code == KeyEvent.VK_P) {
                gp.gameState = gp.playState;
            }
        }

        //DIALOGUE STATE
        else if(gp.gameState==gp.dialogueState)
        {
            if(code == KeyEvent.VK_ENTER)
            {
                gp.gameState=gp.playState;
            }
        }

        //OPTIONS STATE
        else if(gp.gameState==gp.optionState)
        {
            optionState(code);
        }

        //GAME OVER STATE
        else if(gp.gameState==gp.gameOverState)
        {
            gameOverState(code);
        }

        //GAME WON STATE
        else if(gp.gameState==gp.gameWonState)
        {
            GameWon(code);
        }

    }
    public void optionState(int code) {

        if(code==KeyEvent.VK_ESCAPE)
        {
            gp.gameState=gp.playState;
        }
        if(code==KeyEvent.VK_ENTER)
        {
            enterPressed=true;
        }

        int maxCommandNum=0;
        switch (gp.ui.subState)
        {
            case 0: maxCommandNum=6;break;
            case 4: maxCommandNum=1;break;
        }
        if(code==KeyEvent.VK_W)
        {
            gp.ui.commandNum--;
            gp.playSE(8);
            if(gp.ui.commandNum<0)
            {
                gp.ui.commandNum=maxCommandNum;
            }
        }
        if(code==KeyEvent.VK_S)
        {
            gp.ui.commandNum++;
            gp.playSE(8);
            if(gp.ui.commandNum>maxCommandNum)
            {
                gp.ui.commandNum=0;
            }
        }

        if(code==KeyEvent.VK_A)
        {
            if(gp.ui.subState==0)
            {
                if(gp.ui.commandNum==1 && gp.music.volumeScale>0)
                {
                    gp.music.volumeScale--;
                    gp.music.checkVolume();
                    gp.playSE(8);
                }
                if(gp.ui.commandNum==2 && gp.se.volumeScale>0)
                {
                    gp.se.volumeScale--;
                    gp.playSE(8);
                }
            }
        }
        if (code==KeyEvent.VK_D)
        {
            if(gp.ui.subState==0)
            {
                if(gp.ui.commandNum==1 && gp.music.volumeScale<5)
                {
                    gp.music.volumeScale++;
                    gp.music.checkVolume();
                    gp.playSE(8);
                }
                if(gp.ui.commandNum==2 && gp.se.volumeScale<5)
                {
                    gp.se.volumeScale++;
                    gp.playSE(8);
                }
            }
        }


    }
    public void GameWon(int code) {

        if(code==KeyEvent.VK_ENTER)
        {
            gp.gameState=gp.titleState;
            gp.resetGame(true);
            gp.player.hasSoul=0;
        }

    }

    public void gameOverState(int code) {

        if(code==KeyEvent.VK_W)
        {
            gp.ui.commandNum--;
            if(gp.ui.commandNum<0)
            {
                gp.ui.commandNum=1;
            }
            gp.playSE(8);
        }
        if(code==KeyEvent.VK_S)
        {
            gp.ui.commandNum++;
            if(gp.ui.commandNum>1)
            {
                gp.ui.commandNum=0;
            }
            gp.playSE(8);
        }

        if(code==KeyEvent.VK_ENTER)
        {
            if(gp.ui.commandNum==0)
            {
                gp.gameState=gp.playState;
                gp.resetGame(false);
            }
            else if(gp.ui.commandNum==1)
            {
                gp.gameState=gp.titleState;
                gp.resetGame(true);
                gp.player.hasSoul=0;
            }
        }

    }

    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            this.upPressed = false;
        }

        if (code == KeyEvent.VK_S) {
            this.downPressed = false;
        }

        if (code == KeyEvent.VK_A) {
            this.leftPressed = false;
        }

        if (code == KeyEvent.VK_D) {
            this.rightPressed = false;
        }
        if (code == KeyEvent.VK_J) {
            this.jPressed=false;
        }
        if (code == KeyEvent.VK_K) {
            shotKeyPressed=false;
        }

    }
}
