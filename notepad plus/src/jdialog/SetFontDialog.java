package jdialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import notepad.Notepad;

public class SetFontDialog extends JDialog {
	private JPanel contentPanel=new JPanel();
	private JTextField txtFontFace;
	private JTextField txtFontStyle;
	private JTextField txtFontSize;
	private JList<String> lstFontFace = new JList<String>();
	private JList<String> lstFontStyle = new JList<String>();
    private JList<String> lstFontSize = new JList<String>();
    private JTextArea txtarea=new JTextArea("演示字体");
    private Font fontList=new Font("微软雅黑", Font.PLAIN, 14);

	private static final long serialVersionUID = 1L;
	
	
	
	public SetFontDialog(final Notepad frame, String title, boolean model){
		this.setModal(model);
	    this.setTitle(title);
	    this.setSize(400, 420);
	    this.setLocationRelativeTo(frame);
	    
	    getContentPane().setLayout(new BorderLayout());
	    contentPanel.setLayout(null);
	    contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	    getContentPane().add(contentPanel, BorderLayout.CENTER);

	    JLabel lblFontFace = new JLabel("字体");
	    lblFontFace.setBounds(20, 10, 130, 20);
	    contentPanel.add(lblFontFace);

	    JLabel lblFontStyle = new JLabel("字形");
	    lblFontStyle.setBounds(170, 10, 110, 20);
	    contentPanel.add(lblFontStyle);
	    
	    JLabel lblFontSize = new JLabel("大小");
	    lblFontSize.setBounds(300, 10, 60, 20);
	    contentPanel.add(lblFontSize);
	    
	    txtFontFace = new JTextField("微软雅黑");
	    txtFontFace.setBounds(20, 32, 130, 22);
	    contentPanel.add(txtFontFace);
	    txtFontFace.setColumns(10);

	    txtFontStyle = new JTextField("PLAIN");
	    txtFontStyle.setBounds(170, 32, 110, 22);
	    contentPanel.add(txtFontStyle);
	    txtFontStyle.setColumns(10);

	    txtFontSize=new JTextField("36");
	    txtFontSize.setBounds(300, 32, 60, 22);
	    contentPanel.add(txtFontSize);
	    txtFontSize.setColumns(10);
	    
	    JScrollPane jspFontFace=new JScrollPane();
	    jspFontFace.setBounds(20, 62, 130, 120);
	    jspFontFace.setViewportView(lstFontFace);
	    contentPanel.add(jspFontFace);
	    
	    JScrollPane jspFontStyle=new JScrollPane();
	    jspFontStyle.setBounds(170, 62, 110, 120);	
	    jspFontStyle.setViewportView(lstFontStyle);
	    contentPanel.add(jspFontStyle);
	    
	    JScrollPane jspFontSize=new JScrollPane();
	    jspFontSize.setBounds(300, 62, 60, 120);
	    jspFontSize.setViewportView(lstFontSize);
	    contentPanel.add(jspFontSize);
	    
	    txtarea.setBounds(20, 192, 340, 120);
	    contentPanel.add(txtarea);
	    txtarea.setBackground(this.getBackground());
	    
	    //非静态块
	    {
	    	JPanel btnPan=new JPanel();
		    btnPan.setBorder(new EmptyBorder(10, 10, 10, 10));
		    btnPan.setLayout(new FlowLayout(FlowLayout.RIGHT));
		    getContentPane().add(btnPan, BorderLayout.SOUTH);
	    	{
	    		JButton okbtn=new JButton("确定");
	    		okbtn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						frame.setFontContent(setfont());
						
					}
				});
	    		btnPan.add(okbtn);
	    	}
	    	{
	    	    JButton cancelbtn=new JButton("取消");
	    	    cancelbtn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
						
					}
				});
	    	    btnPan.add(cancelbtn);
	    	}
	    	
	    }
	    this.loadData();
	    this.setfont();
	    this.addSetFontListener();
	    
	    
	    
	    
	}
	
	public Font setfont(){
		String fontFace=this.txtFontFace.getText();
		
		int fontStyle=0;
		switch(this.txtFontStyle.getText()){
		case "PLAIN":
		case "常规":
			fontStyle=Font.PLAIN;
			break;
		case "ITALIC":
		case "倾斜":
			fontStyle=Font.ITALIC;
			break;
		case "BOLD":
		case "粗体":
			fontStyle=Font.BOLD;
			break;
		
		}
		
		int fontSize=0;
		fontSize=Integer.parseInt(this.txtFontSize.getText());
		Font font=new Font(fontFace, fontStyle, fontSize);
		
		this.txtarea.setFont(font);
		return font;
		
	}
	
	public void loadData(){
		Vector<String> loadDataface=new Vector<String>();
		loadDataface.add("微软雅黑");
	    loadDataface.add("宋体");
	    loadDataface.add("楷体");
	    loadDataface.add("黑体");
	    loadDataface.add("仿宋");
	    loadDataface.add("DIALOG");
	    loadDataface.add("DIALOG_INPUT");
	    loadDataface.add("MONOSPACED");
	    loadDataface.add("SANS_SERIF");
	    loadDataface.add("SERIF");
	    loadDataface.add("TRUETYPE_FONT");
	    loadDataface.add("TYPE1_FONT");
	    this.lstFontFace.setListData(loadDataface);
	    this.lstFontFace.setFont(this.fontList);
	    this.lstFontFace.setSelectedIndex(0);
	    this.txtFontFace.setEnabled(false);
	    
	    Vector<String> loadDatastyle=new Vector<String>();
	    loadDatastyle.add("常规");
	    loadDatastyle.add("常规");
	    loadDatastyle.add("倾斜");
	    loadDatastyle.add("粗体");
	    loadDatastyle.add("PLAIN");
	    loadDatastyle.add("ITALIC");
	    loadDatastyle.add("BOLD");
	    this.lstFontStyle.setListData(loadDatastyle);
	    this.lstFontStyle.setFont(fontList);
	    this.lstFontStyle.setSelectedIndex(0);
	    this.txtFontStyle.setEnabled(false);
	    
	    Vector<String> listDataFontSize = new Vector<String>();
	    for (int i = 8; i < 13; i++) {
	      listDataFontSize.add(String.valueOf(i));
	    }
	    for (int i = 14; i < 30; i += 2) {
	      listDataFontSize.add(String.valueOf(i));
	    }
	    listDataFontSize.add("36");
	    listDataFontSize.add("48");
	    listDataFontSize.add("72");
	    this.lstFontSize.setListData(listDataFontSize);
	    this.lstFontSize.setFont(this.fontList);
	    this.lstFontSize.setSelectedIndex(13);
	    this.txtFontSize.setEditable(false);
		
	}
	
	
	
	
	public void addSetFontListener(){
		 SetFontTextFieldDocumentListener setFontTextFieldDocumentListener = new SetFontTextFieldDocumentListener();
		    this.txtFontFace.getDocument().addDocumentListener(setFontTextFieldDocumentListener);
		    this.txtFontStyle.getDocument().addDocumentListener(setFontTextFieldDocumentListener);
		    this.txtFontSize.getDocument().addDocumentListener(setFontTextFieldDocumentListener);
		    this.lstFontFace.addListSelectionListener(new SetFontListSelectionListener(this.lstFontFace, this.txtFontFace));
		    this.lstFontStyle.addListSelectionListener(new SetFontListSelectionListener(this.lstFontStyle, this.txtFontStyle));
		    this.lstFontSize.addListSelectionListener(new SetFontListSelectionListener(this.lstFontSize, this.txtFontSize));
		
		
	}
	
	 private class SetFontTextFieldDocumentListener implements DocumentListener{

		@Override
		public void insertUpdate(DocumentEvent e) {
			setfont();
			
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			
		}
		 
	 }
	 
	 
	 private class SetFontListSelectionListener implements ListSelectionListener{
		 private JList<String> jList;
		 private JTextField jField;

		    SetFontListSelectionListener(JList<String> jList, JTextField jField) {
		      this.jList = jList;
		      this.jField = jField;
		    }

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(e.getValueIsAdjusting())
			{
			  this.jField.setText(this.jList.getSelectedValue());
			
			}
		}
		 
		 
		 
	 }
	 
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public SetFontDialog() {
		this(null,"对话",false);
	}
	
	
	

}

