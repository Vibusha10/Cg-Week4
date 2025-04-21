import java.util.*;
import java.text.*;

class Policy {
    private String policyNumber;
    private String policyholderName;
    private Date expiryDate;
    private double premiumAmount;

    public Policy(String policyNumber, String policyholderName, Date expiryDate, double premiumAmount) {
        this.policyNumber = policyNumber;
        this.policyholderName = policyholderName;
        this.expiryDate = expiryDate;
        this.premiumAmount = premiumAmount;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public String getPolicyholderName() {
        return policyholderName;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public double getPremiumAmount() {
        return premiumAmount;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return "PolicyNumber: " + policyNumber + ", Policyholder: " + policyholderName +
               ", Expiry: " + sdf.format(expiryDate) + ", Premium: " + premiumAmount;
    }
}

public class Program1 {

    private Map<String, Policy> policyMap = new HashMap<>();
    private Map<String, Policy> policyLinkedMap = new LinkedHashMap<>();
    private Map<String, Policy> policyTreeMap = new TreeMap<>(new Comparator<String>() {
        @Override
        public int compare(String policyNumber1, String policyNumber2) {
            Date expiryDate1 = policyMap.get(policyNumber1).getExpiryDate();
            Date expiryDate2 = policyMap.get(policyNumber2).getExpiryDate();
            return expiryDate1.compareTo(expiryDate2);
        }
    });

    public void addPolicy(Policy policy) {
        policyMap.put(policy.getPolicyNumber(), policy);
        policyLinkedMap.put(policy.getPolicyNumber(), policy);
        policyTreeMap.put(policy.getPolicyNumber(), policy);
    }

    public Policy getPolicyByNumber(String policyNumber) {
        return policyMap.get(policyNumber);
    }

    public List<Policy> getPoliciesExpiringWithin30Days() {
        List<Policy> expiringSoon = new ArrayList<>();
        Date currentDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.DAY_OF_YEAR, 30);
        Date date30DaysFromNow = cal.getTime();

        for (Policy policy : policyMap.values()) {
            if (policy.getExpiryDate().before(date30DaysFromNow)) {
                expiringSoon.add(policy);
            }
        }

        return expiringSoon;
    }

    public List<Policy> getPoliciesByPolicyholder(String policyholderName) {
        List<Policy> policiesForHolder = new ArrayList<>();
        for (Policy policy : policyMap.values()) {
            if (policy.getPolicyholderName().equalsIgnoreCase(policyholderName)) {
                policiesForHolder.add(policy);
            }
        }
        return policiesForHolder;
    }

    public void removeExpiredPolicies() {
        Date currentDate = new Date();
        policyMap.values().removeIf(policy -> policy.getExpiryDate().before(currentDate));
        policyLinkedMap.values().removeIf(policy -> policy.getExpiryDate().before(currentDate));
        policyTreeMap.values().removeIf(policy -> policy.getExpiryDate().before(currentDate));
    }

    public void displayPolicies(Map<String, Policy> map) {
        for (Policy policy : map.values()) {
            System.out.println(policy);
        }
    }

    public static void main(String[] args) throws ParseException {
        Program1 system = new Program1();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Policy policy1 = new Policy("P001", "Alice Smith", sdf.parse("2025-06-30"), 1200.50);
        Policy policy2 = new Policy("P002", "Bob Johnson", sdf.parse("2024-12-15"), 800.00);
        Policy policy3 = new Policy("P003", "Charlie Davis", sdf.parse("2026-03-10"), 1500.75);

        system.addPolicy(policy1);
        system.addPolicy(policy2);
        system.addPolicy(policy3);

        System.out.println("All Policies:");
        system.displayPolicies(system.policyMap);

        System.out.println("\nPolicies Expiring Within 30 Days:");
        List<Policy> expiringPolicies = system.getPoliciesExpiringWithin30Days();
        for (Policy policy : expiringPolicies) {
            System.out.println(policy);
        }

        System.out.println("\nPolicies for Policyholder 'Alice Smith':");
        List<Policy> policiesForAlice = system.getPoliciesByPolicyholder("Alice Smith");
        for (Policy policy : policiesForAlice) {
            System.out.println(policy);
        }

        System.out.println("\nRemoving Expired Policies...");
        system.removeExpiredPolicies();
        system.displayPolicies(system.policyMap);
    }
}