package com.inventorymanager;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/*
 * 
 * Load data from database
 * Maintain a queue of changes
 * Execute changes
 * 
 * 
 * 
 */



public class DataHandler {
	
	List<Product> productList;
	List<Category> categoryList;
	List<Brand> brandList;
	
	public DataHandler()	{
		try(Connection conn = MySQLJDBCUtil.getConnection())	{
			DatabaseMetaData md = conn.getMetaData();
			ResultSet rs = md.getTables(null, null, "%", null);
			while (rs.next()) {
			  System.out.println(rs.getString(3));
			}
		} catch(SQLException ex)	{
			System.out.println(ex.getMessage());
		}
		
		
		productList = new ArrayList<Product>();
		categoryList = new ArrayList<Category>();
		brandList = new ArrayList<Brand>();
	}
	
	/*********************************************************************************************
	 * 
	 * Product methods
	 * 
	 *********************************************************************************************/
	
	public void loadProducts()	{
		//String sql = "SELECT productID, categoryId, brandId, productName, quantity, cost, expirationDate FROM products";
		String sql = "SELECT * FROM products";
		
		try(Connection conn = MySQLJDBCUtil.getConnection())	{
			
			try(Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql))	{
				while(rs.next())	{
					int productId = rs.getInt("productId");
					int categoryId = rs.getInt("categoryId");
					int brandId = rs.getInt("brandId");
					String name = rs.getString("productName");
					int quantity = rs.getInt("quantity");
					float cost = rs.getFloat("cost");
					Date expirationDate = rs.getDate("expirationDate");
					
					Product product = new Product(productId, categoryId, brandId, name, quantity, cost, expirationDate);
					productList.add(product);
				}
				
			} catch(SQLException e) {
				System.out.println(e.getMessage());
			}
			
		} catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	
	public void printProducts()	{
		for(Product p : productList) {
			System.out.println(p.getName());
		}
	}
	
	public String[] getProductNames()	{
		String[] names = new String[productList.size()];
		for(int i=0; i<names.length; i++) {
			names[i] = productList.get(i).getName();
		}
		return names;
	}
	
	public Product getProduct(int i) {
		return productList.get(i);
	}
	
	public List<Product> getProductList()	{
		return productList;
	}
	
	public void addProduct(Product p)	{
		String sql = "INSERT INTO products(productId, categoryId, brandId, productName, quantity, cost, expirationDate) "
				   + "VALUES(?,?,?,?,?,?,?)";
		
		try(Connection conn = MySQLJDBCUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);){
			
			//Set parameters for statement
			pstmt.setInt(1, p.getProductId());
			pstmt.setInt(2, p.getCategoryId());
			pstmt.setInt(3, p.getBrandId());
			pstmt.setString(4, p.getName());
			pstmt.setInt(5, p.getQuantity());
			pstmt.setFloat(6, p.getCost());
			pstmt.setDate(7, p.getExpiration());
			
			pstmt.executeUpdate();
			
		} catch(SQLException ex)	{
			System.out.println(ex.getMessage());
		} 		
	}
	
	public void editProduct(Product p_orig, Product p_new)	{
		String sql = "UPDATE products "
				   + "SET productId = ?, categoryId = ?, brandId = ?, productName = ?, quantity = ?, "
				   + 	 "cost = ?, expirationDate = ? "
				   + "WHERE productId = ?";
		
		try(Connection conn = MySQLJDBCUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);)	{
			
			//Set parameters for statement
			pstmt.setInt(1, p_new.getProductId());
			pstmt.setInt(2, p_new.getCategoryId());
			pstmt.setInt(3, p_new.getBrandId());
			pstmt.setString(4, p_new.getName());
			pstmt.setInt(5, p_new.getQuantity());
			pstmt.setFloat(6, p_new.getCost());
			pstmt.setDate(7, p_new.getExpiration());
			
			pstmt.setInt(8, p_orig.getProductId());

			pstmt.executeUpdate();
			
		} catch(SQLException ex)	{
			System.out.println(ex.getMessage());
		}
	}
	
	/*********************************************************************************************
	 * 
	 * Category methods
	 * 
	 *********************************************************************************************/
	
	
	public void loadCategories()	{
		String sql = "SELECT categoryId, categoryName FROM categories";
		
		try(Connection conn = MySQLJDBCUtil.getConnection())	{
			
			try(Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql))	{
				while(rs.next())	{
					int categoryId = rs.getInt("categoryId");
					String name = rs.getString("categoryName");
					Category category = new Category(categoryId, name);
					
					categoryList.add(category);
				}
				
			} catch(SQLException e) {
				System.out.println(e.getMessage());
			}
			
		} catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public Category getCategory(int i)	{
		return categoryList.get(i);
	}
	
	public List<Category> getCategoryList()	{
		return categoryList;
	}
	
	public void addCategory(Category c)	{			
		String sql = "INSERT INTO categories(categoryId, categoryName) "
				   + "VALUES(?,?)";
		
		try(Connection conn = MySQLJDBCUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);){
			
			//Set parameters for statement
			pstmt.setInt(1, c.getCategoryId());
			pstmt.setString(2, c.getName());
			
			pstmt.executeUpdate();
			
		} catch(SQLException ex)	{
			System.out.println(ex.getMessage());
		}
			
	}
	
	public void editCategory(Category c_orig, Category c_new)	{
		String sql = "UPDATE categories "
				   + "SET categoryId = ?, categoryName = ? "
				   + "WHERE CategoryId = ?";
		
		try(Connection conn = MySQLJDBCUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);)	{
			
			//Set parameters for statement
			pstmt.setInt(1, c_new.getCategoryId());
			pstmt.setString(2, c_new.getName());
			
			pstmt.setInt(3, c_orig.getCategoryId());

			pstmt.executeUpdate();
			
		} catch(SQLException ex)	{
			System.out.println(ex.getMessage());
		}
	}
	
	
	/*********************************************************************************************
	 * 
	 * Brand methods
	 * 
	 *********************************************************************************************/
	
	
	public void loadBrands()	{
		String sql = "SELECT brandId, brandName FROM brands";
		
		try(Connection conn = MySQLJDBCUtil.getConnection())	{
			
			try(Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql))	{
				while(rs.next())	{
					int brandId = rs.getInt("brandId");
					String name = rs.getString("brandName");
					Brand brand = new Brand(brandId, name);
					
					brandList.add(brand);
				}
				
			} catch(SQLException e) {
				System.out.println(e.getMessage());
			}
			
		} catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public List<Brand> getBrandList()	{
		return brandList;
	}
}
