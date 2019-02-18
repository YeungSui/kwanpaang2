package core.repository;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class GenericDao<T> {
	@PersistenceContext
	private EntityManager em;
	private Class<T> parameterizedType;
	
	public GenericDao(){
		Class cls = getClass();
		Type type = cls.getGenericSuperclass();
		if(type instanceof ParameterizedType) {
			Type[] typeArray = ((ParameterizedType)type).getActualTypeArguments();
			if(typeArray != null && typeArray.length > 0) {
				parameterizedType = (Class)typeArray[0];
			}
		}
	}
	
	public void save(T entity) {
		em.persist(entity);
	}
	public void delete(T entity) {
		em.remove(entity);
	}
	public void update(T entity) {
		em.persist(entity);
	}
	public List<T> findByJpql(String jpql){
		Query query = em.createQuery(jpql,this.parameterizedType);
		return (List<T>)query.getResultList();
	}
}
