package com.abhideep.log.reader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.lucene.index.CorruptIndexException;

import com.abhideep.log.index.LoggerIndexer;

public class ReadLoggerFile {

	private final File loggerDirLocation;
	private final String log_regx = "(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2},\\d{3}) (INFO|DEBUG|ERROR|WARN|ALL|FATAL|OFF|TRACE)( *)(\\[.*?\\]) (\\(.*?\\)) (.*)";
	public ReadLoggerFile(File loggerDirLocation){
		this.loggerDirLocation=loggerDirLocation;
	}
	
	public void readLoggerData(final LoggerIndexer logIndex) throws IOException, CorruptIndexException{
		final Pattern compiledLogPattern = Pattern.compile(log_regx);
		final BufferedReader bufferedReader = new BufferedReader(new FileReader(loggerDirLocation));
		final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("D://data//log.log")));
		String readline;
		Long lineNo=0L;
		String prevLogLevel = "ERROR";
		final StringBuffer errorMessage = new StringBuffer();
		while((readline = bufferedReader.readLine()) != null){
			final Matcher matcher = compiledLogPattern.matcher(readline);
			lineNo++;
			if(!matcher.matches()){
				switch(prevLogLevel){
				case "ERROR" : if(!readline.isEmpty()){
									errorMessage.append(readline+"\n");
								}
				}
				continue;
			}
			
			/*System.out.println("Line no :-"+ lineNo);
			System.out.println("Date :- "+ matcher.group(1));
			System.out.println("Log Level :- "+ matcher.group(2));
			System.out.println("Class :- "+ matcher.group(4));
			System.out.println("Thread :- "+ matcher.group(5));
			System.out.println("Message :- "+ matcher.group(6));
			bufferedWriter.write(lineNo+"  "+matcher.group(1) +"  " +matcher.group(2)+ "  "+matcher.group(4) +"  "+matcher.group(5)+"  "+matcher.group(6)+"\n");*/
			if(errorMessage.length()>0){
				bufferedWriter.write("Error Message :-"+errorMessage.toString()+"\n");
				logIndex.createIndex(matcher.group(1), matcher.group(2), matcher.group(4), matcher.group(5), matcher.group(6), errorMessage.toString());
				errorMessage.setLength(0);
			} else {
				logIndex.createIndex(matcher.group(1), matcher.group(2), matcher.group(4), matcher.group(5), matcher.group(6));
			}
		}
		bufferedReader.close();
		bufferedWriter.flush();
		bufferedWriter.close();
		
	}
	
	public static void main(String[] args) throws IOException {
		
		String log = "2016-10-03 00:03:03,388 ERROR  [com.clearstream.gaf.ui.jsf.exception.ErrorExceptionHandler] (http-executor-threads - 481) Exception from JSF: ";
		String DATE = "2016";
		String date_reg = "(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2},\\d{3}) (INFO|DEBUG|ERROR)( *)(\\[.*?\\]) (\\(.*?\\)) (.*?)";
		String s = "2016-10-03 00:00:16,935 INFO [com.clearstream.gaf.ui.jsf.resources.UserManagementWrapper]";
		/*Pattern compile = Pattern.compile("(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2},\\d{3}) (INFO|DEBUG|ERROR) (\\[.*\\])");
		Matcher matcher = compile.matcher(s);
		System.out.println(matcher.matches());*/
		
		/*
		Pattern pattern = Pattern.compile(date_reg);
		Matcher matcher = pattern.matcher(log);
		
		System.out.println(matcher.matches());
		
		
		System.out.println(matcher.group(1));
		System.out.println(matcher.group(2));
		System.out.println(matcher.group(3));
		System.out.println(matcher.group(4));
		System.out.println(matcher.group(5));*/
		
		/*
		System.out.println(DATE.matches(date_reg));
		String[] split = log.split(date_reg);
		
		for(int i=0;i<split.length;i++){
			System.out.println("Hello    "+split[i]);
		}*/
		
		
		//new ReadLoggerFile(new File("D://data//server_log.log")).readLoggerData();
		
		
		
	}
	
	
	
}

