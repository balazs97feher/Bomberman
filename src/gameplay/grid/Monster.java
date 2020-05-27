package gameplay.grid;

public class Monster extends Character{
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
    public ElementType getType() {
        return ElementType.MONSTER;
    }
}
