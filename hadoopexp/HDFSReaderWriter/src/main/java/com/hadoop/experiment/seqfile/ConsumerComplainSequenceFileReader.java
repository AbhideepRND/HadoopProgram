package com.hadoop.experiment.seqfile;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;

import com.hadoop.experiment.hdfsreadwrite.ConfigurationHDFS;

public class ConsumerComplainSequenceFileReader extends ConfigurationHDFS{

	public void readSequenceData(String[] args) throws IOException {

		final Path path = new Path(args[0]);

		try (SequenceFile.Reader reader = new SequenceFile.Reader(

				ConfigurationHDFS.getFileSystem(), path, ConfigurationHDFS.getConfiguration())) {
			Text key = new Text();
			ConsumerComplainWriter stockPriceWriter = new ConsumerComplainWriter();
			while (reader.next(key, stockPriceWriter)) {
				System.out.println(key + "," + stockPriceWriter);
			}
		}
	}
}
