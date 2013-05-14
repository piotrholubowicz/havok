package pl.poznan.ilim.havok.client.guibuilder.statuseditor;

import pl.poznan.ilim.havok.client.StatusesUpdatedEvent;
import pl.poznan.ilim.havok.client.StatusesUpdatedEventHandler;
import pl.poznan.ilim.havok.client.guibuilder.GridEditorProvider;
import pl.poznan.ilim.havok.client.guibuilder.IViewBuilder;
import pl.poznan.ilim.havok.client.model.Model;
import pl.poznan.ilim.havok.entities.Status;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.smartgwt.client.widgets.grid.events.EditCompleteEvent;
import com.smartgwt.client.widgets.grid.events.EditCompleteHandler;
import com.smartgwt.client.widgets.layout.Layout;

public class StatusEditorController implements IsSerializable, IViewBuilder, StatusesUpdatedEventHandler {
	private static final String VIEW_NAME = "Statusy";
	private Model dataSource;
	private StatusesListGrid statusesGrid;
	
	public StatusEditorController(Model model) {
		this.dataSource = model;
		this.dataSource.getEventBus().addHandler(StatusesUpdatedEvent.TYPE, this);
	}
	
	@Override
	public String getViewName() {
		return StatusEditorController.VIEW_NAME;
	}

	@Override
	public String getIconName() {
		return null;
	}

	@Override
	public Layout createView() {
		this.statusesGrid = new StatusesListGrid();
		this.statusesGrid.setData(this.getStatuses());
		this.statusesGrid.addEditCompleteHandler(new EditCompleteHandler() {
			@Override
			public void onEditComplete(EditCompleteEvent event) {
				StatusesListGrid source = (StatusesListGrid) event.getSource();
				source.redraw();
			}
		});
		GridEditorProvider editingLayoutProvider = new GridEditorProvider(statusesGrid,
				this.getViewName());
		return editingLayoutProvider.getLayout();
	}

	@Override
	public void onStatusesUpdated(StatusesUpdatedEvent event) {
		if (this.statusesGrid != null) {
			this.statusesGrid.setData(this.getStatuses());
		}
		
	}
	
	private StatusRecord[] getStatuses() {
		Status[] statuses = this.dataSource.getStatuses();
		if (statuses != null) {
			StatusRecord[] records = new StatusRecord[statuses.length];
			for (int i = 0; i < statuses.length; i++) {
				records[i] = new StatusRecord(statuses[i]);
			}
			return records;
		}
		return null;
	}	
}
