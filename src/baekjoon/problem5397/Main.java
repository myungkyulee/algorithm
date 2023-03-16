package baekjoon.problem5397;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        for (int w = 0; w < t; w++) {
            List<Character> L = new LinkedList<>();
            String s = br.readLine();

            ListIterator<Character> it = L.listIterator();

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '<') {
                    if (it.hasPrevious()) {
                        it.previous();
                    }
                } else if (c == '>') {
                    if (it.hasNext()) {
                        it.next();
                    }
                } else if (c == '-') {
                    if(it.hasPrevious()) {
                        it.previous();
                        it.remove();
                    }
                } else {
                    it.add(c);
                }
            }
            for (Character c : L) {
                bw.write(c + "");
            }
            bw.write("\n");
        }


        br.close();
        bw.flush();
        bw.close();
    }
}