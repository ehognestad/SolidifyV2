package no.komplett.solidify;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import no.komplett.solidify.data.Category;
import no.komplett.solidify.data.Product;
import no.komplett.solidify.data.SalesData;

import org.jdom.Attribute;
import org.jdom.DataConversionException;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;

public class HotlistGenerator {

	private static final String DATA_PATH = "src/no/komplett/solidify/data/";
	
	private static final SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd'T'h:M:s");
	
	/**
	 * Tasks
	 * 
	 * 1. Get categories
	 * 2. Get products
	 * 3. Get sales data from a given date
	 * 4. Generate hotlist for all stores
	 * 
	 * - Hotlist has different format depending on store and store type
	 * @throws ParseException 
	 * 
	 */
	
	public static void main(String[] args) throws ParseException{
		try {
			List<Category> categories = getCategories();
			List<Product> products = getProducts();
			
			ProductService productService = new ProductServiceImplementation(products);
			Collection<Integer> productsNotInStock = productService.getProductsNotInStock();
			System.out.println("products not in stock: " + productsNotInStock.size());
			
			Collection<Integer> productsNotInStockAndNotInStockWithinNextWeek = productService.getProductsNotInStockAndNotInStockWithinTimeFrame(getNextWeek());
			System.out.println("products not in stock and not in stock next week: " + productsNotInStockAndNotInStockWithinNextWeek.size());
			
			Collection<Integer> productsToRemove = productService.getProductsToRemove();
			System.out.println("productsToRemove: " + productsToRemove.size());
			
//			List<SalesData> salesDatas = getSales();
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static Date getNextWeek(){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, -2);
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		
		return calendar.getTime();
	}
	
	private static List<SalesData> getSales() throws JDOMException, IOException,
			DataConversionException {
		Document salesDataDoc = XmlParser.getDocument(DATA_PATH + "SalesData.xml");
		List<Element> rowElement = salesDataDoc.getRootElement().getChildren();
		
		List<SalesData> salesDatas = new ArrayList<SalesData>();
		for (Iterator iterator = rowElement.iterator(); iterator.hasNext();) {
			Element element = (Element) iterator.next();
			
			List<Attribute> attributes = element.getAttributes();
			SalesData salesData = new SalesData();
			
			salesData.setStoreId(element.getAttribute("StoreID").getIntValue());
			salesData.setCustomerGroupId(element.getAttribute("KDGRP").getIntValue());
			salesData.setSku(element.getAttribute("SKU").getIntValue());
			salesData.setDay(element.getAttribute("Day").getIntValue());
			salesData.setMonth(element.getAttribute("Month").getIntValue());
			salesData.setYear(element.getAttribute("Year").getIntValue());
			salesData.setCount(element.getAttribute("Count").getIntValue());
			salesData.setItemStatus(element.getAttributeValue("ItemStatus"));
			
			salesDatas.add(salesData);
		}
		
		return salesDatas;
	}

	private static List<Product> getProducts() throws JDOMException, IOException,
			DataConversionException, ParseException {
		Document productsDoc = XmlParser.getDocument(DATA_PATH + "Products.xml");
		List<Element> rowList = productsDoc.getRootElement().getChildren();
		
		List<Product> products = new ArrayList<Product>();
		for (Iterator iterator = rowList.iterator(); iterator.hasNext();) {
			Element rowElement = (Element) iterator.next();
			
			List<Attribute> attributes = rowElement.getAttributes();
			Product product = new Product();
			for (Iterator iterator2 = attributes.iterator(); iterator2
					.hasNext();) {
				Attribute attribute = (Attribute) iterator2.next();
				
				if(attribute.getName().equalsIgnoreCase("MATNR")){
					product.setId(attribute.getIntValue());
				} else if (attribute.getName().equalsIgnoreCase("MAKTX1")){
					product.setDescription(attribute.getValue());
				} else if(attribute.getName().equalsIgnoreCase("MAKTX2")){
					product.setDescription2(attribute.getValue());
				} else if(attribute.getName().equalsIgnoreCase("storeid")){
					product.setStoreId(attribute.getIntValue());
				} else if(attribute.getName().equalsIgnoreCase("browsenodeid")){
					product.setBrowseNodeId(attribute.getIntValue());
				} else if(attribute.getName().equalsIgnoreCase("AvailableDate1")){
					product.setAvailableDate(dateParser.parse(attribute.getValue()));
				} else if(attribute.getName().equalsIgnoreCase("ZCPRICE")){
					product.setPrice(attribute.getDoubleValue());
				} else if(attribute.getName().equalsIgnoreCase("ZCPRICE_GROSS")){
					product.setPriceGross(attribute.getDoubleValue());
				} else if(attribute.getName().equalsIgnoreCase("ZCPRICE_VAT")){
					product.setPriceVAT(attribute.getDoubleValue());
				} else if(attribute.getName().equalsIgnoreCase("QuantityForSale")){
					product.setQuantityForSale(attribute.getDoubleValue());
				} else if(attribute.getName().equalsIgnoreCase("QuantityForSale1")){
					product.setQuantityForSale1(attribute.getDoubleValue());
				} else if(attribute.getName().equalsIgnoreCase("DISMM")){
					product.setDismm(attribute.getValue());
				} else if(attribute.getName().equalsIgnoreCase("BISMT")){
					product.setBismt(attribute.getValue());
				} else if(attribute.getName().equalsIgnoreCase("MFRPN")){
					product.setMfrpn(attribute.getValue());
				} else if(attribute.getName().equalsIgnoreCase("MFRNR")){
					product.setMfrnr(attribute.getValue());
				} else if(attribute.getName().equalsIgnoreCase("VMSTA")){
					product.setVmsta(attribute.getValue());
				} else if(attribute.getName().equalsIgnoreCase("SPART")){
					product.setSpart(attribute.getIntValue());
				} else if(attribute.getName().equalsIgnoreCase("VTWEG")){
					product.setVtveg(attribute.getIntValue());
				} else if(attribute.getName().equalsIgnoreCase("WAERS")){
					product.setCurrency(attribute.getValue());
				} else if(attribute.getName().equalsIgnoreCase("WERKS")){
					product.setWerks(attribute.getIntValue());
				}
			}
			
			products.add(product);
		}
		
		return products;
	}

	private static List<Category> getCategories() throws JDOMException, IOException,
			DataConversionException {
		Document categoriesDoc = XmlParser.getDocument(DATA_PATH + "Categories.xml");
		List<Element> rowList = categoriesDoc.getRootElement().getChildren();
		
		List<Category> categories = new ArrayList<Category>();
		for (Iterator iterator = rowList.iterator(); iterator.hasNext();) {
			Element rowElement = (Element) iterator.next();
			
			List<Attribute> attributes = rowElement.getAttributes();
			
			Category category = new Category();
			for (Iterator iterator2 = attributes.iterator(); iterator2
					.hasNext();) {
				Attribute attribute = (Attribute) iterator2.next();
				
				if(attribute.getName().equalsIgnoreCase("storeid")){
					category.setStoreId(attribute.getIntValue());
				} else if(attribute.getName().equalsIgnoreCase("catalogid")){
					category.setCatalogId(attribute.getIntValue());
				} else if(attribute.getName().equalsIgnoreCase("browsenodeid")){
					category.setBrowseNodeId(attribute.getIntValue());
				} else if(attribute.getName().equalsIgnoreCase("CountOfProducts")){
					category.setProductCount(attribute.getIntValue());
				} else if(attribute.getName().equalsIgnoreCase("bn")){
					category.setType(attribute.getValue());
				} else if(attribute.getName().equalsIgnoreCase("url")){
					category.setUrl(attribute.getValue());
				}
			}
			
			categories.add(category);
		}
		
		System.out.println("Categories size: " + categories.size());
		return categories;
	}
}
