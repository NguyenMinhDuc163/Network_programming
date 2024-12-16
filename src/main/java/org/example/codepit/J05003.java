package org.example.codepit;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

class Student{
    private String studentID, studentName, classRoom, birth;
    private double gpa;

    public Student(int index, String studentName, String classRoom, String birth, double gpa) {
        this.studentID = String.format("B20DCCN%03d", index);
        this.studentName = studentName;
        this.classRoom = classRoom;
        this.birth = birth;
        this.gpa = gpa;
    }

    String normalizeBirth(String birth){
        StringBuilder sb = new StringBuilder(birth);
        if(sb.charAt(1) == '/'){
            sb.insert(0, "0");
        }
        if(sb.charAt(4) == '/'){
            sb.insert(3, "0");
        }
        return sb.toString();
    }

    String normalizeName(String name){
        String[] words = name.trim().split("\\s+");
        String res = "";
        for (String word : words) {
            res += word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase() + " ";
        }
        return res.substring(0, res.length() - 1);
    }

    public double getGpa() {
        return gpa;
    }

    @Override
    public String toString() {
        return String.join(" ", studentID, normalizeName(studentName), classRoom, normalizeBirth(birth), String.format("%.2f", gpa));
    }
}
public class J05003 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Student> students = new ArrayList<>();
        for(int i = 1 ; i <= n; i++){
            sc.nextLine();
            students.add(new Student(i, sc.nextLine(), sc.nextLine(), sc.nextLine(), sc.nextDouble()));
        }
        students.stream().sorted(Comparator.comparing(Student::getGpa).reversed()).forEach(System.out::println);
    }
}