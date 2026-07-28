import java.util.List;
import java.util.ArrayList;
import java.io.*;
import java.time.LocalDate;
public class GastoRepositorio
{
public void guardarDatos(List<Gasto> gastos){
        try {
            FileWriter aG = new FileWriter("BBDDgastos.csv");
            BufferedWriter bG = new BufferedWriter(aG);
            for (Gasto gasto:gastos){
                bG.write(gasto.getImporte()+"|"+gasto.getCategoria()+"|"+
                gasto.getDescripcion()+"|"+gasto.getFecha());
                bG.newLine();
            }
            bG.close();
        } catch (IOException e){
            System.out.println("Error al guardar los datos:"+e.getMessage());
        }
    }
public List<Gasto> cargarDatos(){
    List<Gasto> gastos= new ArrayList<>();
        try{
            FileReader frG = new FileReader("BBDDgastos.csv");
            BufferedReader brG = new BufferedReader(frG);
            
            String linea;
            while ((linea = brG.readLine()) != null){
                String[] campos = linea.split("\\|");
                double importe = Double.parseDouble(campos[0]);
                String categoria = campos[1];
                String descripcion = campos[2];
                LocalDate fecha = LocalDate.parse(campos[3]);
                gastos.add(new Gasto(importe, categoria, descripcion,fecha));
            }
            brG.close();
        } catch (FileNotFoundException e){
            System.out.println("Aún no tienes datos guardados.");
        } catch (IOException e) {
            System.out.println("Error al cargar los datos"+e.getMessage());
        }
        return gastos;
    }
}