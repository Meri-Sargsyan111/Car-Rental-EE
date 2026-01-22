package Servlet;

import Service.CarService;
import enums.CarStatus;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Car;

import java.io.IOException;
    @WebServlet("/cars")
    public class CarServlet extends HttpServlet {

        private final CarService carService = new CarService();

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                throws ServletException, IOException {

            req.setAttribute("cars", carService.getAllCars());
            req.getRequestDispatcher("/cars.jsp").forward(req, resp);
        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp)
                throws ServletException, IOException {

            Car car = new Car();
            car.setBrand(req.getParameter("brand"));
            car.setModel(req.getParameter("model"));
            car.setYear(Integer.parseInt(req.getParameter("year")));
            car.setDailyRate(Double.parseDouble(req.getParameter("dailyRate")));
            car.setStatus(CarStatus.AVAILABLE);

            carService.addCar(car);
            resp.sendRedirect("/cars");
        }
    }
