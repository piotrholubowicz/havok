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

import pl.poznan.ilim.havok.client.services.ItemService;
import pl.poznan.ilim.havok.entities.Item;
import pl.poznan.ilim.havok.entities.ResultBean;
import pl.poznan.ilim.havok.hibernate.HibernateHelper;

/**
 * @author PHolubow
 * 
 */
public class ItemServiceImpl extends PersistentRemoteService implements ItemService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 20110201L;
	private static Logger log = Logger.getLogger(ItemServiceImpl.class);
	private HibernateUtil gileadHibernateUtil = new HibernateUtil();
	private Date lastModificationDate;

	public ItemServiceImpl() {
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
	 * @see pl.poznan.ilim.havok.services.ItemService#createItem(pl.poznan.ilim.havok.entities.Item)
	 */
	@Override
	public void createItem(Item item) {
		Session session = gileadHibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(item);
		session.getTransaction().commit();
		lastModificationDate = new Date();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pl.poznan.ilim.havok.services.ItemService#retrieveItems()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultBean<Item> retrieveItems() {
		Session session = gileadHibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Item> items = new ArrayList<Item>(session.createQuery("from Item").list());
		session.getTransaction().commit();
		return new ResultBean<Item>(items.toArray(new Item[] {}), new Date());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pl.poznan.ilim.havok.services.ItemService#updateItem(pl.poznan.ilim.havok.entities.Item)
	 */
	@Override
	public void updateItem(Item item) {
		Session session = gileadHibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(item);
		session.getTransaction().commit();
		lastModificationDate = new Date();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pl.poznan.ilim.havok.services.ItemService#deleteItem(pl.poznan.ilim.havok.entities.Item)
	 */
	@Override
	public void deleteItem(Item item) {
		Session session = gileadHibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(item);
		session.getTransaction().commit();
		lastModificationDate = new Date();
	}

	@Override
	public ResultBean<Item> retrieveItems(Date lastUpdate) {
		if (lastUpdate.before(lastModificationDate)) {
			return retrieveItems();
		}
		return null;
	}
}
