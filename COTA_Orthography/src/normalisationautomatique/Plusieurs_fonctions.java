package normalisationautomatique;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class Plusieurs_fonctions {

    private static Map<Character,Integer> Compression_MAP =new LinkedHashMap<Character,Integer>();
        // Compression = input: aaabbaaa output: a3b2a3
    //utilisée dans le cas de l'Accentuation

    public static String getRouge() {
    return "\033[31m";
    }
 
    public static String getVert() {
    return "\033[32m";
    }
    static String compression(String c){
        String output="";
        for(int i=0;i<c.length();i++){
            Character character=c.charAt(i);
            if(Compression_MAP.containsKey(character)){
                Compression_MAP.put(character, Compression_MAP.get(character)+1);
            }else
                Compression_MAP.put(character, 1);
        }
        output = Compression_MAP.entrySet().stream().map((entry) -> entry.getValue()+""+entry.getKey().charValue()).reduce(output, String::concat);
        return output;
    }
    
       // pour calculer le nombre de caractères unique dans un mot = input: abcbc output: 3
    //utilisée dans certain cas de l'Onomatopée
       /* 
    public static int countUniqueCharacters(String s) {
        char characters[] = s.toCharArray();
        int countOfUniqueChars = s.length();
        for (int i = 0; i < characters.length; i++) {
            if (i != s.indexOf(characters[i])) {
                countOfUniqueChars--;
            }
        }
        return countOfUniqueChars;*/
    public static long countUniqueCharacters(String s) {
       return s.chars()
                .distinct()
                .count();
    }
    
    public static String arabicToLatin(String s) {
        s = s.replace("\u0621", "'");
        s = s.replace("\u0622", "|");
        s = s.replace("\u0623", ">");
        s = s.replace("\u0624", "&");
        s = s.replace("\u0625", "<");
        s = s.replace("\u0626", "}");
        s = s.replace("\u0627", "A");
        s = s.replace("\u0628", "b");
        s = s.replace("\u0629", "p");
        s = s.replace("\u062a", "t");
        s = s.replace("\u062b", "v");
        s = s.replace("\u062c", "j");
        s = s.replace("\u062d", "H");
        s = s.replace("\u062e", "x");
        s = s.replace("\u062f", "d");
        s = s.replace("\u0630", "*");
        s = s.replace("\u0631", "r");
        s = s.replace("\u0632", "z");
        s = s.replace("\u0633", "s");
        s = s.replace("\u0634", "$");
        s = s.replace("\u0634", "c");
        s = s.replace("\u0635", "S");
        s = s.replace("\u0636", "D");
        s = s.replace("\u0637", "T");
        s = s.replace("\u0638", "Z");
        s = s.replace("\u0639", "E");
        s = s.replace("\u063a", "g");
        s = s.replace("\u0641", "f");
        s = s.replace("\u0642", "q");
        s = s.replace("\u0643", "k");
        s = s.replace("\u0644", "l");
        s = s.replace("\u0645", "m");
        s = s.replace("\u0646", "n");
        s = s.replace("\u0647", "h");
        s = s.replace("\u0648", "w");
        s = s.replace("\u0649", "Y");
        s = s.replace("\u064a", "y");
        s = s.replace("\u0625", "{");
        s = s.replace("\u06a8", "G");
        return s;
    }

    /*
     * public static void main(String[] args) throws Exception {
     * MethodeRegles r=new MethodeRegles();
     * byte[] encodedText = Files.readAllBytes(Paths.get("textTestApp.txt"));
     * String allText = new String(encodedText, StandardCharsets.UTF_8);
     * String arabe = latinToArabic(allText);
     * TexteNO.stringToFile(arabe, "textArabe.txt");
     * }
     */

 /*
     * public void neg(String ch1 ,String ch2){
     * if(ch1.indexOf("m")==0 && ch1.contains(ch2)&& ch2.lastIndexOf("l")!=-1){
     * int L=ch2.length();
     * String ch11=ch1.substring(1,L+1);
     * String ch22=ch1.substring(L+1);
     * }
     * }
     */
    public static String latinToArabic(String s) {
        s = s.replace("'", "\u0621");
        s = s.replace("|", "\u0622");
        s = s.replace(">", "\u0623");
        s = s.replace("&", "\u0624");
        s = s.replace("<", "\u0625");
        s = s.replace("}", "\u0626");
        s = s.replace("A", "\u0627");
        s = s.replace("b", "\u0628");
        s = s.replace("p", "\u0629");
        s = s.replace("t", "\u062a");
        s = s.replace("v", "\u062b");
        s = s.replace("j", "\u062c");
        s = s.replace("H", "\u062d");
        s = s.replace("x", "\u062e");
        s = s.replace("d", "\u062f");
        s = s.replace("*", "\u0630");
        s = s.replace("r", "\u0631");
        s = s.replace("z", "\u0632");
        s = s.replace("s", "\u0633");
        s = s.replace("$", "\u0634");
        s = s.replace("c", "\u0634");
        s = s.replace("S", "\u0635");
        s = s.replace("D", "\u0636");
        s = s.replace("T", "\u0637");
        s = s.replace("Z", "\u0638");
        s = s.replace("E", "\u0639");
        s = s.replace("g", "\u063a");
        s = s.replace("f", "\u0641");
        s = s.replace("q", "\u0642");
        s = s.replace("k", "\u0643");
        s = s.replace("l", "\u0644");
        s = s.replace("m", "\u0645");
        s = s.replace("n", "\u0646");
        s = s.replace("h", "\u0647");
        s = s.replace("w", "\u0648");
        s = s.replace("Y", "\u0649");
        s = s.replace("y", "\u064a");
        s = s.replace("{", "\u0625");
        s = s.replace("G", "\u06a8");
        return s;
    }

    //public static void main ( String[] args ) throws IOException {
    //  afficher( "modif.txt" , "modif1.txt" );
    /*
     * convnumber( "11" );
     * String ch = "ها البلاد supp_esp";
     *
     * String car_spec = ch.substring( 0 , 1 );
     * System.out.println( car_spec );
     *
     * String prec_spec1 = "esp";
     * System.out.println( prec_spec1 );
     *
     * String prec_spec2 = "*";
     * System.out.println( prec_spec2 );
     *
     * int suiv1 = ch.indexOf( car_spec );
     * String suiv_spec1 = ch.substring( suiv1 + 1 , 2 );
     * System.out.println( suiv_spec1 );
     *
     * int suiv2 = ch.indexOf( car_spec );
     * String suiv_spec2 = ch.substring( suiv2 + 2 , 2 );
     * System.out.println( suiv_spec2 );
     *
     * String deb1 = ch.substring( 0 , 1 );
     * System.out.println( deb1 );
     *
     * String deb2 = ch.substring( 1 , 2 );
     * System.out.println( deb2 );
     *
     * String fin1 = ch.substring( ch.length() - 2 , ch.length() - 1 );
     * System.out.println( fin1 );
     *
     * String fin2 = ch.substring( ch.length() - 1 );
     * System.out.println( fin2 );
     *
     * int d = ch.lastIndexOf( " " );
     *
     * String classe = ch.substring( d + 1 );
     * System.out.println( classe );
     *
     * Plusieurs_fonctions arftt = new Plusieurs_fonctions();
     *
     * System.out.println( "nombreMot:" + arftt.calculer( "calcul mot.txt" )
     * );
     * System.out.println( "totalerreur:" + arftt.calculeMotErr(
     * "calculmotErr.txt" ) );
     * System.out.println( "motcorrect:" + arftt.calculejust(
     * "calculjustFaut.txt" ) );
     * System.out.println( "motfaut:" + arftt.calculeFaut(
     * "calculjustFaut.txt" ) );
     */
    //}
    public static String removeSpace(String string) {
        //System.out.println(string);
        //System.out.println();
        return string.replace("\n", " ").replace("\r", " ");
    }

    public static void saveText_inFile(String contenu, String fileName) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(contenu);
            writer.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }
