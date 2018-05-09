package bean;

public class MatterBean {
	private String matterid;
	private String mattername;
	private String spec;
	private String measurement;
	private int amount;
	private String other;
	
	public String getMatterid() {
		return matterid;
	}
	public void setMatterid(String matterid) {
		this.matterid = matterid;
	}
	public String getMattername() {
		return mattername;
	}
	public void setMattername(String mattername) {
		this.mattername = mattername;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public String getMeasurement() {
		return measurement;
	}
	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
}
