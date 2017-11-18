<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<body>
	<h2>Welcome to our banking APP</h2>

	<h3><%=request.getUserPrincipal().getName()%>
		<a href="<%=request.getContextPath()%>/logout">Logout </a>
	</h3>
	<hr>
	<a href="customers/show/all">Clientii nostri</a>

	<%
		if (request.isUserInRole("bankmanager")) {
	%>
	<a href="accounts/show/all">Conturile noastre</a>
	<a href="transactions/show/all">Tranzactiile noastre</a>
	<%
		}
	%>
	<br>
	<button id="btn">Adu clienti</button>
	<textarea id="cli" rows="15" cols="40"></textarea>
	<script type="text/javascript">
		var btn = document.getElementById('btn');
		var cli = document.getElementById('cli');

		var request = new XMLHttpRequest();

		request.onreadystatechange = function() {
			if (request.readyState === 4) {
				cli.style.border = '1px solid #e8e8e8';
				if (request.status === 200) {
					cli.value = request.responseText;
				} else {
					cli.value = 'An error occurred during your request: '
							+ request.status + ' ' + request.statusText;
				}
			}
		}

		request.open('GET', '/banking/rest/customers');

		btn.addEventListener('click', function() {
			this.style.display = 'none';
			request.send();
		});
	</script>
	
</body>
</html>
