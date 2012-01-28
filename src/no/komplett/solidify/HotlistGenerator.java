package no.komplett.solidify;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import no.komplett.solidify.data.Category;
import no.komplett.solidify.data.Product;
import no.komplett.solidify.data.ProductSaleResult;
import no.komplett.solidify.data.SalesData;
import no.komplett.solidify.data.Stores;
import no.komplett.solidify.service.DataHelper;
import no.komplett.solidify.service.ProductService;
import no.komplett.solidify.service.ProductServiceImplementation;
import no.komplett.solidify.service.SalesService;
import no.komplett.solidify.service.SalesServiceImplementation;
import no.komplett.solidify.util.ItemSalesStatus;

import org.jdom.Attribute;
import org.jdom.DataConversionException;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;

/**
 * 
 * @author eivind
 *
 */
public class HotlistGenerator {

	public static final int TOP_NUMBER_OF_PRODUCTS = 5;

	private static DataHelper dataHelper;
	
	private static String hotListFileName = "%s_Hotlist_%s_Category_%s.xml";

	public static void main(String[] args) throws ParseException{
		generateHotLists(new Date(), "data/testdata/");
	}

	private static void generateHotLists(Date date, String dataDirectory) throws ParseException {
		try {
			dataHelper = new DataHelper(dataDirectory);
			List<Category> categories = dataHelper.getCategories();
			print("Number of categories: " + categories.size());

			//Getting products from store
			Collection<Product> products = dataHelper.getProducts();
			print("Number of products: " + products.size());

			ProductService productService = new ProductServiceImplementation(products);

			//Gettting all sales for store
			Collection<SalesData> allSales = dataHelper.getSales();
			SalesService salesService = new SalesServiceImplementation(allSales);
			print("Number of sales: " + allSales.size());

			print("==================================================================");

			for(Stores.Store store : Stores.All){
				Collection<Product> storeProducts = productService.getProductsForStoreId(store.StoreId);
				Collection<Category> storeCategories = filterCategoriesForStore(store.StoreId, categories);

				Collection<SalesData> storeSales = salesService.getSalesFromSpecificStore(store.StoreId);
				print("Sales for storeid[" + store.StoreId + "]: " + storeSales.size());

				calculateTopThreeSellersPerCategoryPerMonthAndPerWeek(date, store, storeProducts, storeSales, storeCategories);

				print("==================================================================");
			}
		} catch (Exception e) {
			print("*********************");
			print("Exception generating hotlists: " + e.getMessage());
			print("*********************");
		}
	}

	private static Collection<Category> filterCategoriesForStore(int storeId, Collection<Category> categories){
		Collection<Category> storeCategories = new HashSet<Category>();
		for (Iterator<Category> iterator = categories.iterator(); iterator.hasNext();) {
			Category category = iterator.next();

			if(category.getStoreId() == storeId){
				storeCategories.add(category);
			}
		}

		return storeCategories;
	}

	private static void calculateTopThreeSellersPerCategoryPerMonthAndPerWeek(Date date, Stores.Store store, Collection<Product> products, Collection<SalesData> storeSales, Collection<Category> categories) throws DataConversionException, JDOMException, IOException, ParseException{
		ProductService productService = new ProductServiceImplementation(products);

		//Getting products not in stock
		Collection<Integer> productsNotInStock = productService.getProductsNotInStock();
		print("products not in stock: " + productsNotInStock.size());

		//getting products not in stock and not in stock within week
		Collection<Integer> productsNotInStockAndNotInStockWithinNextWeek = productService.getProductsNotInStockAndNotInStockWithinTimeFrame(getNextWeek(date));
		print("products not in stock and not in stock next week: " + productsNotInStockAndNotInStockWithinNextWeek.size());

		//Getting specific products to remove, demo products, company specific products, and endoflife products
		Collection<Integer> productsToRemove = productService.getProductsToRemove();
		print("Number of products to remove: " + productsToRemove.size());

		//processing sales
		SalesService salesService = new SalesServiceImplementation(storeSales);
		Collection<SalesData> salesToRemove = salesService.getSalesToRemove(productsToRemove);
		print("Number of sales to remove: " + salesToRemove.size());
		
		//Getting cancelled orders
		salesToRemove.addAll(salesService.getSalesWithSpecificStatus(ItemSalesStatus.CANCELLED));

		storeSales.removeAll(salesToRemove);
		print("edited sales size: " + storeSales.size());

		//Removing specific customergroups
		Set<Integer> customerGroupsToIgnore = new HashSet<Integer>();
		customerGroupsToIgnore.add(10);
		salesToRemove = salesService.getSalesFromSpecificCustomerGroups(customerGroupsToIgnore);
		storeSales.removeAll(salesToRemove);
		print("Number of sales size after removing certain customergroups: " + storeSales.size());

		//Removing specific store types
		customerGroupsToIgnore = new HashSet<Integer>();
		customerGroupsToIgnore.add(20);
		customerGroupsToIgnore.add(30);
		salesToRemove = salesService.getSalesFromSpecificStoreTypesAndCustomerGroups(getStoreTypesToExclude(), customerGroupsToIgnore);
		storeSales.removeAll(salesToRemove);
		print("Number of sales size after removing certain store types: " + storeSales.size());

		//Getting sales from current week
		Collection<SalesData> salesCurrentWeek = salesService.getSalesFromCurrentWeek(date);
		print("Number of sales from current week: " + salesCurrentWeek.size());

		//Getting sales from current month
		Collection<SalesData> salesCurrentMonth = salesService.getSalesFromCurrentMonth(date);
		print("Number of sales from current month: " + salesCurrentMonth.size());

		for (Iterator<Category>iterator = categories.iterator(); iterator.hasNext();) {
			Category category = (Category) iterator.next();

			List<ProductSaleResult> weeklyResult = new ArrayList<ProductSaleResult>(getTopSellingProductsList(category, salesCurrentWeek, products));
			print("Weekly result size: " + weeklyResult.size());

			List<ProductSaleResult> monthlyResult = new ArrayList<ProductSaleResult>(getTopSellingProductsList(category, salesCurrentMonth, products));
			print("Monthly result size: " + monthlyResult.size());

			generateHotlist(weeklyResult, category, "Week", store.StoreId);
			generateHotlist(monthlyResult, category, "Month", store.StoreId);
		}
	}

