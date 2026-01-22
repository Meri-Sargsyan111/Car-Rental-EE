<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 17.01.2026
  Time: 21:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.carrentalee.model.Rental" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Rentals</title>
</head>
<body>

<h2>Create Rental</h2>

<form method="post" action="rentals">
    Car ID:
    <input type="number" name="carId" required><br><br>

    Customer ID:
    <input type="number" name="customerId" required><br><br>

    Start Date:
    <input type="date" name="startDate" required><br><br>

    End Date:
    <input type="date" name="endDate" required><br><br>

    Daily Rate:
    <input type="number" step="0.01" name="dailyRate" required><br><br>

    <button type="submit">Create Rental</button>
</form>


<%!
    private class Rental {
        public Object getId() {
            return null;
        }

        public Object getCarId() {
            return null;
        }

        public Object getCustomerId() {
            return null;
        }

        public Object getStartDate() {
            return null;
        }

        public Object getEndDate() {
            return null;
        }

        public Object getTotalCost() {
            return null;
        }

        public Object getStatus() {
            return null;
        }
    }
%><%
    List<Rental> rentals = (List<Rental>) request.getAttribute("rentals");
    if (rentals != null)
        for (Rental r : rentals) {
%>
<tr>
    <td><%= r.getId() %>
    </td>
    <td><%= r.getCarId() %>
    </td>
    <td><%= r.getCustomerId() %>
    </td>
    <td><%= r.getStartDate() %>
    </td>
    <td><%= r.getEndDate() %>
    </td>
    <td><%= r.getTotalCost() %>
    </td>
    <td><%= r.getStatus() %>
    </td>
</tr>
<% } %>

</table>

<br>
<a href="index.jsp">Back</a>

</body>
</html>

