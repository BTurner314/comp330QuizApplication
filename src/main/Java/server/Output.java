package server;

import java.io.PrintStream;
import java.util.*;

public class Output {

    PrintStream out;

    public Output(PrintStream out) {
        this.out = out;
    }

    public int askTrueFalse(Map<String, String> ques, Scanner in) {
        String answer;
        int score = 0;
        boolean valid;
        out.println("Enter True or False: ");
        Iterator<Map.Entry<String, String>> itr = ques.entrySet().iterator(); //Creating iterator to go through map. Might be able to do with for loop not sure which is better
        while (itr.hasNext()) {
            valid = false; //Boolean to only allow valid answers
            Map.Entry<String, String> pair = itr.next(); //assigning the next entry in map to variable
            out.print(pair.getKey() + ": ");
            while (!valid) {
                answer = in.nextLine(); //Get input
                if (answer.equalsIgnoreCase("true") || answer.equalsIgnoreCase("false")) { //If the response is True or False than its valid
                    if (answer.equalsIgnoreCase(pair.getValue())) { //Check if it matches the answer from json file
                        out.println("Correct!");
                        score++;
                    } else {
                        out.println("Incorrect");
                    }
                    valid = true;
                } else {
                    out.println("Please enter a valid response.");
                }
            }
        }
        return score;
    }

    public int askMatching(Map<String, String> ques, Scanner in) { //Currently asks to match one at a time, cant do process of elimination like on paper. Could change this to allow for it
        String answer;
        int score = 0;
        boolean valid;
        ArrayList<String> words = new ArrayList<>(ques.size());
        ArrayList<String> descriptions = new ArrayList<>(ques.size());
        for(Map.Entry<String, String> pair : ques.entrySet()){ //Assign words and descriptions to separate arraylists so descriptions can be randomized
            words.add(pair.getKey());
            descriptions.add(pair.getValue());
        }
        Collections.shuffle(descriptions); //Shuffles the list

        out.println("Your words are: " + words.toString());
        out.println("Match them to the following.");

        for (String description : descriptions) { //Iterates through descriptions and prints them
            out.print(description + " : ");
            valid = false;
            while(!valid) { //Similar process to true false to check valid responses
                answer = in.nextLine();
                if (!ques.containsKey(answer)) { // If the questions map doesnt contain the key
                    out.println("Please enter a valid response.");
                } else if (ques.get(answer).equalsIgnoreCase(description)) { //Checks the value associated to the key given against description (Verified its in the map from first if)
                    out.println("Correct!");
                    score++;
                    valid = true;
                } else {
                    out.println("Incorrect");
                    valid = true;
                }
            }
        }

        return score;
    }
}