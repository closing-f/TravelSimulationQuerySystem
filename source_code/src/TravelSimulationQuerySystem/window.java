package �ͷ�������ģ���ѯϵͳ;

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
int flag=0;//���ϵͳʱ�仹���ֶ�����
int whethermake=0;//����Ƿ񰴹��ƶ�·�߰�ť
LocalDateTime date,date3;//��¼ʱ��
 JLabel jlTime = new JLabel();//��ǰϵͳʱ���ǩ
 int aa=0;//��ʱ�����
 Timer timer;//���ö�ʱ��
Scedule timetabletest;//��α�
best_path bestpathtest;
path pathtest;
int starta;//��¼ϵͳʱ�����
JLayeredPane pane = new JLayeredPane();  // �ֲ�����	
JLabel label;	//����ͼƬ
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
	userlog.appendMethod("  �û���¼\r\n");
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
image = new ImageIcon("1.jpg");                 //�������͸��             
label = new JLabel(image);		//�ѱ���ͼƬ��ӵ���ǩ�� 		
panel1.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());   //�ѱ�ǩ����Ϊ��ͼƬ�ȸߵȿ� 		
panel1 = (JPanel)this.getContentPane(); 	//���ҵ��������Ϊ�������		
panel1.add(label);				
JTextArea textField1 = new JTextArea(5,10);
textField1.setBounds(500, 150, 400, 450);
textField1.setLineWrap(true);                         // �Զ�����
textField1.setFont(new Font("����", Font.PLAIN, 16));   // ��������
textField1.setEditable(false);
// �����������, ָ��������ʾ����ͼ���(textArea), ��ֱ������һֱ��ʾ, ˮƽ�������Ӳ���ʾ
JScrollPane scrollPane = new JScrollPane(
		textField1,
        ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
);
scrollPane.setBounds(500, 150, 400, 450);
JButton btn01 = new JButton("�ƶ�·��");
btn01.setLocation(50, 500);
btn01.setSize(100, 30);
btn01.addActionListener(new ActionListener() 
 {
    
    public void actionPerformed(ActionEvent e) {
        // ��ȡ�����¼�Դ���ǰ�ť����
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
  		 textField1.append("==========  ʱ�䰲��  =========\r\n");
  		 textField1.append("����ʱ��Ϊ��");
         if(flag==0)
  		 textField1.append(strDate1);
         else
        textField1.append(strDate3);
  		 textField1.append("\r\n");
  		 textField1.append("Ԥ�Ƶ���ʱ��Ϊ��");
  		textField1.append(strDate2);
  		textField1.append("\r\n");
    	 textField1.append("������Ϊ���Ƽ������β���\r\n");
    	 textField1.append("==========  ���β���  =========\r\n");
       for(int i=pathtest.total-1;i>=0;i--)
       {
    	   textField1.append(pathtest.paths[i].toString());
       }
       textField1.append("========= ף����;���========\r\n"); 
    }
});

JButton btn02 = new JButton("�����ѯ");
btn02.setLocation(180, 500);
btn02.setSize(100, 30);
btn02.addActionListener(new ActionListener() 
{
 
 public void actionPerformed(ActionEvent e) {
     timer.stop();
     if(whethermake==0)
     {
    	 textField1.append("����δ�ƶ�·��\r\n");
     }
     else
     {
     textField1.append("==========  ״̬��ѯ  =========\r\n");
     LocalDateTime ate=date.plusHours(aa);
     Duration duration = Duration.between(date3,ate);
     long hours = duration.toHours();//����Сʱ��
     if(hours<0)
     {
    	 textField1.append("����δ������\r\n");
     }
     else
     {
    	 ate=date3.plusHours(hours);
    	 int index=0;	 
     index=pathtest.getindexs(timenum,hours);
     if(index==-1)
     {	 textField1.append("���Ѿ���վ����󳵴�Ϊ��\r\n");
          textField1.append(pathtest.paths[0].toString());
     }
     else
     {
    	 textField1.append("��Ŀǰ�����ĳ���Ϊ��\r\n");
         textField1.append(pathtest.paths[index].toString());
     }
     }
     textField1.append("==========  ��ѯ���  =========\r\n");
 }
 }
});

