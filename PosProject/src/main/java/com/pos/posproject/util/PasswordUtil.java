/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pos.posproject.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stupa
 */
public class PasswordUtil {
    public static String convertToSha256(String password){
        try{
            MessageDigest messageDigest=MessageDigest.getInstance("SHA-256");
            messageDigest.update(password.getBytes());
            byte[] digest=messageDigest.digest();
            String result=new BigInteger(1, digest).toString(16);
            return result;
        }catch (NoSuchAlgorithmException ex){
            Logger.getLogger(PasswordUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
