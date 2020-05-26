package Q1021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader getBufferedReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    static int getInt(String str) {
        return Integer.parseInt(str);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = getBufferedReader();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int N = getInt(stk.nextToken());
        int M = getInt(stk.nextToken());

        ArrayDeque<Integer> position = new ArrayDeque<>();
        for(int i = 0; i < N; ++i) {
            position.add(i + 1);
        }

        int result = 0;
        stk = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; ++i) {
            int wanted = getInt(stk.nextToken());

            if(wanted == position.peek()) {
                position.poll();
                continue;
            }

            int left = 0;
            ArrayDeque<Integer> leftShift = new ArrayDeque<>(position);
            for(int j = 0; j < leftShift.size(); ++j) {
                int head = leftShift.poll();
                leftShift.add(head);
                left++;

                head = leftShift.peek();
                if(wanted == head) {
                    leftShift.poll();
                    break;
                }
            }

            int right = 0;
            ArrayDeque<Integer> rightShift = new ArrayDeque<>(position);
            for(int j = 0; j < rightShift.size(); ++j) {
                int tail = rightShift.removeLast();
                rightShift.addFirst(tail);
                right++;

                int head = rightShift.peek();
                if(wanted == head) {
                    rightShift.poll();
                    break;
                }
            }

            if(left > right) {
                position = rightShift;
                result += right;
            } else {
                position = leftShift;
                result += left;
            }
        }

        System.out.println(result);
        br.close();
    }
}

