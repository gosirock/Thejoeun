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
import javax.swing.SwingConstants;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Basket extends JFrame {

	// Table
	private final DefaultTableModel outerTable = new DefaultTableModel();

	private JPanel contentPane;
	private JTable innerTable;
	private JButton btnNewButton_1_1;

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
		lblNewLabel_2_1_1_1.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_2_1_1_1.setBounds(34, 499, 81, 18);
		contentPane.add(lblNewLabel_2_1_1_1);

		JButton btnNewButton = new JButton("돌아가기");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backAction();
			}
		});
		btnNewButton.setFont(new Font("굴림", Font.PLAIN, 15));
		btnNewButton.setBounds(374, 497, 97, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("전체삭제");
		btnNewButton_1.setFont(new Font("굴림", Font.PLAIN, 15));
		btnNewButton_1.setBounds(507, 283, 97, 23);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("구매");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				purchasesAction();
				JOptionPane.showMessageDialog(null, "구매가 완료되었습니다");
			}
		});
		btnNewButton_2.setFont(new Font("굴림", Font.PLAIN, 15));
		btnNewButton_2.setBounds(507, 497, 97, 23);
		contentPane.add(btnNewButton_2);

		JLabel lblsum = new JLabel(Integer.toString(sumAction()));
		lblsum.setBounds(147, 501, 108, 16);
		contentPane.add(lblsum);
		contentPane.add(getBtnNewButton_1_1());
	}

	private JButton getBtnNewButton_1_1() {
		if (btnNewButton_1_1 == null) {
			btnNewButton_1_1 = new JButton("선택삭제");
			btnNewButton_1_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//deleteAction();
				}
			});
			btnNewButton_1_1.setFont(new Font("굴림", Font.PLAIN, 15));
			btnNewButton_1_1.setBounds(398, 283, 97, 23);
		}
		return btnNewButton_1_1;
	}

	private JTable getInnerTable() {
		if (innerTable == null) {
			innerTable = new JTable();
			innerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			innerTable.setModel(outerTable);
		}
		return innerTable;
	}

	// ----- function -----
	private void tableInit() {

		outerTable.addColumn("id");
		outerTable.addColumn("브랜드");
		outerTable.addColumn("제품명");
		outerTable.addColumn("가격");
		outerTable.addColumn("선택수량");
		outerTable.setColumnCount(5);

		int i = outerTable.getRowCount();
		for (int j = 0; j < i; j++) {
			outerTable.removeRow(0);
		}

		innerTable.setAutoResizeMode(innerTable.AUTO_RESIZE_OFF);
		
	

		int vColindex = 0;
		TableColumn col = innerTable.getColumnModel().getColumn(vColindex);
		int width = 80;
		col.setPreferredWidth(width);

		vColindex = 1;
		col = innerTable.getColumnModel().getColumn(vColindex);
		width = 155;
		col.setPreferredWidth(width);

		vColindex = 2;
		col = innerTable.getColumnModel().getColumn(vColindex);
		width = 155;
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

	private void searchAction() {

		DaoBasket daoBasket = new DaoBasket();
		ArrayList<Dto> buyLinst = daoBasket.buyLinst();
		int listCount = buyLinst.size();
		
		for(int i = 0; i< listCount; i++) {
			
			String price = Integer.toString(buyLinst.get(i).getPprice());
			String qty = Integer.toString(buyLinst.get(i).getBqty());
			int a = buyLinst.get(i).getPprice();
			int b = buyLinst.get(i).getBqty();
			
			
			String[] qTxt = {buyLinst.get(i).getPid(), buyLinst.get(i).getPbrand(), buyLinst.get(i).getPname(), price,
					qty};
			outerTable.addRow(qTxt);
			}

	}

	private int sumAction() {
		DaoBasket daoBasket = new DaoBasket();
		ArrayList<DtoBasket> dtoList = daoBasket.selectLinst();
		int listCount = dtoList.size();
		int sum = 0;
		for (int i = 0; i < listCount; i++) {
			String price = Integer.toString(dtoList.get(i).getPpricne());
			String stock = Integer.toString(dtoList.get(i).getBqty());
			int a = dtoList.get(i).getPpricne();
			int b = dtoList.get(i).getBqty();
			sum += a * b;

		}
		return sum;
	}

//	private void adminOpen() {
//		Admin admin = new Admin();
//		admin.setVisible(true);
//		dispose();
//	}

	private void backAction() {
		Buy buy = new Buy();
		buy.main(null);
		dispose();
	}
	
	private void purchasesAction() {
		int i = innerTable.getSelectedRow();
		//String baspid = (String) innerTable.getValueAt(i, 0);
		//String basqty = tfqty.getText();
		
		DaoBasket daoBasket = new DaoBasket();

		//ArrayList<DtoBasket> dtoList = daoBasket.selectLinst();
		
		//DaoProduct daoProduct  = new DaoProduct();
		//DaoProduct dao = new DaoProduct();
		boolean result = daoBasket.purchasesAction();
		
		
	}

//	private void tableClick() {
//
//		int i = innerTable.getSelectedRow();
//		String wkpid = (String) innerTable.getValueAt(i, 0);
//
//		DaoBasket daoBasket = new DaoBasket(wkpid);
//		Dto dto = daoBasket.tableClick();
//		innerTable.setEnabled(false);

		// String imagefile = dto.getPimagename();

//		lblImage.setIcon(new ImageIcon(imagefile));
//		lblImage.setHorizontalAlignment(SwingConstants.CENTER);

//		File file = new File(imagefile);
//		file.delete();

	}

//	private void deleteAction() {
//		// 테이블에서 선택한 행 삭제
//        DefaultTableModel model = (DefaultTableModel) table.getModel();
//        model.removeRow(deleteAction());
//    }
//}
