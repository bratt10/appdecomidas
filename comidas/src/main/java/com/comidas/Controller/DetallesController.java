package com.comidas.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comidas.Service.DetalleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/detalles")
public class DetallesController {
    @Autowired
    DetalleService detalleserve;
    @GetMapping("/{id]")
     public ResponseEntity<?> getMostrarVentasdeproducto(Long id) {
        try {
           detalleserve.cantidadDeVentasDeUnProducto(id);
           return ResponseEntity.status(HttpStatus.ACCEPTED).body("La cantidad de ventas del producto");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener la cantidad de venta");
        }
    }
     
}
