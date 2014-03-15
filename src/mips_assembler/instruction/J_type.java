package mips_assembler.instruction;

/**
 * Prototype class of an J_type MIPS instruction extending Instruction
 * @author Alexander Eick (620),    alexander.eick@outlook.de
 * @author Leandro Pessoa (741),    leandroeliasster@gmail.com
 */
public abstract class J_type extends Instruction{
    /**
     * The operation code of the MIPS instruction as boolean array
     */
    protected boolean[] opCode;
    /**
     * The instruction index of the MIPS instruction to which the jump shall be
     * performed as boolean array
     */
    protected boolean[] instr_index;
    
    /**
     * Constructor
     * @param instr_addr    int         unique address of the instruction
     * @param ac            String      assembly code of the instruction
     * @param ac_split      String[]    assembly code split into sub-parts
     */
    public J_type(int instr_addr, String ac, String[] ac_split){
        super(instr_addr, ac, ac_split);
        init();
        createMC();
    }
    
    /**
     * Method prototype to decipher the assembly code for each instruction
     */
    protected abstract void decipherAssembly();
    
    //initialize all boolean array to false
    private void init(){
        this.opCode = new boolean[]{false, false, false, false, false, false};
        this.instr_index = new boolean[]{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};
    }
    
    /**
     * Method to create the machine code by deciphering the assembly code
     * and copy each deciphered part into the array mc (representing the 
     * machine code)
     */
    protected void createMC(){
        decipherAssembly();
        System.arraycopy(opCode, 0, mc, 0, 6);
        System.arraycopy(instr_index, 0, mc, 6, 26);
    }
    
    /**
     * Method to convert the instruction index from decimal representation to 
     * binary representation and store the result in the 
     * instr_index boolean array
     * @param instr_index   String  The immediate value
     */
    public void convertInstr_index(String instr_index){
        int i = Integer.parseInt(instr_index);
        String b = Integer.toBinaryString(i);
        char[] c = new char[((b.toCharArray().length > 26) ? 26 : b.toCharArray().length)];
        System.arraycopy(b.toCharArray(), ((b.toCharArray().length > 26) ? 26 : 0), c, 0, ((b.toCharArray().length > 26) ? 26 : b.toCharArray().length));
        for (int j = c.length - 1; j >= 0; j--) {
            if(c[j] == '1'){
                this.instr_index[this.instr_index.length - (c.length - j)] = true;
            }
        }
    }
    
    /**
     * Method to retrieve the operation code of the MIPS instruction
     * @return  boolean[]   The operation code
     */
    public boolean[] getOpCode(){
        return this.opCode;
    }
    
    /**
     * Method to retrieve the instruction index of the target MIPS instruction 
     * @return  boolean[]   The instruction index
     */
    public boolean[] getInstr_index(){
        return this.instr_index;
    }
    
    /**
     * Method to retrieve the machine code as String of 1´s and 0´s
     * the string is formated so that a J_type instruction is easily recognized
     * @return  String  The machine code
     */
    @Override
    public String getMCstring(){
        String s = "";
        for (int i = 0; i < mc.length; i++) {
            if(mc[i] == true)
                s = s.concat("1");
            else
                s = s.concat("0");
            if (i == 5)
                s = s.concat(" "); 
        }
        
        return s;
    }
}
