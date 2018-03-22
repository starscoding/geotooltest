package entity;

public class Station extends GisBaseStation{


	private static final long serialVersionUID = -8569348127142633942L;

	/**
	 * 表示基站是否退服 0-否;1-是 
	 */ 
	private String isquit;
	/**
	 * 退服信息
	 */
	private GisExitStation exitInfo;
	
	/**
	 * 基站与投诉点的距离
	 */
	private double distatce;
	
	/**
	 * lac-ci
	 * */
	private String lacCi;
	
	/**
	 *泰森多边形的边界 
	 * @return
	 */
	private String areaPonints;
	/**
	 * 故障时间
	 * @return
	 */
	private String gzTime;
	/***
	 * 所有故障时间范围整合字段
	 */
	private String timeRange;
	
	/**
	 * 故障类型
	 */
	private String gzlx;
	
	/**
	 * 故障时间
	 */
	private String atime;
	/**
	 * 故障清除时间
	 */
	private String etime;
	
	public String getLacCi() {
		return lacCi;
	}

	public void setLacCi(String lacCi) {
		this.lacCi = lacCi;
	}

	public String getIsquit() {
		return isquit;
	}

	public void setIsquit(String isquit) {
		this.isquit = isquit;
	}

	public GisExitStation getExitInfo() {
		return exitInfo;
	}

	public void setExitInfo(GisExitStation exitInfo) {
		this.exitInfo = exitInfo;
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

	public double getDistatce() {
		return distatce;
	}

	public void setDistatce(double distatce) {
		this.distatce = distatce;
	}

	public void setAreaPonints(String areaPonints) {
		this.areaPonints = areaPonints;
	}

	public String getAreaPonints() {
		return areaPonints;
	}

	public void setGzTime(String gzTime) {
		this.gzTime = gzTime;
	}

	public String getGzTime() {
		return gzTime;
	}

	public void setTimeRange(String timeRange) {
		this.timeRange = timeRange;
	}

	public String getTimeRange() {
		return timeRange;
	}

	public void setGzlx(String gzlx) {
		this.gzlx = gzlx;
	}

	public String getGzlx() {
		return gzlx;
	}

	public void setAtime(String atime) {
		this.atime = atime;
	}

	public String getAtime() {
		return atime;
	}

	public void setEtime(String etime) {
		this.etime = etime;
	}

	public String getEtime() {
		return etime;
	}
	

}
