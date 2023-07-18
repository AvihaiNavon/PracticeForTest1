package org.example;

import java.io.*;
import java.util.*;

public class FilePractice {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        new FilePractice();
    }

    private File file;

    public FilePractice() {
        //createFile();// בניית קובץ טקסט
        //writeToFile();// כתיבה על קובץ טקסט
        //readFromFile();// קריאה מקובץ טקסט
        //createFileBinary();// בניית קובץ בינארי
        //writingBinaryFile();// כתיבה על קובץ בינארי
        //readFromBinaryFile();// קריאה מקובץ בינארי
        //studentGrades();// תרגיל כיתה

    }

    public void createFile() {
        String address = "C:\\Users\\אביחי\\Desktop\\מדעי המחשב\\file\\first.txt";
        file = new File(address);// הגדרת משתנה ומתן כתובת
        try {
            boolean success = file.createNewFile();//מצריך try/catch יוצר את הקובץ- יש לתת כתובת עם שם וסיומת מתאימים
            if (success) {
                System.out.println("File created successfully");
            } else {
                System.out.println("File already exists");
            }

        } catch (IOException e) { // תצורת חריגה שקשורה לבעיות קלט פלט , בעיית תקשורת IO
            e.printStackTrace();
            System.out.println("There is a problem that does not allow the creation of the file");
        }
    }

    public void writeToFile() {
        String text = "avihai navon";
        //כתיבה בתצורה רגילה
        if (file != null && file.exists()) {// בודק שהקובץ כבר הוגדר
            try {
                FileWriter fileWriter = new FileWriter(file); // אובייקט אשר מאפשר את הכתיבה על הקובץ
                fileWriter.write(text);//ביצוע פעולת הכתיבה
                fileWriter.close();//תמיד חייב לסגור על מנת שמישהו אחד יעבוד על הקובץ כל פעם

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        //כתיבה בשימוש עם BufferedWriter
        String fullText = "avihai navon" + "\n" + " noa navon" + "\n" + "itai navon";
        if (file != null && file.exists()) {
            try {
                FileWriter fileWriter = new FileWriter(file); // הכנסת הקובץ למשתנה הכתיבה של קובץ טקסט
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(fullText); // עם המידע גדול על מנת לצמצם את הזמן ביצוע הפעולה יוצרים פעולה שמחלקת את המידע לנתחים
                bufferedWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void readFromFile() {
        List<String> lines = new ArrayList<>();
        BufferedReader bufferedReader = null;// אובייקט שקורא שורות
        FileReader fileReader = null;// פותח וקורא את המסמך
        try {
            fileReader = new FileReader(file);// מקבל את הקובץ קריאה
            bufferedReader = new BufferedReader(fileReader);//קורא השורות מקבל את הטקסט
            String line;//מייצג שורה
            do {
                line = bufferedReader.readLine();// מבצע את פעולת הקריאת שורה ועובר לשורה הבאה
                lines.add(line);
            } while (line != null);
            bufferedReader.close();
            fileReader.close();
            System.out.println(lines);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void createFileBinary() { // יצירת קובץ בינרי
        String address = "C:\\Users\\אביחי\\Desktop\\מדעי המחשב\\file\\first.bin";
        file = new File(address);// הגדרת משתנה ומתן כתובת
        try {
            boolean success = file.createNewFile();//מצריך try/catch יוצר את הקובץ- יש לתת כתובת עם שם וסיומת מתאימים
            if (success) {
                System.out.println("File created successfully");
            } else {
                System.out.println("File already exists");
            }

        } catch (IOException e) { // תצורת חריגה שקשורה לבעיות קלט פלט , בעיית תקשורת IO
            e.printStackTrace();
            System.out.println("There is a problem that does not allow the creation of the file");
        }
    }

    public void writingBinaryFile() { // לכתוב על קובץ בינארי
        Student student = new Student("avihai", 123456);

        if (file != null && file.exists()) {// בודק שהקובץ כבר הוגדר
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);//המידע  המסורלז נישמר על הדיסק, המחלקה שכותבת על הקובץ
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);//מבצעת סריאליזציה על האובייקטים בקובץ
                objectOutputStream.writeObject(student);// כותב את האובייקטים
                objectOutputStream.close();
                fileOutputStream.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void readFromBinaryFile() {
        Student student = null;
        if (file != null && file.exists()) {
            FileInputStream fileInputStream = null;
            try {
                fileInputStream = new FileInputStream(file);// לוקח מהקובץ, לשים לב ל input
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);// לוקח את האובייקט
                student = (Student) objectInputStream.readObject();
                objectInputStream.close();
                fileInputStream.close();
                System.out.println(student);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public void studentGrades() {
        Scanner scanner = new Scanner(System.in);
        File fileStudentGrades = new File("C:\\Users\\אביחי\\Desktop\\מדעי המחשב\\file\\StudentGrades.txt");
        try {
            boolean success = fileStudentGrades.createNewFile();
            if (success) {
                System.out.println("The file was created successfully");
            } else System.out.println("The file already exists");

            if (fileStudentGrades != null && fileStudentGrades.exists()) {
                FileReader fileReader = new FileReader(fileStudentGrades);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line = null;
                do {
                    line = bufferedReader.readLine();
                    if (line != null) System.out.println(line);
                } while (line != null);
                bufferedReader.close();
                fileReader.close();

            }


            HashMap<String, Integer> exercises = new HashMap<>();
            exercises.put("3+2=", 5);
            exercises.put("5+4=", 9);
            exercises.put("8+2=", 10);

            System.out.println("Answer the following exercises: ");
            int result = 0;
            int answer = 0;
            for (String exercise : exercises.keySet()) {
                System.out.println(exercise);
                System.out.println("Enter your answer: ");
                try {
                    answer = scanner.nextInt();

                    if (answer == exercises.get(exercise)) {
                        result++;
                    } else {
                        result -= 3;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            String finalResult = String.valueOf(result);
            if (fileStudentGrades != null && fileStudentGrades.exists()) {
                FileWriter fileWriter = new FileWriter(fileStudentGrades);
                fileWriter.write(finalResult);
                fileWriter.close();
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}

class Student implements Serializable { //ממשק מחייב במידה ורוצים ליצור קובץ מהאובייקט
    private String name;
    private Integer id;

    public Student(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
