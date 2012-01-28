package no.komplett.solidify;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
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
import org.jdom.output.XMLOutputter;

public class DataHelper {

	private static final String OUTPUT_DIR = "output/hotlists/";
	
	private String dataDirectory;

	private  final SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd'T'h:M:s");
	//	private  final SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd h:M:s");

	public DataHelper(String dataDirectory){
		if(!dataDirectory.endsWith("/")){
			dataDirectory = dataDirectory + "/";
		}
		this.dataDirectory = dataDirectory;
	}

	public List<SalesData> getSales() throws JDOMException, IOException, DataConversionException {
		Document salesDataDoc = XmlParser.getDocument(dataDirectory + "SalesData.xml");
		List<Element> rowElement = salesDataDoc.getRootElement().getChildren();

		List<SalesData> salesDatas = new ArrayList<SalesData>();
		for (Iterator<Element>iterator = rowElement.iterator(); iterator.hasNext();) {
			Element element = iterator.next();

			SalesData salesData = new SalesData();

			salesData.setStoreId(element.getAttribute("StoreID").getIntValue());
			salesData.setCustomerGroupId(element.getAttribute("KDGRP").getIntValue());
			salesData.setProductId(element.getAttribute("SKU").getIntValue());
			salesData.setDay(element.getAttribute("Day").getIntValue());
			salesData.setMonth(element.getAttribute("Month").getIntValue());
			salesData.setYear(element.getAttribute("Year").getIntValue());
			salesData.setCount(element.getAttribute("Count").getIntValue());
			salesData.setItemStatus(element.getAttributeValue("ItemStatus"));

			salesDatas.add(salesData);
		}

		return salesDatas;
	}

	public Collection<Product> getProducts() throws JDOMException, IOException, DataConversionException, ParseException {
		Document productsDoc = XmlParser.getDocument(dataDirectory + "Products.xml");
		List<Element> rowList = productsDoc.getRootElement().getChildren();

		List<Product> products = new ArrayList<Product>();
		for (Iterator<Element>iterator = rowList.iterator(); iterator.hasNext();) {
			Element rowElement =  iterator.next();

			List<Attribute> attributes = rowElement.getAttributes();
			Product product = new Product();
			for (Iterator<Attribute> iterator2 = attributes.iterator(); iterator2
					.hasNext();) {
				Attribute attribute = (Attribute) iterator2.next();

				if(attribute.getName().equalsIgnoreCase("MATNR")){
					product.setId(attribute.getIntValue());
				} else if (attribute.getName().equalsIgnoreCase("MAKTX")){
					product.setDescription(attribute.getValue());
				} else if(attribute.getName().equalsIgnoreCase("MAKTX2")){
					product.setDescription2(attribute.getValue());
				} else if(attribute.getName().equalsIgnoreCase("storeid")){
					product.setStoreId(attribute.getIntValue());
				} else if(attribute.getName().equalsIgnoreCase("browsenodeid")){
					product.setCategoryId(attribute.getIntValue());
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
					if(attribute.getValue() != null && attribute.getValue().length() > 0){
						product.setSpart(attribute.getIntValue());
					}
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

	public List<Category> getCategories() throws JDOMException, IOException, DataConversionException {
		Document categoriesDoc = XmlParser.getDocument(dataDirectory + "Categories.xml");
		List<Element> rowList = categoriesDoc.getRootElement().getChildren();

		List<Category> categories = new ArrayList<Category>();
		for (Iterator<Element> iterator = rowList.iterator(); iterator.hasNext();) {
			Element rowElement = iterator.next();

			List<Attribute> attributes = rowElement.getAttributes();

			Category category = new Category();
			for (Iterator<Attribute> iterator2 = attributes.iterator(); iterator2
					.hasNext();) {
				Attribute attribute = iterator2.next();

				if(attribute.getName().equalsIgnoreCase("storeid")){
					category.setStoreId(attribute.getIntValue());
				} else if(attribute.getName().equalsIgnoreCase("catalogid")){
					category.setCatalogId(attribute.getIntValue());
				} else if(attribute.getName().equalsIgnoreCase("browsenodeid")){
					category.setId(attribute.getIntValue());
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

		return categories;
	}

	public  void writeXmlToFile(Document doc, String fileName) throws IOException{
		XMLOutputter out = new XMLOutputter();
		out.setIndent("  "); // use two space indent
		out.setNewlines(true); 
		java.io.FileWriter writer = new java.io.FileWriter(OUTPUT_DIR + fileName);
		out.output(doc, writer);
		writer.flush();
		writer.close();      
	}

}
