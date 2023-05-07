package com.javalec.base;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.javalec.dao.DaoAdmin;
import com.javalec.dao.DaoAdminUpdate;
import com.javalec.dao.DaoConditionList;
import com.javalec.dto.Dto;
import com.javalec.dto.DtoAdmin;
import com.javalec.util.ShareVar;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Admin extends JFrame {

	private JPanel contentPane;
	private JTable innerTable;
	private JRadioButton rbInsert;
	private JRadioButton rbUpdate;
	private JRadioButton rbDelete;
	private JTextField tfBrand;
	private JTextField tfName;
	private JTextField tfStock;
	private JTextField tfPrice;
	private JRadioButton rbQuery;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblNewLabel_2_1_1_1_1;
	private JTextField tfFilePath;
	private JLabel lblNewLabel_2_2;
	private JTextField tfID;
	
	
	private final DefaultTableModel outerTable = new DefaultTableModel();
	private JButton btnOK;
	
	
	String message = "";
	private JLabel lblNewLabel_1;
	private JLabel lblImage;
	private JButton btnFilePath;
	private JButton btnQuery;
	private JComboBox cbSelection;
	private JTextField tfSelection;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin frame = new Admin();
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
	public Admin() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				tableInit();  // 테이블초기화
				searchAction();  // 데이터불러오기
				screenPartition(); //  radiobtn 이 눌러진 상태로 textfield 화면표시해주기
				
			}
		});
		setTitle("관리자모드");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.setBounds(12, 72, 592, 223);
		contentPane.add(scrollPane);
		
		innerTable = new JTable();
		innerTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnOK.setEnabled(true);  // 테이블누를때 클릭실행가능
				tableClick();
				
			}
		});
		scrollPane.setViewportView(innerTable);
		innerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		innerTable.setModel(outerTable);
		
		JLabel lblNewLabel = new JLabel("ID :");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel.setBounds(12, 10, 24, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblUserId = new JLabel("");
		lblUserId.setFont(new Font("굴림", Font.PLAIN, 15));
		lblUserId.setBounds(48, 10, 108, 15);
		contentPane.add(lblUserId);
		
		JLabel lblNewLabel_2 = new JLabel("브랜드 :");
		lblNewLabel_2.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(238, 338, 81, 18);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("상품명 :");
		lblNewLabel_2_1.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_2_1.setBounds(238, 366, 81, 18);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("재고 :");
		lblNewLabel_2_1_1.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_2_1_1.setBounds(238, 394, 81, 18);
		contentPane.add(lblNewLabel_2_1_1);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("가격 :");
		lblNewLabel_2_1_1_1.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_2_1_1_1.setBounds(238, 422, 81, 18);
		
		contentPane.add(lblNewLabel_2_1_1_1);
		contentPane.add(getRbInsert());
		contentPane.add(getRbUpdate());
		contentPane.add(getRbDelete());
		contentPane.add(getTfBrand());
		contentPane.add(getTfName());
		contentPane.add(getTfStock());
		contentPane.add(getTfPrice());
		contentPane.add(getRbQuery());
		contentPane.add(getLblNewLabel_2_1_1_1_1());
		contentPane.add(getTfFilePath());
		contentPane.add(getLblNewLabel_2_2());
		contentPane.add(getTfID());
		contentPane.add(getBtnOK());
		
		
		contentPane.add(getLblImage());
		contentPane.add(getBtnFilePath());
		contentPane.add(getBtnQuery());
		contentPane.add(getCbSelection());
		contentPane.add(getTfSelection());
	}
	private JRadioButton getRbInsert() {
		if (rbInsert == null) {
			rbInsert = new JRadioButton("입력");
			rbInsert.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					screenPartition();
				}
			});
			buttonGroup.add(rbInsert);
			rbInsert.setBounds(8, 21, 61, 23);
		}
		return rbInsert;
	}
	private JRadioButton getRbUpdate() {
		if (rbUpdate == null) {
			rbUpdate = new JRadioButton("수정");
			rbUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					screenPartition();
				}
			});
			buttonGroup.add(rbUpdate);
			rbUpdate.setBounds(73, 21, 61, 23);
		}
		return rbUpdate;
	}
	private JRadioButton getRbDelete() {
		if (rbDelete == null) {
			rbDelete = new JRadioButton("삭제");
			rbDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					screenPartition();
				}
			});
			buttonGroup.add(rbDelete);
			rbDelete.setBounds(138, 21, 61, 23);
		}
		return rbDelete;
	}
	private JTextField getTfBrand() {
		if (tfBrand == null) {
			tfBrand = new JTextField();
			tfBrand.setBounds(346, 337, 116, 21);
			tfBrand.setColumns(10);
		}
		return tfBrand;
	}
	private JTextField getTfName() {
		if (tfName == null) {
			tfName = new JTextField();
			tfName.setColumns(10);
			tfName.setBounds(346, 365, 116, 21);
		}
		return tfName;
	}
	private JTextField getTfStock() {
		if (tfStock == null) {
			tfStock = new JTextField();
			tfStock.setColumns(10);
			tfStock.setBounds(346, 393, 116, 21);
		}
		return tfStock;
	}
	private JTextField getTfPrice() {
		if (tfPrice == null) {
			tfPrice = new JTextField();
			tfPrice.setColumns(10);
			tfPrice.setBounds(346, 421, 116, 21);
		}
		return tfPrice;
	}
	private JRadioButton getRbQuery() {
		if (rbQuery == null) {
			rbQuery = new JRadioButton("검색");
			rbQuery.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					screenPartition();
				}
			});
			rbQuery.setSelected(true);
			buttonGroup.add(rbQuery);
			rbQuery.setBounds(203, 21, 61, 23);
		}
		return rbQuery;
	}
	private JLabel getLblNewLabel_2_1_1_1_1() {
		if (lblNewLabel_2_1_1_1_1 == null) {
			lblNewLabel_2_1_1_1_1 = new JLabel("파일경로 :");
			lblNewLabel_2_1_1_1_1.setFont(new Font("굴림", Font.PLAIN, 15));
			lblNewLabel_2_1_1_1_1.setBounds(238, 451, 81, 18);
		}
		return lblNewLabel_2_1_1_1_1;
	}
	private JTextField getTfFilePath() {
		if (tfFilePath == null) {
			tfFilePath = new JTextField();
			tfFilePath.setColumns(10);
			tfFilePath.setBounds(346, 450, 232, 21);
		}
		return tfFilePath;
	}
	private JLabel getLblNewLabel_2_2() {
		if (lblNewLabel_2_2 == null) {
			lblNewLabel_2_2 = new JLabel("상품ID :");
			lblNewLabel_2_2.setFont(new Font("굴림", Font.PLAIN, 15));
			lblNewLabel_2_2.setBounds(238, 306, 81, 18);
		}
		return lblNewLabel_2_2;
	}
	private JTextField getTfID() {
		if (tfID == null) {
			tfID = new JTextField();
			tfID.setColumns(10);
			tfID.setBounds(346, 305, 116, 21);
		}
		return tfID;
	}
	
	private JButton getBtnOK() {
		if (btnOK == null) {
			btnOK = new JButton("OK");
			btnOK.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPartition();
				}
			});
			btnOK.setBounds(481, 507, 97, 23);
		}
		return btnOK;
	}
	

	private JButton getBtnFilePath() {
		if (btnFilePath == null) {
			btnFilePath = new JButton("Load");
			btnFilePath.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					filePath();
				}
			});
			btnFilePath.setBounds(481, 470, 97, 23);
		}
		return btnFilePath;
	}
	
	private JLabel getLblImage() {
		if (lblImage == null) {
			lblImage = new JLabel("");
			lblImage.setBounds(24, 322, 148, 162);
		}
		return lblImage;
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// function
	
private void tableClick() {  //
		
		if(rbUpdate.isSelected()) {
			tfName.setEditable(true);
			tfBrand.setEditable(true);
			tfID.setEditable(false);
			tfStock.setEditable(true);
			tfPrice.setEditable(true);
			tfFilePath.setEditable(true);
			btnOK.setVisible(true);
			btnOK.setEnabled(true);
		}
		
		
		if(rbDelete.isSelected()) {
			tfName.setEditable(false);
			tfBrand.setEditable(false);
			tfID.setEditable(false);
			tfStock.setEditable(false);
			tfPrice.setEditable(false);
			tfFilePath.setEditable(false);
			btnOK.setVisible(true);
			btnOK.setEnabled(true);
		}
		
		if (rbInsert.isSelected()) {
			tfName.setEditable(true);
			tfBrand.setEditable(true);
			tfID.setEditable(true);
			tfStock.setEditable(true);
			tfPrice.setEditable(true);
			tfFilePath.setEditable(true);
			btnOK.setVisible(true);
			btnOK.setEnabled(true);
		}
		
		int i = innerTable.getSelectedRow();
		String wkSequence = (String) innerTable.getValueAt(i, 0);
		//int wkSeqno = Integer.parseInt(wkSequence);
		
		// Dao 에 의뢰한다.
		DaoAdmin dao = new DaoAdmin(wkSequence);
		DtoAdmin dto = dao.tableClick();
		
		tfID.setText(dto.getPid());
		tfBrand.setText(dto.getPbrand());
		tfName.setText(dto.getPname());
		tfStock.setText(Integer.toString(dto.getPstock()));
		tfPrice.setText(Integer.toString(dto.getPprice()));
		//tfFilePath.setText(dto.getPimagename());
		
		
		// Image 처리
		String filePath = dto.getPimagename();
		
		lblImage.setIcon(new ImageIcon(filePath));
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);  // label의 중앙에 파일 위치
		
		File file = new File(filePath);
		file.delete();
		
		
		
	}












