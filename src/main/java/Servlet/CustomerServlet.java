package Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Customer;

import java.io.IOException;

@WebServlet("/customers")
public class CustomerServlet extends HttpServlet {

    private final CustomerService customerService = new CustomerService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("customers", customerService.getAllCustomers());
        req.getRequestDispatcher("/customers.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Customer c = new Customer();
        c.setName(req.getParameter("name"));
        c.setSurname(req.getParameter("surname"));
        c.setLicenseNumber(req.getParameter("license"));
        c.setPhone(req.getParameter("phone"));
        c.setEmail(req.getParameter("email"));

        customerService.addCustomer(c);
        resp.sendRedirect("/customers");

    }

    private class CustomerService {
        public Object getAllCustomers() {
            return null;
        }

        public void addCustomer(Customer c) {

        }
    }
}
