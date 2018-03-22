package entity;

public class GridInfo {

	private String id;
	private String districtList;
	private double lon;
	private double lat;
	private double minLon;
	private double minLat;
	private double maxLon;
	private double maxLat;
	private int xwidth;
	private int ywidth;
	private int position;
	private String colName;
	private int rowName;
	private String district;
	private String vendor;
	
	
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDistrictList() {
		return districtList;
	}
	public void setDistrictList(String districtList) {
		this.districtList = districtList;
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
	public double getMinLon() {
		return minLon;
	}
	public void setMinLon(double minLon) {
		this.minLon = minLon;
	}
	public double getMinLat() {
		return minLat;
	}
	public void setMinLat(double minLat) {
		this.minLat = minLat;
	}
	public double getMaxLon() {
		return maxLon;
	}
	public void setMaxLon(double maxLon) {
		this.maxLon = maxLon;
	}
	public double getMaxLat() {
		return maxLat;
	}
	public void setMaxLat(double maxLat) {
		this.maxLat = maxLat;
	}
	public int getXwidth() {
		return xwidth;
	}
	public void setXwidth(int xwidth) {
		this.xwidth = xwidth;
	}
	public int getYwidth() {
		return ywidth;
	}
	public void setYwidth(int ywidth) {
		this.ywidth = ywidth;
	}
	public String getColName() {
		return colName;
	}
	public void setColName(String colName) {
		this.colName = colName;
	}
	public int getRowName() {
		return rowName;
	}
	public void setRowName(int rowName) {
		this.rowName = rowName;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	
	
}
