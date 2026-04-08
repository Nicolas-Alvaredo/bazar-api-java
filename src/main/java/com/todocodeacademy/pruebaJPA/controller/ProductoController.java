package com.todocodeacademy.pruebaJPA.controller;

import com.todocodeacademy.pruebaJPA.model.Producto;
import com.todocodeacademy.pruebaJPA.service.IProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    @Autowired
    private IProductoService productoServ;

    @PostMapping("/crear")
    public String crearProducto(@RequestBody Producto prod) {
        productoServ.saveProducto(prod);
        return "Producto creado correctamente";
    }

    @GetMapping
    public List<Producto> traerProductos() {
        return productoServ.getProductos();
    }

    @GetMapping("/{codigo_producto}")
    public Producto traerProducto(@PathVariable("codigo_producto") Long codigoProducto) {
        return productoServ.findProducto(codigoProducto);
    }

    @DeleteMapping("/eliminar/{codigo_producto}")
    public String eliminarProducto(@PathVariable("codigo_producto") Long codigoProducto) {
        try {
            productoServ.deleteProducto(codigoProducto);
            return "Producto eliminado correctamente";
        } catch (Exception e) {
            return "No se puede eliminar el producto porque está asociado a una venta.";
        }
    }

    @PutMapping("/editar/{codigo_producto}")
    public Producto editarProducto(@PathVariable("codigo_producto") Long codigoProducto,
                                   @RequestBody Producto prod) {
        return productoServ.editProducto(codigoProducto, prod);
    }

    @GetMapping("/falta_stock")
    public List<Producto> productosFaltaStock() {
        return productoServ.getProductosFaltaStock();
    }
}