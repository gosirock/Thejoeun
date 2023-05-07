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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

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
	private JComboBox cbEmail;
	private JLabel lblNewLabel_2;

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
		setBounds(100, 100, 355, 477);
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
		tfEmail.setBounds(96, 227, 90, 21);
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
				joinAction();
			}
		});
		btnJoin.setEnabled(false);
		btnJoin.setBounds(123, 401, 117, 29);
		contentPane.add(btnJoin);
		contentPane.add(getCbEmail());
		contentPane.add(getLblNewLabel_2());
	}
	private JComboBox getCbEmail() {
		if (cbEmail == null) {
			cbEmail = new JComboBox();
			cbEmail.setEnabled(false);
			cbEmail.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					emailAction();
				}
			});
			cbEmail.setModel(new DefaultComboBoxModel(new String[] {"naver.com", "daum.net", "gmail.com", "직접입력"}));
			cbEmail.setBounds(213, 226, 136, 27);
		}
		return cbEmail;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("@");
			lblNewLabel_2.setBounds(198, 230, 17, 16);
		}
		return lblNewLabel_2;
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
	
	private void joinAction() {
		String uid = tfId.getText();
		String upassword = tfPw.getText();
		String uname = tfName.getText();
		String uphone = tfPhone.getText();
		String uemail = tfEmail.getText();
		String uemailcb = cbEmail.getSelectedItem().toString();
		String uaddress = tfAddress.getText();
		
		if(uid.isEmpty() || upassword.isEmpty() || uname.isEmpty() || uphone.isEmpty() || uemail.isEmpty() || uemailcb.isEmpty()
				|| uaddress.isEmpty()) {
			JOptionPane.showMessageDialog(this, "회원정보를 입력해주세요");
		}else {
			Dao dao = new Dao(uid, upassword, uname, uphone, uemail+"@"+uemailcb, uaddress);
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
		
		
		
	}
	
	
	//중복체크
	private void dupCheck() {
		String uid = tfId.getText();
		if(uid.isEmpty()) {
			JOptionPane.showMessageDialog(this,"아이디를 입력해주세요.");
		}else {
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
					cbEmail.setEnabled(true);
				}	
			}
	}		
		
	
	private void emailAction() {
		int i = cbEmail.getSelectedIndex();
		String emailselect = "";
		switch(i) {
		case 0:
			cbEmail.setEditable(false);
			break;
		case 1:
			cbEmail.setEditable(false);
			break;
		case 2:
			cbEmail.setEditable(false);
			break;
		case 3:
			cbEmail.setEditable(true);
			break;
		default:
			break;
		
	}
	
}
}