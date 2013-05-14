/**
 * 
 */
package pl.poznan.ilim.havok.client;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author PHolubow
 *
 */
public class ItemsUpdatedEvent extends GwtEvent<ItemsUpdatedEventHandler> {

	public static Type<ItemsUpdatedEventHandler> TYPE = new Type<ItemsUpdatedEventHandler>();
	
	/* (non-Javadoc)
	 * @see com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared.EventHandler)
	 */
	@Override
	protected void dispatch(ItemsUpdatedEventHandler handler) {
		handler.onItemsUpdated(this);
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
	 */
	@Override
	public Type<ItemsUpdatedEventHandler> getAssociatedType() {
		return TYPE;
	}

}
