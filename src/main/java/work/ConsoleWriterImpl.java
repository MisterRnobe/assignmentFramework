package work;

import java.io.PrintStream;

public class ConsoleWriterImpl implements ConsoleWriter {
    private final PrintStream console;

    public ConsoleWriterImpl(PrintStream console) {
        this.console = console;
    }

    @Override
    public void greeting() {
        console.println("Hello world");
    }

    @Override
    public void greetingInRussian() {
        console.println("Привет, мир");
    }
}
