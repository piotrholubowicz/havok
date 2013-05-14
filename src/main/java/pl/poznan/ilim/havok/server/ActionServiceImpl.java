/**
 * 
 */
package pl.poznan.ilim.havok.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import pl.poznan.ilim.havok.client.services.ActionService;
import pl.poznan.ilim.havok.entities.Item;
import pl.poznan.ilim.havok.entities.Observation;
import pl.poznan.ilim.havok.entities.Status;
import pl.poznan.ilim.havok.hibernate.HibernateHelper;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author PHolubow
 * 
 */
public class ActionServiceImpl extends RemoteServiceServlet implements ActionService {

	private static final long serialVersionUID = 20110201L;

	private final static Logger log = Logger.getLogger(ActionServiceImpl.class);
	
	public static Date lastModificationDate = new Date();

	@Override
	public void move(String itemId, Integer oldStatusId, Integer newStatusId, int quantity) {
		Observation firstObservation = null;
		Observation secondObservation = null;

		log.debug(String.format("Move %s from %d to %d, quantity %d", itemId, oldStatusId,
				newStatusId, quantity));

		Session session = HibernateHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		if (oldStatusId != 0) {
			firstObservation = getFirstGtinObservation(itemId, oldStatusId, quantity);
		}

		if (oldStatusId == 0 || firstObservation != null) {
			if (newStatusId != 0) {
				secondObservation = getSecondGtinObservation(itemId, newStatusId);
			}
			if (newStatusId == 0 || secondObservation != null) {
				if (firstObservation != null) {
					updateObvservationQuantity(firstObservation, -quantity);
				}
				if (secondObservation != null) {
					updateObvservationQuantity(secondObservation, quantity);
				}
				log.debug("Move completed");
			}
		}
		lastModificationDate = new Date();
		session.getTransaction().commit();
	}

