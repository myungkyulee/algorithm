package baekjoon.problem1431;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        List<SerialNumber> list = new ArrayList<>();
        for(int i=0;i<n;i++){
            SerialNumber sn = new SerialNumber(sc.next());
            list.add(sn);
        }

        Collections.sort(list);

        for(SerialNumber s : list){
            System.out.println(s);
        }
    }

    static class SerialNumber implements Comparable<SerialNumber> {
        String number;
        int sum = -1;

        SerialNumber(String s) {
            number = s;
        }

        int getSum() {
            if (sum == -1) {
                sum();
            }
            return sum;
        }

        void sum() {
            sum = 0;
            for (int i = 0; i < number.length(); i++) {
                char c = number.charAt(i);
                if (c >= '0' && c <= '9') {
                    sum += c - '0';
                }
            }
        }


        public int compareTo(SerialNumber n) {
            if(this.number.length() == n.number.length()){
                if(this.getSum() == n.getSum()) {
                    return this.number.compareTo(n.number);
                }
                return this.getSum() - n.getSum();
            }
            return this.number.length() - n.number.length();
        }

        public String toString(){
            return number;
        }
    }
}
