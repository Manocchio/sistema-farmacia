package controller.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.Conexao;
import model.Venda;

public class VendaDAO implements DAO{

	@Override
	public void insert(Object o) {
		Venda c = (Venda) o;
		
		String sql = "insert into tbVenda (dataVenda,valorTotalVenda,idCliente) "
				+ "values (?,?,?)";
		

		try {
			PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
			ps.setString(1, c.getDataVenda());
			ps.setDouble(2, c.getValorTotalVenda());
			ps.setInt(3, c.getIdCliente());
			
			ps.execute();
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}

		
	}

	@Override
	public void update(Object o) {

		Venda c = (Venda) o;
		String sql = "update tbVenda set dataVenda=?,valorTotalVenda=?,idCliente=?";
		
		try {
			PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
			ps.setString(1, c.getDataVenda());
			ps.setDouble(2, c.getValorTotalVenda());
			ps.setInt(3, c.getIdCliente());
			
			ps.execute();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		
	}

	@Override
	public void delete(Object o) {
		
		Venda c = (Venda) o;
		String sql = "delete from tbVenda where id=?";
		
		try {
			PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);

			ps.setInt(1, c.getIdVenda());

			ps.execute();

		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		
	}

	@Override
	public Object select(int i) {
		
		Venda c = new Venda();
		String sql = "select * from tbVenda where id=?";
		
		try {
		PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
			while(rs.next()) {
				
				c.setDataVenda(rs.getString("dataVenda"));
				c.setValorTotalVenda(rs.getDouble("valorTotalVenda"));
				c.setIdCliente(rs.getInt("idCliente"));

			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}	
		return c;

	}

	@Override

	public List select() {
		List list = new ArrayList();
		String sql = "select * from tbVenda";

		PreparedStatement ps;
		
		try {
			ps = Conexao.getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Venda c = new Venda();

				c.setDataVenda(rs.getString("dataVenda"));
				c.setValorTotalVenda(rs.getDouble("valorTotalVenda"));
				c.setIdCliente(rs.getInt("idCliente"));
				list.add(c);
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
