/**
 * 
 */
package pl.poznan.ilim.havok.client.model;

import pl.poznan.ilim.havok.client.guibuilder.actions.ActionRecord;
import pl.poznan.ilim.havok.entities.Item;
import pl.poznan.ilim.havok.entities.Observation;
import pl.poznan.ilim.havok.entities.Status;

import com.google.gwt.event.shared.EventBus;

/**
 * @author PHolubow
 * 
 */
public abstract class Model {

	public static Model create(boolean serverConnection) {
		if (serverConnection)
			return new SimpleModel();
		else
			return new ClientOnlyModel();
	}

	public abstract Status[] getStatuses();

	public abstract Item[] getItems();

	public abstract Observation[] getObservations();

	public abstract ActionRecord[] getActions();

	public abstract void clearActions();

	public abstract void addAction(ActionRecord action);

	public abstract EventBus getEventBus();

}
