import java.util.Collections;

//Classes from Felipe Zschornack and Maria Isabel V Lima
public class FCFS extends SchedulingAlgorithm {

	FCFS(String inputFile) {
		super(inputFile);
	}
		
	//NON-PREEMPTIVE
	public void schedule() {
		readFile();
		Collections.sort(processes, new ArrivalTimeComparator());
		
		int pCount = 0;
		Process currentProcess = null;
		
		while(true) {
			
			//puts all processes that arrive at current cpuTick in waitList
			while((pCount < processes.size()) && (processes.get(pCount).arrivalTime == cpuTick)) {
				waitList.add(processes.get(pCount));
				pCount++;
			}

			//if CPU is busy, continues serving current process
			if(cpuBusy) {
				processJob(currentProcess);
			}
			
			if(!waitList.isEmpty() && !cpuBusy) { //there are processes waiting, then serve next process
				currentProcess = acceptNextJob();			
			} else if (pCount == processes.size() && !cpuBusy) { //there aren't any processes anymore
				break;
			}
			cpuTick++;
		}
		stats.updateAll();
		writeFile();
	}
}
