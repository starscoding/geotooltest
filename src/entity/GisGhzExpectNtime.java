package entity;

public class GisGhzExpectNtime {


	private static final long serialVersionUID = 8762539700173078466L;

	private String id;
	/**
	 * 规划站ID
	 */
	private String ghzId;
	/**
	 * 规划站进度ID 
	 */
	private String progressId;
	/**
	 * 修改用户
	 */
	private String modifyUser;
	/**
	 * 修改原因
	 */
	private String modifyReason;
	/**
	 * 预计入网时间
	 */
	private String expectNtime;
	/**
	 *  数据入库时间
	 */
	private String recordTime;

	public GisGhzExpectNtime() {
	}

	public GisGhzExpectNtime(String id, String ghzId, String progressId,
			String modifyReason, String expectNtime, String recordTime, String modifyUser) {
		this.id = id;
		this.ghzId = ghzId;
		this.progressId = progressId;
		this.modifyReason = modifyReason;
		this.modifyUser = modifyUser;
		this.expectNtime = expectNtime;
		this.recordTime = recordTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProgressId() {
		return progressId;
	}

	public void setProgressId(String progressId) {
		this.progressId = progressId;
	}

	public String getExpectNtime() {
		return expectNtime;
	}

	public void setExpectNtime(String expectNtime) {
		this.expectNtime = expectNtime;
	}
	
	public String getModifyReason() {
		return modifyReason;
	}

	public void setModifyReason(String modifyReason) {
		this.modifyReason = modifyReason;
	}

	public String getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}

	public String getGhzId() {
		return ghzId;
	}

	public void setGhzId(String ghzId) {
		this.ghzId = ghzId;
	}
	
	public String getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
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


}
