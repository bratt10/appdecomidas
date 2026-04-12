package com.comidas.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.comidas.Model.PedidoModel;
import com.comidas.Service.PedidoService;

@RestController                  
@RequestMapping("/pedido")          
public class PedidosController {

    @Autowired
    private PedidoService pedidoService;
    @PostMapping("/crearpedido")
    public ResponseEntity<?> crearPedido(@RequestBody PedidoModel datos, @RequestParam Long usuarioId,@RequestParam Long mesaId) {

        try {
            PedidoModel pedidoGuardado = pedidoService.crearpedido(datos, usuarioId, mesaId);
            return ResponseEntity.status(HttpStatus.CREATED).body(pedidoGuardado); 
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()); 
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor"); 
        }
    }
    @GetMapping
    public ResponseEntity<?> listarPedidos() {
        try {
            List<PedidoModel> lista = pedidoService.listarpedido();
            return ResponseEntity.ok(lista); 
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener los pedidos");
        }
    }

    @GetMapping("/hoy")
    public ResponseEntity<?> pedidosDeHoy() {
        try {
            List<PedidoModel> lista = pedidoService.listarPedidosDelDia();
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener los pedidos del día");
        }
    }

    @GetMapping("/semana")
    public ResponseEntity<?> pedidosDeLaSemana() {
        try {
            List<PedidoModel> lista = pedidoService.listarPedidosDeLaSemana();
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener los pedidos de la semana");
        }
    }

    @GetMapping("/mes")
    public ResponseEntity<?> pedidosDelMes() {
        try {
            List<PedidoModel> lista = pedidoService.listarPedidosDelMes();
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener los pedidos del mes");
        }
    }

    @DeleteMapping("/pedido/{id}")
    public ResponseEntity<?> borrarPedido(@PathVariable Long id) {
        try {
            pedidoService.borrarpedido(id);
            return ResponseEntity.ok("Pedido eliminado correctamente"); 
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido no encontrado"); 
    }
}
}