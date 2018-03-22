package entity;

public class GisLocationInfo extends GisLocation {

	
	private static final long serialVersionUID = -8569348127142633942L;
	private String locationName;
	private String lon_str;
	private String lat_str;

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	
	public String getLon_str() {
		return lon_str;
	}

	public void setLon_str(String lon_str) {
		this.lon_str = lon_str;
	}

	public String getLat_str() {
		return lat_str;
	}

	public void setLat_str(String lat_str) {
		this.lat_str = lat_str;
	}

	public void init(GisLocation gl){
		this.setCity(gl.getCity());
		this.setCounty(gl.getCounty());
		this.setId(gl.getId());
		this.setLatitude(gl.getLatitude());
		this.setLocation(gl.getLocation());
		this.setLocationType(gl.getLocationType());
		this.setLongitude(gl.getLongitude());
		this.setOrigin(gl.getOrigin());
		this.setLocationName(gl.getLocation());
		this.setAddress(gl.getAddress());
		this.setTelephone(gl.getTelephone());
		this.setCtype(gl.getCtype());
		this.setNtype(gl.getNtype());
		this.setVersion(gl.getVersion());
		this.setName(gl.getName());
		this.setRoad(gl.getRoad());
		this.setMenpai(gl.getMenpai());
		this.setFullLocation(gl.getFullLocation());
		this.setStdLocation(gl.getStdLocation());
	}

}
