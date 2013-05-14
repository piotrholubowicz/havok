/**
 * 
 */
package pl.poznan.ilim.havok.client.guibuilder.actions;

import com.google.gwt.event.shared.GwtEvent;


/**
 * @author PHolubow
 * 
 */
public class ClearActionsClickedEvent extends GwtEvent<ActionsEventHandler> {

	public static Type<ActionsEventHandler> TYPE = new Type<ActionsEventHandler>();
	
	public ClearActionsClickedEvent() {
		super();
	}

	@Override
	protected void dispatch(ActionsEventHandler handler) {
		handler.onClearActionsClicked(this);
	}

	@Override
	public Type<ActionsEventHandler> getAssociatedType() {
		return TYPE;
	}

}
