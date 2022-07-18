package Impl;

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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ProductOperatorImpl implements ProductOperator {

    Workbook workbook = new XSSFWorkbook();
    Sheet sheet0 = workbook.createSheet("Таблица продуктов");


    public static TreeMap<Long, String> mapOfNameProducts = new TreeMap<Long, String>();
    public static TreeMap<Long, String> mapOfTypeProducts = new TreeMap<Long, String>();
    public static TreeMap<Long, Integer> mapOfPriceProducts = new TreeMap<Long, Integer>();


    private final int columnOfId = 0;
    private final int columnOfName = 1;
    private final int columnOfType = 2;
    private final int columnOfPrice = 3;

    private long idCounter = 1;
    private long rowCounter = 1;
    private long cellCounter = 0;
    @Override
    public long getAmountId() {
        return mapOfNameProducts.size();
    }
    @Override
    public String getName(long id) {
        return mapOfNameProducts.get(id);
    }
    public  void createBook() throws IOException {

        FileOutputStream fos = new FileOutputStream("C:\\Users\\79083\\Desktop\\acc.xlsx");

        Row row = sheet0.createRow(0);
        Cell cell0 = row.createCell(0);
        cell0.setCellValue("ID продукта");
        Cell cell1 = row.createCell(1);
        cell1.setCellValue("Название");
        Cell cell2 = row.createCell(2);
        cell2.setCellValue("Тип");
        Cell cell3 = row.createCell(3);
        cell3.setCellValue("Цена (руб)");

        workbook.write(fos);
    }

    @Override
    public void addProduct(String name) throws IOException {

        FileOutputStream fos = new FileOutputStream("C:\\Users\\79083\\Desktop\\acc.xlsx");
        idCounter = 1;
        rowCounter = 1;
        cellCounter = 0;

        while (true) {
            if (mapOfNameProducts.containsKey(idCounter)) {
                idCounter++;
                rowCounter++;
            } else {
                mapOfNameProducts.put(idCounter, name);
                Row row = sheet0.createRow((int) rowCounter);
                Cell cellId = row.createCell((int) cellCounter);
                cellId.setCellValue(idCounter);
                Cell cellName = row.createCell((int)++cellCounter);
                cellCounter--;
                cellName.setCellValue(name);
                workbook.write(fos);
                idCounter++;
                rowCounter++;
                break;
            }
        }
    }


    @Override
    public void removeProduct(long id) {
        mapOfNameProducts.remove(id);
        mapOfPriceProducts.remove(id);
        mapOfTypeProducts.remove(id);

        Row row = sheet0.createRow((int) id);
        Cell cellId = row.createCell(columnOfId);
        cellId.setCellValue((Date) null);
        Cell cellName = row.createCell(columnOfName);
        cellName.setCellValue((Date) null);
        Cell cellType = row.createCell(columnOfType);
        cellType.setCellValue((Date) null);
        Cell cellPrice = row.createCell(columnOfPrice);
        cellPrice.setCellValue((Date) null);
    }

    @Override
    public void changeProductName(long id, String name) {
        mapOfNameProducts.put(id,name);
        Row row = sheet0.createRow((int) id);
        Cell cellId = row.createCell(columnOfId);
        cellId.setCellValue(id);
        Cell cellName = row.createCell(columnOfName);
        cellName.setCellValue(name);
        Cell cellType = row.createCell(columnOfType);
        cellType.setCellValue(mapOfTypeProducts.get(id));
    }

    @Override
    public void changeType(long id, String type) {
        mapOfTypeProducts.put(id,type);
        Row row = sheet0.createRow((int) id);
        Cell cellType = row.createCell(columnOfType);
        cellType.setCellValue(type);
        Cell cellId = row.createCell(columnOfId);
        cellId.setCellValue(id);
        Cell cellName = row.createCell(columnOfName);
        cellName.setCellValue(mapOfNameProducts.get(id));
    }

    @Override
    public void changePrice(long id, int price) {
        mapOfPriceProducts.put(id, price);
        Row row = sheet0.createRow((int) id);
        Cell cellPrice = row.createCell(columnOfPrice);
        cellPrice.setCellValue(price);
        Cell cellType = row.createCell(columnOfType);
        cellType.setCellValue(mapOfTypeProducts.get(id));
        Cell cellId = row.createCell(columnOfId);
        cellId.setCellValue(id);
        Cell cellName = row.createCell(columnOfName);
        cellName.setCellValue(mapOfNameProducts.get(id));
    }

    @Override
    public int getPrice(long id) {
        return mapOfPriceProducts.get(id);
    }

    @Override
    public void addType(long id, String type) {
        mapOfTypeProducts.put(id,type);
    }

    @Override
    public void closeStream() throws IOException {
        FileOutputStream fos = new FileOutputStream("C:\\Users\\79083\\Desktop\\acc.xlsx");
        workbook.write(fos);
        fos.close();
    }

}
