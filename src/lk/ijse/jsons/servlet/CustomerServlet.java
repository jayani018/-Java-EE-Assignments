package lk.ijse.jsons.servlet;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;


@WebServlet(urlPatterns = {"/pages/customer"})
public class CustomerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignmentcompany", "root", "1234");
            PreparedStatement pstm = connection.prepareStatement("select * from Customer");
            ResultSet rst = pstm.executeQuery();

            // resp.addHeader("Content-Type","application/json");

//            String json="[";
//            while (rst.next()) {
//                String customer="{";
//                String id = rst.getString(1);
//                String name = rst.getString(2);
//                String address = rst.getString(3);
//                customer+="\"id\":\""+id+"\",";
//                customer+="\"name\":\""+name+"\",";
//                customer+="\"address\":\""+address+"\"";
//                customer+="},";
//                json+=customer;
//            }
//            json=json+"]";
//
//            resp.getWriter().print(json.substring(0,json.length()-2)+"]");
            JsonArrayBuilder allCustomer = Json.createArrayBuilder();
            while (rst.next()) {
                String id = rst.getString(1);
                String name = rst.getString(2);
                String address = rst.getString(3);

                JsonObjectBuilder customerObject = Json.createObjectBuilder();
                customerObject.add("id", id);
                customerObject.add("name", name);
                customerObject.add("address", address);

                allCustomer.add(customerObject.build());
            }
            resp.getWriter().print(allCustomer.build());

            //add customer


//
//            req.setAttribute("keyOne", allCustomers);
//
//            req.getRequestDispatcher("customer.html").forward(req, resp);

            // create a json response including customer data
            // String s= "[{id:""}]";
            //send the output through the ajax response
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cusID = req.getParameter("cusID");
        String cusName = req.getParameter("cusName");
        String cusAddress = req.getParameter("cusAddress");
//        String cusSalary = req.getParameter("cusSalary");
//        String option = req.getParameter("option");

//        resp.addHeader("Content-Type","application/json");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignmentcompany", "root", "1234");

            PreparedStatement pstm = connection.prepareStatement("insert into Customer values(?,?,?)");
                    pstm.setObject(1, cusID);
                    pstm.setObject(2, cusName);
                    pstm.setObject(3, cusAddress);
//                    pstm.setObject(4, 1000.00);

            resp.addHeader("Content-Type", "application/json");

            if (pstm.executeUpdate() > 0) {
                        JsonObjectBuilder response = Json.createObjectBuilder();
                        response.add("state","OK");
                        response.add("message","Sucessfully Added..!");
                        response.add("data","");
                        resp.getWriter().print(response.build());
                    }
//            switch (option) {
//                case "add":
//                    PreparedStatement pstm = connection.prepareStatement("insert into Customer values(?,?,?)");
//                    pstm.setObject(1, cusID);
//                    pstm.setObject(2, cusName);
//                    pstm.setObject(3, cusAddress);
//                  //  pstm.setObject(4, 1000.00);
//                    resp.addHeader("Content-Type", "application/json");
//
//                    if (pstm.executeUpdate() > 0) {
//                        JsonObjectBuilder response = Json.createObjectBuilder();
//                        response.add("state", "OK");
//                        response.add("message", "Sucessfully Added..!");
//                        response.add("data", "");
//                        resp.getWriter().print(response.build());
//                    }
//                    break;
//                case "delete":
//                    PreparedStatement pstm2 = connection.prepareStatement("delete from Customer where id=?");
//                    pstm2.setObject(1, cusID);
//                    if (pstm2.executeUpdate() > 0) {
//                        resp.getWriter().println("Customer Deleted..!");
//                    }
//                    break;
//                case "update":
//                    PreparedStatement pstm3 = connection.prepareStatement("update Customer set name=?,address=? where id=?");
//                    pstm3.setObject(3, cusID);
//                    pstm3.setObject(1, cusName);
//                    pstm3.setObject(2, cusAddress);
//                    if (pstm3.executeUpdate() > 0) {
//                        resp.getWriter().println("Customer Updated..!");
//                    }
//                    break;
           // }

//            resp.sendRedirect("/jsonp/pages/customer");

        } catch (ClassNotFoundException e) {
//            JsonObjectBuilder response = Json.createObjectBuilder();
//            response.add("status",500);
//            response.add("message","Error");
//            response.add("data","");
//            resp.getWriter().print(response.build());
//            resp.getWriter().print(response.build());
//            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            throw new RuntimeException(e);

        } catch (SQLException e) {
            JsonObjectBuilder response = Json.createObjectBuilder();
            response.add("state", "Error");
            response.add("message", e.getMessage());
            response.add("data", "");
            resp.setStatus(400);
            resp.getWriter().print(response.build());
//            resp.getWriter().print(response.build());
//            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        }
    }

    public void doPut(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        String cusID = req.getParameter("cusID");
        String cusName = req.getParameter("cusName");
        String cusAddress = req.getParameter("cusAddress");
//        String cusSalary = req.getParameter("cusSalary");
//        String option = req.getParameter("option");

        resp.addHeader("Content-Type", "application/json");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignmentcompany", "root", "1234");
            PreparedStatement pstm3 = connection.prepareStatement("update Customer set name=?,address=? where id=?");
            pstm3.setObject(3, cusID);
            pstm3.setObject(1, cusName);
            pstm3.setObject(2, cusAddress);
            if (pstm3.executeUpdate() > 0) {
                JsonObjectBuilder response = Json.createObjectBuilder();
                response.add("state","OK");
                response.add("message","Sucessfully Added..!");
                response.add("data","");
                resp.getWriter().print(response.build());
//                resp.getWriter().println("Customer Updated..!");
            }

        } catch (ClassNotFoundException e ) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            JsonObjectBuilder response = Json.createObjectBuilder();
            response.add("state", "Error");
            response.add("message", e.getMessage());
            response.add("data", "");
            resp.setStatus(400);
            resp.getWriter().print(response.build());
        }

    }
    public void doDelete(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        String cusID = req.getParameter("cusID");

        resp.addHeader("Content-Type", "application/json");

//        System.out.println("delete");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignmentcompany", "root", "1234");
            PreparedStatement pstm2 = connection.prepareStatement("delete from Customer where id=?");
            pstm2.setObject(1, cusID);

            if (pstm2.executeUpdate() > 0) {
                resp.getWriter().print("customer Deleted");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            JsonObjectBuilder response = Json.createObjectBuilder();
            response.add("state", "Error");
            response.add("message", e.getMessage());
            response.add("data", "");
            resp.setStatus(400);
            resp.getWriter().print(response.build());

        }
    }
//            resp.addHeader("Content-Type", "application/json");
//

//
//
//
//            if (pstm2.executeUpdate() > 0) {
//                resp.getWriter().print(
//                        Json.createObjectBuilder()
//                                .add("state", "Ok")
//                                .add("message", "Successfully Deleted...!")
//                                .add("data", "[]")
//                                .build()
//                );
////                resp.getWriter().println("Customer Deleted..!");
//            }
//        } catch (ClassNotFoundException | SQLException | IOException e) {
//            resp.addHeader("Content-Type", "application/json");
//            resp.setStatus(400);
//            resp.getWriter().print(
//                    Json.createObjectBuilder()
//                            .add("state", "Error")
//                            .add("message", e.getMessage())
//                            .add("data", "[]")
//                            .build()
//            );
//            e.printStackTrace();
//        }
//
//
//    }
//}
}
