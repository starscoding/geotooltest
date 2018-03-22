package entity;

import java.io.Serializable;

public class ZongzStation implements Serializable {
	
	private static final long serialVersionUID = 8391417616702866128L;

	private String bts_id; // 基站ID/BSC编号
	private String bts_name; // 基站名称
	private String bsc; // BSC ID/归属的bsc标识
	private String net_flight; // 行政区域
	private String address; // 网元设备所属机房地址
	private double longitude; // 网元对应天线所在经度
	private double latitude; // 网元对应天线所在纬度
	private String vendor; // 设备厂商
	private String bts_type; // 基站属性/类型例如：小区覆盖、室内覆盖、宏站等
	private String bts_state; // 网元状态/基站状态//生命周期状态
	private String area; // 覆盖类型/地理区域 室外
	private String city_country; // 所属区县ID/城乡区域
	private String bts_level; // 级别/基站等级
	private String rpttime; // 更新时间
	private String batch_num; // 入库时间戳/数据更新时间戳
	private String dependency;// 管理部门
	private String typeFlag; // 使用频段
	private String cell_name; // 综资小区名
	private String lac; // 小区LAC
	private String ci; // 小区CI
	private String ecgi; // 小区CI
	private String cover_universit; // 覆盖场景
	private int antenna_height; // 天线高度
	private int antenna_direction_angle; // 天线角度
	private String netType; // 网元类型，例如2G基站、2G小区、3G基站等
	private String huawu_name; // 对应的话务资源名称

	// add by luxy 2015-06-30
	private String vip_type;// VIP级别 ，例如:一级vip、非vip、etc
	private String province_id;// 所属省
	private String city_id;// 所属地市
	private String county_id;// 所属区县
	private String room_id;// 所属机房/资源点
	private String shalianame; // 别名
	private String omc_name;// 所属Omc
	private String antenna_type;// 天线类型
	private String total_angel;// 总俯仰角
	private String elec_down_angel;// 电下倾角
	private String machine_down_angel;// 机械下倾角
	private String antenna_name;// 天线名称
	private String bts_real_id;// 所属bts/ENODEB_ID NODEB_ID BTS_ID

	private int min;
	private int max;

	private int gisCount;
	private int gisCount_w;

	public ZongzStation() {

	}

	public ZongzStation(String bts_id, String district, String bts_type,
			String bts_name) {
		this.bts_id = bts_id;
		this.net_flight = district;
		this.bts_type = bts_type;
		this.bts_name = bts_name;
	}

	public ZongzStation(String bts_id, String district, String bts_type,
			String bts_name, String typeFlag) {
		this.bts_id = bts_id;
		this.net_flight = district;
		this.bts_type = bts_type;
		this.bts_name = bts_name;
		this.typeFlag = typeFlag;
	}

	public String getBts_real_id() {
		return bts_real_id;
	}

	public void setBts_real_id(String btsRealId) {
		bts_real_id = btsRealId;
	}

	/**
	 * @return the bts_id
	 */
	public String getBts_id() {
		return bts_id;
	}

	/**
	 * @param bts_id
	 *            the bts_id to set
	 */
	public void setBts_id(String bts_id) {
		this.bts_id = bts_id;
	}

	public String getAntenna_name() {
		return antenna_name;
	}

	public void setAntenna_name(String antennaName) {
		antenna_name = antennaName;
	}

	/**
	 * @return the bts_name
	 */
	public String getBts_name() {
		return bts_name;
	}

	/**
	 * @param bts_name
	 *            the bts_name to set
	 */
	public void setBts_name(String bts_name) {
		this.bts_name = bts_name;
	}

	/**
	 * @return the bsc
	 */
	public String getBsc() {
		return bsc;
	}

	/**
	 * @param bsc
	 *            the bsc to set
	 */
	public void setBsc(String bsc) {
		this.bsc = bsc;
	}

