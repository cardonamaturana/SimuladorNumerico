/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import com.csvreader.CsvWriter;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Math.pow;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;




/**
 *
 * @author USUARIO
 */
public class Generador {
    
    public static ArrayList<Double> Mixto(int m, int a, int c, int s) throws IOException{
        ArrayList<Double> num = new ArrayList<>();
        int x0=s;
        double x=x0;
        for (int i=0; i<=m;i++){
            x = ((a*x)+c)%m;
            num.add(x/m);//realiza el calculo de acuerdo a la formila del metodo congruencial mixto        
        }
        generarInformeMixtos(num);
        return num;
    }
    
    
    public static ArrayList<Double> Multiplicativo(int m, int a, int s) throws IOException{
        ArrayList<Double> num = new ArrayList<>();
        int x0=s;
        double x= x0;
        for (int i=0; i<=m;i++){
             x=((a*x))%m;//selecciona siempre la semilla con el valor 3
            num.add(x/m);//realiza el calculo de acuerdo a la formila del metodo congruencial mixto
        }
        generarInformeMultiplicativo(num);
        return num;
    }
    
    public int validarParametros(double m, double a, double c){
        int r=0;
        if(contadorDivisores((int)(m))>2  ){
           JOptionPane.showMessageDialog(null,"El parametro (m) Debe ser mayor que (a) y (c)");
        }
        if(m<= a || m<=c){
            r=2;//cuando m no es mayor que los demas parametros
        }
        return r;
    }
    
    public int contadorDivisores(int n){
        int cont=0;
        for (int i = 1; i <= n; i++) {
            if(n%i==0){
                cont++;
            }
        }
        return cont;
    }
    
   /*
    public static void encontrarPrimos_2xx32(){
       Workbook libro = new HSSFWorkbook();
        Sheet hoja = libro.createSheet("Numeros Primos");

        List<Double> primos = new ArrayList<>();
        double numMax=pow(2, 32);
        double cont=0, div;
        for (double i = 2; i <= numMax; i++) {
            div=0;
            for (double j = 1; j <=i; j++) {
                if (i%j==0) {
                    div++;                    
                }
            }
            if (div==2) {
                primos.add((double)i);
                Row fila = hoja.createRow((int)cont);
                cont++;
                fila.createCell(0).setCellValue((i));
            }
            
        }
        try {
           FileOutputStream archivo = new FileOutputStream("Numeros.xls");
           libro.write(archivo);
           archivo.close();
           JOptionPane.showMessageDialog(null, numMax);
            JOptionPane.showMessageDialog(null, "ok");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    */
    
