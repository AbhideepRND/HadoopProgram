package com.hadoop.experiment.mapreducecompositekey;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ReduceLoggerGroup extends Reducer<CompositeKeyGroup, Text, CompositeKeyGroup, Text>{

	@Override
	protected void reduce(CompositeKeyGroup arg0, Iterable<Text> values,
			Reducer<CompositeKeyGroup, Text, CompositeKeyGroup, Text>.Context context) throws IOException, InterruptedException {
			final List<String> groupVal = new ArrayList<String>();
			values.forEach(e -> groupVal.add(e.toString()));
			context.write(arg0, new Text(String.join("#~#", groupVal) ));
	}
}
