package com.todocodeacademy.pruebaJPA.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MayorVentaDTO {

    private Long codigoVenta;
    private Double total;
    private Integer cantidadProductos;
    private String nombreCliente;
    private String apellidoCliente;

    public MayorVentaDTO() {
    }

    public MayorVentaDTO(Long codigoVenta, Double total, Integer cantidadProductos, String nombreCliente, String apellidoCliente) {
        this.codigoVenta = codigoVenta;
        this.total = total;
        this.cantidadProductos = cantidadProductos;
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
    }
}