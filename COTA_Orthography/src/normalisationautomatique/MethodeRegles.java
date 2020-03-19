package normalisationautomatique;

import java.io.*;
import static java.nio.file.Files.list;
import java.util.*;
import javax.swing.RowFilter.Entry;

public class MethodeRegles {

    
    private static final TreeMap<String, String> EXCEPTION_MAP = new TreeMap();
    private static final TreeMap<String, TreeSet> VERB_MAP = new TreeMap();
    private static final ArrayList<String> VERB_LIST = new ArrayList();
    private static final ArrayList<String> VERB_NOM_MA_LIST = new ArrayList();
    private static final ArrayList<String> SPLIT_LIST = new ArrayList();
    private static final ArrayList<String> VERB_3_PER_LIST = new ArrayList();
    private static ArrayList<String> proper_noun_List = new ArrayList();
    private static ArrayList<String> nom_List = new ArrayList();
    private static ArrayList<String> Yeny_List = new ArrayList();
    private static ArrayList<String> First_A_LIST = new ArrayList();
    private static ArrayList<String> NchAllah_LIST = new ArrayList();
    private static ArrayList<String> MachAllah_LIST = new ArrayList();
    private static ArrayList<String> AsmAllah_LIST = new ArrayList();
    private static ArrayList<String> Hmd_LIST = new ArrayList();
    private static TreeMap<String, String> Sad_sin_Map = new TreeMap();
    private static TreeMap<String, String> henp_Map = new TreeMap();
    private static TreeMap<String, String> Aenp_Map = new TreeMap();
    private static TreeMap<String, String> YenA_Map = new TreeMap();
    private static TreeMap<String, String> sin_en_Sad_Map = new TreeMap();
    private static TreeMap<String, String> youAenY_Map = new TreeMap();
    public static ArrayList<String> NomArabeSansAL_List = new ArrayList();