private void tableInit() {
	outerTable.addColumn("상품 ID");
	outerTable.addColumn("브랜드명");
	outerTable.addColumn("상품명");
	outerTable.addColumn("재고");
	outerTable.addColumn("가격");
	outerTable.setColumnCount(5);
	
	int i = outerTable.getRowCount();
	for(int j = 0 ; j < i ; j++) {
		outerTable.removeRow(0);
	}
	
	innerTable.setAutoResizeMode(innerTable.AUTO_RESIZE_OFF);
	
	int vColIndex = 0;
	TableColumn col = innerTable.getColumnModel().getColumn(vColIndex);
	int width = 50;
	col.setPreferredWidth(width);
	
	vColIndex = 1;
	col = innerTable.getColumnModel().getColumn(vColIndex);
	width = 150;
	col.setPreferredWidth(width);
	
	vColIndex = 2;
	col = innerTable.getColumnModel().getColumn(vColIndex);
	width = 150;
	col.setPreferredWidth(width);
	
	vColIndex = 3;
	col = innerTable.getColumnModel().getColumn(vColIndex);
	width = 50;
	col.setPreferredWidth(width);
	
	vColIndex = 4;
	col = innerTable.getColumnModel().getColumn(vColIndex);
	width = 200;
	col.setPreferredWidth(width);
}










