package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
     * @throws Exception - пробрасываем исключения
     */
    @SuppressWarnings("checkstyle:InnerAssignment")
    public static void handle(ArgsName argsName) throws Exception {
        var employers = parseFile(argsName);
        StringBuffer dataSB = new StringBuffer();

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
                String s = (String) employer.get(numIndex[i]);
                rowSB.append(s)
                        .append((i < numIndex.length - 1) ? argsName.get("delimiter") : "");
            }
            rowSB.append(System.lineSeparator());
            dataSB.append(rowSB);
        }

        try {
            Files.writeString(Path.of(argsName.get("out")), dataSB);
        } catch (IOException e) {
            System.out.println("Ошибка при записи итогового файла");
        }
    }

    private static List<String> splitRows(ArgsName argsName) {
        List<String> result = new ArrayList<>();
        var scanner = new Scanner(argsName.get("filter"))
                .useDelimiter(",");
        while (scanner.hasNext()) {
            result.add(scanner.next());
        }
        return result;
    }

    /**
     * Выполняет выгрузку файла в коллекцию для дальнейшей обработки
     * @param argsName - перечень принятых параметров при запуске файл
     * @return - возвращает коллекцию, полученных из файла источника
     * @throws IOException - пробрасывает исключения, связанный с доступом к файлу
     */
    private static List<List> parseFile(ArgsName argsName) throws IOException {
        List<List> employee = new LinkedList<>();
        try (var scanner = new Scanner(Path.of(argsName.get("path")))) {
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
        } catch (IOException e) {
            System.out.println("Ошибка при парсинге файла");
        }
        return employee;
    }

    /**
     * Проверка формата заданной строки параметров
     * @param s - строка параметра
     * @return - возвращает true, если строка удовлетворяет формату -key=value, где key и value - не пустые значения.
     */
    private static boolean checkArguments(String s) {
        Pattern pattern = Pattern.compile("^-[a-zA-Z]+=[-?\\w=.,\";:~\\\\]+$");
        Matcher matcher = pattern.matcher(s);
        if (!matcher.find()) {
            throw new IllegalArgumentException("The parameter " + s + " must match the format -key=value. ");
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        String[] param = Arrays.stream(args)
                .filter(CSVReader::checkArguments)
                .toArray(String[]::new);
        ArgsName argsName = ArgsName.of(param);
        CSVReader.handle(argsName);
    }

}
