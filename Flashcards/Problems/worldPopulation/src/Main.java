import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.LinkedHashMap;

public class Main {
    public static void main(String[] args) {
        String path = "../dataset_91069.txt";
        File file = new File(path);
        int maxDif = 0;

        try(Scanner sc = new Scanner(file)) {
            String dummy = sc.nextLine();
            Map<Integer, Long> yearAndPopulation = new LinkedHashMap<>();
            Map<Integer, Long> diff = new LinkedHashMap<>();
            int year = sc.nextInt();
            //String temp = sc.next();
            //System.out.println(temp);
            //long population = Long.parseLong(sc.next().replaceAll(",", ""));
            long population = sc.nextLong();
            yearAndPopulation.put(year, population);
            int maxYear = 0;
            long maxDiff = 0;
            while(sc.hasNext()) {
                long prevPopulation = population;
                year = sc.nextInt();
                population = sc.nextLong();
                //population = Long.parseLong(sc.next().replaceAll(",", ""));
                diff.put(year, population - prevPopulation);
                yearAndPopulation.put(year, population);
                if(maxDiff < population - prevPopulation) {
                    maxYear = year;
                    maxDiff = population - prevPopulation;
                }
            }
            System.out.println(maxYear);

        } catch(FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

}
