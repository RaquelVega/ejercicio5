import java.util.Scanner;

/**
 * Gestor de Procesos - Controlador principal implementando patrón MVC.
 * Coordina entre el modelo (procesos) y la vista (interfaz de usuario).
 * Maneja toda la interacción con el usuario según las buenas prácticas.
 * 
 * @author Raquel Vega
 * @version 1.0
 */
public class GestorProcesos {
    private PlanificadorProcesos planificador;
    private Scanner scanner;
    private boolean ejecutandose;
    
    /**
     * Constructor del gestor.
     * Inicializa el planificador y el scanner para entrada de usuario.
     */
    public GestorProcesos() {
        this.planificador = new PlanificadorProcesos();
        this.scanner = new Scanner(System.in);
        this.ejecutandose = false;
    }
    
    /**
     * Inicia el gestor de procesos.
     * Método principal que maneja el flujo de la aplicación.
     */
    public void iniciar() {
        mostrarBienvenida();
        ejecutandose = true;
        
        // Evitar while(true) con break - usar variable de control
        while (ejecutandose) {
            mostrarMenu();
            int opcion = obtenerOpcion();
            procesarOpcion(opcion);
            
            if (ejecutandose) {
                pausarParaContinuar();
            }
        }
        
        finalizarSistema();
    }
    
    /**
     * Muestra mensaje de bienvenida al usuario.
     */
    private void mostrarBienvenida() {
        System.out.println(repetirCaracter("=", 60));
        System.out.println("    SIMULADOR DE PROCESOS - PROGRAMACIÓN ORIENTADA A OBJETOS");
        System.out.println(repetirCaracter("=", 60));
        System.out.println("Demuestra: Herencia, Polimorfismo, Encapsulación");
        System.out.println();
    }
    
    /**
     * Muestra el menú principal de opciones.
     */
    private void mostrarMenu() {
        System.out.println("\n" + repetirCaracter("=", 40));
        System.out.println("           MENÚ PRINCIPAL");
        System.out.println(repetirCaracter("=", 40));
        System.out.println("1. Crear Proceso");
        System.out.println("2. Listar Procesos en Cola");
        System.out.println("3. Ejecutar Todos los Procesos");
        System.out.println("4. Información de Tipos de Proceso");
        System.out.println("5. Estadísticas del Sistema");
        System.out.println("6. Limpiar Cola de Procesos");
        System.out.println("0. Salir del Sistema");
        System.out.println(repetirCaracter("=", 40));
        System.out.print("Seleccione una opción: ");
    }
    
    /**
     * Obtiene y valida la opción del usuario.
     * @return Opción válida seleccionada por el usuario
     */
    private int obtenerOpcion() {
        try {
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir salto de línea
            return opcion;
        } catch (Exception e) {
            scanner.nextLine(); // Limpiar entrada inválida
            System.out.println("❌ Entrada inválida. Por favor ingrese un número.");
            return -1;
        }
    }
    
