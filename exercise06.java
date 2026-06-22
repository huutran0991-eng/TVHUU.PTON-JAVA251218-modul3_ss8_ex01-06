import java.util.Scanner;

public class exercise06 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        IBookManager manager = new BookManager();
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n=== HỆ THỐNG QUẢN LÝ SÁCH ===");
            System.out.println("1. Thêm quyển sách mới");
            System.out.println("2. Hiển thị tất cả sách");
            System.out.println("3. Xóa quyển sách (Theo mã ISBN)");
            System.out.println("4. Thoát");
            System.out.print("Vui lòng chọn chức năng (1-4): ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Nhập tiêu đề sách: ");
                    String title = scanner.nextLine();
                    System.out.print("Nhập tên tác giả: ");
                    String author = scanner.nextLine();
                    System.out.print("Nhập mã ISBN: ");
                    String isbn = scanner.nextLine();
                    System.out.print("Nhập năm xuất bản: ");
                    int year = Integer.parseInt(scanner.nextLine());

                    Book newBook = new Book(title, author, isbn, year);
                    manager.addBook(newBook);
                    break;

                case 2:
                    manager.displayBooks();
                    break;

                case 3:
                    System.out.print("Nhập mã ISBN của sách cần xóa: ");
                    String deleteIsbn = scanner.nextLine();
                    manager.removeBook(deleteIsbn);
                    break;

                case 4:
                    System.out.println("=> Đang thoát chương trình. Tạm biệt!");
                    isRunning = false;
                    break;

                default:
                    System.out.println("=> Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
        scanner.close();
    }

    public static class Book {
        private String title;
        private String author;
        private String isbn;
        private int year;

        // Constructor để khởi tạo sách
        public Book(String title, String author, String isbn, int year) {
            this.title = title;
            this.author = author;
            this.isbn = isbn;
            this.year = year;
        }

        // Phương thức trả về thông tin chi tiết
        public String getDetails() {
            return "ISBN: " + isbn + " | Tiêu đề: " + title + " | Tác giả: " + author + " | Năm XB: " + year;
        }

        // Getter cho ISBN dùng để tìm kiếm và xóa
        public String getIsbn() {
            return isbn;
        }
    }

    public static class BookManager implements IBookManager {
        // Giả sử thư viện chứa tối đa 100 quyển sách
        private Book[] books = new Book[100];
        private int size = 0; // Biến theo dõi số lượng sách thực tế

        @Override
        public void addBook(Book book) {
            if (size < books.length) {
                books[size] = book;
                size++;
                System.out.println("=> Thêm sách thành công!");
            } else {
                System.out.println("=> Lỗi: Thư viện đã đầy, không thể thêm sách mới!");
            }
        }

        @Override
        public void removeBook(String isbn) {
            int indexToDelete = -1;

            // 1. Tìm vị trí của quyển sách có mã ISBN tương ứng
            for (int i = 0; i < size; i++) {
                if (books[i].getIsbn().equals(isbn)) { // Dùng .equals() để so sánh chuỗi
                    indexToDelete = i;
                    break;
                }
            }

            // 2. Nếu tìm thấy, thực hiện xóa và dịch chuyển mảng
            if (indexToDelete != -1) {
                for (int i = indexToDelete; i < size - 1; i++) {
                    books[i] = books[i + 1]; // Dịch phần tử bên phải sang trái
                }
                books[size - 1] = null; // Xóa phần tử bị trùng ở cuối
                size--; // Giảm số lượng sách
                System.out.println("=> Đã xóa sách có mã ISBN: " + isbn);
            } else {
                System.out.println("=> Lỗi: Không tìm thấy sách với mã ISBN: " + isbn);
            }
        }

        @Override
        public void displayBooks() {
            if (size == 0) {
                System.out.println("=> Thư viện hiện đang trống.");
            } else {
                System.out.println("--- DANH SÁCH QUYỂN SÁCH ---");
                for (int i = 0; i < size; i++) {
                    System.out.println(books[i].getDetails());
                }
            }
        }
    }

    // Interface định nghĩa các hoạt động quản lý sách
    public static interface IBookManager {
        void addBook(Book book);
        void removeBook(String isbn);
        void displayBooks();
    }
}
