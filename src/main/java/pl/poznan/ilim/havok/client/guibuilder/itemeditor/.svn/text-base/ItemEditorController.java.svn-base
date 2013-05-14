package pl.poznan.ilim.havok.client.guibuilder.itemeditor;

import pl.poznan.ilim.havok.client.ItemsUpdatedEvent;
import pl.poznan.ilim.havok.client.ItemsUpdatedEventHandler;
import pl.poznan.ilim.havok.client.guibuilder.GridEditorProvider;
import pl.poznan.ilim.havok.client.guibuilder.IViewBuilder;
import pl.poznan.ilim.havok.client.model.Model;
import pl.poznan.ilim.havok.entities.Item;

import com.smartgwt.client.widgets.layout.Layout;

public class ItemEditorController implements IViewBuilder, ItemsUpdatedEventHandler {
	private static final String VIEW_NAME = "Przedmioty";
	private ItemsListGrid itemsGrid;
	private Model dataSource;

	public ItemEditorController(Model model) {
		this.dataSource = model;
		this.dataSource.getEventBus().addHandler(ItemsUpdatedEvent.TYPE, this);
	}

	@Override
	public String getViewName() {
		return ItemEditorController.VIEW_NAME;
	}
	
	@Override
	public String getIconName() {
		return null;
	}

	@Override
	public Layout createView() {
		this.itemsGrid = new ItemsListGrid();
		this.itemsGrid.setData(this.getItems());
		GridEditorProvider editingLayoutProvider = new GridEditorProvider(this.itemsGrid,
				this.getViewName());
		return editingLayoutProvider.getLayout();
	}

	@Override
	public void onItemsUpdated(ItemsUpdatedEvent event) {
		if (this.itemsGrid != null) {
			this.itemsGrid.setData(this.getItems());
		}
	}

	private ItemRecord[] getItems() {
		Item[] items = this.dataSource.getItems();
		if (items != null) {
			ItemRecord[] records = new ItemRecord[items.length];
			for (int i = 0; i < items.length; i++) {
				records[i] = new ItemRecord(items[i]);
			}
			return records;
		}
		return null;
	}
}
