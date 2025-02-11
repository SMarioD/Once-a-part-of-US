package main;

import entity.NPC_OldMan;
import monster.*;
import object.*;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp)
    {
        this.gp=gp;
    }
    public void setObject()
    {
        gp.obj[0][0]=new Obj_Door(gp);
        gp.obj[0][0].worldX=gp.tileSize*19;
        gp.obj[0][0].worldY=gp.tileSize*21;

        gp.obj[0][1]=new Obj_Boots(gp);
        gp.obj[0][1].worldX=gp.tileSize*30;
        gp.obj[0][1].worldY=gp.tileSize*42;

        gp.obj[0][2]=new Obj_Chest(gp);
        gp.obj[0][2].worldX=gp.tileSize*30;
        gp.obj[0][2].worldY=gp.tileSize*42;

        gp.obj[0][3]=new Obj_Key(gp);
        gp.obj[0][3].worldX=gp.tileSize*21;
        gp.obj[0][3].worldY=gp.tileSize*11;

        gp.obj[0][4]=new Obj_Soul(gp);
        gp.obj[0][4].worldX=gp.tileSize*9;
        gp.obj[0][4].worldY=gp.tileSize*21;

        gp.obj[0][5]=new Obj_Soul(gp);
        gp.obj[0][5].worldX=gp.tileSize*10;
        gp.obj[0][5].worldY=gp.tileSize*22;

        gp.obj[0][6]=new Obj_Soul(gp);
        gp.obj[0][6].worldX=gp.tileSize*11;
        gp.obj[0][6].worldY=gp.tileSize*21;

        gp.obj[0][7]=new Obj_Door(gp);
        gp.obj[0][7].worldX=gp.tileSize*23;
        gp.obj[0][7].worldY=gp.tileSize*40;

        gp.obj[0][8]=new Obj_Key(gp);
        gp.obj[0][8].worldX=gp.tileSize*9;
        gp.obj[0][8].worldY=gp.tileSize*37;

        gp.obj[0][9]=new Obj_Key(gp);
        gp.obj[0][9].worldX=gp.tileSize*40;
        gp.obj[0][9].worldY=gp.tileSize*17;

        gp.obj[0][10]=new Obj_Heart(gp);
        gp.obj[0][10].worldX=gp.tileSize*10;
        gp.obj[0][10].worldY=gp.tileSize*29;

        gp.obj[0][11]=new Obj_EnergyCrystal(gp);
        gp.obj[0][11].worldX=gp.tileSize*13;
        gp.obj[0][11].worldY=gp.tileSize*30;
    }
    public void setNPC()
    {
        gp.npc[0][0]=new NPC_OldMan(gp);
        gp.npc[0][0].worldX=gp.tileSize*18;
        gp.npc[0][0].worldY=gp.tileSize*42;
    }

    public void setMonster()
    {
        gp.monster[0][0]=new MON_Orc(gp);
        gp.monster[0][0].worldX=gp.tileSize*10;
        gp.monster[0][0].worldY=gp.tileSize*28;

        gp.monster[0][1]=new MON_Orc(gp);
        gp.monster[0][1].worldX=gp.tileSize*11;
        gp.monster[0][1].worldY=gp.tileSize*27;

        gp.monster[0][2]=new MON_OrcW(gp);
        gp.monster[0][2].worldX=gp.tileSize*37;
        gp.monster[0][2].worldY=gp.tileSize*11;

        gp.monster[0][3]=new MON_OrcW(gp);
        gp.monster[0][3].worldX=gp.tileSize*37;
        gp.monster[0][3].worldY=gp.tileSize*15;

        gp.monster[0][4]=new MON_Orc(gp);
        gp.monster[0][4].worldX=gp.tileSize*12;
        gp.monster[0][4].worldY=gp.tileSize*28;

        gp.monster[0][5]=new MON_Orc(gp);
        gp.monster[0][5].worldX=gp.tileSize*11;
        gp.monster[0][5].worldY=gp.tileSize*29;

        gp.monster[0][6]=new MON_Orc(gp);
        gp.monster[0][6].worldX=gp.tileSize*14;
        gp.monster[0][6].worldY=gp.tileSize*30;

        gp.monster[0][7]=new MON_OrcW(gp);
        gp.monster[0][7].worldX=gp.tileSize*40;
        gp.monster[0][7].worldY=gp.tileSize*13;

        gp.monster[0][8]=new MON_OrcW(gp);
        gp.monster[0][8].worldX=gp.tileSize*40;
        gp.monster[0][8].worldY=gp.tileSize*12;

        gp.monster[0][9]=new MON_Orc(gp);
        gp.monster[0][9].worldX=gp.tileSize*34;
        gp.monster[0][9].worldY=gp.tileSize*11;

        gp.monster[0][10]=new MON_Orc(gp);
        gp.monster[0][10].worldX=gp.tileSize*35;
        gp.monster[0][10].worldY=gp.tileSize*13;

        gp.monster[0][11]=new MON_Orc(gp);
        gp.monster[0][11].worldX=gp.tileSize*40;
        gp.monster[0][11].worldY=gp.tileSize*17;

        gp.monster[0][12]=new MON_Orc(gp);
        gp.monster[0][12].worldX=gp.tileSize*40;
        gp.monster[0][12].worldY=gp.tileSize*8;

        gp.monster[0][13]=new MON_Orc(gp);
        gp.monster[0][13].worldX=gp.tileSize*35;
        gp.monster[0][13].worldY=gp.tileSize*27;

        gp.monster[0][14]=new MON_Orc(gp);
        gp.monster[0][14].worldX=gp.tileSize*38;
        gp.monster[0][14].worldY=gp.tileSize*29;

        gp.monster[0][15]=new MON_Orc(gp);
        gp.monster[0][15].worldX=gp.tileSize*40;
        gp.monster[0][15].worldY=gp.tileSize*28;

        gp.monster[0][16]=new MON_Orc(gp);
        gp.monster[0][16].worldX=gp.tileSize*11;
        gp.monster[0][16].worldY=gp.tileSize*22;

        gp.monster[0][17]=new MON_Orc(gp);
        gp.monster[0][17].worldX=gp.tileSize*14;
        gp.monster[0][17].worldY=gp.tileSize*20;

        gp.monster[0][18]=new MON_Orc(gp);
        gp.monster[0][18].worldX=gp.tileSize*17;
        gp.monster[0][18].worldY=gp.tileSize*22;

        gp.monster[1][19]=new MON_Bat(gp);
        gp.monster[1][19].worldX=gp.tileSize*20;
        gp.monster[1][19].worldY=gp.tileSize*22;

        gp.monster[1][20]=new MON_Wizard(gp);
        gp.monster[1][20].worldX=gp.tileSize*24;
        gp.monster[1][20].worldY=gp.tileSize*22;

        gp.monster[2][21]=new MON_Skeleton(gp);
        gp.monster[2][21].worldX=gp.tileSize*24;
        gp.monster[2][21].worldY=gp.tileSize*22;

        gp.monster[1][22]=new MON_Wizard(gp);
        gp.monster[1][22].worldX=gp.tileSize*10;
        gp.monster[1][22].worldY=gp.tileSize*27;

        gp.monster[1][23]=new MON_Wizard(gp);
        gp.monster[1][23].worldX=gp.tileSize*10;
        gp.monster[1][23].worldY=gp.tileSize*25;

        gp.monster[1][24]=new MON_Wizard(gp);
        gp.monster[1][24].worldX=gp.tileSize*15;
        gp.monster[1][24].worldY=gp.tileSize*24;

        gp.monster[1][25]=new MON_Wizard(gp);
        gp.monster[1][25].worldX=gp.tileSize*15;
        gp.monster[1][25].worldY=gp.tileSize*27;

        gp.monster[1][26]=new MON_Bat(gp);
        gp.monster[1][26].worldX=gp.tileSize*12;
        gp.monster[1][26].worldY=gp.tileSize*25;

        gp.monster[1][27]=new MON_Bat(gp);
        gp.monster[1][27].worldX=gp.tileSize*13;
        gp.monster[1][27].worldY=gp.tileSize*25;

        gp.monster[1][28]=new MON_Bat(gp);
        gp.monster[1][28].worldX=gp.tileSize*12;
        gp.monster[1][28].worldY=gp.tileSize*26;

        gp.monster[1][29]=new MON_Bat(gp);
        gp.monster[1][29].worldX=gp.tileSize*13;
        gp.monster[1][29].worldY=gp.tileSize*26;

        gp.monster[1][30]=new MON_Bat(gp);
        gp.monster[1][30].worldX=gp.tileSize*11;
        gp.monster[1][30].worldY=gp.tileSize*20;

        gp.monster[1][31]=new MON_Bat(gp);
        gp.monster[1][31].worldX=gp.tileSize*12;
        gp.monster[1][31].worldY=gp.tileSize*16;

        gp.monster[1][32]=new MON_Bat(gp);
        gp.monster[1][32].worldX=gp.tileSize*11;
        gp.monster[1][32].worldY=gp.tileSize*12;

        gp.monster[1][33]=new MON_Bat(gp);
        gp.monster[1][33].worldX=gp.tileSize*12;
        gp.monster[1][33].worldY=gp.tileSize*6;

        gp.monster[1][34]=new MON_Bat(gp);
        gp.monster[1][34].worldX=gp.tileSize*14;
        gp.monster[1][34].worldY=gp.tileSize*8;

        gp.monster[1][35]=new MON_Bat(gp);
        gp.monster[1][35].worldX=gp.tileSize*20;
        gp.monster[1][35].worldY=gp.tileSize*7;

        gp.monster[1][36]=new MON_Bat(gp);
        gp.monster[1][36].worldX=gp.tileSize*31;
        gp.monster[1][36].worldY=gp.tileSize*7;

        gp.monster[1][37]=new MON_Bat(gp);
        gp.monster[1][37].worldX=gp.tileSize*39;
        gp.monster[1][37].worldY=gp.tileSize*8;

        gp.monster[1][38]=new MON_Bat(gp);
        gp.monster[1][38].worldX=gp.tileSize*35;
        gp.monster[1][38].worldY=gp.tileSize*13;

        gp.monster[1][39]=new MON_Bat(gp);
        gp.monster[1][39].worldX=gp.tileSize*36;
        gp.monster[1][39].worldY=gp.tileSize*15;

        gp.monster[1][40]=new MON_Bat(gp);
        gp.monster[1][40].worldX=gp.tileSize*34;
        gp.monster[1][40].worldY=gp.tileSize*19;

        gp.monster[1][41]=new MON_Bat(gp);
        gp.monster[1][41].worldX=gp.tileSize*33;
        gp.monster[1][41].worldY=gp.tileSize*29;


        gp.monster[1][42]=new MON_Bat(gp);
        gp.monster[1][42].worldX=gp.tileSize*35;
        gp.monster[1][42].worldY=gp.tileSize*32;


        gp.monster[1][43]=new MON_Bat(gp);
        gp.monster[1][43].worldX=gp.tileSize*33;
        gp.monster[1][43].worldY=gp.tileSize*33;


        gp.monster[1][44]=new MON_Bat(gp);
        gp.monster[1][44].worldX=gp.tileSize*34;
        gp.monster[1][44].worldY=gp.tileSize*39;

        gp.monster[1][45]=new MON_Wizard(gp);
        gp.monster[1][45].worldX=gp.tileSize*31;
        gp.monster[1][45].worldY=gp.tileSize*28;


        gp.monster[1][46]=new MON_Wizard(gp);
        gp.monster[1][46].worldX=gp.tileSize*36;
        gp.monster[1][46].worldY=gp.tileSize*39;


        gp.monster[1][47]=new MON_Wizard(gp);
        gp.monster[1][47].worldX=gp.tileSize*31;
        gp.monster[1][47].worldY=gp.tileSize*27;


        gp.monster[1][48]=new MON_Wizard(gp);
        gp.monster[1][48].worldX=gp.tileSize*37;
        gp.monster[1][48].worldY=gp.tileSize*23;


    }
}
