import java.io.*;
import java.util.*;
import java.text.*;

/**
 * A program that schedules an optimal route for a truck to complete a set of jobs
 *
 * @author Kevin Ni
 */
public class FreightSystem{

	private static final String UNLOADING = "Unloading";
	private static final String COST = "Cost";
	private static final String JOB = "Job";

	/**
	 * This is the entry point of the program 
	 * 
	 * @param args 
	 * command line arguments. args[0] should contain the name of the input file.
	 *
	 * @pre args.length == 1
	 * @pre args[0] != null
	 */
	public static void main(String[] args){
		new FreightSystem().run(args[0]);
	}

	private AStarRouter router;

	/**
	 * class constructor
	 */
	private FreightSystem(){
		router = new AStarRouter();
	}

	/**
	 * runs the rental system on an input file
	 *
	 * @param fin input file
	 * @pre fin != null
	 */
	private void run(String fin){
		Scanner sc = null;
		try{
			sc = new Scanner(new FileReader(fin));

			int i = 1;
			while(sc.hasNextLine()){
				executeLine(sc.nextLine(), i++);
			}

			System.out.println(router.run());
		}
		catch(FileNotFoundException e)
		{
			System.err.println(String.format("File %s not found", fin));
		}
		finally{
			if (sc != null){
				sc.close();
			}
		}
	}

	/**
	 * parses and executes one line of input
	 *
	 * @param line
	 * the line of input to be parsed and executed
	 *
	 * @param n
	 * the line number
	 */
	private void executeLine(String line, int n){
		String[] input = digest(line);
		if(input.length == 0) return;

		try{
			switch(input[0]){
				case UNLOADING:
					router.declareNode(input[2], Integer.parseInt(input[1]));
				case COST:
					router.declareEdge(input[2], input[3], Integer.parseInt(input[1]));
				case JOB:
					router.declareJob(input[1], input[2]);
			}
		}
		catch(NumberFormatException | IndexOutOfBoundsException e){
			System.err.println(String.format("Line %s not parseable", n));
		}
	}

	/**
	 * removes comments from a line and splits the string on whitespaces
	 *
	 * @param s the line to be processed
	 *
	 * @pre s != null
	 *
	 * @post value != null
	 * @post value.containsAll(x -&gt; {x != null})
	 *
	 * @return s with comments removed and splitted on whitespace
	 */
	private String[] digest(String s){
		String decommented = s.split("#", 2)[0];
		return decommented.split("\\s+");
	}
}
