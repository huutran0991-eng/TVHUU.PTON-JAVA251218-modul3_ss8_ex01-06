public class exercise02 {
    public static void main(String[] args) {
        System.out.println("--- HỆ THỐNG QUẢN LÝ PHƯƠNG TIỆN ---");

        // Không thể làm thế này: exercise02.Vehicle v = new exercise02.Vehicle("Lỗi", 0); -> Sẽ bị lỗi biên dịch

        // Khởi tạo đối tượng exercise02.Car và exercise02.Bike
        Vehicle myCar = new Car("Toyota Camry", 220);
        Vehicle myBike = new Bike("Honda Winner", 140);

        // Gọi phương thức trên myCar
        myCar.start();        // Chạy logic dùng chung từ lớp cha
        myCar.displayInfo();  // Chạy logic riêng của lớp exercise02.Car

        System.out.println("-----------------------------------");

        // Gọi phương thức trên myBike
        myBike.start();       // Chạy logic dùng chung từ lớp cha
        myBike.displayInfo(); // Chạy logic riêng của lớp exercise02.Bike
    }

    // Khai báo lớp trừu tượng bằng từ khóa abstract
    public abstract static class Vehicle {
        protected String name;
        protected int speed;

        // Constructor của lớp trừu tượng (dùng để các lớp con gọi thông qua super)
        public Vehicle(String name, int speed) {
            this.name = name;
            this.speed = speed;
        }

        // Phương thức thông thường: Tất cả các lớp con đều dùng chung logic này
        public void start() {
            System.out.println("exercise02.Vehicle is starting...");
        }

        // Phương thức trừu tượng: Không có phần thân (body {}), kết thúc bằng dấu chấm phẩy
        // Bắt buộc các lớp kế thừa phải @Override
        public abstract void displayInfo();
    }

    public static class Car extends Vehicle {

        public Car(String name, int speed) {
            super(name, speed); // Gọi constructor của exercise02.Vehicle
        }

        // Bắt buộc phải ghi đè phương thức displayInfo()
        @Override
        public void displayInfo() {
            System.out.println("[exercise02.Car] Tên xe: " + name + " | Tốc độ tối đa: " + speed + " km/h");
        }
    }

    public static class Bike extends Vehicle {

        public Bike(String name, int speed) {
            super(name, speed);
        }

        // Bắt buộc phải ghi đè phương thức displayInfo()
        @Override
        public void displayInfo() {
            System.out.println("[exercise02.Bike] Tên xe máy: " + name + " | Tốc độ tối đa: " + speed + " km/h");
        }
    }
}
