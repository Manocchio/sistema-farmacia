package controller.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.Conexao;
import model.Fornecedor;

public class FornecedorDAO implements DAO{

	@Override
	public void insert(Object o) {
	
		Fornecedor c = (Fornecedor) o;
		
		String sql = "insert into tbFornecedor (nomeFornecedor)"
				+ "values (?)";
		

		try {
			PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
			ps.setString(1, c.getNomeFornecedor());
			ps.execute();
			ps.close();
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}

	}

	@Override
	public void update(Object o) {
		
		Fornecedor c = (Fornecedor) o;
		String sql = "update tbFornecedor set nomeFornecedor =? where idFornecedor=?;\r\n" + 
				"";
		
		try {
			PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
			ps.setString(1, c.getNomeFornecedor());
			ps.setInt(2, c.getIdFornecedor());
			ps.execute();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Object o) {
		
		Fornecedor c = (Fornecedor) o;
		String sql = "delete from tbFornecedor where idFornecedor=?";
		
		try {
			PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);

			ps.setInt(1, c.getIdFornecedor());
			ps.execute();

		} catch (SQLException e) {
			
			e.printStackTrace();
		}

	}

	@Override
	public Fornecedor select(int i) {
		
		Fornecedor c = new Fornecedor();
		String sql = "select * from tbFornecedor where idFornecedor=?";
		
		try {
		PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
		ps.setInt(1, i);
		ResultSet rs = ps.executeQuery();
		
			while(rs.next()) {
				
				c.setIdFornecedor(rs.getInt("idFornecedor"));
				c.setNomeFornecedor(rs.getString("nomeFornecedor"));
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}	
		return c;
		
	}

	@Override
	public List<Fornecedor> select() {

		List<Fornecedor> fornecedores = new ArrayList<>();
		String sql = "select * from tbFornecedor";

		PreparedStatement ps;
		
		try {
			ps = Conexao.getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Fornecedor c = new Fornecedor();

				c.setIdFornecedor(rs.getInt("idFornecedor"));
				c.setNomeFornecedor(rs.getString("nomeFornecedor"));
				
				fornecedores.add(c);
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fornecedores;
	}
}
