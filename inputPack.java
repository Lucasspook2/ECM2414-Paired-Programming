import java.io.*;
import java.util.ArrayList;

//this class provides methods to deal with the input of the pack 

public class inputPack{

    
    //finds the vetted pack file
    public static ArrayList<Card> getPack(String file){
    ArrayList<Card> res = new ArrayList<Card>();
    try(BufferedReader br = new BufferedReader(new FileReader(file))){
        
        String line;
        while((line = br.readLine()) != null){
            res.add(new Card(Integer.parseInt(line)));
        }
        return res;
    } catch (IOException e){
        e.printStackTrace();
        return res;
    }

    }

    //counts the lines in the file to determine validity 
    public static int countLines(String file){
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while (br.readLine() != null) {
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    
}