package com.todocodeacademy.pruebaJPA.repository;

import com.todocodeacademy.pruebaJPA.model.Venta;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVentaRepository extends JpaRepository<Venta, Long> {

    public List<Venta> findByFechaVenta(LocalDate fechaVenta);

    public Venta findTopByOrderByTotalDesc();

    public List<Venta> findByUnClienteIdCliente(Long idCliente);
}