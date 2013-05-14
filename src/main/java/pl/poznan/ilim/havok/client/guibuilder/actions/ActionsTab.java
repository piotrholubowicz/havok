/**
 * 
 */
package pl.poznan.ilim.havok.client.guibuilder.actions;

import java.util.LinkedHashMap;

import pl.poznan.ilim.havok.client.StatusesUpdatedEvent;
import pl.poznan.ilim.havok.client.StatusesUpdatedEventHandler;
import pl.poznan.ilim.havok.client.model.Model;
import pl.poznan.ilim.havok.entities.Status;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Autofit;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.FormItemInputTransformer;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.layout.VStack;

/**
 * @author PHolubow
 * 
 */
public class ActionsTab extends VLayout implements StatusesUpdatedEventHandler {
	private Model model;

	private TextItem barcodeField;
	private SelectItem oldStatusSelect;
	private SelectItem newStatusSelect;
	private Label alert;
	private ListGrid actionsGrid;
	private IButton clearButton;
	private TextItem quantityField;

	private EventBus eventBus;

	private LinkedHashMap<String, String> statusesMap;
	private SelectItem currentStatusSelect = null;
	private boolean oldStatusSelectEnabled = true;
	private boolean newStatusSelectEnabled = true;

	public EventBus getEventBus() {
		return this.eventBus;
	}

	public ActionsTab(Model model2) {
		super(0);
		this.model = model2;
		this.model.getEventBus().addHandler(StatusesUpdatedEvent.TYPE, this);
		this.eventBus = new SimpleEventBus();

		this.setStyleName("ActionsTab");

		initializeStatusesMap();
		createAlertLabel();
		createBarcodeField();
		createStatusSelect();
		createActionsGrid();
		createClearButton();

		final DynamicForm form = new DynamicForm();
		form.setAutoFocus(true);
		form.setItems(barcodeField, oldStatusSelect, newStatusSelect);

		HLayout layoutLeftAndRight = new HLayout(0);
		VLayout layoutLeft = new VLayout(10);
		Layout layoutRight = createDigitsPanel();

		layoutLeft.addMember(form);
		layoutLeft.addMember(actionsGrid);
		layoutLeft.addMember(clearButton);

		layoutLeftAndRight.addMember(layoutLeft);
		layoutLeftAndRight.addMember(layoutRight);

		this.addMember(alert);
		this.addMember(layoutLeftAndRight);

	}

	private void initializeStatusesMap() {
		Status[] statuses = this.model.getStatuses();
		statusesMap = new LinkedHashMap<String, String>();
		if (statuses != null) {
			for (Status status : statuses) {
				String color = status.getColorCss() == null ? "black" : status.getColorCss();
				statusesMap.put("" + status.getId(), "<span style='color:" + color + ";'>" + status.getName()
						+ "</span>");
			}
		} else {
			GWT.log("Statuses are null");
		}
	}

	private void createAlertLabel() {
		alert = new Label();
		alert.setAlign(Alignment.CENTER);
		alert.setHeight(50);
	}

	private Layout createDigitsPanel() {
		quantityField = new TextItem();
		quantityField.setWidth(200);
		quantityField.setShowTitle(false);
		quantityField.setKeyPressFilter("[0-9.]");
		quantityField.setHint("1");
		quantityField.setShowHintInField(true);
		quantityField.setTextAlign(Alignment.RIGHT);
		quantityField.setInputTransformer(new FormItemInputTransformer() {

			@Override
			public Object transformInput(DynamicForm form, FormItem item, Object value, Object oldValue) {
				if (value == null)
					return null;
				try {
					int val = Integer.parseInt(value.toString());
					GWT.log("Value = " + val);
					if (val == 0)
						return null;
					else
						return "" + val;
				} catch (NumberFormatException ex) {
					return oldValue;
				}
			}
		});

		DynamicForm form = new DynamicForm();
		form.setItems(quantityField);

		int width = 60;
		int height = 60;

		HLayout layout1 = new HLayout(10);
		layout1.setHeight(height);
		layout1.addMember(createButton(width, height, "7"));
		layout1.addMember(createButton(width, height, "8"));
		layout1.addMember(createButton(width, height, "9"));
		HLayout layout2 = new HLayout(10);
		layout2.setHeight(height);
		layout2.addMember(createButton(width, height, "4"));
		layout2.addMember(createButton(width, height, "5"));
		layout2.addMember(createButton(width, height, "6"));
		HLayout layout3 = new HLayout(10);
		layout3.setHeight(height);
		layout3.addMember(createButton(width, height, "1"));
		layout3.addMember(createButton(width, height, "2"));
		layout3.addMember(createButton(width, height, "3"));
		HLayout layout4 = new HLayout(10);
		layout4.setHeight(height + 20);
		layout4.addMember(createButton(2 * width + 10, height, "0"));
		HLayout layout5 = new HLayout(10);
		layout5.setHeight(height);
		layout5.addMember(createClearDigitsButton(95, height, "Wyczyść"));
		layout5.addMember(createOKDigitsButton(95, height, "OK"));

		Layout layout = new VStack(10);
		layout.addMember(form);
		layout.addMember(layout1);
		layout.addMember(layout2);
		layout.addMember(layout3);
		layout.addMember(layout4);
		layout.addMember(layout5);
		return layout;
	}

