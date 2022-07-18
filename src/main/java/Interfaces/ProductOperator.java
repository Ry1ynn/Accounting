package Interfaces;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface ProductOperator {
    long getAmountId();
    String getName(long id);
    void createBook() throws IOException;
    void addProduct(String name) throws IOException;
    void closeStream() throws IOException;
    void removeProduct(long id);
    void changeProductName(long id, String name);
    void changeType(long id, String type);
    void addType(long id, String type);
    void changePrice(long id, int price);
    int getPrice(long id);
}
