package mainapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyApp {

    // Method to check if the current branch is 'main' and there are other branches
    public static String isCurrentBranchMain() {
        try {
            System.out.println("Checking if the current branch is 'main' and there are other branches...");

            // Get the list of branches using 'git branch --list'
            String branches = executeCommand("git branch --list").trim();

            // Pattern to match the current branch (marked with a *) and check if 'main' is the current branch
            Pattern currentBranchPattern = Pattern.compile("\\*\\s*(main)");
            Matcher currentBranchMatcher = currentBranchPattern.matcher(branches);

            // Pattern to check if there are other branches besides 'main'
            Pattern otherBranchesPattern = Pattern.compile("\\s*(?!main).*"); // Matches any branch other than 'main'
            Matcher otherBranchesMatcher = otherBranchesPattern.matcher(branches);

            // Check if the current branch is 'main' and there are other branches
            if (currentBranchMatcher.find() && otherBranchesMatcher.find()) {
                return "true"; // Current branch is 'main' and there are other branches
            } else {
                return "false"; // Either the current branch is not 'main' or no other branches exist
            }

        } catch (Exception e) {
            System.out.println("Error in isCurrentBranchMain method: " + e.getMessage());
            return "";
        }
    }

    // Method to check if 'feature-branch' exists using 'git branch --list'
    public static String isFeatureBranchCreated() {
        try {
            System.out.println("Checking if 'feature-branch' exists...");

            // Get the list of branches using 'git branch --list'
            String branches = executeCommand("git branch --list").trim();

            // Pattern to match feature-branch
            Pattern pattern = Pattern.compile("\\s*(feature-branch)\\s*");
            Matcher matcher = pattern.matcher(branches);

            // Check if feature-branch exists in the branch list
            if (matcher.find()) {
                return "true";
            } else {
                return "false";
            }

        } catch (Exception e) {
            System.out.println("Error in isFeatureBranchCreated method: " + e.getMessage());
            return "";
        }
    }

    // Method to check if initial content of file.txt exists in 'feature-branch' using 'git show feature-branch:file.txt'
    public static String isInitialCommitContentInFeatureBranch() {
        try {
            System.out.println("Checking if asked commit exists in 'feature-branch'...");

            // Show the contents of file.txt from 'feature-branch'
            String content = executeCommand("git show feature-branch:file.txt").trim();

            // Pattern to check if 'file.txt' contains the expected content
            Pattern pattern = Pattern.compile(".*Initial content.*");
            Matcher matcher = pattern.matcher(content);

            // Check if 'file.txt' contains the expected content from the 'feature-branch'
            if (matcher.find()) {
                return "true";
            } else {
                return "false";
            }

        } catch (Exception e) {
            System.out.println("Error in isFileInFeatureBranch method: " + e.getMessage());
            return "";
        }
    }

    // Method to check if 'file.txt' is present in 'feature-branch' using 'git ls-tree -r feature-branch --name-only'
    public static String isFileInFeatureBranchListed() {
        try {
            System.out.println("Checking if 'file.txt' is listed in 'feature-branch'...");

            // List the files in 'feature-branch'
            String files = executeCommand("git ls-tree -r feature-branch --name-only").trim();

            // Pattern to check if 'file.txt' is listed in the files
            Pattern pattern = Pattern.compile("\\s*file\\.txt\\s*");
            Matcher matcher = pattern.matcher(files);

            // Check if 'file.txt' is present in the list of files
            if (matcher.find()) {
                return "true";
            } else {
                return "false";
            }

        } catch (Exception e) {
            System.out.println("Error in isFileInFeatureBranchListed method: " + e.getMessage());
            return "";
        }
    }

    // Helper method to execute git commands
    private static String executeCommand(String command) throws Exception {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.directory(new File(".")); // Ensure this is the correct directory where Git repo is located
        processBuilder.command("bash", "-c", command);
        Process process = processBuilder.start();

        StringBuilder output = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }

        int exitVal = process.waitFor();
        if (exitVal == 0) {
            return output.toString();
        } else {
            System.out.println("Command failed with exit code: " + exitVal);
            throw new RuntimeException("Failed to execute command: " + command);
        }
    }

    public static void main(String[] args) {
        try {
            // Checking if the current branch is main
            String currentBranch = isCurrentBranchMain();
            System.out.println("Is the current branch 'main'?: " + currentBranch);

            // Checking if feature-branch exists
            String featureBranchExists = isFeatureBranchCreated();
            System.out.println("Is 'feature-branch' created?: " + featureBranchExists);

            // Checking if initial content in file.txt exists in 'feature-branch'
            String initialContentInFeatureBranch = isInitialCommitContentInFeatureBranch();
            System.out.println("Is initial content in file.txt exists 'feature-branch'?: " + initialContentInFeatureBranch);

            // Checking if 'file.txt' is listed in 'feature-branch'
            String fileInFeatureBranchListed = isFileInFeatureBranchListed();
            System.out.println("Is 'file.txt' listed in 'feature-branch'?: " + fileInFeatureBranchListed);

        } catch (Exception e) {
            System.out.println("Error in main method: " + e.getMessage());
        }
    }
}
