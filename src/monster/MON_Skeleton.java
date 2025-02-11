package monster;

import entity.Entity;
import main.GamePanel;
import object.Obj_EnergyCrystal;
import object.Obj_Heart;
import object.Obj_Soul;
import object.Obj_YourSoul;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class MON_Skeleton extends Entity {

    GamePanel gp;
    public MON_Skeleton(GamePanel gp) {
        super(gp);

        this.gp=gp;

        type=2;
        boss=true;
        name="Salvatorul";
        speed=2;
        attack=3;
        maxLife=12;
        life=maxLife;

        int size=gp.tileSize*5;
        solidArea.x=48;
        solidArea.y=48;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;
        solidArea.width=size-48*2;
        solidArea.height=size-48;
        attackArea.width=170;
        attackArea.height=170;

        getImage();
        getAttackImage();
        getOrcDyingImage();

    }

    public void getAttackImage()
    {
        int i=5;

        attackUp1=setup(2,3,"/monster/SkeletonSpritesSheet",gp.tileSize*i,gp.tileSize*i*2);
        attackUp2=setup(3,3,"/monster/SkeletonSpritesSheet",gp.tileSize*i,gp.tileSize*i*2);
        attackDown1=setup(0,3,"/monster/SkeletonSpritesSheet",gp.tileSize*i,gp.tileSize*i*2);
        attackDown2=setup(1,3,"/monster/SkeletonSpritesSheet",gp.tileSize*i,gp.tileSize*i*2);
        attackLeft1=setup(0,4,"/monster/SkeletonSpritesSheet",gp.tileSize*i*2,gp.tileSize*i);
        attackLeft2=setup(1,4,"/monster/SkeletonSpritesSheet",gp.tileSize*i*2,gp.tileSize*i);
        attackRight1=setup(2,4,"/monster/SkeletonSpritesSheet",gp.tileSize*i*2,gp.tileSize*i);
        attackRight2=setup(3,4,"/monster/SkeletonSpritesSheet",gp.tileSize*i*2,gp.tileSize*i);
    }


    public void getImage()
    {
        int i=5;

        right1=setup(0,1,"/monster/SkeletonSpritesSheet",gp.tileSize*i,gp.tileSize*i);
        right2=setup(1,1,"/monster/SkeletonSpritesSheet",gp.tileSize*i,gp.tileSize*i);
        right3=setup(2,1,"/monster/SkeletonSpritesSheet",gp.tileSize*i,gp.tileSize*i);
        right4=setup(3,1,"/monster/SkeletonSpritesSheet",gp.tileSize*i,gp.tileSize*i);
        right5=setup(0,1,"/monster/SkeletonSpritesSheet",gp.tileSize*i,gp.tileSize*i);
        right6=setup(1,1,"/monster/SkeletonSpritesSheet",gp.tileSize*i,gp.tileSize*i);
        left1=setup(0,2,"/monster/SkeletonSpritesSheet",gp.tileSize*i,gp.tileSize*i);
        left2=setup(1,2,"/monster/SkeletonSpritesSheet",gp.tileSize*i,gp.tileSize*i);
        left3=setup(2,2,"/monster/SkeletonSpritesSheet",gp.tileSize*i,gp.tileSize*i);
        left4=setup(3,2,"/monster/SkeletonSpritesSheet",gp.tileSize*i,gp.tileSize*i);
        left5=setup(0,2,"/monster/SkeletonSpritesSheet",gp.tileSize*i,gp.tileSize*i);
        left6=setup(1,2,"/monster/SkeletonSpritesSheet",gp.tileSize*i,gp.tileSize*i);
    }
    public void getOrcDyingImage()
    {
        int i=5;

        oDeath1=setup(0,0,"/monster/SkeletonSpritesSheet",gp.tileSize*i,gp.tileSize*i);
        oDeath2=setup(1,0,"/monster/SkeletonSpritesSheet",gp.tileSize*i,gp.tileSize*i);
        oDeath3=setup(2,0,"/monster/SkeletonSpritesSheet",gp.tileSize*i,gp.tileSize*i);
        oDeath4=setup(3,0,"/monster/SkeletonSpritesSheet",gp.tileSize*i,gp.tileSize*i);
        oDeath5=setup(3,0,"/monster/SkeletonSpritesSheet",gp.tileSize*i,gp.tileSize*i);
        oDeath6=setup(3,0,"/monster/SkeletonSpritesSheet",gp.tileSize*i,gp.tileSize*i);

    }

    public void update()
    {
        super.update();

        int xDistance=Math.abs(worldX-gp.player.worldX);
        int yDistance=Math.abs(worldY-gp.player.worldY);
        int tileDistance=(xDistance+yDistance)/gp.tileSize;

        if(onPath==false && tileDistance <5)
        {
            int i= new Random().nextInt(100)+1;
            if(i>50)
            {
                onPath=true;
            }
        }

        if(attacking==true)
        {
            attacking();
        }
    }

    public void setAction()
    {
        if(getTileDistance(gp.player)<40)
        {
            moveTowardPlayer(60);
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

        if(attacking==false)
        {
            checkAttackOrNot(60,gp.tileSize*10,gp.tileSize*5);
        }
    }
    public void checkDrop()
    {
        //CAST A DIE

            dropItem(new Obj_YourSoul(gp));
    }
}
