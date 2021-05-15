package 低风险旅游模拟查询系统;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;import javax.swing.JLabel;import javax.swing.JLayeredPane;import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
public class window extends JFrame 
{
int flag=0;//标记系统时间还是手动设置
int whethermake=0;//标记是否按过制定路线按钮
LocalDateTime date,date3;//记录时间
 JLabel jlTime = new JLabel();//当前系统时间标签
 int aa=0;//定时器相关
 Timer timer;//设置定时器
Scedule timetabletest;//班次表
best_path bestpathtest;
path pathtest;
int starta;//记录系统时间相关
JLayeredPane pane = new JLayeredPane();  // 分层网格	
JLabel label;	//背景图片
JPanel panel1 = new JPanel();	JTextField field1 = new JTextField();
ImageIcon image;	
int destnum,sourcenum,typenum,starttimenum,timenum,yearnum=2020,monthnum=1,daynum=1;
public window() throws IOException
{		
DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	date=LocalDateTime.now();
	date3=date;
	String strDate2 = dtf2.format(date);
	userlog.appendMethod(strDate2);	
	userlog.appendMethod("  用户登录\r\n");
	 jlTime.setText( strDate2 );
	 jlTime.setBounds(210, 130, 200, 60);
	 Timer timer = new Timer(1000* 1, new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				aa++;	
			LocalDateTime ate=date.plusHours(aa);
			DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String strDate2 = dtf2.format(ate);
				 jlTime.setText( strDate2 );
			}

		});
	timer.start();