JButton btn03 = new JButton("�˳���ѯ");
btn03.setLocation(310, 500);
btn03.setSize(100, 30);
btn03.addActionListener(new ActionListener() 
{

public void actionPerformed(ActionEvent e) {
   timer.start();
}
});



String[] listyear = new String[]{"2020��","2021��","2022��","2023��","2024��"
};
//����һ�������б��
final JComboBox<String> comboBoxyear = new JComboBox<String>(listyear);
comboBoxyear.setBounds(50, 450, 80, 30);
//�����Ŀѡ��״̬�ı�ļ�����
comboBoxyear.addItemListener(new ItemListener() {
 @Override
 public void itemStateChanged(ItemEvent e) {
     // ֻ����ѡ�е�״̬
     if (e.getStateChange() == ItemEvent.SELECTED) {
         yearnum= comboBoxyear.getSelectedIndex()+2020;
     
         
     }
     
 }
});
String[] listmonth = new String[]{"1��","2��","3��","4��","5��","6��","7��","8��","9��","10��","11��","12��",
};
//����һ�������б��
final JComboBox<String> comboBoxmonth = new JComboBox<String>(listmonth);
comboBoxmonth.setBounds(150, 450, 80, 30);
//�����Ŀѡ��״̬�ı�ļ�����
comboBoxmonth.addItemListener(new ItemListener() {
 @Override
 public void itemStateChanged(ItemEvent e) {
     // ֻ����ѡ�е�״̬
     if (e.getStateChange() == ItemEvent.SELECTED) {
         monthnum= comboBoxmonth.getSelectedIndex()+1;

     }
     
 }
});
String[] listday = new String[]{"1��","2��","3��","4��","5��","6��","7��","8��","9��","10��","11��","12��","13��","14��","15��","16��","17��","18��","19��","20��","21��","22��","23��","24��","25��","26��","27��","28��","29��","30��","31��"
};
//����һ�������б��
final JComboBox<String> comboBoxday= new JComboBox<String>(listday);
comboBoxday.setBounds(250, 450, 80, 30);
//�����Ŀѡ��״̬�ı�ļ�����
comboBoxday.addItemListener(new ItemListener() {
 @Override
 public void itemStateChanged(ItemEvent e) {
     // ֻ����ѡ�е�״̬
     if (e.getStateChange() == ItemEvent.SELECTED) {
         daynum= comboBoxday.getSelectedIndex()+1;
     }
     
 }
});
String[] listtimetype = new String[]{"��ǰϵͳʱ��","�ֶ�����ʱ��"
};
//����һ�������б��
final JComboBox<String> comboBoxtimetype= new JComboBox<String>(listtimetype);
comboBoxtimetype.setBounds(190, 410, 150, 30);
//�����Ŀѡ��״̬�ı�ļ�����
comboBoxtimetype.addItemListener(new ItemListener() {
 @Override
 public void itemStateChanged(ItemEvent e) {
     // ֻ����ѡ�е�״̬
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
String[] listsource = new String[]{"����","�Ϻ�","����","������","����","���",
		"����","�ɶ�","����","�人","����","��³ľ��"
};
//����һ�������б��
final JComboBox<String> comboBoxsource = new JComboBox<String>(listsource);
comboBoxsource.setBounds(190, 215, 150, 30);
//�����Ŀѡ��״̬�ı�ļ�����
comboBoxsource.addItemListener(new ItemListener() {
 @Override
 public void itemStateChanged(ItemEvent e) {
     // ֻ����ѡ�е�״̬
     if (e.getStateChange() == ItemEvent.SELECTED) {
         sourcenum= comboBoxsource.getSelectedIndex();
     }
     
 }
});


String[] listdest = new String[]{"����","�Ϻ�","����","������","����","���",
		"����","�ɶ�","����","�人","����","��³ľ��"};
//����һ�������б��
final JComboBox<String> comboBoxdest = new JComboBox<String>(listdest);
comboBoxdest.setBounds(190, 285, 150, 30);
//�����Ŀѡ��״̬�ı�ļ�����
comboBoxdest.addItemListener(new ItemListener() {
 @Override
 public void itemStateChanged(ItemEvent e) {
     // ֻ����ѡ�е�״̬
     if (e.getStateChange() == ItemEvent.SELECTED) {
    	 destnum= comboBoxdest.getSelectedIndex();
     }
 }
});

String[] listtype = new String[]{"��ͷ��ղ���", "��ʱ��ͷ��ղ���"};
//����һ�������б��
final JComboBox<String> comboBoxtype= new JComboBox<String>(listtype);
comboBoxtype.setBounds(190, 355, 150, 30);
//�����Ŀѡ��״̬�ı�ļ�����
comboBoxtype.addItemListener(new ItemListener() {
 @Override
 public void itemStateChanged(ItemEvent e) {
     // ֻ����ѡ�е�״̬
     if (e.getStateChange() == ItemEvent.SELECTED) {
    	 typenum= comboBoxtype.getSelectedIndex();
     }
 }
});
JLabel timeLabel = new JLabel();
timeLabel.setText("��ǰϵͳʱ�䣺");
timeLabel.setFont(new Font("�����п�", Font.PLAIN, 25));  // �������壬null ��ʾʹ��Ĭ������
timeLabel.setBounds(30, 150, 200, 20);

 
 JLabel sourceLabel = new JLabel();
 sourceLabel .setText("�����أ�");
 sourceLabel .setFont(new Font("�����п�", Font.PLAIN, 25));  // �������壬null ��ʾʹ��Ĭ������
 sourceLabel .setBounds(30, 220, 100, 20);
 
 JLabel destLabel = new JLabel();
 destLabel.setText("Ŀ�ĵأ�");
 destLabel.setFont(new Font("�����п�", Font.PLAIN, 25));  // �������壬null ��ʾʹ��Ĭ������
 destLabel.setBounds(30, 290, 100, 20);
 
 
 JLabel typeLabel = new JLabel();
 typeLabel .setText("���β���ѡ��:");
 typeLabel .setFont(new Font("�����п�", Font.PLAIN, 25));  // �������壬null ��ʾʹ��Ĭ������
 typeLabel .setBounds(30, 360, 180, 20);
 JLabel timetypeLabel = new JLabel();
 timetypeLabel.setText("ʱ��ѡ��");
 timetypeLabel.setFont(new Font("�����п�", Font.PLAIN, 25));  // �������壬null ��ʾʹ��Ĭ������
 timetypeLabel.setBounds(30, 420, 150, 20);
 JLabel trvalLabel = new JLabel();
 trvalLabel.setText("����·�ߣ�");
 trvalLabel.setFont(new Font("�����п�", Font.PLAIN, 25));  // �������壬null ��ʾʹ��Ĭ������
 trvalLabel.setBounds(500, 100, 180, 20);
 
 JLabel Title = new JLabel();
 Title.setText("�ͷ�������ģ���ѯϵͳ");
 Title.setFont(new Font("��������", Font.PLAIN, 36));  // �������壬null ��ʾʹ��Ĭ������
 Title.setBounds(100, 50, 500, 38);
Title.setForeground(Color.YELLOW);
this.addWindowListener(new WindowAdapter() {
public void windowClosing(WindowEvent e) {
 super.windowClosing(e);
//���붯��
LocalDateTime date2=LocalDateTime.now();

String strDate2 = dtf2.format(date2);
try {
	userlog.appendMethod(strDate2);
} catch (IOException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}
try {
	userlog.appendMethod("  �û��˳�\r\n");
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
this.setTitle("�ͷ�������ģ���ѯϵͳ");		
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

