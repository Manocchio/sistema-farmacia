package model;

public class ItensVenda {
	private int idItensVenda;
	private int idVenda;
	private int idProduto;
	private int qtdItensVenda;
	private double subTotalVenda;
	public int getIdItensVenda() {
		return idItensVenda;
	}
	public void setIdItensVenda(int idItensVenda) {
		this.idItensVenda = idItensVenda;
	}
	public int getIdVenda() {
		return idVenda;
	}
	public void setIdVenda(int idVenda) {
		this.idVenda = idVenda;
	}
	public int getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}
	public int getQtdItensVenda() {
		return qtdItensVenda;
	}
	public void setQtdItensVenda(int qtdItensVenda) {
		this.qtdItensVenda = qtdItensVenda;
	}
	public double getSubTotalVenda() {
		return subTotalVenda;
	}
	public void setSubTotalVenda(double subTotalVenda) {
		this.subTotalVenda = subTotalVenda;
	}
}
