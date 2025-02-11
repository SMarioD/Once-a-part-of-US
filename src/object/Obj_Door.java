package object;

import entity.Entity;
import main.GamePanel;



public class Obj_Door extends Entity {
    public Obj_Door(GamePanel gp)
    {
        super(gp);
        name="Door";
        right1=setup(1,0,"/objects/ObjestSpriteSheet",gp.tileSize,gp.tileSize);
        collision=true;

        solidArea.x=0;
        solidArea.y=16;
        solidArea.width=48;
        solidArea.height=32;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;

    }
}
