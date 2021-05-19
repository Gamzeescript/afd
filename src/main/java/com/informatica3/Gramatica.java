package com.informatica3;
import java.util.*;
public class Gramatica{


   /* public static void main(String[] args){

        Scanner sc= new Scanner(System.in); 

        System.out.print("Favor ingrese el valor del alfabeto (String)====>");
        String alfabeto = sc.next();

        System.out.print("Favor ingrese el valor del estado inicial (int)====>");
        int estadoInicial = sc.nextInt();

        System.out.print("Favor ingrese el valor del estado final (int)====>");
        int estadoFinal = sc.nextInt();

        LogicaPrograma programa = new LogicaPrograma(alfabeto,estadoInicial,estadoFinal);
        programa.hacerReporte();

        sc.close();

        

    }*/
    public static void main(String[] args){
        if(args!=null && (args.length==3 || args.length==4)){
            LogicaPrograma programa = new LogicaPrograma(args);
            programa.procesarArchivo();

        }else{
            System.out.println("Forma de uso: java -jar ./target/gramatica-1.0-SNAPSHOT.jar path_gramatica (-afn|-afd|-check) archivo_salida [path_cuerdas]");
        }
    }

}