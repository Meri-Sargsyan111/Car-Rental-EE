package service;

import db.DBConnectionProvider;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {

    private final Connection conn = DBConnectionProvider.getInstance().getConnection();

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        try (Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM customer");
            while (rs.next()) {
                customers.add(new Customer(
                        rs.getInt("ID"),
                        rs.getString("NAME"),
                        rs.getString("SURNAME"),
                        rs.getString("LICENSE_NUMBER"),
                        rs.getString("PHONE"),
                        rs.getString("EMAIL"),
                        rs.getString("STATUS")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
    }

    public void addCustomer(Customer c) {
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO customer(name, surname, license_number, phone, email, status) VALUES (?,?,?,?,?,?)"
            );
            ps.setString(1, c.getName());
            ps.setString(2, c.getSurname());
            ps.setString(3, c.getLicenseNumber());
            ps.setString(4, c.getPhone());
            ps.setString(5, c.getEmail());
            ps.setString(6, c.getStatus());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
