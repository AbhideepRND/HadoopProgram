package com.abhideep.log;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.store.LockObtainFailedException;

import com.abhideep.log.index.LoggerIndexer;
import com.abhideep.log.reader.ReadLoggerFile;
import com.abhideep.log.serach.SearchLogger;

public class StartAnalyzer {

	public static void main(String[] args) throws CorruptIndexException, LockObtainFailedException, IOException, ParseException {
		
		final LoggerIndexer loggerIndexer = new LoggerIndexer("/home/liveyoung/Downloads/Log File/log");
		final ReadLoggerFile readLoggerFile = new ReadLoggerFile(new File("/home/liveyoung/Downloads/Log File/access_log/access_log"));
		readLoggerFile.readLoggerData(loggerIndexer);
		loggerIndexer.close();
		SearchLogger searchLogger = new SearchLogger("/home/liveyoung/Downloads/Log File/log");
		searchLogger.search("64.242.88.10");
		
	}
}
