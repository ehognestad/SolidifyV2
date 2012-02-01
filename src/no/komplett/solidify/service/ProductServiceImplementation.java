package no.komplett.solidify.service;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import no.komplett.solidify.data.Product;
import no.komplett.solidify.specification.IsDemoProductSpecification;
import no.komplett.solidify.specification.NotInStockSpecification;
import no.komplett.solidify.specification.ProductEndOfLifeSpecification;
import no.komplett.solidify.specification.ProductFromCompanySpecification;
import no.komplett.solidify.specification.ProductFromStoreSpecification;
import no.komplett.solidify.specification.ProductsExpiringSpecification;
import no.komplett.solidify.specification.Specification;

public class ProductServiceImplementation implements ProductService{
	
	private Collection<Product> products;
	
	public ProductServiceImplementation(Collection<Product> products){
		this.products = products;
	}
	
	public Collection<Integer> getProductsNotInStock() {
		final Specification inStockSpecification = new NotInStockSpecification(1);
		return filterProducts(inStockSpecification);
	}
	
	public Collection<Integer> getProductsNotInStockAndNotInStockWithinTimeFrame(Date notInStockBeforeDate) {
		//System.out.println("notInStockBeforeDate: " + notInStockBeforeDate);
		
		final Specification outOfStockSpecification = new NotInStockSpecification(1);
		final Specification notInStockWithinNextWeek = new ProductsExpiringSpecification(notInStockBeforeDate);
		final Specification outOfStockAndNotInStockWithinNextWeekSpecification = outOfStockSpecification.and(notInStockWithinNextWeek);
		
		return filterProducts(outOfStockAndNotInStockWithinNextWeekSpecification);
	}
	
	public Collection<Integer> getProductsToRemove() {
		final Specification demoProductsSpecification = new IsDemoProductSpecification(getDemoProductTypeIds());
		final Specification companySpecification = new ProductFromCompanySpecification(getCompanyMinimumPriceMap());
		final Specification productEndOfLifeSpecification = new ProductEndOfLifeSpecification();
		final Specification productsToRemoveSpecification = demoProductsSpecification.or(companySpecification).or(productEndOfLifeSpecification);
		
		return filterProducts(productsToRemoveSpecification);
	}
	
	@Override
	public Collection<Product> getProductsForStoreId(int storeId){
		final Specification productFromStoreSpecification = new ProductFromStoreSpecification(storeId);
		
		return applySpecification(productFromStoreSpecification);
	}
	
	private Set<Integer> getDemoProductTypeIds(){
		Set<Integer> demoProductTypeIds = new HashSet<Integer>();
		demoProductTypeIds.add(90);
		demoProductTypeIds.add(91);
		demoProductTypeIds.add(92);
		
		return demoProductTypeIds;
	}
	
	private Map<Integer, Double> getCompanyMinimumPriceMap(){
		Map<Integer, Double> companyMinPriceMap = new HashMap<Integer, Double>();
		companyMinPriceMap.put(325,  1000.00);
		companyMinPriceMap.put(311, 500.00);
		companyMinPriceMap.put(318, 500.00);
		
		return companyMinPriceMap;
				
	}
	
	private Collection<Integer> filterProducts(final Specification specification) {
		Collection<Integer> productIds = new HashSet<Integer>();
		for(Product product : products){
			if(specification.isSatisfiedBy(product)){
				productIds.add(product.getId());
			}
		}
		
		return productIds;
	}
	
	private Collection<Product> applySpecification(final Specification specification) {
		Collection<Product> productsToKeep = new HashSet<Product>();
		for(Product product : products){
			if(specification.isSatisfiedBy(product)){
				productsToKeep.add(product);
			}
		}
		
		return productsToKeep;
	}
}
