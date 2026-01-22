package service;

import db.DBConnectionProvider;
import model.Rental;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class RentalService {

    private final Connection conn = DBConnectionProvider.getInstance().getConnection();

    public double calculateTotalCost(double dailyRate, LocalDate start, LocalDate end) {
        long days = ChronoUnit.DAYS.between(start, end);
        if (days == 0) days = 1;
        return days * dailyRate;
    }

    public void addRental(Rental r, double dailyRate) {
        try {
            double total = calculateTotalCost(dailyRate, r.getStartDate(), r.getEndDate());

            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO rental(car_id, customer_id, start_date, end_date, total_cost, status) VALUES (?,?,?,?,?,?)"
            );
            ps.setInt(1, r.getCarId());
            ps.setInt(2, r.getCustomerId());
            ps.setDate(3, Date.valueOf(r.getStartDate()));
            ps.setDate(4, Date.valueOf(r.getEndDate()));
            ps.setDouble(5, total);
            ps.setString(6, String.valueOf(r.getStatus()));
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

