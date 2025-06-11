/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Date;

public class Comentario {
    private int idcomentario;
    private int idnoticia;
    private int idusuario;
    private String contenido;
    private Date fecha;
    private Integer idrespuesta;  // Puede ser null si es comentario principal (no respuesta)
    private String tipoUsuario;   // interno o externo

    public Comentario() {}

    // Constructor para comentario normal (sin respuesta)
    public Comentario(int idnoticia, int idusuario, String contenido, Date fecha) {
        this.idnoticia = idnoticia;
        this.idusuario = idusuario;
        this.contenido = contenido;
        this.fecha = fecha;
        this.idrespuesta = null;
    }

    // Constructor para respuesta (con idrespuesta)
    public Comentario(int idnoticia, int idusuario, String contenido, Date fecha, Integer idrespuesta) {
        this.idnoticia = idnoticia;
        this.idusuario = idusuario;
        this.contenido = contenido;
        this.fecha = fecha;
        this.idrespuesta = idrespuesta;
    }

    

    public int getIdcomentario() {
        return idcomentario;
    }

    public void setIdcomentario(int idcomentario) {
        this.idcomentario = idcomentario;
    }

    public int getIdnoticia() {
        return idnoticia;
    }

    public void setIdnoticia(int idnoticia) {
        this.idnoticia = idnoticia;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getIdrespuesta() {
        return idrespuesta;
    }

    public void setIdrespuesta(Integer idrespuesta) {
        this.idrespuesta = idrespuesta;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}