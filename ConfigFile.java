import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ConfigFile {
    public enum mode {C, D}

    private enum config {MODE, NUM, TABLE}

    private mode realMode;
    private String fTableName;
    private int numBytes;

    private Map<config, String> configKeys = new HashMap<>();
    private Map<String, String> configArgs = new HashMap<>();

    private int SIZE = 3;
    private String REGEX = ":";

    /**
     * initialization of keys
     */
    ConfigFile() {
        configKeys.put(config.MODE, "mode");
        configKeys.put(config.NUM, "num");
        configKeys.put(config.TABLE, "table");
    }

    /**
     * set mode using enum
     *
     * @param strMode stringMode
     * @throws ConfigFileException
     */
    private void setMode(String strMode) throws ConfigFileException {
        if (strMode.equals("code"))
            realMode = mode.C;
        else if (strMode.equals("decode"))
            realMode = mode.D;
        else
            throw new ConfigFileException("Invalidate mode");
    }

    /**
     * parsing configuration file
     *
     * @param fName file name
     * @throws IOException
     * @throws ConfigFileException
     */
    public void parseConfigFile(String fName) throws IOException, ConfigFileException {
        BufferedReader reader = new BufferedReader(new FileReader(fName));
        String str;

        while ((str = reader.readLine()) != null) {
            String[] parts = str.split(REGEX);
            configArgs.put(parts[0].replaceAll("\\s*", ""), parts[1].replaceAll("\\s*", ""));
        }

        if (configArgs.size() != SIZE) {
            throw new ConfigFileException("Incorrect configuration file");
        }

        fTableName = configArgs.get(configKeys.get(config.TABLE));
        if (!fTableName.contains("txt")) {
            throw new ConfigFileException("Incorrect table name");
        }

        numBytes = Integer.parseInt(configArgs.get(configKeys.get(config.NUM)));
        if (numBytes <= 0) {
            throw new ConfigFileException("Wrong number of code bytes");
        }

        setMode(configArgs.get(configKeys.get(config.MODE)));
    }

    /**
     * get mode
     *
     * @return mode
     */
    public mode getMode() {
        return realMode;
    }

    /**
     * get number of bytes for encoding
     *
     * @return number of bytes
     */
    public int numBytes() {
        return numBytes;
    }

    /**
     * get name of byte table
     *
     * @return file name
     */
    public String getFTableName() {
        return fTableName;
    }
}

class ConfigFileException extends Exception {
    ConfigFileException(String message) {
        super(message);
    }
}