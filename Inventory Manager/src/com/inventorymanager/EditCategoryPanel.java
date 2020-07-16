package com.inventorymanager;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EditCategoryPanel extends JPanel	{
	private static int FIELD_WIDTH = 15;
	
	private JTextField categoryId;
	private JTextField name;
	
	public EditCategoryPanel()	{
		this.categoryId = new JTextField(FIELD_WIDTH);
		this.name = new JTextField(FIELD_WIDTH);
		
		drawPanel();
	}
	
	public EditCategoryPanel(Category c)	{
		this.categoryId = new JTextField(FIELD_WIDTH);
		this.categoryId.setText(String.valueOf(c.getCategoryId()));
		
		this.name = new JTextField(FIELD_WIDTH);
		this.name.setText(c.getName());
		
		drawPanel();
	}
	
	private void drawPanel()	{
		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Product Editor"),
				 BorderFactory.createEmptyBorder(10, 10, 10, 10)));

		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10,10,0,10);
		gbc.anchor = GridBagConstraints.EAST;
				
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(new JLabel("Category ID:", JLabel.LEFT), gbc);
		gbc.gridx = 1;
		add(categoryId, gbc);
					
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(new JLabel("Category Name:", JLabel.LEFT), gbc);
		gbc.gridx = 1;
		add(name, gbc);
	}

	public int getCategoryId() {
		return Integer.valueOf(categoryId.getText());
	}

	public String getName() {
		return name.getText();
	}
	
}
