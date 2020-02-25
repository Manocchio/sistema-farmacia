package controller.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.Conexao;
import model.ItensVenda;

public class ItensVendaDAO implements DAO{

	@Override
	public void insert(Object o) {
		ItensVenda c = (ItensVenda) o;
		
		String sql = "insert into tbItensVenda (idVenda,idProduto,qtdItensVenda,subTotalVenda) "
				+ "values (?,?,?,?)";
		

		try {
			PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
			ps.setInt(1, c.getIdVenda());
			ps.setInt(2, c.getIdProduto());
			ps.setInt(3, c.getQtdItensVenda());
			ps.setDouble(4, c.getSubTotalVenda());
			
			ps.execute();
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}

	}

	@Override
	public void update(Object o) {
		ItensVenda c = (ItensVenda) o;
		String sql = "update tbItensVenda set idVenda=?,idProduto=?,qtdItensVenda=?,subTotalVenda=?";
		
		try {
			PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
			ps.setInt(1, c.getIdVenda());
			ps.setInt(2, c.getIdProduto());
			ps.setInt(3, c.getQtdItensVenda());
			ps.setDouble(4, c.getSubTotalVenda());
			
			ps.execute();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Object o) {
		ItensVenda c = (ItensVenda) o;
		String sql = "delete tbItensVenda where id=?";
		
		try {
			PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);

			ps.setInt(1, c.getIdVenda());
			ps.setInt(2, c.getIdProduto());
			ps.setInt(3, c.getQtdItensVenda());
			ps.setDouble(4, c.getSubTotalVenda());

			ps.execute();

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public Object select(int i) {
		
		ItensVenda c = new ItensVenda();
		String sql = "select * from tbItensVenda where id=?";
		
		try {
		PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
			while(rs.next()) {
				
				c.setIdVenda(rs.getInt("idVenda"));
				c.setIdProduto(rs.getInt("idProduto"));
				c.setQtdItensVenda(rs.getInt("qtdItensVenda"));
				c.setSubTotalVenda(rs.getDouble("subTotalVenda"));
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}	
		return c;
	}

	@Override
	public List select() {

		List list = new ArrayList();
		String sql = "select * from tbItensVenda";

		PreparedStatement ps;
		
		try {
			ps = Conexao.getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ItensVenda c = new ItensVenda();

				c.setIdVenda(rs.getInt("idVenda"));
				c.setIdProduto(rs.getInt("idProduto"));
				c.setQtdItensVenda(rs.getInt("qtdItensVenda"));
				c.setSubTotalVenda(rs.getDouble("subTotalVenda"));
				
				list.add(c);
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}

}
