package object;

import entity.Entity;
import main.GamePanel;



public class Obj_Key extends Entity {
    public Obj_Key(GamePanel gp)
    {
        super(gp);

        name="Key";
        right1=setup(8,0,"/objects/ObjestSpriteSheet",gp.tileSize,gp.tileSize);
    }
}
