package 低风险旅游模拟查询系统;
import java.util.*;
import java.io.*;
import java.util.Scanner;
public class Scedule {	
path[] citys=new path[12];
public Scedule() 
{
	
	Scanner ac = null;
	try {
		ac = new Scanner(new BufferedReader(new FileReader("Timetable.txt")));
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}   
	for(int i=0;i<12;i++)
	{
		citys[i]=new path();
		for(int j=0;j<30;j++)
		{
			citys[i].paths[j]=new Travel_ticket();
		}
		
	}
	while(ac.hasNext())
	{		
			Travel_ticket a=new Travel_ticket();//读入
				a.source=ac.nextInt();
				a.dest=ac.nextInt();
				a.media_type=ac.nextDouble();
				a.city_type=ac.nextInt();
				a.num=ac.next();
				a.start_time=ac.nextInt();
				a.end_time=ac.nextInt();
				int index=a.source;
			citys[index].paths[citys[index].total]=a;
			citys[index].total++;
			
	}
	
	}
}

