package mips_assembler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.LineNumberReader;

/**
 * Class to load and store an assembly and binary file, respectively.
 * @author Alexander Eick (620),    alexander.eick@outlook.de
 * @author Leandro Pessoa (741),    leandroeliasster@gmail.com
 */
public class Load_Store {

    /**
     * Method to load an assembly file (.asm) and return its content as array
     * of strings
     * @param filePath  String      The path of the .asm file
     * @return          String[]    The assembly code
     */
    public String[] loadAsmFile(String filePath) {
        int size = 0;
        String[] ac = new String[size];

        try {
            FileReader fr = new FileReader(filePath);
            LineNumberReader lr = new LineNumberReader(fr);
            lr.skip(Long.MAX_VALUE);
            size = lr.getLineNumber();//determine file size
            fr.close();
            fr = new FileReader(filePath);//close an reopen to return to the beginning of the file
            BufferedReader br = new BufferedReader(fr);
            ac = new String[size];
            for (int i = 0; i < ac.length; i++) {//copy lines from file to String array
                ac[i] = br.readLine();
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return ac;
    }
    
    /**
     * Method to save an binary file (.bin) at the specified location
     * @param filePath  String      The path of .bin file
     * @param mc        String[]    The machine code
     * @return          boolean     true if successful; false if an 
     *                              exception occurred
     */
    public boolean saveBinFile(String filePath, String[] mc){
        try {
            System.out.println(filePath.substring(filePath.length()-4));
            FileWriter fw = new FileWriter((filePath.substring(filePath.length()-4).equalsIgnoreCase(".bin")) ? filePath : (filePath + ".bin"));
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(mc[0]);
            for (int i = 1; i < mc.length; i++) {
                bw.newLine();
                bw.write(mc[i]);
            }
            bw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return true;
    }
}
