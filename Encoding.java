import java.io.IOException;

public class Encoding {
    private String fInName;
    private String fOutName;
    private String fConfigName;
    private Table tabMap;
    private ConfigFile confFile;
    /**
     * initialize files
     *
     * @param fInN     input file name
     * @param fOutN    output file name
     * @param fConfigN configuration file name
     */
    Encoding(String fInN, String fOutN, String fConfigN) throws IOException, ConfigFileException, TableException {
        fInName = fInN;
        fOutName = fOutN;
        fConfigName = fConfigN;

        confFile = new ConfigFile();
        confFile.parseConfigFile(fConfigName);

        tabMap = new Table(confFile.getFTableName(), confFile.getMode(), confFile.numBytes());
        tabMap.makeTable();


    }

    /**
     * encoding file using encoding table
     *
     * @throws IOException
     * @throws ConfigFileException
     * @throws TableException
     */
    void runEncoding() throws IOException {
        ReadingBytes inFile = new ReadingBytes(fInName);

        WritingBytes outFile = new WritingBytes(fOutName);

        int sizeBytesArr = inFile.sizeInFile() / confFile.numBytes();
        int sizeRestBytesArr = inFile.sizeInFile() % confFile.numBytes();

        for (int i = 0; i < sizeBytesArr; i++) {
            byte[] inArrBytes = inFile.getBytesArr(confFile.numBytes());
            byte[] outArrBytes = tabMap.getValue(inArrBytes);

            if (outArrBytes != null)
                outFile.writeBytesArr(outArrBytes);
            else
                outFile.writeBytesArr(inArrBytes);
        }

        if (sizeRestBytesArr != 0) {
            byte[] restBytes = inFile.getBytesArr(sizeRestBytesArr);
            outFile.writeBytesArr(restBytes);
        }
    }
}
