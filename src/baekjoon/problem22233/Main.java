package baekjoon.problem22233;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Set<String> keywords = new HashSet<>();
        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            keywords.add(input);
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), ",");
            while (st.hasMoreTokens()) {
                String s = st.nextToken();

                keywords.remove(s);
            }
            bw.write(keywords.size() + "\n");
        }

        br.close();
        bw.flush();
        bw.close();
    }
}
