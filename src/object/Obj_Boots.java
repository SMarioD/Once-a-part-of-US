package object;

import entity.Entity;
import main.GamePanel;


public class Obj_Boots extends Entity {
    public Obj_Boots(GamePanel gp)
    {
        super(gp);
        name="Boots";
        right1=setup(7,0,"/objects/ObjestSpriteSheet",gp.tileSize,gp.tileSize);
    }
}
