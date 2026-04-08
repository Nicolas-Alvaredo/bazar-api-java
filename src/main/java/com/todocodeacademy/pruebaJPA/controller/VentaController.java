package com.todocodeacademy.pruebaJPA.controller;

import com.todocodeacademy.pruebaJPA.dto.MayorVentaDTO;
import com.todocodeacademy.pruebaJPA.dto.VentaPorFechaDTO;
import com.todocodeacademy.pruebaJPA.dto.VentaRequestDTO;
import com.todocodeacademy.pruebaJPA.model.Producto;
import com.todocodeacademy.pruebaJPA.model.Venta;
import com.todocodeacademy.pruebaJPA.service.IVentaService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ventas")
@CrossOrigin(origins = "*")
public class VentaController {

    @Autowired
    private IVentaService ventaServ;

    @PostMapping("/crear")
    public Venta crearVenta(@RequestBody VentaRequestDTO ventaDto) {
        return ventaServ.saveVenta(ventaDto);
    }

    @GetMapping
    public List<Venta> traerVentas() {
        return ventaServ.getVentas();
    }

    @GetMapping("/{codigo_venta}")
    public Venta traerVenta(@PathVariable("codigo_venta") Long codigoVenta) {
        return ventaServ.findVenta(codigoVenta);
    }

    @DeleteMapping("/eliminar/{codigo_venta}")
    public String eliminarVenta(@PathVariable("codigo_venta") Long codigoVenta) {
        ventaServ.deleteVenta(codigoVenta);
        return "Venta eliminada correctamente";
    }

    @PutMapping("/editar/{codigo_venta}")
    public Venta editarVenta(@PathVariable("codigo_venta") Long codigoVenta,
                             @RequestBody VentaRequestDTO ventaDto) {
        return ventaServ.editVenta(codigoVenta, ventaDto);
    }

    @GetMapping("/productos/{codigo_venta}")
    public List<Producto> traerProductosDeVenta(@PathVariable("codigo_venta") Long codigoVenta) {
        return ventaServ.getProductosDeVenta(codigoVenta);
    }

    @GetMapping("/fecha/{fecha_venta}")
    public VentaPorFechaDTO traerResumenPorFecha(
            @PathVariable("fecha_venta")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaVenta) {
        return ventaServ.getResumenVentasPorFecha(fechaVenta);
    }

    @GetMapping("/mayor_venta")
    public MayorVentaDTO traerMayorVenta() {
        return ventaServ.getMayorVenta();
    }

    // BONUS
    @GetMapping("/cliente/{id_cliente}")
    public List<Venta> traerVentasPorCliente(@PathVariable("id_cliente") Long idCliente) {
        return ventaServ.getVentasPorCliente(idCliente);
    }
}