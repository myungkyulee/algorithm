package baekjoon.problem1138;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;
    static int[] result;
    static boolean[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        arr = new int[n];
        result = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            arr[i] = num;
        }

        for (int i = 0; i < n; i++) {
            int num = arr[i];
            int count = 0;
            int index = 0;
            while (count != num) {
                if (result[index++] == 0) {
                    count++;
                }
            }
            while(result[index] != 0){
                index++;
            }
            result[index] = i + 1;
        }

        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }

        br.close();
        bw.flush();
        bw.close();
    }
}
