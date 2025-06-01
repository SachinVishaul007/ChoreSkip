package com.northeastern.choreless.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.northeastern.choreless.model.ChoreGroup;
import com.northeastern.choreless.model.Roommate;
import com.northeastern.choreless.util.HibernateUtil;

@Repository
public class RoommateDAO {

	private SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

	public List<Roommate> getByGroupId(int id) {
		try (Session session = sessionFactory.openSession()) {
			String queryString = "FROM Roommate where choregroup.id=" + id;   
			Query query = session.createQuery(queryString, Roommate.class);
			List<Roommate> roommates = query.list();
			return roommates;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void save(Roommate roommate) {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.getTransaction();
			transaction.begin();

			session.merge(roommate);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Roommate getByEmail(String email) {
		try (Session session = sessionFactory.openSession()) {
			String queryString = "FROM Roommate where email=" + "'" + email + "'";
			Query query = session.createQuery(queryString, Roommate.class);
			List<Roommate> roommates = query.list();
			return roommates.size() == 1 ? roommates.get(0) : null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean checkByEmail(String email) {
		try (Session session = sessionFactory.openSession()) {
			String queryString = "FROM Roommate where email=" + "'" + email + "'";
			Query query = session.createQuery(queryString, Roommate.class);
			List<Roommate> roommates = query.list();
			return roommates.size() >0 ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	
	public Roommate getById(int id) {
		try (Session session = sessionFactory.openSession()) {
			String queryString = "FROM Roommate where id=" + id;
			Query query = session.createQuery(queryString, Roommate.class);
			List<Roommate> roommates = query.list();
			return roommates.size() == 1 ? roommates.get(0) : null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
//	public void deleteRedundant() {
//		try (Session session = sessionFactory.openSession()) {
//			Transaction transaction = session.getTransaction();
//			transaction.begin();
//
//			String queryString = "DELETE FROM Roommate r WHERE r.choregroup IS NULL";
//			Query query = session.createQuery(queryString);
//			query.executeUpdate();
//
//			transaction.commit();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

}
