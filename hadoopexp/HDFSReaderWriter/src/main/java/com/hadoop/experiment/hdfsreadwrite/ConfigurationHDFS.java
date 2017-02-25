package com.hadoop.experiment.hdfsreadwrite;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class ConfigurationHDFS {

	protected FileSystem getFileSystem() throws IOException{
		final Configuration configuration = new Configuration();
		configuration.addResource(new Path("/home/hitman/ProgramFiles/hadoop-2.6.2/etc/hadoop/core-site.xml"));
		configuration.addResource(new Path("/home/hitman/ProgramFiles/hadoop-2.6.2/etc/hadoop/hdfs-site.xml"));
		final FileSystem fileSystem = FileSystem.get(configuration);
		return fileSystem;
	}
	
}
