package Pharmacy;

public class Purchase {
	private String id;
	private int pur_code;
	private String pur_name;
	private int pur_stock;
	private int pur_price;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getPur_code() {
		return pur_code;
	}
	public void setPur_code(int pur_code) {
		this.pur_code = pur_code;
	}
	public String getPur_name() {
		return pur_name;
	}
	public void setPur_name(String pur_name) {
		this.pur_name = pur_name;
	}
	public int getPur_stock() {
		return pur_stock;
	}
	public void setPur_stock(int pur_stock) {
		this.pur_stock = pur_stock;
	}
	public int getPur_price() {
		return pur_price;
	}
	public void setPur_price(int pur_price) {
		this.pur_price = pur_price;
	}
	
	
	public Purchase() {
		super();
	}
	public Purchase(String id, int pur_code, String pur_name, int pur_stock, int pur_price) {
		super();
		this.id = id;
		this.pur_code = pur_code;
		this.pur_name = pur_name;
		this.pur_stock = pur_stock;
		this.pur_price = pur_price;
	}
	@Override
	public String toString() {
		return "Purchase [id=" + id + ", pur_code=" + pur_code + ", pur_name=" + pur_name + ", pur_stock=" + pur_stock
				+ ", pur_price=" + pur_price + "]";
	}
	
		
	
}
