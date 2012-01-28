package no.komplett.solidify.util;

public enum ItemSalesStatus {
	
	CANCELLED("CD"),
	SHIPPED("SH");
	
	private String constant;
	
	private ItemSalesStatus(String constant){
		this.constant = constant;
	}

	public String getConstant() {
		return constant;
	}
}
