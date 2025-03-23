package com.libraryapp.test.functional;

import static com.libraryapp.test.utils.TestUtils.businessTestFile;
import static com.libraryapp.test.utils.TestUtils.currentTest;
import static com.libraryapp.test.utils.TestUtils.testReport;
import static com.libraryapp.test.utils.TestUtils.yakshaAssert;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Order;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import mainapp.MyApp;

public class MainFunctionalTest {

    @AfterAll
    public static void afterAll() {
        testReport();
    }

    @Test
    @Order(1)
    public void testCurrentBranchIsMain() throws IOException {
        try {
            // Check if the current branch is 'main'
            String result = MyApp.isCurrentBranchMain();

            // Assert that the result is 'true' indicating we are on 'main'
            yakshaAssert(currentTest(), result.equals("true"), businessTestFile);
        } catch (Exception ex) {
            yakshaAssert(currentTest(), false, businessTestFile);
        }
    }

    @Test
    @Order(2)
    public void testFeatureBranchCreated() throws IOException {
        try {
            // Check if the 'feature-branch' is created
            String result = MyApp.isFeatureBranchCreated();

            // Assert that the result is 'true' indicating 'feature-branch' exists
            yakshaAssert(currentTest(), result.equals("true"), businessTestFile);
        } catch (Exception ex) {
            yakshaAssert(currentTest(), false, businessTestFile);
        }
    }

    @Test
    @Order(3)
    public void testInitialCommitContentInFeatureBranch() throws IOException {
        try {
            // Check if 'commit' exists in 'feature-branch'
            String result = MyApp.isInitialCommitContentInFeatureBranch();

            // Assert that 'file.txt' contains 'Initial commit' content from 'feature-branch'
            yakshaAssert(currentTest(), result.equals("true"), businessTestFile);
        } catch (Exception ex) {
            yakshaAssert(currentTest(), false, businessTestFile);
        }
    }

    @Test
    @Order(4)
    public void testFileInFeatureBranchListed() throws IOException {
        try {
            // Check if 'file.txt' is listed in 'feature-branch'
            String result = MyApp.isFileInFeatureBranchListed();

            // Assert that 'file.txt' is listed in 'feature-branch'
            yakshaAssert(currentTest(), result.equals("true"), businessTestFile);
        } catch (Exception ex) {
            yakshaAssert(currentTest(), false, businessTestFile);
        }
    }
}
