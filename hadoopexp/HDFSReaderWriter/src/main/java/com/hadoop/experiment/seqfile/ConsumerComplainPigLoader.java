package com.hadoop.experiment.seqfile;

import java.io.IOException;
import java.util.Arrays;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileRecordReader;
import org.apache.pig.FileInputLoadFunc;
import org.apache.pig.backend.hadoop.executionengine.mapReduceLayer.PigSplit;
import org.apache.pig.data.Tuple;
import org.apache.pig.data.TupleFactory;

public class ConsumerComplainPigLoader extends FileInputLoadFunc{

	 private SequenceFileRecordReader<Text, ConsumerComplainWriter> reader;
	 
	@Override
	public InputFormat getInputFormat() throws IOException {
		return new SequenceFileInputFormat<Text, ConsumerComplainWriter>();
	}

	@Override
	public Tuple getNext() throws IOException {
		
		boolean next;
		
			try{
				 next = reader.nextKeyValue();
			} catch(InterruptedException e){
				throw new IOException(e);
			}
			
			if(!next) return null;
			
			final ConsumerComplainWriter currentValue = reader.getCurrentValue();
			
			if(currentValue == null ){
				return null;
			}
			if(!(currentValue instanceof ConsumerComplainWriter)){
				return null;
			}
			
			ConsumerComplainWriter value = (ConsumerComplainWriter) currentValue;
			
			return TupleFactory.getInstance().newTuple(value.getTuppleList());
		
	}

	@Override
	public void prepareToRead(RecordReader arg0, PigSplit arg1) throws IOException {
		this.reader = (SequenceFileRecordReader) arg0;
	}

	@Override
	public void setLocation(String arg0, Job arg1) throws IOException {
		FileInputFormat.setInputPaths(arg1, arg0);
	}

	
}
