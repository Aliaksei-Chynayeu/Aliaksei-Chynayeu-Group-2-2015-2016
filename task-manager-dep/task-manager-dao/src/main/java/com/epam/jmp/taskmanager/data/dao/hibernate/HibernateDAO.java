package com.epam.jmp.taskmanager.data.dao.hibernate;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.epam.jmp.taskmanager.data.dao.AbstractDAO;
import com.epam.jmp.taskmanager.exception.TechnicalDAOException;
import com.epam.jmp.taskmanager.model.BeanBase;

public class HibernateDAO<T extends BeanBase> extends AbstractDAO<T, Session>{

	private Class<T> entityClassType;
	private SessionFactory sessionFactory;
	public static final Logger LOG = Logger.getLogger(HibernateDAO.class);
	
	public HibernateDAO() {
		this.init();
	}
	
	protected void init(){
	}

	@Override
	@Transactional(rollbackOn={Exception.class})
	public List<T> getList() throws TechnicalDAOException {
		List<T> objs = null;
		Session session = getSessionFactory().openSession();
		try {

			session.getTransaction().begin();
			Criteria criteria = session.createCriteria(this.getEntityClassType());
			objs = criteria.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			throw new TechnicalDAOException("Fail to list in HibernateNewsDAO", e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return (List<T>) objs;
	}

	@Override
	@Transactional(rollbackOn={Exception.class})
	public T fetchById(long id) throws TechnicalDAOException {
		T obj = null;
       Session session = getSessionFactory().openSession();
       try {
           session.getTransaction().begin();
           obj = (T) session.createCriteria(getEntityClassType()).add(Restrictions.idEq(id)).uniqueResult();  
           session.getTransaction().commit();
       } catch (HibernateException e) {
           throw new TechnicalDAOException("Fail to fetch by id in HibernateNewsDAO", e);
       } finally {
           if (session != null) {
               session.close();
           }
       }
       return (T) obj;
	}

	@Override
	@Transactional(rollbackOn={Exception.class})
	public T save(T obj) throws TechnicalDAOException {
       Session session = getSessionFactory().openSession();
       try {
           session.getTransaction().begin();
           session.saveOrUpdate(obj);
           session.getTransaction().commit();
       } catch (Exception e) {
           throw new TechnicalDAOException("Fail to save or update  in HibernateNewsDAO", e);
       } finally {
           if (session != null) {
               session.close();
           }
       }
		return this.fetchById(obj.getId());
	}

	@Override
	@Transactional(rollbackOn={Exception.class})
	public void remove(Long... ids) throws TechnicalDAOException {
		 Session session = getSessionFactory().openSession();
	        try {
	            session.getTransaction().begin();
	            for (Long id : ids) {
	                if (id != null && id != 0) {
	                    T obj = (T) session.createCriteria(getEntityClassType())
	                    				 .add(Restrictions.idEq(id))
	                    				 .uniqueResult();
	                    session.delete(obj);
	                }
	            }
	            session.getTransaction().commit();
	        } catch (HibernateException e) {
	            throw new TechnicalDAOException("Fail to delete news in HibernateNewsDAO", e);
	        } finally {
	            if (session != null) {
	                session.close();
	            }
	        }

	}

   public void setSessionFactory(SessionFactory sessionFactory) {
       this.sessionFactory = sessionFactory;
   }

   public SessionFactory getSessionFactory() {
       return sessionFactory;
   }
	public Class<T> getEntityClassType() throws TechnicalDAOException {
		if (entityClassType == null) {
			throw new TechnicalDAOException("Entity class type can not be null.");
		}
		return entityClassType;
	}


	public void setEntityClassType(Class<T> entityClassType) {
		this.entityClassType = entityClassType;
	}
}
