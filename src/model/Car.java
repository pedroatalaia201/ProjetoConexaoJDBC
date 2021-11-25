package model;

public class Car {
    private int id;
    private String modelo;
    private String marca;
    private int ano;
    private int qtd;
    private double valor;
    
    public Car(){
    }
    
    public Car(int id, String modelo, String marca, int ano, int qtd, double valor){
        this.id = id;
        this.modelo = modelo;
        this.marca = marca;
        this.ano = ano;
        this.qtd = qtd;
        this.valor = valor;
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
    
    public int getQtd(){
        return qtd;
    }
    
    public void setQtd(int qtd){
        this.qtd = qtd;
    }
    
    public double getValor(){
        return valor;
    }
    
    public void setValor(double valor){
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Car{\n" + "id=" + id + ", modelo=" + modelo + "\nmarca=" + marca + ", ano=" + ano + 
                "\n quant=" + qtd + ", R$" + valor + '}';
    }
    
    
}
