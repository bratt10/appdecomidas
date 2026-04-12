package com.comidas.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.comidas.Model.ProductoMode;
import com.comidas.Service.ProductoService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RequestMapping("/productos")
@RestController
public class ProductoController {
    @Autowired
    private ProductoService producto;

    @PostMapping
    public  ResponseEntity<?> postCrearProducto(@RequestBody ProductoMode productocrear) {
        try {
            ProductoMode produc = producto.crearProducto(productocrear);
            return ResponseEntity.status(HttpStatus.CREATED).body(produc);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @GetMapping("/productos")
    public ResponseEntity<?> getVerproductos() {
        try {
            List<ProductoMode> productos = producto.listarProductos();
            return ResponseEntity.ok(productos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error a obtener los pedidos");

        }
    }
    @PostMapping("/{id}")
    public ResponseEntity<?> postMethodName(@RequestBody ProductoMode productoaeditar, @RequestParam Long id) {
        try {
            ProductoMode productoeditar = producto.editarProducto(productoaeditar, id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(productoeditar);
        } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> DeleteProducto(@PathVariable Long id){
        producto.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
    
    
    
}
