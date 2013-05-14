package pl.poznan.ilim.havok.client.guibuilder.itemeditor;

import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.menu.MenuItem;

public class ItemsListGrid extends ListGrid {
	private static final String HEADER_GTIN = "Numer GTIN";
	private static final String HEADER_DESCRIPTION = "Opis przedmiotu";
	private static final String HEADER_PRICE = "Cena jednostkowa";
	
	public ItemsListGrid() {
		this.initializeClass();
		this.initializeListGridFields();
	}
	
	@Override
	protected MenuItem[] getHeaderContextMenuItems(final Integer fieldNum) {
		return new MenuItem[] {};
	}
	
	private void initializeClass() {
		this.setShowAllRecords(true);
		this.setShowGridSummary(false);
		this.setShowGroupSummary(false);
		this.setCanEdit(true);		
		this.setEditEvent(ListGridEditEvent.CLICK);
		this.setWidth100();
		this.setHeight100();
	}
	
	private void initializeListGridFields() {
		ListGridField fieldGtin = new ListGridField(ItemRecord.ATTRIBUTE_GTIN,
				ItemsListGrid.HEADER_GTIN);
		ListGridField fieldDescription = new ListGridField(ItemRecord.ATTRIBUTE_DESCRIPTION,
				ItemsListGrid.HEADER_DESCRIPTION);
		ListGridField fieldPrice = new ListGridField(ItemRecord.ATTRIBUTE_PRICE,
				ItemsListGrid.HEADER_PRICE);
		this.setFields(fieldGtin, fieldDescription, fieldPrice);
	}
}
