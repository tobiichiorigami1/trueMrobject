package com;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class Flower implements Writable{

	/**
	 * @param args
	 */
	private long upflow;
	private long downflow;
	private long sumflow;
	
	public Flower() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getUpflow() {
		return upflow;
	}

	public void setUpflow(long upflow) {
		this.upflow = upflow;
	}

	public long getDownflow() {
		return downflow;
	}

	public void setDownflow(long downflow) {
		this.downflow = downflow;
	}

	public long getSumflow() {
		return sumflow;
	}

	public void setSumflow(long sumflow) {
		this.sumflow = sumflow;
	}

	public Flower(long upflow, long downflow) {
		
		this.upflow = upflow;
		this.downflow = downflow;
		this.sumflow = upflow+downflow;
	}
   
	@Override
	public String toString() {
		return upflow+" "+ downflow
				+" "+ sumflow;
	}
       
	public void readFields(DataInput in) throws IOException {
		   this.upflow=in.readLong();
		   this.downflow=in.readLong();
		   this.sumflow=in.readLong();
		
	}

	public void write(DataOutput out) throws IOException {
		// TODO Auto-generated method stub
		out.writeLong(upflow);
		out.writeLong(downflow);
		out.writeLong(sumflow);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
