package org.demo.MyDemoUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.demo.mapping.Person;
import org.demo.mapping.UserInfo;
import org.demo.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.mapping.PersistentClass;
import org.json.JSONException;
import org.json.JSONObject;

public class App {
	public static void main1(String[] args) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Person p1 = new Person();
		p1.setFirstName("arisetti");
		p1.setLastName("vamsi");
		p1.setEmail("test@demo.com");

		UserInfo uInfo = new UserInfo();

		uInfo.setUsername("vamsi");
		uInfo.setUserloc("Hyderabad");
		uInfo.setPassword("testpassword");
		uInfo.setSalt("testsalt");
		uInfo.setPwdhash("testpwdhash");

		p1.setUserInfo(uInfo);

		long personId = (Long) session.save(p1);
		session.getTransaction().commit();

		Person person = (Person) session.get(Person.class, personId);
		System.out.println(person);

		session.close();
	}

	public static void main(String[] args) {
		try {
			doSelectUserInfo();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public static void doSelectUserInfo() throws JSONException, NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();

		String[] strCols = sessionFactory.getClassMetadata(Person.class).getPropertyNames();
		/*
		 * for (String column : strCols) { System.out.println("column names :"
		 * +column.substring(0,1).toUpperCase()+column.substring(1)); }
		 */
		session.beginTransaction();
		// Query qry = session.createQuery("FROM UserInfo where username =
		// :username");
		// qry.setString("username", "vamsi");

		Query qry = session.createQuery("FROM Person where lastname = :username");
		qry.setString("username", "vamsi");

		List lst = qry.list();
		Iterator itr = lst.iterator();
		List<String> lstObj = null;
		Map<Integer, Map<String, Object>> mapObj = new HashMap<Integer, Map<String, Object>>();
		int index = 0;
		while (itr.hasNext()) {
			Person prsn = (Person) itr.next();

			Map<String, Object> mapPrsn = new HashMap<String, Object>();
			Map<String, Object> mapwithCols = new HashMap<String, Object>();
			for (String column : strCols) {
				String columnNm = column.substring(0, 1).toUpperCase() + column.substring(1);

				Method method = prsn.getClass().getMethod("get" + columnNm, null);
				Object mtdRtn = method.invoke(prsn, null);
				mapwithCols.put(columnNm, method.invoke(prsn, null));
			}

			mapObj.put(index, mapwithCols);
			++index;
		}
		JSONObject json = new JSONObject();
		json.put("Details", mapObj);
		System.out.println("json map >>" + json);
	}
}