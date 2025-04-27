/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.jdbc.ra9;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Usuario 1
 */

public class GestionBDD {

    private static String datosCon = "jdbc:mysql://localhost:3306/";
    private static String database = "alboraDataBase";
    private static String usuario = "root";
    private static String password = "";
    private Connection con;
    
   public GestionBDD(){

    
        try {
            con = DriverManager.getConnection(datosCon, usuario, password);
            createDB();
            createTableEmployee();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createDB() throws SQLException {
        String query = "CREATE DATABASE IF NOT EXISTS " + database;
        try (Statement stmt = con.createStatement()) {
            stmt.executeUpdate(query);
            con = DriverManager.getConnection(datosCon + database, usuario, password);
        }
    }

    public void createTableEmployee() throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS Employee ("
                + "id INT PRIMARY KEY AUTO_INCREMENT,"
                + "nombre VARCHAR(50),"
                + "edad INT,"
                + "departamento VARCHAR(50),"
                + "salario DOUBLE)";
        try (Statement stmt = con.createStatement()) {
            stmt.executeUpdate(query);
        }
    }

    public void insertarEmpleado(Empleado empleado) throws SQLException {
        String query = "INSERT INTO Employee(nombre, edad, departamento, salario) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, empleado.getNombre());
            pstmt.setInt(2, empleado.getEdad());
            pstmt.setString(3, empleado.getDepartamento());
            pstmt.setDouble(4, empleado.getSalario());
            pstmt.executeUpdate();
        }
    }

    public void actualizarEmpleado(Empleado empleado) throws SQLException {
        String query = "UPDATE Empleado SET nombre = ?, edad = ?, departamento = ?, salario = ? WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
           pstmt.setString(1, empleado.getNombre());
            pstmt.setInt(2, empleado.getEdad());
            pstmt.setString(3, empleado.getDepartamento());
            pstmt.setDouble(4, empleado.getSalario());
            pstmt.setInt(5, empleado.getId());
            pstmt.executeUpdate();
        }
    }

    public void borrarEmpleado(int id) throws SQLException {
        String query = "DELETE FROM Empleado WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    public ArrayList<Empleado> listarEmpleados() throws SQLException {
        String query = "SELECT * FROM Empleado";
        ArrayList<Empleado> employeeList = new ArrayList<>();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                employeeList.add(new Empleado(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("edad"),
                        rs.getString("departamento"),
                        rs.getDouble("salario")
                ));
            }
        }
        return employeeList;
    }

    // --- Métodos de interacción con el usuario ---

    public void ingresarEmpleado() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Ingrese nombre del empleado:");
            String name = sc.nextLine();
            System.out.println("Ingrese edad del empleado:");
            int age = sc.nextInt();
            sc.nextLine(); // limpiar buffer
            System.out.println("Ingrese departamento del empleado:");
            String department = sc.nextLine();
            System.out.println("Ingrese salario del empleado:");
            double salary = sc.nextDouble();

            Empleado empleado = new Empleado(0, name, age, department, salary);
            insertarEmpleado(empleado);
            System.out.println("Empleado ingresado correctamente.");
        } catch (Exception e) {
            System.out.println("Error al ingresar empleado.");
            e.printStackTrace();
        }
    }
        public boolean getEmpleadoId(Empleado employee) throws SQLException {
        boolean exist = false;
        String query = "select id from Employee;";
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Empleado> employeeList = new ArrayList<>();
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                
            }
            for (Empleado employee1 : employeeList) {
                if (employee1.getId() == employee.getId()) {
                    exist = true;
                    break;
                }
            }
            return exist;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            rs.close();
            stmt.close();
        }
        return exist;
    }

    public void actualizarEmpleado() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Ingrese ID del empleado a actualizar:");
            int id = sc.nextInt();
            sc.nextLine(); // limpiar buffer
            System.out.println("Ingrese nuevo nombre:");
            String name = sc.nextLine();
            System.out.println("Ingrese nueva edad:");
            int age = sc.nextInt();
            sc.nextLine();
            System.out.println("Ingrese nuevo departamento:");
            String department = sc.nextLine();
            System.out.println("Ingrese nuevo salario:");
            double salary = sc.nextDouble();

            Empleado empleado = new Empleado(id, name, age, department, salary);
            actualizarEmpleado(empleado);
            System.out.println("Empleado actualizado correctamente.");
        } catch (Exception e) {
            System.out.println("Error al actualizar empleado.");
            e.printStackTrace();
        }
    }

    public void borrarEmpleado() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Ingrese ID del empleado a borrar:");
            int id = sc.nextInt();
            borrarEmpleado(id);
            System.out.println("Empleado borrado correctamente.");
        } catch (Exception e) {
            System.out.println("Error al borrar empleado.");
            e.printStackTrace();
        }
    }

   
}
    

