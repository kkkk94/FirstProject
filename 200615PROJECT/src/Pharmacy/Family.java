package Pharmacy;

public class Family {
	private int fcode;
	private String fid;
	private String fname;
	private String fphone;
	private String fresnum;
	public int getFcode() {
		return fcode;
	}
	public void setFcode(int fcode) {
		this.fcode = fcode;
	}
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getFphone() {
		return fphone;
	}
	public void setFphone(String fphone) {
		this.fphone = fphone;
	}
	public String getFresnum() {
		return fresnum;
	}
	public void setFresnum(String fresnum) {
		this.fresnum = fresnum;
	}
	
	public Family() {
		super();
	}
	public Family(int fcode, String fid, String fname, String fphone, String fresnum) {
		super();
		this.fcode = fcode;
		this.fid = fid;
		this.fname = fname;
		this.fphone = fphone;
		this.fresnum = fresnum;
	}
	
	@Override
	public String toString() {
		return "Family [fcode=" + fcode + ", fid=" + fid + ", fname=" + fname + ", fphone=" + fphone + ", fresnum="
				+ fresnum + "]";
	}
	
	
	
	
}
