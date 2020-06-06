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
        Image monster_leftimg = new Image("File:pictures/monster_left.png");
        Image monster_rightimg = new Image("File:pictures/monster_right.png");
        Image monster_upimg = new Image("File:pictures/monster_up.png");
        Image monster_downimg = new Image("File:pictures/monster_down.png");
        Image player1_leftimg = new Image("File:pictures/player1_left.png");
        Image player1_rightimg = new Image("File:pictures/player1_right.png");
        Image player1_upimg = new Image("File:pictures/player1_up.png");
        Image player1_downimg = new Image("File:pictures/player1_down.png");
        Image player2_leftimg = new Image("File:pictures/player2_left.png");
        Image player2_rightimg = new Image("File:pictures/player2_right.png");
        Image player2_upimg = new Image("File:pictures/player2_up.png");
        Image player2_downimg = new Image("File:pictures/player2_down.png");

        gridPane.getChildren().clear();
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
                        switch (player.getDirection()){
                            case EAST:
                                gridPane.add(new ImageView(player1_rightimg), element.getPosition().getPosX(), element.getPosition().getPosY());
                                break;
                            case WEST:
                                gridPane.add(new ImageView(player1_leftimg), element.getPosition().getPosX(), element.getPosition().getPosY());
                                break;
                            case SOUTH:
                                gridPane.add(new ImageView(player1_downimg), element.getPosition().getPosX(), element.getPosition().getPosY());
                                break;
                            case NORTH:
                                gridPane.add(new ImageView(player1_upimg), element.getPosition().getPosX(), element.getPosition().getPosY());
                                break;
                        }
                    }
                    else{
                        switch (player.getDirection()){
                            case EAST:
                                gridPane.add(new ImageView(player2_rightimg), element.getPosition().getPosX(), element.getPosition().getPosY());
                                break;
                            case WEST:
                                gridPane.add(new ImageView(player2_leftimg), element.getPosition().getPosX(), element.getPosition().getPosY());
                                break;
                            case SOUTH:
                                gridPane.add(new ImageView(player2_downimg), element.getPosition().getPosX(), element.getPosition().getPosY());
                                break;
                            case NORTH:
                                gridPane.add(new ImageView(player2_upimg), element.getPosition().getPosX(), element.getPosition().getPosY());
                                break;
                        }
                    }
                    break;
                case MONSTER:
                    GUI_Monster monster = (GUI_Monster) element;
                    switch (monster.getDirection()){
                        case EAST:
                            gridPane.add(new ImageView(monster_rightimg), element.getPosition().getPosX(), element.getPosition().getPosY());
                            break;
                        case WEST:
                            gridPane.add(new ImageView(monster_leftimg), element.getPosition().getPosX(), element.getPosition().getPosY());
                            break;
                        case SOUTH:
                            gridPane.add(new ImageView(monster_downimg), element.getPosition().getPosX(), element.getPosition().getPosY());
                            break;
                        case NORTH:
                            gridPane.add(new ImageView(monster_upimg), element.getPosition().getPosX(), element.getPosition().getPosY());
                            break;
                    }
                    break;

            }
        }
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
                    Position newpos = new Position(height,width);
                    switch (direction){
                        case EAST:
                            newpos = new Position(element.getPosition().getPosY(), element.getPosition().getPosX()+1);
                            break;
                        case WEST:
                            newpos = new Position(element.getPosition().getPosY(),element.getPosition().getPosX()-1);
                            break;
                        case NORTH:
                            newpos = new Position(element.getPosition().getPosY()-1,element.getPosition().getPosX());
                            break;
                        case SOUTH:
                            newpos = new Position(element.getPosition().getPosY()+1, element.getPosition().getPosX());
                            break;
                    }
                    player.setPosition(newpos);
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
                    Position newpos = new Position(height,width);
                    switch (direction){
                        case EAST:
                            newpos = new Position(element.getPosition().getPosY(), element.getPosition().getPosX()+1);
                            break;
                        case WEST:
                            newpos = new Position(element.getPosition().getPosY(),element.getPosition().getPosX()-1);
                            break;
                        case NORTH:
                            newpos = new Position(element.getPosition().getPosY()-1,element.getPosition().getPosX());
                            break;
                        case SOUTH:
                            newpos = new Position(element.getPosition().getPosY()+1, element.getPosition().getPosX());
                            break;
                    }
                    monster.setPosition(newpos);
                    monster.setDirection(direction);
                }
            }
        }
    }
}
