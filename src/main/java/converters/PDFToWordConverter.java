package converters;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by akhil on 12/23/2016.
 */

public class PDFToWordConverter extends Converter {

    public void convert(File file) throws IOException {
        XWPFDocument xwpfDocument = new XWPFDocument();
        PdfReader reader = new PdfReader(file.getAbsolutePath());
        PdfReaderContentParser parser = new PdfReaderContentParser(reader);
        for (int pageNumber = 1; pageNumber <= reader.getNumberOfPages(); pageNumber++) {
            TextExtractionStrategy strategy = parser.processContent(pageNumber, new SimpleTextExtractionStrategy());
            String text = strategy.getResultantText();
            XWPFParagraph paragraph = xwpfDocument.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText(text);
            run.addBreak(BreakType.PAGE);
        }
        String fileName = file.getAbsolutePath().substring(0, file.getAbsolutePath().lastIndexOf('.')).concat(".docx");
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        xwpfDocument.write(fileOutputStream);
        fileOutputStream.close();
        reader.close();
    }
}
