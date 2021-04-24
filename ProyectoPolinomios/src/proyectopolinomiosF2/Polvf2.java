
package proyectopolinomiosF2;
import javax.swing.JOptionPane;

public class Polvf2 
{
    private int tamano; //quitar private a los 2 pa usarlos en un mismo proyecto (pasarlo)
    private float vec[];
    
    //Método Constructor
    public Polvf2(int datos)
    {
        tamano = (datos * 2)+ 1;
        vec = new float[tamano];
        vec[0] = datos;
    }
    
    //Método para obtener un dato
    public float getDato(int pos)
    {
        return(vec[pos]);
    }
    
    //Método para asignar un dato
    public void setDato(float dato, int pos)
    {
        vec[pos] = dato;
    }
    
    //Método para obtener el tamaño del vector
    public int getTamano()
    {
        return(tamano);
    }
    
    //Método para mostrar lo evaluado en X
    public String mostrarEvaluado(float exp)
    {
        int k;
        String salida = "<html>";
        for(k = 1 ; k < vec[0] * 2 ; k+=2)
        {
            if(vec[k+1] >= 0 && k > 1)
            {
                salida = salida+" + ";
            }
            if((int)vec[k+1] > vec[k+1])
            {
                salida = salida+vec[k+1]+"("+(int)exp+")<sup>"+((int)vec[k])+"</sup>";
            }
            else
            {
                salida = salida+(int)vec[k+1]+"("+(int)exp+")<sup>"+((int)vec[k])+"</sup>";
            }
        }
        salida = salida + "</html>";
        return(salida);
    }
    
    //método para mostrar
    public String mostrar()
    {
        int k;
        String salida = "<html>";
        for(k = 1 ; k < vec[0] * 2 ; k+=2)
        {
            if(vec[k+1] >= 0 && k > 1)
            {
                salida = salida+" + ";
            }
            if((int)vec[k+1] < vec[k+1] || (int)vec[k+1] > vec[k+1])
            {
                salida = salida+vec[k+1]+"X<sup>"+((int)vec[k])+"</sup>";
            }
            else
            {
                salida = salida+(int)vec[k+1]+"X<sup>"+((int)vec[k])+"</sup>";
            }
        }
        salida = salida + "</html>";
        return(salida);
    }
    
    //Método para almacenar un término
    public void almacenarTermino(float coef, int exp, int pos)
    {
        vec[pos] = exp;
        vec[pos+1] = coef;
    }
    
    //Método para ingresar los términos
    public void ingresarTerminos()
    {
        float coef, Cent = 0;
        int exp, r;
        float []matriz = new float[(int)vec[0]];
        int cont = 1;
        for(int i = 0 ; i < vec[0] ; i++)
        {
            do{
                coef = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el coeficiente (diferente de 0) del término "+(i+1)+" de "+(int)vec[0]));
            }while(coef == 0);
            do{
                if(Cent == 1)
                {
                    JOptionPane.showMessageDialog(null,"El exponente ya está en el Polinomio.");
                }
                Cent = 0;
                exp = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el exponente del término "+(i+1)+" de "+(int)vec[0]));
                if(exp == 0)
                {
                    exp = 2147483647;
                }
                if(i == 0)
                {
                    matriz[i] = exp;
                }
                else if(i > 0)
                {
                    r = 0;
                    while(r <= i && Cent == 0)
                    {
                        if(matriz[r] == exp)
                        {
                            Cent = 1;
                        }
                        r++;
                    }
                    if(Cent != 1)
                    {
                        if(exp >= 0)
                        {   
                            matriz[i] = exp;
                        }
                        Cent = 2; 
                    }
                }
            }while(exp < 0 || Cent == 1);       
            if(exp == 2147483647)
            {
                exp = 0;
            }
            this.almacenarTermino(coef, exp, cont);
            cont += 2;
        }
    }
    
    //Método para ordenar descendentemente
    public void OrdenTerminos()
    {
        int Cont1, ContAux, auxExp;
        float auxCoef;
        if(vec[0] > 1)
        {
            for(int i = 0 ; i < vec[0] - 1 ; i++)
            {
                ContAux = 1;
                Cont1 = 2;
                for(int j = 0 ; j < vec[0] - 1 ; j++)
                {
                    if(vec[ContAux] < vec[ContAux+2])
                    {
                        auxExp = (int)vec[Cont1-1];
                        auxCoef = vec[Cont1];
                        vec[Cont1-1] = vec[Cont1+1];
                        vec[Cont1] = vec[Cont1+2];
                        vec[Cont1+1] = auxExp;
                        vec[Cont1+2] = auxCoef;
                    }
                    if(vec[ContAux+1] < vec[ContAux+3] && vec[ContAux] == vec[ContAux+2]) //esto para en caso de poder ser coeficientes repetidos
                    {
                        auxExp = (int)vec[Cont1-1];
                        auxCoef = vec[Cont1];
                        vec[Cont1-1] = vec[Cont1+1];
                        vec[Cont1] = vec[Cont1+2];
                        vec[Cont1+1] = auxExp;
                        vec[Cont1+2] = auxCoef;
                    }
                    Cont1 += 2;
                    ContAux += 2;
                }
            }
        }
    }
    
