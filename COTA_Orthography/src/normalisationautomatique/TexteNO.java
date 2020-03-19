package normalisationautomatique;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.charset.StandardCharsets;
import javax.swing.GroupLayout.Alignment;
import javax.swing.*;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class TexteNO extends JFrame {

    private static final long serialVersionUID = -526063491664113371L;
    private final JPanel contentPane;
    private String contenu;
    private JTextArea textArea_1;
    private JTextArea txtrHkwmtwa;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TexteNO frame = new TexteNO();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public TexteNO() {
        setTitle("Normalisation automatique d'orthographe du dialecte tunisien");
        setBackground(Color.BLUE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 815, 573);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JScrollPane scrollPane = new JScrollPane();

        JScrollPane scrollPane_1 = new JScrollPane();

        JButton btnNormalize = new JButton("Normalize");
        btnNormalize.setBackground(Color.LIGHT_GRAY);
        btnNormalize.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 14));
        btnNormalize.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                contenu = Plusieurs_fonctions.arabicToLatin(txtrHkwmtwa.getText());
                stringToFile(contenu.replace("\n", " ").replace("\r", " "), "textTestApp.txt");
                String nonNorm = null;
                String norm = null;
                ModelKnn mm = new ModelKnn();
                System.out.println("//////////////////////// Debut H");
                try {
                    mm.listMots.clear();
                    if (mm.afficherh(contenu, "output_h.arff")) {
                        mm.modelKnn("output_h.arff", "modelApph.model", ModelKnn.cls_h);
                        try (BufferedReader br = new BufferedReader(new FileReader("resultat_h.arff"))) {
                            int index = 1;
                            for (String line; (line = br.readLine()) != null;) {
                                if (index > 15) {
                                    String data = line.substring(0, line.lastIndexOf(","));
                                    String classe = line.substring(line.lastIndexOf(",") + 1);
                                    String nonNormalise = mm.listMots.get(index - 16).contenu;
                                    System.out.println(nonNormalise + " : " + classe + " : " + data);
                                    String normalise = null;
                                    switch (classe) {
                                        case "supp_esp_supp_alif":
                                            normalise = supp_esp_supp_alif((nonNormalise), "h");
                                            break;
                                        case "supp_esp":
                                            normalise = supp_esp((nonNormalise), "h");
                                            break;
                                        case "supp_esp_supp_lam":
                                            normalise = supp_esp_supp_lam(nonNormalise, "h");
                                            break;
                                        case "supp_esp_supp_lam_supp_alif":
                                            normalise = supp_esp_supp_lam_supp_alif(nonNormalise, "h");
                                            break;
                                        case "supp_esp_ajout_alif":
                                            normalise = supp_esp_ajout_alif(nonNormalise, "h");
                                            break;
                                        case "ajout_alif":
                                            normalise = ajout_alif(nonNormalise, "h");
                                            break;
                                        default:
                                            break;
                                    }
                                    if (normalise != null) {

                                        contenu = contenu.replaceAll(nonNormalise, normalise);
                                    }
                                }
                                index++;
                            }
                        }
                    }
                } catch (Exception e1) {
                }
                System.out.println("//////////////////////// Fin H");
                System.out.println("//////////////////////// Debut M");
                try {
                    mm.listMots.clear();
                    if (mm.afficherM(contenu, "output_m.arff")) {
                        mm.modelKnn("output_m.arff", "modelAppm.model", ModelKnn.cls_m);
                        try (BufferedReader br = new BufferedReader(new FileReader("resultat_m.arff"))) {
                            //String linPrec;
                            int index = 1;
                            for (String line; (line = br.readLine()) != null;) {
                                if (index > 15) {
                                    String classe = line.substring(line.lastIndexOf(",") + 1);
                                    String nonNormalise = mm.listMots.get(index - 16).contenu;
                                    System.out.println(nonNormalise + " : " + classe);
                                    String normalise = null;
                                    switch (classe) {
                                        case "supp_esp_supp_alif":
                                            normalise = supp_esp_supp_alif(nonNormalise, "m");
                                            break;
                                        case "supp_esp":
                                            normalise = supp_esp(nonNormalise, "m");
                                            break;
                                        case "supp_esp_supp_lam":
                                            normalise = supp_esp_supp_lam(nonNormalise, "m");
                                            break;
                                        case "supp_esp_supp_lam_supp_alif":
                                            normalise = supp_esp_supp_lam_supp_alif(nonNormalise, "m");
                                            break;
                                        case "supp_esp_ajout_alif":
                                            normalise = supp_esp_ajout_alif(nonNormalise, "m");
                                            break;
                                        case "ajout_alif":
                                            normalise = ajout_alif(nonNormalise, "m");
                                            break;
                                        default:
                                            break;
                                    }
                                    if (normalise != null) {
                                        contenu = contenu.replaceAll(nonNormalise, normalise);
                                    }
                                }
                                index++;
                            }
                        }
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                System.out.println("//////////////////////// Fin M");
                System.out.println("//////////////////////// Debut E");
                try {
                    int cc = 0;
                    mm.listMots.clear();
                    if (mm.afficherE(contenu, "output_E.arff")) {
                        mm.modelKnn("output_E.arff", "modelAppE.model", ModelKnn.cls_E);
                        try (BufferedReader br = new BufferedReader(new FileReader("resultat_E.arff"))) {
                            int index = 1;
                            for (String line; (line = br.readLine()) != null;) {
                                if (index > 15) {
                                    String classe = line.substring(line.lastIndexOf(",") + 1);
                                    String nonNormalise = mm.listMots.get(index - 16).contenu;
                                    System.out.println(nonNormalise + " : " + classe);
                                    String normalise = null;
                                    switch (classe) {
                                        case "supp_esp_supp_alif":
                                            normalise = supp_esp_supp_alif(nonNormalise, "E");
                                            break;
                                        case "supp_esp":
                                            normalise = supp_esp(nonNormalise, "E");
                                            break;
                                        case "supp_esp_supp_lam":
                                            normalise = supp_esp_supp_lam(nonNormalise, "E");
                                            break;
                                        case "supp_esp_supp_lam_supp_alif":
                                            normalise = supp_esp_supp_lam_supp_alif(nonNormalise, "E");
                                            break;
                                        case "supp_esp_ajout_alif":
                                            normalise = supp_esp_ajout_alif(nonNormalise, "E");
                                            break;
                                        case "ajout_alif":
                                            normalise = ajout_alif(nonNormalise, "E");
                                            break;
                                        default:
                                            break;
                                    }
                                    if (normalise != null) {
                                        contenu = contenu.replaceAll(nonNormalise, normalise);
                                    }
                                }
                                index++;
                            }
                        }
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                System.out.println("//////////////////////// Fin E");
                System.out.println("//////////////////////// Debut F");
                try {
                    mm.listMots.clear();
                    if (mm.afficherF(contenu, "output_f.arff")) {
                        mm.modelKnn("output_f.arff", "modelAppF.model", ModelKnn.cls_f);
                        try (BufferedReader br = new BufferedReader(new FileReader("resultat_f.arff"))) {
                            int index = 1;
                            for (String line; (line = br.readLine()) != null;) {
                                if (index > 15) {
                                    String classe = line.substring(line.lastIndexOf(",") + 1);
                                    String nonNormalise = mm.listMots.get(index - 16).contenu;
                                    System.out.println(nonNormalise + " : " + classe);
                                    String normalise = null;
                                    switch (classe) {
                                        case "supp_esp_supp_alif":
                                            normalise = supp_esp_supp_alif(nonNormalise, "f");
                                            break;
                                        case "supp_esp":
                                            normalise = supp_esp(nonNormalise, "f");
                                            break;
                                        case "supp_esp_supp_lam":
                                            normalise = supp_esp_supp_lam(nonNormalise, "f");
                                            break;
                                        case "supp_esp_supp_lam_supp_alif":
                                            normalise = supp_esp_supp_lam_supp_alif(nonNormalise, "f");
                                            break;
                                        case "supp_esp_ajout_alif":
                                            normalise = supp_esp_ajout_alif(nonNormalise, "f");
                                            break;
                                        case "ajout_alif":
                                            normalise = ajout_alif(nonNormalise, "f");
                                            break;
                                        default:
                                            break;
                                    }
                                    if (normalise != null) {
                                        contenu = contenu.replaceAll(nonNormalise, normalise);
                                    }
                                }
                                index++;
                            }
                        }
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                System.out.println("//////////////////////// Fin F");
                System.out.println("//////////////////////// Debut K");
                try {
                    mm.listMots.clear();
                    if (mm.afficherK(contenu, "output_k.arff")) {
                        mm.modelKnn("output_k.arff", "modelAppk.model", ModelKnn.cls_k);
                        try (BufferedReader br = new BufferedReader(new FileReader("resultat_k.arff"))) {
                            int index = 1;
                            for (String line; (line = br.readLine()) != null;) {
                                if (index > 15) {
                                    String classe = line.substring(line.lastIndexOf(",") + 1);
                                    String nonNormalise = mm.listMots.get(index - 16).contenu;
                                    System.out.println(nonNormalise + " : " + classe);
                                    String normalise = null;
                                    switch (classe) {
                                        case "supp_esp_supp_alif":
                                            normalise = supp_esp_supp_alif(nonNormalise, "k");
                                            break;
                                        case "supp_esp":
                                            normalise = supp_esp(nonNormalise, "k");
                                            break;
                                        case "supp_esp_supp_lam":
                                            normalise = supp_esp_supp_lam(nonNormalise, "k");
                                            break;
                                        case "supp_esp_supp_lam_supp_alif":
                                            normalise = supp_esp_supp_lam_supp_alif(nonNormalise, "k");
                                            break;
                                        case "supp_esp_ajout_alif":
                                            normalise = supp_esp_ajout_alif(nonNormalise, "k");
                                            break;
                                        case "ajout_alif":
                                            normalise = ajout_alif(nonNormalise, "k");
                                            break;
                                        default:
                                            break;
                                    }
                                    if (normalise != null) {
                                        contenu = contenu.replaceAll(nonNormalise, normalise);
                                    }
                                }
                                index++;
                            }
                        }
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                //liste d'exeption
                try {
                    String[] parts = contenu.split(" ");

                    int i = 0;
                    for (int p = 0; p < parts.length; p++) {
                        try (BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream("latainexeption.txt"), "UTF8"))) {
                            for (String line; (line = br1.readLine()) != null;) {
                                if (parts[p].equals(line.substring(0, line.indexOf(" ")))) {
                                    parts[p] = line.substring(line.indexOf(" ") + 1);

                                } else if ((parts[p].indexOf("w") == 0 || parts[p].indexOf("b") == 0 || parts[p].indexOf("l") == 0 || parts[p].indexOf("f") == 0 || parts[p].indexOf("k") == 0)
                                        && parts[p].substring(1).equals(line.substring(0, line.indexOf(" ")))) {
                                    String ch = parts[p].substring(0, 1);
                                    parts[p] = ch + line.substring(line.indexOf(" ") + 1);

                                }
                            }
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }

                    StringBuilder sb = new StringBuilder();
                    for (String str : parts) {
                        sb.append(str.replaceAll("(?m)(^ *| +(?= |$))", "").replaceAll("(?m)^$([\r\n]+?)(^$[\r\n]+?^)+", "$1"));
                        sb.append(" ");
                    }

                    contenu = sb.toString();

                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                //regle de futur
                try {

                    String[] parts = contenu.split(" ");

                    int i = 0;

                    for (int p = 0; p < parts.length; p++) {
                        try (BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream("lataiListeVerbes.txt"), "UTF8"))) {
                            for (String line; (line = br1.readLine()) != null;) {
                                int L = line.length();
                                // bA$ykH

                                if (parts[p].indexOf("bA$") == 0 && parts[p].substring(3).contains(line)) {
                                    nonNorm = parts[p];
                                    String ch2 = nonNorm.substring(3);
                                    String normaliseF = "bA$" + " " + ch2;
                                    parts[p] = normaliseF;

                                } else if (parts[p].indexOf("wbA$") == 0 && parts[p].substring(4).contains(line)) {
                                    nonNorm = parts[p];
                                    String ch2 = nonNorm.substring(4);
                                    String normaliseF = "wbA$" + " " + ch2;
                                    parts[p] = normaliseF;

                                }
                                // b$ykH

                                if (parts[p].indexOf("b$") == 0 && parts[p].substring(2).contains(line)) {
                                    nonNorm = parts[p];
                                    String ch2 = nonNorm.substring(2);
                                    String normaliseF = "bA$" + " " + ch2;
                                    parts[p] = normaliseF;

                                } else if (parts[p].indexOf("wb$") == 0 && parts[p].substring(3).contains(line)) {
                                    nonNorm = parts[p];
                                    String ch2 = nonNorm.substring(3);
                                    String normaliseF = "wbA$" + " " + ch2;
                                    parts[p] = normaliseF;

                                }

                            }
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }

                    StringBuilder sb = new StringBuilder();
                    for (String str : parts) {
                        sb.append(str.replaceAll("(?m)(^ *| +(?= |$))", "").replaceAll("(?m)^$([\r\n]+?)(^$[\r\n]+?^)+", "$1"));
                        sb.append(" ");
                    }

                    contenu = sb.toString();

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                // regle verbe negation

                try {

                    String[] parts = contenu.split(" ");
                    //int i=0;
                    for (int p = 0; p < parts.length; p++) {
                        try (BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream("lataiListeVerbes.txt"), "UTF8"))) {

                            for (String line; (line = br1.readLine()) != null;) {

                                int L = line.length();
                                //mAykH$
                                //مانخالفش
                                if (parts[p].indexOf("mA") == 0 && parts[p].contains(line) && parts[p].indexOf("$") == L + 2 && parts[p].lastIndexOf("$") == parts[p].length() - 1) {
                                    nonNorm = parts[p];
                                    String normalise = MethodeRegles.negation_mA_Verb2(nonNorm);
                                    parts[p] = normalise;

                                } //mAykHhA$
                                else if (parts[p].indexOf("mA") == 0 && parts[p].contains(line) && (parts[p].indexOf("hA") == L + 2 || parts[p].indexOf("hw") == L + 2) && parts[p].indexOf("$") == L + 4 && parts[p].lastIndexOf("$") == parts[p].length() - 1) {
                                    nonNorm = parts[p];
                                    String normalise = "mA " + nonNorm.substring(2);
                                    parts[p] = normalise;

                                } //wmAykH$
                                else if (parts[p].indexOf("wmA") == 0 && parts[p].contains(line) && parts[p].indexOf("$") == L + 3 && parts[p].lastIndexOf("$") == parts[p].length() - 1) {
                                    nonNorm = parts[p];
                                    String normalise = "wmA " + nonNorm.substring(3);
                                    parts[p] = normalise;

                                } //wmAykHhA$
                                else if (parts[p].indexOf("wmA") == 0 && parts[p].contains(line) && (parts[p].indexOf("hA") == L + 3 || parts[p].indexOf("hw") == L + 3) && parts[p].indexOf("$") == L + 5 && parts[p].lastIndexOf("$") == parts[p].length() - 1) {
                                    nonNorm = parts[p];
                                    String normalise = "mA " + nonNorm.substring(3);
                                    parts[p] = normalise;

                                } //mAykHlw$
                                else if (parts[p].indexOf("mA") == 0 && parts[p].contains(line) && parts[p].indexOf("l") == L + 2 && line.endsWith("l") == false && parts[p].lastIndexOf("$") == parts[p].length() - 1) {
                                    nonNorm = parts[p];
                                    String ch1 = nonNorm.substring(2, L + 2);
                                    String ch2 = nonNorm.substring(L + 2);
                                    String normaliseF = "mA" + " " + ch1 + " " + ch2;
                                    parts[p] = normaliseF;

                                } //mAykHhAlw$
                                else if (parts[p].indexOf("mA") == 0 && parts[p].contains(line) && (parts[p].indexOf("hA") == L + 2 || parts[p].indexOf("hw") == L + 2) && line.endsWith("l") == false && parts[p].lastIndexOf("$") == parts[p].length() - 1) {
                                    nonNorm = parts[p];
                                    String ch1 = nonNorm.substring(2, L + 4);
                                    String ch2 = nonNorm.substring(L + 4);
                                    String normaliseF = "mA" + " " + ch1 + " " + ch2;
                                    parts[p] = normaliseF;

                                } //wmAykHhAlw$
                                else if (parts[p].indexOf("wmA") == 0 && parts[p].contains(line) && (parts[p].indexOf("hA") == L + 3 || parts[p].indexOf("hw") == L + 3) && line.endsWith("l") == false && parts[p].lastIndexOf("$") == parts[p].length() - 1) {
                                    nonNorm = parts[p];
                                    String ch1 = nonNorm.substring(3, L + 5);
                                    String ch2 = nonNorm.substring(L + 5);
                                    String normaliseF = "wmA" + " " + ch1 + " " + ch2;
                                    parts[p] = normaliseF;

                                } //wmAykHlw$
                                else if (parts[p].indexOf("wmA") == 0 && parts[p].contains(line) && parts[p].indexOf("l") == L + 3 && line.endsWith("l") == false && parts[p].lastIndexOf("$") == parts[p].length() - 1) {
                                    nonNorm = parts[p];
                                    String ch1 = nonNorm.substring(3, L + 3);
                                    String ch2 = nonNorm.substring(L + 3);
                                    String normaliseF = "wmA" + " " + ch1 + " " + ch2;
                                    parts[p] = normaliseF;

                                } //mAqAllw$
                                else if (parts[p].indexOf("mA") == 0 && parts[p].contains(line) && parts[p].lastIndexOf("l") == L + 2 && line.endsWith("l") == true && parts[p].lastIndexOf("$") == parts[p].length() - 1) {
                                    nonNorm = parts[p];
                                    String ch1 = nonNorm.substring(2, L + 2);
                                    String ch2 = nonNorm.substring(L + 2);
                                    String normaliseF = "mA" + " " + ch1 + " " + ch2;
                                    parts[p] = normaliseF;

                                } //wmAqAllw$
                                else if (parts[p].indexOf("wmA") == 0 && parts[p].contains(line) && parts[p].lastIndexOf("l") == L + 3 && line.endsWith("l") == true && parts[p].lastIndexOf("$") == parts[p].length() - 1) {
                                    nonNorm = parts[p];
                                    String ch1 = nonNorm.substring(3, L + 3);
                                    String ch2 = nonNorm.substring(L + 3);
                                    String normaliseF = "mA" + " " + ch1 + " " + ch2;
                                    parts[p] = normaliseF;

                                } //mAqalw$
                                else if (parts[p].indexOf("mA") == 0 && parts[p].contains(line) && line.endsWith("l") == true && parts[p].lastIndexOf("$") == parts[p].length() - 1) {
                                    nonNorm = parts[p];
                                    String normalise = MethodeRegles.negation_mA_Verb2(nonNorm);
                                    parts[p] = normalise;

                                } //wmAqAlw$
                                else if (parts[p].indexOf("wmA") == 0 && parts[p].contains(line) && line.endsWith("l") == true && parts[p].lastIndexOf("$") == parts[p].length() - 1) {
                                    nonNorm = parts[p];
                                    String normalise = "wmA " + nonNorm.substring(3);
                                    parts[p] = normalise;

                                } //mA ykHlw$ /kHl$
                                else if (p != 0 && parts[p - 1].equals("mA") && parts[p].indexOf(line) == 0 && parts[p].indexOf("l") == L && line.endsWith("l") == false && parts[p].lastIndexOf("$") == parts[p].length() - 1) {
                                    nonNorm = parts[p];
                                    String ch1 = nonNorm.substring(0, L);
                                    String ch2 = nonNorm.substring(L);
                                    String normaliseF = ch1 + " " + ch2;
                                    parts[p] = normaliseF;

                                } //wmA ykHlw$
                                else if (p != 0 && parts[p - 1].equals("wmA") && parts[p].indexOf(line) == 0 && parts[p].indexOf("l") == L && line.endsWith("l") == false && parts[p].lastIndexOf("$") == parts[p].length() - 1) {
                                    nonNorm = parts[p];
                                    String ch1 = nonNorm.substring(0, L);
                                    String ch2 = nonNorm.substring(L);
                                    String normaliseF = ch1 + " " + ch2;
                                    parts[p] = normaliseF;

                                } //mA qAllw$
                                else if (p != 0 && parts[p - 1].equals("mA") && parts[p].indexOf(line) == 0 && parts[p].lastIndexOf("l") == L && line.endsWith("l") == true && parts[p].lastIndexOf("$") == parts[p].length() - 1) {
                                    nonNorm = parts[p];
                                    String ch1 = nonNorm.substring(0, L);
                                    String ch2 = nonNorm.substring(L);
                                    String normaliseF = ch1 + " " + ch2;
                                    parts[p] = normaliseF;

                                } //wmA qAllw$
                                else if (p != 0 && parts[p - 1].equals("wmA") && parts[p].indexOf(line) == 0 && parts[p].lastIndexOf("l") == L && line.endsWith("l") == true && parts[p].lastIndexOf("$") == parts[p].length() - 1) {
                                    nonNorm = parts[p];
                                    String ch1 = nonNorm.substring(0, L);
                                    String ch2 = nonNorm.substring(L);
                                    String normaliseF = ch1 + " " + ch2;
                                    parts[p] = normaliseF;

                                } //mtkAfyny$/kHl/mykH$/مغشش
                                else if (parts[p].indexOf("m") == 0 && parts[p].contains(line) && parts[p].lastIndexOf("$") == L + 1 && parts[p].lastIndexOf("$") == parts[p].length() - 1) {
                                    nonNorm = parts[p];
                                    String normalise = MethodeRegles.negation_mA_Verb1(nonNorm);
                                    parts[p] = normalise;

                                } //wmtkAfyny$
                                else if (parts[p].indexOf("wm") == 0 && parts[p].contains(line) && parts[p].lastIndexOf("$") == L + 2 && parts[p].lastIndexOf("$") == parts[p].length() - 1) {
                                    nonNorm = parts[p];
                                    String normalise = "wmA " + nonNorm.substring(2);
                                    parts[p] = normalise;

                                } //mykHlw$ //ما نخ ليوش
                                else if (parts[p].indexOf("m") == 0 && parts[p].contains(line) && parts[p].indexOf("l") == L + 1 && line.endsWith("l") == false && parts[p].lastIndexOf("$") == parts[p].length() - 1) {
                                    nonNorm = parts[p];
                                    String ch1 = nonNorm.substring(1, L + 1);
                                    String ch2 = nonNorm.substring(L + 1);
                                    String normaliseF = "mA" + " " + ch1 + " " + ch2;
                                    parts[p] = normaliseF;

                                } //wmykHlw$
                                else if (parts[p].indexOf("wm") == 0 && parts[p].contains(line) && parts[p].indexOf("l") == L + 2 && line.endsWith("l") == false && parts[p].lastIndexOf("$") == parts[p].length() - 1) {
                                    nonNorm = parts[p];
                                    String ch1 = nonNorm.substring(2, L + 2);
                                    String ch2 = nonNorm.substring(L + 2);
                                    String normaliseF = "wmA" + " " + ch1 + " " + ch2;
                                    parts[p] = normaliseF;

                                } //mqAllw$
                                else if (parts[p].indexOf("m") == 0 && parts[p].contains(line) && parts[p].lastIndexOf("l") == L + 1 && line.endsWith("l") == true && parts[p].lastIndexOf("$") == parts[p].length() - 1) {
                                    nonNorm = parts[p];
                                    String ch1 = nonNorm.substring(1, L + 1);
                                    String ch2 = nonNorm.substring(L + 1);
                                    String normaliseF = "mA" + " " + ch1 + " " + ch2;
                                    parts[p] = normaliseF;

                                } //wmqAllw$
                                else if (parts[p].indexOf("wm") == 0 && parts[p].contains(line) && parts[p].lastIndexOf("l") == L + 2 && line.endsWith("l") == true && parts[p].lastIndexOf("$") == parts[p].length() - 1) {
                                    nonNorm = parts[p];
                                    String ch1 = nonNorm.substring(2, L + 2);
                                    String ch2 = nonNorm.substring(L + 2);
                                    String normaliseF = "wmA" + " " + ch1 + " " + ch2;
                                    parts[p] = normaliseF;

                                } //mqAlw$
                                else if (parts[p].indexOf("m") == 0 && parts[p].contains(line) && line.endsWith("l") == true && parts[p].lastIndexOf("$") == parts[p].length() - 1) {
                                    nonNorm = parts[p];
                                    String normalise = "mA " + nonNorm.substring(1);
                                    parts[p] = normalise;

                                } //wmqAlw$
                                else if (parts[p].indexOf("wm") == 0 && parts[p].contains(line) && line.endsWith("l") == true && parts[p].lastIndexOf("$") == parts[p].length() - 1) {
                                    nonNorm = parts[p];
                                    String normalise = "wmA " + nonNorm.substring(2);
                                    parts[p] = normalise;

                                }

                            }

                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }

                    StringBuilder sb = new StringBuilder();
                    for (String str : parts) {
                        sb.append(str.replaceAll("(?m)(^ *| +(?= |$))", "").replaceAll("(?m)^$([\r\n]+?)(^$[\r\n]+?^)+", "$1"));
                        sb.append(" ");
                    }

                    contenu = sb.toString();

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                //question

                try {

                    String[] parts = contenu.split(" ");
                    int i = 0;
                    for (int p = 0; p < parts.length; p++) {
                        try (BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream("lataiListeVerbes.txt"), "UTF8"))) {
                            for (String line; (line = br1.readLine()) != null;) {
                                int L = line.length();

                                //A$gSyt/
                                if ((parts[p].indexOf("A$") == 0 || parts[p].indexOf(">$") == 0) && parts[p].substring(2).contains(line) && parts[p].endsWith("p") == false) {
                                    nonNorm = parts[p];
                                    String ch1 = nonNorm.substring(2);
                                    String normalise = "A$ " + ch1;
                                    parts[p] = normalise;

                                }
                                //wA$gSyt
                                if ((parts[p].indexOf("wA$") == 0 || parts[p].indexOf("w>$") == 0) && parts[p].substring(3).contains(line) && parts[p].endsWith("p") == false) {
                                    nonNorm = parts[p];
                                    String ch1 = nonNorm.substring(3);
                                    String normalise = "wA$ " + ch1;
                                    parts[p] = normalise;

                                } //$gSyt/$hwr
                                else if (parts[p].indexOf("$") == 0 && parts[p].substring(1).contains(line) && parts[p].endsWith("p") == false) {
                                    nonNorm = parts[p];
                                    String ch1 = nonNorm.substring(1);
                                    String normalise = "A$ " + ch1;
                                    parts[p] = normalise;

                                } //w$gSyt
                                else if (parts[p].indexOf("w$") == 0 && parts[p].substring(2).contains(line) && parts[p].endsWith("p") == false) {
                                    nonNorm = parts[p];
                                    String ch1 = nonNorm.substring(2);
                                    String normalise = "wA$ " + ch1;
                                    parts[p] = normalise;

                                }
                            }

                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }

                    StringBuilder sb = new StringBuilder();
                    for (String str : parts) {
                        sb.append(str.replaceAll("(?m)(^ *| +(?= |$))", "").replaceAll("(?m)^$([\r\n]+?)(^$[\r\n]+?^)+", "$1"));
                        sb.append(" ");
                    }

                    contenu = sb.toString();

                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                //regle mA+verbe
                try {

                    String[] parts = contenu.split(" ");

                    int i = 0;
                    for (int p = 0; p < parts.length; p++) {
                        try (BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream("Latain liste mA+verbe.txt"), "UTF8"))) {
                            for (String line; (line = br1.readLine()) != null;) { // mAkml
                                int L = line.length();
                                if (parts[p].indexOf("mA") == 0 && parts[p].substring(2).equals(line)) {
                                    nonNorm = parts[p];
                                    String ch1 = nonNorm.substring(2);
                                    String normaliseF = "mA" + " " + ch1;
                                    parts[p] = normaliseF;

                                } else if (parts[p].indexOf("wmA") == 0 && parts[p].substring(3).equals(line)) {
                                    nonNorm = parts[p];
                                    String ch1 = nonNorm.substring(3);
                                    String normaliseF = "mA" + " " + ch1;
                                    parts[p] = normaliseF;

                                }

                            }
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }

                    StringBuilder sb = new StringBuilder();
                    for (String str : parts) {
                        sb.append(str.replaceAll("(?m)(^ *| +(?= |$))", "").replaceAll("(?m)^$([\r\n]+?)(^$[\r\n]+?^)+", "$1"));
                        sb.append(" ");
                    }

                    contenu = sb.toString();

                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                //regle verbe +pronom
                try {

                    String[] parts = contenu.split(" ");
                    //int i=0;

                    for (int p = 0; p < parts.length; p++) {
                        try (BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream("lataiListeVerbes.txt"), "UTF8"))) {
                            for (String line; (line = br1.readLine()) != null;) { // cas /xzrlhA /المليح/  عامل
                                int L = line.length();

                                if (parts[p].indexOf(line) == 0 && parts[p].indexOf("l") == L && line.endsWith("l") == false && (parts[p].substring(L).equals("ly") || parts[p].substring(L).equals("lnA")
                                        || parts[p].substring(L).equals("lk") || parts[p].substring(L).equals("lkm") || parts[p].substring(L).equals("lhA")
                                        || parts[p].substring(L).equals("lhm") || parts[p].substring(L).equals("lw")) && parts[p].endsWith("l") == false) {
                                    nonNorm = parts[p];
                                    String ch1 = nonNorm.substring(0, L);
                                    String ch2 = nonNorm.substring(L);
                                    String normaliseF = ch1 + " " + ch2;
                                    parts[p] = normaliseF;

                                    /*
                                     * i++;
                                     * System.out.println("verbe+pronom:"+i+":
                                     * "+parts[p]);
                                     */
                                } // yEmlhAlhm/
                                else if (parts[p].indexOf(line) == 0 && (parts[p].indexOf("hA") == L || parts[p].indexOf("hw") == L) && parts[p].lastIndexOf("l") == L + 2) {
                                    nonNorm = parts[p];
                                    String ch1 = nonNorm.substring(0, L + 2);
                                    String ch2 = nonNorm.substring(L + 2);
                                    String normaliseF = ch1 + " " + ch2;
                                    parts[p] = normaliseF;
                                    /*
                                     * i++;
                                     * System.out.println("verbe+pronom:"+i+":
                                     * "+parts[p]);
                                     */

                                } //qAllhm
                                else if (parts[p].indexOf(line) == 0 && (parts[p].substring(L).equals("ly") || parts[p].substring(L).equals("lnA")
                                        || parts[p].substring(L).equals("lk") || parts[p].substring(L).equals("lkm") || parts[p].substring(L).equals("lhA")
                                        || parts[p].substring(L).equals("lhm") || parts[p].substring(L).equals("lw")) && line.lastIndexOf("l") != -1 && parts[p].lastIndexOf("l") == L) {
                                    nonNorm = parts[p];
                                    String ch1 = nonNorm.substring(0, L);
                                    String ch2 = nonNorm.substring(L);
                                    String normaliseF = ch1 + " " + ch2;
                                    parts[p] = normaliseF;
                                    /*
                                     * i++;
                                     * System.out.println("verbe+pronom:"+i+":
                                     * "+parts[p]);
                                     */

                                } //wkHlw
                                else if (parts[p].indexOf("w") == 0 && parts[p].indexOf(line) == 1 && parts[p].indexOf("l") == L + 1 && line.endsWith("l") == false && (parts[p].substring(L + 1).equals("ly") || parts[p].substring(L + 1).equals("lnA")
                                        || parts[p].substring(L + 1).equals("lk") || parts[p].substring(L + 1).equals("lkm") || parts[p].substring(L + 1).equals("lhA")
                                        || parts[p].substring(L + 1).equals("lhm") || parts[p].substring(L + 1).equals("lw")) && parts[p].substring(L + 1).length() > 1) {
                                    nonNorm = parts[p];
                                    String ch1 = nonNorm.substring(0, L + 1);
                                    String ch2 = nonNorm.substring(L + 1);
                                    String normaliseF = ch1 + " " + ch2;
                                    parts[p] = normaliseF;
                                    /*
                                     * i++;
                                     * System.out.println("verbe+pronom:"+i+":
                                     * "+parts[p]);
                                     */

                                } // wyEmlhAlhm
                                else if (parts[p].indexOf("w") == 0 && parts[p].indexOf(line) == 1 && (parts[p].indexOf("hA") == L + 1 || parts[p].indexOf("hw") == L + 1) && parts[p].lastIndexOf("l") == L + 3) {
                                    nonNorm = parts[p];
                                    String ch1 = nonNorm.substring(0, L + 3);
                                    String ch2 = nonNorm.substring(L + 3);
                                    String normaliseF = ch1 + " " + ch2;
                                    parts[p] = normaliseF;
                                    /*
                                     * i++;
                                     * System.out.println("verbe+pronom:"+i+":
                                     * "+parts[p]);
                                     */

                                } //wqAllhm
                                else if (parts[p].indexOf("w") == 0 && parts[p].indexOf(line) == 1 && line.endsWith("l") == true && parts[p].lastIndexOf("l") == L + 1 && (parts[p].substring(L + 1).equals("ly") || parts[p].substring(L + 1).equals("lnA")
                                        || parts[p].substring(L + 1).equals("lk") || parts[p].substring(L + 1).equals("lkm") || parts[p].substring(L + 1).equals("lhA")
                                        || parts[p].substring(L + 1).equals("lhm") || parts[p].substring(L + 1).equals("lw"))) {
                                    nonNorm = parts[p];
                                    String ch1 = nonNorm.substring(0, L + 1);
                                    String ch2 = nonNorm.substring(L + 1);
                                    String normaliseF = ch1 + " " + ch2;
                                    parts[p] = normaliseF;
                                    /*
                                     * i++;
                                     * System.out.println("verbe+pronom:"+i+":
                                     * "+parts[p]);
                                     */

                                }

                            }
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }

                    StringBuilder sb = new StringBuilder();
                    for (String str : parts) {
                        sb.append(str.replaceAll("(?m)(^ *| +(?= |$))", "").replaceAll("(?m)^$([\r\n]+?)(^$[\r\n]+?^)+", "$1"));
                        sb.append(" ");
                    }

                    contenu = sb.toString();

                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                //3éme personne de singulier(liste des noms et seulement lw)
                try {

                    String[] parts = contenu.split(" ");

                    int i = 0;
                    for (int p = 0; p < parts.length; p++) {
                        try (BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream("latainlisteVerbe3Personne.txt"), "UTF8"))) {
                            for (String line; (line = br1.readLine()) != null;) {
                                int L = line.length();
                                int pa = parts[p].length();
                                // noms: وحطو/AmtyAzwA /يد
                                if (parts[p].length() > L && parts[p].lastIndexOf("w") == pa - 1 && parts[p].indexOf("w") == 0 && parts[p].substring(1, pa - 1).equals(line)) {
                                    nonNorm = parts[p];
                                    String ch1 = nonNorm.substring(0, L + 1);
                                    String normalise = ch1 + "h";
                                    parts[p] = normalise;
                                    /*
                                     * i++; System.out.println("3PerNoms:"+i+":
                                     * "+parts[p]);
                                     */
                                } else if (parts[p].length() > L && parts[p].endsWith("wA") == true && parts[p].indexOf("w") == 0 && parts[p].substring(1, pa - 2).equals(line)) {
                                    nonNorm = parts[p];
                                    String ch1 = nonNorm.substring(0, L + 1);
                                    String normalise = ch1 + "h";
                                    parts[p] = normalise;
                                    /*
                                     * i++; System.out.println("3PerNoms:"+i+":
                                     * "+parts[p]);
                                     */

                                } else if (parts[p].length() > L && parts[p].lastIndexOf("w") == pa - 1 && parts[p].indexOf("l") == 0 && parts[p].substring(1, pa - 1).equals(line)) {
                                    nonNorm = parts[p];
                                    String ch1 = nonNorm.substring(0, L + 1);
                                    String normalise = ch1 + "h";
                                    parts[p] = normalise;
                                    /*
                                     * i++; System.out.println("3PerNoms:"+i+":
                                     * "+parts[p]);
                                     */
                                } else if (parts[p].length() > L && parts[p].endsWith("wA") == true && parts[p].indexOf("l") == 0 && parts[p].substring(1, pa - 2).equals(line)) {
                                    nonNorm = parts[p];
                                    String ch1 = nonNorm.substring(0, L + 1);
                                    String normalise = ch1 + "h";
                                    parts[p] = normalise;
                                    /*
                                     * i++; System.out.println("3PerNoms:"+i+":
                                     * "+parts[p]);
                                     */

                                } else if (parts[p].length() > L && parts[p].lastIndexOf("w") == pa - 1 && parts[p].indexOf("k") == 0 && parts[p].substring(1, pa - 1).equals(line)) {
                                    nonNorm = parts[p];
                                    String ch1 = nonNorm.substring(0, L + 1);
                                    String normalise = ch1 + "h";
                                    parts[p] = normalise;
                                    /*
                                     * i++; System.out.println("3PerNoms:"+i+":
                                     * "+parts[p]);
                                     */
                                } else if (parts[p].length() > L && parts[p].endsWith("wA") == true && parts[p].indexOf("k") == 0 && parts[p].substring(1, pa - 2).equals(line)) {
                                    nonNorm = parts[p];
                                    String ch1 = nonNorm.substring(0, L + 1);
                                    String normalise = ch1 + "h";
                                    parts[p] = normalise;
                                    /*
                                     * i++; System.out.println("3PerNoms:"+i+":
                                     * "+parts[p]);
                                     */

                                } else if (parts[p].length() > L && parts[p].lastIndexOf("w") == pa - 1 && parts[p].indexOf("b") == 0 && parts[p].substring(1, pa - 1).equals(line)) {
                                    nonNorm = parts[p];
                                    String ch1 = nonNorm.substring(0, L + 1);
                                    String normalise = ch1 + "h";
                                    parts[p] = normalise;
                                    /*
                                     * i++; System.out.println("3PerNoms:"+i+":
                                     * "+parts[p]);
                                     */
                                } else if (parts[p].length() > L && parts[p].endsWith("wA") == true && parts[p].indexOf("b") == 0 && parts[p].substring(1, pa - 2).equals(line)) {
                                    nonNorm = parts[p];
                                    String ch1 = nonNorm.substring(0, L + 1);
                                    String normalise = ch1 + "h";
                                    parts[p] = normalise;
                                    /*
                                     * i++; System.out.println("3PerNoms:"+i+":
                                     * "+parts[p]);
                                     */

                                } else if (parts[p].length() > L && parts[p].lastIndexOf("w") == pa - 1 && parts[p].substring(0, pa - 1).equals(line)) {
                                    nonNorm = parts[p];
                                    String ch1 = nonNorm.substring(0, L);
                                    String normalise = ch1 + "h";
                                    parts[p] = normalise;
                                    /*
                                     * i++; System.out.println("3PerNoms:"+i+":
                                     * "+parts[p]);
                                     */

                                } else if (parts[p].length() > L && parts[p].endsWith("wA") == true && parts[p].substring(0, pa - 2).equals(line)) {
                                    nonNorm = parts[p];
                                    String ch1 = nonNorm.substring(0, L);
                                    String normalise = ch1 + "h";
                                    parts[p] = normalise;
                                    /*
                                     * i++; System.out.println("3PerNoms:"+i+":
                                     * "+parts[p]);
                                     */

                                }

                            }
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }

                    StringBuilder sb = new StringBuilder();
                    for (String str : parts) {
                        sb.append(str.replaceAll("(?m)(^ *| +(?= |$))", "").replaceAll("(?m)^$([\r\n]+?)(^$[\r\n]+?^)+", "$1"));
                        sb.append(" ");
                    }

                    contenu = sb.toString();

                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                //3éme personne de singulier et pluriel
                try {

                    String[] parts = contenu.split(" ");
                    int i = 0;

                    for (int p = 0; p < parts.length; p++) {
                        try (BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream("lataiListeVerbes.txt"), "UTF8"))) {
                            for (String line; (line = br1.readLine()) != null;) {
                                int L = line.length();
                                int pa = parts[p].length();
                                // noms: وحطو

                                if (parts[p].length() == L && parts[p].endsWith("Aw") == true && parts[p].equals(line)) {
                                    String normalise = parts[p] + "A";
                                    parts[p] = normalise;
                                    /*
                                     * i++; System.out.println("3PerVerb:"+i+":
                                     * "+parts[p]);
                                     */

                                } else if (parts[p].endsWith("yw") == true && parts[p].equals(line) && parts[p].length() == L) {
                                    String normalise = parts[p] + "A";
                                    parts[p] = normalise;
                                    /*
                                     * i++; System.out.println("3PerVerb:"+i+":
                                     * "+parts[p]);
                                     */

                                } else if (parts[p].length() >= L && (parts[p].endsWith("Aw") == true || parts[p].endsWith("yw") == true) && parts[p].substring(1, pa).equals(line)) {
                                    String normalise = parts[p] + "A";
                                    parts[p] = normalise;
                                    /*
                                     * i++; System.out.println("3PerVerb:"+i+":
                                     * "+parts[p]);
                                     */

                                } else if (parts[p].length() > L && parts[p].lastIndexOf("w") == pa - 1 && parts[p].substring(1, pa - 1).equals(line)) {
                                    nonNorm = parts[p];
                                    String ch1 = nonNorm.substring(0, L + 1);
                                    String normalise = ch1 + "h" + "/" + parts[p] + "A";
                                    parts[p] = normalise;
                                    /*
                                     * i++; System.out.println("3PerVerb:"+i+":
                                     * "+parts[p]);
                                     */
                                } else if (parts[p].length() > L && parts[p].lastIndexOf("w") == pa - 1 && parts[p].substring(0, pa - 1).equals(line)) {
                                    nonNorm = parts[p];
                                    String ch1 = nonNorm.substring(0, L);
                                    String normalise = ch1 + "h" + "/" + parts[p] + "A";
                                    parts[p] = normalise;
                                    /*
                                     * i++; System.out.println("3PerVerb:"+i+":
                                     * "+parts[p]);
                                     */

                                } else if (parts[p].equals("lw")) {
                                    parts[p] = "lh";
                                    /*
                                     * i++; System.out.println("3PerVerb:"+i+":
                                     * "+parts[p]);
                                     */

                                }

                            }
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }

                    StringBuilder sb = new StringBuilder();
                    for (String str : parts) {
                        sb.append(str.replaceAll("(?m)(^ *| +(?= |$))", "").replaceAll("(?m)^$([\r\n]+?)(^$[\r\n]+?^)+", "$1"));
                        sb.append(" ");
                    }

                    contenu = sb.toString();

                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                //regle ya+noms
                try {

                    String[] parts = contenu.split(" ");
                    int i = 0;

                    for (int p = 0; p < parts.length; p++) {
                        try (BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream("lataintNomPropre.txt"), "UTF8"))) {
                            for (String line; (line = br1.readLine()) != null;) {

                                if (parts[p].indexOf("yA") == 0 && parts[p].substring(2).equals(line)) {
                                    nonNorm = parts[p];
                                    String ch1 = nonNorm.substring(2);
                                    String normalise = "yA " + ch1;
                                    parts[p] = normalise;
                                    /*
                                     * i++; System.out.println("yA:"+i+":
                                     * "+parts[p]);
                                     */
                                }

                                if (parts[p].indexOf("wyA") == 0 && parts[p].substring(3).equals(line)) {
                                    nonNorm = parts[p];
                                    String ch1 = nonNorm.substring(3);
                                    String normalise = "wyA " + ch1;
                                    parts[p] = normalise;
                                    /*
                                     * i++; System.out.println("yA:"+i+":
                                     * "+parts[p]);
                                     */
                                }
                            }
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }

                    StringBuilder sb = new StringBuilder();
                    for (String str : parts) {
                        sb.append(str.replaceAll("(?m)(^ *| +(?= |$))", "").replaceAll("(?m)^$([\r\n]+?)(^$[\r\n]+?^)+", "$1"));
                        sb.append(" ");
                    }

                    contenu = sb.toString();

                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                //regle ElY
                try {

                    String[] parts = contenu.split(" ");

                    for (int p = 0; p < parts.length; p++) {
                        try (BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream("LatainListNomArabe.txt"), "UTF8"))) {
                            for (String line; (line = br1.readLine()) != null;) {

                                if (parts[p].equals("Ely") && parts[p + 1].contains(line)) {

                                    parts[p] = "ElY";
                                } else if (parts[p].equals("wEly") && parts[p + 1].contains(line)) {

                                    parts[p] = "wElY";
                                }
                            }
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }

                    StringBuilder sb = new StringBuilder();
                    for (String str : parts) {
                        sb.append(str.replaceAll("(?m)(^ *| +(?= |$))", "").replaceAll("(?m)^$([\r\n]+?)(^$[\r\n]+?^)+", "$1"));
                        sb.append(" ");
                    }

                    contenu = sb.toString();

                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                //regle pour b + " "
                try {
                    String[] parts = contenu.split(" ");

                    for (int p = 0; p < parts.length; p++) {
                        if (parts[p].equals("b") && parts[p + 1] == " ") {
                            parts[p + 1] = "";

                        }
                    }

                    StringBuilder sb = new StringBuilder();
                    for (String str : parts) {
                        sb.append(str.replaceAll("(?m)(^ *| +(?= |$))", "").replaceAll("(?m)^$([\r\n]+?)(^$[\r\n]+?^)+", "$1"));
                        sb.append(" ");
                    }

                    contenu = sb.toString();

                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                //nombre
                try {

                    String[] parts = contenu.split(" ");
                    int i = 0;

                    String[] Nombre = {"HdA$", "vnA$", "vltTA$", "ArbETA$", "xmsTA$", "stTA$", "sbETA$", "vmnTA$", "tsETA$"};

                    for (int p = 0; p < parts.length; p++) {

                        if ((Plusieurs_fonctions.isANumber(parts[p]) == true)) {
                            parts[p] = Plusieurs_fonctions.convnumber(parts[p]);
                            /*
                             * i++; System.out.println("nombre:"+i+":
                             * "+parts[p]);
                             */
                        }

                        for (int n = 0; n < Nombre.length; n++) {
                            int L = Nombre[n].length();

                            // cas : HdA$nrAjel
                            if (parts[p].contains(Nombre[n])
                                    && parts[p].indexOf("n") == L
                                    && parts[p].length() > L + 1) {
                                nonNorm = parts[p];
                                String ch1 = nonNorm.substring(0, L + 1);
                                String normalise = ch1 + " "
                                        + nonNorm.substring(L + 1);
                                parts[p] = normalise;
                                /*
                                 * i++; System.out.println("nombre:"+i+":
                                 * "+parts[p]);
                                 */

                            } // cas : HdA$ n rAjl
                            else if (parts[p].equals(Nombre[n])
                                    && parts[p + 1].equals("n")) {
                                nonNorm = parts[p] + parts[p + 1];

                                String normalise = nonNorm;
                                parts[p] = normalise;
                                parts[p + 1] = "";
                                /*
                                 * i++; System.out.println("nombre:"+i+":
                                 * "+parts[p]);
                                 */
                            } // cas : HdA$ rAjl
                            else if (parts[p].equals(Nombre[n]) && parts[p + 1].equals("n") == false) {
                                parts[p] = parts[p] + "n";
                                /*
                                 * i++; System.out.println("nombre:"+i+":
                                 * "+parts[p]);
                                 */

                            }

                        }

                    }

                    StringBuilder sb = new StringBuilder();
                    for (String str : parts) {
                        sb.append(str.replaceAll("(?m)(^ *| +(?= |$))", "")
                                .replaceAll("(?m)^$([\r\n]+?)(^$[\r\n]+?^)+",
                                        "$1"));
                        sb.append(" ");
                    }

                    contenu = sb.toString();

                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                //regle pour h en p
                try {

                    String[] parts = contenu.split(" ");
                    int i = 0;

                    for (int p = 0; p < parts.length; p++) {
                        try (BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream("latinhenp.txt"), "UTF8"))) {
                            for (String line; (line = br1.readLine()) != null;) {
                                if (parts[p].equals(line.substring(0, line.indexOf(" ")))) {
                                    parts[p] = line.substring(line.indexOf(" ") + 1);
                                    /*
                                     * i++; System.out.println("henP:"+i+":
                                     * "+parts[p]);
                                     */
                                } else if ((parts[p].indexOf("w") == 0 || parts[p].indexOf("b") == 0 || parts[p].indexOf("l") == 0 || parts[p].indexOf("f") == 0 || parts[p].indexOf("k") == 0)
                                        && parts[p].substring(1).equals(line.substring(0, line.indexOf(" ")))) {
                                    String ch = parts[p].substring(0, 1);
                                    parts[p] = ch + line.substring(line.indexOf(" ") + 1);
                                    /*
                                     * i++; System.out.println("henP:"+i+":
                                     * "+parts[p]);
                                     */

                                }
                            }
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }

                    StringBuilder sb = new StringBuilder();
                    for (String str : parts) {
                        sb.append(str.replaceAll("(?m)(^ *| +(?= |$))", "").replaceAll("(?m)^$([\r\n]+?)(^$[\r\n]+?^)+", "$1"));
                        sb.append(" ");
                    }

                    contenu = sb.toString();

                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                //regle pour A en p
                try {

                    String[] parts = contenu.split(" ");

                    int i = 0;
                    for (int p = 0; p < parts.length; p++) {
                        try (BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream("latinAenp.txt"), "UTF8"))) {
                            for (String line; (line = br1.readLine()) != null;) {
                                if (parts[p].equals(line.substring(0, line.indexOf(" ")))) {
                                    parts[p] = line.substring(line.indexOf(" ") + 1);
                                    // i++;
                                    //System.out.println("AenP:"+i+": "+parts[p]);

                                } else if ((parts[p].indexOf("w") == 0 || parts[p].indexOf("b") == 0 || parts[p].indexOf("l") == 0 || parts[p].indexOf("f") == 0 || parts[p].indexOf("k") == 0)
                                        && parts[p].substring(1).equals(line.substring(0, line.indexOf(" ")))) {
                                    String ch = parts[p].substring(0, 1);
                                    parts[p] = ch + line.substring(line.indexOf(" ") + 1);

                                    // i++;
                                    //  System.out.println("AenP:"+i+": "+parts[p]);
                                }
                            }
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }

                    StringBuilder sb = new StringBuilder();
                    for (String str : parts) {
                        sb.append(str.replaceAll("(?m)(^ *| +(?= |$))", "").replaceAll("(?m)^$([\r\n]+?)(^$[\r\n]+?^)+", "$1"));
                        sb.append(" ");
                    }

                    contenu = sb.toString();

                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                //regle pour Aouy en Y
                try {

                    String[] parts = contenu.split(" ");
                    int i = 0;

                    for (int p = 0; p < parts.length; p++) {
                        try (BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream("latinyouAenY.txt"), "UTF8"))) {
                            for (String line; (line = br1.readLine()) != null;) {
                                if (parts[p].equals(line.substring(line.indexOf(" ") + 1))) {
                                    parts[p] = line.substring(0, line.indexOf(" "));
                                    i++;
                                    System.out.println("AyenY:" + i + ": " + parts[p]);
                                } else if ((parts[p].indexOf("w") == 0 || parts[p].indexOf("b") == 0 || parts[p].indexOf("l") == 0 || parts[p].indexOf("f") == 0 || parts[p].indexOf("k") == 0)
                                        && parts[p].substring(1).equals(line.substring(line.indexOf(" ") + 1))) {
                                    String ch = parts[p].substring(0, 1);
                                    parts[p] = ch + line.substring(0, line.indexOf(" "));
                                    i++;
                                    System.out.println("AyenY:" + i + ": " + parts[p]);

                                }
                            }
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }

                    StringBuilder sb = new StringBuilder();
                    for (String str : parts) {
                        sb.append(str.replaceAll("(?m)(^ *| +(?= |$))", "").replaceAll("(?m)^$([\r\n]+?)(^$[\r\n]+?^)+", "$1"));
                        sb.append(" ");
                    }

                    contenu = sb.toString();

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                //regle pour Yen y
                try {

                    String[] parts = contenu.split(" ");
                    int i = 0;

                    for (int p = 0; p < parts.length; p++) {
                        try (BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream("latainYeny.txt"), "UTF8"))) {
                            for (String line; (line = br1.readLine()) != null;) {
                                if (parts[p].equals(line.substring(line.indexOf(" ") + 1))) {
                                    parts[p] = line.substring(0, line.indexOf(" "));

                                } else if ((parts[p].indexOf("w") == 0 || parts[p].indexOf("b") == 0 || parts[p].indexOf("l") == 0 || parts[p].indexOf("f") == 0 || parts[p].indexOf("k") == 0)
                                        && parts[p].substring(1).equals(line.substring(line.indexOf(" ") + 1))) {
                                    String ch = parts[p].substring(0, 1);
                                    parts[p] = ch + line.substring(0, line.indexOf(" "));

                                }
                            }
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }

                    StringBuilder sb = new StringBuilder();
                    for (String str : parts) {
                        sb.append(str.replaceAll("(?m)(^ *| +(?= |$))", "").replaceAll("(?m)^$([\r\n]+?)(^$[\r\n]+?^)+", "$1"));
                        sb.append(" ");
                    }

                    contenu = sb.toString();

                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                //regle pour Sen s
                try {

                    String[] parts = contenu.split(" ");

                    int i = 0;
                    for (int p = 0; p < parts.length; p++) {
                        try (BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream("latainصenس.txt"), "UTF8"))) {
                            for (String line; (line = br1.readLine()) != null;) {
                                if (parts[p].equals(line.substring(line.indexOf(" ") + 1))) {
                                    parts[p] = line.substring(0, line.indexOf(" "));

                                } else if ((parts[p].indexOf("w") == 0 || parts[p].indexOf("b") == 0 || parts[p].indexOf("l") == 0 || parts[p].indexOf("f") == 0 || parts[p].indexOf("k") == 0)
                                        && parts[p].substring(1).equals(line.substring(line.indexOf(" ") + 1))) {
                                    String ch = parts[p].substring(0, 1);
                                    parts[p] = ch + line.substring(0, line.indexOf(" "));

                                }
                            }
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }

                    StringBuilder sb = new StringBuilder();
                    for (String str : parts) {
                        sb.append(str.replaceAll("(?m)(^ *| +(?= |$))", "").replaceAll("(?m)^$([\r\n]+?)(^$[\r\n]+?^)+", "$1"));
                        sb.append(" ");
                    }

                    contenu = sb.toString();

                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                //regle pour sen S
                try {

                    String[] parts = contenu.split(" ");

                    int i = 0;
                    for (int p = 0; p < parts.length; p++) {
                        try (BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream("latainسenص.txt"), "UTF8"))) {
                            for (String line; (line = br1.readLine()) != null;) {
                                if (parts[p].equals(line.substring(line.indexOf(" ") + 1))) {
                                    parts[p] = line.substring(0, line.indexOf(" "));

                                } else if ((parts[p].indexOf("w") == 0 || parts[p].indexOf("b") == 0 || parts[p].indexOf("l") == 0 || parts[p].indexOf("f") == 0 || parts[p].indexOf("k") == 0)
                                        && parts[p].substring(1).equals(line.substring(line.indexOf(" ") + 1))) {
                                    String ch = parts[p].substring(0, 1);
                                    parts[p] = ch + line.substring(0, line.indexOf(" "));

                                }
                            }
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }

                    StringBuilder sb = new StringBuilder();
                    for (String str : parts) {
                        sb.append(str.replaceAll("(?m)(^ *| +(?= |$))", "").replaceAll("(?m)^$([\r\n]+?)(^$[\r\n]+?^)+", "$1"));
                        sb.append(" ");
                    }

                    contenu = sb.toString();

                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                //regle pour Yen A
                try {

                    String[] parts = contenu.split(" ");
                    int i = 0;

                    for (int p = 0; p < parts.length; p++) {
                        try (BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream("latainYenA.txt"), "UTF8"))) {
                            for (String line; (line = br1.readLine()) != null;) {
                                if (parts[p].equals(line.substring(line.indexOf(" ") + 1))) {
                                    parts[p] = line.substring(0, line.indexOf(" "));

                                } else if ((parts[p].indexOf("w") == 0 || parts[p].indexOf("b") == 0 || parts[p].indexOf("l") == 0 || parts[p].indexOf("f") == 0 || parts[p].indexOf("k") == 0)
                                        && parts[p].substring(1).equals(line.substring(line.indexOf(" ") + 1))) {
                                    String ch = parts[p].substring(0, 1);
                                    parts[p] = ch + line.substring(0, line.indexOf(" "));

                                }
                            }
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }

                    StringBuilder sb = new StringBuilder();
                    for (String str : parts) {
                        sb.append(str.replaceAll("(?m)(^ *| +(?= |$))", "").replaceAll("(?m)^$([\r\n]+?)(^$[\r\n]+?^)+", "$1"));
                        sb.append(" ");
                    }

                    contenu = sb.toString();

                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                //exeption
                try {

                    String[] parts = contenu.split(" ");

                    for (int p = 0; p < parts.length; p++) {
                        try (BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream("latainexeption.txt"), "UTF8"))) {
                            for (String line; (line = br1.readLine()) != null;) {
                                if (parts[p].equals(line.substring(0, line.indexOf(" ")))) {
                                    parts[p] = line.substring(line.indexOf(" ") + 1);
                                } else if ((parts[p].indexOf("w") == 0 || parts[p].indexOf("b") == 0 || parts[p].indexOf("l") == 0 || parts[p].indexOf("f") == 0 || parts[p].indexOf("k") == 0)
                                        && parts[p].substring(1).equals(line.substring(0, line.indexOf(" ")))) {
                                    String ch = parts[p].substring(0, 1);
                                    parts[p] = ch + line.substring(line.indexOf(" ") + 1);

                                }
                            }
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }

                    StringBuilder sb = new StringBuilder();
                    for (String str : parts) {
                        sb.append(str.replaceAll("(?m)(^ *| +(?= |$))", "").replaceAll("(?m)^$([\r\n]+?)(^$[\r\n]+?^)+", "$1"));
                        sb.append(" ");
                    }

                    contenu = sb.toString();

                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                //regle replaceW et les hamza
                try {
                    int i = 0;
                    char[] partsCatacter = contenu.toCharArray();
                    for (int k = 0; k < partsCatacter.length; k++) {
                        if (((k == 0 || partsCatacter[k - 1] == ' ') && 
                                (partsCatacter[k] == 'w') && 
                                partsCatacter[k + 1] == ' ' && (partsCatacter[k + 2] == 'w') && 
                                partsCatacter[k + 3] == ' ')) {
                            partsCatacter[k] = '\u0000';
                            partsCatacter[k + 1] = '\u0000';
                            partsCatacter[k + 3] = '\u0000';

                        } else if (((k == 0 || partsCatacter[k - 1] == ' ') && 
                                (partsCatacter[k] == 'w') && 
                                partsCatacter[k + 1] == ' ')){
                            partsCatacter[k + 1] = '\u0000';

                        } else if ((k == 0 || partsCatacter[k - 1] == 'w') && partsCatacter[k] == 'w' && partsCatacter[k + 1] == ' ') {
                            partsCatacter[k] = ' ';
                            partsCatacter[k + 1] = 'w';

                        } else if (partsCatacter[k] == '|') {
                            nonNorm = String.valueOf(partsCatacter[k]);
                            norm = MethodeRegles.rempAA(nonNorm);
                            partsCatacter[k] = norm.charAt(0);
                            /*
                             * i++; System.out.println("replaceW: "+i);
                             */
                        } else if (partsCatacter[k] == '<') {
                            nonNorm = String.valueOf(partsCatacter[k]);
                            norm = MethodeRegles.rempI(nonNorm);
                            partsCatacter[k] = norm.charAt(0);
                            /*
                             * i++; System.out.println("replaceW: "+i);
                             */

                        } else if (partsCatacter[k] == '>') {
                            nonNorm = String.valueOf(partsCatacter[k]);

                            norm = MethodeRegles.rempHamza(nonNorm);
                            partsCatacter[k] = norm.charAt(0);
                            /*
                             * i++; System.out.println("replaceW: "+i);
                             */
                        } else if (partsCatacter[k] == '&') {
                            nonNorm = String.valueOf(partsCatacter[k]);
                            norm = MethodeRegles.rempHamzaWaw(nonNorm);
                            partsCatacter[k] = norm.charAt(0);
                            /*
                             * i++; System.out.println("replaceW: "+i);
                             */
                        } else if (partsCatacter[k] == '}') {
                            nonNorm = String.valueOf(partsCatacter[k]);

                            norm = MethodeRegles.rempHamzaYa(nonNorm);
                            partsCatacter[k] = norm.charAt(0);
                            /*
                             * i++; System.out.println("replaceW: "+i);
                             */

                        } else if (partsCatacter[k] == '\'') {
                            partsCatacter[k] = '\u0000';
                            /*
                             * i++; System.out.println("replaceW: "+i);
                             */

                        } else if (partsCatacter[k] == 'G') {
                            nonNorm = String.valueOf(partsCatacter[k]);
                            norm = MethodeRegles.rempG(nonNorm);
                            partsCatacter[k] = norm.charAt(0);
                            /*
                             * i++; System.out.println("replaceW: "+i);
                             */

                            
                        }

                    }

                    StringBuilder sb = new StringBuilder();
                    for (char c : partsCatacter) {
                        if (c != '\u0000') {
                            sb.append(c);
                        }
                    }

                    contenu = sb.toString();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                textArea_1.setText(Plusieurs_fonctions.latinToArabic(contenu));
            }

        });

        JButton btnParcourir = new JButton("Parcourir");
        btnParcourir.setBackground(Color.LIGHT_GRAY);
        btnParcourir.setFont(new Font("Arial Black", Font.ITALIC, 14));
        btnParcourir.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                JFileChooser fc = new JFileChooser();
                String nomFic = "";
                //Creer un filtre qui ne sélectionnera que les fichiers .txt
                FileNameExtensionFilter ff = new FileNameExtensionFilter("Fichiers texte", "txt");
                fc.setFileFilter(ff);

                int returnVal = fc.showOpenDialog(getParent());

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    nomFic = fc.getSelectedFile().getAbsolutePath();
                    /*
                     * //Appeler la fonction qui remplira le JTextArea en lui
                     * donnant le nom du fichier récupéré.
                     *
                     * BufferedReader brr = null; try { brr = new
                     * BufferedReader(new FileReader(new File(nomFic))); } catch
                     * (FileNotFoundException e1) { // TODO Auto-generated catch
                     * block e1.printStackTrace(); } String line; StringBuilder
                     * sb = new StringBuilder();
                     *
                     * try { while((line=brr.readLine())!= null){
                     * sb.append(line); sb.append( "\n" ); } } catch
                     * (IOException ee) { // TODO Auto-generated catch block
                     * ee.printStackTrace(); }
                     * txtrHkwmtwa.setText(sb.toString());
                     */

                    StringBuilder str = new StringBuilder();
                    try {
                        BufferedReader br = new BufferedReader(new InputStreamReader(new java.io.FileInputStream(nomFic), StandardCharsets.UTF_8));
                        String ligne = null;

                        while ((ligne = br.readLine()) != null) {
                            str.append(ligne);
                            str.append("\n");
                        }

                    } catch (Exception err) {
                        //JOptionPane.showMessageDialog(null,err);
                    }
                    txtrHkwmtwa.setText(str.toString());

                }

            }

        });
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                        .addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
                                                .addGap(174)
                                                .addComponent(btnParcourir, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(btnNormalize, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(scrollPane_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 769, Short.MAX_VALUE)
                                        .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 769, Short.MAX_VALUE))
                                .addContainerGap())
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(btnNormalize)
                                        .addComponent(btnParcourir, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                                .addGap(17))
        );

        textArea_1 = new JTextArea();
        textArea_1.setFont(new Font("Arial", Font.PLAIN, 16));
        textArea_1.setMargin(new Insets(10, 10, 10, 10));
        textArea_1.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        scrollPane_1.setViewportView(textArea_1);
        scrollPane_1.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        txtrHkwmtwa = new JTextArea();
        txtrHkwmtwa.setFont(new Font("Arial", Font.PLAIN, 16));
        txtrHkwmtwa.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        txtrHkwmtwa.setMargin(new Insets(10, 10, 10, 10));
        scrollPane.setViewportView(txtrHkwmtwa);
        scrollPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        contentPane.setLayout(gl_contentPane);

        /*
         * try { byte[] encoded =
         * Files.readAllBytes(Paths.get("lataintestNouveau.txt")); contenu = new
         * String(encoded, StandardCharsets.UTF_8);
         * textArea.setText(MethodeRegles.latinToArabic(contenu)); } catch
         * (IOException e) { e.printStackTrace(); }
         */
    }

    public static void stringToFile(String contenu, String fileName) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(contenu);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String openFile() {

        JFileChooser fc = new JFileChooser();
        String nomFic = "";
        String text = " ";
        FileNameExtensionFilter ff = new FileNameExtensionFilter("Fichiers texte", "txt");
        fc.setFileFilter(ff);

        int returnVal = fc.showOpenDialog(getParent());

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            nomFic = fc.getSelectedFile().getAbsolutePath();

            StringBuilder str = new StringBuilder();
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(new java.io.FileInputStream(nomFic), StandardCharsets.UTF_8));
                String ligne = null;

                while ((ligne = br.readLine()) != null) {
                    str.append(ligne);
                    str.append("\n");
                }

            } catch (Exception err) {
                //JOptionPane.showMessageDialog(null,err);
            }

            text = str.toString();
            txtrHkwmtwa.setText(str.toString());

        }

        return text;
    }

    public static String supp_esp_supp_alif(String ch, String c) {
        String ch1 = ch.replaceFirst("A ", "");
        System.out.println(ch1);
        return ch1;
    }

    public static String supp_esp(String ch, String c) {
        String ch1 = ch.replace(" ", "");
        System.out.println(ch1);
        return ch1;
    }

    public static String supp_esp_supp_lam(String ch, String c) {
        String ch1 = ch.replaceFirst("l ", "");
        System.out.println(ch1);
        return ch1;
    }

    public static String supp_esp_supp_lam_supp_alif(String ch, String c) {
        String ch1 = ch.replaceFirst("Al ", "");
        System.out.println(ch1);
        return ch1;
    }

    public static String supp_esp_ajout_alif(String ch, String c) {
        String ch1 = ch.replaceFirst("l ", "Al");
        System.out.println(ch1);
        return ch1;
    }

    public static String ajout_alif(String ch, String c) {
        String ch1 = ch.replaceFirst("l", "Al");
        System.out.println(ch1);
        return ch1;
    }
}
