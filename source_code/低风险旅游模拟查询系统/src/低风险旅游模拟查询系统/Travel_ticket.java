package �ͷ�������ģ���ѯϵͳ;

public class Travel_ticket {
String[] listsource = new String[]{"����","�Ϻ�","����","������","����","���",
			"����","�ɶ�","����","�人","����","��³ľ��"};
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
		type="�ɻ�";
	else if(Math.abs(media_type-0.5)<1e-6d)
		type="��";
	else
		type="�ͳ�";
	return("�����ء���Ŀ�ĵأ�"+listsource[source]+"����"+listsource[dest]+"\r\n��ֹʱ�䣺"+start_time+":00��"+end_time+":00"+"\r\n�������Σ�"+num+"   �������ͣ�"+type+"\r\n");
}
}