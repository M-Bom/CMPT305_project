import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Lab3Main {
    /**
     * Main method that handles user interaction, file reading, and data filtering.
     *
     * @param args Command-line arguments (not used in this program).
     * @throws IOException If an error occurs while reading the CSV file.
     */
    public static void main(String[] args) throws IOException {
        // File name of the CSV file
        // "src/main/resources/Property_Assessment_Data_2024.csv"
        Scanner scanner = new Scanner(System.in);
        System.out.print("CSV filename: ");
        String filename = scanner.next();
        String csvFileName = "src/main/resources/" + filename;

        // Checks if the file name exists
        if (!Files.exists(Paths.get(csvFileName))) {
            System.out.println("Error in opening " + filename);
            return;
        }

        // Creates propertyAssessments value using PropertyAssessments class
        PropertyAssessments propertyAssessments = new PropertyAssessments(csvFileName);

        // Asks user to input a neighbourhood
        System.out.print("Please enter a neighbourhood name: ");
        String neighbourhoodName = scanner.next();

        // Creates a new PropertyAssessment value using the filter
        PropertyAssessments neighbourhoodPropertyAssessments = propertyAssessments.getFilteredData(neighbourhoodName);
        if (neighbourhoodPropertyAssessments != null) {
            System.out.printf("There are %,d properties in %s\n", neighbourhoodPropertyAssessments.getSize(), neighbourhoodName);
            System.out.printf("The mean value is CAD %,.0f\n", neighbourhoodPropertyAssessments.getMean());
            System.out.printf("The median value is CAD %,d\n\n", neighbourhoodPropertyAssessments.getMedian());
        } else {
            System.out.println("Neighbourhood is not found\n");
        }

        // Asks user to input an assessment class (residential, commercial, etc.)
        System.out.print("Please enter an assessment class: ");
        String assessmentClass = scanner.next();

        // Creates a new PropertyAssessment value using the filter
        PropertyAssessments assementClassPropertyAssessments = propertyAssessments.getFilteredData(assessmentClass);
        if (assementClassPropertyAssessments != null) {
            System.out.printf("There are %,d %s properties in Edmonton\n", assementClassPropertyAssessments.getSize(), assessmentClass);
            System.out.printf("The min value is CAD %,d\n", assementClassPropertyAssessments.minAssessedValue());
            System.out.printf("The max value is CAD %,d\n\n", assementClassPropertyAssessments.maxAssessedValue());
        } else {
            System.out.println("Sorry, can't find " + assessmentClass + " properties\n");
        }

        // Used to obtain garage data for infographic
        /*System.out.print("Please state if the properties have a garage(Y/N)?: ");
        String garage = scanner.next();

        PropertyAssessments garagePropertyAssessments = propertyAssessments.getFilteredData(garage);
        if (garagePropertyAssessments != null) {
            if (garage.equalsIgnoreCase("Y")) {
                System.out.printf("There are %,d properties in Edmonton with a garage\n", garagePropertyAssessments.getSize());
                System.out.printf(garagePropertyAssessments.toString());
            } else if (garage.equalsIgnoreCase("N")) {
                System.out.printf("There are %,d properties in Edmonton with no garage\n", garagePropertyAssessments.getSize());
                System.out.printf(garagePropertyAssessments.toString());
            }
        } else {
            System.out.println("Sorry, can't find " + garage + " properties\n");
        }*/
    }
}
