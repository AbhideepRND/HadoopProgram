package com.hadoop.experiment.seqfile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.DefaultCodec;

import com.hadoop.experiment.hdfsreadwrite.ConfigurationHDFS;
import com.sun.tools.internal.ws.wsdl.document.jaxws.Exception;

/**
 * 
 * @author Abhideep Bakshi
 *
 *	The SequenceStockFileWriter is used to read the data from local file system and write the data in HDFS 
 *	is Sequence File format. And it use block compression logic where synchronizer marker is added between 
 *	two blocks. 
 */

public class ConsumerComplainSequenceFileWriter {

	public void writeSequenceFile(String[] args) throws IOException {
		
		
		final FileSystem fileSystem = ConfigurationHDFS.getFileSystem();
		String desctinationLoc = args[1]; 
		String filename = args[0].substring(args[0].lastIndexOf("/")+1, args[0].length());
		filename= filename.substring(0,filename.indexOf("."))+".seqfile";
		
		if(desctinationLoc.charAt(desctinationLoc.length()-1) !='/'){
			desctinationLoc = desctinationLoc + "/" + filename;
		} else{
			desctinationLoc = desctinationLoc + filename;
		}
		
		final File inputFile = new File(args[0]);
		final FSDataOutputStream fsOutputStream = fileSystem.create(new Path(desctinationLoc));
		
		/**
		 * Here we cerate the SequenceFileWriter with block compression logic
		 * AutoCloseable is implemented by Writer class 
		 */
		try(SequenceFile.Writer writer =    
		        SequenceFile.createWriter(ConfigurationHDFS.getConfiguration(),
		        		fsOutputStream,Text.class,ConsumerComplainWriter.class,
		                SequenceFile.CompressionType.BLOCK,
		                new DefaultCodec());
				final BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile))){
			
			Text key = new Text();
			bufferedReader.lines().forEach(e->{
				ConsumerComplainWriter fromLine;
				try {
					fromLine = ConsumerComplainWriter.fromLine(e);
					key.set(fromLine.getComplainID());
					writer.append(key, fromLine);
				} catch (IOException | ArrayIndexOutOfBoundsException el) {
					System.out.println("Error in "+e);
				}
			});
		} 
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		
		
		String filename = "Consumer_Complaints.csv";
		System.out.println(filename.substring(0,filename.indexOf(".")));
		/*final BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("//home//liveyoung//Downloads//Consumer_Complaints.csv")));
		bufferedReader.lines().forEach(e->{
			ConsumerComplainWriter fromLine;
			try {
				fromLine = ConsumerComplainWriter.fromLine(e);
				System.out.println(fromLine.getComplainID());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});*/
	}
}
