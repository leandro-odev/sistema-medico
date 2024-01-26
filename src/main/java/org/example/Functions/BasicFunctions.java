package org.example.Functions;

import java.util.InputMismatchException;

public class BasicFunctions {

    public static boolean verifyCpf(String cpf) {
        if (cpf.length() != 11 || cpf.equals("00000000000") || cpf.equals("11111111111") ||
                cpf.equals("22222222222") || cpf.equals("33333333333") || cpf.equals("44444444444") ||
                cpf.equals("55555555555") || cpf.equals("66666666666") || cpf.equals("77777777777") ||
                cpf.equals("88888888888") || cpf.equals("99999999999"))
            return false;

        char dig10;
        char dig11;
        int sm, i, r, num, peso;

        try {
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                num = (cpf.charAt(i) - '0');
                sm += num * peso;
                peso--;
            }

            r = 11 - (sm % 11);
            dig10 = (r == 10 || r == 11) ? '0' : (char) (r + '0');

            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (cpf.charAt(i) - '0');
                sm += num * peso;
                peso--;
            }

            r = 11 - (sm % 11);
            dig11 = (r == 10 || r == 11) ? '0' : (char) (r + '0');

            return dig10 == cpf.charAt(9) && dig11 == cpf.charAt(10);
        } catch (InputMismatchException erro) {
            return false;
        }
    }
}
