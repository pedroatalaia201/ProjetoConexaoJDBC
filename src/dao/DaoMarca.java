
package dao;
import model.Marca;
import java.util.List;


public interface DaoMarca {
    public void saveMarca(Marca marca);
    public void updateMarca(Marca marca);
    public void deleteMarca(int id);
    public List<Marca> getMarcas();
}
