package notepad;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import jdialog.AboutDialog;
import jdialog.CalculatorDialog;
import jdialog.CalendarDialog;
import jdialog.FindorReplaceDialog;
import jdialog.FindDialog;
import jdialog.SetFontDialog;

public class Notepad extends JFrame {
	/**
	* 
	*/
	public boolean nexte;
	private static final long serialVersionUID = 1L;
	private String unknowFile = "无标题";
	private String separator = " - ";
	private String fileName = "无标题";
	private String title = " - 记事本";
	private Font fontMenu = new Font("YaHei Consolas Hybrid", Font.PLAIN, 12);// 字体的单位：磅
	private Font fontContent = new Font("YaHei Consolas Hybrid", Font.PLAIN, 16);
	private String imagework = "F:\\EclipseImage";
	private JPanel panBottom;
	private JLabel labCol = new JLabel("第1行");
	private JLabel labRow = new JLabel("第1列");
	private JMenuItem mnuNew;
	private JMenuItem mnuOpen;
	private JMenuItem mnuSave;
	private JMenuItem mnuSaveAs;
	private JMenuItem mnuExit;

	private JMenuItem mnuUndo;
	private JMenuItem mnuCut;
	private JMenuItem mnuCopy;
	private JMenuItem mnuPaste;
	private JMenuItem mnuDelete;
	private JMenuItem mnuFind;
	private JMenuItem mnuFindNext;
	private JMenuItem mnuReplace;
	private JMenuItem mnuGoto;
	private JMenuItem mnuSelectAll;
	private JMenuItem mnuDateTime;

	private JCheckBoxMenuItem mnuAutoWarp;
	private JMenuItem mnuFont;

	private JCheckBoxMenuItem mnuStatus;

	private JMenuItem mnuViewHelp;
	private JMenuItem mnuAbout;
	private JMenuItem mnuCalculator;
	private Frame frame;

	// private File projectPath = new
	// File(ClassLoader.getSystemResource("").getPath()).getParentFile();
	// private String workPath = this.projectPath.getAbsolutePath() +
	// File.separator + "img" + File.separator;

	public Font getFontMenu() {
		return this.fontMenu;
	}

	@SuppressWarnings("unused")
	private class MaxLengthDocument extends PlainDocument {
		private static final long serialVersionUID = 1L;
		int maxChars;

		public MaxLengthDocument(int maxChars) {
			this.maxChars = maxChars;
		}

		@Override
		public void insertString(int offset, String s, AttributeSet a) throws BadLocationException {
			if (this.getLength() + s.length() > this.maxChars) {
				Toolkit.getDefaultToolkit().beep();
				return;
			}
			super.insertString(offset, s, a);
		}
	}

	private JScrollPane jspMain = new JScrollPane();
	public JTextArea txtContent = new JTextArea();

	public void setFontContent(Font fontContent) {
		this.fontContent = fontContent;
		this.txtContent.setFont(this.fontContent);
		// this.txtContent.repaint();
	}

	private boolean isModify = false;

