import java.io.File;
import java.io.IOException;


public class GCEncrypt {

    public static void main(String[] args) {
        if (args.length != 3)
            throw new IllegalArgumentException("Incorrect input format! Need 3 args (filePath, password, out)");

        try {
            GCECore.encrypt(new File(args[0]), args[1], args[2]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
