package com.javalec.base;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.javalec.dao.Dao;
import com.javalec.dao.DaoBasket;
import com.javalec.dto.Dto;
import com.javalec.util.ShareVar;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Basket extends JFrame {

	
	// Table
	private final DefaultTableModel outerTable = new DefaultTableModel();
	
	private JPanel contentPane;
	private JTable innerTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Buy frame = new Buy();
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
	public Basket() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
			
				tableInit();
				searchAction();

			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 50, 592, 223);
		contentPane.add(scrollPane);
		
		innerTable = new JTable();
		innerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(innerTable);
		
		JLabel lblNewLabel = new JLabel("ID :");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel.setBounds(12, 10, 45, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblUserId = new JLabel(ShareVar.loginUserId);
		lblUserId.setFont(new Font("굴림", Font.PLAIN, 15));
		lblUserId.setBounds(48, 10, 108, 15);
		contentPane.add(lblUserId);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("총 구매액 :");
		lblNewLabel_2_1_1_1.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_2_1_1_1.setBounds(34, 499, 81, 18);
		contentPane.add(lblNewLabel_2_1_1_1);
		
		JButton btnNewButton = new JButton("돌아가기");
		btnNewButton.setFont(new Font("굴림", Font.PLAIN, 15));
		btnNewButton.setBounds(374, 497, 97, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("전체삭제");
		btnNewButton_1.setFont(new Font("굴림", Font.PLAIN, 15));
		btnNewButton_1.setBounds(507, 283, 97, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("구매");
		btnNewButton_2.setFont(new Font("굴림", Font.PLAIN, 15));
		btnNewButton_2.setBounds(507, 497, 97, 23);
		contentPane.add(btnNewButton_2);
	}
	
	
	
	private void tableInit() {
		
		
		outerTable.addColumn("id");
		outerTable.addColumn("브랜드");
		outerTable.addColumn("제품명");
		outerTable.addColumn("가격");
		outerTable.addColumn("선택수량");
		outerTable.setColumnCount(5);
		
		int i = outerTable.getRowCount();
		for(int j=0; j<i; j++) {
			outerTable.removeRow(0);
		}
		
		innerTable.setAutoResizeMode(innerTable.AUTO_RESIZE_OFF);
		
		int vColindex = 0;
		TableColumn col = innerTable.getColumnModel().getColumn(vColindex);
		int width = 50;
		col.setPreferredWidth(width);
		
		
		vColindex = 1;
		col = innerTable.getColumnModel().getColumn(vColindex);
		width = 100;
		col.setPreferredWidth(width);
				
		vColindex = 2;
		col = innerTable.getColumnModel().getColumn(vColindex);
		width = 100;
		col.setPreferredWidth(width);
		
		vColindex = 3;
		col = innerTable.getColumnModel().getColumn(vColindex);
		width = 100;
		col.setPreferredWidth(width);
		
		vColindex = 4;
		col = innerTable.getColumnModel().getColumn(vColindex);
		width = 100;
		col.setPreferredWidth(width);
	}

	// 테이블 정보 넣기
	private void searchAction() {
		DaoBasket daoBasket = new DaoBasket();
		ArrayList<Dto> dtoList = daoBasket.selectLinst();
		int listCount = dtoList.size();
		
		for(int i = 0; i< listCount; i++) {
			
			String price = Integer.toString(dtoList.get(i).getPprice());
			String stock = Integer.toString(dtoList.get(i).getPstock());
			
			String[] qTxt = {dtoList.get(i).getPid(), dtoList.get(i).getPbrand(), dtoList.get(i).getPname(), price,
					stock};
			outerTable.addRow(qTxt);
			}
			
		}
}
