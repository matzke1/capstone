// Capstone Java binding
// By Nguyen Anh Quynh & Dang Hoang Vu,  2013

package capstone;

import com.sun.jna.Structure;
import com.sun.jna.Pointer;
import com.sun.jna.Union;
import com.sun.jna.NativeLong;

import java.util.List;
import java.util.Arrays;

public class Mips {

  // Operand type
  public static final int MIPS_OP_INVALID = 0;  // Uninitialized.
  public static final int MIPS_OP_REG = 1;   // Register operand.
  public static final int MIPS_OP_IMM = 2;   // Immediate operand.
  public static final int MIPS_OP_MEM = 3;   // Memory operand

  public static class MemType extends Structure {
    public int base;
    public long disp;

    @Override
    public List getFieldOrder() {
      return Arrays.asList("base", "disp");
    }
  }

  public static class OpValue extends Union {
    public int reg;
    public long imm;
    public MemType mem;

    @Override
    public List getFieldOrder() {
      return Arrays.asList("reg", "imm", "mem");
    }
  }

  public static class Operand extends Structure {
    public int type;
    public OpValue value;

    public void read() {
      super.read();
      if (type == MIPS_OP_MEM)
        value.setType(MemType.class);
      if (type == MIPS_OP_IMM)
        value.setType(Long.TYPE);
      if (type == MIPS_OP_REG)
        value.setType(Integer.TYPE);
      if (type == MIPS_OP_INVALID)
        return;
      readField("value");
    }
    @Override
    public List getFieldOrder() {
      return Arrays.asList("type", "value");
    }
  }

  public static class UnionOpInfo extends Capstone.UnionOpInfo {
    public short op_count;
    public Operand [] op;

    public UnionOpInfo() {
      op = new Operand[8];
    }

    public UnionOpInfo(Pointer p) {
      op = new Operand[8];
      useMemory(p);
      read();
    }

    public static int getSize() {
      return (new UnionOpInfo()).size();
    }

    public void read() {
      readField("op_count");
      op = new Operand[op_count];
      readField("op");
    }

    @Override
    public List getFieldOrder() {
      return Arrays.asList("op_count", "op");
    }
  }

  public static class OpInfo extends Capstone.OpInfo {

    public Operand [] op;

    public OpInfo(UnionOpInfo e) {
      if (e.op_count == 0) {
        op = null;
        return;
      }
      op = e.op;
    }
  }

