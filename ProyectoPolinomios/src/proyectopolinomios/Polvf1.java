
package proyectopolinomios;
import javax.swing.JOptionPane;

public class Polvf1 
{
    int n;
    float vec[];
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
                if((int)vec[k] < vec[k] || (int)vec[k] > vec[k])
                {
                    salida = salida+vec[k]+"X<sup>"+((int)vec[0]+1-k)+"</sup>";
                }
                else
                {
                    salida = salida+(int)vec[k]+"X<sup>"+((int)vec[0]+1-k)+"</sup>";
                }
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
    public Integer ingresarTerminos()
    {
        float coef;
        int exp, cont = 0;
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
            cont++;
        }
        return cont;
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
        int expt, expA;
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
    
    public Polista multiplicarPolvf1ConPolvf2(Polvf2 B) //método creado por el profesor
    {
        int k, j, expA, expB, expR;
        float coeA, coeB, coefR;
        Polista R= new Polista();
        
        for(j = 1; j < B.getDato(0) * 2+1; j += 2)
        {
            expB = (int)B.getDato(j);
            coeB = B.getDato(j+1);
            for(k = 1 ; k <vec[0]+2 ; k++)
            {
                coeA=vec[k];
                expA=(int)vec[0]+1-k;

                coefR=coeA*coeB;

                expR= expA+expB;
                if(coefR!=0)
                {
                    R.insertarTerm(coefR, expR);
                }
            }
        }
        return(R);
    }  
    public Polista sumarPolvf1ConPolvf2(Polvf2 B)
    {
        int k, j;
        Polista R= new Polista();

        for(k = 1 ; k < vec[0]+2 ; k++)
        {
            if(vec[k] != 0)
            {
                R.insertarTerm(vec[k], (int)vec[0]+1-k); //se traspasan los valores de Polvf1 de inicio a fin a Polista 
            }
        }
        
        for(j = 1; j < B.getDato(0) * 2 + 1; j += 2)
        {
            R.insertarTerm(B.getDato(j+1), (int)B.getDato(j)); //se traspasan los valores de Polvf2 de inicio a fin a Polista
        }                                                   //acá ya empiezan a sumarse entre dentro del método "mágico", teniendo en cuenta los valores iniciales que dispone Polista previamente
        return(R);
    }
    
    public Boolean compararPolvf1conPolvf2(Polvf2 B, int cont) //acá cont es un valor que recibe del método ingresartérminos de Polvf1, porque necesitaba saber
    {                                                              //cuantos valores tenía independientemente del grado de Polvf1, entonces ese método pasó a retornar Integer
        int j, expA, expB;
        boolean bool = true; //inicialización del bool
        float coeA, coeB;

        if(B.getDato(0) == cont) //si Polvf1 y Polvf2 tienen la misma magnitud de valores (coef/exp) entonces entrará, sino, no porque obviamente tienen que ser mínimo iguales en tamaño
        {
            for(j = 1; j < B.getDato(0) * 2 + 1; j += 2)
            {
                expB = (int)B.getDato(j);
                coeB = B.getDato(j+1);

                coeA = vec[0] + 1 - expB; //encuentra la posición conociendo el exponente (acá se usa el valor de Polvf2 como referencia, ya que si son iguales, lo encontrará en Polvf1)
                expA = (int)vec[0] + 1 - (int)coeA; //encuentra el exponente conociendo la posición, la posición es el valor anterior, ya que es el puente para el exponente y el coeficiente
                coeA = vec[(int)coeA]; //acá se encuentra el valor real del coeficiente, para poder comparar

                if(expA == expB) //entra si ambos exp son iguales
                {
                    if(coeB != coeA) //si los coeficientes no son iguales entra
                    {
                        bool = false;
                        j = (int)B.getDato(0) * 2 + 1; //forzado de cierre del ciclo
                    }
                }
                else //si no son iguales
                {
                    bool = false;
                    j = (int)B.getDato(0) * 2 + 1; //forzado de cierre del ciclo
                }
            }
        }
        else
        {
            bool = false; //acá se pone falso si no son iguales en tamaño
        }
        return(bool);
    }
}