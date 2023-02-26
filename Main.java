
import java.util.*;
import java.io.Console;

class User
{
    String id;
    String fullName;

    User(){}
    public User(String id,String fullName)
    {
        this.id=id;
        this.fullName=fullName;
    }


    public String getId()
    {
        return id;
    }

    public String getFullName()
    {
        return fullName;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }
}

class Student extends User{
    //String courseName;
    String password;
    Map<String, LinkedList<String>> marksData=new HashMap<>();
    Student(){}
    Student(String sid,String sname,String password)
    {
        super(sid,sname);
        this.password=password;
    }

    public Map<String, LinkedList<String>> getMarksData()
    {
        return marksData;
    }

    public void setMarksData(Map<String, LinkedList<String>> marksData)
    {
        this.marksData = marksData;
    }
    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void getFullName(String fullName)
    {
        this.fullName = fullName;
    }

    @Override
    public String toString()
    {
        return "Student{" +
                "marksData=" + marksData +
                ", id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}

class Teacher extends User
{
    String password;
    String courseId;
    String courseName;
    String criteria;

    //public Map<String,Teacher> teacherData=new HashMap<String,Teacher>();

    Teacher(){}
    Teacher(String name,String username,String password)
    {
        super(username,name);
        this.password=password;
        //System.out.println(this.id);
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getCourseId()
    {
        return courseId;
    }

    public void setCourseId(String courseId)
    {
        this.courseId = courseId;
    }

    public String getCourseName()
    {
        return courseName;
    }

    public void setCourseName(String courseName)
    {
        this.courseName = courseName;
    }

    public String getCriteria()
    {
        return criteria;
    }

    public void setCriteria(String criteria)
    {
        this.criteria = criteria;
    }

    public void print()
    {
        System.out.println(this.fullName);
    }
}

class Operations extends Teacher{
    Scanner sc=new Scanner(System.in);
    //List<Teacher> teacherData=new ArrayList<>();
    Map<String,Teacher> teacherData=new HashMap<>();
    Map<String,Student> studentData=new HashMap<>();
    public void login()
    {
        String choice="";
        do
        {
            System.out.println("\n=============== Login ===============");
            System.out.println("1. Teacher");
            System.out.println("2. Student");
            System.out.println("3. Back to Main Menu");
            System.out.println("=====================================");
            System.out.print("\nEnter your choice: ");
            choice=sc.nextLine();
            switch (choice){
                case "1":
                    teacherLogin();
                    break;
                case "2":studentLogin();
                    break;
                case "3":
                    break;
                default:
                    System.out.println("\n! Invalid Choice !");
            }
        }while(!choice.equals("3"));
    }

    private void deleteStudent(String courseId){
        System.out.println("\nEnter ID of the student to be deleted: ");
        String sid=sc.nextLine();
        if (sid.matches("^[a-zA-Z\\s]*$"))
        {
            System.out.println("\n! ID should be numbers only !\n");
            return;
        }
        else
        {
            studentData.get(sid).getMarksData().remove(courseId);
            System.out.println("\nStudent Deleted Successfully...");
        }
    }

    boolean isValidTeacher(String userid, String pass){
        if (teacherData.containsKey(userid))
        {
            if (teacherData.get(userid).getPassword().equals(pass))
            {
                return true;
            }
        }
        return false;
    }

    boolean isValidStudent(String sid, String pass){
        if (studentData.containsKey(sid))
        {
            if (studentData.get(sid).getPassword().equals(pass))
            {
                return true;
            }
        }
        return false;
    }

    private void forgotPassword(String sid)
    {
        System.out.print("Enter Your ID: ");
        String stid = sc.nextLine();
        if (studentData.containsKey(stid))
        {
            if (studentData.get(sid).getId().equals(stid))
            {
                System.out.println("\nValid Student...");
                // System.out.print("Enter New Password: ");
                String uPassword = printPassword();
                if(uPassword.length() < 8)
                {
                    System.out.println("\n! Password must be atleast 8 characters long !");
                }
                else{
                    studentData.get(sid).setPassword(uPassword);
                    System.out.println("\nPassword Updated Successfully...");
                }
            }
        }
        else
        {
            System.out.println("\n! ID not valid.. Try again !");
            forgotPassword(sid);
        }
    }

    //login function
    private void studentDetails(String sid)
    {
        System.out.println("\n\nID: " + studentData.get(sid).getId());
        System.out.println("Name: " + studentData.get(sid).getFullName() );
        System.out.println("Course ID" + "\t\tCourse Name");
        System.out.println(studentData.get(sid).getMarksData().get(0));
        // System.out.println(studentData.get(sid).getPassword());
    }

    private void teacherLogin()
    {
        System.out.println("\n=========== Teacher Login ===========");
        System.out.print("Enter username: ");
        String userid=sc.nextLine();
         System.out.print("Enter password: ");
         String password=sc.nextLine();
//        String password = printPassword();
        // System.out.println(teacherData);
        Teacher t=teacherData.get(userid);
        //System.out.println(teacherData.containsKey(userid));
        if(!teacherData.containsKey(userid))
        {
            System.out.println("\n! Username doesn't exist.. Try Again !");
            return;
        }
        boolean check;
        check = isValidTeacher(userid, password);
        if(check){
            System.out.println("\nLogin Successful...");
            System.out.println("\nEnter course deatils...");
            if(t.getCourseId()==null)
            {
                System.out.println("\n========== Course Details =========");
                System.out.print("Enter course ID: ");
                String courseId=sc.nextLine();
                System.out.print("Enter course name: ");
                String courseName=sc.nextLine();
                System.out.print("Enter course passing criteria: ");
                String criteria=sc.nextLine();
                t.setCourseId(courseId);
                t.setCourseName(courseName);
                t.setCriteria(criteria);
                //addStudents(courseId,courseName);
            }
            courseId=teacherData.get(userid).getCourseId();
            courseName=teacherData.get(userid).getCourseName();
            String choice="";
            do
            {
                System.out.println("\n======== Student Management ========");
                System.out.println("1. Add Students");
                System.out.println("2. View Students");
                System.out.println("3. Update Student Info");
                System.out.println("4. Add Marks");
                System.out.println("5. Delete student from course");
                System.out.println("6. Back");
                System.out.println("======================================");
                System.out.print("\nEnter your choice: ");
                choice=sc.nextLine();
                switch (choice)
                {
                    case "1":
                        addStudentsToCourse(courseId,courseName);
                        break;
                    case "2":
                        viewStudents(courseId);
                        break;
                    case "3":
                        updateStudent(courseId);
                        break;
                    case "4":
                        addMarksStudent(courseId);
                        break;
                    case "5":deleteStudent(courseId);
                        break;
                    case "6":
                        break;
                    default:
                        System.out.println("\n! Invalid Choice !");
                }
            }while(!choice.equals("6"));
        }
        else{
            System.out.println("\n! Wrong Password !\n");
            System.out.print("Forgot password(Y/N)? ");
            char option = sc.next().charAt(0);
            sc.nextLine();
            if (option == 'y' || option == 'Y')
            {
                // forgotPassword(userid);
            }
            else if (option == 'n' || option == 'N')
            {
                return;
            }
            else
            {
                System.out.println("\nYou entered wrong character....");
            }
        }
    }//teacher login

    private void studentLogin()
    {
        System.out.println("\n=========== Student Login ===========");
        System.out.print("Enter your id: ");
        String sid=sc.nextLine();
         System.out.print("Enter password: ");
         String password=sc.nextLine();
//        String password = printPassword();
        //check if id and pass match
        if(!studentData.containsKey(sid))
        {
            System.out.println("\n! ID does not exist.. Try Again !");
            return;
        }
        boolean check;
        check = isValidStudent(sid, password);
        if(check){
            studentDetails(sid);
        }
        // System.out.println(studentData.get(sid)); //here show report card
        else{
            System.out.println("\n! Wrong Password !\n");
            System.out.print("Forgot password(Y/N)? ");
            char option = sc.next().charAt(0);
            sc.nextLine();
            if (option == 'y' || option == 'Y')
            {
                forgotPassword(sid);
            }
            else if (option == 'n' || option == 'N')
            {
                return;
            }
            else
            {
                System.out.println("\nYou entered wrong character....");
            }
        }
    }//student login

    private void addMarksStudent(String courseId)
    {
        System.out.println("\nList of student enrolled in the course"); //format all print messge
        System.out.println("=======================================");
        viewStudents(courseId);
        System.out.println("=======================================");
        System.out.println("Enter id of student to update marks: ");
        String sid=sc.nextLine();
        //check condition if id enrolled in that course else print message and return
        if(!studentData.containsKey(sid)){
            System.out.println("! ID doesn't exist.. Try Again !");
        }
        updateMark(sid,courseId);
        //also if there short name as above in code then give propername->sid->studentId
    }

    private void updateStudent(String courseId){
        System.out.println("List of student enrolled in the course");
        System.out.println("=======================================");
        viewStudents(courseId);
        System.out.println("=======================================");
        System.out.println("Enter student ID to update: ");
        String sid=sc.nextLine();
        if(!studentData.containsKey(sid)){
            System.out.println("! ID doesn't exist.. Try Again !");
        }
        else{
            System.out.println("========= Update Student Info ========");
            System.out.println("1. Update Name");
            System.out.println("2. Update Mark");
            System.out.println("3. Back");

            System.out.print("\nEnter your choice: ");
            String choice;
            choice=sc.nextLine();

            switch (choice)
            {
                case "1":
                    updateName(sid,courseId);
                    break;
                case "2":
                    updateMark(sid,courseId);
                    break;
                case "3":
                    break;
                default:
                    System.out.println("\n! Invalid Choice !");
            }
        }
    }

    private void updateName(String studentId,String courseId){
        System.out.println("Previous Name: "+studentData.get(studentId).getFullName());
        System.out.println("Enter New Name: ");
        String newName=sc.nextLine();
        if(!newName.matches("^[a-zA-Z\\s]*$"))
        {
            System.out.println("\n! Name should be only characters !\n");
            return;
        }
        else
        {
            studentData.get(studentId).setFullName(newName);//.get(courseId).add(1,newMarks)
            System.out.println("\nName Updated Successfully...");
        }
    }

    private void updateMark(String studentId,String courseId)
    {
        System.out.println("Id: "+studentId);
        System.out.println("Name: "+studentData.get(studentId).getFullName());
        //explanation of below print line for marks
        //Map<id,Student(class)>
        //          |
        //       Map<courseId,List(courseName(0 index),Makrs(1st index)
        //from gateMarksData we fetch map associate with course id of student id
        System.out.println("Previous Marks: "+studentData.get(studentId).getMarksData().get(courseId).get(1));
        System.out.println("Enter new Marks: ");
        String newMarks=sc.nextLine();
        if (newMarks.matches("^[a-zA-Z\\s]*$"))
        {
            System.out.println("\n! Marks should be numbers only !\n");
            return;
        }
        else
        {
            studentData.get(studentId).getMarksData().get(courseId).set(1,newMarks);//.get(courseId).add(1,newMarks)
            System.out.println("\nMarks Updated Successfully...");
        }
    }

    private void viewStudents(String courseId)
    {
        boolean flag=false;
        for(String id: studentData.keySet())
        {
            // System.out.println(id+" "+studentData.get(id).getMarksData());

            if(studentData.get(id).getMarksData().size()>0)
            {

                if(studentData.get(id).getMarksData().containsKey(courseId))
                {
                    flag=true;
                    System.out.println("\nID\t\tName");
                    System.out.println("--\t\t----");
                    System.out.println(id+"\t\t"+studentData.get(id).getFullName());
                }
                //here check where to add else condition or to print no student in course
            }
        }
        if(!flag)
            System.out.println("No student registered in your course yet");
    }

    String printPassword()
    {
        Console console = System.console();
        char[] p = console.readPassword("Enter Password:");
        for (int i = 0; i < p.length; i++)
        {
            console.printf("*");
        }
        return String.valueOf(p);
    }

    boolean isValidUserName(String username){
        if (teacherData.containsKey(username))
        {
            System.out.println("\n! Username already taken..\nPlease try for another username !");
            return false;
        }
        else if (!username.equals(username.toLowerCase()))
        {
            System.out.println("\n! Use only lowercase characters in username!");
            return false;
        }
        else if (username.contains(" "))
        {
            System.out.println("\n! No spaces allowed in username !");
            return false;
        }
        else if (!username.matches("^[a-z0-9_]+$"))
        {
            System.out.println("\n! No special characters allowed in username !");
            return false;
        }
        return true;
    }

    private void viewAllStudent()
    {
        //here check if teacher  try to show his stu and no stu assign course that means list is empty then show message "no student in your course"
        System.out.println("\nID\t\tName");
        System.out.println("--\t\t----");
        for(String id: studentData.keySet())
        {
            System.out.println(id+"\t\t"+studentData.get(id).getFullName());
        }
    }

    private void addStudentsToCourse(String courseId,String courseName)
    {

        //here is list of student <- print message
        System.out.println("List of the students present in the system");
        System.out.println("============================================");
        viewAllStudent();
        System.out.println("============================================");
        System.out.print("\nEnter ID of the student you want to add: ");
        String id=sc.nextLine();

        if(!studentData.containsKey(id))
        {
            System.out.println("\n! ID doesn't found !");
            return;
        }
        else if (id.matches("^[a-zA-Z\\s]*$"))
        {
            System.out.println("\n! ID should be numbers only !\n");
            return;
        }
        else
        {
            Student s=studentData.get(id);
            //check if map is empty
            Map<String,LinkedList<String>> mp=new HashMap<>();
            if(s.getMarksData().isEmpty())
            {
                System.out.println("\nStudent added successfully...");
                mp=new HashMap<String,LinkedList<String>>();
                mp.put(courseId,new LinkedList<String>(Arrays.asList(courseName,"-")));
            }
            if(!s.getMarksData().isEmpty())
            {
                mp=s.getMarksData();
                mp.put(courseId,new LinkedList<String>(Arrays.asList(courseName,"-")));
                //System.out.println("Map : "+mp);
            }
            //Map<String,Integer> mp=new HashMap<String,Integer>() {{put(courseId,0);}};
            s.setMarksData(mp);
        }
    }

    //registrations of both
    private void teacherRegister()
    {
        //VALIDATIONS
        System.out.println("\n======= Teacher Registration ========");
        System.out.print("Enter Your Full Name: ");
        String name=sc.nextLine();
        if (!name.matches("^[a-zA-Z\\s]+$"))
        {
            System.out.println("\n! Only Characters Allowed in Full Name !");
            return;
        }
        System.out.print("Enter Username: ");
        String username=sc.nextLine();
        boolean validUserName = isValidUserName(username);
        if (!validUserName)
        {
            return;
        }
        String password=sc.nextLine();
//        String password = printPassword();
        if (password.length() < 8) {
            System.out.print("\n\n! Password must be atleast 8 characters long !\n");
        }
        else{
            Teacher t=new Teacher(name,username,password);
            teacherData.put(username,t);
            System.out.println("\nRegistration Successful...");
        }
    }

    private void studentRegister()
    {
        System.out.println("\n======= Student Registration ========");
        System.out.print("Enter Your ID: ");
        String sid=sc.nextLine();
        if(studentData.containsKey(sid))
        {
            System.out.println("\n! ID already registered !");
        }
        else if (sid.matches("^[a-zA-Z\\s]*$"))
        {
            System.out.println("\n! ID should be numbers only !\n");
            return;
        }
        else
        {
            System.out.print("Enter Your Name: ");
            String sname=sc.nextLine();
            if(!sname.matches("^[a-zA-Z\\s]*$"))
            {
                System.out.println("\n! Name should be only characters !\n");
                return;
            }
            String password=sc.nextLine();
//            String password = printPassword();
            if (password.length() < 8) {
                System.out.print("\n\n! Password must be atleast 8 characters long !\n");
            }
            else{
                studentData.put(sid,new Student(sid,sname,password));
                System.out.println("\nRegistration Successful...");
            }
        }
    }

    void registration()
    {
        String choice;
        do
        {
            System.out.println("\n=========== Registration ============");
            System.out.println("1. Teacher");
            System.out.println("2. Student");
            System.out.println("3. Back to Main Menu");
            System.out.println("=====================================");
            System.out.print("\nEnter your choice: ");
            choice=sc.nextLine();
            switch (choice){
                case "1": teacherRegister();
                    break;
                case "2":studentRegister();
                    break;
                case "3":break;
                default:
                    System.out.println("\n! Invalid Choice !");
            }
        }while(!choice.equals("3"));
    }
}

class Menu{
    Scanner sc=new Scanner(System.in);
    public void options(){
        //Scanner sc=new Scanner(System.in);
        Operations op=new Operations();
        String choice="";
        do
        {
            System.out.println("\n============ Main Menu ==============");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.println("=====================================");
            System.out.print("\nEnter your choice: ");
            choice=sc.nextLine();
            switch (choice)
            {
                case "1":
                    op.registration();
                    break;
                case "2":
                    //when student try to login and no course assign to him then show message
                    op.login();
                    break;
                case "3":break;
                default:
                    System.out.println("\n! Invalid Choice !\n");
            }
        }while(!choice.equals("3"));
    }
}

public class Main
{
    public static void main(String args[])
    {
        System.out.println("\n|- Welcome to a console based E-campus system -|");
        new Menu().options();
    }
}