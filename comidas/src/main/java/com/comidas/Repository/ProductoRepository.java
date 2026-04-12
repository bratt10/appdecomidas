package com.comidas.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comidas.Model.ProductoMode;


public interface ProductoRepository extends JpaRepository<ProductoMode, Long>{
    List<ProductoMode> findByActivoTrue();
  
}
