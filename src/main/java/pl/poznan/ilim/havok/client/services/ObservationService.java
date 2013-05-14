/**
 * 
 */
package pl.poznan.ilim.havok.client.services;

import java.util.Date;

import pl.poznan.ilim.havok.entities.Observation;
import pl.poznan.ilim.havok.entities.ResultBean;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author PHolubow
 * 
 */
@RemoteServiceRelativePath("observationservice")
public interface ObservationService extends RemoteService {

	public void createObservation(Observation observation);

	public ResultBean<Observation> retrieveObservations();

	public ResultBean<Observation> retrieveObservations(Date lastUpdate);

	public void updateObservation(Observation observation);

	public void deleteObservation(Observation observation);

}
