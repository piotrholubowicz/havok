package pl.poznan.ilim.havok.client.guibuilder.statuseditor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.ColorPicker;
import com.smartgwt.client.widgets.form.events.ColorSelectedEvent;
import com.smartgwt.client.widgets.form.events.ColorSelectedHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.menu.MenuItem;

public class StatusesListGrid extends ListGrid implements IsSerializable {
	private static final String HEADER_NAME = "Nazwa";
	private static final String HEADER_CSSCOLOR = "Kolor wyświetlania";
	private static final String COLORCSS_CELL_BORDER = "1px solid #FFFFFF";
	private static final int COLORCSS_CELL_WIDTH = 120;

	public StatusesListGrid() {
		this.initializeClass();
		this.initializeListGridFields();
	}
	
	@Override
	protected MenuItem[] getHeaderContextMenuItems(final Integer fieldNum) {
		return new MenuItem[] {};
	}	
	
	@Override
	protected Canvas createRecordComponent(final ListGridRecord record, Integer colNum) {
		String fieldName = this.getFieldName(colNum);
		if (fieldName.equals(StatusRecord.ATTRIBUTE_COLORCSS)) {
			GWT.log("Creating record component");
			GWT.log(record.toString());
			if (record instanceof StatusRecord) {
				final Label label = new Label();
				StatusRecord statusRecord = (StatusRecord) record;	
				label.setHeight(this.getCellHeight());
				label.setWidth(StatusesListGrid.COLORCSS_CELL_WIDTH);
				GWT.log("CSS Color = " + statusRecord.getColorCss());
				label.setBackgroundColor(statusRecord.getColorCss());
				label.setBorder(StatusesListGrid.COLORCSS_CELL_BORDER);
				label.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						ColorPicker picker = new ColorPicker();
						picker.addColorSelectedHandler(new ColorSelectedHandler() {
							
							@Override
							public void onColorSelected(ColorSelectedEvent event) {
								label.setBackgroundColor(event.getColor());
							}
						});
						picker.showNextTo(label);
					}
				});
				return label;
			}
		}
				
		return null;
	}

	private void initializeClass() {
		this.setShowAllRecords(true);
		this.setShowRecordComponents(true);          
        this.setShowRecordComponentsByCell(true); 	
		this.setWidth100();
		this.setHeight100();
	}

	private void initializeListGridFields() {
		ListGridField fieldName = new ListGridField(StatusRecord.ATTRIBUTE_NAME,
				StatusesListGrid.HEADER_NAME);
		ListGridField fieldColor = new ListGridField(StatusRecord.ATTRIBUTE_COLORCSS,
				StatusesListGrid.HEADER_CSSCOLOR);
		fieldColor.setWidth(StatusesListGrid.COLORCSS_CELL_WIDTH);
		fieldColor.setCanEdit(false);
		this.setFields(fieldName, fieldColor);
	}
}
