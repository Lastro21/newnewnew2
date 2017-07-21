package com.example.newnewnew.service;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class ChangeValuesUsers {




    public void changeBackground_color(String background_color, Integer user_id){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.getNamedQuery("changeBackground_color")
                .setParameter(0, background_color)
                .setParameter(1, user_id);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public void changeBackground_block(String background_block, Integer user_id){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.getNamedQuery("changeBackground_block")
                .setParameter(0, background_block)
                .setParameter(1, user_id);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public void changeFont_color(String font_color, Integer user_id){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.getNamedQuery("changeFont_color")
                .setParameter(0, font_color)
                .setParameter(1, user_id);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public void changeBlock_widht(String block_widht, Integer user_id){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.getNamedQuery("changeBlock_widht")
                .setParameter(0, block_widht)
                .setParameter(1, user_id);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public void changeBlock_height(String block_height, Integer user_id){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.getNamedQuery("changeBlock_height")
                .setParameter(0, block_height)
                .setParameter(1, user_id);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }




}
