package com.application;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * This is the Controller of the Web Application. It delegates the view and model.
 */
@Controller
public class ApplicationController {

  private ModelCreator modelCreator;

  /**
   * Method for handling the HTTP GET request for normal data set. Takes data from the model and
   * sends it to the view.
   *
   * @param model User Interface model map
   * @return String to redirect the GET requests
   */
  @GetMapping("/normal")
  public String root(ModelMap model) {
    modelCreator = new ModelCreator("normal.csv");
    model.addAttribute("AllDuplicates", modelCreator.duplicates());
    model.addAttribute("AllNonDuplicates", modelCreator.nonDuplicates());
    return "normal";
  }

  /**
   * Method for handling the HTTP GET request for advanced data set. Takes data from the model and
   * sends it to the view.
   *
   * @param model User Interface model map
   * @return String to redirect the GET request
   */
  @GetMapping("/advanced")
  public String getAdvancedRequest(ModelMap model) {
    modelCreator = new ModelCreator("advanced.csv");
    model.addAttribute("AllDuplicates", modelCreator.duplicates());
    model.addAttribute("AllNonDuplicates", modelCreator.nonDuplicates());
    return "advanced";
  }
}
