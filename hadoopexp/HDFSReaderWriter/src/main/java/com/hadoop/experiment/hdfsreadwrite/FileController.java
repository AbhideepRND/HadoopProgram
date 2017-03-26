package com.hadoop.experiment.hdfsreadwrite;

import java.io.Console;
import java.io.IOException;
import java.util.Scanner;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.ToolRunner;

import com.hadoop.experiment.mapreduce.ReduceLogFile;
import com.hadoop.experiment.mapreducecompositekey.ReduceLogConfig;
import com.hadoop.experiment.seqfile.ConsumerComplainMapReduce;
import com.hadoop.experiment.seqfile.ConsumerComplainSequenceFileReader;
import com.hadoop.experiment.seqfile.ConsumerComplainSequenceFileWriter;

public class FileController {

	public static void main(String[] args) throws IOException {
		final Console console = System.console();
		try(final Scanner scanner = new Scanner(System.in)){
		while (true) {

			System.out
					.println("=================    Welcome to HDFS File System    =============");
			System.out.println("1. For Add file to HDFS ");
			System.out.println("2. For Delete file to HDFS ");
			System.out.println("3. Apply MapReduce ");
			System.out.println("4. Apply MapReduce By Group ");
			System.out.println("5. Sequence file writer in HDFS");
			System.out.println("6. Sequence file reader");
			System.out.println("7. Run reduce job over sequence file");
			System.out.println("20. Exit ");
			try{
			switch (scanner.nextInt()) {
			case 1:
					System.out
							.println("=================    Please provide the below details  =============");
					System.out.println("1. Source File location ");
						
						final String sourceLocation = console.readLine();
					System.out.println("2. Destination File Location ");
						final String destinationLocation = console.readLine();
						new AddFilesToHDFS().readFile(sourceLocation.trim(),destinationLocation.trim());
						break;
			case 2:
							System.out
							.println("=================    Please provide the below details  =============");
					System.out.println("1. Source File location ");
						final String deleteLocation = console.readLine();
						new DeleteFilesToHDFS().deleteFormHDFS(deleteLocation.trim());
						break;
						
			case 3: 	String[] logArgs = new String[2];
						System.out.println("Please provide the input file location");
						logArgs[0] = console.readLine();
						System.out.println("Please provide the out file location");
						logArgs[1] = console.readLine();
												
						//logArgs[0] = "/InputFiles/Log/access_log";
						//logArgs[1] = "/OutputFiles/Log";
						
						final int run = new ReduceLogFile().run(logArgs);
						if( run ==1){
								System.out.println("Reduce of file is complete");
						}
						break;
			case 4: 	String[] logArgs2 = new String[2];
						/*System.out.println("Please provide the input file location");
						logArgs2[0] = console.readLine();
						System.out.println("Please provide the out file location");
						logArgs2[1] = console.readLine();*/
												
						logArgs2[0] = "/InputFiles/Log/access_log";
						logArgs2[1] = "/OutputFiles/Log";
						
						final int run2 = new ReduceLogConfig().run(logArgs2);
						if( run2 ==1){
								System.out.println("Reduce of file is complete");
						}
						break;
						
			case 5: 	String[] sequenceData = new String[2];
						System.out.println("Please provide the input file location in local directory");
						sequenceData[0] = console.readLine();
						System.out.println("Please provide the output file location in HDFS");
						sequenceData[1] = console.readLine();
												
						new ConsumerComplainSequenceFileWriter().writeSequenceFile(sequenceData);
								System.out.println("Reduce of file is complete");
						break;
			case 6: 	String[] sequenceData2 = new String[2];
						System.out.println("Please provide the output file location in HDFS");
						sequenceData2[0] = console.readLine();
												
						new ConsumerComplainSequenceFileReader().readSequenceData(sequenceData2);
								System.out.println("Reduce of file is complete");
						break;
						
			case 7: 	String[] sequenceData3 = new String[2];
						System.out.println("Please provide the input file location in HDFS");
						sequenceData3[0] = console.readLine();
						System.out.println("Please provide the output file location in HDFS");
						sequenceData3[1] = console.readLine();
												
						final int run3 =  new ConsumerComplainMapReduce().run(sequenceData3);
						if(run3 == 0){
							System.out.println("Reduce of file is complete");
						} else{
							System.out.println("Reduce Job is failed");
						}
								
						break;
					
			case 20: System.exit(0);
				}
			} catch (IOException e){
				e.printStackTrace();
			} catch(Exception e){
				e.printStackTrace();
			}
			
			}

		}

	}

}
