package main;

import java.awt.*;

public class EventHandler {
    GamePanel gp;
    EventRect eventRect[][][];

    int previousEventX,previousEventY;
    boolean canTouchEvent=true;

    public EventHandler(GamePanel gp)
    {
        this.gp=gp;
        eventRect= new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        int map=0;
        int col=0;
        int row=0;
        while(map<gp.maxMap && col<gp.maxWorldCol&&row<gp.maxWorldRow)
        {
            eventRect[map][col][row]=new EventRect();
            eventRect[map][col][row].x=23;
            eventRect[map][col][row].y=23;
            eventRect[map][col][row].width=2;
            eventRect[map][col][row].height=2;
            eventRect[map][col][row].eventRectDefaultX=eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY=eventRect[map][col][row].y;

            col++;
            if(col==gp.maxWorldCol)
            {
                col=0;
                row++;
                if(row==gp.maxWorldRow)
                {
                    row=0;
                    map++;
                }
            }
        }
    }

    public void checkEvent()
    {

        //Check if the player character is more the 1 tile away from the last event
        int xDistance=Math.abs(gp.player.worldX-previousEventX);
        int yDistance=Math.abs(gp.player.worldY-previousEventY);
        int distance=Math.max(xDistance,yDistance);
        if(distance>gp.tileSize)
        {
            canTouchEvent=true;
        }


        if(canTouchEvent==true)
        {
            if (hit(0,22, 34, "any") == true) {
                paperMistery1(gp.dialogueState);
            }
            else if (hit(0,23, 21, "any") == true) {
                paperMistery2(gp.dialogueState);
            }
            else if (hit(0,10, 20, "any") == true) {
                paperMistery3(gp.dialogueState);
            }
            else if (hit(0,41, 12, "any") == true) {
                paperMistery4(gp.dialogueState);
            }
            else if (hit(0,41, 13, "any") == true) {
                paperMistery5(gp.dialogueState);
            }
            else if (hit(0,21, 19, "any") == true) {
                healingPool(gp.dialogueState);
            }
            else if(hit(0,22, 6, "any") == true) {
//                if(gp.player.hasSoul>=9) {
                    teleport(1, 20, 43);
               // }
            }
            else if(hit(0,23, 6, "any") == true) {
//                if(gp.player.hasSoul>=9) {
                    teleport(1, 21, 43);
              //  }
            }
            else if(hit(1,13, 5, "any") == true) {
//                if(gp.player.hasSoul>=26) {
                    teleport(2, 25, 10);
            //    }
            }
            else if(hit(1,14, 5, "any") == true) {
//                if(gp.player.hasSoul>=26) {
                    teleport(2, 26, 10);
                }
          //  }
        }


    }
    public boolean hit(int map,int col,int row,String reqDirection)
    {
        boolean hit=false;

        if(map==gp.currentMap) {
            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
            eventRect[map][col][row].x = col * gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row * gp.tileSize + eventRect[map][col][row].y;

            if (gp.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false) {
                if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                    hit = true;

                    previousEventX = gp.player.worldX;
                    previousEventY = gp.player.worldY;
                }
            }

            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
        }

        return hit;
    }
    public void paperMistery1(int gameState)
    {
        if(gp.keyH.enterPressed==true) {
            gp.gameState = gameState;
            gp.ui.currentDialogue = "JURNALUL VANATORULUI:\nCeva ciudat se intampla in padure.\nAm reusit sa tin departe bestiile,dar\nparca //~~//";
            canTouchEvent = false;
        }

    }
    public void paperMistery2(int gameState)
    {
        if(gp.keyH.enterPressed==true) {
            gp.gameState = gameState;
            gp.ui.currentDialogue = "JURNALUL VANATORULUI:\nCopacul a cazut, bestiile incep sa atace!\nCAD CRENGI//~~//";
            canTouchEvent = false;
        }

    }
    public void paperMistery3(int gameState)
    {
        if(gp.keyH.enterPressed==true) {
            gp.gameState = gameState;
            gp.ui.currentDialogue = "Scriu asta in cazul in care supravietuitorii\ngasesc locul asta inaintea monstrilor\nV nu s-a intors, o sa plecam singuri\ndupa mancare.";
            canTouchEvent = false;
        }

    }
    public void paperMistery4(int gameState)
    {
        if(gp.keyH.enterPressed==true) {
            gp.gameState = gameState;
            gp.ui.currentDialogue = "JURNALUL VANATORULUI:\nNu mai rezist cu frica asta,\nam pierdut prea multi oameni,DE DATA\nASTA, MERG EU IN CASA LOR!";
            canTouchEvent = false;
        }

    }
    public void paperMistery5(int gameState)
    {
        if(gp.keyH.enterPressed==true) {
            gp.gameState = gameState;
            gp.ui.currentDialogue = "JURNALUL VANATORULUI:\nSituatia este mai grava decat credeam\nnimeni nu s-a mai intors si din cate am\nvazut, se pare ca in Copac//~~//";
            canTouchEvent = false;
        }

    }
    public void damagePit(int gameState)
    {
        gp.gameState = gameState;
        gp.ui.currentDialogue = "You have come, but you will not leave\n EVER.";
        gp.player.life-=1;
        //eventRect[col][row].eventDone =true;
        canTouchEvent=false;
    }
    public void healingPool(int gameState)
    {
        if(gp.keyH.enterPressed==true)
        {
            gp.gameState=gameState;
            gp.ui.currentDialogue="Ai mancat scoarta unui copac medicinal.\n(The progress has been saved)";
            gp.player.life=gp.player.maxLife;
            gp.player.energy=gp.player.maxEnergy;
        }

    }

    public void teleport(int map,int col,int row)
    {
        gp.currentMap=map;
        gp.player.worldX=gp.tileSize*col;
        gp.player.worldY=gp.tileSize*row;
        previousEventX=gp.player.worldX;
        previousEventY=gp.player.worldY;
        canTouchEvent=false;
        gp.playSE(10);
    }


}
