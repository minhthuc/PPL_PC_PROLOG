import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PCProg {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String Choice;
        ArrayList<PC> pcArrayList = new ArrayList<>();
        System.out.println("Creating a new Computer?");
        System.out.println("Y/N ?");

        Choice = sc.nextLine();

        while (Choice.toLowerCase().equals("y")){
            System.out.println("You are creating a new Computer");
            PC pc = createObject();
            pcArrayList.add(pc);

            System.out.println(pc.toStringFormated());
            System.out.println("Do you want to make a new computer ? (Y/N)");
            Choice = sc.nextLine();
        }
        displayReport(pcArrayList);


        System.out.println("Do you want to save report to file?");
        String choice = sc.nextLine();
        if (choice.toLowerCase().equals("y")){
            System.out.println("Name of file?");
            String filename = sc.nextLine();
            saveReport(filename, pcArrayList);
        }
    }

    /**
     *
     * @param file
     * @effect write down the report to file
     */
     static void saveReport(String file, ArrayList<PC> pcArrayList){
        try {
            FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(displayReport2String(pcArrayList));
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
     static void displayReport(ArrayList<PC> pcArrayList){
        String rs = "";
        rs += headerGenerate(getMaxArrayList(pcArrayList), "PCPROG REPOR");
        int i = 1;
        for (PC pc : pcArrayList){
            rs+= pc.leftAlign(4, String.valueOf(i));
            rs += pc.toStringFormated() + "\n";
            i++;
        }
        System.out.println(rs);
    }

    /** @return the report as tabular with title
     *          automatic generate the order from first to last one
     *          the columns respectively are
     *          model   | year   | manufacture   | Components
     */
     static String displayReport2String(ArrayList<PC> pcArrayList){
        String rs = "";
        rs += headerGenerate(getMaxArrayList(pcArrayList), "PCPROG REPOR");
        int i = 1;
        for (PC pc : pcArrayList){
            rs+= pc.leftAlign(4, String.valueOf(i));
            rs += pc.toStringFormated() + "\n";
            i++;
        }
        return rs;
    }

    /**
     *
     * @return header as length character with title is content
     * like
     * ----------------------------------------------------------
     *                          content
     * ----------------------------------------------------------
     */
     static String headerGenerate(int length, String content){
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
     static PC createObject(){
        Scanner sc = new Scanner(System.in);
        PC pc = new PC();
        //add model
        System.out.println("Fill in to add model to your computer");
        String model = sc.nextLine();

        while (!pc.modelValidate(model)){
            System.out.println("Please re-add model to your computer");
            model = sc.nextLine();
        }
        pc.setModel(model);

        //add year
        System.out.println("Fill in to add Year to your computer");
        String year = sc.nextLine();

        //test
//            int year = 2016;
        while (!pc.yearValidate(year)){
            System.out.println("Please re-add year to your computer");
            year =sc.nextLine();
        }
        pc.setYear(year);

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
        while (!components.toLowerCase().equals("n")){
            while (set.isDuplicate(components)){
                System.out.println("re-add components to set");
                components = sc.nextLine();
            }
            set.addPart(components);
            System.out.println("do you want to adding more?");
            components = sc.nextLine();
        }
        pc.setComponents(set);
        System.out.println(set+" Added into Set");
        return pc;
    }


    /**
     *
     * @param arrayList
     * @return size of the highest unit's length
     */
    public static int getMaxArrayList(ArrayList<PC> arrayList){
         int max = 0;
        for (PC pc : arrayList) {
            if (max < pc.toStringFormated().length()){
                max = pc.toStringFormated().length();
            }
        }
        return max;
    }
}
