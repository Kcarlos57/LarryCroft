package nz.ac.wgtn.swen225.lc.domain;
import nz.ac.wgtn.swen225.lc.domain.items.Item;
import nz.ac.wgtn.swen225.lc.domain.items.Key;
import nz.ac.wgtn.swen225.lc.domain.items.Treasure;
import nz.ac.wgtn.swen225.lc.domain.tiles.Tile;
import nz.ac.wgtn.swen225.lc.domain.tiles.Tile_Empty;
import nz.ac.wgtn.swen225.lc.domain.tiles.Tile_LockedDoor;
import nz.ac.wgtn.swen225.lc.domain.tiles.Tile_Wall;

/**
 * Holds the data containing a level
 */
public class Level {


    //TODO Modify to take from persistency

    /**
     * Creates a default level for testing
     */
    public Level(){

    }
    /**
     * Creates a level from relevant data
     */
    public Level(String levelName, int width, int height){
        TempBuildFromString(levelName,width,height);
        inventory = new Inventory();
    }
    int width, height;
    private Tile[][] tiles;
    private Actor larry;
    private Actor[] actors;
    private Inventory inventory;

    int treasureNum;
    int levelTreasure;
    int collectedTreasure;

    /**
     * Returns a 2d array of tile objects containing the tile data o the level
     * @return
     */
    public Tile[][] getTiles(){
        return tiles;
    }

    /**
     * Gets larry
     * @return
     */
    public Actor getLarry(){
        return larry;
    }

    /**
     * Gets all non larry actors
     * @return
     */
    public Actor[] getNPCs(){
        return actors;
    }

    /**
     * Gets an array of all actors, npcs and larry
     * @return
     */
    public Actor[] getAllActors(){
        int aSize = 0;
        if(getNPCs()==null){
            aSize = 1;
        } else {
            aSize = getNPCs().length + 1;
        }

        Actor[] out = new Actor[aSize];
        out[0] = getLarry();
        if(aSize==1) return out;

        for (int i = 0; i < getNPCs().length; i++){
            out[i+1] = actors[i];
        }
        return out;
    }


    /**
     * Tries to move larry in a direction
     * @param direction
     */
    public void MoveLarry(Domain.Direction direction){
        Position checkPos = new Position(larry.getPosition().getX(),larry.getPosition().getY());

        switch (direction){
            case Up -> {checkPos.setY(checkPos.getY()-1);  }
            case Down -> checkPos.setY(checkPos.getY()+1);
            case Left -> checkPos.setX(checkPos.getX()-1);
            case Right -> checkPos.setX(checkPos.getX()+1);
        }

        TryUseKeyOnDoor(checkPos);
        if (CheckValidMoveTile(checkPos)){
            larry.Move(direction);

            if(tiles[larry.getPosition().getX()][larry.getPosition().getY()].hasItem()){
                PickupItem();
            }

        }else{
            throw new InvalidMovementException("Player cannot walk onto tile");
        }


    }

    private void TryUseKeyOnDoor(Position doorPos){
        if(!CheckPositionExists(doorPos))return;

        if(tiles[doorPos.getX()][doorPos.getY()].isWalkable()) return;
        if(! (tiles[doorPos.getX()][doorPos.getY()] instanceof Tile_LockedDoor)) return;

        Tile_LockedDoor door = (Tile_LockedDoor) tiles[doorPos.getX()][doorPos.getY()];

        for(int i = 0; i < inventory.getInventorySize(); i++){
            if(inventory.getItemAtPos(i) instanceof Key){

                if(door.OpenDoor( (Key)inventory.getItemAtPos(i) )){
                    //Door Opens
                    inventory.RemoveItemAt(i);
                    return;
                }
            }
        }

    }

    private void PickupItem(){
        if(tiles[larry.getPosition().getX()][larry.getPosition().getY()].getItem().canGoInInventory()){
            if (!inventory.isFull()) {
                inventory.addItem(tiles[larry.getPosition().getX()][larry.getPosition().getY()].RemoveItem());
                System.out.println("Item put into Inventory");
            }
        }else {
            Item item = tiles[larry.getPosition().getX()][larry.getPosition().getY()].RemoveItem();
            if(item instanceof Treasure){
                levelTreasure--;
                collectedTreasure++;
            }

        }
    }

    /**
     * Checks if a tile at a position is walkable
     * @param position
     * @return
     */
    private boolean CheckValidMoveTile(Position position){
        if(!CheckPositionExists(position)) return false;

        return tiles[position.getX()][position.getY()].isWalkable();
    }

    /**
     * Check if a position if a tile on the board
     * @param position
     */
    private boolean CheckPositionExists(Position position){
        return position.getX() > 0 || position.getY() > 0 ||
                position.getX() < width || position.getY() < height;

    }




    /**
     * Causes all non-player actors to make a move
     */
    public void Step(){

    }

    //TODO Remove and proper integration
    /**
     * Temp until persistency
     */
    void TempBuildFromString(String s, int dimX, int dimY){
        treasureNum = 0;
        width = dimX;
        height = dimY;

        tiles = new Tile[dimX][dimY];
        for(int y = 0; y < dimY; y++){
            for(int x = 0; x < dimX; x++){



                switch (s.charAt(x +(y * dimX))){
                    case '#' :
                        tiles[x][y] = new Tile_Wall();
                      break;
                    case 'L' :
                        tiles[x][y] = new Tile_Empty();
                        larry = new Actor(new Position(x,y), Domain.Direction.Up, "Player");
                        break;
                    case 'T' :
                        Tile t = new Tile_Empty();
                        t.SetItem(new Treasure());
                        treasureNum++;
                        levelTreasure++;
                        tiles[x][y] = t;
                        break;
                    case 'K' :
                        Tile a = new Tile_Empty();
                        Key k = new Key(Key.KeyColor.Red);
                        a.SetItem(k);

                        tiles[x][y] = a;
                        break;

                    case 'X' :
                        Tile d = new Tile_LockedDoor(new Key(Key.KeyColor.Red));
                        tiles[x][y] = d;
                        break;
                    default:
                        tiles[x][y] = new Tile_Empty();
                        break;
                }

            }
        }
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        for(int j = 0; j < height; j++){
            for(int i = 0; i < width; i ++){
                if(larry.getPosition().getX()==i && larry.getPosition().getY()==j){
                    stringBuilder.append('L');
                }else if (tiles[i][j].hasItem()){
                    if(tiles[i][j].getItem() instanceof Key) {
                        stringBuilder.append('K');
                    }else{
                        stringBuilder.append('T');
                    }

                }else{
                    stringBuilder.append(tiles[i][j].toString());
                }
            }
            stringBuilder.append('\n');
        }
        stringBuilder.append('\n');

        stringBuilder.append("Treasure Collected: " + collectedTreasure +"/" +treasureNum + '\n');
        stringBuilder.append("Treasure Remaining: " + levelTreasure +"/" +treasureNum);


        return stringBuilder.toString();
    }


}
