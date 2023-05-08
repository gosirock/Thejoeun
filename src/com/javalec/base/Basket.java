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
import com.javalec.dao.DaoProduct;
import com.javalec.dto.Dto;
import com.javalec.dto.DtoBasket;
import com.javalec.util.ShareVar;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Basket extends JFrame {

	
	// Table
	private final DefaultTableModel outerTable = new DefaultTableModel();
	
	private JPanel contentPane;
	private JTable innerTable;
	private JButton btnSelectDelete;
	private JLabel lblSum;
	private JButton btnM;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Basket frame = new Basket();
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
				sumAction();

			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 435);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 50, 592, 223);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(getInnerTable());
		
		JLabel lblNewLabel = new JLabel("ID :");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel.setBounds(12, 10, 45, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblUserId = new JLabel(ShareVar.loginUserId);
		lblUserId.setFont(new Font("굴림", Font.PLAIN, 15));
		lblUserId.setBounds(48, 10, 108, 15);
		contentPane.add(lblUserId);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("총 구매액 :");
		lblNewLabel_2_1_1_1.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblNewLabel_2_1_1_1.setBounds(35, 312, 121, 18);
		contentPane.add(lblNewLabel_2_1_1_1);
		
		JButton btnReturn = new JButton("돌아가기");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				returnAction();
			}
		});
		btnReturn.setFont(new Font("굴림", Font.PLAIN, 15));
		btnReturn.setBounds(387, 365, 97, 23);
		contentPane.add(btnReturn);
		
		JButton btnAllDelete = new JButton("전체삭제");
		btnAllDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				allDeleteAction();
			}
		});
		btnAllDelete.setFont(new Font("굴림", Font.PLAIN, 15));
		btnAllDelete.setBounds(507, 283, 97, 23);
		contentPane.add(btnAllDelete);
		
		JButton btnBuy = new JButton("구매");
		btnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyAction();
			}
		});
		btnBuy.setFont(new Font("굴림", Font.PLAIN, 15));
		btnBuy.setBounds(507, 365, 97, 23);
		contentPane.add(btnBuy);
		contentPane.add(getBtnSelectDelete());
		contentPane.add(getLblSum());
		
		JButton btnMM = new JButton("");
		btnMM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main main = new Main();
				main.setVisible(true);
				dispose();
				
			}
		});
		btnMM.setBounds(595, 0, 45, 29);
		contentPane.add(btnMM);
	}
	
	
	
	
	
	private JTable getInnerTable() {
		if (innerTable == null) {
			innerTable = new JTable();
			innerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			innerTable.setModel(outerTable);
		}
		return innerTable;
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
	private JLabel getLblSum() {
		if (lblSum == null) {
			lblSum = new JLabel("");
			lblSum.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
			lblSum.setBounds(149, 312, 121, 20);
		}
		return lblSum;
	}

	private JButton getBtnSelectDelete() {
		if (btnSelectDelete == null) {
			btnSelectDelete = new JButton("선택항목삭제");
			btnSelectDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					selectDeleteAction();
				}
			});
			btnSelectDelete.setFont(new Font("Dialog", Font.PLAIN, 15));
			btnSelectDelete.setBounds(387, 285, 108, 23);
		}
		return btnSelectDelete;
	}

	
	private void searchAction() {
		DaoBasket daoBasket = new DaoBasket();
		ArrayList<DtoBasket> dtoList = daoBasket.selectLinst();
		int listCount = dtoList.size();
		
		for(int i = 0; i< listCount; i++) {
			
			String price = Integer.toString(dtoList.get(i).getPpricne());
			String stock = Integer.toString(dtoList.get(i).getBqty());
			int a = dtoList.get(i).getPpricne();
			int b = dtoList.get(i).getBqty();
			
			
			String[] qTxt = {dtoList.get(i).getPid(), dtoList.get(i).getPbrand(), dtoList.get(i).getPname(), price,
					stock};
			outerTable.addRow(qTxt);
			}
			
		}
	
	private void sumAction() {
		DaoBasket daoBasket = new DaoBasket();
		ArrayList<DtoBasket> dtoList = daoBasket.selectLinst();
		int listCount = dtoList.size();
		int sum =0;
		for(int i = 0; i< listCount; i++) {
			String price = Integer.toString(dtoList.get(i).getPpricne());
			String stock = Integer.toString(dtoList.get(i).getBqty());
			int a = dtoList.get(i).getPpricne();
			int b = dtoList.get(i).getBqty();
			sum += a * b;
	}
		lblSum.setText(Integer.toString(sum));
		
		
}
	private void returnAction() {
		
		Buy buy = new Buy();
		buy.setVisible(true);
		dispose();
		
	}
	
	private void allDeleteAction() {
		
		DaoProduct daoProduct = new DaoProduct();
		daoProduct.basketEmptyAction();
		JOptionPane.showMessageDialog(this, "장바구니를 비웠습니다.");
		tableInit();
		searchAction();
		sumAction();
	}
	
	private void selectDeleteAction() {
		
		int i = innerTable.getSelectedRow();
		String wkpid = (String) innerTable.getValueAt(i, 0);
		DaoProduct daoProduct = new DaoProduct(wkpid);
		daoProduct.selectDelete();
		String message = (String)innerTable.getValueAt(i, 2);
		JOptionPane.showMessageDialog(this, message+"제품이 삭제 되었습니다.");
		tableInit();
		searchAction();
		sumAction();
	}
	private void buyAction() {
		
		DaoProduct daoProduct = new DaoProduct();
		daoProduct.buyAction();
		JOptionPane.showMessageDialog(this, "상품을 구매했습니다. 감사합니다.");
		tableInit();
		searchAction();
		sumAction();
	}
}
