package object;

import entity.Entity;
import main.GamePanel;

public class Obj_YourSoul extends Entity{
    GamePanel gp;
    public Obj_YourSoul(GamePanel gp)
    {
        super(gp);
        this.gp=gp;

        name="YourSoul";
        right1=setup(10,0,"/objects/ObjestSpriteSheet",gp.tileSize,gp.tileSize);
    }

}
