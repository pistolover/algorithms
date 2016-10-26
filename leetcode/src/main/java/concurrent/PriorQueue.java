package concurrent;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class PriorQueue {

    public static void main(String[] args) {
        Queue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.add(new Random().nextInt(100));
        }

        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            System.err.println(poll);
        }
    }

}
