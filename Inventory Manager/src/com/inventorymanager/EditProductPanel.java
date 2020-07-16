package com.inventorymanager;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import java.sql.Date;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EditProductPanel extends JPanel {
	private static int FIELD_WIDTH = 15;
	private static String COMBO_BOX_DUMMY_VALUE = "01234567890123456";
		
	private JTextField productId;
	private JTextField name;
	private JTextField quantity;
	private JTextField cost;
	private JTextField expiration;
	
	private JComboBox<String> categoryId;
	private JComboBox<String> brandId;
	
	private String[] categoryIdList = {"1","2","3"};
	private String[] brandIdList = {"1","2","3"};
	
	public EditProductPanel()	{	
		this.productId = new JTextField(FIELD_WIDTH);
		this.name = new JTextField(FIELD_WIDTH);
		this.quantity = new JTextField(FIELD_WIDTH);
		this.cost = new JTextField(FIELD_WIDTH);
		this.expiration = new JTextField(FIELD_WIDTH);
		
		this.categoryId = new JComboBox<String>(categoryIdList);
		categoryId.setPrototypeDisplayValue(COMBO_BOX_DUMMY_VALUE);
		
		this.brandId = new JComboBox<String>(brandIdList);
		brandId.setPrototypeDisplayValue(COMBO_BOX_DUMMY_VALUE);	

		drawPanel();
	}
	
	public EditProductPanel(Product p) {
		this.productId = new JTextField(FIELD_WIDTH);
		this.productId.setText(String.valueOf(p.getProductId()));
		
		this.name = new JTextField(FIELD_WIDTH);
		this.name.setText(p.getName());
		
		this.quantity = new JTextField(FIELD_WIDTH);
		this.quantity.setText(String.valueOf(p.getQuantity()));
		
		this.cost = new JTextField(FIELD_WIDTH);
		this.cost.setText(String.valueOf(p.getCost()));
		
		this.expiration = new JTextField(FIELD_WIDTH);
		this.expiration.setText(String.valueOf(p.getExpiration()));
		
		this.categoryId = new JComboBox<String>(categoryIdList);
		categoryId.setPrototypeDisplayValue(COMBO_BOX_DUMMY_VALUE);
		
		this.brandId = new JComboBox<String>(brandIdList);
		brandId.setPrototypeDisplayValue(COMBO_BOX_DUMMY_VALUE);	

		drawPanel();
	}
	
	private void drawPanel()	{	
		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Product Editor"),
	            									 BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10,10,0,10);
		c.anchor = GridBagConstraints.EAST;
		
		c.gridx = 0;
		c.gridy = 0;
		add(new JLabel("Product ID:", JLabel.LEFT), c);
		c.gridx = 1;
		add(productId, c);
		
		c.gridx = 0;
		c.gridy = 1;
		add(new JLabel("Category ID:", JLabel.LEFT), c);
		c.gridx = 1;
		add(categoryId, c);
		
		c.gridx = 0;
		c.gridy = 2;
		add(new JLabel("Brand ID:", JLabel.LEFT), c);
		c.gridx = 1;
		add(brandId, c);
		
		c.gridx = 0;
		c.gridy = 3;
		add(new JLabel("Product Name:", JLabel.LEFT), c);
		c.gridx = 1;
		add(name, c);
		
		c.gridx = 0;
		c.gridy = 4;
		add(new JLabel("Cost:", JLabel.LEFT), c);
		c.gridx = 1;
		add(cost, c);
		
		c.gridx = 0;
		c.gridy = 5;
		add(new JLabel("Quantity:", JLabel.LEFT), c);
		c.gridx = 1;
		add(quantity, c);
		
		c.gridx = 0;
		c.gridy = 6;
		add(new JLabel("Expiration Date:", JLabel.LEFT), c);
		c.gridx = 1;
		add(expiration, c);
		
	}
	
	public int getProductId() {
		return Integer.valueOf(productId.getText());
	}
	
	public int getCategoryId()	{
		return Integer.valueOf((String) categoryId.getSelectedItem());
	}
	
	public int getBrandId()	{
		return Integer.valueOf((String) brandId.getSelectedItem());
	}
	
	public String getName() {
		return name.getText();
	}
	
	public int getQuantity() {
		return Integer.valueOf(quantity.getText());
	}
	
	public float getCost()	{
		return Float.valueOf(cost.getText());
	}
	
	public Date getExpiration() {
		return Date.valueOf(expiration.getText());
	}
	
}
