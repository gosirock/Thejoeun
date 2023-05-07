package com.javalec.base;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.javalec.dao.Dao;
import com.javalec.dao.DaoProduct;
import com.javalec.dao.DaoConditionList;
import com.javalec.dto.Dto;
import com.javalec.util.ShareVar;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Buy extends JFrame {

	private JPanel contentPane;
	
	// 유저아이디
	
	// Table
	
	private final DefaultTableModel outerTable = new DefaultTableModel();
	private JTextField tfBrand;
	private JTable innerTable;
	private JTextField tfName;
	private JTextField tfqty;
	private JTextField tfPrice;
	private JLabel lblImage;
	private JButton btnBuygo;
	private JComboBox cbSelection;
	private JButton btnQuery;
	private JTextField tfSelection;

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
	public Buy() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				//윈도우 열렸을때\
				tableInit();
				searchAction();
				
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 508, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 50, 482, 223);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(getInnerTable());

		JLabel lblNewLabel = new JLabel("ID :");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel.setBounds(12, 10, 42, 15);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_2 = new JLabel("브랜드 :");
		lblNewLabel_2.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(238, 303, 81, 18);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("상품명 :");
		lblNewLabel_2_1.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_2_1.setBounds(238, 357, 81, 18);
		contentPane.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_1_1 = new JLabel("구매수량 :");
		lblNewLabel_2_1_1.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_2_1_1.setBounds(238, 463, 81, 18);
		contentPane.add(lblNewLabel_2_1_1);

		JLabel lblNewLabel_2_1_1_1 = new JLabel("가격 :");
		lblNewLabel_2_1_1_1.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_2_1_1_1.setBounds(238, 404, 81, 18);
		contentPane.add(lblNewLabel_2_1_1_1);

		JButton btnBasket = new JButton("담기");
		btnBasket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				basketAction();
			}
		});
		btnBasket.setFont(new Font("굴림", Font.PLAIN, 15));
		btnBasket.setBounds(280, 519, 97, 23);
		contentPane.add(btnBasket);
		contentPane.add(getTfBrand());
		
		tfName = new JTextField();
		tfName.setEditable(false);
		tfName.setColumns(10);
		tfName.setBounds(315, 354, 130, 26);
		contentPane.add(tfName);
		
		tfqty = new JTextField();
		tfqty.setColumns(10);
		tfqty.setBounds(315, 460, 130, 26);
		contentPane.add(tfqty);
		
		tfPrice = new JTextField();
		tfPrice.setEditable(false);
		tfPrice.setColumns(10);
		tfPrice.setBounds(315, 401, 130, 26);
		contentPane.add(tfPrice);
		contentPane.add(getLblImage());
		
		JLabel lblUserId = new JLabel(ShareVar.loginUserId);
		lblUserId.setBounds(52, 10, 109, 16);
		contentPane.add(lblUserId);
		contentPane.add(getBtnBuygo());
		contentPane.add(getCbSelection());
		contentPane.add(getBtnQuery());
		contentPane.add(getTfSelection());
	}
	
	private JLabel getLblImage() {
		if (lblImage == null) {
			lblImage = new JLabel("");
			lblImage.setBounds(12, 285, 213, 240);
		}
		return lblImage;
	}
	private JTextField getTfBrand() {
		if (tfBrand == null) {
			tfBrand = new JTextField();
			tfBrand.setEditable(false);
			tfBrand.setBounds(315, 303, 130, 26);
			tfBrand.setColumns(10);
		}
		return tfBrand;
	}
	private JTable getInnerTable() {
		if (innerTable == null) {
			innerTable = new JTable();
			innerTable.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					tableClick();
					
				}
			});
			innerTable.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			innerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			innerTable.setModel(outerTable);
		}
		return innerTable;
	}
	private JComboBox getCbSelection() {
		if (cbSelection == null) {
			cbSelection = new JComboBox();
			cbSelection.setModel(new DefaultComboBoxModel(new String[] {"브랜드", "제품명"}));
			cbSelection.setBounds(173, 11, 108, 27);
		}
		return cbSelection;
	}
	private JButton getBtnBuygo() {
		if (btnBuygo == null) {
			btnBuygo = new JButton("구매화면");
			btnBuygo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buyAction();
				}
			});
			btnBuygo.setFont(new Font("Dialog", Font.PLAIN, 15));
			btnBuygo.setBounds(397, 517, 97, 23);
		}
		return btnBuygo;
	}
	private JButton getBtnQuery() {
		if (btnQuery == null) {
			btnQuery = new JButton("검색");
			btnQuery.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					conditionQuery();
				}
			});
			btnQuery.setBounds(424, 9, 70, 29);
		}
		return btnQuery;
	}
	private JTextField getTfSelection() {
		if (tfSelection == null) {
			tfSelection = new JTextField();
			tfSelection.setBounds(293, 12, 130, 26);
			tfSelection.setColumns(10);
		}
		return tfSelection;
	}

	
	
	// function ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	
	private void tableInit() {
		
		
		outerTable.addColumn("id");
		outerTable.addColumn("브랜드");
		outerTable.addColumn("제품명");
		outerTable.addColumn("가격");
		outerTable.addColumn("재고");
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
		Dao dao = new Dao();
		ArrayList<Dto> dtoList = dao.selectLinst();
		int listCount = dtoList.size();
		
		for(int i = 0; i< listCount; i++) {
			
			String price = Integer.toString(dtoList.get(i).getPprice());
			String stock = Integer.toString(dtoList.get(i).getPstock());
			
			String[] qTxt = {dtoList.get(i).getPid(), dtoList.get(i).getPbrand(), dtoList.get(i).getPname(), price,
					stock};
			outerTable.addRow(qTxt);
			}
			
		}
	
	// 테이블 클릭

	private void tableClick() {
		
		int i = innerTable.getSelectedRow();
		String wkpid = (String) innerTable.getValueAt(i, 0);
		
		DaoProduct dao = new DaoProduct(wkpid);
		Dto dto = dao.tableClick();
		
		tfBrand.setText(dto.getPbrand());
		tfName.setText(dto.getPname());
		tfPrice.setText(Integer.toString(dto.getPprice()));
		
		String imagefile = dto.getPimagename();
		
		ImageIcon imgicon = new ImageIcon(imagefile);
		Image img = imgicon.getImage();
		
		Image updateImg = img.getScaledInstance(213, 240, Image.SCALE_SMOOTH);
		lblImage.setIcon(new ImageIcon(updateImg));
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		
		File file = new File(imagefile);
		file.delete();
		
	}
