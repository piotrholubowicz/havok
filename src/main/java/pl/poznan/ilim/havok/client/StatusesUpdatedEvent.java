/**
 * 
 */
package pl.poznan.ilim.havok.client;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author PHolubow
 *
 */
public class StatusesUpdatedEvent extends GwtEvent<StatusesUpdatedEventHandler> {

	public static Type<StatusesUpdatedEventHandler> TYPE = new Type<StatusesUpdatedEventHandler>();
	
	/* (non-Javadoc)
	 * @see com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared.EventHandler)
	 */
	@Override
	protected void dispatch(StatusesUpdatedEventHandler handler) {
		handler.onStatusesUpdated(this);
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
	 */
	@Override
	public Type<StatusesUpdatedEventHandler> getAssociatedType() {
		return TYPE;
	}

}
