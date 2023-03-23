package com.innowise.employeeserviceee;

import com.innowise.employeeserviceee.dto.EmployeeDTO;
import com.innowise.employeeserviceee.service.EmployeeService;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

@WebServlet("/employees")
@AllArgsConstructor
@NoArgsConstructor
public class ControllerServlet extends HttpServlet {
    @EJB
    private EmployeeService employeeService;

    @Override
    @GET
    @Produces("text/plain")
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ControllerServlet.doGet");
        List<EmployeeDTO> allEmployees = employeeService.getAllEmployees();
        System.out.println(allEmployees);
//        response.setContentType("text/html");
//        PrintWriter writer = response.getWriter();
//        try{
//            String url = "jdbc:mysql://localhost:3306/my_db?useSSL=false";
//            String username = "bestuser";
//            String password = "bestuser";
//            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
//            try (Connection conn = DriverManager.getConnection(url, username, password)){
//
//                writer.println("Connection to ProductDB succesfull!");
//            }
//        }
//        catch(Exception ex){
//            writer.println("Connection failed...");
//            writer.println(ex);
//        }
//        finally {
//            writer.close();
//        }
    }

//        </persistence-unit>
//    <persistence-unit name="myPersistenceUnit" transaction-type="JTA">
//        <jta-data-source>java:/MyDS</jta-data-source>
//        <properties>
//            <property name="hibernate.connection.driver_class" value="com.mysql.jdbc.Driver" />
//            <property name="hibernate.connection.url" value="jdbc:mysql://localhost:3306/my_db?useSSL=false" />
//            <property name="hibernate.connection.username" value="bestuser" />
//            <property name="hibernate.connection.password" value="bestuser" />
//        </properties>
//    </persistence-unit>
//    @GET
//    @Produces("text/plain")
//    public String hello() {
//        return "Hello, from employee servlet!";
//    }
}