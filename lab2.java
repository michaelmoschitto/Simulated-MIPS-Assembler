import java.lang.System;
import java.util.Scanner;

// import sun.security.util.ArrayUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Hashtable;

public class lab2 {
    public static void main(String args[]){
        String line;
        String split[];
        ArrayList<String[]> instructionList = new ArrayList<String[]>();
        Hashtable<String, Integer> labelDict = new Hashtable<String, Integer>();
        Hashtable<String, String> opcodeDict = new Hashtable<String, String>();
        Hashtable<String, String> functionDict = new Hashtable<String, String>();
        Hashtable<String, String> typeDict = new Hashtable<String, String>();
        Hashtable<String, String> registerDict = new Hashtable<String, String>();

        // and , or, add, addi, sll, sub, slt, beq, bne, lw, sw, j, jr, and jal
        fillOpcodeDict(opcodeDict);
        fillFunctionDict(functionDict);
        fillTypeDict(typeDict);
        fillRegisterDict(registerDict);


        try{
            String file = args[0];
            // System.out.println('\n' + file + '\n');
            Scanner scanner = new Scanner(new File(file));

            
                while(scanner.hasNextLine()){
                    line = scanner.nextLine();
                    // System.out.println(line);

                    split = cleanAndSplitLine(line);
                    split = cleanComments(split);
                    // split = deleteEmptyElements(split);
                    split = deleteEndComments(split);
                    split = deleteEmptyElements(split);
                    if(split != null){
                        instructionList.add(split);
                    }
                }
                
                

                fillLabelDict(instructionList,labelDict);

                // int LineNum = 0;
                // System.out.println("Instruction List:");
                // for (int j = 0; j < instructionList.size(); j++) {
                //     System.out.println("Line: " + LineNum + Arrays.deepToString(instructionList.get(j)));

                //     if (instructionList.get(j).length > 1) {
                //         LineNum++;
                //     }
                // }

                // System.out.println("Labels: " + labelDict.toString());
                
                // System.out.println("\n"+ "OpcodeDict: " + opcodeDict.toString() + "\n");
                // System.out.println("FunctionDict: " + functionDict.toString());
                // System.out.println("\n"+"TypeDict: " + typeDict.toString());
                // System.out.println("\n" + "RegisterDict: " + registerDict.toString());
                // System.out.println("Imm to String: " + immToString("-10"));
                // System.out.println("Get Type: " + getopcodeType("sub", typeDict));

                // shamtToString("-3");
                printBinary(instructionList, labelDict, opcodeDict, functionDict, typeDict, registerDict);

            //     private static void printBinary(ArrayList<String[]> instructionList, Hashtable<String, String> labelDict,
            // Hashtable<String, String> opcodeDict, Hashtable<String, String> functionDict,
            // Hashtable<String, String> typeDict, Hashtable<String, String> registerDict) {

                // printBinary(instructionList, labelDict, opcodeDict, functionDict, typeDict, registerDict);
                // System.out.println("Reg to String: " + regToString("t3",registerDict));
                // System.out.println(String.format("%32s", Integer.toBinaryString((64))).replace(' ', '0'));
                

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        private static void fillOpcodeDict(Hashtable <String,String> opcodeDict){
            
                // opcodes are 6 bits
                opcodeDict.put("and","000000");
                opcodeDict.put("or","000000"); 
                opcodeDict.put("add", "000000"); 
                opcodeDict.put("addi", "001000"); 
                opcodeDict.put("sll", "000000"); 
                opcodeDict.put("sub", "000000"); 
                opcodeDict.put("slt", "000000"); 
                opcodeDict.put("beq","000100");
                opcodeDict.put("bne","000101");
                opcodeDict.put("lw","100011");
                opcodeDict.put("sw","101011");
                opcodeDict.put("j","000010");
                opcodeDict.put("jr","000000");
                opcodeDict.put("jal","000011");
            
        }

        private static void fillFunctionDict(Hashtable<String,String> functionDict){
                // opcodes are 6 bits
                functionDict.put("and","100100");
                functionDict.put("or","100101"); 
                functionDict.put("add", "100000");
                // functionDict.put("addi", null); 
                functionDict.put("sll", "000000"); 
                functionDict.put("sub", "100010"); 
                functionDict.put("slt", "101010"); 
                // functionDict.put("beq",null);
                // functionDict.put("bne",null);
                // functionDict.put("lw",null);
                // functionDict.put("sw",null);
                // functionDict.put("j",null);
                functionDict.put("jr","001000");
                // functionDict.put("jal",null);
            
        }

        private static void fillTypeDict(Hashtable<String, String> typeDict) {
            // opcodes are 6 bits
            typeDict.put("and", "R");
            typeDict.put("or", "R");
            typeDict.put("add", "R");
            typeDict.put("jr", "R");
            typeDict.put("sll", "R");
            typeDict.put("sub", "R");
            typeDict.put("slt", "R");
            typeDict.put("addi", "I");
            typeDict.put("beq","I");
            typeDict.put("bne","I");
            typeDict.put("lw","I");
            typeDict.put("sw","I");
            typeDict.put("j","J");
            typeDict.put("jal","J");

        }

        private static void fillRegisterDict(Hashtable<String,String> registerDict){
            registerDict.put("0","00000");
            registerDict.put("zero", "00000");

            registerDict.put("r0", "00000");
            // registerDict.put("at", "00001");
            registerDict.put("v0", "00010");
            registerDict.put("v1", "00011");
            registerDict.put("a0", "00100");
            registerDict.put("a1","00101");
            registerDict.put("a2","00110");
            registerDict.put("a3","00111");
            registerDict.put("t0","01000");
            registerDict.put("t1","01001");
            registerDict.put("t2","01010");
            registerDict.put("t3", "01011");
            registerDict.put("t4", "01100");
            registerDict.put("t5", "01101");
            registerDict.put("t6", "01110");
            registerDict.put("t7", "01111");
            registerDict.put("s0", "10000");
            registerDict.put("s1", "10001");
            registerDict.put("s2","10010");
            registerDict.put("s3", "10011");
            registerDict.put("s4", "10100");
            registerDict.put("s5", "10101");
            registerDict.put("s6", "10110");
            registerDict.put("s7","10111");
            registerDict.put("t8","11000");
            registerDict.put("t9","11001");
            // registerDict.put("k0","11010");
            // registerDict.put("k1","11011");
            // registerDict.put("gp","11100");
            registerDict.put("sp", "11101");
            registerDict.put("s8", "11110");
            registerDict.put("ra", "11111");

        }

        private static String[] cleanAndSplitLine(String l) {
        String split[];
        l = l.trim();
        // if(l.charAt(0) =='#'){
        //     return null;
        // }
        // l = l.replace(","," ");
        l = l.replace(":",": ");
        l = l.replace("(","");
        l = l.replace(")","");


        split = l.split("[ ,$]");

        for(int i = 0; i < (split.length); i++){
            // split[i] = split[i].replace(',',' ');
            split[i] = split[i].trim();
        }

        return split;
    }

    private static String[] cleanComments(String[] s){
        if(s.length == 0 || s[0].equals("") || (s[0].equals("#")) || s[0].charAt(0) == '#'){
            return null;
        }else{
            return s;
        }
    }

    private static String[] deleteEmptyElements(String[] s){
        ArrayList<String> newList = new ArrayList<String>();

        if(s == null){
            return null;
        }else{
            for(int i = 0; i < s.length; i++){
                if((!s[i].equals(""))){
                    newList.add(s[i]);
                }
            }
            Object[] objArr = newList.toArray(); 
            String[] str = Arrays.copyOf(objArr, objArr.length, String[].class);
            return str;
        }
    }

    private static String[] deleteEndComments(String[] s){
        ArrayList<String> newList = new ArrayList<String>();
        if(s == null){
            return null;
        }else{
            for(int i = 0; i < s.length; i++){
                for(int j = 0; j<s[i].length();j++){
                    if(s[i].charAt(j) == '#'){
                        s[i] = s[i].substring(0,j).trim();
                        newList.add(s[i]);
                        Object[] objArr = newList.toArray(); 
                        String[] str = Arrays.copyOf(objArr, objArr.length, String[].class);
                        return str;
                    }
                }
                newList.add(s[i]);
            }
            Object[] objArr = newList.toArray(); 
                        String[] str = Arrays.copyOf(objArr, objArr.length, String[].class);
                        return str;

        }
    }

    private static void fillLabelDict(ArrayList<String[]> a, Hashtable<String, Integer> ht){
        int ln = 0;
        for(int i = 0; i<a.size();i++){
            String[] line = a.get(i);
            String arg0 = line[0];
            if(arg0.contains(":")){
                String label = arg0.substring(0,arg0.indexOf(":"));
                String[] newstr = removeTheElement(line, 0);
                a.remove(i);
                a.add(i, newstr);
                ht.put(label,ln);
            }

            if(line.length > 1){
                ln++;
            }
        }

        for(int j = 0; j<a.size();j++){
            if(a.get(j).length == 0){
                a.remove(j);
            }
        }
    }

    private static String[] removeTheElement(String[] arr, int index) {
        String[] arr2 = new String[arr.length - 1];
        for (int i = 0, k = 0; i < arr.length; i++) {
            if (i == index) {
                continue;
            }
            arr2[k++] = arr[i];
        }
        return arr2;
    }

    private static String shamtToString(String s){
        int amt = Integer.parseInt(s);

        return String.format("%5s", Integer.toBinaryString((amt))).replace(' ', '0');
    }

    

    private static String immToString(String s){
        int imm = Integer.parseInt(s);

        if(imm >= 0){
            return String.format("%16s", Integer.toBinaryString((imm))).replace(' ','0');

        }else{
            return String.format("%16s", Integer.toBinaryString((imm))).replace(' ', '0').substring(16);
        }

    }

    private static String addrToString(Integer addr){
        

        return String.format("%26s", Integer.toBinaryString((addr))).replace(' ', '0');
    }

    private static String getopcodeType(String s, Hashtable<String,String> ht){
        return ht.get(s);
    }

    private static void printBinary(ArrayList<String[]> instructionList, Hashtable<String,Integer> labelDict, Hashtable<String,String> opcodeDict, Hashtable<String,String> functionDict, Hashtable<String,String> typeDict, Hashtable<String,String> registerDict){
        int lineNum = 0;

        for(int i = 0; i < instructionList.size(); i++){
            
            String [] line = instructionList.get(i);
            String opcode = line[0];
            if (!opcodeDict.containsKey(opcode)) {
                System.out.println("invalid instruction: " + opcode);
                return;
            }
            
            if(getopcodeType(opcode, typeDict).equals("R")){
                printRInstruction(line, opcodeDict, registerDict, functionDict);

            }else if(getopcodeType(opcode, typeDict).equals("I")){
                printIInstruction(line, lineNum, opcodeDict, registerDict, labelDict);
            }else{
                printJInstruction(line, opcodeDict, labelDict);
            }
            if (instructionList.get(i).length > 1) {
                lineNum++;
            }
        }

    }

    private static void printRInstruction(String[] line, Hashtable<String, String> opcodeDict, Hashtable<String,String> registerDict, Hashtable<String,String> functionDict){

        String opcode = line[0];
        String rd = "";
        String rs = "";
        String rt = "";
        String shamt = "";
        String func = "";

       

        if(opcode.equals("sll")){
            rs = "00000";
            rd = registerDict.get(line[1]);
            rt = registerDict.get(line[2]);
            shamt = shamtToString(line[3]);
            func = functionDict.get(opcode);

        }else if(opcode.equals("jr")){
            rs = registerDict.get(line[1]);
            rt = "00000";
            rd = "00000";
            shamt = "00000";
            func = functionDict.get(opcode);
        }else{
            rd = registerDict.get(line[1]);
            rs = registerDict.get(line[2]);
            rt = registerDict.get(line[3]);
            shamt = "00000 ";
            func = functionDict.get(opcode);
        }
        opcode = opcodeDict.get(opcode);
        System.out.println(opcode + " " + rs + " " + rt + " " + rd + " " + shamt + " " + func);

    }

    private static void printIInstruction(String[] line, int lineNum, Hashtable<String, String> opcodeDict, Hashtable<String,String> registerDict, Hashtable<String,Integer> labelDict){
        // System.out.println("I Instruction");

        String opcode = line[0];
        String rs = "";
        String rt = "";
        int offset = 0;
        String imm = "";
        String label = "";
        

        if(opcode.equals("beq") || opcode.equals("bne")){
            rs = registerDict.get(line[1]);
            rt = registerDict.get(line[2]);
            label = line[3];

            offset = labelDict.get(label)  - lineNum  - 1;
            
            imm = immToString(Integer.toString(offset));
        }else if(opcode.equals("sw") || opcode.equals("lw")){
            rt = registerDict.get(line[1]);
            rs = registerDict.get(line[3]);
            imm = immToString(line[2]);
        }else{
            rt = registerDict.get(line[1]);
            rs = registerDict.get(line[2]);
            imm = immToString(line[3]);
        }
            opcode = opcodeDict.get(opcode);
            System.out.println(opcode + " " + rs + " " + rt + " "  + imm);

    }

    private static void printJInstruction(String[] line,Hashtable<String, String> opcodeDict, Hashtable<String,Integer> labelDict){
        // System.out.println("J Instruction");

        String opcode = line[0];
        String label = line[1];
        String addr = "";

        
        addr = addrToString(labelDict.get(label));
        

        opcode = opcodeDict.get(opcode);
        System.out.println(opcode + " " + addr);

    }

    



    
}

