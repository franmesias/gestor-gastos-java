package com.franmesias.gestordegastos;
import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
@Entity
@Table(name="gasto")

public class Gasto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name="importe", nullable=false)
    private double importe;
    @Column (name="descripcion", nullable=false, length = 40)
    private String descripcion;
    @Column (name="fecha", nullable=false)
    private LocalDate fecha;
    @ManyToOne
    @JoinColumn(name="categoria_id")
    private Categoria categoria;
    public Gasto(){}
    public Gasto(double importe, Categoria categoria, String descripcion, LocalDate fecha) {
        this.importe = importe;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }
    public Long getId(){
        return id;
    }
    public double getImporte(){
        return importe;
    }
    public void setImporte(double importe) {
        this.importe = importe;
    }
    public Categoria getCategoria(){
        return categoria;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    public String getDescripcion(){
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha){
        this.fecha=fecha;
    }
    @Override
    public String toString(){
        return "Gasto de "+importe+"€ en "+descripcion+". En la categoría: "+categoria.getNombre()+"Fecha: "+fecha;
    }
    public String describirPorCat(){
        return "- Gasto de "+importe+"€ en "+descripcion+"("+fecha+")";
    }
}

