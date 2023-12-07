package repository;

import java.util.ArrayList;

import models.Model;
import utility.Connection;

public class TeamRepository implements Repository {
    @Override
    public boolean insert(String nama, Integer id, String nim, String teamName) {
        Connection connection = Connection.getConnection();
        ArrayList<Model> model = connection.readFile("team.csv");

        for (Model m : model) {
            if(m.getNama().compareTo(nama) == 0) {
                System.out.println("Team lain telah memiliki nama ini, mohon masukkan nama lain");
                return true;
            }
        }
        connection.updateFile("team.csv", new Model(nama, model.size()+1));
        return false;
    }

    @Override
    public void show(String atribut, ArrayList<String> filter, boolean join, String table, String con) {
        Connection connection = Connection.getConnection();
        ArrayList<Model> team = connection.readFile("team.csv");
        if(join) {
            ArrayList<Model> user = connection.readFile("user.csv");
        }

        if(atribut.compareTo("name") == 0) {
            if(filter.get(0).compareTo("=") == 0) {
                for (Model t : team) {
                    for(int i = 1; i<filter.size()-1; i++) {
                        if(t.getNama().compareTo(filter.get(i))==0) {
                            System.out.println(t.getNama());
                        }
                    }
                }
            } else if(filter.get(0).compareTo("!=") == 0) {
                for (Model t : team) {
                    for(int i = 1; i<filter.size()-1; i++) {
                        if(t.getNama().compareTo(filter.get(i))!=0) {
                            System.out.println(t.getNama());
                        }
                    }
                }
            }
        }
    }
}
