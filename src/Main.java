import java.util.Scanner;

public class Main {
// SI HAY UN SOLO COSTO
//  private static final double COSTO_BASE_FIJO = 2000.0;
/* SI HAY COSTO DIFERENCIADO POR TIPO DE ANIMAL
  private static final double COSTO_BASE_AVES = 1500.0;
  private static final double COSTO_BASE_CAZADORES = 3000.0;
 */
  public static void main(String[] args) {
    Inventario inventario = new Inventario();
    Menu menu = new Menu();
    Scanner scanner = new Scanner(System.in);

    String input;
    int opcion = 0;

    do {
      menu.mostrarMenuPrincipal();
      input = scanner.nextLine();

      /**
       * Trata de parsear a un integer,
       * si es una letra fallará entonces el error
       * provocará que salga.
       **/
      try {
        opcion = Integer.parseInt(input);
      } catch (NumberFormatException e) {
        System.out.println("\n👋 ¡Gracias por usar el sistema veterinario!");
        break;
      }

      switch (opcion) {
        case 1:
          agregarAvicola(inventario, scanner);
          System.out.println("\nPresione Enter para continuar...");
          scanner.nextLine();
          break;
        case 2:
          agregarCacera(inventario, scanner);
          System.out.println("\nPresione Enter para continuar...");
          scanner.nextLine();
          break;
        case 3:
          inventario.listarTodosLosAnimales();
          System.out.println("\nPresione Enter para continuar...");
          scanner.nextLine();
          break;
        case 4:
          buscarAnimal(inventario, scanner);
          System.out.println("\nPresione Enter para continuar...");
          scanner.nextLine();
          break;
        case 5:
          eliminarAnimal(inventario, scanner);
          System.out.println("\nPresione Enter para continuar...");
          scanner.nextLine();
          break;
        case 6:
          System.out.println("Total de animales: " + inventario.getCantidadDeAnimales());
          System.out.println("\nPresione Enter para continuar...");
          scanner.nextLine();
          break;
        case 7:
          actualizarAvicola(inventario, scanner);
          System.out.println("\nPresione Enter para continuar...");
          scanner.nextLine();
          break;
        case 8:
          actualizarCacera(inventario, scanner);
          System.out.println("\nPresione Enter para continuar...");
          scanner.nextLine();
          break;
          /*case 9:
          calcularCostoCuidado(inventario, scanner);
          System.out.println("\nPresione Enter para continuar...");
          scanner.nextLine();
          break; */
        default:
          System.out.println("❌ Opción no válida. Intente nuevamente.");
          System.out.println("\nPresione Enter para continuar...");
          scanner.nextLine();
      }

    } while (true);

    scanner.close();
  }

  private static void agregarAvicola(Inventario inventario, Scanner scanner) {
    System.out.println("\n📝 AGREGAR NUEVO ANIMAL AVÍCOLA");
    System.out.print("Ingrese nombre: ");
    String nombre = scanner.nextLine();
    System.out.print("Ingrese especie: ");
    String especie = scanner.nextLine();
    System.out.print("Ingrese edad: ");
    int edad = scanner.nextInt();
    System.out.print("Ingrese peso (kg): ");
    double peso = scanner.nextDouble();
    System.out.print("Ingrese tipo de plumaje (EXOTICO/COLORIDO/COMUN): ");
    scanner.nextLine(); // Limpiar buffer
    String tipoPlumaje = scanner.nextLine();

    try {
      Avicolas avicola = new Avicolas(especie, edad, nombre, peso, tipoPlumaje);

      if (inventario.agregarAvicola(avicola)) {
        System.out.println("✅ Animal avícola agregado exitosamente");
      } else {
        System.out.println("❌ Error: Ya existe un animal con ese nombre");
      }
    } catch (PesoInsuficienteException e) {
      System.out.println("❌ Error: " + e.getMessage());
    }
  }

