/**
 * 
 */
package pl.poznan.ilim.havok.client.guibuilder.charts;

import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.visualizations.PieChart;
import com.google.gwt.visualization.client.visualizations.PieChart.Options;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Window;

/**
 * @author piotrek
 * 
 */
public class ChartWindow extends Window {

	private ChartWindow() {
		super();
		setWidth(100);
		setHeight(100);
		setAutoSize(true);
		setAutoCenter(true);
		setAlign(Alignment.CENTER);
		setAlign(VerticalAlignment.CENTER);
		setTitle("Wykres");
		setIsModal(true);
		setShowModalMask(true);
		setShowMinimizeButton(false);
		setDismissOnOutsideClick(true);
	}

	public ChartWindow(AbstractDataTable table, String title) {
		this();

		 Options options = Options.create();
		 options.setWidth(700);
		 options.setHeight(500);
		 options.set3D(true);
		 options.setTitle(title);
		 
		 // Używamy starej (obecnie oznaczonej jako deprecated) wersji PieCharts
		 // Dokumentacja znajduje się tu: 
		 //   http://code.google.com/apis/visualization/documentation/gallery/piechart_old.html
		 // Nowa wersja (1.1) i dokumentacja natomiast są tu:
		 //   http://code.google.com/intl/pl-PL/apis/visualization/documentation/gallery/piechart.html
		 // W repozytorium mavena nie znalazlem jednak najnowszej wersji pod GWT; 
		 // 1.0.2 z której korzystamy jest tą najbardziej aktualną
		 
		 // W związku z powyższym, ustawianie opcji dziala w ten sposob
		 options.setOption("legendFontSize", 12);

		 PieChart pieChart = new PieChart(table, options);
		 addItem(pieChart);
	}

}
