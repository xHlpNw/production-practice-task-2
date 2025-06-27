/*
2. Коллекции (Управление моделями)
    • Создайте список с названиями моделей машин (например: Toyota Camry, BMW X5). Могут быть дубликаты!
    • Удалите дубликаты, затем отсортируйте модели в обратном алфавитном порядке, выведите на экран, затем сохраните в Set.
    • Реализуйте проверку: если модель содержит слово "Tesla", замените её на "ELECTRO_CAR".
3. equals/hashCode (Сравнение автомобилей)
    • Создайте класс Car с полями: VIN, модель, производитель, год выпуска, пробег, цена
    • Переопределите equals и hashCode, чтобы две машины считались одинаковыми только при совпадении VIN.
    • Добавьте в HashSet несколько машин (включая дубликаты по VIN) и убедитесь, что дубликаты не добавляются.
    • Реализуйте Comparable<Car> для сортировки по году выпуска (от новых к старым).*
4. Stream API (Анализ автопарка)
    Дан список машин (List<Car>):
        • Отфильтруйте только машины с пробегом меньше 50_000 км (добавьте поле mileage).
        • Отсортируйте по цене (по убыванию).
        • Выведите топ-3 самые дорогие машины.
        • Посчитайте средний пробег всех машин.
        • Сгруппируйте машины по производителю в Map<String, List<Car>>.
5. Практическое задание: Автоцентр (Реализация системы) *
    Реализуйте класс CarDealership, содержащий список автомобилей. У каждой машины:
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


import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String choice;
        do {
            System.out.print("\n1. Первое задание (массив)\n2. Второе задание (коллекции)\n" +
                    "3. Третье задание (equals & hashcode)\n4. Четвёртое задание (StreamAPI)\n" +
                    "5. Пятое задание (Автоцентр)\n" +
                    "Выберите пукнт списка или введите 'quit' для возврата к меню: ");
            choice = in.nextLine();
            switch (choice) {
                case "1":
                    firstTask();
                    break;
                case "2":

                    break;
                case "3":

                    break;
                case "4":

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
            System.out.print("\n1. Вывести массив\n2. Сгенерировать массив\n" +
                    "3. Вывести машины, выпущенные после 2015 года\n4. Вывести средний возраст авто\n" +
                    "Выберите пукнт списка или введите 'return' для возврата к меню: ");
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
                            System.out.print(year + "\t");
                            if (count % 20 == 0) System.out.println();
                        }
                    }
                    break;
                case "4":
                    int sum = 0;
                    for (int year : carYears) sum += year;
                    double average = (double) sum / carYears.length;
                    System.out.println("Среднее значение: " + average);
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
            System.out.print(array[i] + "\t");
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
}