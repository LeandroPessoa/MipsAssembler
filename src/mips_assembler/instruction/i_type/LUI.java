package mips_assembler.instruction.i_type;

import mips_assembler.instruction.I_type;

/**
 * Class describing the MIPS 'Load Upper Immediate' instruction
 * @author Alexander Eick (620),    alexander.eick@outlook.de
 * @author Leandro Pessoa (741),    leandroeliasster@gmail.com
 */
public class LUI extends I_type{
    
    /**
     * Constructor
     * @param instr_addr    int         unique address of the instruction
     * @param ac            String      assembly code of the instruction
     * @param ac_split      String[]    assembly code split into sub-parts
     */
    public LUI(int instr_addr, String ac, String[] ac_split){
        super(instr_addr, ac, ac_split);
    }
    
    
    protected void decipherAssembly(){
        rt = convertReg(ac_split[1]);
        convertImmediate(ac_split[2]);
        opCode[2] = opCode[3] = opCode[4] = opCode[5] = true;
    }
}
