/*
    Código hecho por: Carlos Alberto González Agudelo
    Fecha: 2021 - 01 
    Politécnico Colombiano Jaime Isaza Cadavid
    Ingeniería Informática - Algoritmos y Programación 3
*/
package proyectopolinomios;
import java.awt.*;
import javax.swing.*;

public class ProyectoPolinomios 
{
    static int[] validar = new int[3];
    
    public static void main(String[] args) 
    {
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 16));
        UIManager.put("OptionPane.buttonFont", new Font("Arial", Font.PLAIN, 16));
        menuppal();
    }
    
    //Método para el menú principal
    public static void menuppal()
    {
        Polvf1 A = null, B= null, C = null;
        Polvf2 A2 = null, B2 = null, C2 = null;
        Polista A3 = null, B3 = null, C3 = null;
        String var = "";
        int opcion=0;
        int grado1 = 0, dat = 0;
        String menu="***MENU PRINCIPAL***\n" +
        "1- Polinomio en vector forma 1\n" +
        "2- Polinomio en vector forma 2\n" +
        "3- Polinomio en lista\n" +
        "4- Mezcla de Polinomios\n" +      
        "0- Salir";
        do
        {
            try{
            opcion=Integer.parseInt(JOptionPane.showInputDialog(menu));
            switch(opcion)
            {
                case 1:
                    do{
                        if(validar[0] == 1)
                        {
                            var = JOptionPane.showInputDialog("¿Desea ingresar nuevos Polinomios forma 1? Digite S/N");
                            if(var.equalsIgnoreCase("s"))
                            {
                                validar[0] = 0;
                            }
                            else
                            {
                                menuPolvf1(A, B, C);      
                            }
                        }
                        else if(validar[0] == 0)
                        {
                            A = null; B = null; C = null;
                            do{
                               grado1=Integer.parseInt(JOptionPane.showInputDialog("Ingrese el grado del polinomio 1")); 
                            }while(grado1 < 1);

                            A=new Polvf1(grado1); 
                            A.ingresarTerminos();

                            do{
                               grado1=Integer.parseInt(JOptionPane.showInputDialog("Ingrese el grado del polinomio 2")); 
                            }while(grado1 < 1);
                            B=new Polvf1(grado1) ; 
                            B.ingresarTerminos();
                            
                            validar[0] = 1;
                            var = "N";
                            menuPolvf1(A, B, C);
                        }
                    }while(var.equalsIgnoreCase("S"));
                    
                break;
                case 2: 
                    do{
                        if(validar[1] == 1)
                        {
                            var = JOptionPane.showInputDialog("¿Desea ingresar nuevos Polinomios forma 2? Digite S/N");
                            if(var.equalsIgnoreCase("s"))
                            {
                                validar[1] = 0;
                            }
                            else
                            {
                                menuPolvf2(A2, B2, C2);
                            }
                        }
                        else if(validar[1] == 0)
                        {
                            A2 = null; B2 = null; C2 = null;
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
                            
                            validar[1] = 1;
                            var = "N";
                            menuPolvf2(A2, B2, C2);
                        }
                    }while(var.equalsIgnoreCase("S"));
                    
                break;
                case 3: 
                    do{
                        if(validar[2] == 1)
                        {
                            var = JOptionPane.showInputDialog("¿Desea ingresar nuevos Polinomios tipo lista? Digite S/N");
                            if(var.equalsIgnoreCase("s"))
                            {
                                validar[2] = 0;
                            }
                            else
                            {
                                menuPolista(A3, B3, C3); 
                            }
                        }
                        else if(validar[2] == 0)
                        {
                            A3 = null; B3 = null; C3 = null;
                            
                            A3 = new Polista();
                            A3.ingresarTerminos();

                            JOptionPane.showMessageDialog(null, "Pasando al Polinomio 2...");

                            B3 = new Polista();
                            B3.ingresarTerminos();
                            
                            validar[2] = 1;
                            var = "N";
                            menuPolista(A3, B3, C3); 
                        }
                    }while(var.equalsIgnoreCase("S"));
                break;
                case 4:
                    menuPolCombinados();
                break;
                case 0:System.exit(0);
                break;
                default: JOptionPane.showMessageDialog(null,"opcion no válida"); 
            }//fin switch
            }catch(NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(null,"opcion no válida"); 
            }
        }while(opcion!=0);
    }
    
    //Método para el menú de polinomio en vector forma 1
    public static void menuPolvf1(Polvf1 A, Polvf1 B, Polvf1 C)
    {
        int opcion=-1;
        float valorx;
        String menu="***MENU POLVF1***\n" +
        "1- Mostrar\n" +
        "2- Evaluar\n" +
        "3- Sumar\n" +
        "4- Multiplicar\n" +
        "5- Dividir\n" +
        "6- Ir al menú ppal\n" +
        "0- Salir"; 
        
        do
        { 
            try{
            opcion=Integer.parseInt(JOptionPane.showInputDialog(menu));
            switch(opcion)
            {
                case 1:  JOptionPane.showMessageDialog(null,"Datos del polinomio 1\n"+A.mostrar()
                                            + "\nDatos del polinomio 2\n"+B.mostrar());
                        A.mostrar();B.mostrar();
                break;
                case 2:valorx=Float.parseFloat(JOptionPane.showInputDialog("Ingrese el valor para x"));
                     JOptionPane.showMessageDialog(null,"El resultado es "+ A.evaluar(valorx));
                break;
                case 3:  C = A.sumar(B);
                        JOptionPane.showMessageDialog(null,"Datos del Polinomio 1\n"+A.mostrar()
                        +"\nDatos del Polinomio 2\n"+B.mostrar()
                        +"\nDatos del Polinomio Suma\n"+C.mostrar());
                break;
                case 4: C=A.multiplicar(B);
                        JOptionPane.showMessageDialog(null,"Datos del polinomio 1\n"+A.mostrar()
                                            +"\nDatos del polinomio 2\n"+B.mostrar()
                                            +"\nDatos del polinomio multiplicacion\n"+C.mostrar());
                break;
                case 5: if( A.getDato(0) >= B.getDato(0) )
                        {
                                C=A.dividir(B);
                                JOptionPane.showMessageDialog(null,"Datos del polinomio 1\n"+A.mostrar()
                                            +"\nDatos del polinomio 2\n"+B.mostrar()
                                            +"\nDatos del polinomio division\n"+C.mostrar());
                        }
                        else
                        {
                             JOptionPane.showMessageDialog(null,"No se puede dividir");
                        }
                break;
                case 6: opcion = 0;
                break;
                case 0:System.exit(0);
                    break;
                default: JOptionPane.showMessageDialog(null,"opcion no válida"); 
           }//fin switch
           }catch(NumberFormatException ex)
           {
               JOptionPane.showMessageDialog(null,"opcion no válida"); 
           }
       }while(opcion!=0);         

    }
    //Método para el menú de polinomio en vector forma 2
    public static void menuPolvf2(Polvf2 A, Polvf2 B, Polvf2 C)
    {
       
        int opcion = 0, Cent = 0, Opc = 0, /*elim = 0,*/ VarX; 
        boolean Res = true, Valor = true;
        float valorX;
        String menu2="***MENU POLVF2***\n" +
        "1-.Mostrar\n" +
        "2-.Evaluar\n" +
        "3-.Sumar\n" +
        "4-.Multiplicar\n" +
        "5-.Dividir\n" +
        "6-.Comparar\n" +
        "7-.Eliminar Primer Monomio Con Exponente Impar\n" +
        "8-.Ir al menú ppal\n" +
        "0-.Salir ";

        do
        {
            try{
            opcion = Integer.parseInt(JOptionPane.showInputDialog(menu2));
            switch(opcion)
            {
                case 1: JOptionPane.showMessageDialog(null,"Datos del Polinomio 1\n"+A.mostrar()
                        +"\nDatos del Polinomio 2\n"+B.mostrar());
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
                                JOptionPane.showMessageDialog(null,"Polinomio A \n"+A.mostrarEvaluado(valorX)
                                        +"\nEl resultado es: "+A.evaluar(valorX));
                            break;
                            case 2:
                                JOptionPane.showMessageDialog(null,"Polinomio A \n"+B.mostrarEvaluado(valorX)
                                        +"\nEl resultado es: "+B.evaluar(valorX));
                            break;
                        }
                break;
                case 3: C = A.sumar(B);
                        JOptionPane.showMessageDialog(null,"Datos del Polinomio 1\n"+A.mostrar()
                        +"\nDatos del Polinomio 2\n"+B.mostrar()
                        +"\nDatos del Polinomio Sumado\n"+C.mostrar());
                break;
                case 4: C = A.multiplicar(B);
                        JOptionPane.showMessageDialog(null,"Datos del Polinomio 1\n"+A.mostrar()
                        +"\nDatos del Polinomio 2\n"+B.mostrar()
                        +"\nDatos del Polinomio Multiplicado\n"+C.mostrar());
                break;
                case 5: if(A.getDato(1) >= B.getDato(1))  
                        {
                            C = A.dividir(B);
                            JOptionPane.showMessageDialog(null,"Datos del Polinomio 1 (Dividendo)\n"+A.mostrar()
                            +"\nDatos del Polinomio 2 (Divisor)\n"+B.mostrar()
                            +"\nDatos del Polinomio Resultante (Cociente)\n"+C.mostrar());                            
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "No se puede dividir, el Polinomio B tiene grado mayor");
                        }
                break;
                case 6: if(Cent == 0)
                        {
                            JOptionPane.showMessageDialog(null, "En este momento se compararán ambos Polinomios.");
                            Res = A.comparar(B);   
                            Cent = 1;
                        }
                        if(Res == true)
                        {
                            JOptionPane.showMessageDialog(null, "El Polinomio A:\n"+
                                    A.mostrar()+"\n y el Polinomio B:\n"+
                                    B.mostrar()+"\n son iguales.");
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "El Polinomio A:\n"+
                                    A.mostrar()+"\n y el Polinomio B:\n"+
                                    B.mostrar()+"\n no son iguales.");
                        }
                break;
                case 7: do{
                            Opc = Integer.parseInt(JOptionPane.showInputDialog("Digite 1 para revisar el Polinomio 1\n"
                                                                           + "Digite 2 para revisar el polinomio 2"));
                        }while(Opc < 1 || Opc > 2);
                        switch(Opc)
                        {
                            case 1:
                            if(A.getDato(0) == 1)
                            {
                                JOptionPane.showMessageDialog(null, "No puede intentar eliminar el único monomio que está en el Polinomio.\n"
                                        +A.mostrar());
                            }
                            else
                            {
                                do{
                                    Valor = A.eliminar();
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
                            if(B.getDato(0) == 1)
                            {
                                JOptionPane.showMessageDialog(null, "No puede intentar eliminar el único monomio que está en el Polinomio.\n"
                                        +B.mostrar());
                            }
                            else
                            {   
                                do{
                                    Valor = B.eliminar();
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
                case 8: opcion = 0;
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
    //Método para el menú de polinomio en lista
    public static void menuPolista(Polista A, Polista B, Polista C)
    {
        int opcion=-1;
        float valorx;
        
        String menu="***MENU POLISTA***\n" +
        "1- Mostrar\n" +
        "2- Evaluar\n" +
        "3- Sumar\n" +
        "4- Multiplicar\n" +
        "5- Dividir\n" +
        "6- Ir al menú ppal\n" +
        "0- Salir";
        do
        { 
            try{
            opcion=Integer.parseInt(JOptionPane.showInputDialog(menu));
            switch(opcion)
            {
                case 1:JOptionPane.showMessageDialog(null,"Datos del polinomio 1\n"+A.mostrar()
                                            + "\nDatos del polinomio 2\n"+B.mostrar());
                   A.mostrar();B.mostrar();
                break;
                case 2:valorx=Float.parseFloat(JOptionPane.showInputDialog("Ingrese el valor para x"));
                   JOptionPane.showMessageDialog(null,"El resultado es "+ A.evaluar(valorx));
                break;
                case 3: C=A.sumar(B);
                       JOptionPane.showMessageDialog(null,"Datos del polinomio 1\n"+A.mostrar()
                                            +"\nDatos del polinomio 2\n"+B.mostrar()
                                            +"\nDatos del polinomio Suma\n"+C.mostrar());
                break;
                case 4: C=A.multiplicar(B);
                       JOptionPane.showMessageDialog(null,"Datos del polinomio 1\n"+A.mostrar()
                                            +"\nDatos del polinomio 2\n"+B.mostrar()
                                            +"\nDatos del polinomio multiplicacion\n"+C.mostrar());
                break;
                case 5: if(A.getCab().getExp() >= B.getCab().getExp())
                        {
                            C = A.dividir(B);
                            JOptionPane.showMessageDialog(null,"Datos del Polinomio 1 (Dividendo)\n"+A.mostrar()
                            +"\nDatos del Polinomio 2 (Divisor)\n"+B.mostrar()
                            +"\nDatos del Polinomio Resultante (Cociente)\n"+C.mostrar());                            
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "No se puede dividir, el Polinomio B tiene grado mayor");
                        }
                break;
                case 6: opcion = 0;
                break;
                case 0:System.exit(0);
                    break;
                default: JOptionPane.showMessageDialog(null,"Opcion no válida"); 
           }//fin switch
           }catch(NumberFormatException ex)
           {
               JOptionPane.showMessageDialog(null,"Opcion no válida"); 
           }
       }while(opcion!=0);
    }

    //Método para el menú de polinomio combinados
    public static void menuPolCombinados()
    {
        boolean Res;
        int opcion1, num, grado1, dat;
        Polvf1 AA = null; Polvf2 BB = null; Polista CC = null;
        String menuespecial="***SUB-MENÚ***\n" +
        "1- Polista = Polvf1 * Polvf2\n" +
        "2- Polista = Polvf1 + Polvf2\n" +
        "3- Polvf2 = Polista / Polvf1\n" +
        "4- Polvf1 = Polvf2 / Polista\n" +
        "5- Polvf2 = Polista * Polvf1\n" +
        "6- Comparar Polvf1 con Polvf2\n" +     
        "7- Volver al menú ppal\n" +   
        "0- Salir del programa"; 

        try
        {
            do{
                opcion1=Integer.parseInt(JOptionPane.showInputDialog(menuespecial));
            }while(opcion1 < 0 || opcion1 > 7);
            switch(opcion1)
            {
                case 1:
                    do{
                       grado1=Integer.parseInt(JOptionPane.showInputDialog("Ingrese el grado del polinomio 1 forma 1")); 
                    }while(grado1 < 1);
                    AA = new Polvf1(grado1); 
                    AA.ingresarTerminos();       

                    do{
                        dat = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de monomios para el polinomio 2 forma 2"));
                    }while(dat < 1);
                    BB = new Polvf2(dat);
                    BB.ingresarTerminos();
                    BB.OrdenTerminos(); 
                    CC = AA.multiplicarPolvf1ConPolvf2(BB);
                    JOptionPane.showMessageDialog(null,"Datos del polinomio 1\n"+AA.mostrar()
                                                                +"\nDatos del polinomio 2\n"+BB.mostrar()
                                                                +"\nDatos del polinomio Multiplicación\n"+CC.mostrar());                                    
                break;
                case 2:
                    do{
                       grado1=Integer.parseInt(JOptionPane.showInputDialog("Ingrese el grado del polinomio 1 forma 1")); 
                    }while(grado1 < 1);
                    AA = new Polvf1(grado1); 
                    AA.ingresarTerminos();

                    do{
                        dat = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de monomios para el polinomio 2 forma 2"));
                    }while(dat < 1);
                    BB = new Polvf2(dat);
                    BB.ingresarTerminos();
                    BB.OrdenTerminos();      
                    CC = AA.sumarPolvf1ConPolvf2(BB);
                                                     JOptionPane.showMessageDialog(null,"Datos del polinomio 1\n"+AA.mostrar()
                                                                +"\nDatos del polinomio 2\n"+BB.mostrar()
                                                                +"\nDatos del polinomio Suma\n"+CC.mostrar());           
                break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Pasando al Polinomio 1 forma lista...");
                    CC = new Polista();
                    CC.ingresarTerminos();
                    do{
                       grado1=Integer.parseInt(JOptionPane.showInputDialog("Ingrese el grado del polinomio 2 forma 1")); 
                    }while(grado1 < 1);
                    AA = new Polvf1(grado1);
                    AA.ingresarTerminos();
                    if(CC.getCab().getExp() >= AA.getDato(0))
                    {                  
                        BB = CC.PolistaDivididoconPolvf1(AA);
                                                         JOptionPane.showMessageDialog(null,"Datos del polinomio 1\n"+CC.mostrar()
                                                                    +"\nDatos del polinomio 2\n"+AA.mostrar()
                                                                    +"\nDatos del polinomio Dividido\n"+BB.mostrar()); 
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "El grado del Polinomio 2 forma 1 es mayor que el grado del Polinomio 1 tipo lista");
                    }
                break;
                case 4:
                    do{
                        dat = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de monomios para el polinomio 1 forma 2"));
                    }while(dat < 1);
                    BB = new Polvf2(dat);
                    BB.ingresarTerminos();
                    BB.OrdenTerminos(); 
                    
                    JOptionPane.showMessageDialog(null, "Pasando al Polinomio 2 forma lista...");
                    CC = new Polista();
                    CC.ingresarTerminos();
                    
                    if(BB.getDato(1) >= CC.getCab().getExp())
                    {
                        AA = BB.Polvf2DivididoconPolista(CC);
                                                         JOptionPane.showMessageDialog(null,"Datos del polinomio 1\n"+BB.mostrar()
                                                                    +"\nDatos del polinomio 2\n"+CC.mostrar()
                                                                    +"\nDatos del polinomio Dividido\n"+AA.mostrar()); 
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "El grado del Polinomio 2 forma lista es mayor que el grado del Polinomio 1 forma 2");
                    }
                break;
                case 5:
                    JOptionPane.showMessageDialog(null, "Pasando al Polinomio 1 forma lista...");
                    CC = new Polista();
                    CC.ingresarTerminos();

                    do{
                       grado1=Integer.parseInt(JOptionPane.showInputDialog("Ingrese el grado del polinomio 2 forma 1")); 
                    }while(grado1 < 1);
                    AA = new Polvf1(grado1); 
                    AA.ingresarTerminos();
                    
                    BB = CC.PolistaMultiplicadoconPolvf1(AA);
                                                     JOptionPane.showMessageDialog(null,"Datos del polinomio 1\n"+CC.mostrar()
                                                                +"\nDatos del polinomio 2\n"+AA.mostrar()
                                                                +"\nDatos del polinomio Multiplicación\n"+BB.mostrar()); 
                break;
                case 6:
                    do{
                       grado1=Integer.parseInt(JOptionPane.showInputDialog("Ingrese el grado del polinomio 1 forma 1")); 
                    }while(grado1 < 1);
                    AA = new Polvf1(grado1); 
                    num = AA.ingresarTerminos();
                    do{
                        dat = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de monomios para el polinomio 2 forma 2"));
                    }while(dat < 1);
                    BB = new Polvf2(dat);
                    BB.ingresarTerminos();
                    BB.OrdenTerminos();   
                    Res = AA.compararPolvf1conPolvf2(BB, num);
                    if(Res == true)
                    {
                        JOptionPane.showMessageDialog(null, "El Polinomio 1 forma 1:\n"+
                                AA.mostrar()+"\n y el Polinomio 2 forma 2:\n"+
                                BB.mostrar()+"\n son iguales.");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "El Polinomio 1 forma 1:\n"+
                                AA.mostrar()+"\n y el Polinomio 2 forma 2:\n"+
                                BB.mostrar()+"\n no son iguales.");
                    }
                break;
                case 7:
                    menuppal();
                break;
                case 0:
                    System.exit(0);
            }
        }catch(NumberFormatException ex)
        {
            JOptionPane.showMessageDialog(null,"opcion no válida"); 
        }
    }

}