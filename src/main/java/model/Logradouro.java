package model;

public class Logradouro {
	int idLogradouro;
	String municipio;
	String estado;
	
	public Logradouro(String municipio, String estado) {
		idLogradouro = -1;
		this.municipio = municipio;
		this.estado = estado;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public int getIdLogradouro() {
		return idLogradouro;
	}
	
	public void setIdLogradouro(int idLogradouro) {
		this.idLogradouro = idLogradouro;
	}
	
	@Override
	public int hashCode() {
	    int result = 1;
	    result += ((municipio == null) ? 0 : municipio.hashCode());
	    result += ((estado == null) ? 0 : estado.hashCode());
	    return result;
	 }
	 
	@Override
	public boolean equals(Object logradouro) {
		if (this.getMunicipio().equals( ((Logradouro)logradouro).getMunicipio() ) &&
				this.getEstado().equals( ((Logradouro)logradouro).getEstado() )) {
			return true;
		}
		return false;
	}
}
