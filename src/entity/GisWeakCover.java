package entity;

import java.util.Set;

public class GisWeakCover {

	private String id;
	private double longitude;
	private double latitude;
	private String po_mod_reason;
	private String longitude_str;
	private String latitude_str;
	private String zone;
	private String address;
	private String addressPinyin;
	private String addressPinyin_s;
	private String addressPinyinShort;
	private String projectType;
	private String projectProgress;
	private String projectStage;
	private String projectOvertime;
	private String explanation;
	private String importTime;
	private String difficult;//当前无法实施原因 
	private Set processLogs;
	private String gisNum;

	private Double radius;// 投诉区域覆盖半径（米）
	private String cover_type;// 投诉点细分覆盖类型
	private String sub_mcc;// 分公司
	private String area;// 小区名称
	private int hot_level;// 热点星级
	private String can_inner_cover;// 是否可以占用室内覆盖信号
	private String can_cell_cover;// 是否可以占用小区覆盖信号
	private String cap_cell_name;// 占用小区名
	private String cap_cell_lac;// LAC
	private String cap_cell_ci;// CI
	private String cap_cell_signal;// 信号强度
	private String cap_cell_pm;// 上/下行通话质量
	private String h1_cell_name;// 最强邻区1小区名
	private String h1_cell_lac;// 最强邻区1LAC
	private String h1_cell_ci;// 最强邻区1CI
	private String h1_cell_signal;// 最强邻区1信号强度
	private String h2_cell_name;// 最强邻区2小区名
	private String h2_cell_lac;// 最强邻区2LAC
	private String h2_cell_ci;// 最强邻区2CI
	private String h2_cell_signal;// 最强邻区2信号强度

	private String jc_lx;// 解决类型
	private String caddr1;// 地址1
	private String caddr2;
	private String caddr3;
	private String caddr4;
	private int tsl;// 投诉量
	private int audit_status;// 审核状态0-未审核；1-已审核；2-未提交
	private String pt_last_utime;
	private String create_time;
	private String ensure_time;
	private Double gd_longitude;
	private Double gd_latitude;
	private String ifClosed;
	private String operateType;// 操作类型：新增、导入、保存、提交审核、状态确认、审核通过、审核不通过
	private String modContent;// 修改内容

	// addtest
	private String busi_type;
	private String batch;
	private String project_progress_expand;//处理子进度
	private String progress_gist;//进度依据 
	private String progress_gist1;
	private String progress_gist2;//进度依据2 
	private String gis_network_order;//基站入网工单
	private String revisit_result;//协同平台满意度回访结果

	private String lx_process;//立项（进度依据）
	private String gz_process;//购租受阻（进度依据）
	private String gcjs_process;//工程建设受阻（进度依据）
	private String gz_process1;//购租受阻（进度依据1）
	private String gcjs_process1;//工程建设受阻（进度依据1）

	private String lx_process_tab;//维护立项（进度依据）
	private String gz_process_tab;//维护购租受阻（进度依据）
	private String gcjs_process_tab;//维护工程建设受阻（进度依据）
	private String gz_process1_tab;//维护购租受阻（进度依据1）
	private String gcjs_process1_tab;//维护工程建设受阻（进度依据1）
	
	
	private String projectType_tab;//解决方案
	private String jc_lx_tab;// 解决类型
	private String projectOvertime_tab;//预计解决时间
	private String po_mod_reason_tab;//预计解决时间修改原因
	private String projectStage_tab;//当前解决状态
	private String projectProgress_tab;//处理进度
	private String project_progress_expand_tab;//处理子进度
	private String difficult_tab;//当前无法实施原因 
	private String progress_gist_tab;//进度依据 
	private String progress_gist1_tab;//进度依据1
	private String progress_gist2_tab;//进度依据2 
	private String gis_network_order_tab;//基站入网工单
	private String revisit_result_tab; //协同平台满意度回访结果
	
	private String ensure_time_tab;//维护确认时间
	private String importTime_tab;//维护更新时间
	
