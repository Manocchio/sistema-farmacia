package controller;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
	
	
	public Conexao() {
		connection = this.getConnection();
	}
	
	public static Connection connection = null;
	private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
	public final static String DBNAME = "bdfarmacia";
	private final static String URL = "jdbc:mysql://localhost:3306/"+DBNAME+"?useTimezone=true&serverTimezone=UTC";
	private final static String LOGIN = "root";
	private final static String SENHA =  "1234";
	
	public static Connection getConnection() {
		
		try {
			
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL,LOGIN,SENHA);
			System.out.println("Conex�o realizada com sucesso");
			return connection;

		} catch (ClassNotFoundException e) {
			
			System.out.println("Driver não encontrado"+ e.toString());
			return null;
		}
		
		catch (Exception e) {
			System.out.println("Falha ao conectar" +e.toString());
			return null;
			
		}
		
	}
		
		public void close() {
			
			try {
				connection.close();
			} catch (Exception e) {
				System.out.println(e);
			}
			
			
		}
		
		
		
		
	}
	
