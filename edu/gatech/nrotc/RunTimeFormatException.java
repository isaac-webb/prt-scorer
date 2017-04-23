package edu.gatech.nrotc;

/**
  * This Exception is thrown when a time is in an invalid format for run time
  * calculations.
  *
  * @author Isaac Webb
  * @version 1.0
  */
public class RunTimeFormatException extends Exception {
    /**
      * Creates a RunTimeFormatException describing what the invalid time was.
      *
      * @param  String time          The invalid run time String
      */
    public RunTimeFormatException(String time) {
        super(time + " is an invalid PRT run time.");
    }
}
