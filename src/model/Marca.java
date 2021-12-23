package model;

public class Marca {
    private int id;
    private String descricao;
    
    public Marca(){
    }
    
    public Marca(int id, String description){
        this.id = id;
        this.descricao = description;
    }
    
    // press atl + ins to open the diaolog box, and select getters and setters to generate the get and set to each atribute;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    @Override
    public String toString(){
        return "Marca{ " + "id: " + id +", description: " + descricao + " }" ;
    }
}