private void clearColumn() {
		
		tfBrand.setText("");
		tfName.setText("");
		tfPrice.setText("");
		tfqty.setText("");
		
	}
	// 담기
	private void basketAction() {
		
		int i = innerTable.getSelectedRow();
		String baspid = (String) innerTable.getValueAt(i, 0);
		String basqty = tfqty.getText();
		
		DaoProduct dao = new DaoProduct(baspid, basqty);
		boolean result = dao.basketAction();
		
		if(result) {
			JOptionPane.showMessageDialog(this, "해당상품을 장바구니에 담았습니다");
			tfqty.setText(null);
		}else {
			JOptionPane.showMessageDialog(this, "장바구니를 비워주세요");
		}
	}
	
	private void buyAction() {
		
		Basket basket = new Basket();
		basket.setVisible(true);
		dispose();

	}
	
	private void conditionQuery() {
		int i = cbSelection.getSelectedIndex();
		String conditionQueryColumn = "";
		switch(i) {
		case 0:
			conditionQueryColumn = "pbrand";
			break;
		case 1:
			conditionQueryColumn = "pname";
			break;
		default:
			break;
		
	}
		tableInit();
		conditionQueryAction(conditionQueryColumn);
		clearColumn();
	}
	
	private void conditionQueryAction(String conditionQueryColumn) {
		
		DaoConditionList dao = new DaoConditionList(conditionQueryColumn, tfSelection.getText()); 
		ArrayList<Dto> dtoList = dao.conditionList();
		int listCount = dtoList.size();
		
		for(int i = 0; i < listCount; i++) {
			String price = Integer.toString(dtoList.get(i).getPprice());
			String stock = Integer.toString(dtoList.get(i).getPstock());
			
			String[] qTxt = {dtoList.get(i).getPid(), dtoList.get(i).getPbrand(), dtoList.get(i).getPname(), price,
					stock};
			outerTable.addRow(qTxt);
		}

	}
	
	
}
