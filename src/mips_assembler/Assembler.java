package mips_assembler;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import mips_assembler.instruction.*;
import mips_assembler.instruction.r_type.*;
import mips_assembler.instruction.i_type.*;
import mips_assembler.instruction.j_type.*;

/**
 * Assembler class which creates the different MIPS instructions according to
 * the assembly code and stores them in a list. Error in assembling are stored 
 * in a separate list
 * @author Alexander Eick (620),    alexander.eick@outlook.de
 * @author Leandro Pessoa (741),    leandroeliasster@gmail.com
 */
public class Assembler {

    private List<Instruction> binCode = new LinkedList<Instruction>();
    private List<InvalidFormatException> erorrs = new LinkedList<InvalidFormatException>();

    /**
     * Method to create the binary/machine code from an assembly code
     * @param aca   String[]    The assembly code with one assembly instruction
     *                          per array element
     */
    public void createBinCode(String[] aca) {
        for (int i = 0; i < aca.length; i++) {
            try{
                binCode.add(createInstr(aca[i], splitAc(aca[i]), i));
            } catch(InvalidFormatException e){
                erorrs.add(e);
            }
        }
    }
    
    /**
     * Method to reset the two list containing the machine code and the 
     * assembly errors
     */
    public void reset(){
        binCode.clear();
        erorrs.clear();
    }
    
    //Method to split an assembly instruction into all sub-parts
    private String[] splitAc(String ac){
        String[] temp = ac.split(" ", 2);//yielding the mnemonic in array element 0 and the rest of the assembly instruction in array element 1
        temp[1] = temp[1].replaceAll(" ", "").split("#", 2)[0];//deleting all spaces and cutting of the comment part (after #)
        String[] temp2 = temp[1].split(",");//spliting the registers
        String[] temp3 = new String[(temp2.length + 1)];
        System.arraycopy(temp, 0, temp3, 0, 1);
        System.arraycopy(temp2, 0, temp3, 1, temp2.length);//combine the mnemonic and registers in 1 string
        
        return temp3;
    }
    
    //Method to split an load or store assembly instruction into base and offset
    private String[] load_store_split(String[] ac_split){
        String[] temp;
        temp = ac_split[2].split("\\(");//split string at '('
        temp[1] = temp[1].replaceAll("\\)", "");//delete ')'
        String[] temp2 = new String[ac_split.length + temp.length - 1];
        System.arraycopy(ac_split, 0, temp2, 0, 2);
        System.arraycopy(temp, 0, temp2, 2, 2);//combine ac_split with base and offset strings
        
        return temp2;
    }
    
