package baekjoon.problem5397;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        for (int w = 0; w < t; w++) {
            List<Character> L = new LinkedList<>();
            String s = br.readLine();

            int it = 0;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '<') {
                    if (it == 0) continue;
                    it--;
                }
                else if (c == '>') {
                    if (it == L.size()) continue;
                    it++;
                }
                else if (c == '-') {
                    if (it == 0) continue;

                    L.remove(--it);
                }
                else {
                    L.add(it, c);
                }
            }
            for (it = 0; it <L.size(); it++) {
                bw.write(L.get(it));
            }
            bw.write("\n");
        }


        br.close();
        bw.flush();
        bw.close();
    }
}