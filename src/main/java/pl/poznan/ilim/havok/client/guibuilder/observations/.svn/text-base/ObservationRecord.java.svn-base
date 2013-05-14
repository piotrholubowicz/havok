package pl.poznan.ilim.havok.client.guibuilder.observations;

import java.util.Date;

import pl.poznan.ilim.havok.entities.Observation;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class ObservationRecord extends ListGridRecord implements IsSerializable {
	private Observation source;
	public static final String ATTRIBUTE_GTIN = "GTIN";
	public static final String ATTRIBUTE_SERIAL = "Serial";
	public static final String ATTRIBUTE_DESCRIPTION = "Description";
	public static final String ATTRIBUTE_STATUS = "Status";
	public static final String ATTRIBUTE_DATE = "Date";
	public static final String ATTRIBUTE_QUANTITY = "Quantity";
	public static final String ATTRIBUTE_STATUSCOLOR = "StatusColor";
	public static final String ATTRIBUTE_STATUSID = "StatusId";

	public ObservationRecord(String gtin, String serial, String description, int statusId, String currentStatus,
			String cssColor, Date date, int quantity) {
		this.initializeData(gtin, serial, description, statusId, currentStatus, cssColor, date, quantity);
	}

	private void initializeData(String gtin, String serial, String description, int statusId,
			String currentStatus, String cssColor, Date date, int quantity) {
		this.setAttribute(ObservationRecord.ATTRIBUTE_GTIN, gtin);
		this.setAttribute(ObservationRecord.ATTRIBUTE_SERIAL, serial);
		this.setAttribute(ObservationRecord.ATTRIBUTE_DESCRIPTION, description);
		this.setAttribute(ObservationRecord.ATTRIBUTE_STATUS, currentStatus);
		this.setAttribute(ObservationRecord.ATTRIBUTE_DATE, date);
		this.setAttribute(ObservationRecord.ATTRIBUTE_QUANTITY, quantity);
		this.setAttribute(ObservationRecord.ATTRIBUTE_STATUSCOLOR, cssColor);
		this.setAttribute(ObservationRecord.ATTRIBUTE_STATUSID, statusId);
	}

	public ObservationRecord(Observation observation) {
		GWT.log("Creating observation, id = " + observation.getId());
		String gtin = observation.getItem().getGtin();
		String desc = observation.getItem().getDescription();
		String status = observation.getStatus().getName();
		String color = observation.getStatus().getColorCss();
		int statusId = observation.getStatus().getId();

		this.initializeData(gtin, observation.getSerial(), desc, statusId, status, color,
				observation.getDate(), observation.getQuantity());
		this.source = observation;
	}

	public Observation getUpdatedObservation() {
		// TODO: Observations editing
		return this.source;
	}

	public String getGtin() {
		return this.getAttribute(ObservationRecord.ATTRIBUTE_GTIN);
	}

	public String getSerial() {
		return this.getAttribute(ObservationRecord.ATTRIBUTE_SERIAL);
	}

	public String getDescription() {
		return this.getAttribute(ObservationRecord.ATTRIBUTE_DESCRIPTION);
	}

	public String getStatusName() {
		return this.getAttribute(ObservationRecord.ATTRIBUTE_STATUS);
	}

	public String getDate() {
		return this.getAttribute(ObservationRecord.ATTRIBUTE_DATE);
	}

	public String getQuantity() {
		return this.getAttribute(ObservationRecord.ATTRIBUTE_QUANTITY);
	}

	public String getStatusColor() {
		return this.getAttribute(ObservationRecord.ATTRIBUTE_STATUSCOLOR);
	}

	public String getStatusId() {
		return this.getAttribute(ObservationRecord.ATTRIBUTE_STATUSID);
	}
}
