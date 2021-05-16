package com.informatica3;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import javax.xml.namespace.QName;

public class Gramatica {

    public static final String OPCION1 = "-afd";
    public static final String OPCION2 = "-afn";
    public static final String OPCION3 = "-check";
    public static final String OPCION4 = "-min";

    public static ArrayList<String> excepcion;
    public static ArrayList<String> abecedario;
    public static ArrayList<String> token;

    //public static int estado;
    //public static int estadoFinal;
    //public static String resultante;


    /*private static void Inicializar(){

        estado = 0;
        estadoFinal = 0;
        resultante = "";

        excepcion = new ArrayList();
        abecedario = new ArrayList();
        token = new ArrayList();
        
    }

    // objeto Scanner 
    public static Scanner sc = new Scanner(System.in);


    private static void Procesar(){

        System.out.print("Introduzca una cadena: ");
        String cadAbc = sc.nextLine();
        String cadena = "";

        for(int i = 0; i < cadAbc.length(); i++){
            char chtr = cadAbc.charAt(i);

            switch(chtr){
                case '\r':
                case '\t':
                case '\n':
                case '\b':
                case '\f':

                break;

                default:
                cadena = cadena + chtr;
                
            } // fin del switch
        } // fin del for 

        for(estado = 0; estado < cadena.length(); estado++){
            char  chtr = cadena.charAt(estado);
            int cAscii = chtr;

            switch(estadoFinal){
                case 0:
                if((cAscii >= 65 && cAscii <= 90) || (cAscii >= 97 && cAscii <= 122)){
                    estadoFinal = 1;
                    resultante = " " + chtr;

                } else if (cAscii >= 48 && cAscii <= 57){
                    estadoFinal = 2;
                    resultante = " " + chtr;
                } else if(chtr == ' '){
                    estadoFinal = 0;
                } else {
                    System.out.println("error en resultante " + chtr);
                    estadoFinal = 0;
                    excepcion.add(""+chtr);
                }
                break;
                case 1:
                if((cAscii >= 65 && cAscii <= 90) || (cAscii >= 97 && cAscii <= 122)){
                    estadoFinal = 1;
                    resultante = " " + chtr;
                    
                } else {
                    estadoFinal = 0;
                    excepcion.add(""+chtr);
                    resultante = "";
                    estado --;
                }
                break;
                case 2:
                if(cAscii >= 48 && cAscii <= 57){
                    estadoFinal = 3;
                    resultante = resultante + chtr;
                    
                }
                break;
                case 3:
                if(cAscii >= 46){
                    estadoFinal = 4;
                    resultante = resultante + chtr;
                    
                }
                break;
                case 4:
                if(cAscii >= 48 && cAscii <= 57){
                    estadoFinal = 5;
                    resultante = resultante + chtr;
                    
                }
                break;
                case 5:
                excepcion.add(""+chtr);
                resultante = "";
                estadoFinal = 0;
                estado --;

            }
        }
        
    }

    private static void Imprimir(){
        for(int i = 0; i < abecedario.size(); i++){
            System.out.println(abecedario.get(i));
        }
    }

    private static void ImprimirE(){
        for(int i = 0; i < excepcion.size(); i++){
            System.out.println(excepcion.get(i));
        }
    }

    */

   

    public static void crearArchivo(String nombrearchivo , String texto){
        Path path = Paths.get(nombrearchivo);

        try {
            Files.write(path, texto.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
           System.err.println("Error al escribir el archivo: "+nombrearchivo+" "+e.getMessage());
        }
    }

    public static void main(String[] args) {

        if (args != null && args.length == 1) {

            String opcionSeleccionada = args[0];
            String nombreArchivo = "./archivoafn.afn";
            String nombreArchivo1 = "./archivoafd.afd";
            String nombreArchivo2 = "./archivoafncheck.afd";
            String nombreArchivo3 = "./archivoafnmin.afd";
 
            String descripcion = "Ejemplo de Archivo de texto para este programa!";



            // para construir nuestras gramaticas y procesos
            // de AFN Y AFD 

            if (opcionSeleccionada.equals(OPCION1)) {

                System.out.println("Ejecutando " + OPCION1);
               

               // Inicializar();
                //Procesar();
                //Imprimir();
                //ImprimirE();



                crearArchivo(nombreArchivo1, descripcion);

            } else if (opcionSeleccionada.equals(OPCION2)) {

                System.out.println("Ejecutando " + OPCION2);

                crearArchivo(nombreArchivo, descripcion);


            } else if (opcionSeleccionada.equals(OPCION3)) {

                System.out.println("Ejecutando " + OPCION3);

                crearArchivo(nombreArchivo2, descripcion);


            } else if (opcionSeleccionada.equals(OPCION4)) {

                System.out.println("Ejecutando " + OPCION4);

                crearArchivo(nombreArchivo3, descripcion);

            } else {
                System.out.println("Opcion Invalida:");
                
                System.out.println();
            }

        } else {
            System.out.println();
        }

    }

}