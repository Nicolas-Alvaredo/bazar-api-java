package com.todocodeacademy.pruebaJPA.service;

import com.todocodeacademy.pruebaJPA.dto.MayorVentaDTO;
import com.todocodeacademy.pruebaJPA.dto.VentaPorFechaDTO;
import com.todocodeacademy.pruebaJPA.dto.VentaRequestDTO;
import com.todocodeacademy.pruebaJPA.model.Cliente;
import com.todocodeacademy.pruebaJPA.model.Producto;
import com.todocodeacademy.pruebaJPA.model.Venta;
import com.todocodeacademy.pruebaJPA.repository.IClienteRepository;
import com.todocodeacademy.pruebaJPA.repository.IProductoRepository;
import com.todocodeacademy.pruebaJPA.repository.IVentaRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaService implements IVentaService {

    @Autowired
    private IVentaRepository ventaRepo;

    @Autowired
    private IClienteRepository clienteRepo;

    @Autowired
    private IProductoRepository productoRepo;

    @Override
    public Venta saveVenta(VentaRequestDTO ventaDto) {
        Cliente cliente = clienteRepo.findById(ventaDto.getIdCliente()).orElse(null);

        if (cliente == null) {
            return null;
        }

        List<Producto> productos = obtenerProductosDesdeCodigos(ventaDto.getListaCodigosProductos());

        if (productos.isEmpty()) {
            return null;
        }

        if (!hayStockSuficiente(productos)) {
            throw new RuntimeException("No hay stock suficiente para concretar la venta.");
        }

        Double total = calcularTotal(productos);

        Venta venta = new Venta();
        venta.setCodigoVenta(ventaDto.getCodigoVenta());
        venta.setFechaVenta(ventaDto.getFechaVenta());
        venta.setUnCliente(cliente);
        venta.setListaProductos(productos);
        venta.setTotal(total);

        descontarStock(productos);

        return ventaRepo.save(venta);
    }

    @Override
    public List<Venta> getVentas() {
        return ventaRepo.findAll();
    }

    @Override
    public Venta findVenta(Long codigoVenta) {
        return ventaRepo.findById(codigoVenta).orElse(null);
    }

    @Override
    public void deleteVenta(Long codigoVenta) {
        Venta venta = this.findVenta(codigoVenta);

        if (venta != null) {
            devolverStock(venta.getListaProductos());
            ventaRepo.deleteById(codigoVenta);
        }
    }

    @Override
    public Venta editVenta(Long codigoVenta, VentaRequestDTO ventaDto) {
        Venta ventaExistente = this.findVenta(codigoVenta);

        if (ventaExistente == null) {
            return null;
        }

        devolverStock(ventaExistente.getListaProductos());

        Cliente cliente = clienteRepo.findById(ventaDto.getIdCliente()).orElse(null);

        if (cliente == null) {
            return null;
        }

        List<Producto> productos = obtenerProductosDesdeCodigos(ventaDto.getListaCodigosProductos());

        if (productos.isEmpty()) {
            return null;
        }

        if (!hayStockSuficiente(productos)) {
            throw new RuntimeException("No hay stock suficiente para editar la venta.");
        }

        Double total = calcularTotal(productos);

        ventaExistente.setFechaVenta(ventaDto.getFechaVenta());
        ventaExistente.setUnCliente(cliente);
        ventaExistente.setListaProductos(productos);
        ventaExistente.setTotal(total);

        descontarStock(productos);

        return ventaRepo.save(ventaExistente);
    }

    @Override
    public List<Producto> getProductosDeVenta(Long codigoVenta) {
        Venta venta = this.findVenta(codigoVenta);

        if (venta != null) {
            return venta.getListaProductos();
        }

        return new ArrayList<>();
    }

    @Override
    public VentaPorFechaDTO getResumenVentasPorFecha(LocalDate fechaVenta) {
        List<Venta> ventasDelDia = ventaRepo.findByFechaVenta(fechaVenta);

        Long cantidadVentas = (long) ventasDelDia.size();
        Double montoTotal = 0.0;

        for (Venta venta : ventasDelDia) {
            montoTotal += venta.getTotal();
        }

        return new VentaPorFechaDTO(fechaVenta, cantidadVentas, montoTotal);
    }

    @Override
    public MayorVentaDTO getMayorVenta() {
        Venta mayorVenta = ventaRepo.findTopByOrderByTotalDesc();

        if (mayorVenta == null) {
            return null;
        }

        return new MayorVentaDTO(
                mayorVenta.getCodigoVenta(),
                mayorVenta.getTotal(),
                mayorVenta.getListaProductos().size(),
                mayorVenta.getUnCliente().getNombre(),
                mayorVenta.getUnCliente().getApellido()
        );
    }

    @Override
    public List<Venta> getVentasPorCliente(Long idCliente) {
        return ventaRepo.findByUnClienteIdCliente(idCliente);
    }

    private List<Producto> obtenerProductosDesdeCodigos(List<Long> codigos) {
        List<Producto> productos = new ArrayList<>();

        if (codigos == null || codigos.isEmpty()) {
            return productos;
        }

        for (Long codigo : codigos) {
            Producto producto = productoRepo.findById(codigo).orElse(null);
            if (producto != null) {
                productos.add(producto);
            }
        }

        return productos;
    }

    private Double calcularTotal(List<Producto> productos) {
        Double total = 0.0;

        for (Producto producto : productos) {
            total += producto.getCosto();
        }

        return total;
    }

    private boolean hayStockSuficiente(List<Producto> productos) {
        for (Producto producto : productos) {
            if (producto.getCantidadDisponible() == null || producto.getCantidadDisponible() < 1) {
                return false;
            }
        }
        return true;
    }

    private void descontarStock(List<Producto> productos) {
        for (Producto producto : productos) {
            producto.setCantidadDisponible(producto.getCantidadDisponible() - 1);
            productoRepo.save(producto);
        }
    }

    private void devolverStock(List<Producto> productos) {
        if (productos == null) {
            return;
        }

        for (Producto producto : productos) {
            producto.setCantidadDisponible(producto.getCantidadDisponible() + 1);
            productoRepo.save(producto);
        }
    }
}