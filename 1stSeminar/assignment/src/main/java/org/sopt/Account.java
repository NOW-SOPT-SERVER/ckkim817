package org.sopt;

import java.util.Random;

public class Account {

        private String accountNumber;
        private String name;
        private String pw;
        private int balance;

        public Account(String name, String pw) {
            this.accountNumber = makeRandomAccountNumber();
            this.name = name;
            this.pw = pw;
            this.balance = 0;
        }

        // 계좌 번호 난수로 생성
        public String makeRandomAccountNumber() {
            Random random = new Random();
            return (random.nextInt(9000) + 1000) + "-" + (random.nextInt(9000) + 1000);
        }

        public String getAccountNumber() {
            return this.accountNumber;
        }

        public String getPw() {
            return this.pw;
        }

        public int getBalance() {
            return balance;
        }

        public void deposit(int money) {
            balance += money;
        }

        public int withdraw(int money) {
            if (balance - money >= 0) {
                balance -= money;
            } else {
                System.out.println("잔액이 부족합니다.");
            }

            return balance;
        }
    }
