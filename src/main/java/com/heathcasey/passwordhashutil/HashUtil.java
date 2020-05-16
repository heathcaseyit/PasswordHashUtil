/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heathcasey.passwordhashutil;

import java.util.Scanner;

/**
 *
 * @author Heath
 */
public class HashUtil {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String hashedpw = "";
        Pbkdf2Hash hasher = new Pbkdf2Hash();
        if (args != null && args.length > 0) {
            if (args[0].equals("--verify")) {
                if (hasher.verify(args[1].toCharArray(), args[2])) {
                    System.out.println("Token is Valid.");
                } else {
                    System.out.println("Token is Invalid.");
                }
            } else if (args[0].equals("--generate")) {
                hashedpw = hasher.generate(args[1].toCharArray());
                System.out.println("Hashed password:");
                System.out.println(hashedpw);
                System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");

            } else if (args[0].equals("--help")) {
                System.out.println("--------HashUtil Help--------");
                System.out.println("To run in promted mode, run HashUtil without options");
                System.out.println("");
                System.out.println("To verify password matches hashed token, use the options:");
                System.out.println("--verify password token");
                System.out.println("");
                System.out.println("To generate token from password, use the options:");
                System.out.println("--generate password");
                System.out.println("");
                System.out.println("--------End Help--------");
            } else {
                System.out.println("Invalid Arguments!");
            }

        } else {
            System.out.println("Enter password to hash: ");
            String rawpw = in.nextLine();
            hashedpw = hasher.generate(rawpw.toCharArray());
            System.out.println("Hashed password:");
            System.out.println(hashedpw);
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
            System.out.println("Enter password to verify match: ");
            String rawpw2 = in.nextLine();
            boolean isMatch = hasher.verify(rawpw2.toCharArray(), hashedpw);

            while (!isMatch) {
                System.out.println("Passwords did not Match, Renter Password: ");
                rawpw2 = in.nextLine();
                isMatch = hasher.verify(rawpw2.toCharArray(), hashedpw);
            }
            System.out.println("Passwords match!");
        }

    }

}
