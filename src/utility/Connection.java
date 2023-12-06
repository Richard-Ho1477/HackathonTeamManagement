package utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import models.*;

public class Connection {
    private static Connection connection = null;

    private ArrayList <Model> model;

    private Connection(){
        this.model = new ArrayList<>();
    }

    public static synchronized Connection getConnection(){
        if (connection == null)
            connection = new Connection();
 
        return connection;
    }

    public ArrayList<Model> readFile(String fileName){
        File file = new File(fileName);
        String inputLog = "";
        model.clear();

        try {
            Scanner fileR = new Scanner(file);
            inputLog = fileR.nextLine();
            while(fileR.hasNextLine()){
                
                inputLog = fileR.nextLine();
                int limit = inputLog.length() - inputLog.replace(",", "").length();
                String[] word = inputLog.split(",",limit+1);
                if(limit == 2) model.add(new User(word[1],Integer.valueOf(word[2]),word[0]));
                else model.add(new Team(word[1],Integer.valueOf(word[0])));
                
            }
            fileR.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return this.model;
    }

    public void updateFile(String fileName, Model model){
        File file = new File(fileName);
        File newFile = new File("temp.csv");
        String currentLine = "";
        try {
            FileWriter fw = new FileWriter("temp.csv", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
        
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
        
            while((currentLine = br.readLine()) != null){
                pw.println(currentLine);
            }
            if(fileName.compareTo("user.csv") == 0) currentLine = String.format("%s,%s,%d",((User)model).getNim(), model.getNama(), model.getId());
            else currentLine = String.format("%d,%s", model.getId(), model.getNama());
            pw.println(currentLine);
        
            pw.flush(); pw.close(); fr.close(); br.close(); bw.close(); fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        file.delete();
        File dump = new File(fileName);
        newFile.renameTo(dump);
    }
}
