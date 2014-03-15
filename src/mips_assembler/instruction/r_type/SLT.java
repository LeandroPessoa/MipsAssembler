package mips_assembler.instruction.r_type;

import mips_assembler.instruction.R_type;

/**
 * Class describing the MIPS 'Set on Less Than' instruction
 * @author Alexander Eick (620),    alexander.eick@outlook.de
 * @author Leandro Pessoa (741),    leandroeliasster@gmail.com
 */
public class SLT extends R_type{
    
    /**
     * Constructor
     * @param instr_addr    int         unique address of the instruction
     * @param ac            String      assembly code of the instruction
     * @param ac_split      String[]    assembly code split into sub-parts
     */
    public SLT(int instr_addr, String ac, String[] ac_split){
        super(instr_addr, ac, ac_split);
    }
    
    
    protected void decipherAssembly(){
        rs = convertReg(ac_split[2]);
        rt = convertReg(ac_split[3]);
        rd = convertReg(ac_split[1]);
        funct[0] = funct[2] = funct[4] = true;
    }
}
