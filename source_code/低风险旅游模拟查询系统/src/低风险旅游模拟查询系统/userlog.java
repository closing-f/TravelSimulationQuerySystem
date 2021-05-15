package 低风险旅游模拟查询系统;

import java.io.FileWriter;
import java.io.IOException;

public class userlog {
	

	public static void appendMethod( String content) throws IOException {
			//打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
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