package com.hadoop.experiment.hdfsreadwrite;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class ConfigurationHDFS {

	final private static Configuration configuration ;
	static {
		configuration = new Configuration();
		configuration.addResource(new Path("/home/hitman/ProgramFiles/hadoop-2.6.2/etc/hadoop/core-site.xml"));
		configuration.addResource(new Path("/home/hitman/ProgramFiles/hadoop-2.6.2/etc/hadoop/hdfs-site.xml"));
	}
	
	public static  FileSystem getFileSystem() throws IOException{
		return FileSystem.get(configuration);
	}
	
	public static Configuration getConfiguration() throws IOException{
		return configuration;
	}
	
	
}
