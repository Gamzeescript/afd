package com.informatica3;
import java.util.*;
public class GramaticaAlpha{


 
    public static void main(String[] args){
        if(args!=null && (args.length==3 || args.length==4)){
            LogicaPrograma programa = new LogicaPrograma(args);
            programa.procesarArchivo();

        }else{
            System.out.println("Forma de uso: java -jar ./target/gramatica-1.0-SNAPSHOT.jar path_gramatica (-afn|-afd|-check) archivo_salida [path_cuerdas]");
        }
    }

}