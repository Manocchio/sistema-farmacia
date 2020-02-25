package controller.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import controller.Conexao;
import model.Cliente;

public class ClienteDAO implements DAO {

	@Override
	public void insert(Object o) {
		Cliente c = (Cliente) o;
		
		String sql = "insert into tbCliente (logCliente,numLog,cepCliente,bairroCliente,ufCliente,complementoLog,nomeCliente,dataNasc,cpfCliente,generoCliente,municipioCliente) "
				+ "values (?,?,?,?,?,?,?,?,?,?,?)";
		

		try {
			PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
			ps.setString(1, c.getLogCliente());
			ps.setString(2, c.getNumLog());
			ps.setString(3, c.getCepCliente());
			ps.setString(4, c.getBairroCliente());
			ps.setString(5, c.getUfCliente());
			ps.setString(6, c.getComplementoLog());
			ps.setString(7, c.getNomeCliente());
			ps.setString(8, c.getDataNasc());
			ps.setString(9, c.getCpfCliente());
			ps.setString(10, c.getGeneroCliente());
			ps.setString(11, c.getMunicipioCliente());
			
			ps.execute();
			ps.close();
			
		} catch (SQLException e) {
		
			System.out.println("Falha ao cadastrar cliente");
			e.printStackTrace();
		}

	}

	@Override
	public void update(Object o) {
		Cliente c = (Cliente) o;
		String sql = "update tbCliente set logCliente=?,numLog=?,cepCliente=?, bairroCliente=?,ufCliente=?,complementoLog=?,nomeCliente=?,dataNasc=?,cpfCliente=?,generoCliente=?,municipioCliente=? where idCliente =?";
		
		try {
			PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
			ps.setString(1, c.getLogCliente());
			ps.setString(2, c.getNumLog());
			ps.setString(3, c.getCepCliente());
			ps.setString(4, c.getBairroCliente());
			ps.setString(5, c.getUfCliente());
			ps.setString(6, c.getComplementoLog());
			ps.setString(7, c.getNomeCliente());
			ps.setString(8, c.getDataNasc());
			ps.setString(9, c.getCpfCliente());
			ps.setString(10, c.getGeneroCliente());
			ps.setString(11, c.getMunicipioCliente());
			ps.setInt(12, c.getIdCliente());
			ps.execute();
			
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, "Um campo deve estar faltando!");
		}
	}

	@Override
	public void delete(Object o) {
		Cliente c = (Cliente) o;

		String sql = "delete from tbCliente where idCliente=?";
		
		try {
			PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);

			ps.setInt(1, c.getIdCliente());

			ps.execute();

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public Cliente select(int i) {
		
		Cliente c = new Cliente();
		String sql = "select * from tbCliente where idCliente=?";
		
		try {
		PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
		ps.setInt(1, i);
		ResultSet rs = ps.executeQuery();
		
			while(rs.next()) {
				
				c.setIdCliente(rs.getInt("idCliente"));
				c.setLogCliente(rs.getString("logCliente"));
				c.setNumLog(rs.getString("numLog"));
				c.setCepCliente(rs.getString("cepCliente"));
				c.setBairroCliente(rs.getString("bairroCliente"));
				c.setUfCliente(rs.getString("ufCliente"));
				c.setComplementoLog(rs.getString("complementoLog"));
				c.setNomeCliente(rs.getString("nomeCliente"));
				c.setDataNasc(rs.getString("dataNasc"));
				c.setCpfCliente(rs.getString("cpfCliente"));
				c.setGeneroCliente(rs.getString("generoCliente"));
				c.setMunicipioCliente(rs.getString("municipioCliente"));
			}
			
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, "Não foi possível encontrar este ID");
		}	
		return c;
	}

	@Override
	public List<Cliente> select() {
		

		List<Cliente> clientes = new ArrayList<>();
		String sql = "select * from tbCliente";

		PreparedStatement ps;
		
		try {
			ps = Conexao.getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Cliente c = new Cliente();
				
				c.setIdCliente(rs.getInt("idCliente"));
				c.setLogCliente(rs.getString("logCliente"));
				c.setNumLog(rs.getString("numLog"));
				c.setCepCliente(rs.getString("cepCliente"));
				c.setBairroCliente(rs.getString("bairroCliente"));
				c.setUfCliente(rs.getString("ufCliente"));
				c.setComplementoLog(rs.getString("complementoLog"));
				c.setNomeCliente(rs.getString("nomeCliente"));
				c.setDataNasc(rs.getString("dataNasc"));
				c.setCpfCliente(rs.getString("cpfCliente"));
				c.setGeneroCliente(rs.getString("generoCliente"));
				c.setMunicipioCliente(rs.getString("municipioCliente"));
			
				clientes.add(c);
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clientes;
	}
}
