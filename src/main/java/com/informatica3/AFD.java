import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.FileNameMap;
import java.util.Scanner;
import javax.swing.text.html.HTMLEditorKit;



/*
	Utilice esta clase para guardar la informacion de su
	AFD. NO DEBE CAMBIAR LOS NOMBRES DE LA CLASE NI DE LOS 
	METODOS que ya existen, sin embargo, usted es libre de 
	agregar los campos y metodos que desee.
*/
public class AFD
{
    
    int[][] matrix;
    String[] alphabet;
    int numberStates;
    int[] finalStates;    
    
    
	
    /*
            Implemente el constructor de la clase AFD
            que recibe como argumento un string que 
            representa el path del archivo que contiene
            la informacion del afd (i.e. "Documentos/archivo.afd").
            Puede utilizar la estructura de datos que desee
    */
    public AFD(String path)
    {
        int lineNumber = 0;
        try  
        {  
            //the file to be opened for reading  
            FileInputStream fis =    new FileInputStream(path);       
            Scanner sc  =   new Scanner(fis);    //file to be scanned  
            //returns true if there is another line to read  
            while(sc.hasNextLine())  
            {  
                String actualline = sc.nextLine();
                lineNumber++;
                if (lineNumber == 1) 
                {
                    alphabet = actualline.split(",");                    
                }
                else
                {
                    if (lineNumber == 2)
                    {
                        numberStates = Integer.parseInt(actualline);
                        //state first
                        matrix = new int [numberStates][alphabet.length];
                        
                    }
                    else
                    {
                        if(lineNumber == 3)
                        {
                            
                            String[] aux2 = actualline.split(",");
                            finalStates = new int[aux2.length];
                            for (int i = 0; i < aux2.length; i++) 
                            {
                                finalStates[i]=Integer.parseInt(aux2[i]);
                                
                            }
                        }
                        else
                        {
                            String[] aux;
                            aux = actualline.split(",");
                            
                            for (int i = 0; i < aux.length; i++)
                            {
                                //i are states
                                //j are the symbols (number of line - 4)
                                matrix [i] [lineNumber - 4] = Integer.parseInt(aux[i]);
                            }
                        }
                    }
                }
  //returns the line that was skipped  
            }  
            sc.close();     //closes the scanner  
        }  
        catch(IOException e)  
        {  
            e.printStackTrace();  
        }  

    }
    
    public void  evaluateString(String path, String nombrearchivosalida)
    {
        
        try  
        {  
            //the file to be opened for reading  
            FileInputStream fis =    new FileInputStream(path);       
            Scanner sc  =   new Scanner(fis);    //file to be scanned  
            //returns true if there is another line to read  
            
            
            FileWriter writer = new FileWriter(nombrearchivosalida);
            BufferedWriter bw = new BufferedWriter(writer);

           
           
          
            
            
            
            while(sc.hasNextLine())  
            {  
                String actualline = sc.nextLine();              
                boolean res = accept(actualline);
                if (res == true)
                {
                    
                    bw.write("aceptada");
                    bw.write("\r\n");

                    
                }
                else
                {
                    bw.write("rechazada");
                    bw.write("\r\n");
                }              
                
               
                  
  //returns the line that was skipped  
            }  
            sc.close();  
            bw.close();//closes the scanner  
        }  
        catch(IOException e)  
        {  
            e.printStackTrace();  
        }  
        
    }
    
    
    public int getTransition(int currentState, char symbol)    
    {
        int position = -1;
        String mySymbol = String.valueOf(symbol);
        
        for (int i = 0; i < alphabet.length; i++)
        {
            if (mySymbol.equals(alphabet[i]))
            {
                position = i;
                break;
            }            
            
        }
        if (position >= 0) 
        {
            return matrix[currentState][position];
        }
        else
        {
            System.out.println("Simbolo no reconocido");
            return 0;
        }
        
    
    }

    public boolean accept(String string)
    {
        int state = 1; //initial state
        for (char symbol : string.toCharArray())
        {
            state = getTransition(state, symbol);
        }
        
        boolean result = false;
        
        for(int aux3    :   finalStates)
        {
            if (state == aux3)
            {
                result = true;
                break;
                
            }
        }
        return result;
        
        
        
    }
 
}
