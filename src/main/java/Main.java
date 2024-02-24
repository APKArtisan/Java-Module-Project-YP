import java.util.Scanner;
import java.util.ArrayList;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.start();
    }
}
class Calculator{
    Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
    String rub;
    public void start(){
        System.out.println("Добро пожаловать в калькулятор счета!");
        System.out.println("На скольких человек необходимо разделить счет?");
        while (true){
            System.out.println("Введите количество персон, больше одной...");
            if(scanner.hasNextInt()){
                int command = scanner.nextInt();
                if(command > 1){
                    CalculatorScore(command);
                    break;
                }else{
                    System.out.println("Некоректное количество гостей!");
                    scanner.nextLine();
                }
            }else{
                System.out.println("Некоректное количество гостей!");
                scanner.next();
            }
        }
    }
    public void CalculatorScore(int person){
        ArrayList<Product> productList = new ArrayList<>();
        double productSumm = 0;
        while(true){
            System.out.println("Введите название товара...");
            scanner.nextLine();
            String product = scanner.nextLine();
            while (true){
                System.out.println("Введите стоимость товара в формате рубли.копейки...");
                if(scanner.hasNextDouble()){
                    double cost = scanner.nextDouble();
                    scanner.nextLine();
                    if(cost > 0 ){
                        Product newProduct = new Product(product, cost);
                        productList.add(newProduct);
                        productSumm = productSumm + newProduct.price;
                        rub = formatAmount(productSumm);
                        System.out.println("Товар " + newProduct.name + " успешно добавлен");
                        System.out.printf("Общая стоимость товаров %.2f %s%n", productSumm, rub);
                        break;
                    }else{
                        System.out.println("Введите неотрицательное число, больше нуля!!!");
                    }
                }else{
                    System.out.println("Некоректное значение!");
                    scanner.nextLine();

                }
            }
            System.out.println("Хотите добавить еще один товар?");
            String answer = scanner.next();
            if(answer.equalsIgnoreCase("Завершить")){
                System.out.println("Добавленные товары:");
                  for (Product productItem : productList) {
                     System.out.println("Название: " + productItem.name + ", Стоимость: " + productItem.price + " " + (rub = formatAmount(productItem.price)));
                  }
                productSumm = productSumm / person;
                rub = formatAmount(productSumm);
                System.out.printf("Сколько должен заплатить каждый человек:%n%.2f %s", productSumm, rub);
                break;
            }

        }

    }
    public String formatAmount(Double summ){
        int formatedAmount = (int) Math.floor(summ);
        String rub;

        if((formatedAmount % 100 >= 11) && (formatedAmount % 100 <= 14)){
            rub ="рублей";
        }else if((formatedAmount % 10) == 1) {
            rub ="рубль";
        }else if((formatedAmount % 10 > 1) && (formatedAmount % 10 <= 4)) {
            rub ="рубля";
        }else{
            rub ="рублей";
            }

        return rub;
    }
}

class Product {
    public String name;
    public double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
}
