package com.hadoop.experiment.mapreduce;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * 
 * @author liveyoung
 *
 *         This class is used to map the log file into key/value pair.
 *
 *         When we extends the Mapper class it come in generic format
 *         LongWritable -> is key in Text -> is value in Text -> is key out Text
 *         -> is value out.
 */

public class MapLogger extends Mapper<LongWritable, Text, Text, Text> {

	private final String log_regx = "(.*?) (- -) (\\[\\d{2}/\\w{3}/\\d{4}:\\d{2}:\\d{2}:\\d{2} -\\d{4}\\]) (\".*?\") (\\d{3}) (\\d{1,10}|-)";
	private final Pattern dateCompilePatt = Pattern.compile("(\\[)(\\d{2}/\\w{3}/\\d{4})(:\\d{2}:\\d{2}:\\d{2}) (-\\d{4}\\])");
	private final Pattern compiledLogPattern = Pattern.compile(log_regx);
	
	/**
	 * setup method is used to extract the file from the context.
	 */
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		
		final Matcher matcher = compiledLogPattern.matcher(value.toString());
		if(matcher.matches()){
			context.write(new Text(matcher.group(1)), value);
			/*final Matcher dateMatcher = dateCompilePatt.matcher(matcher.group(3));
			if(dateMatcher.matches()){
				dateMatcher.group(2);
			}*/
		}
	}

	
}
