import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadingBytes {
    private FileInputStream fileIn;

    /**
     * initialize class input file
     *
     * @param fName name of input file
     */
    ReadingBytes(String fName) {
        try {
            fileIn = new FileInputStream(fName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * get file size
     *
     * @return file size in bytes
     * @throws IOException
     */
    int sizeInFile() throws IOException {
        return fileIn.available();
    }

    /**
     * get array of bytes
     *
     * @param numBytes size of array
     * @return array of bytes
     * @throws IOException
     */
    public byte[] getBytesArr(int numBytes) throws IOException {
        byte[] buffer = new byte[numBytes];
        for (int iByte = 0; iByte < numBytes; iByte++) {
            buffer[iByte] = (byte) fileIn.read();
        }
        return buffer;
    }
}
