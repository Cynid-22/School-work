import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

class BufferWriter extends Thread {
    private String filePath;
    private LinkedList<String> queue;

    public BufferWriter(String filePath, LinkedList<String> queue) {
        this.filePath = filePath;
        this.queue = queue;
    }

    public void run() {
        BufferedReader fileReader = null;
        try {
            fileReader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = fileReader.readLine()) != null) {
                String[] words = line.split("\\s+");

                synchronized (queue) {
                    for (String word : words) {
                        word = word.replaceAll("[^a-zA-Z]", "");
                        if (!word.isEmpty()) {
                            queue.add(word);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } finally {
            try {
                if (fileReader != null) fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}