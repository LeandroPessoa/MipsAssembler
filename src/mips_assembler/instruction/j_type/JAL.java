package mips_assembler.instruction.j_type;

import mips_assembler.instruction.J_type;

/**
 * Class describing the MIPS 'Jump and Link' instruction
 * @author Alexander Eick (620),    alexander.eick@outlook.de
 * @author Leandro Pessoa (741),    leandroeliasster@gmail.com
 */
public class JAL extends J_type{
    
    /**
     * Constructor
     * @param instr_addr    int         unique address of the instruction
     * @param ac            String      assembly code of the instruction
     * @param ac_split      String[]    assembly code split into sub-parts
     */
    public JAL(int instr_addr, String ac, String[] ac_split){
        super(instr_addr, ac, ac_split);
    }
    
    
    protected void decipherAssembly(){
        convertInstr_index(ac_split[1]);
        opCode[4] = opCode[5] = true;
    }
}
