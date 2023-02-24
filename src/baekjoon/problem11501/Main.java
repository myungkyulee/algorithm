package baekjoon.problem11501;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());


        for (int i = 0; i < t; i++) {
            int days = Integer.parseInt(br.readLine());
            ArrayList<Integer> arr = new ArrayList<>();

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < days; j++) {
                int price = Integer.parseInt(st.nextToken());
                arr.add(price);
            }
            int max = -1;
            long sum = 0;
            for (int j = arr.size() - 1; j >= 0; j--) {
                if (arr.get(j) > max) {
                    max = arr.get(j);
                } else {
                    sum += max - arr.get(j);
                }
            }

            bw.write(sum + "\n");
        }

        br.close();
        bw.flush();
        bw.close();
    }
}
