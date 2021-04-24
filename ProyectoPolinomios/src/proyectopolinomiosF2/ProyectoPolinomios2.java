
package proyectopolinomiosF2;
import java.awt.*;
import javax.swing.*;

public class ProyectoPolinomios2 
{

    public static void main(String[] args) 
    {
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 15));
        UIManager.put("OptionPane.buttonFont", new Font("Arial", Font.PLAIN, 15));
        Polvf2 A2, B2, C2;
        int dat, opcion = 0, Cent = 0, Opc = 0, elim = 0, VarX;
        boolean Res = true, Valor = true;
        float valorX;
        String menu="***MENU***\n" +
        "1-.Mostrar\n" +
        "2-.Evaluar\n" +
        "3-.Sumar\n" +
        "4-.Multiplicar\n" +
        "5-.Dividir\n" +
        "6-.Comparar\n" +
        "7-.Eliminar Primer Monomio Con Exponente Impar\n" +
        "8-.Cambiar Polinomios\n" +     
        "0-.Salir ";
        do{
            dat = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de monomios para el primer Polinomio"));
        }while(dat < 1);
        A2 = new Polvf2(dat);
        A2.ingresarTerminos();
        A2.OrdenTerminos();
        
        do{
            dat = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de monomios para el segundo Polinomio"));
        }while(dat < 1);
        B2 = new Polvf2(dat);
        B2.ingresarTerminos();
        B2.OrdenTerminos();
        do
        {
            try{
            opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));
            switch(opcion)
            {
                case 1: JOptionPane.showMessageDialog(null,"Datos del Polinomio 1\n"+A2.mostrar()
                        +"\nDatos del Polinomio 2\n"+B2.mostrar());
                break;
                case 2: do{
                            valorX = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el valor para X"));
                        }while(valorX < 1);
                        do{
                            VarX = Integer.parseInt(JOptionPane.showInputDialog("Digite 1 para el Polinomio A\n"
                                                                            + "Digite 2 para el Polinomio B"));
                        }while(VarX < 1 || VarX > 2);
                        switch(VarX)
                        {
                            case 1:
                                JOptionPane.showMessageDialog(null,"Polinomio A \n"+A2.mostrarEvaluado(valorX)
                                        +"\nEl resultado es: "+A2.evaluar(valorX));
                            break;
                            case 2:
                                JOptionPane.showMessageDialog(null,"Polinomio A \n"+B2.mostrarEvaluado(valorX)
                                        +"\nEl resultado es: "+B2.evaluar(valorX));
                            break;
                        }
                break;
                case 3: C2 = A2.sumar(B2);
                        JOptionPane.showMessageDialog(null,"Datos del Polinomio 1\n"+A2.mostrar()
                        +"\nDatos del Polinomio 2\n"+B2.mostrar()
                        +"\nDatos del Polinomio Sumado\n"+C2.mostrar());
                break;
                case 4: C2 = A2.multiplicar(B2);
                        JOptionPane.showMessageDialog(null,"Datos del Polinomio 1\n"+A2.mostrar()
                        +"\nDatos del Polinomio 2\n"+B2.mostrar()
                        +"\nDatos del Polinomio Multiplicado\n"+C2.mostrar());
                break;
                case 5: if(A2.getDato(1) >= B2.getDato(1))  
                        {
                            C2 = A2.dividir(B2);
                            JOptionPane.showMessageDialog(null,"Datos del Polinomio 1 (Dividendo)\n"+A2.mostrar()
                            +"\nDatos del Polinomio 2 (Divisor)\n"+B2.mostrar()
                            +"\nDatos del Polinomio Resultante (Cociente)\n"+C2.mostrar());                            
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "No se puede dividir, el Polinomio B tiene grado mayor");
                        }
                break;
                case 6: if(Cent == 0)
                        {
                            JOptionPane.showMessageDialog(null, "En este momento se compararán ambos Polinomios.");
                            Res = A2.comparar(B2);   
                            Cent = 1;
                        }
                        if(Res == true)
                        {
                            JOptionPane.showMessageDialog(null, "El Polinomio A:\n"+
                                    A2.mostrar()+"\n y el Polinomio B:\n"+
                                    B2.mostrar()+"\n son iguales.");
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "El Polinomio A:\n"+
                                    A2.mostrar()+"\n y el Polinomio B:\n"+
                                    B2.mostrar()+"\n no son iguales.");
                        }
                break;
                case 7: do{
                            Opc = Integer.parseInt(JOptionPane.showInputDialog("Digite 1 para revisar el Polinomio 1\n"
                                                                           + "Digite 2 para revisar el polinomio 2"));
                        }while(Opc < 1 || Opc > 2);
                        switch(Opc)
                        {
                            case 1:
                            if(A2.getDato(0) == 1)
                            {
                                JOptionPane.showMessageDialog(null, "No puede intentar eliminar el único monomio que está en el Polinomio.\n"
                                        +A2.mostrar());
                            }
                            else
                            {
                                do{
                                    Valor = A2.eliminar();
                                    if(Valor == false)
                                    {
                                        JOptionPane.showMessageDialog(null, "No hay exponentes impares en el Polinomio B");
                                        Valor = true;
                                    }
                                    /*if(Valor == false)
                                    {
                                        JOptionPane.showMessageDialog(null, "El exponente ingresado no está en el Polinomio A");
                                    }
                                    elim = Integer.parseInt(JOptionPane.showInputDialog("El Polinomio A:\n"+
                                                    A.mostrar()+"\n"+ 
                                                    "Digite el Monomio que desee eliminar a partir del exponente"));*/
                                    //mandar variable elim | Valor = A.eliminar(elim);
                                }while(Valor == false);
                            }
                            break;
                            case 2:
                            if(B2.getDato(0) == 1)
                            {
                                JOptionPane.showMessageDialog(null, "No puede intentar eliminar el único monomio que está en el Polinomio.\n"
                                        +B2.mostrar());
                            }
                            else
                            {   
                                do{
                                    Valor = B2.eliminar();
                                    if(Valor == false)
                                    {
                                        JOptionPane.showMessageDialog(null, "No hay exponentes impares en el Polinomio B");
                                        Valor = true;
                                    }
                                    /*if(Valor == false)
                                    {
                                        JOptionPane.showMessageDialog(null, "El exponente ingresado no está en el Polinomio B");
                                    }
                                    elim = Integer.parseInt(JOptionPane.showInputDialog("El Polinomio B:\n"+
                                                    B.mostrar()+"\n"+ 
                                                    "Digite el Monomio que desee eliminar a partir del exponente"));*/
                                     //mandar variable elim | Valor = B.eliminar(elim);*/
                                }while(Valor == false);
                            }
                            break;
                        }
                        Valor = true;
                break;
                case 8:do{
                            Opc = Integer.parseInt(JOptionPane.showInputDialog("Digite 1 para cambiar todo el Polinomio 1\n"
                                                                           + "Digite 2 para cambiar todo el polinomio 2\n"
                                                                           + "Digite 3 para cambiar ambos Polinomios"));
                        }while(Opc < 1 || Opc > 3);
                        if(Opc == 1 || Opc == 3)
                        {
                            do{
                                dat = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de monomios para el primer Polinomio"));
                            }while(dat < 1);
                            if(dat > A2.getDato(0))
                            {
                                for(int h = 0 ; h < dat - A2.getDato(0) + 1 ; h++)
                                {
                                    A2.ajustar();
                                }
                            }
                            else
                            {
                                for(int h = 0 ; h < A2.getDato(0) - dat; h++)
                                {
                                    A2.almacenarTermino(-1, -1, 2);
                                    A2.redimensionar(1);
                                }
                            }
                            A2.setDato(dat, 0);
                            A2.ingresarTerminos();
                            A2.OrdenTerminos();
                        }
                        if(Opc == 2|| Opc == 3)
                        {
                            do{
                                dat = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de monomios para el segundo Polinomio"));
                            }while(dat < 1);
                            if(dat > B2.getDato(0))
                            {
                                for(int h = 0 ; h < dat - B2.getDato(0) + 1 ; h++)
                                {
                                    B2.ajustar();
                                }
                            }
                            else
                            {
                                for(int h = 0 ; h < B2.getDato(0) - dat; h++)
                                {
                                    B2.almacenarTermino(-1, -1, 2);
                                    B2.redimensionar(1);
                                }
                            }
                            B2.setDato(dat, 0);
                            B2.ingresarTerminos();
                            B2.OrdenTerminos();
                        }
                        Cent = 0;
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
