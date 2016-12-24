package converters;

/**
 * Created by akhil on 12/23/2016.
 */
public class ConverterFactory {
    public static Converter getConverter(String input) {
        if (ConversionType.PDF_TO_TEXT.name().toLowerCase().contains(input.toLowerCase()))
            return new PDFtoTextConverter();
        else if (ConversionType.PDF_TO_WORD.name().toLowerCase().contains(input.toLowerCase()))
            return new PDFToWordConverter();
        else
            return null;
    }
}
