package �ͷ�������ģ���ѯϵͳ;

import java.io.FileWriter;
import java.io.IOException;

public class userlog {
	

	public static void appendMethod( String content) throws IOException {
			//��һ��д�ļ��������캯���еĵڶ�������true��ʾ��׷����ʽд�ļ�
            FileWriter writer = null;
			try {
				writer = new FileWriter("user_log.txt", true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            writer.write(content);
            writer.close();
       
    }
}