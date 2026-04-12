package com.comidas.Model;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedidos")
@Data
public class PedidoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación con Usuario
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioModel usuario;

    // Relación con Mesa
    @ManyToOne
    @JoinColumn(name = "mesa_id", nullable = false)
    private MesaModel mesa;

    // Estado del pedido
    @Enumerated(EnumType.STRING)
    private EstadoPedido estado;

    // Fecha del pedido  
    private LocalDateTime fecha;

    // Relación con detalles
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<DetallePedidoModel> detalles;

    @PrePersist
    public void prePersist() {
        this.fecha = LocalDateTime.now();
        this.estado = EstadoPedido.PENDIENTE;
    }
    public enum EstadoPedido {
    PENDIENTE,
    LISTO
    }
}