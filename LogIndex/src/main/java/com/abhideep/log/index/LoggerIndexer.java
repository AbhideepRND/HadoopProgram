package com.abhideep.log.index;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.util.Version;

public class LoggerIndexer {
 
	private IndexWriter indexWriter;
	
	public LoggerIndexer(String indexDir) throws CorruptIndexException, LockObtainFailedException, IOException {
		if(indexWriter == null){
			this.indexWriter = new IndexWriter(FSDirectory.open(new File(indexDir)), 
					new IndexWriterConfig(Version.LUCENE_36, new StandardAnalyzer(Version.LUCENE_36)) );
		} 
	}
	
	
	/**
	 * createIndex method is used to create the index on data passed
	 * @param args - create index on item
	 * @throws IOException 
	 * @throws CorruptIndexException 
	 */
	public void createIndex(final String... args) throws CorruptIndexException, IOException{
		
		final Document document = new Document();
		document.add(new Field("Date", args[0], Field.Store.YES, Field.Index.ANALYZED));
		document.add(new Field("LogLevel", args[1], Field.Store.YES, Field.Index.ANALYZED));
		document.add(new Field("Class", args[2], Field.Store.YES, Field.Index.ANALYZED));
		document.add(new Field("Thread", args[3], Field.Store.YES, Field.Index.ANALYZED));
		document.add(new Field("Message", args[4], Field.Store.YES, Field.Index.ANALYZED));
		if(args.length == 6){
			document.add(new Field("Error", args[5], Field.Store.YES, Field.Index.ANALYZED));
		}
		
		indexWriter.addDocument(document);
	}
	
	public void close() throws CorruptIndexException, IOException{
		if(indexWriter != null){
			indexWriter.close();
		}
	}
	
}
