/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

public class Usuario {
    private int idusuario;
    private String correo;
    private String password;
    private String tipo;

    public Usuario() {}

    public Usuario(int idusuario, String correo, String password, String tipo) {
        this.idusuario = idusuario;
        this.correo = correo;
        this.password = password;
        this.tipo = tipo;
    }

    public Usuario(String correo, String password, String tipo) {
        this.correo = correo;
        this.password = password;
        this.tipo = tipo;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
