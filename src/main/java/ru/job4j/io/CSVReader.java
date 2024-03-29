package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * Программа парсинга CSV-файлов
 * Пример запуска программы:
 * java -jar target/csvReader.jar -path=file.csv -delimiter=";"  -out=stdout -filter=name,age
 */
public class CSVReader {
    /**
     * Парсинг входящих CSV-файлов и преобразование их в иной CSV-файл,
     * составленный на основе переданного фильтра
     * @param argsName - параметры, переданные при запуске программы:
     *                 -path - путь источника данных
     *                 -out - путь к файлу с результатом. Значение stdout - вывод на консоль
     *                 -delimiter - разделитель, применяемый в CSV-файле
     *                 -filter - названия столбцов для выборки. Эти данные
     *                 будут переданы в файл с результатами
     */
    public static void handle(ArgsName argsName) {
        var employers = parseFile(argsName);
        StringBuilder dataSB = new StringBuilder();
        List<String> paramFilter;
        paramFilter = splitRows(argsName);
        int[] numIndex = new int[paramFilter.size()];
        int index = 0;
        for (String value : paramFilter) {
            numIndex[index++] = employers.get(0).indexOf(value);
        }
        for (var employer : employers) {
            StringBuilder rowSB = new StringBuilder();
            for (int i = 0; i < numIndex.length; i++) {
                String s = employer.get(numIndex[i]);
                rowSB.append(s)
                        .append((i < numIndex.length - 1) ? argsName.get("delimiter") : "");
            }
            rowSB.append(System.lineSeparator());
            dataSB.append(rowSB);
        }
        try {
            String out = argsName.get("out").toLowerCase();
            if ("stdout".equals(out)) {
                System.out.print(dataSB);
            } else {
                Files.writeString(Path.of(out), dataSB);
            }
        } catch (IOException e) {
            System.out.println("Ошибка при записи итогового файла");
        }
    }

    private static List<String> splitRows(ArgsName argsName) {
        List<String> result = new ArrayList<>();
        try (var scanner = new Scanner(argsName.get("filter"))
                .useDelimiter(",")) {
            while (scanner.hasNext()) {
                result.add(scanner.next());
            }
        } catch (Exception e) {
            System.out.println("Не удалось обработать названия столбцов, "
                    + "переданных в параметре filter");
        }
        return result;
    }

    /**
     * Выполняет выгрузку файла в коллекцию для дальнейшей обработки
     * @param argsName - перечень принятых параметров при запуске файл
     * @return - возвращает коллекцию, полученных из файла источника
     */
    private static List<List<String>> parseFile(ArgsName argsName) {
        List<List<String>> employee = new LinkedList<>();
        try (var scanner = new Scanner(Path.of(argsName.get("path")))) {
            scanner.useDelimiter(System.getProperty("line.separator"));
            while (scanner.hasNext()) {
                List<String> row = new LinkedList<>();
                try (Scanner line = new Scanner(scanner.next())
                        .useDelimiter(argsName.get("delimiter"))) {
                    while ((line.hasNext())) {
                        row.add(line.next());
                    }
                    employee.add(row);
                } catch (Exception e) {
                    System.out.println("Ошибка при парсинге строки из "
                            + "файла по параметру delimiter");
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при парсинге файла");
        }
        return employee;
    }

    private static void checkArgs(ArgsName argsName) {
        if (!(argsName.get("path").endsWith(".csv"))) {
            throw new IllegalArgumentException("Для файла источника необходимо использовать "
                    + "расширение .csv (-path=[nameFile].csv)");
        }
        if (!(("stdout".equals(argsName.get("out"))) || (argsName.get("out")).endsWith(".csv"))) {
            throw new IllegalArgumentException("Для вывода необходимо использовать либо параметр "
                    + "stdout, либо файл с расширением .csv (-out=stdout или -out=[nameFile].csv)");
        }
        if (!(argsName.get("delimiter").matches(";,"))) {
            throw new IllegalArgumentException("Допустимые значения delimiter - знак ; или ,");
        }
        if (!(argsName.get("filter").length() > 0)) {
            throw new IllegalArgumentException("Не указан фильтр в параметре filter");
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length == 4) {
            ArgsName argsName = ArgsName.of(args);
            checkArgs(argsName);
            CSVReader.handle(argsName);
        } else {
            String e = String.join(
                    System.lineSeparator(),
                    "Программе необходимо передать 4 параметра:",
                    "-path - путь источника данных",
                    "-out - путь к файлу с результатом. Значение stdout - вывод на консоль",
                    "-delimiter - разделитель, применяемый в CSV-файле",
                    "-filter - названия столбцов для выборки. "
                            + "Эти данные будут переданы в файл с результатами"
                    );
            throw new IllegalArgumentException(e);
        }
    }
}
