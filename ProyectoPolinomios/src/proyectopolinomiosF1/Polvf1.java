
package proyectopolinomiosF1;
import javax.swing.JOptionPane;


public class Polvf1 
{
    private int n;
    private float vec[];
    //Método Constructor
    public Polvf1(int grado)
    {
        n = grado + 2;
        vec = new float[n];
        vec[0] = grado;
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
    public int getTamano()
    {
        return(n);
    }
    //método para mostrar
    public String mostrar()
    {
        int k;
        String salida = "<html>";
        for(k = 1 ; k < vec[0]+2 ; k++)
        {
            if(vec[k] != 0)
            {
                if(vec[k] > 0 && k > 1)
                {
                    salida = salida+" + ";
                }
                salida = salida+(int)vec[k]+"X<sup>"+((int)vec[0]+1-k)+"</sup>";
            }
        }
        salida = salida + "</html>";
        return(salida);
    }
    //Método para almacenar un término
    public void almacenarTermino(float coef, int exp)
    {
        int pos;
        if(exp >= 0 && exp <= vec[0])
        {
            pos = (int)vec[0] + 1 - exp;
            if(vec[pos] == 0)
            {
                vec[pos] = coef;
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Ya existe un término con ese exponente.");
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null,"El exponente no corresponde al Polinomio.");
        }
    }
    //Método para ingresar los términos
    public void ingresarTerminos()
    {
        float coef;
        int exp;
        String resp;        
        resp = JOptionPane.showInputDialog("¿Desea ingresar un término? S/N");
        while(resp.equalsIgnoreCase("S"))
        {
            coef = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el coeficiente"));
            exp = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el exponente"));
            this.almacenarTermino(coef, exp);
            resp = JOptionPane.showInputDialog("¿Desea ingresar un término? S/N");
            if(!resp.equalsIgnoreCase("S") && vec[1] == 0)
            {
                JOptionPane.showMessageDialog(null, "Falta ingresar un Polinomio");
                resp="S";
            }
        }
    }
    //Método para ajustar
    public void ajustar()
    {
        int k, j = 1, cont = 0;
        while(j < vec[0]+2 && vec[j] == 0)
        {
            cont++;
            j++;
        }
        for(k = j ; k < vec[0]+2 ; k++)
        {
            vec[k - cont] = vec[k];
        }
        
        vec[0] = vec[0] - cont;
    }
    //Método para evaluar
    public float evaluar(float x)
    {
        float resultado = 0;
        for(int k = 1 ; k < vec[0]+2 ; k++)
        {
            resultado = resultado + vec[k] * (float)Math.pow(x, vec[0]+1-k);
        }
        return(resultado);
    }
    //Método para insertar un término
    public void insertarTerm(float coef, int exp)
    {
        int pos;
        String resp;
        if(exp < 0)
        {
            JOptionPane.showMessageDialog(null, "El exponente no es válido");
        }
        else
        {
            if(exp <= vec[0])
            {
                pos = (int)vec[0]+1-exp;
                vec[pos] = vec[pos]+coef;
                this.ajustar();
            }
            else
            {
                this.redimensionar(exp);
                vec[0] = exp;
                vec[1] = coef;
            }
        }
    }
    //método para redimensionar
    public void redimensionar(int exp)
    {
        int k, pos = exp + 1;
        n = exp + 2;
        float aux[] = new float [n];
        for(k = (int)vec[0]+1 ; k > 0 ; k--)
        {
            aux[pos] = vec[k];
            pos = pos - 1;
        }
        //delete(vec) no hace falta hacerlo, Java libera automáticamente el vector vec
        vec = aux;
    }
    //Método para sumar 2 polinomios
    public Polvf1 sumar(Polvf1 B)
    {
        int k = 1, j = 1, expA, expB, posR, my;
        if(vec[0] > B.getDato(0))
        {
            my = (int)vec[0];
        }
        else
        {
            my = (int)B.getDato(0);
        }
        Polvf1 R = new Polvf1(my);
        while(k < vec[0]+2 && j < B.getDato(0)+2)
        {
            expA = (int)vec[0]+1 - k;
            expB = (int)B.getDato(0)+1 - j;
            if(expA == expB)
            {
                posR = (int)R.getDato(0)+1 - expA;
                R.setDato(vec[k]+B.getDato(j), posR);
                k++;
                j++;
            }
            else
            {
                if(expA > expB)
                {
                    posR = (int)R.getDato(0)+1 - expA;
                    R.setDato(vec[k], posR);
                    k++;
                }    
                else
                {
                    posR = (int)R.getDato(0)+1 - expB;
                    R.setDato(B.getDato(j), posR);
                    j++;
                }
            }
        }
        R.ajustar();
        return(R);
    }
    //Método para multiplicar dos polinomios
    public Polvf1 multiplicar(Polvf1 B)
    {
        int k, j, expR;
        float coefR; 
        Polvf1 R = new Polvf1((int)(vec[0] + B.getDato(0)) );
        
        for(j = 1 ; j < B.getDato(0)+2 ; j++)
        {
            for(k = 1 ; k < vec[0]+2 ; k++)
            {
                coefR = vec[k] * B.getDato(j);
                expR = (int)(vec[0]+1 - k + B.getDato(0)+1 - j);
                R.insertarTerm(coefR, expR);
            }
        }
        return(R);
    }
    
    public Polvf1 hacerCopia()
    {
        Polvf1 Copia = new Polvf1((int)(vec[0]));
        for (int k = 1; k < vec[0] + 2; k++) 
        {            
            Copia.insertarTerm(vec[k], (int)vec[0]+1-k);
        }
        return (Copia);
    }
    
    public Polvf1 dividir(Polvf1 B)
    {
        int expt, posR, expA, posA;
        float coet, coeA;
        Polvf1 Copia = this.hacerCopia();
        Polvf1 R = new Polvf1 ((int)(vec[0] - B.getDato(0)));         
        while(Copia.getDato(0) >= B.getDato(0))
        {
            expt = (int)(Copia.getDato(0) - B.getDato(0));
            coet = Copia.getDato(1) / B.getDato(1);
            R.insertarTerm(coet, expt);
            for(int k = 1 ; k < (B.getDato(0)+2) ; k++)
            {
                expA = expt + (int)B.getDato(0) + 1 - k;
                coeA = coet * B.getDato(k);
                Copia.insertarTerm(-coeA, expA);
            }
            Copia.ajustar();
        }
        return(R);
    }
}