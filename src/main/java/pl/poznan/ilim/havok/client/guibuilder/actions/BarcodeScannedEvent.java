/**
 * 
 */
package pl.poznan.ilim.havok.client.guibuilder.actions;

import com.google.gwt.event.shared.GwtEvent;


/**
 * @author PHolubow
 * 
 */
public class BarcodeScannedEvent extends GwtEvent<ActionsEventHandler> {

	public static Type<ActionsEventHandler> TYPE = new Type<ActionsEventHandler>();
	
	private String barcode;

	public String getBarcode() {
		return barcode;
	}

	public BarcodeScannedEvent(String barcode) {
		super();
		this.barcode = barcode;
	}

	@Override
	protected void dispatch(ActionsEventHandler handler) {
		handler.onBarcodeScanned(this);
	}

	@Override
	public Type<ActionsEventHandler> getAssociatedType() {
		return TYPE;
	}

}
