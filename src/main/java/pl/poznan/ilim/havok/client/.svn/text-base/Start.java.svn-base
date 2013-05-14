package pl.poznan.ilim.havok.client;

import pl.poznan.ilim.havok.client.guibuilder.IViewBuilder;
import pl.poznan.ilim.havok.client.guibuilder.Margins;
import pl.poznan.ilim.havok.client.guibuilder.StyleNames;
import pl.poznan.ilim.havok.client.guibuilder.actions.ActionsController;
import pl.poznan.ilim.havok.client.guibuilder.itemeditor.ItemEditorController;
import pl.poznan.ilim.havok.client.guibuilder.observations.ObservationsController;
import pl.poznan.ilim.havok.client.guibuilder.statuseditor.StatusEditorController;
import pl.poznan.ilim.havok.client.model.Model;

import com.google.gwt.core.client.EntryPoint;
import com.smartgwt.client.types.Side;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Start implements EntryPoint {
	private Model model;

	@Override
	public void onModuleLoad() {
		model = Model.create(true);
		ObservationsController observations = new ObservationsController(this.model);
		ActionsController actionsController = new ActionsController(model);
//		ChartsController charts = new ChartsController();
		StatusEditorController statusEditor = new StatusEditorController(this.model);
		ItemEditorController itemEditor = new ItemEditorController(this.model);

		TabSet tabsContainer = new TabSet();
		tabsContainer.setTabBarPosition(Side.TOP);
		tabsContainer.setTabBarThickness(32);
		tabsContainer.setWidth100();  
		tabsContainer.setHeight100();

		this.addTabPage(actionsController, tabsContainer);
		this.addTabPage(observations, tabsContainer);
//		this.addTabPage(charts, tabsContainer);
		this.addEditorTabPage(statusEditor, itemEditor, tabsContainer);

		Label title = new Label();
		title.setContents("HAVOK");
		title.setStyleName(StyleNames.TITLE);

		HLayout titleBar = new HLayout();
		titleBar.setHeight("10%");
		titleBar.setMinHeight(60);
		titleBar.setWidth100();
		titleBar.addMember(title);

		VLayout tabsPanelArea = new VLayout();
		tabsPanelArea.setWidth("80%");
		tabsPanelArea.setHeight("90%");
		tabsPanelArea.setLeft("10%");
		tabsPanelArea.setTop("5%");
		tabsPanelArea.addMember(titleBar);
		tabsPanelArea.addMember(tabsContainer);

		tabsPanelArea.draw();
	}

	private void addTabPage(IViewBuilder builder, TabSet tabsContainer) {
		Tab tab = new Tab(builder.getViewName());
		Layout layout = builder.createView();
		layout.setLayoutMargin(Margins.CONTAINER_NARROW);
		tab.setIcon(builder.getIconName());
		tab.setPane(layout);
		tabsContainer.addTab(tab);
	}

	private void addEditorTabPage(IViewBuilder statusEditor, IViewBuilder itemEditor,
			TabSet tabsContainer) {
		HLayout layout = new HLayout();
		layout.setLayoutMargin(Margins.CONTAINER_NARROW);
		layout.setMembersMargin(Margins.COMPONENTS_WIDE);
		layout.addMember(statusEditor.createView());
		layout.addMember(itemEditor.createView());
		Tab tab = new Tab("Edycja danych");
		tab.setIcon("tab-edit.png");
		tab.setPane(layout);
		tabsContainer.addTab(tab);
	}
}
