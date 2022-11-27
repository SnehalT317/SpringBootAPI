package com.sample.application.mysqlservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("MySQLService")

public class MySQLService {

	
	private static int count =0;
	@Value("${userName}")
	private String userName;
	
	@Value("${password}")
	private String password;
	
	@Value("${connectionString}")
	private String connectionString;
	/*
	 * public static void main(String args[]) throws ClassNotFoundException,
	 * SQLException {
	 * 
	 * JSONObject k = new JSONObject(); k.put("fisrtname", "Snehal"); JSONArray arr
	 * = new JSONArray(); arr.add(k); System.out.println(arr);
	 * 
	 * new MySQLService().run(); }
	 */
	//JSONArray jsonArr = new JSONArray();
	
	//@PostConstruct
	public Connection intializeConnection()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		    Connection connection = DriverManager.getConnection(connectionString, userName,password);
		    System.out.println("Creating Connctiomn");
		    return connection;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	@SuppressWarnings("unchecked")
	public JSONArray run() throws ClassNotFoundException, SQLException {

		
		JSONArray jsonArr = new JSONArray();
		// mydb is database
		// mydbuser is name of database
		// mydbuser is password of database
		Connection connection=intializeConnection();
		Statement statement;
		statement = connection.createStatement();
		ResultSet resultSet;
		resultSet = statement.executeQuery("select * from employees");

		List<String> output = new ArrayList<String>();
		while (resultSet.next()) {
			JSONObject obj = new JSONObject();
			String first_name = resultSet.getString("first_name").trim();
			output.add(first_name);
			obj.put("first_name", first_name);
			jsonArr.add(obj);
		}

		System.out.println(jsonArr);
		resultSet.close();
		statement.close();
		connection.close();
		return jsonArr;

	}
	

	@SuppressWarnings("unchecked")
	public JSONArray run(int id) throws SQLException {
		JSONArray jsonArr = new JSONArray();
		Connection connection=intializeConnection();
		
		System.out.println("Run: " +count++);
		Statement statement;
		statement = connection.createStatement();
		ResultSet resultSet;
		resultSet = statement.executeQuery("select emp_no,first_name, last_name from employees WHERE emp_no = "+ id +"");

		//List<String> output = new ArrayList<String>();
		while (resultSet.next()) {
			JSONObject obj = new JSONObject();
			String first_name = resultSet.getString("first_name").trim();
			String emp_no = resultSet.getString("emp_no").trim();
			String last_name = resultSet.getString("last_name").trim();
			//output.add(first_name);
			obj.put("first_name", first_name);
			obj.put("emp_no", emp_no);
			obj.put("last_name", last_name);
			jsonArr.add(obj);
		}
		

		System.out.println(jsonArr);
		resultSet.close();
		statement.close();
		connection.close();
		return jsonArr;
		
	}
	
	public JSONArray runByDept(String dept) throws SQLException {
		
		JSONArray jsonArr = new JSONArray();
		Connection connection=intializeConnection();
		
		//System.out.println("Run: " +count++);
		Statement statement;
		statement = connection.createStatement();
		ResultSet resultSet;
		resultSet = statement.executeQuery("SELECT e.emp_no,e.first_name,e.last_name,d.dept_name FROM dept_emp de \n"
				+ "INNER JOIN employees e ON de.emp_no=e.emp_no \n"
				+ "INNER JOIN departments d ON de.dept_no = d.dept_no and dept_name =  '"+ dept +"'");

		//List<String> output = new ArrayList<String>();
		while (resultSet.next()) {
			JSONObject obj = new JSONObject();
			String first_name = resultSet.getString("first_name").trim();
			String emp_no = resultSet.getString("emp_no").trim();
			String last_name = resultSet.getString("last_name").trim();
			//output.add(first_name);
			obj.put("first_name", first_name);
			obj.put("emp_no", emp_no);
			obj.put("last_name", last_name);
			jsonArr.add(obj);
		}
		

		System.out.println(jsonArr);
		resultSet.close();
		statement.close();
		connection.close();
		return jsonArr;
	}
	
	public int deleteEmployee(int id) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection=intializeConnection();
		
		//System.out.println("Run: " +count++);
		Statement statement;
		statement = connection.createStatement();
		int resultSet;
		//resultSet = statement.execute("DELETE from employees WHERE emp_no = "+ id +"");
		resultSet = statement.executeUpdate("DELETE from employees WHERE emp_no = "+ id +"");
		return resultSet;
	}
	
	
	public int updateEmployee(int id) throws SQLException {
		// TODO Auto-generated method stub
		
       Connection connection=intializeConnection();
		
		//System.out.println("Run: " +count++);
		Statement statement;
		statement = connection.createStatement();
		int resultSet;
		//resultSet = statement.execute("DELETE from employees WHERE emp_no = "+ id +"");
		resultSet = statement.executeUpdate("update salaries set salary = "+50000+" where emp_no =  "+ id +"");
		return resultSet;
	}
	
	public int addEmployee(int id,String birth_date, String first_name, String last_name, String gender, String hire_date) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection=intializeConnection();
		Statement statement;
		statement = connection.createStatement();
		int resultSet;
		//resultSet = statement.execute("DELETE from employees WHERE emp_no = "+ id +"");
		String query = "INSERT INTO employees VALUES ("+id +",'"+birth_date+"','"+first_name+"','"+
				last_name+"','"+gender+"','"+hire_date+"')";
		resultSet = statement.executeUpdate(query);
		return resultSet;
		
	}

}
