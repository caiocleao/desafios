package quartaQuestao;

/** Class implemented for polymorfism use **/
public class ColegaDeTrabalho {

	String nome;
	String empresa;
	String relacionamento;
	
	public ColegaDeTrabalho ( String nome, String empresa, String relacionamento ) {
		this.nome = nome;
		this.empresa = empresa;
		this.relacionamento = relacionamento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getRelacionamento() {
		return relacionamento;
	}

	public void setRelacionamento(String relacionamento) {
		this.relacionamento = relacionamento;
	}
	
}
