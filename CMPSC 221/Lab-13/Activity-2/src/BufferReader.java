import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

class BufferReader extends Thread {
    private LinkedList<String> queue;
    private boolean running = true;
    // HashMap used for tally [cite: 208]
    private HashMap<String, Integer> frequencyMap = new HashMap<>();

    public BufferReader(LinkedList<String> queue) {
        this.queue = queue;
    }

    public void stopReading() {
        this.running = false;
    }

    public void run() {
        while (running || !queue.isEmpty()) {
            String word = null;
            synchronized (queue) {
                if (!queue.isEmpty()) {
                    word = queue.removeFirst();
                }
            }

            if (word != null) {
                frequencyMap.put(word.toLowerCase(), frequencyMap.getOrDefault(word.toLowerCase(), 0) + 1);
            } else {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        printMostFrequent();
    }

    private void printMostFrequent() {
        String mostFrequentWord = "";
        int maxCount = 0;

        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                mostFrequentWord = entry.getKey();
                maxCount = entry.getValue();
            }
        }

        System.out.println("\nMost Frequent Word: \"" + mostFrequentWord + "\"");
        System.out.println("Frequency: " + maxCount);
    }
}