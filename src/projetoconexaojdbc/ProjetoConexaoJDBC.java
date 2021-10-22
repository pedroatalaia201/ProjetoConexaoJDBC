package projetoconexaojdbc;
import conexao.Conexao;

public class ProjetoConexaoJDBC {

   
    public static void main(String[] args) {
        Conexao con = new Conexao();
        
        con.getConection();
    }   
}
