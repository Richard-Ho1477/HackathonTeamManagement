package models;

public class Model {
    private String nama;
    private Integer id;

    public Model(String nama, int id){
        this.nama = nama;
        this.id = id;
    }

    public void setNama(String nama){
        this.nama = nama;
    }

    public String getNama(){
        return nama;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getId(){
        return id;
    }
}
