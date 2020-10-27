package Pharmacy;

public class Goods {
	private int gcode;
	private String gname;
	private int gstock;
	private int gprice;
	
	public int getGcode() {
		return gcode;
	}
	public void setGcode(int gcode) {
		this.gcode = gcode;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public int getGstock() {
		return gstock;
	}
	public void setGstock(int gstock) {
		this.gstock = gstock;
	}
	public int getGprice() {
		return gprice;
	}
	public void setGprice(int gprice) {
		this.gprice = gprice;
	}
	
	public Goods() {
		super();
	}
	public Goods(int gcode, String gname, int gstock, int gprice) {
		super();
		this.gcode = gcode;
		this.gname = gname;
		this.gstock = gstock;
		this.gprice = gprice;
	}
	
	@Override
	public String toString() {
		return "상품정보 [gcode=" + gcode + ", gname=" + gname + ", gstock=" + gstock + ", gprice=" + gprice + "]";
	}
	
}
