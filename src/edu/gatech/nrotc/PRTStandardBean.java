package edu.gatech.nrotc;

/**
  * This class defines the PRT score bracket bean for a single row in a PRT
  * standard. All properties are stored as integers.
  *
  * @author Isaac Webb
  * @version 1.0
  */
public class PRTStandardBean {
    private int score = 0;
    private int curlUps = 0;
    private int pushUps = 0;
    private int runTime = 0;
    private String bracket = "";

    /**
      * Returns the number of seconds for a given mm:ss time String.
      *
      * @param  String                 time          The time in mm:ss format
      * @return                        The integer number of seconds for the
      * time
      * @throws RunTimeFormatException Thrown if the time is not formatted
      *                                correctly
      */
    private static int convertTimeToSeconds(String time) throws
    RunTimeFormatException {
        String[] parts = time.split(":");

        if (parts.length != 2) {
            throw new RunTimeFormatException(time);
        }

        try {
            int mins = Integer.parseInt(parts[0]);
            int secs = Integer.parseInt(parts[1]);
            return mins * 60 + secs;
        } catch (NumberFormatException e) {
            throw new RunTimeFormatException(time);
        }
    }

    /**
      * A no-args constructor for compatibility that sets all values to 0.
      */
    public PRTStandardBean() {
    }

    /**
      * Creates a PRTStandardBean with the given properties.
      *
      * @param  int                    score         The score for the standard
      * @param  int                    curlUps       The curl ups for the score
      * @param  int                    pushUps       The push ups for the score
      * @param  String                 runTime       The run time for the score
      *                                              in mm:ss format
      * @param  String                 bracket       The bracket for the score
      * @throws RunTimeFormatException Thrown if the run is not formatted
      *                                correctly
      */
    public PRTStandardBean(int score, int curlUps, int pushUps, String runTime, String bracket)
    throws RunTimeFormatException {
        this.score = (score > 0) ? score : 0;
        this.curlUps = (curlUps > 0) ? curlUps : 0;
        this.pushUps = (pushUps > 0) ? pushUps : 0;
        this.bracket = bracket;
        int time = convertTimeToSeconds(runTime);
        this.runTime = (time > 0) ? time : 0;
    }

    // Getters and setters

    /**
      * Returns the PRTStandardBean's score.
      *
      * @return The PRTStandardBean's score
      */
    public Integer getScore() {
        return score;
    }

    /**
      * Sets the PRTStandardBean's score. Will put a floor on the value at 0.
      *
      * @param int score The new score
      */
    public void setScore(int score) {
        this.score = (score > 0) ? score : 0;
    }

    /**
      * Returns the PRTStandardBean's curl ups.
      *
      * @return The PRTStandardBean's curl ups
      */
    public int getCurlUps() {
        return curlUps;
    }

    /**
      * Sets the PRTStandardBean's number of curl ups. Will put a floor on the
      * value at 0.
      *
      * @param int curlUps The new number of curl ups
      */
    public void setCurlUps(int curlUps) {
        this.curlUps = (curlUps > 0) ? curlUps : 0;
    }

    /**
      * Returns the PRTStandardBean's push ups.
      *
      * @return The PRTStandardBean's push ups
      */
    public int getPushUps() {
        return pushUps;
    }

    /**
      * Sets the PRTStandardBean's number of push ups. Will put a floor on the
      * value at 0.
      *
      * @param int pushUps The new number of push ups
      */
    public void setPushUps(int pushUps) {
        this.pushUps = (pushUps > 0) ? pushUps : 0;
    }

    /**
      * Returns the PRTStandardBean's run time.
      *
      * @return The PRTStandardBean's run time
      */
    public int getRunTime() {
        return runTime;
    }

    /**
      * Returns the PRTStandardBean's run time in mm:ss format.
      *
      * @return The mm:ss representation of the PRTStandardBean's run time
      */
    public String getRunTimeString() {
        return String.format("%d:%d", runTime / 60, runTime % 60);
    }

    /**
      * Sets the PRTStandardBean's run time. Will put a floor on the value at 0.
      *
      * @param int runTime The new run time
      */
    public void setRunTime(int runTime) {
        this.runTime = (runTime > 0) ? runTime : 0;
    }

    /**
      * Sets the PRTStandardBean's run time.
      *
      * @param  String runTime       The run time in mm:ss format
      * @return        false if the run time was formatted incorrectly, true
      *                otherwise
      */
    public boolean setRunTime(String runTime) {
        int time = 0;
        try {
            time = convertTimeToSeconds(runTime);
        } catch (RunTimeFormatException e) {
            return false;
        }
        setRunTime(time);
        return true;
    }

    /**
      * Returns the PRTStandardBean's bracket.
      *
      * @return The PRTStandardBean's bracket
      */
    public String getBracket() {
        return bracket;
    }

    /**
      * Sets the PRTStandardBean's bracket.
      *
      * @param String bracket The new bracket
      */
    public void setBracket(String bracket) {
        this.bracket = bracket;
    }

    @Override
    public String toString() {
        return String.format("%d s, %d c, %d p, %d r",
                             score,
                             curlUps,
                             pushUps,
                             runTime);
    }
}
