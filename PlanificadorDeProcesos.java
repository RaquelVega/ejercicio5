import java.util.ArrayList;
import java.util.List;

/**
 * Planificador de Procesos - Gestiona la cola de procesos y su ejecución.
 * Implementa el patrón Strategy para diferentes algoritmos de planificación.
 * 
 * @author Raquel Vega
 * @version 1.0
 */
public class PlanificadorProcesos {
    private List<Proceso> colaProcesos;
    private int contadorEjecuciones;
    
    /**
     * Constructor por defecto.
     * Inicializa la cola de procesos vacía.
     */
    public PlanificadorProcesos() {
        this.colaProcesos = new ArrayList<>();
        this.contadorEjecuciones = 0;
    }
    
    /**
     * Agrega un proceso a la cola de planificación.
     * @param proceso El proceso a agregar
     * @return true si se agregó exitosamente, false en caso contrario
     */
    public boolean agregarProceso(Proceso proceso) {
        if (proceso == null) {
            return false;
        }
        return colaProcesos.add(proceso);
    }
    
    /**
     * Lista todos los procesos en la cola.
     * Utiliza polimorfismo para mostrar información específica de cada tipo.
     */
    public void listarProcesos() {
        if (colaProcesos.isEmpty()) {
            return; // No mostrar mensaje aquí - solo en Principal
        }
        
        for (int i = 0; i < colaProcesos.size(); i++) {
            // Polimorfismo: cada proceso implementa toString() de manera diferente
            System.out.println((i + 1) + ". " + colaProcesos.get(i).toString());
        }
    }
    
    /**
     * Ejecuta todos los procesos en la cola.
     * Demuestra polimorfismo - cada tipo de proceso ejecuta de manera diferente.
     */
    public void ejecutarTodosProcesos() {
        if (colaProcesos.isEmpty()) {
            return;
        }
        
        contadorEjecuciones++;
        
        for (Proceso proceso : colaProcesos) {
            // Polimorfismo: execute() se comporta diferente según el tipo de proceso
            proceso.ejecutar();
        }
    }
    
    /**
     * Obtiene el tiempo promedio de la cola (simulado).
     * @return tiempo promedio estimado
     */
    public int obtenerTiempoPromedioCola() {
        if (colaProcesos.isEmpty()) {
            return 0;
        }
        
        int tiempoTotal = 0;
        for (Proceso proceso : colaProcesos) {
            // Polimorfismo: cada tipo calcula su tiempo de manera diferente
            tiempoTotal += proceso.obtenerTiempoEjecucion();
        }
        
        return tiempoTotal / colaProcesos.size();
    }
    
    /**
     * Limpia la cola de procesos.
     */
    public void limpiarCola() {
        colaProcesos.clear();
        contadorEjecuciones = 0;
    }
    
    /**
     * Finaliza el planificador liberando recursos.
     */
    public void finalizar() {
        limpiarCola();
    }
    
    // Getters
    public List<Proceso> getColaProcesos() {
        return new ArrayList<>(colaProcesos); // Retorna copia para encapsulación
    }
    
    public int getTamanoCola() {
        return colaProcesos.size();
    }
    
    public int getContadorEjecuciones() {
        return contadorEjecuciones;
    }
    
    @Override
    public String toString() {
        return String.format("PlanificadorProcesos{procesos=%d, ejecuciones=%d}", 
                           colaProcesos.size(), contadorEjecuciones);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PlanificadorProcesos that = (PlanificadorProcesos) obj;
        return contadorEjecuciones == that.contadorEjecuciones && 
               colaProcesos.equals(that.colaProcesos);
    }
}
