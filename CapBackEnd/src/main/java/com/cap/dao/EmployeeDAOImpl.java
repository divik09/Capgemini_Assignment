package com.cap.dao;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


import org.springframework.stereotype.Repository;

import com.cap.dto.Employees;

@Repository
public class EmployeeDAOImpl implements EmployeesDAO{
	
	@PersistenceContext
	private EntityManager entity;

	public EmployeeDAOImpl(EntityManager entity) {
		
		this.entity = entity;
	}
	
	
	public Employees findById(long id) {
		
		Employees dto=entity.find(Employees.class, id);
		return dto;
	}
	
	
	public long add(Employees dto) {
		entity.persist(dto);
		return dto.getId();
	}
	
	
	public void update(Employees dto) {
		entity.merge(dto);
	}
	
	
	public void delete(long dto) {
		entity.remove(dto);
	}
	
public List search(Employees dto,int pageNo,int pageSize){
		
		List list = new ArrayList<>();
		
		CriteriaBuilder builder = entity.getCriteriaBuilder();
		CriteriaQuery<Employees> query = builder.createQuery(Employees.class);
		Root<Employees> r = query.from(Employees.class);
		query.select(r);
		
		System.out.println("query is --->"+r);
		
		if(dto.getName()!=null) {
			list.add(builder.like(r.get("name"),dto.getName()));
		}
		
		if(dto.getAge()!=0) {
			list.add(builder.equal(r.get("age"), dto.getAge()));
		}
          if(dto.getDepartment()!=null) {
			list.add(builder.like(r.get("department"),dto.getDepartment()));
		}
		
		
		query.where((Predicate[]) list.toArray(new Predicate[list.size()]));
		
		TypedQuery<Employees> tq = entity.createQuery(query);
		
		
		if(pageSize>0) {
			tq.setFirstResult((pageNo-1)*pageSize);
			tq.setMaxResults(pageSize);
		}
		
				
		list = tq.getResultList();
		
		System.out.println("size of list "+list.size());

		return list;
	}

}
