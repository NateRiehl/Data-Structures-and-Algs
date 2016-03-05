import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
/**
 * Disk.java
 * CS216, Fall 2016
 * @author Nate Riehl
 * Implementation of Disk class for Packing game
 *
 */
public class Disk {
	private int cx;
	private int origCX; //Used for reset() method
	private int cy;
	private int origCY; //Used for reset() method
	private int radius;
	private int diameter;
	private int ID;
	private static int nextID;
	private Color c;
	/**
	 * Disk constructor which takes in a center-x, center-x, and radius and instantiates a disk
	 * @param cx is the center x-coordinate of Disk
	 * @param cy is the center y-coordinate of Disk
	 * @param radius is the radius of Disk
	 */
	public Disk(int cx, int cy, int radius){
		this.cx = cx;
		this.origCX = cx;
		this.cy = cy;
		this.origCY = cy;
		this.radius = radius;		
		diameter = radius * 2;
		c = Color.getHSBColor((float) Math.random(), (float) Math.random(), (float) Math.random()); // Returns a random color
		ID = nextID;
		nextID++;
	}
	/**
	 * Draws filled circle at point (cx, cy) with given dimensions
	 * @param g is used to create filled oval shape and set ID
	 */
	public void draw(Graphics g){
		g.setColor(c);
		g.fillOval(cx - radius, cy - radius, diameter, diameter);
		g.setColor(Color.BLACK);
		g.drawString("" + ID, cx, cy);
	}
	/**
	 * @return the center x-coordinate of the Disk
	 */
	public int getCX(){
		return cx;
	}
	/**
	 * @return the center y-coordinate of the Disk
	 */
	public int getCY(){
		return cy;
	}
	/**
	 * @return the radius of the Disk
	 */
	public int getRadius(){
		return radius;
	}	
	/**
	 * Uses Area formula to compute area of Disk
	 *@return the area of the disk
	 */
	public double area(){
		return Math.PI * (Math.pow(radius, 2));
	}
	/**
	 * Set center to parameter, ordered pair of location (x,y)
	 * @param x coordinate to set center 
	 * @param y coordinate to set center
	 */
	public void setCenter(int x, int y){
		cx = x;
		cy = y;
	}
	/**
	 * Reset Disk to its original center
	 */
	public void reset(){
		setCenter(origCX, origCY);
	}
	/**
	 * Checks if point (x,y) is inside the disk
	 * @param x -coordinate to check
	 * @param y -coordinate to check
	 * @return Whether (x, y) is within boundaries of Disk
	 */
	public boolean inside(int x, int y){
		int left = cx - radius;
		int right = cx + radius;

		int top = cy - radius;
		int bottom = cy + radius;

		if(x > left && x < right){
			if(y > top && y < bottom){
				return true;
			}
		}
		return false;
	}
	/**
	 * Checks if Disk is within bounded rectangle
	 * @param x -coordinate of upper-left of rectangle
	 * @param y -coordinate of upper-left of rectangle
	 * @param w is width of the rectangle
	 * @param h is height of the rectangle
	 * @return  true if the disk is within the rectangle given by the parameters (x,y) and width/height. False otherwise
	 */
	public boolean within(int x, int y, int w, int h){
		int leftX = cx-radius;
		int rightX = cx+radius;
		int topY = cy-radius;
		int bottomY = cy + radius;
		if((leftX) > x && (rightX) < (x+w)){
			if((topY) > y && (bottomY) < (y + h)){
				return true;
			}
		}
		return false;
	}
	/**
	 * Uses the Euclidean formula to calculate the distance between Disk and a point (xp, yp)
	 * @param xp -coordinate of point to check
	 * @param yp -coordinate of point to check
	 * @return the distance between the center of this disk and (xp, yp)
	 */
	public double distance(int xp, int yp){
		double a = Math.pow(cx - xp, 2); //Half of euclidean equation
		double b = Math.pow(cy - yp, 2); //Other half of eucl.
		//Calculates euclidean distance between (xp, yp) and (cx, cy)
		return Math.sqrt(a + b);	
	}
	/**
	 * Calculates the distance between this Disk and a separate Disk
	 * @param d
	 *@return the distance between the center of this disk and the center of d
	 */
	public double distance(Disk d){
		int dx = d.getCX();
		int dy = d.getCY();
		double a = Math.pow(cx - dx, 2); //Half of euclidean equation
		double b = Math.pow(cy - dy, 2); //Other half of eucl.
		//Calculates euclidean distance between (xp, yp) and (cx, cy)
		return Math.sqrt(a + b);	
	}
	/**
	 * Checks if this disk is within the boundaries of another disk
	 * @param d
	 * @return true if Disk d overlaps with Disk. False otherwise-
	 */
	public boolean overlap(Disk d){
		int dist = d.getRadius() + radius; //r1 + r2
		double distance = distance(d);
		if(distance < dist){
			return true;
		}
		return false;
	}
}


