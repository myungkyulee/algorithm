package baekjoon.problem20006;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int p = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Room> rooms = new ArrayList<>();

        for (int i = 0; i < p; i++) {
            st = new StringTokenizer(br.readLine());
            int level = Integer.parseInt(st.nextToken());
            String id = st.nextToken();
            boolean flag = false;
            for (Room room : rooms) {
                if (room.getSize() != m) {
                    if (room.check(level)) {
                        room.enter(id, level);
                        flag = true;
                        break;
                    }
                }
            }
            if (!flag) {
                Room room = new Room(id, level);
                rooms.add(room);
            }

        }

        for (Room room : rooms) {
            if (room.getSize() == m) {
                bw.write("Started!\n");
            } else bw.write("Waiting!\n");
            bw.write(room + "");
        }


        br.close();
        bw.flush();
        bw.close();
    }

    static class Room {
        static int sequence = 0;
        int roomId;
        int high;
        int low;
        List<Member> members = new ArrayList<>();

        public Room(String id, int level) {
            roomId = ++sequence;
            high = level + 10;
            low = level - 10;
            members.add(new Member(id, level));
        }

        public int getSize() {
            return members.size();
        }

        public boolean check(int level) {
            if (level >= low && level <= high) {
                return true;
            }
            return false;
        }

        public void enter(String id, int level) {
            members.add(new Member(id, level));
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            Collections.sort(members);

            for (Member member : members) {
                sb.append(member).append('\n');
            }

            return sb.toString();
        }
    }

    static class Member implements Comparable<Member> {
        String id;
        int level;

        Member(String id, int level) {
            this.id = id;
            this.level = level;
        }

        @Override
        public String toString() {
            return String.valueOf(level) + " " + id;
        }

        @Override
        public int compareTo(Member m){
            return id.compareTo(m.id);
        }
    }
}
