package mips_assembler.instruction.i_type;

import mips_assembler.instruction.I_type;

/**
 * Class describing the MIPS 'Branch on Not Equal' instruction
 * @author Alexander Eick (620),    alexander.eick@outlook.de
 * @author Leandro Pessoa (741),    leandroeliasster@gmail.com
 */
public class BNE extends I_type{
    
    /**
     * Constructor
     * @param instr_addr    int         unique address of the instruction
     * @param ac            String      assembly code of the instruction
     * @param ac_split      String[]    assembly code split into sub-parts
     */
    public BNE(int instr_addr, String ac, String[] ac_split){
        super(instr_addr, ac, ac_split);
    }
    
    
    protected void decipherAssembly(){
        rs = convertReg(ac_split[1]);
        rt = convertReg(ac_split[2]);
        convertImmediate(ac_split[3]);
        opCode[3] = opCode[5] = true;
    }
}
