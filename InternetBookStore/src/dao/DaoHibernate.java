package dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class DaoHibernate<Entity>{

	@Resource
	private SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public int insert(Entity entity){
		Session session=null;
		int result=0;
		session=sessionFactory.getCurrentSession();
		System.out.println("实体标识:"+session.save(entity));
		result++;
		return result;
	}
	public int insertList(List<Entity> entities){
		int result=0;
		for(Entity entity:entities)
			result+=insert(entity);
		return result;
	}
	public int update(Entity entity){
		Session session=null;
		int result=0;
		session=sessionFactory.getCurrentSession();
		session.update(entity);
		result++;
		return result;
	}
	public int updateList(List<Entity> entities){
		int result=0;
		for(Entity entity:entities)
			result+=update(entity);
		return result;
	}
	public int delete(Entity entity){
		Session session=null;
		int result=0;
		session=sessionFactory.getCurrentSession();
		session.delete(entity);
		result++;
		return result;
	}
	public int deleteList(List<Entity> entities){
		int result=0;
		for(Entity entity:entities)
			result+=delete(entity);
		return result;
	}
	public int delete(Class<Entity> entityClass,Serializable serializable){
		Session session=null;
		int result=0;
		session=sessionFactory.getCurrentSession();
		session.delete(session.load(entityClass, serializable));
		result++;
		return result;
	}
	public int deleteList(Class<Entity> entityClass,Serializable... serializables){
		int result=0;
		for(Serializable serializable:serializables)
			result+=delete(entityClass,serializable);
		return result;
	}
	public Entity findByID(Class<Entity> entityClass,Serializable serializable) {
		Session session=null;
		Entity entity=null;
		session=sessionFactory.getCurrentSession();
		entity=(Entity)session.get(entityClass, serializable);
		return entity;
	}
	public List<Entity> findListByID(Class<Entity> entityClass,Serializable... serializables) {
		List<Entity> entities=new ArrayList<>(0);
		for(Serializable serializable:serializables)
			entities.add(findByID(entityClass, serializable));
		return entities;
	}
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Entity> find(String hQL,Object... params){
		Session session=null;
		List<Entity> entities=null;
		session=sessionFactory.getCurrentSession();
		Query<Entity> query=session.createQuery(hQL);
		for(int i=0;i<params.length;i++)
			query.setParameter(i,params[i]);
		entities=query.list();
		return entities;
	}
	@SuppressWarnings("unchecked")
	public int count(String hQL,Object... params){
		Session session=null;
		int count;
		session=sessionFactory.getCurrentSession();
		Query<Integer> query=session.createQuery(hQL);
		for(int i=0;i<params.length;i++)
			query.setParameter(i,params[i]);
		count=query.getFirstResult();
		return count;
	}
	@SuppressWarnings("unchecked")
	public double sum(String hQL,Object... params){
		Session session=null;
		double sum;
		session=sessionFactory.getCurrentSession();
		Query<Double> query=session.createQuery(hQL);
		for(int i=0;i<params.length;i++)
			query.setParameter(i,params[i]);
		sum=query.getFirstResult();
		return sum;
	}
}
