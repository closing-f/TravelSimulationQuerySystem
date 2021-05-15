package 低风险旅游模拟查询系统;

public class best_path {
	 int START;
	 int type;
	 int source;
	 int dest;
	 Scedule timetable;
     int[] existNum=new int[12];//记录已经记录的点
	 int[][] path_num=new int[12][2];//记录车次，第一个记录上一站来自哪里，第二个记录使用的班次
	 double [] path_weight=new double[12];//记录源点到各个点的最短距离
	 public best_path(int source,int dest,Scedule timetabl,int start_time,int type)//构造方法
	{
			this.source=source;
			this.dest=dest;
			timetable=timetabl;
			START=start_time;
			this.type=type;
			for(int i=0;i<12;i++)
			{	this.path_weight[i]=-1;
			   this.existNum[i]=-1;
			   path_num[i][0]=path_num[i][1]=-1;
			}
			
	}
	public double  getweight(int s,int i,int start_time)
	{
		int starta=timetable.citys[s].paths[i].start_time;
		int enda=timetable.citys[s].paths[i].end_time;
		int totalTime,weightTime;
		if(enda<starta)
			totalTime=enda+24-starta;
		else
			totalTime=enda-starta;
		if(starta<start_time)
		   weightTime=starta+24-start_time;		
		else
			weightTime=starta-start_time;
		double totalWeight=weightTime*timetable.citys[s].paths[i].city_type+totalTime*timetable.citys[s].paths[i].media_type;
		return totalWeight;
	}
 public double  getTimeweight(int s,int i,int start_time)
	{
		 int starta=timetable.citys[s].paths[i].start_time;
			int enda=timetable.citys[s].paths[i].end_time;
			int totalTime,weightTime;
			if(enda<starta)
				totalTime=enda+24-starta;
			else
				totalTime=enda-starta;
			if(starta<start_time)
			   weightTime=starta+24-start_time;		
			else
				weightTime=starta-start_time;
			double totalWeight=weightTime*timetable.citys[s].paths[i].city_type+totalTime*timetable.citys[s].paths[i].media_type;
			return totalWeight*(totalTime+weightTime)*(totalTime+weightTime);
			
	}
	
public int find_least_weight(int s,int start_time)
{
		
		for(int i=0;i<timetable.citys[s].total;i++)
		{
			
			if(existNum[timetable.citys[s].paths[i].dest]==-1)//如果该点没有经过
			{
				if(getweight(s,i,start_time)+path_weight[s]<path_weight[timetable.citys[s].paths[i].dest]||Math.abs(path_weight[timetable.citys[s].paths[i].dest]+1)<1e-6d)
				{	path_weight[timetable.citys[s].paths[i].dest]=getweight(s,i,start_time);
					path_num[timetable.citys[s].paths[i].dest][0]=s;
					path_num[timetable.citys[s].paths[i].dest][1]=i;
				}
					
			}
		}
		return 0;
		
}

public int Time_limited_minimum_risk_strategy_Path(int s,int start_time)
{
	
	for(int i=0;i<timetable.citys[s].total;i++)
	{
		
		if(existNum[timetable.citys[s].paths[i].dest]==-1)//如果该点没有经过
		{
			if((getTimeweight(s,i,start_time)+path_weight[s]<path_weight[timetable.citys[s].paths[i].dest])||Math.abs(path_weight[timetable.citys[s].paths[i].dest]+1)<1e-6d)
			{
				path_weight[timetable.citys[s].paths[i].dest]=getTimeweight(s,i,start_time)+path_weight[s];
				path_num[timetable.citys[s].paths[i].dest][0]=s;
				path_num[timetable.citys[s].paths[i].dest][1]=i;
				
			}
				
	
				
		}
	}	
	
	return 0;
	
}	
public path get_strategy_Path()
{
	double min=-1;
	int indexS=-1;
	existNum[this.source]=0;
	path_weight[this.source]=0;
	if(this.type==0)
	this.find_least_weight(this.source,START);
	else 
		this.Time_limited_minimum_risk_strategy_Path(this.source,START);
for(int k=0;k<11;k++)
	{
	
	if(Math.abs(path_weight[dest]+1)>=0.000001)
		break;
	indexS=-1;
	min=-1;
	for(int i=0;i<12;i++)
	{
		if((indexS==-1&&path_weight[i]>0&&existNum[i]==-1)||(min>0&&path_weight[i]<path_weight[indexS]&&existNum[i]==-1))	
		{
			indexS=i;
		}
	}
	if(indexS==dest||indexS==-1)
		break;
	existNum[indexS]=0;
	if(this.type==0)
	this.find_least_weight(indexS, timetable.citys[path_num[indexS][0]].paths[path_num[indexS][1]].end_time);
	else
	this.Time_limited_minimum_risk_strategy_Path(indexS, timetable.citys[path_num[indexS][0]].paths[path_num[indexS][1]].end_time);
	}
	path i=new path();
	for(int j=0;j<30;j++)
		i.paths[j]=new Travel_ticket();
	int timinal=this.dest;
	int index=0;
	while(timinal!=this.source)
	{
		i.paths[index]=timetable.citys[path_num[timinal][0]].paths[path_num[timinal][1]];
		timinal=timetable.citys[path_num[timinal][0]].paths[path_num[timinal][1]].source;
		index++;
		}
	i.total=index;
	return i;
}
}
