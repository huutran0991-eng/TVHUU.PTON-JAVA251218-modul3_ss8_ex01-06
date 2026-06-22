import java.util.Scanner;

public class exercise05 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Khởi tạo đối tượng quản lý thông qua Interface
        ICRUD categoryManager = new CategoryManagement();
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n=== QUẢN LÝ DANH MỤC (CRUD VỚI MẢNG) ===");
            System.out.println("1. Thêm danh mục mới");
            System.out.println("2. Hiển thị tất cả danh mục");
            System.out.println("3. Cập nhật danh mục");
            System.out.println("4. Xóa danh mục");
            System.out.println("5. Thoát");
            System.out.print("Vui lòng chọn chức năng (1-5): ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Nhập ID: ");
                    int addId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Nhập tên danh mục: ");
                    String addName = scanner.nextLine();
                    System.out.print("Nhập mô tả: ");
                    String addDesc = scanner.nextLine();
                    categoryManager.addCategory(new Category(addId, addName, addDesc));
                    break;

                case 2:
                    Category[] list = categoryManager.findAll();
                    if (list.length == 0) {
                        System.out.println("=> Danh sách trống.");
                    } else {
                        System.out.println("--- DANH SÁCH DANH MỤC ---");
                        for (Category c : list) {
                            System.out.println(c.toString());
                        }
                    }
                    break;

                case 3:
                    System.out.print("Nhập ID danh mục cần cập nhật: ");
                    int updateId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Nhập tên mới: ");
                    String updateName = scanner.nextLine();
                    System.out.print("Nhập mô tả mới: ");
                    String updateDesc = scanner.nextLine();

                    // Tạo một đối tượng exercise05.Category tạm thời chứa thông tin update
                    Category updatedData = new Category(updateId, updateName, updateDesc);
                    categoryManager.updateCategory(updatedData);
                    break;

                case 4:
                    System.out.print("Nhập ID danh mục cần xóa: ");
                    int deleteId = Integer.parseInt(scanner.nextLine());
                    categoryManager.deleteById(deleteId);
                    break;

                case 5:
                    System.out.println("=> Đang thoát chương trình. Tạm biệt!");
                    isRunning = false;
                    break;

                default:
                    System.out.println("=> Lựa chọn không hợp lệ, vui lòng thử lại.");
            }
        }
        scanner.close();
    }

    public static class Category {
        private int id;
        private String name;
        private String description;

        // Constructor không tham số
        public Category() {
        }

        // Constructor full tham số
        public Category(int id, String name, String description) {
            this.id = id;
            this.name = name;
            this.description = description;
        }

        // Getters and Setters
        public int getId() { return id; }
        public void setId(int id) { this.id = id; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }

        @Override
        public String toString() {
            return "ID: " + id + " | Tên: " + name + " | Mô tả: " + description;
        }
    }

    // Interface định nghĩa hợp đồng CRUD
    public static interface ICRUD {
        Category[] findAll();
        void addCategory(Category category);
        void updateCategory(Category category);
        void deleteById(int id);
    }

    public static class CategoryManagement implements ICRUD {
        // Khởi tạo mảng tĩnh với sức chứa 100 phần tử
        private Category[] categories = new Category[100];
        // Biến size để theo dõi số lượng phần tử thực tế đang có trong mảng
        private int size = 0;

        @Override
        public Category[] findAll() {
            // Trả về một mảng mới chỉ chứa các phần tử có dữ liệu thật (tránh trả về phần tử null)
            Category[] actualData = new Category[size];
            for (int i = 0; i < size; i++) {
                actualData[i] = categories[i];
            }
            return actualData;
        }

        @Override
        public void addCategory(Category category) {
            if (size < categories.length) {
                categories[size] = category;
                size++; // Tăng biến đếm sau khi thêm thành công
                System.out.println("=> Thêm danh mục thành công!");
            } else {
                System.out.println("=> Lỗi: Bộ nhớ mảng đã đầy!");
            }
        }

        @Override
        public void updateCategory(Category category) {
            boolean isUpdated = false;
            for (int i = 0; i < size; i++) {
                // Tìm danh mục theo ID
                if (categories[i].getId() == category.getId()) {
                    // Cập nhật thông tin mới
                    categories[i].setName(category.getName());
                    categories[i].setDescription(category.getDescription());
                    System.out.println("=> Cập nhật danh mục thành công!");
                    isUpdated = true;
                    break;
                }
            }
            if (!isUpdated) {
                System.out.println("=> Không tìm thấy danh mục với ID: " + category.getId());
            }
        }

        @Override
        public void deleteById(int id) {
            int indexToDelete = -1;
            // 1. Tìm vị trí (index) của phần tử cần xóa
            for (int i = 0; i < size; i++) {
                if (categories[i].getId() == id) {
                    indexToDelete = i;
                    break;
                }
            }

            // 2. Thực hiện xóa và dịch chuyển mảng
            if (indexToDelete != -1) {
                for (int i = indexToDelete; i < size - 1; i++) {
                    categories[i] = categories[i + 1]; // Dịch phần tử bên phải sang trái
                }
                categories[size - 1] = null; // Xóa phần tử thừa ở cuối
                size--; // Giảm số lượng phần tử
                System.out.println("=> Xóa danh mục thành công!");
            } else {
                System.out.println("=> Không tìm thấy danh mục với ID: " + id);
            }
        }
    }
}
