package nz.ac.wgtn.swen225.lc.domain;

public class Position{
    private int x,y;

    /**
     * Default Constructor, Initialzes at zero
     */
    public Position(){
        x = 0;
        y = 0;
    }

    /**
     * Initilizes position at given point
     */
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    // X Getter and Setters

    /**
     * Gets X parameter
     */
    public int getX(){
        return x;
    }
    /**
     * Sets X parameter
     */
    public void setX(int x){
        this.x = x;
    }

    // X Getter and Setter
    /**
     * Gets Y parameter
     */
    public int getY(){
        return y;
    }
    /**
     * Sets Y parameter
     */
    public void setY(int y){
        this.y = y;
    }

    /**
     * Gives String output for debugging
     */
    @Override
    public String toString(){
        return "Position - X: " + this.getX() + " Y: " + this.getY();
    }

    /**
     * Checks if given object has same X and Y value
     */
    @Override
    public boolean equals(Object other){
        //If same obj - true
        if(this == other)
            return true;

        //If other not same class - false
        if(!(other instanceof Position))
            return false;

        //If values are same - true otherwise false;
        Position o = (Position) other;
        return this.getX() == o.getX() && this.getY() == o.getY();

    }

}