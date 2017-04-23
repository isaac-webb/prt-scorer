package edu.gatech.nrotc;

import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileWriter;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.ParseDouble;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.prefs.CsvPreference;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.CsvBeanWriter;

public class PRTScorer {
    private static final CellProcessor[] READING_PROCESSORS = new CellProcessor[] {
        new NotNull(),
        new NotNull(),
        new ParseInt(),
        new NotNull(),
        new ParseInt(),
        new ParseInt(),
        new NotNull()
    };

    private static final CellProcessor[] WRITING_PROCESSORS = new CellProcessor[] {
        new NotNull(),
        new NotNull(),
        new ParseInt(),
        new NotNull(),
        new ParseInt(),
        new ParseInt(),
        new ParseInt(),
        new ParseInt(),
        new NotNull(),
        new ParseInt(),
        new ParseDouble(),
        new NotNull()
    };


    public static void main(String[] args) throws Exception {
        if (args.length != 3) {
            System.out.println("Usage: java -jar PRTScorer.jar <standards> <in-file> <out-file>");
            return;
        }

        // Create the PRTStandard
        PRTStandard m1719 = new PRTStandard(args[0] + "/M17-19.csv");
        PRTStandard m2024 = new PRTStandard(args[0] + "/M20-24.csv");
        PRTStandard f1719 = new PRTStandard(args[0] + "/F17-19.csv");
        PRTStandard f2024 = new PRTStandard(args[0] + "/F20-24.csv");

        // Read in all of the PRTScores
        ArrayList<PRTScoreBean> prtScores = new ArrayList<>();
        ICsvBeanReader beanReader = null;
        try {
            beanReader = new CsvBeanReader(new FileReader(
                                               args[1]),
                                           CsvPreference.STANDARD_PREFERENCE);
            final String[] header = beanReader.getHeader(true);

            PRTScoreBean prtScoreBean;
            while ((prtScoreBean =
                        beanReader.read(PRTScoreBean.class, header,
                                        READING_PROCESSORS)) != null) {
                prtScores.add(prtScoreBean);
            }
        } finally {
            if (beanReader != null) {
                beanReader.close();
            }
        }

        prtScores.forEach(score -> {
            try {
                if (score.getSex().equalsIgnoreCase("M")) {
                    if (score.getAge() <= 19) {
                        m1719.scorePRT(score);
                    } else {
                        m2024.scorePRT(score);
                    }
                } else {
                    if (score.getAge() <= 19) {
                        f1719.scorePRT(score);
                    } else {
                        f2024.scorePRT(score);
                    }
                }
            } catch (Exception e) {
                System.out.printf("Score for %s is invalid.\n", score.getName());
            }
        });

        ICsvBeanWriter beanWriter = null;
        try {
                beanWriter = new CsvBeanWriter(new FileWriter(args[2]),
                        CsvPreference.STANDARD_PREFERENCE);

                final String[] header = new String[] { "name", "birthday", "age", "sex", "curlUps", "curlUpsScore", "pushUps", "pushUpsScore", "runTimeString", "runTimeScore", "averageScore", "bracket" };

                beanWriter.writeHeader(header);

                for (PRTScoreBean prtScore : prtScores ) {
                        beanWriter.write(prtScore, header, WRITING_PROCESSORS);
                }

        } finally {
            if( beanWriter != null ) {
                beanWriter.close();
            }
        }
    }
}
