/**
 * I/O Process - Represents input/output intensive processes.
 * Examples: File operations, database queries, network requests.
 */
public class ProcesoES extends Proceso {
    private String deviceType;
    private int operations;
    
    public ProcesoES(String name, String deviceType, int operations) {
        super(name);
        this.deviceType = deviceType;
        this.operations = Math.max(1, operations);
    }
    
    // Overloaded constructor
    public ProcesoES(String name, String deviceType) {
        this(name, deviceType, 2); // Default 2 operations
    }
    
    @Override
    public void execute() {
        System.out.println("  → Starting I/O operations with " + deviceType);
        
        for (int i = 1; i <= operations; i++) {
            System.out.println("    I/O Operation " + i + "/" + operations + " - Accessing " + deviceType + "...");
            
            // Simulate I/O blocking
            System.out.println("      [BLOCKED] Waiting for " + deviceType + "...");
            try {
                Thread.sleep(400); // I/O wait time
            } catch (InterruptedException e) {
                System.out.println("    I/O interrupted!");
                return;
            }
            System.out.println("      [UNBLOCKED] Data received from " + deviceType);
        }
        System.out.println("  → I/O operations completed!");
    }
    
    @Override
    public String getProcessType() {
        return "I/O Process";
    }
    
    // Getters and Setters
    public String getDeviceType() { return deviceType; }
    public void setDeviceType(String deviceType) { this.deviceType = deviceType; }
    public int getOperations() { return operations; }
    public void setOperations(int operations) { 
        this.operations = Math.max(1, operations); 
    }
    
    @Override
    public String toString() {
        return super.toString() + " - Device: " + deviceType + ", Operations: " + operations;
    }
}