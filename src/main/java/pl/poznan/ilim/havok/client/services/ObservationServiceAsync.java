/**
 * 
 */
package pl.poznan.ilim.havok.client.services;

import java.util.Date;

import pl.poznan.ilim.havok.entities.Observation;
import pl.poznan.ilim.havok.entities.ResultBean;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author PHolubow
 * 
 */
public interface ObservationServiceAsync {

	public void createObservation(Observation observation, AsyncCallback<Void> callback);

	public void retrieveObservations(AsyncCallback<ResultBean<Observation>> callback);

	public void retrieveObservations(Date lastUpdate, AsyncCallback<ResultBean<Observation>> callback);

	public void updateObservation(Observation observation, AsyncCallback<Void> callback);

	public void deleteObservation(Observation observation, AsyncCallback<Void> callback);

}
