package edu.gatech.nrotc;

/**
  * This class defines a single PRT score for an individual.
  *
  * @author Isaac Webb
  * @version 1.0
  */
public class PRTScoreBean extends PRTStandardBean {
    private String name = "";
    private String birthday = "";
    private int age = 0;
    private String sex = "";
    private int curlUpsScore = 0;
    private int pushUpsScore = 0;
    private int runTimeScore = 0;

    /**
     * A no-args constructor for compatibility.
     */
    public PRTScoreBean() {
    }

    /**
     * Creates a PRTScoreBean with the given values.
     *
     * @param  String name          The PRTScoreBean's name
     * @param  String birthday      The PRTScoreBean's birthday
     * @param  int    age           The PRTScoreBean's age
     * @param  String sex           THe PRTScoreBean's sex
     */
    public PRTScoreBean(String name, String birthday, int age, String sex, int curlUps, int pushUps, String runTime) throws RunTimeFormatException {
        super(0, curlUps, pushUps, runTime, "");
        this.name = name;
        this.birthday = birthday;
        this.age = age;
        this.sex = sex;
    }

    /**
      * Returns the PRTScoreBean's name.
      *
      * @return The PRTScoreBean's name
      */
    public String getName() {
        return name;
    }

    /**
      * Sets the PRTScoreBean's name.
      *
      * @param String name The new name
      */
    public void setName(String name) {
        this.name = name;
    }

    /**
      * Returns the PRTScoreBean's birthday.
      *
      * @return The PRTScoreBean's birthday
      */
    public String getBirthday() {
        return birthday;
    }

    /**
      * Sets the PRTScoreBean's birthday.
      *
      * @param String birthday The new birthday
      */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
      * Returns the PRTScoreBean's age.
      *
      * @return The PRTScoreBean's age
      */
    public int getAge() {
        return age;
    }

    /**
      * Sets the PRTScoreBean's age. Will put a floor on the value at 0.
      *
      * @param int age The new age
      */
    public void setAge(int age) {
        this.age = (age > 0) ? age : 0;
    }

    /**
      * Returns the PRTScoreBean's sex.
      *
      * @return The PRTScoreBean's sex
      */
    public String getSex() {
        return sex;
    }

    /**
      * Sets the PRTScoreBean's sex.
      *
      * @param String sex The new sex
      */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
      * Returns the PRTScoreBean's curl ups score.
      *
      * @return The PRTScoreBean's curl ups score
      */
    public int getCurlUpsScore() {
        return curlUpsScore;
    }

    /**
      * Sets the PRTScoreBean's curl ups score. Will put a floor on the value at 0.
      *
      * @param int curlUpsScore The new curl ups score
      */
    public void setCurlUpsScore(int curlUpsScore) {
        this.curlUpsScore = (curlUpsScore > 0) ? curlUpsScore : 0;
    }

    /**
      * Returns the PRTScoreBean's push ups score.
      *
      * @return The PRTScoreBean's push ups score
      */
    public int getPushUpsScore() {
        return pushUpsScore;
    }

    /**
      * Sets the PRTScoreBean's push ups score. Will put a floor on the value at 0.
      *
      * @param int pushUpsScore The new push ups score
      */
    public void setPushUpsScore(int pushUpsScore) {
        this.pushUpsScore = (pushUpsScore > 0) ? pushUpsScore : 0;
    }

    /**
      * Returns the PRTScoreBean's run time score.
      *
      * @return The PRTScoreBean's run time score
      */
    public int getRunTimeScore() {
        return runTimeScore;
    }

    /**
      * Sets the PRTScoreBean's run time score. Will put a floor on the value at 0.
      *
      * @param int pushUpsScore The new run time score
      */
    public void setRunTimeScore(int runTimeScore) {
        this.runTimeScore = (runTimeScore > 0) ? runTimeScore : 0;
    }

    /**
     * Returns the PRTScoreBean's computed average score.
     *
     * @return The average score
     */
    public double getAverageScore() {
        return (curlUpsScore + pushUpsScore + runTimeScore) / 3.0;
    }
}
