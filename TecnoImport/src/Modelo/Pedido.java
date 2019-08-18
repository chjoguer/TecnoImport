/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Christian Guerrero
 */
public class Pedido {
    Integer idpedido;
    String atriculo;
    Integer cantidad;
    String direccion;

    public Pedido(Integer idpedido, String atriculo, Integer cantidad, String direccion) {
        this.idpedido = idpedido;
        this.atriculo = atriculo;
        this.cantidad = cantidad;
        this.direccion = direccion;
    }

    public Integer getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(Integer idpedido) {
        this.idpedido = idpedido;
    }

    public String getAtriculo() {
        return atriculo;
    }

    public void setAtriculo(String atriculo) {
        this.atriculo = atriculo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    
}
