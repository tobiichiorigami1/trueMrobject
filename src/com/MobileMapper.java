package com;

import java.io.IOException;
//import com.Flower;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Mapper.Context;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class MobileMapper {

	/**
	 * @param args
	 */
	public static class MyMapper extends Mapper<LongWritable,Text,Text,Flower>{
			@Override
			protected void map(LongWritable key, Text value, Context context)
					throws IOException, InterruptedException {
				// TODO Auto-generated method stub
				System.out.println(value.toString().split(" ").length);
				  Long upflow=Long.parseLong(value.toString().split(" ")[8]);
				  Long downflow=Long.parseLong(value.toString().split(" ")[9]);
				  //Long sumflow=upflow+downflow;
				  String aString=value.toString().split(" ")[0];
				  Flower flower=new Flower(upflow, downflow);
				  context.write(new Text(aString),flower);
			}
	}
	public static class MyReduce extends Reducer<Text,Flower,Text,Text>{
		       Long upflow=0L;
		       Long downflow=0L;
			@Override
			protected void reduce(Text te, Iterable<Flower> flo,
					Context con)
					throws IOException, InterruptedException {
				// TODO Auto-generated method stub
				    for(Flower f:flo){
				    	upflow+=f.getUpflow();
				    	downflow+=f.getDownflow();
				    	}
				    //long sumflow=upflow+downflow;
				    Flower gFlower=new Flower(upflow, downflow);
				   con.write(te, new Text(gFlower.toString()));
			}
	}
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		// TODO Auto-generated method stub
        Configuration conf=new Configuration();
        Job job=new Job(conf, "jishu");
        job.setJarByClass(MobileMapper.class);
        job.setMapperClass(MyMapper.class);
        job.setReducerClass(MyReduce.class);
        //job.setCombinerClass(MyReduce.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Flower.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        FileInputFormat.addInputPath(job, new Path("file:///user/input"));
	    FileOutputFormat.setOutputPath(job, new Path("hdfs:///user/output"));
	    System.exit(job.waitForCompletion(true)?1:0);
	}

}
