package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Files;

public class CSVReaderTest {

    @Test
    void whenFilterTwoColumns(@TempDir Path folder) throws Exception {
        String data = String.join(
                System.lineSeparator(),
                "name;age;last_name;education",
                "Tom;20;Smith;Bachelor",
                "Jack;25;Johnson;Undergraduate",
                "William;30;Brown;Secondary special"
        );
        File file = folder.resolve("source.csv").toFile();
        File target = folder.resolve("target.csv").toFile();
        ArgsName argsName = ArgsName.of(new String[]{
                "-path=" + file.getAbsolutePath(), "-delimiter=;",
                "-out=" + target.getAbsolutePath(), "-filter=name,education"});
        Files.writeString(file.toPath(), data);
        String expected = String.join(
                System.lineSeparator(),
                "name;education",
                "Tom;Bachelor",
                "Jack;Undergraduate",
                "William;Secondary special"
        ).concat(System.lineSeparator());
        CSVReader.handle(argsName);
        String strFile = Files.readString(target.toPath());
        assertThat(strFile, equalTo(expected));
    }

    @Test
    void whenFilterThreeColumns(@TempDir Path folder) throws Exception {
        String data = String.join(
                System.lineSeparator(),
                "name,age,last_name,education",
                "Tom,20,Smith,Bachelor",
                "Jack,25,Johnson,Undergraduate",
                "William,30,Brown,Secondary special"
        );
        File file = folder.resolve("source.csv").toFile();
        File target = folder.resolve("target.csv").toFile();
        ArgsName argsName = ArgsName.of(new String[]{
                "-path=" + file.getAbsolutePath(), "-delimiter=,",
                "-out=" + target.getAbsolutePath(), "-filter=education,age,last_name"
        });
        Files.writeString(file.toPath(), data);
        String expected = String.join(
                System.lineSeparator(),
                "education,age,last_name",
                "Bachelor,20,Smith",
                "Undergraduate,25,Johnson",
                "Secondary special,30,Brown"
        ).concat(System.lineSeparator());
        CSVReader.handle(argsName);
        assertThat(Files.readString(target.toPath()), equalTo(expected));
    }

    @Test
    void whenOutSdtOut(@TempDir Path folder) throws Exception {
        String data = String.join(
                System.lineSeparator(),
                "name;age;last_name;education",
                "Tom;20;Smith;Bachelor",
                "Jack;25;Johnson;Undergraduate",
                "William;30;Brown;Secondary special"
        );
        File file = folder.resolve("source.csv").toFile();
        ArgsName argsName = ArgsName.of(new String[]{
                "-path=" + file.getAbsolutePath(), "-delimiter=;",
                "-out=stdout", "-filter=name,education"});
        Files.writeString(file.toPath(), data);
        String expected = String.join(
                System.lineSeparator(),
                "name;education",
                "Tom;Bachelor",
                "Jack;Undergraduate",
                "William;Secondary special"
        ).concat(System.lineSeparator());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        CSVReader.handle(argsName);
        assertThat(out.toString(), equalTo(expected));
    }
}