/**
 * 
 */
package pl.poznan.ilim.havok.client.services;

import java.util.Date;

import pl.poznan.ilim.havok.entities.ResultBean;
import pl.poznan.ilim.havok.entities.Status;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author PHolubow
 * 
 */
public interface StatusServiceAsync {

	public void createStatus(Status status, AsyncCallback<Void> callback);

	public void retrieveStatuses(AsyncCallback<ResultBean<Status>> callback);

	public void retrieveStatuses(Date lastUpdate, AsyncCallback<ResultBean<Status>> callback);

	public void updateStatus(Status status, AsyncCallback<Void> callback);

	public void deleteStatus(Status status, AsyncCallback<Void> callback);

}
