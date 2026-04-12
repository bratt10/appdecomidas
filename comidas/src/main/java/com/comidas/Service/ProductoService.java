package com.comidas.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comidas.Model.ProductoMode;
import com.comidas.Repository.ProductoRepository;

@Service
public class ProductoService {
    @Autowired
    ProductoRepository productorepo;

    public ProductoMode crearProducto(ProductoMode producto){
        return productorepo.save(producto);
    }
    
    public List<ProductoMode> listarProductos(){
        List<ProductoMode> productoactivo = productorepo.findByActivoTrue();
        return productoactivo;
    }

    public ProductoMode editarProducto(ProductoMode producto, Long id){
        Optional<ProductoMode> pro = productorepo.findById(id);
        if (!pro.isPresent()) {
            throw new IllegalArgumentException("El producto no existe");
        }
        ProductoMode productoeditar = pro.get();
        productoeditar.setNombre(producto.getNombre());
        productoeditar.setPrecio(producto.getPrecio());
        productoeditar.setImagenUrl(producto.getImagenUrl());
        productoeditar.setActivo(producto.isActivo());

        return productorepo.save(productoeditar);
    }

    public void eliminarProducto(Long id){
        Optional<ProductoMode> pro = productorepo.findById(id);
        if (!pro.isPresent()) {
            throw new IllegalArgumentException("El producto no existe");
        }
        ProductoMode productoaeliminar=pro.get();
        productorepo.delete(productoaeliminar);

    }
    
}
