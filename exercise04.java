public class exercise04 {
    public static void main(String[] args) {
        System.out.println("=== HỆ THỐNG QUẢN LÝ NGÂN HÀNG ===");

        // 1. Khởi tạo hai tài khoản exercise04.BankAccount
        BankAccount accountA = new BankAccount("VN123", "Nguyễn Văn A", "0901234567", 5000.0);
        BankAccount accountB = new BankAccount("VN456", "Trần Thị B", "0987654321", 1000.0);

        System.out.println("\n[Số dư ban đầu]");
        accountA.displayBalance();
        accountB.displayBalance();

        // 2. Thực hiện chuyển tiền từ tài khoản A đến tài khoản B (Ví dụ: Chuyển $1500)
        double transferAmount = 1500.0;
        System.out.println("\n--- BẮT ĐẦU CHUYỂN KHOẢN $" + transferAmount + " TỪ A SANG B ---");

        // Logic chuyển tiền: A rút tiền ra -> B nhận tiền vào
        // (Trong thực tế, ta nên kiểm tra xem A có rút thành công không rồi mới cộng cho B)
        if (accountA.getBalance() >= transferAmount) {
            accountA.withdraw(transferAmount); // A bị trừ tiền
            accountB.deposit(transferAmount);  // B được cộng tiền
            System.out.println("=> Chuyển khoản thành công!");
        } else {
            System.out.println("=> Chuyển khoản thất bại do tài khoản A không đủ số dư.");
        }

        // 3. In ra số dư của từng tài khoản sau khi chuyển tiền
        System.out.println("\n[Số dư sau giao dịch]");
        accountA.displayBalance();
        accountB.displayBalance();
    }

    // Định nghĩa Interface (Hợp đồng giao dịch)
    public static interface Ibank {
        void deposit(double amount);
        void withdraw(double amount);
    }

    public static class BankAccount implements Ibank {
        private String accountId;
        private double balance;
        private String userName;
        private String phoneNumber;

        // Constructor khởi tạo tài khoản
        public BankAccount(String accountId, String userName, String phoneNumber, double initialBalance) {
            this.accountId = accountId;
            this.userName = userName;
            this.phoneNumber = phoneNumber;
            this.balance = initialBalance;
        }

        // Triển khai phương thức nhận tiền
        @Override
        public void deposit(double amount) {
            if (amount > 0) {
                balance += amount;
                System.out.println("Giao dịch: +$" + amount + " vào tài khoản " + userName);
            } else {
                System.out.println("Lỗi: Số tiền nạp phải lớn hơn 0.");
            }
        }

        // Triển khai phương thức rút tiền
        @Override
        public void withdraw(double amount) {
            if (amount <= 0) {
                System.out.println("Lỗi: Số tiền rút phải lớn hơn 0.");
            } else if (amount > balance) {
                System.out.println("Lỗi: Số dư không đủ để rút $" + amount + " từ tài khoản " + userName);
            } else {
                balance -= amount;
                System.out.println("Giao dịch: -$" + amount + " từ tài khoản " + userName);
            }
        }

        // Phương thức hiển thị số dư
        public void displayBalance() {
            System.out.println("Tài khoản [" + accountId + "] - " + userName + " | Số dư hiện tại: $" + balance);
        }

        // Getter cho balance (Dùng để kiểm tra logic chuyển khoản bên ngoài nếu cần)
        public double getBalance() {
            return balance;
        }
    }
}
