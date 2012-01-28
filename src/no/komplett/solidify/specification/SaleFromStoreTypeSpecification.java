package no.komplett.solidify.specification;

import java.util.Set;

import no.komplett.solidify.data.SalesData;
import no.komplett.solidify.data.Stores;

public class SaleFromStoreTypeSpecification extends AbstractSpecification {

	private Set<String> storeTypes;

	public SaleFromStoreTypeSpecification(Set<String> storeTypes) {
		this.storeTypes = storeTypes;
	}

	public boolean isSatisfiedBy(Object o) {
		if (o instanceof SalesData) {
			SalesData sale = (SalesData) o;
			
//			System.out.println("sale store id: " + sale.getStoreId());

			Stores.Store currentStore = null;
			for(Stores.Store store : Stores.All){
				if(sale.getStoreId() == store.StoreId){
					currentStore = store;
					break;
				}
			}
			
//			System.out.println("current store type: " + currentStore.StoreType);

			return storeTypes.contains(currentStore.StoreType);
		} else {
			throw new ClassCastException("Specification only for Sale - received: " + o.getClass().getCanonicalName());
		}
	}
}