//calculer le nbre de mots dans un fichier
    public int calculer(String fileName) throws IOException {
        byte[] encodedText = Files.readAllBytes(Paths.get(fileName));
        String allText = new String(encodedText, StandardCharsets.UTF_8);
        String[] parts = allText.split(" ");
        return parts.length;
    }

    public int calculeMotErr(String fileName) throws IOException {
        byte[] encodedText = Files.readAllBytes(Paths.get(fileName));
        String allText = new String(encodedText, StandardCharsets.UTF_8);
        int i = 0;
        char[] partsCatacter = allText.toCharArray();
        for (int k = 0; k < partsCatacter.length; k++) {
            if (partsCatacter[k] == '[') {
                i++;
            }
        }
        return i;
    }

    public int calculejust(String fileName) throws IOException {
        byte[] encodedText = Files.readAllBytes(Paths.get(fileName));
        String allText = new String(encodedText, StandardCharsets.UTF_8);
        int i = 0;
        char[] partsCatacter = allText.toCharArray();
        for (int k = 0; k < partsCatacter.length; k++) {
            if (partsCatacter[k] == '(') {
                i++;
            }
        }
        return i;
    }

    public int calculeFaut(String fileName) throws IOException {
        byte[] encodedText = Files.readAllBytes(Paths.get(fileName));
        String allText = new String(encodedText, StandardCharsets.UTF_8);
        int i = 0;
        char[] partsCatacter = allText.toCharArray();
        for (int k = 0; k < partsCatacter.length; k++) {
            if (partsCatacter[k] == '[') {
                i++;

            }

        }
        return i;
    }

    static org.jdom.Document document;
    static Element racine;
    protected static final List<String> UNITESLIST = new ArrayList<>();
    protected static final List<String> DIXLIST = new ArrayList<>();
    protected static final List<String> CENTSLIST = new ArrayList<>();
    protected static final List<String> MILLELIST = new ArrayList<>();
    protected static String[] parties1;
    protected static String[] parties2;

    public static String convnumber(String nombre) {

        try {
            lireFichier("./data/ENTREE-NUM.xml");
            analyserFichier();
        } catch (Exception e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();

        parties1 = nombre.split("-");

        for (int i = 0; i < parties1.length; i++) {

            parties2 = parties1[i].split("\\.");
            for (int j = 0; j < parties2.length; j++) {
                int nb = Integer.valueOf(parties2[j]);
                //System.out.print(numero2lettres(nb));
                sb.append(numero2lettres(nb));
                if (j != (parties2.length - 1)) {
                    //System.out.print("فاصل ");
                    sb.append("فاصل ");
                }
            }

            if (i != (parties1.length - 1)) {
                System.out.print("-"); // A modifier !
            }
        }
        // System.out.println(numero2lettres(100111));
        //System.out.println();
        return sb.toString();
    }

    static void lireFichier(String fichier) throws Exception {
        SAXBuilder sxb = new SAXBuilder();
        document = sxb.build(new File(fichier));
        racine = document.getRootElement();
    }

    protected static void analyserFichier() {
        Element entry = racine.getChild("LEXICAL-ENTRY");
        List listTemps = entry.getChildren();
        Iterator i = listTemps.iterator();
        while (i.hasNext()) {
            Element courant = (Element) i.next();
            if (courant.getName().trim().equals("NUMBER_0_TO_20")) {
                List list = courant.getChildren();
                Iterator j = list.iterator();
                while (j.hasNext()) {
                    Element courant2 = (Element) j.next();
                    UNITESLIST.add(courant2.getChild("Noun_Num_DT").getText());
                }
            }
            if (courant.getName().trim().equals("NUMBER_10_TO_90")) {
                List list = courant.getChildren();
                Iterator j = list.iterator();
                while (j.hasNext()) {
                    Element courant2 = (Element) j.next();
                    DIXLIST.add(courant2.getChild("Noun_Num_DT").getText());
                }
            }
            if (courant.getName().trim().equals("NUMBER_100_TO_200")) {
                List list = courant.getChildren();
                Iterator j = list.iterator();
                while (j.hasNext()) {
                    Element courant2 = (Element) j.next();
                    CENTSLIST.add(courant2.getChild("Noun_Num_DT").getText());
                }
            }
            if (courant.getName().trim().equals("NUMBER_1000_TO_2000")) {
                List list = courant.getChildren();
                Iterator j = list.iterator();
                MILLELIST.add(" ");
                while (j.hasNext()) {
                    Element courant2 = (Element) j.next();
                    MILLELIST.add(courant2.getChild("Noun_Num_DT").getText());
                }
            }
        }
    }

    public static String numero2lettres(long number) {
        StringBuilder sb = new StringBuilder();

        if (number == 0) {
            return "صفر";
        }

        long log = 1000000000000000000L, sub = number;
        int i = 7;
        do {
            sub = number / log;
            number = number % log;
            log = log / 1000;
            i--;
            if (sub != 0) {

                if (sub == 2 && i != 0) {
                    sb.append("");
                    sb.append(MILLELIST.get(2));
                } else if (sub == 1 && i != 0) {
                    sb.append("");
                    sb.append(MILLELIST.get(1));
                } // case of number <11000 and number >2000
                else if (sub > 2 && sub < 11 && i != 0) {
                    sb.append(centaines2lettres((int) sub));
                    sb.append("");
                    sb.append(MILLELIST.get(3));
                    // case of number >11000
                } else {
                    sb.append(centaines2lettres((int) sub));
                    // add the specific letter of tunisian dialect "n"
                    sb.append(nFunction(sub, i));
                    // System.out.println("oui");
                    sb.append(" ");
                    sb.append(MILLELIST.get(i));

                }

                if (number != 0) {
                    sb.append(" و");
                    // System.out.println(number);
                }
            }
        } while (number != 0);

        return sb.toString();
    }

    protected static String nFunction(long sub, long i) {
        if (sub > 10 && i != 0) {
            return "ن";
        }
        return "";
    }

    protected static String centaines2lettres(int n) {
        StringBuilder sb = new StringBuilder();
        if (n > 99) {
            if (n < 300 && n >= 200) {
                sb.append(CENTSLIST.get(1));
            } else {
                if (n >= 300) {
                    sb.append(UNITESLIST.get((n / 100) - 1));
                    sb.append(" ");
                }
                sb.append(CENTSLIST.get(0));
            }

            n = n % 100;
            if (n != 0) {
                sb.append(" و");
                // System.out.println("oui");
            }
        }

        if (n > 0 && n < 20) {
            sb.append(UNITESLIST.get(n - 1));
            int n1 = n / 20;

            if (n1 != 0) {
                sb.append(" و");
                // System.out.println("oui");
            }
        }

        if (n > 0) {
            // System.out.println(n);
            // n1 = n / 20;
            int n1 = n % 10;
            int n2 = n1 % 10;
            // System.out.println(n1);
            // System.out.println(n2);
            if (n1 != 0 && n2 > 0 && n > 19) {

                sb.append(UNITESLIST.get(n1 - 1));
                if (n1 != 0) {
                    sb.append(" و");
                }
            }

        }

        if (n > 19) {
            sb.append(DIXLIST.get((n / 10) - 1));

            n = n % 10;
            if (n != 0) {
                // sb.append(" و");
            }
        }

        /*
         * if (n > 0) { sb.append(UNITSLIST.get(n - 1)); }
         */
        return sb.toString();
    }

    public static boolean isANumber(String s) {
        if (s == null) {
            return false;
        }
        try {
            BigDecimal bigDecimal = new java.math.BigDecimal(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void afficher(String fileName, String outputfileName) throws IOException {
        // The name of the file to open.
        // This will reference one line at a time
        String line = null;
        Writer out = null;
        String text = "";
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputfileName), "Unicode"));
        } catch (UnsupportedEncodingException | FileNotFoundException e1) {

            e1.printStackTrace();
        }
        // FileReader reads text files in the default encoding.
        FileReader fileReader;
        try {
            fileReader = new FileReader(fileName);
            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            try {
                while ((line = bufferedReader.readLine()) != null) {
                    String string = line;
                    String s = line;
                    text += s.substring(2, s.indexOf(" ")) + " " + s.substring(s.indexOf(" ") + 3);
                    text += "\n";
                }
            } catch (IOException e) {

                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
        out.write(text);
        out.close();
    }

}
