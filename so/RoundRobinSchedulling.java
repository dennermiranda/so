import java.util.Collections;

public class RoundRobinSchedulling extends SchedulingAlgorithm {

	private int quantum = 1;

	RoundRobinSchedulling(String inputFile) {
		super(inputFile);
	}

	public void schedule() {
		readFile();
		Collections.sort(processes, new ArrivalTimeComparator());
		
		int pCount = 0;
		//must set quantum on build
		int remainingQuantum = this.quantum;
		Process currentProcess = null;
		while (true) {

			// puts all processes that arrive at current cpuTick in waitList
			while ((pCount < processes.size()) && (processes.get(pCount).arrivalTime == cpuTick)) {
				waitList.add(processes.get(pCount));
				pCount++;
			}

			// if CPU is busy, continues serving current process and there is still quantum
			if (cpuBusy&& remainingQuantum!=0) {
				processJob(currentProcess);
				remainingQuantum--;
			}
			
			//sets cpuBusy to false if quantum has reached zero
			if(remainingQuantum==0)
				cpuBusy = false;

			if (!waitList.isEmpty() && !cpuBusy) { // there are processes
													// waiting, then serve next
													// process
				if(currentProcess.remainingBurstTime > 0){
					waitList.add(currentProcess);
				}
				currentProcess = acceptNextJob();
				remainingQuantum = this.quantum;
				
			} else if (pCount == processes.size() && !cpuBusy) { // there aren't
																	// any
																	// processes
																	// anymore
				break;
			}
			cpuTick++;
		}
		stats.updateAll();
		writeFile();
	}
	
	public int getQuantum() {
		return quantum;
	}

	public void setQuantum(int quantum) {
		this.quantum = quantum;
	}
}
