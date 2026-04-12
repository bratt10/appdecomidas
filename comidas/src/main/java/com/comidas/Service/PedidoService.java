package com.comidas.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.comidas.Model.DetallePedidoModel;
import com.comidas.Model.MesaModel;
import com.comidas.Model.PedidoModel;
import com.comidas.Model.ProductoMode;
import com.comidas.Model.UsuarioModel;
import com.comidas.Repository.MesaRepository;
import com.comidas.Repository.PedidoRepository;
import com.comidas.Repository.ProductoRepository;
import com.comidas.Repository.UsuarioRepository;


@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidos;

    @Autowired
    UsuarioRepository usuario;
    
    @Autowired
    MesaRepository mesas;

    @Autowired
    ProductoRepository productoRepository; 

    public PedidoModel crearpedido(PedidoModel pedido, Long usuarioId, Long mesaId){
        Optional<UsuarioModel> usr = usuario.findById(usuarioId);
        if (!usr.isPresent()) {
            throw new IllegalArgumentException("El usuario no existe");
        }

        Optional<MesaModel> mesa = mesas.findById(mesaId);
        if (!mesa.isPresent()) {
            throw new IllegalArgumentException("La mesa no existe");        
        }

        pedido.setUsuario(usr.get());
        pedido.setMesa(mesa.get());

        PedidoModel pedidoGuardado = pedidos.save(pedido);

        if (pedido.getDetalles() != null) {

            // recorrer detalles
            for (DetallePedidoModel detalle : pedido.getDetalles()) {

                //validar producto
                Optional<ProductoMode> pro = productoRepository.findById(detalle.getProducto().getId());
                if (!pro.isPresent()) {
                    throw new IllegalArgumentException("Producto no existe");
                }

                // asignar producto real
                detalle.setProducto(pro.get());

                // asignar pedido al detalle
                detalle.setPedido(pedidoGuardado);
            }
        }
        return pedidoGuardado;
    }
    public List<PedidoModel> listarpedido(){
        return pedidos.findAll(Sort.by(Sort.Direction.DESC, "fecha"));
    }

    public List<PedidoModel> listarPedidosDelDia() {
        LocalDateTime inicio = LocalDate.now().atStartOfDay();
        LocalDateTime fin = inicio.plusDays(1);
        return pedidos.findByFechaBetween(inicio, fin);
    }

    public long contarPedidosDelDia() {
        LocalDateTime inicio = LocalDate.now().atStartOfDay();
        LocalDateTime fin = inicio.plusDays(1);
        return pedidos.countByFechaBetween(inicio, fin);
    }

    public List<PedidoModel> listarPedidosDeLaSemana() {
        LocalDate hoy = LocalDate.now();
        LocalDate inicioSemana = hoy.with(DayOfWeek.MONDAY);
        LocalDateTime inicio = inicioSemana.atStartOfDay();
        LocalDateTime fin = inicio.plusDays(7);
        return pedidos.findByFechaBetween(inicio, fin);
    }

    public long contarPedidosDeLaSemana() {
        LocalDate hoy = LocalDate.now();
        LocalDate inicioSemana = hoy.with(DayOfWeek.MONDAY);
        LocalDateTime inicio = inicioSemana.atStartOfDay();
        LocalDateTime fin = inicio.plusDays(7);
        return pedidos.countByFechaBetween(inicio, fin);
    }

    public List<PedidoModel> listarPedidosDelMes() {
        LocalDate hoy = LocalDate.now();
        LocalDate inicioMes = hoy.withDayOfMonth(1);
        LocalDateTime inicio = inicioMes.atStartOfDay();
        LocalDateTime fin = inicio.plusMonths(1);
        return pedidos.findByFechaBetween(inicio, fin);
    }

    public long contarPedidosDelMes() {
        LocalDate hoy = LocalDate.now();
        LocalDate inicioMes = hoy.withDayOfMonth(1);
        LocalDateTime inicio = inicioMes.atStartOfDay();
        LocalDateTime fin = inicio.plusMonths(1);
        return pedidos.countByFechaBetween(inicio, fin);
    }

    public void borrarpedido(Long id){
        pedidos.deleteById(id);
    }
}