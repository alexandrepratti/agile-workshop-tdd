package br.com.caelum.tdd.exercicio2;

public class GeradorDeNotaFiscal {

	private final EnviadorDeEmail email;
	private final NotaFiscalDao dao;
	private final Sap sap;

	public GeradorDeNotaFiscal(EnviadorDeEmail email, NotaFiscalDao dao, Sap sap) {
		this.email = email;
		this.dao = dao;
		this.sap = sap;
	}
	
	public NotaFiscal gera(Fatura fatura) {
		
		double valor = fatura.getValorMensal();
		
		NotaFiscal nf = new NotaFiscal(valor, impostoSimplesSobreO(valor));
		
		email.enviaEmail(nf);
		dao.persiste(nf);
		sap.envia(nf);

		return nf;
	}

	private double impostoSimplesSobreO(double valor) {
		return valor * 0.06;
	}
}
