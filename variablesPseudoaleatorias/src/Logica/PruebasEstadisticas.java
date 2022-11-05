/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javax.swing.JOptionPane;

/**
 *
 * @author USUARIO
 */
public class PruebasEstadisticas {
    
    public static boolean PruebaDeKolmogorov_Smirnov(ArrayList<Double> numeros){
        ArrayList<Double> ordenados = new ArrayList<>();
        ArrayList<Double> diferencias1 = new ArrayList<>();
        ArrayList<Double> diferencias2 = new ArrayList<>();
        
        ArrayList<Double> tabla= new ArrayList<>();
        ArrayList<Double> indices= new ArrayList<>();
        
        
        tabla.add(0.975 ); indices.add((double)1);
        tabla.add(0.842 ); indices.add((double)2);
        tabla.add(0.708 ); indices.add((double)3);
        tabla.add(0.624 ); indices.add((double)4);
        tabla.add(0.563 ); indices.add((double)5);
        tabla.add(0.521 ); indices.add((double)6);
        tabla.add(0.486 ); indices.add((double)7);
        tabla.add(0.457 ); indices.add((double)8); 
        tabla.add(0.432 ); indices.add((double)9);
        tabla.add(0.409 ); indices.add((double)10);
        tabla.add(0.391 ); indices.add((double)11);
        tabla.add(0.375 ); indices.add((double)12); 
        tabla.add(0.361 ); indices.add((double)13);
        tabla.add(0.349 ); indices.add((double)14);
        tabla.add(0.338 ); indices.add((double)15);
        tabla.add(0.328 ); indices.add((double)16);
        tabla.add(0.318 ); indices.add((double)17);
        tabla.add(0.309 ); indices.add((double)18);
        tabla.add(0.301 ); indices.add((double)19);
        tabla.add(0.294 ); indices.add((double)20);
        tabla.add(0.264 ); indices.add((double)25);
        tabla.add(0.242 ); indices.add((double)30);
        tabla.add(0.230 ); indices.add((double)35);
        tabla.add(0.210 ); indices.add((double)40);
        tabla.add(0.188 ); indices.add((double)50);
        tabla.add(0.172 ); indices.add((double)60);
        tabla.add(0.160 ); indices.add((double)70);
        tabla.add(0.150 ); indices.add((double)80);
        tabla.add(0.141 ); indices.add((double)90);
        tabla.add(0.134 ); indices.add((double)100);
        
        double n = numeros.size();
        double valor=0;
        double ecuacion;
        
        ordenados=numeros;
        double max=0;
        Collections.sort(ordenados);
        int a=1;
        for (int i = 0; i < n; i++) {
            diferencias1.add((double)a/n);
            diferencias2.add(ValorAbsoluto((double)ordenados.get(i)-diferencias1.get(i)));
            a++;
        }
        max=Collections.max(diferencias2);
        
        if(n<=100){
            for (int i = 0; i < 29; i++) {
                valor=0;
                if(n>=indices.get(i)&&n<indices.get(i+1)){
                    //JOptionPane.showMessageDialog(null, "tamaño indices"+ indices.size());
                    //JOptionPane.showMessageDialog(null, "tamaño tabla"+ tabla.size());
                    valor=tabla.get(i);
                }
                
            }if(valor==0){
                    valor=0.134;
                } 
             if(ValorAbsoluto(valor)>max){
               return true;  
             }else{
                 return false;
             }
             
        }
        ecuacion = (double)1.36/sqrt(n);
        if(ValorAbsoluto(ecuacion)>max){
            return true;
        }
        return false;
    }
    
    public static boolean PruebaDeFrecuencia(ArrayList<Double> numeros){
        int n = numeros.size();
        double r1=0.2,r2=0.4,r3=0.6,r4=0.8,r5=1;
        double n1=0,n2=0,n3=0,n4=0,n5=0;
        double c1,c2,c3,c4,c5,cTot;
        double fE=(double)n/5;
        for (int i = 0; i < n; i++) {
            double num= numeros.get(i);
            if(num>=0&&num<r1){
                n1++;
            }else if(num>=r1&&num<r2){
                n2++;
            }else if(num>=r2&&num<r3){
                n3++;
            }else if(num>=r3&&num<r4){
                n4++;
            }else if(num>=r4&&num<=r5){
                n5++;
            }
            }
        c1=(double)pow(n1-fE,2)/fE;
        c2=(double)pow(n2-fE,2)/fE;
        c3=(double)pow(n3-fE,2)/fE;
        c4=(double)pow(n4-fE,2)/fE;
        c5=(double)pow(n5-fE,2)/fE;
        cTot=c1+c2+c3+c4+c5;
        if((double)ValorAbsoluto(cTot)<9.4877){
           
             return true;
        }
        return false;
    }
    
    
    
    
    
    
    public static boolean PruebaDePromedio(ArrayList<Double> numeros){
        double suma=0, promedio=0,aux,aux2;
        int n= numeros.size();
        for (int i = 0; i <n; i++) {
            suma += (double)numeros.get(i);
        }
        promedio =(double) (suma/(n));
        
        aux=(double)1/2;
        aux2=sqrt(n);
            double z0 = ((promedio-aux)*aux2)/((double)sqrt((double)1/12));
            if (ValorAbsoluto(z0)<(double)1.96){
                
                return true;
            }
          return false;
    }
    
    public static boolean pruebaCorridaArribaAbajo (ArrayList<Double> numeros){
        int n= numeros.size();
        
        ArrayList<Integer> bits = new ArrayList<>();
        
        int i, corridas, dato;
        
        double  media, varianza, z;
                
        for (i=1; i<n-1; i++){
            if (numeros.get(i)<numeros.get(i+1)){
                bits.add(0);
            }
            else{
                bits.add(1);
            }                
        }
        
        
        corridas = 1;        
        dato= bits.get(0);
        
        for (i=1; i<bits.size(); i++){
            if (bits.get(i) != dato){
                corridas++;
                dato = bits.get(i);
            }
        }
        
        //JOptionPane.showMessageDialog(null,"corridas "+ corridas);
        
        media = (2*n-1)/ (double)3;
        //JOptionPane.showMessageDialog(null,"media "+ media);
        
        varianza = (16*n-29)/(double) 90;        
        //JOptionPane.showMessageDialog(null,"varianza "+ varianza);
        
        z = Math.abs((corridas-media)/Math.sqrt(varianza));
        //JOptionPane.showMessageDialog(null,"Zo "+z);
        
        if( z < 1.96){
           // JOptionPane.showMessageDialog(null,"los numeros NO se pueden RECHAZAR, aprueban el criterio de prueba corridas arriba abajo");
            return true;
        }else{
            //JOptionPane.showMessageDialog(null,"NO APRUEBAN el criterio de prueba corridas arriba abajo");
            return false;
        }

    }
    public static double RetornarCantidad(ArrayList<Double> numeros){
        for (int i = 1; i < numeros.size(); i++) {
            if(numeros.get(i).equals(numeros.get(0))){
                return i-1;
            }else{
                
            }
        }
        return numeros.size()-1;
    }
    
    
    
    public static double ValorAbsoluto(double a){
        double b=a;
        if(a<0){
            b=a*(-1);
        }
        return b;
    }
}
