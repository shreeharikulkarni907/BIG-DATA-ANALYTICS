

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class WCDriver extends Configured implements Tool
{

	@Override
	public int run(String[] arg0) throws Exception {
		// TODO Auto-generated method stub
		if(arg0.length<2)
		{
			return -1;
		}
		
		JobConf conf=new JobConf(WCDriver.class);
		FileInputFormat.setInputPaths(conf,new Path(arg0[0]));
		FileOutputFormat.setOutputPath(conf,new Path(arg0[1]));
		conf.setMapperClass(WCMapper.class);
		conf.setReducerClass(WCReducer.class);
		conf.setMapOutputKeyClass(Text.class);
		conf.setMapOutputValueClass(IntWritable.class);
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(IntWritable.class);
		JobClient.runJob(conf);
		
		
		return 0;
	}
	public static void main(String args[]) throws Exception
	{
		System.out.println(ToolRunner.run(new WCDriver(), args));
	}
	
}