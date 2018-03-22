package entity;

public class GisGsmBqzInfo {

	private static final long serialVersionUID = 8762539700173078466L;

	/**
	 * 规划站ID
	 */
	private String id;

	/**
	 * 弱覆盖点经度
	 */
	private double longitude;

	/**
	 * 弱覆盖点纬度
	 */
	private double latitude;

	/**
	 * 行政区
	 */
	private String zone;

	/**
	 * 属地分公司
	 */
	private String district;

	/**
	 * 详细地址
	 */
	private String address;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 中文名称
	 */
	private String nameCn;

	/**
	 * 归属片区ID
	 */
	private String blockId;

	/**
	 * 数据入库时间戳
	 */
	private String batchNum;

	private double max_lng;
	private double max_lat;
	private double min_lng;
	private double min_lat;
	private String startTime;
	private String endTime;

	/**
	 * 环线位置
	 */
	private String loopLocation;
	/**
	 * 所属场景
	 */
	private String scene;
	/**
	 * 加站编号(唯一性)
	 */
	private String addstationNum;
	/**
	 * 厂商
	 */
	private String manufacturer;
	/**
	 * 站型
	 */
	private String stationType;
	/**
	 * 加站类型
	 */
	private String addstationType;
	/**
	 * 加站理由
	 */
	private String addstationReason;
	/**
	 * 规划挂高
	 */
	private String ghHigh;
	/**
	 * 规划方向
	 */
	private String ghPath;
	/**
	 * 规划倾角
	 */
	private String ghDip;
	/**
	 * 所属方案
	 */
	private String program;
	/**
	 * 原站名
	 */
	private String baseName;
	/**
	 * 搬迁名
	 */
	private String tfName;
	/**
	 * 关闭时间
	 */
	private String closeTime;
	/**
	 * 数据更新时间 格式：YYYY-MM-DD HH24:MI:SS
	 */
	private String updateTime;
	/**
	 * 规划站类型 0:规划站;1:搬迁站
	 */
	private String ghzType;
	/**
	 * 工程进度类型 1:购租中;2:工程建设中;3:受阻; 4:已入网;
	 */
	private String progressType;
	/**
	 * 工程进度子类别
	 */
	private String progressSubtype;
	/**
	 * 站名
	 */
	private String stationName;
	/**
	 * 预计入网时间
	 */
	private String expectNtime;
	/**
	 * 是否未入网 0:未入网 1：入网
	 */
	private String isNotNetwork;
	/**
	 * 基站关闭原因
	 */
	private String closeReason;
	/**
	 * 待审核状态 0:待提交审核 1:待审核 2:已审核
	 */
	private String progressStation;
	/**
	 * 关联规划站当前进度信息
	 */
	private GisGsmBqzProgress nowProgressInfo;
	/**
	 * 是否是规划站管理查询 0 ：规划站 1：搬迁站
	 */
	private String ifGhzManageSelect;
	/**
	 * 关联规划站预计入网时间修改信息
	 */
	private GisGhzExpectNtime gisGhzExpectNtime;
	/**
	 * 分公司规划站数量
	 */
	private String districtGhzNum;
	/**
	 * 未更新规划站数量 默认为0
	 */
	private String notModifyGhzNum = "0";

	/**
	 * 已入网规划站数量 默认为0
	 */
	private String listSteptNum = "0";
	/**
	 * 已更新规划站数量 默认为0
	 */
	private String modifyNum = "0";

	/**
	 * 未入网规划站数量 默认为0
	 */
	private String notlistSteptNum = "0";

	/**
	 * 更新率 默认为0%
	 */
	private String modifyNumParsent = "0%";

	/**
	 * 修改时间
	 */
	private String modifyTime;
	/**
	 * 入库时间
	 */
	private String recordTime;
	/**
	 * 口径应用状态（0:在用; 1:正常关闭; 2:过期关闭）
	 */
	private String ifClosed;

	private String progress11Remark;
	private String progress11Stime;
	private String flag;
	/**
	 * 操作时间
	 */
	private String operatingTime;
	/**
	 * 根据不同的操作角色生成的记录类型，用于设置用户查询记录的权限 记录类型： 0:新增 1：保存未提交审核 2：待审核 3：已审核
	 * 
	 */
	private String recordType;
	/***
	 * 授权类型： 0：新增权限 1：新增记录审核权限 2：修改权限 3：修改审核权限
	 */
	private String authorityType;

