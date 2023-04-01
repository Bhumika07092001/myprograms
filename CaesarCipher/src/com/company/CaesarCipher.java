package com.company;
import java.util.Scanner;

public class CaesarCipher{
    public static final String ALPHABET="abcdefghijklmnopqrstuvwxyz";
    public static String encrypt(String plainText, int shiftKey){
        plainText= plainText.toLowerCase();
        String cipherText ="";
        for(int i=0; i<plainText.length(); i++){
            int charPosition= ALPHABET.indexOf(plainText.charAt(i));
            int ogvalue=(shiftKey+charPosition)%26;
            char replaceValue=ALPHABET.charAt(ogvalue);
            cipherText +=replaceValue;
        }
        return cipherText;
    }


    public static void main(String[] args){
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter the string for Encryption");
        String message= new String();
        message= sc.next();
        System.out.println(encrypt(message,3));
        sc.close();
    }

}


