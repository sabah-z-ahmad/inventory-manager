package com.inventorymanager;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class Window extends JPanel implements ActionListener	{
	DataHandler dh;	
//	JLabel label;
//	JList<String> list;
//	DefaultListModel<String> model;

	JPanel productPanel;
	JPanel categoryPanel;
	JPanel brandPanel;
	JPanel buttonPanel;
	
	JTabbedPane tabbedPane;
	
	JTable productTable;
	JTable categoryTable;
	JTable brandTable;
	
	JTextField filterBar;
	
	JButton buttonDelete;
	JButton buttonAdd;
	JButton buttonEdit;

	
	public Window()	{
		initUI();
	}
	
	private void initUI()	{

		dh = new DataHandler();
		dh.loadProducts();
		dh.loadCategories();
		dh.loadBrands();

		initProductPanel();
		initCategoryPanel();
		initBrandPanel();
		initButtonPanel();
		
		tabbedPane = new JTabbedPane();
		tabbedPane.setBounds(50,50,200,200);
		tabbedPane.add("Products",productPanel);
		tabbedPane.add("Categories",categoryPanel);
		tabbedPane.add("Brands",brandPanel);
		
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridheight = 3;
		add(buttonPanel, gbc);
		
		gbc.fill = GridBagConstraints.BOTH;
		
		
		
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 3;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		add(tabbedPane, gbc);

		
	}
	
	private void initProductPanel()	{
		String[] columnNames = {"Product ID","Category ID","Brand ID","Product Name","Quantity","Cost","Expiration Date"};	
		DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
		productTable = new JTable(tableModel);
		
		for(Product p : dh.getProductList())	
			tableModel.addRow(p.getObjectArray());
		
		JScrollPane scrollPane = new JScrollPane(productTable);
		productTable.setFillsViewportHeight(true);
		
		productPanel = new JPanel();
		productPanel.setLayout(new BorderLayout());
		productPanel.add(scrollPane, BorderLayout.CENTER);
	}
	
	private void initCategoryPanel()	{
		String[] columnNames = {"Category ID","Category Name"};
		DefaultTableModel tableModel = new DefaultTableModel(columnNames,0);
		categoryTable = new JTable(tableModel);
		
		for(Category c : dh.getCategoryList())
			tableModel.addRow(c.getObjectArray());
		
		JScrollPane scrollPane = new JScrollPane(categoryTable);
		categoryTable.setFillsViewportHeight(true);
	
		categoryPanel = new JPanel();
		categoryPanel.setLayout(new BorderLayout());
		categoryPanel.add(scrollPane, BorderLayout.CENTER);
	}
	
	private void initBrandPanel() {
		String[] columnNames = {"Brand ID","Brand Name"};
		DefaultTableModel tableModel = new DefaultTableModel(columnNames,0);
		brandTable = new JTable(tableModel);
		
		for(Brand b : dh.getBrandList())
			tableModel.addRow(b.getObjectArray());
		
		JScrollPane scrollPane = new JScrollPane(brandTable);
		brandTable.setFillsViewportHeight(true);
		
		brandPanel = new JPanel();
		brandPanel.setLayout(new BorderLayout());
		brandPanel.add(scrollPane, BorderLayout.CENTER);
	}
	
	private void initButtonPanel() {
		buttonDelete = new JButton();
		buttonDelete.setText("Delete");
		buttonDelete.addActionListener(this);
		
		buttonAdd = new JButton();
		buttonAdd.setText("Add");
		buttonAdd.addActionListener(this);
		
		buttonEdit = new JButton();
		buttonEdit.setText("Edit");
		buttonEdit.addActionListener(this);
	
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		buttonPanel.add(buttonEdit, gbc);
		
		gbc.gridy = 1;
		buttonPanel.add(buttonAdd, gbc);
		
		gbc.gridy = 2;
		buttonPanel.add(buttonDelete, gbc);
		
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		String com = evt.getActionCommand();
		if(com.contentEquals("Delete"))	{
//			int i = list.getSelectedIndex();
//			list.clearSelection();
//			model.remove(i);
//			System.out.println("Deleting item #" + (i+1));
			System.out.println(tabbedPane.getSelectedIndex());
			
			
			
		}
		else if(com.contentEquals("Add"))	{
			int result;
			int tab = tabbedPane.getSelectedIndex();
			
			switch(tab)	{
			case 0:
				EditProductPanel addProductPanel = new EditProductPanel();
				result = JOptionPane.showConfirmDialog(null, addProductPanel, "Please Enter Product Details", 
														   JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				if(result == JOptionPane.OK_OPTION)	{
					//!!! Check if any fields are empty
					//!!! Check for duplicates
					
					Product p = new Product(addProductPanel.getProductId(), addProductPanel.getCategoryId(), 
											addProductPanel.getBrandId(), addProductPanel.getName(), 
											addProductPanel.getQuantity(), addProductPanel.getCost(), 
											addProductPanel.getExpiration());
					
					dh.addProduct(p);
					
					//!!! Show success message
				}
				break;
				
			case 1:
				EditCategoryPanel addCategoryPanel = new EditCategoryPanel();
				result = JOptionPane.showConfirmDialog(null, addCategoryPanel, "Please Enter Category Details", 
						   JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				
				if(result == JOptionPane.OK_OPTION)	{
					//!!! Check if any fields are empty
					//!!! Check for duplicates
					
					Category c = new Category(addCategoryPanel.getCategoryId(), addCategoryPanel.getName());
					
					dh.addCategory(c);
					
					//!!! Show success message
				}
				break;
				
			case 2:
				System.out.println("B");
				break;
				
			default:
				break;
			}
			
			
			
			
		}
		else if(com.contentEquals("Edit"))	{
			int result, i;
			int tab = tabbedPane.getSelectedIndex();
			
			switch(tab)	{
			case 0:
				i = productTable.getSelectedRow();
				
				if(i != -1)	{
					Product p_orig = dh.getProduct(i);
					EditProductPanel editPanel = new EditProductPanel(p_orig);
					
					result = JOptionPane.showConfirmDialog(null, editPanel, "Please Enter Product Details", 
															   JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
					
					if(result == JOptionPane.OK_OPTION)	{
						//!!! Check if any fields are empty
						//!!! Check for duplicates
						
						Product p_new = new Product(editPanel.getProductId(), editPanel.getCategoryId(), 
													editPanel.getBrandId(), editPanel.getName(), editPanel.getQuantity(), 
													editPanel.getCost(), editPanel.getExpiration());
					
						dh.editProduct(p_orig, p_new);
						
						//!!! Show success message
					}
				}
				break;
				
			case 1:
				i = categoryTable.getSelectedRow();
				Category c_orig = dh.getCategory(i);
				EditCategoryPanel addCategoryPanel = new EditCategoryPanel(c_orig);
				
				result = JOptionPane.showConfirmDialog(null, addCategoryPanel, "Please Enter Category Details", 
						   JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				
				if(result == JOptionPane.OK_OPTION)	{
					//!!! Check if any fields are empty
					//!!! Check for duplicates
					
					Category c_new = new Category(addCategoryPanel.getCategoryId(), addCategoryPanel.getName());
					
					dh.editCategory(c_orig, c_new);
					
					//!!! Show success message
				}
				break;
				
			case 2:
				System.out.println("B");
				break;
				
			default:
				break;
			}
			
			
		}
	}
	
}
