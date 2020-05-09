package gameplay.grid;

public class Monster extends Character{
    protected int id;

    public Monster(int monsterId, Position pos, Direction dir) {
        super(pos, dir);
        id = monsterId;
    }


    @Override
    public void move(Direction dir) {
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

    public int getId(){
        return id;
    }

    @Override
    public void die() {
        isAlive=false;
    }

    @Override
    public ElementType getType() {
        return ElementType.MONSTER;
    }
}
