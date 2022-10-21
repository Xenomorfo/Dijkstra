
package Logger;

import java.io.*;
import java.util.Calendar;
import java.util.Properties;

/**
 * Responsável pelos logs utilizando o padrão Singleton
 * @author José Canelas
 */
public class ParkLogs {
   
    private static final ParkLogs logger = new ParkLogs();
    
    /**
     *
     * Classe ParkLogs
     */
    private ParkLogs() {
        
    }
    
    /**
     *
     * @param msg Mensagem
     */

    public void writeToLog(String msg){
        
        Calendar cal = Calendar.getInstance();
        InputStream input = null;
        Properties prop = new Properties();
        FileOutputStream file = null;
        try {
            input = new FileInputStream("file.properties");
            prop.load(input);
            String filePath = prop.getProperty("fileLog");
            FileWriter arq = new FileWriter(filePath,true);
            PrintWriter saveArq = new PrintWriter(arq);
            String line = getPrefix(cal)+ " : "+ getMessage(msg);
            saveArq.write(line);
            arq.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
   
    /**
     *
     * @return Única Instância
     */
    public static ParkLogs getInstance(){
        return logger;
    }
    
    private String getPrefix(Calendar cal) {
        return "\nEvento - "+cal.getTime().toString();
    }
    
    private String getMessage(String msg) {
        return msg;
    }
    
    
}
