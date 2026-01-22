package Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/rentals")
public class RentalServlet extends HttpServlet {

    private final RentalService rentalService;

    public RentalServlet() {
        rentalService = new RentalService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/rentals.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Rental r = new Rental();
        r.setCarId(Integer.parseInt(req.getParameter("carId")));
        r.setCustomerId(Integer.parseInt(req.getParameter("customerId")));
        r.setStartDate(LocalDate.parse(req.getParameter("startDate")));
        r.setEndDate(LocalDate.parse(req.getParameter("endDate")));


        double dailyRate = Double.parseDouble(req.getParameter("dailyRate"));

        rentalService.addRental(r, dailyRate);
        resp.sendRedirect("/rentals");
    }

    private class RentalService {
        public void addRental(Rental r, double dailyRate) {

        }
    }

    private class Rental {
        private int carId;
        private int customerId;
        private LocalDate endDate;
        private LocalDate startDate;

        public void setCarId(int carId) {
            this.carId = carId;
        }

        public int getCarId() {
            return carId;
        }

        public void setCustomerId(int customerId) {

            this.customerId = customerId;
        }

        public int getCustomerId() {
            return customerId;
        }

        public void setEndDate(LocalDate endDate) {
            this.endDate = endDate;
        }

        public void setStartDate(LocalDate startDate) {
            this.startDate = startDate;
        }

        public LocalDate getStartDate() {
            return startDate;
        }
    }
}
