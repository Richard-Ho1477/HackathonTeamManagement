package main;

import java.io.File;
import java.util.*;
import utility.*;
import models.*;

public class Main {
    public static void main(String[] args){
        Connection connect = Connection.getConnection();

        ArrayList <Model> m = connect.readFile("user.csv");
        for (Model model : m) {
            System.out.println(model.getId() + model.getNama());
        }

    }
}
