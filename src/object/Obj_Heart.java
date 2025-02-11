package object;

import entity.Entity;
import main.GamePanel;



public class Obj_Heart extends Entity {
    GamePanel gp;
    public Obj_Heart(GamePanel gp)
    {
        super(gp);
        this.gp = gp;

        name="Heart";
        right1=setup(5,0,"/objects/ObjestSpriteSheet",gp.tileSize,gp.tileSize);
        image=setup(5,0,"/objects/ObjestSpriteSheet",gp.tileSize,gp.tileSize);
        image2=setup(6,0,"/objects/ObjestSpriteSheet",gp.tileSize,gp.tileSize);
        image3=setup(4,0,"/objects/ObjestSpriteSheet",gp.tileSize,gp.tileSize);
    }
}