    //Método para evaluar
    public float evaluar(float x)
    {
        float resultado = 0;
        for(int k = 1 ; k < vec[0] * 2 ; k+=2)
        {
            resultado = resultado + vec[k+1] * (float)Math.pow(x, vec[k]);
        }
        return(resultado);
    }
    
    //Método para comparar
    public boolean comparar(Polvf2 B)
    {
        boolean r = true;
        int Cont = 2;
        for(int i = 0 ; i < vec[0] ; i++) 
        {
            if(vec[Cont-1] == B.getDato(Cont-1))
            {
                if(!(vec[Cont] == B.getDato(Cont)))
                {
                    r = false;
                    i = (int)vec[0];
                }
            }
            else
            {
                r = false;
                i = (int)vec[0];
            }
            Cont += 2;
        }
        return(r);
    }

    //Método para sumar 2 polinomios
    public Polvf2 sumar(Polvf2 B)
    {
        int Cont = 1, i = 0, Cont2;
        
        Polvf2 R = new Polvf2((int)(vec[0] + B.getDato(0)));
        R.setDato(vec[0] + B.getDato(0), 0);
        
        while(i < vec[0])
        {
            R.almacenarTermino(vec[Cont+1], (int)vec[Cont], Cont);
            Cont+=2;
            i++;
        }
        i = 0;
        Cont2 = 1;
        while(i < B.getDato(0))
        {
            R.setDato(B.getDato(Cont2), Cont);
            R.setDato(B.getDato(Cont2+1), Cont+1);
            Cont+=2;
            Cont2+=2;
            i++;
        }
        R.suma();
        R.OrdenTerminos();
        return(R); 
        
        /* MÉTODO ALTERNO PARA 2 ARRAYS llenos
        int Cont = 0, Cont2, ContTotal, Cent, ContAux = 1;
        
        ContTotal = (int)(vec[0] + B.getDato(0));
        for(int i = 1 ; i <= vec[0] ; i++)
        {
            Cont2 = 0;
            for(int h = 1 ; h <= B.getDato(0) ; h++)
            {
                if(vec[i+Cont] == B.getDato(h+Cont2))
                {
                    ContTotal--;
                }
                Cont2++;
            }
            Cont++;
        }
        Polvf2 R = new Polvf2(ContTotal); 
        Cont = 0;
        for(int i = 1 ; i <= B.getDato(0) ; i++)
        {
            if(i == 1)
            {
                for(int g = 0 ; g < (int)vec[0] ; g++)
                {
                    R.setDato(vec[ContAux], ContAux);
                    R.setDato(vec[ContAux+1], ContAux+1);
                    ContAux += 2;
                }
            }
            Cent = 0;
            Cont2 = 0;
            for(int h = 1 ; h <= vec[0] ; h++)
            {
                if(B.getDato(i+Cont) == R.getDato(h+Cont2))
                {
                    R.setDato((B.getDato(i+Cont+1) + R.getDato(h+Cont2+1)), h+Cont2+1);
                    Cent = 1;
                    h = (int)B.getDato(0);
                }
                Cont2++;
            }
            if(Cent == 0)
            {
                R.setDato(B.getDato(i+Cont), ContAux);
                R.setDato(B.getDato(i+Cont+1), ContAux+1);  
                ContAux += 2;
            }
            Cont++;
        }*/
    }

    //Método para multiplicar dos polinomios
    public Polvf2 multiplicar(Polvf2 B)
    {
        int Cont = 0, Cont2, Pos = 1;
        
        Polvf2 R = new Polvf2((int)(vec[0] * B.getDato(0)));
 
        for(int i = 1 ; i <= B.getDato(0) ; i++)
        {
            Cont2 = 0;
            for(int h = 1 ; h <= (int)vec[0] ; h++)
            {
                R.setDato(B.getDato(i+Cont) + vec[h+Cont2], Pos);
                R.setDato(B.getDato(i+Cont+1) * vec[h+Cont2+1], Pos+1);
                Pos+=2;
                Cont2++;
            }
            Cont++;
        }
        R.OrdenTerminos();
        R.suma();
        return(R);
    }
    
