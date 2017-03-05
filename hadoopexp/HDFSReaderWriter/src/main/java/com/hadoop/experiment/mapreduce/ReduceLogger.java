package com.hadoop.experiment.mapreduce;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ReduceLogger extends Reducer<Text, Text, Text, Text>{

	@Override
	protected void reduce(Text key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {
		final List<String> groupVal = new ArrayList<String>();
		/*for (Text val : values) {
			groupVal.add(val.toString());
		}*/
		values.forEach(e -> groupVal.add(e.toString()));
		context.write(key, new Text(String.join(",", groupVal)));
	}
}
