package pl.poznan.ilim.havok.client.guibuilder.charts;

import pl.poznan.ilim.havok.client.model.Model;
import pl.poznan.ilim.havok.entities.Item;
import pl.poznan.ilim.havok.entities.Observation;
import pl.poznan.ilim.havok.entities.Status;

import com.google.gwt.core.client.GWT;
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.visualizations.PieChart;

public class ChartsController {

	private Model model;

	public ChartsController(Model model2) {
		this.model = model2;

		// Create a callback to be called when the visualization API
		// has been loaded.
		Runnable onLoadCallback = new Runnable() {
			public void run() {
				GWT.log("Visualization API loaded");
			}
		};

		// Load the visualization api, passing the onLoadCallback to be called
		// when loading is done.
		VisualizationUtils.loadVisualizationApi(onLoadCallback, PieChart.PACKAGE);

	}

	public ChartWindow createGtinChartWindow(String gtin) throws Exception {
		Item item = null;
		for (Item item2 : model.getItems()) {
			if (item2.getGtin().equalsIgnoreCase(gtin))
				item = item2;
		}
		if (item == null)
			throw new Exception("Unknown GTIN");
		String title = "Statusy dla " + item.getDescription() + " (" + item.getGtin() + ")";
		return new ChartWindow(createGtinTable(item), title);
	}

	public ChartWindow createStatusChartWindow(int statusId) throws Exception {
		Status status = null;
		for (Status status2 : model.getStatuses()) {
			if (status2.getId() == statusId)
				status = status2;
		}
		if (status == null)
			throw new Exception("Unknown status");
		String title = "Obiekty o statusie " + status.getName();
		return new ChartWindow(createStatusTable(status), title);
	}

	private AbstractDataTable createGtinTable(Item item) {
		DataTable data = DataTable.create();
		data.addColumn(ColumnType.STRING, "Status");
		data.addColumn(ColumnType.NUMBER, "Liczba sztuk");
		Observation[] observations = model.getObservations();
		for (Observation observation : observations) {
			if (observation.getItem().getGtin().equalsIgnoreCase(item.getGtin())) {
				int idx = data.addRow();
				data.setValue(idx, 0, observation.getStatus().getName());
				data.setValue(idx, 1, observation.getQuantity());
			}
		}
		return data;
	}

	private AbstractDataTable createStatusTable(Status status) {
		DataTable data = DataTable.create();
		data.addColumn(ColumnType.STRING, "Status");
		data.addColumn(ColumnType.NUMBER, "Liczba sztuk");
		Observation[] observations = model.getObservations();
		for (Observation observation : observations) {
			if (observation.getStatus().getId() == status.getId()) {
				int idx = data.addRow();
				data.setValue(idx, 0, observation.getItem().getDescription() + " ("
						+ observation.getItem().getGtin() + ")");
				data.setValue(idx, 1, observation.getQuantity());
			}
		}
		return data;
	}
}
