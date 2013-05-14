/**
 * 
 */
package pl.poznan.ilim.havok.client.services;

import java.util.Date;

import pl.poznan.ilim.havok.entities.Item;
import pl.poznan.ilim.havok.entities.ResultBean;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author PHolubow
 * 
 */
@RemoteServiceRelativePath("itemservice")
public interface ItemService extends RemoteService {

	public void createItem(Item item);

	public ResultBean<Item> retrieveItems();

	public ResultBean<Item> retrieveItems(Date lastUpdate);

	public void updateItem(Item item);

	public void deleteItem(Item item);

}
