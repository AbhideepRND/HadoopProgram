package com.hadoop.experiment.mapreducecompositekey;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class ReduceLogConfig {

	public int run(String[] args) throws Exception {
		/**
		 * Get a handle for the Configuration instance for the job.
		 */
		final Configuration configuration = new Configuration();
		final Job job = Job.getInstance(configuration, "WorkOnLog"); // Get initialize the Job that work on container
		
		/**
		 * The Job class’s @setJarByClass informs MapReduce that the supplied class should
			be used to determine the encapsulating JAR, which in turn is added to the
			classpath of all your map and reduce tasks.
			If JarByClass is not set then it will throw an error 
			java.lang.RuntimeException: java.lang.ClassNotFoundException: Class com.hadoop.experiment.mapreduce.MapLogger not found
		 */
		job.setJarByClass(ReduceLogConfig.class); 
		
		/**
		 * Set the Mapper class that used in Job
		 */
		job.setMapperClass(MapperLoggerGroup.class);
		
		/**
		 * Set the Reducer class that used in Job
		 */
		job.setReducerClass(ReduceLoggerGroup.class);
		
		/**If the map output key/value types differ from the input types, you must tell
		Hadoop what they are. Here, the map will output each word and file as key/value
		pairs, and both are Text objects.
		*/
		job.setOutputKeyClass(CompositeKeyGroup.class);
		job.setOutputValueClass(Text.class);
		
		Path input = new Path(args[0]);
		Path output = new Path(args[1]);
		
		/**
		 * Set the HDFS Input/Output directory for the Job
		 */
		FileInputFormat.addInputPath(job, input);
		FileOutputFormat.setOutputPath(job, output);
		
		/**
		 * Tell the framework to run the job and block until the job has completed.
		 */
	    if (job.waitForCompletion(true)) {
	        System.out.println("Job completed successfully, use the following command to list the output:");
	        System.out.println("");
	        System.out.format("  hadoop fs -cat %s/part*%n", output.toString());

	        return 0;
	      }
	      return 1;
		
	}
}
