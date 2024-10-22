/*
Name: Mohammed
Purpose: To write a program to simualte the growth of differnet types of bacteria in a given time frame and afterwards calculate the growth rate.
Date: February 2nd, 2024
*/

import java.io.*;
import java.util.*;
import java.text.*;

public class Main {
  public static void main(String[] args) {

    Scanner input = new Scanner (System.in);
    DecimalFormat df = new DecimalFormat("0.00");
    
    int repeat = 2, timePeriod = 0, simulatedTime = 0, bacteriaAmount1 = 0, bacteriaAmount2 = 0, bacteriaAmount3 = 0, bacteriaInitial1 = 0, bacteriaInitial2 = 0, bacteriaInitial3 = 0, runs = 1;
    String line, bacteriaType1 = "", bacteriaType2 = "", bacteriaType3 = "";
    


    boolean found = false;

    do{

      do{
        System.out.print("\nEnter time: ");

        try{
          FileReader fr = new FileReader ("Bacteria Data.txt");
          BufferedReader br = new BufferedReader(fr);

          timePeriod = input.nextInt();
          line = br.readLine();

          while(line != null && !found){
            if (Integer.parseInt(line) == timePeriod){
              found = true;     
            }

            else{
              for (int i = 1; i <=8; i++){
                line = br.readLine();
              }

            }
          }

          if (!found){
            System.out.print("\nTime period not found!");
          }
          else{

            line = br.readLine();
            bacteriaType1 = line;
            line = br.readLine();
            bacteriaAmount1 = Integer.parseInt(line);
            line = br.readLine();
            bacteriaType2 = line;
            line = br.readLine();
            bacteriaAmount2 = Integer.parseInt(line);
            line = br.readLine();
            bacteriaType3 = line;
            line = br.readLine();
            bacteriaAmount3 = Integer.parseInt(line);

            bacteriaInitial1 = bacteriaAmount1;
            bacteriaInitial2 = bacteriaAmount2;
            bacteriaInitial3 = bacteriaAmount3;

          }  



        }
        catch(IOException e){
          System.out.print("\nFile not found");
        }
        catch(InputMismatchException er){
          System.out.println("\nNot a number");
          input.next();
        }
      } while (found != true);
  

      for (int i = 0; i <= timePeriod / 20; i ++){

        System.out.println("\nCurent time: " + simulatedTime + " minutes");

        System.out.print("------------------------------------\nBacteria 1: ");
        System.out.print("|");

        for (int x = 0; x < bacteriaAmount1/500; x++){
          System.out.print("*");
        }

        System.out.print("\n" + bacteriaType1 + " (Population: " + bacteriaAmount1 + ")\n");

        System.out.print("\nBacteria 2: ");
        System.out.print("|");

        for (int x = 0; x < bacteriaAmount2/500; x++){
          System.out.print("*");
        }
    
        System.out.print("\n" + bacteriaType2 + " (Population: " + bacteriaAmount2 + ")\n");

        System.out.print("\nBacteria 3: ");
        System.out.print("|");

        for (int x = 0; x < bacteriaAmount3/500; x++){
          System.out.print("*");
        }

        System.out.print("\n" + bacteriaType3 + " (Population: " + bacteriaAmount3 + ")\n------------------------------------\n\n");

        try{
          Thread.sleep(2000);
        }
        catch (InterruptedException err){
          System.out.println("System was not able to pause");
        }

        if (simulatedTime <= timePeriod && simulatedTime <= timePeriod - 20){
          simulatedTime += 20;

          bacteriaAmount1 *= (double)(Math.random() * 1) + 1.5;
          bacteriaAmount2 *= (double)(Math.random() * 1) + 1.5;
          bacteriaAmount3 *= (double)(Math.random() * 1) + 1.5;
        }
      }


      try{
        FileWriter fw = new FileWriter("results.txt", true);
        PrintWriter pw = new PrintWriter (fw);

        if (runs != 1){
          pw.print("\n\n");
        }

        pw.println("Simulation #" + runs);

        runs += 1;

        pw.println("Time Period: " + timePeriod + " minutes");
        pw.println("Growth rate for " + bacteriaType1 + ": " + df.format((double)(bacteriaAmount1 - bacteriaInitial1)/bacteriaInitial1 * 100) + "%");
        pw.println("Growth rate for " + bacteriaType2 + ": " + df.format((double)(bacteriaAmount2 - bacteriaInitial2)/bacteriaInitial2 * 100) + "%");
        pw.println("Growth rate for " + bacteriaType3 + ": " + df.format((double)(bacteriaAmount3 - bacteriaInitial3)/bacteriaInitial3 * 100) + "%");

        pw.close();
        
      }

      catch(IOException e){
        System.out.println("Could not write to file");
      }

      do{

        System.out.print("\nWould you like to run the program again? (0 = exit, 1 = again): ");

        try{
          repeat = input.nextInt();
        }
        catch(InputMismatchException e){
          System.out.print("Please enter '0' or '1' only: ");
          input.next();
        }

      }while (!(repeat == 0 || repeat == 1));

      found = false;
      simulatedTime = 0;


    }while (repeat != 0);


  }
}

