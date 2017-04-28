# GT PRT Scorer
This application automatically grades Navy PRT results for males and females, 17-24 years old. It is written in Java and uses CSV files for both the scoring standards and the score inputs and outputs.

## Usage
To run the scorer, you must also have the super-csv-2.4.0.jar file in the same directory as the PRTScorer.jar file. To run the scorer, execute
```
java -jar PRTScorer.jar <standards-folder> <input-file> <output-file>
```

`<standards-folder>` should be the path to the folder containing the standards without a trailing file separator (i.e. `standards-folder` not `standards-folder/`). See the Standards section for more information on formatting.

`<input-file>` should be the CSV file containing the scores that need to be graded. See the Input Score File section for more information on how to format this file. The filename should always have a `.csv` extension.

`<output-file>` should be the desired name of the output CSV file which will contain the calculated scores. For more information on output formatting, see the Output Score File section. The filename should always have a `.csv` extension.

### Standards
The standards folder should contain four files: `M17-19.csv`, `M20-24.csv`, `F17-19.csv`, and `F20-24.csv` containing the scoring standards for males 17-19, males 20-24, females 17-19, and females 20-24 respectively.

Each of these should be in the standard CSV file format and contain five columns: Score, Curl Ups, Push Ups, Run Time, and Bracket. A header line should be included as follows:
```
score,curlUps,pushUps,runTime,bracket
```

Each subsequent line should define a single score category. See the standards files in this repository for concrete examples.

### Input Score File
The input score file should be in the standard CSV file format and contain seven columns: Name, Birthday, Age, Sex, Curl Ups, Push Ups, and Run Time. A header line should be included as follows:
```
name,birthday,age,sex,curlUps,pushUps,runTime
```

Each subsequent line should define a single person to be scored. The name and birthday fields can be in any format, but the age, curl ups, and push ups fields must all be integers, the sex field should either be `M` or `F`, and the run time should be in MM:SS format. Any incorrectly formatted scores may lead to errors while running the program or incorrect output score files.

For example, if John Doe was born on 31AUG97, was 20 years old, did 100 curl ups, 100 sit ups, and ran a 10:00, his line would appears as follows somewhere below the header line:
```
"Doe, John",31-Aug-97,20,M,100,100,10:00
```

### Output Score File
The output score file will be in the standard CSV file format and contain the same number of rows as the input score file but with the following twelve columns: Name, Birthday, Age, Sex, Curl Ups, Curl Ups Score, Push Ups, Push Ups Score, Run Time, Run Time Score, Average Score, and Bracket. A header line will be included as follows:
```
name,birthday,age,sex,curlUps,curlUpsScore,pushUps,pushUpsScore,runTimeString,runTimeScore,averageScore,bracket
```

The scores for curl ups, push ups, and run time are calculated using the bracket defined for each person's age and sex. The average score is simply the average of the three sub scores, and the bracket is the description for the average score.

## Dependencies
As stated in the usage section, this application requires the [Super CSV](https://github.com/super-csv/super-csv) library to function properly. Please download v2.4.0 [here](https://github.com/super-csv/super-csv/releases).
