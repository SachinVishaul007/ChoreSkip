package com.northeastern.choreless.util;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.util.HashMap;
import java.util.Map;

import com.northeastern.choreless.model.*;

public class HibernateUtil {

	public static SessionFactory buildSessionFactory(){
        Map<String, Object> settings = new HashMap<>();
        settings.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        settings.put("hibernate.connection.url", "jdbc:mysql://choreskiprds.c2f2k4m0ei08.us-east-1.rds.amazonaws.com:3306/choreskipdb2");
        settings.put("hibernate.connection.username", "admin");
        settings.put("hibernate.connection.password", "123qweasd");


        settings.put("hibernate.hbm2ddl.auto", "update");
        settings.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        settings.put("hibernate.dialect.storage_engine", "innodb");
        settings.put("hibernate.show-sql", "true");

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(settings).build();

        MetadataSources metadataSources = new MetadataSources(serviceRegistry);
        metadataSources.addPackage("com.northeastern.choreless.model");
        metadataSources.addAnnotatedClasses(Roommate.class, ChoreGroup.class,Chore.class, RoommateChoreDebtor.class);

        Metadata metadata = metadataSources.buildMetadata();

        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        return sessionFactory;
    }
	
}
//PROD/CLOUD
//settings.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
//settings.put("hibernate.connection.url", "jdbc:mysql://mysql-1a200063-sachinpiano123-470e.a.aivencloud.com:25749/chorelessdb2");
//settings.put("hibernate.connection.username", "avnadmin");
//settings.put("hibernate.connection.password","AVNS_L76HAe5z1jjt5QLgdhi");


//local
//settings.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
//settings.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/chorelessdb2");
//settings.put("hibernate.connection.username", "root");
//settings.put("hibernate.connection.password","root");