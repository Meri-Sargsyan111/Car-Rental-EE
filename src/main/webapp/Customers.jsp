<%@ page import="model.Customer" %>
<%@ page import="java.util.List" %>
<html>
<head>
  <title>Customers</title>
</head>

<h2>Add Customer</h2>
<form method="post" action="customers">
  Name: <input name="name" required><br>
  Surname: <input name="surname" required><br>
  License: <input name="license" required><br>
  Phone: <input name="phone" required><br>
  Email: <input name="email" required><br>
  <button type="submit">Save</button>
</form>
<hr>

<body>


<%
  List<Customer> customers = (List<Customer>) request.getAttribute("customers");
  for (Customer c : customers) {
%>
<tr>
  <td><%= c.getId() %></td>
  <td><%= c.getName() %></td>
  <td><%= c.getSurname() %></td>
  <td><%= c.getLicenseNumber() %></td>
  <td><%= c.getPhone() %></td>
  <td><%= c.getEmail() %></td>
</tr>
<% } %>

</table>

<br>
<a href="index.jsp">Back</a>

</body>
</html>