	/**
	 * @return the net_flight
	 */
	public String getNet_flight() {
		return net_flight;
	}

	/**
	 * @param net_flight
	 *            the net_flight to set
	 */
	public void setNet_flight(String net_flight) {
		this.net_flight = net_flight;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude
	 *            the longitude to set
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude
	 *            the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the vendor
	 */
	public String getVendor() {
		return vendor;
	}

	/**
	 * @param vendor
	 *            the vendor to set
	 */
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	/**
	 * @return the bts_type
	 */
	public String getBts_type() {
		return bts_type;
	}

	/**
	 * @param bts_type
	 *            the bts_type to set
	 */
	public void setBts_type(String bts_type) {
		this.bts_type = bts_type;
	}

	/**
	 * @return the bts_state
	 */
	public String getBts_state() {
		return bts_state;
	}

	/**
	 * @param bts_state
	 *            the bts_state to set
	 */
	public void setBts_state(String bts_state) {
		this.bts_state = bts_state;
	}

	/**
	 * @return the area
	 */
	public String getArea() {
		return area;
	}

	/**
	 * @param area
	 *            the area to set
	 */
	public void setArea(String area) {
		this.area = area;
	}

	/**
	 * @return the city_country
	 */
	public String getCity_country() {
		return city_country;
	}

	/**
	 * @param city_country
	 *            the city_country to set
	 */
	public void setCity_country(String city_country) {
		this.city_country = city_country;
	}

	/**
	 * @return the bts_level
	 */
	public String getBts_level() {
		return bts_level;
	}

	/**
	 * @param bts_level
	 *            the bts_level to set
	 */
	public void setBts_level(String bts_level) {
		this.bts_level = bts_level;
	}

	/**
	 * @return the rpttime
	 */
	public String getRpttime() {
		return rpttime;
	}

	/**
	 * @param rpttime
	 *            the rpttime to set
	 */
	public void setRpttime(String rpttime) {
		this.rpttime = rpttime;
	}

	/**
	 * @return the batch_num
	 */
	public String getBatch_num() {
		return batch_num;
	}

	/**
	 * @param batch_num
	 *            the batch_num to set
	 */
	public void setBatch_num(String batch_num) {
		this.batch_num = batch_num;
	}

	/**
	 * @return the dependency
	 */
	public String getDependency() {
		return dependency;
	}

	/**
	 * @param dependency
	 *            the dependency to set
	 */
	public void setDependency(String dependency) {
		this.dependency = dependency;
	}

	/**
	 * @return the min
	 */
	public int getMin() {
		return min;
	}

	/**
	 * @param min
	 *            the min to set
	 */
	public void setMin(int min) {
		this.min = min;
	}

	/**
	 * @return the max
	 */
	public int getMax() {
		return max;
	}

	/**
	 * @param max
	 *            the max to set
	 */
	public void setMax(int max) {
		this.max = max;
	}

	/**
	 * @return the gisCount
	 */
	public int getGisCount() {
		return gisCount;
	}

	/**
	 * @param gisCount
	 *            the gisCount to set
	 */
	public void setGisCount(int gisCount) {
		this.gisCount = gisCount;
	}

	/**
	 * @return the gisCount_w
	 */
	public int getGisCount_w() {
		return gisCount_w;
	}

	/**
	 * @param gisCount_w
	 *            the gisCount_w to set
	 */
	public void setGisCount_w(int gisCount_w) {
		this.gisCount_w = gisCount_w;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.eastcom_sw.cis.domain.BaseObject#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.eastcom_sw.cis.domain.BaseObject#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.eastcom_sw.cis.domain.BaseObject#hashCode()
	 */
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @return the typeFlag
	 */
	public String getTypeFlag() {
		return typeFlag;
	}

	/**
	 * @param typeFlag
	 *            the typeFlag to set
	 */
	public void setTypeFlag(String typeFlag) {
		this.typeFlag = typeFlag;
	}

	/**
	 * @return the cell_name
	 */
	public String getCell_name() {
		return cell_name;
	}

	/**
	 * @param cell_name
	 *            the cell_name to set
	 */
	public void setCell_name(String cell_name) {
		this.cell_name = cell_name;
	}

	/**
	 * @return the lac
	 */
	public String getLac() {
		return lac;
	}

	/**
	 * @param lac
	 *            the lac to set
	 */
	public void setLac(String lac) {
		this.lac = lac;
	}

	/**
	 * @return the ci
	 */
	public String getCi() {
		return ci;
	}

	/**
	 * @param ci
	 *            the ci to set
	 */
	public void setCi(String ci) {
		this.ci = ci;
	}

	/**
	 * @return the cover_universit
	 */
	public String getCover_universit() {
		return cover_universit;
	}

	/**
	 * @param cover_universit
	 *            the cover_universit to set
	 */
	public void setCover_universit(String cover_universit) {
		this.cover_universit = cover_universit;
	}

	/**
	 * @return the antenna_height
	 */
	public int getAntenna_height() {
		return antenna_height;
	}

	/**
	 * @param antenna_height
	 *            the antenna_height to set
	 */
	public void setAntenna_height(int antenna_height) {
		this.antenna_height = antenna_height;
	}

	/**
	 * @return the antenna_direction_angle
	 */
	public int getAntenna_direction_angle() {
		return antenna_direction_angle;
	}

	/**
	 * @param antenna_direction_angle
	 *            the antenna_direction_angle to set
	 */
	public void setAntenna_direction_angle(int antenna_direction_angle) {
		this.antenna_direction_angle = antenna_direction_angle;
	}

	/**
	 * @return the huawu_name
	 */
	public String getHuawu_name() {
		return huawu_name;
	}

	/**
	 * @param huawu_name
	 *            the huawu_name to set
	 */
	public void setHuawu_name(String huawu_name) {
		this.huawu_name = huawu_name;
	}

	/**
	 * @return the netType
	 */
	public String getNetType() {
		return netType;
	}

	/**
	 * @param netType
	 *            the netType to set
	 */
	public void setNetType(String netType) {
		this.netType = netType;
	}

	public String getVip_type() {
		return vip_type;
	}

	public void setVip_type(String vipType) {
		vip_type = vipType;
	}

	public String getProvince_id() {
		return province_id;
	}

	public void setProvince_id(String provinceId) {
		province_id = provinceId;
	}

	public String getCity_id() {
		return city_id;
	}

	public void setCity_id(String cityId) {
		city_id = cityId;
	}

	public String getCounty_id() {
		return county_id;
	}

	public void setCounty_id(String countyId) {
		county_id = countyId;
	}

	public String getRoom_id() {
		return room_id;
	}

	public void setRoom_id(String roomId) {
		room_id = roomId;
	}

	public String getShalianame() {
		return shalianame;
	}

	public void setShalianame(String shalianame) {
		this.shalianame = shalianame;
	}

	public String getOmc_name() {
		return omc_name;
	}

	public void setOmc_name(String omcName) {
		omc_name = omcName;
	}

	public String getAntenna_type() {
		return antenna_type;
	}

	public void setAntenna_type(String antennaType) {
		antenna_type = antennaType;
	}

	public String getTotal_angel() {
		return total_angel;
	}

	public void setTotal_angel(String totalAngel) {
		total_angel = totalAngel;
	}

	public String getElec_down_angel() {
		return elec_down_angel;
	}

	public void setElec_down_angel(String elecDownAngel) {
		elec_down_angel = elecDownAngel;
	}

	public String getMachine_down_angel() {
		return machine_down_angel;
	}

	public void setMachine_down_angel(String machineDownAngel) {
		machine_down_angel = machineDownAngel;
	}

	public String getEcgi() {
		return ecgi;
	}

	public void setEcgi(String ecgi) {
		this.ecgi = ecgi;
	}


}
