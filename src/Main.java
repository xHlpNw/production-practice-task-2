import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String choice;
        do {
            System.out.print("""
                    
                    1. Первое задание (массив)
                    2. Второе задание (коллекции)
                    3. Третье задание (equals & hashcode)
                    4. Четвёртое задание (StreamAPI)
                    5. Пятое задание (Автоцентр)
                    Выберите пукнт списка или введите 'quit' для возврата к меню:\s""");
            choice = in.nextLine();
            switch (choice) {
                case "1":
                    firstTask();
                    break;
                case "2":
                    secondTask();
                    break;
                case "3":
                    //  3. equals/hashCode (Сравнение автомобилей)
                    //  • Создайте класс Car с полями: VIN, модель, производитель, год выпуска, пробег, цена
                    //  • Переопределите equals и hashCode, чтобы две машины считались одинаковыми только при совпадении VIN.
                    //  • Добавьте в HashSet несколько машин (включая дубликаты по VIN) и убедитесь, что дубликаты не добавляются.
                    //  • Реализуйте Comparable<Car> для сортировки по году выпуска (от новых к старым).*
                    Set<Car> carSet = new HashSet<>(initializeCarList());

                    // попытка добавить автомобиль с уже существующем VIN-номером
                    carSet.add(new Car("DJS9381777J546969", "on-DO", "Datsun"));

                    System.out.println("\nНабор автомобилей:");
                    System.out.println(carSet.stream().map(Car::toString)
                            .collect(Collectors.joining(";\n")));

                    System.out.println("\nОтсортированные по году (от новых к старым):");
                    Car[] carArray = carSet.toArray(new Car[0]);
                    Arrays.sort(carArray);
                    for (Car car : carArray) System.out.println(car);
                    break;
                case "4":
                    //  4. Stream API (Анализ автопарка)
                    //  Дан список машин (List<Car>):
                    List<Car> cars = initializeCarList();

                    System.out.println("\nСписок автомобилей:");
                    String result = cars.stream().map(Car::toString)
                            .collect(Collectors.joining(";\n"));
                    System.out.println(result);

                    //  • Отфильтруйте только машины с пробегом меньше 50_000 км (добавьте поле mileage).
                    System.out.println("\nАвтомобили с пробегом менее 50000км:");
                    result = cars.stream().filter(car -> car.getMileage() < 50000)
                            .map(Car::toString).collect(Collectors.joining(";\n"));
                    System.out.println(result);

                    //  • Отсортируйте по цене (по убыванию).
                    System.out.println("\nСписок автомобилей (по убыванию цены):");
                    result = cars.stream().sorted(Comparator.comparingInt(Car::getPrice).reversed())
                            .map(Car::toString).collect(Collectors.joining(";\n"));
                    System.out.println(result);

                    //  • Выведите топ-3 самые дорогие машины.
                    System.out.println("\nТоп-3 машин по стоимости:");
                    result = cars.stream().sorted(Comparator.comparingInt(Car::getPrice).reversed())
                            .limit(3).map(Car::toString).collect(Collectors.joining(";\n"));
                    System.out.println(result);

                    //  • Посчитайте средний пробег всех машин.
                    OptionalDouble averageMileage = cars.stream().mapToInt(Car::getMileage).average();
                    averageMileage.ifPresentOrElse(
                            avg -> System.out.printf("\nСредний пробег автомобилей: %.2fкм.\n", avg),
                            () -> System.out.println("\nСписок пуст, средний пробег не найден")
                    );

                    //  • Сгруппируйте машины по производителю в Map<String, List<Car>>.
                    Map<String, List<Car>> carsByManufacturers = cars.stream()
                            .collect(Collectors.toMap(
                                    Car::getManufacturer,
                                    car -> new ArrayList<>(List.of(car)),
                                    (existing, replacement) -> {
                                        existing.add(replacement.getFirst());
                                        return existing;
                                    }
                            ));
                    for (String manufacturer : carsByManufacturers.keySet()) {
                        System.out.printf("\nАвтомобили %s:\n", manufacturer);
                        result = carsByManufacturers.get(manufacturer).stream()
                                .map(Car::toString).collect(Collectors.joining(";\n"));
                        System.out.println(result);
                    }
                    break;
                case "5":
                    fifthTask();
                    break;
                case "quit":
                    break;
                default:
                    System.out.println("Ошибка! Неизвестная команда, повторите ввод.");
            }
        } while (!choice.equals("quit"));
    }

    public static void firstTask(){
        //  1. Массивы (Работа с парком машин)
        //      • Создайте массив, представляющий годы выпуска 50 случайных машин (от 2000 до 2025).
        //      • Выведите только машины, выпущенные после 2015 года.
        //      • Посчитайте средний возраст авто.

        int[] carYears;
        carYears = generateRandomCarYears(50, 2000, 2025);
        Scanner in = new Scanner(System.in);
        String choice;
        do {
            System.out.print("""
                    
                    1. Вывести массив
                    2. Сгенерировать массив
                    3. Вывести машины, выпущенные после 2015 года
                    4. Вывести средний возраст авто
                    Выберите пункт списка или введите 'return' для возврата к меню:\s""");
            choice = in.nextLine();

            switch (choice){
                case "1":
                    System.out.println("Массив:");
                    printArray(carYears, 20);
                    break;
                case "2":
                    carYears = generateRandomCarYears(50, 2000, 2025);
                    System.out.println("Новый массив:");
                    printArray(carYears, 20);
                    break;
                case "3":
                    int count = 0;
                    for (int year : carYears) {
                        if (year > 2015) {
                            count++;
                            System.out.printf("%s\t", year);
                            if (count % 20 == 0) System.out.println();
                        }
                    }
                    if (count % 20 != 0) System.out.println();
                    break;
                case "4":
                    int sum = 0;
                    for (int year : carYears) sum += year;
                    double average = (double) sum / carYears.length;
                    System.out.printf("Среднее значение: %.2f\n", average);
                    break;
                case "return":
                    break;
                default:
                    System.out.println("Ошибка! Неизвестная команда, повторите ввод.");
            }
        } while (!choice.equals("return"));
    }

    public static void secondTask(){
        //  2. Коллекции (Управление моделями)
        //  • Создайте список с названиями моделей машин (например: Toyota Camry, BMW X5). Могут быть дубликаты!
        List<String> carModels = new ArrayList<>(List.of(
                "Toyota Camry", "BMW M5", "Audi A6", "Porsche Taycan", "Toyota Camry",
                "Porsche Cayenne", "Tesla Model Y", "Tesla Cybertruck", "BMW X6",
                "Audi RS6", "BMW M5", "Toyota Camry", "Volkswagen Touareg", "Tesla Model S",
                "BMW M5 Touring", "Porsche Taycan Turbo S"
        ));
        System.out.println("Первоначальный список:");
        System.out.println(String.join(", ", carModels));

        //  • Удалите дубликаты, затем отсортируйте модели в обратном алфавитном порядке, выведите на экран, затем сохраните в Set.
        // Удаление дубликатов через Set
        Set<String> set = new LinkedHashSet<>(carModels);
        List<String> notDuplicatedList = new ArrayList<>(set);
        System.out.println("\nСписок без дубликатов (через LinkedHashSet):");
        System.out.println(String.join(", ", notDuplicatedList));

        // Алгоритмическое удаление дубликатов
        for (int i = 0; i < carModels.size(); i++){
            for (int j = i + 1; j < carModels.size(); j++){
                if (carModels.get(i).equals(carModels.get(j))) {
                    carModels.remove(j);
                    j--;
                }
            }
        }
        System.out.println("\nСписок без дубликатов (Алгоритмически):");
        System.out.println(String.join(", ", carModels));

        // Сортировка в обратном алфавитном порядке
        carModels.sort(Collections.reverseOrder());
        System.out.println("\nОтсортированный список:");
        System.out.println(String.join(", ", carModels));

        // Сохранение в set
        set = new HashSet<>(carModels);
        System.out.println("\nМножество (Set):");
        System.out.println(String.join(", ", set));

        //  • Реализуйте проверку: если модель содержит слово "Tesla", замените её на "ELECTRO_CAR".
        for (int i = 0; i < carModels.size(); i++){
            if (carModels.get(i).contains("Tesla")) {
                carModels.set(i, "ELECTRO_CAR");
            }
        }
        System.out.println("\nСписок с заменой 'Tesla*' на 'ELECTRO_CAR':");
        System.out.println(String.join(", ", carModels));
    }

    public static void fifthTask() {
        /*
            Реализуйте класс CarDealership, содержащий список автомобилей.
            У каждой машины:
            ● VIN, модель, производитель, год выпуска, пробег, цена, тип (enum: SEDAN, SUV, ELECTRIC и т.д.).
            Методы:
            1. Добавить машину в автоцентр (проверять дубликаты по VIN).
            2. Найти все машины указанного производителя (использовать Stream).
            3. Вывести среднюю цену машин определённого типа (SUV, ELECTRIC и др.).
            4. Вернуть список машин, отсортированных по году выпуска (от новых к старым).
            5. Дополнительно: реализовать статистику:
                o Количество машин каждого типа.
                o Самая старая и самая новая машина в наличии.

            Создайте меню с возможностью вызова каждого метода (например, через Scanner).
        */
        CarDealership dealership = new CarDealership();
        Scanner in = new Scanner(System.in);
        String choice;
        do {
            System.out.print("""
                    
                    1. Вывести список автомобилей
                    2. Инициализировать список
                    3. Добавить машину в автоцентр
                    4. Вывести все машины указанного производителя
                    5. Вывести среднюю цену определённого типа кузова
                    6. Вывести список машин (от новых к старым)
                    7. Вывести статистику
                    Выберите пункт списка или введите 'return' для возврата к меню:\s""");
            choice = in.nextLine();

            switch (choice) {
                case "1":
                    System.out.println(dealership);
                    break;
                case "2":
                    dealership = new CarDealership(initializeCarList());
                    System.out.println("Список автомобилей проинициализирован");
                    break;
                case "3":
                    // 1. Добавить машину в автоцентр (проверять дубликаты по VIN).
                    System.out.print("Введите VIN: ");
                    String VIN = in.nextLine();
                    if (VIN.isEmpty()) {
                        System.out.println("Ошибка! VIN-номер пуст, повторите попытку.");
                        break;
                    }
                    System.out.print("Введите производителя: ");
                    String manufacturer = in.nextLine();
                    if (manufacturer.isEmpty()) {
                        System.out.println("Ошибка! Название производителя не может быть пустым, повторите попытку.");
                        break;
                    }
                    System.out.print("Введите модель: ");
                    String model = in.nextLine();
                    if (model.isEmpty()) {
                        System.out.println("Ошибка! Название модели не может быть пустым, повторите попытку.");
                        break;
                    }
                    if (dealership.addCar(new Car(VIN, model, manufacturer))) {
                        System.out.println("Автомобиль добавлен в ассортимент дилера");
                    } else System.out.println("У дилера уже зарегистрирован автомобиль с таким VIN-номером");
                    break;
                case "4":
                    // 2. Найти все машины указанного производителя (использовать Stream).
                    System.out.print("Введите название производителя: ");
                    String carManufacturer = in.nextLine();
                    List<Car> manufacturerCars = dealership.getCarsByManufacturer(carManufacturer);
                    if (manufacturerCars.isEmpty()) {
                        System.out.println("У дилера нет автомобилей с данным производителем");
                    } else System.out.println(manufacturerCars.stream().map(Car::toString)
                                .collect(Collectors.joining(";\n")));
                    break;
                case "5":
                    // 3. Вывести среднюю цену машин определённого типа (SUV, ELECTRIC и др.).
                    System.out.println("Варианты кузовов: SEDAN, HATCHBACK, ESTATE, COUPE, SUV, PICKUP, CONVERTIBLE");
                    System.out.print("Введите название кузова из предложенного списка: ");
                    CarBodyType carType;
                    try {
                        carType = CarBodyType.valueOf(in.nextLine().toUpperCase());
                    } catch (Exception e) {
                        System.out.println("Ошибка! Такового кузова нет в списке, повторите попытку.");
                        break;
                    }
                    OptionalDouble avgPrice = dealership.getAveragePriceForBodyType(carType);
                    avgPrice.ifPresentOrElse(
                            avg -> System.out.printf("Средняя цена машин с кузовом %s: $%.2f\n",
                                    carType, avg),
                            () -> System.out.printf("Отсутствуют автомобили с типом кузова %s\n", carType)
                    );
                    break;
                case "6":
                    // 4. Вернуть список машин, отсортированных по году выпуска (от новых к старым).
                    List<Car> list = dealership.getSortedCarList();
                    if (list.isEmpty()) {
                        System.out.println("Список автомобилей пуст");
                        break;
                    }
                    System.out.println(list.stream().map(Car::toString)
                            .collect(Collectors.joining(";\n")));
                    break;
                case "7":
                    // 5. Дополнительно: реализовать статистику:
                    //   o Количество машин каждого типа.
                    //   o Самая старая и самая новая машина в наличии.
                    dealership.printStatistics();
                    break;
                case "return":
                    break;
                default:
                    System.out.println("Ошибка! Неизвестная команда, повторите ввод.");
            }
        } while (!choice.equals("return"));
    }

    public static void printArray(int[] array, int inline){
        for (int i = 0; i < array.length; i++){
            System.out.printf("%d\t", array[i]);
            if ((i + 1) % inline == 0) System.out.println();
        }
        if (array.length % inline != 0) System.out.println();
    }

    public static int[] generateRandomCarYears(int capacity, int min, int max){
        int[] carYears = new int[capacity];
        Random rand = new Random();
        for (int i = 0; i < carYears.length; i++){
            carYears[i] = rand.nextInt(min, max + 1);
        }
        return carYears;
    }

    public static List<Car> initializeCarList(){
        return new ArrayList<>(List.of(
                new Car("WDB1240821F323866", "911", "Porsche",
                        Year.of(2019), 20000, 250000, CarBodyType.CONVERTIBLE),
                new Car("DJS9381777J546969", "Granta", "Lada",
                        Year.of(2019), 182000, 3000, CarBodyType.HATCHBACK),
                new Car("IYI8768762X215498", "SkyLine GT-R", "Nissan",
                        Year.of(2000), 12000, 400000, CarBodyType.COUPE),
                new Car("UWU8765432K980954", "R8", "Audi"),
                new Car("VOO0000004K000000", "MX-5", "Mazda",
                        Year.of(2019), 26500, 32000, CarBodyType.COUPE),
                new Car("TRU8887777F432441", "TT", "Audi",
                        Year.of(2009), 198900, 18300, CarBodyType.COUPE),
                new Car("XOR9818756S776677", "Levante", "Maserati",
                        Year.of(2020), 28000, 74000, CarBodyType.SEDAN),
                new Car("LOX4343434Z434343", "2101", "Lada",
                        Year.of(1981), 4390, 30000, CarBodyType.SEDAN)
        ));
    }
}