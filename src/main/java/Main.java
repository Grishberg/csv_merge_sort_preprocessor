import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.sun.org.apache.xpath.internal.operations.Mod;
import models.Model;

import java.io.*;
import java.util.*;

/**
 * Created by grishberg on 31.05.16.
 */
public class Main {
    public static final String OUTFILE_CSV = "outfile.csv";
    public static final String IN_CSV = "CC_ABONS/PROBLEMS.TXT";
    CSVWriter csvWriter;
    long startTime = System.currentTimeMillis();

    public static void main(String[] args) {
        Main main = new Main();
    }

    public Main() {

        File outFile = new File(OUTFILE_CSV);
        if (outFile.exists()) {
            outFile.delete();
        }
        File tmpDir = new File("tmp");
        if (!tmpDir.exists()) {
            tmpDir.mkdirs();
        }
        try {
            csvWriter = new CSVWriter(new FileWriter(OUTFILE_CSV));

            //List<Model> models = processCsv(IN_CSV);
            processTmpCsv();
            // Сортировка по ИД
            //models.sort(new AbonIdComparator<Model>());
            //for (Model model : models) {
            //    writeToCsv(model.getLine());
            //}
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (csvWriter != null) {
                try {
                    csvWriter.flush();
                    csvWriter.close();
                } catch (IOException e) {
                }
            }
        }
        System.out.printf("work time = %d\n", System.currentTimeMillis() - startTime);
    }

    /**
     * Слияние файлов
     *
     * @throws IOException
     */
    private void processTmpCsv() throws IOException {
        File root = new File("tmp");
        File[] list = root.listFiles();
        // инициализация ридеров
        List<CSVReader> readers = new LinkedList<CSVReader>();
        for (int i = 0; i < list.length; i++) {
            readers.add(new CSVReader(new FileReader(list[i])));
        }
        while (readers.size() > 0) {
            List<Model> models = new ArrayList<Model>(20);
            Iterator<CSVReader> iterator = readers.iterator();
            while (iterator.hasNext()) {
                CSVReader reader = iterator.next();
                String[] lines = reader.readNext();
                if (lines == null) {
                    reader.close();
                    iterator.remove();
                    System.out.printf("%d, readers size = d\n",
                            System.currentTimeMillis() - startTime, readers.size());
                    continue;
                }
                models.add(new Model(lines));
            }
            models.sort(new AbonIdComparator<Model>());
            for (Model currentModel : models) {
                writeToCsv(currentModel.getLine());
            }
        }
    }

    private List<Model> processCsv(String fileName) throws IOException {
        List<Model> res = new ArrayList<Model>(800000);
        CSVReader reader = new CSVReader(new FileReader(fileName));
        String[] nextLine;
        long counter = 0;
        int fileCounter = 0;

        while ((nextLine = reader.readNext()) != null) {
            // nextLine[] is an array of values from the line
            Model model = new Model(nextLine);
            res.add(model);
            //writeToCsv(nextLine);
            counter++;
            if (counter > 800000) {
                //sort and save to file
                saveToTmp(fileCounter++, res);
                res = new ArrayList<Model>();
                counter = 0;
                System.out.printf("%d, wrote to tmp/%03d.csv\n",
                        System.currentTimeMillis() - startTime, fileCounter - 1);
            }
        }
        if (counter > 0) {
            //sort and save to file
            saveToTmp(fileCounter, res);
        }
        return res;
    }

    private void saveToTmp(int count, List<Model> models) throws IOException {
        models.sort(new AbonIdComparator<Model>());
        CSVWriter csvTmpWriter = new CSVWriter(
                new FileWriter(String.format("tmp/%03d.csv", count)));
        for (Model model : models) {
            csvTmpWriter.writeNext(model.getLine());
        }
        csvTmpWriter.flush();
        csvTmpWriter.close();
    }

    private void writeToCsv(String[] line) {
        csvWriter.writeNext(line);
    }

    private static class AbonIdComparator<T extends Model> implements Comparator<T> {
        public int compare(Model o1, Model o2) {
            if (o2 == null || o2.getId_abon() == null) {
                return -1;
            }
            if (o1.getId_abon() == null) {
                return 1;
            }
            return o1.getId_abon().compareTo(o1.getId_abon());
        }
    }
}
