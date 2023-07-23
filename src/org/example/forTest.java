package org.example;

import java.util.*;

public class forTest {
    public static void main(String[] args) {

        final ShareResource<Student1> shareResource=new ShareResource<>();
        final GradeBook gradeBook=new GradeBook();
        Thread threadA=new Thread(()->{
            while (true){
                shareResource.addToList(new Student1());

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread threadB=new Thread(()->{
            while (true){
                Student1 student=shareResource.removeFromList();
                if(student!=null){
                    System.out.println("Processed: "+ student);
                    if(gradeBook.getTopStudent()==student){
                        gradeBook.updateTopStudent(null);
                    }
                }
                try {
                    Thread.sleep(3000); // צריך לעלות קצת על מנת שיבצע מחיקה
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread threadC=new Thread(()->{
            while (true){
                List<Student1> student1List=shareResource.getList();
                Collections.sort(student1List);
                System.out.println(student1List);
                if (student1List.size()>0) {
                    System.out.println(student1List.get((student1List.size()-1)));
                    gradeBook.updateTopStudent(student1List.get(0));


                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        threadA.start();
        threadB.start();
        threadC.start();
    }


}
class Student1 implements Comparable<Student1>{
    private static final List<String> FIRST_NAME = Arrays.asList("JOHN", "Emma", "Michal", "David", "emily");
    private static final List<String> LAST_NAME = Arrays.asList("SS", "PP", "LL", "CC", "HH");
    private static final List<String> COURSES = Arrays.asList("MATH", "SCIENCE", "HISTORY", "MUSIC", "ART");
    private static final Random RANDOM = new Random();
    private final String firstName;
    private final String lastName;
    private final Map<String, Integer> coursesGradens;

    public Student1() {
        this.firstName = FIRST_NAME.get(RANDOM.nextInt(FIRST_NAME.size())); // מגדיר שם תלמיד
        this.lastName = LAST_NAME.get(RANDOM.nextInt(LAST_NAME.size())); // מגדיר שם משפחה לתלמיד
        this.coursesGradens = new HashMap<>(); // יוצר מפה של קורסים וציונים
        COURSES.forEach(item -> coursesGradens.put(item, RANDOM.nextInt(101)));// נותן שמות לקורסים ונותן ציון לכל קורס
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student1 student1)) return false;
        return Objects.equals(firstName, student1.firstName) && Objects.equals(lastName, student1.lastName) && Objects.equals(coursesGradens, student1.coursesGradens);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, coursesGradens);
    }

    public int avg(){
        int sum= this.coursesGradens.values().stream().reduce(Integer::sum).orElse(0);
        return sum/this.coursesGradens.size();
    }


    @Override
    public String toString() {
        return "Student1{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", coursesGradens=" + coursesGradens +" avg: "+ avg()+
                '}';
    }



    @Override
    public int compareTo(Student1 o) {
        int avgDiff = this.avg() - o.avg();
        if (avgDiff > 0) {
            return 1;
        } else if (avgDiff < 0) {
            return -1;
        } else {
            return 0;
        }
    }
}
class ShareResource<T> {
        private final List<T> list = new ArrayList<>(); // הוספת אלמנט לרשימה
        private final Random random = new Random();

        public void addToList(T element) {
            if (!list.contains(element)) { // צריך לבדוק שפונקציית ההשווה קיימת כדי שיעבוד ההשוואה ברשימה
                list.add(element);
            }
        }
        public T removeFromList(){
            T element= null;
            if(!list.isEmpty()){
                element=list.remove(random.nextInt(list.size()));//// צריך לבדוק שפונקציית ההשווה קיימת כדי שיעבוד ההשוואה ברשימה
            }
            return element;
        }

    public List<T> getList() {
        return list;
    }
}


class GradeBook{
    private Student1 topStudent;
    public void updateTopStudent(Student1 student){
        this.topStudent=student;
    }
    public Student1 getTopStudent(){
        return this.topStudent;
    }
}