package jdialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.html.HTMLDocument;

import notepad.Notepad;

public class AboutDialog extends JDialog{
  private Notepad frame;
  private JPanel pantop;
  private String image="F:\\EclipseImage";
  private JPanel pancenter;
  private JLabel lblarea;
  private Font font=new Font("YaHei Consolas Hybrid", Font.PLAIN, 14);
  private JPanel panBottom;
  private JButton btn;
  

  public AboutDialog(Notepad frame,String title,boolean bomal) {
	  
    super(frame,title,bomal);
    this.frame=frame;
    this.setSize(550, 500);
    this.setLocationRelativeTo(frame);
    
    this.pantop=new JPanel();
    pantop.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
    this.pancenter=new JPanel();
    this.lblarea=new JLabel("<html> Windows 10是美国微软公司研发的跨平台及设备应用的操作系统。<br> 是微软发布的最后一个独立Windows版本。 <br> Windows 10共有7个发行版本，分别面向不同用户和设备。 <html> ");
    this.lblarea.setFont(font);
    this.lblarea.setEnabled(true);
    this.panBottom=new JPanel();
    this.btn=new JButton("确定");
    btn.setFont(font);
    
    ImageIcon icons=new ImageIcon(this.image+File.separator+"image.png");
    JLabel lblicons=new JLabel(icons,JLabel.CENTER);
    //图片缩放
    icons.setImage(icons.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));
    lblicons.setIcon(icons);
    
    
    Icon icon = new ImageIcon(this.image+File.separator+"about.png");
    JLabel lblimage=new JLabel(icon,JLabel.CENTER);
    int width1=317;
    int height1=62;
    Dimension dimension1=new Dimension(width1, height1);
    lblimage.setPreferredSize(dimension1);
    
    this.add(pantop,BorderLayout.NORTH);
    this.add(pancenter,BorderLayout.CENTER);
    this.add(panBottom,BorderLayout.SOUTH);
    pantop.add(lblimage,BorderLayout.NORTH);
    pancenter.add(lblicons,BorderLayout.NORTH);
    pancenter.add(lblarea,BorderLayout.NORTH);
    panBottom.setLayout(new GridLayout(1,4));
    panBottom.add(new JLabel());
    panBottom.add(new JLabel());
    panBottom.add(new JLabel());
    panBottom.add(btn);
    
    this.btn.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			
		}
	});
  
    
    
    
	  
  }

  public AboutDialog() {
	this(null,"帮助",false);
}


  public static void main(String[] args) {
	AboutDialog ad = new AboutDialog();
	ad.setVisible(true);
}
  
 

  
}
