import java.io.*;
import java.util.*;
import java.text.*;

/**
 * Program to peform A* search 
 *
 * @author Kevin Ni
 */
public class FreightSystem{

	static final String UNLOADING = "Unloading";
	static final String COST = "Cost";
	static final String JOB = "Job";

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

	/**
	 * class constructor
	 */
	FreightSystem(){
	}

	/**
	 * runs the rental system on an input file
	 *
	 * @param fin input file
	 * @pre fin != null
	 */
	void run(String fin){
		Scanner sc = null;
		try{
			sc = new Scanner(new FileReader(fin));

			while(sc.hasNextLine()){
				executeLine(sc.nextLine());
			}
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
	 */
	void executeLine(String line){
		String[] input = digest(line);
		if(input.length == 0) return;
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
	String[] digest(String s){
		String decommented = s.split("#", 2)[0];
		return decommented.split("\\s+");
	}
}
