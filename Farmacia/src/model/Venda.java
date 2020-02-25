package model;

public class Venda {
	private int idVenda;
	private String dataVenda;
	private double valorTotalVenda;
	private int idCliente;
	
	public int getIdVenda() {
		return idVenda;
	}
	
	public void setIdVenda(int idVenda) {
		this.idVenda = idVenda;
	}
	
	public String getDataVenda() {
		return dataVenda;
	}
	
	public void setDataVenda(String dataVenda) {
		this.dataVenda = dataVenda;
	}
	
	public double getValorTotalVenda() {
		return valorTotalVenda;
	}
	
	public void setValorTotalVenda(double valorTotalVenda) {
		this.valorTotalVenda = valorTotalVenda;
	}
	
	public int getIdCliente() {
		return idCliente;
	}
	
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
}
