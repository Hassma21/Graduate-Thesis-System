package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Helper.DbHelper;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import View.Gts;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;

public class User extends JFrame implements IRefresh {

	
	private JPanel contentPane;
	private JTable table;
	static JTable tableData;
	static DefaultTableModel model=new DefaultTableModel();
	static Object[] columns= {"THESISNO","TITLE","ABSTRACT","TYPE","UNIVERSITY","INSTITUTE","VISOR"};
	static Object[] rows= new Object[7];
	Connection con=DbHelper.connDb();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					User frame = new User();
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
	public User() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 996, 584);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(173, 216, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAdd = new JButton("Add Thesis");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Add frame=new Add();
				frame.setVisible(true);
			
			}
		});
		btnAdd.setBackground(Color.GREEN);
		btnAdd.setBounds(803, 71, 127, 27);
		contentPane.add(btnAdd);
		
		JButton btnDelete = new JButton("Delete Thesis");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row=tableData.getSelectedRow();
				String cell=tableData.getModel().getValueAt(row, 0).toString();
				String sql="DELETE FROM THESISTB WHERE THESISNO="+cell;
				try {
					Statement st=con.createStatement();
					int response=JOptionPane.showConfirmDialog( null, "Thesis will be deleteded Are you Sure?","Delete",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
					if(response==0) {
						st.executeUpdate(sql);
						Refresh();	
					}
					else {
						JOptionPane.showMessageDialog(null, "Not Deleted");
					}
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
			}
		});
		btnDelete.setBackground(new Color(255, 69, 0));
		btnDelete.setBounds(803, 149, 127, 27);
		contentPane.add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 53, 722, 220);
		contentPane.add(scrollPane);
		
		tableData = new JTable();
		tableData.setBounds(111, 218, 400, 139);
		model.setColumnIdentifiers(columns);
		
		
		scrollPane.setViewportView(tableData);
      		//contentPane.add(tableData);
		
	
		
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
			while(rs.next()) {
				rows[0]=rs.getString("THESISNO");
				rows[1]=rs.getString("TITLE");
				rows[2]=rs.getString("ABSTRACT");
				rows[3]=rs.getString("TYPE_NAME");
				rows[4]=rs.getString("UNIVERSITY_NAME");
				rows[5]=rs.getString("INSTITUTE_NAME");
				rows[6]=rs.getString("VISOR_NAME_SURNAME");
				
				model.addRow(rows);	
				
			}
			tableData.setModel(model);
			
			JButton btnNewButton = new JButton("Exit");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					close();
				}
			});
			btnNewButton.setBackground(Color.RED);
			btnNewButton.setBounds(0, 516, 85, 21);
			contentPane.add(btnNewButton);
			
			}
		 catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catch(Exception e2) {
			e2.printStackTrace();
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
			model.setRowCount(0);
			
			while(rs.next()) {
				rows[0]=rs.getString("THESISNO");
				rows[1]=rs.getString("TITLE");
				rows[2]=rs.getString("ABSTRACT");
				rows[3]=rs.getString("TYPE_NAME");
				rows[4]=rs.getString("UNIVERSITY_NAME");
				rows[5]=rs.getString("INSTITUTE_NAME");
				rows[6]=rs.getString("VISOR_NAME_SURNAME");
				model.addRow(rows);	
				
				
			}
			tableData.setModel(model);
			
			}
		 catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catch(Exception e2) {
			e2.printStackTrace();
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
