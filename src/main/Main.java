package main;

import java.io.File;
import java.util.*;
import utility.*;
import models.*;

public class Main {
    public static void main(String[] args){
        int co = 0;
        boolean call = true;
        Scanner sc = new Scanner(System.in);
        do{
            do{
                System.out.println("\n\n1. Main Menu"); 
                System.out.println("2. Insert Data"); 
                System.out.println("3. Show");
                System.out.println("4. Exit");
                try {
                    co = sc.nextInt();
                } catch (Exception e) {
                    System.out.println(e);
                }
                sc.nextLine();
            }while(co < 1 || co > 4);
            switch (co) {
                case 1:
                    
                    break;
                case 2:
                    // insertData();
                    break;
                case 3:
                    // showData();
                    break;
                case 4:
                    call = false;
                    break;
            }
        }while(call);
    }
}
