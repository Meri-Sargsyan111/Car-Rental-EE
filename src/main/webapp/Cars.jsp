<%@ page import="com.example.carrentalee.model.Car" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <title>Cars</title>
</head>

<body>

<h2>Add Car</h2>
<form method="post" action="cars">
    Brand: <input name="brand" required><br>
    Model: <input name="model" required><br>
    Year: <input type="number" name="year" required><br>
    Daily Rate: <input type="number" step="0.01" name="dailyRate" required><br>
    <button type="submit">Save</button>
</form>

<hr>

<h2>Cars List</h2>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Brand</th>
        <th>Model</th>
        <th>Year</th>
        <th>Daily Rate</th>
        <th>Status</th>
    </tr>

    <%
        List<Car> cars = (List<Car>) request.getAttribute("cars");

        if (cars != null && !cars.isEmpty()) {
            for (Car car : cars) {
    %>
    <tr>
        <td><%= car.getId() %></td>
        <td><%= car.getBrand() %></td>
        <td><%= car.getModel() %></td>
        <td><%= car.getYear() %></td>
        <td><%= car.getDailyRate() %></td>
        <td><%= car.getStatus() %></td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="6">Մեքենաներ դեռ չկան</td>
    </tr>
    <%
        }
    %>

</table>

<br>
<a href="index.jsp">Back</a>

</body>
</html>
