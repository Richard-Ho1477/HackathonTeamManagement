package main;

import java.util.*;

import repository.FacadeRep;

public class Main {
    public static void main(String[] args){
        int co = 0;
        boolean call = true;
        Scanner sc = new Scanner(System.in);
        FacadeRep fr = new FacadeRep();
        do{
            do{
                System.out.println("\n\n1. Main Menu"); 
                System.out.println("2. Insert Data"); 
                System.out.println("3. Show");
                System.out.println("4. Exit");
                System.out.print("Choose: ");
                try {
                    co = sc.nextInt();
                } catch (Exception e) {
                    System.out.println(e);
                }
                sc.nextLine();
            }while(co < 1 || co > 4);
            switch (co) {
                case 1:
                    // Bakal balik ke Main menu
                    break;
                case 2:
                    do{
                        System.out.println("\nWhich table to insert? 1. User, 2. Team.");
                        try {
                            co = sc.nextInt();
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        sc.nextLine();
                    }while(co != 1 && co != 2);
                    fr.insertData(co);
                    break;
                case 3:
                    do{
                        System.out.println("\nWhich table to insert? 1. User, 2. Team.");
                        try {
                            co = sc.nextInt();
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        sc.nextLine();
                    }while(co != 1 && co != 2);
                    fr.showData(co);
                    break;
                case 4:
                    call = false;
                    break;
            }
        }while(call);
        sc.close();
    }
}
