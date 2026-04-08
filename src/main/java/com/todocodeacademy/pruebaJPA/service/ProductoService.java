package com.todocodeacademy.pruebaJPA.service;

import com.todocodeacademy.pruebaJPA.model.Producto;
import com.todocodeacademy.pruebaJPA.repository.IProductoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private IProductoRepository productoRepo;

    @Override
    public void saveProducto(Producto prod) {
        productoRepo.save(prod);
    }

    @Override
    public List<Producto> getProductos() {
        return productoRepo.findAll();
    }

    @Override
    public Producto findProducto(Long codigoProducto) {
        return productoRepo.findById(codigoProducto).orElse(null);
    }

    @Override
    public void deleteProducto(Long codigoProducto) {
        productoRepo.deleteById(codigoProducto);
    }

    @Override
    public Producto editProducto(Long codigoProducto, Producto prod) {
        Producto producto = this.findProducto(codigoProducto);

        if (producto != null) {
            producto.setNombre(prod.getNombre());
            producto.setMarca(prod.getMarca());
            producto.setCosto(prod.getCosto());
            producto.setCantidadDisponible(prod.getCantidadDisponible());
            return productoRepo.save(producto);
        }

        return null;
    }

    @Override
    public List<Producto> getProductosFaltaStock() {
        return productoRepo.findByCantidadDisponibleLessThan(5.0);
    }
}