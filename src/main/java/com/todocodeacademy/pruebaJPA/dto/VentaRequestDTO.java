package com.todocodeacademy.pruebaJPA.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VentaRequestDTO {

    private Long codigoVenta;
    private LocalDate fechaVenta;
    private Long idCliente;
    private List<Long> listaCodigosProductos;

    public VentaRequestDTO() {
    }

    public VentaRequestDTO(Long codigoVenta, LocalDate fechaVenta, Long idCliente, List<Long> listaCodigosProductos) {
        this.codigoVenta = codigoVenta;
        this.fechaVenta = fechaVenta;
        this.idCliente = idCliente;
        this.listaCodigosProductos = listaCodigosProductos;
    }
}