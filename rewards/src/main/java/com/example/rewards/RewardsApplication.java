package com.example.rewards;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootApplication
public class RewardsApplication {

    private static Map<String, RewardsPerCustomer> rewards;

    public static void main(String[] args) throws ParseException {
        rewards = new HashMap<>();
        List<Transaction> transactionList = getTransactionsList();
        calculateRewardsForCustomer(rewards, transactionList);

        System.out.println(rewards);
    }

    private static void calculateRewardsForCustomer(Map<String, RewardsPerCustomer> rewards,
                                                    List<Transaction> transactionList) {

        // Group the transactions based on customerId
        Map<String, List<Transaction>> transactionsPerPerson = transactionList.stream()
                .collect(Collectors.groupingBy(Transaction::getCustomerId));

        for (Map.Entry<String, List<Transaction>> entry : transactionsPerPerson.entrySet()) {

            List<Transaction> personTransactions = entry.getValue();
            RewardsPerCustomer rewardsPerCustomer = new RewardsPerCustomer();

            rewardsPerCustomer.setTotalRewardsEarned(personTransactions.stream()
                    .map(transaction -> rewardsForAmount(transaction.getAmount())).reduce(0L, Long::sum));

            //Group the personTransactions based on month
            Map<Integer, List<Transaction>> transactionsPerPersonPerMonth = personTransactions.stream()
                    .collect(Collectors.groupingBy(Transaction::getTransactionMonth));

            Map<Integer, Long> rewardsPerMonthMap = new HashMap<>();
            for (Map.Entry<Integer, List<Transaction>> entry1 : transactionsPerPersonPerMonth.entrySet()) {
                List<Transaction> personTransactionsPerMonth = entry1.getValue();
                rewardsPerMonthMap.put(entry1.getKey() + 1, personTransactionsPerMonth.stream()
                        .map(transaction -> rewardsForAmount(transaction.getAmount())).reduce(0L, Long::sum));
            }
            rewardsPerCustomer.setRewardsEarnedPerMonthMap(rewardsPerMonthMap);
            rewards.put(entry.getKey(), rewardsPerCustomer);
        }
    }

    private static long rewardsForAmount(long amount) {
        if (amount <= 50) {
            return 0;
        } else if (amount <= 100) {
            return amount - 50;
        } else {
            return 2 * (amount - 100) + 50;
        }
    }

    private static List<Transaction> getTransactionsList() throws ParseException {

        //Placeholder to provide test Data, this can be easily enhanced to fetch the data from text file if needed.
        List<Transaction> transactionsList = new ArrayList<>();

        transactionsList.add(new Transaction(20, "Vinay", new SimpleDateFormat("dd/MM/yyyy")
                .parse("10/03/2021")));
        transactionsList.add(new Transaction(90, "Vinay", new SimpleDateFormat("dd/MM/yyyy")
                .parse("28/04/2021")));
        transactionsList.add(new Transaction(150, "Pinky", new SimpleDateFormat("dd/MM/yyyy")
                .parse("30/05/2021")));
        transactionsList.add(new Transaction(780, "Vinay", new SimpleDateFormat("dd/MM/yyyy")
                .parse("12/05/2021")));
        transactionsList.add(new Transaction(30, "Pinky", new SimpleDateFormat("dd/MM/yyyy")
                .parse("03/03/2021")));
        transactionsList.add(new Transaction(100, "Pinky", new SimpleDateFormat("dd/MM/yyyy")
                .parse("07/03/2021")));
        transactionsList.add(new Transaction(12, "Vinay", new SimpleDateFormat("dd/MM/yyyy")
                .parse("25/05/2021")));
        transactionsList.add(new Transaction(80, "Vinay", new SimpleDateFormat("dd/MM/yyyy")
                .parse("22/03/2021")));
        transactionsList.add(new Transaction(50, "Kate", new SimpleDateFormat("dd/MM/yyyy")
                .parse("03/03/2021")));
        transactionsList.add(new Transaction(34, "Kate", new SimpleDateFormat("dd/MM/yyyy")
                .parse("17/04/2021")));
        transactionsList.add(new Transaction(76, "Kate", new SimpleDateFormat("dd/MM/yyyy")
                .parse("19/04/2021")));
        transactionsList.add(new Transaction(192, "Vinay", new SimpleDateFormat("dd/MM/yyyy")
                .parse("11/04/2021")));
        transactionsList.add(new Transaction(232, "Pinky", new SimpleDateFormat("dd/MM/yyyy")
                .parse("08/04/2021")));
        transactionsList.add(new Transaction(76, "Pinky", new SimpleDateFormat("dd/MM/yyyy")
                .parse("23/04/2021")));
        transactionsList.add(new Transaction(143, "Kate", new SimpleDateFormat("dd/MM/yyyy")
                .parse("29/03/2021")));
        transactionsList.add(new Transaction(21, "Pinky", new SimpleDateFormat("dd/MM/yyyy")
                .parse("22/05/2021")));
        transactionsList.add(new Transaction(543, "Kate", new SimpleDateFormat("dd/MM/yyyy")
                .parse("01/05/2021")));
        transactionsList.add(new Transaction(476, "Kate", new SimpleDateFormat("dd/MM/yyyy")
                .parse("31/05/2021")));

        return transactionsList;
    }
}