    /**
     * Procesa la opción seleccionada por el usuario.
     * @param opcion Opción a procesar
     */
    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1: 
                crearProceso(); 
                break;
            case 2: 
                listarProcesos(); 
                break;
            case 3: 
                ejecutarTodosLosProcesos(); 
                break;
            case 4: 
                mostrarInformacionTipos(); 
                break;
            case 5: 
                mostrarEstadisticas(); 
                break;
            case 6: 
                limpiarCola(); 
                break;
            case 0: 
                ejecutandose = false; 
                break;
            default: 
                System.out.println("❌ Opción inválida. Intente nuevamente.");
        }
    }
    
    /**
     * Permite al usuario crear un nuevo proceso.
     * Demuestra polimorfismo en la creación de diferentes tipos.
     */
    private void crearProceso() {
        System.out.println("\n--- CREAR NUEVO PROCESO ---");
        System.out.println("Tipos disponibles:");
        System.out.println("1. Proceso CPU (Cálculo intensivo)");
        System.out.println("2. Proceso E/S (Entrada/Salida)");
        System.out.println("3. Proceso Demonio (Servicio de sistema)");
        System.out.print("Seleccione tipo: ");
        
        int tipo = obtenerOpcion();
        System.out.print("Ingrese nombre del proceso: ");
        String nombre = scanner.nextLine();
        
        Proceso proceso = null;
        
        // Polimorfismo: diferentes tipos implementan la misma interfaz
        switch (tipo) {
            case 1:
                proceso = crearProcesoCPU(nombre);
                break;
            case 2:
                proceso = crearProcesoES(nombre);
                break;
            case 3:
                proceso = crearProcesoDemonio(nombre);
                break;
            default:
                System.out.println("❌ Tipo de proceso inválido.");
                return;
        }
        
        if (proceso != null && planificador.agregarProceso(proceso)) {
            System.out.println("✅ Proceso creado exitosamente:");
            System.out.println("   " + proceso.toString());
        } else {
            System.out.println("❌ Error al crear el proceso.");
        }
    }
    
    /**
     * Crea un proceso CPU con parámetros específicos.
     */
    private Proceso crearProcesoCPU(String nombre) {
        System.out.print("Tipo de tarea: ");
        String tarea = scanner.nextLine();
        System.out.print("Intensidad (1-5): ");
        int intensidad = obtenerOpcion();
        return new ProcesoCPU(nombre, tarea, intensidad);
    }
    
    /**
     * Crea un proceso E/S con parámetros específicos.
     */
    private Proceso crearProcesoES(String nombre) {
        System.out.print("Tipo de dispositivo: ");
        String dispositivo = scanner.nextLine();
        System.out.print("Número de operaciones: ");
        int operaciones = obtenerOpcion();
        return new ProcesoES(nombre, dispositivo, operaciones);
    }
    
    /**
     * Crea un proceso demonio con parámetros específicos.
     */
    private Proceso crearProcesoDemonio(String nombre) {
        System.out.print("Tipo de servicio: ");
        String servicio = scanner.nextLine();
        System.out.print("Número de ciclos: ");
        int ciclos = obtenerOpcion();
        return new ProcesoDemonio(nombre, servicio, ciclos);
    }
    
    /**
     * Lista todos los procesos en la cola.
     * Demuestra polimorfismo en la representación toString().
     */
    private void listarProcesos() {
        System.out.println("\n--- PROCESOS EN COLA ---");
        if (planificador.getTamanoCola() == 0) {
            System.out.println("📭 No hay procesos en la cola.");
            return;
        }
        
        System.out.println("Total de procesos: " + planificador.getTamanoCola());
        System.out.println();
        planificador.listarProcesos(); // Método que no imprime mensajes de usuario
    }
    
    /**
     * Ejecuta todos los procesos en la cola.
     * Demuestra polimorfismo en la ejecución.
     */
    private void ejecutarTodosLosProcesos() {
        System.out.println("\n--- EJECUTANDO PROCESOS ---");
        if (planificador.getTamanoCola() == 0) {
            System.out.println("📭 No hay procesos para ejecutar.");
            return;
        }
        
        System.out.println("🚀 Iniciando ejecución de " + planificador.getTamanoCola() + " proceso(s)...");
        System.out.println();
        
        long tiempoInicio = System.currentTimeMillis();
        planificador.ejecutarTodosProcesos(); // Polimorfismo en acción
        long tiempoFin = System.currentTimeMillis();
        
        System.out.println();
        System.out.println("✅ Ejecución completada en " + (tiempoFin - tiempoInicio) + " ms");
    }
    
    /**
     * Muestra información detallada sobre los tipos de proceso.
     */
    private void mostrarInformacionTipos() {
        System.out.println("\n" + repetirCaracter("=", 50));
        System.out.println("         INFORMACIÓN DE TIPOS DE PROCESO");
        System.out.println(repetirCaracter("=", 50));
        
        System.out.println("\n🖥️  PROCESOS CPU:");
        System.out.println("   • Tareas computacionalmente intensivas");
        System.out.println("   • Ejemplos: Codificación de video, cálculos, renderizado");
        System.out.println("   • Características: Alto uso de CPU, poco I/O");
        
        System.out.println("\n💾  PROCESOS E/S:");
        System.out.println("   • Operaciones de entrada/salida con dispositivos");
        System.out.println("   • Ejemplos: Lectura de archivos, consultas DB, red");
        System.out.println("   • Características: Frecuentes bloqueos por I/O");
        
        System.out.println("\n👻  PROCESOS DEMONIO:");
        System.out.println("   • Servicios de sistema en segundo plano");
        System.out.println("   • Ejemplos: Monitoreo, logging, antivirus");
        System.out.println("   • Características: Ejecución continua, baja prioridad");
    }
    
    /**
     * Muestra estadísticas del sistema.
     */
    private void mostrarEstadisticas() {
        System.out.println("\n--- ESTADÍSTICAS DEL SISTEMA ---");
        System.out.println("📊 Procesos en cola: " + planificador.getTamanoCola());
        System.out.println("🔄 Ejecuciones realizadas: " + planificador.getContadorEjecuciones());
        System.out.println("⏱️  Tiempo promedio estimado: " + planificador.obtenerTiempoPromedioCola() + " ms");
        System.out.println("🏷️  Planificador: " + planificador.toString());
    }
    
    /**
     * Limpia la cola de procesos.
     */
    private void limpiarCola() {
        System.out.println("\n--- LIMPIAR COLA ---");
        if (planificador.getTamanoCola() == 0) {
            System.out.println("📭 La cola ya está vacía.");
            return;
        }
        
        System.out.print("¿Está seguro de eliminar todos los procesos? (s/n): ");
        String confirmacion = scanner.nextLine().toLowerCase();
        
        if (confirmacion.equals("s") || confirmacion.equals("si")) {
            planificador.limpiarCola();
            System.out.println("✅ Cola limpiada exitosamente.");
        } else {
            System.out.println("❌ Operación cancelada.");
        }
    }
    
    /**
     * Pausa la ejecución para que el usuario pueda leer los resultados.
     */
    private void pausarParaContinuar() {
        System.out.println("\n" + repetirCaracter("-", 40));
        System.out.print("Presione Enter para continuar...");
        scanner.nextLine();
    }
    
    /**
     * Finaliza el sistema liberando recursos.
     */
    private void finalizarSistema() {
        System.out.println("\n" + repetirCaracter("=", 40));
        System.out.println("🔚 Finalizando Simulador de Procesos...");
        planificador.finalizar();
        scanner.close();
        System.out.println("✅ Sistema finalizado correctamente.");
        System.out.println("¡Gracias por usar el simulador!");
        System.out.println(repetirCaracter("=", 40));
    }
    
    /**
     * Método utilitario para repetir un carácter n veces.
     * Reemplaza String.repeat() para compatibilidad con Java < 11
     */
    private String repetirCaracter(String caracter, int veces) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < veces; i++) {
            sb.append(caracter);
        }
        return sb.toString();
    }
    
    // Getters para encapsulación
    public PlanificadorProcesos getPlanificador() {
        return planificador;
    }
    
    public boolean isEjecutandose() {
        return ejecutandose;
    }
}
