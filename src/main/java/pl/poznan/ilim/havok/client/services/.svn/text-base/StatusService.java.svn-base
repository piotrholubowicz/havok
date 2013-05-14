/**
 * 
 */
package pl.poznan.ilim.havok.client.services;

import java.util.Date;

import pl.poznan.ilim.havok.entities.ResultBean;
import pl.poznan.ilim.havok.entities.Status;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author PHolubow
 * 
 */
@RemoteServiceRelativePath("statusservice")
public interface StatusService extends RemoteService {

	public void createStatus(Status status);

	public ResultBean<Status> retrieveStatuses();

	public ResultBean<Status> retrieveStatuses(Date lastUpdate);

	public void updateStatus(Status status);

	public void deleteStatus(Status status);

}
