package pl.poznan.ilim.havok.client.guibuilder;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * Provides method creating simple layout for objects editing: objects grid set
 * for easy editing, together with fixed title.
 * @author MKorecki
 */
public class GridEditorProvider {
	private static final String TEXT_BUTTON_NEW = "Utwórz nowy";
	private static final int TITLE_LABEL_HEIGHT = 25;
	
	private ListGrid grid;
	private String title;
	
	public GridEditorProvider(ListGrid grid, String title) {
		this.grid = grid;
		this.title = title;
	}

	public Layout getLayout() {
		this.grid.setShowGridSummary(false);
		this.grid.setShowGroupSummary(false);
		this.grid.setCanEdit(true);		
		this.grid.setEditEvent(ListGridEditEvent.CLICK);
		this.grid.setCanRemoveRecords(true);
		
		Label editorTitle = new Label(this.title);
		editorTitle.setStyleName(StyleNames.HEADER_MEDIUM);
		editorTitle.setHeight(GridEditorProvider.TITLE_LABEL_HEIGHT);
		
		Button insertNewRecord = new Button(GridEditorProvider.TEXT_BUTTON_NEW);
		insertNewRecord.addClickHandler(new NewRecordClickHandler());
		
		HLayout rightAlignedButtonsPanel = new HLayout(Margins.COMPONENTS_WIDE);
		rightAlignedButtonsPanel.setAlign(Alignment.RIGHT);
		rightAlignedButtonsPanel.addMember(insertNewRecord);
		
		HLayout headerPanel = new HLayout();
		headerPanel.addMember(editorTitle);
		headerPanel.addMember(rightAlignedButtonsPanel);

		VLayout layout = new VLayout(Margins.COMPONENTS_NARROW);
		layout.addMember(headerPanel);
		layout.addMember(this.grid);
		return layout;
	}
	
	private class NewRecordClickHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			GridEditorProvider.this.grid.startEditingNew();
		}		
	}
}
