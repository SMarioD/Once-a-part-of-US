

package entity;

import main.GamePanel;
import main.UtilityTool;
import monster.MON_Skeleton;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Entity {
    GamePanel gp;


    public BufferedImage right1,right2,right3,right4,right5,right6,left1,left2,left3,left4,left5,left6,idle1,idle2,idle3,idle4,idle5,idle6;
    public BufferedImage oDeath1,oDeath2,oDeath3,oDeath4,oDeath5,oDeath6;
    public BufferedImage attackUp1,attackUp2,attackDown1,attackDown2,attackLeft1,attackLeft2,attackRight1,attackRight2;
    public BufferedImage image,image2,image3;
    public Rectangle solidArea= new Rectangle(0,0,48,48);
    public Rectangle attackArea=new Rectangle(0,0,0,0);
    public int solidAreaDefaultX,solidAreaDefaultY;
    public boolean collision=false;
    String dialogues[]=new String[20];
    public final int type_obstacle=1;

    //STATE
    public int worldX;
    public int worldY;
    public String direction="down";
    int dialogueIndex=0;
    public int spriteNum=1;
    public boolean collisionOn=false;
    public boolean invincible=false;
    public boolean attacking=false;
    public boolean alive=true;
    public boolean dying=false;
    public boolean hpBarOn=false;
    public boolean onPath=false;


    //COUNTER
    public int spriteCounter=0;
    public int actionLockCounter =0;
    public int invincibleCounter=0;
    public int shotAvailablCounter=0;
    int dyingCounter=0;
    public int hpBarCounter=0;


    // CHARACTER ATRIBUTES
    public String name;
    public int type;//0-player   1-npc  2-monster
    public int speed;
    public int maxLife;
    public int life;
    public int attack;
    public int maxEnergy;
    public int energy;
    public Projectile projectile;
    public boolean boss;

    //ITEM ATTRIBUTES
    public int useCost;


    public Entity(GamePanel gp)
    {
        this.gp=gp;
    }

    public int getScreenX()
    {
        int screenX=worldX-gp.player.worldX+gp.player.screenX;
        return screenX;
    }
    public int getScreenY()
    {
        int screenY=worldY-gp.player.worldY+gp.player.screenY;
        return screenY;
    }

    public int getCenterX()
    {
        int centerX=worldX+left1.getWidth()/2;
        return centerX;
    }

    public int getCenterY()
    {
        int centerY=worldY+right1.getHeight()/2;
        return centerY;
    }

    public int getXdistance(Entity target)
    {
        int xDistance=Math.abs(getCenterX()-target.getCenterX());
        return xDistance;
    }

    public int getYdistance(Entity target)
    {
        int yDistance=Math.abs(getCenterY()-target.getCenterY());
        return yDistance;
    }

    public int getTileDistance(Entity target)
    {
        int tileDistance=getXdistance(target)+getYdistance(target);
        return tileDistance;
    }

    public int getGoalCol(Entity target)
    {
        int goalCol=(target.worldX+target.solidArea.x)/gp.tileSize;
        return goalCol;
    }
    public int getGoalRow(Entity target)
    {
        int goalRow=(target.worldY+target.solidArea.y)/gp.tileSize;
        return goalRow;
    }

    public void setAction()
    {

    }

    public void speak()
    {
        if(dialogues[dialogueIndex]==null)
        {
            dialogueIndex=0;
        }
        gp.ui.currentDialogue=dialogues[dialogueIndex];
        dialogueIndex++;

        switch (gp.player.direction)
        {
            case "up":
                direction="down";
                break;
            case "down":
                direction="up";
                break;
            case "left":
                direction="right";
                break;
            case "right":
                direction="left";
                break;

        }
    }

    public void checkDrop()
    {

    }
    public void dropItem(Entity droppedItem)
    {
        for(int i=0;i<gp.obj[1].length;i++)
        {
            if(gp.obj[gp.currentMap][i]==null)
            {
                gp.obj[gp.currentMap][i]=droppedItem;
                gp.obj[gp.currentMap][i].worldX=worldX;
                gp.obj[gp.currentMap][i].worldY=worldY;
                break;
            }
        }
    }


    public void checkCollision()
    {
        collisionOn=false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this,false);
        gp.cChecker.checkEntity(this,gp.npc);
        gp.cChecker.checkEntity(this,gp.monster);
        boolean contactPlayer=gp.cChecker.checkPlayer(this);

        if(this.type==2 && contactPlayer==true){
            damagePlayer(attack);
        }
        if(collisionOn==false)
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

    }


    public void update()
    {

            if(attacking==true)
            {
                attacking();
            }
            else
            {
                setAction();
                checkCollision();
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
            if (invincible==true)
            {
                invincibleCounter++;
                if(invincibleCounter>40)
                {
                    invincible=false;
                    invincibleCounter=0;
                }
            }
            if(shotAvailablCounter <30)
            {
                shotAvailablCounter++;
            }



    }

    public void moveTowardPlayer(int interval)
    {
        actionLockCounter++;
        if(actionLockCounter>interval)
        {
            if(getXdistance(gp.player)>getYdistance(gp.player))
            {
                if(gp.player.getCenterX()<getCenterX())
                {
                    direction="left";
                }
                else
                {
                    direction="right";
                }
            }
            else if(getXdistance(gp.player)<getYdistance(gp.player))
            {
                if(gp.player.getCenterY()<getCenterY())
                {
                    direction="up";
                }
                else
                {
                    direction="down";
                }
            }
            actionLockCounter=0;
        }
    }

    public void attacking()
    {
        spriteCounter++;
        if(spriteCounter<=80)
        {
            spriteNum =1;
        }
        if(spriteCounter>80 && spriteCounter<=165)
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
            if(gp.cChecker.checkPlayer(this)==true)
            {
                damagePlayer(attack);
            }

            //After checking coliision, resize the original data
            worldX=currentWorldX;
            worldY=currentWorldY;
            solidArea.width=solidAreaWidth;
            solidArea.height=solidAreaHeight;
        }
        if(spriteCounter>165)
        {
            spriteNum=1;
            spriteCounter=0;
            attacking=false;
        }
    }

    public void checkAttackOrNot(int rate,int straight,int horizontal)
    {

        boolean targetInRange=false;
        int xDis=getXdistance(gp.player);
        int yDis=getYdistance(gp.player);

        switch (direction){
            case "up":
                if(gp.player.getCenterY()<getCenterY() && yDis <straight && xDis<horizontal)
                {
                    targetInRange=true;
                }
                break;
            case "down":
                if(gp.player.getCenterY()>getCenterY() && yDis <straight && xDis<horizontal)
                {
                    targetInRange=true;
                }
                break;
            case "left":
                if(gp.player.getCenterX()<getCenterX() && xDis <straight && yDis<horizontal)
                {
                    targetInRange=true;
                }
                break;
            case "right":
                if(gp.player.getCenterX()>getCenterX() && xDis <straight && yDis<horizontal)
                {
                    targetInRange=true;
                }
                break;
        }

        if(targetInRange==true)
        {
            //Check if it initiates an attack
            int i=new Random().nextInt(rate);
            if(i==0)
            {
                attacking=true;
                spriteNum=1;
                spriteCounter=0;
                shotAvailablCounter=0;

            }
        }

    }


    public void damagePlayer(int attack)
    {
            if(gp.player.invincible==false)
            {
                gp.playSE(6);
                gp.player.life-=attack;
                gp.player.invincible=true;
            }
    }

    public boolean inCamera()
    {
        boolean inCamera=false;
        if(worldX +gp.tileSize*5>gp.player.worldX-gp.player.screenX
                && worldX -gp.tileSize<gp.player.worldX+gp.player.screenX
                && worldY+gp.tileSize*5>gp.player.worldY-gp.player.screenY
                && worldY -gp.tileSize< gp.player.worldY+gp.player.screenY)
        {
            inCamera=true;
        }
        return inCamera;
    }

    public void draw(Graphics2D g2)
    {
        BufferedImage image = null;

        if(inCamera()==true){

            int tempScreenX=getScreenX();
            int tempScreenY=getScreenY();

            switch (this.direction) {
                case "up":
                    if (attacking == false) {
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
                    if (attacking == true) {
                        tempScreenY = getScreenY() - right1.getHeight();
                        if (spriteNum == 1) {
                            image = this.attackUp1;
                        }
                        if (spriteNum == 2) {
                            image = this.attackUp2;
                        }
                    }
                    break;
                case "down":
                    if (attacking == false) {
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
                    if (attacking == true) {
                        if (spriteNum == 1) {
                            image = this.attackDown1;
                        }
                        if (spriteNum == 2) {
                            image = this.attackDown2;
                        }
                    }
                    break;
                case "left":
                    if (attacking == false) {
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
                    if (attacking == true) {
                        tempScreenX = getScreenX() - left1.getWidth();
                        if (spriteNum == 1) {
                            image = this.attackLeft1;
                        }
                        if (spriteNum == 2) {
                            image = this.attackLeft2;
                        }
                    }
                    break;
                case "right":
                    if (attacking == false) {
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
                    if (attacking == true) {
                        if (spriteNum == 1) {
                            image = this.attackRight1;
                        }
                        if (spriteNum == 2) {
                            image = this.attackRight2;
                        }
                    }
                    break;
                case "Idle":
                    if (attacking == false) {
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
                    if (attacking == true) {
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
                hpBarOn=true;
                hpBarCounter=0;
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.4f));
            }
            if(dying==true)
            {
                dyingAnimation(g2);
            }
            else {

                g2.drawImage(image, tempScreenX, tempScreenY,null);
            }
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
        }
    }

    public void dyingAnimation(Graphics2D g2)
    {
        BufferedImage image = null;

        int screenX=worldX-gp.player.worldX + gp.player.screenX;
        int screenY=worldY-gp.player.worldY+gp.player.screenY;
        dyingCounter++;
        int i=1;
        if(gp.currentMap==2)
        {
            i=5;
        }
        if(dyingCounter<=15)
        {
            image = this.oDeath1;
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
            g2.drawImage(image, screenX, screenY, gp.tileSize*i, gp.tileSize*i, null);

        }
        if(dyingCounter>15 && dyingCounter<=30)
        {
            image = this.oDeath2;
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
            g2.drawImage(image, screenX, screenY, gp.tileSize*i, gp.tileSize*i, null);

        }
        if(dyingCounter>30 && dyingCounter<=45)
        {
            image = this.oDeath3;
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
            g2.drawImage(image, screenX, screenY, gp.tileSize*i, gp.tileSize*i, null);
        }
        if(dyingCounter>45 && dyingCounter<=60)
        {
            image = this.oDeath4;
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
            g2.drawImage(image, screenX, screenY, gp.tileSize*i, gp.tileSize*i, null);
        }
        if(dyingCounter>60 && dyingCounter<=75)
        {
            image = this.oDeath5;
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
            g2.drawImage(image, screenX, screenY, gp.tileSize*i, gp.tileSize*i, null);
        }
        if(dyingCounter>75 && dyingCounter<=90)
        {
            image = this.oDeath6;
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
            g2.drawImage(image, screenX, screenY, gp.tileSize*i, gp.tileSize*i, null);

        }
        if(dyingCounter>90)
        {
            dying=false;
            alive=false;
        }
    }

    public BufferedImage setup(int indexX, int indexY, String path, int width, int height) {
        UtilityTool uTool=new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(path + ".png")).getSubimage(indexX * gp.tileSize, indexY * gp.tileSize, gp.tileSize, gp.tileSize);;
            image=uTool.scaleImage(image,width,height);
        } catch (IOException e) {
            System.out.println("image failed: "+path);
            e.printStackTrace();
        }
        catch(RasterFormatException rs)
        {
            System.out.println("image failed: "+path);
            System.err.println(""+(indexX * gp.tileSize)+","+(indexY * gp.tileSize)+","+width+","+height);
            System.exit(1);
        }
        return image;
    }



    public void searchPath(int goalCol,int goalRow)
    {
        int startCol=(worldX+solidArea.x)/gp.tileSize;
        int startRow=(worldY+solidArea.y)/gp.tileSize;

        gp.pFinder.setNodes(startCol,startRow,goalCol,goalRow,this);

        if(gp.pFinder.search()==true)
        {
            //Next worldX & worldY
            int nextX=gp.pFinder.pathList.get(0).col*gp.tileSize;
            int nextY=gp.pFinder.pathList.get(0).row*gp.tileSize;
            //Entity' solidArea position
            int enLeftX=worldX+solidArea.x;
            int enRightX=worldX+solidArea.x+solidArea.width;
            int enTopY=worldY+solidArea.y;
            int enBottomY=worldY+solidArea.y+solidArea.height;

            if(enTopY>nextY && enLeftX >=nextX && enRightX < nextX +gp.tileSize)
            {
                direction="up";
            }
            else if(enTopY<nextY && enLeftX >=nextX && enRightX < nextX +gp.tileSize)
            {
                direction="down";
            }
            else if(enTopY>=nextY && enBottomY<nextY+gp.tileSize)
            {
                //left or right
                if(enLeftX>nextX)
                {
                    direction="left";
                }
                if(enLeftX<nextX)
                {
                    direction="right";
                }
            }
            else if(enTopY>nextY && enLeftX>nextX)
            {
                //up or left
                direction="up";
                checkCollision();
                if(collisionOn==true)
                {
                    direction="left";
                }

            }
            else if(enTopY>nextY && enLeftX<nextX)
            {
                //up or right
                direction="up";
                checkCollision();
                if(collisionOn==true)
                {
                    direction="right";
                }
            }
            else if(enTopY<nextY && enLeftX>nextX)
            {
                //down or left
                direction="down";
                checkCollision();
                if(collisionOn==true)
                {
                    direction="left";
                }
            }
            else if(enTopY<nextY && enLeftX<nextX)
            {
                //down or right
                direction="down";
                checkCollision();
                if(collisionOn==true)
                {
                    direction="right";
                }
            }



        }
    }
}
