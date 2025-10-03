/**
 * Abstract base class for all processes in the simulator.
 * Demonstrates inheritance and polymorphism principles.
 */
public abstract class Proceso {
    private static int nextPID = 1000;
    private int pid;
    private String name;
    
    public Proceso(String name) {
        this.pid = nextPID++;
        this.name = name;
    }
    
    // Abstract method - must be implemented by subclasses
    public abstract void execute();
    
    public abstract String getProcessType();
    
    // Getters and Setters
    public int getPid() { return pid; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    @Override
    public String toString() {
        return String.format("[PID: %d] %s (%s)", pid, name, getProcessType());
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Proceso process = (Proceso) obj;
        return pid == process.pid;
    }
}
