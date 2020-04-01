import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> input = new ArrayList<>();
        while(sc.hasNextLine()) {
            input.add(sc.nextLine());
        }
        System.out.println(input.size());
        for (String line: input) {
            System.out.println(line);
        }
        sc.close();
    }
}