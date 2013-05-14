/**
 * 
 */
package pl.poznan.ilim.havok.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author PHolubow
 * 
 */
@RemoteServiceRelativePath("actionservice")
public interface ActionService extends RemoteService {

	public void move(String itemId, Integer oldStatusId, Integer newStatusId, int quantity);

	public void move(String itemId, Integer oldStatusId, Integer newStatusId, String serial);

	public void move(String itemId, Integer newStatusId, String serial);
}
