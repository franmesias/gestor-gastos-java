import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.time.LocalDate;
import java.util.stream.Collectors;
public class GestorGastos { 
    private ArrayList<Gasto> gastos;
    private HashMap <String, Double> categorias = new HashMap<>();
    private GastoRepositorio repo = new GastoRepositorio();
    public GestorGastos (){
        this.gastos = new ArrayList<>();
    }
    public void guardar(){
        repo.guardarDatos(gastos);
    }
    public void cargar(){
        List<Gasto> gastosCargados = repo.cargarDatos();
        for (Gasto gasto:gastosCargados){
            nuevoGasto(gasto.getImporte(),gasto.getCategoria(),gasto.getDescripcion(),gasto.getFecha());
        }
    }
    public void nuevoGasto(double importe, String categoria, String descripcion, LocalDate fecha) {
    gastos.add(new Gasto(importe, categoria, descripcion,fecha)); 
    if (!categorias.containsKey(categoria)){
        double totalCat = importe;
        categorias.put(categoria, totalCat);
    } else {
    double totalCat = categorias.get(categoria);
    totalCat += importe;
    categorias.replace(categoria, totalCat);
    }
    }
    public boolean sinGastos(){
        return gastos.isEmpty();
    }
    public double getTotal(){
    return gastos.stream()
                 .mapToDouble(Gasto::getImporte)
                 .sum();
    }
    public String filtrarCat (String categoria) {
        StringBuilder sB = new StringBuilder(); 
    	for (Gasto gasto : gastos) {
                if (categoria.equals(gasto.getCategoria())){
                    sB.append(gasto.describirPorCat()+"\n");
                }
            }
    	return sB.toString();
    }
    public double getTotalCat (String categoria) {
        double totalCat=0;
        totalCat=categorias.get(categoria);
        return totalCat;
    }
    public String listarCat (){
        StringBuilder sB = new StringBuilder();
    	for(Map.Entry<String, Double> entrada:categorias.entrySet()){
            sB.append("Total de gastos en "+entrada.getKey()+":"+entrada.getValue()+"€\n");
        }
    	return sB.toString();
    }
    public List<Gasto> filtrarFechas (LocalDate fecha1, LocalDate fecha2){
        return gastos.stream()
                            .filter(gasto -> !gasto.getFecha().isBefore(fecha1)
                            && !gasto.getFecha().isAfter(fecha2))
                            .collect(Collectors.toList());
    }
}