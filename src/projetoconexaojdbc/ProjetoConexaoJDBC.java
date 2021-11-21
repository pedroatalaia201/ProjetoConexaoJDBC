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
        int pos = -1;
        do{
            op = Integer.parseInt(JOptionPane.showInputDialog("Digite: \n1- para cadastrar um carro\n"
                    + "2-Alterar os carros cadastrados\n3-Deletar os carros\n4-Listar os carros\n"
                    + "5-Comprar\n6-Vender\n7-Total vendas\n8-Sai"));
            
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
                    String modelo = JOptionPane.showInputDialog("Digite o modelo do carro a ser alterado: ");
                    
                    List<Car> listToChange = new ArrayList<Car>();  
                    listToChange.clear();
                    listToChange = dao.getCars();
        
                    for(int i = 0; i < listToChange.size(); i++){
                        //equals serve para comparação de strings
                        if(modelo.equals(listToChange.get(i).getModelo())){
                            pos = i;
                        }
                    }
                    
                    if(pos != -1){
                        Car carAlt = new Car();
                        carAlt.setMarca(JOptionPane.showInputDialog("Digite a marca do carro:"));
                        carAlt.setModelo(JOptionPane.showInputDialog("Digite o modelo do carro:"));
                        carAlt.setAno(Integer.parseInt(JOptionPane.showInputDialog("Digite o ano do carro:")));
                        //define o id do carro que terá os dados alterados:
                        carAlt.setId(listToChange.get(pos).getId());
                        
                        dao.updateCar(carAlt);
                        pos = -1;
                    }
                    break;
                case 3:
                    String modelDel = JOptionPane.showInputDialog("Digite o modelo do carro a ser alterado: ");
                    
                    List<Car> listToDelete = new ArrayList<Car>();  
                    listToDelete.clear();
                    listToChange = dao.getCars();
        
                    for(int i = 0; i < listToDelete.size(); i++){
                        //equals serve para comparação de strings
                        if(modelDel.equals(listToDelete.get(i).getModelo())){
                            pos = i;
                        }
                    }
                    
                    
                    
                    if(pos != -1){
                        int resp = JOptionPane.showConfirmDialog(null, "Você tem certeza de que deseja excluir este carro?" +
                         listToDelete.get(pos).getModelo() + "Excluir " + JOptionPane.YES_NO_OPTION);
                        //Em YES_NO_OPTION  o valor 0 é equivalente ao sim;
                        if(resp == 0){
                            dao.deleteCar(listToDelete.get(pos).getId());
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Exclusão cancelada");
                        }
                        pos = -1;
                    }
                    break;
                case 4:
                    List<Car> list = new ArrayList<Car>();  
                    list.clear();
                    list = dao.getCars();
        
                    for(int i = 0; i < list.size(); i++){
                        JOptionPane.showMessageDialog(null, list.get(i).toString());
                    }
                    break;
                case 5:
                    String modelBuy = JOptionPane.showInputDialog("Digite o modelo do carro a ser comprado: ");
                    
                    List<Car> listToBuy = new ArrayList<Car>();  
                    listToBuy.clear();
                    listToChange = dao.getCars();
        
                    for(int i = 0; i < listToBuy.size(); i++){
                        //equals serve para comparação de strings
                        if(modelBuy.equals(listToBuy.get(i).getModelo())){
                            pos = i;
                        }
                    }                    
                    
                    if(pos != -1){
                        int resp = JOptionPane.showConfirmDialog(null, "Você tem certeza de que deseja comprar este carro?" +
                         listToBuy.get(pos).getModelo() + "Comprar " + JOptionPane.YES_NO_OPTION);
                        //Em YES_NO_OPTION  o valor 0 é equivalente ao sim;
                        if(resp == 0){
                            int qtd = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade que deseja comprar: "));                         
                            int total = dao.getQtdCar(listToBuy.get(pos).getModelo()) + qtd;                            
                            dao.updateQtdCar(total, listToBuy.get(pos).getId());
                            
                            JOptionPane.showMessageDialog(null, "Compara efetuada com sucesso");
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Compra cancelada");
                        }
                        pos = -1;
                    }

                    
                    break;
                case 6:
                    /* vou ter de verificar se a qtd de carros no banco de daods é maior que zero,
                        e ao realizar a venda ele deve subtrair a quantidade no banco*/
                    break;
                case 7:
                    break;
                case 8:
                    JOptionPane.showMessageDialog(null, "Saindo........");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida");
                    break;
            }
        }while(op != 8);
        
        
    }   
}