	private static void printProductSalesResults(List<ProductSaleResult> weeklyResult) {
		for (Iterator<ProductSaleResult> iterator = weeklyResult.iterator(); iterator.hasNext();) {
			ProductSaleResult productSaleResult = (ProductSaleResult) iterator.next();
			print("saleresult: " + productSaleResult.getProduct().getId() + ", no of sales: " + productSaleResult.getNumberOfSales());
		}
	}
	
	private static Date getNextWeek(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, 7);

		return calendar.getTime();
	}

	private static Document generateHotlist(Collection<ProductSaleResult> products, Category category, String mode, Integer storeId) throws IOException{
		Element rootElement = new Element("Hotlist");
		Document doc = new Document(rootElement);

		rootElement.setAttribute(new Attribute("store", storeId.toString()));
		rootElement.setAttribute("category", String.valueOf(category.getId()));
		rootElement.setAttribute(new Attribute("type", mode));

		for(ProductSaleResult saleResult : products){
			Element itemElement = new Element("item");
			rootElement.addContent(itemElement);

			itemElement.setAttribute(new Attribute("ProductId", String.valueOf(saleResult.getProduct().getId())));
			itemElement.setAttribute("Product", saleResult.getProduct().getDescription());
			itemElement.setAttribute("TotalSales", String.valueOf(saleResult.getNumberOfSales()));
		}

		dataHelper.writeXmlToFile(doc, String.format(hotListFileName, storeId, mode, category.getId()));

		return doc;
	}

	private static Collection<ProductSaleResult> getTopSellingProductsList(Category category, Collection<SalesData> sales, Collection<Product> products){
		Map<Integer, Product> productMap = new HashMap<Integer, Product>();
		for(Product product : products){
			if(product.getCategoryId() == category.getId()){
				productMap.put(new Integer(product.getId()), product);
			}
		}

		Map<Integer, ProductSaleResult> resultMap = getSalesResults(sales, productMap);

		List<ProductSaleResult> saleResults = new ArrayList<ProductSaleResult>(resultMap.values());
		Collections.sort(saleResults);

		if(saleResults.size() > 0){
			saleResults = saleResults.subList(0, saleResults.size() >= TOP_NUMBER_OF_PRODUCTS ? TOP_NUMBER_OF_PRODUCTS : saleResults.size());
		}

		return saleResults;
	}

	private static Map<Integer, ProductSaleResult> getSalesResults(Collection<SalesData> sales, Map<Integer, Product> productMap) {
		//Counting number of sales per product
		Map<Integer, ProductSaleResult> resultMap = new HashMap<Integer, ProductSaleResult>();
		for(SalesData sale : sales){
			if(resultMap.get(sale.getProductId()) != null){
				ProductSaleResult saleResult = resultMap.get(sale.getProductId());
				saleResult.setNumberOfSales(saleResult.getNumberOfSales() + sale.getCount());
			} else{
				if(productMap.get(sale.getProductId()) != null){
					ProductSaleResult saleResult = new ProductSaleResult(productMap.get(sale.getProductId()), sale.getCount());
					resultMap.put(sale.getProductId(), saleResult);
				}
			}
		}
		return resultMap;
	}

	private static Set<String> getStoreTypesToExclude(){
		Set<String> set = new HashSet<String>();
		set.add("B2C");

		return set;
	}

	private static void print(String stringToPring){
		System.out.println(stringToPring);
	}
}
