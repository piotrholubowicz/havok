/**
 * 
 */
package pl.poznan.ilim.havok.client.model;

import java.util.ArrayList;
import java.util.Date;

import pl.poznan.ilim.havok.client.guibuilder.actions.ActionRecord;
import pl.poznan.ilim.havok.entities.Item;
import pl.poznan.ilim.havok.entities.Observation;
import pl.poznan.ilim.havok.entities.Status;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;

/**
 * @author PHolubow
 * 
 */
public class ClientOnlyModel extends Model {

	private Status[] statuses;
	private Item[] items;
	private Observation[] observations;

	private ArrayList<ActionRecord> actions = new ArrayList<ActionRecord>();

	// events
	private EventBus eventBus = new SimpleEventBus();

	public Status[] getStatuses() {
		return statuses;
	}

	public Item[] getItems() {
		return items;
	}

	public Observation[] getObservations() {
		return observations;
	}

	public ActionRecord[] getActions() {
		return actions.toArray(new ActionRecord[actions.size()]);
	}

	public void clearActions() {
		actions.clear();
	}

	public void addAction(ActionRecord action) {
		actions.add(0, action);
	}

	public EventBus getEventBus() {
		return this.eventBus;
	}

	ClientOnlyModel() {
		statuses = new Status[] { new Status("Produkcja", "#FF0000", null),
				new Status("Magazyn", "#F1BB00", null), new Status("Sprzedaż", "#F000D0", null),
				new Status("Biuro", "#00FF00", null), new Status("Dyspozytornia", "#0000FF", null) };
		for (int i = 0; i < statuses.length; i++)
			statuses[i].setId(i);

		items = new Item[] { new Item("5900005625516", "Kalendarz ścienny Barbara", 19.90f, null),
				new Item("5900005258396", "Młot pneumatyczny, model XT-200", 2099.50f, null),
				new Item("5900003232516", "Koperta jaskrawa", 0.90f, null),
				new Item("5900001111516", "Krzesło obrotowe", 99.99f, null),
				new Item("5900005615516", "Szafa trzydrzwiowa", 129.90f, null),
				new Item("5900005225167", "Kosz", 29.90f, null),
				new Item("5900005232351", "Tablica biała", 229.49f, null),
				new Item("5900005551623", "Krzesło komfort+", 289.0f, null),
				new Item("5900005628901", "Terminal Motorolla", 3500.0f, null) };

		observations = new Observation[] { new Observation(items[0], statuses[1], 5, new Date()),
				new Observation(items[0], statuses[2], 3, new Date()),
				new Observation(items[0], statuses[4], 13, new Date()),
				new Observation(items[1], statuses[0], 7, new Date()),
				new Observation(items[1], statuses[1], 9, new Date()),
				new Observation(items[2], statuses[2], 14, new Date()),
				new Observation(items[2], statuses[3], 2, new Date()),
				new Observation(items[3], statuses[1], 7, new Date()),
				new Observation(items[3], statuses[3], 16, new Date()),
				new Observation(items[3], statuses[4], 8, new Date()),
				new Observation(items[4], statuses[0], 5, new Date()),
				new Observation(items[5], statuses[1], 16, new Date()),
				new Observation(items[5], statuses[2], 1, new Date()),
				new Observation(items[5], statuses[3], 9, new Date()),
				new Observation(items[6], statuses[0], 17, new Date()),
				new Observation(items[6], statuses[1], 7, new Date()),
				new Observation(items[6], statuses[2], 9, new Date()),
				new Observation(items[6], statuses[3], 11, new Date()),
				new Observation(items[6], statuses[4], 6, new Date()),
				new Observation(items[7], statuses[1], 6, new Date()),
				new Observation(items[7], statuses[3], 10, new Date()),
				new Observation(items[8], statuses[0], 7, new Date()),
				new Observation(items[8], statuses[2], 7, new Date()),
				new Observation(items[8], statuses[3], 10, new Date()),
				new Observation(items[8], statuses[4], 12, new Date()) };
		for (int i = 0; i < observations.length; i++)
			observations[i].setId(i);
	}
}
