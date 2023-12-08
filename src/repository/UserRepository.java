package repository;

import java.util.ArrayList;

import models.Model;
import models.User;
import utility.Connection;

public class UserRepository implements Repository{
    @Override
    public boolean insert(String nama, Integer id, String nim, String teamName) {
        Connection connection = Connection.getConnection();
        ArrayList<Model> temp = connection.readFile("team.csv");
        ArrayList<Model> team = (ArrayList) temp.clone();
        ArrayList<Model> model = connection.readFile("user.csv");
        int count = 0;
        for (int i = 0; i < team.size(); i++) {
            if(i+1 == team.size() && team.get(i).getNama().compareTo(teamName) != 0) {
                TeamRepository tTemp = new TeamRepository();
                boolean tem = tTemp.insert(teamName, 0, "","");
                id = team.size() + 1;
            }
            else if(team.get(i).getNama().compareTo(teamName) == 0) {
                id = team.get(i).getId();
                break;
            }
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
        connection.updateFile("user.csv", new User(nama, id, nim));
        return false;
    }

    @Override
    public void find(String atribut, ArrayList<String> filter, boolean join, String table, String con) {
        Connection connection = Connection.getConnection();
        ArrayList<Model> temp = connection.readFile(table);
        ArrayList<Model> team = (ArrayList) temp.clone();
        ArrayList<Model> user = connection.readFile("user.csv");
        int n = 0;
        
        if(join) System.out.printf("| %-12s | %-40s | %-7s | %-30s |\n", "Nim User","Nama User", "Id User", "Nama Team");
        else System.out.printf("| %-12s | %-40s | %-7s |\n", "Nim User", "Nama User", "Id User");

        if(filter.size()==0) {
            for (Model u : user) {
                if(join) {
                    for (Model t : team) {
                        if(u.getId()==t.getId()) {
                            System.out.printf("| %-12s | %-40s | %-7s | %-30s |\n", ((User) u).getNim(), u.getNama(), u.getId(), t.getNama());
                        }
                    }
                } else {
                    System.out.printf("| %-12s | %-40s | %-7s |\n", ((User) u).getNim(), u.getNama(), u.getId());
                }
                return;
            }
        }

        if(atribut.compareTo("name") == 0 || atribut.compareTo("nama") == 0) {
            if(filter.get(0).compareTo("=") == 0) n = 1;
            if(filter.get(0).compareTo("!=") == 0) n = 2;
            if(n == 0) {
                System.out.println("Operator yang dimasukkan salah, harap masukkan kembali");
                return;
            }
            for (Model u : user) {
                for(int i = 1; i<filter.size(); i++) {
                    if((n == 1 && u.getNama().compareTo(filter.get(i))==0) || (n == 2 && u.getNama().compareTo(filter.get(i))!=0)) {
                        if(join) {
                            for (Model t : team) {
                                if(u.getId()==t.getId()) {
                                    System.out.printf("| %-12s | %-40s | %-7s | %-30s |\n", ((User) u).getNim(), u.getNama(), u.getId(), t.getNama());
                                }
                            }
                        } else {
                           System.out.printf("| %-12s | %-40s | %-7s |\n", ((User) u).getNim(), u.getNama(), u.getId());
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
            for (Model u : user) {
                for(int i = 1; i<filter.size(); i++) {
                    if((n == 1 && u.getId() == Integer.valueOf(filter.get(i))) || (n == 2 && u.getId() != Integer.valueOf(filter.get(i))) || (n == 3 && u.getId() < Integer.valueOf(filter.get(i))) || (n == 4 && u.getId() > Integer.valueOf(filter.get(i))) || (n == 5 && u.getId() <= Integer.valueOf(filter.get(i))) || (n == 6 && u.getId() >= Integer.valueOf(filter.get(i)))) {
                        if(join) {
                            for (Model t : team) {
                                if(u.getId()==t.getId()) {
                                    System.out.printf("| %-12s | %-40s | %-7s | %-30s |\n", ((User) u).getNim(), u.getNama(), u.getId(), t.getNama());
                                }
                            }
                        } else {
                           System.out.printf("| %-12s | %-40s | %-7s |\n", ((User) u).getNim(), u.getNama(), u.getId());
                        }
                    }
                }
            }
        } else if(atribut.compareTo("nim") == 0) {
            if(filter.get(0).compareTo("=") == 0) n = 1;
            if(filter.get(0).compareTo("!=") == 0) n = 2;
            if(n == 0) {
                System.out.println("Operator yang dimasukkan salah, harap masukkan kembali");
                return;
            }
            for (Model u : user) {
                for(int i = 1; i<filter.size(); i++) {
                    if((n == 1 && ((User) u).getNim().compareTo(filter.get(i))==0) || (n == 2 && ((User) u).getNim().compareTo(filter.get(i))!=0)) {
                        if(join) {
                            for (Model t : team) {
                                if(u.getId()==t.getId()) {
                                    System.out.printf("| %-12s | %-40s | %-7s | %-30s |\n", ((User) u).getNim(), u.getNama(), u.getId(), t.getNama());
                                }
                            }
                        } else {
                           System.out.printf("| %-12s | %-40s | %-7s |\n", ((User) u).getNim(), u.getNama(), u.getId());
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
        ArrayList<Model> temp = connection.readFile("team.csv");
        ArrayList<Model> team = (ArrayList) temp.clone();
        ArrayList<Model> user = connection.readFile("user.csv");
        int n = 0;
        
        if(filter.size()==0) {
            if(user.size() != 0) return user.get(0);
            return null;
        }

        if(atribut.compareTo("name") == 0 || atribut.compareTo("nama") == 0) {
            if(filter.get(0).compareTo("=") == 0) n = 1;
            if(filter.get(0).compareTo("!=") == 0) n = 2;
            if(n == 0) {
                System.out.println("Operator yang dimasukkan salah, harap masukkan kembali");
                return null;
            }
            for (Model u : user) {
                for(int i = 1; i<filter.size(); i++) {
                    if((n == 1 && u.getNama().compareTo(filter.get(i))==0) || (n == 2 && u.getNama().compareTo(filter.get(i))!=0)) {
                        return u;
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
            for (Model u : user) {
                for(int i = 1; i<filter.size(); i++) {
                    if((n == 1 && u.getId() == Integer.valueOf(filter.get(i))) || (n == 2 && u.getId() != Integer.valueOf(filter.get(i))) || (n == 3 && u.getId() < Integer.valueOf(filter.get(i))) || (n == 4 && u.getId() > Integer.valueOf(filter.get(i))) || (n == 5 && u.getId() <= Integer.valueOf(filter.get(i))) || (n == 6 && u.getId() >= Integer.valueOf(filter.get(i)))) {
                        return u;
                    }
                }
            }
        } else if(atribut.compareTo("nim") == 0) {
            if(filter.get(0).compareTo("=") == 0) n = 1;
            if(filter.get(0).compareTo("!=") == 0) n = 2;
            if(n == 0) {
                System.out.println("Operator yang dimasukkan salah, harap masukkan kembali");
                return null;
            }
            for (Model u : user) {
                for(int i = 1; i<filter.size(); i++) {
                    if((n == 1 && ((User) u).getNim().compareTo(filter.get(i))==0) || (n == 2 && ((User) u).getNim().compareTo(filter.get(i))!=0)) {
                        return u;
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
