package com.comidas.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.comidas.Model.DetallePedidoModel;

public interface DetalleRepository extends JpaRepository<DetallePedidoModel, Long> {
    @Query("Select SUM(d.cantidad) from DetallePedidoModel d WHERE d.producto.id = :producto.id")
    Long totalVendidoProducto(@Param("productoId") Long productoId);
    
    
}
