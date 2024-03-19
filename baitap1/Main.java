import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhao so luong sinh viên: ");
        int n = scanner.nextInt();

        List<String> students = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            System.out.println("Nhao thong tin sinh viên " + (i + 1) + ":");
            System.out.print("Ma sinh viên: ");
            String maSV = scanner.next();
            System.out.print(" Tên: ");
            String Ten = scanner.next();
            System.out.print("sex: ");
            String gioiTinh = scanner.next().toLowerCase();
            System.out.print("Diem Python: ");
            double diemPython = scanner.nextDouble();
            System.out.print("Diem Java: ");
            double diemJava = scanner.nextDouble();

            students.add((maSV + ","+ Ten + ","+ gioiTinh + ","+ diemPython + ","+ diemJava));
        }

        // Ghi danh sách học viên ra file
        try (PrintWriter writer = new PrintWriter("input.txt")) {
            for (String student : students) {
                writer.println(student.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Đọc dữ liệu từ file
        List<Student> studentsFromFile = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                studentsFromFile.add(new Student(parts[0], parts[1], parts[2], Double.parseDouble(parts[3]), Double.parseDouble(parts[4])));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Sắp xếp học viên theo điểm trung bình giảm dần
        studentsFromFile.sort(Comparator.comparingDouble(Student::getAvg).reversed());

        // In thông tin học viên và ghi ra file
        System.out.println("Danh sách sinh viên sau khi sắp xếp:");
        try (PrintWriter writer = new PrintWriter("output.txt")) {
            for (Student student : studentsFromFile) {
                System.out.println(student);
                writer.println(student.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Nhập 
        System.out.print("Nhap tên sinh viên cần tìm: ");
        String searchName = scanner.next();

        // Tìm kiếm 
        boolean found = false;
        System.out.println("Danh sách sinh viên có  tên \"" + searchName + "\":");
        for (Student student : studentsFromFile) {
            if (student.getName().equals(searchName)) {
                System.out.println(student);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Không tìm thay sinh viên có tên \"" + searchName + "\".");
        }

        System.out.println("Danh sách sinh viên da dau:");
        for (Student student : studentsFromFile) {
            if (student.getAvg() >= 5) {
                System.out.println(student);
            }
        }
        
        System.out.println("Danh sách sinh vien da truot:");
        for (Student student : studentsFromFile) {
            if (student.getAvg() < 5) {
                System.out.println(student);
            }
            System.out.println("Danh sách sinh vien da dau:");
for (Student student2 : studentsFromFile) {
    if (student.getAvg() >= 5) {
        if (student.getGender().equals("Nam")) {
            System.out.println(student);
        }
    }
}

System.out.println("Danh sách dinh vien da truot:");
for (Student student2 : studentsFromFile) {
    if (student.getAvg() < 5) {
        if (student.getGender().equals("Nữ")) {
            System.out.println(student);
        }
    }
}
        
        }
        System.out.println("Danh sách sinh viên diem tb > 8 ");
        for (Student student : studentsFromFile) {
            if (student.getAvg() > 8) {
                System.out.println(student);
            }
        }
    }}