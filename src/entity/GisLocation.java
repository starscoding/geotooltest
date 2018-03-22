package entity;

import java.io.Serializable;

public class GisLocation implements Serializable{

	private static final long serialVersionUID = -8569348127142633942L;
	
	private String id;
	private Integer version;
	private String location;
	private String locationPinyin;
	private String locationPinyin_s;
	private String locationPinyinShort;
	private double longitude;
	private double latitude;
	private String city;
	private String county;
	private String origin;
	private String locationType;
	private String publishtime;
	private String detailtype;
	private String endtime;
	private String affectService; // add by wangfy 2011-6-15
	private String address;
	private String telephone;
	private String ctype;
	private String ntype;
	private String name;
	private String road;
	private String menpai;
	private String fullLocation;
	private String stdLocation;

	int[] shuziList = new int[3];

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocationPinyin() {
		return locationPinyin;
	}

	public void setLocationPinyin(String locationPinyin) {
		this.locationPinyin = locationPinyin;
	}

	public String getLocationPinyin_s() {
		return locationPinyin_s;
	}

	public void setLocationPinyin_s(String locationPinyin_s) {
		this.locationPinyin_s = locationPinyin_s;
	}

	public String getLocationPinyinShort() {
		return locationPinyinShort;
	}

	public void setLocationPinyinShort(String locationPinyinShort) {
		this.locationPinyinShort = locationPinyinShort;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPublishtime() {
		return publishtime;
	}

	public void setPublishtime(String publishtime) {
		this.publishtime = publishtime;
	}

	public String getDetailtype() {
		return detailtype;
	}

	public void setDetailtype(String detailtype) {
		this.detailtype = detailtype;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getAffectService() {
		return affectService;
	}

	public void setAffectService(String affectService) {
		this.affectService = affectService;
	}

	public String getAddress() {
		return address;
	}

	public void Location(String address) {
		this.address = address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	public String getNtype() {
		return ntype;
	}

	public void setNtype(String ntype) {
		this.ntype = ntype;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoad() {
		return road;
	}

	public void setRoad(String road) {
		this.road = road;
	}

	public String getMenpai() {
		return menpai;
	}

	public void setMenpai(String menpai) {
		this.menpai = menpai;
	}

	public String getFullLocation() {
		return fullLocation;
	}

	public void setFullLocation(String fullLocation) {
		this.fullLocation = fullLocation;
	}

	public String getStdLocation() {
		return stdLocation;
	}

	public void setStdLocation(String stdLocation) {
		this.stdLocation = stdLocation;
	}

	public int[] getShuziList() {
		return shuziList;
	}

	public void setShuziList(int[] shuziList) {
		this.shuziList = shuziList;
	}


}
