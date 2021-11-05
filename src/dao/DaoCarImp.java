package dao;

import java.util.List;
import java.util.ArrayList;
import model.Car;
import java.sql.*;
import conexao.Conexao;
import javax.swing.JOptionPane;


public class DaoCarImp implements DaoCarro{
    //variavel que recebe a conexão da classe Conexao
    Connection conexao;
    //Prepara o caminho até o Banco
    PreparedStatement pstm = null;

    @Override
    public void saveCar(Car car) {
        conexao = new Conexao().getConection();
        
        try{
            this.pstm = conexao.prepareStatement("INSERT INTO db_car(marca_carro, modelo_carro, ano_carro) VALUES (?, ?, ?)");
            this.pstm.setString(1, car.getMarca());
            this.pstm.setString(2, car.getModelo());                //precisa estar em ordem;
            this.pstm.setInt(3, car.getAno());
            this.pstm.execute();
            
            JOptionPane.showMessageDialog(null, "Carro inserido com sucesso");
        }
        catch(SQLException error){
            JOptionPane.showMessageDialog(null, "Erro ao inserir dados do Banco de Dados: " + error);
        }
        finally{
            try{
                conexao.close();
            }
            catch(SQLException errorC){
                JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão: " + errorC);
            }
        }
    }

    @Override
    public void updateCar(Car car) {
        
    }

    @Override
    public void deleteCar(int id) {
       
    }

    @Override
    public List<Car> getCars() {
        //Tenta conectar no banco
        conexao =  new Conexao().getConection();
        //Recupera todos os dados de uma consula no BD
        ResultSet rs = null;
        //rs.first(); rs.next(); rs.previous(); rs.last();
        List<Car> cars = new ArrayList<Car>();
        
        String sql = "select * from db_car";
        
        try{
            pstm = conexao.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = pstm.executeQuery();
            rs.first();
        
            do{
                Car c = new Car();
                c.setId(rs.getInt("id_carro"));
                c.setMarca(rs.getString("marca_carro"));
                c.setModelo(rs.getString("modelo_carro"));
                c.setAno(rs.getInt("ano_carro"));
            
                cars.add(c);
            }while(rs.next());
            pstm.close();
        }
        catch(SQLException e){  
            JOptionPane.showMessageDialog(null, "Erro ao buscar carro no banco de dados: " + e + " SQL: " + sql);
        }
        finally{
            try{
                conexao.close();
            }
            catch(SQLException b){
                JOptionPane.showMessageDialog(null, "Erro ao fechar o banco depois da busca: " + b);
            }
        }
        return cars;
    }
    
}
