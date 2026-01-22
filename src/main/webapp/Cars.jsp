<%@ page import="model.Car" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Cars</title>
</head>

<h2>Add Car</h2>
<form method="post" action="cars">
    Brand: <input name="brand" required><br>
    Model: <input name="model" required><br>
    Year: <input type="number" name="year" required><br>
    Daily Rate: <input type="number" step="0.01" name="dailyRate" required><br>
    <button type="submit">Save</button>
</form>

<hr>
<body>

<%
    List<Car> cars = (List<Car>) request.getAttribute("cars");
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
<% } %>


<br>
<a href="index.jsp">Back</a>

</body>
</html>