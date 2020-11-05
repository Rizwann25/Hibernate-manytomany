package com.atmecs.hibernate.main;

import java.util.HashSet;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.atmecs.hibernate.model.Cart1;
import com.atmecs.hibernate.model.Item;
import com.atmecs.hibernate.model.Item1;
import com.atmecs.hibernate.util.HibernateAnnotationUtil;

public class HibernateManyToManyAnnotationMain {

	public static void main(String[] args) {
		Item item1 = new Item();
		item1.setDescription("samsung"); item1.setPrice(300);
		Item item2 = new Item();
		item2.setDescription("nokia"); item2.setPrice(200);
		Cart1 cart = new Cart1();
		cart.setTotal(500);
		Set<Item1> items = new HashSet<Item1>();
	//	items.add(item1); items.add(item2);
		cart.setItems(items);
		
		SessionFactory sessionFactory = null;
		try{
		sessionFactory = HibernateAnnotationUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.save(cart);
		System.out.println("Before committing transaction");
		tx.commit();
		sessionFactory.close();
		
		System.out.println("Cart ID="+cart.getId());
		System.out.println("Item1 ID="+item1.getId());
		System.out.println("Item2 ID="+item2.getId());
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(sessionFactory != null && !sessionFactory.isClosed()) sessionFactory.close();
		}
	}

}
