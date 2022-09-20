import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.util.Date;
import java.text.SimpleDateFormat; 
public class ToDoList {
    String name;
	String description;
    Date dueDate;
    String status;
     
    public static List<ToDoList> currentList = new ArrayList<ToDoList>();
	public ToDoList(String name, String description, Date dueDate, String status) {
		this.name = name;
		this.description = description;
        this.dueDate= dueDate;
        this.status=status;
	}
    public String toString(){
        String t = "";
        t+="Name : " +name+"\n";
        t+="Description : "+description+"\n";
        t+="Due Date : "+dueDate+"\n";
        t+="Status : "+status+"\n";
        return t;
    }
    
    public static void addTask(String[] arr)throws Exception {
        Date x= new SimpleDateFormat("dd/MM/yyyy").parse(arr[3]);
        ToDoList st=new ToDoList(arr[1], arr[2],x,arr[4]);
        currentList.add(st);   
    }
    
    public static String removeTask(String name){
        for(ToDoList task:currentList){
            if (task.name.equals(name)){currentList.remove(task);
            return "done";}
        }
        return name+"doesnot exist";
    }

    public static String updateTask(String name, String status){
        for(ToDoList task:currentList){
            if (task.name.equals(name)){task.status=status;
            return "done";}
        }
        return name+"doesnot exist";
    }

    public static void showList() {
        System.out.println();
        System.out.println("----------------------");       
        System.out.println("To-Do List");
        System.out.println("----------------------");
        int number = 0;
        for (ToDoList item : currentList) {
            System.out.println(++number + " " + item);
        }
        System.out.println("----------------------");


    }

	public static void main(String args[]) throws Exception {   
        LocalDate Now=LocalDate.now();
        Scanner in = new Scanner (System.in);
        while(in.hasNextLine()){
            String mystr = in.nextLine();
            if(mystr.equals("")){break;}
            else{
                String[] arr = mystr.split(", ", 0);
                if (arr[0].equals("add")){addTask(arr);}
                else if (arr[0].equals("remove")){System.out.println(removeTask(arr[1]));}
                else if (arr[0].equals("update")){System.out.println(updateTask(arr[1],arr[2]));}
                else{
                    for(ToDoList task:currentList){
                        if (task.name.equals(arr[0])){System.out.println(task);}
                        else if (arr[0].contains("/")){
                            Date y= new SimpleDateFormat("dd/MM/yyyy").parse(arr[0]);
                            if(task.dueDate.equals(y)){System.out.println(task);}
                        }
                        else if (task.status.equals(arr[0])){System.out.println(task);}
                        else if (arr[0].equals("overdues") && task.status.equals("in process") || task.status.equals("incomplete") ){System.out.println(task);}
                    }
                }
            }   
        }
        in.close();  
        showList();   
	}
}