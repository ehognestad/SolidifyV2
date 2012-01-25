package no.komplett.solidify.data;


/**
 *   <row StoreID="312" KDGRP="40" SKU="645227" Day="6" Month="8" Year="2011" Count="1" ItemStatus="CF" />
 
 * @author eivind
 *
 */
public class SalesData {
	private int storeId;
	
	private int customerGroupId;
	
	private int sku;
	
	private int day;
	
	private int month;
	
	private int year;
	
	private int count;
	
	private String itemStatus;

	public SalesData() {
		super();
	}

	public SalesData(int storeId, int customerGroupId, int sku, int day,
			int month, int year, int count, String itemStatus) {
		super();
		this.storeId = storeId;
		this.customerGroupId = customerGroupId;
		this.sku = sku;
		this.day = day;
		this.month = month;
		this.year = year;
		this.count = count;
		this.itemStatus = itemStatus;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public int getCustomerGroupId() {
		return customerGroupId;
	}

	public void setCustomerGroupId(int customerGroupId) {
		this.customerGroupId = customerGroupId;
	}

	public int getSku() {
		return sku;
	}

	public void setSku(int sku) {
		this.sku = sku;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}
}
