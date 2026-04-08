package com.todocodeacademy.pruebaJPA.service;

import com.todocodeacademy.pruebaJPA.dto.MayorVentaDTO;
import com.todocodeacademy.pruebaJPA.dto.VentaPorFechaDTO;
import com.todocodeacademy.pruebaJPA.dto.VentaRequestDTO;
import com.todocodeacademy.pruebaJPA.model.Producto;
import com.todocodeacademy.pruebaJPA.model.Venta;
import java.time.LocalDate;
import java.util.List;

public interface IVentaService {

    public Venta saveVenta(VentaRequestDTO ventaDto);

    public List<Venta> getVentas();

    public Venta findVenta(Long codigoVenta);

    public void deleteVenta(Long codigoVenta);

    public Venta editVenta(Long codigoVenta, VentaRequestDTO ventaDto);

    public List<Producto> getProductosDeVenta(Long codigoVenta);

    public VentaPorFechaDTO getResumenVentasPorFecha(LocalDate fechaVenta);

    public MayorVentaDTO getMayorVenta();

    public List<Venta> getVentasPorCliente(Long idCliente);
}