timetabletest=new Scedule();
image = new ImageIcon("1.jpg");                 //设置组件透明             
label = new JLabel(image);		//把背景图片添加到标签里 		
panel1.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());   //把标签设置为和图片等高等宽 		
panel1 = (JPanel)this.getContentPane(); 	//把我的面板设置为内容面板		
panel1.add(label);				
JTextArea textField1 = new JTextArea(5,10);
textField1.setBounds(500, 150, 400, 450);
textField1.setLineWrap(true);                         // 自动换行
textField1.setFont(new Font("楷体", Font.PLAIN, 16));   // 设置字体
textField1.setEditable(false);
// 创建滚动面板, 指定滚动显示的视图组件(textArea), 垂直滚动条一直显示, 水平滚动条从不显示
JScrollPane scrollPane = new JScrollPane(
		textField1,
        ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
);
scrollPane.setBounds(500, 150, 400, 450);
JButton btn01 = new JButton("制定路线");
btn01.setLocation(50, 500);
btn01.setSize(100, 30);
btn01.addActionListener(new ActionListener() 
 {
    
    public void actionPerformed(ActionEvent e) {
        // 获取到的事件源就是按钮本身
        // JButton btn = (JButton) e.getSource();
    	timer.start();
    	whethermake=1;
    	LocalDateTime ate=date.plusHours(aa);
    	if(flag==1)
    	date3=LocalDateTime.of(yearnum, monthnum, daynum, timenum, 0, 0);
    	else 
    		date3=ate;
    	starta=aa;
    	timenum=ate.getHour();    	
    	bestpathtest=new best_path(sourcenum, destnum, timetabletest, timenum, typenum);
    	  pathtest=bestpathtest.get_strategy_Path(); 
    	  int finitime=0;
		try {
			if(flag==0)
			finitime = pathtest.gethours(ate,timenum);
			else
			finitime = pathtest.gethours(date3,timenum);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		LocalDateTime ateb;
		if(flag==0)
    	  ateb=date.plusHours(aa+finitime);
		else
		 ateb=date3.plusHours(finitime);
    	  DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
  		String strDate2 = dtf2.format(ateb);
  		String strDate1 = dtf2.format(ate);
  		String strDate3 = dtf2.format(date3);
  		textField1.setText(" "); 
  		 textField1.append("==========  时间安排  =========\r\n");
  		 textField1.append("出发时间为：");
         if(flag==0)
  		 textField1.append(strDate1);
         else
        textField1.append(strDate3);
  		 textField1.append("\r\n");
  		 textField1.append("预计到达时间为：");
  		textField1.append(strDate2);
  		textField1.append("\r\n");
    	 textField1.append("下面是为您推荐的旅游策略\r\n");
    	 textField1.append("==========  旅游策略  =========\r\n");
       for(int i=pathtest.total-1;i>=0;i--)
       {
    	   textField1.append(pathtest.paths[i].toString());
       }
       textField1.append("========= 祝您旅途愉快========\r\n"); 
    }
});

JButton btn02 = new JButton("进入查询");
btn02.setLocation(180, 500);
btn02.setSize(100, 30);
btn02.addActionListener(new ActionListener() 
{
 
 public void actionPerformed(ActionEvent e) {
     timer.stop();
     if(whethermake==0)
     {
    	 textField1.append("您尚未制定路线\r\n");
     }
     else
     {
     textField1.append("==========  状态查询  =========\r\n");
     LocalDateTime ate=date.plusHours(aa);
     Duration duration = Duration.between(date3,ate);
     long hours = duration.toHours();//相差的小时数
     if(hours<0)
     {
    	 textField1.append("您尚未出发。\r\n");
     }
     else
     {
    	 ate=date3.plusHours(hours);
    	 int index=0;	 
     index=pathtest.getindexs(timenum,hours);
     if(index==-1)
     {	 textField1.append("您已经到站，最后车次为：\r\n");
          textField1.append(pathtest.paths[0].toString());
     }
     else
     {
    	 textField1.append("您目前乘坐的车次为：\r\n");
         textField1.append(pathtest.paths[index].toString());
     }
     }
     textField1.append("==========  查询完毕  =========\r\n");
 }
 }
});

JButton btn03 = new JButton("退出查询");
btn03.setLocation(310, 500);
btn03.setSize(100, 30);
btn03.addActionListener(new ActionListener() 
{

public void actionPerformed(ActionEvent e) {
   timer.start();
}
});



String[] listyear = new String[]{"2020年","2021年","2022年","2023年","2024年"
};
//创建一个下拉列表框
final JComboBox<String> comboBoxyear = new JComboBox<String>(listyear);
comboBoxyear.setBounds(50, 450, 80, 30);
//添加条目选中状态改变的监听器
comboBoxyear.addItemListener(new ItemListener() {
 @Override
 public void itemStateChanged(ItemEvent e) {
     // 只处理选中的状态
     if (e.getStateChange() == ItemEvent.SELECTED) {
         yearnum= comboBoxyear.getSelectedIndex()+2020;
     
         
     }
     
 }
});
String[] listmonth = new String[]{"1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月",
};
//创建一个下拉列表框
final JComboBox<String> comboBoxmonth = new JComboBox<String>(listmonth);
comboBoxmonth.setBounds(150, 450, 80, 30);
//添加条目选中状态改变的监听器
comboBoxmonth.addItemListener(new ItemListener() {
 @Override
 public void itemStateChanged(ItemEvent e) {
     // 只处理选中的状态
     if (e.getStateChange() == ItemEvent.SELECTED) {
         monthnum= comboBoxmonth.getSelectedIndex()+1;

     }
     
 }
});
String[] listday = new String[]{"1号","2号","3号","4号","5号","6号","7号","8号","9号","10号","11号","12号","13号","14号","15号","16号","17号","18号","19号","20号","21号","22号","23号","24号","25号","26号","27号","28号","29号","30号","31号"
};
//创建一个下拉列表框
final JComboBox<String> comboBoxday= new JComboBox<String>(listday);
comboBoxday.setBounds(250, 450, 80, 30);
//添加条目选中状态改变的监听器
comboBoxday.addItemListener(new ItemListener() {
 @Override
 public void itemStateChanged(ItemEvent e) {
     // 只处理选中的状态
     if (e.getStateChange() == ItemEvent.SELECTED) {
         daynum= comboBoxday.getSelectedIndex()+1;
     }
     
 }
});
String[] listtimetype = new String[]{"当前系统时间","手动设置时间"
};
//创建一个下拉列表框
final JComboBox<String> comboBoxtimetype= new JComboBox<String>(listtimetype);
comboBoxtimetype.setBounds(190, 410, 150, 30);
//添加条目选中状态改变的监听器
comboBoxtimetype.addItemListener(new ItemListener() {
 @Override
 public void itemStateChanged(ItemEvent e) {
     // 只处理选中的状态
     if (e.getStateChange() == ItemEvent.SELECTED) {
        if(comboBoxtimetype.getSelectedIndex()==0)
        {
                 flag=0;
        }
        else
        {
        	flag=1;
        }
        	
     }
     
 }
});
String[] listsource = new String[]{"北京","上海","广州","哈尔滨","长春","天津",
		"西宁","成都","贵阳","武汉","福州","乌鲁木齐"
};
//创建一个下拉列表框
final JComboBox<String> comboBoxsource = new JComboBox<String>(listsource);
comboBoxsource.setBounds(190, 215, 150, 30);
//添加条目选中状态改变的监听器
comboBoxsource.addItemListener(new ItemListener() {
 @Override
 public void itemStateChanged(ItemEvent e) {
     // 只处理选中的状态
     if (e.getStateChange() == ItemEvent.SELECTED) {
         sourcenum= comboBoxsource.getSelectedIndex();
     }
     
 }
});


String[] listdest = new String[]{"北京","上海","广州","哈尔滨","长春","天津",
		"西宁","成都","贵阳","武汉","福州","乌鲁木齐"};
//创建一个下拉列表框
final JComboBox<String> comboBoxdest = new JComboBox<String>(listdest);
comboBoxdest.setBounds(190, 285, 150, 30);
//添加条目选中状态改变的监听器
comboBoxdest.addItemListener(new ItemListener() {
 @Override
 public void itemStateChanged(ItemEvent e) {
     // 只处理选中的状态
     if (e.getStateChange() == ItemEvent.SELECTED) {
    	 destnum= comboBoxdest.getSelectedIndex();
     }
 }
});

String[] listtype = new String[]{"最低风险策略", "限时最低风险策略"};
//创建一个下拉列表框
final JComboBox<String> comboBoxtype= new JComboBox<String>(listtype);
comboBoxtype.setBounds(190, 355, 150, 30);
//添加条目选中状态改变的监听器
comboBoxtype.addItemListener(new ItemListener() {
 @Override
 public void itemStateChanged(ItemEvent e) {
     // 只处理选中的状态
     if (e.getStateChange() == ItemEvent.SELECTED) {
    	 typenum= comboBoxtype.getSelectedIndex();
     }
 }
});
JLabel timeLabel = new JLabel();
timeLabel.setText("当前系统时间：");
timeLabel.setFont(new Font("华文行楷", Font.PLAIN, 25));  // 设置字体，null 表示使用默认字体
timeLabel.setBounds(30, 150, 200, 20);

 
 JLabel sourceLabel = new JLabel();
 sourceLabel .setText("出发地：");
 sourceLabel .setFont(new Font("华文行楷", Font.PLAIN, 25));  // 设置字体，null 表示使用默认字体
 sourceLabel .setBounds(30, 220, 100, 20);
 
 JLabel destLabel = new JLabel();
 destLabel.setText("目的地：");
 destLabel.setFont(new Font("华文行楷", Font.PLAIN, 25));  // 设置字体，null 表示使用默认字体
 destLabel.setBounds(30, 290, 100, 20);
 
 
 JLabel typeLabel = new JLabel();
 typeLabel .setText("旅游策略选择:");
 typeLabel .setFont(new Font("华文行楷", Font.PLAIN, 25));  // 设置字体，null 表示使用默认字体
 typeLabel .setBounds(30, 360, 180, 20);
 JLabel timetypeLabel = new JLabel();
 timetypeLabel.setText("时间选择：");
 timetypeLabel.setFont(new Font("华文行楷", Font.PLAIN, 25));  // 设置字体，null 表示使用默认字体
 timetypeLabel.setBounds(30, 420, 150, 20);
 JLabel trvalLabel = new JLabel();
 trvalLabel.setText("旅游路线：");
 trvalLabel.setFont(new Font("华文行楷", Font.PLAIN, 25));  // 设置字体，null 表示使用默认字体
 trvalLabel.setBounds(500, 100, 180, 20);
 
 JLabel Title = new JLabel();
 Title.setText("低风险旅游模拟查询系统");
 Title.setFont(new Font("华文琥珀", Font.PLAIN, 36));  // 设置字体，null 表示使用默认字体
 Title.setBounds(100, 50, 500, 38);
Title.setForeground(Color.YELLOW);
this.addWindowListener(new WindowAdapter() {
public void windowClosing(WindowEvent e) {
 super.windowClosing(e);
//加入动作
LocalDateTime date2=LocalDateTime.now();

String strDate2 = dtf2.format(date2);
try {
	userlog.appendMethod(strDate2);
} catch (IOException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}
try {
	userlog.appendMethod("  用户退出\r\n");
} catch (IOException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}}});

pane.add(panel1,JLayeredPane.DEFAULT_LAYER);  	
pane.add(timetypeLabel, JLayeredPane.MODAL_LAYER);	
pane.add(comboBoxtimetype, JLayeredPane.MODAL_LAYER);	
pane.add(comboBoxyear, JLayeredPane.MODAL_LAYER);	
pane.add(comboBoxmonth, JLayeredPane.MODAL_LAYER);	
pane.add(comboBoxday, JLayeredPane.MODAL_LAYER);	
pane.add(btn01, JLayeredPane.MODAL_LAYER);		
pane.add(btn02, JLayeredPane.MODAL_LAYER);	
pane.add(btn03, JLayeredPane.MODAL_LAYER);	
pane.add(comboBoxsource, JLayeredPane.MODAL_LAYER);	
pane.add(comboBoxtype, JLayeredPane.MODAL_LAYER);		
pane.add(comboBoxdest, JLayeredPane.MODAL_LAYER);		
pane.add(scrollPane, JLayeredPane.MODAL_LAYER);		
pane.add(timeLabel, JLayeredPane.MODAL_LAYER);		
pane.add(Title, JLayeredPane.MODAL_LAYER);		
pane.add(sourceLabel, JLayeredPane.MODAL_LAYER);	
pane.add(destLabel, JLayeredPane.MODAL_LAYER);	
pane.add(trvalLabel, JLayeredPane.MODAL_LAYER);	
pane.add(typeLabel, JLayeredPane.MODAL_LAYER);	

pane.add(jlTime, JLayeredPane.MODAL_LAYER);	
this.setTitle("低风险旅游模拟查询系统");		
this.setBounds(100,100,image.getIconWidth(), image.getIconHeight());	
this.setDefaultCloseOperation(EXIT_ON_CLOSE);		
this.setLayeredPane(pane);	
this.setVisible(true);	
}	

public static void main(String[] args) throws IOException
{
  
	new window();

}

}

