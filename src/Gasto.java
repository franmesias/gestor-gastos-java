import java.time.LocalDate;

/*
 Clase para incluir los gastos
 */
public class Gasto {
    private double importe;
    private String categoria;
    private String descripcion;
    private LocalDate fecha;
    public Gasto(double importe, String categoria, String descripcion, LocalDate fecha) {
        this.importe = importe;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }
    public double getImporte(){
        return importe;
    }
    public String getCategoria(){
        return categoria;
    }
    public String getDescripcion(){
        return descripcion;
    }
    public LocalDate getFecha(){
        return fecha;
    }
    @Override
    public String toString(){
         return "Gasto de "+importe+"€ en "+descripcion+". En la categoría: "+categoria+". Fecha: "+fecha;
    }
    public String describirPorCat(){
        return "- Gasto de "+importe+"€ en "+descripcion+"("+fecha+")";
    }
}
