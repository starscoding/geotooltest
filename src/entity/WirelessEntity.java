package entity;

import java.io.Serializable;

public class WirelessEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8119547781608919221L;
	/**
	 * 资源数据类型-基站
	 */
	public final static int WL_TYPE_BASESTATION = 0;
	/**
	 * 资源数据类型-小区
	 */
	public final static int WL_TYPE_CELL = 1;

	/**
	 * 基站类型-2G基站
	 */
	public final static int WL_TYPE_BS_BTS = 10;
	/**
	 * 基站类型-3G基站
	 */
	public final static int WL_TYPE_BS_NODEB = 11;
	/**
	 * 基站类型-4G基站
	 */
	public final static int WL_TYPE_BS_ENODEB = 12;

	/**
	 * 小区类型-2G小区
	 */
	public final static int WL_TYPE_CELL_CELL = 20;
	/**
	 * 小区类型-3G小区
	 */
	public final static int WL_TYPE_CELL_UTRANCELL = 21;
	/**
	 * 小区类型-4G小区
	 */
	public final static int WL_TYPE_CELL_EUTRANCELL = 22;

	/**
	 * 资源数据类型
	 */
	private int wlType = -1;

	/**
	 * 基站类型（2G基站/3G基站/4G基站）
	 */
	private int btsType = -1;

	/**
	 * 小区类型
	 */
	private int cellType = -1;

	private String stationCategory; // 基站属性，例如：小区覆盖、室内覆盖、宏站等

	private String cityName; // 行政区名称，例如： 徐汇等

	private String name; // 基站或小区综资名称

	private String netId; // 综资网元标识，一般为ID或者编号

	private String status; // 状态：在网，工程等

	private String coverage;// 覆盖类型：市内，室外，室内外

	private String hostAddress;// 设备所属机房地址-输入地址关联

	private String remoteuinitAddress;// 拉远点地址-输入地址关联

	/**
	 * 下面三个地址用来区分地址中含有("号、弄")查询
	 */
	// private String hostAddressNong;// 含有"弄"地址
	// private String hostAddressHao;// 含有"号"地址
	private DoublePoint leftDownPoint; // 左下角顶点经纬度
	private DoublePoint rightUpPoint; // 右上角顶点经纬度

	/**
	 * @return the wlType
	 */
	public int getWlType() {
		return wlType;
	}

	/**
	 * @param wlType
	 *            the wlType to set
	 */
	public void setWlType(int wlType) {
		this.wlType = wlType;
	}

	/**
	 * @return the btsType
	 */
	public int getBtsType() {
		return btsType;
	}

	/**
	 * @param btsType
	 *            the btsType to set
	 */
	public void setBtsType(int btsType) {
		this.btsType = btsType;
	}

	/**
	 * @return the cellType
	 */
	public int getCellType() {
		return cellType;
	}

	/**
	 * @param cellType
	 *            the cellType to set
	 */
	public void setCellType(int cellType) {
		this.cellType = cellType;
	}

	/**
	 * @return the stationCategory
	 */
	public String getStationCategory() {
		return stationCategory;
	}

	/**
	 * @param stationCategory
	 *            the stationCategory to set
	 */
	public void setStationCategory(String stationCategory) {
		this.stationCategory = stationCategory;
	}

	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * @param cityName
	 *            the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the leftDownPoint
	 */
	public DoublePoint getLeftDownPoint() {
		return leftDownPoint;
	}

	/**
	 * @param leftDownPoint
	 *            the leftDownPoint to set
	 */
	public void setLeftDownPoint(DoublePoint leftDownPoint) {
		this.leftDownPoint = leftDownPoint;
	}

	/**
	 * @return the rightUpPoint
	 */
	public DoublePoint getRightUpPoint() {
		return rightUpPoint;
	}

	/**
	 * @param rightUpPoint
	 *            the rightUpPoint to set
	 */
	public void setRightUpPoint(DoublePoint rightUpPoint) {
		this.rightUpPoint = rightUpPoint;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the netId
	 */
	public String getNetId() {
		return netId;
	}

	/**
	 * @param netId
	 *            the netId to set
	 */
	public void setNetId(String netId) {
		this.netId = netId;
	}

	public String getCoverage() {
		return coverage;
	}

	public void setCoverage(String coverage) {
		this.coverage = coverage;
	}

	public String getHostAddress() {
		return hostAddress;
	}

	public void setHostAddress(String hostAddress) {
		this.hostAddress = hostAddress;
	}

	public String getRemoteuinitAddress() {
		return remoteuinitAddress;
	}

	public void setRemoteuinitAddress(String remoteuinitAddress) {
		this.remoteuinitAddress = remoteuinitAddress;
	}



}