    //Método para sumar lo multiplicado
    public void suma()
    {
        int auxExp;
        for(int g = 0 ; g < 3 ; g++)
        {
            for(int i = 1 ; i < ((vec[0] * 2) + 1)   ; i+=2)
            {
                auxExp = (int)vec[i];
                for(int r = i+2 ; r < ((vec[0] * 2) + 1) ; r+=2)
                {
                    if(vec[r] == auxExp)
                    {
                        vec[i+1] = vec[i+1] + vec[r+1];
                        if(vec[i+1] == 0)
                        {
                            vec[i] = -1;
                            vec[i+1] = -1;
                        }
                        vec[r] = -1;
                        vec[r+1] = -1;
                        this.redimensionar(2);
                    }
                }
                if(vec[i] == 0)
                {
                    vec[i] = -1;
                    vec[i+1] = -1; //Si el del final es 0, no lo toma, con esto si
                    this.redimensionar(2);   
                }
            }
        }
    }
    
    //Método para elliminar un dato 
    public boolean eliminar()
    {   
        boolean Var = false;
            
        for(int k = 1 ; k < ((int)(vec[0] * 2 ) + 1) ; k+=2)
        {
            if(!(vec[k] % 2 == 0))
            {
                Var = true;
                vec[k] = -1;
                vec[k+1] = -1;
                k = (int)(vec[0] * 2 + 1);
            }
        }
        /* PARA ELIMINAR EL EXPONENTE INDICADO // poner public boolean eliminar(int exp)
        for(int k = 1 ; k < ((int)(vec[0] * 2 ) + 1) ; k+=2)
        {
            if(vec[k] == exp)
            {
                Var = true;
                vec[k] = -1;
                vec[k+1] = -1;
                k = (int)(vec[0] * 2 + 1);
            }
        }
        */
        if(Var == true)
        {
            this.redimensionar(2);
            JOptionPane.showMessageDialog(null, "Monomio Eliminado");
        }
        return(Var);
    }
    
    //método para redimensionar según el -1 a eliminar
    public void redimensionar(int elegir)
    {
        float aux[] = new float[(int)(vec[0] * 2  + 1 ) - 2];
        
        if(elegir == 1)
        {
            aux[0]--;
        }
        else
        {
            aux[0] = vec[0] - 1;
        }
        int Cont = 1;
        for(int k = 1 ; k < ((int)(vec[0] * 2 ) + 1) ; k+=2)
        {
            if(vec[k] != -1 || vec[k+1] != -1)
            {
                aux[Cont] = vec[k];
                aux[Cont+1] = vec[k+1];
                Cont+=2;
            }
        }
        vec = aux;
    }
    
    //método para ajustar
    public void ajustar()
    {
        float aux[] = new float[(int)(vec[0] * 2  + 1 ) + 2];
        aux[0] = vec[0] + 1;
        if(vec[0] > 0)
        {
            for(int k = 1 ; k < ((vec[0] * 2 ) + 1) ; k+=2)
            {
               aux[k] = vec[k];
               aux[k+1] = vec[k+1]; 
            }
        }
        vec = aux;
    }
    
    //método para hacer copia
    public Polvf2 hacerCopia()
    {
        Polvf2 Copia = new Polvf2((int)vec[0]);
        Copia.setDato(vec[0], 0);
        for (int k = 1; k < (vec[0] * 2) + 1; k+=2) 
        {
            Copia.almacenarTermino(vec[k+1], (int)vec[k], k);
        }
        return(Copia);
    }

    //dividir dos polinomios
    public Polvf2 dividir(Polvf2 B)
    {
        int expt, Exp1, Cont = 1, Cent = 0;
        float coet, Coef1;

        Polvf2 Residuo = this.hacerCopia(); 
        Polvf2 Op = new Polvf2((int)(B.getDato(0) * 2) + 1);
        Polvf2 Cociente = new Polvf2 (0);
        while(Residuo.getDato(0) != 0 && Cent == 0)
        {
            if(Residuo.getDato(1) >= B.getDato(1))
            {
                expt = (int)(Residuo.getDato(1) - B.getDato(1));
                coet = Residuo.getDato(2) / B.getDato(2);
                Cociente.ajustar();
                Cociente.almacenarTermino(coet, expt, Cont);
                for(int k = 1; k < (B.getDato(0) * 2) + 1 ; k+=2)
                {
                    Exp1 = expt + (int)B.getDato(k);
                    Coef1 = coet * B.getDato(k+1);
                    Op.almacenarTermino(-Coef1, Exp1, k);
                }
                Residuo = Residuo.sumar(Op);
                Cont+=2;
            }
            else
            {
                Cent = 1;
            }
        }
        return(Cociente);
    }
    
}