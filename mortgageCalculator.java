class MortgageCalculator {
    private double housePrice;
    private double loanAmount;
    private double downPayment;
    private int term;
    private double interestRate;
    private boolean monthly;
    private double PMI;

    public MortgageCalculator(double housePrice, double loanAmount, double downPayment, int term, double interestRate, boolean monthly) {
        this.housePrice = housePrice;
        this.loanAmount = loanAmount;
        this.downPayment = downPayment;
        this.term = term;
        this.interestRate = interestRate;
        this.monthly = monthly;

        if (downPayment < 0.2 * housePrice) {
            if (monthly) {
                PMI = 0.01 * loanAmount;
            } else {
                PMI = 0.0005 * loanAmount;
            }
        }
    }

    public double calculateMortgage() {
        double r;
        int n;
        if (monthly) {
            r = (interestRate / 12) / 100;
            n = term * 12;
        } else {
            r = (interestRate / 26) / 100;
            n = term * 26;
        }
        // M = P [ i(1 + i)^n ] / [ (1 + i)^n – 1]

        double mortgagePayment = (loanAmount * r * Math.pow(1 + r, n)) / (Math.pow(1 + r, n) - 1);
        return mortgagePayment + PMI;
    }
}

class MortgageCalculatorTest {
    public static void main(String[] args) {
        MortgageCalculator calculator = new MortgageCalculator(200000, 180000, 40000, 30, 4.5, true);
        System.out.println("Mortgage Payment: $" + calculator.calculateMortgage());
    }
}

