package projetoconexaojdbc;

import conexao.Conexao;
import java.util.ArrayList;
import java.util.List;
import dao.DaoCarImp;
import javax.swing.JOptionPane;
import model.Car;

public class ProjetoConexaoJDBC {

   
    public static void main(String[] args) {
        DaoCarImp dao = new DaoCarImp();
        
        List<Car> list = new ArrayList<Car>();  
        list.clear();
        list = dao.getCars();
        
        for(int i = 0; i < list.size(); i++){
            JOptionPane.showMessageDialog(null, list.get(i).toString());
        }
    }   
}
