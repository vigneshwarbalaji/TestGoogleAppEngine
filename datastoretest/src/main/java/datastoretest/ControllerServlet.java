package datastoretest;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class ControllerServlet
 */



@WebServlet("/ControllerServlet")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() throws ServletException {
	    UserDao dao = new DataStoreDao();
	    this.getServletContext().setAttribute("dao", dao);
	}
       
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("mail") != null)
		{
			//Long id = Long.decode();
			String mail = request.getParameter("mail");
			
			//System.out.println(mail);
			
			UserDao dao = (UserDao) this.getServletContext().getAttribute("dao");
			
		      List<Employee>res = dao.getUser(mail);
		      
		      //System.out.println(res.size());
		    
		      if(res != null)
		      {
//		    		  response.getWriter().print("id: "+res.getId()+"<br>");
//				      response.getWriter().print("mail: "+res.getMail()+"<br>");
//				      response.getWriter().print("name: "+res.getName()+"<br>");  
		    	  
		    	  
		    	  response.getWriter().write("The user that you had searched is:\n");
		    	  for(Employee emp :res)
		    	  {
		    		  response.getWriter().print("Id"+emp.getId()+"<br>");
				      response.getWriter().print("mail: "+emp.getMail()+"<br>");
				      response.getWriter().print("name: "+emp.getName()+"<br>");
		    	  }
		    	  
			        
		      }
		      else
		      {
		    	  response.getWriter().write("The user is not found\n");
		      }
		}
		else
		{
			UserDao dao = (UserDao) this.getServletContext().getAttribute("dao");
			
		    List<Employee> users = null;

			Result<Employee>results = dao.listUsers();
			
			try {
				users = results.result;
	    } catch (Exception e) {
	        throw new ServletException("Error listing books", e);
	      }
			
			response.getWriter().write("The list of users are: <br>");

			
			for(Employee emp : users)
			{	
				response.getWriter().write("Id = "+emp.getId()+"<br>");
				response.getWriter().write("Name = "+emp.getName()+"<br>");
				response.getWriter().write("Mail = "+emp.getMail()+"<br>");
				
				response.getWriter().write("<br><br>");
			}
			 

		}
	      
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
			
		try {
			Employee emp = new Employee();
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			
			emp.setName(request.getParameter("name"));
			emp.setMail(request.getParameter("email"));

			  UserDao dao = (UserDao)this.getServletContext().getAttribute("dao");
			  
			    
			  	Long id = dao.createEmp(emp);
			  	
			  	response.getWriter().print("Employee Added:<br>");
			    response.getWriter().write("in ID: "+id.toString()+"<br>");
			    response.getWriter().print("email:"+email+"<br> ");
			    response.getWriter().print("name:"+name+"<br> ");
			    
			  } catch (Exception e) {
			    throw new ServletException("Error creating User", e);
			  }
}
			
/*	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		Employee emp = new Employee();
//		
//		emp.setMail(request.getParameter("upmail"));
//		
//		UserDao dao = (UserDao) this.getServletContext().getAttribute("dao");
//		
//		 Employee res = dao.updateEmployee(emp);
//
//		 if(res !=null) {
//		    	response.getWriter().print("Employee was updated successfully !");
//		    }
//		    else {
//		    	response.getWriter().print("Failed to update Employee");
//		    }
		
		
		Long id = Long.decode(request.getParameter("id"));
		String name = request.getParameter("name");
		String mail = request.getParameter("mail");
		//System.out.println(id);
		
		UserDao dao = (UserDao) this.getServletContext().getAttribute("dao");
	    //try {
		
	      List<Employee> res = dao.getUser(mail);
	    
	      if(res != null)
	      {
	    	  
	    	  for (Employee emp : res) 
	    	  {
	    		  response.getWriter().write("The user that you want to update is:\n");
			      response.getWriter().print("id: "+emp.getId()+"<br>");
			      response.getWriter().print("mail: "+emp.getMail()+"<br>");
			      response.getWriter().print("name: "+emp.getName()+"<br>");
	    	  }
	    	  
	      }
	      else
	      {
	    	  response.getWriter().write("The user is not found\n");
	      }
		
		if(emp.getMail() == null)
		{
//			response.getWriter().print("Do you want to update mail of this user\n y r n");
//			response.getWriter().print("<input type='text' id = 'choice' name='choose'>");
//			response.getWriter().print("<input type='button' id = 'confirm' name='button' onclick='choiceMail'>'EnterChoice'</button>");
//			response.getWriter().print("<script>");
//			response.getWriter().print("function choiceMail()");
//			response.getWriter().print("{");
//			//response.getWriter().print("");
//			response.getWriter().print("var xhr = new XMLHttpRequest();");
//			response.getWriter().print("var choice = document.getElementById(\"choice\").value;");
//			response.getWriter().print("xhr.open('PUT','http://localhost:8080/ControllerServlet', true);");
//			response.getWriter().print("xhr.send(\"choice=\"+choice);");
//			response.getWriter().print("}");
//			response.getWriter().print("</script>");
//			
//			String choice = request.getParameter("choice");
//			System.out.println(choice);
			
			Employee emp = new Employee();
			emp.setId(id);
			emp.setMail(mail);
			emp.setName(res.getName());
			
			boolean result = dao.updateUser(emp);
			
			if(result == true)
			{
				response.getWriter().print("mail updated successfully");
			}
			else
			{
				response.getWriter().print("cant update");
			}
		}
		else if(res.getName() == null)
		{
			Employee emp = new Employee();
			emp.setId(id);
			emp.setMail(res.getMail());
			emp.setName(name);
			
			boolean result = dao.updateUser(emp);
			
			if(result == true)
			{
				response.getWriter().print("name updated successfully");
			}
			else
			{
				response.getWriter().print("cant update");
			}
			
		}
		
		
		
}		*/
	

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mailid = request.getParameter("delmail");
	    String mail = request.getParameter("delmail");
	    UserDao dao = (UserDao) this.getServletContext().getAttribute("dao");
	    try {
	      boolean res = dao.deleteUser(mail);
	      //System.out.println("Test..............");
	      
	      if(res == true)
	      {
	    	  response.getWriter().print("The given id is deleted: "+mailid);  
	      }
	      else
	      {
	    	  response.getWriter().print("The given id is not found");
	      }
	      
	      //response.sendRedirect("/books");
	    } catch (Exception e) {
	      throw new ServletException("Error deleting book", e);
	    }
	  }

		
}

