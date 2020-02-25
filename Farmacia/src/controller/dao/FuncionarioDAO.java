package controller.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.Conexao;
import model.Funcionario;


public class FuncionarioDAO implements DAO{

	@Override
	public void insert(Object o) {
		Funcionario c = (Funcionario) o;
	
		String sql = "insert into tbFuncionario (nomeFuncionario,rgFuncionario,cpfFuncionario,generoFuncionario) "
			+ "values (?,?,?,?)";
	

		try {
			PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
			ps.setString(1, c.getNomeFuncionario());
			ps.setString(2, c.getRgFuncionario());
			ps.setString(3, c.getCpfFuncionario());
			ps.setString(4, c.getGeneroFuncionario());
			ps.execute();
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
	}

	@Override
	public void update(Object o) {
		
		Funcionario c = (Funcionario) o;
		String sql = "update tbFuncionario set nomeFuncionario=?,rgFuncionario=?,cpfFuncionario=?, generoFuncionario=? where idFuncionario=?;";
		
		try {
			PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
			ps.setString(1, c.getNomeFuncionario());
			ps.setString(2, c.getRgFuncionario());
			ps.setString(3, c.getCpfFuncionario());
			ps.setString(4, c.getGeneroFuncionario());
			ps.setInt(5, c.getIdFuncionario());
			ps.execute();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		
	}

	@Override
	public void delete(Object o) {

		Funcionario c = (Funcionario) o;
		String sql = "delete from tbFuncionario where idFuncionario=?";
		
		try {
			PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);

			ps.setInt(1, c.getIdFuncionario());
			ps.execute();

		} catch (SQLException e) {
			
			e.printStackTrace();
		}

	}

	@Override
	public Funcionario select(int i) {
		
		Funcionario c = new Funcionario();
		String sql = "select * from tbFuncionario where idFuncionario=?";
		
		try {
		PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
		ps.setInt(1, i);
		ResultSet rs = ps.executeQuery();
		
			while(rs.next()) {
				c.setIdFuncionario(rs.getInt("idFuncionario"));
				c.setNomeFuncionario(rs.getString("nomeFuncionario"));
				c.setRgFuncionario(rs.getString("rgFuncionario"));
				c.setCpfFuncionario((rs.getString("cpfFuncionario")));
				c.setGeneroFuncionario(rs.getString("generoFuncionario"));
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}	
		return c;

	}

	@Override
	public List<Funcionario> select() {

		List<Funcionario> funcionarios = new ArrayList<>();
		String sql = "select * from tbFuncionario";

		PreparedStatement ps;
		
		try {
			ps = Conexao.getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Funcionario c = new Funcionario();
				
				c.setIdFuncionario(rs.getInt("idFuncionario"));
				c.setNomeFuncionario(rs.getString("nomeFuncionario"));
				c.setRgFuncionario(rs.getString("rgFuncionario"));
				c.setCpfFuncionario((rs.getString("cpfFuncionario")));
				c.setGeneroFuncionario(rs.getString("generoFuncionario"));
				
				funcionarios.add(c);
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return funcionarios;
	}

}


