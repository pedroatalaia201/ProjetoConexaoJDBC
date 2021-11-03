package dao;
import java.util.List;
import model.Car;

public interface DaoCarro {
    public void saveCar(Car car);
    public void updateCar(Car car);
    public void deleteCar(int id);
    public List<Car> getCars();
}
