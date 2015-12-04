import java.util.Comparator;

//Classes from Felipe Zschornack and Maria Isabel V Lima
public class Process {
	int arrivalTime;
	String pID;
	int burstTime;
	int priority;
	
	int turnaround;
	int waitingTime;
	int responseTime;
	int throughput;
	int contextSwitchCount;

	Process(String[] parts) {
		this.arrivalTime = Integer.parseInt(parts[0]);
		this.pID = parts[1];
		this.burstTime = Integer.parseInt(parts[2]);
		this.priority = Integer.parseInt(parts[3]);
		
		this.turnaround = 0;
		this.waitingTime = 0;
		this.responseTime = 0;
		this.throughput = 0;
		this.contextSwitchCount = 0;
	}
}

class ArrivalTimeComparator implements Comparator<Process> {
    @Override
    public int compare(Process p1, Process p2) {
        return p1.arrivalTime - p2.arrivalTime;
    }
}

class BurstTimeComparator implements Comparator<Process> {
    @Override
    public int compare(Process p1, Process p2) {
        return p1.burstTime - p2.burstTime;
    }
}

class PriorityComparator implements Comparator<Process> {
    @Override
    public int compare(Process p1, Process p2) {
        return p1.priority - p2.priority;
    }
}