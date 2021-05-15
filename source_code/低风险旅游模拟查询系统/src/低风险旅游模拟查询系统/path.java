package 低风险旅游模拟查询系统;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
public class path {	
	int total=0;
	int timetotal=0;
Travel_ticket[] paths=new Travel_ticket[30];
public void setTotal(int total)
{
	this.total=total;
}
public int gethours(LocalDateTime a,int sta) throws IOException
{
	LocalDateTime date;
	DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String strDate= dtf2.format(a);
	int start=sta;
	int  hour=0;
	for(int i=this.total-1;i>=0;i--)
	{
		if(paths[i].start_time<start)
			hour=hour+paths[i].start_time+24-start;
		else
			hour=hour+paths[i].start_time-start;
		
		date=a.plusHours(hour);
		strDate= dtf2.format(date);
		userlog.appendMethod(strDate);
		userlog.appendMethod("   用户乘坐车次如下\r\n");
		userlog.appendMethod(paths[i].toString());
		
		if(paths[i].end_time<paths[i].start_time)
			hour=hour+paths[i].end_time+24-paths[i].start_time;
		else
			hour=hour+paths[i].end_time-paths[i].start_time;
		  start=paths[i].end_time;
	}
	this.timetotal=hour;
	return hour;
	 
}
public int getindexs(int sta,long hours)
{
	int start=sta;
	int hour=0;
	int indexs=-1;
	for(int i=this.total-1;i>=0;i--)
	{
		if(paths[i].start_time<start)
			hour=hour+paths[i].start_time+24-start;
		else
			hour=hour+paths[i].start_time-start;
		if(paths[i].end_time<paths[i].start_time)
			hour=hour+paths[i].end_time+24-paths[i].start_time;
		else
			hour=hour+paths[i].end_time-paths[i].start_time;
		start=paths[i].end_time;
		if(hour>hours)
		{	indexs=i;
		     break;
		}
		
	}
	return indexs;
	
}
}
