<%@ page import="com.example.carrentalee.model.Customer" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
  <title>Customers</title>
</head>

<body>

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

<h2>Customers List</h2>

<table border="1">
  <tr>
    <th>ID</th>
    <th>Name</th>
    <th>Surname</th>
    <th>License</th>
    <th>Phone</th>
    <th>Email</th>
  </tr>

  <%
    List<Customer> customers = (List<Customer>) request.getAttribute("customers");

    if (customers != null && !customers.isEmpty()) {
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
  <%
    }
  } else {
  %>
  <tr>
    <td colspan="6">Հաճախորդներ դեռ չկան</td>
  </tr>
  <%
    }
  %>

</table>

<br>
<a href="index.jsp">Back</a>

</body>
</html>
