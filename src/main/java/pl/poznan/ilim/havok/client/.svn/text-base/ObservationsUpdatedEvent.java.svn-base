/**
 * 
 */
package pl.poznan.ilim.havok.client;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author PHolubow
 *
 */
public class ObservationsUpdatedEvent extends GwtEvent<ObservationsUpdatedEventHandler> {

	public static Type<ObservationsUpdatedEventHandler> TYPE = new Type<ObservationsUpdatedEventHandler>();
	
	/* (non-Javadoc)
	 * @see com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared.EventHandler)
	 */
	@Override
	protected void dispatch(ObservationsUpdatedEventHandler handler) {
		handler.onObservationsUpdated(this);
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
	 */
	@Override
	public Type<ObservationsUpdatedEventHandler> getAssociatedType() {
		return TYPE;
	}

}
