package com.informatica3;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
public class LogicaPrograma{
    
   private String pathGramatica;
   //private String tipoProcesamiento;
   private FormaProc tipoProcesamiento;
   private String archivoSalida;
   private String pathCuerdas;
   

   public LogicaPrograma(String[] args){
      System.out.println(args[1]);
      this.pathGramatica = args[0];
      this.tipoProcesamiento = evaluarProcesamiento(args[1]);
      System.out.println(this.tipoProcesamiento);
      this.archivoSalida = args[2];
      if(args.length==4){
         this.pathCuerdas = args[3];
      }
   }

   private FormaProc evaluarProcesamiento(String tipoProcesamientoStr){
      String tipo = tipoProcesamientoStr.trim();
      if(tipo.equals("-afd")){
         return FormaProc.afd;
      }else if(tipo.equals("-afn")){
         return FormaProc.afn;
      }else if(tipo.equals("-check")){
         return FormaProc.check;
      }else{
         return FormaProc.afd;
      }
   }

   private String procesarAfd(String lineaActual){
      return "afd-"+lineaActual;
   }

   private String procesarAfn(String lineaActual){
      return "afn-"+lineaActual;
   }

   private String procesarCheck(String lineaActual){
      return "check-"+lineaActual+pathCuerdas;
   }

   private List<String> leerArchivo(){
      List<String> lineas = new ArrayList<>();
      try {
         lineas = Files.readAllLines(Paths.get(this.pathGramatica), StandardCharsets.UTF_8);
      } catch (IOException e) {
         System.err.println("Error reading the file: "+this.pathGramatica+" exception: "+e);
      }
      return lineas;
   }

   private String procesarLinea(String lineaActual){
      String lineaSalida = null;
      if(tipoProcesamiento.equals(FormaProc.afd)){
         lineaSalida = procesarAfd(lineaActual);
      }else if(tipoProcesamiento.equals(FormaProc.afn)){
         lineaSalida = procesarAfn(lineaActual);
      }else if(tipoProcesamiento.equals(FormaProc.check)){
         lineaSalida = procesarCheck(lineaActual);
      }
      return lineaSalida;
   }

   private void escribirArchivoSalida(StringBuilder sb){
      Path path = Paths.get(this.archivoSalida);

      try {
          Files.write(path, sb.toString().getBytes(StandardCharsets.UTF_8));
      } catch (IOException e) {
         System.err.println("Error al escribir el archivo: "+this.archivoSalida+" exception: "+e);
      }
   }

   public void procesarArchivo(){
      StringBuilder sb = new StringBuilder();
      List<String> lineas = leerArchivo();
      lineas.stream().forEach((linea)->{
         sb.append(procesarLinea(linea)).append("\n");
      });
      escribirArchivoSalida(sb);
   }

}