  // MIPS registers
  public static final int MIPS_REG_INVALID = 0;
  public static final int MIPS_REG_0 = 1;
  public static final int MIPS_REG_ZERO = MIPS_REG_0;
  public static final int MIPS_REG_1 = 2;
  public static final int MIPS_REG_AT = MIPS_REG_1;
  public static final int MIPS_REG_2 = 3;
  public static final int MIPS_REG_V0 = MIPS_REG_2;
  public static final int MIPS_REG_3 = 4;
  public static final int MIPS_REG_V1 = MIPS_REG_3;
  public static final int MIPS_REG_4 = 5;
  public static final int MIPS_REG_A0 = MIPS_REG_4;
  public static final int MIPS_REG_5 = 6;
  public static final int MIPS_REG_A1 = MIPS_REG_5;
  public static final int MIPS_REG_6 = 7;
  public static final int MIPS_REG_A2 = MIPS_REG_6;
  public static final int MIPS_REG_7 = 8;
  public static final int MIPS_REG_A3 = MIPS_REG_7;
  public static final int MIPS_REG_8 = 9;
  public static final int MIPS_REG_T0 = MIPS_REG_8;
  public static final int MIPS_REG_9 = 10;
  public static final int MIPS_REG_T1 = MIPS_REG_9;
  public static final int MIPS_REG_10 = 11;
  public static final int MIPS_REG_T2 = MIPS_REG_10;
  public static final int MIPS_REG_11 = 12;
  public static final int MIPS_REG_T3 = MIPS_REG_11;
  public static final int MIPS_REG_12 = 13;
  public static final int MIPS_REG_T4 = MIPS_REG_12;
  public static final int MIPS_REG_13 = 14;
  public static final int MIPS_REG_T5 = MIPS_REG_13;
  public static final int MIPS_REG_14 = 15;
  public static final int MIPS_REG_T6 = MIPS_REG_14;
  public static final int MIPS_REG_15 = 16;
  public static final int MIPS_REG_T7 = MIPS_REG_15;
  public static final int MIPS_REG_16 = 17;
  public static final int MIPS_REG_S0 = MIPS_REG_16;
  public static final int MIPS_REG_17 = 18;
  public static final int MIPS_REG_S1 = MIPS_REG_17;
  public static final int MIPS_REG_18 = 19;
  public static final int MIPS_REG_S2 = MIPS_REG_18;
  public static final int MIPS_REG_19 = 20;
  public static final int MIPS_REG_S3 = MIPS_REG_19;
  public static final int MIPS_REG_20 = 21;
  public static final int MIPS_REG_S4 = MIPS_REG_20;
  public static final int MIPS_REG_21 = 22;
  public static final int MIPS_REG_S5 = MIPS_REG_21;
  public static final int MIPS_REG_22 = 23;
  public static final int MIPS_REG_S6 = MIPS_REG_22;
  public static final int MIPS_REG_23 = 24;
  public static final int MIPS_REG_S7 = MIPS_REG_23;
  public static final int MIPS_REG_24 = 25;
  public static final int MIPS_REG_T8 = MIPS_REG_24;
  public static final int MIPS_REG_25 = 26;
  public static final int MIPS_REG_T9 = MIPS_REG_25;
  public static final int MIPS_REG_26 = 27;
  public static final int MIPS_REG_K0 = MIPS_REG_26;
  public static final int MIPS_REG_27 = 28;
  public static final int MIPS_REG_K1 = MIPS_REG_27;
  public static final int MIPS_REG_28 = 29;
  public static final int MIPS_REG_GP = MIPS_REG_28;
  public static final int MIPS_REG_29 = 30;
  public static final int MIPS_REG_SP = MIPS_REG_29;
  public static final int MIPS_REG_30 = 31;
  public static final int MIPS_REG_FP = MIPS_REG_30;
  public static final int MIPS_REG_S8 = MIPS_REG_30;
  public static final int MIPS_REG_31 = 32;
  public static final int MIPS_REG_RA = MIPS_REG_31;
  public static final int MIPS_REG_DSPCCOND = 33;
  public static final int MIPS_REG_DSPCARRY = 34;
  public static final int MIPS_REG_DSPEFI = 35;
  public static final int MIPS_REG_DSPOUTFLAG = 36;
  public static final int MIPS_REG_DSPOUTFLAG16_19 = 37;
  public static final int MIPS_REG_DSPOUTFLAG20 = 38;
  public static final int MIPS_REG_DSPOUTFLAG21 = 39;
  public static final int MIPS_REG_DSPOUTFLAG22 = 40;
  public static final int MIPS_REG_DSPOUTFLAG23 = 41;
  public static final int MIPS_REG_DSPPOS = 42;
  public static final int MIPS_REG_DSPSCOUNT = 43;
  public static final int MIPS_REG_AC0 = 44;
  public static final int MIPS_REG_HI0 = MIPS_REG_AC0;
  public static final int MIPS_REG_AC1 = 45;
  public static final int MIPS_REG_HI1 = MIPS_REG_AC1;
  public static final int MIPS_REG_AC2 = 46;
  public static final int MIPS_REG_HI2 = MIPS_REG_AC2;
  public static final int MIPS_REG_AC3 = 47;
  public static final int MIPS_REG_HI3 = MIPS_REG_AC3;
  public static final int MIPS_REG_LO0 = MIPS_REG_HI0;
  public static final int MIPS_REG_LO1 = MIPS_REG_HI1;
  public static final int MIPS_REG_LO2 = MIPS_REG_HI2;
  public static final int MIPS_REG_LO3 = MIPS_REG_HI3;
  public static final int MIPS_REG_F0 = 48;
  public static final int MIPS_REG_F1 = 49;
  public static final int MIPS_REG_F2 = 50;
  public static final int MIPS_REG_F3 = 51;
  public static final int MIPS_REG_F4 = 52;
  public static final int MIPS_REG_F5 = 53;
  public static final int MIPS_REG_F6 = 54;
  public static final int MIPS_REG_F7 = 55;
  public static final int MIPS_REG_F8 = 56;
  public static final int MIPS_REG_F9 = 57;
  public static final int MIPS_REG_F10 = 58;
  public static final int MIPS_REG_F11 = 59;
  public static final int MIPS_REG_F12 = 60;
  public static final int MIPS_REG_F13 = 61;
  public static final int MIPS_REG_F14 = 62;
  public static final int MIPS_REG_F15 = 63;
  public static final int MIPS_REG_F16 = 64;
  public static final int MIPS_REG_F17 = 65;
  public static final int MIPS_REG_F18 = 66;
  public static final int MIPS_REG_F19 = 67;
  public static final int MIPS_REG_F20 = 68;
  public static final int MIPS_REG_F21 = 69;
  public static final int MIPS_REG_F22 = 70;
  public static final int MIPS_REG_F23 = 71;
  public static final int MIPS_REG_F24 = 72;
  public static final int MIPS_REG_F25 = 73;
  public static final int MIPS_REG_F26 = 74;
  public static final int MIPS_REG_F27 = 75;
  public static final int MIPS_REG_F28 = 76;
  public static final int MIPS_REG_F29 = 77;
  public static final int MIPS_REG_F30 = 78;
  public static final int MIPS_REG_F31 = 79;
  public static final int MIPS_REG_FCC0 = 80;
  public static final int MIPS_REG_FCC1 = 81;
  public static final int MIPS_REG_FCC2 = 82;
  public static final int MIPS_REG_FCC3 = 83;
  public static final int MIPS_REG_FCC4 = 84;
  public static final int MIPS_REG_FCC5 = 85;
  public static final int MIPS_REG_FCC6 = 86;
  public static final int MIPS_REG_FCC7 = 87;
  public static final int MIPS_REG_W0 = 88;
  public static final int MIPS_REG_W1 = 89;
  public static final int MIPS_REG_W2 = 90;
  public static final int MIPS_REG_W3 = 91;
  public static final int MIPS_REG_W4 = 92;
  public static final int MIPS_REG_W5 = 93;
  public static final int MIPS_REG_W6 = 94;
  public static final int MIPS_REG_W7 = 95;
  public static final int MIPS_REG_W8 = 96;
  public static final int MIPS_REG_W9 = 97;
  public static final int MIPS_REG_W10 = 98;
  public static final int MIPS_REG_W11 = 99;
  public static final int MIPS_REG_W12 = 100;
  public static final int MIPS_REG_W13 = 101;
  public static final int MIPS_REG_W14 = 102;
  public static final int MIPS_REG_W15 = 103;
  public static final int MIPS_REG_W16 = 104;
  public static final int MIPS_REG_W17 = 105;
  public static final int MIPS_REG_W18 = 106;
  public static final int MIPS_REG_W19 = 107;
  public static final int MIPS_REG_W20 = 108;
  public static final int MIPS_REG_W21 = 109;
  public static final int MIPS_REG_W22 = 110;
  public static final int MIPS_REG_W23 = 111;
  public static final int MIPS_REG_W24 = 112;
  public static final int MIPS_REG_W25 = 113;
  public static final int MIPS_REG_W26 = 114;
  public static final int MIPS_REG_W27 = 115;
  public static final int MIPS_REG_W28 = 116;
  public static final int MIPS_REG_W29 = 117;
  public static final int MIPS_REG_W30 = 118;
  public static final int MIPS_REG_W31 = 119;
  public static final int MIPS_REG_MAX = 120;

