package mips_assembler.instruction;

/**
 * Prototype class of a 32-bit MIPS instruction
 *
 * @author Alexander Eick (620), alexander.eick@outlook.de
 * @author Leandro Pessoa (741), leandroeliasster@gmail.com
 */
public abstract class Instruction {

    private int instr_addr;
    /**
     * Machine code as boolean array
     */
    protected boolean[] mc;
    /**
     * Assembly code as String
     */
    protected String ac;
    /**
     * Assembly code split up into sub-parts (mnemonics, registers and
     * constants) to generate instruction machine code
     */
    protected String[] ac_split;
    /**
     * Mnemonic of the MIPS instruction
     */
    protected String mnemonic;

    /**
     * Constructor
     *
     * @param instr_addr int unique address of the instruction
     * @param ac String assembly code of the instruction
     * @param ac_split String[] assembly code split into sub-parts
     */
    public Instruction(int instr_addr, String ac, String[] ac_split) {
        this.instr_addr = instr_addr;
        this.mc = new boolean[32];//MIPS instructions are 32 bit wide
        this.ac = ac;
        this.ac_split = ac_split;
        this.mnemonic = ac_split[0];
    }

    /**
     * Method to retrieve the instruction address
     *
     * @return int The instruction address
     */
    public int getInstr_addr() {
        return this.instr_addr;
    }

    /**
     * Method to retrieve the machine code as boolean array of length 32
     *
     * @return boolean[] The machine code
     */
    public boolean[] getMC() {
        return this.mc;
    }

    /**
     * Method to retrieve the machine code as String of 1´s and 0´s
     *
     * @return String The machine code
     */
    public String getMCstring() {
        String s = "";
        for (int i = 0; i < mc.length; i++) {
            if (mc[i]) {
                s = s.concat("1");
            } else {
                s = s.concat("0");
            }
        }
        return s;
    }

    /**
     * Method to retrieve the original assembly instruction
     *
     * @return String The assembly instruction
     */
    public String getAC() {
        return this.ac;
    }

    /**
     * Method to retrieve the mnemonics of the instruction
     *
     * @return String The mnemonics
     */
    public String getMnemonic() {
        return this.mnemonic;
    }

    /**
     * Method prototype to create the machine code from assembly code
     */
    protected abstract void createMC();

    /**
     * Method prototype to decipher the assembly code for each instruction
     */
    protected abstract void decipherAssembly();

    /**
     * Method to convert the assembly representation of registers into
     * binary/machine code
     *
     * @param reg String The register to decipher
     * @return boolean[] The binary representation of the register in the MIPS
     * architecture
     */
    protected boolean[] convertReg(String reg) {
        boolean[] regB;
        switch (reg) {
            case "$s0":
                regB = new boolean[]{true, false, false, false, false};
                break;
            case "$s1":
                regB = new boolean[]{true, false, false, false, true};
                break;
            case "$s2":
                regB = new boolean[]{true, false, false, true, false};
                break;
            case "$s3":
                regB = new boolean[]{true, false, false, true, true};
                break;
            case "$s4":
                regB = new boolean[]{true, false, true, false, false};
                break;
            case "$s5":
                regB = new boolean[]{true, false, true, false, true};
                break;
            case "$s6":
                regB = new boolean[]{true, false, true, true, false};
                break;
            case "$s7":
                regB = new boolean[]{true, false, true, true, true};
                break;
            case "$t0":
                regB = new boolean[]{false, true, false, false, false};
                break;
            case "$t1":
                regB = new boolean[]{false, true, false, false, true};
                break;
            case "$t2":
                regB = new boolean[]{false, true, false, true, false};
                break;
            case "$t3":
                regB = new boolean[]{false, true, false, true, true};
                break;
            case "$t4":
                regB = new boolean[]{false, true, true, false, false};
                break;
            case "$t5":
                regB = new boolean[]{false, true, true, false, true};
                break;
            case "$t6":
                regB = new boolean[]{false, true, true, true, false};
                break;
            case "$t7":
                regB = new boolean[]{false, true, true, true, true};
                break;
            case "$t8":
                regB = new boolean[]{true, true, false, false, false};
                break;
            case "$t9":
                regB = new boolean[]{true, true, false, false, true};
                break;
            default://should never occur
                regB = new boolean[]{false, false, false, false, false};
        }
        return regB;
    }
}
