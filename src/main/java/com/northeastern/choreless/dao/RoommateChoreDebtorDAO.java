package com.northeastern.choreless.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.northeastern.choreless.model.Chore;
import com.northeastern.choreless.model.RoommateChoreDebtor;
import com.northeastern.choreless.util.HibernateUtil;



@Repository
public class RoommateChoreDebtorDAO {
private SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
	
	public void  save(RoommateChoreDebtor rcd) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.getTransaction();
            transaction.begin();

//            ChoreGroup savedGroup = (ChoreGroup) session.merge(group);
            session.merge(rcd);
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
	
	public RoommateChoreDebtor getDebtors(int chore_id, int roommate_id){
		try (Session session = sessionFactory.openSession()) {
			String queryString = "FROM RoommateChoreDebtor where roommate.id=" + roommate_id + " and chore.choreId="+chore_id;
			Query query = session.createQuery(queryString, RoommateChoreDebtor.class);
			List<RoommateChoreDebtor> rcd = query.list();
			return rcd.size() == 1 ? rcd.get(0) : null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
}
