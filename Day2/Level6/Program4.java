import java.util.*;

public class Program4 {
    private Map<String, Double> customerAccountsMap = new HashMap<>();
    private Map<String, Double> sortedAccountsMap = new TreeMap<>(Comparator.comparing(customerAccountsMap::get).reversed());
    private Queue<String> withdrawalRequestsQueue = new LinkedList<>();

    public void addAccount(String accountNumber, double initialBalance) {
        customerAccountsMap.put(accountNumber, initialBalance);
    }

    public void makeWithdrawalRequest(String accountNumber) {
        if (customerAccountsMap.containsKey(accountNumber)) {
            withdrawalRequestsQueue.add(accountNumber);
            System.out.println("Withdrawal request added for account: " + accountNumber);
        } else {
            System.out.println("Account not found: " + accountNumber);
        }
    }

    public void processWithdrawals() {
        while (!withdrawalRequestsQueue.isEmpty()) {
            String accountNumber = withdrawalRequestsQueue.poll();
            System.out.println("Processing withdrawal for account: " + accountNumber);
            double balance = customerAccountsMap.get(accountNumber);
            if (balance > 0) {
                double withdrawalAmount = 100.00;
                if (balance >= withdrawalAmount) {
                    customerAccountsMap.put(accountNumber, balance - withdrawalAmount);
                    System.out.println("Withdrawal successful. New balance: " + (balance - withdrawalAmount));
                } else {
                    System.out.println("Insufficient funds for withdrawal.");
                }
            } else {
                System.out.println("Account " + accountNumber + " has no balance.");
            }
        }
    }

    public void displayAccounts() {
        System.out.println("\nCustomer Accounts:");
        for (Map.Entry<String, Double> entry : customerAccountsMap.entrySet()) {
            System.out.println("Account Number: " + entry.getKey() + ", Balance: " + entry.getValue());
        }
    }

    public void displaySortedAccounts() {
        System.out.println("\nAccounts sorted by balance:");
        sortedAccountsMap.putAll(customerAccountsMap);
        for (Map.Entry<String, Double> entry : sortedAccountsMap.entrySet()) {
            System.out.println("Account Number: " + entry.getKey() + ", Balance: " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        Program4 bank = new Program4();

        bank.addAccount("A1001", 1500.00);
        bank.addAccount("A1002", 500.00);
        bank.addAccount("A1003", 1200.75);

        bank.displayAccounts();
        bank.displaySortedAccounts();

        bank.makeWithdrawalRequest("A1001");
        bank.makeWithdrawalRequest("A1002");
        bank.makeWithdrawalRequest("A1003");

        bank.processWithdrawals();
    }
}