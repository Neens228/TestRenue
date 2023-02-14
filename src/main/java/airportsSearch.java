
import java.io.*;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class airportsSearch {
    public static void main(String[] args) {

        while (true) {

            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите строку: ");
            String searchingString = scanner.nextLine();

            if(searchingString.equals("!quit")) break;
            long timeStart = System.currentTimeMillis();

            try(FileReader fileReader = new FileReader(String.valueOf(Paths.get("src/main/resources/airports.csv")));
                BufferedReader bufferedReader = new BufferedReader(fileReader)) {

                String fullString;
                short index = (short) (Short.parseShort(args[0])-1); //reading an argument
                AtomicInteger countOfStrings = new AtomicInteger(0);

                while ((fullString = bufferedReader.readLine()) != null) {
                    String[] splitArray = fullString.split(","); //splitting values into an array
                    if (splitArray[index].toLowerCase().startsWith(searchingString.toLowerCase(),1)) { //checking of our string
                        System.out.println(splitArray[index] + "[" + fullString + "]"); //output a string that satisfies the condition
                        countOfStrings.getAndIncrement();
                    }
                }
                bufferedReader.close();
                long finalTime = System.currentTimeMillis() - timeStart;
                System.out.println("Количество найденных строк: " + countOfStrings.get() + ", время затраченное на поиск: " + finalTime + " мс");
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}



