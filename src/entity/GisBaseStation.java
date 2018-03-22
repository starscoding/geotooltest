package entity;

public class GisBaseStation extends ZongzStation{

	private static final long serialVersionUID = -8569348127142633942L;
	/* "0"室内 "1"室外 */
	private String isShinei = "0";
	
	private double coverRadius;//覆盖半径
	


	public GisBaseStation() {

	}

	public GisBaseStation(String bts_id, String district, String bts_type,
			String bts_name) {
		super(bts_id, district, bts_type, bts_name);
	}

	public String getIsShinei() {
		return isShinei;
	}

	public void setIsShinei(String isShinei) {
		this.isShinei = isShinei;
	}

	public double getCoverRadius() {
		return coverRadius;
	}

	public void setCoverRadius(double coverRadius) {
		this.coverRadius = coverRadius;
	}
}
