//Classes from Felipe Zschornack and Maria Isabel V Lima
public class Statistics {
	SchedulingAlgorithm sa;
	String header;
	float processingTime;
	float cpuUtilization;
	float avrgThroughput;
	float avrgTurnaround;
	float avrgWaitingTime;
	float avrgResponseTime;
	float avrgContextSwitch;
	int executedProcesses;
	
	Statistics(SchedulingAlgorithm sa) {
		this.sa = sa;
		this.header = "|| " + sa.name + " ||" ;
		this.processingTime = 0;
		this.cpuUtilization = 0;
		this.avrgThroughput = 0;
		this.avrgTurnaround = 0;
		this.avrgWaitingTime = 0;
		this.avrgResponseTime = 0;
		this.avrgContextSwitch = 0;
		this.executedProcesses = 0;
	}
	
	public void getProcessingTime() {
		float sum = 0;
		for(int i=0; i<sa.servedList.size(); i++) {
			sum += sa.servedList.get(i).burstTime;
		}
		processingTime = sum;
	}
	
	public void getCpuUtilization() {
		cpuUtilization = processingTime/sa.cpuTick;
	}
	
	public void getAvrgThroughput() {
		avrgThroughput = (float)sa.servedList.size()/sa.cpuTick;
	}
	
	public void getAvrgTurnaround() {
		float sum = 0;
		for(int i=0; i<sa.servedList.size(); i++) {
			sum += sa.servedList.get(i).turnaround;
		}
		avrgTurnaround = sum/sa.servedList.size();
	}
	
	public void getAvrgWaitingTime() {
		float sum = 0;
		for(int i=0; i<sa.servedList.size(); i++) {
			System.out.println(sa.servedList.get(i).waitingTime);
			sum += sa.servedList.get(i).waitingTime;
		}
		
		avrgWaitingTime = sum/sa.servedList.size();
	}
	
	public void getAvrgResponseTime() {
		float sum = 0;
		for(int i=0; i<sa.servedList.size(); i++) {
			sum += sa.servedList.get(i).responseTime;
		}
		
		avrgResponseTime = sum/sa.servedList.size();
	}
	
	public void getExecutedProcesses() {
		executedProcesses = sa.servedList.size();
	}
	
	public void updateAll() {
		getProcessingTime();
		getCpuUtilization();
		getAvrgThroughput();
		getAvrgTurnaround();
		getAvrgWaitingTime();
		getAvrgResponseTime();
		getExecutedProcesses();	
	}
	
    @Override
    public String toString() {
        String out = "";
        out += header + "\n";
        out += "Processing time: " + processingTime + "\n";
        out += "CPU utilization rate: " + cpuUtilization*100 + "%\n";
        out += "Average throughput: " + avrgThroughput + "\n";
        out += "Average turnaround: " + avrgTurnaround + "\n";
        out += "Average waiting time: " + avrgWaitingTime + "\n";
        out += "Average response time: " + avrgResponseTime + "\n";
        out += "Executed processes: " + executedProcesses + "\n";
        return out;
    }
}
