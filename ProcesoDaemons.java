/**
 * Daemon Process - Represents background system services.
 * Examples: System monitoring, logging services, antivirus scanning.
 */
public class ProcesoDaemons extends Proceso {
    private String serviceType;
    private int cycles;
    
    public ProcesoDaemons(String name, String serviceType, int cycles) {
        super(name);
        this.serviceType = serviceType;
        this.cycles = Math.max(1, cycles);
    }
    
    // Overloaded constructor
    public ProcesoDaemons(String name, String serviceType) {
        this(name, serviceType, 3); // Default 3 cycles
    }
    
    @Override
    public void execute() {
        System.out.println("  → Starting daemon service: " + serviceType);
        
        for (int i = 1; i <= cycles; i++) {
            System.out.println("    Monitoring cycle " + i + "/" + cycles + " - " + serviceType);
            System.out.println("      → Checking system status...");
            
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                System.out.println("    Daemon interrupted!");
                return;
            }
            
            System.out.println("      → " + serviceType + " status: OK");
        }
        System.out.println("  → Daemon service completed!");
    }
    
    @Override
    public String getProcessType() {
        return "Daemon Process";
    }
    
    // Getters and Setters
    public String getServiceType() { return serviceType; }
    public void setServiceType(String serviceType) { this.serviceType = serviceType; }
    public int getCycles() { return cycles; }
    public void setCycles(int cycles) { 
        this.cycles = Math.max(1, cycles); 
    }
    
    @Override
    public String toString() {
        return super.toString() + " - Service: " + serviceType + ", Cycles: " + cycles;
    }
}
