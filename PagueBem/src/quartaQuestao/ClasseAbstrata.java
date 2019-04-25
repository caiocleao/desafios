package quartaQuestao;

public abstract class ClasseAbstrata {

	// Issue solved using abstract and hierarchy concepts. Not Polimorfism.
	String groupOne = "Amigo";
	String groupTwo = "Colega de Trabalho";
	String groupThree = "Parente";
	
	// Used to demonstrate polymorfism
	Amigo amigo;
	ColegaDeTrabalho cdt;
	Parente parente;
	
	public abstract void imprimirAssinatura( String mensage, String nome, String ramal, Amigo amigo );
	public abstract void imprimirAssinatura( String mensage, String nome, String ramal, ColegaDeTrabalho cdt );
	public abstract void imprimirAssinatura( String mensage, String nome, String ramal, Parente parente );
	public abstract void imprimirAssinatura( String mensage, String nome, String ramal );
	
	public void sendMail ( String mensage, String nome, String ramal, String destinatario ) {
		
		// Can be switched for switch-case if it is preferable.
		if ( destinatario.equals(groupOne)) {
			amigo = new Amigo ( nome, 10);
			imprimirAssinatura( mensage, nome, ramal, amigo );
		} else if ( destinatario.equals(groupTwo) ) {
			cdt = new ColegaDeTrabalho (nome, "Colega", "UsadoParaTestes");
			imprimirAssinatura( mensage, nome, ramal, cdt );
		} else if ( destinatario.equals(groupThree) ) {
			parente = new Parente(nome, "PrimoInventado");
			imprimirAssinatura( mensage, nome, ramal, parente );
		}
		
	}
	
}
