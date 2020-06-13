package datastoretest;

import datastoretest.Result;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.jstl.sql.ResultSupport;

import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilter;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.google.appengine.api.datastore.QueryResultList;
import com.google.appengine.api.datastore.Query.FilterPredicate;


public class DataStoreDao implements UserDao
{
	 private DatastoreService datastore;
	  private static final String Employee_KIND = "Emp";

	  public DataStoreDao() {
	    datastore = DatastoreServiceFactory.getDatastoreService();
	  }
	  
	  public Result<Employee>listUsers()
	  {
		  FetchOptions fetchOptions = FetchOptions.Builder.withLimit(10);
		  
		  	Query query = new Query(Employee_KIND).addSort(Employee.NAME, SortDirection.ASCENDING);
			  PreparedQuery preparedQuery = datastore.prepare(query);
			  QueryResultIterator<Entity>results = preparedQuery.asQueryResultIterator(fetchOptions);
			  
			  List<Employee> resultUsers = entitiesToUsers(results);
			  
			  return new Result<>(resultUsers);
			  
	  }
	  
	  public List<Employee> entitiesToUsers(Iterator<Entity> results) {
		  
		  List<Employee> resultUsers = new ArrayList<>();
		    while(results.hasNext()) {  
		      resultUsers.add(entityToUser(results.next()));      
		    }
		    System.out.println(resultUsers.size());
		    
		    
		    return resultUsers;
}
	  
	  
	  public Employee entityToUser(Entity entity) {
		  
		  Employee emp = new Employee();
		  emp.setMail((String)entity.getProperty(Employee.MAIL));
		  emp.setId(entity.getKey().getId());
		  emp.setName((String) entity.getProperty(Employee.NAME));
		      	        
					        return emp;
		      
		}

	  
//	  public Employee entityToEmp(Iterator<Entity> results) {
//		  
//		  Employee resultUser = new Employee();
//		  //List<Employee> resultUsers = new ArrayList<>();
//		    while(results.hasNext()) {  
////		      resultUsers.add(entityToUser(results.next()));
//		    	resultUser = entityToUser(results.next());
//		    }
//		    
//		    //System.out.println(resultUser.getName());
//		    
//		    return resultUser;
//}
	  
	  public List<Employee> getUser(String email)
	  {
		  
		  
		  
		  FetchOptions fetchOptions = FetchOptions.Builder.withLimit(5);
		
		  Filter propertyFilter = new FilterPredicate(Employee.MAIL, FilterOperator.EQUAL, email);
		  
		  Query q = new Query("Emp").setFilter(propertyFilter);
		
		  PreparedQuery preparedQuery = datastore.prepare(q);
		  
		  QueryResultIterator<Entity>results = preparedQuery.asQueryResultIterator(fetchOptions);
		  
		  return entitiesToUsers(results);
	  }
	  
	  
	  public boolean updateUser(Employee emp)
	  {
		  Key key = KeyFactory.createKey(Employee_KIND, emp.getId());  // From a book, create a Key
		  Entity entity = new Entity(key);
		  
		 if(emp != null)
		 {
			 entity.setProperty(emp.MAIL, emp.getMail());
			 entity.setProperty(emp.NAME, emp.getName());
			 datastore.put(entity);
			 
			 return true;
		 }
		 
		 
		 return false;
		  
	  }
	  
	  
	  
	  
	  
	  @Override
	  public Long createEmp(Employee emp) {
	    Entity incUserEntity = new Entity(Employee_KIND); 
	    incUserEntity.setProperty(emp.NAME,emp.getName());
	    incUserEntity.setProperty(emp.MAIL,emp.getMail());
	    

	    Key userKey = datastore.put(incUserEntity); // Save the Entity
	    return userKey.getId();                     // The ID of the Key
	    
	  }
	    
	    
	  @Override
	  public Employee readUser(Long userId) {
	    try {
	      Entity userEntity = datastore.get(KeyFactory.createKey(Employee_KIND, userId));
	      return entityToUser(userEntity);
	    } catch (EntityNotFoundException e) {
	      return null;
	    }
	  }
	  
	  
	  /*
	  public List<User> entitiesToUsers(Iterator<Entity> results) {
			List<User> resultUsers = new ArrayList<>();
			while (results.hasNext()) {  
			      resultUsers.add(entityToUser(results.next()));      
			}
			return resultUsers;
		}

	  
	  
	  @Override
	  public Result<User> listUsers(String startCursorString) {
	    FetchOptions fetchOptions = FetchOptions.Builder.withLimit(3); // Only show 10 at a time
	    if (startCursorString != null && !startCursorString.equals("")) {
	      fetchOptions.startCursor(Cursor.fromWebSafeString(startCursorString)); // Where we left off
	    }
	    Query query = new Query(User_KIND) // We only care about Books
	        .addSort(User.ID, SortDirection.ASCENDING); // Use default Index "title"
	    PreparedQuery preparedQuery = datastore.prepare(query);
	    QueryResultIterator<Entity> results = preparedQuery.asQueryResultIterator(fetchOptions);

	    List<User> resultUsers = entitiesToUsers(results);     // Retrieve and convert Entities
	    Cursor cursor = results.getCursor();              // Where to start next time
	    if (cursor != null && resultUsers.size() == 3) {         // Are we paging? Save Cursor
	      String cursorString = cursor.toWebSafeString();               // Cursors are WebSafe
	      return new Result<>(resultUsers, cursorString);
	    } else {
	      return new Result<>(resultUsers);
	    }
	  }
	  */
//	    
//	    return new User.Builder()                       
//	            .name(User.getName())
//	            .email(User.geteMail())
//	            .id(UserKey.getId())
//	            .build();                   
	  
	  
//	  @Override
//	  public Employee updateEmployee(Employee emp) {
//	    //Key key = KeyFactory.createKey(Employee_KIND, emp.getId());
//		Entity entityObj = new Entity(Employee_KIND,emp.getId());
//
//		entityObj.setProperty(Employee.MAIL, emp.getMail());
//		//entityObj.setProperty(Category.MODIFIED_BY, catObj.getModifiedBy());
//		datastore.put(entityObj);
//	    // From a book, create a Key
//	    //Entity entityobj = new Entity(key);
//	    // Convert Book to an Entity
//
//	    //if(entity.getProperty(emp.MAIL).equals())
//	    
//		Entity resultObj;
//		try {
//			resultObj = datastore.get(KeyFactory.createKey(Employee_KIND, emp.getId()));
//			return entityToCategory(resultObj);
//		} catch (EntityNotFoundException e) {
//			e.printStackTrace();
//			return null;
//		}

	    
	    //entityObj.setProperty(emp.MAIL, emp.getMail());
	    
	                       // Update the Entity
	 // }
	  

	  
	  
	  @Override
	  public boolean deleteUser(String mail) {
	    Key key = KeyFactory.createKey(Employee_KIND, mail);        // Create the Key
	    datastore.delete(key);                 
	    if(getUser(mail) ==  null)
	    {
	    	return true;
	    }
	    
	    return false;
	  }
	  
}


