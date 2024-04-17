package org.sopt;

import java.util.Scanner;

public class Banker {

    Scanner sc = new Scanner(System.in);
    User user = new User("김창균", 25);

    public void run() {
        String input;
        do {
            mainMenu();
            input = inputMainMenu();
            detailMenu(input);
        } while (!input.equals("5"));
    }

    private void mainMenu() {
        System.out.println("어서오세요, 나솝은행입니다! 무엇을 도와드릴까요?");
        System.out.println("1.계좌 개설   2.입금   3.출금   4.계좌 잔액 조회   5.종료");
        System.out.print("> ");
    }

    private String inputMainMenu() {
        boolean checkInput = false;

        do {
            String input = sc.nextLine().trim();

            if (!input.matches("^[1-5]$")) {
                System.out.println("1~5 사이의 숫자를 입력해 주세요.");
            } else {
                checkInput = true;

                return input;
            }
        } while (!checkInput);

        return null;
    }

    private void detailMenu(String menuNumber) {
        int money;
        String findAccountNumber;
        Account account;

        switch (menuNumber) {
            case "1":
                System.out.println("새로 개설된 계좌의 계좌 번호는 " + createAccount(user) + " 입니다.");

                break;
            case "2":
                System.out.println("입금할 계좌 번호와 금액을 입력하세요.");
                System.out.print("> ");
                findAccountNumber = sc.next();
                money = sc.nextInt();
                sc.nextLine();
                account = findAccount(findAccountNumber, user);
                account.deposit(money);

                break;
            case "3":
                System.out.println("출금할 계좌 번호화 금액을 입력하세요.");
                System.out.print("> ");
                findAccountNumber = sc.next();
                money = sc.nextInt();
                sc.nextLine();
                account = findAccount(findAccountNumber, user);
                account.withdraw(money);

                break;
            case "4":
                System.out.println("잔액을 조회할 계좌 번호를 입력하세요.");
                System.out.print("> ");
                findAccountNumber = sc.next();
                sc.nextLine();
                account = findAccount(findAccountNumber, user);

                if (account != null) {
                    int balance = account.getBalance();
                    System.out.println("잔액이 " + balance + "원 남았습니다.");
                }

                break;
            case "5":
                break;
        }
    }

    private String createAccount(User user)  {
        System.out.println("개설할 계좌의 비밀번호를 입력하세요.");
        System.out.print("> ");
        String pw = sc.next();
        sc.nextLine();
        Account newAccount = new Account(user.getName(), pw);
        user.addAccount(newAccount);

        return newAccount.getAccountNumber();
    }

    private Account findAccount(String accountNum, User user) {
        Account account = user.findAccount(accountNum);
        if (account != null) {
            System.out.println("비밀번호를 입력하세요.");
            System.out.print("> ");
            String pw = sc.next();
            sc.nextLine();

            if (account.getPw().equals(pw)) {
                return account;
            }

            System.out.println("비밀번호가 일치하지 않아 조회에 실패했습니다.");

            return null;
        } else {
            return null;
        }
    }
}
