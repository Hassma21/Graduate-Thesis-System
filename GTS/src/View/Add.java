package View;

import java.awt.BorderLayout;
import com.toedter.calendar.*;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import View.User;
import Helper.DbHelper;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.security.auth.login.AccountException;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import com.toedter.components.JLocaleChooser;
import com.toedter.calendar.JDateChooser;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;



public class Add extends JFrame implements IRefresh {

	private JPanel contentPane;
	private JTextField textTitle;
	private JTextField txtYear;
	private JTextField textFieldPage;
	private JTextArea textAreaAbstract;
	private JComboBox comboBoxType;
	private JComboBox comboBoxInstitute;
	private JComboBox comboBoxUniversity;
	private JComboBox comboBoxLanguage;
	private JComboBox comboBoxVisor;
	private JComboBox comboBoxCoVisor;
	private JButton btnNewButtonSave;
	static String k;
	Connection con=DbHelper.connDb();
	public static ResultSet rs;
	 
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					Add frame = new Add();
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
	public Add() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 876, 523);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabelLogo = new JLabel(new ImageIcon(getClass().getResource("gts22.jpg")));
		lblNewLabelLogo.setBounds(613, 345, 239, 131);
		contentPane.add(lblNewLabelLogo);
		
		JLabel lblNewLabel = new JLabel("Title:");
		lblNewLabel.setBounds(10, 54, 64, 17);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Abstract:");
		lblNewLabel_1.setBounds(10, 95, 64, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Year Of Thesis:");
		lblNewLabel_2.setBounds(0, 201, 109, 13);
		contentPane.add(lblNewLabel_2);
				
		JLabel lblNewLabel_3 = new JLabel("Type:");
		lblNewLabel_3.setBounds(10, 239, 45, 13);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("University:");
		lblNewLabel_4.setBounds(10, 285, 81, 13);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Institute:");
		lblNewLabel_5.setBounds(10, 326, 45, 19);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Language:");
		lblNewLabel_6.setBounds(411, 95, 146, 13);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Super Visor:");
		lblNewLabel_7.setBounds(411, 147, 146, 13);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("CO-Super Visor:");
		lblNewLabel_8.setBounds(411, 187, 146, 13);
		contentPane.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Number Of Page:");
		lblNewLabel_9.setBounds(411, 56, 160, 13);
		contentPane.add(lblNewLabel_9);
		
		textTitle = new JTextField();
		textTitle.setBounds(108, 53, 96, 19);
		contentPane.add(textTitle);
		textTitle.setColumns(10);
		
		textAreaAbstract = new JTextArea();
		textAreaAbstract.setBounds(110, 94, 282, 79);
		contentPane.add(textAreaAbstract);
		
		txtYear = new JTextField();
		txtYear.setBounds(108, 198, 96, 19);
		contentPane.add(txtYear);
		txtYear.setColumns(10);
		
		comboBoxType = new JComboBox();
		comboBoxType.setBounds(108, 235, 134, 21);
		contentPane.add(comboBoxType);
		comboBoxType.addItem("Select");
		
		try {
			
			
			Statement st=con.createStatement();
			String sqlquery="SELECT TYPE_NAME FROM TYPETB ORDER BY TYPE_NAME ASC ";
			ResultSet rs=st.executeQuery(sqlquery);
			while(rs.next()) {
				comboBoxType.addItem(rs.getString("TYPE_NAME"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		comboBoxUniversity = new JComboBox();
		comboBoxUniversity.setBounds(108, 281, 134, 21);
		contentPane.add(comboBoxUniversity);
		comboBoxUniversity.addItem("Select");
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
						comboBoxInstitute.removeAllItems();
						while(rs.next()) {
							comboBoxInstitute.addItem(rs.getString("INSTITUTE_NAME"));
							
						}
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		
	
		 comboBoxInstitute = new JComboBox();
		 comboBoxInstitute.setBounds(108, 325, 206, 21);
	     contentPane.add(comboBoxInstitute);
	     comboBoxInstitute.addItem("Select");
		
		
		textFieldPage = new JTextField();
		textFieldPage.setBounds(581, 53, 96, 19);
		contentPane.add(textFieldPage);
		textFieldPage.setColumns(10);
		
		 comboBoxLanguage = new JComboBox();
		 comboBoxLanguage.setBounds(578, 91, 99, 21);
		 contentPane.add(comboBoxLanguage);
	     comboBoxLanguage.addItem("Select");
		
        try {
			
			Statement st=con.createStatement();
			
			String sqlquery="SELECT LANGUAGE_NAME FROM LANGUAGETB ORDER BY LANGUAGE_NAME ASC ";
			ResultSet rs=st.executeQuery(sqlquery);
			comboBoxLanguage.addItem("");
			while(rs.next()) {
				comboBoxLanguage.addItem(rs.getString("LANGUAGE_NAME"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		comboBoxVisor = new JComboBox();
		comboBoxVisor.setBounds(581, 143, 134, 21);
		contentPane.add(comboBoxVisor);
		comboBoxVisor.addItem("Select");
		
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
       
		
		 comboBoxCoVisor = new JComboBox();
		comboBoxCoVisor.setBounds(581, 183, 134, 21);
		contentPane.add(comboBoxCoVisor);
		comboBoxCoVisor.addItem("Select");
		
       try {
			
			Statement st=con.createStatement();
			
			String sqlquery="SELECT VISOR_NAME_SURNAME FROM VISORTB ORDER BY VISOR_NAME_SURNAME ASC ";
			ResultSet rs=st.executeQuery(sqlquery);
			while(rs.next()) {
				comboBoxCoVisor.addItem(rs.getString("VISOR_NAME_SURNAME"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 btnNewButtonSave = new JButton("SAVE");
		btnNewButtonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int response=JOptionPane.showConfirmDialog( null, "Thesis will be recorded Are you Sure?","Record",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				if(response==0) {
					Save();
					Refresh();
					JOptionPane.showMessageDialog(null, "Saved");
				}
				else {
					JOptionPane.showMessageDialog(null, "NotSaved");
				}	
			}
		});
		btnNewButtonSave.setBackground(Color.RED);
		btnNewButtonSave.setBounds(581, 255, 85, 21);
		contentPane.add(btnNewButtonSave);
		
		
	}
	public void Save() {
		 try {
			 Statement st=con.createStatement();
			 String title=textTitle.getText();
			 
			 String abstractt=textAreaAbstract.getText();
			 String sqlquery="SELECT AUTHORID FROM AUTHORTB"
					 + " WHERE AUTHOR_USERNAME="+
						"'"+Gts.getUserName().getText()+"'"
						+" AND AUTHOR_PASSWORD="+
						"'"+Gts.getPassword().getText()+"'";
			 
			 rs=st.executeQuery(sqlquery);
			 rs.next();
			 int authorid=rs.getInt("AUTHORID");	
			String  time=txtYear.getText();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date thisDate = dateFormat.parse(time);
            java.sql.Date date = new java.sql.Date(thisDate.getTime());
      
			String sqlquery1="SELECT TYPEID FROM TYPETB"
					 + " WHERE TYPE_NAME="+
						"'"+comboBoxType.getSelectedItem().toString()+"'";
						
			rs=st.executeQuery(sqlquery1);
			rs.next();
			int typeid=rs.getInt("TYPEID");
			 
			
			String sqlquery2="SELECT UNIVERSITYID FROM UNIVERSITYTB"
					 + " WHERE UNIVERSITY_NAME="+
						"'"+comboBoxUniversity.getSelectedItem().toString()+"'";
						
			rs=st.executeQuery(sqlquery2);
			rs.next();
			int universityid=rs.getInt("UNIVERSITYID");
			
			
			String sqlquery3="SELECT INSTITUTEID FROM INSTITUTETB"
					 + " WHERE INSTITUTE_NAME="+
						"'"+comboBoxInstitute.getSelectedItem().toString()+"'";
						
			rs=st.executeQuery(sqlquery3);
			rs.next();
			int instituteid=rs.getInt("INSTITUTEID");
			
			String numberofpage=textFieldPage.getText();
			
			 
			
			String sqlquery4="SELECT LANGUAGEID FROM  LANGUAGETB"
					 + " WHERE LANGUAGE_NAME="+
						"'"+comboBoxLanguage.getSelectedItem().toString()+"'";
						
			rs=st.executeQuery(sqlquery4);
			rs.next();
			int languageid=rs.getInt("LANGUAGEID");
			
			LocalDateTime subtime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "yyyy-mm-dd hh:mm:ss" ) ;

			String sqlquery5="SELECT VISORID FROM  VISORTB"
					 + " WHERE VISOR_NAME_SURNAME="+
						"'"+comboBoxVisor.getSelectedItem().toString()+"'";
						
			rs=st.executeQuery(sqlquery5);
			rs.next();
			int visorid=rs.getInt("VISORID");
			
			
			String sqlquery6="SELECT VISORID FROM  VISORTB"
					 + " WHERE VISOR_NAME_SURNAME="+
						"'"+comboBoxCoVisor.getSelectedItem().toString()+"'";
						
			rs=st.executeQuery(sqlquery6);
			rs.next();
			int covisorid=rs.getInt("VISORID");
			
			
			String query="INSERT INTO THESISTB(TITLE,ABSTRACT,AUTHORID,YEAROFTHESIS,TYPEID,UNIVERSITYID,INSTITUTEID,NUMBEROFPAGE,LANGUAGEID,SUBMISSIONDATE,SUPERVISORID,CO_SUPERVISORID) "
					+ "VALUES('"+title+"','"+abstractt+"','"+authorid+"','"+date+"','"+typeid+"','"+universityid+"','"+instituteid+"','"+numberofpage+"','"+languageid+"','"+subtime+"','"+visorid+"','"+covisorid+"') ";
			
			
				st.executeUpdate(query);
			
				
			} catch (SQLException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
	}

	@Override
	public void Refresh() {
               try {
			
			Statement st=con.createStatement();
			
			String sqlquery="SELECT T.THESISNO,T.TITLE,T.ABSTRACT,TYPETB.TYPE_NAME,U.UNIVERSITY_NAME,I.INSTITUTE_NAME,V.VISOR_NAME_SURNAME FROM THESISTB AS T\r\n"
					+ "INNER JOIN UNIVERSITYTB AS U ON T.UNIVERSITYID=U.UNIVERSITYID\r\n"
					+ "INNER JOIN INSTITUTETB AS I ON T.INSTITUTEID=I.INSTITUTEID\r\n"
					+ "INNER JOIN TYPETB ON T.TYPEID=TYPETB.TYPEID\r\n"
					+ "INNER JOIN VISORTB AS V ON T.SUPERVISORID=V.VISORID\r\n"
					+"INNER JOIN AUTHORTB AS A ON T.AUTHORID=A.AUTHORID\r\n"
					+ " WHERE A.AUTHOR_USERNAME="+
					"'"+Gts.getUserName().getText()+"'"
					+" AND A.AUTHOR_PASSWORD="+
					"'"+Gts.getPassword().getText()+"'";
			
			ResultSet rs=st.executeQuery(sqlquery);
			User.model.setRowCount(0);
			
			while(rs.next()) {
				User.rows[0]=rs.getString("THESISNO");
				User.rows[1]=rs.getString("TITLE");
				User.rows[2]=rs.getString("ABSTRACT");
				User.rows[3]=rs.getString("TYPE_NAME");
				User.rows[4]=rs.getString("UNIVERSITY_NAME");
				User.rows[5]=rs.getString("INSTITUTE_NAME");
				User.rows[6]=rs.getString("VISOR_NAME_SURNAME");
				User.model.addRow(User.rows);	
				
				
			}
			User.tableData.setModel(User.model);
			
			
			}
		 catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catch(Exception e2) {
			e2.printStackTrace();
		}
		
		
	}

	
		
		
	}

	
		
		
		
	

