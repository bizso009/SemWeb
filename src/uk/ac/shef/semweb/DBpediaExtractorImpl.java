package uk.ac.shef.semweb;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.sparql.engine.http.QueryExceptionHTTP;

public class DBpediaExtractorImpl extends XMLExtractorImpl implements DBpediaExtractor {
	
	private String service = "http://dbpedia.org/sparql";
	
	@Override
	public void getDBpediaInfo(String URL) {
		// TODO Auto-generated method stub
	}

	public void getProperty()
	{
		String sparqlQueryString= "Query goes here";
		try
		{
			runQuery(sparqlQueryString);
		}catch(QueryExceptionHTTP e)
		{
			System.out.println(this.service + " is DOWN");
		}
	}
	
	public ResultSet runQuery(String queryString) throws QueryExceptionHTTP
	{
		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.sparqlService(this.service, query);
		ResultSet results = qexec.execSelect();
		ResultSetFormatter.out(System.out, results, query);
		qexec.close();
		return results;		
	}
}
