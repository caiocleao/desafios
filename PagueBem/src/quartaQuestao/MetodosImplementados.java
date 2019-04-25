package quartaQuestao;

public class MetodosImplementados extends ClasseAbstrata {

	// Since this class extends ClasseAbstrata, we have access to the atributes defined there. 
	String mensage; 
	String nome; 
	String ramal;
	
	public MetodosImplementados ( String mensage, String nome, String ramal ) {
		this.mensage = mensage;
		this.nome = nome;
		this.ramal = ramal;
	}
	
	// Implemented methods only print in console. Used to show knowledge and applicability of abstract methods, hierarquy and polimorfism
	public void imprimirAssinatura( String mensage, String nome, String ramal, Amigo amigo ) {
		System.out.println("E-mail sent to " + groupOne );
	}
	
	public void imprimirAssinatura( String mensage, String nome, String ramal, ColegaDeTrabalho cdt ) {
		System.out.println("E-mail sent to " + groupTwo );
	}
	
	public void imprimirAssinatura( String mensage, String nome, String ramal, Parente parente ) {
		System.out.println("E-mail sent to " + groupThree );
	}	

	public void imprimirAssinatura( String mensage, String nome, String ramal ) {
		System.out.println("E-mail sent to default group");
	}
	
}
