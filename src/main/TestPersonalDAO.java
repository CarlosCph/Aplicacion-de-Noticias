/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import dao.PersonalDAO;
import modelo.Personal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TestPersonalDAO {
    public static void main(String[] args) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = sdf.parse("2023-06-01");

            Personal p = new Personal("Ramirez", "Lopez", "Ana", "Calle Falsa 123", fecha);

            PersonalDAO dao = new PersonalDAO();

            
            boolean exito = dao.insertar(p);
            System.out.println("Inserción exitosa? " + exito);

            
            List<Personal> lista = dao.obtenerTodos();
            for (Personal persona : lista) {
                System.out.println(persona);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}