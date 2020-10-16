import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Calculadora extends Frame implements ActionListener {

	public static void main (String args[]) {
		Calculadora c = new Calculadora("Calculadora",300,400);	
		c.setVisible(true);
		c.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				System.exit(0); 
				} 
			});
	}
	
	//Array de botones
	public String botones[] = {"1","2","3","+","4","5","6","-","7","8","9","*","0","/","=","AC"};
	//Localizacion del resultado
	public TextField calculo;

	public int cifras[] = new int[20];
	public int operaciones[] = new int[20];
	 //Contador
	public int c;

	public Calculadora (String title, Integer width, Integer height) {
		//Ajuste de la ventana a su tamaño
		setSize(width,height);
		setTitle("Calculadora DAM");
		setLayout(new GridLayout(2,1));
		//Se añde al textfield el numero 0 incial
		calculo = new TextField("0");
		add(calculo);

		cifras[0] = 0;
		
		operaciones[0] = 0;
		
		c = 1;
		//Los botones numeros, operaciones y borrar (AC)
		Panel panelBotones = new Panel(new GridLayout(4,4));
		for (int i=0;i<botones.length;i++) {
			//Creacion de la variable b dond se almacenaran la lista de botones creados en el array
			Button b = new Button(botones[i]);
			//ActionListener para llamar a la accion de cada boton
			b.addActionListener(this);
			//Añadir los botones guardados en b al panel de botones
			panelBotones.add(b);
		}
		//Añadir el panel de botones
		add(panelBotones);
	}

	public void actionPerformed (ActionEvent e) {
		//Se añade al String Operacion la funcion de si la accion cae sobre un boton (getSource)
		//Y se obtiene el valor del boton con getLabel
		String operacion = ((Button)e.getSource()).getLabel();
		//Se crea la funcion ejecutarOrden donde, por parametro de botones, luego se usara
		//para la creacion de la clase donde se ordenaran el valor de los botones pulsados
		ejecutaOrden(operacion);
	}

	
	//Metodo donde se localizan las operaciones matematicas
	public int ejecutarOp () {
		int resultado = cifras[0];
		for (int i=1;i<=c;i++) {
				 if (operaciones[i-1]==0) resultado += cifras[i];
				 
			else if (operaciones[i-1]==1) resultado -= cifras[i];
				 
			else if (operaciones[i-1]==2) resultado *= cifras[i];
				 
			else if (operaciones[i-1]==3) resultado /= cifras[i];
		}
		return resultado;
	}
	
	public void ejecutaOrden (String orden) {
		
		if (ordenNumerica(orden)) {
			//Si el valor del boton es igual 0 se añadira al tf de calculo 
			if (((String)calculo.getText()).equals("0")) calculo.setText(orden);
			//Si es contrario 0 se seguira ejecutanto el bucle if con los siguientes botones 
			else calculo.setText(calculo.getText()+orden);
			//Apartir de aqui se guardaran en las  posiciones las cuales se vayan pulsando
		} else if (orden.equals("+")) {
			cifras[c] = Integer.parseInt(calculo.getText());
			operaciones[c] = 0;
			c+=1;
			calculo.setText("");
		} else if (orden.equals("-")) {
			cifras[c] = Integer.parseInt(calculo.getText());
			operaciones[c] = 1;
			c+=1;
			calculo.setText("");
		} else if (orden.equals("*")) {
			cifras[c] = Integer.parseInt(calculo.getText());
			operaciones[c] = 2;
			c+=1;
			calculo.setText("");
		} else if (orden.equals("/")) {
			cifras[c] = Integer.parseInt(calculo.getText());
			operaciones[c] = 3;
			c+=1;
			calculo.setText("");
			//Al pulsar = se ejecutara el metodo donde se guardan las operaciones 
		} else if (orden.equals("=")) {
			cifras[c] = Integer.parseInt(calculo.getText());
			calculo.setText(String.valueOf(ejecutarOp()));
			c = 0;
			cifras[0] = Integer.parseInt(calculo.getText());
		} else if (orden.equals("AC")) {
			cifras[0] = 0;
			c = 0;
			calculo.setText("");
		}
	}
	//Al pasar valores String a Int se producira un error por lo que se tiene que capturar la excepcion NumberFormatException 
	public boolean ordenNumerica (String orden) {
		try {
			Integer.parseInt(orden);
			return true;
		} catch (NumberFormatException abc) {
			return false;
		}
	}


}