import java.util.*;
import java.util.concurrent.TimeUnit;

class Policy implements Comparable<Policy> {
    private String policyNumber;
    private String policyholderName;
    private Date expiryDate;
    private String coverageType;
    private double premiumAmount;

    public Policy(String policyNumber, String policyholderName, Date expiryDate, String coverageType, double premiumAmount) {
        this.policyNumber = policyNumber;
        this.policyholderName = policyholderName;
        this.expiryDate = expiryDate;
        this.coverageType = coverageType;
        this.premiumAmount = premiumAmount;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public String getCoverageType() {
        return coverageType;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Policy policy = (Policy) obj;
        return policyNumber.equals(policy.policyNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(policyNumber);
    }

    @Override
    public int compareTo(Policy other) {
        return this.expiryDate.compareTo(other.expiryDate);
    }

    @Override
    public String toString() {
        return "PolicyNumber: " + policyNumber + ", Name: " + policyholderName + ", Expiry: " + expiryDate + ", Coverage: " + coverageType + ", Premium: " + premiumAmount;
    }
}

public class Program1 {
    private Set<Policy> hashSetPolicies = new HashSet<>();
    private Set<Policy> linkedHashSetPolicies = new LinkedHashSet<>();
    private Set<Policy> treeSetPolicies = new TreeSet<>();

    public void addPolicy(Policy policy) {
        hashSetPolicies.add(policy);
        linkedHashSetPolicies.add(policy);
        treeSetPolicies.add(policy);
    }

    public void displayHashSet() {
        System.out.println("\nHashSet Policies:");
        for (Policy p : hashSetPolicies) {
            System.out.println(p);
        }
    }

    public void displayLinkedHashSet() {
        System.out.println("\nLinkedHashSet Policies:");
        for (Policy p : linkedHashSetPolicies) {
            System.out.println(p);
        }
    }

    public void displayTreeSet() {
        System.out.println("\nTreeSet Policies (sorted by expiry date):");
        for (Policy p : treeSetPolicies) {
            System.out.println(p);
        }
    }

    public void displayPoliciesExpiringSoon() {
        System.out.println("\nPolicies Expiring Within 30 Days:");
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 30);
        Date thirtyDaysLater = calendar.getTime();

        for (Policy p : hashSetPolicies) {
            if (!p.getExpiryDate().before(now) && !p.getExpiryDate().after(thirtyDaysLater)) {
                System.out.println(p);
            }
        }
    }

    public void displayPoliciesByCoverageType(String coverageType) {
        System.out.println("\nPolicies with Coverage Type: " + coverageType);
        for (Policy p : hashSetPolicies) {
            if (p.getCoverageType().equalsIgnoreCase(coverageType)) {
                System.out.println(p);
            }
        }
    }

    public void findDuplicatePolicies() {
        System.out.println("\nDuplicate Policies based on Policy Number:");
        Set<String> policyNumbers = new HashSet<>();
        Set<String> duplicates = new HashSet<>();

        for (Policy p : hashSetPolicies) {
            if (!policyNumbers.add(p.getPolicyNumber())) {
                duplicates.add(p.getPolicyNumber());
            }
        }

        if (duplicates.isEmpty()) {
            System.out.println("No duplicate policies found.");
        } else {
            for (String dup : duplicates) {
                System.out.println("Duplicate Policy Number: " + dup);
            }
        }
    }

    public void performanceComparison() {
        System.out.println("\nPerformance Comparison:");

        List<Policy> samplePolicies = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        for (int i = 0; i < 10000; i++) {
            cal.set(2025, Calendar.JANUARY, 1 + (i % 30));
            samplePolicies.add(new Policy("PX" + i, "Holder" + i, cal.getTime(), "Health", i * 10.0));
        }

        long start, end;

        Set<Policy> hash = new HashSet<>();
        start = System.nanoTime();
        hash.addAll(samplePolicies);
        end = System.nanoTime();
        System.out.println("HashSet Add Time: " + TimeUnit.NANOSECONDS.toMillis(end - start) + " ms");

        start = System.nanoTime();
        hash.contains(samplePolicies.get(5000));
        end = System.nanoTime();
        System.out.println("HashSet Search Time: " + (end - start) + " ns");

        start = System.nanoTime();
        hash.remove(samplePolicies.get(5000));
        end = System.nanoTime();
        System.out.println("HashSet Remove Time: " + (end - start) + " ns");

        Set<Policy> linkedHash = new LinkedHashSet<>();
        start = System.nanoTime();
        linkedHash.addAll(samplePolicies);
        end = System.nanoTime();
        System.out.println("LinkedHashSet Add Time: " + TimeUnit.NANOSECONDS.toMillis(end - start) + " ms");

        start = System.nanoTime();
        linkedHash.contains(samplePolicies.get(5000));
        end = System.nanoTime();
        System.out.println("LinkedHashSet Search Time: " + (end - start) + " ns");

        start = System.nanoTime();
        linkedHash.remove(samplePolicies.get(5000));
        end = System.nanoTime();
        System.out.println("LinkedHashSet Remove Time: " + (end - start) + " ns");

        Set<Policy> tree = new TreeSet<>();
        start = System.nanoTime();
        tree.addAll(samplePolicies);
        end = System.nanoTime();
        System.out.println("TreeSet Add Time: " + TimeUnit.NANOSECONDS.toMillis(end - start) + " ms");

        start = System.nanoTime();
        tree.contains(samplePolicies.get(5000));
        end = System.nanoTime();
        System.out.println("TreeSet Search Time: " + (end - start) + " ns");

        start = System.nanoTime();
        tree.remove(samplePolicies.get(5000));
        end = System.nanoTime();
        System.out.println("TreeSet Remove Time: " + (end - start) + " ns");
    }

    public static void main(String[] args) {
        Program1 system = new Program1();
        Scanner scanner = new Scanner(System.in);

        Calendar cal = Calendar.getInstance();

        cal.set(2025, Calendar.JUNE, 30);
        Policy policy1 = new Policy("P001", "Alice Smith", cal.getTime(), "Health", 1200.50);

        cal.set(2024, Calendar.MAY, 10);
        Policy policy2 = new Policy("P002", "Bob Johnson", cal.getTime(), "Auto", 800.00);

        cal.set(2024, Calendar.MAY, 5);
        Policy policy3 = new Policy("P003", "Charlie Davis", cal.getTime(), "Home", 1500.75);

        system.addPolicy(policy1);
        system.addPolicy(policy2);
        system.addPolicy(policy3);

        boolean exit = false;
        while (!exit) {
            System.out.println("\nMenu:");
            System.out.println("1. Display HashSet");
            System.out.println("2. Display LinkedHashSet");
            System.out.println("3. Display TreeSet");
            System.out.println("4. Display Policies Expiring Soon");
            System.out.println("5. Display Policies by Coverage Type");
            System.out.println("6. Find Duplicate Policies");
            System.out.println("7. Performance Comparison");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    system.displayHashSet();
                    break;
                case 2:
                    system.displayLinkedHashSet();
                    break;
                case 3:
                    system.displayTreeSet();
                    break;
                case 4:
                    system.displayPoliciesExpiringSoon();
                    break;
                case 5:
                    System.out.print("Enter Coverage Type: ");
                    String coverageType = scanner.nextLine();
                    system.displayPoliciesByCoverageType(coverageType);
                    break;
                case 6:
                    system.findDuplicatePolicies();
                    break;
                case 7:
                    system.performanceComparison();
                    break;
                case 8:
                    exit = true;
                    System.out.println("Exiting Program...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
        scanner.close();
    }
}