    public static void generarPrimos() throws IOException{
        
        CsvWriter csvWriter = new CsvWriter("Numeros primos.csv");
        List<Integer> primos = new ArrayList<>();
        int numMax=(int)pow(2, 10);
        String datos[];
        datos = new  String[numMax];
        int cont=0;
        int div =0;
        for (int i = 2; i <= numMax; i++) {
            div=0;
            for (int j = 1; j <=i; j++) {
                if (i%j==0) {
                    div++;              
                    if (div>2) {
                        //Con esto se sale del ciclo en cuanto los divisores sean mayores que 2, puesto que ya no es primo
                        break;
                    }
                }
                //JOptionPane.showMessageDialog(null, "dentro "+j);
            }
            if(div==2){
                System.out.println("primo --> "+i);
                primos.add(i);
                        datos[cont]=String.valueOf((primos.get(cont)));
                        csvWriter.write(datos[cont]);
                        csvWriter.endRecord();
                        //csvWriter.write(datos[(int)cont]);
                        //Row fila = hoja.createRow((int)cont);
                        cont++;
            }
            
            //JOptionPane.showMessageDialog(null, "fuera  "+i);
        }
        try {
           csvWriter.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
      csvWriter.close();
    }
    
    public static ArrayList<Integer> generarRelativamentePrimosAM(int m) throws IOException{
        
        List<Integer> divisoresDeM = new ArrayList<>();
        ArrayList<Integer> relativamentePrimos = new ArrayList<>();
        for (int i = 1; i < 100; i++) {
            if(i%m==0){
                divisoresDeM.add(i);
                
            }
        }
            int bandera;
            for (int j = 2; j < 100; j++) {
                bandera=0;
                for (int k = 1; k < divisoresDeM.size(); k++) {
                    if(j%divisoresDeM.get(k)==0){
                        bandera++;
                        break;
                    }
                }
                if (bandera<0){
                    relativamentePrimos.add(j);
                }
                
            }
            JOptionPane.showMessageDialog(null, divisoresDeM.toString());
            JOptionPane.showMessageDialog(null, relativamentePrimos.toArray());
            
            
        return relativamentePrimos;
    }
    
    public static void generarValoresDeSemilla() throws IOException{
        
        CsvWriter csvWriter = new CsvWriter("valores de semilla.csv");
        String datos[];
        double numMax=1000;
        datos = new  String[(int)numMax];
        for (int i = 1; i <= numMax; i+=2) {
            datos[i]=String.valueOf(i);
                        csvWriter.write(datos[i]);
                        csvWriter.endRecord();
                        //csvWriter.write(datos[(int)cont]);
                        //Row fila = hoja.createRow((int)cont);
                        
            
            
            //JOptionPane.showMessageDialog(null, "fuera  "+i);
        }
      csvWriter.close();
    }
    
    public static void generarValoresDeAMixto() throws IOException{
        
        CsvWriter csvWriter = new CsvWriter("valores de a mixto.csv");
        String datos[];
        double numMax=23;
        datos = new  String[(int)numMax+3];
        for (int i = 2; i <= numMax; i++) {
            int a=(int)pow(2,i)+1;
            
            datos[i]=String.valueOf((int)(a));
                        csvWriter.write(datos[i]);
                        csvWriter.endRecord();
                        //csvWriter.write(datos[(int)cont]);
                        //Row fila = hoja.createRow((int)cont);
                        
            
            
            //JOptionPane.showMessageDialog(null, "fuera  "+i);
        }
      csvWriter.close();
    }
    
    public static void generarValoresDeAMultiplicativo() throws IOException{
        
        CsvWriter csvWriter = new CsvWriter("valores de a multiplicativo.csv");
        String datos[];
        double numMax=100;
        datos = new  String[(int)numMax*2+3];
        int pos1=1;
        int pos2=2;
        for (int i = 1; pos2 < numMax; i++) {
            int a= 8*i-3;
            int b= 8*i+3;
            //int a=(int)pow(2,i)+1;
            
            datos[pos1]=String.valueOf((int)(a));
            datos[pos2]=String.valueOf((int)(b));
                        csvWriter.write(datos[pos1]);
                        csvWriter.endRecord();
                        csvWriter.write(datos[pos2]);
                        csvWriter.endRecord();
                        pos1+=2;
                        pos2+=2;
                        //csvWriter.write(datos[(int)cont]);
                        //Row fila = hoja.createRow((int)cont);
                        
            
            
            //JOptionPane.showMessageDialog(null, "fuera  "+i);
        }
      csvWriter.close();
    }
    
    
    
    
    
     public static void generarValoresdeC() throws IOException{
        
        
        CsvWriter csvWriter = new CsvWriter("valores de c.csv");
        List<Integer> numeros = new ArrayList<>();
        int numMax=(int)pow(2, 20);
        String datos[];
        datos = new  String[numMax];
        int cont=0, div;
        for (int i = 1; i <= numMax; i++) {
            if((i%8)==5){
                numeros.add(i);
                        datos[cont]=String.valueOf((numeros.get(cont)));
                        csvWriter.write(datos[cont]);
                        csvWriter.endRecord();
                        //csvWriter.write(datos[(int)cont]);
                        //Row fila = hoja.createRow((int)cont);
                        cont++;
            }
            
            //JOptionPane.showMessageDialog(null, "fuera  "+i);
            
        }
        csvWriter.close();
    }
     
     public static List<String>  cargarValoresSemilla() {
        List<String> datos = new ArrayList<>();
        String file = "valores de semilla.csv";
        BufferedReader reader=null;
        String line ="";
        try {           
            reader = new BufferedReader(new FileReader(file));
            datos = reader.lines().toList();
            /*while ((line = reader.readLine())!=null){
                datos = reader.lines().toList();
            }*/
        } catch (Exception e) {
        }

        return datos;
        
    }
     
     public static List<String>  cargarPrimos() {
        List<String> datos = new ArrayList<>();
        String file = "Numeros primos.csv";
        BufferedReader reader=null;
        String line ="";
        try {           
            reader = new BufferedReader(new FileReader(file));
            datos = reader.lines().toList();
            /*while ((line = reader.readLine())!=null){
                
                datos = reader.lines().toList();
            }*/
        } catch (Exception e) {
        }

        return datos;
        
    }
     
     /*public static List<String>  cargarRelativamentePrimosAM() {
        List<String> datos = new ArrayList<>();
        String file = "Numeros relativamente primos a m.csv";
        BufferedReader reader=null;
        String line ="";
        try {           
            reader = new BufferedReader(new FileReader(file));
            datos = reader.lines().toList();
            /*while ((line = reader.readLine())!=null){
                
                datos = reader.lines().toList();
            }*/
      //  } catch (Exception e) {
        //}

        //return datos;
        
    //}
     
     
     
    
     
     
     
     public static List<String>  cargarValoresA() {
        List<String> datos = new ArrayList<>();
        String file = "valores de a.csv";
        BufferedReader reader=null;
        String line ="";
        try {           
            reader = new BufferedReader(new FileReader(file));
            datos = reader.lines().toList();
            /*while ((line = reader.readLine())!=null){
                
                datos = reader.lines().toList();
            }*/
        } catch (Exception e) {
        }

        return datos;
        
    }
     
     public static List<String>  cargarValoresAMultiplicativo() {
        List<String> datos = new ArrayList<>();
        String file = "valores de a multiplicativo.csv";
        BufferedReader reader=null;
        String line ="";
        try {           
            reader = new BufferedReader(new FileReader(file));
            datos=reader.lines().toList();
            //
            /*while ((line = reader.readLine())!=null){
                JOptionPane.showMessageDialog(null, reader.readLine());
                datos = reader.lines().toList();
            }*/
        } catch (Exception e) {
        }

        return datos;
        
    }
     
      public static List<String>  cargarValoresC() {
        List<String> datos = new ArrayList<>();
        String file = "valores de c.csv";
        BufferedReader reader=null;
        String line ="";
        try {           
            reader = new BufferedReader(new FileReader(file));
            datos = reader.lines().toList();
           /* while ((line = reader.readLine())!=null){
                datos = reader.lines().toList();
            }*/
        } catch (Exception e) {
        }

        return datos;
        
    }
      public static boolean ValidarCampos(int a,int c, int m, int s) {
          
          if(m<=a||m<=c || m<s){
              return false;
          }
          return true;
        
    }
      
       public static void generarInformeMixtos(ArrayList<Double> numeros) throws IOException{
           try {
               CsvWriter csvWriter = new CsvWriter("Mixtos.csv");
        for (int i = 0; i < numeros.size(); i++) {
                        csvWriter.write(String.valueOf(numeros.get(i)));
                        csvWriter.endRecord();
                        
        }
        csvWriter.close();
        JOptionPane.showMessageDialog(null, "Reporte generado");
        File objetofile = new File ("Mixtos.csv");
            Desktop.getDesktop().open(objetofile);
           } catch (FileNotFoundException e) {
               JOptionPane.showMessageDialog(null, "El archivo está abierto, por favor cierrelo e intentelo de nuevo!!");
           }
        
    }
     
       
    public static void generarInformeMultiplicativo(ArrayList<Double> numeros) throws IOException{
    try {

                
            CsvWriter csvWriter = new CsvWriter("Multiplicativo.csv");
            for (int i = 0; i < numeros.size(); i++) {
                            csvWriter.write(String.valueOf(numeros.get(i)));
                            csvWriter.endRecord();

            }
            csvWriter.close();
            File objetofile = new File ("Multiplicativo.csv");
            Desktop.getDesktop().open(objetofile);
            JOptionPane.showMessageDialog(null, "Reporte generado!");
                
        }catch (IOException ex) {

                System.out.println(ex);

     }
    }
    
    public static boolean sonRelativamentePrimos (int a, int b){
        ArrayList<Integer> factoresDeA = new ArrayList<>();
        ArrayList<Integer> factoresDeB = new ArrayList<>();
        
        for(int i=2; i<=Integer.min(a, b); i++){
//            JOptionPane.showMessageDialog(null, "resultado de a%i y b%i --> "+"a= "+a+"  "+a%i+"  -->  b= "+b+"  "+b%i+"  -->  i=  "+i );
            if((a%i==0) && (b%i)==0 ){
                return false;
            }
        }
        return true;
    }
}