// DB의 table에서 화면의 table에 들어갈 정보 가져오기 메서드
private void searchAction() {
	DaoAdmin dao = new DaoAdmin();
	//dao.selectList();   // Dao 에서 return 을 준다  받아야함
	ArrayList<DtoAdmin> dtoList = dao.selectList();  // 받아야 하므로 Dto type의 dtoList를 변수로 받는다
	int listCount = dtoList.size();
	
	for( int i = 0; i < listCount ; i++) {
		String temp = dtoList.get(i).getPid();
		String[] qTxt = {temp, dtoList.get(i).getPbrand(), dtoList.get(i).getPname(),Integer.toString(dtoList.get(i).getPstock()),Integer.toString(dtoList.get(i).getPprice())};
		outerTable.addRow(qTxt);  // 화면에 데이터 넣어주기
		
	}
	//tfCount.setText(Integer.toString(listCount));
	
}













private void screenPartition() {
	
	// 검색의 경우
	if (rbQuery.isSelected()) {
		tfName.setEditable(false);
		tfBrand.setEditable(false);
		tfID.setEditable(false);
		tfStock.setEditable(false);
		tfPrice.setEditable(false);
		tfFilePath.setEditable(false);
		btnOK.setVisible(false);
		btnOK.setEnabled(false);
	}

	// 입력의 경우
	
	if (rbInsert.isSelected()) {
		tfName.setEditable(true);
		tfBrand.setEditable(true);
		tfID.setEditable(true);
		tfStock.setEditable(true);
		tfPrice.setEditable(true);
		tfFilePath.setEditable(true);
		btnOK.setVisible(true);
		btnOK.setEnabled(true);
	}
	
	// 수정의 경우
	if 	(rbUpdate.isSelected()) {
		tfName.setEditable(false);
		tfBrand.setEditable(false);
		tfID.setEditable(false);
		tfStock.setEditable(false);
		tfPrice.setEditable(false);
		tfFilePath.setEditable(false);
		btnOK.setVisible(true);
		btnOK.setEnabled(false);
	}
	
	// 삭제의 경우
	if 	(rbDelete.isSelected() ) {
		tfName.setEditable(false);
		tfBrand.setEditable(false);
		tfID.setEditable(false);
		tfStock.setEditable(false);
		tfPrice.setEditable(false);
		tfFilePath.setEditable(false);
		btnOK.setVisible(true);
		btnOK.setEnabled(false);
	}
	

}
