	/***
	 * 授权类型：
	 * 
	 * 1：自动导入 其他：手动导入
	 */
	private String ifAuto;

	private String progress12Ntime;
	private double distance;

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	/**
	 * @return the recordType
	 */
	public String getRecordType() {
		return recordType;
	}

	/**
	 * @param recordType
	 *            the recordType to set
	 */
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	/**
	 * @return the authorityType
	 */
	public String getAuthorityType() {
		return authorityType;
	}

	/**
	 * @param authorityType
	 *            the authorityType to set
	 */
	public void setAuthorityType(String authorityType) {
		this.authorityType = authorityType;
	}

	public String getOperatingTime() {
		return operatingTime;
	}

	public void setOperatingTime(String operatingTime) {
		this.operatingTime = operatingTime;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getProgress11Remark() {
		return progress11Remark;
	}

	public String getProgress11Stime() {
		return progress11Stime;
	}

	public void setProgress11Stime(String progress11Stime) {
		this.progress11Stime = progress11Stime;
	}

	public void setProgress11Remark(String progress11Remark) {
		this.progress11Remark = progress11Remark;
	}

	public GisGsmBqzInfo() {

	}

	public GisGsmBqzProgress getNowProgressInfo() {
		return nowProgressInfo;
	}

	public void setNowProgressInfo(GisGsmBqzProgress nowProgressInfo) {
		this.nowProgressInfo = nowProgressInfo;
	}

	public GisGsmBqzInfo(String id) {
		this.id = id;
	}

	public GisGsmBqzInfo(String id, String blockId) {
		this.id = id;
		this.blockId = blockId;
	}

	public GisGsmBqzInfo(double min_lng, double min_lat, double max_lng, double max_lat) {
		this.min_lng = min_lng;
		this.min_lat = min_lat;
		this.max_lng = max_lng;
		this.max_lat = max_lat;
		this.ghzType = "0";
	}

	public GisGsmBqzInfo(double min_lng, double min_lat, double max_lng, double max_lat, String blockId, String ghzType, String isNotNetwork) {
		this.min_lng = min_lng;
		this.min_lat = min_lat;
		this.max_lng = max_lng;
		this.max_lat = max_lat;
		this.blockId = blockId;
		this.ghzType = ghzType;
		this.isNotNetwork = isNotNetwork;
	}

	public GisGsmBqzInfo(double min_lng, double min_lat, double max_lng, double max_lat, String blockId, String addstationNum, String ghzType, String isNotNetwork) {
		this.min_lng = min_lng;
		this.min_lat = min_lat;
		this.max_lng = max_lng;
		this.max_lat = max_lat;
		this.blockId = "";
		this.addstationNum = addstationNum;
		this.ghzType = ghzType;
		this.isNotNetwork = isNotNetwork;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameCn() {
		return nameCn;
	}

	public void setNameCn(String nameCn) {
		this.nameCn = nameCn;
	}

	public String getBlockId() {
		return blockId;
	}

	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}

	public String getBatchNum() {
		return batchNum;
	}

	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}

	public double getMax_lng() {
		return max_lng;
	}

	public void setMax_lng(double max_lng) {
		this.max_lng = max_lng;
	}

	public double getMax_lat() {
		return max_lat;
	}

	public void setMax_lat(double max_lat) {
		this.max_lat = max_lat;
	}

	public double getMin_lng() {
		return min_lng;
	}

	public void setMin_lng(double min_lng) {
		this.min_lng = min_lng;
	}

	public double getMin_lat() {
		return min_lat;
	}

	public void setMin_lat(double min_lat) {
		this.min_lat = min_lat;
	}

	public String getLoopLocation() {
		return loopLocation;
	}

	public void setLoopLocation(String loopLocation) {
		this.loopLocation = loopLocation;
	}

	public String getScene() {
		return scene;
	}

	public void setScene(String scene) {
		this.scene = scene;
	}

	public String getAddstationNum() {
		return addstationNum;
	}

