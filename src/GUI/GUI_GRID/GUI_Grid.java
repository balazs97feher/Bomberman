package GUI.GUI_GRID;

import gameplay.grid.*;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class GUI_Grid {
    protected int height;
    protected int width;
    protected ArrayList<GUI_GridElement> elements;

    public GUI_Grid(int h, int w, ArrayList<ArrayList<GridElement>> grid){
        height = h;
        width = w;
        elements = new ArrayList<GUI_GridElement>();

        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; ++y){
                GridElement element = grid.get(y).get(x);
                switch (element.getType()){
                    case BOMB:
                        //System.out.println("Bomb");
                        Bomb bomb = (Bomb) element;
                        elements.add(new GUI_Bomb(bomb.getPosition(), bomb.getId()));
                        break;
                    case WALL:
                        //System.out.println("Wall");
                        Wall wall = (Wall) element;
                        elements.add(new GUI_WALL(wall.getPosition(),wall.getDestructible(),wall.getId()));
                        break;
                    case PLAYER:
                        //System.out.println("Player");
                        Player player = (Player) element;
                        elements.add(new GUI_Player(player.getPosition(),player.getDirection(),player.getId()));
                        break;
                    case MONSTER:
                        //System.out.println("Monster");
                        Monster monster = (Monster) element;
                        elements.add(new GUI_Monster(monster.getPosition(),monster.getDirection(),monster.getId()));
                        break;
                    default:
                        //System.out.println("Empty");
                        elements.add(new GUI_EmptyElement(element.getPosition()));
                }
            }
        }


    }


    public void printGUI_Grid(GridPane gridPane){
        Image wall1img = new Image("File:pictures/Wall1.png");
        Image wall2img = new Image("File:pictures/Wall2.png");
        Image bombimg = new Image("File:pictures/Bomb.png");
        Image monsterimg = new Image("File:pictures/Monster.png");
        Image player1img = new Image("File:pictures/Player1.png");
        Image player2img = new Image("File:pictures/Player2.png");

        for(int i = 0; i < elements.size(); i++){
            GUI_GridElement element = elements.get(i);
            switch (element.getType())
            {
                case BOMB:
                    gridPane.add(new ImageView(bombimg), element.getPosition().getPosX(), element.getPosition().getPosY());
                    break;
                case WALL:
                    GUI_WALL wall = (GUI_WALL) element;
                    if(wall.getDestructible() == true){
                        gridPane.add(new ImageView(wall2img), element.getPosition().getPosX(), element.getPosition().getPosY());
                    }
                    else{
                        gridPane.add(new ImageView(wall1img), element.getPosition().getPosX(), element.getPosition().getPosY());
                    }
                    break;
                case PLAYER:
                    GUI_Player player = (GUI_Player) element;
                    if(player.getId() == 0){
                        gridPane.add(new ImageView(player1img), element.getPosition().getPosX(), element.getPosition().getPosY());
                    }
                    else{
                        gridPane.add(new ImageView(player2img), element.getPosition().getPosX(), element.getPosition().getPosY());
                    }
                    break;
                case MONSTER:
                    gridPane.add(new ImageView(monsterimg), element.getPosition().getPosX(), element.getPosition().getPosY());
                    break;

            }
        }
    }

    public void changePosition(Position oldPos, Position newPos){
        for(int i = 0; i < elements.size(); i++){
            GUI_GridElement element = elements.get(i);
            if(element.getPosition().getPosX() == oldPos.getPosX()){
                if(element.getPosition().getPosY() == oldPos.getPosY()){
                    element.setPosition(newPos);
                }
            }

        }
    }

    public void swapPosition(Position pos1, Position pos2){
        Position temp = new Position(height, width);
        changePosition(pos1,temp);
        changePosition(pos2,pos1);
        changePosition(temp,pos2);
    }

    public void addbomb(Position pos, int id){
        elements.add(new GUI_Bomb(pos,id));
    }

    public void removebomb(int id){
        for(int i = 0; i < elements.size(); i++){
            GUI_GridElement element = elements.get(i);
            if(element.getType() == ElementType.BOMB){
                GUI_Bomb bomb = (GUI_Bomb) element;
                if(bomb.getId() == id){
                    elements.remove(i);
                }
            }
        }
    }

    public void removemonster(int id){
        for(int i = 0; i < elements.size(); i++){
            GUI_GridElement element = elements.get(i);
            if(element.getType() == ElementType.MONSTER){
                GUI_Monster monster = (GUI_Monster) element;
                if(monster.getId() == id){
                    elements.remove(i);
                }
            }
        }
    }

    public  void removeplayer(int id){
        for(int i = 0; i < elements.size(); i++){
            GUI_GridElement element = elements.get(i);
            if(element.getType() == ElementType.PLAYER){
                GUI_Player player = (GUI_Player) element;
                if(player.getId() == id){
                    elements.remove(i);
                }
            }
        }
    }

    public  void removewall(int id){
        for(int i = 0; i < elements.size(); i++){
            GUI_GridElement element = elements.get(i);
            if(element.getType() == ElementType.WALL){
                GUI_WALL wall = (GUI_WALL) element;
                if(wall.getId() == id){
                    elements.remove(i);
                }
            }
        }
    }

    public void moveplayer(int playerid, Direction direction){
        for(int i = 0; i < elements.size(); i++){
            GUI_GridElement element = elements.get(i);
            if(element.getType() == ElementType.PLAYER){
                GUI_Player player = (GUI_Player) element;
                if(player.getId() == playerid){
                    Position oldpos = player.getPosition();
                    Position newpos = new Position(height,width);
                    switch (direction){
                        case EAST:
                            newpos = new Position(oldpos.getPosY(), oldpos.getPosX()+1);
                            break;
                        case WEST:
                            newpos = new Position(oldpos.getPosY(), oldpos.getPosX()-1);
                            break;
                        case NORTH:
                            newpos = new Position(oldpos.getPosY()+1, oldpos.getPosX());
                            break;
                        case SOUTH:
                            newpos = new Position(oldpos.getPosY()-1, oldpos.getPosX());
                            break;
                    }
                    swapPosition(oldpos,newpos);
                    player.setDirection(direction);
                }
            }
        }
    }

    public void movemonster(int monsterid, Direction direction){
        for(int i = 0; i < elements.size(); i++){
            GUI_GridElement element = elements.get(i);
            if(element.getType() == ElementType.MONSTER){
                GUI_Monster monster = (GUI_Monster) element;
                if(monster.getId() == monsterid){
                    Position oldpos = monster.getPosition();
                    Position newpos = new Position(height,width);
                    switch (direction){
                        case EAST:
                            newpos = new Position(oldpos.getPosY(), oldpos.getPosX()+1);
                            break;
                        case WEST:
                            newpos = new Position(oldpos.getPosY(), oldpos.getPosX()-1);
                            break;
                        case NORTH:
                            newpos = new Position(oldpos.getPosY()+1, oldpos.getPosX());
                            break;
                        case SOUTH:
                            newpos = new Position(oldpos.getPosY()-1, oldpos.getPosX());
                            break;
                    }
                    swapPosition(oldpos,newpos);
                    monster.setDirection(direction);
                }
            }
        }
    }
}
