package com.gavin.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.gavin.pojo.Admin;
import com.gavin.util.HibernateUtil;



public class AdminDAO {
	
    public List<Admin> getAdmin(Admin admin) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        Transaction tx = null;
        List<Admin> adminList = new ArrayList<>();
        try {
            tx = session.getTransaction();
            //tx.begin();
			Query<Admin> query = session.createQuery("FROM Admin where adminName = '" + admin.getAdminName() + "' and password = '" + admin.getPassword() + "'");
            adminList = query.list();
            
                 
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        } 
        return adminList;
    }
    
}
