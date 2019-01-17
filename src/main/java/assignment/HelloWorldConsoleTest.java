package assignment;

import core.marker.Assignment;
import core.marker.AssignmentTest;
import work.ConsoleWriter;
import work.ConsoleWriterImpl;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static core.util.Asserts.assertEquals;

@Assignment(name = "Вывод в консоль")
public class HelloWorldConsoleTest {

    @AssignmentTest(score = 1, name = "Вывод текста \"Hello world\"")
    public void shouldPrintHelloWorld() {
        ByteArrayOutputStream keeper = new ByteArrayOutputStream();
        PrintStream fakeOut = new PrintStream(keeper);
        ConsoleWriter consoleWriter = new ConsoleWriterImpl(fakeOut);

        consoleWriter.greeting();

        assertEquals("Hello world\r\n", keeper.toString());
    }

    @AssignmentTest(score = 1, name = "Вывод текста \"Привет, мир\"")
    public void shouldPrintInRussian() {
        ByteArrayOutputStream keeper = new ByteArrayOutputStream();
        PrintStream fakeOut = new PrintStream(keeper);
        ConsoleWriter consoleWriter = new ConsoleWriterImpl(fakeOut);

        consoleWriter.greetingInRussian();

        assertEquals("Привет, мир\r\n", keeper.toString());
    }
}
