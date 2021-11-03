package model;

public class Car {
    private int id;
    private String modelo;
    private String marca;
    private int ano;
    
    public Car(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    @Override
    public String toString() {
        return "Car{" + "id=" + id + ", modelo=" + modelo + ", marca=" + marca + ", ano=" + ano + '}';
    }
    
    
}
