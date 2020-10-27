package Pharmacy;

public class Client {
	private int clientCode;
	private String id;
	private String pw;
	private String name;
	private String gender;
	private String phone;
	private String birth;
	private int point;
	private int fcode;
	private String fcheck;
	
	public int getClientCode() {
		return clientCode;
	}
	public void setClientCode(int clientCode) {
		this.clientCode = clientCode;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public int getFcode() {
		return fcode;
	}
	public void setFcode(int fcode) {
		this.fcode = fcode;
	}
	public String getFcheck() {
		return fcheck;
	}
	public void setFcheck(String fcheck) {
		this.fcheck = fcheck;
	}
	
	public Client() {
		super();
	}
	public Client(int clientCode, String id, String pw, String name, String gender, String phone, String birth,
			int point, int fcode, String fcheck) {
		super();
		this.clientCode = clientCode;
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.gender = gender;
		this.phone = phone;
		this.birth = birth;
		this.point = point;
		this.fcode = fcode;
		this.fcheck = fcheck;
	}
	
	@Override
	public String toString() {
		return "Client [clientCode=" + clientCode + ", id=" + id + ", pw=" + pw + ", name=" + name + ", gender="
				+ gender + ", phone=" + phone + ", Birth=" + birth + ", point=" + point + ", fcode=" + fcode
				+ ", fcheck=" + fcheck + "]";
	}
	
}

