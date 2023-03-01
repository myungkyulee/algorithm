package baekjoon.problem1406;

import java.io.*;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        listSolution();
    }

    public static void stackSolution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String text = br.readLine();
        int m = Integer.parseInt(br.readLine());
        Stack<Character> leftStack = new Stack<>();
        Stack<Character> rightStack = new Stack<>();

        for (int i = 0; i < text.length(); i++) {
            leftStack.add(text.charAt(i));
        }

        for (int i = 0; i < m; i++) {
            String command = br.readLine();
            char commandChar = command.charAt(0);
            if (commandChar == 'L') {
                if(!leftStack.isEmpty()) rightStack.add(leftStack.pop());
            } else if (commandChar == 'D') {
                if(!rightStack.isEmpty()) leftStack.add(rightStack.pop());
            } else if (commandChar == 'B'){
                if(!leftStack.isEmpty()) leftStack.pop();
            } else if (commandChar == 'P') {
                leftStack.add(command.charAt(2));
            }
        }
        StringBuilder sb = new StringBuilder();
        while(!leftStack.isEmpty()){
            sb.append(leftStack.pop());
        }
        sb.reverse();
        while(!rightStack.isEmpty()){
            sb.append(rightStack.pop());
        }

        bw.write(sb.toString());

        br.close();
        bw.flush();
        bw.close();
    }

    public static void listSolution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String text = br.readLine();
        int m = Integer.parseInt(br.readLine());
        Editor editor = new Editor();

        for (int i = 0; i < text.length(); i++) {
            editor.add(text.charAt(i));
        }

        for (int i = 0; i < m; i++) {
            String command = br.readLine();
            if (command.charAt(0) == 'B') {
                editor.remove();
            } else if (command.charAt(0) == 'P') {
                editor.add(command.charAt(2));
            } else {
                editor.moveCursor(command.charAt(0));
            }
        }

        bw.write(editor + "\n");

        br.close();
        bw.flush();
        bw.close();
    }

    static class Editor {
        Node cursor;
        Node start;
        Node end;

        public Editor() {
            start = end = new Node('0');
            cursor = end;
        }

        public void moveCursor(char c) {
            if (c == 'D') {
                if (cursor != end) cursor = cursor.getRear();
            } else if (c == 'L') {
                if (cursor != start) cursor = cursor.getFront();
            }
        }

        public void add(char value) {
            Node newNode = new Node(value);

            if (cursor == end) {
                cursor.setRear(newNode);
                newNode.setFront(cursor);
                cursor = end = newNode;
            } else {
                Node rearCursor = cursor.getRear();
                cursor.setRear(newNode);
                rearCursor.setFront(newNode);
                newNode.setFront(cursor);
                newNode.setRear(rearCursor);
                cursor = newNode;
            }
        }

        public void remove() {
            if (cursor == end) {
                end = cursor.getFront();
                end.setRear(null);
                cursor.setFront(null);
                cursor = end;
            } else if (cursor != start) {
                Node frontCursor = cursor.getFront();
                Node rearCursor = cursor.getRear();
                frontCursor.setRear(rearCursor);
                rearCursor.setFront(frontCursor);
                cursor.setFront(null);
                cursor.setRear(null);
                cursor = frontCursor;
            }
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (Node current = start.getRear(); current != null; current = current.getRear()) {
                sb.append(current.getValue());
            }
            return sb.toString();
        }
    }

    static class Node {
        private char value;
        private Node front;
        private Node rear;

        public Node(char value) {
            this.value = value;
        }

        public char getValue() {
            return value;
        }

        public Node getFront() {
            return front;
        }

        public Node getRear() {
            return rear;
        }

        public void setFront(Node node) {
            front = node;
        }

        public void setRear(Node node) {
            rear = node;
        }
    }
}
