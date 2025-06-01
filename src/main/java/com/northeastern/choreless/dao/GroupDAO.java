package com.northeastern.choreless.dao;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.northeastern.choreless.util.*;
import com.northeastern.choreless.model.Chore;
import com.northeastern.choreless.model.ChoreGroup;
import com.northeastern.choreless.model.Roommate;

import java.util.List;

import org.hibernate.Session;

@Repository
public class GroupDAO {

	private SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

	
	
    public ChoreGroup getById(int id) {
        try(Session session = sessionFactory.openSession()) {
            String queryString = "FROM ChoreGroup where groupId="+id;
            Query query = session.createQuery(queryString, ChoreGroup.class);
            List<ChoreGroup> groups = query.list();
            return groups.size() == 1 ? groups.get(0) : null;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public int  save( ChoreGroup group) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.getTransaction();
            transaction.begin();
            
            for (Roommate roommate : group.getRoommates()) {
                roommate.setChoregroup(group);
                session.persist(roommate);
            }
            
            ChoreGroup savedGroup = (ChoreGroup) session.merge(group);
//          session.persist(group);
            int groupId = savedGroup.getGroupId();
            transaction.commit();
            
            return groupId;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(".save() function didnt work so returning 0");
        return 0;
    }
    
    public int  update( ChoreGroup group) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.getTransaction();
            transaction.begin();
//            
            for (Roommate roommate : group.getRoommates()) {
                roommate.setChoregroup(group);
                session.merge(roommate);
            }
            for (Chore chore : group.getChores()) {
                chore.setChoregroup(group);
                session.merge(chore);
            }
//            
            ChoreGroup savedGroup = (ChoreGroup) session.merge(group);
//          session.persist(group);
            int groupId = savedGroup.getGroupId();
            transaction.commit();
            
            return groupId;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(".update() function didnt work so returning 0");
        return 0;
    }
    
    
    public void deleteById(int id) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.getTransaction();
            transaction.begin();
            String queryString = "delete from ChoreGroup where groupId= :id";
            Query query = session.createQuery(queryString);
            query.setParameter("id",id);
            query.executeUpdate();
            transaction.commit();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
	
}
