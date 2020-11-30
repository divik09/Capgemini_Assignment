package com.cap.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cap.dto.Employees;


public interface EmployeesDAO {

	public Employees findById(long id);
	public long add(Employees dto);
	public void update(Employees dto);
	public void delete(long id);
	public List search(Employees dto,int pageNo,int pageSize);
}
