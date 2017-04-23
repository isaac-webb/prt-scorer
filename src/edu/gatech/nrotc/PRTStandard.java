package edu.gatech.nrotc;

import java.util.ArrayList;
import java.io.FileReader;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.prefs.CsvPreference;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.io.CsvBeanReader;

/**
  * This class defines a PRT standard and provides methods for returning scores.
  * The CSV file must have 4 columns: Points, Curl ups, Push ups, and 1.5 mi run
  * in that order. In addition, rows below should be presented in descending
  * point value.
  *
  * @author Isaac Webb
  * @version 1.0
  */
public class PRTStandard {
    private static final CellProcessor[] PROCESSORS = new CellProcessor[] {
        new ParseInt(),
        new ParseInt(),
        new ParseInt(),
        new NotNull(),
        new NotNull()
    };

    private ArrayList<PRTStandardBean> standards = new ArrayList<>();

    /**
      * Creates a PRTStandard to generate scores from based on the CSV file
      * passed in.
      * @param  String                     csvFile       The file containing the
      *                                                  standard
      * @throws PRTStandardFormatException Thrown if any parsing of the standard
      *                                    file fails
      */
    public PRTStandard(String csvFile) throws Exception {
        ICsvBeanReader beanReader = null;
        try {
            beanReader = new CsvBeanReader(new FileReader(
                                               csvFile),
                                           CsvPreference.STANDARD_PREFERENCE);
            final String[] header = beanReader.getHeader(true);

            PRTStandardBean standard;
            while ((standard =
                        beanReader.read(PRTStandardBean.class, header,
                                        PROCESSORS)) != null) {
                standards.add(standard);
            }
        } finally {
            if (beanReader != null) {
                beanReader.close();
            }
        }

        standards.sort((e1, e2) -> {
            return e2.getScore() - e1.getScore();
        });
    }

    /**
      * Scores the number of curl ups done using the PRTStandard.
      *
      * @param  int reps          The number of curl ups done
      * @return     The score for the number of curl ups done
      */
    public int scoreCurlUps(int reps) {
        for (PRTStandardBean standard : standards) {
            if (reps >= standard.getCurlUps()) {
                return standard.getScore();
            }
        }

        // Return a score of 0 if no score bracket is found
        return 0;
    }

    /**
      * Scores the number of push ups done using the PRTStandard.
      *
      * @param  int reps          The number of push ups done
      * @return     The score for the number of push ups done
      */
    public int scorePushUps(int reps) {
        for (PRTStandardBean standard : standards) {
            if (reps >= standard.getPushUps()) {
                return standard.getScore();
            }
        }

        // Return a score of 0 if no score bracket is found
        return 0;
    }

    /**
      * Scores the run time using the PRTStandard.
      *
      * @param  int time          The run time in mm:ss format
      * @return     The score for the given run time
      */
    public int scoreRunTime(int time) {
        for (PRTStandardBean standard : standards) {
            if (time <= standard.getRunTime()) {
                return standard.getScore();
            }
        }

        // Return a score of 0 if no score bracket is found
        return 0;
    }

    /**
      * Assigns a score bracket using the PRTStandard.
      *
      * @param  double score          The average score
      * @return        The bracket for the given score
      */
    public String scoreBracket(double score) {
        for (PRTStandardBean standard : standards) {
            if (score >= standard.getScore()) {
                return standard.getBracket();
            }
        }

        // Return a score of 0 if no score bracket is found
        return "";
    }

    /**
      * Scores the given PRT.
      *
      * @param PRTScoreBean prtScoreBean The PRT to score
      */
    public void scorePRT(PRTScoreBean prtScoreBean) throws RunTimeFormatException {
        prtScoreBean.setCurlUpsScore(scoreCurlUps(prtScoreBean.getCurlUps()));
        prtScoreBean.setPushUpsScore(scorePushUps(prtScoreBean.getPushUps()));
        prtScoreBean.setRunTimeScore(scoreRunTime(prtScoreBean.getRunTime()));
        prtScoreBean.setBracket(scoreBracket(prtScoreBean.getAverageScore()));
    }
}
