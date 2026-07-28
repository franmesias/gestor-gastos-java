import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
public class GestorGastosTest {
	
private GestorGastos gestorTest;
@BeforeEach

public void ejemplos() {
gestorTest = new GestorGastos();
gestorTest.nuevoGasto(53.45, "hogar", "agua", LocalDate.of(2025, 1, 1));
gestorTest.nuevoGasto(12.5, "ocio", "comida", LocalDate.of(2025, 3, 5));
gestorTest.nuevoGasto(1.5, "ocio", "café", LocalDate.now());
}
@Test
public void comprobarTotal() {

double totalTest = gestorTest.getTotal();
double suma = 53.45+12.5+1.5;
assertEquals(suma,totalTest,0.001,"ERROR DE CÓDIGO: El resultado del método no coincide con el total calculado");
}
@Test
public void comprobarTotalCat() {
double totalCatTest = gestorTest.getTotalCat("ocio");
double sumaOcio = 12.5+1.5;
assertEquals(sumaOcio,totalCatTest,0.001,"ERROR DE CÓDIGO: El resultado del método no coincide con el total calculado");
}
@Test
public void comprobarFiltradoFechas(){
LocalDate fecha1Test = LocalDate.of(2025, 1, 1);
LocalDate fecha2Test = LocalDate.of(2025, 12, 31);

List<Gasto> intervaloTest = gestorTest.filtrarFechas(fecha1Test, fecha2Test);

assertEquals(2,intervaloTest.size(),"ERROR DE CÓDIGO: En 2025 solamente hay 2 gastos registrados");}
@Test
public void comprobarIntervaloVacio(){
LocalDate fecha3Test = LocalDate.of(1999, 1, 1);
LocalDate fecha4Test = LocalDate.of(2000, 12, 31);
List<Gasto> intervaloTest2 = gestorTest.filtrarFechas(fecha3Test, fecha4Test);
assertTrue(intervaloTest2.isEmpty(),"ERROR DE CÓDIGO: El intervalo de 1999 a 2000 debería estar vacío");
	}
}
