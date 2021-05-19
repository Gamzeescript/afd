package com.informatica3;


public class AFD {

    int contador;
    boolean aceptado;
    char [] charactr; 

    public void q0(){
        if(contador < charactr.length){
            if(charactr[contador] == 'a'){
                contador++;
                q0();
            }else if (charactr[contador] == 'b'){
                contador++;
                q0();
            }
        }

    }
    public void q1(){
        if(contador < charactr.length){
            if(charactr[contador] == 'a'){
                contador++;
                q1();
            }else if (charactr[contador]== 'b'){
                contador++;
                q1();
            }
        }

    }
    public void q2(){
        if(contador < charactr.length){
            if(charactr[contador] == 'a'){
                contador++;
                q2();
            }else if (charactr[contador] == 'b'){
                contador++;
                q3();
            }
        }

    }
    public void q3(){
        if(contador < charactr.length){
            if(charactr[contador] == 'a'){
                contador++;
                q3();
            }else if (charactr[contador] == 'b'){
                contador++;
                q3();
            }
        }

    }
    public void q4(){
        if(contador < charactr.length){
            if(charactr[contador] == 'a'){
                contador++;
                q0();
            }else if (charactr[contador] == 'b'){
                contador++;
                q0();
            }
        }

    }
    public void inicio(){
        contador = 0;
        aceptado = false;
        q0();

    }



    //Gramatica gram = new Gramatica();
   // String cad = "abc";

    //gram.charactr = cad.toCharArray();
   // gram.inicio();


}