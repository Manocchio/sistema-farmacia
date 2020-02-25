package controller.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.Conexao;
import model.Fabricante;

public class FabricanteDAO implements DAO{

	@Override
	public void insert(Object o) {
		
		Fabricante c = (Fabricante) o;
		
		String sql = "insert into tbFabricante (nomeFabricante)"
				+ "values (?)";
		

		try {
			PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
			ps.setString(1, c.getNomeFabricante());
			ps.execute();
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Object o) {
		Fabricante c = (Fabricante) o;
		String sql = "update tbFabricante set nomeFabricante =? where idFabricante=?;\r\n" + 
				"";
		try {
			PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
			ps.setString(1, c.getNomeFabricante());
			ps.setInt(2, c.getIdFabricante());
			ps.execute();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Object o) {
		
		Fabricante c = (Fabricante) o;
		String sql = "delete from tbFabricante where idFabricante=?";
		
		try {
			PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);

			ps.setInt(1, c.getIdFabricante());
			ps.execute();

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public Fabricante select(int i) {

		Fabricante c = new Fabricante();
		String sql = "select * from tbFabricante where idFabricante=?";
		
		try {
		PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
		ps.setInt(1, i);
		ResultSet rs = ps.executeQuery();
		
		
		
			while(rs.next()) {
				
				c.setIdFabricante(rs.getInt("idFabricante"));
				c.setNomeFabricante(rs.getString("nomeFabricante"));
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}	
		return c;
	}


	@Override
	public List<Fabricante> select() {

		List<Fabricante> fabricantes = new ArrayList<>();
		String sql = "select * from tbFabricante";

		PreparedStatement ps;
		
		try {
			ps = Conexao.getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Fabricante c = new Fabricante();
				
				c.setIdFabricante(rs.getInt("idFabricante"));
				c.setNomeFabricante(rs.getString("nomeFabricante"));
				
				fabricantes.add(c);
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fabricantes;
	}


}
