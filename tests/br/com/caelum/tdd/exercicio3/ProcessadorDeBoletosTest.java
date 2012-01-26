package br.com.caelum.tdd.exercicio3;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class ProcessadorDeBoletosTest {

	@Test
	public void naoDeveGerarPagamentosQuandoNaoHaBoletos() {
		Fatura f = new Fatura("sun", 1000.0);
		List<Boleto> boletosPagos = Collections.emptyList();

		new ProcessadorDeBoletos().processa(boletosPagos, f);
		
		assertEquals(0, f.getPagamentos().size());
	}
	
	@Test
	public void deveGerarUmPagamentoDeBoletoParaAFatura() {
		Fatura f = new Fatura("sun", 1000.0);
		List<Boleto> boletosPagos = Arrays.asList(new Boleto(200.0), new Boleto(500.0));
		
		new ProcessadorDeBoletos().processa(boletosPagos, f);
		
		assertEquals(2, f.getPagamentos().size());
		assertEquals(new Pagamento(200.0, MeioDePagamento.BOLETO), f.getPagamentos().get(0));
		assertEquals(new Pagamento(500.0, MeioDePagamento.BOLETO), f.getPagamentos().get(1));
	}
	
	@Test
	public void deveMostrarFaturaPagaSeSomaDosBoletosMaiorQueAFatura() {
		Fatura f = new Fatura("Barbara", 3000.0);
		
		List<Boleto> boletosPagos = Arrays.asList(new Boleto(1500.0), new Boleto(2000.0));
		new ProcessadorDeBoletos().processa(boletosPagos, f);
		
		assertEquals(true, f.isPago());
	}
	
	@Test
	public void deveMostrarFaturaPagaSeSomaDosBoletosIgualQueAFatura() {
		Fatura f = new Fatura("Barbara", 3000.0);
		
		List<Boleto> boletosPagos = Arrays.asList(new Boleto(1500.0), new Boleto(1500.0));
		new ProcessadorDeBoletos().processa(boletosPagos, f);
		
		assertEquals(true, f.isPago());
	}
	
	@Test
	public void deveMostrarFaturaNaoPagaSeSomaDosBoletosMenorQueAFatura() {
		Fatura f = new Fatura("Barbara", 3000.0);
		
		List<Boleto> boletosPagos = Arrays.asList(new Boleto(1000.0), new Boleto(1500.0));
		new ProcessadorDeBoletos().processa(boletosPagos, f);
		
		assertEquals(false, f.isPago());
	}
}
