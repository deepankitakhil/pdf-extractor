package gui;

import converters.Converter;
import converters.ConverterFactory;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;

/**
 * Created by akhil on 12/22/2016.
 */
public class PDFConverter {

    private static final String USER_HOME = "user.home";
    private static final String PDF = "PDF";

    public static void main(String[] args) throws IOException {
        PDFConverter pdfConverter = new PDFConverter();
        Converter converter = pdfConverter.identifyConversionType(args[0]);
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(PDF, PDF.toLowerCase());
        fileChooser.setFileFilter(filter);
        fileChooser.setCurrentDirectory(new File(System.getProperty(USER_HOME)));
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            converter.convert(selectedFile);
        }
    }

    private Converter identifyConversionType(String input) {
        return ConverterFactory.getConverter(input);
    }
}