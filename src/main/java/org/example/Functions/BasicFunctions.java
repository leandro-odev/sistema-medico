package org.example.Functions;

import java.util.InputMismatchException;

public class BasicFunctions {
    public static boolean verifyCpf(String cpf) {
        if (cpf.length() != 11 || cpf.matches("[0-9]{11}"))
            return false;

        try {
            for (int j = 9; j <= 10; j++) {
                int som = 0, peso = j + 1;
                for (int i = 0; i < j; i++) {
                    int num = cpf.charAt(i) - '0';
                    som += num * peso--;
                }

                int restante = 11 - (som % 11);
                char checkDigit = (restante == 10 || restante == 11) ? '0' : (char) (restante + '0');

                if (checkDigit != cpf.charAt(j))
                    return false;
            }
            return true;
        } catch (InputMismatchException erro) {
            return false;
        }
    }

}
