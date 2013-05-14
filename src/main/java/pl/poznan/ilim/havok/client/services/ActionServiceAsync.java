/**
 * 
 */
package pl.poznan.ilim.havok.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author PHolubow
 * 
 */
public interface ActionServiceAsync {

	public void move(String itemId, Integer oldStatusId, Integer newStatusId, int quantity, AsyncCallback<Void> callback);

	public void move(String itemId, Integer oldStatusId, Integer newStatusId, String serial, AsyncCallback<Void> callback);

	public void move(String itemId, Integer newStatusId, String serial, AsyncCallback<Void> callback);

}
