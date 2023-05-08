package com.javalec.base;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.javalec.dao.Dao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Join extends JFrame {

	private JPanel contentPane;
	private JTextField tfId;
	private JTextField tfPw;
	private JTextField tfName;
	private JTextField tfPhone;
	private JTextField tfEmail;
	private JTextField tfAddress;
	private JButton btnCheck;
	private JButton btnJoin;
	
	String message = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Join frame = new Join();
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
	public Join() {
		addWindowListener(new WindowAdapter() {
		});
		setTitle("회원가입");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 301, 477);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID :");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel.setBounds(12, 28, 57, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Pw :");
		lblPassword.setFont(new Font("굴림", Font.PLAIN, 15));
		lblPassword.setBounds(12, 71, 35, 15);
		contentPane.add(lblPassword);
		
		JLabel lblNewLabel_1 = new JLabel("이름 :");
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(12, 114, 57, 27);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("전화번호 :");
		lblNewLabel_1_1.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(12, 169, 79, 27);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("이메일 :");
		lblNewLabel_1_1_1.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_1_1_1.setBounds(12, 224, 79, 27);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("주소");
		lblNewLabel_1_1_2.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_1_1_2.setBounds(12, 279, 79, 27);
		contentPane.add(lblNewLabel_1_1_2);
		
		tfId = new JTextField();
		tfId.setBounds(94, 25, 121, 21);
		contentPane.add(tfId);
		tfId.setColumns(10);
		
		tfPw = new JTextField();
		tfPw.setEditable(false);
		tfPw.setColumns(10);
		tfPw.setBounds(94, 68, 179, 21);
		contentPane.add(tfPw);
		
		tfName = new JTextField();
		tfName.setEditable(false);
		tfName.setColumns(10);
		tfName.setBounds(94, 117, 179, 21);
		contentPane.add(tfName);
		
		tfPhone = new JTextField();
		tfPhone.setEditable(false);
		tfPhone.setColumns(10);
		tfPhone.setBounds(94, 172, 179, 21);
		contentPane.add(tfPhone);
		
		tfEmail = new JTextField();
		tfEmail.setEditable(false);
		tfEmail.setColumns(10);
		tfEmail.setBounds(94, 227, 179, 21);
		contentPane.add(tfEmail);
		
		tfAddress = new JTextField();
		tfAddress.setEditable(false);
		tfAddress.setColumns(10);
		tfAddress.setBounds(94, 282, 179, 21);
		contentPane.add(tfAddress);
		contentPane.add(getBtnCheck());
		
		
		
		btnJoin = new JButton("회원가입");
		btnJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			Boolean check = checkField();				//Boolean 받기
			if(check) {
				joinAction();
			}else {
				JOptionPane.showMessageDialog(null, "빈칸을 확인하세요", "경고", JOptionPane.INFORMATION_MESSAGE);
				//tfStartNum.setText("");
			}}
		});
		btnJoin.setEnabled(false);
		btnJoin.setBounds(123, 401, 117, 29);
		contentPane.add(btnJoin);
	}
	
	private JButton getBtnCheck() {
		if (btnCheck == null) {
			btnCheck = new JButton("중복체크");
			btnCheck.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dupCheck();
				}
			});
			btnCheck.setBounds(213, 23, 82, 29);
		}
		return btnCheck;
	}


	
	// ---- Function
	
	
	
	private Boolean checkField() {	// 공백 체크
		boolean check;
		if(tfId.getText().equals("") || tfPw.getText().equals("") || tfName.getText().equals("") || tfPhone.getText().equals("") || tfEmail.getText().equals("") || tfAddress.getText().equals("")) {
			check = false;
		}else {
			check = true;
		}
		return check;
	}
	
	private void joinAction() {
		String uid = tfId.getText();
		String upassword = tfPw.getText();
		String uname = tfName.getText();
		String uphone = tfPhone.getText();
		String uemail = tfEmail.getText();
		String uaddress = tfAddress.getText();
		
		Dao dao = new Dao(uid, upassword, uname, uphone, uemail, uaddress);
		boolean result = dao.joinAction();
		
		if(result) {
			JOptionPane.showMessageDialog(this, "회원가입이 완료되었습니다!");
		}else {
			JOptionPane.showMessageDialog(this, "정보를 확인하세요!");
		}
		
		Main main = new Main();
		main.setVisible(true);
		setVisible(false);
	}
	
	
	//중복체크
	private void dupCheck() {
		String uid = tfId.getText();
		Dao dao = new Dao(uid);
		
		boolean result = dao.dupCheck();
		
		if(result) {
			JOptionPane.showMessageDialog(this, "중복된 아이디 입니다.");
		}else {
			JOptionPane.showMessageDialog(this, "사용가능한 아이디 입니다.");
			tfName.setEditable(true);
			tfPw.setEditable(true);
			tfEmail.setEditable(true);
			tfPhone.setEditable(true);
			tfAddress.setEditable(true);
			btnJoin.setEnabled(true);
		}
		
		
	}

}
