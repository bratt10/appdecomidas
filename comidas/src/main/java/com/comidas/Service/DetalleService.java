package com.comidas.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comidas.Repository.DetalleRepository;


@Service
public class DetalleService {

    @Autowired
    private DetalleRepository detalleRepo;

    public Long cantidadDeVentasDeUnProducto(Long productoId){

        if (productoId == null) {
            throw new IllegalArgumentException("El id del producto no puede ser null");
        }

        if (productoId <= 0) {
            throw new IllegalArgumentException("El id del producto debe ser mayor a 0");
        }

        Long total = detalleRepo.totalVendidoProducto(productoId);

        if (total == null) {
            return 0L;
        }

        return total;
    }
}
//falta hacer el controller 
