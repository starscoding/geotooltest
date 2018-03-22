package entity;

import java.io.Serializable;

public class DoublePoint implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -9001840026493208492L;
	
	public double x;
	public double y;
	
	public DoublePoint(){
	}
	public DoublePoint(double xx,double yy){
		x = xx;
		y = yy;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}


}
