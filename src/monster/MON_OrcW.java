package monster;

import entity.Entity;
import main.GamePanel;
import object.Obj_EnergyCrystal;
import object.Obj_Heart;
import object.Obj_Soul;

import java.util.Random;

public class MON_OrcW  extends Entity{
    GamePanel gp;

    public MON_OrcW(GamePanel gp) {
        super(gp);

        this.gp=gp;

        type=2;
        boss=false;
        name="OrcW";
        speed=2;
        attack=2;
        maxLife=4;
        life=maxLife;

        solidArea.x=8;
        solidArea.y=18;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;
        solidArea.width=32;
        solidArea.height=24;

        getImage();
        getOrcDyingImage();

    }

    public void getImage()
    {
        right1=setup(0,1,"/monster/OrcWSpriteSheet",gp.tileSize,gp.tileSize);
        right2=setup(1,1,"/monster/OrcWSpriteSheet",gp.tileSize,gp.tileSize);
        right3=setup(2,1,"/monster/OrcWSpriteSheet",gp.tileSize,gp.tileSize);
        right4=setup(3,1,"/monster/OrcWSpriteSheet",gp.tileSize,gp.tileSize);
        right5=setup(4,1,"/monster/OrcWSpriteSheet",gp.tileSize,gp.tileSize);
        right6=setup(5,1,"/monster/OrcWSpriteSheet",gp.tileSize,gp.tileSize);
        left1=setup(0,2,"/monster/OrcWSpriteSheet",gp.tileSize,gp.tileSize);
        left2=setup(1,2,"/monster/OrcWSpriteSheet",gp.tileSize,gp.tileSize);
        left3=setup(2,2,"/monster/OrcWSpriteSheet",gp.tileSize,gp.tileSize);
        left4=setup(3,2,"/monster/OrcWSpriteSheet",gp.tileSize,gp.tileSize);
        left5=setup(4,2,"/monster/OrcWSpriteSheet",gp.tileSize,gp.tileSize);
        left6=setup(5,2,"/monster/OrcWSpriteSheet",gp.tileSize,gp.tileSize);
    }
    public void getOrcDyingImage()
    {

        oDeath1=setup(0,0,"/monster/OrcWSpriteSheet",gp.tileSize,gp.tileSize);
        oDeath2=setup(1,0,"/monster/OrcWSpriteSheet",gp.tileSize,gp.tileSize);
        oDeath3=setup(2,0,"/monster/OrcWSpriteSheet",gp.tileSize,gp.tileSize);
        oDeath4=setup(3,0,"/monster/OrcWSpriteSheet",gp.tileSize,gp.tileSize);
        oDeath5=setup(4,0,"/monster/OrcWSpriteSheet",gp.tileSize,gp.tileSize);
        oDeath6=setup(5,0,"/monster/OrcWSpriteSheet",gp.tileSize,gp.tileSize);

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
    public void checkDrop()
    {
        //CAST A DIE
        int i=new Random().nextInt(100)+1;

        //SET THE MONSTER DROP
        if(i<60)
        {
            dropItem(new Obj_Soul(gp));
        }
        if(i>=60 && i<80)
        {
            dropItem(new Obj_Soul(gp));
            dropItem(new Obj_EnergyCrystal(gp));
        }
        if(i>80 && i<=100)
        {
            dropItem(new Obj_Soul(gp));
            dropItem(new Obj_Heart(gp));
        }
    }
}


