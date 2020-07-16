package com.inventorymanager;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

public class InventoryManager extends JFrame {
	public InventoryManager()	{
		init();
	}
	
	private void init()	{
		add(new Window());		
		
		/*
		JTextArea ta = new JTextArea(20,20);
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		
		p1.add(ta);
		
		JTabbedPane tp = new JTabbedPane();
		tp.setBounds(50,50,200,200);
		tp.add("Tab1",p1);
		tp.add("Tab2",p2);
		tp.add("Tab3",p3);
		
		add(tp);
		*/
		
		
		
		
		setSize(500, 100);
		setTitle("Inventory Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	public static void main(String[] args)	{		
		EventQueue.invokeLater(() ->	{
			InventoryManager im = new InventoryManager();
			im.pack();
			im.setVisible(true);
		});
	}
}
