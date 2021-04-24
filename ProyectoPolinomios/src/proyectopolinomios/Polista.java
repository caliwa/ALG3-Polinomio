
package proyectopolinomios;

import javax.swing.JOptionPane;

public class Polista 
{
    Nodo cab;
    
    //método constructor
    public Polista()
    {
        cab = null;
    }
            
    public Nodo getCab()
    {
        return cab;
    }
    
    public String mostrar()
    {
        Nodo p=cab;
        String salida = "<html>";
        while(p != null)
        {
            if(p.getCoef() > 0 && p != cab)
            {
                salida = salida+" + ";
            }  
            if((int)p.getCoef() < p.getCoef() || (int)p.getCoef() > p.getCoef())
            {
                salida = salida+p.getCoef()+"X<sup>"+((int)p.getExp())+"</sup>";
            }
            else
            {
                salida = salida+(int)p.getCoef()+"X<sup>"+((int)p.getExp())+"</sup>";
            }
            
            p = p.getLiga();
        }
        salida = salida + "</html>";
        return(salida);
    }
    //método para evaluar un polinomio en lista
    public float evaluar(float x)
    {
        Nodo p = cab;
        float resultado = 0;
        while(p != null)
        {
            resultado = resultado + p.getCoef() * (float)Math.pow(x, p.getExp());
            p = p.getLiga();
        }
        return(resultado);
    }
    //Método para almacenar un término
    public boolean almacenarTerm(float coef, int exp)
    {
        Nodo x, ant = null, p = cab;
        while(p != null && p.getExp() > exp)
        {
            ant = p;
            p = p.getLiga();
        }
        if((p != null && p.getExp() == exp))
        {
            JOptionPane.showMessageDialog(null, "Ya existe un término con este exponente");
            return(false);
        }
        else
        {
            x = new Nodo(coef, exp);
            x.setLiga(p);
            if(p == cab)
            {
                cab = x;
            }
            else
            {
                ant.setLiga(x);
            }
            return(true);
        }
    }
    //Método para ingresar los términos del Polinomio
    public void ingresarTerminos()
    {
        float coef;
        boolean caso = true;
        int exp, Cont = 1;
        String resp;        
        do
        {
            do{
                coef = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el coeficiente (diferente de cero) del monomio #"+Cont));
            }while(coef == 0);  
            
            do{
                exp = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el exponente del monomio #"+Cont));
            }while(exp < 0);
            
            caso = this.almacenarTerm(coef, exp);
            if(caso == true)
            {
                Cont++;   
            }
            resp = JOptionPane.showInputDialog("¿Desea ingresar un término? S/N");
        }while(resp.equalsIgnoreCase("S"));
    }
    
    public void insertarTerm(float coef, int exp)
    {
        Nodo x, p = cab, ant = null;

        while(p != null && p.getExp() > exp)
        {
            ant = p;
            p = p.getLiga();
        }
        if((p != null && p.getExp() == exp))
        {
            if((p.getCoef() + coef) != 0)
            {
                p.setCoef(p.getCoef() + coef);
            }
            else
            {
                if(p == cab)
                {
                    cab = cab.getLiga();
                }
                else
                {
                    ant.setLiga(p.getLiga());
                }
                //delete(p)
            }
        }
        else
        {
            x = new Nodo(coef, exp);
            x.setLiga(p);
            if(p == cab)
            {
                cab = x;
            }
            else
            {
                ant.setLiga(x);
            }
        }
    }
    //método para sumar 2 polinomios
    public Polista sumar(Polista B)
    {
        Nodo p = cab, q = B.getCab();
        Polista R = new Polista();
        while(p != null && q != null)
        {
            if(p.getExp() == q.getExp())
            {
                if((p.getCoef() + q.getCoef()) != 0)
                {
                    R.insertarTerm(p.getCoef() + q.getCoef(), p.getExp());
                }
                p = p.getLiga();
                q = q.getLiga();
            }
            else
            {
                if(p.getExp() > q.getExp())
                {
                    R.insertarTerm(p.getCoef(), p.getExp());
                    p = p.getLiga();
                }
                else
                {
                    R.insertarTerm(q.getCoef(), q.getExp());
                    q = q.getLiga();
                }
            }
        }
        while(p != null)
        {
            R.insertarTerm(p.getCoef(), p.getExp());
            p = p.getLiga();
        }
        while(q != null)
        {
            R.insertarTerm(q.getCoef(), q.getExp());
            q = q.getLiga();
        }
        return(R);
    }
    //método para multiplicar 2 polinomios
    public Polista multiplicar(Polista B)
    {
        Nodo p, q = B.getCab();
        Polista R = new Polista();
        while(q != null) 
        {
            p = cab;
            while(p != null)
            {
                R.insertarTerm(p.getCoef() * q.getCoef(), p.getExp() + q.getExp());
                p = p.getLiga();
            }
            q = q.getLiga();
        }
        return(R);
    }
    
