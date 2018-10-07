import java.net.*;
import java.io.*;

public class SCEmailChallange {
    public static void main(String[] args) throws Exception {
        URL url;
        String emailID;
        String inputLine;

        // Reads the users input in the console
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the email ID of the person you want to find: ");
        // Gets the id of the person input by the user
        emailID = reader.readLine();
        // Constructs the URL object with the URL of the person to be found
        url = new URL("https://www.ecs.soton.ac.uk/people/" + emailID);
        // Creates a bufferedReader on the input stream and reads from the
        // BufferedReader thereby reading from the URL
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        // Loops throught the web page line by line
        while ((inputLine = in.readLine()) != null) {
            // Checks each line of the webpage to find the <title> tag
            if (inputLine.contains("<title>")) {
                // Finds the index of the starting character of the name
                int startPos = inputLine.indexOf(">") + 1;
                // Finds the index of the final character of the name
                int endPos = inputLine.indexOf("|") - 1;

                // Checks to see if the found name is a actual person or if it returns a empty
                // page
                if ((inputLine.substring(startPos, endPos).contains("People"))) {
                    // Prints out an error telling the user that the id of the searched person
                    // doesn't exist
                    System.out.println("This person doesn't exist or hasn't made themselves publicly searchable");
                } else {
                    // Prints out the persons name
                    System.out.println(inputLine.substring(startPos, endPos));
                }

            }
        }
    }
}