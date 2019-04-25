package quartaQuestao;

/** Class implemented for polymorfism use **/
public class Amigo {
	
	String nome;
	int AnosDeAmizade;
	
	public Amigo ( String nome, int AnosDeAmizade ) {
		this.nome = nome;
		this.AnosDeAmizade = AnosDeAmizade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getAnosDeAmizade() {
		return AnosDeAmizade;
	}

	public void setAnosDeAmizade(int anosDeAmizade) {
		AnosDeAmizade = anosDeAmizade;
	}
	
}
