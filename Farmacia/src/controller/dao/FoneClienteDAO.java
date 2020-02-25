package controller.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.Conexao;
import model.FoneCliente;

public class FoneClienteDAO implements DAO {

	@Override
	public void insert(Object o) {
		FoneCliente c = (FoneCliente) o;
		
		String sql = "insert into tbFoneCliente (numFoneCliente,idCliente) "
				+ "values (?,?)";
		

		try {
			PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
			ps.setString(1, c.getNumFoneCliente());
			ps.setInt(2, c.getIdCliente());
			ps.execute();
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Object o) {
		
		FoneCliente c = (FoneCliente) o;
		String sql = "update tbFoneCliente set numFoneCliente=?,idCliente=?";
		
		try {
			PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
			ps.setString(1, c.getNumFoneCliente());
			ps.setInt(2, c.getIdCliente());
			ps.execute();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}


	@Override
	public void delete(Object o) {

			FoneCliente c = (FoneCliente) o;
			String sql = "delete tbFoneCliente where id=?";
			
			try {
				PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);

				ps.setInt(1, c.getIdFoneCliente());

				ps.execute();

			} catch (SQLException e) {
				
				e.printStackTrace();
			}
	}

	@Override
	public Object select(int i) {

		FoneCliente c = new FoneCliente();
		String sql = "select * from tbFoneCliente where id=?";
		
		try {
		PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
			while(rs.next()) {
				
				c.setNumFoneCliente(rs.getString("numFoneCliente"));
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
		String sql = "select * from tbFoneCliente";

		PreparedStatement ps;
		
		try {
			ps = Conexao.getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				FoneCliente c = new FoneCliente();

				c.setNumFoneCliente(rs.getString("numFoneCliente"));
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
