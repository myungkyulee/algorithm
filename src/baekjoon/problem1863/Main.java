package baekjoon.problem1863;

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<>();
        int count = 0;
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if (stack.isEmpty()) {
                if (y != 0) {
                    stack.push(y);
                    count++;
                }
            } else {
                if (stack.peek() < y) {
                    stack.push(y);
                    count++;
                } else if (stack.peek() > y) {
                    while (!stack.isEmpty() && stack.peek() > y) {
                        stack.pop();
                    }
                    if (stack.isEmpty()) {
                        if (y != 0) {
                            stack.push(y);
                            count++;
                        }
                    } else if (stack.peek() != y) {
                        stack.push(y);
                        count++;
                    }
                }
            }
        }

        bw.write(count + "");

        br.close();
        bw.flush();
        bw.close();
    }
}
