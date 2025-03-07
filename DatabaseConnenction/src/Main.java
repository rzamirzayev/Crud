import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        {
            Scanner scanner = new Scanner(System.in);
            String input;
            Connenction connection=new Connenction();

            String process = "1. People table show \n" +
                    "2. Add People \n" +
                    "3. Edit People \n" +
                    "4. Remove People \n" +
                    "Q. Quit"
                    ;

            while (true) {
                System.out.println(process);
                System.out.print("Select an option: ");
                input = scanner.nextLine().trim();

                if (input.equalsIgnoreCase("Q")) {
                    System.out.println("Exiting...");
                    break;
                }
                if (input.equalsIgnoreCase("1")) {
                    connection.showPeople();
                }
                if(input.equalsIgnoreCase("2")) {
                    System.out.println("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.println("Enter Surname: ");
                    String surname = scanner.nextLine();
                    connection.AddPeople(name,surname);
                    System.out.println("****************");
                    connection.showPeople();

                }
                if(input.equalsIgnoreCase("3")) {
                    System.out.println("Enter Id: ");
                    int id = scanner.nextInt();
                    System.out.println("Enter Name: ");
                    String name = scanner.nextLine();

                    connection.EditPeople(id,name);
                    System.out.println("****************");
                    connection.showPeople();
                }
                if(input.equalsIgnoreCase("4")) {
                    System.out.println("Enter Id: ");
                    int id = scanner.nextInt();

                    connection.removePeople(id);

                    System.out.println("****************");
                    connection.showPeople();
                }
            }

            scanner.close();

        }
    }
}