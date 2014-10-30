package br.com.caelum.agiletickets.models;

import static org.junit.Assert.*;

import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;

public class EspetaculoTest {

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.vagas(5));
	}

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.vagas(6));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.vagas(15));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.vagas(5, 3));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.vagas(10, 3));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.vagas(5, 3));
	}
	
	@Test
	public void deveCriarUmaSessaoDiaria() throws Exception {
		Espetaculo espetaculo = new Espetaculo();
		espetaculo.criaSessoes(new LocalDate(2014,10, 29), new LocalDate(2014,10, 29), new LocalTime(21,0), Periodicidade.DIARIA);
		
		Sessao sessao = espetaculo.getSessoes().get(0);
		
		assertEquals(sessao.getDia(), "29/10/14");
		assertEquals(sessao.getHora(), "21:00");
		assertEquals(sessao.getEspetaculo(), espetaculo);
		
	}
	
	@Test
	public void deveCriarTresSessoesEmDiasSeguidos() throws Exception {
		Espetaculo espetaculo = new Espetaculo();
		List<Sessao> sessoes = espetaculo.criaSessoes(new LocalDate(2014,10, 29), new LocalDate(2014,10, 31), 
				new LocalTime(21,0), Periodicidade.DIARIA);
		
		
		assertEquals(sessoes.size(), 3);
		assertEquals(sessoes.get(0).getDia(), "29/10/14");
		assertEquals(sessoes.get(1).getDia(), "30/10/14");
		assertEquals(sessoes.get(2).getDia(), "31/10/14");
		assertEquals(sessoes.get(0).getHora(), "21:00");
		assertEquals(sessoes.get(1).getHora(), "21:00");
		assertEquals(sessoes.get(2).getHora(), "21:00");
		assertEquals(sessoes.get(0).getEspetaculo(), espetaculo);
		assertEquals(sessoes.get(1).getEspetaculo(), espetaculo);
		assertEquals(sessoes.get(2).getEspetaculo(), espetaculo);
	}
	
	@Test
	public void deveCriarCincoSessoesEmSemanasConsecutivas() throws Exception {
		Espetaculo espetaculo = new Espetaculo();
		List<Sessao> sessoes = espetaculo.criaSessoes(new LocalDate(2014,11, 1), new LocalDate(2014,11, 30), 
				new LocalTime(21,0), Periodicidade.SEMANAL);
		
		assertEquals(sessoes.size(), 5);
		assertEquals(sessoes.get(0).getDia(), "01/11/14");
		assertEquals(sessoes.get(4).getDia(), "29/11/14");
	}

	private Sessao sessaoComIngressosSobrando(int quantidade) {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(quantidade * 2);
		sessao.setIngressosReservados(quantidade);

		return sessao;
	}
	
	
	
}
