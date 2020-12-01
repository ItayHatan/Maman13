/**
 * Maman 12.1
 * This class present a point in the first quoter of the Cartesian axis system.
 * @author Itay Hatan.
 * @version 15/10/20
 */

public class Point {

    private double _radius;
    private double _alpha ;
    private final double DEFAULT_VALUE = 0;
    private final double MAX_ALPHA = 90;

    /**
     * Constructor for objects of class Point. Construct a new point with the specified x y coordinates.
     * If the x coordinate is negative it is set to zero. If the y coordinate is negative it is set to zero.
     * @param x The X coordinate.
     * @param y The Y coordinate.
     */

    public Point (double x, double y) {
        if (y< DEFAULT_VALUE) {
            y = DEFAULT_VALUE;
        }
        if (x<= DEFAULT_VALUE)
        {
            x = DEFAULT_VALUE;
            _alpha = MAX_ALPHA;
        }
        else
            _alpha = this.radiansToDegrees(Math.atan(y / x));
        _radius = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    /**
     * Constructor for objects of class Point. Copy constructor, construct a point using another point.
     * @param other - The point from which to construct the new object
     */
    public Point (Point other){
        _radius = other._radius;
        _alpha = other._alpha;
    }

    private double degreesToRadian(double alpha){
        //Convert method - convert from degree to radian
        return alpha * Math.PI / 180;
    }

    private double radiansToDegrees(double alpha){
        //Convert method - convert from radian to degree
        return alpha * 180 / Math.PI;
    }

    /**
     * Check the distance between this point and a given point.
     * @param other The point to check the distance from.
     * @return The distance.
     */
    public double distance(Point other){
        double deltaX, deltaY;
        deltaX = this.getX() - other.getX();
        deltaY = this.getY() - other.getY();
        return Math.sqrt(Math.pow(deltaX,2)+Math.pow(deltaY,2));
    }

    /**
     * Check if the given point is equal to this point.
     * @param other The point to check equality with
     * @return True if the given point is equal to this point.
     */
    public boolean equals (Point other){
        return this.getX() == other.getX() && this.getY() == other.getY();
    }

    /**
     * This method returns the x coordinate of the point.
     * @return The x coordinate of the point.
     */
    public double getX() {
        double alphaToRadians =  this.degreesToRadian(_alpha );
        return  this.round(Math.cos(alphaToRadians)* _radius);
    }


    /**
     * This method returns the y coordinate of the point.
     * @return The y coordinate of the point.
     */
    public double getY() {
        double alphaToRadians =  this.degreesToRadian(_alpha );
        return  this.round(Math.sin(alphaToRadians)* _radius);
    }

    /**
     * Check if this point is above a received point.
     * @param other - The point to check if this point is above.
     * @return True if this point is above the other point.
     */
    public boolean isAbove (Point other){
        return this.getY() > other.getY();
    }

    /**
     * Check if this point is left of a received point.
     * @param other - The point to check if this point is left of.
     * @return True if this point is left of the other point.
     */
    public boolean isLeft (Point other){
        return this.getX() < other.getX();
    }

    /**
     * Check if this point is right of a received point.
     * @param other - The point to check if this point is right of.
     * @return True if greater, False if not.
     */
    public boolean isRight (Point other){
        return other.isLeft(this);
    }

    /**
     * Check if this point is below a received point.
     * @param other - The point to check if this point is below.
     * @return True if this point is below the other point.
     */
    public boolean isUnder (Point other){
        return other.isAbove(this);
    }

    /**
     * Moves a point. If either coordinate becomes negative the point remains unchanged.
     * @param dx The difference to add to x.
     * @param dy The difference to add to y.
     */
    public void move(double dx, double dy) {
        if(this.getY() + dy >= DEFAULT_VALUE && this.getX() + dx >= DEFAULT_VALUE){
            this.setY(this.getY() + dy);
            this.setX(this.getX() + dx);
        }
    }

    /**
     * This method sets the x coordinate of the point.
     * If the new x coordinate is negative the old x coordinate will remain unchanged.
     * @param x The new x coordinate.
     */
    public void setX(double x) {
        double y = this.getY();
        if (x > DEFAULT_VALUE) {
            _alpha = (Math.atan(y / x) * 180) / Math.PI;
            _radius = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        }
    }

    /**
     * This method sets the y coordinate of the point.
     * If the new y coordinate is negative the old y coordinate will remain unchanged.
     * @param y the new value of Y coordinate, must be greater then DEFAULT VALUE.
     */
    public void setY(double y) {
        double x = this.getX();
        if(y > DEFAULT_VALUE){
            _alpha = (Math.atan(y/ x) *180) /Math.PI;
            _radius = Math.sqrt(Math.pow(x, 2) +Math.pow(y ,2));
        }
    }

    /**
     * Returns a string representation of Point in the format (x,y).
     * @Overrides toString in class java.lang.Object.
     * @return A String representation of the Point.
     */
    public String toString(){
        return "(" + this.getX() + "," + this.getY() + ")";
    }

    private double round(double num) // round a number so will be only 4 numbers after the point
    {
        return Math.round(num*1000000000)/1000000000.0;
    }
}