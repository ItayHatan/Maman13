/**
 * Maman 13
 * @author Itay Hatand
 * @version 10/11/20
 * This class represent a polygon in the Cartesian axis system system.
 */

public class Polygon {

    Point[] _vertices;
    int _noOfVertices;
    final int MAX_VERTICES = 10;
    final  int MIN_TO_POLYGON =3;

    /**
     * Constructor a new polygon.
     * Initialized to max number of vertices.
     */
    public Polygon(){
        _vertices = new Point[MAX_VERTICES];
        _noOfVertices = 0;
    }

    /**
     * Add a vertices to the polygon.
     * @param x The X value of the new point in the polygon.
     * @param y The Y value of the new point in the polygon.
     * @return True if the point added Successfully, false if not.
     */
    public boolean addVertex(double x, double y) {
        if(_noOfVertices < MAX_VERTICES){
            _vertices[_noOfVertices] = new Point(x,y);
            _noOfVertices ++;
            return true;
        }
        return false;
    }

    /**
     * Check the highest point of the polygon.
     * @return The highest point of the polygon.
     */
    public Point highestVertex(){
        if(_noOfVertices > 0){
            int counter = 0;
            double maxY = -1;
            //find what is the max Y value.
            for(int i = 0; i < _noOfVertices; i++){
                if(_vertices[i].getY() > maxY)
                    maxY = _vertices[i].getY();
            }// find which point this value belong.
            for(int j = 0; j <_noOfVertices; j++){
                if(_vertices[j].getY() == maxY){
                    break;
                }else{
                    counter ++;
                }
            }
            return new Point(_vertices[counter]);
        }
        return null;
    }

    /**
     * Return a string representation of this segment in the format:
     * ((2.0,1.0),(5.0,0.0),(7.0,5.0),(4.0,6.0),(1.0,4.0)).
     * @Overrides toString in class java.lang.Object.
     * @return String representation of this polygon.
     */
    @Override
    public String toString() {
        if(_vertices[0] == null){
            return "The polygon has 0 vertices.";
        }
        return "The polygon has " + this._noOfVertices + " vertices:\n(" + visualArr() +
                _vertices[_noOfVertices - 1] + ")";
    }

    /**
     * Calculate the polygon perimeter.
     * @return The polygon perimeter.
     */
    public double calcPerimeter(){
        double perimeter = 0;
        // Compare the pointers with ==.
        if(_vertices[1] == null){
            return 0;
        }else{
            for(int i = 0; i < _noOfVertices -1 ; i++){
                if(_vertices[i + 1] != null){
                    perimeter += _vertices[i].distance(_vertices[i + 1]);
                }
            }perimeter += _vertices[0].distance(_vertices[_noOfVertices -1]);
        } return perimeter;
    }

    /**
     * Calculate the polygon area.
     * @return The polygon area.
     */
    public double calcArea(){
        double sum = 0;
        if(_noOfVertices < MIN_TO_POLYGON){
            sum = 0;
        }else {
            for(int i = 1; i <= _noOfVertices - 2; i++){
                sum += heronCalc(_vertices[0], _vertices[i], _vertices[i + 1]);
            }
        }
        return sum;
    }

    /**
     * Compute the polygon area, which constructed by this polygon and a reference polygon.
     * @param other the reference polygon.
     * @return True if this polygon bigger than the reference polygon.
     */
    public boolean isBigger(Polygon other){
        return this.calcArea() > other.calcArea();
    }

    /**
     * Check what is the position of a point in the array.
     * @param p A point to be check if in the array.
     * @return The position of the point in the array, if not in the array return -1.
     */
    public int findVertex(Point p){
        for(int i =0; i <_noOfVertices; i++){
            if(_vertices[i].equals(p))
                return i;
        }return -1;
    }

    /**
     * Return the next point of the polygon.
     * @param p point to be check if in the polygon.
     * @return if in the polygon -> the next vertices
     *         if the polygon have one vertices or the point in the last position -> the first vertices
     *         else -> return null.
     */
    public Point getNextVertex(Point p){
        if(findVertex(p) == -1){
            return null;
        }else{
            if(_noOfVertices == 1 || _vertices[_noOfVertices -1].equals(p)){
                return new Point(_vertices[0]);
            }else{
                return new Point(_vertices[findVertex(p) +1]);
            }
        }
    }

    /**
     * Create a rectangle as a polygon that block the polygon.
     * @return The rectangle that block as a polygon.
     */

    public Polygon getBoundingBox(){
        if(_noOfVertices <=MIN_TO_POLYGON ){
            return null;
        }else {
            Polygon boundingBox = new Polygon();
            boundingBox.addVertex(getMinX(), getMinY());
            boundingBox.addVertex(getMaxX(), getMinY());
            boundingBox.addVertex(getMaxX(), highestVertex().getY());
            boundingBox.addVertex(getMinX(), highestVertex().getY());
            return boundingBox;
        }
    }

    /*
        Calculate the area of a triangle.
    */
    private double heronCalc(Point one, Point two, Point three){
        double a = one.distance(two);
        double b = one.distance(three);
        double c = two.distance(three);
        double s = (a + b + c) /2;
        double powAreaOfTriangle = s * (s - a)*(s - b) * (s - c);
        return Math.sqrt(powAreaOfTriangle);
    }

    /*
        Return the minimum X value in the polygon.
     */
    private double getMinX(){
        double minX = _vertices[0].getX();
        for (int i = 1; i < _noOfVertices; i++){
            minX = Math.min(minX, _vertices[i].getX());
        }
        return minX;
    }

    /*
        Return the minimum Y value in the polygon.
     */
    private double getMinY(){
        double minY = _vertices[0].getY();
        for (int i = 1; i < _noOfVertices; i++){
            minY = Math.min(minY, _vertices[i].getY());
        }
        return minY  ;
    }

    /*
        Return the maximum X value in the polygon.
     */
    private double getMaxX(){
        double maxX = _vertices[0].getX();
        for (int i = 1; i < _noOfVertices; i++){
            maxX = Math.max(maxX, _vertices[i].getX());
        }
        return maxX;
    }

    /*
        Create a string from all the vertices to string.
     */
    private String visualArr(){
        String s = "";
        for (int i = 0; i < _noOfVertices - 1; i ++){
            if(_vertices[i] != null){
                s += _vertices[i].toString();
                s += ",";
            }
        }
        return s;
    }
}