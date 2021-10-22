package conexao;
import java.sql.*;
import javax.swing.JOptionPane;

public class Conexao {
    
    Connection conexao;
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost:3306/db_conexao";
    private final String user = "root";
    private final String password = "";
    
    public Connection getConection(){
        try{
            Class.forName(driver);
            JOptionPane.showMessageDialog(null,"Driver is working");
            
            conexao = DriverManager.getConnection(url, user, password);
            JOptionPane.showMessageDialog(null, "Conexão realzada com sucesso");
        }
        catch(ClassNotFoundException drive){
            JOptionPane.showMessageDialog(null,"Driver não encontrado: " + drive);
        }
        catch(SQLException sql){
            JOptionPane.showMessageDialog(null,"erro com a fonte de dados" + sql);
        }
        
        return conexao;
    }
}