	public Notepad() {

		int fWidth = 800;
		int fHeight = 600;
		this.setSize(fWidth, fHeight);

		// 设置窗体标题
		this.setTitle(this.fileName);

		// 设置窗体图标
		ImageIcon icon = new ImageIcon(this.imagework + File.separator + "image.png");
		this.setIconImage(icon.getImage());
		icon.getDescription();

		// 将菜单栏组装到窗体中
		this.setJMenuBar(this.buildMenuBar());
		// 主工作区
		this.txtContent.setFont(this.fontMenu);
		this.txtContent.setLineWrap(false);
		this.jspMain.getViewport().add(this.txtContent);
		this.add(this.jspMain, BorderLayout.CENTER);

		// 添加监听器的方法
		this.addListener();

		JLabel lblStatus = new JLabel();
		lblStatus.setFont(new Font("微软雅黑", Font.PLAIN, 12));

		this.labCol = new JLabel("第1行");
		this.labRow = new JLabel("第1列");
        lblStatus.add(labCol);
        lblStatus.add(labRow);
		
        this.panBottom = new JPanel();
		panBottom.setLayout(new GridLayout(1, 5));
		this.lblmodify = new JLabel();
		//panBottom.add(lblmodify);
		
		panBottom.add(new JLabel());
		panBottom.add(new JLabel());
		panBottom.add(new JLabel());
		panBottom.add(lblStatus);
		panBottom.add(labCol);
		panBottom.add(labRow);
		panBottom.setBorder(new LineBorder(Color.GRAY));

		//this.add(panBottom, BorderLayout.SOUTH);
		this.mnuStatus.setSelected(false);

		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private JLabel lblmodify;

	/**
	 * 构建菜单栏
	 * 
	 * @return
	 */
	private JMenuBar buildMenuBar() {
		// 创建菜单组件
		// 菜单栏
		JMenuBar menuBar = new JMenuBar();
		// 菜单
		JMenu menuFile = new JMenu("文件(F)");
		JMenu menuEdit = new JMenu("编辑(E)");
		JMenu menuFormat = new JMenu("格式(O)");
		JMenu menuView = new JMenu("查看(V)");
		JMenu menuHelp = new JMenu("帮助(H)");

		// 文件菜单
		this.mnuNew = this.buildMenu(menuFile, "新建(N)");
		this.mnuOpen = this.buildMenu(menuFile, "打开(O)...");
		this.mnuSave = this.buildMenu(menuFile, "保存(S)");
		this.mnuSaveAs = this.buildMenu(menuFile, "另存为(A)...");
		menuFile.addSeparator();// 添加菜单的分隔线
		this.mnuExit = this.buildMenu(menuFile, "退出(X)");

		// 编辑菜单
		this.mnuUndo = this.buildMenu(menuEdit, "撤销(U)");
		menuEdit.addSeparator();
		this.mnuCut = this.buildMenu(menuEdit, "剪切(T)     Ctrl+X");
		this.mnuCopy = this.buildMenu(menuEdit, "复制(C)    Ctrl+C");
		this.mnuPaste = this.buildMenu(menuEdit, "粘贴(P)   Ctrl+V");
		this.mnuDelete = this.buildMenu(menuEdit, "删除(L)     Del");
		menuEdit.addSeparator();
		this.mnuFind = this.buildMenu(menuEdit, "查找(F)...");
		this.mnuFindNext = this.buildMenu(menuEdit, "查找下一个(N)");
		this.mnuReplace = this.buildMenu(menuEdit, "替换(R)...");
		this.mnuGoto = this.buildMenu(menuEdit, "转到(G)...");
		menuEdit.addSeparator();
		this.mnuSelectAll = this.buildMenu(menuEdit, "全选(A)");
		this.mnuDateTime = this.buildMenu(menuEdit, "时间/日期(D)");

		// 格式菜单
		// this.mnuAutoWarp = this.buildMenu(menuFormat, "自动换行(W)");
		this.mnuAutoWarp = new JCheckBoxMenuItem("自动换行(W)");
		menuFormat.setFont(this.fontMenu);
		this.mnuAutoWarp.setFont(this.fontMenu);
		menuFormat.add(this.mnuAutoWarp);
		this.mnuFont = this.buildMenu(menuFormat, "字体(F)...");

		// 查看菜单
		// this.mnuStatus = this.buildMenu(menuView, "状态栏(S)");
		this.mnuStatus = new JCheckBoxMenuItem("状态栏(S)");
		menuView.setFont(this.fontMenu);
		this.mnuStatus.setFont(this.fontMenu);
		menuView.add(this.mnuStatus);

		// 帮助菜单
		this.mnuViewHelp = this.buildMenu(menuHelp, "查看帮助(H)");
		menuHelp.addSeparator();
		this.mnuAbout = this.buildMenu(menuHelp, "关于记事本(A)");
		this.mnuCalculator=this.buildMenu(menuHelp, "计算器");

		// 将菜单组装到菜单栏
		menuBar.add(menuFile);
		menuBar.add(menuEdit);
		menuBar.add(menuFormat);
		menuBar.add(menuView);
		menuBar.add(menuHelp);

		return menuBar;
	}

	/**
	 * 点击自动换行菜单项的状态改变事件处理方法
	 */

	/**
	 * 菜单的组装
	 * 
	 * @param menu
	 *            菜单名
	 * @param menuText
	 *            菜单项文字
	 * @return
	 */
	private JMenuItem buildMenu(JMenu menu, String menuText) {
		// 菜单的组装
		JMenuItem mnuItem = new JMenuItem(menuText);// 菜单项
		menu.setFont(this.fontMenu);// 设置菜单字体
		mnuItem.setFont(this.fontMenu); // 设置菜单项字体
		menu.add(mnuItem);// 将菜单项组装进菜单
		return mnuItem;
	}

	@Override
	public void setTitle(String fileName) {
		super.setTitle(fileName + this.title);
	}
	
	// 增加监听器的方法
	public void addListener() {
		this.txtContent.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				this.changedUpdate(e);

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				this.changedUpdate(e);

			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				if (!isModify) {
					//System.out.println("changedUpdate");
					setModify("未保存");
				}
			}

		});
		// 点击新建文件菜单的操作
		this.mnuNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!isModify) {
					initContent();
				} else {
					int choose = JOptionPane.showConfirmDialog(null, "是否将更改保存到 " + unknowFile, title,
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
							new ImageIcon(imagework + "image.png"));
					// System.out.println(choose);
					switch (choose) {
					case JOptionPane.YES_OPTION:
						try {
							setSave();
						} catch (FileNotFoundException e1) {
							
							e1.printStackTrace();
						}
						break;
					case JOptionPane.NO_OPTION:
						initContent();
						break;
					case JOptionPane.CANCEL_OPTION:
						break;
					default:
						break;
					}
				}
			}
		});

		this.mnuOpen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!isModify) {
					openFile();
				} else {
					int choose = JOptionPane.showConfirmDialog(null, "是否将更改保存到 " + unknowFile, title,
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
							new ImageIcon(imagework + "image.png"));
					// System.out.println(choose);
					switch (choose) {
					case JOptionPane.YES_OPTION:
						try {
							setSave();
							txtContent.setText(null);
						} catch (FileNotFoundException e1) {
							
							e1.printStackTrace();
						}
						break;
					case JOptionPane.NO_OPTION:
						openFile();
						break;
					case JOptionPane.CANCEL_OPTION:
						break;
					default:
						break;
					}
				}
			}
		});
		this.mnuSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!isModify) {

				}
				try {
					setSave();
					txtContent.setText(" ");
				} catch (FileNotFoundException e1) {
			
					e1.printStackTrace();
				}

			}

		});
		this.mnuSaveAs.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!isModify) {

				}
				try {
					setSave();

				} catch (FileNotFoundException e1) {
					
					e1.printStackTrace();
				}

			}
		});
		this.mnuExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!isModify) {
					System.exit(0);
				} else {
					int choose = JOptionPane.showConfirmDialog(null, "是否将更改保存到 " + unknowFile, title,
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
							new ImageIcon(imagework + "image.png"));
					// System.out.println(choose);
					switch (choose) {
					case JOptionPane.YES_OPTION:
						try {
							setSave();
							System.exit(0);
						} catch (FileNotFoundException e1) {
							
							e1.printStackTrace();
						}
						break;
					case JOptionPane.NO_OPTION:
						System.exit(0);
						break;
					case JOptionPane.CANCEL_OPTION:
						break;
					default:
						break;
					}

				}

			}
		});
		this.mnuUndo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				txtContent.setText(null);
			}
		});
		this.mnuCut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 调用JtextArea里面的删除复制粘贴功能
				txtContent.cut();

			}

		});
		this.mnuCopy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				txtContent.copy();

			}
		});
		this.mnuPaste.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				txtContent.paste();

			}
		});
		this.mnuDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				txtContent.replaceSelection(null);

			}
		});
		this.mnuSelectAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				txtContent.selectAll();

			}
		});
		this.mnuFind.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SetFind();

			}
		});
		this.mnuFindNext.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SetFind();

			}
		});
		this.mnuReplace.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FindorReplace();

			}

		});
		this.mnuDateTime.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showCalendar();

			}

		});
		this.mnuFont.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setFontDialog();

			}
		});
		this.txtContent.addCaretListener(new CaretListener() {

			@Override
			public void caretUpdate(CaretEvent e) {
				setLight();

			}

		});
		this.mnuStatus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mnuStatus();
				
			}

		});
		this.mnuAutoWarp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setautoWarp();

			}
		});
		this.mnuAbout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				aboutHandler();

			}

		});
		this.mnuCalculator.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SetCalculator();
				
			}
		});

	}

	// 初始化内容区域
	private void initContent() {
		// 设置窗体标题
		this.setTitle(this.unknowFile + this.separator + this.title);
		this.txtContent.setText("");
		this.setModify("新文件");
		//this.setStatus(1, 1);
	}

