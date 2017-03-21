package com.hadoop.experiment.mapreducecompositekey;

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
 * The MapperLoggerGroup is used to create mapper of CompositeKeyGroup which conatin the 
 * IP Address and Date on which user access the site
 *
 */
public class MapperLoggerGroup extends Mapper<LongWritable, Text, CompositeKeyGroup, Text>{

	private final String log_regx = "(.*?) (- -) (\\[\\d{2}/\\w{3}/\\d{4}:\\d{2}:\\d{2}:\\d{2} -\\d{4}\\]) (\".*?\") (\\d{3}) (\\d{1,10}|-)";
	private final Pattern dateCompilePatt = Pattern.compile("(\\[)(\\d{2}/\\w{3}/\\d{4})(:\\d{2}:\\d{2}:\\d{2}) (-\\d{4}\\])");
	private final Pattern compiledLogPattern = Pattern.compile(log_regx);
	

	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, CompositeKeyGroup, Text>.Context context)
			throws IOException, InterruptedException {
		final Matcher matcher = compiledLogPattern.matcher(value.toString());
		if(matcher.matches()){
			final Matcher dateMatcher = dateCompilePatt.matcher(matcher.group(3));
			if(dateMatcher.matches()){
				final CompositeKeyGroup compositeKeyGroup = new CompositeKeyGroup();
				compositeKeyGroup.setIpAddress(matcher.group(1));
				compositeKeyGroup.setLogdate(dateMatcher.group(2));
				context.write(compositeKeyGroup, 
						new Text("["+matcher.group(4) + "," +matcher.group(5) +","+matcher.group(6)+"]"));
			}
		}
	}
}
