public class exercise03 {
    public static void main(String[] args) {
        System.out.println("--- HỆ THỐNG QUẢN LÝ HÌNH HỌC VÀ MÀU SẮC ---");

        // Khởi tạo các đối tượng hình học
        Circle myCircle = new Circle(5.0);
        Rectangle myRectangle = new Rectangle(10.0, 4.0);
        Square mySquare = new Square(7.0);

        // Hiển thị trạng thái ban đầu
        System.out.println("\n[Trước khi tô màu]");
        myCircle.displayInfo();
        myRectangle.displayInfo();
        mySquare.displayInfo();

        // Sử dụng phương thức từ interface exercise03.Colorable để thiết lập màu
        myCircle.setColor("Đỏ (Red)");
        myRectangle.setColor("Xanh lá (Green)");
        mySquare.setColor("Vàng (Yellow)");

        // Hiển thị trạng thái sau khi tô màu
        System.out.println("\n[Sau khi tô màu]");
        myCircle.displayInfo();
        myRectangle.displayInfo();
        mySquare.displayInfo();
    }

    // Khai báo Interface (Giao diện)
    public static interface Colorable {
        // Phương thức trong interface mặc định là public và abstract
        void setColor(String color);
    }

    // Sử dụng từ khóa implements để thực thi interface
    public static class Circle implements Colorable {
        private double radius;
        private String color; // Thuộc tính lưu trữ màu sắc hiện tại

        public Circle(double radius) {
            this.radius = radius;
            this.color = "Chưa có màu"; // Màu mặc định
        }

        // Bắt buộc phải ghi đè phương thức từ exercise03.Colorable
        @Override
        public void setColor(String color) {
            this.color = color;
        }

        public void displayInfo() {
            System.out.println("Hình tròn [Bán kính: " + radius + "] - Màu sắc: " + color);
        }
    }

    public static class Square implements Colorable {
        private double side;
        private String color;

        public Square(double side) {
            this.side = side;
            this.color = "Chưa có màu";
        }

        @Override
        public void setColor(String color) {
            this.color = color;
        }

        public void displayInfo() {
            System.out.println("Hình vuông [Cạnh: " + side + "] - Màu sắc: " + color);
        }
    }

    public static class Rectangle implements Colorable {
        private double length;
        private double width;
        private String color;

        public Rectangle(double length, double width) {
            this.length = length;
            this.width = width;
            this.color = "Chưa có màu";
        }

        @Override
        public void setColor(String color) {
            this.color = color;
        }

        public void displayInfo() {
            System.out.println("Hình chữ nhật [Dài: " + length + " | Rộng: " + width + "] - Màu sắc: " + color);
        }
    }
}
