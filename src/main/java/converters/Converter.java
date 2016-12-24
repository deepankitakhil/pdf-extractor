package converters;

import java.io.File;
import java.io.IOException;

/**
 * Created by akhil on 12/23/2016.
 */
public abstract class Converter {
    public abstract void convert(File file) throws IOException;
}
