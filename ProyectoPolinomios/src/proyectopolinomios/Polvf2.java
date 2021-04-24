
package proyectopolinomios;
import javax.swing.JOptionPane;

public class Polvf2 
{
    int tamano; //quitar private a los 2 pa usarlos en un mismo proyecto (pasarlo)
    float vec[];
    
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
    public String mostrarEvaluado(float exp) //evaluar modificado, para que muestre enteros sin coma decimal y para que muestre el valor que tomó la X
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
    public String mostrar() //mostrar modificado, para que muestre enteros sin coma decimal
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
                    exp = 2147483647; //esto es porque el cero lo toma como posición por defecto, entonces se agrega esto para validar que es una posición individual de los otros ceros que significa que no se ha insertado nada
                }
                if(i == 0)
                {
                    matriz[i] = exp; //acá se agrega por primer vez el primer valor para comparar
                }
                else if(i > 0) //acá entra la segunda vez para comparar según la matriz anterior
                {
                    r = 0;
                    while(r <= i && Cent == 0)
                    {
                        if(matriz[r] == exp)
                        {
                            Cent = 1;  //acá dice sí es igual
                        }
                        r++;
                    }
                    if(Cent != 1)
                    {
                        if(exp >= 0)
                        {
                            matriz[i] = exp; //si no es igual, lo agrega a la matriz en la posicición ubicada, y activa el centinela de otra forma para que siga derecho y pueda ingresar otro valor
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
                    if(vec[ContAux] < vec[ContAux+2]) //este ordenamiento burbuja está hecho para organizar el ingreso desordenado de monomios, y para organizar resultados descendentemente
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
        for(int k = 1 ; k < vec[0] * 2 ; k += 2)
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
        
        //el comparar va a la par de cada uno, apenas encuentre uno que no corresponda suelta falso y forza el cierre del ciclo
        for(int i = 0 ; i < vec[0] ; i++) 
        {
            if(vec[Cont-1] == B.getDato(Cont-1))
            {
                if(!(vec[Cont] == B.getDato(Cont)))
                {
                    r = false;
                    i = (int)vec[0]; //freno del ciclo
                }
            }
            else
            {
                r = false;
                i = (int)vec[0]; //freno del ciclo
            }
            Cont += 2;
        }
        return(r);
    }

    //Método para sumar 2 polinomios
    public Polvf2 sumar(Polvf2 B)
    {
        int Cont = 1, i = 0, Cont2;
        
        Polvf2 R = new Polvf2((int)(vec[0] + B.getDato(0))); //se suman posiciones para insertar todo en un mismo vecto
        R.setDato(vec[0] + B.getDato(0), 0);
        
        while(i < vec[0])
        {
            R.almacenarTermino(vec[Cont+1], (int)vec[Cont], Cont); //se inserta todo en el mismo vector
            Cont+=2;  
            i++;
        }
        i = 0;
        Cont2 = 1;
        while(i < B.getDato(0))
        {
            R.setDato(B.getDato(Cont2), Cont);
            R.setDato(B.getDato(Cont2+1), Cont+1);   //se inserta todo en el mismo vector, siguiendo el contador anterior
            Cont+=2;
            Cont2+=2;
            i++;
        }
        R.suma();   //acá se suman los valores que tengan exponentes iguales dentro del mismo vector, se recorre anidado
        R.OrdenTerminos(); //se devuelve el valor organizado descendentemente
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
        
        Polvf2 R = new Polvf2((int)(vec[0] * B.getDato(0))); //se crea un vector con todas las casillas posibles al multiplicar
        
        for(int i = 1 ; i <= B.getDato(0) ; i++) //no se usa i+=2 para individualizar todo y manejar manualment todo mejor
        {
            Cont2 = 0;
            for(int h = 1 ; h <= (int)vec[0] ; h++)
            {
                R.setDato(B.getDato(i+Cont) + vec[h+Cont2], Pos);
                R.setDato(B.getDato(i+Cont+1) * vec[h+Cont2+1], Pos+1); //se insertan los coeficientes multiplicados y los exponentes sumados 
                Pos+=2; //pos nunca se reinicia en 1, tiene que seguir hasta el final seguido
                Cont2++;
            }
            Cont++;
        }
        R.OrdenTerminos();//se ordena
        R.suma(); //después de haberse puesto los 2 polinomios multiplicados y sumados, se sumarán nuevamente acá los que tengan mismo exponente, ya que quedaron faltantes para el resultado final
        return(R);
    }
    
    //Método para sumar lo multiplicado
    public void suma()
    {
        int auxExp;
        for(int g = 0 ; g < 3 ; g++) //esto es para recorrer +2 veces, para sumar 2 polinomios que hayan faltado por sumar y estén pegados
        {
            for(int i = 1 ; i < ((vec[0] * 2) + 1)   ; i+=2) //recorre el vector
            {
                auxExp = (int)vec[i];
                for(int r = i+2 ; r < ((vec[0] * 2) + 1) ; r+=2)  //recorre el segundo vector anidado, r = i+2 es para indicar que debe estar siempre 2 posiciones por delante para comparar el posicionado sobre los demás
                {
                    if(vec[r] == auxExp)
                    {
                        vec[i+1] = vec[i+1] + vec[r+1];
                        if(vec[i+1] == 0)
                        {
                            vec[i] = -1;
                            vec[i+1] = -1;   //se le pone -1 para que redimensionar reconozca que no debe pasar este valor al nuevo vector redimensionado
                        }
                        vec[r] = -1;
                        vec[r+1] = -1;
                        this.redimensionar(2);
                    }
                }
                if(vec[i] == 0)
                {
                    vec[i] = -1;
                    vec[i+1] = -1; //Si el monomio del final es 0, no lo toma, con esto si
                    this.redimensionar(2);   //según lo mandado acá, el método redimensiona según el -1 agregado
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
            if(!(vec[k] % 2 == 0)) //este  método es para eliminar todo exp impar, el primero que encuentre, le agrega -1 a exp y coef para que redimensionar reconozca y elimine la posición exacta
            {
                Var = true;
                vec[k] = -1;    
                vec[k+1] = -1;
                k = (int)(vec[0] * 2 + 1); //elimina el primer exp impar
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
        
        //sin esta separación da error
        if(elegir == 1)
        {
            aux[0]--; //acá es para cuando se desea eliminar todos los elementos seguidos del vector dentro de un for por fuera  del método (método no usado por usar el eliminar para el primer exp impar)
        }
        else
        {
            aux[0] = vec[0] - 1; //acá es para cuando se desea eliminar un elemento del vector
        }
        int Cont = 1;
        for(int k = 1 ; k < ((int)(vec[0] * 2 ) + 1) ; k+=2)
        {
            if(vec[k] != -1 || vec[k+1] != -1)
            {
                aux[Cont] = vec[k];
                aux[Cont+1] = vec[k+1]; //acá se redimensiona sin el valor a eliminar, si el arreglo anterior tenía -1 en coef y exp significa que se eliminó, eliminando esa posición
                Cont+=2;
            }
        }
        vec = aux;
    }
    
    //método para ajustar
    public void ajustar()
    {
        float aux[] = new float[(int)(vec[0] * 2  + 1 ) + 2];
        aux[0] = vec[0] + 1;  //aumenta el límite del vector, con un +1
        if(vec[0] > 0)
        {
            for(int k = 1 ; k < ((vec[0] * 2 ) + 1) ; k+=2)
            {
               aux[k] = vec[k];
               aux[k+1] = vec[k+1];  //acá se re-ingresan los mismos valores, pero el arreglo ya tiene una posicición más para ingresar un nuevo valor de coef y exp 
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
            Copia.almacenarTermino(vec[k+1], (int)vec[k], k); //copia exacta del polinomio, para que lo tome aparte del que se ingresó la primer vez y muestre individualmente
        }
        return(Copia);
    }

    //dividir dos polinomios
    public Polvf2 dividir(Polvf2 B)
    {
        int expt, Exp1, Cont = 1, Cent = 0;
        float coet, Coef1;

        Polvf2 Residuo = this.hacerCopia(); //el residuo inicial
        Polvf2 Op = new Polvf2((int)(B.getDato(0) * 2) + 1); //acá se ponen el residuo operado, y este pasaria a ser el nuevo residuo en el método
        Polvf2 Cociente = new Polvf2 (0); //cociente resultante
        while(Residuo.getDato(0) != 0 && Cent == 0)
        {
            if(Residuo.getDato(1) >= B.getDato(1))
            {
                expt = (int)(Residuo.getDato(1) - B.getDato(1)); //acá compara los 2 primeros exponentes de cada polinomio para seguir operando,  
                coet = Residuo.getDato(2) / B.getDato(2);       //termina hasta que el exponente del 2do polinomio sea mayor al residuo o el residuo sea igual a cero
                Cociente.ajustar(); //cada que se ingrese el dato dividido y restado de ambos polinomios, se ajusta para tener el # de valores exacto para el cociente según se van agregando
                Cociente.almacenarTermino(coet, expt, Cont); //se almacenan los valores
                for(int k = 1; k < (B.getDato(0) * 2) + 1 ; k+=2)
                {
                    Exp1 = expt + (int)B.getDato(k);
                    Coef1 = coet * B.getDato(k+1);
                    Op.almacenarTermino(-Coef1, Exp1, k); //acá se almacena lo que operará con el residuo
                }
                Residuo = Residuo.sumar(Op); //el resultado entre el residuo y lo que se operaba, pasa a ser el nuevo residuo 
                Cont+=2;
            }
            else
            {
                Cent = 1; //acá entra si la división es exacta, es decir que el residuo fue cero
            }
        }
        return(Cociente);
    }
    
    public Polvf1 Polvf2DivididoconPolista(Polista B)
    {
        int Cont = 1;
        Polvf2 R2 = new Polvf2(0); 
        Nodo q = B.getCab(); 
        
        while(q != null)
        {
            R2.ajustar(); //se aumenta el vector +1 posición para coeficiente y exponente cada vez que se ingrese un dato que posea Polista
            R2.almacenarTermino(q.getCoef(), q.getExp(), Cont); //se ponen los datos del Polista a formato polvf2
            Cont+=2;
            q = q.getLiga();
        }
        
        Polvf2 R1 = this.hacerCopia(); //se hace una copia de polvf2 para operar con la anterior polvf2
        
        R1 = R1.dividir(R2); //se dividen acá
        
        Polvf1 R = new Polvf1((int)R1.getDato(1)); //se crea un polvf1 que debe ser el resultado, para inicializar el constructor se le agrega la posición 1 de polvf2 resultante (R1)
                                                   //Polvf1 tendrá el número de casillas del grado ingresado en R1 que es Polvf2
        for(int j = 1; j < vec[0] * 2 + 1; j += 2)
        {
            R.almacenarTermino(R1.getDato(j+1), (int)R1.getDato(j)); //acá se almacena los valores de R1 en polvf1, y se pone en su indicación, para exp es vec[0] + 1 - R1.getDato(j+1)
        }                                                            //                                                                              coef es vec[0] + 1 - R1.getDato(j)
        
        return(R); //se manda el retorno como está definido, retorno de un objeto Polvf1
    }
    
}