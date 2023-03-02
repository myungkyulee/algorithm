package baekjoon.problem2304;

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        List<Pair> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            list.add(new Pair(l, h));
        }

        Collections.sort(list);


        Stack<Integer> stack = new Stack<>();
        int sum = 0;
        int preIndex = 0;
        for (int i = 0; i < list.size(); i++) {
            int index = list.get(i).index;
            int height = list.get(i).height;

            if (stack.isEmpty()) {
                stack.push(height);
                sum += height;
                preIndex = index;
            } else {
                if (stack.peek() <= height) {
                    sum += stack.peek() * (index - (preIndex + 1));
                    sum += height;
                    stack.pop();
                    stack.push(height);
                    preIndex = index;
                }
            }
        }

        int max = stack.pop();
        stack = new Stack<>();
        for (int i = list.size() - 1; i >= 0; i--) {
            int index = list.get(i).index;
            int height = list.get(i).height;

            if (stack.isEmpty()) {
                stack.push(height);
                sum += height;
                preIndex = index;
            } else {
                if (stack.peek() < height) {
                    sum += stack.peek() * (preIndex - 1 - index);
                    sum += height;
                    stack.pop();
                    stack.push(height);
                    preIndex = index;
                }
            }
        }

        bw.write((sum - max) + "");

        br.close();
        bw.flush();
        bw.close();
    }

    static class Pair implements Comparable<Pair> {
        int index;
        int height;

        Pair(int index, int height) {
            this.index = index;
            this.height = height;
        }

        @Override
        public int compareTo(Pair p) {
            return this.index - p.index;
        }
    }
}
