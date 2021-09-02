import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Model {


    public boolean issound = true;


    Controller controller;

    int aliencount;
    public int record ;

    public int getRecord(){
        record = Math.max(aliencount, record);
        return record ;
    }
    public void setController(Controller controller) {
        this.controller = controller;
    }

    Random random = new Random();
    OwnPlain ownPlain = OwnPlain.getOwnPlain();
    OwnPlain.Bullet bullet = OwnPlain.getOwnPlain().new Bullet();

    public Direction direction;
    boolean isShoot = false;

    List<AlienPlain> alienPlains = getAliens();
    int countAliens = 1;


    public void shootFromBullet() throws IOException {
        if((bullet.getX() >=770)){
            isShoot = false;
        }
        bullet.setX(bullet.getX()+4);
        bullet.setBullet(Images.BULLET());
    }

    public void setShoot(boolean shoot) {
        isShoot = shoot;
    }

    public void bulletToPlain() throws IOException {
        bullet.setX(ownPlain.x+50);
        bullet.setY(ownPlain.y+90);
        bullet.setBullet(Images.BULLETNONFIRE());
    }


    public List<AlienPlain> getAliens(){

        List<AlienPlain> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new AlienPlain(950,70*(i+1)));
        }
        return list;
    }
    public void restart(){
        this.alienPlains = getAliens();
        this.ownPlain.x = 50;
        this.ownPlain.y = 50;
        ownPlain.ownPlainIsAlive = true;
        countAliens = 1;
        aliencount = 0;
        isShoot = false;
        bullet.setX(ownPlain.x + 50);
        bullet.setY(ownPlain.y + 90);
    }

    public boolean isAliveAlien(AlienPlain plain)  {
            int x = bullet.getX()- plain.getX();
            int y = bullet.getY()-plain.getY();

            if((y>-15 && y<50) && (x<60 && x>15)){
                plain.isAliveb = false;

                aliencount++;
                return false;
            }

        return true;
    }

    public void bombAlien(AlienPlain plain){
        plain.x = 1000+random.nextInt(15)*10;
        plain.y = random.nextInt(8)*50;
        plain.xbomb = plain.x;
        plain.ybomb = plain.y;

    }

    public void whereOwnAlienAndBombAlien(AlienPlain plain) {
        if(plain.xbomb<-20 ) {
            plain.xbomb = plain.x;
            plain.isBombShoot = false;
        }
        if(plain.y+5 == ownPlain.y+5){
            plain.isBombShoot = true;
        }
        if((plain.xbomb < ownPlain.x+90 && plain.xbomb>ownPlain.x) &&
                (plain.ybomb>ownPlain.y && plain.ybomb<ownPlain.y+70)
        || (ownPlain.x+100 > plain.x && ownPlain.x < plain.x+100) &&
                (ownPlain.y+85 > plain.y && ownPlain.y < plain.y +20)){
            ownPlain.ownPlainIsAlive  = false;
        }
    }

    public boolean ownPlainIsAlive() {
        return ownPlain.ownPlainIsAlive;
    }
}