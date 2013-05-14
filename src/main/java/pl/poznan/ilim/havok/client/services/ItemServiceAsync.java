/**
 * 
 */
package pl.poznan.ilim.havok.client.services;

import java.util.Date;

import pl.poznan.ilim.havok.entities.Item;
import pl.poznan.ilim.havok.entities.ResultBean;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author PHolubow
 * 
 */
public interface ItemServiceAsync {

	public void createItem(Item item, AsyncCallback<Void> callback);

	public void retrieveItems(AsyncCallback<ResultBean<Item>> callback);

	public void retrieveItems(Date lastUpdate, AsyncCallback<ResultBean<Item>> callback);

	public void updateItem(Item item, AsyncCallback<Void> callback);

	public void deleteItem(Item item, AsyncCallback<Void> callback);

}
