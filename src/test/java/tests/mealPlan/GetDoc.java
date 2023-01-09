package tests.mealPlan;

import java.io.*;

public class GetDoc {
    public static String summing(File getFile) throws  IOException {
        //File xmlFile = new File("src/main/resources/FinancialStateRequestWAY4.xml");
        Reader fileReader = new FileReader(getFile);
        BufferedReader bufReader = new BufferedReader(fileReader);
        StringBuilder sb = new StringBuilder();
        String line = bufReader.readLine();
        while (line != null) {
            sb.append(line).append("\n");
            line = bufReader.readLine();
        }
        String xml2String = sb.toString();
        //System.out.println("XML to String using BufferedReader : ");
        //System.out.println(xml2String);
        bufReader.close();
        return xml2String;
    }
}
