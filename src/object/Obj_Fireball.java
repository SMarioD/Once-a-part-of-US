package object;

import entity.Projectile;
import main.GamePanel;

public class Obj_Fireball extends Projectile {

    GamePanel gp;

    public Obj_Fireball(GamePanel gp) {
        super(gp);
        this.gp=gp;

        name="Fireball";
        speed=10;
        maxLife=80;
        life=maxLife;
        attack=2;
        useCost=1;
        alive=false;
        getImage();
    }

    public void getImage()
    {
        right1=setup(0,0,"/projectiles/ProjectilesSpriteSheet",gp.tileSize,gp.tileSize);
        right2=setup(1,0,"/projectiles/ProjectilesSpriteSheet",gp.tileSize,gp.tileSize);
        right3=setup(0,0,"/projectiles/ProjectilesSpriteSheet",gp.tileSize,gp.tileSize);
        right4=setup(1,0,"/projectiles/ProjectilesSpriteSheet",gp.tileSize,gp.tileSize);
        right5=setup(0,0,"/projectiles/ProjectilesSpriteSheet",gp.tileSize,gp.tileSize);
        right6=setup(1,0,"/projectiles/ProjectilesSpriteSheet",gp.tileSize,gp.tileSize);
        left1=setup(0,0,"/projectiles/ProjectilesSpriteSheet",gp.tileSize,gp.tileSize);
        left2=setup(1,0,"/projectiles/ProjectilesSpriteSheet",gp.tileSize,gp.tileSize);
        left3=setup(0,0,"/projectiles/ProjectilesSpriteSheet",gp.tileSize,gp.tileSize);
        left4=setup(1,0,"/projectiles/ProjectilesSpriteSheet",gp.tileSize,gp.tileSize);
        left5=setup(0,0,"/projectiles/ProjectilesSpriteSheet",gp.tileSize,gp.tileSize);
        left6=setup(1,0,"/projectiles/ProjectilesSpriteSheet",gp.tileSize,gp.tileSize);
    }


}
