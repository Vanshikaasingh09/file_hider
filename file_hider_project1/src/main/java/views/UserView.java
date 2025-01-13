package views;

import dao.DataDAO;
import model.Data;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UserView {
    private String email;
    UserView(String email){
        this.email=email;
    }
    public void home(){
        do{
            System.out.println("welcome" + this.email );
            System.out.println("1: show hidden file");
            System.out.println("2: hide new file");
            System.out.println("3: unhide file");
            System.out.println("0: exits");
            Scanner sc = new Scanner (System.in);
            int ch = Integer.parseInt(sc.nextLine());
            switch(ch){
                case 1:
                    try {
                        List<Data> files = DataDAO.getAllFile(this.email);
                        System.out.println("Id - file Name");
                        for (Data file : files){
                            System.out.println(file.getId() + "-" +  file.getFilename());
                        }
                    }catch (SQLException  e){
                        e.printStackTrace();

                }
                    break;
                case 2:
                    System.out.println("Enter the file path");
                    String path = sc.nextLine();
                    File f = new File(path);
                    Data file = new Data(0,f.getName(), path, this.email);
                    try {
                        DataDAO.hideFile(file);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;

                case 3:
                    List<Data> files = null;
                    try {
                        files = DataDAO.getAllFile(this.email);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Id - file Name");
                    for (Data File : files){
                        System.out.println(File.getId() + "-" +  File.getFilename());
                    }
                    System.out.println("Enter the id of file to unhide ");
                    int id = Integer.parseInt(sc.nextLine());
                    boolean isValidID = false;
                    for(Data File : files){
                        if (File.getId()== id){
                            isValidID = true;
                            break;

                        }
                    }
                    if (isValidID){
                        try {
                            DataDAO.unhide(id);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }else{
                        System.out.println("wrong");
                    }




                default:
                    System.exit(0);



            }


        } while(true);
    }
}
