import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class WritingBytes {
    private FileOutputStream fileOut;

    /**
     * initialize output file
     *
     * @param fName file name
     */
    WritingBytes(String fName) {
        try {
            fileOut = new FileOutputStream(fName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * write array of bytes into output file
     *
     * @param bytesArr array of coded bytes
     * @throws IOException
     */
    public void writeBytesArr(byte[] bytesArr) throws IOException {
        for (int iBytes = 0; iBytes < bytesArr.length; iBytes++) {
            fileOut.write(bytesArr[iBytes]);
        }
    }
}

