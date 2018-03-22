package entity;


public class WlQueryParamEntity extends WirelessEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7541943005598902590L;

	/**
	 * 结果起始索引号
	 */
	private int start = 0;

	/**
	 * 每页记录数
	 */
	private int pageSize = 20;

	/**
	 * @return the start
	 */
	public int getStart() {
		return start;
	}

	/**
	 * @param start
	 *            the start to set
	 */
	public void setStart(int start) {
		this.start = start;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


}
