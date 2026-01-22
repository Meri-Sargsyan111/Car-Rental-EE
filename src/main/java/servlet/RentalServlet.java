package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Rental;
import service.RentalService;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/rentals")
public class RentalServlet extends HttpServlet {

    private final RentalService rentalService = new RentalService();

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
}