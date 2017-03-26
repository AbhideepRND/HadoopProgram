package com.hadoop.experiment.seqfile;

	
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.DefaultCodec;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;

public class ConsumerComplainMapReduce{

	public int run(String[] arg0) throws Exception {
		final Configuration conf = new Configuration();
		final Job job = new Job(conf);
		job.setJobName("Consumer Complain Map");
		job.setJarByClass(ConsumerComplainMapReduce.class);
		job.setInputFormatClass(SequenceFileInputFormat.class);
		job.setOutputFormatClass(SequenceFileOutputFormat.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(ConsumerComplainWriter.class);
		SequenceFileOutputFormat.setCompressOutput(job, true);  
	    SequenceFileOutputFormat.setOutputCompressionType(job,  
	        SequenceFile.CompressionType.BLOCK);
	    SequenceFileOutputFormat.setOutputCompressorClass(job,  
	        DefaultCodec.class);
	    job.setNumReduceTasks(10);
	    FileInputFormat.setInputPaths(job, new Path(arg0[0]));
	    FileOutputFormat.setOutputPath(job, new Path(arg0[1]));
	    if(job.waitForCompletion(true)){
	    	return 0;
	    }
		return 1;
	}

	
	
}
