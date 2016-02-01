<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
<link rel="stylesheet" href="./css/style.css">
</head>
<body>
	<div class="login-card">
		<img src="./images/logo.jpg" alt="Logo"> <br>
		<form action="Register" method="post">

			<input type="text" name="user" placeholder="User Name" required>
			<input type="password" name="pass" placeholder="Password" required>
			<input type="submit" name="register" class="login login-submit"
				value="Create User">
		</form>
	</div>
</body>
</html>