	private Canvas createOKDigitsButton(int width, int height, String value) {
		IButton button = new IButton(value);
		button.setWidth(width);
		button.setHeight(height);
		button.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				barcodeField.focusInItem();
			}
		});
		return button;
	}

	private IButton createButton(int width, int height, String value) {
		IButton button = new IButton(value);
		button.setWidth(width);
		button.setHeight(height);
		button.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (((IButton) event.getSource()).getTitle().equals("0") && quantityField.getValue() == null)
					return;
				quantityField.setValue((quantityField.getValue() == null ? "" : quantityField.getValue().toString())
						+ ((IButton) event.getSource()).getTitle());
			}
		});
		return button;
	}

	private IButton createClearDigitsButton(int width, int height, String value) {
		IButton button = new IButton(value);
		button.setWidth(width);
		button.setHeight(height);
		button.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				quantityField.clearValue();
			}
		});
		return button;
	}

	private void createClearButton() {
		clearButton = new IButton("Wyczyść");
		clearButton.setWidth(150);
		clearButton.setAlign(Alignment.CENTER);
		clearButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				ActionsTab.this.eventBus.fireEvent(new ClearActionsClickedEvent());
			}
		});
	}

	private void createActionsGrid() {
		actionsGrid = new ListGrid();
		actionsGrid.setWidth(200);
		actionsGrid.setHeight(200);
		actionsGrid.setShowAllRecords(true);
		actionsGrid.setCanResizeFields(true);
		actionsGrid.setAutoFitData(Autofit.HORIZONTAL);
		ListGridField oldStatusField = new ListGridField("oldStatus", "Status 1", 100);
		ListGridField newStatusField = new ListGridField("newStatus", "Status 2", 100);
		ListGridField gtinField = new ListGridField("gtin", "GTIN", 100);
		ListGridField serialField = new ListGridField("serial", "Serial", 100);
		ListGridField quantityField = new ListGridField("quantity", "Sztuk", 50);
		actionsGrid.setFields(oldStatusField, newStatusField, gtinField, serialField, quantityField);
		actionsGrid.setData(model.getActions());
	}

	private void createStatusSelect() {
		oldStatusSelect = new SelectItem();
		oldStatusSelect.setTitle("Poprzedni status");
		oldStatusSelect.setValueMap(statusesMap);

		newStatusSelect = new SelectItem();
		newStatusSelect.setTitle("Nowy status");
		newStatusSelect.setValueMap(statusesMap);

		currentStatusSelect = oldStatusSelect;
	}

	private void updateStatusSelect() {
		oldStatusSelect.setValueMap(statusesMap);
		newStatusSelect.setValueMap(statusesMap);
	}

	private void createBarcodeField() {
		barcodeField = new TextItem("barcode", "Kod kreskowy");
		barcodeField.setWidth(200);
		barcodeField.setHint("<nobr>Ustaw tutaj kursor<br>aby skanować kody kreskowe.</nobr>");
		// barcodeField.setKeyPressFilter("[0-9.]");
		barcodeField.addChangedHandler(new ChangedHandler() {

			@Override
			public void onChanged(ChangedEvent event) {
				if (barcodeField.getValue() != null && barcodeField.getValue().toString().endsWith("$")) {
					String barcode = barcodeField.getValue().toString();
					barcodeField.clearValue();
					ActionsTab.this.eventBus.fireEvent(new BarcodeScannedEvent(barcode));
				}
			};
		});
	}

	@Override
	public void onStatusesUpdated(StatusesUpdatedEvent event) {
		initializeStatusesMap();
		updateStatusSelect();
	}

	public String getOldStatusValue() {
		return (String) oldStatusSelect.getDisplayValue();
	}

	public String getNewStatusValue() {
		return (String) newStatusSelect.getDisplayValue();
	}

	public String getOldStatusId() {
		return (String) oldStatusSelect.getValue();
	}

	public String getNewStatusId() {
		return (String) newStatusSelect.getValue();
	}

	public int getQuantity() throws NumberFormatException {
		if (quantityField.getValue() == null)
			return 1;
		return Integer.parseInt((String) quantityField.getValue());
	}

	public void clearQuantity() {
		quantityField.clearValue();
	}

	public void setMessage(String message, boolean isAlert) {
		if (message == null)
			message = "";
		if (isAlert) {
			alert.setContents("<span style='color:red'>" + message + "</span>");
		} else {
			alert.setContents("<span style='color:black'>" + message + "</span>");
		}
	}

	public void redrawActions() {
		this.actionsGrid.setData(model.getActions());
		this.actionsGrid.markForRedraw();
	}

	public void setCurrentStatus(int statusId) throws Exception {
		if (id == null) {
			currentStatusSelect.clearValue();
		} else {
			if (statusesMap.containsKey(id)) {
				currentStatusSelect.setValue(id);
			} else {
				throw new Exception("Unknown value");
			}
		}
		if (currentStatusSelect == oldStatusSelect && newStatusSelectEnabled) {
			setCurrentStatus(newStatusSelect);
		} else if (currentStatusSelect == newStatusSelect && oldStatusSelectEnabled) {
			setCurrentStatus(oldStatusSelect);
		}
	}

	private void setCurrentStatus(SelectItem newStatus) {
		currentStatusSelect = newStatus;
//		currentStatusSelect.s
	}

}
