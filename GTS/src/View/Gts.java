 package View;
import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.SystemColor;
import Helper.DbHelper;
import javax.swing.JTable;

public class Gts extends JFrame {
    private   JPanel contentPane;
	private static JTextArea textAreaUserName;
	private static JPasswordField passwordField;
	private JButton btnNewButtonLogIn;
	private JButton btnNewButtonExit;
	private JTextField textFieldAuthor;
	private JComboBox comboBoxUniversity; 
	private JComboBox comboBoxInstitue ;
	private JComboBox comboBoxThesisType;
	private JComboBox comboBoxLanguage;
	private JComboBox comboBoxVisor ;
	private JComboBox comboBoxYear1;
	private JComboBox comboBoxYear2;
	private JButton btnFýnd;
	private JScrollPane sp;
	Connection con=DbHelper.connDb();
	static DefaultTableModel model=new DefaultTableModel();
	static Object[] columns= {"ABSTRACT"};
	static Object[] rows= new Object[1];
	static String k;
	private JTable tableAbstract;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Connection con=DbHelper.connDb();
					Gts frame = new Gts();
					frame.setVisible(true);
					frame.setTitle("GTS");
					frame.setIconImage(new ImageIcon(getClass().getResource("/View/tez.png")).getImage());
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	
	public Gts() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 907, 483);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.info);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(120, 187, 684, 259);
		contentPane.add(tabbedPane);
		
		JPanel LogIn = new JPanel();
		LogIn.setBackground(new Color(176, 196, 222));
		tabbedPane.addTab("Log In", null, LogIn, null);
		LogIn.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("USER NAME:");
		lblNewLabel.setBounds(118, 53, 99, 13);
		LogIn.add(lblNewLabel);
		
	    textAreaUserName = new JTextArea();
		textAreaUserName.setBounds(210, 52, 94, 16);
		LogIn.add(textAreaUserName);
		
		JLabel lblNewLabel_1 = new JLabel("PASSWORD:");
		lblNewLabel_1.setBounds(118, 91, 99, 13);
		LogIn.add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(210, 88, 94, 19);
		LogIn.add(passwordField);
		
		 btnNewButtonLogIn = new JButton("Log In");
		btnNewButtonLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				LogIn();	
			}
			});
		btnNewButtonLogIn.setBounds(210, 162, 85, 21);
		LogIn.add(btnNewButtonLogIn);
		
		 btnNewButtonExit = new JButton("Exit");
		btnNewButtonExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		btnNewButtonExit.setBounds(0, 180, 85, 21);
		LogIn.add(btnNewButtonExit);
		
		JPanel Fýnd = new JPanel();
		Fýnd.setBackground(new Color(173, 216, 230));
		Fýnd.setToolTipText("");
		Fýnd.setForeground(Color.BLACK);
		tabbedPane.addTab("Fýnd Thesis", null, Fýnd, null);
		Fýnd.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("University:");
		lblNewLabel_2.setBounds(10, 39, 80, 13);
		Fýnd.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Author:");
		lblNewLabel_3.setBounds(417, 13, 57, 13);
		Fýnd.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Year:");
		lblNewLabel_4.setBounds(406, 60, 57, 13);
		Fýnd.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Institute:");
		lblNewLabel_5.setBounds(10, 72, 66, 13);
		Fýnd.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Thesis Type:");
		lblNewLabel_6.setBounds(10, 101, 80, 13);
		Fýnd.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Super V\u0131sor:");
		lblNewLabel_7.setBounds(406, 101, 80, 13);
		Fýnd.add(lblNewLabel_7);
		
		comboBoxUniversity = new JComboBox();
		comboBoxUniversity.getModel().setSelectedItem("Select");
		comboBoxUniversity.setBounds(100, 35, 128, 21);
		Fýnd.add(comboBoxUniversity);
		
		
          try {
			
			Statement st=con.createStatement();
			
			String sqlquery="SELECT UNIVERSITY_NAME FROM UNIVERSITYTB ORDER BY UNIVERSITY_NAME ASC ";
			ResultSet rs=st.executeQuery(sqlquery);
			while(rs.next()) {
				comboBoxUniversity.addItem(rs.getString("UNIVERSITY_NAME"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        comboBoxUniversity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try {
			     	   
						
						Statement st=con.createStatement();
						
						k=comboBoxUniversity.getSelectedItem().toString();
						
						String sqlquery="SELECT INSTITUTE_NAME FROM INSTITUTETB AS I\r\n"
								+ "INNER JOIN UNIVERSITYTB AS U ON\r\n"
								+ "U.UNIVERSITYID=I.UNIVERSITYID\r\n"
								+ "WHERE U.UNIVERSITY_NAME="+"'"+k+"'";
						ResultSet rs=st.executeQuery(sqlquery);
						comboBoxInstitue.removeAllItems();
						while(rs.next()) {
							comboBoxInstitue.addItem(rs.getString("INSTITUTE_NAME"));
							
						}
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		
		comboBoxInstitue = new JComboBox();
		comboBoxInstitue.setBounds(89, 68, 198, 21);
		Fýnd.add(comboBoxInstitue);
		
		 try {
				Statement st=con.createStatement();
				k=comboBoxUniversity.getSelectedItem().toString();
				
				String sqlquery="SELECT INSTITUTE_NAME FROM INSTITUTETB AS I\r\n"
						+ "INNER JOIN UNIVERSITYTB AS U ON\r\n"
						+ "U.UNIVERSITYID=I.UNIVERSITYID\r\n"
						+ "WHERE U.UNIVERSITY_NAME="+"'"+k+"'";
				ResultSet rs=st.executeQuery(sqlquery);
				comboBoxInstitue.removeAllItems();
				while(rs.next()) {
					comboBoxInstitue.addItem(rs.getString("INSTITUTE_NAME"));
					
				}
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
	    comboBoxThesisType = new JComboBox();
		comboBoxThesisType.setBounds(100, 97, 187, 21);
		comboBoxThesisType.getModel().setSelectedItem("Select");
		Fýnd.add(comboBoxThesisType);
		
		try {
			Statement st=con.createStatement();
			String sqlquery="SELECT TYPE_NAME FROM TYPETB ORDER BY TYPE_NAME ASC ";
			ResultSet rs=st.executeQuery(sqlquery);
			while(rs.next()) {
				comboBoxThesisType.addItem(rs.getString("TYPE_NAME"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		textFieldAuthor = new JTextField();
		textFieldAuthor.setBounds(484, 10, 96, 19);
		Fýnd.add(textFieldAuthor);
		textFieldAuthor.setColumns(10);
		
		comboBoxYear1 = new JComboBox();
		comboBoxYear1.getModel().setSelectedItem("Select");
		comboBoxYear1.setBounds(466, 56, 58, 21);
		Fýnd.add(comboBoxYear1);
		
		
		for(int year = 1950; year<LocalDate.now().getYear(); ++year)
		{
			comboBoxYear1.addItem(year);
		}
		
		JLabel lblNewLabel_9 = new JLabel("<= year <=");
		lblNewLabel_9.setBounds(534, 60, 66, 13);
		Fýnd.add(lblNewLabel_9);
		
		comboBoxYear2 = new JComboBox();
		comboBoxYear2.getModel().setSelectedItem("Select");
		comboBoxYear2.setBounds(593, 56, 57, 21);
		Fýnd.add(comboBoxYear2);
		
		for(int year = 1950; year<LocalDate.now().getYear(); ++year)
		{
			comboBoxYear2.addItem(year);
		}
		
		btnFýnd = new JButton("FIND");
		btnFýnd.setBackground(new Color(255, 255, 255));
		btnFýnd.setToolTipText("FIND THESIS");
		btnFýnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Find();
			}
		});
		btnFýnd.setBounds(269, 170, 85, 21);
		Fýnd.add(btnFýnd);
		
		JLabel lblNewLabel_10 = new JLabel("Language:");
		lblNewLabel_10.setBounds(10, 139, 66, 13);
		Fýnd.add(lblNewLabel_10);
		
		comboBoxLanguage = new JComboBox();
		comboBoxLanguage.getModel().setSelectedItem("Select");
		comboBoxLanguage.setBounds(89, 135, 105, 21);
		Fýnd.add(comboBoxLanguage);
		
		
        try {
			
			Statement st=con.createStatement();
			
			String sqlquery="SELECT LANGUAGE_NAME FROM LANGUAGETB ORDER BY LANGUAGE_NAME ASC ";
			ResultSet rs=st.executeQuery(sqlquery);
			
			while(rs.next()) {
				comboBoxLanguage.addItem(rs.getString("LANGUAGE_NAME"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		comboBoxVisor = new JComboBox();
		comboBoxVisor.setBounds(509, 97, 141, 21);
		Fýnd.add(comboBoxVisor);
		comboBoxVisor.getModel().setSelectedItem("Select");
		
		tableAbstract = new JTable();
		tableAbstract.setBounds(410, 138, 259, 84);
		Fýnd.add(tableAbstract);
		Gts.model.setColumnIdentifiers(columns);
		
		sp=new JScrollPane();
		Fýnd.add(sp);
		
		 try {
				
				Statement st=con.createStatement();
				
				String sqlquery="SELECT VISOR_NAME_SURNAME FROM VISORTB ORDER BY VISOR_NAME_SURNAME ASC ";
				ResultSet rs=st.executeQuery(sqlquery);  
				while(rs.next()) {
					comboBoxVisor.addItem(rs.getString("VISOR_NAME_SURNAME"));
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		JLabel lblNewLabelLogo = new JLabel(new ImageIcon(getClass().getResource("gts22.jpg")));
		lblNewLabelLogo.setBounds(410, 29, 239, 131);
		contentPane.add(lblNewLabelLogo);
		
		JLabel lblNewLabel_8 = new JLabel("GTS");
		lblNewLabel_8.setBackground(SystemColor.activeCaption);
		lblNewLabel_8.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_8.setBounds(269, 78, 57, 38);
		contentPane.add(lblNewLabel_8);
		
		
	
	}
	public static JTextArea getUserName() {
		return textAreaUserName;
		
	}
	public static JPasswordField getPassword() {
		return passwordField;
		
	}
	
	public void Find() {
		try {
			Statement st=con.createStatement();
			Gts.model.setRowCount(0);
		   String author=textFieldAuthor.getText();
		   String universityName="";
		   String instituteName="";
		   String thesisType="";
		   String year1="";
		   String year2="";
		   String language="";
		   String visor="";
		   
		   if(comboBoxUniversity.getSelectedItem()!=null) {
			    universityName=comboBoxUniversity.getSelectedItem().toString();   
		   }
		   if(comboBoxInstitue.getSelectedItem()!=null) {
			    instituteName=comboBoxInstitue.getSelectedItem().toString();  
		   }
		   if(comboBoxThesisType.getSelectedItem()!=null) {
			   thesisType=comboBoxThesisType.getSelectedItem().toString();   
		   }
		   if(comboBoxYear1.getSelectedItem()!=null) {
			    year1=comboBoxYear1.getSelectedItem().toString();   
		   }
		   if(comboBoxYear2.getSelectedItem()!=null) {
			    year2=comboBoxYear2.getSelectedItem().toString();   
		   }
		   if(comboBoxLanguage.getSelectedItem()!=null) {
			    language=comboBoxLanguage.getSelectedItem().toString(); 
		   }
		   if(comboBoxLanguage.getSelectedItem()!=null) {
			    language=comboBoxLanguage.getSelectedItem().toString(); 
		   }
		   if(comboBoxVisor.getSelectedItem()!=null) {
			   visor=comboBoxVisor.getSelectedItem().toString();
		   }
		  
		  
		   
		   String stringQuery="SELECT ABSTRACT FROM THESISTB AS T "
		   		+ "INNER JOIN AUTHORTB AS A ON T.AUTHORID=A.AUTHORID "
		   		+ "INNER JOIN UNIVERSITYTB AS U ON T.UNIVERSITYID=U.UNIVERSITYID "
		   		+ "INNER JOIN INSTITUTETB AS I ON T.INSTITUTEID=I.INSTITUTEID "
		   		+ "INNER JOIN TYPETB AS TP ON T.TYPEID=TP.TYPEID "
		   		+ "INNER JOIN LANGUAGETB AS L ON T.LANGUAGEID=L.LANGUAGEID "
		   		+ "INNER JOIN VISORTB AS V ON T.SUPERVISORID=V.VISORID "
		   		+ "WHERE U.UNIVERSITY_NAME="+"'"+universityName+"'"
		   		+" OR "+"I.INSTITUTE_NAME="+"'"+instituteName+"'"
		   		+" OR "+"TP.TYPE_NAME="+"'"+thesisType+"'"
		   		+" OR "+"(YEAR(T.YEAROFTHESIS) BETWEEN "+"'"+year1+"'"+" AND "+"'"+year2+"'"+")"
		   		+" OR "+"L.LANGUAGE_NAME="+"'"+language+"'"
		   		+" OR "+"V.VISOR_NAME_SURNAME="+"'"+visor+"'";
		   
			ResultSet rs=st.executeQuery(stringQuery);
			
			while(rs.next()) {
				rows[0]=rs.getString("ABSTRACT");
				Gts.model.addRow(rows);		
			}
			tableAbstract.setModel(Gts.model);
			
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
	}
	public void LogIn() {
		try {
			
			Statement st=con.createStatement();
			String sqlquery="SELECT * FROM AUTHORTB WHERE AUTHOR_USERNAME="+"'"+textAreaUserName.getText()+"'"+" AND AUTHOR_PASSWORD="+"'"+passwordField.getText()+"'";
			
			ResultSet rs=st.executeQuery(sqlquery);
			if(rs.next()) {
				JOptionPane.showMessageDialog(btnNewButtonLogIn,"Hello, Welcome to GTS.");
				User frame=new User();
				frame.setVisible(true);
				dispose();
				
				
			}
			else {
				JOptionPane.showMessageDialog(btnNewButtonLogIn,"Please enter correctly"); 
				
			}
							
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}
	public void close() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WindowEvent closeWindow=new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWindow);
	}
}
