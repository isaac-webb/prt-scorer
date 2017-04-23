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
import edu.gatech.nrotc.PRTStandard;
import edu.gatech.nrotc.PRTScoreBean;

public class PRTTester {
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
        new ParseDouble()
    };


    public static void main(String[] args) throws Exception {
        // Create the PRTStandard
        PRTStandard standard = new PRTStandard("M17-19.csv");

        // Read in all of the PRTScores
        ArrayList<PRTScoreBean> prtScores = new ArrayList<>();
        ICsvBeanReader beanReader = null;
        try {
            beanReader = new CsvBeanReader(new FileReader(
                                               "testData.csv"),
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
                standard.scorePRT(score);
            } catch (Exception e) {
                System.out.println("Incorrect score found");
            }
        });

        ICsvBeanWriter beanWriter = null;
        try {
                beanWriter = new CsvBeanWriter(new FileWriter("scores.csv"),
                        CsvPreference.STANDARD_PREFERENCE);

                final String[] header = new String[] { "name", "birthday", "age", "sex", "curlUps", "curlUpsScore", "pushUps", "pushUpsScore", "runTimeString", "runTimeScore", "averageScore" };

                beanWriter.writeHeader(header);

                for (PRTScoreBean prtScore : prtScores ) {
                        beanWriter.write(prtScore, header, WRITING_PROCESSORS);
                }

        }
        finally {
                if( beanWriter != null ) {
                        beanWriter.close();
                }
        }
    }
}
