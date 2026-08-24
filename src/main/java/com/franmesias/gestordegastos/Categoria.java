package com.franmesias.gestordegastos;
import jakarta.persistence.*;

@Entity
@Table(name="categoria")

public class Categoria {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column (name="nombre", nullable=false, unique=true)
    private String nombre;

    public Categoria(){}

    public Categoria(String nombre){
        this.nombre=nombre;}

    public Long getId(){
        return id;}

    public String getNombre(){
        return nombre;}

    public void setNombre(String nombre){
        this.nombre=nombre;}

    @Override
    public String toString(){
        return "Categoría: "+nombre;}

}
