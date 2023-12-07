package repository;

import java.util.ArrayList;
import java.util.Scanner;

public class FacadeRep {
    private TeamRepository teamRep;
    private UserRepository userRep;
    private Scanner sc;
    private String inputName, inputNim, inputTeamName, condition;
    private Integer filter, join;

    public FacadeRep(){
        this.teamRep = new TeamRepository();
        this.userRep = new UserRepository();
        this.sc = new Scanner(System.in);
    }

    public void insertData(int fType){
        if(fType == 1) insertUser();
        else insertTeam();
    }

    public void showData(int fType){
        do{
            System.out.println("Want to filter by condition? 1. Yes, 2. No");
            try {
                this.filter = sc.nextInt();
            } catch (Exception e) {
                System.out.println(e);
            }
            sc.nextLine();
        }while(this.filter != 1 && this.filter != 2);
        do{
            System.out.println("Want to join table? 1. Yes, 2. No.");
            try {
                this.join = sc.nextInt();
            } catch (Exception e) {
                System.out.println(e);
            }
            sc.nextLine();
        }while(this.join != 1 && this.join != 2);
        do{
            if(this.filter == 1)System.out.println("Add codition. Seperate filter by semicolon.");
            else System.out.println("Add codition.");
            try {
                this.condition = sc.nextLine();
            } catch (Exception e) {
                System.out.println(e);
                sc.nextLine();
            }
            int limit = this.condition.length() - this.condition.replace(";", "").length();
            String[] word = this.condition.split(";",limit+1);
            if(this.filter == 1 && word.length > 1 && this.condition.charAt(this.condition.length() - 1) != ';') break;
            else if(this.filter == 2 && !this.condition.contains(";")) break;
        }while(true);
        
        String atribut = "";
        ArrayList <String> filter = new ArrayList<>();
        boolean bJoin = true;
        if(this.filter == 1){
            int limit = this.condition.length() - this.condition.replace(";", "").length();
            String[] word = this.condition.split(";",limit+1);
            atribut = word[0];
            for (int i = 1; i < word.length; i++) filter.add(word[i]);
        }
        else atribut = this.condition;

        if(this.join == 2) bJoin = false;

        if(fType == 1) userRep.show(atribut,filter,bJoin,"team.csv","conn");
        else teamRep.show(atribut,filter,bJoin,"user.csv","conn");
    }

    private void insertUser(){
        do{
            System.out.print("Add name: ");
            try {
                this.inputName = sc.nextLine();
            } catch (Exception e) {
                System.out.println(e);
            }
            System.out.print("Add nim: ");
            try {
                this.inputNim = sc.nextLine();
            } catch (Exception e) {
                System.out.println(e);
            }
            System.out.print("Add team: ");
            try {
                this.inputTeamName = sc.nextLine();
            } catch (Exception e) {
                System.out.println(e);
            }
        }while(userRep.insert(this.inputName, 0, this.inputNim, this.inputTeamName));

        System.out.println("User created!");
    }

    private void insertTeam(){
        do{
            System.out.print("Add name: ");
            try {
                this.inputName = sc.nextLine();
            } catch (Exception e) {
                System.out.println(e);
            }
        }while(teamRep.insert(this.inputName, 0, "", ""));

        System.out.println("Team created!");
    }
}
