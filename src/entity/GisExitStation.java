package entity;

public class GisExitStation {

	
	private static final long serialVersionUID = -8569348127142633942L;
	private String id;
	private int category;
	private String atime;
	private String warn_code;
	private String district;
	private String bsc_name;
	private String cell_name;
	private String cell_name_cn;
	private String cell_status;
	private String cell_p;
	private String warn_status;
	private String vendor;
	private String fault_desc;
	private String i_time;
	private String u_time;
	private String etime;
	
	public GisExitStation(){
		
	}
	
	public GisExitStation(int category,String cell_name){
		this.category=category;
		this.cell_name=cell_name;
	}
		
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public String getAtime() {
		return atime;
	}
	public void setAtime(String atime) {
		this.atime = atime;
	}
	public String getWarn_code() {
		return warn_code;
	}
	public void setWarn_code(String warn_code) {
		this.warn_code = warn_code;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getBsc_name() {
		return bsc_name;
	}
	public void setBsc_name(String bsc_name) {
		this.bsc_name = bsc_name;
	}
	public String getCell_name() {
		return cell_name;
	}
	public void setCell_name(String cell_name) {
		this.cell_name = cell_name;
	}
	public String getCell_name_cn() {
		return cell_name_cn;
	}
	public void setCell_name_cn(String cell_name_cn) {
		this.cell_name_cn = cell_name_cn;
	}
	public String getCell_status() {
		return cell_status;
	}
	public void setCell_status(String cell_status) {
		this.cell_status = cell_status;
	}
	public String getCell_p() {
		return cell_p;
	}
	public void setCell_p(String cell_p) {
		this.cell_p = cell_p;
	}
	public String getWarn_status() {
		return warn_status;
	}
	public void setWarn_status(String warn_status) {
		this.warn_status = warn_status;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getFault_desc() {
		return fault_desc;
	}
	public void setFault_desc(String fault_desc) {
		this.fault_desc = fault_desc;
	}
	public String getI_time() {
		return i_time;
	}
	public void setI_time(String i_time) {
		this.i_time = i_time;
	}
	public String getU_time() {
		return u_time;
	}
	public void setU_time(String u_time) {
		this.u_time = u_time;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEtime() {
		return etime;
	}

	public void setEtime(String etime) {
		this.etime = etime;
	}	
	

}
