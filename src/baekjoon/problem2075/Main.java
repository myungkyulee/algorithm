package baekjoon.problem2075;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        mySolution();
    }

    private static void pqSolution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());


        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                pq.offer(Integer.parseInt(st.nextToken()));
            }
        }
        int result = 0;
        for(int i=0;i<n;i++){
            result = pq.poll();
        }


        bw.write(result + "");

        br.close();
        bw.flush();
        bw.close();
    }

    private static void mySolution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        System.out.println(1500*1500);
        int n = Integer.parseInt(br.readLine());

        int[][] arr = new int[n][n];


        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int num = Integer.parseInt(st.nextToken());
                arr[j][i] = num;
            }
        }

        int[] index = new int[n];
        Arrays.fill(index, n - 1);
        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Integer.MIN_VALUE;
            int maxIndex = -1;
            for (int j = 0; j < index.length; j++) {
                if (index[j] < 0) continue;
                if (arr[j][index[j]] > max) {
                    max = arr[j][index[j]];
                    maxIndex = j;
                }
            }
            index[maxIndex]--;
        }
        bw.write(max + "");

        br.close();
        bw.flush();
        bw.close();
    }
}
