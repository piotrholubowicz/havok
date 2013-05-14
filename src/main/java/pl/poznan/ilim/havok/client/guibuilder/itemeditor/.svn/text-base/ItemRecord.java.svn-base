package pl.poznan.ilim.havok.client.guibuilder.itemeditor;

import pl.poznan.ilim.havok.entities.Item;

import com.smartgwt.client.widgets.grid.ListGridRecord;

public class ItemRecord extends ListGridRecord {
	private Item source;
	public static final String ATTRIBUTE_GTIN = "GTIN";
	public static final String ATTRIBUTE_DESCRIPTION = "Description";
	public static final String ATTRIBUTE_PRICE = "Price";
	
	public ItemRecord(String gtin, String description, Float price) {
		this.setAttribute(ItemRecord.ATTRIBUTE_GTIN, gtin);
		this.setAttribute(ItemRecord.ATTRIBUTE_DESCRIPTION, description);
		this.setAttribute(ItemRecord.ATTRIBUTE_PRICE, price);
	}
	
	public ItemRecord(Item item) {
		this(item.getGtin(), item.getDescription(), item.getPrice());
		source = item;
	}	
	
	public Item getUpdatedItem() {
		float price = Float.parseFloat(this.getPrice());		
		this.source.setGtin(this.getGtin());
		this.source.setDescription(this.getDescription());
		this.source.setPrice(price);
		
		return this.source;
	}
	
	public String getGtin() {
		return this.getAttribute(ItemRecord.ATTRIBUTE_GTIN);
	}
	
	public String getDescription() {
		return this.getAttribute(ItemRecord.ATTRIBUTE_DESCRIPTION);
	}

	public String getPrice() {
		return this.getAttribute(ItemRecord.ATTRIBUTE_PRICE);
	}
}
