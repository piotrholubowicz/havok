/**
 * 
 */
package pl.poznan.ilim.havok.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.gilead.core.PersistentBeanManager;
import net.sf.gilead.core.hibernate.HibernateUtil;
import net.sf.gilead.core.serialization.GwtProxySerialization;
import net.sf.gilead.core.store.stateless.StatelessProxyStore;
import net.sf.gilead.gwt.PersistentRemoteService;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import pl.poznan.ilim.havok.client.services.StatusService;
import pl.poznan.ilim.havok.entities.ResultBean;
import pl.poznan.ilim.havok.entities.Status;
import pl.poznan.ilim.havok.hibernate.HibernateHelper;

/**
 * @author PHolubow
 * 
 */
public class StatusServiceImpl extends PersistentRemoteService implements StatusService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 20110201L;
	private static Logger log = Logger.getLogger(StatusServiceImpl.class);
	private HibernateUtil gileadHibernateUtil = new HibernateUtil();
	private Date lastModificationDate;

	public StatusServiceImpl() {
		gileadHibernateUtil.setSessionFactory(HibernateHelper.getSessionFactory());
		PersistentBeanManager persistentBeanManager = new PersistentBeanManager();
		persistentBeanManager.setPersistenceUtil(gileadHibernateUtil);
		StatelessProxyStore sps = new StatelessProxyStore();
		sps.setProxySerializer(new GwtProxySerialization());
		persistentBeanManager.setProxyStore(sps);
		setBeanManager(persistentBeanManager);
		lastModificationDate = new Date();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pl.poznan.ilim.havok.services.StatusService#createStatus(pl.poznan.ilim.havok.client.entities
	 * .Status)
	 */
	@Override
	public void createStatus(Status status) {
		Session session = gileadHibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(status);
		session.getTransaction().commit();
		lastModificationDate = new Date();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pl.poznan.ilim.havok.services.StatusService#retrieveStatuses()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultBean<Status> retrieveStatuses() {
		Session session = gileadHibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Status> statuses = new ArrayList<Status>(session.createQuery("from Status").list());
		session.getTransaction().commit();
		return new ResultBean<Status>(statuses.toArray(new Status[] {}), new Date());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pl.poznan.ilim.havok.services.StatusService#updateStatus(pl.poznan.ilim.havok.client.entities
	 * .Status)
	 */
	@Override
	public void updateStatus(Status status) {
		Session session = gileadHibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(status);
		session.getTransaction().commit();
		lastModificationDate = new Date();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pl.poznan.ilim.havok.services.StatusService#deleteStatus(pl.poznan.ilim.havok.client.entities
	 * .Status)
	 */
	@Override
	public void deleteStatus(Status status) {
		Session session = gileadHibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(status);
		session.getTransaction().commit();
		lastModificationDate = new Date();
	}

	@Override
	public ResultBean<Status> retrieveStatuses(Date lastUpdate) {
		if (lastUpdate.before(lastModificationDate)) {
			return retrieveStatuses();
		}
		return null;
	}
}
