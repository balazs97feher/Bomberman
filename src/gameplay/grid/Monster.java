package gameplay.grid;

public class Monster extends Character{
    private int id;
    private static int nextId = 0;

    public Monster(Position pos, Direction dir) {
        super(pos, dir);
        id = nextId;
        nextId++;
    }


    public int getId(){
        return id;
    }

    @Override
    public void die() {
        isAlive = false;
    }

    @Override
    public ElementType getType() {
        return ElementType.MONSTER;
    }
}
