package br.com.alura.loja.vo;

import java.time.LocalDate;

public class RelatorioDeVendasVo {
	private String nomeproduto;
	private Long quantidadeVendida;
	private LocalDate dataUltimaVenda;
	
	public RelatorioDeVendasVo() {
	}

	public RelatorioDeVendasVo(String nomeproduto, Long quantidadeVendida, LocalDate dataUltimaVenda) {
		this.nomeproduto = nomeproduto;
		this.quantidadeVendida = quantidadeVendida;
		this.dataUltimaVenda = dataUltimaVenda;
	}

	public String getNomeproduto() {
		return nomeproduto;
	}

	public Long getQuantidadeVendida() {
		return quantidadeVendida;
	}

	public LocalDate getDataUltimaVenda() {
		return dataUltimaVenda;
	}
	
	@Override
	public String toString() {
		return "Relatório de Vendas: nome do produto: "+nomeproduto
				+", quantidade: "+ quantidadeVendida
				+", data da ultima venda: "+ dataUltimaVenda;
	}
	
}
