<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h3>Google data store</h3>
	<p id='name'></p>
	
<h4>Create User:</h4>	
	username:
	<input type="text" id = "username" name= "name">
	email:
	<input type = "text" id = "mail" name = "email">
	<button type = "button" onclick="createUser()">Add</button>
	<br><br>
	
<h4>Get User</h4>
	Enter mail id:
	<input type = "text" id = "list" name = "mailid">
	<button type = "button" id = "list" onclick="listUser()">List User</button>
	<br><br>

<h4>Delete User</h4>
	Enter mail:
	<input type="text" id = "delmail" name= "delname">
	<button type = "button" onclick="deleteUsers()">delete</button>
	<br><br>
	
<h4>Update user</h4>
	Enter id:
	<input type = "text" id = "id" name = "id"><br><br>
	Enter Name:
	<input type = "text" id = "upname" name = "upname"><br><br>
	Enter Mail:
	<input type = "text" id = "upmail" name = "upmail"><br><br>
	
	<button type = "button" onclick="updateUsers()">Update</button>
	<br><br><br>
	
<h4>List All Users</h4>
	List all users:
	<button type = "button" id = "list" onclick="listAll()">List All</button>
	<br><br>
	
	<script>
	function createUser()
	{
	var xhr = new XMLHttpRequest();

	var name = document.getElementById("username").value;
	var email = document.getElementById("mail").value;
	
    xhr.open('POST','http://localhost:8080/ControllerServlet', true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    
    xhr.send("name="+name+"&email="+email);


    xhr.onload = function() {
        
    	
        let responseobj = this.response;
        if (this.status == 200) {
            document.getElementById('name').innerHTML = responseobj;
        } else if (this.status == 404) {
            document.getElementById('name').innerHTML = '<h1>Not Found -- 404 Error</h1>'
        }
    }
    
	}
	
	
	function listUser()
	{
	var xhr = new XMLHttpRequest();

    xhr.open('GET','http://localhost:8080/ControllerServlet?mail='+document.getElementById("list").value, true);

    xhr.send();
    
    xhr.onload = function() {
        
    	
        let responseobj = this.response;
        if (this.status == 200) {
            document.getElementById('name').innerHTML = responseobj;
        } else if (this.status == 404) {
            document.getElementById('name').innerHTML = '<h1>Not Found -- 404 Error</h1>'
        }
    }
    
	}
	
	
	function deleteUsers()
	{
	var xhr = new XMLHttpRequest();

	//var delid = document.getElementById("delid").value;
	
	
    xhr.open('DELETE','http://localhost:8080/ControllerServlet?delmail='+document.getElementById("delmail").value, true);
    
    xhr.send();
    
    xhr.onload = function() {
        
    	
        let responseobj = this.response;
        if (this.status == 200) {
            document.getElementById('name').innerHTML = responseobj;
        } else if (this.status == 404) {
            document.getElementById('name').innerHTML = '<h1>Not Found -- 404 Error</h1>'
        }
    }
    
	}
	
	function updateUsers()
	{
	var xhr = new XMLHttpRequest();

	
	
    xhr.open('PUT','http://localhost:8080/ControllerServlet?id='+document.getElementById("id").value+'&mail='+document.getElementById("upmail").value+'&name='+document.getElementById("upname").value, true);

    xhr.send();
    
    xhr.onload = function() {
        
    	
        let responseobj = this.response;
        if (this.status == 200) {
            document.getElementById('name').innerHTML = responseobj;
        } else if (this.status == 404) {
            document.getElementById('name').innerHTML = '<h1>Not Found -- 404 Error</h1>'
        }
    }
    	
	}
	
	
/*	
	function choiceMail()
	{
	var xhr = new XMLHttpRequest();

	//var name = document.getElementById("username").value;
	var choice = document.getElementById("choice").value;
	//var email = "b";
	//var params = "name="+name"&email="+email;
	
    xhr.open('PUT','http://localhost:8080/ControllerServlet', true);
    //xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    
    //xhr.send("email="+email);
    xhr.send("choice="+choice);
    
	}
*/
/*
    xhr.onload = function() {
        
    	
        let responseobj = this.response;
        if (this.status == 200) {
            document.getElementById('name').innerHTML = responseobj;
        } else if (this.status == 404) {
            document.getElementById('name').innerHTML = '<h1>Not Found -- 404 Error</h1>'
        }
    }
    
	}*/
	
	
	
	function listAll()
	{
	var xhr = new XMLHttpRequest();
	
    xhr.open('GET','http://localhost:8080/ControllerServlet', true);

    xhr.send();
    
    xhr.onload = function() {
        
    	
        let responseobj = this.response;
        if (this.status == 200) {
            document.getElementById('name').innerHTML = responseobj;
        } else if (this.status == 404) {
            document.getElementById('name').innerHTML = '<h1>Not Found -- 404 Error</h1>'
        }
    }
    
	}



	</script>

</body>
</html>