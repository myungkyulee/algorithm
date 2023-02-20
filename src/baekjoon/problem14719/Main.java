package baekjoon.problem14719;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        List<Integer> arr = new ArrayList<>();

        while (st.hasMoreTokens()) {
            int num = Integer.parseInt(st.nextToken());
            arr.add(num);
        }

        int result = 0;
        for (int i = 0; i < arr.size(); i++) {
            int leftTop = findLeftTop(arr, i);
            int rightTop = findRightTop(arr, i);
            int minTop = Math.min(leftTop, rightTop);

            result += minTop - arr.get(i);
        }

        bw.write(result + "");

        br.close();
        bw.flush();
        bw.close();
    }

    private static int findRightTop(List<Integer> arr, int pos) {
        int top = arr.get(pos);
        for (int i = pos + 1; i < arr.size(); i++) {
            if (top < arr.get(i)) {
                top = arr.get(i);
            }
        }
        return top;
    }

    private static int findLeftTop(List<Integer> arr, int pos) {
        int top = arr.get(pos);
        for (int i = pos - 1; i >= 0; i--) {
            if (top < arr.get(i)) {
                top = arr.get(i);
            }
        }
        return top;
    }
}
