package controller.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.Conexao;
import model.Produto;

public class ProdutoDAO implements DAO{

	@Override
	public void insert(Object o) {
		
		Produto c = (Produto) o;
		
		String sql = "insert into tbProduto (nomeProduto,precoProduto,idFornecedor,idFabricante,tipoProduto) "
				+ "values (?,?,?,?,?)";
		

		try {
			PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
			ps.setString(1, c.getNomeProduto());
			ps.setDouble(2, c.getPrecoProduto());
			ps.setInt(3, c.getIdFornecedor());
			ps.setInt(4, c.getIdFabricante());
			ps.setString(5, c.getTipoProduto());

			ps.execute();
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Object o) {
		
		Produto c = (Produto) o;
		String sql = "update tbProduto set nomeProduto=?,precoProduto=?,idFornecedor=?,idFabricante=?,tipoProduto=? where idProduto=?";
		
		try {
			PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
			ps.setString(1, c.getNomeProduto());
			ps.setDouble(2, c.getPrecoProduto());
			ps.setInt(3, c.getIdFornecedor());
			ps.setInt(4, c.getIdFabricante());
			ps.setString(5, c.getTipoProduto());
			ps.setInt(6, c.getIdProduto());
			
			ps.execute();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(Object o) {
		
		Produto c = (Produto) o;
		String sql = "delete from tbProduto where idProduto=?";
		
		try {
			PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);

			ps.setInt(1, c.getIdProduto());

			ps.execute();

		} catch (SQLException e) {
			
			e.printStackTrace();
		}

	}

	@Override
	public Produto select(int i) {
		
		Produto c = new Produto();
		String sql = "select * from tbProduto where idProduto=?";
		
		try {
		PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
		ps.setInt(1, i);
		ResultSet rs = ps.executeQuery();
		
			while(rs.next()) {
				
				c.setIdProduto(rs.getInt("idProduto"));
				c.setNomeProduto(rs.getString("nomeProduto"));
				c.setPrecoProduto(rs.getDouble("precoProduto"));
				c.setIdFornecedor(rs.getInt("idFornecedor"));
				c.setIdFabricante(rs.getInt("idFabricante"));
				c.setTipoProduto(rs.getString("tipoProduto"));
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}	
		return c;
	}


	@Override
	public List<Produto> select() {

		List<Produto> produtos = new ArrayList<>();
		String sql = "select * from tbProduto";

		PreparedStatement ps;
		
		try {
			ps = Conexao.getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Produto c = new Produto();
				
				c.setIdProduto(rs.getInt("idProduto"));
				c.setNomeProduto(rs.getString("nomeProduto"));
				c.setPrecoProduto(rs.getDouble("precoProduto"));
				c.setIdFornecedor(rs.getInt("idFornecedor"));
				c.setIdFabricante(rs.getInt("idFabricante"));
				c.setTipoProduto(rs.getString("tipoProduto"));			
				produtos.add(c);
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return produtos;
	}

}