  private static void agregarCacera(Inventario inventario, Scanner scanner) {
    System.out.println("\n📝 AGREGAR NUEVO ANIMAL CAZADOR");
    System.out.print("Ingrese nombre: ");
    String nombre = scanner.nextLine();
    System.out.print("Ingrese especie: ");
    String especie = scanner.nextLine();
    System.out.print("Ingrese edad: ");
    int edad = scanner.nextInt();
    System.out.print("Ingrese peso (kg): ");
    double peso = scanner.nextDouble();
    scanner.nextLine(); // Limpiar buffer

    Caceras cacera = new Caceras(especie, edad, nombre, peso);

    if (inventario.agregarCacera(cacera)) {
      System.out.println("✅ Animal cazador agregado exitosamente");
    } else {
      System.out.println("❌ Error: Ya existe un animal con ese nombre");
    }
  }

  private static void buscarAnimal(Inventario inventario, Scanner scanner) {
    System.out.print("🔍 Ingrese nombre del animal a buscar: ");
    String nombre = scanner.nextLine();

    Animalito animal = inventario.buscarAnimal(nombre.toUpperCase());
    if (animal != null) {
      System.out.println("✅ Animal encontrado:");
      String emoji = animal.verTipoDeAnimal();
      System.out.println(emoji + " " + animal);
    } else {
      System.out.println("❌ No se encontró un animal con ese nombre");
    }
  }

  private static void eliminarAnimal(Inventario inventario, Scanner scanner) {
    System.out.print("🗑️ Ingrese nombre del animal a eliminar: ");
    String nombre = scanner.nextLine();

    if (inventario.eliminarAnimal(nombre.toUpperCase())) {
      System.out.println("✅ Animal eliminado exitosamente");
    } else {
      System.out.println("❌ No se encontró un animal con ese nombre");
    }
  }

  private static void actualizarAvicola(Inventario inventario, Scanner scanner) {
    System.out.print("🔄 Ingrese nombre del animal avícola a actualizar: ");
    String nombre = scanner.nextLine();

    if (inventario.buscarAnimal(nombre.toUpperCase()) != null) {
      System.out.println("Ingrese los nuevos datos:");
      System.out.print("Especie: ");
      String especie = scanner.nextLine();
      System.out.print("Edad: ");
      int edad = scanner.nextInt();
      System.out.print("Peso (kg): ");
      double peso = scanner.nextDouble();
      System.out.print("Tipo de plumaje: ");
      scanner.nextLine(); // Limpiar buffer
      String tipoPlumaje = scanner.nextLine();

      try {
        Avicolas avicolaActualizada = new Avicolas(especie, edad, nombre, peso, tipoPlumaje);

        if (inventario.actualizarAvicola(nombre.toUpperCase(), avicolaActualizada)) {
          System.out.println("✅ Animal avícola actualizado exitosamente");
        } else {
          System.out.println("❌ Error al actualizar el animal avícola");
        }
      } catch (PesoInsuficienteException e) {
        System.out.println("❌ Error: " + e.getMessage());
      }
    } else {
      System.out.println("❌ No se encontró un animal avícola con ese nombre");
    }
  }

