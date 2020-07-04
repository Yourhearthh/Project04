package jdialog;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import notepad.Notepad;

public class FindorReplaceDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Notepad frame;
	private JTextField txtFind;
	private JTextField txtReplace;
	private boolean flag = false;
	private JRadioButton rdbtnd;
	private JRadioButton rdbtnu;
	private JCheckBox chkCase;
	

	public FindorReplaceDialog() {
		this(null, "查找", false);
	}

	public FindorReplaceDialog(Notepad frame, String title, boolean modal) {
		super(frame, title, modal);
		this.frame = frame;
		this.setTitle(title);
		this.setModal(modal);
		this.setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		this.setLocationRelativeTo(frame);
		frame.nexte = false;

		// 第一行面板
		JPanel panFind = new JPanel();
		panFind.setBounds(10, 25, 414, 40);
		getContentPane().add(panFind);
		panFind.setLayout(null);
		JLabel lblFind = new JLabel("查找内容：");
		lblFind.setBounds(10, 13, 65, 15);
		panFind.add(lblFind);
		txtFind = new JTextField();
		txtFind.setBounds(75, 8, 200, 25);
		txtFind.setColumns(10);
		txtFind.setBackground(SystemColor.menu);
		panFind.add(txtFind);
		JButton btnFindNext = new JButton("查找下一个(F)");
		btnFindNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				findHandler();

			}

		});
		btnFindNext.setBounds(284, 8, 120, 25);
		panFind.add(btnFindNext);

		// 第二行面板
		JPanel panReplace = new JPanel();
		panReplace.setBounds(10, 65, 414, 80);
		getContentPane().add(panReplace);
		panReplace.setLayout(null);
		JLabel lblReplace = new JLabel("替换为：");
		lblReplace.setBounds(10, 13, 65, 15);
		panReplace.add(lblReplace);
		txtReplace = new JTextField();
		txtReplace.setColumns(10);
		txtReplace.setBackground(SystemColor.menu);
		txtReplace.setBounds(75, 8, 200, 25);
		panReplace.add(txtReplace);
		JButton btnReplace = new JButton("替换(R)");
		btnReplace.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				replaceHandler();
			}
		});
		btnReplace.setBounds(284, 8, 120, 25);
		panReplace.add(btnReplace);
		JButton btnReplaceAll = new JButton("全部替换(A)");
		btnReplaceAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				replaceAllHandler();
			}

		});
		btnReplaceAll.setBounds(284, 48, 120, 25);
		panReplace.add(btnReplaceAll);

		// 其他面板
		JPanel panOther = new JPanel();
		panOther.setBounds(10, 145, 414, 90);
		getContentPane().add(panOther);
		panOther.setLayout(null);

		JButton btnCancel = new JButton("取消");
		btnCancel.setBounds(284, 8, 120, 25);
		panOther.add(btnCancel);

		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});

		JPanel panDirection = new JPanel();
		panDirection.setBounds(115, 20, 160, 60);
		panOther.add(panDirection);
		panDirection.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "\u65B9\u5411",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED));

		this.rdbtnu = new JRadioButton("向上(U)");
		panDirection.add(rdbtnu);

		this.rdbtnd = new JRadioButton("向下(D)");
		rdbtnd.setSelected(true);
		panDirection.add(rdbtnd);

		this.chkCase = new JCheckBox("区分大小写(C)");
		chkCase.setBounds(5, 45, 110, 25);
		panOther.add(chkCase);

		// 单选按钮的按钮组
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnu);
		buttonGroup.add(rdbtnd);


	}

	
	
	
	public void findHandler() {
		boolean flag=false;
		int pos = frame.txtContent.getCaretPosition();
		String content = frame.txtContent.getText(), findcontent = txtFind.getText();
		if(!chkCase.isSelected())
			{
			  findcontent=findcontent.toLowerCase();
			  content=content.toLowerCase();
		    }	
		if (this.rdbtnd.isSelected())
		{
			for (int i = pos; i <= content.length()-findcontent.length(); i++) 
			{				
				if (content.substring(i, i + findcontent.length()).equals(findcontent)) 
				{
					frame.txtContent.setSelectionStart(i);
					frame.txtContent.setSelectionEnd(i + findcontent.length());
					flag=true;
					break;					
				}
			}
			if(!flag){
				JOptionPane.showMessageDialog(null, "找不到"+findcontent);
			}

		} else if (this.rdbtnu.isSelected()) 
		{
			if(frame.nexte){
				pos--;
			}
			for (int i = pos; i >= findcontent.length(); i--)
			{
				if (content.substring(i - findcontent.length(), i).equals(findcontent)) 
				{
					// frame.txtContent.select(i - findcontent.length(),i);
					frame.nexte=true;
					frame.txtContent.setSelectionStart(i - findcontent.length());
					frame.txtContent.setSelectionEnd(i);
					flag=true;

					break;
				}
			}
			if(!flag){
				JOptionPane.showMessageDialog(null, "找不到"+findcontent);
			}
			
		}
		
	}

	private void replaceHandler() {
		String replace = txtReplace.getText();
		frame.txtContent.replaceSelection(replace);
		frame.txtContent.setSelectionEnd(frame.txtContent.getCaretPosition());

	}
	
	private void replaceAllHandler() {
//		int pos = frame.txtContent.getCaretPosition();
//		String content=frame.txtContent.getText();
//		for(int i=pos;i<content.length();i++)
//		{
//		   String allplace = txtReplace.getText();
//		   frame.txtContent.replaceSelection(allplace);
//		   frame.txtContent.setSelectionStart(i);
//		   frame.txtContent.setSelectionEnd(content.length());
//		   
//		}
		String content=frame.txtContent.getText();		
		String findcontent=txtFind.getText();
		String allreplace = txtReplace.getText();
		if(!chkCase.isSelected()){
			content=content.toLowerCase();
			findcontent=findcontent.toLowerCase();
			
		}
		content=content.replaceAll(findcontent, allreplace);
		frame.txtContent.setText(content);
		
		
		
	}
	
	

}
