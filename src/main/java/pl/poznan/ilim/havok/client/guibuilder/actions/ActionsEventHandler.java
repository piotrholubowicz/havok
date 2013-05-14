package pl.poznan.ilim.havok.client.guibuilder.actions;

import com.google.gwt.event.shared.EventHandler;

public interface ActionsEventHandler extends EventHandler {

	public void onBarcodeScanned(BarcodeScannedEvent event);

	public void onClearActionsClicked(ClearActionsClickedEvent event);

}