  private static void actualizarCacera(Inventario inventario, Scanner scanner) {
    System.out.print("🔄 Ingrese nombre del animal cazador a actualizar: ");
    String nombre = scanner.nextLine();

    if (inventario.buscarAnimal(nombre.toUpperCase()) != null) {
      System.out.println("Ingrese los nuevos datos:");
      System.out.print("Especie: ");
      String especie = scanner.nextLine();
      System.out.print("Edad: ");
      int edad = scanner.nextInt();
      System.out.print("Peso (kg): ");
      double peso = scanner.nextDouble();
      scanner.nextLine(); // Limpiar buffer

      Caceras caceraActualizada = new Caceras(especie, edad, nombre, peso);

      if (inventario.actualizarCacera(nombre.toUpperCase(), caceraActualizada)) {
        System.out.println("✅ Animal cazador actualizado exitosamente");
      } else {
        System.out.println("❌ Error al actualizar el animal cazador");
      }
    } else {
      System.out.println("❌ No se encontró un animal cazador con ese nombre");
    }
  }
  /*// ... pegar al final de la clase Main, junto a los otros métodos privados

  private static void calcularCostoCuidado(Inventario inventario, Scanner scanner) {
    System.out.println("\n💲 CALCULAR COSTO DE CUIDADO");
    System.out.print("Ingrese nombre del animal: ");
    String nombre = scanner.nextLine();

    Animalito animal = inventario.buscarAnimal(nombre.toUpperCase());

    if (animal != null) {
      // Verificamos si el animal implementa la interfaz Cuidados
      if (animal instanceof Cuidados) {
        System.out.println("✅ Animal encontrado: " + animal.getNombre() + " (" + animal.verTipoDeAnimal() + ")");
        
        System.out.print("Ingrese el costo base operativo ($): ");
        try {
          double costoBase = scanner.nextDouble();
          scanner.nextLine(); // Limpiar buffer

          // Hacemos el casting a la interfaz Cuidados para usar el método
          Cuidados animalConCuidados = (Cuidados) animal;
          
          double costoFinal = animalConCuidados.calcularCostoCuidado(costoBase, animal.getEdad());
          
          System.out.println("----------------------------------------");
          System.out.println("Cálculo para: " + animal.getEspecie());
          System.out.println("Edad: " + animal.getEdad() + " años");
          System.out.printf("💰 COSTO FINAL DE CUIDADO: $%.2f%n", costoFinal);
          System.out.println("----------------------------------------");
          
        } catch (Exception e) {
          System.out.println("❌ Error: Ingrese un monto válido.");
          scanner.nextLine(); // Limpiar buffer en caso de error
        }
      } else {
        System.out.println("⚠️ Este animal no requiere cálculos especiales de cuidado.");
      }
    } else {
      System.out.println("❌ No se encontró un animal con ese nombre.");
    }
  }
   */
 
