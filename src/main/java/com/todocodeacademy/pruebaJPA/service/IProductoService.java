package com.todocodeacademy.pruebaJPA.service;

import com.todocodeacademy.pruebaJPA.model.Producto;
import java.util.List;

public interface IProductoService {

    public void saveProducto(Producto prod);

    public List<Producto> getProductos();

    public Producto findProducto(Long codigoProducto);

    public void deleteProducto(Long codigoProducto);

    public Producto editProducto(Long codigoProducto, Producto prod);

    public List<Producto> getProductosFaltaStock();
}