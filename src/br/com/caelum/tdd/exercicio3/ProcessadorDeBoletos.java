package br.com.caelum.tdd.exercicio3;

import java.util.List;

public class ProcessadorDeBoletos {

	public void processa(List<Boleto> boletos, Fatura fatura) {
		for(Boleto boleto : boletos) {
			Pagamento pagamento = new Pagamento(boleto.getValor(), MeioDePagamento.BOLETO);
			fatura.getPagamentos().add(pagamento);
		}
		
		
		double totalPagamentos = 0.0;
		for (Pagamento pagamento : fatura.getPagamentos()) {
			totalPagamentos += pagamento.getValor();
		}
		
		if(totalPagamentos >= fatura.getValor()) {
			fatura.setPago(true);
		}
		
	}
}
