//**
 * Proceso Demonio - Representa servicios de sistema en segundo plano.
 * Ejemplos: Monitoreo del sistema, servicios de logging, escaneo antivirus.
 * 
 * @author Raquel Vega
 * @version 1.0
 */
public class ProcesoDemonio extends Proceso {
    private String tipoServicio;
    private int ciclos;
    
    /**
     * Constructor completo.
     * @param nombre Nombre del proceso demonio
     * @param tipoServicio Tipo de servicio que proporciona
     * @param ciclos Número de ciclos de monitoreo
     */
    public ProcesoDemonio(String nombre, String tipoServicio, int ciclos) {
        super(nombre);
        this.tipoServicio = tipoServicio != null ? tipoServicio : "Servicio Genérico";
        this.ciclos = Math.max(1, ciclos);
    }
    
    /**
     * Constructor con ciclos por defecto.
     * @param nombre Nombre del proceso demonio
     * @param tipoServicio Tipo de servicio que proporciona
     */
    public ProcesoDemonio(String nombre, String tipoServicio) {
        this(nombre, tipoServicio, 3); // 3 ciclos por defecto
    }
    
    /**
     * Implementación específica de ejecución para procesos demonio.
     * Simula monitoreo continuo del sistema.
     */
    @Override
    public void ejecutar() {
        for (int i = 1; i <= ciclos; i++) {
            try {
                // Simula trabajo de monitoreo (menos intensivo)
                Thread.sleep(150 + (int)(Math.random() * 100));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
    
    @Override
    public String obtenerTipoProceso() {
        return "Proceso Demonio";
    }
    
    @Override
    public int obtenerTiempoEjecucion() {
        return (150 + 50) * ciclos; // Tiempo promedio estimado
    }
    
    // Getters y Setters con validación
    public String getTipoServicio() { 
        return tipoServicio; 
    }
    
    public void setTipoServicio(String tipoServicio) { 
        if (tipoServicio != null && !tipoServicio.trim().isEmpty()) {
            this.tipoServicio = tipoServicio.trim();
        }
    }
    
    public int getCiclos() { 
        return ciclos; 
    }
    
    public void setCiclos(int ciclos) { 
        this.ciclos = Math.max(1, ciclos); 
    }
    
    /**
     * Override de toString con información específica del proceso demonio.
     */
    @Override
    public String toString() {
        return super.toString() + 
               String.format(" - Servicio: %s, Ciclos: %d", tipoServicio, ciclos);
    }
    
    /**
     * Override de equals considerando atributos específicos.
     */
    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        ProcesoDemonio that = (ProcesoDemonio) obj;
        return ciclos == that.ciclos && 
               tipoServicio.equals(that.tipoServicio);
    }
}
