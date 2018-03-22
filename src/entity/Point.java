package entity;

public class Point {

	private double x;
	private double y;
	private double lon;
	private double lat;
	
	public static double X_EACH_WIDTH_1000 = 0.01042792345332; // 经度1000m每格长度
	public static double Y_EACH_WIDTH_1000 = 0.00898316074327; // 纬度1000m每格长度
	
	

	public Point(double lon, double lat) {
		super();
		this.lon = lon;
		this.lat = lat;
	}

	public Point() {
		super();
		// TODO Auto-generated constructor stub
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

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public static Point lonLatToKm(Point p){
		p.setX(p.getX()/X_EACH_WIDTH_1000);
		p.setY(p.getLat()/Y_EACH_WIDTH_1000);
		return p;
	}
	
	public static Point kmToLonLat(Point p){
		p.setLon(p.getX()*X_EACH_WIDTH_1000);
		p.setLat(p.getY()*Y_EACH_WIDTH_1000);
		return p;
	}

	@Override
//	public String toString() {
//		return "Point [lat=" + lat + ", lon=" + lon + ", x=" + x + ", y=" + y + "]";
//	}
	public String toString() {
		return lon + ","+lat;
	}
	
}
