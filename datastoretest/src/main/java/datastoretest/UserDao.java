package datastoretest;



import java.util.List;

import datastoretest.Result;

public interface UserDao 
{
	Employee readUser(Long userId);
	
	Long createEmp(Employee Emp);
	
	boolean deleteUser(String mail);
	
	public List<Employee> getUser(String mail);
	
	public boolean updateUser(Employee emp);
	
	//public Employee updateEmployee(Employee emp);
	
	Result<Employee> listUsers();
}
