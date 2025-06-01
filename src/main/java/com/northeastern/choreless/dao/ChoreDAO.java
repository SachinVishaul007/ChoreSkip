package com.northeastern.choreless.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.northeastern.choreless.model.Chore;
import com.northeastern.choreless.model.ChoreGroup;
import com.northeastern.choreless.model.Roommate;
import com.northeastern.choreless.util.HibernateUtil;

@Repository
public class ChoreDAO {
	private SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
	
	public void  save( Chore chore) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.getTransaction();
            transaction.begin();

//            ChoreGroup savedGroup = (ChoreGroup) session.merge(group);
            session.merge(chore);
//            int groupId = savedGroup.getGroupId();
            transaction.commit();
            
//            return groupId;
        }
        catch (Exception e){
            e.printStackTrace();
        }
//        System.out.println(".save() function didnt work so returning 0");
//        return 0;
    }
	
	public Chore getById(int id) {
		try (Session session = sessionFactory.openSession()) {
			String queryString = "FROM Chore where choreId=" + id;
			Query query = session.createQuery(queryString, Chore.class);
			List<Chore> chores = query.list();
			return chores.size() == 1 ? chores.get(0) : null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	public List<Chore> getAll() {
		try (Session session = sessionFactory.openSession()) {
			String queryString = "FROM Chore";
			Query query = session.createQuery(queryString, Chore.class);
			List<Chore> chores = query.list();
			return chores;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public List<Chore> getByGroupId(int id) {
		try (Session session = sessionFactory.openSession()) {
			String queryString = "FROM Chore where choregroup.groupId=" + id;
			Query query = session.createQuery(queryString, Chore.class);
			List<Chore> chores = query.list();
			return chores;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
    
}
