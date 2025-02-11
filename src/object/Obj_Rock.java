package object;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

public class Obj_Rock extends Projectile {

    GamePanel gp;

    public Obj_Rock(GamePanel gp) {
        super(gp);
        this.gp=gp;

        name="Rock";
        speed=8;
        maxLife=80;
        life=maxLife;
        attack=2;
        useCost=1;
        alive=false;
        getImage();
    }

    public void getImage()
    {
        right1=setup(2,0,"/projectiles/ProjectilesSpriteSheet",gp.tileSize,gp.tileSize);
        right2=setup(2,0,"/projectiles/ProjectilesSpriteSheet",gp.tileSize,gp.tileSize);
        right3=setup(2,0,"/projectiles/ProjectilesSpriteSheet",gp.tileSize,gp.tileSize);
        right4=setup(2,0,"/projectiles/ProjectilesSpriteSheet",gp.tileSize,gp.tileSize);
        left1=setup(2,0,"/projectiles/ProjectilesSpriteSheet",gp.tileSize,gp.tileSize);
        left2=setup(2,0,"/projectiles/ProjectilesSpriteSheet",gp.tileSize,gp.tileSize);
        left3=setup(2,0,"/projectiles/ProjectilesSpriteSheet",gp.tileSize,gp.tileSize);
        left4=setup(2,0,"/projectiles/ProjectilesSpriteSheet",gp.tileSize,gp.tileSize);
    }

    public boolean haveResource(Entity user)
    {
        boolean haveResource = false;
        if(user.energy>=useCost)
        {
            haveResource=true;
        }
        return haveResource;
    }

    public void subtractResource(Entity user)
    {
        user.energy-=useCost;
    }
}
