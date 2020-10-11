package cn.itcase.FourOperations;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AnswerWrite {
    static String all="";
    static int cnt=1;
    public static void save(){
        try{
            File file = new File("Answers.txt");
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.write(all);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void Write(Fraction answer){
        all+=(cnt+". "+answer+"\n");
        cnt++;
    }
}