    //Method to create instructions according to their mnemonic
    //returns the created instruction or throws an exception if the instruction as the wrong format for mnemonic
    private Instruction createInstr(String ac, String[] ac_split, int id) throws InvalidFormatException {
        Instruction instr;
        switch (ac_split[0].toUpperCase()) {
            case "ADD":
                if (ac_split.length != 4) {
                    throw new InvalidFormatException("INVALID FORMAT on line " + id);
                } else {
                    instr = new ADD(id, ac, ac_split);
                }
                break;
            case "ADDI":
                if (ac_split.length != 4) {
                    throw new InvalidFormatException("INVALID FORMAT on line " + id);
                } else if(!checkNumber(ac_split[3])){
                    throw new InvalidFormatException("INVALID FORMAT on line " + id);
                } else {
                    instr = new ADDI(id, ac, ac_split);
                }
                break;
            case "ADDIU":
                if (ac_split.length != 4) {
                    throw new InvalidFormatException("INVALID FORMAT on line " + id);
                } else if(!checkNumber(ac_split[3])){
                    throw new InvalidFormatException("INVALID FORMAT on line " + id);
                } else {
                    instr = new ADDIU(id, ac, ac_split);
                }
                break;
            case "ADDU":
                if (ac_split.length != 4) {
                    throw new InvalidFormatException("INVALID FORMAT on line " + id);
                } else {
                    instr = new ADDU(id, ac, ac_split);
                }
                break;
            case "AND":
                if (ac_split.length != 4) {
                    throw new InvalidFormatException("INVALID FORMAT on line " + id);
                } else {
                    instr = new AND(id, ac, ac_split);
                }
                break;
            case "ANDI":
                if (ac_split.length != 4) {
                    throw new InvalidFormatException("INVALID FORMAT on line " + id);
                } else if(!checkNumber(ac_split[3])){
                    throw new InvalidFormatException("INVALID FORMAT on line " + id);
                } else {
                    instr = new ANDI(id, ac, ac_split);
                }
                break;
            case "BEQ":
                if (ac_split.length != 4) {
                    throw new InvalidFormatException("INVALID FORMAT on line " + id);
                } else if(!checkNumber(ac_split[3])){
                    throw new InvalidFormatException("INVALID FORMAT on line " + id);
                } else {
                    instr = new BEQ(id, ac, ac_split);
                }
                break;
            case "BNE":
                if (ac_split.length != 4) {
                    throw new InvalidFormatException("INVALID FORMAT on line " + id);
                } else if(!checkNumber(ac_split[3])){
                    throw new InvalidFormatException("INVALID FORMAT on line " + id);
                } else {
                    instr = new BNE(id, ac, ac_split);
                }
                break;
            case "J":
                if (ac_split.length != 2) {
                    throw new InvalidFormatException("INVALID FORMAT on line " + id);
                } else if(!checkNumber(ac_split[1])){
                    throw new InvalidFormatException("INVALID FORMAT on line " + id);
                } else {
                    instr = new J(id, ac, ac_split);
                }
                break;
            case "JAL":
                if (ac_split.length != 2) {
                    throw new InvalidFormatException("INVALID FORMAT on line " + id);
                } else if(!checkNumber(ac_split[1])){
                    throw new InvalidFormatException("INVALID FORMAT on line " + id);
                } else {
                    instr = new JAL(id, ac, ac_split);
                }
                break;
            case "JR":
                if (ac_split.length != 2) {
                    throw new InvalidFormatException("INVALID FORMAT on line " + id);
                } else {
                    instr = new JR(id, ac, ac_split);
                }
                break;
            case "LB":
                if (ac_split.length != 3) {
                    throw new InvalidFormatException("INVALID FORMAT on line " + id);
                } else if(!checkNumber(load_store_split(ac_split)[2])){
                    throw new InvalidFormatException("INVALID FORMAT on line " + id);
                } else {
                    instr = new LB(id, ac, load_store_split(ac_split));
                }
                break;
            case "LBU":
                if (ac_split.length != 3) {
                    throw new InvalidFormatException("INVALID FORMAT on line " + id);
                } else if(!checkNumber(load_store_split(ac_split)[2])){
                    throw new InvalidFormatException("INVALID FORMAT on line " + id);
                } else {
                    instr = new LBU(id, ac, load_store_split(ac_split));
                }
                break;
            case "LH":
                if (ac_split.length != 3) {
                    throw new InvalidFormatException("INVALID FORMAT on line " + id);
                } else if(!checkNumber(load_store_split(ac_split)[2])){
                    throw new InvalidFormatException("INVALID FORMAT on line " + id);
                } else {
                    instr = new LH(id, ac, load_store_split(ac_split));
                }
                break;
            case "LHU":
                if (ac_split.length != 3) {
                    throw new InvalidFormatException("INVALID FORMAT on line " + id);
                } else if(!checkNumber(load_store_split(ac_split)[2])){
                    throw new InvalidFormatException("INVALID FORMAT on line " + id);
                } else {
                    instr = new LHU(id, ac, load_store_split(ac_split));
                }
                break;
            case "LUI":
                if (ac_split.length != 3) {
                    throw new InvalidFormatException("INVALID FORMAT on line " + id);
                } else if(!checkNumber(ac_split[2])){
                    throw new InvalidFormatException("INVALID FORMAT on line " + id);
                } else {
                    instr = new LUI(id, ac, ac_split);
                }
                break;
            case "LW":
                if (ac_split.length != 3) {
                    throw new InvalidFormatException("INVALID FORMAT on line " + id);
                } else if(!checkNumber(load_store_split(ac_split)[2])){
                    throw new InvalidFormatException("INVALID FORMAT on line " + id);
                } else {
                    instr = new LW(id, ac, load_store_split(ac_split));
                }
                break;
            case "NOR":
                if (ac_split.length != 4) {
                    throw new InvalidFormatException("INVALID FORMAT on line " + id);
                } else {
                    instr = new NOR(id, ac, ac_split);
                }
                break;
            case "OR":
                if (ac_split.length != 4) {
                    throw new InvalidFormatException("INVALID FORMAT on line " + id);
                } else {
                    instr = new OR(id, ac, ac_split);
                }
                break;
            case "ORI":
                if (ac_split.length != 4) {
                    throw new InvalidFormatException("INVALID FORMAT on line " + id);
                } else if(!checkNumber(ac_split[3])){
                    throw new InvalidFormatException("INVALID FORMAT on line " + id);
                } else {
                    instr = new ORI(id, ac, ac_split);
                }
                break;
            case "SB":
                if (ac_split.length != 3) {
                    throw new InvalidFormatException("INVALID FORMAT on line " + id);
                } else if(!checkNumber(load_store_split(ac_split)[2])){
                    throw new InvalidFormatException("INVALID FORMAT on line " + id);
                } else {
                    instr = new SB(id, ac, load_store_split(ac_split));
                }
                break;
            case "SH":
                if (ac_split.length != 3) {
                    throw new InvalidFormatException("INVALID FORMAT on line " + id);
                } else if(!checkNumber(load_store_split(ac_split)[2])){
                    throw new InvalidFormatException("INVALID FORMAT on line " + id);
                } else {
                    instr = new SH(id, ac, load_store_split(ac_split));
                }
                break;
            case "SLL":
                if (ac_split.length != 4) {
                    throw new InvalidFormatException("INVALID FORMAT on line " + id);
                } else {
                    instr = new SLL(id, ac, ac_split);
                }
                break;
            case "SLT":
                if (ac_split.length != 4) {
                    throw new InvalidFormatException("INVALID FORMAT on line " + id);
                } else {
                    instr = new SLT(id, ac, ac_split);
                }
                break;
            case "SLTI":
                if (ac_split.length != 4) {
                    throw new InvalidFormatException("INVALID FORMAT on line " + id);
                } else if(!checkNumber(ac_split[3])){
                    throw new InvalidFormatException("INVALID FORMAT on line " + id);
                } else {
                    instr = new SLTI(id, ac, ac_split);
                }
                break;
            case "SLTIU":
                if (ac_split.length != 4) {
                    throw new InvalidFormatException("INVALID FORMAT on line " + id);
                } else if(!checkNumber(ac_split[3])){
                    throw new InvalidFormatException("INVALID FORMAT on line " + id);
                } else {
                    instr = new SLTIU(id, ac, ac_split);
                }
                break;
            case "SLTU":
                if (ac_split.length != 4) {
                    throw new InvalidFormatException("INVALID FORMAT on line " + id);
                } else {
                    instr = new SLTU(id, ac, ac_split);
                }
                break;
            case "SRL":
                if (ac_split.length != 4) {
                    throw new InvalidFormatException("INVALID FORMAT on line " + id);
                } else {
                    instr = new SRL(id, ac, ac_split);
                }
                break;
            case "SUB":
                if (ac_split.length != 4) {
                    throw new InvalidFormatException("INVALID FORMAT on line " + id);
                } else {
                    instr = new SUB(id, ac, ac_split);
                }
                break;
            case "SUBU":
                if (ac_split.length != 4) {
                    throw new InvalidFormatException("INVALID FORMAT on line " + id);
                } else {
                    instr = new SUBU(id, ac, ac_split);
                }
                break;
            case "SW":
                if (ac_split.length != 3) {
                    throw new InvalidFormatException("INVALID FORMAT on line " + id);
                } else if(!checkNumber(load_store_split(ac_split)[2])){
                    throw new InvalidFormatException("INVALID FORMAT on line " + id);
                } else {
                    instr = new SW(id, ac, load_store_split(ac_split));
                }
                break;
            default://if assembly instruction is unknown or menmonic is misspelled
                throw new InvalidFormatException("INVALID FORMAT on line " + id);
        }
        return instr;
    }
    
