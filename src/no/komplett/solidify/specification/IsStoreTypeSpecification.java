package no.komplett.solidify.specification;

import no.komplett.solidify.data.Stores.Store;

public class IsStoreTypeSpecification extends AbstractSpecification {
  
	private String storeType;

  public IsStoreTypeSpecification(String storeType) {
    this.storeType = storeType;
  }

  public boolean isSatisfiedBy(Object o) {
    if (o instanceof Store) {
    	Store store = (Store) o;
      return store.StoreType.equalsIgnoreCase(storeType);
    } else {
      throw new ClassCastException("Specification only for Sale - received: " + o.getClass().getCanonicalName());
    }
  }
}