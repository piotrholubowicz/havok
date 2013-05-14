/**
 * 
 */
package pl.poznan.ilim.havok.client.guibuilder.actions;

import pl.poznan.ilim.havok.client.guibuilder.IViewBuilder;
import pl.poznan.ilim.havok.client.model.Model;
import pl.poznan.ilim.havok.client.services.ActionService;
import pl.poznan.ilim.havok.client.services.ActionServiceAsync;
import pl.poznan.ilim.havok.entities.Item;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author PHolubow
 * 
 */
public class ActionsController implements IViewBuilder, ActionsEventHandler {

	public final String GS1_128_CodeId = "H";
	public final String EAN_13_CodeId = "M";
	public final String AI_Location = "91";
	public final String AI_GTIN = "02";
	public final String AI_Serial = "21";
	public final int GS1_128_GTIN_Length = 14;

	// MVC
	private ActionsTab view;
	private Model model;

	// GWT services
	private ActionServiceAsync actionService;
	private AsyncCallback<Void> callback;
	private boolean onlySerialItems = false;

	public ActionsController(Model model2) {
		this.model = model2;

		GWT.log("Creating services");
		actionService = GWT.create(ActionService.class);
		callback = new AsyncCallback<Void>() {

			@Override
			public void onSuccess(Void arg0) {
			}

			@Override
			public void onFailure(Throwable arg0) {
				GWT.log("Failed to store action on server");
			}
		};

		GWT.log("Creating view");
		view = new ActionsTab(model);
		view.getEventBus().addHandler(BarcodeScannedEvent.TYPE, this);
		view.getEventBus().addHandler(ClearActionsClickedEvent.TYPE, this);
	}

	@Override
	public String getViewName() {
		return "Skanowanie";
	}

	@Override
	public String getIconName() {
		return "tab-actions.png";
	}

	@Override
	public VLayout createView() {
		return view;
	}

	@Override
	public void onBarcodeScanned(BarcodeScannedEvent event) {
		String barcode = event.getBarcode().substring(0, event.getBarcode().length() - 1);
		// czy to jest status?
		if (barcode.startsWith(GS1_128_CodeId + AI_Location)) {
			try {
				int statusId = Integer.parseInt(barcode.substring(GS1_128_CodeId.length() + AI_Location.length()));
				view.setCurrentStatus(statusId);
				view.setMessage("Odczytano status", false);
			} catch (NumberFormatException ex) {
				view.setMessage("Kod kreskowy nie składa się z samych cyfr", true);
				GWT.log("Kod kreskowy nie składa się z samych cyfr", ex);
			} catch (Exception ex) {
				view.setMessage("Nieznany status", true);
				GWT.log("Nieznany status", ex);
			}
		} else {
			if (barcode.startsWith(GS1_128_CodeId + AI_GTIN)) {
				barcode = barcode.substring(GS1_128_CodeId.length() + AI_GTIN.length());
				if (barcode.length() < GS1_128_GTIN_Length) {
					view.setMessage("Niepoprawny GTIN", true);
					return;
				}
				String gtin = barcode.substring(0, GS1_128_GTIN_Length);
				if (barcode.substring(GS1_128_GTIN_Length).startsWith(AI_Serial)) {
					String serial = barcode.substring(GS1_128_GTIN_Length + AI_Serial.length());
					gtinScanned(gtin, serial);
				} else {
					gtinScanned(gtin);
				}
			} else if (barcode.startsWith(EAN_13_CodeId)) {
				barcode = barcode.substring(EAN_13_CodeId.length());
				gtinScanned(barcode);
			} else {
				serialScanned(barcode);
			}
		}
	}

	private void serialScanned(String serial) {
		// TODO Pobrać GTIN z bazy lub zwrócić błąd
		String gtin = "12345678901234";
		gtinScanned(gtin, serial);
	}

	@Override
	public void onClearActionsClicked(ClearActionsClickedEvent event) {
		model.clearActions();
		view.redrawActions();
	}

	private void gtinScanned(String gtin) {
		Item[] items = ActionsController.this.model.getItems();
		if (items == null)
			return;
		boolean correctGtin = false;
		for (Item item : items)
			if (item.getGtin().equalsIgnoreCase(gtin))
				correctGtin = true;
		if (!correctGtin) {
			view.setMessage("Nieznany GTIN: " + gtin, true);
			GWT.log("Nieznany GTIN: " + gtin);
			return;
		} else {
			view.setMessage("Odczytano GTIN: " + gtin, false);
		}
		try {
			int quantity = view.getQuantity();
			if (view.getOldStatusId() == null) {
				view.setMessage("Nie ustawiono obecnego statusu", true);
				return;
			}
			if (view.getNewStatusId() == null) {
				view.setMessage("Nie ustawiono nowego statusu", true);
				return;
			}
			actionService.move(gtin, Integer.valueOf(view.getOldStatusId()), Integer.valueOf(view.getNewStatusId()),
					quantity, callback);
			ActionRecord action = new ActionRecord(view.getOldStatusValue(), view.getNewStatusValue(), gtin, quantity,
					null);
			model.addAction(action);
			view.clearQuantity();
			view.redrawActions();
		} catch (NumberFormatException ex) {
			view.setMessage("Niepoprawna liczba sztuk", true);
			GWT.log("Niepoprawna liczba sztuk", ex);
		}
	}

	private void gtinScanned(String gtin, String serial) {
		Item[] items = ActionsController.this.model.getItems();
		if (items == null)
			return;
		boolean correctGtin = false;
		for (Item item : items)
			if (item.getGtin().equalsIgnoreCase(gtin))
				correctGtin = true;
		if (!correctGtin) {
			view.setMessage("Nieznany GTIN: " + gtin, true);
			GWT.log("Nieznany GTIN: " + gtin);
			return;
		} else {
			view.setMessage("Odczytano GTIN: " + gtin, false);
		}
		if (onlySerialItems) {
			if (view.getNewStatusId() == null) {
				view.setMessage("Nie ustawiono nowego statusu", true);
				return;
			}
			actionService.move(gtin, Integer.valueOf(view.getNewStatusId()), serial, callback);
			ActionRecord action = new ActionRecord(" ", view.getNewStatusValue(), gtin, 1, serial);
			model.addAction(action);
		} else {
			if (view.getOldStatusId() == null) {
				view.setMessage("Nie ustawiono obecnego statusu", true);
				return;
			}
			if (view.getNewStatusId() == null) {
				view.setMessage("Nie ustawiono nowego statusu", true);
				return;
			}
			actionService.move(gtin, Integer.valueOf(view.getOldStatusId()), Integer.valueOf(view.getNewStatusId()),
					serial, callback);
			ActionRecord action = new ActionRecord(view.getOldStatusValue(), view.getNewStatusValue(), gtin, 1, serial);
			model.addAction(action);
		}
		view.clearQuantity();
		view.redrawActions();
	}
}
