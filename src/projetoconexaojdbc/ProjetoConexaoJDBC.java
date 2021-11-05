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
        
        int op;
        do{
            op = Integer.parseInt(JOptionPane.showInputDialog("Digite: \n1- para cadastrar um carro\n2-Listar os carros cadastrados"));
            
            switch(op)
            {
                case 1:
                    Car car = new Car();
                    car.setMarca(JOptionPane.showInputDialog("Digite a marca do carro:"));
                    car.setModelo(JOptionPane.showInputDialog("Digite o modelo do carro:"));
                    car.setAno(Integer.parseInt(JOptionPane.showInputDialog("Digite o ano do carro:")));
                    
                    dao.saveCar(car);
                    break;
                case 2:
                    List<Car> list = new ArrayList<Car>();  
                    list.clear();
                    list = dao.getCars();
        
                    for(int i = 0; i < list.size(); i++){
                        JOptionPane.showMessageDialog(null, list.get(i).toString());
                    }
                    break;
                case 3:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida");
                    break;
            }
        }while(op != 3);
        
        
    }   
}
