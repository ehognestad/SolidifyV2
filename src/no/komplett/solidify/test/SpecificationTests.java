package no.komplett.solidify.test;

import java.util.HashSet;
import java.util.Set;

import org.testng.Assert;

import no.komplett.solidify.data.SalesData;
import no.komplett.solidify.specification.SaleStatusSpecification;
import no.komplett.solidify.specification.SaleInCustomerGroupSpecification;
import no.komplett.solidify.specification.Specification;
import no.komplett.solidify.util.ItemSalesStatus;

public class SpecificationTests {

	public static void main(String[] args){
		SpecificationTests tests = new SpecificationTests();
		tests.testCancelledSaleSpecification("CD");
		tests.testInvalidCancelledSaleSpecification("CD");
		tests.testValidSaleInCustomerGroupSpecification(1);
		tests.testInvalidSaleInCustomerGroupSpecification(-99);
	}

	private void testCancelledSaleSpecification(String cancelledCode){
		System.out.println("testing cancelled sale specification");
		final Specification cancelledSaleSpecification = new SaleStatusSpecification(ItemSalesStatus.CANCELLED);

		SalesData sale = new SalesData(1, 1, 1, 1, 1, 1, 1, cancelledCode);
		assert(cancelledSaleSpecification.isSatisfiedBy(sale)) : "This sale should have been cancelled";

		sale = new SalesData(1, 1, 1, 1, 1, 1, 1, "SH");
		Assert.assertEquals(cancelledSaleSpecification.isSatisfiedBy(sale), false, "This sale should not have been considered cancelled");

		System.out.println("testCancelledSaleSpecification OK");
	}
	
	private void testInvalidCancelledSaleSpecification(String cancelledCode){
		System.out.println("testing invalid cancelled sale specification");
		final Specification cancelledSaleSpecification = new SaleStatusSpecification(ItemSalesStatus.CANCELLED);

		SalesData sale = new SalesData(1, 1, 1, 1, 1, 1, 1, cancelledCode);
		Assert.assertEquals(cancelledSaleSpecification.isSatisfiedBy(sale), false, "This sale should not have been considered cancelled");

		System.out.println("testInvalidCancelledSaleSpecification OK");
	}

	private void testValidSaleInCustomerGroupSpecification(int customerGroupId){
		System.out.println("testing sale that exists customergroup: " + customerGroupId);
		SalesData sale = new SalesData(1, 1, 1, 1, 1, 1, 1, "CD");

		Set<Integer> customerGroupSet = new HashSet<Integer>();
		customerGroupSet.add(customerGroupId);
		final Specification saleInCustomerGroup = new SaleInCustomerGroupSpecification(customerGroupSet);

		Assert.assertEquals(saleInCustomerGroup.isSatisfiedBy(sale), true, "The customergroupid[" + customerGroupId + "] should be satisfied by the specification");
		System.out.println("testValidSaleInCustomerGroupSpecification OK");
	}

	private void testInvalidSaleInCustomerGroupSpecification(int customerGroupId){
		System.out.println("testing sale not existing in customergroup: " + customerGroupId);

		SalesData sale = new SalesData(1, 1, 1, 1, 1, 1, 1, "CD");

		Set<Integer> customerGroupSet = new HashSet<Integer>();
		customerGroupSet.add(customerGroupId);
		final Specification inCustomerGroupSpecification = new SaleInCustomerGroupSpecification(customerGroupSet);

		Assert.assertEquals(inCustomerGroupSpecification.isSatisfiedBy(sale), false, "This customergroupid[" + customerGroupId + "] should be invalid");
		System.out.println("testInvalidSaleInCustomerGroupSpecification OK");
	}

}
