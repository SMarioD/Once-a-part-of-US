package object;

import entity.Entity;
import main.GamePanel;

public class Obj_Soul extends Entity{
    GamePanel gp;
    public Obj_Soul(GamePanel gp)
    {
        super(gp);
        this.gp=gp;

        name="Soul";
        right1=setup(9,0,"/objects/ObjestSpriteSheet",gp.tileSize,gp.tileSize);
    }
    public void use(Entity entity)
    {
        gp.playSE(2);
        entity.life+=1;
    }
}
