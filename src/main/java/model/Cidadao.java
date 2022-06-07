package model;

public class Cidadao {
	String nome;
	int idade;
	Logradouro logradouro;
	
	public Cidadao (String nome, int idade, Logradouro logradouro) {
		this.nome = nome;
		this.idade = idade;
		this.logradouro = logradouro;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	public Logradouro getLogradouro() {
		return logradouro;
	}
	
	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
	}
	
	@Override
	public String toString() {
		return String.format("Cidadao [nome=%s, idade=%d, municipio=%s, estado=%s]",
				nome, idade, logradouro.getMunicipio(), logradouro.getEstado());
	}
	
	@Override
	public int hashCode() {
	    int result = 1;
	    result += ((nome == null) ? 0 : nome.hashCode());
	    result += ((idade == 0) ? 0 : idade);
	    result += ((logradouro == null) ? 0 : logradouro.hashCode());
	    return result;
	 }
	 
	@Override
	public boolean equals(Object cidadao) {
		if (nome.equals( ((Cidadao)cidadao).getNome() ) &&
				idade == ((Cidadao)cidadao).getIdade() &&
				logradouro.equals(((Cidadao)cidadao).getLogradouro()) ) {
			return true;
		}
		return false;
	}
}
