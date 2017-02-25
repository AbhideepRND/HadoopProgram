package com.hadoop.experiment.hdfsreadwrite;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class AddFilesToHDFS  extends ConfigurationHDFS{
	
	public void readFile(final String sourceLoc, final String desctLoc ) throws IOException{
		String desctinationLoc = desctLoc; 
		final String filename = sourceLoc.substring(sourceLoc.lastIndexOf("/")+1, sourceLoc.length());
		
		if(desctinationLoc.charAt(desctinationLoc.length()-1) !='/'){
			desctinationLoc = desctinationLoc + "/" + filename;
		} else{
			desctinationLoc = desctinationLoc + filename;
		}
	
		try(FileSystem fileSystem = getFileSystem()){
			final Path destPath = new Path(desctinationLoc);
			if(fileSystem.exists(destPath)){
				try(FSDataOutputStream out = fileSystem.append(destPath)){
					@SuppressWarnings("resource")
					final BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(new File(sourceLoc)));
					byte[] bufferData = new byte[1024];
					int bytes = 0;
					while((bytes = bufferedInputStream.read(bufferData)) >0 ){
						out.write(bufferData,0,bytes);
					}
				}
			} else{
				try(FSDataOutputStream out = fileSystem.create(destPath, true)){
					@SuppressWarnings("resource")
					final BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(new File(sourceLoc)));
					byte[] bufferData = new byte[1024];
					int bytes = 0;
					while((bytes = bufferedInputStream.read(bufferData)) >0 ){
						out.write(bufferData,0,bytes);
					}
				}
			}
			
			
			
		}
	}
	
	

}