	private double distance;
	
	
	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public String getLx_process() {
		return lx_process;
	}

	public void setLx_process(String lxProcess) {
		lx_process = lxProcess;
	}

	public String getGz_process() {
		return gz_process;
	}

	public void setGz_process(String gzProcess) {
		gz_process = gzProcess;
	}

	public String getGcjs_process() {
		return gcjs_process;
	}

	public void setGcjs_process(String gcjsProcess) {
		gcjs_process = gcjsProcess;
	}

	public String getGz_process1() {
		return gz_process1;
	}

	public void setGz_process1(String gzProcess1) {
		gz_process1 = gzProcess1;
	}

	public String getGcjs_process1() {
		return gcjs_process1;
	}

	public void setGcjs_process1(String gcjsProcess1) {
		gcjs_process1 = gcjsProcess1;
	}

	public String getProgress_gist1() {
		return progress_gist1;
	}

	public void setProgress_gist1(String progressGist1) {
		progress_gist1 = progressGist1;
	}

	public String getProgress_gist2() {
		return progress_gist2;
	}

	public void setProgress_gist2(String progressGist2) {
		progress_gist2 = progressGist2;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public String getModContent() {
		return modContent;
	}

	public void setModContent(String modContent) {
		this.modContent = modContent;
	}

	public Double getGd_longitude() {
		return gd_longitude;
	}

	public void setGd_longitude(Double gdLongitude) {
		gd_longitude = gdLongitude;
	}

	public Double getGd_latitude() {
		return gd_latitude;
	}

	public void setGd_latitude(Double gdLatitude) {
		gd_latitude = gdLatitude;
	}

	public String getEnsure_time() {
		return ensure_time;
	}

	public void setEnsure_time(String ensureTime) {
		ensure_time = ensureTime;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String createTime) {
		create_time = createTime;
	}

	public Double getRadius() {
		return radius;
	}

	public void setRadius(Double radius) {
		this.radius = radius;
	}

	public String getPo_mod_reason() {
		return po_mod_reason;
	}

	public void setPo_mod_reason(String poModReason) {
		po_mod_reason = poModReason;
	}

	public String getCover_type() {
		return cover_type;
	}

	public void setCover_type(String coverType) {
		cover_type = coverType;
	}

	public String getSub_mcc() {
		return sub_mcc;
	}

	public void setSub_mcc(String subMcc) {
		sub_mcc = subMcc;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public int getHot_level() {
		return hot_level;
	}

	public void setHot_level(int hotLevel) {
		hot_level = hotLevel;
	}

	public String getCan_inner_cover() {
		return can_inner_cover;
	}

	public void setCan_inner_cover(String canInnerCover) {
		can_inner_cover = canInnerCover;
	}

	public String getCan_cell_cover() {
		return can_cell_cover;
	}

	public void setCan_cell_cover(String canCellCover) {
		can_cell_cover = canCellCover;
	}

	public String getCap_cell_name() {
		return cap_cell_name;
	}

	public void setCap_cell_name(String capCellName) {
		cap_cell_name = capCellName;
	}

	public String getCap_cell_lac() {
		return cap_cell_lac;
	}

	public void setCap_cell_lac(String capCellLac) {
		cap_cell_lac = capCellLac;
	}

	public String getCap_cell_ci() {
		return cap_cell_ci;
	}

	public void setCap_cell_ci(String capCellCi) {
		cap_cell_ci = capCellCi;
	}

	public String getCap_cell_signal() {
		return cap_cell_signal;
	}

	public void setCap_cell_signal(String capCellSignal) {
		cap_cell_signal = capCellSignal;
	}

	public String getCap_cell_pm() {
		return cap_cell_pm;
	}

	public void setCap_cell_pm(String capCellPm) {
		cap_cell_pm = capCellPm;
	}

	public String getH1_cell_name() {
		return h1_cell_name;
	}

	public void setH1_cell_name(String h1CellName) {
		h1_cell_name = h1CellName;
	}

	public String getH1_cell_lac() {
		return h1_cell_lac;
	}

	public void setH1_cell_lac(String h1CellLac) {
		h1_cell_lac = h1CellLac;
	}

	public String getH1_cell_ci() {
		return h1_cell_ci;
	}

	public void setH1_cell_ci(String h1CellCi) {
		h1_cell_ci = h1CellCi;
	}

	public String getH1_cell_signal() {
		return h1_cell_signal;
	}

	public void setH1_cell_signal(String h1CellSignal) {
		h1_cell_signal = h1CellSignal;
	}

	public String getH2_cell_name() {
		return h2_cell_name;
	}

	public void setH2_cell_name(String h2CellName) {
		h2_cell_name = h2CellName;
	}

	public String getH2_cell_lac() {
		return h2_cell_lac;
	}

	public void setH2_cell_lac(String h2CellLac) {
		h2_cell_lac = h2CellLac;
	}

	public String getH2_cell_ci() {
		return h2_cell_ci;
	}

	public void setH2_cell_ci(String h2CellCi) {
		h2_cell_ci = h2CellCi;
	}

	public String getH2_cell_signal() {
		return h2_cell_signal;
	}

	public void setH2_cell_signal(String h2CellSignal) {
		h2_cell_signal = h2CellSignal;
	}

	public String getJc_lx() {
		return jc_lx;
	}

	public void setJc_lx(String jcLx) {
		jc_lx = jcLx;
	}

	public String getCaddr1() {
		return caddr1;
	}

	public void setCaddr1(String caddr1) {
		this.caddr1 = caddr1;
	}

	public String getCaddr2() {
		return caddr2;
	}

	public void setCaddr2(String caddr2) {
		this.caddr2 = caddr2;
	}

	public String getCaddr3() {
		return caddr3;
	}

	public void setCaddr3(String caddr3) {
		this.caddr3 = caddr3;
	}

	public String getCaddr4() {
		return caddr4;
	}

	public void setCaddr4(String caddr4) {
		this.caddr4 = caddr4;
	}

	public int getTsl() {
		return tsl;
	}

	public void setTsl(int tsl) {
		this.tsl = tsl;
	}

	/**
	 * 是否使用地图边界过滤 0：不使用 1：使用
	 */
	private String isUseBox = "1";

	/**
	 * 是否是关联查询弱覆盖 0：不是 1：是
	 */
	private String isRelation = "0";

	public GisWeakCover() {
	}

	public GisWeakCover(String zone, String gisNum) {
		this.zone = zone;
		this.gisNum = gisNum;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddressPinyin() {
		return addressPinyin;
	}

	public void setAddressPinyin(String addressPinyin) {
		this.addressPinyin = addressPinyin;
	}

	public String getAddressPinyin_s() {
		return addressPinyin_s;
	}

	public void setAddressPinyin_s(String addressPinyin_s) {
		this.addressPinyin_s = addressPinyin_s;
	}

	public String getAddressPinyinShort() {
		return addressPinyinShort;
	}

	public void setAddressPinyinShort(String addressPinyinShort) {
		this.addressPinyinShort = addressPinyinShort;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getProjectProgress() {
		return projectProgress;
	}

	public void setProjectProgress(String projectProgress) {
		this.projectProgress = projectProgress;
	}

	public String getProjectStage() {
		return projectStage;
	}

	public void setProjectStage(String projectStage) {
		this.projectStage = projectStage;
	}

	public String getProjectOvertime() {
		return projectOvertime;
	}

	public void setProjectOvertime(String projectOvertime) {
		this.projectOvertime = projectOvertime;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public String getDifficult() {
		return difficult;
	}

	public void setDifficult(String difficult) {
		this.difficult = difficult;
	}

	public String getLongitude_str() {
		return longitude_str;
	}

	public void setLongitude_str(String longitude_str) {
		this.longitude_str = longitude_str;
	}

	public String getLatitude_str() {
		return latitude_str;
	}

	public void setLatitude_str(String latitude_str) {
		this.latitude_str = latitude_str;
	}

	public Set getProcessLogs() {
		return processLogs;
	}

	public void setProcessLogs(Set processLogs) {
		this.processLogs = processLogs;
	}

	public String getGisNum() {
		return gisNum;
	}

	public void setGisNum(String gisNum) {
		this.gisNum = gisNum;
	}

	public GisWeakCover duplicate() {

		GisWeakCover tmpWeak = new GisWeakCover();
		tmpWeak.setId(this.getId());
		tmpWeak.setLongitude(this.getLongitude());
		tmpWeak.setLatitude(this.getLatitude());
		tmpWeak.setPo_mod_reason(this.getPo_mod_reason());
		tmpWeak.setZone(this.getZone());
		tmpWeak.setAddress(this.getAddress());
		tmpWeak.setAddressPinyin(this.getAddressPinyin());
		tmpWeak.setAddressPinyin_s(this.getAddressPinyin_s());
		tmpWeak.setAddressPinyinShort(this.getAddressPinyinShort());
		tmpWeak.setProjectType(this.getProjectType());
		tmpWeak.setProjectProgress(this.getProjectProgress());
		tmpWeak.setProjectStage(this.getProjectStage());
		tmpWeak.setProjectOvertime(this.getProjectOvertime());
		tmpWeak.setExplanation(this.getExplanation());
		tmpWeak.setImportTime(this.getImportTime());
		tmpWeak.setDifficult(this.getDifficult());
		tmpWeak.setRadius(this.getRadius());
		tmpWeak.setCover_type(this.getCover_type());
		tmpWeak.setSub_mcc(this.getSub_mcc());
		tmpWeak.setArea(this.getArea());
		tmpWeak.setCan_inner_cover(this.getCan_inner_cover());
		tmpWeak.setCan_cell_cover(this.getCan_cell_cover());
		tmpWeak.setCap_cell_ci(this.getCap_cell_ci());
		tmpWeak.setCap_cell_lac(this.getCap_cell_lac());
		tmpWeak.setCap_cell_name(this.getCap_cell_name());
		tmpWeak.setCap_cell_pm(this.getCap_cell_pm());
		tmpWeak.setCap_cell_signal(this.getCap_cell_signal());
		tmpWeak.setH1_cell_ci(this.getH1_cell_ci());
		tmpWeak.setH1_cell_lac(this.getH1_cell_lac());
		tmpWeak.setH1_cell_name(this.getH1_cell_name());
		tmpWeak.setH1_cell_signal(this.getH1_cell_signal());
		tmpWeak.setH2_cell_ci(this.getH2_cell_ci());
		tmpWeak.setH2_cell_lac(this.getH2_cell_lac());
		tmpWeak.setH2_cell_name(this.getH2_cell_name());
		tmpWeak.setH2_cell_signal(this.getH2_cell_signal());
		tmpWeak.setJc_lx(this.getJc_lx());
		tmpWeak.setCaddr1(this.getCaddr1());
		tmpWeak.setCaddr2(this.getCaddr2());
		tmpWeak.setCaddr3(this.getCaddr3());
		tmpWeak.setCaddr4(this.getCaddr4());
		tmpWeak.setTsl(this.getTsl());
		tmpWeak.setAudit_status(this.getAudit_status());
		tmpWeak.setCreate_time(this.getCreate_time());

		tmpWeak.setLx_process(this.getLx_process());
		tmpWeak.setGz_process(this.getGz_process());
		tmpWeak.setGcjs_process(this.getGcjs_process());
		tmpWeak.setGz_process1(this.getGz_process1());
		tmpWeak.setGcjs_process1(this.getGcjs_process1());
		
		tmpWeak.setEnsure_time(this.getEnsure_time());
		tmpWeak.setBusi_type(this.getBusi_type());
		tmpWeak.setBatch(this.getBatch());
		tmpWeak.setProject_progress_expand(this.getProject_progress_expand());
		tmpWeak.setProgress_gist(this.getProgress_gist());
		tmpWeak.setProgress_gist1(this.getProgress_gist1());
		tmpWeak.setProgress_gist2(this.getProgress_gist2());
		tmpWeak.setGis_network_order(this.getGis_network_order());
		tmpWeak.setRevisit_result(this.getRevisit_result());
		
		return tmpWeak;
	}
	//导出EXCIL所需字段
	public GisWeakCover exportduplicate() {
		GisWeakCover tmpWeak = new GisWeakCover();
		tmpWeak.setId(this.getId());
		tmpWeak.setLongitude(this.getLongitude());
		tmpWeak.setLatitude(this.getLatitude());
		tmpWeak.setPo_mod_reason(this.getPo_mod_reason());
		tmpWeak.setZone(this.getZone());
		tmpWeak.setAddress(this.getAddress());
		tmpWeak.setAddressPinyin(this.getAddressPinyin());
		tmpWeak.setAddressPinyin_s(this.getAddressPinyin_s());
		tmpWeak.setAddressPinyinShort(this.getAddressPinyinShort());
		tmpWeak.setProjectType(this.getProjectType());
		tmpWeak.setProjectProgress(this.getProjectProgress());
		tmpWeak.setProjectStage(this.getProjectStage());
		tmpWeak.setProjectOvertime(this.getProjectOvertime());
		tmpWeak.setExplanation(this.getExplanation());
		tmpWeak.setImportTime(this.getImportTime());
		tmpWeak.setDifficult(this.getDifficult());
		tmpWeak.setRadius(this.getRadius());
		tmpWeak.setCover_type(this.getCover_type());
		tmpWeak.setSub_mcc(this.getSub_mcc());
		tmpWeak.setArea(this.getArea());
		tmpWeak.setCan_inner_cover(this.getCan_inner_cover());
		tmpWeak.setCan_cell_cover(this.getCan_cell_cover());
		tmpWeak.setCap_cell_ci(this.getCap_cell_ci());
		tmpWeak.setCap_cell_lac(this.getCap_cell_lac());
		tmpWeak.setCap_cell_name(this.getCap_cell_name());
		tmpWeak.setCap_cell_pm(this.getCap_cell_pm());
		tmpWeak.setCap_cell_signal(this.getCap_cell_signal());
		tmpWeak.setH1_cell_ci(this.getH1_cell_ci());
		tmpWeak.setH1_cell_lac(this.getH1_cell_lac());
		tmpWeak.setH1_cell_name(this.getH1_cell_name());
		tmpWeak.setH1_cell_signal(this.getH1_cell_signal());
		tmpWeak.setH2_cell_ci(this.getH2_cell_ci());
		tmpWeak.setH2_cell_lac(this.getH2_cell_lac());
		tmpWeak.setH2_cell_name(this.getH2_cell_name());
		tmpWeak.setH2_cell_signal(this.getH2_cell_signal());
		tmpWeak.setJc_lx(this.getJc_lx());
		tmpWeak.setCaddr1(this.getCaddr1());
		tmpWeak.setCaddr2(this.getCaddr2());
		tmpWeak.setCaddr3(this.getCaddr3());
		tmpWeak.setCaddr4(this.getCaddr4());
		tmpWeak.setTsl(this.getTsl());
		tmpWeak.setAudit_status(this.getAudit_status());
		tmpWeak.setCreate_time(this.getCreate_time());

		tmpWeak.setLx_process(this.getLx_process());
		tmpWeak.setGz_process(this.getGz_process());
		tmpWeak.setGcjs_process(this.getGcjs_process());
		tmpWeak.setGz_process1(this.getGz_process1());
		tmpWeak.setGcjs_process1(this.getGcjs_process1());
		
		tmpWeak.setEnsure_time(this.getEnsure_time());
		tmpWeak.setBusi_type(this.getBusi_type());
		tmpWeak.setBatch(this.getBatch());
		tmpWeak.setProject_progress_expand(this.getProject_progress_expand());
		tmpWeak.setProgress_gist(this.getProgress_gist());
		tmpWeak.setProgress_gist1(this.getProgress_gist1());
		tmpWeak.setProgress_gist2(this.getProgress_gist2());
		tmpWeak.setGis_network_order(this.getGis_network_order());
		tmpWeak.setRevisit_result(this.getRevisit_result());
		
		tmpWeak.setProjectType_tab(this.getProjectType_tab());
		tmpWeak.setJc_lx_tab(this.getJc_lx_tab());
		tmpWeak.setProjectOvertime_tab(this.getProjectOvertime_tab());
		tmpWeak.setProjectStage_tab(this.getProjectStage_tab());
		tmpWeak.setProjectProgress_tab(this.getProjectProgress_tab());
		tmpWeak.setProject_progress_expand_tab(this.getProject_progress_expand_tab());
		tmpWeak.setDifficult_tab(this.getDifficult_tab());
		tmpWeak.setProgress_gist_tab(this.getProgress_gist_tab());
		tmpWeak.setProgress_gist1_tab(this.getProgress_gist1_tab());
		tmpWeak.setProgress_gist2_tab(this.getProgress_gist2_tab());
		tmpWeak.setGis_network_order_tab(this.getGis_network_order_tab());
		tmpWeak.setRevisit_result_tab(this.getRevisit_result_tab());
		
		tmpWeak.setLx_process_tab(this.getLx_process_tab());
		tmpWeak.setGz_process_tab(this.getGz_process_tab());
		tmpWeak.setGcjs_process_tab(this.getGcjs_process_tab());
		tmpWeak.setGz_process1_tab(this.getGz_process1_tab());
		tmpWeak.setGcjs_process1_tab(this.getGcjs_process1_tab());
		
		tmpWeak.setEnsure_time_tab(this.getEnsure_time_tab());
		tmpWeak.setImportTime_tab(this.getImportTime_tab());
		return tmpWeak;
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

	public String getIsUseBox() {
		return isUseBox;
	}

	public void setIsUseBox(String isUseBox) {
		this.isUseBox = isUseBox;
	}

	public String getImportTime() {
		return importTime;
	}

	public void setImportTime(String importTime) {
		this.importTime = importTime;
	}

	public String getIsRelation() {
		return isRelation;
	}

	public void setIsRelation(String isRelation) {
		this.isRelation = isRelation;
	}

	public int getAudit_status() {
		return audit_status;
	}

	public void setAudit_status(int auditStatus) {
		audit_status = auditStatus;
	}

	public String getPt_last_utime() {
		return pt_last_utime;
	}

	public void setPt_last_utime(String ptLastUtime) {
		pt_last_utime = ptLastUtime;
	}

	public String getIfClosed() {
		return ifClosed;
	}

	public void setIfClosed(String ifClosed) {
		this.ifClosed = ifClosed;
	}

	public String getBusi_type() {
		return busi_type;
	}

	public void setBusi_type(String busiType) {
		busi_type = busiType;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getProject_progress_expand() {
		return project_progress_expand;
	}

	public void setProject_progress_expand(String projectProgressExpand) {
		project_progress_expand = projectProgressExpand;
	}

	public String getProgress_gist() {
		return progress_gist;
	}

	public void setProgress_gist(String progressGist) {
		progress_gist = progressGist;
	}

	public String getGis_network_order() {
		return gis_network_order;
	}

	public void setGis_network_order(String gisNetworkOrder) {
		gis_network_order = gisNetworkOrder;
	}

	public String getRevisit_result() {
		return revisit_result;
	}

	public void setRevisit_result(String revisitResult) {
		revisit_result = revisitResult;
	}

	public String getProjectType_tab() {
		return projectType_tab;
	}

	public void setProjectType_tab(String projectType_tab) {
		this.projectType_tab = projectType_tab;
	}

	public String getJc_lx_tab() {
		return jc_lx_tab;
	}

	public void setJc_lx_tab(String jc_lx_tab) {
		this.jc_lx_tab = jc_lx_tab;
	}

	public String getProjectOvertime_tab() {
		return projectOvertime_tab;
	}

	public void setProjectOvertime_tab(String projectOvertime_tab) {
		this.projectOvertime_tab = projectOvertime_tab;
	}

	public String getPo_mod_reason_tab() {
		return po_mod_reason_tab;
	}

	public void setPo_mod_reason_tab(String po_mod_reason_tab) {
		this.po_mod_reason_tab = po_mod_reason_tab;
	}

	public String getProjectStage_tab() {
		return projectStage_tab;
	}

	public void setProjectStage_tab(String projectStage_tab) {
		this.projectStage_tab = projectStage_tab;
	}

	public String getProjectProgress_tab() {
		return projectProgress_tab;
	}

	public void setProjectProgress_tab(String projectProgress_tab) {
		this.projectProgress_tab = projectProgress_tab;
	}

	public String getProject_progress_expand_tab() {
		return project_progress_expand_tab;
	}

	public void setProject_progress_expand_tab(String project_progress_expand_tab) {
		this.project_progress_expand_tab = project_progress_expand_tab;
	}

	public String getDifficult_tab() {
		return difficult_tab;
	}

	public void setDifficult_tab(String difficult_tab) {
		this.difficult_tab = difficult_tab;
	}

	public String getProgress_gist_tab() {
		return progress_gist_tab;
	}

	public void setProgress_gist_tab(String progress_gist_tab) {
		this.progress_gist_tab = progress_gist_tab;
	}

	public String getProgress_gist1_tab() {
		return progress_gist1_tab;
	}

	public void setProgress_gist1_tab(String progress_gist1_tab) {
		this.progress_gist1_tab = progress_gist1_tab;
	}

	public String getProgress_gist2_tab() {
		return progress_gist2_tab;
	}

	public void setProgress_gist2_tab(String progress_gist2_tab) {
		this.progress_gist2_tab = progress_gist2_tab;
	}

	public String getGis_network_order_tab() {
		return gis_network_order_tab;
	}

	public void setGis_network_order_tab(String gis_network_order_tab) {
		this.gis_network_order_tab = gis_network_order_tab;
	}

	public String getRevisit_result_tab() {
		return revisit_result_tab;
	}

	public void setRevisit_result_tab(String revisit_result_tab) {
		this.revisit_result_tab = revisit_result_tab;
	}

	public String getLx_process_tab() {
		return lx_process_tab;
	}

	public void setLx_process_tab(String lx_process_tab) {
		this.lx_process_tab = lx_process_tab;
	}

	public String getGz_process_tab() {
		return gz_process_tab;
	}

	public void setGz_process_tab(String gz_process_tab) {
		this.gz_process_tab = gz_process_tab;
	}

	public String getGcjs_process_tab() {
		return gcjs_process_tab;
	}

	public void setGcjs_process_tab(String gcjs_process_tab) {
		this.gcjs_process_tab = gcjs_process_tab;
	}

	public String getGz_process1_tab() {
		return gz_process1_tab;
	}

	public void setGz_process1_tab(String gz_process1_tab) {
		this.gz_process1_tab = gz_process1_tab;
	}

	public String getGcjs_process1_tab() {
		return gcjs_process1_tab;
	}

	public void setGcjs_process1_tab(String gcjs_process1_tab) {
		this.gcjs_process1_tab = gcjs_process1_tab;
	}

	public String getEnsure_time_tab() {
		return ensure_time_tab;
	}

	public void setEnsure_time_tab(String ensure_time_tab) {
		this.ensure_time_tab = ensure_time_tab;
	}

	public String getImportTime_tab() {
		return importTime_tab;
	}

	public void setImportTime_tab(String importTime_tab) {
		this.importTime_tab = importTime_tab;
	}

}