private void actionPartition() {
	// 입력의 경우 OK 버튼을 눌렀을 때
	if (rbInsert.isSelected()) {
		int i_chk = insertFieldCheck();
		if (i_chk == 0) {
			insertAction();
			System.out.println("****");
			tableInit();
			searchAction();
			clearColumn();
			
		}else {
			JOptionPane.showMessageDialog(this, "\n"+message+ "입력하세요!", "주소록 정보",JOptionPane.INFORMATION_MESSAGE); //this 는 active 창에 띄우고 null은 화면아무데나 중앙에 띄워라
		} 
	}
	
	// 수정의 경우 OK 버튼을 눌렀을 때
	if (rbUpdate.isSelected()) {
		int i_chk = insertFieldCheck();
		if (i_chk == 0) {
			updateAction();
			tableInit();
			searchAction();
			clearColumn();
			
		}else {
		JOptionPane.showMessageDialog(this, "주소록 정보 수정\n"+message+ "입력하세요!", "주소록 정보",JOptionPane.INFORMATION_MESSAGE); //this 는 active 창에 띄우고 null은 화면아무데나 중앙에 띄워라
		} 
	}
	// 삭제의 경우 OK 버튼을 눌렀을때
	if (rbDelete.isSelected()) {
		int i_chk = insertFieldCheck();
		if (i_chk == 0) {
			deleteAction();
			tableInit();
			searchAction();
			clearColumn();
			
		}else {
			JOptionPane.showMessageDialog(this, "주소록 정보 삭제\n"+message+ "입력하세요!", "주소록 정보",JOptionPane.INFORMATION_MESSAGE); //this 는 active 창에 띄우고 null은 화면아무데나 중앙에 띄워라
		} 
	}
	
	
}













private int insertFieldCheck() {
	int i = 0;
	
	
	if(tfID.getText().trim().length() == 0) {
		i++;
		message = "상품ID를 ";
		tfID.requestFocus();  // 어디 자리가 비었는지 커서로 보여
	}
	if(tfBrand.getText().trim().length() == 0) {
		i++;
		message = "브랜드를 ";
		tfBrand.requestFocus();
	}
	if(tfName.getText().trim().length() == 0) {
		i++;
		message = "상품명을 ";
		tfName.requestFocus();
	}
	if(tfStock.getText().trim().length() == 0) {
		i++;
		message = "재고를 ";
		tfStock.requestFocus();
	}
	if(tfPrice.getText().trim().length() == 0) {
		i++;
		message = "가격을 ";
		tfPrice.requestFocus();
	}
	return i;
	
}














