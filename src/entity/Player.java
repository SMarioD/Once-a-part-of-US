package entity;

import main.GamePanel;
import main.KeyHandler;
import object.Obj_Rock;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {
    private static Player instance=null;
    KeyHandler keyH;
    //MouseListener ml;
    public final int screenX;
    public final int screenY;
    public int hasKey=0;
    public int hasSoul=0;

    private Player(GamePanel gp, KeyHandler keyH) {
        super(gp);

        this.keyH = keyH;
        //this.ml=ml;


        screenX=gp.screenWidth/2 -(gp.tileSize/2);
        screenY=gp.screeHeight/2-(gp.tileSize/2);

        solidArea=new Rectangle();
        solidArea.x=8;
        solidArea.y=18;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;
        solidArea.width=32;
        solidArea.height=24;

        attackArea.width=26;
        attackArea.height=36;

        this.setDefaultValues();


    }
    public static Player getInstance(GamePanel gp, KeyHandler keyH)// SINGLETONE
    {
        if (instance == null) {
            instance = new Player(gp, keyH);
        }
        return instance;
    }

    public void setDefaultValues() {
        //mapa 1
        this.worldX = gp.tileSize*23;
        this.worldY = gp.tileSize*34;

        this.speed = 4;
        this.direction = "down";

        //PLAYER STATUS
        maxLife=6;
        life=maxLife;
        maxEnergy=4;
        energy=maxEnergy;
        attack=1;
        projectile=new Obj_Rock(gp);

        getPlayerImage();
        getPlayerAttackImage();
    }

    public void setDefaultPositions()
    {
        worldX = gp.tileSize*23;
        worldY = gp.tileSize*34;
        direction = "down";
    }

    public void restoreLifeAndEnergy()
    {
        life=maxLife;
        energy=maxEnergy;
        invincible=false;
    }

    public void getPlayerImage()
    {

        right1=setup(0,1,"/player/PlayerSpriteSheet",gp.tileSize,gp.tileSize);
        right2=setup(1,1,"/player/PlayerSpriteSheet",gp.tileSize,gp.tileSize);
        right3=setup(2,1,"/player/PlayerSpriteSheet",gp.tileSize,gp.tileSize);
        right4=setup(3,1,"/player/PlayerSpriteSheet",gp.tileSize,gp.tileSize);
        right5=setup(4,1,"/player/PlayerSpriteSheet",gp.tileSize,gp.tileSize);
        right6=setup(5,1,"/player/PlayerSpriteSheet",gp.tileSize,gp.tileSize);
        left1=setup(0,2,"/player/PlayerSpriteSheet",gp.tileSize,gp.tileSize);
        left2=setup(1,2,"/player/PlayerSpriteSheet",gp.tileSize,gp.tileSize);
        left3=setup(2,2,"/player/PlayerSpriteSheet",gp.tileSize,gp.tileSize);
        left4=setup(3,2,"/player/PlayerSpriteSheet",gp.tileSize,gp.tileSize);
        left5=setup(4,2,"/player/PlayerSpriteSheet",gp.tileSize,gp.tileSize);
        left6=setup(5,2,"/player/PlayerSpriteSheet",gp.tileSize,gp.tileSize);
        idle1=setup(0,0,"/player/PlayerSpriteSheet",gp.tileSize,gp.tileSize);
        idle2=setup(1,0,"/player/PlayerSpriteSheet",gp.tileSize,gp.tileSize);
        idle3=setup(2,0,"/player/PlayerSpriteSheet",gp.tileSize,gp.tileSize);
        idle4=setup(3,0,"/player/PlayerSpriteSheet",gp.tileSize,gp.tileSize);
        idle5=setup(4,0,"/player/PlayerSpriteSheet",gp.tileSize,gp.tileSize);
        idle6=setup(5,0,"/player/PlayerSpriteSheet",gp.tileSize,gp.tileSize);

    }

    public void getPlayerAttackImage()
    {

        attackUp1=setup(0,0,"/player/PlayerAttackSpritesSheet",gp.tileSize,gp.tileSize*2);
        attackUp2=setup(1,0,"/player/PlayerAttackSpritesSheet",gp.tileSize,gp.tileSize*2);
        attackDown1=setup(2,0,"/player/PlayerAttackSpritesSheet",gp.tileSize,gp.tileSize*2);
        attackDown2=setup(3,0,"/player/PlayerAttackSpritesSheet",gp.tileSize,gp.tileSize*2);
        attackLeft1=setup(0,1,"/player/PlayerAttackSpritesSheet",gp.tileSize*2,gp.tileSize);
        attackLeft2=setup(1,1,"/player/PlayerAttackSpritesSheet",gp.tileSize*2,gp.tileSize);
        attackRight1=setup(2,1,"/player/PlayerAttackSpritesSheet",gp.tileSize*2,gp.tileSize);
        attackRight2=setup(3,1,"/player/PlayerAttackSpritesSheet",gp.tileSize*2,gp.tileSize);

    }


    public void update() {


        if(attacking==true)
        {
            attacking();
        }
        else if(keyH.upPressed==true||keyH.downPressed==true ||keyH.leftPressed==true || keyH.rightPressed==true || keyH.enterPressed==true || keyH.jPressed==true)
        {
            // Handle player movement
            if (keyH.upPressed) {
                direction = "up";
            } else if (keyH.downPressed) {

                direction = "down";
            } else if (keyH.leftPressed) {

                direction = "left";
            } else if (keyH.rightPressed) {

                direction = "right";
            }

            //CHECK TILE COLLISION
            collisionOn=false;
            gp.cChecker.checkTile(this);

            //Check npc collision
            int npcIndex=gp.cChecker.checkEntity(this,gp.npc);
            interactNPC(npcIndex);

            //Check object collision
            int objIndex = gp.cChecker.checkObject(this,true);
            pickUpObject(objIndex);

            //CHECK MONSTER COLLISIONS
            int monsterIndex=gp.cChecker.checkEntity(this,gp.monster);
            contactMonster(monsterIndex);

            //Check event
            gp.eHandler.checkEvent();


            if(collisionOn==false && keyH.enterPressed==false)
            {
                switch (direction)
                {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

            gp.keyH.enterPressed=false;

            spriteCounter++;
            if (spriteCounter>12)
            {
                if(spriteNum==1)
                {
                    spriteNum=2;
                } else if (spriteNum==2) {
                    spriteNum = 3;
                }else if (spriteNum==3) {
                    spriteNum=4;
                }else if (spriteNum==4) {
                    spriteNum=5;
                }else if (spriteNum==5) {
                    spriteNum = 6;
                }else if (spriteNum==6) {
                    spriteNum=1;
                }
                spriteCounter=0;
            }
        }
        else{
            direction="Idle";
            spriteCounter++;
            if (spriteCounter>12)
            {
                if(spriteNum==1)
                {
                    spriteNum=2;
                } else if (spriteNum==2) {
                    spriteNum = 3;
                }else if (spriteNum==3) {
                    spriteNum=4;
                }else if (spriteNum==4) {
                    spriteNum=5;
                }else if (spriteNum==5) {
                    spriteNum = 6;
                }else if (spriteNum==6) {
                    spriteNum=1;
                }
                spriteCounter=0;
            }
        }

        if(gp.keyH.shotKeyPressed == true && projectile.alive == false && shotAvailablCounter ==30 && projectile.haveResource(this)==true)
        {
            //SET DEFAULT COORDINATES, DIRECTION AND USER
            projectile.set(worldX,worldY,direction,true,this);

            //Subtract the cost

                projectile.subtractResource(this);

            //ADD IT TO THE
            gp.projectileList.add(projectile);

            shotAvailablCounter=0;

            gp.playSE(7);

        }

        //This needs to be outside of key if statement!
        if (invincible==true)
        {
            invincibleCounter++;
            if(invincibleCounter>60)
            {
                invincible=false;
                invincibleCounter=0;
            }
        }
        if(shotAvailablCounter <30)
        {
            shotAvailablCounter++;
        }
        if(life>maxLife)
        {
            life=maxLife;
        }
        if(energy>maxEnergy)
        {
            energy=maxEnergy;
        }
        if(life<=0)
        {
            gp.gameState=gp.gameOverState;
            gp.playSE(9);
        }
    }

    public void attacking()
    {
        spriteCounter++;
        if(spriteCounter<=5)
        {
            spriteNum =1;
        }
        if(spriteCounter>5 && spriteCounter<=25)
        {
            spriteNum=2;

            //Save the current worldX, worldY, solidArea
            int currentWorldX=worldX;
            int currentWorldY=worldY;
            int solidAreaWidth=solidArea.width;
            int solidAreaHeight=solidArea.height;

            //Adjust player's worldX/Y for the attackArea
            switch (direction)
            {
                case"up":
                    worldY-=attackArea.height;
                    break;
                case "down":
                    worldY+=attackArea.height;
                    break;
                case "left":
                    worldX-=attackArea.width;
                    break;
                case "right":
                    worldX+=attackArea.width;
                    break;
            }

            // attackArea become solidArea
            solidArea.width = attackArea.width;
            solidArea.height=attackArea.height;

            // Check monster collision with the update worldX,worldY and solidArea
            int monsterIndex=gp.cChecker.checkEntity(this,gp.monster);
            damageMonster(monsterIndex,attack);

            //After checking coliision, resize the original data
            worldX=currentWorldX;
            worldY=currentWorldY;
            solidArea.width=solidAreaWidth;
            solidArea.height=solidAreaHeight;
        }
        if(spriteCounter>25)
        {
            spriteNum=1;
            spriteCounter=0;
            attacking=false;
        }
    }

    public void pickUpObject(int i)
    {
        if(i!=999)
        {
            String objectName=gp.obj[gp.currentMap][i].name;

            switch(objectName)
            {
                case "Key":
                    gp.playSE(1);
                    hasKey++;
                    gp.obj[gp.currentMap][i]=null;
                    break;
                case "Door":
                    if(hasKey>0)
                    {
                        gp.playSE(3);
                        gp.obj[gp.currentMap][i]=null;
                        hasKey--;
                    }
                    break;
                case "Soul":
                    hasSoul++;
                    gp.obj[gp.currentMap][i]=null;
                    break;
                case "Boots":
                    gp.playSE(2);
                    speed+=2;
                    gp.obj[gp.currentMap][i]=null;
                    break;
                case "Heart":
                    gp.playSE(2);
                    life+=2;
                    gp.obj[gp.currentMap][i]=null;
                    break;
                case "Energy Crystal":
                    gp.playSE(2);
                    energy+=1;
                    gp.obj[gp.currentMap][i]=null;
                    break;
                case "Chest":
                    if(hasKey>0)
                    {
                        gp.playSE(3);
                        gp.obj[gp.currentMap][i]=null;
                        hasKey--;
                    }
                    break;
                case "YourSoul":
                    gp.gameState=gp.gameWonState;
                    break;
            }

        }
    }

    public void interactNPC(int i)
    {
        if(i!=999)
        {
            if(gp.keyH.enterPressed==true) {
                gp.gameState = gp.dialogueState;
                gp.npc[gp.currentMap][i].speak();
            }
        }
        if(i==999)
        {
            if(gp.keyH.jPressed==true)
            {
                //System.out.println(" APASARE IN attacking");
                attacking=true;
            }
        }
    }


    public void contactMonster(int i)
    {
        if(i!=999) {
            if(invincible==false)
            {
                if(gp.monster[gp.currentMap][i].name=="Orc") {
                    gp.playSE(6);
                    life -= 1;
                    invincible = true;
                }
                if(gp.monster[gp.currentMap][i].name=="OrcW") {
                    gp.playSE(6);
                    life -= 2;
                    invincible = true;
                }

            }
        }

    }

    public void damageMonster(int i,int attack)
    {
        if(i!=999)
        {
            if(gp.monster[gp.currentMap][i].invincible==false)
            {
                gp.playSE(5);
                gp.monster[gp.currentMap][i].life-=attack;
                gp.monster[gp.currentMap][i].invincible=true;

                if(gp.monster[gp.currentMap][i].life<=0){
                    gp.monster[gp.currentMap][i].dying=true;
                }
            }
        }

    }

    public void draw(Graphics2D g2) {
        // Your draw logic remains unchanged
        BufferedImage image = null;
        int tempScreenX=screenX;
        int tempScreenY=screenY;

        switch (this.direction) {
            case "up":
                if(attacking==false) {
                    if (spriteNum == 1) {
                        image = this.left1;
                    }
                    if (spriteNum == 2) {
                        image = this.left2;
                    }
                    if (spriteNum == 3) {
                        image = this.left3;
                    }
                    if (spriteNum == 4) {
                        image = this.left4;
                    }
                    if (spriteNum == 5) {
                        image = this.left5;
                    }
                    if (spriteNum == 6) {
                        image = this.left6;
                    }
                }
                if(attacking==true)
                {
                    tempScreenY =screenY -gp.tileSize;
                    if (spriteNum == 1) {
                        image = this.attackUp1;
                    }
                    if (spriteNum == 2) {
                        image = this.attackUp2;
                    }
                }
                break;
            case "down":
                if(attacking==false) {
                    if (spriteNum == 1) {
                        image = this.right1;
                    }
                    if (spriteNum == 2) {
                        image = this.right2;
                    }
                    if (spriteNum == 3) {
                        image = this.right3;
                    }
                    if (spriteNum == 4) {
                        image = this.right4;
                    }
                    if (spriteNum == 5) {
                        image = this.right5;
                    }
                    if (spriteNum == 6) {
                        image = this.right6;
                    }
                }
                if (attacking==true)
                {
                    if (spriteNum == 1) {
                        image = this.attackDown1;
                    }
                    if (spriteNum == 2) {
                        image = this.attackDown2;
                    }
                }
                break;
            case "left":
                if(attacking==false) {
                    if (spriteNum == 1) {
                        image = this.left1;
                    }
                    if (spriteNum == 2) {
                        image = this.left2;
                    }
                    if (spriteNum == 3) {
                        image = this.left3;
                    }
                    if (spriteNum == 4) {
                        image = this.left4;
                    }
                    if (spriteNum == 5) {
                        image = this.left5;
                    }
                    if (spriteNum == 6) {
                        image = this.left6;
                    }
                }
                if(attacking==true)
                {
                    tempScreenX=screenX-gp.tileSize;
                    if (spriteNum == 1) {
                        image = this.attackLeft1;
                    }
                    if (spriteNum == 2) {
                        image = this.attackLeft2;
                    }
                }
                break;
            case "right":
                if(attacking==false) {
                    if (spriteNum == 1) {
                        image = this.right1;
                    }
                    if (spriteNum == 2) {
                        image = this.right2;
                    }
                    if (spriteNum == 3) {
                        image = this.right3;
                    }
                    if (spriteNum == 4) {
                        image = this.right4;
                    }
                    if (spriteNum == 5) {
                        image = this.right5;
                    }
                    if (spriteNum == 6) {
                        image = this.right6;
                    }
                }
                if (attacking==true)
                {
                    if (spriteNum == 1) {
                        image = this.attackRight1;
                    }
                    if (spriteNum == 2) {
                        image = this.attackRight2;
                    }
                }
                break;
            case "Idle":
                if(attacking==false) {
                    if (spriteNum == 1) {
                        image = this.idle1;
                    }
                    if (spriteNum == 2) {
                        image = this.idle2;
                    }
                    if (spriteNum == 3) {
                        image = this.idle3;
                    }
                    if (spriteNum == 4) {
                        image = this.idle4;
                    }
                    if (spriteNum == 5) {
                        image = this.idle5;
                    }
                    if (spriteNum == 6) {
                        image = this.idle6;
                    }
                    break;
                }
                if (attacking==true)
                {
                    if (spriteNum == 1) {
                        image = this.attackRight1;
                    }
                    if (spriteNum == 2) {
                        image = this.attackRight2;
                    }
                }
        }

        if(invincible==true)
        {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.4f));
        }

        g2.drawImage(image, tempScreenX, tempScreenY, null);

        //Reset alpha

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));

    }
}
