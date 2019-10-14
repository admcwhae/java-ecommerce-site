/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed.shop.jdbc;

import java.sql.Date;
import java.util.ArrayList;
import util.MyHash;

/**
 *
 * @author amcwhae
 */
public class SetUpDB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ProductDB productDB = new ProductDB();
        EmployeeDB employeeDB = new EmployeeDB();
        OrderDB orderDB = new OrderDB();
        OrderlineDB orderlineDB = new OrderlineDB();

        orderlineDB.dropOrderlineTable();
        productDB.dropProductTable();
        orderDB.dropOrderTable();
        employeeDB.dropEmployeeTable();

        employeeDB.createEmployeeTable();
        orderDB.createOrderTable();
        productDB.createProductTable();
        orderlineDB.createOrderlineTable();

        ArrayList<Employee> employeeList = prepareEmployeeData();
        employeeDB.addRecords(employeeList);

        ArrayList<Order> orderList = prepareOrderData();
        orderDB.addRecords(orderList);

        ArrayList<Product> productList = prepareProductData();
        productDB.addRecords(productList);

        ArrayList<Orderline> orderlineList = prepareOrderlineData();
        orderlineDB.addRecords(orderlineList);
    }

    public static ArrayList<Employee> prepareEmployeeData() {
        ArrayList<Employee> employeeList = new ArrayList<>();

        String password = MyHash.getSHA256HashedString("password");
                
        Employee employee1 = new Employee("admin", "Alex Admin", password, "SHOP-ADMIN");
        Employee employee2 = new Employee("manager", "Manager Man", password, "SHOP-MANAGER");
        Employee employee3 = new Employee("employee", "Erin Employee", password, "SHOP-EMPLOYEE");

        employeeList.add(employee1);
        employeeList.add(employee2);
        employeeList.add(employee3);

        return employeeList;
    }

    public static ArrayList<Order> prepareOrderData() {
        ArrayList<Order> orderList = new ArrayList<>();

        Order order1 = new Order(1, "Jacotin Dieulafoy", "Palais de Bannois", "Kingdom of France", "processing", new Date(6,6,2019));
        Order order2 = new Order(2, "Richard III", "Castle of York", "Kingdom of Britain", "sent", new Date(4,6,2019));

        orderList.add(order1);
        orderList.add(order2);

        return orderList;
    }

    public static ArrayList<Product> prepareProductData() {
        ArrayList<Product> productList = new ArrayList<>();
        String imagesFilePath = "images/";
        String description;
        String thumbnail;

        description = "This is the trebuchet.";
        thumbnail = imagesFilePath + "thumb_trebuchet.png";
        Product product1 = new Product(1, "Trebuchet", thumbnail, thumbnail, description, 200, 200, 5);

        description = "This is the onager.";
        thumbnail = imagesFilePath + "thumb_onager.png";
        Product product2 = new Product(2, "Onager", thumbnail, thumbnail, description, 160, 135, 10);

        description = "This is the siege ram.";
        thumbnail = imagesFilePath + "thumb_ram.png";
        Product product3 = new Product(3, "Siege Ram", thumbnail, thumbnail, description, 160, 75, 20);

        description = "This is the scorpion.";
        thumbnail = imagesFilePath + "thumb_scorpion.png";
        Product product4 = new Product(4, "Scorpion", thumbnail, thumbnail, description, 75, 75, 15);

        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
        productList.add(product4);

        return productList;
    }

    public static ArrayList<Orderline> prepareOrderlineData() {
        ArrayList<Orderline> orderlineList = new ArrayList<>();

        Orderline orderline1 = new Orderline(1, 1, 1, 3);
        Orderline orderline2 = new Orderline(1, 3, 1, 5);
        Orderline orderline3 = new Orderline(1, 4, 2, 10);
        
        orderlineList.add(orderline1);
        orderlineList.add(orderline2);
        orderlineList.add(orderline3);

        return orderlineList;
    }

}
