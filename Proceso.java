/**
 * Clase abstracta base para todos los procesos del simulador.
 * Define la interfaz común y demuestra principios de herencia y polimorfismo.
 * 
 * @author Raquel Vega
 * @version 1.0
 */
public abstract class Proceso {
    private static int siguientePID = 1000;
    private final int pid;
    private String nombre;
    
    /**
     * Constructor protegido para uso de subclases.
     * @param nombre Nombre descriptivo del proceso
     */
    protected Proceso(String nombre) {
        this.pid = siguientePID++;
        this.nombre = nombre != null ? nombre : "Proceso Sin Nombre";
    }
    
    /**
     * Método abstracto que debe ser implementado por cada tipo de proceso.
     * Demuestra polimorfismo - cada subclase implementa su propia lógica.
     */
    public abstract void ejecutar();
    
    /**
     * Obtiene el tipo de proceso como String.
     * @return Tipo específico del proceso
     */
    public abstract String obtenerTipoProceso();
    
    /**
     * Calcula el tiempo estimado de ejecución.
     * Método abstracto para demostrar polimorfismo.
     * @return Tiempo estimado en milisegundos
     */
    public abstract int obtenerTiempoEjecucion();
    
    // Getters y Setters con encapsulación apropiada
    public final int getPid() { 
        return pid; 
    }
    
    public String getNombre() { 
        return nombre; 
    }
    
    public void setNombre(String nombre) { 
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre.trim();
        }
    }
    
    /**
     * Override de toString para representación legible.
     * Cada subclase puede extender esta implementación.
     */
    @Override
    public String toString() {
        return String.format("[PID: %d] %s (%s)", 
                           pid, nombre, obtenerTipoProceso());
    }
    
    /**
     * Override de equals basado en PID único.
     * Dos procesos son iguales si tienen el mismo PID.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Proceso proceso = (Proceso) obj;
        return pid == proceso.pid;
    }
    
    /**
     * Override de hashCode consistente con equals.
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(pid);
    }
}

