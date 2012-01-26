package no.komplett.solidify;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import no.komplett.solidify.data.Product;
import no.komplett.solidify.rules.InStockSpecification;
import no.komplett.solidify.rules.IsDemoProductSpecification;
import no.komplett.solidify.rules.ProductFromCompanySpecification;
import no.komplett.solidify.rules.ProductsExpiringSpecification;
import no.komplett.solidify.rules.Specification;

public class ProductServiceImplementation implements ProductService{
	
	private Collection<Product> products;
	
	public ProductServiceImplementation(Collection<Product> products){
		this.products = products;
	}
	
	@Override
	public Collection<Product> getProductsInStockNotExpiringWithinWeek() {
		Collection<Product> productsToKeep = new HashSet<Product>();
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		Date oneWeekAhead = calendar.getTime();
		
		final Specification inStockSpecification = new InStockSpecification(1);
		final Specification inStockWithinNextWeek = new ProductsExpiringSpecification(oneWeekAhead);
		final Specification demoProducsSpecification = new IsDemoProductSpecification(getDemoProductTypeIds());
		final Specification productFromCompanySpecification = new ProductFromCompanySpecification(325);
		
		final Specification productsInStockAndNotExpiringWithingWeek = inStockSpecification.and(inStockWithinNextWeek).not(demoProducsSpecification);
		
		for(Product product : products){
			if(productsInStockAndNotExpiringWithingWeek.isSatisfiedBy(product)){
				productsToKeep.add(product);
			}
		}
		
		return productsToKeep;
	}
	
	public Collection<Product> getProductsInStock() {
		Collection<Product> productsToKeep = new HashSet<Product>();
		
		final Specification inStockSpecification = new InStockSpecification(1);
		
		for(Product product : products){
			if(inStockSpecification.isSatisfiedBy(product)){
				productsToKeep.add(product);
			}
		}
		
		return productsToKeep;
	}
	
	public Collection<Product> getNonDemoProducts() {
		Collection<Product> productsToKeep = new HashSet<Product>();
		
		final Specification demoProducsSpecification = new IsDemoProductSpecification(getDemoProductTypeIds());
		
		for(Product product : products){
			if(!demoProducsSpecification.isSatisfiedBy(product)){
				productsToKeep.add(product);
			}
		}
		
		return productsToKeep;
	}
	
	private Set<Integer> getDemoProductTypeIds(){
		Set<Integer> demoProductTypeIds = new HashSet<Integer>();
		demoProductTypeIds.add(90);
		demoProductTypeIds.add(91);
		demoProductTypeIds.add(92);
		
		return demoProductTypeIds;
	}
}
