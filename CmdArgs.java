import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CmdArgs {
    private enum cmdArguments {INPUT, OUTPUT, CONFIG}

    private Map<String, String> cmdArgs = new HashMap<>();
    private Map<cmdArguments, String> cmdKeys = new HashMap<>();
    private ArrayList<String> keys = new ArrayList<>();

    private int NUM_ARGS = 6;
    private int STEP = 2;

    /**
     * initialization of keys of command arguments
     */
    CmdArgs() {
        cmdKeys.put(cmdArguments.INPUT, "input:");
        cmdKeys.put(cmdArguments.OUTPUT, "output:");
        cmdKeys.put(cmdArguments.CONFIG, "config:");

        keys.add(cmdKeys.get(cmdArguments.INPUT));
        keys.add(cmdKeys.get(cmdArguments.OUTPUT));
        keys.add(cmdKeys.get(cmdArguments.CONFIG));
    }

    /**
     * initialize map of command arguments by key-value
     *
     * @param Args array of strings, consisting keys and values
     * @throws CmdArgException
     */

    public void parsingCmdArgs(String[] Args) throws CmdArgException {
        if (Args.length != NUM_ARGS) {
            throw new CmdArgException("Incorrect number of command arguments");
        }

        for (int i = 0; i < Args.length; i += STEP) {
            if (keys.contains(Args[i]))
                cmdArgs.put(Args[i], Args[i + 1]);
        }
        if (cmdArgs.size() != NUM_ARGS / STEP) {
            throw new CmdArgException("Incorrect command arguments");
        }
    }

    /**
     * get name of input file
     *
     * @return file name
     */
    public String getFInName() {

        return cmdArgs.get(cmdKeys.get(cmdArguments.INPUT));
    }

    /**
     * get name of output file
     *
     * @return file name
     */
    public String getFOutName() {

        return cmdArgs.get(cmdKeys.get(cmdArguments.OUTPUT));
    }

    /**
     * get name of configuration file
     *
     * @return file name
     */
    public String getFConfigName() {

        return cmdArgs.get(cmdKeys.get(cmdArguments.CONFIG));
    }
}

class CmdArgException extends Exception {
    CmdArgException(String message) {
        super(message);
    }
}
