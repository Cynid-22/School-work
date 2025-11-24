import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        LinkedList<String> queue = new LinkedList<>();

        String filePath = "src/test.txt";

        BufferWriter writer = new BufferWriter(filePath, queue);
        BufferReader reader = new BufferReader(queue);

        writer.start();
        reader.start();

        try {
            writer.join();

            reader.stopReading();
            reader.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}