//	private void setStatus(int row, int col) {// Count：1
//
//		JPanel panStatus = ((JPanel) this.getContentPane().getComponent(1));
//		JLabel lblStatus = (JLabel) panStatus.getComponent(3);
//		lblStatus.setText("第 " + row + " 行/第 " + col + " 列");
//	}

	public void setModify(String modify) {
		switch (modify) {
		case "新文件":
		case "未修改":
		case "已保存":
			this.isModify = false;
			this.mnuSave.setEnabled(false);

			break;
		case "未保存":
			this.isModify = true;
			this.mnuSave.setEnabled(true);

			break;
		default:
			modify = "";

		}
		//TODO
		//JLabel lblmodify = (JLabel) ((JPanel) this.getContentPane().getComponent(1)).getComponent(0);
		//lblmodify.setText(modify);
		
		lblmodify.setText(modify);
	}
	// 捕捉光标状态栏
		private void setLight() {

			try {
				int pos = this.txtContent.getCaretPosition();
				int lineOfC = this.txtContent.getLineOfOffset(pos) + 1;
				int col = pos - this.txtContent.getLineStartOffset(lineOfC - 1) + 1;
				this.labCol.setText("第 " + String.valueOf(lineOfC) + " 行");
				this.labRow.setText("第 " + String.valueOf(col) + " 列");
			} catch (BadLocationException e) {
				System.out.println("无法获得光标位置");
				e.printStackTrace();
			}

		}

	// 换行方法
	private void setautoWarp() {
		this.txtContent.setLineWrap(this.mnuAutoWarp.getState());
	}

	// 捕捉光标在文本区域
	private void mnuStatus() {
		if (mnuStatus.isSelected()) {
			this.add(this.panBottom,BorderLayout.SOUTH);
			this.repaint();
		} else if(!mnuStatus.isSelected()){
			this.remove(this.panBottom);
			this.repaint();
			
		}
	}

	// 日历
	private void showCalendar() {
		CalendarDialog frm = new CalendarDialog(this, "日历", true);
		frm.setVisible(true);

	}
	//计算器
	private void SetCalculator() {
		CalculatorDialog cd=new CalculatorDialog(this, "计算器", true);
		cd.setVisible(true);
		
	}

	// 查找、查找下一个、替换
	private void FindorReplace() {
		FindorReplaceDialog fod = new FindorReplaceDialog(this, "查找", true);
		fod.setVisible(true);
	}
	//查找
	private void SetFind() {
		FindDialog rd=new FindDialog(this, "查找", true);
		rd.setVisible(true);
	}

	// 获得帮助的窗口
	private void aboutHandler() {
		AboutDialog ad = new AboutDialog(this, "帮助", true);
		ad.setVisible(true);

	}

	private void setSave() throws FileNotFoundException {

		FileDialog fd = new FileDialog(frame, "保存文件", FileDialog.SAVE);
		fd.setVisible(true);
		try {
			FileWriter fw = new FileWriter(fd.getDirectory() + fd.getFile() + ".txt");
			String b = null;
			b = this.txtContent.getText();
			fw.write(b);
			fw.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
		txtContent.setText("");

	}

	// 设置字体方法
	public void setFontDialog() {
		JDialog dialog = new SetFontDialog(this, "字体", true);

		dialog.setVisible(true);
	}

	// 打开文件操作方法
	public void openFile() {

		JFileChooser chooser = new JFileChooser(imagework);
		javax.swing.filechooser.FileFilter filter1 = new FileNameExtensionFilter("网页文件（*.html,*jsp）", "html", "jsp");
		chooser.setFileFilter(filter1);
		javax.swing.filechooser.FileFilter filter2 = new FileNameExtensionFilter("文本文件（*.txt）", "txt");
		chooser.setFileFilter(filter2);
		int returnVal = chooser.showOpenDialog(this);

		switch (returnVal) {
		case JFileChooser.APPROVE_OPTION:
			this.txtContent.setText(null);
			File file = chooser.getSelectedFile();

			try {
				BufferedReader br = new BufferedReader(
						new InputStreamReader(new FileInputStream(file), Notepad.getFilecharset(file)));
				String content;

				while ((content = br.readLine()) != null) {
					this.txtContent.append(content);
					this.txtContent.append("\r\n");
				}
				br.close();
				this.setModify("未修改");
				//this.setStatus(1, 1);
				this.txtContent.setFont(this.fontContent);
				this.txtContent.getCaret().setDot(0);// 移动到文档最顶端

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}
			this.setTitle(file.getName() + title);
			break;
		case JFileChooser.CANCEL_OPTION:
			break;
		case JFileChooser.ERROR_OPTION:
			break;

		}

	}

	private static String getFilecharset(File sourceFile) {
		String charset = "GBK";
		byte[] first3Bytes = new byte[3];
		try {
			boolean checked = false;
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sourceFile));
			bis.mark(0);
			int read = bis.read(first3Bytes, 0, 3);
			if (read == -1) {
				bis.close();
				return charset; // 文件编码为 ANSI
			} else if (first3Bytes[0] == (byte) 0xFF && first3Bytes[1] == (byte) 0xFE) {
				charset = "UTF-16LE"; // 文件编码为 Unicode
				checked = true;
			} else if (first3Bytes[0] == (byte) 0xFE && first3Bytes[1] == (byte) 0xFF) {
				charset = "UTF-16BE"; // 文件编码为 Unicode big endian
				checked = true;
			} else if (first3Bytes[0] == (byte) 0xEF && first3Bytes[1] == (byte) 0xBB
					&& first3Bytes[2] == (byte) 0xBF) {
				charset = "UTF-8"; // 文件编码为 UTF-8
				checked = true;
			}
			bis.reset();
			if (!checked) {
				while ((read = bis.read()) != -1) {
					if (read >= 0xF0)
						break;
					if (0x80 <= read && read <= 0xBF) // 单独出现BF以下的，也算是GBK
						break;
					if (0xC0 <= read && read <= 0xDF) {
						read = bis.read();
						if (0x80 <= read && read <= 0xBF) // 双字节 (0xC0 - 0xDF)
							// (0x80
							// - 0xBF),也可能在GB编码内
							continue;
						else
							break;
					} else if (0xE0 <= read && read <= 0xEF) {// 也有可能出错，但是几率较小
						read = bis.read();
						if (0x80 <= read && read <= 0xBF) {
							read = bis.read();
							if (0x80 <= read && read <= 0xBF) {
								charset = "UTF-8";
								break;
							} else
								break;
						} else
							break;
					}
				}
			}
			bis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return charset;
	}

}
