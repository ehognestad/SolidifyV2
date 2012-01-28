package no.komplett.solidify.data;

public class ProductSaleResult implements Comparable<ProductSaleResult> {

	private Product product;
	
	private int numberOfSales;

	public ProductSaleResult(Product product, int numberOfSales) {
		super();
		this.product = product;
		this.numberOfSales = numberOfSales;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getNumberOfSales() {
		return numberOfSales;
	}

	public void setNumberOfSales(int numberOfSales) {
		this.numberOfSales = numberOfSales;
	}

	@Override
	public int compareTo(ProductSaleResult productSaleResult) {
		return numberOfSales < productSaleResult.getNumberOfSales() ? 1 : 0;
	}
}
