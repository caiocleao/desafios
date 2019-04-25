package quadrado;

class Ponto {
	
	private float x;
	private float y;
	public Ponto ( float x1, float y1) {
		x = x1;
		y = y1;
	}
}

public class Quadrado {
	
	private Ponto verticeXY;
	private float lado;
	
	public Quadrado ( float l) {
		verticeXY = null;
		lado = l;
	}
	
	public float calcularArea() {
		return (lado*lado);
	}
	
	public void createPonto ( float x, float y ) {
		
		Ponto p = new Ponto (x, y);
		this.verticeXY = p;
		
	}
	
}
