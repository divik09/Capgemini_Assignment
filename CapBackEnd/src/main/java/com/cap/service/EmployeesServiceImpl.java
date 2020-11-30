package com.cap.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cap.dao.EmployeesDAO;
import com.cap.dto.Employees;

@Service
public class EmployeesServiceImpl implements EmployeesService{

	
	@Autowired
	EmployeesDAO employeesdao;
	
	@Transactional(propagation = Propagation.REQUIRED,readOnly = false)
	@Override
	public long add(Employees employees) {
		
		long save = employeesdao.add(employees);
		
		return save;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void delete(long id) {
	   employeesdao.delete(id);
	}

	@Override
	public Employees findById(long id) {
		Employees findById = employeesdao.findById(id);
		return findById;
	}

	@Override
	public void update(Employees dto) {
          employeesdao.update(dto);		
	}

	@Override
	public List search(Employees dto, int pageNo, int pageSize) {
         List search = employeesdao.search(dto, pageNo, pageSize);
		return search;
	}
	
	
	
	

}
