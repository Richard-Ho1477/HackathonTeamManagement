package repository;

import java.util.ArrayList;

import models.Model;
import models.User;
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
    public void find(String atribut, ArrayList<String> filter, boolean join, String table, String con) {
        Connection connection = Connection.getConnection();
        ArrayList<Model> temp = connection.readFile(table);
        ArrayList<Model> user = (ArrayList) temp.clone();
        ArrayList<Model> team = connection.readFile("team.csv");
        int n = 0;

        
        if(join) System.out.printf("| %-30s | %-7s | %-12s | %-40s |\n", "Nama Team", "Id Team", "Nim User", "Nama User");
        else System.out.printf("| %-30s | %-7s |\n", "Nama Team", "Id Team");

        if(filter.size()==0) {
            for (Model t : team) {
                if(join) {
                    for (Model u : user) {
                        if(u.getId()==t.getId()) {
                            System.out.printf("| %-30s | %-7d | %-12s | %-40s |\n", t.getNama(), t.getId(), ((User) u).getNim(), u.getNama());
                        }
                    }
                } else {
                    System.out.printf("| %-30s | %-7d |\n", t.getNama(), t.getId());
                }
            }
            return;
        }

        if(atribut.compareTo("name") == 0 || atribut.compareTo("nama") == 0) {
            if(filter.get(0).compareTo("=") == 0) n = 1;
            if(filter.get(0).compareTo("!=") == 0) n = 2;
            if(n == 0) {
                System.out.println("Operator yang dimasukkan salah, harap masukkan kembali");
                return;
            }
            for (Model t : team) {
                for(int i = 1; i<filter.size(); i++) {
                    if((n == 1 && t.getNama().compareTo(filter.get(i))==0) || (n == 2 && t.getNama().compareTo(filter.get(i))!=0)) {
                        if(join) {
                            for (Model u : user) {
                                if(u.getId()==t.getId()) {
                                    System.out.printf("| %-30s | %-7d | %-12s | %-40s |\n", t.getNama(), t.getId(), ((User) u).getNim(), u.getNama());
                                }
                            }
                        } else {
                           System.out.printf("| %-30s | %-7d |\n", t.getNama(), t.getId());
                        }
                    }
                }
            }
        } else if(atribut.compareTo("id") == 0) {
            if(filter.get(0).compareTo("=") == 0) n = 1;
            if(filter.get(0).compareTo("!=") == 0) n = 2;
            if(filter.get(0).compareTo("<") == 0) n = 3;
            if(filter.get(0).compareTo(">") == 0) n = 4;
            if(filter.get(0).compareTo("<=") == 0 || filter.get(0).compareTo("=<") == 0) n = 5;
            if(filter.get(0).compareTo(">=") == 0 || filter.get(0).compareTo(">=") == 0) n = 6;
            if(n == 0) {
                System.out.println("Operator yang dimasukkan salah, harap masukkan kembali");
                return;
            }
            for (Model t : team) {
                for(int i = 1; i<filter.size(); i++) {
                    if((n == 1 && t.getId() == Integer.valueOf(filter.get(i))) || (n == 2 && t.getId() != Integer.valueOf(filter.get(i))) || (n == 3 && t.getId() < Integer.valueOf(filter.get(i))) || (n == 4 && t.getId() > Integer.valueOf(filter.get(i))) || (n == 5 && t.getId() <= Integer.valueOf(filter.get(i))) || (n == 6 && t.getId() >= Integer.valueOf(filter.get(i)))) {
                        if(join) {
                            for (Model u : user) {
                                if(u.getId()==t.getId()) {
                                    System.out.printf("| %-30s | %-7d | %-12s | %-40s |\n", t.getNama(), t.getId(), ((User) u).getNim(), u.getNama());
                                }
                            }
                        } else {
                           System.out.printf("| %-30s | %-7d |\n", t.getNama(), t.getId());
                        }
                    }
                }
            }
        } else {
            System.out.println("Tidak ada atribut dalam tabel");
            return;
        }
    }

    @Override
    public Model findOne(String atribut, ArrayList<String> filter) {
        Connection connection = Connection.getConnection();
        ArrayList<Model> team = connection.readFile("team.csv");
        int n = 0;

        if(filter.size()==0) {
            if(team.size() != 0) return team.get(0);
            return null;
        }

        if(atribut.compareTo("name") == 0 || atribut.compareTo("nama") == 0) {
            if(filter.get(0).compareTo("=") == 0) n = 1;
            if(filter.get(0).compareTo("!=") == 0) n = 2;
            if(n == 0) {
                System.out.println("Operator yang dimasukkan salah, harap masukkan kembali");
                return null;
            }
            for (Model t : team) {
                for(int i = 1; i<filter.size(); i++) {
                    if((n == 1 && t.getNama().compareTo(filter.get(i))==0) || (n == 2 && t.getNama().compareTo(filter.get(i))!=0)) {
                        return t;
                    }
                }
            }
        } else if(atribut.compareTo("id") == 0) {
            if(filter.get(0).compareTo("=") == 0) n = 1;
            if(filter.get(0).compareTo("!=") == 0) n = 2;
            if(filter.get(0).compareTo("<") == 0) n = 3;
            if(filter.get(0).compareTo(">") == 0) n = 4;
            if(filter.get(0).compareTo("<=") == 0 || filter.get(0).compareTo("=<") == 0) n = 5;
            if(filter.get(0).compareTo(">=") == 0 || filter.get(0).compareTo(">=") == 0) n = 6;
            if(n == 0) {
                System.out.println("Operator yang dimasukkan salah, harap masukkan kembali");
                return null;
            }
            for (Model t : team) {
                for(int i = 1; i<filter.size(); i++) {
                    if((n == 1 && t.getId() == Integer.valueOf(filter.get(i))) || (n == 2 && t.getId() != Integer.valueOf(filter.get(i))) || (n == 3 && t.getId() < Integer.valueOf(filter.get(i))) || (n == 4 && t.getId() > Integer.valueOf(filter.get(i))) || (n == 5 && t.getId() <= Integer.valueOf(filter.get(i))) || (n == 6 && t.getId() >= Integer.valueOf(filter.get(i)))) {
                        return t;
                    }
                }
            }
        } else {
            System.out.println("Tidak ada atribut dalam tabel");
            return null;
        }

        return null;
    }
}
