package mips_assembler.instruction;

/**
 * Prototype class of an I_type MIPS instruction extending Instruction
 * @author Alexander Eick (620),    alexander.eick@outlook.de
 * @author Leandro Pessoa (741),    leandroeliasster@gmail.com
 */
public abstract class I_type extends Instruction{
    /**
     * The operation code of the MIPS instruction as boolean array
     */
    protected boolean[] opCode;
    /**
     * The source register of the MIPS instruction as boolean array
     */
    protected boolean[] rs;
    /**
     * The target register of the MIPS instruction as boolean array
     */
    protected boolean[] rt;
    /**
     * The immediate value (constant) of the MIPS instruction as boolean array
     */
    protected boolean[] immediate;
    
    /**
     * Constructor
     * @param instr_addr    int         unique address of the instruction
     * @param ac            String      assembly code of the instruction
     * @param ac_split      String[]    assembly code split into sub-parts
     */
    public I_type(int instr_addr, String ac, String[] ac_split){
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
        this.rs = new boolean[]{false, false, false, false, false};
        this.rt = new boolean[]{false, false, false, false, false};
        this.immediate = new boolean[]{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};
    }
    
    /**
     * Method to create the machine code by deciphering the assembly code
     * and copy each deciphered part into the array mc (representing the 
     * machine code)
     */
    protected void createMC(){
        decipherAssembly();
        System.arraycopy(opCode, 0, mc, 0, 6);
        System.arraycopy(rs, 0, mc, 6, 5);
        System.arraycopy(rt, 0, mc, 11, 5);
        System.arraycopy(immediate, 0, mc, 16, 16);
    }
    
    /**
     * Method to convert the immediate value from decimal representation to 
     * binary representation and store the result in the immediate boolean array
     * @param imm   String  The immediate value
     */
    protected void convertImmediate(String imm){
        int i = Integer.parseInt(imm);
        char[] c = Integer.toBinaryString(i).toCharArray();
        for (int j = immediate.length - 1; j >= 0; j--) {
            if((j - (immediate.length - c.length) >= 0) && (c[j - (immediate.length - c.length)] == '1'))
                this.immediate[j] = true;
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
     * Method to retrieve the source register of the MIPS instruction
     * @return  boolean[]   The source register
     */
    public boolean[] getRs(){
        return this.rs;
    }
    
    /**
     * Method to retrieve the target register of the MIPS instruction
     * @return  boolean[]   The target register
     */
    public boolean[] getRt(){
        return this.rt;
    }
    
    /**
     * Method to retrieve the immediate value of the MIPS instruction
     * @return  boolean[]   The immediate value
     */
    public boolean[] getImmediate(){
        return this.immediate;
    }
    
    /**
     * Method to retrieve the machine code as String of 1´s and 0´s
     * the string is formated so that a I_type instruction is easily recognized
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
            if (i == 5 || i == 10 || i == 15)
                s = s.concat(" ");
        }
        return s;
    }
}
