package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {
    /**
     * Метод парсинга CSV-файлов
     * пример запуска программы:
     * java -jar target/csvReader.jar -path=file.csv -delimiter=";"  -out=stdout -filter=name,age
     * @param argsName - параметры, переданные при запуске программы:
     *                 -path - путь источника данных
     *                 -out - путь к файлу с результатом
     *                 -delimiter - разделитель, применяемый в CSV-файле
     *                 -filter - названия столбцов для выборки. Эти данный
     *                 будут переданы в файл с результатами
     * @throws Exception - пробрасываем исключения
     */
    public static void handle(ArgsName argsName) throws Exception {
        List<List> employers = parseFile(argsName);

        //TODO выделить данную часть в отдельный метод getNameColumn
        System.out.println(employers.get(0));
        String[] param = {"name", "age"};
        int count = 0;
        int numParam = 2;
        int[] numIndex = new int[numParam];
        int index = 0;
        for (String s: param) {
            while (index < numParam) {
                if (employers.get(0).get(index).equals(s)) {
                    System.out.println(index);
                    numIndex[count++] = index;
                    break;
                }
                index++;
            }
        }

        System.out.println(numIndex.toString());

        String data = String.join(
                System.lineSeparator(),
                "name;age",
                "Tom;20",
                "Jack;25",
                "William;30"
        ).concat(System.lineSeparator());
        Files.writeString(Path.of(argsName.get("out")), data);
    }



    /**
     * Выполняет выгрузку файла в коллекцию для дальнейшей обработки
     * @param argsName - перечень принятых параметров при запуске файл
     * @return - возвращает коллекцию, полученных из файла источника
     * @throws IOException - пробрасывает исключения, связанный с доступом к файлу
     */
    private static List<List> parseFile(ArgsName argsName) throws IOException {
        List<List> employee = new LinkedList<>();
        var scanner = new Scanner(Path.of(argsName.get("path")));
        scanner.useDelimiter(System.getProperty("line.separator"));

        while (scanner.hasNext()) {
            List<String> row = new LinkedList<>();
            Scanner line = new Scanner(scanner.next());
            line.useDelimiter(argsName.get("delimiter"));
            while ((line.hasNext())) {
                row.add(line.next());
            }
            employee.add(row);
        }
        return employee;
    }
}
