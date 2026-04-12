package com.comidas.Repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comidas.Model.PedidoModel;


public interface PedidoRepository extends JpaRepository<PedidoModel, Long>{
    List<PedidoModel> findByMesaId(Long mesaId);
    List<PedidoModel> findByFechaBetween(LocalDateTime start, LocalDateTime end);
    long countByFechaBetween(LocalDateTime start, LocalDateTime end);

}
