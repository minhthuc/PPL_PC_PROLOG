import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PCProg {
    static ArrayList<PC> pcArrayList = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static PC pc = new PC();
    static int size = 0;
    static String Choice ="";

    public static void main(String[] args) {
        System.out.println("Creating a new Computer?");
        System.out.println("Y/N ?");

        Choice = sc.nextLine();

        //test
//        Choice = "y";
        while (Choice.toLowerCase().equals("y")){
            System.out.println("You are creating a new Computer");

            createObject();

            pcArrayList.add(pc);
            System.out.println(pc.toStringFormated());
            System.out.println("Do you want to make a new computer ? (Y/N)");
            Choice = sc.nextLine();
        }
        displayReport();


        System.out.println("Do you want to save report to file?");
        String choice = sc.nextLine();
        if (choice.toLowerCase().equals("y")){
            System.out.println("Name of file?");
            String filename = sc.nextLine();
            saveReport(filename);
        }
    }

    /**
     *
     * @param file
     * @effect write down the report to file
     */
    public static void saveReport(String file){
        try {
            FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(displayReport2String());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  the report as tabular with title
     *          automatic generate the order from first to last one
     *          the columns respectively are
     *          model   | year   | manufacture   | Components
     */
    public static void displayReport(){
        String rs = "";
        rs += headerGenerate(100, "PCPROG REPOR");
        int i = 1;
        for (PC pc : pcArrayList){
            rs+= pc.leftAlign(4, String.valueOf(i));
            rs += pc.toStringFormated();
            i++;
        }
        System.out.println(rs);
    }

    /** @return the report as tabular with title
     *          automatic generate the order from first to last one
     *          the columns respectively are
     *          model   | year   | manufacture   | Components
     */
    public static String displayReport2String(){
        String rs = "";
        rs += headerGenerate(100, "PCPROG REPOR");
        int i = 1;
        for (PC pc : pcArrayList){
            rs+= pc.leftAlign(4, String.valueOf(i));
            rs += pc.toStringFormated();
            i++;
        }
        return rs;
    }

    /**
     *
     * @param length
     * @param content
     * @return header as length character with title is content
     * like
     * ----------------------------------------------------------
     *                          content
     * ----------------------------------------------------------
     */
    public static String headerGenerate(int length, String content){
        String dash = "";
        String header = "";
        for (int i = 0; i<length; i++){
            if (i < (length - content.length())/2){
                header += " ";
            }else if (i == (length - content.length())/2){
                header += content;
            }
            dash += "-";
        }
        return dash + "\n" + header + "\n" + dash + "\n";
    }

    /**
     * @effect: the process that user can follow create a PC
     */
    public static void createObject(){
        pc = new PC();
        //add model
        System.out.println("Fill in to add model to your computer");
        String model = sc.nextLine();

        //test
//            String model = "Vostro 3650MT";
        while (!pc.modelValidate(model)){
            System.out.println("Please re-add model to your computer");
            model = sc.nextLine();

        }
        pc.setModel(model);

        //add year
        System.out.println("Fill in to add Year to your computer");
        int year = sc.nextInt();

        //test
//            int year = 2016;
        sc.nextLine();
        while (!pc.yearValidate(year)){
            System.out.println("Please re-add year to your computer");
            year = sc.nextInt();

        }
        pc.setYear(year);

        //test
//            System.out.println("pass");
        //add manufacture
        System.out.println("Fill in to add Manufacture to your computer");
        String manufacturer = sc.nextLine();
        while (!pc.manufacturerValidate(manufacturer)){
            System.out.println("Please re-add Manufacture to your computer");
            manufacturer = sc.nextLine();
        }
        pc.setManufacturer(manufacturer);

        Set set = new Set();

        System.out.println("Adding components in");
        System.out.println("Type your component");
        String components = sc.nextLine();
        while (!components.toLowerCase().equals("y")){
            while (set.isDuplicate(components)){
                System.out.println("re-add components to set");
                components = sc.nextLine();
            }
            set.addPart(components);
            System.out.println("do you want to cancel adding?");
            System.out.println("if not please continue to add");
            System.out.println("next component!");
            components = sc.nextLine();

        }
        pc.setComponents(set);
    }
}
