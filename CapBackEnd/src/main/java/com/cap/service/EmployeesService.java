package com.cap.service;

import java.util.List;

import com.cap.dto.Employees;

public interface EmployeesService {

	public Employees findById(long id);
	public long add(Employees dto);
	public void update(Employees dto);
	public void delete(long id);
	public List search(Employees dto,int pageNo,int pageSize);
	
}
