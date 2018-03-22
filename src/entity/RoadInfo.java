package entity;

import java.util.List;

public class RoadInfo {

	private String id;
	private String lonlats;
	private String mesh;
	private String nameOrg;
	private String name;
	private String namePY;
	private String type;
	private String width;
	private String routeNo;
	private String length;
	private String lonStart;
	private String latStart;
	private String lonEnd;
	private String latEnd;
//	private Point coordinate;
	
	private double xStart;
	private double xEnd;
	private double yStart;
	private double yEnd;
	
	private List<Point> points;
	

	
	public List<Point> getPoints() {
		return points;
	}
	public void setPoints(List<Point> points) {
		this.points = points;
	}
	public double getxStart() {
		return xStart;
	}
	public void setxStart(double xStart) {
		this.xStart = xStart;
	}
	public double getxEnd() {
		return xEnd;
	}
	public void setxEnd(double xEnd) {
		this.xEnd = xEnd;
	}
	public double getyStart() {
		return yStart;
	}
	public void setyStart(double yStart) {
		this.yStart = yStart;
	}
	public double getyEnd() {
		return yEnd;
	}
	public void setyEnd(double yEnd) {
		this.yEnd = yEnd;
	}
	public String getNameOrg() {
		return nameOrg;
	}
	public void setNameOrg(String nameOrg) {
		this.nameOrg = nameOrg;
	}
	public String getLonStart() {
		return lonStart;
	}
	public void setLonStart(String lonStart) {
		this.lonStart = lonStart;
	}
	public String getLatStart() {
		return latStart;
	}
	public void setLatStart(String latStart) {
		this.latStart = latStart;
	}
	public String getLonEnd() {
		return lonEnd;
	}
	public void setLonEnd(String lonEnd) {
		this.lonEnd = lonEnd;
	}
	public String getLatEnd() {
		return latEnd;
	}
	public void setLatEnd(String latEnd) {
		this.latEnd = latEnd;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLonlats() {
		return lonlats;
	}
	public void setLonlats(String lonlats) {
		this.lonlats = lonlats;
	}
	public String getMesh() {
		return mesh;
	}
	public void setMesh(String mesh) {
		this.mesh = mesh;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNamePY() {
		return namePY;
	}
	public void setNamePY(String namePY) {
		this.namePY = namePY;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getRouteNo() {
		return routeNo;
	}
	public void setRouteNo(String routeNo) {
		this.routeNo = routeNo;
	}
	@Override
	public String toString() {
		return "RoadInfo [id=" + id + ", latEnd=" + latEnd + ", latStart=" + latStart + ", length=" + length + ", lonEnd=" + lonEnd + ", lonStart=" + lonStart + ", lonlats=" + lonlats + ", mesh="
				+ mesh + ", name=" + name + ", namePY=" + namePY + ", routeNo=" + routeNo + ", type=" + type + ", width=" + width + "]";
	}
	
	
}
