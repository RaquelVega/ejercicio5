/**
 * CPU Process - Represents computational intensive processes.
 * Examples: Video encoding, mathematical calculations, image processing.
 */
public class ProcesoCPU extends Proceso {
    private String taskType;
    private int intensity; // 1-5 scale
    
    public ProcesoCPU(String name, String taskType, int intensity) {
        super(name);
        this.taskType = taskType;
        this.intensity = Math.max(1, Math.min(5, intensity));
    }
    
    // Overloaded constructor
    public ProcesoCPU(String name, String taskType) {
        this(name, taskType, 3); // Default medium intensity
    }
    
    @Override
    public void execute() {
        System.out.println("  → Executing " + taskType + " (Intensity: " + intensity + "/5)");
        
        // Simulate CPU work
        for (int i = 1; i <= intensity; i++) {
            System.out.println("    Processing step " + i + "/" + intensity + "...");
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                System.out.println("    Process interrupted!");
                return;
            }
        }
        System.out.println("  → CPU task completed!");
    }
    
    @Override
    public String getProcessType() {
        return "CPU Process";
    }
    
    // Getters and Setters
    public String getTaskType() { return taskType; }
    public void setTaskType(String taskType) { this.taskType = taskType; }
    public int getIntensity() { return intensity; }
    public void setIntensity(int intensity) { 
        this.intensity = Math.max(1, Math.min(5, intensity)); 
    }
    
    @Override
    public String toString() {
        return super.toString() + " - Task: " + taskType + ", Intensity: " + intensity;
    }
}
