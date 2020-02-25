package model;

public class Produto {
	private int idProduto;
	private String nomeProduto;
	private double precoProduto;
	private int idFornecedor;
	private int idFabricante;
	private String tipoProduto;
	
	public int getIdProduto() {
		return idProduto;
	}
	
	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}
	
	public String getNomeProduto() {
		return nomeProduto;
	}
	
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	
	public double getPrecoProduto() {
		return precoProduto;
	}
	
	public void setPrecoProduto(double precoProduto) {
		this.precoProduto = precoProduto;
	}
	
	public int getIdFornecedor() {
		return idFornecedor;
	}
	
	public void setIdFornecedor(int idFornecedor) {
		this.idFornecedor = idFornecedor;
	}
	
	public int getIdFabricante() {
		return idFabricante;
	}
	
	public void setIdFabricante(int idFabricante) {
		this.idFabricante = idFabricante;
	}
	
	public String getTipoProduto() {
		return tipoProduto;
	}
	
	public void setTipoProduto(String tipoProduto) {
		this.tipoProduto = tipoProduto;
	}

}
