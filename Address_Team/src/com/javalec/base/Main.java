package com.javalec.base;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

	private JPanel contentPane;
	private JLabel lbAddress;
	private JLabel lbName;
	private JLabel lbPhone;
	private JLabel lbEmail;
	private JLabel lbRelation;
	private JButton btInput;
	private JTextField tfName;
	private JTextField tfTelno;
	private JTextField tfAddress;
	private JTextField tfEmail;
	private JTextField tfRelation;

	// Database 환경 정의
	private final String url_mysql = "jdbc:mysql://127.0.0.1/useraddress?serverTimezone=UTC&characterEncoding=utf8&useSSL=FALSE";	// 127.0.0.1 은 내 로컬 주소 
	private final String id_mysql = "root";
	private final String pw_mysql = "qwer1234";
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setTitle("주소록 등록");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 371);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLbAddress());
		contentPane.add(getLbName());
		contentPane.add(getLbPhone());
		contentPane.add(getLbEmail());
		contentPane.add(getLbRelation());
		contentPane.add(getBtInput());
		contentPane.add(getTfName());
		contentPane.add(getTfTelno());
		contentPane.add(getTfAddress());
		contentPane.add(getTfEmail());
		contentPane.add(getTfRelation());
	}
	private JLabel getLbAddress() {
		if (lbAddress == null) {
			lbAddress = new JLabel("주소 :");
			lbAddress.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			lbAddress.setBounds(43, 125, 61, 16);
		}
		return lbAddress;
	}
	private JLabel getLbName() {
		if (lbName == null) {
			lbName = new JLabel("성명 :");
			lbName.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			lbName.setBounds(43, 43, 61, 16);
		}
		return lbName;
	}
	private JLabel getLbPhone() {
		if (lbPhone == null) {
			lbPhone = new JLabel("전화번호 :");
			lbPhone.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			lbPhone.setBounds(43, 83, 76, 16);
		}
		return lbPhone;
	}
	private JLabel getLbEmail() {
		if (lbEmail == null) {
			lbEmail = new JLabel("전자우편 :");
			lbEmail.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			lbEmail.setBounds(43, 166, 76, 16);
		}
		return lbEmail;
	}
	private JLabel getLbRelation() {
		if (lbRelation == null) {
			lbRelation = new JLabel("관계 :");
			lbRelation.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			lbRelation.setBounds(43, 214, 61, 16);
		}
		return lbRelation;
	}
	private JButton getBtInput() {
		if (btInput == null) {
			btInput = new JButton("입력");
			btInput.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int i_chk = insertFieldCheck();
					if(i_chk == 0) {
						insertAction();
					}
				}
			});
			btInput.setBounds(370, 272, 117, 29);
		}
		return btInput;
	}
	private JTextField getTfName() {
		if (tfName == null) {
			tfName = new JTextField();
			tfName.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			tfName.setBounds(149, 39, 130, 26);
			tfName.setColumns(10);
		}
		return tfName;
	}
	private JTextField getTfTelno() {
		if (tfTelno == null) {
			tfTelno = new JTextField();
			tfTelno.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			tfTelno.setColumns(10);
			tfTelno.setBounds(149, 79, 203, 26);
		}
		return tfTelno;
	}
	private JTextField getTfAddress() {
		if (tfAddress == null) {
			tfAddress = new JTextField();
			tfAddress.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			tfAddress.setColumns(10);
			tfAddress.setBounds(149, 121, 329, 26);
		}
		return tfAddress;
	}
	private JTextField getTfEmail() {
		if (tfEmail == null) {
			tfEmail = new JTextField();
			tfEmail.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			tfEmail.setColumns(10);
			tfEmail.setBounds(149, 162, 238, 26);
		}
		return tfEmail;
	}
	private JTextField getTfRelation() {
		if (tfRelation == null) {
			tfRelation = new JTextField();
			tfRelation.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			tfRelation.setColumns(10);
			tfRelation.setBounds(149, 204, 357, 26);
		}
		return tfRelation;
	}
	
	// --- functions
	//
	
	private int insertFieldCheck() {
		int i = 0;
		String message = "";
		
		if(tfName.getText().trim().length() == 0) {			 // 공백없애기 위해 trim 필수
			i++;
			message = "이름을 ";
			tfName.requestFocus();
		}
		if(tfTelno.getText().trim().length() == 0) {
			i++;
			message = "전화번호를 ";
			tfTelno.requestFocus();
		}
		if(tfAddress.getText().trim().length() == 0) {
			i++;
			message = "주소를 ";
			tfAddress.requestFocus();
		}
		if(tfEmail.getText().trim().length() == 0) {
			i++;
			message = "전자우편을 ";
			tfEmail.requestFocus();
		}
		if(tfRelation.getText().trim().length() == 0) {
			i++;
			message = "관계를 ";
			tfRelation.requestFocus();
		}
		
		if(i > 0) {
			JOptionPane.showMessageDialog(null, message + "확인하세요!");
		}
		
		return i;
	}
	
	
	private void insertAction() {
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");	// 데이터 연결 정의
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			String query = "insert into userinfo (name, telno, address, email, relation)";	// 입력정의
			String query1 = " values (?, ?, ?, ?, ?)";	// ?를 해결하는게 PreparedStatement 이다
			
			ps = conn_mysql.prepareStatement(query + query1);	// 데이터값 넣기
			ps.setString(1, tfName.getText().trim());
			ps.setString(2, tfTelno.getText().trim());
			ps.setString(3, tfAddress.getText().trim());
			ps.setString(4, tfEmail.getText().trim());
			ps.setString(5, tfRelation.getText().trim());
			
			ps.executeUpdate();  //실행
			conn_mysql.close();
			
			JOptionPane.showMessageDialog(null, tfName.getText() + "님의 정보가 입력 되었습니다.");
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
}