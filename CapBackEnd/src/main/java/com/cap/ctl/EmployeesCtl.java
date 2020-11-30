	package com.cap.ctl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cap.dto.Employees;
import com.cap.service.EmployeesService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/employees")
public class EmployeesCtl {
   
	 
	   
	
	@Autowired
	EmployeesService service;
	
	@GetMapping
	public String test() {
		return "testing SuccessFull";
	}
	
	
	@GetMapping("/getEmployee/{id}")
	public Map<String, Object> getEmployee(@PathVariable long id){
		 Map<String,Object> response = new HashMap<>();	
		Employees dto = service.findById(id);
		if(dto==null) {
			response.put("message","Employee Not Present");
		}else {
			response.put("data",dto);
		}
		return response;
	}
	
	
	//@GetMapping("/search/{id}")
	@RequestMapping(value = "/search",method = {RequestMethod.GET,RequestMethod.POST})
	public Map<String,Object> search(@RequestBody Employees employees){
		 Map<String,Object> response = new HashMap<>();
		System.out.println("inside search "+ employees);
		List<Employees> employeesList = service.search(employees, 1, 5);
		response.put("list", employeesList);
		
		return response;
	}
	
	@PostMapping("/save")
	public Map<String,Object> addorupdate(@RequestBody Employees employees) {
		 Map<String,Object> response = new HashMap<>();
		if(employees.getId()>0) {
			service.update(employees);
			response.put("message", "Data Updated Successfully");
			 
		}else {
			long addorupdate = service.add(employees);
			 response.put("message", "Data Saved Successfully");
			 response.put("data", addorupdate);
		}
		 return response;	
	}
	
	@GetMapping("/delete/{id}")
	public Map<String,Object> delete(@PathVariable long id) {
		 Map<String,Object> response = new HashMap<>();
		     Employees dto = service.findById(id);
		
		      if(dto!= null) {
		    	  service.delete(id);  
		    	  response.put("message", "Data is Deleted");
		      }else {
		    	  response.put("error", "Record does not exists");
		      }
		      
		      
		
		    return response;
	}
	
	
	
	
}
