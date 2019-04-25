package quartaQuestao;

/** Class implemented for polymorfism use **/
public class Parente {

	String nome;
	String relacionamento;
	
	public Parente ( String nome, String relacionamento ) {
		this.nome = nome;
		this.relacionamento = relacionamento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRelacionamento() {
		return relacionamento;
	}

	public void setRelacionamento(String relacionamento) {
		this.relacionamento = relacionamento;
	}
	
}
