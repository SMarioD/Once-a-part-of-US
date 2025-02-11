package entity;

import main.GamePanel;

import java.awt.*;
import java.util.Random;

public class NPC_OldMan extends Entity{
    public NPC_OldMan(GamePanel gp)
    {
        super(gp);

        direction="down";
        speed=2;

        solidArea=new Rectangle();
        solidArea.x=8;
        solidArea.y=16;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;
        solidArea.width=30;
        solidArea.height=30;

        getImage();
        setDialogue();
    }
    public void getImage()
    {

        right1=setup(0,0,"/npc/NPCSpriteSheet",gp.tileSize,gp.tileSize);
        right2=setup(1,0,"/npc/NPCSpriteSheet",gp.tileSize,gp.tileSize);
        right3=setup(2,0,"/npc/NPCSpriteSheet",gp.tileSize,gp.tileSize);
        right4=setup(3,0,"/npc/NPCSpriteSheet",gp.tileSize,gp.tileSize);
        right5=setup(0,0,"/npc/NPCSpriteSheet",gp.tileSize,gp.tileSize);
        right6=setup(1,0,"/npc/NPCSpriteSheet",gp.tileSize,gp.tileSize);
        left1=setup(4,0,"/npc/NPCSpriteSheet",gp.tileSize,gp.tileSize);
        left2=setup(5,0,"/npc/NPCSpriteSheet",gp.tileSize,gp.tileSize);
        left3=setup(6,0,"/npc/NPCSpriteSheet",gp.tileSize,gp.tileSize);
        left4=setup(7,0,"/npc/NPCSpriteSheet",gp.tileSize,gp.tileSize);
        left5=setup(4,0,"/npc/NPCSpriteSheet",gp.tileSize,gp.tileSize);
        left6=setup(5,0,"/npc/NPCSpriteSheet",gp.tileSize,gp.tileSize);

    }
    public void setDialogue()
    {
        dialogues[0]="Ce esti tu??";
        dialogues[1]="Hmmm, observ ca nici tu nu esti\nconstient de asta.\nChiar daca imi pari familiar. ";
        dialogues[2]="Eu sunt ultimul supravietuitor\nal acestei paduri.";
        dialogues[3]="Am reusit sa fac asta folosind\nmagie straveche, dar\nnu am putut sa ii ajut\nsi pe ceilalti.";
        dialogues[4]="Daca vrei sa scapi din padure\nsingurul drum este catre\nmarele copac, dar ai grija\norcii misuna in apropierea iesirii.";
    }
    public void setAction()
    {

        if(onPath==true)
        {

            int goalCol=(gp.player.worldX+gp.player.solidArea.x)/gp.tileSize;
            int goalRow=(gp.player.worldY+gp.player.solidArea.y)/gp.tileSize;

            searchPath(goalCol,goalRow);
        }
        else
        {
            actionLockCounter ++;
            if(actionLockCounter == 120) {
                Random random = new Random();
                int i = random.nextInt(100) + 1;

                if (i <= 25) {
                    direction = "up";
                }
                if (i > 25 && i <= 50) {
                    direction = "down";
                }
                if (i > 50 && i <= 75) {
                    direction = "left";
                }
                if (i > 75 && i <= 100) {
                    direction = "right";
                }
                actionLockCounter = 0;
            }
        }
    }

    public void speak()
    {
        super.speak();

        onPath =true;
    }

}
