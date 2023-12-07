package repository;

import java.util.ArrayList;

import models.Model;
import models.User;
import utility.Connection;

public class UserRepository implements Repository{
    @Override
    public boolean insert(String nama, Integer id, String nim, String teamName) {
        Connection connection = Connection.getConnection();
        ArrayList<Model> model = connection.readFile("user.csv");
        int count = 0;
        for (int i = 0; i < model.size(); i++) {
            ArrayList<Model> team = connection.readFile("team.csv");
            if(i+1 == model.size() && team.get(i).getNama().compareTo(teamName) != 0) {
                TeamRepository tTemp = new TeamRepository();
                boolean temp = tTemp.insert(teamName, 0, "","");
            }
            else if(model.get(i).getNama().compareTo(teamName) == 0) break;
        }
        for (Model m : model) {
            if(((User) m).getNim().compareTo(nim) == 0) {
                System.out.println("User lain telah memiliki nim ini, mohon masukkan nim lain");
                return true;
            }
            if(m.getId() == id) {
                count++;
            }
        }
        if(count == 3) {
            System.out.println("Team ini telah penuh, harap memilih team lain");
            return true;
        }
        connection.updateFile("user.csv", new User(nama, model.size()+1, nim));
        return false;
    }

    @Override
    public void show(String atribut, ArrayList<String> filter, boolean join, String table, String con){
    }
}
