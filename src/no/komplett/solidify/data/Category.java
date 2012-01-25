package no.komplett.solidify.data;

/**
 * <row storeId="310" DeptId="1" CatalogId="10719" BrowseNodeId="10099" Depth="2" Path=".10719.10099." ParentNodeId="0" CountOfProducts="144" Type="BN" Url="" 
 * BrowseNodeTxt="Hjemmekino/HiFi" />
 * @author eivind
 *
 */
public class Category {
	
	private int storeId;
	
	private int catalogId;
	
	private int browseNodeId;
	
	private int productCount;
	
	private String type;
	
	private String url;
	
	private String browseNodeText;
	
	public Category() {
		super();
	}

	public Category(int storeId, int catalogId, int browseNodeId,
			int productCount, String type, String url, String browseNodeText) {
		super();
		this.storeId = storeId;
		this.catalogId = catalogId;
		this.browseNodeId = browseNodeId;
		this.productCount = productCount;
		this.type = type;
		this.url = url;
		this.browseNodeText = browseNodeText;
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

	public int getBrowseNodeId() {
		return browseNodeId;
	}

	public void setBrowseNodeId(int browseNodeId) {
		this.browseNodeId = browseNodeId;
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
