import java.util.Collections;


public class PriorityP extends SchedulingAlgorithm {
	PriorityP(String inputFile) {
		super(inputFile);
	}
		
	//PREEMPTIVE
	public void schedule() {
		readFile();
		Collections.sort(processes, new ArrivalTimeComparator());
		
		int pCount = 0;
		Process currentProcess = null;
		
		while(true) {
			// update waiting time
			for(int i = 0; i < waitList.size(); i++)
				waitList.get(i).waitingTime += 1;
			
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
				Collections.sort(waitList, new PriorityComparator());
				currentProcess = acceptNextJob();			
			} else if (pCount == processes.size() && !cpuBusy) { //there aren't any processes anymore
				break;
			}
			cpuTick++;
		}
		stats.updateAll();
		writeFile();
	}
	
	@Override
	public void processJob(Process p) {
		// verify if a high priority process has arrived
		Collections.sort(waitList, new PriorityComparator());

		// if so, stop it
		if (!waitList.isEmpty() && waitList.getFirst().priority < p.priority) {
			p.contextSwitchCount += 1;
			cpuBusy = false;
			waitList.add(p);

		} else { // segue com a vida
			p.remainingBurstTime--;
			if(p.remainingBurstTime == 0) { //if current process is served, it is removed from waitList and CPU becomes free
				cpuBusy = false;
				p.responseTime = cpuTick;
				p.turnaround = cpuTick - p.arrivalTime;
				servedList.add(p);
			}
		}
	}
}
