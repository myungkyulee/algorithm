package baekjoon.problem2467;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Integer> arr = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            arr.add(Integer.parseInt(st.nextToken()));
        }

        int left = 0;
        int right = arr.size() - 1;
        int result = Integer.MAX_VALUE;
        int resultLeft = 0;
        int resultRight = right;

        while (left < right) {
            int leftValue = arr.get(left);
            int rightValue = arr.get(right);

            if (result > Math.abs(leftValue + rightValue)) {
                result = Math.abs(leftValue + rightValue);
                resultLeft = left;
                resultRight = right;
            }

            if (Math.abs(leftValue) > Math.abs(rightValue)) {
                left++;
            } else {
                right--;
            }
        }

        bw.write(arr.get(resultLeft) + " " + arr.get(resultRight));

        br.close();
        bw.flush();
        bw.close();
    }
}
