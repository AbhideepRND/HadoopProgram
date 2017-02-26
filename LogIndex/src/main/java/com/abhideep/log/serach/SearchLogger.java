package com.abhideep.log.serach;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class SearchLogger {

	private IndexSearcher indexSearcher;
	private QueryParser queryParser;
	public SearchLogger(String indexDir) throws CorruptIndexException, IOException {

		if(indexSearcher == null){
			this.indexSearcher = new IndexSearcher(FSDirectory.open(new File(indexDir))); 
			StandardAnalyzer standardAnalyzer = new StandardAnalyzer(Version.LUCENE_36);
			queryParser = new QueryParser(Version.LUCENE_36, "Date", standardAnalyzer);
		}

	}
	
	public void search(String queryString) throws ParseException, IOException{
		Query query = queryParser.parse(queryString);
		 int totalHits = indexSearcher.search(query,10).totalHits;
		 TopDocs search = indexSearcher.search(query, 10);
		 System.out.println(totalHits);
		/*scoreDocs.
		if(scoreDocs.length>0){
			System.out.println(scoreDocs[0]);
			System.out.println(scoreDocs[1]);
			
		}*/
		
	}
	
}
