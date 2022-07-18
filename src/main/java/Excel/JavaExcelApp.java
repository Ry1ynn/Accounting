package Excel;

import Impl.ConnectorImpl;
import Impl.ProductOperatorImpl;
import Interfaces.Calculating;
import Interfaces.Connector;
import Interfaces.ProductOperator;
import Maths.CalculatingImpl;

import java.io.IOException;
import java.util.Scanner;

public class JavaExcelApp {
    public static void main(String[] args) throws IOException {
        Connector connector = new ConnectorImpl();
        connector.createBooks();
        while (connector.Menu()) {
            connector.Menu();
        }

    }
}
