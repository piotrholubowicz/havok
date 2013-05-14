/**
 * 
 */
package pl.poznan.ilim.havok.client.model;

import java.util.ArrayList;
import java.util.Date;

import pl.poznan.ilim.havok.client.ItemsUpdatedEvent;
import pl.poznan.ilim.havok.client.ObservationsUpdatedEvent;
import pl.poznan.ilim.havok.client.StatusesUpdatedEvent;
import pl.poznan.ilim.havok.client.guibuilder.actions.ActionRecord;
import pl.poznan.ilim.havok.client.services.ItemService;
import pl.poznan.ilim.havok.client.services.ItemServiceAsync;
import pl.poznan.ilim.havok.client.services.ObservationService;
import pl.poznan.ilim.havok.client.services.ObservationServiceAsync;
import pl.poznan.ilim.havok.client.services.StatusService;
import pl.poznan.ilim.havok.client.services.StatusServiceAsync;
import pl.poznan.ilim.havok.entities.Item;
import pl.poznan.ilim.havok.entities.Observation;
import pl.poznan.ilim.havok.entities.ResultBean;
import pl.poznan.ilim.havok.entities.Status;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author PHolubow
 * 
 */
public class SimpleModel extends Model {

	public final int UpdateIntervalMs = 5000;

	private Status[] statuses;
	private Item[] items;
	private Observation[] observations;

	private ArrayList<ActionRecord> actions = new ArrayList<ActionRecord>();

	// GWT services
	private ItemServiceAsync itemService;
	private ObservationServiceAsync observationService;
	private StatusServiceAsync statusService;
	private AsyncCallback<ResultBean<Item>> itemsCallback;
	private AsyncCallback<ResultBean<Observation>> observationsCallback;
	private AsyncCallback<ResultBean<Status>> statusesCallback;

	private Date lastTimeItemsUpdated;
	private Date lastTimeObservationsUpdated;
	private Date lastTimeStatusesUpdated;

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

	SimpleModel() {
		itemService = GWT.create(ItemService.class);
		observationService = GWT.create(ObservationService.class);
		statusService = GWT.create(StatusService.class);

		itemsCallback = new AsyncCallback<ResultBean<Item>>() {

			@Override
			public void onSuccess(ResultBean<Item> items) {
				if (items != null) {
					SimpleModel.this.lastTimeItemsUpdated = items.getFetchDate();
					SimpleModel.this.items = items.getResults();
					SimpleModel.this.eventBus.fireEvent(new ItemsUpdatedEvent());
				}
			}

			@Override
			public void onFailure(Throwable ex) {
				GWT.log("Failed to retrieve items from server", ex);
			}
		};

		observationsCallback = new AsyncCallback<ResultBean<Observation>>() {

			@Override
			public void onSuccess(ResultBean<Observation> observations) {
				if (observations != null) {
					SimpleModel.this.lastTimeObservationsUpdated = observations.getFetchDate();
					SimpleModel.this.observations = observations.getResults();
					SimpleModel.this.eventBus.fireEvent(new ObservationsUpdatedEvent());
				}
			}

			@Override
			public void onFailure(Throwable ex) {
				GWT.log("Failed to retrieve observations from server", ex);
			}
		};

		statusesCallback = new AsyncCallback<ResultBean<Status>>() {

			@Override
			public void onSuccess(ResultBean<Status> statuses) {
				if (statuses != null) {
					SimpleModel.this.lastTimeStatusesUpdated = statuses.getFetchDate();
					SimpleModel.this.statuses = statuses.getResults();
					SimpleModel.this.eventBus.fireEvent(new StatusesUpdatedEvent());
				}
			}

			@Override
			public void onFailure(Throwable ex) {
				GWT.log("Failed to retrieve statuses from server", ex);
			}
		};

		Timer timer = new Timer() {

			@Override
			public void run() {
				itemService.retrieveItems(lastTimeItemsUpdated, itemsCallback);
				observationService.retrieveObservations(lastTimeObservationsUpdated,
						observationsCallback);
				statusService.retrieveStatuses(lastTimeStatusesUpdated, statusesCallback);
			}
		};

		itemService.retrieveItems(itemsCallback);
		observationService.retrieveObservations(observationsCallback);
		statusService.retrieveStatuses(statusesCallback);

		timer.scheduleRepeating(UpdateIntervalMs);
	}

}