  // MIPS instructions
  public static final int MIPS_INS_INVALID = 0;
  public static final int MIPS_INS_ABSQ_S = 1;
  public static final int MIPS_INS_ADD = 2;
  public static final int MIPS_INS_ADDQH = 3;
  public static final int MIPS_INS_ADDQH_R = 4;
  public static final int MIPS_INS_ADDQ = 5;
  public static final int MIPS_INS_ADDQ_S = 6;
  public static final int MIPS_INS_ADDSC = 7;
  public static final int MIPS_INS_ADDS_A = 8;
  public static final int MIPS_INS_ADDS_S = 9;
  public static final int MIPS_INS_ADDS_U = 10;
  public static final int MIPS_INS_ADDUH = 11;
  public static final int MIPS_INS_ADDUH_R = 12;
  public static final int MIPS_INS_ADDU = 13;
  public static final int MIPS_INS_ADDU_S = 14;
  public static final int MIPS_INS_ADDVI = 15;
  public static final int MIPS_INS_ADDV = 16;
  public static final int MIPS_INS_ADDWC = 17;
  public static final int MIPS_INS_ADD_A = 18;
  public static final int MIPS_INS_ADDI = 19;
  public static final int MIPS_INS_ADDIU = 20;
  public static final int MIPS_INS_AND = 21;
  public static final int MIPS_INS_ANDI = 22;
  public static final int MIPS_INS_APPEND = 23;
  public static final int MIPS_INS_ASUB_S = 24;
  public static final int MIPS_INS_ASUB_U = 25;
  public static final int MIPS_INS_AVER_S = 26;
  public static final int MIPS_INS_AVER_U = 27;
  public static final int MIPS_INS_AVE_S = 28;
  public static final int MIPS_INS_AVE_U = 29;
  public static final int MIPS_INS_BALIGN = 30;
  public static final int MIPS_INS_BC1F = 31;
  public static final int MIPS_INS_BC1T = 32;
  public static final int MIPS_INS_BCLRI = 33;
  public static final int MIPS_INS_BCLR = 34;
  public static final int MIPS_INS_BEQ = 35;
  public static final int MIPS_INS_BGEZ = 36;
  public static final int MIPS_INS_BGEZAL = 37;
  public static final int MIPS_INS_BGTZ = 38;
  public static final int MIPS_INS_BINSLI = 39;
  public static final int MIPS_INS_BINSL = 40;
  public static final int MIPS_INS_BINSRI = 41;
  public static final int MIPS_INS_BINSR = 42;
  public static final int MIPS_INS_BITREV = 43;
  public static final int MIPS_INS_BLEZ = 44;
  public static final int MIPS_INS_BLTZ = 45;
  public static final int MIPS_INS_BLTZAL = 46;
  public static final int MIPS_INS_BMNZI = 47;
  public static final int MIPS_INS_BMNZ = 48;
  public static final int MIPS_INS_BMZI = 49;
  public static final int MIPS_INS_BMZ = 50;
  public static final int MIPS_INS_BNE = 51;
  public static final int MIPS_INS_BNEGI = 52;
  public static final int MIPS_INS_BNEG = 53;
  public static final int MIPS_INS_BNZ = 54;
  public static final int MIPS_INS_BPOSGE32 = 55;
  public static final int MIPS_INS_BREAK = 56;
  public static final int MIPS_INS_BSELI = 57;
  public static final int MIPS_INS_BSEL = 58;
  public static final int MIPS_INS_BSETI = 59;
  public static final int MIPS_INS_BSET = 60;
  public static final int MIPS_INS_BZ = 61;
  public static final int MIPS_INS_BEQZ = 62;
  public static final int MIPS_INS_B = 63;
  public static final int MIPS_INS_BNEZ = 64;
  public static final int MIPS_INS_BTEQZ = 65;
  public static final int MIPS_INS_BTNEZ = 66;
  public static final int MIPS_INS_CEIL = 67;
  public static final int MIPS_INS_CEQI = 68;
  public static final int MIPS_INS_CEQ = 69;
  public static final int MIPS_INS_CFC1 = 70;
  public static final int MIPS_INS_CFCMSA = 71;
  public static final int MIPS_INS_CLEI_S = 72;
  public static final int MIPS_INS_CLEI_U = 73;
  public static final int MIPS_INS_CLE_S = 74;
  public static final int MIPS_INS_CLE_U = 75;
  public static final int MIPS_INS_CLO = 76;
  public static final int MIPS_INS_CLTI_S = 77;
  public static final int MIPS_INS_CLTI_U = 78;
  public static final int MIPS_INS_CLT_S = 79;
  public static final int MIPS_INS_CLT_U = 80;
  public static final int MIPS_INS_CLZ = 81;
  public static final int MIPS_INS_CMPGDU = 82;
  public static final int MIPS_INS_CMPGU = 83;
  public static final int MIPS_INS_CMPU = 84;
  public static final int MIPS_INS_CMP = 85;
  public static final int MIPS_INS_COPY_S = 86;
  public static final int MIPS_INS_COPY_U = 87;
  public static final int MIPS_INS_CTC1 = 88;
  public static final int MIPS_INS_CTCMSA = 89;
  public static final int MIPS_INS_CVT = 90;
  public static final int MIPS_INS_C = 91;
  public static final int MIPS_INS_CMPI = 92;
  public static final int MIPS_INS_DADD = 93;
  public static final int MIPS_INS_DADDI = 94;
  public static final int MIPS_INS_DADDIU = 95;
  public static final int MIPS_INS_DADDU = 96;
  public static final int MIPS_INS_DCLO = 97;
  public static final int MIPS_INS_DCLZ = 98;
  public static final int MIPS_INS_DERET = 99;
  public static final int MIPS_INS_DEXT = 100;
  public static final int MIPS_INS_DEXTM = 101;
  public static final int MIPS_INS_DEXTU = 102;
  public static final int MIPS_INS_DI = 103;
  public static final int MIPS_INS_DINS = 104;
  public static final int MIPS_INS_DINSM = 105;
  public static final int MIPS_INS_DINSU = 106;
  public static final int MIPS_INS_DIV_S = 107;
  public static final int MIPS_INS_DIV_U = 108;
  public static final int MIPS_INS_DMFC0 = 109;
  public static final int MIPS_INS_DMFC1 = 110;
  public static final int MIPS_INS_DMFC2 = 111;
  public static final int MIPS_INS_DMTC0 = 112;
  public static final int MIPS_INS_DMTC1 = 113;
  public static final int MIPS_INS_DMTC2 = 114;
  public static final int MIPS_INS_DMULT = 115;
  public static final int MIPS_INS_DMULTU = 116;
  public static final int MIPS_INS_DOTP_S = 117;
  public static final int MIPS_INS_DOTP_U = 118;
  public static final int MIPS_INS_DPADD_S = 119;
  public static final int MIPS_INS_DPADD_U = 120;
  public static final int MIPS_INS_DPAQX_SA = 121;
  public static final int MIPS_INS_DPAQX_S = 122;
  public static final int MIPS_INS_DPAQ_SA = 123;
  public static final int MIPS_INS_DPAQ_S = 124;
  public static final int MIPS_INS_DPAU = 125;
  public static final int MIPS_INS_DPAX = 126;
  public static final int MIPS_INS_DPA = 127;
  public static final int MIPS_INS_DPSQX_SA = 128;
  public static final int MIPS_INS_DPSQX_S = 129;
  public static final int MIPS_INS_DPSQ_SA = 130;
  public static final int MIPS_INS_DPSQ_S = 131;
  public static final int MIPS_INS_DPSUB_S = 132;
  public static final int MIPS_INS_DPSUB_U = 133;
  public static final int MIPS_INS_DPSU = 134;
  public static final int MIPS_INS_DPSX = 135;
  public static final int MIPS_INS_DPS = 136;
  public static final int MIPS_INS_DROTR = 137;
  public static final int MIPS_INS_DROTR32 = 138;
  public static final int MIPS_INS_DROTRV = 139;
  public static final int MIPS_INS_DSBH = 140;
  public static final int MIPS_INS_DDIV = 141;
  public static final int MIPS_INS_DSHD = 142;
  public static final int MIPS_INS_DSLL = 143;
  public static final int MIPS_INS_DSLL32 = 144;
  public static final int MIPS_INS_DSLLV = 145;
  public static final int MIPS_INS_DSRA = 146;
  public static final int MIPS_INS_DSRA32 = 147;
  public static final int MIPS_INS_DSRAV = 148;
  public static final int MIPS_INS_DSRL = 149;
  public static final int MIPS_INS_DSRL32 = 150;
  public static final int MIPS_INS_DSRLV = 151;
  public static final int MIPS_INS_DSUBU = 152;
  public static final int MIPS_INS_DDIVU = 153;
  public static final int MIPS_INS_DIV = 154;
  public static final int MIPS_INS_DIVU = 155;
  public static final int MIPS_INS_EI = 156;
  public static final int MIPS_INS_ERET = 157;
  public static final int MIPS_INS_EXT = 158;
  public static final int MIPS_INS_EXTP = 159;
  public static final int MIPS_INS_EXTPDP = 160;
  public static final int MIPS_INS_EXTPDPV = 161;
  public static final int MIPS_INS_EXTPV = 162;
  public static final int MIPS_INS_EXTRV_RS = 163;
  public static final int MIPS_INS_EXTRV_R = 164;
  public static final int MIPS_INS_EXTRV_S = 165;
  public static final int MIPS_INS_EXTRV = 166;
  public static final int MIPS_INS_EXTR_RS = 167;
  public static final int MIPS_INS_EXTR_R = 168;
  public static final int MIPS_INS_EXTR_S = 169;
  public static final int MIPS_INS_EXTR = 170;
  public static final int MIPS_INS_ABS = 171;
  public static final int MIPS_INS_FADD = 172;
  public static final int MIPS_INS_FCAF = 173;
  public static final int MIPS_INS_FCEQ = 174;
  public static final int MIPS_INS_FCLASS = 175;
  public static final int MIPS_INS_FCLE = 176;
  public static final int MIPS_INS_FCLT = 177;
  public static final int MIPS_INS_FCNE = 178;
  public static final int MIPS_INS_FCOR = 179;
  public static final int MIPS_INS_FCUEQ = 180;
  public static final int MIPS_INS_FCULE = 181;
  public static final int MIPS_INS_FCULT = 182;
  public static final int MIPS_INS_FCUNE = 183;
  public static final int MIPS_INS_FCUN = 184;
  public static final int MIPS_INS_FDIV = 185;
  public static final int MIPS_INS_FEXDO = 186;
  public static final int MIPS_INS_FEXP2 = 187;
  public static final int MIPS_INS_FEXUPL = 188;
  public static final int MIPS_INS_FEXUPR = 189;
  public static final int MIPS_INS_FFINT_S = 190;
  public static final int MIPS_INS_FFINT_U = 191;
  public static final int MIPS_INS_FFQL = 192;
  public static final int MIPS_INS_FFQR = 193;
  public static final int MIPS_INS_FILL = 194;
  public static final int MIPS_INS_FLOG2 = 195;
  public static final int MIPS_INS_FLOOR = 196;
  public static final int MIPS_INS_FMADD = 197;
  public static final int MIPS_INS_FMAX_A = 198;
  public static final int MIPS_INS_FMAX = 199;
  public static final int MIPS_INS_FMIN_A = 200;
  public static final int MIPS_INS_FMIN = 201;
  public static final int MIPS_INS_MOV = 202;
  public static final int MIPS_INS_FMSUB = 203;
  public static final int MIPS_INS_FMUL = 204;
  public static final int MIPS_INS_MUL = 205;
  public static final int MIPS_INS_NEG = 206;
  public static final int MIPS_INS_FRCP = 207;
  public static final int MIPS_INS_FRINT = 208;
  public static final int MIPS_INS_FRSQRT = 209;
  public static final int MIPS_INS_FSAF = 210;
  public static final int MIPS_INS_FSEQ = 211;
  public static final int MIPS_INS_FSLE = 212;
  public static final int MIPS_INS_FSLT = 213;
  public static final int MIPS_INS_FSNE = 214;
  public static final int MIPS_INS_FSOR = 215;
  public static final int MIPS_INS_FSQRT = 216;
  public static final int MIPS_INS_SQRT = 217;
  public static final int MIPS_INS_FSUB = 218;
  public static final int MIPS_INS_SUB = 219;
  public static final int MIPS_INS_FSUEQ = 220;
  public static final int MIPS_INS_FSULE = 221;
  public static final int MIPS_INS_FSULT = 222;
  public static final int MIPS_INS_FSUNE = 223;
  public static final int MIPS_INS_FSUN = 224;
  public static final int MIPS_INS_FTINT_S = 225;
  public static final int MIPS_INS_FTINT_U = 226;
  public static final int MIPS_INS_FTQ = 227;
  public static final int MIPS_INS_FTRUNC_S = 228;
  public static final int MIPS_INS_FTRUNC_U = 229;
  public static final int MIPS_INS_HADD_S = 230;
  public static final int MIPS_INS_HADD_U = 231;
  public static final int MIPS_INS_HSUB_S = 232;
  public static final int MIPS_INS_HSUB_U = 233;
  public static final int MIPS_INS_ILVEV = 234;
  public static final int MIPS_INS_ILVL = 235;
  public static final int MIPS_INS_ILVOD = 236;
  public static final int MIPS_INS_ILVR = 237;
  public static final int MIPS_INS_INS = 238;
  public static final int MIPS_INS_INSERT = 239;
  public static final int MIPS_INS_INSV = 240;
  public static final int MIPS_INS_INSVE = 241;
  public static final int MIPS_INS_J = 242;
  public static final int MIPS_INS_JAL = 243;
  public static final int MIPS_INS_JALR = 244;
  public static final int MIPS_INS_JR = 245;
  public static final int MIPS_INS_JRC = 246;
  public static final int MIPS_INS_JALRC = 247;
  public static final int MIPS_INS_LB = 248;
  public static final int MIPS_INS_LBUX = 249;
  public static final int MIPS_INS_LBU = 250;
  public static final int MIPS_INS_LD = 251;
  public static final int MIPS_INS_LDC1 = 252;
  public static final int MIPS_INS_LDC2 = 253;
  public static final int MIPS_INS_LDI = 254;
  public static final int MIPS_INS_LDL = 255;
  public static final int MIPS_INS_LDR = 256;
  public static final int MIPS_INS_LDXC1 = 257;
  public static final int MIPS_INS_LH = 258;
  public static final int MIPS_INS_LHX = 259;
  public static final int MIPS_INS_LHU = 260;
  public static final int MIPS_INS_LL = 261;
  public static final int MIPS_INS_LLD = 262;
  public static final int MIPS_INS_LSA = 263;
  public static final int MIPS_INS_LUXC1 = 264;
  public static final int MIPS_INS_LUI = 265;
  public static final int MIPS_INS_LW = 266;
  public static final int MIPS_INS_LWC1 = 267;
  public static final int MIPS_INS_LWC2 = 268;
  public static final int MIPS_INS_LWL = 269;
  public static final int MIPS_INS_LWR = 270;
  public static final int MIPS_INS_LWX = 271;
  public static final int MIPS_INS_LWXC1 = 272;
  public static final int MIPS_INS_LWU = 273;
  public static final int MIPS_INS_LI = 274;
  public static final int MIPS_INS_MADD = 275;
  public static final int MIPS_INS_MADDR_Q = 276;
  public static final int MIPS_INS_MADDU = 277;
  public static final int MIPS_INS_MADDV = 278;
  public static final int MIPS_INS_MADD_Q = 279;
  public static final int MIPS_INS_MAQ_SA = 280;
  public static final int MIPS_INS_MAQ_S = 281;
  public static final int MIPS_INS_MAXI_S = 282;
  public static final int MIPS_INS_MAXI_U = 283;
  public static final int MIPS_INS_MAX_A = 284;
  public static final int MIPS_INS_MAX_S = 285;
  public static final int MIPS_INS_MAX_U = 286;
  public static final int MIPS_INS_MFC0 = 287;
  public static final int MIPS_INS_MFC1 = 288;
  public static final int MIPS_INS_MFC2 = 289;
  public static final int MIPS_INS_MFHC1 = 290;
  public static final int MIPS_INS_MFHI = 291;
  public static final int MIPS_INS_MFLO = 292;
  public static final int MIPS_INS_MINI_S = 293;
  public static final int MIPS_INS_MINI_U = 294;
  public static final int MIPS_INS_MIN_A = 295;
  public static final int MIPS_INS_MIN_S = 296;
  public static final int MIPS_INS_MIN_U = 297;
  public static final int MIPS_INS_MODSUB = 298;
  public static final int MIPS_INS_MOD_S = 299;
  public static final int MIPS_INS_MOD_U = 300;
  public static final int MIPS_INS_MOVE = 301;
  public static final int MIPS_INS_MOVF = 302;
  public static final int MIPS_INS_MOVN = 303;
  public static final int MIPS_INS_MOVT = 304;
  public static final int MIPS_INS_MOVZ = 305;
  public static final int MIPS_INS_MSUB = 306;
  public static final int MIPS_INS_MSUBR_Q = 307;
  public static final int MIPS_INS_MSUBU = 308;
  public static final int MIPS_INS_MSUBV = 309;
  public static final int MIPS_INS_MSUB_Q = 310;
  public static final int MIPS_INS_MTC0 = 311;
  public static final int MIPS_INS_MTC1 = 312;
  public static final int MIPS_INS_MTC2 = 313;
  public static final int MIPS_INS_MTHC1 = 314;
  public static final int MIPS_INS_MTHI = 315;
  public static final int MIPS_INS_MTHLIP = 316;
  public static final int MIPS_INS_MTLO = 317;
  public static final int MIPS_INS_MULEQ_S = 318;
  public static final int MIPS_INS_MULEU_S = 319;
  public static final int MIPS_INS_MULQ_RS = 320;
  public static final int MIPS_INS_MULQ_S = 321;
  public static final int MIPS_INS_MULR_Q = 322;
  public static final int MIPS_INS_MULSAQ_S = 323;
  public static final int MIPS_INS_MULSA = 324;
  public static final int MIPS_INS_MULT = 325;
  public static final int MIPS_INS_MULTU = 326;
  public static final int MIPS_INS_MULV = 327;
  public static final int MIPS_INS_MUL_Q = 328;
  public static final int MIPS_INS_MUL_S = 329;
  public static final int MIPS_INS_NLOC = 330;
  public static final int MIPS_INS_NLZC = 331;
  public static final int MIPS_INS_NMADD = 332;
  public static final int MIPS_INS_NMSUB = 333;
  public static final int MIPS_INS_NOR = 334;
  public static final int MIPS_INS_NORI = 335;
  public static final int MIPS_INS_NOT = 336;
  public static final int MIPS_INS_OR = 337;
  public static final int MIPS_INS_ORI = 338;
  public static final int MIPS_INS_PACKRL = 339;
  public static final int MIPS_INS_PCKEV = 340;
  public static final int MIPS_INS_PCKOD = 341;
  public static final int MIPS_INS_PCNT = 342;
  public static final int MIPS_INS_PICK = 343;
  public static final int MIPS_INS_PRECEQU = 344;
  public static final int MIPS_INS_PRECEQ = 345;
  public static final int MIPS_INS_PRECEU = 346;
  public static final int MIPS_INS_PRECRQU_S = 347;
  public static final int MIPS_INS_PRECRQ = 348;
  public static final int MIPS_INS_PRECRQ_RS = 349;
  public static final int MIPS_INS_PRECR = 350;
  public static final int MIPS_INS_PRECR_SRA = 351;
  public static final int MIPS_INS_PRECR_SRA_R = 352;
  public static final int MIPS_INS_PREPEND = 353;
  public static final int MIPS_INS_RADDU = 354;
  public static final int MIPS_INS_RDDSP = 355;
  public static final int MIPS_INS_RDHWR = 356;
  public static final int MIPS_INS_REPLV = 357;
  public static final int MIPS_INS_REPL = 358;
  public static final int MIPS_INS_ROTR = 359;
  public static final int MIPS_INS_ROTRV = 360;
  public static final int MIPS_INS_ROUND = 361;
  public static final int MIPS_INS_RESTORE = 362;
  public static final int MIPS_INS_SAT_S = 363;
  public static final int MIPS_INS_SAT_U = 364;
  public static final int MIPS_INS_SB = 365;
  public static final int MIPS_INS_SC = 366;
  public static final int MIPS_INS_SCD = 367;
  public static final int MIPS_INS_SD = 368;
  public static final int MIPS_INS_SDC1 = 369;
  public static final int MIPS_INS_SDC2 = 370;
  public static final int MIPS_INS_SDL = 371;
  public static final int MIPS_INS_SDR = 372;
  public static final int MIPS_INS_SDXC1 = 373;
  public static final int MIPS_INS_SEB = 374;
  public static final int MIPS_INS_SEH = 375;
  public static final int MIPS_INS_SH = 376;
  public static final int MIPS_INS_SHF = 377;
  public static final int MIPS_INS_SHILO = 378;
  public static final int MIPS_INS_SHILOV = 379;
  public static final int MIPS_INS_SHLLV = 380;
  public static final int MIPS_INS_SHLLV_S = 381;
  public static final int MIPS_INS_SHLL = 382;
  public static final int MIPS_INS_SHLL_S = 383;
  public static final int MIPS_INS_SHRAV = 384;
  public static final int MIPS_INS_SHRAV_R = 385;
  public static final int MIPS_INS_SHRA = 386;
  public static final int MIPS_INS_SHRA_R = 387;
  public static final int MIPS_INS_SHRLV = 388;
  public static final int MIPS_INS_SHRL = 389;
  public static final int MIPS_INS_SLDI = 390;
  public static final int MIPS_INS_SLD = 391;
  public static final int MIPS_INS_SLL = 392;
  public static final int MIPS_INS_SLLI = 393;
  public static final int MIPS_INS_SLLV = 394;
  public static final int MIPS_INS_SLT = 395;
  public static final int MIPS_INS_SLTI = 396;
  public static final int MIPS_INS_SLTIU = 397;
  public static final int MIPS_INS_SLTU = 398;
  public static final int MIPS_INS_SPLATI = 399;
  public static final int MIPS_INS_SPLAT = 400;
  public static final int MIPS_INS_SRA = 401;
  public static final int MIPS_INS_SRAI = 402;
  public static final int MIPS_INS_SRARI = 403;
  public static final int MIPS_INS_SRAR = 404;
  public static final int MIPS_INS_SRAV = 405;
  public static final int MIPS_INS_SRL = 406;
  public static final int MIPS_INS_SRLI = 407;
  public static final int MIPS_INS_SRLRI = 408;
  public static final int MIPS_INS_SRLR = 409;
  public static final int MIPS_INS_SRLV = 410;
  public static final int MIPS_INS_ST = 411;
  public static final int MIPS_INS_SUBQH = 412;
  public static final int MIPS_INS_SUBQH_R = 413;
  public static final int MIPS_INS_SUBQ = 414;
  public static final int MIPS_INS_SUBQ_S = 415;
  public static final int MIPS_INS_SUBSUS_U = 416;
  public static final int MIPS_INS_SUBSUU_S = 417;
  public static final int MIPS_INS_SUBS_S = 418;
  public static final int MIPS_INS_SUBS_U = 419;
  public static final int MIPS_INS_SUBUH = 420;
  public static final int MIPS_INS_SUBUH_R = 421;
  public static final int MIPS_INS_SUBU = 422;
  public static final int MIPS_INS_SUBU_S = 423;
  public static final int MIPS_INS_SUBVI = 424;
  public static final int MIPS_INS_SUBV = 425;
  public static final int MIPS_INS_SUXC1 = 426;
  public static final int MIPS_INS_SW = 427;
  public static final int MIPS_INS_SWC1 = 428;
  public static final int MIPS_INS_SWC2 = 429;
  public static final int MIPS_INS_SWL = 430;
  public static final int MIPS_INS_SWR = 431;
  public static final int MIPS_INS_SWXC1 = 432;
  public static final int MIPS_INS_SYNC = 433;
  public static final int MIPS_INS_SYSCALL = 434;
  public static final int MIPS_INS_SAVE = 435;
  public static final int MIPS_INS_TEQ = 436;
  public static final int MIPS_INS_TEQI = 437;
  public static final int MIPS_INS_TGE = 438;
  public static final int MIPS_INS_TGEI = 439;
  public static final int MIPS_INS_TGEIU = 440;
  public static final int MIPS_INS_TGEU = 441;
  public static final int MIPS_INS_TLT = 442;
  public static final int MIPS_INS_TLTI = 443;
  public static final int MIPS_INS_TLTU = 444;
  public static final int MIPS_INS_TNE = 445;
  public static final int MIPS_INS_TNEI = 446;
  public static final int MIPS_INS_TRUNC = 447;
  public static final int MIPS_INS_TLTIU = 448;
  public static final int MIPS_INS_VSHF = 449;
  public static final int MIPS_INS_WAIT = 450;
  public static final int MIPS_INS_WRDSP = 451;
  public static final int MIPS_INS_WSBH = 452;
  public static final int MIPS_INS_XOR = 453;
  public static final int MIPS_INS_XORI = 454;
  public static final int MIPS_INS_NOP = 455;
  public static final int MIPS_INS_MAX = 456;

  // MIPS group of instructions
  public static final int MIPS_GRP_INVALID = 0;
  public static final int MIPS_GRP_BITCOUNT = 1;
  public static final int MIPS_GRP_DSP = 2;
  public static final int MIPS_GRP_DSPR2 = 3;
  public static final int MIPS_GRP_FPIDX = 4;
  public static final int MIPS_GRP_MSA = 5;
  public static final int MIPS_GRP_MIPS32R2 = 6;
  public static final int MIPS_GRP_MIPS64 = 7;
  public static final int MIPS_GRP_MIPS64R2 = 8;
  public static final int MIPS_GRP_SEINREG = 9;
  public static final int MIPS_GRP_STDENC = 10;
  public static final int MIPS_GRP_SWAP = 11;
  public static final int MIPS_GRP_MICROMIPS = 12;
  public static final int MIPS_GRP_MIPS16MODE = 13;
  public static final int MIPS_GRP_FP64BIT = 14;
  public static final int MIPS_GRP_NONANSFPMATH = 15;
  public static final int MIPS_GRP_NOTFP64BIT = 16;
  public static final int MIPS_GRP_RELOCSTATIC = 17;
  public static final int MIPS_GRP_MAX = 18;

}
