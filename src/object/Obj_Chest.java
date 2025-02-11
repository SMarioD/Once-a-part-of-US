package object;

import entity.Entity;
import main.GamePanel;



public class Obj_Chest extends Entity {
    public Obj_Chest(GamePanel gp)
    {
        super(gp);
        name="Chest";
        right1=setup(0,0,"/objects/ObjestSpriteSheet",gp.tileSize,gp.tileSize);
        collision=true;
    }
}
