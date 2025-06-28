/*
Реализуйте класс CarDealership, содержащий список автомобилей.
У каждой машины:
    ● VIN, модель, производитель, год выпуска, пробег, цена, тип (enum: SEDAN, SUV, ELECTRIC и т.д.).
*/

import java.util.*;
import java.util.stream.Collectors;

public class CarDealership {
    private List<Car> carList;

    public List<Car> getCarList() {
        return carList;
    }

    public CarDealership() {
        carList = new ArrayList<>();
    }

    public CarDealership(List<Car> carList) {
        this.carList = carList;
    }

    // 1. Добавить машину в автоцентр (проверять дубликаты по VIN).
    public boolean addCar(Car newCar) {
        for (Car car : carList) {
            if (car.equals(newCar)) return false;
        }
        carList.add(newCar);
        return true;
    }

    // 2. Найти все машины указанного производителя (использовать Stream).
    public List<Car> getCarsByManufacturer(String manufacturer) {
        return carList.stream().filter(
                car -> car.getManufacturer().equalsIgnoreCase(manufacturer)
        ).toList();
    }

    // 3. Вывести среднюю цену машин определённого типа (SUV, ELECTRIC и др.).
    public OptionalDouble getAveragePriceForBodyType(CarBodyType carBodyType) {
        OptionalDouble averagePrice = carList.stream()
                .filter(car -> car.getBodyType().equals(carBodyType))
                .mapToInt(Car::getPrice).average();
        return averagePrice;
    }

    // 4. Вернуть список машин, отсортированных по году выпуска (от новых к старым).
    public List<Car> getSortedCarList() {
        return carList.stream().sorted(
                Comparator.comparing(Car::getYearOfRelease).reversed()
        ).toList();
    }

    /*
    5. Дополнительно: реализовать статистику:
    o Количество машин каждого типа.
    o Самая старая и самая новая машина в наличии.
    */
    public Map<CarBodyType, Long> getCarTypeStatistics(){
        return carList.stream().collect(Collectors.groupingBy(
                Car::getBodyType,
                Collectors.counting()
        ));
    }

    public Car getOldestCar() {
        return carList.stream().min(Comparator.comparing(Car::getYearOfRelease))
                .orElseThrow(() -> new IllegalStateException("Список машин пуст"));
    }

    public Car getNewestCar() {
        return carList.stream().max(Comparator.comparing(Car::getYearOfRelease))
                .orElseThrow(() -> new IllegalStateException("Список машин пуст"));
    }

    public void printStatistics() {
        if (carList.isEmpty()) {
            System.out.println("Список автомобилей пуст, статистика отсутствует.");
            return;
        }
        System.out.println("Статистика по типам кузова:");
        var carTypeMap = getCarTypeStatistics();
        System.out.println(carTypeMap.keySet().stream().map(
                carType -> carType.toString() + ": " + carTypeMap.get(carType)
        ).collect(Collectors.joining(";\n")));
        System.out.printf("\nСамый новый автомобиль: %s\n", getNewestCar());
        System.out.printf("\nСамый старый автомобиль: %s\n", getOldestCar());
    }

    public String toString() {
        if (carList.isEmpty()) return "Список автомобилей пуст";
        return "Список автомобилей:\n%s".formatted(carList.stream()
                .map(Car::toString).collect(Collectors.joining(";\n")));
    }
}
