import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

//Classes from Felipe Zschornack and Maria Isabel V Lima
public class SchedulingAlgorithm {
	String name;
	String inputFile;
	int remainingBurstTime;
	int cpuTick;
	boolean cpuBusy;
	LinkedList<Process> processes;
	LinkedList<Process> waitList;
	LinkedList<Process> servedList;
	Statistics stats;
	
	SchedulingAlgorithm(String inputFile) {
		this.name = getClass().getName();
		this.inputFile = inputFile;
		this.remainingBurstTime = 0;
		this.cpuTick = 0;
		this.cpuBusy = false;
		this.processes = new LinkedList<Process>();
		this.waitList = new LinkedList<Process>();
		this.servedList = new LinkedList<Process>();
		this.stats = new Statistics(this);
	}

	public void readFile() {
		BufferedReader br = null;

		try {
			String currentLine;
			br = new BufferedReader(new FileReader(inputFile));

			while ((currentLine = br.readLine()) != null) {
				String[] parts = currentLine.split(",");
				Process p = new Process(parts);
				processes.add(p);
			}
		} catch (IOException e) {
			System.err.println("Error reading file");
	        System.exit(1);
			//e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public void writeFile() {
		try {
			String content = stats.toString();
			File file = new File("statistics.txt");

			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();

			System.out.println("Statistics file generated");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void processJob(Process p) {
		remainingBurstTime--;
		if(remainingBurstTime == 0) { //if current process is served, it is removed from waitList and CPU becomes free
			cpuBusy = false;
			p.responseTime = cpuTick;
			p.turnaround = cpuTick - p.arrivalTime;
			servedList.add(p);
			waitList.remove();
		}
	}

	public Process acceptNextJob() {
		cpuBusy = true;
		Process p = waitList.getFirst();
		p.waitingTime = cpuTick - p.arrivalTime;
		remainingBurstTime = p.burstTime;
		
		return p;
	}
}
