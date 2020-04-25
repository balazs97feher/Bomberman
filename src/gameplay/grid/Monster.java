package gameplay.grid;

public class Monster extends Character{
    protected int id;

    public Monster(int monsterId, Position pos, Direction dir) {
        super(pos, dir);
        id=monsterId;
    }


    @Override
    protected void move(Direction dir) {
        switch (dir){
            case NORTH:
                position.posY--;
                break;
            case EAST:
                position.posX++;
                break;
            case SOUTH:
                position.posY++;
                break;
            case WEST:
                position.posX--;
                break;
        }
    }

    @Override
    protected void die() {
        isAlive=false;
    }
}
