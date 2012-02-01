package no.komplett.solidify.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import no.komplett.solidify.data.SalesData;
import no.komplett.solidify.specification.SaleFromCurrentMonthSpecification;
import no.komplett.solidify.specification.SaleFromCurrentWeekSpecification;
import no.komplett.solidify.specification.SaleFromStoreSpecification;
import no.komplett.solidify.specification.SaleFromStoreTypeSpecification;
import no.komplett.solidify.specification.SaleInCustomerGroupSpecification;
import no.komplett.solidify.specification.SaleStatusSpecification;
import no.komplett.solidify.specification.SalesToRemoveSpecification;
import no.komplett.solidify.specification.Specification;
import no.komplett.solidify.util.ItemSalesStatus;

public class SalesServiceImplementation implements SalesService {

	Collection<SalesData> salesDataCollection;

	public SalesServiceImplementation(Collection<SalesData> salesDataCollection){
		this.salesDataCollection = salesDataCollection;
	}

	@Override
	public Collection<SalesData> getSalesFromCurrentWeek(Date today) {
		Collection<SalesData> salesToKeep;

		salesToKeep = new HashSet<SalesData>();
		final Specification currentWeekSpecification = new SaleFromCurrentWeekSpecification(today);
		for(SalesData sale : salesDataCollection){
			if(currentWeekSpecification.isSatisfiedBy(sale)){
				salesToKeep.add(sale);
			}
		}

		return salesToKeep;
	}

	@Override
	public Collection<SalesData> getSalesFromCurrentMonth(Date today) {
		Collection<SalesData> salesToKeep;

		salesToKeep = new HashSet<SalesData>();
		final Specification currentMonthSpecification = new SaleFromCurrentMonthSpecification(today);
		for(SalesData sale : salesDataCollection){
			if(currentMonthSpecification.isSatisfiedBy(sale)){
				salesToKeep.add(sale);
			}
		}

		return salesToKeep;
	}

	@Override
	public Collection<SalesData> getSalesFromSpecificCustomerGroups(Set<Integer> customerGroupIds) {
		Collection<SalesData> salesToKeep;

		salesToKeep = new HashSet<SalesData>();
		final Specification currentMonthSpecification = new SaleInCustomerGroupSpecification(customerGroupIds);
		for(SalesData sale : salesDataCollection){
			if(currentMonthSpecification.isSatisfiedBy(sale)){
				salesToKeep.add(sale);
			}
		}

		return salesToKeep;
	}

	@Override
	public Collection<SalesData> getSalesFromSpecificStoreTypesAndCustomerGroups(Set<String> storeTypes, Set<Integer> customerGroupIds) {
		Collection<SalesData> salesToKeep;

		salesToKeep = new HashSet<SalesData>();
		final Specification saleInCustomerGroupSpecification = new SaleInCustomerGroupSpecification(customerGroupIds);
		final Specification saleFromStoreTypeSpecification = new SaleFromStoreTypeSpecification(storeTypes);
		final Specification saleFromStoreTypeAndCustomerGroupsSpecification = saleInCustomerGroupSpecification.and(saleFromStoreTypeSpecification);

		for(SalesData sale : salesDataCollection){
			if(saleFromStoreTypeAndCustomerGroupsSpecification.isSatisfiedBy(sale)){
				salesToKeep.add(sale);
			}
		}

		return salesToKeep;
	}

	@Override
	public Collection<SalesData> getSalesFromSpecificStore(int storeId) {
		Collection<SalesData> salesToKeep;

		salesToKeep = new HashSet<SalesData>();
		final Specification currentMonthSpecification = new SaleFromStoreSpecification(storeId);
		for(SalesData sale : salesDataCollection){
			if(currentMonthSpecification.isSatisfiedBy(sale)){
				salesToKeep.add(sale);
			}
		}

		return salesToKeep;
	}

	public Collection<SalesData> getSalesToRemove(Collection<Integer> productsToRemove){
		Collection<SalesData> salesToRemove = new ArrayList<SalesData>();

		final Specification salesToRemoveSpecification = new SalesToRemoveSpecification(productsToRemove);
		for(SalesData sale : salesDataCollection){
			if(salesToRemoveSpecification.isSatisfiedBy(sale)){
				salesToRemove.add(sale);
			}
		}
		
		return salesToRemove;
	}

	@Override
	public Collection<SalesData> getSalesWithSpecificStatus(ItemSalesStatus itemStatus) {
		Collection<SalesData> salesToRemove = new ArrayList<SalesData>();

		final Specification cancelledSalesSpecification = new SaleStatusSpecification(itemStatus);
		for(SalesData sale : salesDataCollection){
			if(cancelledSalesSpecification.isSatisfiedBy(sale)){
				salesToRemove.add(sale);
			}
		}
		
		return salesToRemove;
	}
}
