package 低风险旅游模拟查询系统;

public class Travel_ticket {
String[] listsource = new String[]{"北京","上海","广州","哈尔滨","长春","天津",
			"西宁","成都","贵阳","武汉","福州","乌鲁木齐"};
int source;
int dest;
int start_time;
int end_time;
double media_type;
int city_type;
String num;
public String toString()
{
	String type;
	if(Math.abs(media_type-0.9)<1e-6d)
		type="飞机";
	else if(Math.abs(media_type-0.5)<1e-6d)
		type="火车";
	else
		type="客车";
	return("出发地——目的地："+listsource[source]+"——"+listsource[dest]+"\r\n起止时间："+start_time+":00—"+end_time+":00"+"\r\n乘坐车次："+num+"   乘坐类型："+type+"\r\n");
}
}