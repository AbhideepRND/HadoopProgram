******************	Story for Spring Integration ****************
1. In first story of Integration the application will expose a Rest Web service. The service will expect a JSON or XML request.
   The request should contain user-id and on the basis of user-id service will retrieve all the information from database.
   The information is related to personal data and transaction details. The service will split the information and 
   aggregator will combine all the information. The response will be XML or JSON.
   	
   	Scenario 
   		1. JDBCTemplate is used to do a database call.
   		2. If exception will occur then error message should be added in response.
   		3. After the data get fetch audit table will get updated.
   		4. Free to use the every component of spring integration.
   		
2. In Second story of integration the application read the data from Queue Channel. The data in this channel is related to transaction. 
	On the basis of Transaction type it will route the message to particular service activator. After doing some business logic over the 
	data the Service activator will save the information to database.
		
			Scenarion:-
				1. The data will be populate in queue by reading the data from a file.
				2. The queue should be a synchronous queue.
				3. If an error will occur then save the information in a file. 

				   
   	    
