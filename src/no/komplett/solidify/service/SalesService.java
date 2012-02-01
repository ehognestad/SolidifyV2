package no.komplett.solidify.service;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

import no.komplett.solidify.data.Product;
import no.komplett.solidify.data.SalesData;
import no.komplett.solidify.util.ItemSalesStatus;

public interface SalesService {

	Collection<SalesData> getSalesFromCurrentWeek(Date today);

	Collection<SalesData> getSalesFromCurrentMonth(Date today);

	Collection<SalesData> getSalesFromSpecificCustomerGroups(Set<Integer> customerGroups);

	Collection<SalesData> getSalesFromSpecificStoreTypesAndCustomerGroups(Set<String> storeTypes, Set<Integer> customerGroupIds);
	
	Collection<SalesData> getSalesFromSpecificStore(int storeId);

	Collection<SalesData> getSalesToRemove(Collection<Integer> productsToRemove);
	
	Collection<SalesData> getSalesWithSpecificStatus(ItemSalesStatus itemStatus);
}
