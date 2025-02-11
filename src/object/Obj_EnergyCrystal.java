package object;

import entity.Entity;
import main.GamePanel;

public class Obj_EnergyCrystal extends Entity {
    GamePanel gp;

    public Obj_EnergyCrystal(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name="Energy Crystal";
        right1=image=setup(2,0,"/objects/ObjestSpriteSheet",gp.tileSize,gp.tileSize);
        image=setup(2,0,"/objects/ObjestSpriteSheet",gp.tileSize,gp.tileSize);
        image2=setup(3,0,"/objects/ObjestSpriteSheet",gp.tileSize,gp.tileSize);

    }
}
