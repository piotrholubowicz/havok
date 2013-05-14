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

import pl.poznan.ilim.havok.client.services.ObservationService;
import pl.poznan.ilim.havok.entities.Observation;
import pl.poznan.ilim.havok.entities.ResultBean;
import pl.poznan.ilim.havok.hibernate.HibernateHelper;

/**
 * @author PHolubow
 * 
 */
public class ObservationServiceImpl extends PersistentRemoteService implements ObservationService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 20110201L;
	private static Logger log = Logger.getLogger(ObservationServiceImpl.class);
	private HibernateUtil gileadHibernateUtil = new HibernateUtil();
	private Date lastModificationDate;

	public ObservationServiceImpl() {
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
	 * pl.poznan.ilim.havok.services.ObservationService#createObservation(pl.poznan.ilim.havok.client
	 * .entities.Observation)
	 */
	@Override
	public void createObservation(Observation observation) {
		Session session = gileadHibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(observation);
		session.getTransaction().commit();
		lastModificationDate = new Date();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pl.poznan.ilim.havok.services.ObservationService#retrieveObservations()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultBean<Observation> retrieveObservations() {
		Session session = gileadHibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Observation> observations = new ArrayList<Observation>(session.createQuery(
				"from Observation").list());
		session.getTransaction().commit();
		return new ResultBean<Observation>(observations.toArray(new Observation[] {}), new Date());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pl.poznan.ilim.havok.services.ObservationService#updateObservation(pl.poznan.ilim.havok.client
	 * .entities.Observation)
	 */
	@Override
	public void updateObservation(Observation observation) {
		Session session = gileadHibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(observation);
		session.getTransaction().commit();
		lastModificationDate = new Date();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pl.poznan.ilim.havok.services.ObservationService#deleteObservation(pl.poznan.ilim.havok.client
	 * .entities.Observation)
	 */
	@Override
	public void deleteObservation(Observation observation) {
		Session session = gileadHibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(observation);
		session.getTransaction().commit();
		lastModificationDate = new Date();
	}

	@Override
	public ResultBean<Observation> retrieveObservations(Date lastUpdate) {
		if (ActionServiceImpl.lastModificationDate.after(lastModificationDate)) {
			lastModificationDate = ActionServiceImpl.lastModificationDate;
		}
		if (lastUpdate.before(lastModificationDate)) {
			return retrieveObservations();
		}
		return null;
	}
}
