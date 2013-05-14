package pl.poznan.ilim.havok.client.guibuilder.observations;

import pl.poznan.ilim.havok.client.ObservationsUpdatedEvent;
import pl.poznan.ilim.havok.client.ObservationsUpdatedEventHandler;
import pl.poznan.ilim.havok.client.guibuilder.IViewBuilder;
import pl.poznan.ilim.havok.client.guibuilder.StyleNames;
import pl.poznan.ilim.havok.client.guibuilder.charts.ChartWindow;
import pl.poznan.ilim.havok.client.guibuilder.charts.ChartsController;
import pl.poznan.ilim.havok.client.model.Model;
import pl.poznan.ilim.havok.entities.Observation;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.VLayout;

public class ObservationsController implements IsSerializable, IViewBuilder,
		ObservationsUpdatedEventHandler {
	private static final String VIEW_NAME = "Obserwacje obiektów";
	private static final String LABEL_HEADER = "Grupuj obserwacje:";
	private static final String BUTTON_GROUP_GTIN_NAME = "Numerami GTIN";
	private static final String BUTTON_GROUP_STATUS_NAME = "Statusami";
	private Model dataSource;
	private ObservationsListGrid observationsGrid;

	private static ChartsController chartsController;
	
	public ObservationsController(Model model) {
		this.dataSource = model;
		this.dataSource.getEventBus().addHandler(ObservationsUpdatedEvent.TYPE, this);
		chartsController = new ChartsController(model);
	}

	@Override
	public String getViewName() {
		return ObservationsController.VIEW_NAME;
	}
	
	@Override
	public String getIconName() {
		return "tab-observations.png";
	}

	@Override
	public Layout createView() {
		this.observationsGrid = new ObservationsListGrid();
		this.observationsGrid.setData(this.getObservations());
		VLayout headerPanel = this.createHeaderPanel();
		VLayout mainContainer = new VLayout();
		mainContainer.setWidth100();
		mainContainer.setHeight100();
		mainContainer.addMember(headerPanel);
		mainContainer.addMember(this.observationsGrid);

		return mainContainer;
	}

	@Override
	public void onObservationsUpdated(ObservationsUpdatedEvent event) {
		if (this.observationsGrid != null) {
			this.observationsGrid.setData(this.getObservations());
		}
	}

	private ObservationRecord[] getObservations() {
		Observation[] observations = this.dataSource.getObservations();
		if (observations != null) {
			ObservationRecord[] records = new ObservationRecord[observations.length];
			for (int i = 0; i < observations.length; i++) {
				records[i] = new ObservationRecord(observations[i]);
			}
			return records;
		}
		return null;
	}

	private VLayout createHeaderPanel() {
		Button btnGroupByGtin = new Button(ObservationsController.BUTTON_GROUP_GTIN_NAME);
		Button btnGroupByStatus = new Button(ObservationsController.BUTTON_GROUP_STATUS_NAME);
		btnGroupByGtin.addClickHandler(new ButtonGroupByClickHandler(this.observationsGrid,
				btnGroupByStatus, ObservationRecord.ATTRIBUTE_GTIN));
		btnGroupByStatus.addClickHandler(new ButtonGroupByClickHandler(this.observationsGrid,
				btnGroupByGtin, ObservationRecord.ATTRIBUTE_STATUS));
		// Default grouping is by GTIN, disable its button at start
		btnGroupByGtin.disable();

		Label title = new Label(ObservationsController.LABEL_HEADER);
		title.setWidth100();
		title.setHeight(30);
		title.setStyleName(StyleNames.HEADER_MEDIUM);

		HLayout buttonsTitleBar = new HLayout();
		buttonsTitleBar.setWidth100();
		buttonsTitleBar.setHeight(30);
		buttonsTitleBar.addMember(btnGroupByGtin);
		buttonsTitleBar.addMember(btnGroupByStatus);

		VLayout layout = new VLayout();
		layout.setWidth100();
		layout.addMember(title);
		layout.addMember(buttonsTitleBar);

		return layout;
	}

	private class ButtonGroupByClickHandler implements ClickHandler {
		private ListGrid list;
		private Button otherToggler;
		private String groupByFieldName;

		public ButtonGroupByClickHandler(ListGrid list, Button toggler, String groupByFieldName) {
			this.list = list;
			this.otherToggler = toggler;
			this.groupByFieldName = groupByFieldName;
		}

		@Override
		public void onClick(ClickEvent event) {
			Button source = (Button) event.getSource();
			source.disable();
			this.otherToggler.enable();
			this.list.groupBy(this.groupByFieldName);
			this.list.getGroupTree().openAll();
		}
	}
	
	public static native void exportStaticMethod() /*-{
	$wnd.gtinchart =
	$entry(@pl.poznan.ilim.havok.client.guibuilder.observations.ObservationsController::displayGtinChart(Ljava/lang/String;));
	$wnd.statuschart =
	$entry(@pl.poznan.ilim.havok.client.guibuilder.observations.ObservationsController::displayStatusChart(I));
	}-*/;

	public static void displayGtinChart(String gtin) {
		if (chartsController == null) {
			GWT.log("Charts controller is null");
			return;
		}
		try {
			ChartWindow chart = chartsController.createGtinChartWindow(gtin);
			chart.show();
		} catch (Exception e) {
			SC.warn("Nieznany GTIN " + gtin);
			GWT.log("Nieznany GTIN " + gtin);
		}
	}

	public static void displayStatusChart(int statusId) {
		if (chartsController == null) {
			GWT.log("Charts controller is null");
			return;
		}
		try {
			ChartWindow chart = chartsController.createStatusChartWindow(statusId);
			chart.show();
		} catch (Exception e) {
			SC.warn("Nieznany status " + statusId);
			GWT.log("Nieznany status " + statusId);
		}
	}


}
