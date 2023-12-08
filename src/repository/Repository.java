package repository;

import java.util.ArrayList;

import models.*;

public interface Repository {
    boolean insert(String nama, Integer id, String nim, String teamName);
    void find(String atribut, ArrayList<String> filter, boolean join, String table, String con);
    Model findOne(String atribut, ArrayList<String> filter);
}
