package modulo;

public class Calculadora {
	public double dividir(double a, double b) throws Exception{
		if(b==0) {
			throw new Exception ("No se puede dividir entre 0");
		}
		else {
			return a/b;
		}
	}
}