  /*  CALCULAR COSTO DE CUIDADO CON GASTO FIJO
  private static void calcularCostoCuidado(Inventario inventario, Scanner scanner) {
    System.out.println("\n💲 CALCULAR COSTO (Base fija: $" + COSTO_BASE_FIJO + ")");
    System.out.print("Ingrese nombre del animal: ");
    String nombre = scanner.nextLine();

    Animalito animal = inventario.buscarAnimal(nombre.toUpperCase());

    if (animal != null) {
        if (animal instanceof Cuidados) {
            // YA NO PEDIMOS EL DATO, USAMOS LA CONSTANTE DIRECTAMENTE
            double costoFinal = ((Cuidados) animal).calcularCostoCuidado(COSTO_BASE_FIJO, animal.getEdad());

            System.out.println("✅ Animal: " + animal.getNombre());
            System.out.println("📊 Costo calculado: $" + costoFinal);
        } else {
            System.out.println("⚠️ Este animal no requiere cuidados especiales.");
        }
    } else {
        System.out.println("❌ No se encontró el animal.");
    }
}
   */
  /* EN EL CASO DE QUE NOS DEN EL COSTO Y SEA FIJO GASTOS TOTALES
  private static void mostrarReporteDeGastos(Inventario inventario) {
    double gastoTotal = 0;
    
    System.out.println("\n=== REPORTE DE GASTOS MENSUALES (Base: $" + COSTO_BASE_FIJO + ") ===");
    
    // Juntamos ambas listas para recorrerlas
    ArrayList<Animalito> todos = new ArrayList<>();
    todos.addAll(inventario.getAvicolas());
    todos.addAll(inventario.getCaceras());

    for (Animalito a : todos) {
        if (a instanceof Cuidados) {
            double costoIndividual = ((Cuidados) a).calcularCostoCuidado(COSTO_BASE_FIJO, a.getEdad());
            System.out.println(a.getNombre() + " (" + a.verTipoDeAnimal() + "): $" + costoIndividual);
            gastoTotal += costoIndividual;
        }
    }
    
    System.out.println("--------------------------------");
    System.out.println("💰 GASTO TOTAL DE LA VETERINARIA: $" + gastoTotal);
} */
/* EN EL CASO DE QUE SEA UN COSTO DIFERENCIADO POR TIPO DE ANIMAL
private static void calcularCostoCuidado(Inventario inventario, Scanner scanner) {
    System.out.println("\n💲 CALCULAR COSTO DIFERENCIADO");
    System.out.print("Ingrese nombre del animal: ");
    String nombre = scanner.nextLine();

    Animalito animal = inventario.buscarAnimal(nombre.toUpperCase());

    if (animal != null) {
        double costoBaseSeleccionado = 0; // Variable auxiliar
        double costoFinal = 0;

        // 1. DECIDIMOS EL COSTO BASE SEGÚN EL TIPO
        if (animal instanceof Avicolas) {
            costoBaseSeleccionado = COSTO_BASE_AVES;
            System.out.println("ℹ️ Detectado: AVICOLA (Base aplicada: $" + COSTO_BASE_AVES + ")");
        } else if (animal instanceof Caceras) {
            costoBaseSeleccionado = COSTO_BASE_CAZADORES;
            System.out.println("ℹ️ Detectado: CAZADOR (Base aplicada: $" + COSTO_BASE_CAZADORES + ")");
        }

        // 2. HACEMOS EL CÁLCULO SI CORRESPONDE
        if (animal instanceof Cuidados) {
            // Pasamos la variable 'costoBaseSeleccionado' que ya tiene el precio correcto
            costoFinal = ((Cuidados) animal).calcularCostoCuidado(costoBaseSeleccionado, animal.getEdad());

            System.out.println("----------------------------------------");
            System.out.println("Animal: " + animal.getNombre());
            System.out.printf("💰 COSTO FINAL: $%.2f%n", costoFinal);
            System.out.println("----------------------------------------");
        } 
    } else {
        System.out.println("❌ No se encontró el animal.");
    }
} */
/* COSTO DE CUIDADO EN TOTAL CON GASTOS DIFERENCIADOS
private static void mostrarReporteTotal(Inventario inventario) {
    // Definimos los costos fijos aquí para este reporte
    final double COSTO_BASE_AVES = 1500.0;
    final double COSTO_BASE_CAZADORES = 3000.0;

    System.out.println("\n📊 REPORTE DE GASTOS TOTALES (Precios diferenciados)");
    System.out.println("==================================================");
    System.out.println("Base Aves: $" + COSTO_BASE_AVES + " | Base Cazadores: $" + COSTO_BASE_CAZADORES);
    System.out.println("--------------------------------------------------");

    double totalGeneral = 0;
    
    // 1. Juntamos todos los animales en una sola lista auxiliar
    java.util.ArrayList<Animalito> todosLosAnimales = new java.util.ArrayList<>();
    todosLosAnimales.addAll(inventario.getAvicolas());
    todosLosAnimales.addAll(inventario.getCaceras());

    // 2. Recorremos y calculamos
    for (Animalito a : todosLosAnimales) {
        double base = 0;
        
        // Asignamos precio según el tipo
        if (a instanceof Avicolas) base = COSTO_BASE_AVES;
        else if (a instanceof Caceras) base = COSTO_BASE_CAZADORES;
        
        // Verificamos y calculamos
        if (a instanceof Cuidados) {
            double costo = ((Cuidados) a).calcularCostoCuidado(base, a.getEdad());
            totalGeneral += costo;
            
            // (Opcional) Imprimir cada línea para ver el detalle
            System.out.printf(" - %-10s (%s): $%.2f%n", a.getNombre(), a.verTipoDeAnimal(), costo);
        }
    }

    System.out.println("==================================================");
    System.out.printf("💰 TOTAL A PAGAR EN CUIDADOS: $%.2f%n", totalGeneral);
    System.out.println("==================================================");
  }
 */
}