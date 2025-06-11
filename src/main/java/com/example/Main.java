package com.example;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ApiConnector connector = new ApiConnector();
        ExchangeRates rates = connector.getRates("USD");

        if (rates == null) {
            System.out.println("Não foi possível obter as taxas de câmbio. Encerrando aplicação.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        int option = 0;

        String menu = "*****************************************************\n" +
                "Bem-vindo(a) ao Conversor de Moedas! 💰\n\n" +
                "Escolha uma das opções de conversão:\n\n" +
                "1) Dólar Americano (USD) => Real Brasileiro (BRL)\n" +
                "2) Real Brasileiro (BRL) => Dólar Americano (USD)\n" +
                "3) Dólar Americano (USD) => Peso Argentino (ARS)\n" +
                "4) Peso Argentino (ARS) => Dólar Americano (USD)\n" +
                "5) Dólar Americano (USD) => Iene Japonês (JPY)\n" +
                "6) Euro (EUR)             => Real Brasileiro (BRL)\n" +
                "7) Sair\n" +
                "*****************************************************";

        while (option != 7) {
            System.out.println(menu);
            try {
                System.out.print("Digite a opção desejada: ");
                option = scanner.nextInt();

                if (option >= 1 && option <= 6) {
                    System.out.print("Digite o valor que deseja converter: ");
                    double amount = scanner.nextDouble();
                    convertCurrency(option, amount, rates);
                } else if (option != 7) {
                    System.out.println("Opção inválida. Tente novamente.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Por favor, digite um número.");
                scanner.next();
            }
        }

        System.out.println("Obrigado por usar o Conversor de Moedas! 👋");
        scanner.close();
    }

    public static void convertCurrency(int option, double amount, ExchangeRates rates) {
        Map<String, Double> conversionRates = rates.getConversionRates();
        double fromRate, toRate, result;
        String fromCurrency = "", toCurrency = "";

        switch (option) {
            case 1:
                fromCurrency = "USD";
                toCurrency = "BRL";
                result = amount * conversionRates.get(toCurrency);
                break;
            case 2:
                fromCurrency = "BRL";
                toCurrency = "USD";
                fromRate = conversionRates.get(fromCurrency);
                result = amount / fromRate;
                break;
            case 3:
                fromCurrency = "USD";
                toCurrency = "ARS";
                result = amount * conversionRates.get(toCurrency);
                break;
            case 4:
                fromCurrency = "ARS";
                toCurrency = "USD";
                fromRate = conversionRates.get(fromCurrency);
                result = amount / fromRate;
                break;
            case 5:
                fromCurrency = "USD";
                toCurrency = "JPY";
                result = amount * conversionRates.get(toCurrency);
                break;
            case 6:
                fromCurrency = "EUR";
                toCurrency = "BRL";
                fromRate = conversionRates.get(fromCurrency);
                toRate = conversionRates.get(toCurrency);
                result = (amount * toRate) / fromRate;
                break;
            default:
                System.out.println("Opção inválida.");
                return;
        }

        System.out.println("\n--- Resultado da Conversão ---");
        System.out.printf("Valor: %.2f [%s]\nConvertido para: %.2f [%s]\n\n", amount, fromCurrency, result, toCurrency);
    }
}