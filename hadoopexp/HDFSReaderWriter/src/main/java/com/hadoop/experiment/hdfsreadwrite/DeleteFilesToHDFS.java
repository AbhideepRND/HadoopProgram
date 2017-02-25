package com.hadoop.experiment.hdfsreadwrite;

import java.io.IOException;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class DeleteFilesToHDFS extends ConfigurationHDFS{

	@SuppressWarnings("deprecation")
	public void deleteFormHDFS(String destinationLoc) throws IOException, Exception{
		
		try(FileSystem fileSystem = getFileSystem()){
		final Path path = new Path(destinationLoc);
			if(!fileSystem.exists(path)){
				throw new Exception("File not exits");
			}
			fileSystem.delete(path);
		}

		
	}
}
