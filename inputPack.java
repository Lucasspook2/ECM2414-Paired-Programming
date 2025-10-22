import java.io.*;
import java.util.ArrayList;


public class inputPack{

    public static ArrayList<Integer> getPack(String file){
    ArrayList<Integer> res = new ArrayList<Integer>();
    try(BufferedReader br = new BufferedReader(new FileReader(file))){
        
        String line;
        while((line = br.readLine()) != null){
            res.add(Integer.parseInt(line));
        }
        return res;
    } catch (IOException e){
        e.printStackTrace();
        return res;
    }

    }
}