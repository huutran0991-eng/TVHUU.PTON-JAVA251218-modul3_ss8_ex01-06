import java.util.ArrayList;
import java.util.List;

public class exercise01 {
    public static void main(String[] args) {
        // Tạo danh sách kiểu exercise01.Animals nhưng chứa các đối tượng con (Đa hình)
        List<Animals> animalList = new ArrayList<>();

        // Thêm dữ liệu hợp lệ
        animalList.add(new Dog("Cậu Vàng", 3, "Chó cỏ Việt Nam"));
        animalList.add(new Cat("Mimi", 2, "Trắng"));

        // Thêm dữ liệu KHÔNG hợp lệ để kiểm tra Validation
        System.out.println("--- Kiểm tra dữ liệu đầu vào ---");
        animalList.add(new Dog("", -5, "Husky")); // Tên rỗng và tuổi âm

        System.out.println("\n--- DANH SÁCH ĐỘNG VẬT ---");
        for (Animals animal : animalList) {
            // Đa hình: Trình biên dịch sẽ tự động biết phải gọi displayInfo() và makeSound() của exercise01.Dog hay exercise01.Cat
            animal.displayInfo();
            System.out.println("Tiếng kêu: " + animal.makeSound());
            System.out.println("-------------------------");
        }
    }

    public static class Dog extends Animals {
        private String breed;

        public Dog(String name, int age, String breed) {
            super(name, age); // Gọi Constructor của lớp cha (exercise01.Animals)
            this.breed = breed;
        }

        public String getBreed() { return breed; }
        public void setBreed(String breed) { this.breed = breed; }

        @Override
        public void displayInfo() {
            super.displayInfo(); // Gọi phương thức in thông tin của lớp cha
            System.out.println(" | Giống chó: " + breed); // Bổ sung thêm thông tin riêng
        }

        @Override
        public String makeSound() {
            return "Woof Woof";
        }
    }

    public static class Cat extends Animals {
        private String furColor;

        public Cat(String name, int age, String furColor) {
            super(name, age);
            this.furColor = furColor;
        }

        public String getFurColor() { return furColor; }
        public void setFurColor(String furColor) { this.furColor = furColor; }

        @Override
        public void displayInfo() {
            super.displayInfo();
            System.out.println(" | Màu lông: " + furColor);
        }

        @Override
        public String makeSound() {
            return "Meow Meow";
        }
    }

    public static class Animals {
        // Sử dụng private để đảm bảo tính đóng gói an toàn nhất
        private String name;
        private int age;

        // Constructor
        public Animals(String name, int age) {
            setName(name); // Gọi setter để tận dụng logic kiểm tra hợp lệ
            setAge(age);
        }

        // Getters & Setters có chứa logic kiểm tra (Validation)
        public String getName() {
            return name;
        }

        public void setName(String name) {
            if (name == null || name.trim().isEmpty()) {
                System.out.println("Cảnh báo: Tên không được để trống. Đặt mặc định là 'Unknown'.");
                this.name = "Unknown";
            } else {
                this.name = name;
            }
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            if (age < 0) {
                System.out.println("Cảnh báo: Tuổi không hợp lệ (" + age + "). Đặt mặc định là 0.");
                this.age = 0;
            } else {
                this.age = age;
            }
        }

        // Phương thức chung
        public void displayInfo() {
            System.out.print("Tên: " + name + " | Tuổi: " + age);
        }

        public String makeSound() {
            return "Some generic sound";
        }
    }
}