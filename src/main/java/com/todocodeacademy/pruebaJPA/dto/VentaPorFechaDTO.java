package com.todocodeacademy.pruebaJPA.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VentaPorFechaDTO {

    private LocalDate fechaVenta;
    private Long cantidadVentas;
    private Double montoTotal;

    public VentaPorFechaDTO() {
    }

    public VentaPorFechaDTO(LocalDate fechaVenta, Long cantidadVentas, Double montoTotal) {
        this.fechaVenta = fechaVenta;
        this.cantidadVentas = cantidadVentas;
        this.montoTotal = montoTotal;
    }
}