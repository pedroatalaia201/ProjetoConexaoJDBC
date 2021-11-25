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
            this.pstm = conexao.prepareStatement("INSERT INTO db_car(marca_carro, modelo_carro, ano_carro, qtd_carro, valor_carro) VALUES (?, ?, ?, ?, ?)");
            this.pstm.setString(1, car.getMarca());
            this.pstm.setString(2, car.getModelo());                //precisa estar em ordem;
            this.pstm.setInt(3, car.getAno());
            this.pstm.setInt(4, 0);//sempre que for setar o valor é possível inicia-lo com o valor 0;
            this.pstm.setDouble(5, car.getValor());
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
        String update = "UPDATE db_car SET marca_carro=? , modelo_carro=? , ano_carro=? , qtd_carro=?, valor_carro=?  WHERE id_carro=?";
        
        conexao = new Conexao().getConection();
          
        try{
            //prepara o caminho para o banco de dados;
            this.pstm = conexao.prepareStatement(update);
            
            this.pstm.setString(1, car.getMarca());
            this.pstm.setString(2, car.getModelo());
            this.pstm.setInt(3, car.getAno());
            this.pstm.setInt(4, car.getQtd());
            this.pstm.setDouble(5, car.getValor());
            this.pstm.setInt(6, car.getId());
            
            this.pstm.execute();
            JOptionPane.showMessageDialog(null, "Car has been update successful");
            
            this.pstm.close();
            //fecha o caminho para o banco de dados;
            
        }catch(SQLException erroUpdate){
            JOptionPane.showMessageDialog(null, "Error on update DataBase: " + erroUpdate);
        }finally{
            try
            {
               conexao.close();
            }
            catch(SQLException errorClose)
            {
                JOptionPane.showMessageDialog(null, "Error on try to update and close the DataBase:" + errorClose);
            }
        }
        
    }

    @Override
    public void deleteCar(int id) {
       String delete = "DELETE FROM db_car WHERE id_carro =?";
       conexao = new Conexao().getConection();
       
       try{
           this.pstm = conexao.prepareStatement(delete);
           this.pstm.setInt(1, id);  //aqui receberá o id recebido como parametro;
           this.pstm.execute();
           this.pstm.close();
       }
       catch(SQLException errorDel){
           JOptionPane.showMessageDialog(null, "Error while try to delete car: " + errorDel);
       }
       finally{
           try{
               conexao.close();
           }
           catch(SQLException errorClose){
               JOptionPane.showMessageDialog(null, "Error on close connection to delete car from DataBase:" + delete);
           }
       }
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
                c.setQtd(rs.getInt("qtd_carro"));
                c.setValor(rs.getDouble("valor_carro"));
            
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
    
    @Override
    public int getQtdCar(String modelBuy){
        //SELECT qtd_carro FROM db_car WHERE model0_carro = '';
        
         //Tenta conectar no banco
        conexao =  new Conexao().getConection();
        //Recupera todos os dados de uma consula no BD
        ResultSet rs = null;
        //rs.first(); rs.next(); rs.previous(); rs.last();
        int qtd = 0;
        
        String sql = "SELECT qtd_carro FROM db_car WHERE modelo_carro = ?";
        
        try{
            pstm = conexao.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            this.pstm.setString(1, modelBuy);
            rs = pstm.executeQuery();
            if (rs.first()){
                qtd = rs.getInt("qtd_carro");
            }
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
        return qtd;
    }
    
    @Override 
    public void updateQtdCar(int qtd, int id){
        String update = "UPDATE db_car SET qtd_carro=?  WHERE id_carro=?";
        conexao = new Conexao().getConection();
          
        try{
            //prepara o caminho para o banco de dados;
            this.pstm = conexao.prepareStatement(update);
            
            this.pstm.setInt(1, qtd);          
            this.pstm.setInt(2, id);
            
            this.pstm.execute();
            JOptionPane.showMessageDialog(null, "Car has been update successful");
            
            this.pstm.close();
            //fecha o caminho para o banco de dados;
            
        }catch(SQLException erroUpdate){
            JOptionPane.showMessageDialog(null, "Error on update DataBase: " + erroUpdate);
        }finally{
            try
            {
               conexao.close();
            }
            catch(SQLException errorClose)
            {
                JOptionPane.showMessageDialog(null, "Error on try to update and close the DataBase:" + errorClose);
            }
        }
        
    }
    
}
