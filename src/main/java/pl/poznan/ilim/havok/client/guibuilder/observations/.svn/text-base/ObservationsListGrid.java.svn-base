package pl.poznan.ilim.havok.client.guibuilder.observations;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.GroupStartOpen;
import com.smartgwt.client.types.SummaryFunctionType;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.GroupNode;
import com.smartgwt.client.widgets.grid.GroupTitleRenderer;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.menu.MenuItem;

public class ObservationsListGrid extends ListGrid implements IsSerializable {

	private static final String HEADER_GTIN = "Numer GTIN";
	private static final String HEADER_SERIAL = "Numer seryjny";
	private static final String HEADER_DESCRIPTION = "Opis przedmiotu";
	private static final String HEADER_STATUS = "Status";
	private static final String HEADER_DATE = "Ostatni odczyt";
	private static final String HEADER_QUANTITY = "Liczba przedmiotów";

	private static final String FORMAT_DATE = "HH:mm:ss, dd/MM/yyyy";
	
	public ObservationsListGrid() {
		this.initializeClass();
		this.initializeListGridFields();
		ObservationsController.exportStaticMethod();
	}

	@Override
	protected String getCellCSSText(ListGridRecord record, int rowNum, int colNum) {
		// Formatted status-cells display (colors)
		if (getFieldName(colNum).equals(ObservationRecord.ATTRIBUTE_STATUS)) {
			if (record instanceof ObservationRecord) {
				ObservationRecord observation = (ObservationRecord) record;
				return this.getCustomStatusStyles(observation);
			}
		}

		return super.getCellCSSText(record, rowNum, colNum);
	}

	@Override
	protected MenuItem[] getHeaderContextMenuItems(final Integer fieldNum) {
		// Disable all default menu items; grouping options are moved to buttons
		return new MenuItem[] {};
	}

	private void initializeClass() {
		this.setShowAllRecords(true);
		this.setCanEdit(false);
		this.setGroupByField(ObservationRecord.ATTRIBUTE_GTIN);
		this.setGroupStartOpen(GroupStartOpen.ALL);
		this.setShowGridSummary(true);
		this.setShowGroupSummary(true);
		this.setWidth100();
		this.setHeight100();
	}

	private void initializeListGridFields() {
		ListGridField fieldGtin = this.createGtinField();
		ListGridField fieldSerial = this.createDefaultField(ObservationRecord.ATTRIBUTE_SERIAL,
				ObservationsListGrid.HEADER_SERIAL);
		ListGridField fieldDescription = this.createDefaultField(
				ObservationRecord.ATTRIBUTE_DESCRIPTION, ObservationsListGrid.HEADER_DESCRIPTION);
		ListGridField fieldStatus = this.createStatusField();
		ListGridField fieldDate = this.createDateField();
		ListGridField fieldAmount = this.createQuantityField();
		this.setFields(fieldGtin, fieldSerial, fieldDescription, fieldStatus, fieldDate,
				fieldAmount);
	}

	private String getCustomStatusStyles(ObservationRecord observation) {
		return "font-weight: bold; color: " + observation.getStatusColor();
	}

	private ListGridField createDefaultField(String fieldName, String fieldHeader) {
		ListGridField field = new ListGridField(fieldName, fieldHeader);
		field.setShowGridSummary(false);
		field.setShowGroupSummary(false);
		return field;
	}

	private ListGridField createGtinField() {
		ListGridField field = this.createDefaultField(ObservationRecord.ATTRIBUTE_GTIN,
				ObservationsListGrid.HEADER_GTIN);
		// Formatted group header display
		field.setGroupTitleRenderer(new GroupTitleRenderer() {
			@Override
			public String getGroupTitle(Object groupValue, GroupNode groupNode,
					ListGridField field, String fieldName, ListGrid grid) {
				Record[] records = groupNode.getGroupMembers();
				if (records.length > 0) {
					if (records[0] instanceof ObservationRecord) {
						ObservationRecord observation = (ObservationRecord) records[0];
						String groupHeader = observation.getGtin()
								+ " : "
								+ observation.getDescription()
								+ "&nbsp;&nbsp;&nbsp;<img src='images/chart-small.png' border='0' onClick=\"window.gtinchart('"
								+ observation.getGtin() + "');\">";
						return groupHeader;
					}
				}

				return "";
			}
		});
		return field;
	}

	private ListGridField createStatusField() {
		ListGridField field = this.createDefaultField(ObservationRecord.ATTRIBUTE_STATUS,
				ObservationsListGrid.HEADER_STATUS);
		// Formatted group header display
		field.setGroupTitleRenderer(new GroupTitleRenderer() {
			@Override
			public String getGroupTitle(Object groupValue, GroupNode groupNode,
					ListGridField field, String fieldName, ListGrid grid) {
				Record[] records = groupNode.getGroupMembers();
				if (records.length > 0) {
					if (records[0] instanceof ObservationRecord) {
						ObservationRecord observation = (ObservationRecord) records[0];
						String groupHeader = observation.getStatusName()
								+ "&nbsp;&nbsp;&nbsp;<img src='images/chart-small.png' border='0' onClick=\"window.statuschart("
								+ observation.getStatusId() + ");\">";
						return groupHeader;
					}
				}

				return "";
			}
		});
		return field;
	}

	private ListGridField createDateField() {
		ListGridField field = this.createDefaultField(ObservationRecord.ATTRIBUTE_DATE,
				ObservationsListGrid.HEADER_DATE);
		final DateTimeFormat dateFormatter = DateTimeFormat
				.getFormat(ObservationsListGrid.FORMAT_DATE);
		// Formatted date cell display
		field.setCellFormatter(new CellFormatter() {
			public String format(Object value, ListGridRecord record, int rowNum, int colNum) {
				if (value != null) {
					try {
						Date dateValue = (Date) value;
						return dateFormatter.format(dateValue);
					} catch (Exception e) {
						return value.toString();
					}
				} else {
					return "";
				}
			}
		});
		return field;
	}

	private ListGridField createQuantityField() {
		ListGridField field = new ListGridField(ObservationRecord.ATTRIBUTE_QUANTITY,
				ObservationsListGrid.HEADER_QUANTITY);
		field.setShowGridSummary(true);
		field.setShowGroupSummary(true);
		field.setSummaryFunction(SummaryFunctionType.SUM);
		return field;
	}
}