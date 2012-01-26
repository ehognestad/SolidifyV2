package no.komplett.solidify;

import java.util.Collection;
import java.util.Date;

import no.komplett.solidify.data.Product;

public interface ProductService {

	Collection<Integer> getProductsNotInStock();
	
	Collection<Integer> getProductsNotInStockAndNotInStockWithinTimeFrame(Date notInStockBeforeDate);
	
	Collection<Integer> getProductsToRemove();
}
