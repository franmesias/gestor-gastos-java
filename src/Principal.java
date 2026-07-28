import java.util.Scanner;
import java.util.InputMismatchException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;
import java.util.List;
public class Principal {
    public static void main(String[]args){
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        double importe=0;
        String categoria;
        String descripcion;
        LocalDate fecha = LocalDate.now();
        GestorGastos gest = new GestorGastos();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        gest.cargar();
        do {
        try{
        System.out.println("Bienvenido/a al gestor de tus gastos. ¿Qué quieres hacer?");
        System.out.println("1. Añadir gasto.");
        System.out.println("2. Mostrar todos tus gastos.");
        System.out.println("3. Mostrar gastos por categoría.");
        System.out.println("4. Filtrar gastos por fecha.");
        System.out.println("5. Salir.");
        opcion = scanner.nextInt();
        switch (opcion){
            case 1: {
               System.out.println("Introduzca un importe.");
               importe = scanner.nextDouble();
               scanner.nextLine();
               if (importe>0){
                   System.out.println("¿En qué ha sido el gasto?");
                   descripcion = scanner.nextLine();
                   System.out.println("Introduzca una categoría para el gasto");
                   categoria = scanner.nextLine();
                   int opcionFecha=0;
                   System.out.println("¿Cuándo se ha producido el gasto?");
                   System.out.println("1.Hoy");
                   System.out.println("2.En otra fecha");
                   opcionFecha = scanner.nextInt();
                   if (opcionFecha == 1) fecha = LocalDate.now();
                   else if (opcionFecha == 2) {
                	   scanner.nextLine();
                       System.out.println("Introduce la fecha en la que se produjo el gasto (formato:dd-mm-AAAA)");
                       String fechaus = scanner.nextLine();
                       fecha = LocalDate.parse(fechaus,formato);
                   } else System.out.println("Opción no válida");
                   gest.nuevoGasto(importe,categoria,descripcion,fecha);
            } else {
                System.out.println("Dato incorrecto, debe ser un número positivo");
            }
        } break;
        case 2: {
            if (!gest.sinGastos()){
            System.out.println("Estos son todos tus gastos:");
            System.out.println(gest.listarCat());
            System.out.println("El total de tus gastos asciende a "+gest.getTotal()+"€");}
            else{System.out.println("Aún no tienes gastos");}
            } break;
        case 3: {
            try{
            System.out.println("¿De qué categoría quieres conocer el gasto total?");
            scanner.nextLine();
            categoria = scanner.nextLine();
            System.out.println(gest.filtrarCat(categoria));
            System.out.println("Total de gastos en "+categoria+": "+gest.getTotalCat(categoria)+"€");
            } catch (NullPointerException ex1){
                System.out.println("Introduce una categoría existente");
            }
        }
         break;
        case 4: {
            try{
            System.out.println("¿Desde qué fecha quiere conocer los gastos? (Formato: DD-MM-AAAA)");
            scanner.nextLine();
            String fecha1Texto = scanner.nextLine();
            LocalDate fecha1 = LocalDate.parse(fecha1Texto,formato);
            System.out.println("¿Hasta qué fecha?");
            String fecha2Texto = scanner.nextLine();
            LocalDate fecha2 = LocalDate.parse(fecha2Texto,formato);
            if (fecha2.isBefore(fecha1)){
            System.out.println("La segunda fecha debe ser posterior a la primera");
            } else {
            List<Gasto> intervalo = gest.filtrarFechas(fecha1,fecha2);
            if (!intervalo.isEmpty()){
            System.out.println("Gastos realizados desde el "+fecha1+"hasta el "+fecha2+":");
            System.out.println(intervalo);}
            else{
            System.out.println("No se han encontrado gastos en ese intervalo");
            }
        }
            } catch (DateTimeParseException ex2){
            System.out.println("Formato de fecha no válido.");   
            }
        }break;
        case 5: {
            gest.guardar();
            System.out.println("¡Hasta luego!");
        }break;
        default: {
            System.out.println("Opción no válida, debe ser un número del 1 al 4.");
        }break;
    }  
        } catch (InputMismatchException ex){
        System.out.println("Opción no válida, debe ser un número del 1 al 4.");
        scanner.next();
        }
    }while (opcion != 5); 
        scanner.close();   
    }
}