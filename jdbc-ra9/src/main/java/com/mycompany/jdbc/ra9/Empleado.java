/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.jdbc.ra9;

/**
 *
 * @author Usuario 1
 */

public class Empleado {
    private int id;
    private String nombre;
    private int edad;
    private String departamento;
    private double salario;

    // Constructor
    public Empleado(int id, String nombre, int edad, String departamento, double salario) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.departamento = departamento;
        this.salario = salario;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nombre: " + nombre + ", Edad: " + edad + 
               ", Departamento: " + departamento + ", Salario: " + salario;
    }
}