/**
 * Proceso CPU - Representa procesos intensivos en cálculo.
 * Ejemplos: Codificación de video, cálculos matemáticos, renderizado.
 * 
 * @author Raquel Vega
 * @version 1.0
 */
public class ProcesoCPU extends Proceso {
    private String tipoTarea;
    private int intensidad; // Escala 1-5
    
    /**
     * Constructor completo.
     * @param nombre Nombre del proceso
     * @param tipoTarea Tipo de tarea a realizar
     * @param intensidad Intensidad del procesamiento (1-5)
     */
    public ProcesoCPU(String nombre, String tipoTarea, int intensidad) {
        super(nombre);
        this.tipoTarea = tipoTarea != null ? tipoTarea : "Tarea CPU";
        this.intensidad = Math.max(1, Math.min(5, intensidad));
    }
    
    /**
     * Constructor con intensidad por defecto.
     * @param nombre Nombre del proceso
     * @param tipoTarea Tipo de tarea a realizar
     */
    public ProcesoCPU(String nombre, String tipoTarea) {
        this(nombre, tipoTarea, 3); // Intensidad media por defecto
    }
    
    /**
     * Implementación específica de ejecución para procesos CPU.
     * Simula trabajo computacional intensivo.
     */
    @Override
    public void ejecutar() {
        // Solo mostrar información técnica, no mensajes de usuario
        for (int i = 1; i <= intensidad; i++) {
            try {
                Thread.sleep(200 + (intensidad * 50)); // Simula carga CPU
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
    
    @Override
    public String obtenerTipoProceso() {
        return "Proceso CPU";
    }
    
    @Override
    public int obtenerTiempoEjecucion() {
        return (200 + (intensidad * 50)) * intensidad;
    }
    
    // Getters y Setters con validación
    public String getTipoTarea() { 
        return tipoTarea; 
    }
    
    public void setTipoTarea(String tipoTarea) { 
        if (tipoTarea != null && !tipoTarea.trim().isEmpty()) {
            this.tipoTarea = tipoTarea.trim();
        }
    }
    
    public int getIntensidad() { 
        return intensidad; 
    }
    
    public void setIntensidad(int intensidad) { 
        this.intensidad = Math.max(1, Math.min(5, intensidad)); 
    }
    
    /**
     * Override de toString con información específica del proceso CPU.
     */
    @Override
    public String toString() {
        return super.toString() + 
               String.format(" - Tarea: %s, Intensidad: %d/5", tipoTarea, intensidad);
    }
    
    /**
     * Override de equals considerando atributos específicos.
     */
    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        ProcesoCPU that = (ProcesoCPU) obj;
        return intensidad == that.intensidad && 
               tipoTarea.equals(that.tipoTarea);
    }
}
