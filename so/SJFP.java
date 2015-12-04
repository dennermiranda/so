import java.util.Collections;

//Classes from Felipe Zschornack and Maria Isabel V Lima
public class SJFP extends SchedulingAlgorithm {
	SJFP(String inputFile) {
		super(inputFile);
	}
	
	//PREEMPTIVE
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
			Collections.sort(waitList, new BurstTimeComparator());
			
			//if CPU is busy, continues serving current process
			if(cpuBusy) {
				if(!waitList.getFirst().equals(currentProcess)) { //a higher priority process has arrived
					if(waitList.getFirst().burstTime < remainingBurstTime) {
						cpuBusy = false;
					}
				} else {
					processJob(currentProcess);
				}
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
