package com.javalec.base;

import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.javalec.dao.Dao;
import com.javalec.util.ShareVar;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Main extends JFrame {

	private JPanel contentPane;
	private JTextField tfId;
	private JPasswordField tfPw;
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 266, 214);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID :");
		lblNewLabel.setBounds(18, 42, 57, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setBounds(18, 81, 89, 15);
		contentPane.add(lblPassword);
		
		tfId = new JTextField();
		tfId.setBounds(116, 39, 116, 21);
		contentPane.add(tfId);
		tfId.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		btnOk.setBounds(135, 125, 97, 23);
		contentPane.add(btnOk);
		
		JButton btnJoin = new JButton("회원가입");
		btnJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				join();
			}
		});
		btnJoin.setBounds(18, 125, 97, 23);
		contentPane.add(btnJoin);
		contentPane.add(getTfPw());
	}
	private JPasswordField getTfPw() {
		if (tfPw == null) {
			tfPw = new JPasswordField();
			tfPw.setBounds(119, 75, 113, 26);
		}
		return tfPw;
	}
// --- Function
	
	private void join() {
		
		Join join = new Join();
		join.setVisible(true);
		setVisible(false);
	}

	private void login() {
		String uid = tfId.getText();
		String upassword = tfPw.getText();
		
		if(uid.equals("admin") && upassword.equals("1234")) {
			Admin admin = new Admin();
			admin.setVisible(true);
			dispose();
		}else {

			Dao dao = new Dao(uid, upassword);
			
			boolean result = dao.logincheck();
			
			if(result == true) {
				ShareVar.loginUserId = uid;
				JOptionPane.showMessageDialog(this, uid+"님 환영합니다");
				Buy buy = new Buy();
				buy.setVisible(true);
				dispose();
				
			}else {
				JOptionPane.showMessageDialog(this, "ID, PassWord를 확인하세요");
			}
		}
	
		}
		
			
}

