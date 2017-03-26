package com.hadoop.experiment.seqfile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.serde.serdeConstants;
import org.apache.hadoop.hive.serde2.AbstractSerDe;
import org.apache.hadoop.hive.serde2.SerDeException;
import org.apache.hadoop.hive.serde2.SerDeStats;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.StandardStructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Writable;

public class ConsumerComplainSerDe extends AbstractSerDe{

	public static final Log LOG = LogFactory
			.getLog(ConsumerComplainSerDe.class.getName());
	 private List<String> columnNames;
	 private StandardStructObjectInspector rowOI;
	 
	/**
	 * Deserialize of Sequence file
	 */
	@Override
	public Object deserialize(Writable field) throws SerDeException {
		if(field instanceof ConsumerComplainWriter){
			LOG.info("<============ inside initialize "+field );
			ConsumerComplainWriter consumerComplainWriter = (ConsumerComplainWriter) field;
			return consumerComplainWriter.getTuppleList();
		}
		
		throw new SerDeException(this.getClass().getName()
				+ " unexpected Writable type " + field.getClass().getName());
		
	}

	@Override
	public ObjectInspector getObjectInspector() throws SerDeException {
		return this.rowOI;
	}

	@Override
	public SerDeStats getSerDeStats() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<? extends Writable> getSerializedClass() {
		// TODO Auto-generated method stub
		return NullWritable.class;
	}

	/** 
	 * The method signature contain the table properties that we have declared in 
	 * during the table creation
	 * 	CREATE TABLE stocks (
		symbol string,
		dates string,
		open double,
		high double,
		low double,
		close double,
		volume int,
		adjClose double
		)
		ROW FORMAT SERDE 'com.hadoop.experiment.seqfile.ConsumerComplainSerDe'
		STORED AS SEQUENCEFILE;
	 */
	@Override
	public void initialize(Configuration arg0, Properties properties) throws SerDeException {
		final String columnNameProperty = properties.getProperty(serdeConstants.LIST_COLUMNS);
		String columnTypeProperty = properties.getProperty(serdeConstants.LIST_COLUMN_TYPES);
		
		 columnNames = Arrays.asList(columnNameProperty.split(","));
		
		 List<TypeInfo> columnTypes = TypeInfoUtils
				 .getTypeInfosFromTypeString(columnTypeProperty);
		 
		 LOG.info("<============ inside initialize " );
		 LOG.info("<============ column size"+columnNames.size()+"column type size{} "+columnTypes.size());
		 LOG.info("<============ column size names "+columnNameProperty);
		 LOG.info("<============ column types "+columnTypeProperty);
		 
		 assert columnNames.size() == columnTypes.size();
		 assert columnNames.size() == 18;

	    final boolean allMatch = columnTypes.stream().allMatch(e->TypeInfoFactory.stringTypeInfo.equals(e.getTypeName()));
	    
	    checkType(columnNames, columnTypes, 0, TypeInfoFactory.stringTypeInfo);
	    checkType(columnNames, columnTypes, 1, TypeInfoFactory.stringTypeInfo);
	    checkType(columnNames, columnTypes, 2, TypeInfoFactory.stringTypeInfo);
	    checkType(columnNames, columnTypes, 3, TypeInfoFactory.stringTypeInfo);
	    checkType(columnNames, columnTypes, 4, TypeInfoFactory.stringTypeInfo);
	    checkType(columnNames, columnTypes, 5, TypeInfoFactory.stringTypeInfo);
	    checkType(columnNames, columnTypes, 6, TypeInfoFactory.stringTypeInfo);
	    checkType(columnNames, columnTypes, 8, TypeInfoFactory.stringTypeInfo);
	    checkType(columnNames, columnTypes, 9, TypeInfoFactory.stringTypeInfo);
	    checkType(columnNames, columnTypes, 10, TypeInfoFactory.stringTypeInfo);
	    checkType(columnNames, columnTypes, 11, TypeInfoFactory.stringTypeInfo);
	    checkType(columnNames, columnTypes, 12, TypeInfoFactory.stringTypeInfo);
	    checkType(columnNames, columnTypes, 13, TypeInfoFactory.stringTypeInfo);
	    checkType(columnNames, columnTypes, 14, TypeInfoFactory.stringTypeInfo);
	    checkType(columnNames, columnTypes, 15, TypeInfoFactory.stringTypeInfo);
	    checkType(columnNames, columnTypes, 16, TypeInfoFactory.stringTypeInfo);
	    checkType(columnNames, columnTypes, 17, TypeInfoFactory.stringTypeInfo);
	    
	    
	 // Constructing the row ObjectInspector:
	// The row consists of some string columns, each column will be a java
	// String object.
	    
	    List<ObjectInspector> columnOIs = new ArrayList<ObjectInspector>(columnNames.size());
	    int count=0;
	    while(count<columnNames.size()){
	    	columnOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
	    	count++;
	    }
	    
	    
	    rowOI = ObjectInspectorFactory.getStandardStructObjectInspector(
	    		columnNames, columnOIs);
	}

	@Override
	public Writable serialize(Object arg0, ObjectInspector arg1) throws SerDeException {
		// TODO Auto-generated method stub
		return NullWritable.get();
	}
	
	public void checkType(List<String> columnNames, List<TypeInfo> columnTypes, int idx, TypeInfo expectedType)
		      throws SerDeException {
		    if (!columnTypes.get(idx).equals(expectedType)) {
		      throw new SerDeException(getClass().getName()
		          + " expected type " + expectedType.toString() + ", but column[" + idx + "] named "
		          + columnNames.get(idx) + " has type " + columnTypes.get(idx));
		    }
		}

	
}
