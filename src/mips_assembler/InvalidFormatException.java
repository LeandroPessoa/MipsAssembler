package mips_assembler;

/**
 * Invalid format exception to make the user aware of an invalid assembly 
 * code format
 * @author Alexander Eick (620),    alexander.eick@outlook.de
 * @author Leandro Pessoa (741),    leandroeliasster@gmail.com
 */
public class InvalidFormatException extends Exception {

    /**
     * Constructor
     * @param message   String  The message to specify the exception
     */
    public InvalidFormatException(String message) {
        super(message);
    }
}
