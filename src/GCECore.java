import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;


public class GCECore {
    public static void encrypt(File fileName, String password, String outPath) throws IOException {
        if (!fileName.isFile())
            throw new IllegalArgumentException(fileName.getName() + " isn't a file!");

        if (!fileName.canRead())
            throw new IllegalArgumentException(fileName.getName() + " can't to read!");

        byte[] data;
        try {
            data = Files.readAllBytes(fileName.toPath());
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }

        if (data.length == 0)
            throw new IllegalArgumentException(fileName + " is empty!");

        System.out.println("-----FILE DATA-----");
        System.out.println("-> Filename : " + fileName.getName());
        System.out.println("-> Password : " + password);
        System.out.println("-> Length : " + data.length);
        System.out.println("-------------------");

        System.out.println("=> Encryption started");
        byte[] toXor = getBytesForXor(password);
        System.out.print("=> Got encryption key: ");
        for (byte b : toXor) {
            System.out.print(b);
        }
        System.out.print('\n');

        for (int i = 0; i < toXor.length; ++i) {
            for (int j = 0; j < data.length; ++j) {
                data[j] ^= toXor[i];
            }
        }

        System.out.println("=> Data successfully encrypted!");
        System.out.println("=> Now writing to file...");

        File out = new File(outPath);

        Files.write(out.toPath(), data);
        System.out.println("=> Succeed! GCEv1");
    }

    public static byte[] getBytesForXor(String password) {
        byte[] pass = password.getBytes(Charset.forName("utf-8"));

        for (int i = 0; i < pass.length; ++i) {
            pass[i] += i;
        }

        return pass;
    }
}