	public void setAddstationNum(String addstationNum) {
		this.addstationNum = addstationNum;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getStationType() {
		return stationType;
	}

	public void setStationType(String stationType) {
		this.stationType = stationType;
	}

	public String getAddstationType() {
		return addstationType;
	}

	public void setAddstationType(String addstationType) {
		this.addstationType = addstationType;
	}

	public String getAddstationReason() {
		return addstationReason;
	}

	public void setAddstationReason(String addstationReason) {
		this.addstationReason = addstationReason;
	}

	public String getGhHigh() {
		return ghHigh;
	}

	public void setGhHigh(String ghHigh) {
		this.ghHigh = ghHigh;
	}

	public String getGhPath() {
		return ghPath;
	}

	public void setGhPath(String ghPath) {
		this.ghPath = ghPath;
	}

	public String getGhDip() {
		return ghDip;
	}

	public void setGhDip(String ghDip) {
		this.ghDip = ghDip;
	}

	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public String getBaseName() {
		return baseName;
	}

	public void setBaseName(String baseName) {
		this.baseName = baseName;
	}

	public String getTfName() {
		return tfName;
	}

	public void setTfName(String tfName) {
		this.tfName = tfName;
	}

	public String getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}

	public String getGhzType() {
		return ghzType;
	}

	public void setGhzType(String ghzType) {
		this.ghzType = ghzType;
	}

	public String getProgressType() {
		return progressType;
	}

	public void setProgressType(String progressType) {
		this.progressType = progressType;
	}

	public String getProgressSubtype() {
		return progressSubtype;
	}

	public void setProgressSubtype(String progressSubtype) {
		this.progressSubtype = progressSubtype;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getExpectNtime() {
		return expectNtime;
	}

	public void setExpectNtime(String expectNtime) {
		this.expectNtime = expectNtime;
	}

	public String getIsNotNetwork() {
		return isNotNetwork;
	}

	public void setIsNotNetwork(String isNotNetwork) {
		this.isNotNetwork = isNotNetwork;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GisGsmBqzInfo other = (GisGsmBqzInfo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GisGhzInfo [address=" + address + ", batchNum=" + batchNum + ", blockId=" + blockId + ", district=" + district + ", id=" + id + ", latitude=" + latitude + ", longitude=" + longitude + ", name=" + name + ", nameCn=" + nameCn + ", zone=" + zone + "]";
	}

	public String getCloseReason() {
		return closeReason;
	}

	public void setCloseReason(String closeReason) {
		this.closeReason = closeReason;
	}

	public String getProgressStation() {
		return progressStation;
	}

	public void setProgressStation(String progressStation) {
		this.progressStation = progressStation;
	}

	public String getIfGhzManageSelect() {
		return ifGhzManageSelect;
	}

	public void setIfGhzManageSelect(String ifGhzManageSelect) {
		this.ifGhzManageSelect = ifGhzManageSelect;
	}

	public GisGhzExpectNtime getGisGhzExpectNtime() {
		return gisGhzExpectNtime;
	}

	public void setGisGhzExpectNtime(GisGhzExpectNtime gisGhzExpectNtime) {
		this.gisGhzExpectNtime = gisGhzExpectNtime;
	}

	public String getDistrictGhzNum() {
		return districtGhzNum;
	}

	public void setDistrictGhzNum(String districtGhzNum) {
		this.districtGhzNum = districtGhzNum;
	}

	public String getNotModifyGhzNum() {
		return notModifyGhzNum;
	}

	public void setNotModifyGhzNum(String notModifyGhzNum) {
		this.notModifyGhzNum = notModifyGhzNum;
	}

	public String getListSteptNum() {
		return listSteptNum;
	}

	public void setListSteptNum(String listSteptNum) {
		this.listSteptNum = listSteptNum;
	}

	public String getModifyNum() {
		return modifyNum;
	}

	public void setModifyNum(String modifyNum) {
		this.modifyNum = modifyNum;
	}

	public String getNotlistSteptNum() {
		return notlistSteptNum;
	}

	public void setNotlistSteptNum(String notlistSteptNum) {
		this.notlistSteptNum = notlistSteptNum;
	}

	public String getModifyNumParsent() {
		return modifyNumParsent;
	}

	public void setModifyNumParsent(String modifyNumParsent) {
		this.modifyNumParsent = modifyNumParsent;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}

	public String getIfClosed() {
		return ifClosed;
	}

	public void setIfClosed(String ifClosed) {
		this.ifClosed = ifClosed;
	}

	public String getProgress12Ntime() {
		return progress12Ntime;
	}

	public void setProgress12Ntime(String progress12Ntime) {
		this.progress12Ntime = progress12Ntime;
	}

	public String getIfAuto() {
		return ifAuto;
	}

	public void setIfAuto(String ifAuto) {
		this.ifAuto = ifAuto;
	}

}