    public static String Code(String pe, String contenu) throws IOException, ClassNotFoundException, Exception {
        //Appel Code
        Runtime runtime = Runtime.getRuntime();
        String[] parts = contenu.split(" ");
        for (int p = 0; p < parts.length; p++) {
        //String s2 = "E:\\Extractor\\Extractor\\wordListSalima.txt";
            try {
                Process process = runtime.exec("D:\\Perl\\bin\\perl.exe" + " " + pe + " " + contenu + " " + parts[p]);//+ ">" + "resultFile.txt");
                parts[p] +=parts[p];
//this.update(getGraphics());
                try {
                    process.waitFor();
                } catch (InterruptedException e) {
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return formattter_final(parts);
    }
    
    public static String replaceW(String ch) throws Exception {
        return ch.replace(" ", "");
    }
    
    public static String replaceDoubleSpace(String ch) throws Exception {
        return ch.trim().replaceAll(" +", " ");
    }

    public static String rempAA(String ch) throws Exception {
        return ch.replace("|", "A");
    }

    public static String rempI(String ch) throws Exception {
        return ch.replace("<", "A");
    }

    public static String rempHamza(String ch) throws Exception {
        return ch.replace(">", "A");
    }

    public static String rempHamzaWaw(String ch) throws Exception {
        return ch.replace("&", "w");
    }

    public static String rempHamzaYa(String ch) throws Exception {
        return ch.replace("}", "y");
    }

    public static String rempG(String ch) throws Exception {
        return ch.replace("G", "q");
    }

    public static String negation_mA_Verb1(String ch) throws Exception {
        return "mA " + ch.substring(1);
    }

    public static String negation_mA_Verb2(String ch) throws Exception {
        return "mA " + ch.substring(2);
    }

    public static void db_files_loading() throws IOException {
        BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream("./data/latainexeption.txt"), "UTF8"));
        //Map<String, String> list = new TreeMap();
        String line;
        while ((line = br1.readLine()) != null) {
            String[] div = line.split(" ");
            String key = div[0];
            String proposition = line.substring(line.indexOf(" ") + 1).trim();
            if (!MethodeRegles.EXCEPTION_MAP.containsKey(key)) {
                MethodeRegles.EXCEPTION_MAP.put(key, proposition);
            }
        }
        br1 = new BufferedReader(new InputStreamReader(new FileInputStream("./data/lataiListeVerbes.txt"), "UTF8"));
        while ((line = br1.readLine()) != null) {
            if (!line.equalsIgnoreCase("\ufeff")) {
                if (!MethodeRegles.VERB_MAP.containsKey(line.substring(0, 1))) {
                    TreeSet words = new TreeSet();
                    words.add(line);
                    MethodeRegles.VERB_MAP.put(line.substring(0, 1), words);
                    MethodeRegles.VERB_LIST.add(line);
                } else {
                    TreeSet words = (TreeSet) MethodeRegles.VERB_MAP.get(line.substring(0, 1));
                    words.add(line);
                    MethodeRegles.VERB_MAP.putIfAbsent(line.substring(0, 1) + "", words);
                    MethodeRegles.VERB_LIST.add(line);
                }
            }
        }
        br1 = new BufferedReader(new InputStreamReader(new FileInputStream("./data/Latain liste mA+verbe.txt"), "UTF8"));
        while ((line = br1.readLine()) != null) {
            if (!line.equalsIgnoreCase("\ufeff")) {
                MethodeRegles.VERB_NOM_MA_LIST.add(line);
            }
        }
        br1 = new BufferedReader(new InputStreamReader(new FileInputStream("./data/Split_List.txt"), "UTF8"));
        while ((line = br1.readLine()) != null) {
            if (!line.equalsIgnoreCase("\ufeff")) {
                MethodeRegles.SPLIT_LIST.add(line);
            }
        }
        br1 = new BufferedReader(new InputStreamReader(new FileInputStream("./data/latainlisteVerbe3Personne.txt"), "UTF8"));
        while ((line = br1.readLine()) != null) {
            if (!line.equalsIgnoreCase("\ufeff")) {
                MethodeRegles.VERB_3_PER_LIST.add(line);
            }
        }
        br1 = new BufferedReader(new InputStreamReader(new FileInputStream("./data/lataintNomPropre.txt"), "UTF8"));
        while ((line = br1.readLine()) != null) {
            if (!line.equalsIgnoreCase("\ufeff")) {
                MethodeRegles.proper_noun_List.add(line);
            }
        }
        br1 = new BufferedReader(new InputStreamReader(new FileInputStream("./data/LatainListNomArabe.txt"), "UTF8"));
        while ((line = br1.readLine()) != null) {
            if (!line.equalsIgnoreCase("\ufeff")) {
                MethodeRegles.nom_List.add(line);
            }
        }
        br1 = new BufferedReader(new InputStreamReader(new FileInputStream("./data/NchAllah.txt"), "UTF8"));
        while ((line = br1.readLine()) != null) {
            if (!line.equalsIgnoreCase("\ufeff")) {
                MethodeRegles.NchAllah_LIST.add(line);
            }
        }
        br1 = new BufferedReader(new InputStreamReader(new FileInputStream("./data/MachAllah.txt"), "UTF8"));
        while ((line = br1.readLine()) != null) {
            if (!line.equalsIgnoreCase("\ufeff")) {
                MethodeRegles.MachAllah_LIST.add(line);
            }
        }
        br1 = new BufferedReader(new InputStreamReader(new FileInputStream("./data/AsmAllah.txt"), "UTF8"));
        while ((line = br1.readLine()) != null) {
            if (!line.equalsIgnoreCase("\ufeff")) {
                MethodeRegles.AsmAllah_LIST.add(line);
            }
        }
        br1 = new BufferedReader(new InputStreamReader(new FileInputStream("./data/Hmd.txt"), "UTF8"));
        while ((line = br1.readLine()) != null) {
            if (!line.equalsIgnoreCase("\ufeff")) {
                MethodeRegles.Hmd_LIST.add(line);
            }
        }
        br1 = new BufferedReader(new InputStreamReader(new FileInputStream("./data/latainYeny.txt"), "UTF8"));
        while ((line = br1.readLine()) != null) {
            if (!line.equalsIgnoreCase("\ufeff")) {
                MethodeRegles.Yeny_List.add(line);
            }
        }
        br1 = new BufferedReader(new InputStreamReader(new FileInputStream("./data/Sad_en_sin.txt"), "UTF8"));
        while ((line = br1.readLine()) != null) {
            String[] div = line.split(" ");
            String proposition = div[0];
            String key = line.substring(line.indexOf(" ") + 1).trim();
            if (!MethodeRegles.Sad_sin_Map.containsKey(key)) {
                MethodeRegles.Sad_sin_Map.put(key, proposition);
            }
        }
        br1 = new BufferedReader(new InputStreamReader(new FileInputStream("./data/latinhenp.txt"), "UTF8"));
        while ((line = br1.readLine()) != null) {
            String[] div = line.split(" ");
            String key = div[0];
            String proposition = line.substring(line.indexOf(" ") + 1).trim();
            if (!MethodeRegles.henp_Map.containsKey(key)) {
                MethodeRegles.henp_Map.put(key, proposition);
            }
        }
        br1 = new BufferedReader(new InputStreamReader(new FileInputStream("./data/latinAenp.txt"), "UTF8"));
        while ((line = br1.readLine()) != null) {
            String[] div = line.split(" ");
            String key = div[0];
            String proposition = line.substring(line.indexOf(" ") + 1).trim();
            if (!MethodeRegles.Aenp_Map.containsKey(key)) {
                MethodeRegles.Aenp_Map.put(key, proposition);
            }
        }
        br1 = new BufferedReader(new InputStreamReader(new FileInputStream("./data/YenA.txt"), "UTF8"));
        while ((line = br1.readLine()) != null) {
            String[] div = line.split(" ");
            String proposition = div[0];
            String key = line.substring(line.indexOf(" ") + 1).trim();
            if (!MethodeRegles.YenA_Map.containsKey(key)) {
                MethodeRegles.YenA_Map.put(key, proposition);
            }
        }
        br1 = new BufferedReader(new InputStreamReader(new FileInputStream("./data/sin_en_Sad.txt"), "UTF8"));
        while ((line = br1.readLine()) != null) {
            String[] div = line.split(" ");
            String proposition = div[0];
            String key = line.substring(line.indexOf(" ") + 1).trim();
            if (!MethodeRegles.sin_en_Sad_Map.containsKey(key)) {
                MethodeRegles.sin_en_Sad_Map.put(key, proposition);
            }
        }
        br1 = new BufferedReader(new InputStreamReader(new FileInputStream("./data/First_A.txt"), "UTF8"));
        while ((line = br1.readLine()) != null) {
            if (!line.equalsIgnoreCase("\ufeff")) {
                MethodeRegles.First_A_LIST.add(line);
            }
        }
        br1 = new BufferedReader(new InputStreamReader(new FileInputStream("./data/latinyouAenY.txt"), "UTF8"));
        while ((line = br1.readLine()) != null) {
            String[] div = line.split(" ");
            String proposition = div[0];
            String key = line.substring(line.indexOf(" ") + 1).trim();
            if (!MethodeRegles.youAenY_Map.containsKey(key)) {
                MethodeRegles.youAenY_Map.put(key, proposition);
            }
        }
        br1 = new BufferedReader(new InputStreamReader(new FileInputStream("data/LatainListNomArabeSansAL.txt"), "UTF8"));
        while ((line = br1.readLine()) != null) {
            if (!MethodeRegles.NomArabeSansAL_List.contains(line)) {
                MethodeRegles.NomArabeSansAL_List.add(line);
            }
        }
    }

    private static String formattter_final(String[] parts) throws Exception {
        StringBuilder sb = new StringBuilder();
        for (String str : parts) {
            sb.append(str.replaceAll("(?m)(^ *| +(?= |$))", "").replaceAll("(?m)^$([\r\n]+?)(^$[\r\n]+?^)+", "$1"));
            sb.append(" ");
        }
        return sb.toString().trim();
    }

    /*public String duplicateWords(String contenu)throws Exception{
        return contenu.replaceAll("([ \\w]+)\\1", "$1");
        
    }*/
        
    static String punctuation_D(String contenu) throws Exception {
    String cc = contenu.replaceAll("[.!?,;؟،]+", " $0 ").replaceAll("[.!?,;؟]", " $0 ");
    String c=replaceDoubleSpace(cc);
    return c;
    }
    
    static String punctuation_F(String contenu) throws Exception {
    String cc = contenu.replaceAll("\\s+[.!?,;؟]", "");
    return cc;
    }
    
    static String accentuation(String contenu) throws Exception {
        String[] parts = contenu.split(" ");
        for (int p = 0; p < parts.length; p++) {
           
            long Char_count = Plusieurs_fonctions.countUniqueCharacters(parts[p]);
            System.out.print("Char_count " + Char_count + "\n avant " + parts[p]);
            
            if (Char_count == 1 && parts[p].length() > 3 ){
                //case ههه 
                parts[p] = parts[p].substring(0, 3);// + (String) Accentuation_Map.get(parts[p]);
                
            } else if ((Char_count == 2) && (parts[p].length() > 4)){
                //case ههخخ
                parts[p] = parts[p].substring(0, 2) + parts[p].substring(parts[p].length()-2, parts[p].length());// + (String) Accentuation_Map.get(parts[p]);
                
            } else if(Char_count > 2 && parts[p].length() > 3 ){//&& Accentuation_Map.containsKey(parts[p].substring(1))){
                //case زيييييييد
                parts[p] = parts[p].replaceAll("(.)\\1{2,}", "$1");// + (String) Accentuation_Map.get(parts[p]);
                /* Pattern explanation:
                    "(.)\\1{2,}" means any character (added to group 1) followed by itself at least twice
                    "$1" references contents of group 1
*/
                System.out.print("apres " + parts[p]);
            }
        }
        return formattter_final(parts);
    }
    
    static String NchAllah_function(String contenu) throws Exception {

        String[] parts = contenu.split(" ");
        for (int p = 0; p < parts.length; p++) {
            System.out.print("avant " + parts[p]);
            Iterator<String> it = NchAllah_LIST.iterator();
            while (it.hasNext()) {
                String line = it.next();
                
                if (parts[p].contains(line))
                    parts[p] = contenu.replace(line, "An $A Allh");
        }
        }
        return formattter_final(parts);
    }
        
    
    static String MachAllah_function(String contenu) throws Exception {

        String[] parts = contenu.split(" ");
        for (int p = 0; p < parts.length; p++) {
            System.out.print("avant " + parts[p]);
            Iterator<String> it = MachAllah_LIST.iterator();
            while (it.hasNext()) {
                String line = it.next();
                
                if (parts[p].contains(line))
                    parts[p] = contenu.replace(line, "mA $A Allh");
        }
        }
        return formattter_final(parts);
    }

    static String AsmAllh_function(String contenu) throws Exception {

        String[] parts = contenu.split(" ");
        for (int p = 0; p < parts.length; p++) {
            System.out.print("avant " + parts[p]);
            Iterator<String> it = AsmAllah_LIST.iterator();
            while (it.hasNext()) {
                String line = it.next();
                
                if (parts[p].contains(line))
                    parts[p] = contenu.replace(line, "Asm Allh");
                
        }
        }
        return formattter_final(parts);
    }
    
    static String Hmd_function(String contenu) throws Exception {

        String[] parts = contenu.split(" ");
        for (int p = 0; p < parts.length; p++) {
            System.out.print("avant " + parts[p]);
            Iterator<String> it = Hmd_LIST.iterator();
            while (it.hasNext()) {
                String line = it.next();
                
                if (parts[p].contains(line))
                    parts[p] = contenu.replace(line, "AlHmd llh");
                
        }
        }
        return formattter_final(parts);
    }

        static String KolD_function(String contenu) throws Exception {

        return contenu.replaceAll("\\b(kl)\\b", "qluA");
    }
    
        static String KolF_function(String contenu) throws Exception {

        return contenu.replaceAll("\\b(qluA)\\b", "kl").replaceAll("\\b(qluA)\\b", "kl");
    }
    
    static String S_en_s_function(String contenu) throws Exception {
        String[] parts = contenu.split(" ");
        for (int p = 0; p < parts.length; p++) {
            System.out.print("avant " + parts[p]);
            if (Sad_sin_Map.containsKey(parts[p])) {
                //if (parts[p].equals(line.substring(0, line.indexOf(" ")))) {
                parts[p] = (String) Sad_sin_Map.get(parts[p]); //line.substring(line.indexOf(" ") + 1);
            } else if ((parts[p].indexOf("w") == 0 || parts[p].indexOf("b") == 0
                    || parts[p].indexOf("l") == 0 || parts[p].indexOf("f") == 0 || parts[p].indexOf("k") == 0) && Sad_sin_Map.containsKey(parts[p].substring(1))) {
                String ch = parts[p].substring(0, 1);
                parts[p] = ch + (String) Sad_sin_Map.get(parts[p]);
            }
            System.out.println("      apres " + parts[p]);
        }
        return formattter_final(parts);
    }
    
    static String First_A_function(String contenu) throws Exception {
        String[] parts = contenu.split(" ");
        for (int p = 0; p < parts.length; p++) {
            System.out.print("avant " + parts[p]);
            Iterator<String> it = First_A_LIST.iterator();
            while (it.hasNext()) {
                String line = it.next();
                int L = line.length();
                
                if (parts[p].length() >= L && (parts[p].indexOf("A") == 0 && parts[p].contains(line))){
                    if (parts[p].equals(line))
                        parts[p] = parts[p].replaceFirst("A","");
                    break;
                }
            }
            System.out.println("      apres " + parts[p]);
        }
        return formattter_final(parts);
    }

    static String Y_en_A_function(String contenu) throws Exception {
        String[] parts = contenu.split(" ");
        for (int p = 0; p < parts.length; p++) {
            System.out.print("avant " + parts[p]);
            if (YenA_Map.containsKey(parts[p])) {
                //if (parts[p].equals(line.substring(0, line.indexOf(" ")))) {
                parts[p] = (String) YenA_Map.get(parts[p]); //line.substring(line.indexOf(" ") + 1);
            } else if ((parts[p].indexOf("w") == 0 || parts[p].indexOf("b") == 0 || parts[p].indexOf("l") == 0 || parts[p].indexOf("f") == 0 || parts[p].indexOf("k") == 0) && YenA_Map.containsKey(parts[p].substring(1))) {
                parts[p] = parts[p].substring(0, 1) + (String) YenA_Map.get(parts[p].substring(1));
            }
            System.out.println("      apres " + parts[p]);
        }
        return formattter_final(parts);
    }

    static String ElY_function(String contenu) throws Exception {
        String[] parts = contenu.split(" ");
        for (int p = 0; p < parts.length; p++) {
            System.out.print("avant " + parts[p]);
            Iterator<String> it = nom_List.iterator();
            while (it.hasNext()) {
                String line = it.next();
                //regle ElY
                if (parts[p].contains(line) && p - 1 >= 0) {
                    if (parts[p - 1].equals("Ely")) {
                        parts[p - 1] = "ElY";
                        break;
                    } else if (parts[p - 1].equals("wEly")) {
                        parts[p - 1] = "wElY";
                        break;
                    }
                }
            }
            //regle pour b + " "
            if (p - 1 >= 0) {
                if (parts[p - 1].equals("b") && " ".equals(parts[p])) {
                    parts[p] = "";
                }
            }
            System.out.println("      apres " + parts[p]);
        }
        return formattter_final(parts);
    }
    
    static String clitic_l_function(String contenu) throws Exception {
        String[] parts = contenu.split(" ");
        for (int p = 0; p < parts.length; p++) {
            if ((parts[p].equals("l")) && (parts[p + 1].startsWith("Al"))){
                parts[p] += parts[p + 1].substring(1);
                parts[p + 1] = "\u0000";
            }
            System.out.println("      apres " + parts[p]);
        }
        return formattter_final(parts);
    }

    static String w_Hamza_function(String contenu) throws Exception {
        
        String cc = contenu.replaceAll("\\b(Al|ll)\\s+", "$1").replace("ـ","");
        
        char[] partsCatacter = cc.toCharArray();
        
        for (int k = 0; k < partsCatacter.length; k++) {
            System.out.print("avant " + partsCatacter[k]);
            if ((k == 0 || partsCatacter[k - 1] == ' ') && (partsCatacter[k] == 'w' || partsCatacter[k] == 'l' || partsCatacter[k] == 'm' || partsCatacter[k] == 'E' || partsCatacter[k] == 'b')) {
                
                if (k + 1 < partsCatacter.length) {
                    if (partsCatacter[k + 1] == ' ') {
                        partsCatacter[k + 1] = '\u0000';
                        }
                } else if (k + 3 < partsCatacter.length) {
                    if (partsCatacter[k + 1] == ' ' && (partsCatacter[k] == 'w' || partsCatacter[k] == 'm' || partsCatacter[k] == 'E' || partsCatacter[k] == 'b' || partsCatacter[k] == 'l') && partsCatacter[k + 3] == ' ') {
                        partsCatacter[k] = '\u0000';
                        partsCatacter[k + 1] = '\u0000';
                        partsCatacter[k + 3] = '\u0000';
                    }
                }
            } else if ((k == 0 || partsCatacter[k - 1] == 'w') && partsCatacter[k] == 'w') {
                if (k + 1 < partsCatacter.length) {
                    if (partsCatacter[k + 1] == ' ') {
                        partsCatacter[k] = ' ';
                        partsCatacter[k + 1] = 'w';
                    }
                }
            } else if (partsCatacter[k] == '|') {
                String nonNorm = String.valueOf(partsCatacter[k]);
                String norm = MethodeRegles.rempI(nonNorm);
                partsCatacter[k] = norm.charAt(0);
            } else if (partsCatacter[k] == '<') {
                String nonNorm = String.valueOf(partsCatacter[k]);
                String norm = MethodeRegles.rempHamza(nonNorm);
                partsCatacter[k] = norm.charAt(0);
            } else if (partsCatacter[k] == '>') {
                String nonNorm = String.valueOf(partsCatacter[k]);
                String norm = MethodeRegles.rempHamza(nonNorm);
                partsCatacter[k] = norm.charAt(0);
            } else if (partsCatacter[k] == '&') {
                String nonNorm = String.valueOf(partsCatacter[k]);
                String norm = MethodeRegles.rempHamzaWaw(nonNorm);
                partsCatacter[k] = norm.charAt(0);

            } else if (partsCatacter[k] == '}') {
                String nonNorm = String.valueOf(partsCatacter[k]);
                String norm = MethodeRegles.rempHamzaYa(nonNorm);
                partsCatacter[k] = norm.charAt(0);

            } else if (partsCatacter[k] == '\'') {
                partsCatacter[k] = '\u0000';

            } else if (partsCatacter[k] == 'G') {
                String nonNorm = String.valueOf(partsCatacter[k]);
                String norm = MethodeRegles.rempG(nonNorm);
                partsCatacter[k] = norm.charAt(0);

            }
            System.out.println("      apres " + partsCatacter[k]);
        }
        StringBuilder sb = new StringBuilder();
        for (char c : partsCatacter) {
            if (c != '\u0000') {
                sb.append(c);
            }
        }
        contenu = sb.toString();

        return contenu;
    }

    static String trois_pers_sing_function(String contenu) throws Exception {
        String[] parts = contenu.split(" ");
        for (int p = 0; p < parts.length; p++) {
            System.out.print("avant " + parts[p]);
            Iterator<String> it = VERB_3_PER_LIST.iterator();
            while (it.hasNext()) {
                String line = it.next();
                int L = line.length();
                int pa = parts[p].length();
                // noms: وحطو/AmtyAzwA /يد
                if (parts[p].length() > L && (parts[p].lastIndexOf("w") == pa - 1
                        && parts[p].substring(1, pa - 1).equals(line)
                        && (parts[p].indexOf("w") == 0 || parts[p].indexOf("l") == 0 || parts[p].indexOf("k") == 0 || parts[p].indexOf("b") == 0)) || (parts[p].endsWith("wA") == true && parts[p].substring(1, pa - 2).equals(line) && (parts[p].indexOf("w") == 0 || parts[p].indexOf("l") == 0 || parts[p].indexOf("k") == 0 || parts[p].indexOf("b") == 0))) {
                    parts[p] = parts[p].substring(0, L + 1) + "h";
                    break;
                } else if (parts[p].length() > L && ((parts[p].lastIndexOf("w") == pa - 1
                        && parts[p].substring(0, pa - 1).equals(line)) || (parts[p].endsWith("wA") == true && parts[p].substring(0, pa - 2).equals(line)))) {
                    parts[p] = parts[p].substring(0, L) + "h";
                    break;
                }
            }
            System.out.println("      apres " + parts[p]);
        }
        return formattter_final(parts);
    }

    static String h_en_p_function(String contenu) throws Exception {
        String[] parts = contenu.split(" ");
        for (int p = 0; p < parts.length; p++) {
            System.out.print("avant " + parts[p]);
            if (henp_Map.containsKey(parts[p])) {
                //if (parts[p].equals(line.substring(0, line.indexOf(" ")))) {
                parts[p] = (String) henp_Map.get(parts[p]); //line.substring(line.indexOf(" ") + 1);
            } else if ((parts[p].indexOf("w") == 0 || parts[p].indexOf("b") == 0 || parts[p].indexOf("l") == 0 || parts[p].indexOf("f") == 0 || parts[p].indexOf("k") == 0)
                    && henp_Map.containsKey(parts[p].substring(1))) {
                parts[p] = parts[p].substring(0, 1) + (String) henp_Map.get(parts[p].substring(1));
            }
            System.out.println("      apres " + parts[p]);
        }
        return formattter_final(parts);
    }

    static String Aouy_en_Y_function(String contenu) throws Exception {
        String[] parts = contenu.split(" ");
        for (int p = 0; p < parts.length; p++) {
            System.out.print("avant " + parts[p]);
            if (youAenY_Map.containsKey(parts[p])) {
                //if (parts[p].equals(line.substring(0, line.indexOf(" ")))) {
                parts[p] = (String) youAenY_Map.get(parts[p]); //line.substring(line.indexOf(" ") + 1);
            } else if ((parts[p].indexOf("w") == 0 || parts[p].indexOf("b") == 0 || parts[p].indexOf("l") == 0 || parts[p].indexOf("f") == 0 || parts[p].indexOf("k") == 0)
                    && youAenY_Map.containsKey(parts[p].substring(1))) {
                parts[p] = parts[p].substring(0, 1) + (String) youAenY_Map.get(parts[p].substring(1));
            }
            System.out.println("      apres " + parts[p]);
        }
        return formattter_final(parts);
    }

    static String nombre_function(String contenu) throws Exception {
        String[] parts = contenu.split(" ");
        String[] Nombres = {"HdA$", "vnA$", "vltTA$", "ArbETA$", "xmsTA$", "stTA$", "sbETA$", "vmnTA$", "tsETA$"};
        for (int p = 0; p < parts.length; p++) {
            System.out.print("avant " + parts[p]);
            if (Plusieurs_fonctions.isANumber(parts[p]) == true) {
                parts[p] = Plusieurs_fonctions.convnumber(parts[p]);
            }
            for (String Nombre1 : Nombres) {
                int L = Nombre1.length();
                // cas : HdA$nrAjel
                if (parts[p].contains(Nombre1) && parts[p].indexOf("n") == L && parts[p].length() > L + 1) {
                    parts[p] = parts[p].substring(0, L + 1) + " " + parts[p].substring(L + 1);
                } // cas : HdA$ n rAjl
                else if (parts[p].equals(Nombre1)) {
                    if (p + 1 < parts.length) {
                        if (parts[p + 1].equals("n")) {
                            String nonNorm = parts[p] + parts[p + 1];
                            parts[p] = nonNorm;
                            parts[p + 1] = "";
                        } else {
                            // cas : HdA$ rAjl
                            parts[p] = parts[p] + "n";
                        }
                    }
                }
            }
            System.out.println("      apres " + parts[p]);
        }
        return formattter_final(parts);
    }

    static String verb_pronoun_function(String contenu) throws Exception {
        String[] parts = contenu.split(" ");
        for (int p = 0; p < parts.length; p++) {
            System.out.print("avant " + parts[p]);
            ArrayList words = VERB_LIST; //.get(key);
            Iterator<String> it = words.iterator();
            while (it.hasNext()) {
                String line = it.next();
                int L = line.length();
                // cas /xzrlhA /المليح/  عامل
                if (parts[p].endsWith("l") == false && parts[p].indexOf(line) == 0 && parts[p].indexOf("l") == L && line.endsWith("l") == false && (parts[p].substring(L).equals("ly") || parts[p].substring(L).equals("lnA") || parts[p].substring(L).equals("lk") || parts[p].substring(L).equals("lkm") || parts[p].substring(L).equals("lhA") || parts[p].substring(L).equals("lhm") || parts[p].substring(L).equals("lw"))) {
                    parts[p] = parts[p].substring(0, L) + " " + parts[p].substring(L);
                    break;
                } else if (parts[p].indexOf(line) == 0 && (parts[p].indexOf("hA") == L || parts[p].indexOf("hw") == L) // yEmlhAlhm/
                        && parts[p].lastIndexOf("l") == L + 2) {
                    parts[p] = parts[p].substring(0, L + 2) + " " + parts[p].substring(L + 2);
                    break;
                } //qAllhm
                else if (parts[p].indexOf(line) == 0 && (parts[p].substring(L).equals("ly") || parts[p].substring(L).equals("lnA") || parts[p].substring(L).equals("lk") || parts[p].substring(L).equals("lkm") || parts[p].substring(L).equals("lhA") || parts[p].substring(L).equals("lhm") || parts[p].substring(L).equals("lw")) && line.lastIndexOf("l") != -1 && parts[p].lastIndexOf("l") == L) {
                    parts[p] = parts[p].substring(0, L) + " " + parts[p].substring(L);
                    break;
                } //wkHlw
                else if (parts[p].indexOf("w") == 0 && parts[p].indexOf(line) == 1 && parts[p].indexOf("l") == L + 1 && line.endsWith("l") == false && (parts[p].substring(L + 1).equals("ly") || parts[p].substring(L + 1).equals("lnA") || parts[p].substring(L + 1).equals("lk") || parts[p].substring(L + 1).equals("lkm") || parts[p].substring(L + 1).equals("lhA") || parts[p].substring(L + 1).equals("lhm") || parts[p].substring(L + 1).equals("lw")) && parts[p].substring(L + 1).length() > 1) {
                    parts[p] = parts[p].substring(0, L + 1) + " " + parts[p].substring(L + 1);
                    break;
                } // wyEmlhAlhm
                else if (parts[p].indexOf("w") == 0 && parts[p].indexOf(line) == 1 && (parts[p].indexOf("hA") == L + 1 || parts[p].indexOf("hw") == L + 1) && parts[p].lastIndexOf("l") == L + 3) {
                    parts[p] = parts[p].substring(0, L + 3) + " " + parts[p].substring(L + 3);
                    break;
                } //wqAllhm
                else if (parts[p].indexOf("w") == 0 && parts[p].indexOf(line) == 1 && line.endsWith("l") == true && parts[p].lastIndexOf("l") == L + 1 && (parts[p].substring(L + 1).equals("ly") || parts[p].substring(L + 1).equals("lnA") || parts[p].substring(L + 1).equals("lk") || parts[p].substring(L + 1).equals("lkm") || parts[p].substring(L + 1).equals("lhA") || parts[p].substring(L + 1).equals("lhm") || parts[p].substring(L + 1).equals("lw"))) {
                    parts[p] = parts[p].substring(0, L + 1) + " " + parts[p].substring(L + 1);
                    break;
                }
            }
            System.out.println("      apres " + parts[p]);
        }
        return formattter_final(parts);
    }

    static String A_en_p_function(String contenu) throws Exception {
        String[] parts = contenu.split(" ");
        for (int p = 0; p < parts.length; p++) {
            System.out.print("avant " + parts[p]);
            if (Aenp_Map.containsKey(parts[p])) {
                //if (parts[p].equals(line.substring(0, line.indexOf(" ")))) {
                parts[p] = (String) Aenp_Map.get(parts[p]); //line.substring(line.indexOf(" ") + 1);
            } else if ((parts[p].indexOf("w") == 0 || parts[p].indexOf("b") == 0 || parts[p].indexOf("l") == 0 || parts[p].indexOf("f") == 0 || parts[p].indexOf("k") == 0) && Aenp_Map.containsKey(parts[p].substring(1))) {
                parts[p] = parts[p].substring(0, 1) + (String) Aenp_Map.get(parts[p].substring(1));
            }
            System.out.println("      apres " + parts[p]);
        }
        return formattter_final(parts);
    }

    static String verbe_negation_function(String contenu) throws Exception {
        String[] parts = contenu.split(" ");
        for (int p = 0; p < parts.length; p++) {
            System.out.print("avant " + parts[p]);
            if (parts[p].indexOf("mA") == 0) {
                Iterator<String> it = VERB_LIST.iterator();
                while (it.hasNext()) {
                    String line = it.next();
                    int L = line.length();
                    if (parts[p].contains(line) && parts[p].indexOf("$") == L + 2 && parts[p].lastIndexOf("$") == parts[p].length() - 1) {
                        //mAykH$                        //مانخالفش
                        parts[p] = MethodeRegles.negation_mA_Verb1(parts[p]);
                        break;
                    } else if (parts[p].contains(line) && (parts[p].indexOf("hA") == L + 2 || parts[p].indexOf("hw") == L + 2) && parts[p].indexOf("$") == L + 4 && parts[p].lastIndexOf("$") == parts[p].length() - 1) {
                        //mAykHhA$
                        parts[p] = "mA " + parts[p].substring(2);
                        break;
                    } else if (parts[p].contains(line) && ((parts[p].indexOf("l") == L + 2 && line.endsWith("l") == false) || (parts[p].lastIndexOf("l") == L + 2 && line.endsWith("l") == true)) && parts[p].lastIndexOf("$") == parts[p].length() - 1) {
                        parts[p] = "mA" + " " + parts[p].substring(2, L + 2) + " " + parts[p].substring(L + 2);
                        break;
                    } else if (parts[p].contains(line) && (parts[p].indexOf("hA") == L + 2 || parts[p].indexOf("hw") == L + 2) && line.endsWith("l") == false && parts[p].lastIndexOf("$") == parts[p].length() - 1) {
                        parts[p] = "mA" + " " + parts[p].substring(2, L + 4) + " " + parts[p].substring(L + 4);
                        break;
                    } else if (parts[p].contains(line) && line.endsWith("l") == true && parts[p].lastIndexOf("$") == parts[p].length() - 1) {
                        parts[p] = MethodeRegles.negation_mA_Verb1(parts[p]);
                        break;
                    }
                }
            } else if (parts[p].indexOf("wmA") == 0) {
                //wmAykH$
                Iterator<String> it = VERB_LIST.iterator();
                while (it.hasNext()) {
                    String line = it.next();
                    int L = line.length();
                    if (parts[p].contains(line) && parts[p].indexOf("$") == L + 3 && parts[p].lastIndexOf("$") == parts[p].length() - 1) {
                        parts[p] = "wmA " + parts[p].substring(3);
                        break;
                    } else if (parts[p].contains(line) //wmAykHhA$
                            && (parts[p].indexOf("hA") == L + 3 || parts[p].indexOf("hw") == L + 3) && parts[p].indexOf("$") == L + 5 && parts[p].lastIndexOf("$") == parts[p].length() - 1) {
                        parts[p] = "mA " + parts[p].substring(3);
                        break;
                    } else if (parts[p].contains(line) //wmAykHhAlw$
                            && (parts[p].indexOf("hA") == L + 3 || parts[p].indexOf("hw") == L + 3) && line.endsWith("l") == false && parts[p].lastIndexOf("$") == parts[p].length() - 1) {
                        parts[p] = "wmA" + " " + parts[p].substring(3, L + 5) + " " + parts[p].substring(L + 5);
                        break;
                    } else if (parts[p].contains(line) //wmAykHlw$
                            && parts[p].indexOf("l") == L + 3 && line.endsWith("l") == false && parts[p].lastIndexOf("$") == parts[p].length() - 1) {
                        parts[p] = "wmA" + " " + parts[p].substring(3, L + 3) + " " + parts[p].substring(L + 3);
                        break;
                    } else if (parts[p].contains(line) //wmAqAllw$
                            && parts[p].lastIndexOf("l") == L + 3 && line.endsWith("l") == true && parts[p].lastIndexOf("$") == parts[p].length() - 1) {
                        parts[p] = "mA" + " " + parts[p].substring(3, L + 3) + " " + parts[p].substring(L + 3);
                        break;
                    } else if (parts[p].contains(line) //wmAqAlw$
                            && line.endsWith("l") == true && parts[p].lastIndexOf("$") == parts[p].length() - 1) {
                        parts[p] = "wmA " + parts[p].substring(3);
                        break;
                    }
                }
            } else if (parts[p].indexOf("m") == 0) {
                Iterator<String> it = VERB_LIST.iterator();
                while (it.hasNext()) {
                    String line = it.next();
                    int L = line.length();
                    if (parts[p].contains(line) && parts[p].lastIndexOf("$") == L + 1 && parts[p].lastIndexOf("$") == parts[p].length() - 1) {
                        parts[p] = MethodeRegles.negation_mA_Verb1(parts[p]);
                        break;
                    } //mykHlw$ //ما نخ ليوش
                    else if (parts[p].contains(line) && parts[p].indexOf("l") == L + 1 && line.endsWith("l") == false && parts[p].lastIndexOf("$") == parts[p].length() - 1) {
                        parts[p] = "mA" + " " + parts[p].substring(1, L + 1) + " " + parts[p].substring(L + 1);
                        break;
                    } else if (parts[p].contains(line) //mqAllw$
                            && parts[p].lastIndexOf("l") == L + 1 && line.endsWith("l") == true && parts[p].lastIndexOf("$") == parts[p].length() - 1) {
                        parts[p] = "mA" + " " + parts[p].substring(1, L + 1) + " " + parts[p].substring(L + 1);
                        break;
                    } else if (parts[p].contains(line) //mqAlw$
                            && line.endsWith("l") == true && parts[p].lastIndexOf("$") == parts[p].length() - 1) {
                        parts[p] = "mA " + parts[p].substring(1);
                        break;
                    }
                }
            } else if (parts[p].indexOf("wm") == 0) {
                Iterator<String> it = VERB_LIST.iterator();
                while (it.hasNext()) {
                    String line = it.next();
                    int L = line.length();
                    if (parts[p].contains(line) //wmtkAfyny$
                            && parts[p].lastIndexOf("$") == L + 2 && parts[p].lastIndexOf("$") == parts[p].length() - 1) {
                        parts[p] = "wmA " + parts[p].substring(2);
                        break;
                    } else if (parts[p].contains(line) //wmykHlw$
                            && parts[p].indexOf("l") == L + 2 && line.endsWith("l") == false && parts[p].lastIndexOf("$") == parts[p].length() - 1) {
                        parts[p] = "wmA" + " " + parts[p].substring(2, L + 2) + " " + parts[p].substring(L + 2);
                        break;
                    } else if (parts[p].contains(line) //wmqAllw$
                            && parts[p].lastIndexOf("l") == L + 2 && line.endsWith("l") == true && parts[p].lastIndexOf("$") == parts[p].length() - 1) {
                        parts[p] = "wmA" + " " + parts[p].substring(2, L + 2) + " " + parts[p].substring(L + 2);
                        break;
                    } else if (parts[p].contains(line) //wmqAlw$
                            && line.endsWith("l") == true && parts[p].lastIndexOf("$") == parts[p].length() - 1) {
                        parts[p] = "wmA " + parts[p].substring(2);
                        break;
                    }
                }
            } else if (p != 0) {
                //mA ykHlw$ /kHl$
                if (parts[p].length() - 1 == parts[p].lastIndexOf("$") && (parts[p - 1].equals("wmA") || parts[p - 1].equals("mA"))) {
                    String key = parts[p].charAt(0) + "";
                    if (VERB_MAP.containsKey(key)) {
                        TreeSet words = VERB_MAP.get(key);
                        Iterator<String> it = words.iterator();
                        while (it.hasNext()) {
                            String line = it.next();
                            int L = line.length();
                            if (parts[p].indexOf(line) == 0 && ((parts[p].lastIndexOf("l") == L && line.endsWith("l") == true) || (parts[p].indexOf("l") == L && line.endsWith("l") == false))) {
                                parts[p] = parts[p].substring(0, L) + " " + parts[p].substring(L);
                                break;
                                //wmA ykHlw$ //mA qAllw$     } //wmA qAllw$
                                //mtkAfyny$/kHl/mykH$/مغشش
                            }
                        }
                    }
                }
            }
            System.out.println("      apres " + parts[p]);
        }
        return formattter_final(parts);
    }

    static String yA_nouns_function(String contenu) throws Exception {
        String[] parts = contenu.split(" ");
        for (int p = 0; p < parts.length; p++) {
            System.out.print("avant " + parts[p]);
            if (parts[p].indexOf("yA") == 0 && proper_noun_List.contains(parts[p].substring(2))) {
                parts[p] = "yA " + parts[p].substring(2);
            }
            if (parts[p].indexOf("wyA") == 0 && proper_noun_List.contains(parts[p].substring(3))) {
                parts[p] = "wyA " + parts[p].substring(3);
            }
            System.out.println("      apres " + parts[p]);
        }
        return formattter_final(parts);
    }

    static String question_function(String contenu) throws Exception {
        String[] parts = contenu.split(" ");
        for (int p = 0; p < parts.length; p++) {
            System.out.print("avant " + parts[p]);
            if (parts[p].endsWith("p") == false) {
                if (parts[p].indexOf("A$") == 0 || parts[p].indexOf(">$") == 0) {
                    //A$gSyt/
                    if (parts[p].length() > 2) {
                        String key = parts[p].charAt(2) + "";
                        if (VERB_MAP.containsKey(key)) {
                            TreeSet words = VERB_MAP.get(key);
                            Iterator<String> it = words.iterator();
                            while (it.hasNext()) {
                                String line = it.next();
                                if (parts[p].substring(2).contains(line)) {
                                    parts[p] = "A$ " + parts[p].substring(2);
                                    break;
                                }
                            }
                        }
                    }
                } else if (parts[p].indexOf("wA$") == 0 || parts[p].indexOf("w>$") == 0) {
                    //wA$gSyt
                    if (parts[p].length() > 3) {
                        String key = parts[p].charAt(3) + "";
                        if (VERB_MAP.containsKey(key)) {
                            TreeSet words = VERB_MAP.get(key);
                            Iterator<String> it = words.iterator();
                            while (it.hasNext()) {
                                String line = it.next();
                                if (parts[p].substring(3).contains(line)) {
                                    parts[p] = "wA$ " + parts[p].substring(3);
                                    break;
                                }
                            }
                        }
                    }
                } else if (parts[p].indexOf("$") == 0) {
                    //$gSyt/$hwr
                    if (parts[p].length() > 1) {
                        String key = parts[p].charAt(1) + "";
                        if (VERB_MAP.containsKey(key)) {
                            TreeSet words = VERB_MAP.get(key);
                            Iterator<String> it = words.iterator();
                            while (it.hasNext()) {
                                String line = it.next();
                                if (parts[p].substring(1).contains(line)) {
                                    parts[p] = "A$ " + parts[p].substring(1);
                                    break;
                                }
                            }
                        }
                    }
                } else if (parts[p].indexOf("w$") == 0) {
                    //w$gSyt
                    if (parts[p].length() > 2) {
                        String key = parts[p].charAt(2) + "";
                        if (VERB_MAP.containsKey(key)) {
                            TreeSet words = VERB_MAP.get(key);
                            Iterator<String> it = words.iterator();
                            while (it.hasNext()) {
                                String line = it.next();
                                if (parts[p].substring(2).contains(line)) {
                                    parts[p] = "wA$ " + parts[p].substring(2);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            System.out.println("      apres " + parts[p]);
        }
        return formattter_final(parts);
    }

    static String exception_list_function(String contenu) throws Exception {
        String[] parts = contenu.split(" ");
        for (int p = 0; p < parts.length; p++) {
            System.out.print("avant " + parts[p]);
            //exception list
            if (EXCEPTION_MAP.containsKey(parts[p])) {
                //if (parts[p].equals(line.substring(0, line.indexOf(" ")))) {
                parts[p] = (String) EXCEPTION_MAP.get(parts[p]); //line.substring(line.indexOf(" ") + 1);
            } else if ((parts[p].indexOf("w") == 0 || parts[p].indexOf("b") == 0 || parts[p].indexOf("l") == 0 || parts[p].indexOf("f") == 0 || parts[p].indexOf("k") == 0) && EXCEPTION_MAP.containsKey(parts[p].substring(1))) {
                String ch = parts[p].substring(0, 1);
                parts[p] = ch + (String) EXCEPTION_MAP.get(parts[p].substring(1));
            }
            System.out.println("      apres " + parts[p]);
        }
        return formattter_final(parts);
    }

    static String split_list_function(String contenu) throws Exception {
        String[] parts = contenu.split(" ");
        String ch = "";
        String ch1 = "";
        for (int p = 0; p < parts.length; p++) {
            System.out.print("avant " + parts[p]);
            while (parts.length > 3 && !SPLIT_LIST.contains(parts[p])){
                for (int l = 3; l < parts.length; l++){
                    ch1 = parts[p].substring(0, l);
                    if (SPLIT_LIST.contains(ch1) || ch1.endsWith("p")) {
                        ch = ch + ch1 + " ";
                        parts[p] = parts[p].substring(l+1, parts.length);
                        l = 0;
                    } 
                }
                parts[p] = ch;
            }
            //exception list
            System.out.println("      apres " + parts[p]);
        }
        return formattter_final(parts);
    }

    static String s_en_S_function(String contenu) throws Exception {
        String[] parts = contenu.split(" ");
        for (int p = 0; p < parts.length; p++) {
            System.out.print("avant " + parts[p]);
            if (sin_en_Sad_Map.containsKey(parts[p])) {
                //if (parts[p].equals(line.substring(0, line.indexOf(" ")))) {
                parts[p] = (String) sin_en_Sad_Map.get(parts[p]); //line.substring(line.indexOf(" ") + 1);
            } else if ((parts[p].indexOf("w") == 0 || parts[p].indexOf("b") == 0
                    || parts[p].indexOf("l") == 0 || parts[p].indexOf("f") == 0 || parts[p].indexOf("k") == 0) && sin_en_Sad_Map.containsKey(parts[p].substring(1))) {
                String ch = parts[p].substring(0, 1);
                parts[p] = ch + (String) sin_en_Sad_Map.get(parts[p]);
            }
            System.out.println("      apres " + parts[p]);
        }
        return formattter_final(parts);
    }

    static String mA_verb_function(String contenu) throws Exception {
        String[] parts = contenu.split(" ");
        for (int p = 0; p < parts.length; p++) {
            System.out.print("avant " + parts[p]);
            // mAkml
            if (parts[p].length() > 2 && VERB_NOM_MA_LIST.contains(parts[p].substring(2)) && parts[p].indexOf("mA") == 0) {
                //&& parts[p].substring(2).equals(line)) {
                parts[p] = "mA" + " " + parts[p].substring(2);
            } else if (parts[p].length() > 3 && VERB_NOM_MA_LIST.contains(parts[p].substring(3)) && parts[p].indexOf("wmA") == 0) {
                //&& parts[p].substring(3).equals(line)) {
                parts[p] = "mA" + " " + parts[p].substring(3);
            }
            System.out.println("      apres " + parts[p]);
        }
        return formattter_final(parts);
    }

    static String trois_pers_sing_plur_function(String contenu) throws Exception {
        String[] parts = contenu.split(" ");
        for (int p = 0; p < parts.length; p++) {
            System.out.print("avant " + parts[p]);
            //int pa = parts[p].length();
            System.out.println(parts[p].length());
            // noms: وحطو
            if (!parts[p].equalsIgnoreCase("")) {
                if (VERB_LIST.contains(parts[p])) {
                    //if (parts[p].equals(line)) {
                    String line = parts[p];
                    int L = line.length();
                    if (parts[p].length() == L && parts[p].endsWith("Aw") == true) {
                        parts[p] = parts[p] + "A";
                    } else if (parts[p].endsWith("yw") == true && parts[p].length() == L) {
                        parts[p] = parts[p] + "A";
                    }
                } else if (VERB_LIST.contains(parts[p].substring(1, parts[p].length()))) {
                    //if (parts[p].substring(1, pa).equals(line)) {
                    int L = parts[p].substring(1, parts[p].length()).length();
                    if (parts[p].length() >= L && (parts[p].endsWith("Aw") == true || parts[p].endsWith("yw") == true)) {
                        parts[p] = parts[p] + "A";
                    }
                } else if (parts[p].length() - 1 > 0 && VERB_LIST.contains(parts[p].substring(1, parts[p].length() - 1))) {
                    // if (parts[p].substring(1, pa - 1).equals(line)) {
                    int L = parts[p].substring(1, parts[p].length() - 1).length();
                    if (parts[p].length() > L && parts[p].lastIndexOf("w") == parts[p].length() - 1) {
                        parts[p] = parts[p].substring(0, L + 1) + "h" + "/" + parts[p] + "A";
                    }
                } else if (VERB_LIST.contains(parts[p].substring(0, parts[p].length() - 1))) {
                    //if (parts[p].substring(0, pa - 1).equals(line)) {
                    int L = parts[p].substring(0, parts[p].length() - 1).length();
                    if (parts[p].length() > L && parts[p].lastIndexOf("w") == parts[p].length() - 1) {
                        parts[p] = parts[p].substring(0, L) + "h" + "/" + parts[p] + "A";
                    }
                } else if (parts[p].equals("lw")) {
                    parts[p] = "lh";
                }
            }
            System.out.println("      apres " + parts[p]);
        }
        return formattter_final(parts);
    }

    public static String Y_en_y_function(String contenu) throws Exception {
        String[] parts = contenu.split(" ");
        for (int p = 0; p < parts.length; p++) {
            System.out.print("avant " + parts[p]);
            Iterator<String> it = Yeny_List.iterator();
            while (it.hasNext()) {
                String line = it.next();
                if (parts[p].equals(line.substring(line.indexOf(" ") + 1))) {
                    parts[p] = line.substring(0, line.indexOf(" "));
                } else if ((parts[p].indexOf("w") == 0 || parts[p].indexOf("b") == 0 || parts[p].indexOf("l") == 0 || parts[p].indexOf("f") == 0 || parts[p].indexOf("k") == 0) && parts[p].substring(1).equals(line.substring(line.indexOf(" ") + 1))) {
                    String ch = parts[p].substring(0, 1);
                    parts[p] = ch + line.substring(0, line.indexOf(" "));
                }
            }
            System.out.println("      apres " + parts[p]);
        }
        return formattter_final(parts);
    }

    static String regle_verb_negation_function(String contenu) throws Exception {
        String[] parts = contenu.split(" ");
        for (int p = 0; p < parts.length; p++) {
            System.out.print("avant " + parts[p]);
            //regle de futur
            //System.out.println(parts[p]);
            // bA$ykH
            if ((parts[p].indexOf("bA$") == 0 || parts[p].indexOf("wb$") == 0) && parts[p].length() >= 4) {
                String key = parts[p].charAt(3) + "";
                if (VERB_MAP.containsKey(key)) {
                    TreeSet words = VERB_MAP.get(key);
                    Iterator<String> it = words.iterator();
                    while (it.hasNext()) {
                        String word = it.next();
                        if (parts[p].substring(3).contains(word)) {
                            parts[p] = parts[p].substring(0, 3) + " " + parts[p].substring(3); //"bA$" + " " + nonNorm.substring(3);
                            break;
                        }
                    }
                }
            } else if (parts[p].indexOf("wbA$") == 0 && parts[p].length() >= 5) {
                //&& parts[p].substring(4).contains(line)) {
                String key = parts[p].charAt(4) + "";
                if (VERB_MAP.containsKey(key)) {
                    TreeSet words = VERB_MAP.get(key);
                    Iterator<String> it = words.iterator();
                    while (it.hasNext()) {
                        String word = it.next();
                        if (parts[p].substring(4).contains(word)) {
                            parts[p] = parts[p].substring(0, 4) + " " + parts[p].substring(4); //parts[p] = "wbA$" + " " + nonNorm.substring(4);
                            break;
                        }
                    }
                }
            } // b$ykH
            else if (parts[p].indexOf("b$") == 0 && parts[p].length() >= 3) {
                String key = parts[p].charAt(4) + "";
                if (VERB_MAP.containsKey(key)) {
                    TreeSet words = VERB_MAP.get(key);
                    Iterator<String> it = words.iterator();
                    while (it.hasNext()) {
                        String word = it.next();
                        if (parts[p].substring(2).contains(word)) {
                            parts[p] = "bA$" + " " + parts[p].substring(2);
                            break;
                        }
                    }
                }
            }
            System.out.println("      apres " + parts[p]);
        }
        return formattter_final(parts);
    }

}
