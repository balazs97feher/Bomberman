package gameplay.grid;

public class Player extends Character {
    protected String name;
    protected int score;

    public Player(String charName, Position pos, Direction dir) {
        super(pos, dir);
        name=charName;
        score=0;
    }

    @Override
    protected void move(Direction dir) {
        // TODO
    }

    @Override
    protected void die() {
        isAlive=false;
    }


}