	@Override
	public void move(String itemId, Integer oldStatusId, Integer newStatusId, String serial) {
		log.debug(String.format("Move %s from %d to %d, serial %s", itemId, oldStatusId,
				newStatusId, serial));

		Session session = HibernateHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		List<Observation> observations = getSerialObservations(itemId, serial);
		if (oldStatusId == 0) {
			if (observations != null && observations.size() > 0) {
				log.error("More than zero observations for itemId=" + itemId + ", serial=" + serial
						+ " and status=" + oldStatusId);
			} else if (newStatusId != 0) {
				Item item = getItem(itemId);
				Status newStatus = getStatus(newStatusId);
				if (item == null || newStatus == null) {
					log.error("Could not update observation for itemId=" + itemId + ", serial="
							+ serial + " and status=" + newStatusId);
				} else {
					Observation observation = new Observation(item, newStatus, 1, new Date());
					observation.setSerial(serial);
					session.save(observation);
					log.debug("New observation added for itemId=" + itemId + " and statusId="
							+ newStatus.getId() + " and serial=" + serial);
				}
			} else {
				log.debug("No move required");
			}
		} else {
			if (observations == null || observations.size() != 1) {
				log.error("No observations for itemId=" + itemId + ", serial=" + serial);
			} else {
				Observation observation = observations.get(0);
				if (observation.getStatus().getId().intValue() != oldStatusId.intValue()) {
					log.error("Wrong statusId for itemId=" + itemId + ", serial=" + serial
							+ ", statusId=" + oldStatusId);
				} else if (newStatusId == 0) {
					session.delete(observation);
					log.debug("Observation deleted for itemId=" + itemId + " and serial=" + serial);
				} else {
					Status newStatus = getStatus(newStatusId);
					if (newStatus == null) {
						log.error("Could not update observation for itemId=" + itemId + ", serial="
								+ serial + " and status=" + newStatusId);
					} else {
						observation.setStatus(newStatus);
						session.update(observation);
						log.debug("Move completed for itemId=" + itemId + " and statusId="
								+ newStatus + " and serial=" + serial);
					}
				}
			}
		}
		lastModificationDate = new Date();
		session.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	private Item getItem(String itemId) {
		Item result;
		Session session = HibernateHelper.getSessionFactory().getCurrentSession();
		Query query = session.createQuery("from Item as it where it.id = :itemId");
		query.setParameter("itemId", itemId);
		List<Item> items = new ArrayList<Item>(query.list());
		if (items == null || items.size() != 1) {
			log.error("Error getting item for itemId=" + itemId);
			result = null;
		} else {
			result = items.get(0);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	private Status getStatus(Integer statusId) {
		Status result;
		Session session = HibernateHelper.getSessionFactory().getCurrentSession();
		Query query = session.createQuery("from Status as st where st.id = :statusId");
		query.setParameter("statusId", statusId);
		List<Status> statuses = new ArrayList<Status>(query.list());
		if (statuses == null || statuses.size() != 1) {
			log.error("Error getting item for statusId=" + statusId);
			result = null;
		} else {
			result = statuses.get(0);
		}
		return result;
	}

	private void updateObvservationQuantity(Observation observation, int quantity) {
		log.debug("Updating observation " + observation.getId() + " with quantity " + quantity);
		Session session = HibernateHelper.getSessionFactory().getCurrentSession();
		observation.setQuantity(observation.getQuantity() + quantity);
		if (observation.getQuantity() == 0) {
			session.delete(observation);
		} else {
			session.update(observation);
		}
	}

	@SuppressWarnings("unchecked")
	private Observation getFirstGtinObservation(String itemId, Integer statusId, Integer quantity) {
		Session session = HibernateHelper.getSessionFactory().getCurrentSession();
		log.debug(String.format("Getting first GTIN for %s, status %d and quantity %d", itemId,
				statusId, quantity));
		Query query = session
				.createQuery("from Observation as ob where ob.item.id = :itemId and ob.status.id = :statusId and serial = NULL");

		query.setParameter("itemId", itemId);
		query.setParameter("statusId", statusId);
		List<Observation> observations = new ArrayList<Observation>(query.list());

		if (observations.size() == 0) {
			log.error("No observations for itemId=" + itemId + " and status=" + statusId);
		} else if (observations.size() > 1) {
			log.error("More than one observation for itemId=" + itemId + " and status=" + statusId);
		} else {
			Observation firstObservation = observations.get(0);
			if (firstObservation != null) {
				if (firstObservation.getQuantity() < quantity) {
					log.error("Wrong quantity for observation for itemId=" + itemId
							+ " and status=" + statusId + " current quantity: "
							+ firstObservation.getQuantity());
				} else if (firstObservation.getSerial() != null
						&& firstObservation.getSerial().length() > 0) {
					log.error("Observation for itemId=" + itemId + " and status=" + statusId
							+ " has serial number. Cannot move.");
				} else {
					return firstObservation;
				}
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private Observation getSecondGtinObservation(String itemId, Integer statusId) {
		Session session = HibernateHelper.getSessionFactory().getCurrentSession();
		log.debug(String.format("Getting second GTIN for %s, status %d", itemId, statusId));
		Query query = session
				.createQuery("from Observation as ob where ob.item.id = :itemId and ob.status.id = :statusId and serial = NULL");

		query.setParameter("itemId", itemId);
		query.setParameter("statusId", statusId);
		List<Observation> observations = new ArrayList<Observation>(query.list());

		if (observations.size() > 1) {
			log.error("More than one observation for itemId=" + itemId + " and status=" + statusId);
		} else {
			if (observations.size() < 1) {
				log.debug("No observations for itemId=" + itemId + " and status=" + statusId
						+ ", creating new.");

				Item item = getItem(itemId);
				Status newStatus = getStatus(statusId);

				if (item == null || newStatus == null) {
					log.error("Could not create new observation for itemId=" + itemId
							+ " and status=" + statusId);
				} else {
					Observation secondObservation = new Observation(item, newStatus, 0, new Date());
					session.save(secondObservation);
					return secondObservation;
				}
			} else {
				return observations.get(0);
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private List<Observation> getSerialObservations(String itemId, String serial) {
		Session session = HibernateHelper.getSessionFactory().getCurrentSession();
		Query query = session
				.createQuery("from Observation as ob where ob.item.id = :itemId and ob.serial = :serial");

		query.setParameter("itemId", itemId);
		query.setParameter("serial", serial);
		return new ArrayList<Observation>(query.list());
	}

	@Override
	public void move(String itemId, Integer newStatusId, String serial) {
		// TODO Auto-generated method stub
		
	}
}
