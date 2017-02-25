package com.hadoop.experiment.hdfsreadwrite;

import java.io.Console;
import java.io.IOException;
import java.util.Scanner;

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
			System.out.println("4. Exit ");
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
						
			case 3: System.out.println("Fuck You");
					break;
					
			case 4: System.exit(0);
				}
			} catch (IOException e){
				System.out.println(e.getMessage());
			} catch(Exception e){
				System.out.println(e.getMessage());
			}
			
			}

		}

	}

}