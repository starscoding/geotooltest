package entity;

public class GisGsmBqzProgress {

	
	private static final long serialVersionUID = 8762539700173078466L;
	
	/**
	 * 唯一标识ID
	 */
	private String id;
	
	/**
	 * 规划站ID
	 */
	private String ghzId;
	private String progress1Stime;
	private String progress1Etime;
	private String progress2Stime;
	private String progress2Etime;
	private String progress3Stime;
	private String progress3Etime;
	private String progress4Stime;
	private String progress4Etime;
	private String progress5Stime;
	private String progress5Etime;
	private String progress6Stime;
	private String progress6Etime;
	private String progress7Stime;
	private String progress7Etime;
	private String progress8Stime;
	private String progress8Etime;
	private String progress9Stime;
	private String progress9Etime;
	private String progress10Stime;
	private String progress10Etime;
	/**
	 * 受阻-起始时间
	 */
	private String progress11Stime;
	/**
	 * 受阻-备注
	 */
	private String progress11Remark;
	/**
	 * 已入网-入网时间
	 */
	private String progress12Ntime;
	
	private String recordTime;
	private String updateTime;
	/**
	 * 预计入网时间
	 */
	private String expectNtime;
	/**
	 * 待审核进度
	 */
	private String examineProgress;
	/**
	 * 修改原因
	 */
	private String modifyReason;
	
	private String progressType;   //工程进度

	public String getExamineProgress() {
		return examineProgress;
	}

	public void setExamineProgress(String examineProgress) {
		this.examineProgress = examineProgress;
	}

	public GisGsmBqzProgress(){
	}
	
	public GisGsmBqzProgress(String ghzId){
		this.ghzId=ghzId;
	}
	
	public GisGsmBqzProgress(String id,String ghzId){
		this.id=id;
		this.ghzId=ghzId;
	}

	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getGhzId() {
		return ghzId;
	}



	public void setGhzId(String ghzId) {
		this.ghzId = ghzId;
	}



	public String getProgress1Stime() {
		return progress1Stime;
	}



	public void setProgress1Stime(String progress1Stime) {
		this.progress1Stime = progress1Stime;
	}



	public String getProgress1Etime() {
		return progress1Etime;
	}



	public void setProgress1Etime(String progress1Etime) {
		this.progress1Etime = progress1Etime;
	}



	public String getProgress2Stime() {
		return progress2Stime;
	}



	public void setProgress2Stime(String progress2Stime) {
		this.progress2Stime = progress2Stime;
	}



	public String getProgress2Etime() {
		return progress2Etime;
	}



	public void setProgress2Etime(String progress2Etime) {
		this.progress2Etime = progress2Etime;
	}



	public String getProgress3Stime() {
		return progress3Stime;
	}



	public void setProgress3Stime(String progress3Stime) {
		this.progress3Stime = progress3Stime;
	}



	public String getProgress3Etime() {
		return progress3Etime;
	}



	public void setProgress3Etime(String progress3Etime) {
		this.progress3Etime = progress3Etime;
	}



	public String getProgress4Stime() {
		return progress4Stime;
	}



	public void setProgress4Stime(String progress4Stime) {
		this.progress4Stime = progress4Stime;
	}



	public String getProgress4Etime() {
		return progress4Etime;
	}



	public void setProgress4Etime(String progress4Etime) {
		this.progress4Etime = progress4Etime;
	}



	public String getProgress5Stime() {
		return progress5Stime;
	}



	public void setProgress5Stime(String progress5Stime) {
		this.progress5Stime = progress5Stime;
	}



	public String getProgress5Etime() {
		return progress5Etime;
	}



	public void setProgress5Etime(String progress5Etime) {
		this.progress5Etime = progress5Etime;
	}



	public String getProgress6Stime() {
		return progress6Stime;
	}



	public void setProgress6Stime(String progress6Stime) {
		this.progress6Stime = progress6Stime;
	}



	public String getProgress6Etime() {
		return progress6Etime;
	}



	public void setProgress6Etime(String progress6Etime) {
		this.progress6Etime = progress6Etime;
	}



	public String getProgress7Stime() {
		return progress7Stime;
	}



	public void setProgress7Stime(String progress7Stime) {
		this.progress7Stime = progress7Stime;
	}



	public String getProgress7Etime() {
		return progress7Etime;
	}



	public void setProgress7Etime(String progress7Etime) {
		this.progress7Etime = progress7Etime;
	}



	public String getProgress8Stime() {
		return progress8Stime;
	}



	public void setProgress8Stime(String progress8Stime) {
		this.progress8Stime = progress8Stime;
	}



	public String getProgress8Etime() {
		return progress8Etime;
	}



	public void setProgress8Etime(String progress8Etime) {
		this.progress8Etime = progress8Etime;
	}



	public String getProgress9Stime() {
		return progress9Stime;
	}

	public void setProgress9Stime(String progress9Stime) {
		this.progress9Stime = progress9Stime;
	}

	public String getProgress9Etime() {
		return progress9Etime;
	}

	public void setProgress9Etime(String progress9Etime) {
		this.progress9Etime = progress9Etime;
	}

	public String getProgress10Stime() {
		return progress10Stime;
	}

	public void setProgress10Stime(String progress10Stime) {
		this.progress10Stime = progress10Stime;
	}

	public String getProgress10Etime() {
		return progress10Etime;
	}

	public void setProgress10Etime(String progress10Etime) {
		this.progress10Etime = progress10Etime;
	}

	public String getProgress11Stime() {
		return progress11Stime;
	}

	public void setProgress11Stime(String progress11Stime) {
		this.progress11Stime = progress11Stime;
	}

	public String getProgress11Remark() {
		return progress11Remark;
	}

	public void setProgress11Remark(String progress11Remark) {
		this.progress11Remark = progress11Remark;
	}

	public String getProgress12Ntime() {
		return progress12Ntime;
	}

	public void setProgress12Ntime(String progress12Ntime) {
		this.progress12Ntime = progress12Ntime;
	}

	public String getRecordTime() {
		return recordTime;
	}



	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}



	public String getUpdateTime() {
		return updateTime;
	}



	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
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

	public String getProgressType() {
		return progressType;
	}

	public void setProgressType(String progressType) {
		this.progressType = progressType;
	}


}
