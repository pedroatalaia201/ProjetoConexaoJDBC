package dao;
import java.util.List;
import java.util.ArrayList;
import model.Marca;
import java.sql.*;
import conexao.Conexao;
import javax.swing.JOptionPane;
import model.Car;

public class DaoMarcaImp implements DaoMarca {
    
    Connection connection;
    PreparedStatement pstm = null;
    

    @Override
    public void saveMarca(Marca marca) {
        connection = new Conexao().getConection();
    }

    @Override
    public void updateMarca(Marca marca) {
        connection = new Conexao().getConection();
    }

    @Override
    public void deleteMarca(int id) {
        connection = new Conexao().getConection();

    }

    @Override
    public List<Marca> getMarcas() {
        List<Marca> list = new ArrayList<Marca>();
        connection = new Conexao().getConection();
        
         //Recupera todos os dados de uma consula no BD
        ResultSet rs = null;
        //rs.first(); rs.next(); rs.previous(); rs.last();
        
        String sql = "select * from db_marca";
        
        try{
            pstm = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = pstm.executeQuery();
            rs.first();
        
            do{
                Marca m = new Marca();
                m.setId(rs.getInt("id_marca"));
                m.setDescricao(rs.getString("descricao_marca"));
            
                list.add(m);
            }while(rs.next());
            pstm.close();
        }
        catch(SQLException e){  
            JOptionPane.showMessageDialog(null, "Erro ao buscar marca no  banco de dados: " + e + " SQL: " + sql);
        }
        finally{
            try{
                connection.close();
            }
            catch(SQLException b){
                JOptionPane.showMessageDialog(null, "Erro ao fechar o banco depois da busca: " + b);
            }
        }
        
        return list;
    }
    
}
