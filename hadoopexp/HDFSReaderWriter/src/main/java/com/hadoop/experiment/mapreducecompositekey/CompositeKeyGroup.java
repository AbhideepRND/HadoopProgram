package com.hadoop.experiment.mapreducecompositekey;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableUtils;

/**
 * 
 * @author liveyoung
 *
 * CompositeKeyGroup is a custom Key that contain the IP Address and Log Date.
 */
public class CompositeKeyGroup implements WritableComparable<CompositeKeyGroup>{

	private String ipAddress;
	private String logdate;
	
	@Override
	public void readFields(DataInput arg0) throws IOException {
		this.ipAddress = WritableUtils.readString(arg0);
		this.logdate = WritableUtils.readString(arg0);
		
	}

	@Override
	public void write(DataOutput arg0) throws IOException {
		WritableUtils.writeString(arg0, this.ipAddress);
		WritableUtils.writeString(arg0, this.logdate);
			
	}

	@Override
	public int compareTo(CompositeKeyGroup data) {
		if(data == null){
			return 0;
		}
		return this.ipAddress.equals(data.getIpAddress()) && this.logdate.equals(data.getLogdate()) ? 0 :1;
			
	}
	
	@Override
	public String toString() {
		return "["+ipAddress + "," + logdate + "]";
	}

	public String getIpAddress() {
		return ipAddress;
	}
	
	public String getLogdate() {
		return logdate;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public void setLogdate(String logdate) {
		this.logdate = logdate;
	}
	
}
