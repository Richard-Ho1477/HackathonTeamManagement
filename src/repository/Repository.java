package repository;

import java.util.ArrayList;

public interface Repository {
    boolean insert(String nama, Integer id, String nim, String teamName);
    void show(String atribut, ArrayList<String> filter, boolean join, String table, String con);
}
