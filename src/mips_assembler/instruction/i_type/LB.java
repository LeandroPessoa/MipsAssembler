package mips_assembler.instruction.i_type;

import mips_assembler.instruction.I_type;

/**
 * Class describing the MIPS 'Load Byte' instruction
 * @author Alexander Eick (620),    alexander.eick@outlook.de
 * @author Leandro Pessoa (741),    leandroeliasster@gmail.com
 */
public class LB extends I_type{
    
    /**
     * Constructor
     * @param instr_addr    int         unique address of the instruction
     * @param ac            String      assembly code of the instruction
     * @param ac_split      String[]    assembly code split into sub-parts
     */
    public LB(int instr_addr, String ac, String[] ac_split){
        super(instr_addr, ac, ac_split);
    }
    
    
    protected void decipherAssembly(){
        rs = convertReg(ac_split[3]);
        rt = convertReg(ac_split[1]);
        convertImmediate(ac_split[2]);
        opCode[0] = true;
    }
}
