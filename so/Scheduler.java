//Classes from Felipe Zschornack and Maria Isabel V Lima
public class Scheduler {

	public static void main(String[] args) {
		if(args.length != 2) {
			System.err.println("Parameters: input_file.csv algorithm_name");
			System.err.println("Possible algorithms: FCFS, SJF, SJFP, Priority, PriorityP, RR");
	        System.exit(1);
		}
		
		switch(args[1]) {
			case "FCFS":
				new FCFS(args[0]).schedule();
				break;
			case "SJF":
				new SJF(args[0]).schedule();
				break;
			case "SJFP":
				new SJFP(args[0]).schedule();
				break;
			case "Priority":
				break;
			case "PriorityP":
				break;
			case "RR":
				break;
			default:
				System.err.println("No matching algorithm found");
				System.err.println("Possible algorithms: FCFS, SJF, SJFP, Priority, PriorityP, RR");
		        System.exit(1);
		}
	}
}
