package Impl;

import Interfaces.Calculating;
import Interfaces.Connector;
import Interfaces.ProductOperator;
import Maths.CalculatingImpl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class ConnectorImpl implements Connector {
    private long idCounter = 1;
    ProductOperator productOperator = new ProductOperatorImpl();
    Calculating calculating = new CalculatingImpl();

    @Override
    public void createBooks() throws IOException {
        productOperator.createBook();
    }

    @Override
    public boolean Menu() throws IOException {
        System.out.println("Здравствуйте, что вы хотите сделать?" +
                "\n1. Добавить товар " +
                "\n2. Удалить товар " +
                "\n3. Изменить параметры товара " +
                "\n4. Внести количество продаж за текущий месяц" +
                "\n5. Выйти");
        String mainAnswer = new Scanner(System.in).nextLine();
        if (mainAnswer.equalsIgnoreCase("1")) {
            System.out.println("Введите имя товара");
            String answerName = new Scanner(System.in).nextLine();
            System.out.println("Вы ввели: " + answerName + ". Продолжить? " +
                    "\n1. Да" +
                    "\n2. Ввести заново");
            String answerCheck = new Scanner(System.in).nextLine();
            if (answerCheck.equalsIgnoreCase("1")) {
                productOperator.addProduct(answerName);
            } else if (answerCheck.equalsIgnoreCase("2")) {
                System.out.println("Введите заново: ");
                String newAnswer = new Scanner(System.in).nextLine();
                productOperator.addProduct(newAnswer);
            } else {
                System.out.println("Некорректный ввод, возврат в меню...");
                Menu();
            }
            System.out.println("Добавьте ему " +
                    "\n-Тип " +
                    "\n-Цену");
            String newType = new Scanner(System.in).nextLine();
            int newPrice = new Scanner(System.in).nextInt();
            productOperator.changeType(idCounter, newType);
            productOperator.changePrice(idCounter, newPrice);
            idCounter++;
        } else if (mainAnswer.equalsIgnoreCase("2")) {
            System.out.println("Какой товар вы хотите удалить? (Введите ID)");
            long answerId = new Scanner(System.in).nextLong();
            System.out.println("Вы точно хотите его удалить? " +
                    "\n1.Да " +
                    "\n2.Нет");
            String confirm = new Scanner(System.in).nextLine();
            if (confirm.equalsIgnoreCase("1")) {
                productOperator.removeProduct(answerId);
            } else if (confirm.equalsIgnoreCase("2")) {
                Menu();
            } else {
                System.out.println("Некорректный ввод, возврат в меню...");
                Menu();
            }
        } else if (mainAnswer.equalsIgnoreCase("3")) {
            System.out.println("Какой товар вы хотите изменить? (Введите ID)");
            long id = new Scanner(System.in).nextLong();
            System.out.println("Что вы хотите изменить? \n" +
                    "1. Имя \n" +
                    "2. Тип \n" +
                    "3. Цену");
            String selectChoose = new Scanner(System.in).nextLine();
            if (selectChoose.equalsIgnoreCase("1")) {
                System.out.println("Введите имя: ");
                String newName = new Scanner(System.in).nextLine();
                productOperator.changeProductName(id, newName);
            } else if (selectChoose.equalsIgnoreCase("2")) {
                System.out.println("Введите тип: ");
                String newType = new Scanner(System.in).nextLine();
                productOperator.changeType(id, newType);
            } else if (selectChoose.equalsIgnoreCase("3")) {
                System.out.println("Введите цену: ");
                int newPrice = new Scanner(System.in).nextInt();
                productOperator.changePrice(id, newPrice);
            }
        } else if (mainAnswer.equalsIgnoreCase("4")) {
            calculating.makeMonthTable();
        }else if (mainAnswer.equalsIgnoreCase("5")) {
                return false;
            } else {
                System.out.println("Некорректный ввод, возврат в меню...");
                Menu();
            }
            return true;
    }
}
