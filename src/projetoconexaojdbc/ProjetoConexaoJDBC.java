package projetoconexaojdbc;

import conexao.Conexao;
import java.util.ArrayList;
import java.util.List;
import dao.DaoCarImp;
import dao.DaoMarcaImp;
import javax.swing.JOptionPane;
import model.Car;
import model.Marca;


public class ProjetoConexaoJDBC {

   
    public static void main(String[] args) {
        DaoCarImp dao = new DaoCarImp();
        DaoMarcaImp daoMarc = new DaoMarcaImp();
        
        int op;
        int pos = -1;
       
        int totalSales = 0;
        do{
            op = Integer.parseInt(JOptionPane.showInputDialog("Digite: \n1- para cadastrar um carro\n"
                    + "2-Alterar os carros cadastrados\n3-Deletar os carros\n4-Listar os carros\n"
                    + "5-Comprar\n6-Vender\n7-Total vendas\n8-Sai"));
            
            switch(op)
            {
                case 1: //cadastrar carro
                    int key = 0;
                    Car car = new Car();
                    //car.setMarca(JOptionPane.showInputDialog("Digite a marca do carro:"));
                    List<Marca> marcas = daoMarc.getMarcas();
                    
                    String myMarc = JOptionPane.showInputDialog("Digite a marcado carro a ser cadstrado");
                    
                    for(int i = 0; i < marcas.size(); i++){
                     if(myMarc.equals(marcas.get(i).getDescricao()))   {
                         key = marcas.get(i).getId();
                     }
                    }
                    if(key != 0){
                        car.setMarca(key);
                        car.setModelo(JOptionPane.showInputDialog("Digite o modelo do carro:"));
                        car.setAno(Integer.parseInt(JOptionPane.showInputDialog("Digite o ano do carro:")));
                        car.setValor(Double.parseDouble(JOptionPane.showInputDialog("Digite o valor do carro: ")));
                        
                        dao.saveCar(car);
                        key = 0;
                    }else{
                        JOptionPane.showMessageDialog(null, "Marca não cadastrada, favor cadastrar esta marca antes de utiliza-la");
                    }                            
                    break;
                    
                case 2: //alterar carro
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
                        
                        int keyUpdate = 0;
                        Car carAlt = new Car();
                        
                        List<Marca> marcasUpdate = daoMarc.getMarcas();
                    
                        String myMarcUp = JOptionPane.showInputDialog("Digite a marcado carro a ser cadstrado");
                    
                        for(int i = 0; i < marcasUpdate.size(); i++){
                            if(myMarcUp.equals(marcasUpdate.get(i).getDescricao()))   {
                                key = marcasUpdate.get(i).getId();
                            }
                        }
                        if(keyUpdate != 0){
                        
                            carAlt.setMarca(keyUpdate);
                            carAlt.setModelo(JOptionPane.showInputDialog("Digite o modelo do carro:"));
                            carAlt.setAno(Integer.parseInt(JOptionPane.showInputDialog("Digite o ano do carro:")));
                            //define o id do carro que terá os dados alterados:
                            carAlt.setId(listToChange.get(pos).getId());

                            dao.updateCar(carAlt);
                            pos = -1;
                        }
                    }
                    break;
                    
                case 3: //deletar carro
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
                    
                case 4: //listar carros
                    List<Car> list = new ArrayList<Car>();  
                    list.clear();
                    list = dao.getCars();
        
                    for(int i = 0; i < list.size(); i++){
                        JOptionPane.showMessageDialog(null, list.get(i).toString());
                    }
                    break;
                    
                case 5: //comprar carro
                    String modelBuy = JOptionPane.showInputDialog("Digite o modelo do carro a ser comprado: ");
                    
                    List<Car> listToBuy = new ArrayList<Car>();  
                    listToBuy.clear();
                    listToBuy = dao.getCars();
        
                    for(int i = 0; i < listToBuy.size(); i++){
                        //equals serve para comparação de strings
                        if(modelBuy.equals(listToBuy.get(i).getModelo())){
                            pos = i;
                        }
                    }                    
                    
                    if(pos != -1){
                        int resp = JOptionPane.showConfirmDialog(null, "Você tem certeza de que deseja comprar este carro: " +
                         listToBuy.get(pos).getModelo() + "?" + JOptionPane.YES_NO_OPTION);
                        //Em YES_NO_OPTION  o valor 0 é equivalente ao sim;
                        if(resp == 0){
                            int qtd = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade que deseja comprar: "));
                            int qtdExist = dao.getQtdCar(listToBuy.get(pos).getModelo());
                            int total = qtdExist + qtd;  
                            //para se usar no total de vendas: 
                        
                            dao.updateQtdCar(total, listToBuy.get(pos).getId());
                            
                            JOptionPane.showMessageDialog(null, "Compara efetuada com sucesso");
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Compra cancelada");
                        }
                        pos = -1;
                    }

                    
                    break;
                case 6: //vender carro
                    /* vou ter de verificar se a qtd de carros no banco de daods é maior que zero,
                        e ao realizar a venda ele deve subtrair a quantidade no banco*/
                    
                    String modelToSale = JOptionPane.showInputDialog(null, "Digite o modelo do carro que deseja comprar: ");
                    
                    List<Car> listCarsSales = new ArrayList<Car>();
                    listCarsSales.clear();
                    listCarsSales = dao.getCars();
                    
                    for(int i = 0; i < listCarsSales.size(); i++){
                        if(modelToSale.equals(listCarsSales.get(i).getModelo())){
                            pos = i;
                        }
                    }
                    //checar se o valor de "pos" não mudou, o que significa que o carro não foi encontrado;
                    if(pos != -1){
                        //quantidade total de carros desse modelo:
                        int totalModels = listCarsSales.get(pos).getQtd();
                        if(totalModels > 0){
                            int qtdToSale = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite a quantidade que deseja comprar do modelo de carro selecionado:"
                                    + "(quantidade disponivel: " + totalModels + ")"));
                            
                            if(qtdToSale <= totalModels){
                                
                                totalModels = (totalModels - qtdToSale);
                                
                                Car carSale = new Car();
                                carSale.setMarca(listCarsSales.get(pos).getMarca());
                                carSale.setModelo(listCarsSales.get(pos).getModelo());
                                carSale.setAno(listCarsSales.get(pos).getAno());
                                carSale.setQtd(totalModels);                     
                                carSale.setId(listCarsSales.get(pos).getId());

                                dao.updateCar(carSale);
                                
                                JOptionPane.showMessageDialog(null, "Venda realizada com sucesso");
                                totalSales = (totalSales + qtdToSale);
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Quantidade informada é maior que a de carros no estoque...");
                                
                            }
                                    
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Desculpe, todas as unidades desse carro já foram vendidas...");
                            
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Modelo de carro não encontrado");
                        
                    }
                                                
                    pos = -1; //voltar a posição inicial da variável auxiliar;
                    
                    break;
                case 7: //total de vendas
                    JOptionPane.showMessageDialog(null, "Total de vendas efetuadas: " + totalSales);
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