private void insertAction() {
	String id = tfID.getText();
	String brand = tfBrand.getText();
	String name = tfName.getText();
	int stock = Integer.parseInt(tfStock.getText());
	int price = Integer.parseInt(tfPrice.getText());
	
	
	// Image File
	FileInputStream input = null;
	File file = new File(tfFilePath.getText());
	try {
		input = new FileInputStream(file);
	}catch(Exception e){
		e.printStackTrace();
		
	}
	
	
	DaoAdmin dao = new DaoAdmin(id, brand, name, stock, price, input);
	boolean result = dao.insertAction(); 
	
	if (result) {
		JOptionPane.showMessageDialog(this, "정보 입력\n"+tfName.getText()+ "님의 정보가 입력되었습니다.", "주소록 정보",JOptionPane.INFORMATION_MESSAGE); //this 는 active 창에 띄우고 null은 화면아무데나 중앙에 띄워라
	}else {
		JOptionPane.showMessageDialog(this, "정보 입력\n"+ "입력 중 문제가 발생했습니다.", "주소록 정보",JOptionPane.ERROR_MESSAGE); //this 는 active 창에 띄우고 null은 화면아무데나 중앙에 띄워라
	}
	
}













private void clearColumn() {
	tfID.setText("");
	tfName.setText("");
	tfBrand.setText("");
	tfStock.setText("");
	tfPrice.setText("");
	tfFilePath.setText("");
}
















private void deleteAction() {
	String id = tfID.getText();
	String brand = tfBrand.getText();
	String name = tfName.getText();
	int stock = Integer.parseInt(tfStock.getText());
	int price = Integer.parseInt(tfPrice.getText());
	String filepath = tfFilePath.getText();
	
	DaoAdmin dao = new DaoAdmin(id);
	boolean result = dao.deleteAction();
	
	if (result) {
		JOptionPane.showMessageDialog(this, "정보 삭제\n"+tfName.getText()+ "님의 정보가 삭제되었습니다.", "주소록 정보",JOptionPane.INFORMATION_MESSAGE); //this 는 active 창에 띄우고 null은 화면아무데나 중앙에 띄워라
	}else {
		JOptionPane.showMessageDialog(this, "정보 삭제\n"+ "삭제 중 문제가 발생했습니다.", "주소록 정보",JOptionPane.ERROR_MESSAGE); //this 는 active 창에 띄우고 null은 화면아무데나 중앙에 띄워라
	}
	
}

		
	
















private void updateAction() {
	String id = tfID.getText();
	String brand = tfBrand.getText();
	String name = tfName.getText();
	int stock = Integer.parseInt(tfStock.getText());
	int price = Integer.parseInt(tfPrice.getText());
	String filepath = tfFilePath.getText();
	
	DaoAdminUpdate dao = new DaoAdminUpdate(id, brand, name, stock, price, filepath);
	boolean result = dao.updateAction();
	
	if (result) {
		JOptionPane.showMessageDialog(this, "정보 수정\n"+tfName.getText()+ "님의 정보가 수정었습니다.", "주소록 정보",JOptionPane.INFORMATION_MESSAGE); //this 는 active 창에 띄우고 null은 화면아무데나 중앙에 띄워라
	}else {
		JOptionPane.showMessageDialog(this, "정보 수정\n"+ "수정 중 문제가 발생했습니다.", "주소록 정보",JOptionPane.ERROR_MESSAGE); //this 는 active 창에 띄우고 null은 화면아무데나 중앙에 띄워라
	}
	
}
	
	














	


	private void filePath() {   // file loading method
		
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG, PNG, BMP", "jpg","png","bmp");
		chooser.setFileFilter(filter);
		
		int ret = chooser.showOpenDialog(null);
		if(ret != JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.", "경고",JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		String filePath = chooser.getSelectedFile().getPath();
		tfFilePath.setText(filePath);
		
		lblImage.setIcon(new ImageIcon(filePath));
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		
		
	}
	private JButton getBtnQuery() {
		if (btnQuery == null) {
			btnQuery = new JButton("검색");
			btnQuery.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					conditionQuery();
				}
			});
			btnQuery.setBounds(534, 21, 70, 23);
		}
		return btnQuery;
	}
	private JComboBox getCbSelection() {
		if (cbSelection == null) {
			cbSelection = new JComboBox();
			cbSelection.setModel(new DefaultComboBoxModel(new String[] {"브랜드명", "제품명"}));
			cbSelection.setBounds(268, 21, 89, 23);
		}
		return cbSelection;
	}
	private JTextField getTfSelection() {
		if (tfSelection == null) {
			tfSelection = new JTextField();
			tfSelection.setBounds(406, 22, 116, 21);
			tfSelection.setColumns(10);
		}
		return tfSelection;
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

