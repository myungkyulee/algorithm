package baekjoon.problem5972;

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<List<Pair>> graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            // 양방향이기 때문에 둘다 넣어줌
            graph.get(a).add(new Pair(b, dist));
            graph.get(b).add(new Pair(a, dist));
        }

        int[] d = new int[n + 1];
        Arrays.fill(d, Integer.MAX_VALUE);

        dijkstra(1, d, graph);

        bw.write(d[n] + "");

        br.close();
        bw.flush();
        bw.close();
    }

    public static void dijkstra(int start, int[] d, List<List<Pair>> graph) {
        Queue<Pair> pq = new PriorityQueue<>();
        d[start] = 0;

        pq.offer(new Pair(start, 0));

        while (!pq.isEmpty()) {
            int node = pq.peek().index;
            int dist = pq.poll().dist;

            if (dist > d[node]) continue;

            for (Pair pair : graph.get(node)) {
                int nextNode = pair.index;
                int cost = pair.dist + dist;

                if (d[nextNode] > cost) {
                    d[nextNode] = cost;
                    pq.offer(new Pair(nextNode, cost));
                }
            }
        }
    }

    static class Pair implements Comparable<Pair> {
        int index;
        int dist;

        public Pair(int index, int dist) {
            this.index = index;
            this.dist = dist;
        }

        @Override
        public int compareTo(Pair pair) {
            return dist - pair.dist;
        }
    }
}