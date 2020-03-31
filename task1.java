import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numrow = 0;
        List<String> input = new LinkedList<>();
        while(sc.hasNextLine()) {
            input.add(sc.nextLine());
            numrow++;
        }

        int numcol = input.get(0).length();
        int[] ufds = new int[numcol * numrow];
        for (int i = 0; i < ufds.length; i++) {
            ufds[i] = i;
        }
        char[][] map = new char[numrow][numcol];
        
        for (int i = 0; i < numrow; i++) {
            String temp = input.get(i);
            for(int j = 0; j < numcol; j++) {
                map[i][j] = temp.charAt(j);
            }
        }

        for(int i = 0; i < numrow; i++) {
            for (int j = 0; j < numcol; j++) {
                if(map[i][j] == '0') {
                    ufds[i * numcol + j] = -1;
                }
                if(j != numcol - 1) {
                    if (map[i][j] == '+' && map[i][j+1] == '+') {
                        unionSet(i * numcol + j, i * numcol + j + 1, ufds); 
                    }
                } else if (i != numrow - 1) {
                    if (map[i][j] == '+' && map[i+1][j] == '+') {
                        unionSet(i * numcol + j, (i + 1)* numcol + j, ufds); 
                    }
                }
            }
        }

        int output = 0;
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int i = 0; i < ufds.length; i++) {
            if (ufds[i] != -1){ 
                if (hm.containsKey(ufds[i])) {
                    int b = hm.get(ufds[i]) + 1;
                    hm.put(ufds[i], b);
                } else {
                    hm.put(ufds[i], 1);
                }
            } 
        }

        
        Collection<Integer> list = hm.values();
        for (Integer a : list) {
            if (a >= 2) {
                output++;
            }
        }



        System.out.println(output);

    }

    private static int findSet(int i, int[] arr) {
        int root;
        if (arr[i] == i) {
            root = i;
        } else {
            root = findSet(arr[i], arr);
            arr[i] = root;
        }
        return root;
    }

    private static boolean checkSameSet(int i, int j, int[] arr) {
        int iRoot = findSet(i, arr);
        int jRoot = findSet(j, arr);
        return (iRoot == jRoot);
    }

    private static void unionSet(int i, int j, int[] arr) {
        if (!checkSameSet(i, j, arr)) {
            arr[findSet(Math.max(i, j), arr)] = findSet(Math.min(i, j), arr);
        }
    }

    public static int vertexIndex(int row, int col) {
        return (row * colSize) + col;
    }
}