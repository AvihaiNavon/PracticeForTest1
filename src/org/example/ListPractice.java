package org.example;

import java.util.*;

public class ListPractice {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        new ListPractice();
    }

    public ListPractice() {
        practiceArrayList();
        //practiceList();
        // practiceHashSet();
        //practiceHashMap();


    }

    public void practiceArrayList() {
        //* מבוסס על מערך, קיים חשיבות לסדר ויש כפיליות
        ArrayList<Integer> number = new ArrayList<>();
        number.add(5);// נכנס למיקום האחרון
        number.add(4);
        number.add(3);
        System.out.println(number);//5,4,3
        System.out.println(number.get(0));// מדפיס מיקום ראשון 5
        System.out.println(number.size());// גודל הרשימה לא כולל אפס 3
        number.remove(2);//מסיר מהמקום השני
        System.out.println(number);//5,4
        number.add(1, 7);//להוסיף איבר לפי מיקום
        System.out.println(number);
        boolean result = number.contains(5);
        System.out.println(result);
        number.add(5);
        System.out.println(number);
        ;
        System.out.println(number.remove(1));
        System.out.println(number);


    }

    public void practiceList() {
        //* מבוסס על מערך, להגדיר רשימה מראש
        List<Integer> number = Arrays.asList(1, 2, 3);
        //רשימה קבועה שלא ניתן להוסיף או להוציא ממנה
        List<String> names = Arrays.asList("AVIHAI", "AVI", "AVIH");
        System.out.println(names);
        //רשימה רגילה
        List<String> names2 = new ArrayList<>();
        names2.add("avihai");
        names2.add("noa");
        names2.add("itay");
        System.out.println(names2.contains("noa"));// ניתן לעשות על סוג רשימה זו גם השוואה במקום לולאה
        System.out.println(names2);
        String AllNames = "";
        for (String name : names2) {
            AllNames += name + ",";

        }
        System.out.println(AllNames);

    }

    public void practiceHashSet() {
        // אין חשיבות לסדר ולא מאפשר כפיליות
        HashSet<String> names = new HashSet<>();
        names.add("avihai");
        names.add("avihai");
        names.add("noa");
        names.add("itai");
        System.out.println(names);//noa,itai,avihai  ללא כפילות
        List<String> namesArrayList = new ArrayList<>(); // יצירת רשימה רגילה
        namesArrayList.add("avihai");
        namesArrayList.add("noa");
        namesArrayList.add("itay");
        namesArrayList.add("avihai");
        System.out.println(namesArrayList);
        HashSet<String> newNamesArrayList = new HashSet<>(namesArrayList);// עושה המרה של הרשימה וכך מסיר את הכפיליות
        System.out.println(newNamesArrayList);
        System.out.println(newNamesArrayList.isEmpty());// בודק אם רשימה ריקה


    }

    public void practiceHashMap() {
        HashMap<String, Integer> id = new HashMap<>();
        id.put("avihai", 13456789);
        id.put("avihai", 25462154);// מוריד כפליות של key ומכניס ערך אחרון
        id.put("noa", 123431543);
        id.put("itai", 123431543);
        System.out.println(id);
        System.out.println(id.get(0));// לא יתן תוצאה מכיוון שאין key של 0
        System.out.println(id.get("itai"));// יחזיר את ה value בלבד
        id.remove("avihai");
        System.out.println(id);
        System.out.println(id.containsKey("itai"));// בודק אם קיים מפתח כזה ברשימה
        System.out.println(id.containsValue(123431543));//בודק אם קיים ערך value ברשימה
        System.out.println(id.isEmpty());//בודק אם ריק
        System.out.println(id.keySet());//מחזיר את המפתחות
        List<String> namesId = new ArrayList<>();
        System.out.println("First way:");
        for (String name : id.keySet()) {// לוקח מפתח מפתח והופך לרשימה
            namesId.add(name);
        }
        System.out.println(namesId);
        ;
        List<Integer> numberId = new ArrayList<>();
        for (Integer num : id.values()) { // דרך לחלף את הערכים לרשימה
            numberId.add(num);
        }
        System.out.println(numberId);
        // דרך נוספת לחלף את הערכים לרשימה חדשה
        System.out.println("Second way:");
        List<String> namesId2 = new ArrayList<>(id.keySet());
        System.out.println(namesId2);
        List<Integer> numberId2 = new ArrayList<>(id.values());
        System.out.println(numberId2);


    }
}
