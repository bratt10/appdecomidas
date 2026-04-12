package com.comidas.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "producto")
@Data
public class ProductoMode {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id; 
    private String nombre;
    private float precio;
    private String imagenUrl;
    private boolean activo;
}