    //Method to check if the immediate or offset or instr_index value is an 
    //integer, to avoid typecasting exceptions
    private boolean checkNumber(String s) {
        char[] c = s.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if(c[i] < 48 || c[i] > 57)
                return false;
        }
        return true;
    }
    
    /**
     * Method to retrieve the machine code as array of Instructions
     * @return  Instruction[]   The machine code
     */
    public Instruction[] getBinCodeInstr(){
        return Arrays.copyOf(this.binCode.toArray(), binCode.size(), Instruction[].class);
    }
    
    /**
     * Method to retrieve to machine code as array of strings
     * @return  String[]    The machine code
     */
    public String[] getBinCodeString(){
        Instruction[] i = getBinCodeInstr();
        String[] s = new String[i.length];
        for (int j = 0; j < i.length; j++) {
            s[j] = i[j].getMCstring();
        }
        return s;
    }
    
    /**
     * Method to retrieve the list of errors as array of InvalidFormatExceptions
     * @return  InvalidFormatException[]    The list of errors
     */
    public InvalidFormatException[] getErrors(){
        return Arrays.copyOf(this.erorrs.toArray(), erorrs.size(), InvalidFormatException[].class);
    }
    
    /**
     * Method to retrieve the Instruction with the given ID, if no Instruction
     * can be found NULL is returned
     * @param id    int             The instruction ID to search for
     * @return      Instruction     The Instruction with the corresponding ID
     *                              or NULL if no Instruction can be found
     */
    public Instruction getInstr(int id){
        for (int j = 0; j < binCode.size(); j++) {
            if(binCode.get(j).getInstr_addr() == id)
                return (Instruction)binCode.get(j);
        }
        return null;
    }
    
    //returns all Instructions with the specified mnemonics
    /**
     * Method to return all Instructions with the given mnemonic as array of
     * Instructions
     * @param mnemonic  String          The mnemonic to search for
     * @return          Instruction[]   The list of Instructions with the
     *                                  specified mnemonics
     */
    public Instruction[] getMnemonics(String mnemonic){
        Instruction[] temp = new Instruction[binCode.size()];
        int c = 0;
        for (int j = 0; j < binCode.size(); j++) {
            if (binCode.get(j).getMnemonic().equalsIgnoreCase(mnemonic))
                temp[c++] = binCode.get(j);
        }
        int s = 0;
        for (int j = 0; j < temp.length; j++) {//count number of elements in temp array
            if(temp[j] != null)
                s++;
        }
        Instruction[] i = new Instruction[s];
        for (int j = 0; j < i.length; j++) {//copy populated elements from temp array
            i[j] = temp[j];
        }
        return i;
    }
}
