package com.application;

import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * This class represents a Model Creator object that deserializes the CSV contents to Java objects.
 * It also offers methods to return duplicates and non duplicate data sets from the CSV content
 * using various parameters and Levenshtein distance.
 */
public class ModelCreator {

  private List<User> userList;
  private List<List<User>> duplicates;
  private List<User> nonDuplicates;

  public ModelCreator(String fileName) {
    this.userList = new LinkedList<>(); /*Using a linkedList as we continuously remove the first
                                          element, which takes O(1) times.*/
    this.duplicates = new ArrayList<>();
    this.nonDuplicates = new ArrayList<>();

    deserializeFromCSVFile(fileName);
  }

  /**
   * Private helper that uses velocity parser to deserialize CSV data into User objects
   *
   * @param csvFile CSV data set
   */
  private void deserializeFromCSVFile(String csvFile) {
    BeanListProcessor<User> rowProcessor = new BeanListProcessor<>(User.class);
    CsvParserSettings parserSettings = new CsvParserSettings();
    parserSettings.setRowProcessor(rowProcessor);
    parserSettings.setHeaderExtractionEnabled(true);

    CsvParser parser = new CsvParser(parserSettings);
    try {
      parser.parse(new FileReader(new File(csvFile)));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    this.userList = rowProcessor.getBeans();
  }

  /**
   * Returns duplicates from the CSV data set based on various parameters.
   *
   * @return List of List containing the duplicate values in the data set
   */
  public List<List<User>> duplicates() {
    List<User> initialList = new ArrayList<>();
    List<List<User>> duplicatesWithSingleSizeNonDuplicates = new ArrayList<>();
    initialList.add(this.userList.get(0));
    duplicatesWithSingleSizeNonDuplicates.add(initialList);
    this.userList.remove(0);

    while (this.userList.size() != 0) {
      boolean added = true;
      for (List<User> duplicate : duplicatesWithSingleSizeNonDuplicates) {
        if (checkIfDuplicate(this.userList.get(0), duplicate.get(0))) {
          duplicate.add(this.userList.get(0));
          added = false;
          break;
        }
      }
      if (added) {
        List<User> newList = new ArrayList<>();
        newList.add(this.userList.get(0));
        duplicatesWithSingleSizeNonDuplicates.add(newList);
      }
      this.userList.remove(0);
    }
    for (List<User> list : duplicatesWithSingleSizeNonDuplicates) {
      if (list.size() == 1) {
        this.nonDuplicates.add(list.get(0));
      } else {
        this.duplicates.add(list);
      }
    }
    return this.duplicates;
  }

  /**
   * Returns list of non duplicate data from the given data set.
   *
   * @return List of non duplicate Users
   */
  public List<User> nonDuplicates() {
    return this.nonDuplicates;
  }

  /**
   * Checks if the two user parameters are duplicates based on their attributes and Levenshtein
   * distance.
   *
   * @param user1 first user
   * @param user2 second user
   * @return true if the users are duplicate, false otherwise
   */
  private boolean checkIfDuplicate(User user1, User user2) {
    String emailOne = checkForNull(user1.getEmail());
    String emailTwo = checkForNull(user2.getEmail());
    String nameOne = checkForNull(user1.getFirst_name()) + checkForNull(user1.getLast_name());
    String nameTwo = checkForNull(user2.getFirst_name()) + checkForNull(user2.getLast_name());
    String phoneOne = checkForNull(user1.getPhone());
    String phoneTwo = checkForNull(user2.getPhone());

    if (emailOne.equals(emailTwo) && phoneOne.equals(phoneTwo)) {
      return true;
    } else if (nameOne.equals(nameTwo) && phoneOne.equals(phoneTwo)) {
      return true;
    } else if (nameOne.equals(nameTwo) && StringUtils.getLevenshteinDistance(emailOne, emailTwo) <= 2) {
      return true;
    } else
      return emailOne.equals(emailTwo) && StringUtils.getLevenshteinDistance(nameOne, nameTwo) <= 2;
  }

  /**
   * Helper method that replaces any null string with empty string.
   *
   * @param str String value
   * @return either lower case of the string parameter or empty String if its null
   */
  private String checkForNull(String str) {
    if (str != null) {
      return str.toLowerCase();
    } else {
      return " ";
    }
  }
}
