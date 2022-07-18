package Maths;

import Impl.ProductOperatorImpl;
import Interfaces.Calculating;
import Interfaces.ProductOperator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

public class CalculatingImpl implements Calculating {
    Workbook workbook = new XSSFWorkbook();
    ProductOperator po = new ProductOperatorImpl();
    Sheet sheet0 = workbook.createSheet("Общая Статистика");

    public static TreeMap<Long, Integer> mapOfSold = new TreeMap<Long, Integer>();

    public static List<Integer> mapOfSum = new ArrayList<>();

    private String[] months = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь",
            "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};


    private int createdMonths = 0;
    private int counter = 0;

    private long idCounter = 1;
    private int rowCounter = 1;
    private int cellCounter = 0;



    public int calculateSumInMonth(int sheetNum) throws IOException {
        FileInputStream fis = new FileInputStream("C:\\Users\\79083\\Desktop\\acc.xlsx");
        FileInputStream fis1 = new FileInputStream("C:\\Users\\79083\\Desktop\\statistics.xlsx");
        Workbook workbook_0 = new XSSFWorkbook(fis);
        Workbook workbook_1 = new XSSFWorkbook(fis1);
        int sum = 0;
        for (int i=1; i<=po.getAmountId(); i++) {
            sum+= workbook_0.getSheetAt(0).getRow(i).getCell(3).getNumericCellValue() +
                    workbook_1.getSheetAt(sheetNum).getRow(i).getCell(1).getNumericCellValue();
        }
        fis1.close();
        fis.close();
        return sum;
    }
    public int calculateSumInTrimester(int monthNum) throws IOException {
        int sumT = mapOfSum.get(monthNum++) + mapOfSum.get(monthNum++) + mapOfSum.get(monthNum);
        return sumT;
    }
    public void makeMonthTable() throws IOException {
        System.out.println("Текущий месяц: " + months[createdMonths]);
        try {
            FileOutputStream fos = new FileOutputStream("C:\\Users\\79083\\Desktop\\statistics.xlsx");
            createdMonths++;
            if (counter==12) {
                counter = 0;
                createdMonths=0;
            }
            Sheet sheet1 = workbook.createSheet(months[counter]);
            counter++;
            Row row1 = sheet1.createRow(0);
            Cell cellId = row1.createCell(0);
            cellId.setCellValue("ID продукта");
            Cell cellSold = row1.createCell(1);
            cellSold.setCellValue("Продано");
            Cell cellSum = row1.createCell(2);
            cellSum.setCellValue("Сумма");

            FileInputStream fis = new FileInputStream("C:\\Users\\79083\\Desktop\\acc.xlsx");
            Workbook workbook1 = new XSSFWorkbook(fis);

            Row row2 = sheet1.createRow(rowCounter);
            Cell cellIDs = row2.createCell(cellCounter);
            cellIDs.setCellValue(workbook1.getSheetAt(0).getRow(rowCounter).getCell(cellCounter).getNumericCellValue());
            while (idCounter< po.getAmountId()) {
                rowCounter++;
                Row row3 = sheet1.createRow(rowCounter);
                Cell cellIDs1 = row3.createCell(cellCounter);
                idCounter++;
                cellIDs1.setCellValue(idCounter);
            }
            addSoldAmount(sheet1);
            workbook.write(fos);
            fis.close();
        } catch (NullPointerException e) {
            System.out.println("Нет товаров!");
        }
    }

    public void addSoldAmount(Sheet sheet) throws IOException {
        FileOutputStream fos = new FileOutputStream("C:\\Users\\79083\\Desktop\\statistics.xlsx");
        long localIdCounter = 1;
        int localRowCounter = 1;
        while (localIdCounter<= po.getAmountId()) {
            Row localRow = sheet.createRow(localRowCounter);
            System.out.println("Сколько было продано: " + po.getName(localIdCounter));
            int sold = new Scanner(System.in).nextInt();
            mapOfSold.put(localIdCounter, sold);
            int sum = mapOfSold.get(localIdCounter) * po.getPrice(localIdCounter);
            mapOfSum.add(sum);
            Cell cellId = localRow.createCell(0);
            Cell cellSold = localRow.createCell(1);
            Cell cellSum = localRow.createCell(2);
            cellId.setCellValue(localIdCounter);
            cellSold.setCellValue(sold);
            cellSum.setCellValue(sum);
            localIdCounter++;
            localRowCounter++;
            workbook.write(fos);
        }
    }


    public void closeStream() throws IOException {
        FileOutputStream fos = new FileOutputStream("C:\\Users\\79083\\Desktop\\statistics.xlsx");
        workbook.write(fos);
        fos.close();
    }
}
