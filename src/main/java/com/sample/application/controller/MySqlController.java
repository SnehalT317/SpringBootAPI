package com.sample.application.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.application.mysqlservice.MySQLService;

@RestController
public class MySqlController {
	
	@Autowired
	MySQLService sqlService;
	
	@RequestMapping(value = "/employees", method = RequestMethod.GET, produces = "application/json")
	public JSONArray getEmployees() throws ClassNotFoundException, SQLException
	{
		JSONArray result = sqlService.run();
		return result;
		
	}
	
	@RequestMapping(value = "/employees/{id}", method = RequestMethod.GET, produces = "application/json")
	public JSONArray getEmployeesbyID(@PathVariable("id") int id) throws ClassNotFoundException, SQLException
	{
		JSONArray result = sqlService.run(id);
		return result;
		
	}
	
	@PostMapping("/employeesbydept")
	public JSONArray getEmployeesbyDept(@RequestBody Map<String,String> body ) throws ClassNotFoundException, SQLException
	{
		JSONArray result = sqlService.runByDept((String) body.get("dept"));
		return result;
		
	}
	

    @DeleteMapping("/employees/{id}")
    // Method
    public String deleteById(@PathVariable("id") int id) throws SQLException {

    	int result = sqlService.deleteEmployee(id);
    	
    	if(result >0)
    	{
    		return "Record Deleted";
    	}
    	else
    	{
    		
    		return "Record not found";
    		
    	}
    }
    	
    	@PutMapping("/employees/{id}")
        // Method
        public String updateRecord(@PathVariable("id") int id) throws SQLException {

        	int result = sqlService.updateEmployee(id);
        	
        	if(result >0)
        	{
        		return "Record Updated";
        	}
        	else
        	{
        		
        		return "Record not found";
        		
        	}
    	}
        	
        	
        	@PostMapping("/employees")
            // Method
            public String addRecord(@RequestBody Map<String,String> body) throws SQLException {

        		int k = (Integer.parseInt((String) body.get("emp_no")));
        		
            	int result = sqlService.addEmployee(k,
            			(String) body.get("birth_date"),
            			(String) body.get("first_name"),
            			(String) body.get("last_name"),
            			(String) body.get("gender"),
            			(String) body.get("hire_date"));
            	
            	if(result >0)
            	{
            		return "Record Updated";
            	}
            	else
            	{
            		
            		return "Record not found";
            		
            	}
    
		
    	
    }
	
	
	
	

}
