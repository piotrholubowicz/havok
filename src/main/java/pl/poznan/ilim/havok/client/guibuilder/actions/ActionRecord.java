/**
 * 
 */
package pl.poznan.ilim.havok.client.guibuilder.actions;

import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * @author PHolubow
 * 
 */
public class ActionRecord extends ListGridRecord {
	
	public ActionRecord(String oldStatus, String newStatus, String gtin, int quantity, String serial) {
		setOldStatus(oldStatus);
		setNewStatus(newStatus);
		setGTIN(gtin);
		setQuantity(quantity);
		setSerial(serial);
	}

	public void setOldStatus(String oldStatus) {
		setAttribute("oldStatus", oldStatus);
	}

	public String getOldStatus() {
		return getAttributeAsString("oldStatus");
	}

	public void setNewStatus(String newStatus) {
		setAttribute("newStatus", newStatus);
	}

	public String getNewStatus() {
		return getAttributeAsString("newStatus");
	}

	public void setGTIN(String gtin) {
		setAttribute("gtin", gtin);
	}

	public String getGTIN() {
		return getAttributeAsString("gtin");
	}

	public void setQuantity(int quantity) {
		setAttribute("quantity", quantity);
	}

	public int getQuantity() {
		return getAttributeAsInt("quantity");
	}

	public void setSerial(String serial) {
		setAttribute("serial", serial);
	}

	public String getSerial() {
		return getAttributeAsString("serial");
	}

}
