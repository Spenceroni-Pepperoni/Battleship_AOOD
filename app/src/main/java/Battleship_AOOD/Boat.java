package Battleship_AOOD;

/**
 * Boat class for Battleship Game
 *
 * @Author Spencer McGrath
 */
public class Boat {
    int size;
    boolean[] hits;
    String boatType;
    Position pos;
    Position[] boatPos;
    String orientation;

    /**
     * Public constructor for Boat class
     * @param boatType Type of boat ie Battleship or Submarine
     * @param pos Starting position of the boat
     * @param orientation Orientation of the boat ie vertical
     */
    public Boat(String boatType,Position pos, String orientation){
        this.boatType = boatType;
        this.pos = pos;
        this.orientation = orientation;
        hits = new boolean[size()];
        size = size();
        boatPos = generateAllPositions();
    }

    /**
     * @return Returns the first letter of the Boat type
     */
    public char abbreviation(){
        return boatType.charAt(0);
    }

    /**
     * @return Returns boat type
     */
    public String name(){
        return boatType;
    }

    /**
     * @return Returns the size of the boat
     */
    public int size(){
        return switch (boatType) {
            case "Aircraft Carrier" -> 5;
            case "Battleship" -> 4;
            case "Cruiser","Submarine" -> 3;
            case "Destroyer" -> 2;
            default -> 0;
        };
    }

    /**
     * Checks of targetPos is on the boat
     * @param targetPos Position to check if it is on the boat
     * @return Returns if Position is on the boat
     */
    public boolean onBoat(Position targetPos){
        for(int i = 0; i<size; i++){
                if((targetPos.rowIndex() == boatPos[i].rowIndex()) && (targetPos.columnIndex() == boatPos[i].columnIndex())){
                    return true;
                }
            }
        return false;
    }



    /**
     * Checks if targetPos has been hit before
     * @param targetPos Position to check if it has been hit
     * @return Returns if the position has been hit
     */
    public boolean isHit(Position targetPos){
        for(int i = 0; i<size; i++){
            if(orientation.equals("vertical")){
                if((targetPos.rowIndex() == pos.rowIndex()+i) && (targetPos.columnIndex() == pos.columnIndex())){
                    return hits[i];
                }
            }else{
                if((targetPos.columnIndex() == pos.columnIndex()+i)&&(targetPos.rowIndex() == pos.rowIndex())){
                    return hits[i];
                }
            }
        }
        return false;
    }

    /**
     * Method that handles hits on the boats
     * @param targetPos Position to check if it has been hit
     */
    public void hit(Position targetPos){
        for(int i = 0; i<size; i++){
            if(orientation.equals("vertical")){
                if((targetPos.rowIndex() == pos.rowIndex()+i) && (targetPos.columnIndex() == pos.columnIndex())){
                    hits[i]=true;
                }
            }else{
                if((targetPos.columnIndex() == pos.columnIndex()+i)&&(targetPos.rowIndex() == pos.rowIndex())){
                    hits[i]=true;
                }
            }
        }
    }

    /**
     * @return Returns if the boat has been sunk
     */
    public boolean sunk(){
        for (boolean hit : hits) {
            if (!hit) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return Returns the boat position
     */
    public Position position(){
        return pos;
    }

    public Position[] getAllPos(){
        return boatPos;
    }

    /**
     * @return Returns the orientation of the boat
     */
    public String direction(){
        return orientation;
    }

    //--------------------------------------------------------------------------
    // For Troubleshooting use only
    //--------------------------------------------------------------------------
    public Position[] generateAllPositions(){
       Position[] temp = new Position[size];
        for(int i = 0; i<size; i++){
            if(orientation.equals("vertical")){
                if(pos.rowIndex()+i > 9){
                    temp[i] = new Position(pos.columnIndex(), pos.rowIndex()-1);
                }else{
                    temp[i] = new Position(pos.columnIndex(), pos.rowIndex()+i);
                }
            }else{
                if(pos.columnIndex()+i > 9){
                    temp[i] = new Position(pos.columnIndex()-1, pos.rowIndex());
                }else{
                    temp[i] = new Position(pos.columnIndex()+i, pos.rowIndex());
                }
            }
        }
        return temp;
    }


}
