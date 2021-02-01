package view;

import java.awt.Dimension;
import javax.swing.*;

import controller.*;
import model.GlobalConstants;

public class Toolbar  extends JToolBar{
	private JButton btnAdd,btnEdit,btnBin,btnSrch,btnAdvSearch;
	public static JTextField srchField;
	
	public Toolbar() {		
		super(SwingConstants.HORIZONTAL);
		
		btnAdd = new JButton();
		btnAdd.setToolTipText(GlobalConstants.addBtnToolTipTxt);
		btnAdd.setIcon(new ImageIcon(GlobalConstants.addImg));
		btnAdd.addActionListener(new AddButtonListener());
		add(btnAdd);
		
		addSeparator();
		
		btnEdit = new JButton();
		btnEdit.setToolTipText(GlobalConstants.editBtnToolTipTxt);
		btnEdit.setIcon(new ImageIcon(GlobalConstants.editImg));
		btnEdit.addActionListener(new EditButtonListener());
		add(btnEdit);
		
		addSeparator();
		
		btnBin = new JButton();
		btnBin.setToolTipText(GlobalConstants.delBtnToolTipTxt);
		btnBin.setIcon(new ImageIcon(GlobalConstants.delImg));
		btnBin.addActionListener(new DeleteButtonListener());
		add(btnBin);
		
		addSeparator();
		
		btnAdvSearch = new JButton();
		btnAdvSearch.addActionListener(new AdvSearchAl());
		btnAdvSearch.setToolTipText(GlobalConstants.advSearchToolTip);
		btnAdvSearch.setIcon(new ImageIcon(GlobalConstants.advSearImg));
		add(btnAdvSearch);
		
		add(Box.createHorizontalGlue());
		addSeparator();
		
		srchField = new JTextField(20);
		srchField.setMaximumSize(new Dimension(350,25));
		srchField.setMinimumSize(new Dimension(350,25));
		srchField.setToolTipText(GlobalConstants.srchFieldToolTipTxt);
		srchField.addMouseListener(new SearchFieldMouseListener());
		add(srchField);	
		addSeparator();
		
		btnSrch = new JButton();
		btnSrch.setToolTipText(GlobalConstants.srchBtnToolTipTxt);
		btnSrch.setIcon(new ImageIcon(GlobalConstants.srcImg));
		btnSrch.addActionListener(new SearchButtonListener());
		add(btnSrch);
	}
	
	
}
