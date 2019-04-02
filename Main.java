import java.io.IOException;

public class Main {
    /**
     * encoding file using code table
     * @param args command arguments
     * @throws CmdArgException
     * @throws ConfigFileException
     * @throws TableException
     * @throws IOException
     */
    public static void encodingFunction(String args[]) throws CmdArgException, ConfigFileException, TableException, IOException {
        CmdArgs argCmd = new CmdArgs();
        argCmd.parsingCmdArgs(args);

        Encoding run = new Encoding(argCmd.getFInName(), argCmd.getFOutName(), argCmd.getFConfigName());
        run.runEncoding();
    }

    public static void main(String args[]) {
        try {
            encodingFunction(args);
        } catch (ConfigFileException | IOException | CmdArgException | TableException e) {
            e.printStackTrace();
        }
    }
}