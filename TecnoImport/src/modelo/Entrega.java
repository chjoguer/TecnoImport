/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Christian Guerrero
 */
public class Entrega {
    private Integer identrga;
    private String descripcion;
    private String direccion;
    private String nombre;
    private String apellido;

    public Entrega(Integer identrga, String descripcion, String direccion, String nombre, String apellido) {
        this.identrga = identrga;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Integer getIdentrga() {
        return identrga;
    }

    public void setIdentrga(Integer identrga) {
        this.identrga = identrga;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    
}
