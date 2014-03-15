package mips_assembler;

import javax.swing.JFrame;
import mips_assembler.instruction.Instruction;


/**
 * Runner class of the MIPS Assembler
 * @author Alexander Eick (620),    alexander.eick@outlook.de
 * @author Leandro Pessoa (741),    leandroeliasster@gmail.com
 */
public class runner {
    public static void main(String[] args) {
        /*Assembler test = new Assembler();
        
        String[] s = new String[31];
        s[0]  = "ADD  $s3,$t3,$s4 #test ADD";
        s[1]  = "ADDI  $s3,$t3,7";
        s[2]  = "ADDIU $s3,$t3,7";
        s[3]  = "ADDU $s3,$t3,$s4";
        s[4]  = "AND $s3,$t3,$s4";
        s[5]  = "ANDI $s3,$t3,7";
        s[6]  = "BEQ  $s3,$t3,7";
        s[7]  = "BNE  $s3,$t3,7";
        s[8]  = "J 31";
        s[9]  = "JAL 31";
        s[10] = "JR $s3";
        s[11] = "LB $s3, 7($t3)";
        s[12] = "LBU $s3, 7($t3)";
        s[13] = "LH $s3, 7($t3)";
        s[14] = "LHU $s3, 7($t3)";
        s[15] = "LUI $s3, 7";
        s[16] = "LW $s3, 7($t3)";
        s[17] = "NOR $s3, $t3, $s4";
        s[18] = "OR $s3, $t3, $s4";
        s[19] = "ORI $s3, $t3, 7";
        s[20] = "SB $s3, 7($t3)";
        s[21] = "SH $s3, 7($t3)";
        s[22] = "SLL $s3, $t3, $s4";
        s[23] = "SLT $s3, $t3, $s4";
        s[24]  = "SLTI  $s3,$t3,7";
        s[25]  = "SLTIU  $s3,$t3,7";
        s[26] = "SLTU $s3, $t3, $s4";
        s[27] = "SRL $s3, $t3, $s4";
        s[28] = "SUB $s3, $t3, $s4";
        s[29] = "SUBU $s3, $t3, $s4";
        s[30] = "SW $s3, 7($t3)";*/
        GUI g = new GUI();
        g.setVisible(true);
        g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
}
