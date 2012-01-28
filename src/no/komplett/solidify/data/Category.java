package no.komplett.solidify.data;

/**
 * <row storeId="310" DeptId="1" CatalogId="10719" BrowseNodeId="10099" Depth="2" Path=".10719.10099." ParentNodeId="0" CountOfProducts="144" Type="BN" Url="" 
 * BrowseNodeTxt="Hjemmekino/HiFi" />
 * @author eivind
 *
 */
public class Category {
	private int id;
	
	private int storeId;
	
	private int catalogId;
	
	private int productCount;
	
	private String type;
	
	private String url;
	
	private String browseNodeText;
	
	public Category() {
		super();
	}

	public Category(int id, int storeId, int catalogId, int productCount, String type, String url, String browseNodeText) {
		super();
		this.storeId = storeId;
		this.catalogId = catalogId;
		this.id = id;
		this.productCount = productCount;
		this.type = type;
		this.url = url;
		this.browseNodeText = browseNodeText;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public int getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(int catalogId) {
		this.catalogId = catalogId;
	}

	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getBrowseNodeText() {
		return browseNodeText;
	}

	public void setBrowseNodeText(String browseNodeText) {
		this.browseNodeText = browseNodeText;
	}

}
