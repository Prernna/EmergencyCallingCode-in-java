import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class EmergencyCallingSystem {
    public static void createFile(String name, String address, String problem, String service) throws IOException {
        // Create a new file for the user
        String filename = name + ".txt";
        FileWriter file = new FileWriter(filename);
        file.write("Name: " + name + "\n");
        file.write("Address: " + address + "\n");
        file.write("Problem: " + problem + "\n");
        file.write("Service: " + service + "\n");
        file.close();
        System.out.println("File created successfully!");
    }

    public static String getService(String problem) {
        // Map the problem to the appropriate service
        if (problem.toLowerCase().contains("fire") || problem.toLowerCase().contains("short circuit")  || problem.toLowerCase().contains("burning")) {
            return "fire";
        } else if (problem.toLowerCase().contains("police") || problem.toLowerCase().contains("thief") ||  problem.toLowerCase().contains("stole") ||problem.toLowerCase().contains("riots") || problem.toLowerCase().contains("misbehave")) {
            return "police";
        } else if (problem.toLowerCase().contains("ambulance") || problem.toLowerCase().contains("accident") || problem.toLowerCase().contains("bleeding")) {
            return "ambulance";
        } else {
            return "unknown";
        }
    }

    public static void emergencyCall() {
        // Get user input
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name = "";
        String address = "";
        String problem = "";
        String service = "";

        try {
            System.out.println("Enter your name:");
            name = reader.readLine();

            System.out.println("Enter your address:");
            address = reader.readLine();

            while (true) {
                System.out.println("Enter the problem:");
                problem = reader.readLine();

                // Map the problem to the appropriate service
                service = getService(problem);

                // Check if a valid service has been found
                if (!service.equals("unknown")) {
                    break;
                }

                System.out.println("Sorry, we were unable to determine the appropriate service for your problem.");
                System.out.println("Please try again with a more detailed problem description.");
            }

            // Create a file for the user
            createFile(name, address, problem, service);

            // Provide some details about the dispatched service
            System.out.println("Your emergency call has been logged. Service dispatched: " + service);
            System.out.println("Please remain calm and follow any instructions given by the " + service.toUpperCase() + " personnel.");
            System.out.println("Help is on the way!");
        } catch (IOException e) {
            System.out.println("An error occurred while reading input: " + e.getMessage());
            throw new RuntimeException("Failed to read user input", e);
        }
    }

    public static void main(String[] args) {
        // Prompt the user to dial 100 to initiate the emergency call
        System.out.println("Please dial 100 to initiate the emergency call.");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = "";

        try {
            while (true) {
                input = reader.readLine();
                if (input.equals("100")) {
                    break;
                }
                System.out.println("Please dial 100 to initiate the emergency call.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading input: " + e.getMessage());
            throw new RuntimeException("Failed to read user input", e);
        }

        // Call the emergencyCall() method once the user has dial
	emergencyCall();
}
