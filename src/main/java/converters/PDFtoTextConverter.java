package converters;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by akhil on 12/23/2016.
 */
public class PDFtoTextConverter extends Converter {

    @Override
    public void convert(File file) throws IOException {
        String text = getText(file);
        createTextFile(text, file.getAbsolutePath());
    }

    private void createTextFile(String text, String filePath) throws IOException {
        int dotIndex = filePath.lastIndexOf('.');
        String fileName = filePath.substring(0, dotIndex).concat(".txt");
        try {
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");
            writer.println(text);
            writer.close();
        } catch (IOException e) {
        }
    }

    String getText(File pdfFile) throws IOException {
        PDDocument doc = PDDocument.load(pdfFile);
        return new PDFTextStripper().getText(doc);
    }
}
