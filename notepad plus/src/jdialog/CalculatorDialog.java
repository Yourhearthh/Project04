package jdialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.omg.CORBA.portable.ApplicationException;

import notepad.Notepad;

public class CalculatorDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Notepad frame;
	private JTextField tf = new JTextField();
	private JButton[] b = new JButton[10];
	private JButton bp, ba, bs, bm, bd, be, btAC, btquxiao, btbai, btjiajian;

	public CalculatorDialog(Notepad frame, String title, boolean modal) {
		super(frame, title, modal);
		this.frame = frame;
		this.setTitle(title);
		this.setModal(modal);
		this.setSize(240, 350);
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(frame);

		JPanel pantop = new JPanel();
		this.add(pantop, BorderLayout.NORTH);
		tf.setFont(new Font("宋体", Font.PLAIN, 50));
		tf.setPreferredSize(new Dimension(230, 70));
		tf.setBackground(Color.YELLOW);
		// tf.setHorizontalAlignment(JTextField.RIGHT);
		pantop.add(tf);
		JPanel panbottom = new JPanel();
		this.add(panbottom, BorderLayout.CENTER);
		panbottom.setLayout(new GridLayout(5, 4));

		for (int i = 0; i <= 9; i++)
			b[i] = new JButton("" + i);
		bp = new JButton(".");
		ba = new JButton("+");
		bs = new JButton("-");
		bm = new JButton("*");
		bd = new JButton("/");
		be = new JButton("=");
		btAC = new JButton("AC");
		btquxiao = new JButton("<");
		btbai = new JButton("%");
		btjiajian = new JButton("+-");

		panbottom.add(btAC);
		panbottom.add(btbai);
		panbottom.add(btjiajian);
		panbottom.add(btquxiao);
		
		panbottom.add(b[7]);
		panbottom.add(b[8]);
		panbottom.add(b[9]);
		panbottom.add(bd);
		
		panbottom.add(b[4]);
		panbottom.add(b[5]);
		panbottom.add(b[6]);
		panbottom.add(bm);
		
		panbottom.add(b[1]);
		panbottom.add(b[2]);
		panbottom.add(b[3]);
		panbottom.add(bs);
		
		panbottom.add(b[0]);
		panbottom.add(bp);
		panbottom.add(be);
		panbottom.add(ba);

		this.addlistener();

	}

	public void addlistener() {
		btAC.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tf.setText("");

			}
		});
		btbai.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Double num = Double.parseDouble(tf.getText());
				tf.setText("" + (num / 100));

			}
		});
		btquxiao.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String c=tf.getText();
				String num=c.substring(0, c.length()-1);
				tf.setText(num);
				
			}
		});
		ba.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tf.getText().indexOf("+") > 0) { // 判断是否有加号，有的话不能再输入加号

				} else {
				tf.setText(tf.getText() + "+");
				}
				
			}
		});
//		bp = new JButton(".");
//		ba = new JButton("+");
//		bs = new JButton("-");
//		bm = new JButton("*");
//		bd = new JButton("/");
		bs.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tf.getText().indexOf("-")>0){					
				}else{
					tf.setText(tf.getText()+"-");
				}
				
				
			}
		});
		//"*e"
        bm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tf.getText().indexOf("*")>0){					
				}else{
					tf.setText(tf.getText()+"*");
				}
				
				
			}
		});
        //"/"
        bd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tf.getText().indexOf("/")>0){					
				}else{
					tf.setText(tf.getText()+"/");
				}
				
				
			}
		});
        //"."
        bp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				if(tf.getText().indexOf(".")>0){					
//				}else{
//					tf.setText(tf.getText()+".");
//				}
				int count;
				String str=tf.getText();
				count=str.length();
				if(count==0){
					str="0.";
					tf.setText(str);
				}else{
					if(!str.contains(".")){
						str+=".";
					}else{
						//JOptionPane.showMessageDialog(null, "再点就不是小数了");
						//System.out.println("再点的话，您输入的将不再是小数");
					}
				}
				tf.setText(tf.getText()+".");
				
				
			}
		});
	//"="
		be.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				/**
				* 加法计算
				*/
				String content = tf.getText(); // 获取文本框的数
				int i = content.indexOf("+"); // 搜索加号的位置
				if (i > 0) {
				// 加法
				String num1 = content.substring(0, i); // 截取加号前的数字
				String num2 = content.substring(i + 1, content.length());// 截取加号以后的数字
				try {
				double fNum = Double.parseDouble(num1);// 强行转化为double的数据类型
				double sNum = Double.parseDouble(num2);// 强行转化为double的数据类型
				double sum = fNum + sNum; // 两数相加
				tf.setText("" + sum);// 输出到文本框

				} catch (Exception e2) {

				}
				}


				/**
				* 减法计算
				*/


				String content1 = tf.getText();// 获取文本框数字
				int j = content1.indexOf("-"); // 搜索减号的位置
				if (j > 0) {
				// 减法
				String num1 = content1.substring(0, j); // 截取减号前的数字
				String num2 = content1.substring(j + 1, content1.length()); // 截取减号后的数字
				try {
				double fNum = Double.parseDouble(num1);// 强行转化为double的数据类型
				double sNum = Double.parseDouble(num2);// 强行转化为double的数据类型
				double jian = fNum - sNum; // 两数相减
				tf.setText("" + jian); // 设置文本框

				} catch (Exception e2) {

				}
				}

				/**
				* 乘法计算
				*/
				String content2 = tf.getText();// 获取文本框数字
				int k = content2.indexOf("*"); // 搜索乘号的位置
				if (k > 0) {
				// 乘法
				String num1 = content2.substring(0, k); // 截取乘号前的数字
				String num2 = content2.substring(k + 1, content2.length());// 截取乘号后面的数字
				try {
				double fNum = Double.parseDouble(num1);// 强行转化为double的数据类型
				double sNum = Double.parseDouble(num2);// 强行转化为double的数据类型
				double cheng = fNum * sNum;// 两数相乘
				tf.setText("" + cheng); // 设置文本框

				} catch (Exception e2) {

				}
				}
				/**
				* 除法计算
				*/


				String content3 = tf.getText();// 获取文本框的内容
				int l = content3.indexOf("/"); // 搜索除号的位置
				if (l > 0) {
				// 除法
				String num1 = content3.substring(0, l);// 截取除号前的数字
				String num2 = content3.substring(l + 1, content3.length());// 截取除号后的数字
				try {
				double fNum = Double.parseDouble(num1);// 强行转化为double的数据类型
				double sNum = Double.parseDouble(num2);// 强行转化为double的数据类型
				double chu = fNum / sNum;// 两数相除
				tf.setText("" + chu);// 设置文本框内容

				} catch (Exception e2) {

				}
				}


				
				
				
			}
		});
		
		b[0].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tf.setText(tf.getText() + 0);

			}
		});
		b[1].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tf.setText(tf.getText() + 1);

			}
		});
		b[2].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tf.setText(tf.getText() + 2);

			}
		});
		b[3].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tf.setText(tf.getText() + 3);

			}
		});
		b[4].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tf.setText(tf.getText() + 4);

			}
		});
		b[5].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tf.setText(tf.getText() + 5);

			}
		});
		b[6].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tf.setText(tf.getText() + 6);

			}
		});
		b[7].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tf.setText(tf.getText() + 7);

			}
		});
		b[8].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tf.setText(tf.getText() + 8);

			}
		});
		b[9].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tf.setText(tf.getText() + 9);

			}
		});
	}

}
