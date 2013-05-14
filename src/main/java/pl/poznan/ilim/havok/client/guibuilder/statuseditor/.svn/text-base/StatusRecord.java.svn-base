package pl.poznan.ilim.havok.client.guibuilder.statuseditor;

import pl.poznan.ilim.havok.entities.Status;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class StatusRecord extends ListGridRecord implements IsSerializable {
	private Status source;
	public static final String ATTRIBUTE_ID = "StatusId";
	public static final String ATTRIBUTE_NAME = "Name";
	public static final String ATTRIBUTE_COLORCSS = "ColorCss";

	public StatusRecord(Status status) {
		this(status.getId(), status.getName(), status.getColorCss());
		this.source = status;
	}
	
	public StatusRecord(int id, String name, String cssColor) {
		this.setAttribute(StatusRecord.ATTRIBUTE_ID, id);
		this.setAttribute(StatusRecord.ATTRIBUTE_NAME, name);
		this.setAttribute(StatusRecord.ATTRIBUTE_COLORCSS, cssColor);
	}
	
	public Status getUpdatedStatus() {
		int id = Integer.parseInt(this.getId());
		this.source.setId(id);
		this.source.setName(this.getName());
		this.source.setColorCss(this.getColorCss());
		
		return this.source;
	}

	public String getId() {
		return this.getAttribute(StatusRecord.ATTRIBUTE_ID);
	}

	public String getName() {
		return this.getAttribute(StatusRecord.ATTRIBUTE_NAME);
	}

	public String getColorCss() {
		return this.getAttribute(StatusRecord.ATTRIBUTE_COLORCSS);
	}
}
