package br.com.caelum.agiletickets.models;

public enum Periodicidade {
	
	DIARIA(1), SEMANAL(7);
	
	private int periodo;
	
	private Periodicidade(int periodo) {
		this.periodo = periodo;
	}
	
	public int getPeriodo() {
		return this.periodo;
	}
	
	
}
