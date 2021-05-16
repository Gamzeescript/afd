package com.informatica3;

import java.util.*;
import java.nio.file.*;
import java.util.stream.Stream;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class GestionArchivos{

    public static List<String> leerArchivo(String nombreArchivo){

        List<String> lines = new ArrayList<>();
        Path path = Paths.get(nombreArchivo);
 
        try (Stream<String> stream = Files.lines(path)) {
            stream.forEach((line)->{
                lines.add(line);
            });
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
        
    }

    public static void crearArchivo(String nombrearchivo , String texto){
        Path path = Paths.get(nombrearchivo);

        try {
            Files.write(path, texto.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
           System.err.println("Error al escribir el archivo: "+nombrearchivo+" "+e.getMessage());
        }
    }

    public static void main(String[] args){

        if(args!=null && args.length==1){
            String nombreArchivo = args[0];
            String nombreArchivoSalida = nombreArchivo+".out";
            //Aca leo las lineas del archivo
            List<String> lines = leerArchivo(nombreArchivo);
            //Aca las proceso
            //Creo un StringBuilder para acumular las lineas de salida
            StringBuilder sb = new StringBuilder();
            int i = 1;
            for(String line : lines){
                String textoProcesado = "linea_"+i+":"+line;
                System.out.println(textoProcesado);
                //Agrego al Buffer de salida
                sb.append(textoProcesado).append("\n");
                i++;
            }
            //Escribo el archivo de salida
            crearArchivo(nombreArchivoSalida, sb.toString());
        }else{
            System.err.println("Forma de uso: java -jar target/gramatica-1.0-SNAPSHOT.jar nombreArchivo");
        }

       
    }
}