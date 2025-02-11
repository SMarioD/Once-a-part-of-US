package tile;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][][];
    BufferedImage spriteSheetMap;
    boolean drawPath=false;


    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[70];
        mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        try {
            spriteSheetMap = ImageIO.read(getClass().getResourceAsStream("/tiles/MapSpritesSheet.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        getTileImage();
        loadMap("/maps/map1.txt",0);
        loadMap("/maps/map2.txt",1);
        loadMap("/maps/map3.txt",2);
    }
    public void setup(int index, int indexX, int indexY, BufferedImage image, boolean collision) {//FACTORY METHOD din cauza acestei functii

        // Functie incarcare imagini
        UtilityTool uTool=new UtilityTool();
        try {
            tile[index] = new Tile();
            tile[index].image = uTool.cropImage(image, gp.tileSize * indexX, gp.tileSize * indexY, gp.tileSize, gp.tileSize);
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void getTileImage() {

            setup(0,0,0,spriteSheetMap,false);

            setup(1,1,0,spriteSheetMap,false);

            setup(2,2,0,spriteSheetMap,false);

            setup(3,3,0,spriteSheetMap,false);

            setup(4,4,0,spriteSheetMap,false);

            setup(5,5,0,spriteSheetMap,false);

            setup(6,6,0,spriteSheetMap,false);

            setup(7,7,0,spriteSheetMap,false);

            setup(8,8,0,spriteSheetMap,true);

            setup(9,0,1,spriteSheetMap,false);

            setup(10,1,1,spriteSheetMap,false);

            setup(11,2,1,spriteSheetMap,false);

            setup(12,3,1,spriteSheetMap,false);

            setup(13,4,1,spriteSheetMap,false);

            setup(14,5,1,spriteSheetMap,false);

            setup(15,6,1,spriteSheetMap,false);

            setup(16,7,1,spriteSheetMap,false);

            setup(17,8,1,spriteSheetMap,true);

            setup(18,9,1,spriteSheetMap,true);

            setup(19,10,1,spriteSheetMap,true);

            setup(20,11,1,spriteSheetMap,true);

            setup(21,12,1,spriteSheetMap,true);

            setup(22,13,1,spriteSheetMap,true);

            setup(23,14,1,spriteSheetMap,true);

            setup(24,15,1,spriteSheetMap,true);

            setup(25,16,1,spriteSheetMap,false);

            setup(26,17,1,spriteSheetMap,false);

            setup(27,18,1,spriteSheetMap,true);

            setup(28,19,1,spriteSheetMap,true);

            setup(29,20,1,spriteSheetMap,true);

            setup(30,21,1,spriteSheetMap,true);

            setup(31,22,1,spriteSheetMap,true);

        setup(32,0,2,spriteSheetMap,false);

        setup(33,1,2,spriteSheetMap,false);

        setup(34,2,2,spriteSheetMap,false);

        setup(35,3,2,spriteSheetMap,false);

        setup(36,4,2,spriteSheetMap,false);

        setup(37,5,2,spriteSheetMap,false);

        setup(38,6,2,spriteSheetMap,false);

        setup(39,7,2,spriteSheetMap,false);

        setup(40,8,2,spriteSheetMap,false);

        setup(41,9,2,spriteSheetMap,false);

        setup(42,10,2,spriteSheetMap,false);

        setup(43,11,2,spriteSheetMap,false);

        setup(44,12,2,spriteSheetMap,false);

        setup(45,13,2,spriteSheetMap,false);

        setup(46,14,2,spriteSheetMap,false);

        setup(47,15,2,spriteSheetMap,false);

        setup(48,16,2,spriteSheetMap,true);

        setup(49,17,2,spriteSheetMap,true);

        setup(50,18,2,spriteSheetMap,true);

        setup(51,19,2,spriteSheetMap,true);

        setup(52,20,2,spriteSheetMap,false);

        setup(53,21,2,spriteSheetMap,false);

        setup(54,22,2,spriteSheetMap,false);

        setup(55,23,2,spriteSheetMap,false);

        setup(56,24,2,spriteSheetMap,false);

        setup(57,25,2,spriteSheetMap,false);

        setup(58,26,2,spriteSheetMap,true);

        setup(59,27,2,spriteSheetMap,true);

        setup(60,28,2,spriteSheetMap,true);

        setup(61,29,2,spriteSheetMap,true);

        setup(62,30,2,spriteSheetMap,true);

        setup(63,31,2,spriteSheetMap,false);

        setup(64,32,2,spriteSheetMap,false);

        setup(65,33,2,spriteSheetMap,false);

        setup(66,34,2,spriteSheetMap,false);

        setup(67,35,2,spriteSheetMap,false);

        setup(68,36,2,spriteSheetMap,false);

    }

    public void loadMap(String filePath,int map)
    {
        try{
            InputStream is= getClass().getResourceAsStream(filePath);
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            int col=0;
            int row=0;
            while(col<gp.maxWorldCol && row< gp.maxWorldRow)
            {
                String line=br.readLine();

                while(col<gp.maxWorldCol)
                {
                    String numbers[]=line.split(" ");

                    int num= Integer.parseInt(numbers[col]);

                    mapTileNum[map][col][row] = num;
                    col++;
                }
                    if(col==gp.maxWorldCol)
                    {
                        col=0;
                        row++;
                    }

            }
            br.close();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2)
    {
       int worldCol=0;
       int worldRow=0;


       while(worldCol<gp.maxWorldCol && worldRow<gp.maxWorldRow)
       {
           int tileNum=mapTileNum[gp.currentMap][worldCol][worldRow];
           int worldX=worldCol*gp.tileSize;
           int worldY=worldRow*gp.tileSize;
           int screenX=worldX-gp.player.worldX + gp.player.screenX;
           int screenY=worldY-gp.player.worldY+gp.player.screenY;


           if(worldX +gp.tileSize>gp.player.worldX-gp.player.screenX
                   && worldX -gp.tileSize<gp.player.worldX+gp.player.screenX
                   && worldY+gp.tileSize>gp.player.worldY-gp.player.screenY
                   && worldY -gp.tileSize< gp.player.worldY+gp.player.screenY){
               g2.drawImage(tile[tileNum].image,screenX,screenY,null);

           }
                      worldCol++;

           if(worldCol==gp.maxWorldCol)
           {
               worldCol=0;
               worldRow++;
           }
       }
       if(drawPath==true)
       {
           g2.setColor(new Color(255,0,0,70));

           for(int i=0;i<gp.pFinder.pathList.size();i++)
           {
               int worldX=gp.pFinder.pathList.get(i).col*gp.tileSize;
               int worldY=gp.pFinder.pathList.get(i).row*gp.tileSize;
               int screenX=worldX-gp.player.worldX + gp.player.screenX;
               int screenY=worldY-gp.player.worldY+gp.player.screenY;

               g2.fillRect(screenX,screenY,gp.tileSize,gp.tileSize);
           }
       }
    }
}
