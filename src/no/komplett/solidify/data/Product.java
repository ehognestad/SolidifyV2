package no.komplett.solidify.data;

import java.util.Date;

/**
 * <row MATNR="105268" MAKTX="Lydkabel Minijack 3.5 mm M/M 10 m" MAKTX2="" 
 * StoreId="310" BrowseNodeId="11290" AvailableDate1="2011-08-11T00:00:00" 
 * ZCPRICE="79.20" ZCPRICE_GROSS="99.00" ZCPRICE_VAT="19.80" QuantityForSale="6.00" 
 * QuantityForSale1="26.00" DISMM="V2" BISMT="" MFRPN="" MFRNR="AESP" VMSTA="" SPART="52" 
 * VTWEG="10" WAERS="NOK" WERKS="3100" />
 * @author eivind
 *
 */
public class Product {
	
	private int id;
	
	private String description;
	
	private String description2;
	
	private int storeId;
	
	private int browseNodeId;
	
	private Date availableDate;
	
	private double price;
	
	private double priceGross;
	
	private double priceVAT;
	
	private double quantityForSale;
	
	private double quantityForSale1;
	
	private String dismm;
	
	private String bismt;
	
	private String mfrpn;
	
	private String mfrnr;
	
	private String vmsta;
	
	private int spart;
	
	private int vtveg;
	
	private String currency;
	
	private int werks;

	public Product() {
	}

	public Product(int id, String description, String description2,
			int storeId, int browseNodeId, Date availableDate, double price,
			double priceGross, double priceVAT, double quantityForSale,
			double quantityForSale1, String dismm, String bismt, String mfrpn,
			String mfrnr, String vmsta, int spart, int vtveg,
			String currency, int werks) {
		super();
		this.id = id;
		this.description = description;
		this.description2 = description2;
		this.storeId = storeId;
		this.browseNodeId = browseNodeId;
		this.availableDate = availableDate;
		this.price = price;
		this.priceGross = priceGross;
		this.priceVAT = priceVAT;
		this.quantityForSale = quantityForSale;
		this.quantityForSale1 = quantityForSale1;
		this.dismm = dismm;
		this.bismt = bismt;
		this.mfrpn = mfrpn;
		this.mfrnr = mfrnr;
		this.vmsta = vmsta;
		this.spart = spart;
		this.vtveg = vtveg;
		this.currency = currency;
		this.werks = werks;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription2() {
		return description2;
	}

	public void setDescription2(String description2) {
		this.description2 = description2;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public int getBrowseNodeId() {
		return browseNodeId;
	}

	public void setBrowseNodeId(int browseNodeId) {
		this.browseNodeId = browseNodeId;
	}

	public Date getAvailableDate() {
		return availableDate;
	}

	public void setAvailableDate(Date availableDate) {
		this.availableDate = availableDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getPriceGross() {
		return priceGross;
	}

	public void setPriceGross(double priceGross) {
		this.priceGross = priceGross;
	}

	public double getPriceVAT() {
		return priceVAT;
	}

	public void setPriceVAT(double priceVAT) {
		this.priceVAT = priceVAT;
	}

	public double getQuantityForSale() {
		return quantityForSale;
	}

	public void setQuantityForSale(double quantityForSale) {
		this.quantityForSale = quantityForSale;
	}

	public double getQuantityForSale1() {
		return quantityForSale1;
	}

	public void setQuantityForSale1(double quantityForSale1) {
		this.quantityForSale1 = quantityForSale1;
	}

	public String getDismm() {
		return dismm;
	}

	public void setDismm(String dismm) {
		this.dismm = dismm;
	}

	public String getBismt() {
		return bismt;
	}

	public void setBismt(String bismt) {
		this.bismt = bismt;
	}

	public String getMfrpn() {
		return mfrpn;
	}

	public void setMfrpn(String mfrpn) {
		this.mfrpn = mfrpn;
	}

	public String getMfrnr() {
		return mfrnr;
	}

	public void setMfrnr(String mfrnr) {
		this.mfrnr = mfrnr;
	}

	public String getVmsta() {
		return vmsta;
	}

	public void setVmsta(String vmsta) {
		this.vmsta = vmsta;
	}

	public int getSpart() {
		return spart;
	}

	public void setSpart(int spart) {
		this.spart = spart;
	}

	public int getVtveg() {
		return vtveg;
	}

	public void setVtveg(int vtveg) {
		this.vtveg = vtveg;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public int getWerks() {
		return werks;
	}

	public void setWerks(int werks) {
		this.werks = werks;
	}
}
