import java.time.Year;
import java.util.Objects;
import java.util.Random;

//  3. equals/hashCode (Сравнение автомобилей)
//  • Создайте класс Car с полями: VIN, модель, производитель, год выпуска, пробег, цена
//  • Переопределите equals и hashCode, чтобы две машины считались одинаковыми только при совпадении VIN.
//  • Реализуйте Comparable<Car> для сортировки по году выпуска (от новых к старым).*
public class Car implements Comparable<Car> {
    private final String VIN;
    private final String model;
    private final String manufacturer;
    private final Year yearOfRelease;
    private int mileage;
    private int price;
    private CarBodyType bodyType;

    public Car(String VIN, String model, String manufacturer) {
        this.VIN = VIN;
        this.model = model;
        this.manufacturer = manufacturer;
        Random rand = new Random();
        yearOfRelease = Year.of(rand.nextInt(1980, 2026));
        mileage = rand.nextInt(100, 200001);
        price = rand.nextInt(1000, 300000);
        bodyType = CarBodyType.values()[rand.nextInt(CarBodyType.values().length)];
    }

    public Car(
            String VIN, String model, String manufacturer, Year yearOfRelease,
            int mileage, int price, CarBodyType bodyType
    ) {
        this.VIN = VIN;
        this.model = model;
        this.manufacturer = manufacturer;
        this.yearOfRelease = yearOfRelease;
        this.mileage = mileage;
        this.price = price;
        this.bodyType = bodyType;
    }

    public String getVIN() {
        return VIN;
    }

    public String getModel() {
        return model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public Year getYearOfRelease() {
        return yearOfRelease;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public CarBodyType getBodyType() {
        return bodyType;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(manufacturer).append(" ").append(model).append(" ").append(yearOfRelease)
                .append(", Кузов: ").append(bodyType).append(", VIN: ").append(VIN)
                .append(", mileage: ").append(mileage).append("km, price: $").append(price);
        return sb.toString();
    }

    @Override
    public int hashCode(){
        return Objects.hash(VIN);
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (!(obj instanceof Car)) return false;
        Car other = (Car) obj;
        return this.VIN.equals(other.VIN);
    }

    @Override
    public int compareTo(Car c){
        return c.yearOfRelease.compareTo(yearOfRelease);
    }
}
