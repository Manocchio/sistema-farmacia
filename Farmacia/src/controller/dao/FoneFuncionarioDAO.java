package controller.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.Conexao;
import model.FoneFuncionario;

public class FoneFuncionarioDAO implements DAO{

	@Override
	public void insert(Object o) {
		
		FoneFuncionario c = (FoneFuncionario) o;
		
		String sql = "insert into tbFoneFuncionario (numFoneFuncionario,idFuncionario) "
				+ "values (?,?)";
		

		try {
			PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
			ps.setString(1, c.getNumFoneFuncionario());
			ps.setInt(2, c.getIdFuncionario());
			ps.execute();
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Object o) {
		
		FoneFuncionario c = (FoneFuncionario) o;
		String sql = "update tbFoneFuncionario set numFoneFuncionario=?,idFuncionario=?";
		
		try {
			PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
			ps.setString(1, c.getNumFoneFuncionario());
			ps.setInt(2, c.getIdFuncionario());
			ps.execute();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Object o) {
		
		FoneFuncionario c = (FoneFuncionario) o;
		String sql = "delete tbFoneFuncionario where id=?";
		
		try {
			PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);

			ps.setInt(1, c.getIdFoneFuncionario());

			ps.execute();

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}


	@Override
	public Object select(int i) {

		FoneFuncionario c = new FoneFuncionario();
		String sql = "select * from tbFoneFuncionario where id=?";
		
		try {
		PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
			while(rs.next()) {
				
				c.setNumFoneFuncionario(rs.getString("numFoneFuncionario"));
				c.setIdFuncionario(rs.getInt("idFuncionario"));
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}	
		return c;

	}

	@Override
	public List select() {
		
		List list = new ArrayList();
		String sql = "select * from tbFoneFuncionario";

		PreparedStatement ps;
		
		try {
			ps = Conexao.getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				FoneFuncionario c = new FoneFuncionario();

				c.setNumFoneFuncionario(rs.getString("numFoneFuncionario"));
				c.setIdFuncionario(rs.getInt("idFuncionario"));
				
				list.add(c);
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
