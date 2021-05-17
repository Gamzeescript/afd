import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Gramatica{

List<String> simbolos;   
List<String> alfabeto;
List<regla> reglasafd;
List<String> estadosafd;
List<regla> reglas;
String lambda = "#";       
String estadofinal;

public Gramatica()
{
     
}

/**
 * @param args the command line arguments
 */
public static void main(String[] args)
{        
    Gramatica z = new Gramatica();
    if(args.length >=3)
    {             
        if (args[1].equals("-afn")) 
        {
            //afn  
            
            
            z.processAfn(args[0], args[2]);
            
            
      
            

        }
        else
        {
            if (args[1].equals("-afd"))
            {
                //afd
                
              
                
                z.processAfd(args[0], args[2]);
               

            }
            else
            {
                if (args[1].equals("-check")) 
                {
                    //check
                    if (args.length == 4)
                    {
                        //eth good                          
            
                        z.processCheck(args[0], args[2], args[3]);
                      

                    }
                    else
                    {
                        //error no puso los 4
                    }

                }
            }
        }
    }
    else
    {
        //malos parametros 
    }
}

public String nuevosimbolo(List<String> l)
{
    int contascii = 65;
    String str;
    
    while (true) 
    {       
        str = Character.toString((char) contascii);
        if (!l.contains(str))
        {               
            break;
        }           
        contascii++;
    } 
    
    return str; 
}

public void processAfn(String pathGramatica, String archivoSalida)
{        
 //the file to be opened for reading  
    FileInputStream theFile = null;       
    try 
    {
        theFile = new FileInputStream(pathGramatica);
        Scanner sc  =   new Scanner(theFile);
        simbolos = new ArrayList<>();
        String simboloInical ="";
        alfabeto = new ArrayList<>();
        reglas = new ArrayList<>();
    
        int contador = 0;     
         estadofinal="";
        String estadoinicial;
        
        boolean estadofinalyacreado = false;
        while(sc.hasNextLine())  
        {  
            contador++;
            String actualline = sc.nextLine();                  
            if (contador == 1)
            {
                simbolos.addAll(Arrays.asList(actualline.split(",")));                                       
            }
            else
            {
                if (contador == 2)
                {
                    alfabeto.addAll(Arrays.asList(actualline.split(",")));
                }
                else
                {
                    if(contador==3)
                    {
                        simboloInical=actualline;
                    }
                    else
                    {
                        //reglas.addAll(Arrays.asList(actualline));
                        String[] reglaproduccion;
                        reglaproduccion = actualline.split("->");
                        
                        if (reglaproduccion[1].length() == 1)
                        {
                            if (simbolos.contains(reglaproduccion[1]))
                            {
                                regla r = new regla(reglaproduccion[0],lambda,reglaproduccion[1]);
                                reglas.add(r);
                                
                            }
                            else
                            {
                                if (estadofinal.length()==0)
                                {
                                    estadofinal= nuevosimbolo(simbolos);
                                    simbolos.add(estadofinal);
                                }
                                                                  
                                regla r = new regla(reglaproduccion[0],reglaproduccion[1],estadofinal);                              
                                reglas.add(r);
                            }
                        }
                        else
                        {
                            //n cosas
                            estadoinicial = reglaproduccion[0];
                            String j = reglaproduccion[1];
                            while (true)
                            {
                                if (alfabeto.contains(j.substring(0, 1)) && alfabeto.contains(j.substring(1, 2)))
                                {
                                  String nsimbolo = nuevosimbolo(simbolos);
                                  simbolos.add(nsimbolo);
                                  regla r = new regla(estadoinicial,j.substring(0, 1),nsimbolo);
                                  reglas.add(r);
                                  estadoinicial = nsimbolo;
                                  j= j.substring(1);
                                } 
                                
                                
                                
                                if (j.length()==1)
                                {
                                    if (estadofinal.length()==0)
                                    {
                                        estadofinal= nuevosimbolo(simbolos);
                                        simbolos.add(estadofinal);
                                    }

                                    regla r = new regla(estadoinicial,j,estadofinal);                                      
                                    reglas.add(r);
                                    break;
                                    
                                }
                                else
                                {                                   
                                    if (alfabeto.contains(j.substring(0, 1)) && simbolos.contains(j.substring(1, 2)))
                                    {

                                      regla r = new regla(estadoinicial,j.substring(0, 1),j.substring(1,2));
                                      reglas.add(r);
                                      break;
                                    } 
                                }
                                
                                
                            }
                            
                        }
                        
                    }
                }
            }                                     
        } 
        sc.close();
        //eliminamos simbolos sin utilizar 
        Iterator<String> it= simbolos.iterator();
        while(it.hasNext())
        {
            String sh = it.next();
            
            boolean encontrado = false;
            
            for (regla r:reglas)
            {
                if (r.getEstadoInicial().equals(sh)||r.getEstadoFinal().equals(sh)) {
                    encontrado = true; 
                    break;
                }                                       
                
            }
            if (encontrado == false)
            {
               it.remove();
                
            }
        }
        
        //agregando estadoo 0
        simbolos.add(0,"0");
        alfabeto.add(0,lambda);
        
        //lambda
        for (String str:simbolos)
        {
            regla r = new regla(str,lambda,str);
            reglas.add(r);
        }
        
        //escribo archivo salida
                                                                                          
        
        FileWriter writer = new FileWriter(archivoSalida);
        BufferedWriter bw = new BufferedWriter(writer);
        
        //imprime alfabetos
        int cont1=0;
        for (String alfa:alfabeto)
        {
            cont1++;
            
            if (cont1>2)
            {
                bw.write(",");
                
            }
            if (cont1>1)
            {
            bw.write(alfa);
            }
        }
        bw.write("\r\n");
        
        
        //imprimir numero de estados
        bw.write(String.valueOf(simbolos.size()));
           bw.write("\r\n");
        
        //imprimir estado final
        bw.write(String.valueOf(simbolos.indexOf(estadofinal)));
           bw.write("\r\n");
        
        //
        for (String alf:alfabeto)
        {
            int cont3=0;
            
            
            for(String sim:simbolos)
            {
                cont3++;
                if (cont3>1)
                {
                    bw.write(",");
                    
                }
                
                String line =transicion(sim, alf, simbolos, reglas);
                bw.write(line);
            }
            bw.write("\r\n");
        }
        
        
 
        
        bw.close();           
                                             
    } 
    catch (IOException ex) 
    {
    }
    
}

public  String transicion(String estadoini, String alf,List<String> sim,List<regla> reg)
{
    int cont=0;
    String res="";
    
    for(regla r: reg)
    {
        if (r.getEstadoInicial().equals(estadoini) && r.getAlfabeto().equals(alf))
        {
            cont++;
            if (cont>1)
            {
                res+=";";
            }
            
            res+=sim.indexOf(r.getEstadoFinal());
        }
    }
    
    if (res.length()==0)
    {
     res="0";   
    }
    
    return res;
}

private void generaAFD(String estado)
{
    System.out.print("Estado: "+estado);
    String[] estados = estado.split("%");
    if (!estadosafd.contains(estado))
    {
        estadosafd.add(estado);
    }
    for (String alfa:alfabeto)
    {
        System.out.println(" evaluando en "+alfa+" ");
        String salida="";
        for(String est:estados)
        {
            for(String e:est.split(";"))
            {
                List<String> evaluacion = transicionAfd(e, alfa, reglas);
                for(String res:evaluacion)
                {
                    if(!salida.contains(res))
                    {
                        if (salida.length()>0)
                        {
                            salida+=";";
                        }
                        salida+=res;
                    }

                }
            }
        }
        if(salida.length()>0)
        {
            String clausura="";
            for(String es:salida.split(";"))
            {
                List<String> listaclausura = transicionAfd(es, "#", reglas);
                for(String lc:listaclausura)
                {
                    if (!salida.concat(clausura).contains(lc))
                    {
                        if (clausura.length()>0)
                        {
                            clausura +=";";
                        }
                        clausura+=lc;
                    }
                }
            }
            String nuevoestado=clausura.length()>0?salida+"%"+clausura:salida;
            System.out.println("Nuevo estado");
            System.out.println(nuevoestado);
            reglasafd.add(new regla(estado, alfa,nuevoestado));
            if (!estadosafd.contains(nuevoestado))
            {
                
                
                generaAFD(nuevoestado);
            }
        }
    }
    
}








public void processAfd(String pathGramatica, String archivoSalida)
{
    reglasafd = new ArrayList<>();
    estadosafd = new ArrayList<>();
  this.processAfn(pathGramatica, "temp.afn");
  Iterator<regla> it= reglas.iterator();
             
  //eliminar reglaslambda
    while(it.hasNext())
    {
        regla sh = it.next();

        boolean encontrado = false;


            if (sh.getEstadoInicial().equals(sh.getEstadoFinal()) && sh.getAlfabeto().equals(lambda) ) 
            {
                 it.remove();
            }                                       
       
    }
    //eliminar lambda
    alfabeto.remove(lambda);
    
    
    String estadoinicialafd= simbolos.get(1);
    
    List<String> listainicial = transicionAfd(estadoinicialafd, lambda, reglas);
    int cont32=0;
    for (String str:listainicial)
    {
        if (!str.equals("0"))
        {
            if (!estadoinicialafd.contains(str))
            {
                if (cont32==0)
                {
                    estadoinicialafd += "%";
                    
                }
            }
            
            if (cont32>0)
            {
                estadoinicialafd+=";";
            }
            
            estadoinicialafd+=str;
            cont32++;
            
        }

    }
  
  
    generaAFD(estadoinicialafd);
    
    
    
    //imprimir 
    try 
    {
         FileWriter writer = new FileWriter(archivoSalida);
        BufferedWriter bw = new BufferedWriter(writer);
        
        //imprime alfabetos
        int cont1=0;
        for (String alfa:alfabeto)
        {
            cont1++;
            
            if (cont1>1)
            {
                bw.write(",");
                
            }
            
            bw.write(alfa);
            
        }
        bw.write("\r\n");
        
        
        
        //imprimir numero de estados
        bw.write(String.valueOf(estadosafd.size()));
           bw.write("\r\n");
        
           int z=0;
        estadosafd.add(0,"0");
        for(String f:estadosafd)
        {
            if (f.contains(estadofinal))
            {
                if (z>0)
                {
                 bw.write(",");
                }
                bw.write(String.valueOf(estadosafd.indexOf(f)));
                z++;
            }
        }
        //imprimir estado final
        
           bw.write("\r\n");
        
        //
        for (String alf:alfabeto)
        {
            int cont3=0;
            
            
            for(String sim:estadosafd)
            {
                cont3++;
                if (cont3>1)
                {
                    bw.write(",");
                    
                }
                
                String line =transicion(sim, alf, estadosafd, reglasafd);
                bw.write(line);
            }
            bw.write("\r\n");
        }
        
        
 
        
        bw.close(); 
        
    } catch (Exception e) 
    {
    }
    
    
    
    
  
}

 public  List<String> transicionAfd(String estadoini, String alf,List<regla> reg)
{
    
    ArrayList<String> resarray = new ArrayList<String>();
    
    for(regla r: reg)
    {
        if (r.getEstadoInicial().equals(estadoini) && r.getAlfabeto().equals(alf))
        {
             resarray.add(r.getEstadoFinal());
        }
    }                    
    
    Collections.sort(resarray);
    return resarray;
}

public void processCheck(String pathGramatica, String archivoSalida, String pathCuerdas){
   //procesa Afn
    
    this.processAfd(pathGramatica, "temp.afd");
    String afdaevaluar="temp.afd";
           
    AFD myafd = new AFD(afdaevaluar);
    String[] cuerdas = pathCuerdas.split(",");
    myafd.evaluateString(cuerdas[0],archivoSalida);
    
}                    
}
