package jdialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import notepad.Notepad;
import notepad.mythread.TimeThread;


public class CalendarDialog extends JDialog {
  private static final long serialVersionUID = 1L;
  private Notepad frame;
  private Font fontTable = new Font("微软雅黑", Font.BOLD, 20);
  private JLabel lblTitle;
  private JLabel lblTime;
  private JTable tblCalendar;
  private JButton btn;
  private int curRow = 0, curCol = 0;

  public CalendarDialog() {
    this(null, "日历", false);
  }

  public CalendarDialog(final Notepad frame, String title, boolean modal) {
    super(frame, title, modal);
    this.frame = frame;

    this.lblTitle = new JLabel("", JLabel.CENTER);
    this.lblTitle.setFont(this.fontTable);
    this.lblTime=new JLabel(" ",JLabel.CENTER);
    this.lblTime.setFont(this.fontTable);

    Vector<String> tHead = new Vector<String>();
    tHead.addAll(Arrays.asList(this.chineseWeek));
    this.tblCalendar = new JTable(this.getCalendarData(), tHead) {
      // 使用匿名内部类覆盖isCellEditable()方法，禁止编辑内容。
      private static final long serialVersionUID = 1L;

      @Override
      public boolean isCellEditable(int row, int column) {
        // return super.isCellEditable(row, column);
        return false;
      }
    };

    this.tblCalendar.setRowSelectionAllowed(false);// 行选择模式
    this.tblCalendar.setColumnSelectionAllowed(false);// 列选择模式
    this.tblCalendar.setCellSelectionEnabled(true);// 单元格选择模式
    this.tblCalendar.setRowHeight(45);// 行高
    if (this.frame != null) {
      this.tblCalendar.setFont(this.frame.getFontMenu());// 设置字体
    } else {
      this.tblCalendar.setFont(this.fontTable);// 设置字体
    }
    this.tblCalendar.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);// 设置自动列调整
    this.tblCalendar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// 选择模式

    this.tblCalendar.getTableHeader().setFont(this.fontTable);// 设置表头字体
    this.tblCalendar.getTableHeader().setReorderingAllowed(false);// 不可调整列位置
    this.tblCalendar.getTableHeader().setResizingAllowed(true);// 不可调整列宽

    // DefaultTableCellRenderer dtc = new DefaultTableCellRenderer();
    // dtc.setForeground(Color.WHITE);
    // dtc.setBackground(Color.BLUE);

    DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
      private static final long serialVersionUID = 1L;

      @Override
      public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JTextField text = new JTextField(value.toString());
        text.setFont(fontTable);
        text.setHorizontalAlignment(JTextField.CENTER);
        if (frame != null) {
          text.setBackground(frame.getBackground());
        } else {
          text.setBackground(tblCalendar.getBackground());
        }
        text.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        text.setOpaque(true);
        if (row == curRow & column == curCol) {
          text.setBackground(Color.BLUE);
          text.setForeground(Color.WHITE);
        } else {
          text.setBackground(Color.WHITE);
          text.setForeground(Color.BLACK);
        }
        return text;
      }
    };
    this.tblCalendar.setDefaultRenderer(Object.class, tcr);
    DefaultTableModel model = (DefaultTableModel) this.tblCalendar.getModel();
    for (int i = 0; i < model.getRowCount(); i++) {
      for (int j = 0; j < model.getColumnCount(); j++) {
        Object val = model.getValueAt(i, j);
        if (String.valueOf(val).equals(String.valueOf(this.nowDate))) {
          this.curRow = i;
          this.curCol = j;
        }
      }
    }

    JScrollPane jspTable = new JScrollPane(this.tblCalendar);
    this.add(jspTable, BorderLayout.CENTER);
    
    JPanel pantop=new JPanel(new GridLayout(2, 1));
    this.add(pantop,BorderLayout.NORTH);
    pantop.add(lblTitle);
    pantop.add(lblTime);
    this.setlblTime();
    
    TimeThread runtime=new TimeThread(this);
    Thread thread=new Thread(runtime);
    thread.start();
    
    JPanel panbottom=new JPanel();
    this.add(panbottom,BorderLayout.SOUTH);
    this.btn=new JButton("插入时间");
    panbottom.add(btn);
    this.btn.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			 int i=frame.txtContent.getCaretPosition();
			 frame.txtContent.insert((getbtnTime()), i);
			dispose();
			
		}

	});
    

    this.setSize(400, 400);
    // this.pack();
    this.setLocationRelativeTo(this.frame);
    this.setResizable(false);
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosed(WindowEvent e) {
          System.out.println("尝试停止时钟线程！");
          runtime.setloop(false);
        }
      });
  }
  

  private String[] chineseWeek = {
      "一","二","三","四","五","六","日"
  };

  private int nowDate;
  
//获取时间方法
  private String getTime() {
		Calendar calendar=Calendar.getInstance();
		SimpleDateFormat adf=new SimpleDateFormat("HH:mm:ss");
		return adf.format(calendar.getTime());
	}
  
  public void setlblTime(){
	  this.lblTime.setText(getTime());
  }
  
  private String getbtnTime() {
		SimpleDateFormat af=new SimpleDateFormat("HH:mm  yyyy:MM:dd");
		return af.format(Calendar.getInstance().getTime());
		
		
	}

  private Vector<Vector<String>> getCalendarData() {
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("今天是yyyy年MM月dd日，EE");
    this.lblTitle.setText(sdf.format(calendar.getTime()));
    this.nowDate = calendar.get(Calendar.DATE);// 当前的日期
    calendar.set(Calendar.DATE, 1);
    int firstWeek = calendar.get(Calendar.DAY_OF_WEEK);// 当月1号的星期
    // 周日1 周一2 周二3 周三4 周四5 周五6 周六7
    calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
    calendar.set(Calendar.DATE, 0);
    int lastDate = calendar.get(Calendar.DATE);// 当月的最后一天
    firstWeek -= 2;
    firstWeek = firstWeek == -1 ? 6 : firstWeek;// 周一 ~ 周日 -> 0-6
    // 周一0 周二1 周三2 周四3 周五4 周六5 周日6

    // System.out.println("今天是" + this.addZero(nowDate, 2) + "日");
    // System.out.println("本月一号是周" + this.chineseWeek[firstWeek]);
    // System.out.println("本月最后一天是" + this.addZero(lastDate, 2) + "日");

    Vector<Vector<String>> tBody = new Vector<Vector<String>>();
    int start = 1 - firstWeek;// 开始的值
    int offset = 7 - (firstWeek + lastDate) % 7;// 月末添加的空格
    int end = firstWeek + lastDate + offset;// 结尾的值
    // System.out.println("start:" + start);
    // System.out.println("offset:" + offset);
    // System.out.println("end:" + end);
    int weekCount = end / 7;
    for (int i = 0; i < weekCount; i++) {
      Vector<String> tRow = new Vector<String>();
      for (int j = 0; j < 7; j++) {
        if (start < 1 || start > lastDate) {
          tRow.add("");
        } else {
          tRow.add(this.addZero(start, 2));
        }
        start++;
      }
      tBody.add(tRow);
    }
    return tBody;
  }
  
  

  private String addZero(int num, int len) {
    String str = String.valueOf(num);
    StringBuffer sb = new StringBuffer(str);
    while (sb.length() < len) {
      sb.insert(0, "0");
    }
    return sb.toString();
  }

 
}

