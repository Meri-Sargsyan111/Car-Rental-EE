package Service;

import db.DBConnectionProvider;
import enums.CarStatus;
import model.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class CarService {
    private final Connection conn = DBConnectionProvider.getInstance().getConnection();

    public List<Car> getAllCars() {
        List<Car> cars = new ArrayList<>();
        try (Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM car");
            while (rs.next()) {
                cars.add(new Car(
                        rs.getInt("ID"),
                        rs.getString("BREND"),
                        rs.getString("MODEL"),
                        rs.getInt("YEAR"),
                        rs.getDouble("DAILY_RATE"),
                        CarStatus.valueOf(rs.getString("STATUS"))
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cars;
    }

    public void addCar(Car car) {
        try{
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO car (BREND, MODEL, YEAR, DAILY_RATE, STATUS) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, car.getBrand());
            ps.setString(2, car.getModel());
            ps.setInt(3, car.getYear());
            ps.setDouble(4, car.getDailyRate());
            ps.setString(5, car.getStatus().name());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
