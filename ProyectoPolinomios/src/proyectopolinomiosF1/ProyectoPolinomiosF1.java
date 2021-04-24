
package proyectopolinomiosF1;
import java.awt.*;
import javax.swing.*;

public class ProyectoPolinomiosF1
{

    public static void main(String[] args) 
    {
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 15));
        UIManager.put("OptionPane.buttonFont", new Font("Arial", Font.PLAIN, 15));
        Polvf1 A, B, C;
        int grado, opcion = 0;
        float valorX;
        String menu="***MENU***\n" +
        "1-.Mostrar\n" +
        "2-.Evaluar\n" +
        "3-.Sumar\n" +
        "4-.Multiplicar\n" +
        "5-.Dividir\n" +
        "0-.Salir ";
        grado = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el grado del Polinomio 1"));
        A = new Polvf1(grado);
        A.ingresarTerminos();
        
        grado = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el grado del Polinomio 2"));
        B = new Polvf1(grado);
        B.ingresarTerminos();        
        
        
        do
        {
            try{
            opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));
            switch(opcion)
            {
                case 1: JOptionPane.showMessageDialog(null,"Datos del Polinomio 1\n"+A.mostrar()
                        +"\nDatos del Polinomio 2\n"+B.mostrar());
                break;
                case 2: valorX = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el valor para X"));
                        JOptionPane.showMessageDialog(null,"El resultado es "+A.evaluar(valorX));
                break;
                case 3: C = A.sumar(B);
                        JOptionPane.showMessageDialog(null,"Datos del Polinomio 1\n"+A.mostrar()
                        +"\nDatos del Polinomio 2\n"+B.mostrar()
                        +"\nDatos del Polinomio Suma\n"+C.mostrar());
                break;
                case 4: C = A.multiplicar(B);
                        C.mostrar();
                break;
                case 5: if(A.getDato(0) >= B.getDato(0))      
                        {
                            C = A.dividir(B);
                            JOptionPane.showMessageDialog(null,"Datos del Polinomio 1\n"+A.mostrar()
                            +"\nDatos del Polinomio 2\n"+B.mostrar()
                            +"\nDatos del Polinomio Dividido\n"+C.mostrar());                            
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "No se puede dividir");
                        }
                break;
                case 0: System.exit(0);
                default:JOptionPane.showMessageDialog(null,"Opción no válida");
            }//fin switch
            }catch(NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(null,"Opción no válida");
            }
        }while(opcion != 0);
    }
    
}