    //método para hacer copia
    public Polista hacerCopia()
    {
        Polista R = new Polista();
        Nodo q = cab;
        
        while(q != null)
        {
            R.insertarTerm(q.getCoef(), q.getExp());
            q = q.getLiga();
        }
        return(R);
    }
    
    //método para dividir 2 polinomios
    public Polista dividir(Polista B) //este método creado por mí, tiene la misma lógica que dividir de Polvf2
    {
        int expt, Exp1, Cent = 0;
        float coet, Coef1;

        Nodo q;
        Polista Cociente = new Polista ();
        Polista Residuo = this.hacerCopia();
        
        while(Residuo.getCab() != null && Cent == 0)
        {
            if(Residuo.getCab().getExp() >= B.getCab().getExp())
            {
                expt = (int)(Residuo.getCab().getExp() - B.getCab().getExp());
                coet = Residuo.getCab().getCoef() / B.getCab().getCoef();
                Cociente.insertarTerm(coet, expt);
                q = B.getCab();
                while(q != null)
                {
                    Exp1 = expt + (int)q.getExp();
                    Coef1 = coet * q.getCoef();
                    Residuo.insertarTerm(-Coef1, Exp1);
                    q = q.getLiga();
                }
            }
            else
            {
                Cent = 1;
            }
        }
        return(Cociente);
    }
    public Polvf2 PolistaDivididoconPolvf1(Polvf1 B)
    {
        Polvf2 R = new Polvf2(0);
        Polvf2 R2 = new Polvf2(0);
        Nodo q = cab;
        int Cont = 1;
        
        while(q != null)
        {
            R2.ajustar(); //se ajusta +1 para posterior ingreso
            R2.almacenarTermino(q.getCoef(), q.getExp(), Cont); //se almacena desde el primer valor hasta el límite de Polista
            Cont+=2;
            q = q.getLiga();
        } 
        
        Cont = 1; //se reinicia el contador para reutilizar la variable
        
        for(int k = 1 ; k < B.getDato(0) + 2 ; k++)
        {
            if(B.getDato(k) != 0) //acá es para que entre solo cuando hayan valores reales en Polvf1, el cero representa solo una casilla sin ningún valor
            {
                R.ajustar(); //se ajusta según si ingresa acá, porque apenas entre a este if es porque sí se ingresarán valores
                R.almacenarTermino((int)B.getDato(k), (int)B.getDato(0)+1-k, Cont); //se almacenan los valores de Polvf1 
                Cont += 2;
            }
        }
        
        R = R2.dividir(R); //se dividen ambos objetos, R2 era de Polista y R era de Polvf1 luego de la conversión de ambos a Polvf2 para dividir con el método de Polvf2
        return(R);
    }
    
    public Polvf2 PolistaMultiplicadoconPolvf1(Polvf1 B)
    {
        Polvf2 R = new Polvf2(0);
        Polvf2 R2 = new Polvf2(0);
        Nodo q = cab;
        int Cont = 1;
        
        while(q != null)
        {
            R2.ajustar(); //se ajusta +1 para posterior ingreso
            R2.almacenarTermino(q.getCoef(), q.getExp(), Cont); //se almacena desde el primer valor hasta el límite de Polista
            Cont+=2;
            q = q.getLiga();
        }        
        
        Cont = 1; //se reinicia el contador para reutilizar la variable
        
        for(int k = 1 ; k < B.getDato(0) + 2 ; k++)
        {
            if(B.getDato(k) != 0) //acá es para que entre solo cuando hayan valores reales en Polvf1, el cero representa solo una casilla sin ningún valor
            {
                R.ajustar(); //se ajusta según si ingresa acá, porque apenas entre a este if es porque sí se ingresarán valores
                R.almacenarTermino((int)B.getDato(k), (int)B.getDato(0)+1-k, Cont); //se almacena desde el primer valor hasta el límite de Polista
                Cont += 2;
            }
        }
        
        R = R2.multiplicar(R); //se multiplican ambos objetos, R2 era de Polista y R era de Polvf1 luego de la conversión de ambos a Polvf2 para dividir con el método de Polvf2
        
        return(R);
    }
    

    
}
