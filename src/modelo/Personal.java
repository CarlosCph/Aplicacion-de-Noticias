/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Date;

public class Personal {
    private int idpersonal;
    private String apepaterno;
    private String apematerno;
    private String nombre;
    private String direccion;
    private Date fechadeingreso;

    
    public Personal() {
    }

    
    public Personal(String apepaterno, String apematerno, String nombre, String direccion, Date fechadeingreso) {
        this.apepaterno = apepaterno;
        this.apematerno = apematerno;
        this.nombre = nombre;
        this.direccion = direccion;
        this.fechadeingreso = fechadeingreso;
    }

    

    public int getIdpersonal() {
        return idpersonal;
    }

    public void setIdpersonal(int idpersonal) {
        this.idpersonal = idpersonal;
    }

    public String getApepaterno() {
        return apepaterno;
    }

    public void setApepaterno(String apepaterno) {
        this.apepaterno = apepaterno;
    }

    public String getApematerno() {
        return apematerno;
    }

    public void setApematerno(String apematerno) {
        this.apematerno = apematerno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechadeingreso() {
        return fechadeingreso;
    }

    public void setFechadeingreso(Date fechadeingreso) {
        this.fechadeingreso = fechadeingreso;
    }

    
    @Override
    public String toString() {
        return "Personal{" +
                "idpersonal=" + idpersonal +
                ", apepaterno='" + apepaterno + '\'' +
                ", apematerno='" + apematerno + '\'' +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", fechadeingreso=" + fechadeingreso +
                '}';
    }
}