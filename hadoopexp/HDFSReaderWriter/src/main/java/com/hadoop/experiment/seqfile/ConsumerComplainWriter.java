package com.hadoop.experiment.seqfile;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableUtils;

import com.opencsv.CSVParser;

public class ConsumerComplainWriter implements WritableComparable<ConsumerComplainWriter>, Cloneable{

	/**
	 * ate received1,Product2,Sub-product3,Issue4,Sub-issue5,Consumer complaint narrative6,Company public response7,Company8,State9,ZIP code10,Tags11,
	 * Consumer consent provided?12,Submitted via13,Date sent to company14,Company response to consumer15,Timely response?16,
	 * Consumer disputed?17,Complaint ID18
	 */
	String receiveDate;
	String complainGroup;
	String subComplainGroup;
	String issue;
	String subIssue;
	String complainNaration;
	String companyPublicResponse;
	String company;
	String state;
	String zipCode;
	String tags;
	String consumerConsent;
	String complainRegisterVia;
	String companySendDate;
	String companyResponse;
	String timelyresponse;
	String consumerDispute;
	String complainId;
	private final static CSVParser csvParser;
	
	static {
		 csvParser = new CSVParser();
	}
	
	@Override
	public void readFields(DataInput in) throws IOException {
		/**
		 * Read the binary form data to Writable field. 
		 */
		this.receiveDate=WritableUtils.readString(in);
		this.complainId=WritableUtils.readString(in);
		this.complainGroup=WritableUtils.readString(in);
		this.subComplainGroup=WritableUtils.readString(in);
		this.issue=WritableUtils.readString(in);
		this.subIssue=WritableUtils.readString(in);
		this.complainNaration=WritableUtils.readString(in);
		this.companyPublicResponse=WritableUtils.readString(in);
		this.company=WritableUtils.readString(in);
		this.state=WritableUtils.readString(in);
		this.zipCode=WritableUtils.readString(in);
		this.tags=WritableUtils.readString(in);
		this.consumerConsent=WritableUtils.readString(in);
		this.complainRegisterVia=WritableUtils.readString(in);
		this.companySendDate=WritableUtils.readString(in);
		this.companyResponse=WritableUtils.readString(in);
		this.timelyresponse=WritableUtils.readString(in);
		this.consumerDispute=WritableUtils.readString(in);
		
		
	}

	@Override
	public void write(DataOutput out) throws IOException {
		/**
		 * Write out the fields in byte format in output stream 
		 */
		WritableUtils.writeString(out,this.receiveDate);
		WritableUtils.writeString(out,this.complainId);
		WritableUtils.writeString(out,this.complainGroup);
		WritableUtils.writeString(out,this.subComplainGroup);
		WritableUtils.writeString(out,this.issue);
		WritableUtils.writeString(out,this.subIssue);
		WritableUtils.writeString(out,this.complainNaration);
		WritableUtils.writeString(out,this.companyPublicResponse);
		WritableUtils.writeString(out,this.company);
		WritableUtils.writeString(out,this.state);
		WritableUtils.writeString(out,this.zipCode);
		WritableUtils.writeString(out,this.tags);
		WritableUtils.writeString(out,this.consumerConsent);
		WritableUtils.writeString(out,this.complainRegisterVia);
		WritableUtils.writeString(out,this.companySendDate);
		WritableUtils.writeString(out,this.companyResponse);
		WritableUtils.writeString(out,this.timelyresponse);
		WritableUtils.writeString(out,this.consumerDispute);
			
	}
	
	public ConsumerComplainWriter() {
	}
	

	public ConsumerComplainWriter(String... args) {
		this.receiveDate=args[0];
		this.complainId=args[17];
		this.complainGroup=args[1];
		this.subComplainGroup=args[2];
		this.issue=args[3];
		this.subIssue=args[4];
		this.complainNaration=args[5];
		this.companyPublicResponse=args[6];
		this.company=args[7];
		this.state=args[8];
		this.zipCode=args[9];
		this.tags=args[10];
		this.consumerConsent=args[11];
		this.complainRegisterVia=args[12];
		this.companySendDate=args[13];
		this.companyResponse=args[14];
		this.timelyresponse=args[15];
		this.consumerDispute=args[16];
	}

	@Override
	public int compareTo(ConsumerComplainWriter arg) {
		return this.complainId.equals(arg.getComplainID())? 0:1;
	}
	
	public static ConsumerComplainWriter fromLine(String line) throws IOException{
		/**
		 * Read the data and convert it into Writable Object
		 */
		String[] split = csvParser.parseLine(line);
		return new ConsumerComplainWriter(split);
	}
	
	
	
	public String getComplainID() {
		return this.complainId;
	}

	@Override
	public String toString() {
		return "ConsumerComplainWriter [receiveDate=" + receiveDate + ", complainGroup=" + complainGroup
				+ ", subComplainGroup=" + subComplainGroup + ", issue=" + issue + ", subIssue=" + subIssue
				+ ", complainNaration=" + complainNaration + ", companyPublicResponse=" + companyPublicResponse
				+ ", company=" + company + ", state=" + state + ", zipCode=" + zipCode + ", tags=" + tags
				+ ", consumerConsent=" + consumerConsent + ", complainRegisterVia=" + complainRegisterVia
				+ ", companySendDate=" + companySendDate + ", companyResponse=" + companyResponse + ", timelyresponse="
				+ timelyresponse + ", consumerDispute=" + consumerDispute + ", complainId=" + complainId + "]";
	}

	public List<Object> getTuppleList(){
		return Arrays.asList(this.receiveDate,
		this.complainGroup,
		this.subComplainGroup,
		this.issue,
		this.subIssue,
		this.complainNaration,
		this.companyPublicResponse,
		this.company,
		this.state,
		this.zipCode,
		this.tags,
		this.consumerConsent,
		this.complainRegisterVia,
		this.companySendDate,
		this.companyResponse,
		this.timelyresponse,
		this.consumerDispute,
		this.complainId);
	}
	
	
	

}
