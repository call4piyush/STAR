<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Login</title>
<link rel="stylesheet" href="css/style.css">
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<style>
body {
	background: url(./images/back.jpg) no-repeat center center fixed;
	background-size: cover;
}
</style>
</head>
<body>

	<div class="login-card " style="top: 25%">
		<img src="./images/logo.jpg" alt="Logo" align="right"> <br>
		<br> <br>
		<form action="Login" method="post">
			<table style="width: 100%">
				<tr>
					<td>User Name:</td>
					<td><input type="text" class="form-control" name="emp"
						placeholder="Enter User Name" required></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" class="form-control" name="pass"
						placeholder="Enter Password" required></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: right;"><a
						href="register.jsp" class="help"
						style="float: right; color: black; background-color: white">Sign
							Up</a></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;"><button
							type="submit" class="login login-submit" name="login">Sign
							In</button></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
