package normalisationautomatique;

import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import weka.classifiers.lazy.IBk;

public class Normalisation implements Serializable {

    public static void loadClassifiers() throws Exception {
        System.out.println("f");
        ModelKnn.cls_f = (IBk) (new ObjectInputStream(new FileInputStream("./models/modelAppF.model"))).readObject();
        System.out.println("k");
        ModelKnn.cls_k = (IBk) (new ObjectInputStream(new FileInputStream("./models/modelAppk.model"))).readObject();
        System.out.println("E");
        ModelKnn.cls_E = (IBk) (new ObjectInputStream(new FileInputStream("./models/modelAppE.model"))).readObject();
        System.out.println("h");
        ModelKnn.cls_h = (IBk) (new ObjectInputStream(new FileInputStream("./models/modelApph.model"))).readObject();
        System.out.println("m");
        ModelKnn.cls_m = (IBk) (new ObjectInputStream(new FileInputStream("./models/modelAppm.model"))).readObject();
    }

    private JFrame frame;
    private String contenu;
    private String contenu_Test;
    private JTextPane textPane_1, textPane;
    private JScrollPane scrollPane, scrollPane_1;
    private JProgressBar progressBar;
    private JLabel labelOpen, labelSave;//, labelnor;
    private JButton normalization_Button;
    private Controle controle;

    /**
     * Create the application.
     */
    public Normalisation() {
        try {
            initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String ajout_alif(String ch, String c) {
        return ch.replaceFirst("l", "Al");
    }

    public static String supp_esp_supp_lam_supp_alif(String ch, String c) {
        return ch.replaceFirst("Al ", "");
    }

    public static String supp_esp_supp_lam(String ch, String c) {
        return ch.replaceFirst("l ", "");
    }

    public static String supp_esp_supp_alif(String ch, String c) {
        return ch.replaceFirst("A ", "");
    }

    public static String supp_esp_ajout_alif(String ch, String c) {
        return ch.replaceFirst("l ", "Al");
    }

    public static String supp_esp(String ch, String c) {
        return ch.replace(" ", "");
    }

    /**
     * Launch the application.
     *
     * @param args
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Normalisation window = new Normalisation();

                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        });
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() throws Exception {
        frame = new JFrame();
        //Image imgIcon = new ImageIcon(this.getClass().getResource("./images/WM4R_icone.jpg")).getImage();
        //frame.setIconImage(imgIcon);
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int x_1 = (int) (e.getComponent().getWidth() / 1.8);
                int w = (int) (e.getComponent().getWidth() / 2.5);
                int h = (int) (e.getComponent().getHeight() / 1.25);
                scrollPane.setBounds(33, 60, w, h - 20);
                scrollPane_1.setBounds(x_1, 60, w, h - 20);
                int x_b = (int) (e.getComponent().getWidth() / 2.3);
                int wB = (int) (e.getComponent().getWidth() / 8.7);
                normalization_Button.setBounds(x_b, 303, wB, 43);
            }
        });
        frame.setBounds(10, 10, 1340, 710);
        frame.setTitle("COTA Orthography (Conventionalized Tunisian Arabic Orthography)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 1114, 30);
        frame.setJMenuBar(menuBar);
        JMenu mnFile = new JMenu("File");
        mnFile.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        menuBar.add(mnFile);
        JMenuItem mntmNew = new JMenuItem("New");
//        mntmNew.setIcon(new ImageIcon(Normalisation.class.getResource("./weka/gui/images/new.gif")));
        mntmNew.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        mnFile.add(mntmNew);
        JMenuItem mntmOpen = new JMenuItem("Open");
        mntmOpen.addActionListener((ActionEvent e) -> {
            TexteNO textNo = new TexteNO();
            String s = textNo.openFile();
            textPane.setText(s);
            /*
             * JFileChooser fc = new JFileChooser();
             * String nomFic = "";
             *
             * FileNameExtensionFilter ff = new
             * FileNameExtensionFilter("Fichiers texte", "txt");
             * fc.setFileFilter(ff);
             *
             * int returnVal = fc.showOpenDialog(getParent());
             *
             * if (returnVal == JFileChooser.APPROVE_OPTION)
             * {
             * nomFic = fc.getSelectedFile().getAbsolutePath();
             *
             *
             * StringBuilder str=new StringBuilder();
             * try
             * { BufferedReader br = new BufferedReader(new
             * InputStreamReader(new
             * java.io.FileInputStream(nomFic),StandardCharsets.UTF_8));
             * String ligne = null;
             *
             * while ((ligne = br.readLine()) != null)
             * {
             * str.append(ligne);
             * str.append( "\n" );
             * }
             *
             * }catch(Exception err)
             * { }
             * textPane.setText(str.toString());}
             */
        });
//        mntmOpen.setIcon(new ImageIcon(Normalisation.class.getResource("./weka/gui/images/open.gif")));
        mntmOpen.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        mnFile.add(mntmOpen);
        JMenuItem mntmSave = new JMenuItem("Save");
        //      mntmSave.setIcon(new ImageIcon(Normalisation.class.getResource("./weka/gui/images/save.gif")));
        mntmSave.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        mnFile.add(mntmSave);

        JMenuItem mntmExit = new JMenuItem("Exit");

        mntmExit.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        mnFile.add(mntmExit);

        JMenu normalize = new JMenu("Normalize");
        normalize.addMouseListener(new MouseAdapter() {

            public void mouseClicked(ActionEvent e) throws Exception {
                normalize(e);
            }
        });
        normalize.addActionListener(this::normalize);

        normalize.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        menuBar.add(normalize);
        frame.getContentPane().setLayout(null);

        textPane = new JTextPane();
        textPane.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textPane.setBounds(326, 63, 550, 600);
        textPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        //frame.getContentPane().add(textPane);

        scrollPane = new JScrollPane();
        //scrollPane.setBounds(33, 60,536, 570);
        scrollPane.setViewportView(textPane);

        frame.getContentPane().add(scrollPane);

        textPane_1 = new JTextPane();
        textPane_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textPane_1.setBounds(1021, 249, 550, 600);
        textPane_1.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        //frame.getContentPane().add(textPane_1);
        scrollPane_1 = new JScrollPane();
        //scrollPane_1.setBounds(757, 60, 536, 570);
        scrollPane_1.setViewportView(textPane_1);
        frame.getContentPane().add(scrollPane_1);
        controle = new Controle();
        normalization_Button = new JButton("Normalize");
        normalization_Button.setForeground(new Color(60, 179, 113));
        normalization_Button.setFont(new Font("Tahoma", Font.PLAIN, 16));
//        btnNewButton.setIcon(new ImageIcon(Normalisation.class.getResource("./weka/gui/beans/icons/cog_go.png")));
        normalization_Button.addActionListener((ActionEvent e) -> {
            controle.actionPerformed(e);
            try {
                normalize(e);
            } catch (Exception ex) {
                Logger.getLogger(Normalisation.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        //btnNewButton.setBounds(580, 303, 154, 43);
        frame.getContentPane().add(normalization_Button);
        progressBar = new JProgressBar();
        progressBar.setBounds(589, 357, 146, 14);
        frame.getContentPane().add(progressBar);
        JLabel newLabel1 = new JLabel("");
        newLabel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // code of open file
            }
        });
//        newLabel1.setIcon(new ImageIcon(Normalisation.class.getResource("./weka/gui/images/new.gif")));
        newLabel1.setBounds(10, 0, 24, 24);
        frame.getContentPane().add(newLabel1);
        labelOpen = new JLabel("");
        //labelOpen.setIcon(new ImageIcon(Normalisation.class.getResource("./weka/gui/images/open.gif")));
        labelOpen.setBounds(36, 0, 24, 24);
        frame.getContentPane().add(labelOpen);
        labelSave = new JLabel("");
        //labelSave.setIcon(new ImageIcon(Normalisation.class.getResource("./weka/gui/images/save.gif")));
        labelSave.setBounds(67, 0, 24, 24);
        frame.getContentPane().add(labelSave);
        MethodeRegles.db_files_loading();
        loadClassifiers();
    }

    public void normalize(ActionEvent e) {
        controle.actionPerformed(e);
        //contenu = Plusieurs_fonctions.arabicToLatin(textPane.getText());
        //jTextPane = new JTextPane();
        //Plusieurs_fonctions.saveText_inFile(Plusieurs_fonctions.removeSpace(contenu), "textTestApp.txt");
        contenu = Plusieurs_fonctions.arabicToLatin(textPane.getText().replace("پ", "ب")).replace("\n", " ").replace("\r", " ");
        contenu = contenu.replace("َ", "").replace("ً", "").replace("ُ", "").replace("ّ", "").replace("ٌ", "").replace("ْ", "").replace("ِ", "").replace("ٍ", "");
        //saveText_inFile(contenu, "textTestApp.txt");
        //String nonNorm = "";
        //String norm = "";
        int i =0;
        try {
            System.out.println("kolD_function");
            contenu = MethodeRegles.KolD_function(contenu);
            do{
                i++;
                System.out.println("le nombre de fois "+i);
            ModelKnn model = new ModelKnn();
            contenu_Test = contenu;
            System.out.println("//////////////////////// Debut H");
            contenu = ModelKnn.classifier_h(contenu, model);
            System.out.println("//////////////////////// Fin H\n//////////////////////// Debut M");
            contenu = ModelKnn.classifier_m(contenu, model);
            System.out.println("//////////////////////// Fin M\n//////////////////////// Debut E");
            contenu = ModelKnn.classifier_e(contenu, model);
            System.out.println("//////////////////////// Fin E\n//////////////////////// Debut F");
            contenu = ModelKnn.classifier_f(contenu, model);
            contenu = MethodeRegles.replaceDoubleSpace(contenu);
            //liste d'exceptions
            //MethodeRegles.db_files_loading();
            
            /*try {
            contenu = MethodeRegles.Code("E:\\Extractor\\Extractor\\code.pl", contenu);
            
            } catch (ClassNotFoundException ex) {
            Logger.getLogger(MethodeRegles.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
            Logger.getLogger(MethodeRegles.class.getName()).log(Level.SEVERE, null, ex);
            }*/
            
            /*System.out.println("Punctuation_D_function");
            contenu = MethodeRegles.punctuation_D(contenu);*/
            System.out.println("Accentuation_function");
            contenu = MethodeRegles.accentuation(contenu);
            /*System.out.println("split_list_function");
            contenu = MethodeRegles.split_list_function(contenu);*/
            System.out.println("First_A_function");
            contenu = MethodeRegles.First_A_function(contenu);
            System.out.println("clitic_l_function");
            contenu = MethodeRegles.clitic_l_function(contenu);
            System.out.println("regle_verb_negation_function");
            contenu = MethodeRegles.regle_verb_negation_function(contenu);
            System.out.println("verbe_negation_function");
            contenu = MethodeRegles.verbe_negation_function(contenu);
            System.out.println("question_function");
            contenu = MethodeRegles.question_function(contenu);
            //question
            //regle mA+verbe
            System.out.println("mA_verb_function");
            contenu = MethodeRegles.mA_verb_function(contenu);
            //regle verbe +pronom
            System.out.println("verb_pronoun_function");
            contenu = MethodeRegles.verb_pronoun_function(contenu);
            System.out.println("trois_pers_sing_function");
            contenu = MethodeRegles.trois_pers_sing_function(contenu); //3éme personne de singulier(liste des noms et seulement lw)
            System.out.println("trois_pers_sing_plur_function");
            contenu = MethodeRegles.trois_pers_sing_plur_function(contenu);        //3éme personne de singulier et pluriel
            System.out.println("yA_nouns_function");
            contenu = MethodeRegles.yA_nouns_function(contenu);//regle ya+noms
            System.out.println("ElY_function");
            contenu = MethodeRegles.ElY_function(contenu);
            System.out.println("nombre_function");
            contenu = MethodeRegles.nombre_function(contenu);
            //nombre
            //regle pour h en p
            System.out.println("h_en_p_function");
            contenu = MethodeRegles.h_en_p_function(contenu);
            System.out.println("A_en_p_function");
            contenu = MethodeRegles.A_en_p_function(contenu);       //regle pour A en p
            //regle pour Aouy en Y
            System.out.println("Aouy_en_Y_function");
            contenu = MethodeRegles.Aouy_en_Y_function(contenu);
            //regle pour Yen y
            System.out.println("Y_en_y_function");
            contenu = MethodeRegles.Y_en_y_function(contenu);
            //regle pour Sen s
            System.out.println("S_en_s_function");
            contenu = MethodeRegles.S_en_s_function(contenu);
            System.out.println("s_en_S_function");
            contenu = MethodeRegles.s_en_S_function(contenu);        //regle pour sen S
            System.out.println("Y_en_A_function");
            contenu = MethodeRegles.Y_en_A_function(contenu);
            //regle pour Yen A
            //exeption
            System.out.println("w_Hamza_function");
            contenu = MethodeRegles.w_Hamza_function(contenu);
            System.out.println("Allah_function");
            contenu = MethodeRegles.NchAllah_function(contenu);
            contenu = MethodeRegles.MachAllah_function(contenu);
            contenu = MethodeRegles.AsmAllh_function(contenu);
            contenu = MethodeRegles.Hmd_function(contenu);
            //regle w et les hamza
            System.out.println("exception_list_function");
            contenu = MethodeRegles.exception_list_function(contenu);
            System.out.println("KolF_function");
            contenu = MethodeRegles.KolF_function(contenu);
            }while(!contenu.equals(contenu_Test));
            /*System.out.println("Punctuation_F_function");
            contenu = MethodeRegles.punctuation_F(contenu);*/
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        /*
         * String nonNorm = null;
         * String norm = null;
         * ModelKnn mm = new ModelKnn();
         * System.out.println("//////////////////////// Debut H");
         * try {
         * mm.listMots.clear();
         * if (mm.afficherh(contenu, "output_h.arff")) {
         * Instances inta = mm.modelKnn("output_h.arff",
         * "./models/modelApph.model", ModelKnn.cls_h);
         * for (int index = 0; index < inta.numInstances(); index++) {
         * Instance s = inta.get(index);
         * String classe = ModelKnn.classes_h[(int) s.classValue()];
         * String nonNormalise = mm.listMots.get(index - 16).contenu;
         * System.out.println(nonNormalise + " : " + classe);
         * String normalise = null;
         * switch (classe) {
         * case "supp_esp_supp_alif":
         * normalise = supp_esp_supp_alif((nonNormalise), "h");
         * break;
         * case "supp_esp":
         * normalise = supp_esp((nonNormalise), "h");
         * break;
         * case "supp_esp_supp_lam":
         * normalise = supp_esp_supp_lam(nonNormalise, "h");
         * break;
         * case "supp_esp_supp_lam_supp_alif":
         * normalise = supp_esp_supp_lam_supp_alif(nonNormalise, "h");
         * break;
         * case "supp_esp_ajout_alif":
         * normalise = supp_esp_ajout_alif(nonNormalise, "h");
         * break;
         * case "ajout_alif":
         * normalise = ajout_alif(nonNormalise, "h");
         * break;
         * default:
         * break;
         * }
         * if (normalise != null) {
         * contenu = contenu.All(nonNormalise, normalise);
         * }
         * }
         *
         * }
         * } catch (Exception e1) {
         * e1.printStackTrace();
         * }
         * System.out.println("//////////////////////// Fin H");
         * System.out.println("//////////////////////// Debut M");
         * try {
         * mm.listMots.clear();
         * if (mm.afficherM(contenu, "output_m.arff")) {
         * Instances inta = mm.modelKnn("output_m.arff",
         * "./models/modelAppm.model", ModelKnn.cls_m);
         * for (int index = 0; index < inta.numInstances(); index++) {
         * Instance s = inta.get(index);
         * String classe = ModelKnn.classes_m[(int) s.classValue()];
         * String nonNormalise = mm.listMots.get(index - 16).contenu;
         * System.out.println(nonNormalise + " : " + classe);
         * String normalise = null;
         * switch (classe) {
         * case "supp_esp_supp_alif":
         * normalise = supp_esp_supp_alif(nonNormalise, "m");
         * break;
         * case "supp_esp":
         * normalise = supp_esp(nonNormalise, "m");
         * break;
         * case "supp_esp_supp_lam":
         * normalise = supp_esp_supp_lam(nonNormalise, "m");
         * break;
         * case "supp_esp_supp_lam_supp_alif":
         * normalise = supp_esp_supp_lam_supp_alif(nonNormalise, "m");
         * break;
         * case "supp_esp_ajout_alif":
         * normalise = supp_esp_ajout_alif(nonNormalise, "m");
         * break;
         * case "ajout_alif":
         * normalise = ajout_alif(nonNormalise, "m");
         * break;
         * default:
         * break;
         * }
         * if (normalise != null) {
         * contenu = contenu.replaceAll(nonNormalise, normalise);
         * }
         * }
         * }
         * } catch (Exception e1) {
         * e1.printStackTrace();
         * }
         * System.out.println("//////////////////////// Fin M");
         * System.out.println("//////////////////////// Debut E");
         * try {
         * mm.listMots.clear();
         * if (mm.afficherE(contenu, "output_E.arff")) {
         * Instances inta = mm.modelKnn("output_E.arff",
         * "./models/modelAppE.model", ModelKnn.cls_E);
         * for (int index = 0; index < inta.numInstances(); index++) {
         * Instance s = inta.get(index);
         * String classe = ModelKnn.classes_E[(int) s.classValue()];
         * String nonNormalise = mm.listMots.get(index - 16).contenu;
         * System.out.println(nonNormalise + " : " + classe);
         * String normalise = null;
         * switch (classe) {
         * case "supp_esp_supp_alif":
         * normalise = supp_esp_supp_alif(nonNormalise, "E");
         * break;
         * case "supp_esp":
         * normalise = supp_esp(nonNormalise, "E");
         * break;
         * case "supp_esp_supp_lam":
         * normalise = supp_esp_supp_lam(nonNormalise, "E");
         * break;
         * case "supp_esp_supp_lam_supp_alif":
         * normalise = supp_esp_supp_lam_supp_alif(nonNormalise, "E");
         * break;
         * case "supp_esp_ajout_alif":
         * normalise = supp_esp_ajout_alif(nonNormalise, "E");
         * break;
         * case "ajout_alif":
         * normalise = ajout_alif(nonNormalise, "E");
         * break;
         * default:
         * break;
         * }
         * if (normalise != null) {
         * contenu = contenu.replaceAll(nonNormalise, normalise);
         * }
         * }
         * }
         * } catch (Exception e1) {
         * e1.printStackTrace();
         * }
         * System.out.println("//////////////////////// Fin E");
         * System.out.println("//////////////////////// Debut F");
         * try {
         * mm.listMots.clear();
         * if (mm.afficherF(contenu, "output_f.arff")) {
         * Instances inta = mm.modelKnn("output_f.arff",
         * "./models/modelAppF.model", ModelKnn.cls_f);
         * for (int index = 0; index < inta.numInstances(); index++) {
         * Instance s = inta.get(index);
         * String classe = ModelKnn.classes_f[(int) s.classValue()];
         * String nonNormalise = mm.listMots.get(index - 16).contenu;
         * System.out.println(nonNormalise + " : " + classe);
         * String normalise = null;
         * switch (classe) {
         * case "supp_esp_supp_alif":
         * normalise = supp_esp_supp_alif(nonNormalise, "f");
         * break;
         * case "supp_esp":
         * normalise = supp_esp(nonNormalise, "f");
         * break;
         * case "supp_esp_supp_lam":
         * normalise = supp_esp_supp_lam(nonNormalise, "f");
         * break;
         * case "supp_esp_supp_lam_supp_alif":
         * normalise = supp_esp_supp_lam_supp_alif(nonNormalise, "f");
         * break;
         * case "supp_esp_ajout_alif":
         * normalise = supp_esp_ajout_alif(nonNormalise, "f");
         * break;
         * case "ajout_alif":
         * normalise = ajout_alif(nonNormalise, "f");
         * break;
         * default:
         * break;
         * }
         * if (normalise != null) {
         * contenu = contenu.replaceAll(nonNormalise, normalise);
         * }
         * }
         *
         * }
         * } catch (Exception e1) {
         * e1.printStackTrace();
         * }
         * System.out.println("//////////////////////// Fin F");
         * System.out.println("//////////////////////// Debut K");
         * try {
         * mm.listMots.clear();
         * if (mm.afficherK(contenu, "output_k.arff")) {
         * Instances inta = mm.modelKnn("output_k.arff",
         * "./models/modelAppk.model", ModelKnn.cls_k);
         * for (int index = 0; index < inta.numInstances(); index++) {
         * Instance s = inta.get(index);
         * String classe = ModelKnn.classes_k[(int) s.classValue()];
         * String nonNormalise = mm.listMots.get(index - 16).contenu;
         * System.out.println(nonNormalise + " : " + classe);
         * String normalise = null;
         * switch (classe) {
         * case "supp_esp_supp_alif":
         * normalise = supp_esp_supp_alif(nonNormalise, "k");
         * break;
         * case "supp_esp":
         * normalise = supp_esp(nonNormalise, "k");
         * break;
         * case "supp_esp_supp_lam":
         * normalise = supp_esp_supp_lam(nonNormalise, "k");
         * break;
         * case "supp_esp_supp_lam_supp_alif":
         * normalise = supp_esp_supp_lam_supp_alif(nonNormalise, "k");
         * break;
         * case "supp_esp_ajout_alif":
         * normalise = supp_esp_ajout_alif(nonNormalise, "k");
         * break;
         * case "ajout_alif":
         * normalise = ajout_alif(nonNormalise, "k");
         * break;
         * default:
         * break;
         * }
         * if (normalise != null) {
         * contenu = contenu.replaceAll(nonNormalise, normalise);
         * }
         * }
         *
         * }
         * } catch (Exception e1) {
         * e1.printStackTrace();
         * }
         * //liste d'exeption
         * try {
         * String[] parts = contenu.split(" ");
         * for (int p = 0; p < parts.length; p++) {
         * try (BufferedReader br1 = new BufferedReader(new
         * InputStreamReader(new FileInputStream("./data/latainexeption.txt"),
         * "UTF8"))) {
         * for (String line; (line = br1.readLine()) != null;) {
         * if (parts[p].equals(line.substring(0, line.indexOf(" ")))) {
         * parts[p] = line.substring(line.indexOf(" ") + 1);
         * } else if ((parts[p].indexOf("w") == 0 || parts[p].indexOf("b") == 0
         * || parts[p].indexOf("l") == 0 || parts[p].indexOf("f") == 0 ||
         * parts[p].indexOf("k") == 0)
         * && parts[p].substring(1).equals(line.substring(0, line.indexOf("
         * ")))) {
         * String ch = parts[p].substring(0, 1);
         * parts[p] = ch + line.substring(line.indexOf(" ") + 1);
         * }
         * }
         * } catch (Exception e1) {
         * e1.printStackTrace();
         * }
         * }
         * StringBuilder sb = new StringBuilder();
         * for (String str : parts) {
         * sb.append(str.replaceAll("(?m)(^ *| +(?= |$))",
         * "").replaceAll("(?m)^$([\r\n]+?)(^$[\r\n]+?^)+", "$1"));
         * sb.append(" ");
         * }
         * contenu = sb.toString();
         * } catch (Exception e1) {
         * e1.printStackTrace();
         * }
         *
         * //regle de futur
         * try {
         * String[] parts = contenu.split(" ");
         * int i = 0;
         * for (int p = 0; p < parts.length; p++) {
         * nonNorm = parts[p];
         * try (BufferedReader br1 = new BufferedReader(new
         * InputStreamReader(new FileInputStream("./data/lataiListeVerbes.txt"),
         * "UTF8"))) {
         * for (String line; (line = br1.readLine()) != null;) {
         * int L = line.length();
         * // bA$ykH
         * if (parts[p].indexOf("bA$") == 0 &&
         * parts[p].substring(3).contains(line)) {
         * parts[p] = "bA$" + " " + nonNorm.substring(3);
         * } else if (parts[p].indexOf("wbA$") == 0 &&
         * parts[p].substring(4).contains(line)) {
         * parts[p] = "wbA$" + " " + nonNorm.substring(4);
         * } // b$ykH
         * if (parts[p].indexOf("b$") == 0 &&
         * parts[p].substring(2).contains(line)) {
         * parts[p] = "bA$" + " " + nonNorm.substring(2);
         * } else if (parts[p].indexOf("wb$") == 0 &&
         * parts[p].substring(3).contains(line)) {
         * parts[p] = "wbA$" + " " + nonNorm.substring(3);
         * }
         * }
         * } catch (Exception e1) {
         * e1.printStackTrace();
         * }
         * }
         * StringBuilder sb = new StringBuilder();
         * for (String str : parts) {
         * sb.append(str.replaceAll("(?m)(^ *| +(?= |$))",
         * "").replaceAll("(?m)^$([\r\n]+?)(^$[\r\n]+?^)+", "$1"));
         * sb.append(" ");
         * }
         * contenu = sb.toString();
         * } catch (Exception e1) {
         * e1.printStackTrace();
         * }
         * // regle verbe negation
         * try {
         * String[] parts = contenu.split(" ");
         * for (int p = 0; p < parts.length; p++) {
         * nonNorm = parts[p];
         * try (BufferedReader br1 = new BufferedReader(new
         * InputStreamReader(new FileInputStream("./data/lataiListeVerbes.txt"),
         * "UTF8"))) {
         * for (String line; (line = br1.readLine()) != null;) {
         * int L = line.length();
         * //mAykH$ //مانخالفش
         * if (parts[p].indexOf("mA") == 0 && parts[p].contains(line)
         * && parts[p].indexOf("$") == L + 2
         * && parts[p].lastIndexOf("$") == parts[p].length() - 1) {
         * parts[p] = MethodeRegles.negation_mA_Verb1(nonNorm);
         * } //mAykHhA$
         * else if (parts[p].indexOf("mA") == 0 && parts[p].contains(line)
         * && (parts[p].indexOf("hA") == L + 2 || parts[p].indexOf("hw") == L +
         * 2)
         * && parts[p].indexOf("$") == L + 4
         * && parts[p].lastIndexOf("$") == parts[p].length() - 1) {
         * parts[p] = "mA " + nonNorm.substring(2);
         * } //mAykHlw$ //mAqAllw$
         * else if (parts[p].indexOf("mA") == 0 && parts[p].contains(line)
         * && ((parts[p].indexOf("l") == L + 2 && line.endsWith("l") == false)
         * || (parts[p].lastIndexOf("l") == L + 2 && line.endsWith("l") ==
         * true))
         * && parts[p].lastIndexOf("$") == parts[p].length() - 1) {
         * parts[p] = "mA" + " " + nonNorm.substring(2, L + 2) + " " +
         * nonNorm.substring(L + 2);
         * } //mAykHhAlw$
         * else if (parts[p].indexOf("mA") == 0 && parts[p].contains(line)
         * && (parts[p].indexOf("hA") == L + 2 || parts[p].indexOf("hw") == L +
         * 2)
         * && line.endsWith("l") == false
         * && parts[p].lastIndexOf("$") == parts[p].length() - 1) {
         * parts[p] = "mA" + " " + nonNorm.substring(2, L + 4) + " " +
         * nonNorm.substring(L + 4);
         * }//mAqalw$
         * else if (parts[p].indexOf("mA") == 0 && parts[p].contains(line)
         * && line.endsWith("l") == true
         * && parts[p].lastIndexOf("$") == parts[p].length() - 1) {
         * parts[p] = MethodeRegles.negation_mA_Verb1(nonNorm);
         * } //wmAykH$
         * else if (parts[p].indexOf("wmA") == 0 && parts[p].contains(line)
         * && parts[p].indexOf("$") == L + 3
         * && parts[p].lastIndexOf("$") == parts[p].length() - 1) {
         * parts[p] = "wmA " + nonNorm.substring(3);
         * } //wmAykHhA$
         * else if (parts[p].indexOf("wmA") == 0 && parts[p].contains(line)
         * && (parts[p].indexOf("hA") == L + 3 || parts[p].indexOf("hw") == L +
         * 3)
         * && parts[p].indexOf("$") == L + 5
         * && parts[p].lastIndexOf("$") == parts[p].length() - 1) {
         * parts[p] = "mA " + nonNorm.substring(3);
         * } //wmAykHhAlw$
         * else if (parts[p].indexOf("wmA") == 0 && parts[p].contains(line)
         * && (parts[p].indexOf("hA") == L + 3 || parts[p].indexOf("hw") == L +
         * 3) && line.endsWith("l") == false && parts[p].lastIndexOf("$") ==
         * parts[p].length() - 1) {
         * parts[p] = "wmA" + " " + nonNorm.substring(3, L + 5) + " " +
         * nonNorm.substring(L + 5);
         * } //wmAykHlw$
         * else if (parts[p].indexOf("wmA") == 0 && parts[p].contains(line)
         * && parts[p].indexOf("l") == L + 3 && line.endsWith("l") == false &&
         * parts[p].lastIndexOf("$") == parts[p].length() - 1) {
         * parts[p] = "wmA" + " " + nonNorm.substring(3, L + 3) + " " +
         * nonNorm.substring(L + 3);
         * } //wmAqAllw$
         * else if (parts[p].indexOf("wmA") == 0 && parts[p].contains(line)
         * && parts[p].lastIndexOf("l") == L + 3
         * && line.endsWith("l") == true
         * && parts[p].lastIndexOf("$") == parts[p].length() - 1) {
         * parts[p] = "mA" + " " + nonNorm.substring(3, L + 3) + " " +
         * nonNorm.substring(L + 3);
         * } //wmAqAlw$
         * else if (parts[p].indexOf("wmA") == 0 && parts[p].contains(line) &&
         * line.endsWith("l") == true && parts[p].lastIndexOf("$") ==
         * parts[p].length() - 1) {
         * parts[p] = "wmA " + nonNorm.substring(3);
         * } else if (parts[p].indexOf("m") == 0 && parts[p].contains(line) &&
         * parts[p].lastIndexOf("$") == L + 1 && parts[p].lastIndexOf("$") ==
         * parts[p].length() - 1) {
         * parts[p] = MethodeRegles.negation_mA_Verb1(nonNorm);
         * } //wmtkAfyny$
         * else if (parts[p].indexOf("wm") == 0 && parts[p].contains(line) &&
         * parts[p].lastIndexOf("$") == L + 2 && parts[p].lastIndexOf("$") ==
         * parts[p].length() - 1) {
         * parts[p] = "wmA " + nonNorm.substring(2);
         * } //mykHlw$ //ما نخ ليوش
         * else if (parts[p].indexOf("m") == 0 && parts[p].contains(line) &&
         * parts[p].indexOf("l") == L + 1 && line.endsWith("l") == false &&
         * parts[p].lastIndexOf("$") == parts[p].length() - 1) {
         * parts[p] = "mA" + " " + nonNorm.substring(1, L + 1) + " " +
         * nonNorm.substring(L + 1);
         * } //wmykHlw$
         * else if (parts[p].indexOf("wm") == 0 && parts[p].contains(line) &&
         * parts[p].indexOf("l") == L + 2 && line.endsWith("l") == false &&
         * parts[p].lastIndexOf("$") == parts[p].length() - 1) {
         * parts[p] = "wmA" + " " + nonNorm.substring(2, L + 2) + " " +
         * nonNorm.substring(L + 2);
         * } //mqAllw$
         * else if (parts[p].indexOf("m") == 0 && parts[p].contains(line) &&
         * parts[p].lastIndexOf("l") == L + 1 && line.endsWith("l") == true &&
         * parts[p].lastIndexOf("$") == parts[p].length() - 1) {
         * parts[p] = "mA" + " " + nonNorm.substring(1, L + 1) + " " +
         * nonNorm.substring(L + 1);
         * } //wmqAllw$
         * else if (parts[p].indexOf("wm") == 0 && parts[p].contains(line) &&
         * parts[p].lastIndexOf("l") == L + 2 && line.endsWith("l") == true &&
         * parts[p].lastIndexOf("$") == parts[p].length() - 1) {
         * parts[p] = "wmA" + " " + nonNorm.substring(2, L + 2) + " " +
         * nonNorm.substring(L + 2);
         * } //mqAlw$
         * else if (parts[p].indexOf("m") == 0 && parts[p].contains(line) &&
         * line.endsWith("l") == true && parts[p].lastIndexOf("$") ==
         * parts[p].length() - 1) {
         * parts[p] = "mA " + nonNorm.substring(1);
         * } //wmqAlw$
         * else if (parts[p].indexOf("wm") == 0 && parts[p].contains(line) &&
         * line.endsWith("l") == true && parts[p].lastIndexOf("$") ==
         * parts[p].length() - 1) {
         * parts[p] = "wmA " + nonNorm.substring(2);
         * } //mA ykHlw$ /kHl$
         * else if (p != 0 && parts[p].indexOf(line) == 0 &&
         * parts[p].lastIndexOf("$") == parts[p].length() - 1
         * && ((parts[p - 1].equals("mA") || parts[p - 1].equals("wmA")) &&
         * parts[p].indexOf("l") == L && line.endsWith("l") == false)
         * || ((parts[p - 1].equals("mA") || parts[p - 1].equals("wmA")) &&
         * parts[p].lastIndexOf("l") == L && line.endsWith("l") == true)) {
         * parts[p] = nonNorm.substring(0, L) + " " + nonNorm.substring(L);
         * //wmA ykHlw$ //mA qAllw$ } //wmA qAllw$
         * //mtkAfyny$/kHl/mykH$/مغشش
         * }
         * }
         * } catch (IOException e2) {
         * e2.printStackTrace();
         * }
         * }
         * StringBuilder sb = new StringBuilder();
         * for (String str : parts) {
         * sb.append(str.replaceAll("(?m)(^ *| +(?= |$))",
         * "").replaceAll("(?m)^$([\r\n]+?)(^$[\r\n]+?^)+", "$1"));
         * sb.append(" ");
         * }
         * contenu = sb.toString();
         * } catch (Exception e1) {
         * e1.printStackTrace();
         * }
         * //question
         *
         * try {
         * String[] parts = contenu.split(" ");
         * for (int p = 0; p < parts.length; p++) {
         * nonNorm = parts[p];
         * try (BufferedReader br1 = new BufferedReader(new
         * InputStreamReader(new FileInputStream("./data/lataiListeVerbes.txt"),
         * "UTF8"))) {
         * for (String line; (line = br1.readLine()) != null;) {
         * //A$gSyt/
         * if ((parts[p].indexOf("A$") == 0 || parts[p].indexOf(">$") == 0) &&
         * parts[p].substring(2).contains(line) && parts[p].endsWith("p") ==
         * false) {
         * parts[p] = "A$ " + nonNorm.substring(2);
         * }
         * //wA$gSyt
         * if ((parts[p].indexOf("wA$") == 0 || parts[p].indexOf("w>$") == 0) &&
         * parts[p].substring(3).contains(line) && parts[p].endsWith("p") ==
         * false) {
         * parts[p] = "wA$ " + nonNorm.substring(3);
         * } //$gSyt/$hwr
         * else if (parts[p].indexOf("$") == 0 &&
         * parts[p].substring(1).contains(line) && parts[p].endsWith("p") ==
         * false) {
         * parts[p] = "A$ " + nonNorm.substring(1);
         * } //w$gSyt
         * else if (parts[p].indexOf("w$") == 0 &&
         * parts[p].substring(2).contains(line) && parts[p].endsWith("p") ==
         * false) {
         * parts[p] = "wA$ " + nonNorm.substring(2);
         * }
         * }
         * } catch (Exception e1) {
         * e1.printStackTrace();
         * }
         * }
         * StringBuilder sb = new StringBuilder();
         * for (String str : parts) {
         * sb.append(str.replaceAll("(?m)(^ *| +(?= |$))",
         * "").replaceAll("(?m)^$([\r\n]+?)(^$[\r\n]+?^)+", "$1"));
         * sb.append(" ");
         * }
         * contenu = sb.toString();
         * } catch (Exception e1) {
         * e1.printStackTrace();
         * }
         * //regle mA+verbe
         * try {
         * String[] parts = contenu.split(" ");
         * for (int p = 0; p < parts.length; p++) {
         * nonNorm = parts[p];
         * try (BufferedReader br1 = new BufferedReader(new
         * InputStreamReader(new FileInputStream("./data/Latain liste
         * mA+verbe.txt"), "UTF8"))) {
         * for (String line; (line = br1.readLine()) != null;) { // mAkml
         * if (parts[p].indexOf("mA") == 0 &&
         * parts[p].substring(2).equals(line)) {
         * parts[p] = "mA" + " " + nonNorm.substring(2);
         * } else if (parts[p].indexOf("wmA") == 0 &&
         * parts[p].substring(3).equals(line)) {
         * parts[p] = "mA" + " " + nonNorm.substring(3);
         * }
         * }
         * } catch (Exception e1) {
         * e1.printStackTrace();
         * }
         * }
         * StringBuilder sb = new StringBuilder();
         * for (String str : parts) {
         * sb.append(str.replaceAll("(?m)(^ *| +(?= |$))",
         * "").replaceAll("(?m)^$([\r\n]+?)(^$[\r\n]+?^)+", "$1"));
         * sb.append(" ");
         * }
         * contenu = sb.toString();
         * } catch (Exception e1) {
         * e1.printStackTrace();
         * }
         *
         * //regle verbe +pronom
         * try {
         * String[] parts = contenu.split(" ");
         * for (int p = 0; p < parts.length; p++) {
         * nonNorm = parts[p];
         * try (BufferedReader br1 = new BufferedReader(new
         * InputStreamReader(new FileInputStream("./data/lataiListeVerbes.txt"),
         * "UTF8"))) {
         * for (String line; (line = br1.readLine()) != null;) { // cas /xzrlhA
         * /المليح/ عامل
         * int L = line.length();
         * if (parts[p].indexOf(line) == 0 && parts[p].indexOf("l") == L &&
         * line.endsWith("l") == false && (parts[p].substring(L).equals("ly") ||
         * parts[p].substring(L).equals("lnA")
         * || parts[p].substring(L).equals("lk") ||
         * parts[p].substring(L).equals("lkm") ||
         * parts[p].substring(L).equals("lhA")
         * || parts[p].substring(L).equals("lhm") ||
         * parts[p].substring(L).equals("lw")) && parts[p].endsWith("l") ==
         * false) {
         * parts[p] = nonNorm.substring(0, L) + " " + nonNorm.substring(L);
         * } // yEmlhAlhm/
         * else if (parts[p].indexOf(line) == 0 && (parts[p].indexOf("hA") == L
         * || parts[p].indexOf("hw") == L) && parts[p].lastIndexOf("l") == L +
         * 2) {
         * parts[p] = nonNorm.substring(0, L + 2) + " " + nonNorm.substring(L +
         * 2);
         * } //qAllhm
         * else if (parts[p].indexOf(line) == 0 &&
         * (parts[p].substring(L).equals("ly") ||
         * parts[p].substring(L).equals("lnA")
         * || parts[p].substring(L).equals("lk") ||
         * parts[p].substring(L).equals("lkm") ||
         * parts[p].substring(L).equals("lhA")
         * || parts[p].substring(L).equals("lhm") ||
         * parts[p].substring(L).equals("lw")) && line.lastIndexOf("l") != -1 &&
         * parts[p].lastIndexOf("l") == L) {
         * parts[p] = nonNorm.substring(0, L) + " " + nonNorm.substring(L);
         * } //wkHlw
         * else if (parts[p].indexOf("w") == 0 && parts[p].indexOf(line) == 1 &&
         * parts[p].indexOf("l") == L + 1 && line.endsWith("l") == false &&
         * (parts[p].substring(L + 1).equals("ly") || parts[p].substring(L +
         * 1).equals("lnA")
         * || parts[p].substring(L + 1).equals("lk") || parts[p].substring(L +
         * 1).equals("lkm") || parts[p].substring(L + 1).equals("lhA")
         * || parts[p].substring(L + 1).equals("lhm") || parts[p].substring(L +
         * 1).equals("lw")) && parts[p].substring(L + 1).length() > 1) {
         * parts[p] = nonNorm.substring(0, L + 1) + " " + nonNorm.substring(L +
         * 1);
         * } // wyEmlhAlhm
         * else if (parts[p].indexOf("w") == 0 && parts[p].indexOf(line) == 1 &&
         * (parts[p].indexOf("hA") == L + 1 || parts[p].indexOf("hw") == L + 1)
         * && parts[p].lastIndexOf("l") == L + 3) {
         * parts[p] = nonNorm.substring(0, L + 3) + " " + nonNorm.substring(L +
         * 3);
         * } //wqAllhm
         * else if (parts[p].indexOf("w") == 0 && parts[p].indexOf(line) == 1 &&
         * line.endsWith("l") == true && parts[p].lastIndexOf("l") == L + 1 &&
         * (parts[p].substring(L + 1).equals("ly") || parts[p].substring(L +
         * 1).equals("lnA")
         * || parts[p].substring(L + 1).equals("lk") || parts[p].substring(L +
         * 1).equals("lkm") || parts[p].substring(L + 1).equals("lhA")
         * || parts[p].substring(L + 1).equals("lhm") || parts[p].substring(L +
         * 1).equals("lw"))) {
         * parts[p] = nonNorm.substring(0, L + 1) + " " + nonNorm.substring(L +
         * 1);
         * }
         * }
         * } catch (Exception e1) {
         * e1.printStackTrace();
         * }
         * }
         *
         * StringBuilder sb = new StringBuilder();
         * for (String str : parts) {
         * sb.append(str.replaceAll("(?m)(^ *| +(?= |$))",
         * "").replaceAll("(?m)^$([\r\n]+?)(^$[\r\n]+?^)+", "$1"));
         * sb.append(" ");
         * }
         * contenu = sb.toString();
         * } catch (Exception e1) {
         * e1.printStackTrace();
         * }
         *
         * //3éme personne de singulier(liste des noms et seulement lw)
         * try {
         * String[] parts = contenu.split(" ");
         * for (int p = 0; p < parts.length; p++) {
         * nonNorm = parts[p];
         * try (BufferedReader br1 = new BufferedReader(new
         * InputStreamReader(new
         * FileInputStream("./data/latainlisteVerbe3Personne.txt"), "UTF8"))) {
         * for (String line; (line = br1.readLine()) != null;) {
         * int L = line.length();
         * int pa = parts[p].length();
         * // noms: وحطو/AmtyAzwA /يد
         *
         * if (parts[p].length() > L) {
         * if ((parts[p].lastIndexOf("w") == pa - 1 && parts[p].substring(1, pa
         * - 1).equals(line) && (parts[p].indexOf("w") == 0 ||
         * parts[p].indexOf("l") == 0 || parts[p].indexOf("k") == 0 ||
         * parts[p].indexOf("b") == 0))
         * || (parts[p].endsWith("wA") == true && parts[p].substring(1, pa -
         * 2).equals(line) && (parts[p].indexOf("w") == 0 ||
         * parts[p].indexOf("l") == 0 || parts[p].indexOf("k") == 0 ||
         * parts[p].indexOf("b") == 0))) {
         * parts[p] = nonNorm.substring(0, L + 1) + "h";
         * } else if (((parts[p].lastIndexOf("w") == pa - 1 &&
         * parts[p].substring(0, pa - 1).equals(line))
         * || (parts[p].endsWith("wA") == true && parts[p].substring(0, pa -
         * 2).equals(line)))) {
         * parts[p] = nonNorm.substring(0, L) + "h";
         * }
         * }
         * }
         * } catch (Exception e1) {
         * e1.printStackTrace();
         * }
         * }
         * StringBuilder sb = new StringBuilder();
         * for (String str : parts) {
         * sb.append(str.replaceAll("(?m)(^ *| +(?= |$))",
         * "").replaceAll("(?m)^$([\r\n]+?)(^$[\r\n]+?^)+", "$1"));
         * sb.append(" ");
         * }
         *
         * contenu = sb.toString();
         *
         * } catch (Exception e1) {
         * e1.printStackTrace();
         * }
         *
         * //3éme personne de singulier et pluriel
         * try {
         * String[] parts = contenu.split(" ");
         * for (int p = 0; p < parts.length; p++) {
         * try (BufferedReader br1 = new BufferedReader(new
         * InputStreamReader(new FileInputStream("./data/lataiListeVerbes.txt"),
         * "UTF8"))) {
         * for (String line; (line = br1.readLine()) != null;) {
         * int L = line.length();
         * int pa = parts[p].length();
         * // noms: وحطو
         * if (parts[p].length() == L && parts[p].endsWith("Aw") == true &&
         * parts[p].equals(line)) {
         * parts[p] = parts[p] + "A";
         * } else if (parts[p].endsWith("yw") == true && parts[p].equals(line)
         * && parts[p].length() == L) {
         * parts[p] = parts[p] + "A";
         * } else if (parts[p].length() >= L && (parts[p].endsWith("Aw") == true
         * || parts[p].endsWith("yw") == true) && parts[p].substring(1,
         * pa).equals(line)) {
         * parts[p] = parts[p] + "A";
         * } else if (parts[p].length() > L && parts[p].lastIndexOf("w") == pa -
         * 1 && parts[p].substring(1, pa - 1).equals(line)) {
         * nonNorm = parts[p];
         * parts[p] = nonNorm.substring(0, L + 1) + "h" + "/" + parts[p] + "A";
         * } else if (parts[p].length() > L && parts[p].lastIndexOf("w") == pa -
         * 1 && parts[p].substring(0, pa - 1).equals(line)) {
         * nonNorm = parts[p];
         * parts[p] = nonNorm.substring(0, L) + "h" + "/" + parts[p] + "A";
         * } else if (parts[p].equals("lw")) {
         * parts[p] = "lh";
         * }
         * }
         * } catch (Exception e1) {
         * e1.printStackTrace();
         * }
         * }
         *
         * StringBuilder sb = new StringBuilder();
         * for (String str : parts) {
         * sb.append(str.replaceAll("(?m)(^ *| +(?= |$))",
         * "").replaceAll("(?m)^$([\r\n]+?)(^$[\r\n]+?^)+", "$1"));
         * sb.append(" ");
         * }
         * contenu = sb.toString();
         * } catch (Exception e1) {
         * e1.printStackTrace();
         * }
         *
         * //regle ya+noms
         * try {
         *
         * String[] parts = contenu.split(" ");
         * for (int p = 0; p < parts.length; p++) {
         * nonNorm = parts[p];
         * try (BufferedReader br1 = new BufferedReader(new
         * InputStreamReader(new FileInputStream("./data/lataintNomPropre.txt"),
         * "UTF8"))) {
         * for (String line; (line = br1.readLine()) != null;) {
         * if (parts[p].indexOf("yA") == 0 &&
         * parts[p].substring(2).equals(line)) {
         * parts[p] = "yA " + nonNorm.substring(2);
         * }
         * if (parts[p].indexOf("wyA") == 0 &&
         * parts[p].substring(3).equals(line)) {
         * parts[p] = "wyA " + nonNorm.substring(3);
         * }
         * }
         * } catch (Exception e1) {
         * e1.printStackTrace();
         * }
         * }
         *
         * StringBuilder sb = new StringBuilder();
         * for (String str : parts) {
         * sb.append(str.replaceAll("(?m)(^ *| +(?= |$))",
         * "").replaceAll("(?m)^$([\r\n]+?)(^$[\r\n]+?^)+", "$1"));
         * sb.append(" ");
         * }
         *
         * contenu = sb.toString();
         *
         * } catch (Exception e1) {
         * e1.printStackTrace();
         * }
         *
         * //regle ElY
         * try {
         *
         * String[] parts = contenu.split(" ");
         * for (int p = 0; p < parts.length; p++) {
         * try (BufferedReader br1 = new BufferedReader(new
         * InputStreamReader(new
         * FileInputStream("./data/LatainListNomArabe.txt"), "UTF8"))) {
         * for (String line; (line = br1.readLine()) != null;) {
         * if (parts[p].equals("Ely") && parts[p + 1].contains(line)) {
         * parts[p] = "ElY";
         * } else if (parts[p].equals("wEly") && parts[p + 1].contains(line)) {
         * parts[p] = "wElY";
         * }
         * }
         * } catch (Exception e1) {
         * e1.printStackTrace();
         * }
         * }
         *
         * StringBuilder sb = new StringBuilder();
         * for (String str : parts) {
         * sb.append(str.replaceAll("(?m)(^ *| +(?= |$))",
         * "").replaceAll("(?m)^$([\r\n]+?)(^$[\r\n]+?^)+", "$1"));
         * sb.append(" ");
         * }
         *
         * contenu = sb.toString();
         *
         * } catch (Exception e1) {
         * e1.printStackTrace();
         * }
         *
         * //regle pour b + " "
         * try {
         * String[] parts = contenu.split(" ");
         *
         * for (int p = 0; p < parts.length; p++) {
         * if (parts[p].equals("b") && " ".equals(parts[p + 1])) {
         * parts[p + 1] = "";
         *
         * }
         * }
         *
         * StringBuilder sb = new StringBuilder();
         * for (String str : parts) {
         * sb.append(str.replaceAll("(?m)(^ *| +(?= |$))",
         * "").replaceAll("(?m)^$([\r\n]+?)(^$[\r\n]+?^)+", "$1"));
         * sb.append(" ");
         * }
         *
         * contenu = sb.toString();
         *
         * } catch (Exception e1) {
         * e1.printStackTrace();
         * }
         *
         * //nombre
         * try {
         * String[] parts = contenu.split(" ");
         * String[] Nombres = {"HdA$", "vnA$", "vltTA$", "ArbETA$", "xmsTA$",
         * "stTA$", "sbETA$", "vmnTA$", "tsETA$"};
         * for (int p = 0; p < parts.length; p++) {
         * if ((Plusieurs_fonctions.isANumber(parts[p]) == true)) {
         * parts[p] = Plusieurs_fonctions.convnumber(parts[p]);
         * }
         * for (String Nombre1 : Nombres) {
         * int L = Nombre1.length();
         * // cas : HdA$nrAjel
         * if (parts[p].contains(Nombre1) && parts[p].indexOf("n") == L &&
         * parts[p].length() > L + 1) {
         * nonNorm = parts[p];
         * parts[p] = nonNorm.substring(0, L + 1) + " " + nonNorm.substring(L +
         * 1);
         * } // cas : HdA$ n rAjl
         * else if (parts[p].equals(Nombre1)) {
         * if (parts[p + 1].equals("n")) {
         * nonNorm = parts[p] + parts[p + 1];
         * parts[p] = nonNorm;
         * parts[p + 1] = "";
         * } // cas : HdA$ rAjl
         * else {
         * parts[p] = parts[p] + "n";
         * }
         * }
         * }
         *
         * StringBuilder sb = new StringBuilder();
         * for (String str : parts) {
         * sb.append(str.replaceAll("(?m)(^ *| +(?= |$))",
         * "").replaceAll("(?m)^$([\r\n]+?)(^$[\r\n]+?^)+", "$1"));
         * sb.append(" ");
         * }
         * contenu = sb.toString();
         * }
         * } catch (Exception e1) {
         * e1.printStackTrace();
         * }
         * //regle pour h en p
         * try {
         * String[] parts = contenu.split(" ");
         * for (int p = 0; p < parts.length; p++) {
         * try (BufferedReader br1 = new BufferedReader(new
         * InputStreamReader(new FileInputStream("./data/latinhenp.txt"),
         * "UTF8"))) {
         * for (String line; (line = br1.readLine()) != null;) {
         * if (parts[p].equals(line.substring(0, line.indexOf(" ")))) {
         * parts[p] = line.substring(line.indexOf(" ") + 1);
         * } else if ((parts[p].indexOf("w") == 0 || parts[p].indexOf("b") == 0
         * || parts[p].indexOf("l") == 0 || parts[p].indexOf("f") == 0 ||
         * parts[p].indexOf("k") == 0)
         * && parts[p].substring(1).equals(line.substring(0, line.indexOf("
         * ")))) {
         * parts[p] = parts[p].substring(0, 1) + line.substring(line.indexOf("
         * ") + 1);
         * }
         * }
         * } catch (Exception e1) {
         * e1.printStackTrace();
         * }
         * }
         *
         * StringBuilder sb = new StringBuilder();
         * for (String str : parts) {
         * sb.append(str.replaceAll("(?m)(^ *| +(?= |$))",
         * "").replaceAll("(?m)^$([\r\n]+?)(^$[\r\n]+?^)+", "$1"));
         * sb.append(" ");
         * }
         * contenu = sb.toString();
         * } catch (Exception e1) {
         * e1.printStackTrace();
         * }
         * //regle pour A en p
         * try {
         * String[] parts = contenu.split(" ");
         * for (int p = 0; p < parts.length; p++) {
         * try (BufferedReader br1 = new BufferedReader(new
         * InputStreamReader(new FileInputStream("./data/latinAenp.txt"),
         * "UTF8"))) {
         * for (String line; (line = br1.readLine()) != null;) {
         * if (parts[p].equals(line.substring(0, line.indexOf(" ")))) {
         * parts[p] = line.substring(line.indexOf(" ") + 1);
         * } else if ((parts[p].indexOf("w") == 0 || parts[p].indexOf("b") == 0
         * || parts[p].indexOf("l") == 0 || parts[p].indexOf("f") == 0 ||
         * parts[p].indexOf("k") == 0)
         * && parts[p].substring(1).equals(line.substring(0, line.indexOf("
         * ")))) {
         * parts[p] = parts[p].substring(0, 1) + line.substring(line.indexOf("
         * ") + 1);
         * }
         * }
         * } catch (Exception e1) {
         * e1.printStackTrace();
         * }
         * }
         * StringBuilder sb = new StringBuilder();
         * for (String str : parts) {
         * sb.append(str.replaceAll("(?m)(^ *| +(?= |$))",
         * "").replaceAll("(?m)^$([\r\n]+?)(^$[\r\n]+?^)+", "$1"));
         * sb.append(" ");
         * }
         * contenu = sb.toString();
         * } catch (Exception e1) {
         * e1.printStackTrace();
         * }
         *
         * //regle pour Aouy en Y
         * try {
         * String[] parts = contenu.split(" ");
         * int i = 0;
         * for (int p = 0; p < parts.length; p++) {
         * try (BufferedReader br1 = new BufferedReader(new
         * InputStreamReader(new FileInputStream("./data/latinyouAenY.txt"),
         * "UTF8"))) {
         * for (String line; (line = br1.readLine()) != null;) {
         * if (parts[p].equals(line.substring(line.indexOf(" ") + 1))) {
         * parts[p] = line.substring(0, line.indexOf(" "));
         * i++;
         * System.out.println("AyenY:" + i + ": " + parts[p]);
         * } else if ((parts[p].indexOf("w") == 0 || parts[p].indexOf("b") == 0
         * || parts[p].indexOf("l") == 0 || parts[p].indexOf("f") == 0 ||
         * parts[p].indexOf("k") == 0)
         * && parts[p].substring(1).equals(line.substring(line.indexOf(" ") +
         * 1))) {
         * parts[p] = parts[p].substring(0, 1) + line.substring(0,
         * line.indexOf(" "));
         * i++;
         * System.out.println("AyenY:" + i + ": " + parts[p]);
         * }
         * }
         * } catch (Exception e1) {
         * e1.printStackTrace();
         * }
         * }
         *
         * StringBuilder sb = new StringBuilder();
         * for (String str : parts) {
         * sb.append(str.replaceAll("(?m)(^ *| +(?= |$))",
         * "").replaceAll("(?m)^$([\r\n]+?)(^$[\r\n]+?^)+", "$1"));
         * sb.append(" ");
         * }
         *
         * contenu = sb.toString();
         *
         * } catch (Exception e1) {
         * e1.printStackTrace();
         * }
         * //regle pour Yen y
         *
         * try {
         * String[] parts = contenu.split(" ");
         * for (int p = 0; p < parts.length; p++) {
         * try (BufferedReader br1 = new BufferedReader(new
         * InputStreamReader(new FileInputStream("./data/latainYeny.txt"),
         * "UTF8"))) {
         * for (String line; (line = br1.readLine()) != null;) {
         * if (parts[p].equals(line.substring(line.indexOf(" ") + 1))) {
         * parts[p] = line.substring(0, line.indexOf(" "));
         * } else if ((parts[p].indexOf("w") == 0 || parts[p].indexOf("b") == 0
         * || parts[p].indexOf("l") == 0 || parts[p].indexOf("f") == 0 ||
         * parts[p].indexOf("k") == 0)
         * && parts[p].substring(1).equals(line.substring(line.indexOf(" ") +
         * 1))) {
         * String ch = parts[p].substring(0, 1);
         * parts[p] = ch + line.substring(0, line.indexOf(" "));
         * }
         * }
         * } catch (Exception e1) {
         * e1.printStackTrace();
         * }
         * }
         * StringBuilder sb = new StringBuilder();
         * for (String str : parts) {
         * sb.append(str.replaceAll("(?m)(^ *| +(?= |$))",
         * "").replaceAll("(?m)^$([\r\n]+?)(^$[\r\n]+?^)+", "$1"));
         * sb.append(" ");
         * }
         * contenu = sb.toString();
         * } catch (Exception e1) {
         * e1.printStackTrace();
         * }
         *
         * //regle pour Sen s
         * try {
         * String[] parts = contenu.split(" ");
         * for (int p = 0; p < parts.length; p++) {
         * try (BufferedReader br1 = new BufferedReader(new
         * InputStreamReader(new FileInputStream("./data/Sad_en_sin.txt"),
         * "UTF8"))) {
         * for (String line; (line = br1.readLine()) != null;) {
         * if (parts[p].equals(line.substring(line.indexOf(" ") + 1))) {
         * parts[p] = line.substring(0, line.indexOf(" "));
         * } else if ((parts[p].indexOf("w") == 0 || parts[p].indexOf("b") == 0
         * || parts[p].indexOf("l") == 0 || parts[p].indexOf("f") == 0 ||
         * parts[p].indexOf("k") == 0)
         * && parts[p].substring(1).equals(line.substring(line.indexOf(" ") +
         * 1))) {
         * String ch = parts[p].substring(0, 1);
         * parts[p] = ch + line.substring(0, line.indexOf(" "));
         * }
         * }
         * } catch (Exception e1) {
         * e1.printStackTrace();
         * }
         * }
         *
         * StringBuilder sb = new StringBuilder();
         * for (String str : parts) {
         * sb.append(str.replaceAll("(?m)(^ *| +(?= |$))",
         * "").replaceAll("(?m)^$([\r\n]+?)(^$[\r\n]+?^)+", "$1"));
         * sb.append(" ");
         * }
         *
         * contenu = sb.toString();
         *
         * } catch (Exception e1) {
         * e1.printStackTrace();
         * }
         * //regle pour sen S
         *
         * try {
         * String[] parts = contenu.split(" ");
         * for (int p = 0; p < parts.length; p++) {
         * try (BufferedReader br1 = new BufferedReader(new
         * InputStreamReader(new FileInputStream("./data/sin_en_Sad.txt"),
         * "UTF8"))) {
         * for (String line; (line = br1.readLine()) != null;) {
         * if (parts[p].equals(line.substring(line.indexOf(" ") + 1))) {
         * parts[p] = line.substring(0, line.indexOf(" "));
         * } else if ((parts[p].indexOf("w") == 0 || parts[p].indexOf("b") == 0
         * || parts[p].indexOf("l") == 0 || parts[p].indexOf("f") == 0 ||
         * parts[p].indexOf("k") == 0)
         * && parts[p].substring(1).equals(line.substring(line.indexOf(" ") +
         * 1))) {
         * String ch = parts[p].substring(0, 1);
         * parts[p] = ch + line.substring(0, line.indexOf(" "));
         * }
         * }
         * } catch (Exception e1) {
         * e1.printStackTrace();
         * }
         * }
         *
         * StringBuilder sb = new StringBuilder();
         * for (String str : parts) {
         * sb.append(str.replaceAll("(?m)(^ *| +(?= |$))",
         * "").replaceAll("(?m)^$([\r\n]+?)(^$[\r\n]+?^)+", "$1"));
         * sb.append(" ");
         * }
         * contenu = sb.toString();
         * } catch (Exception e1) {
         * e1.printStackTrace();
         * }
         * //regle pour Yen A
         * try {
         * String[] parts = contenu.split(" ");
         * for (int p = 0; p < parts.length; p++) {
         * try (BufferedReader br1 = new BufferedReader(new
         * InputStreamReader(new FileInputStream("./data/YenA.txt"), "UTF8"))) {
         * for (String line; (line = br1.readLine()) != null;) {
         * if (parts[p].equals(line.substring(line.indexOf(" ") + 1))) {
         * parts[p] = line.substring(0, line.indexOf(" "));
         * } else if ((parts[p].indexOf("w") == 0 || parts[p].indexOf("b") == 0
         * || parts[p].indexOf("l") == 0 || parts[p].indexOf("f") == 0 ||
         * parts[p].indexOf("k") == 0)
         * && parts[p].substring(1).equals(line.substring(line.indexOf(" ") +
         * 1))) {
         * String ch = parts[p].substring(0, 1);
         * parts[p] = ch + line.substring(0, line.indexOf(" "));
         * }
         * }
         * } catch (Exception e1) {
         * e1.printStackTrace();
         * }
         * }
         *
         * StringBuilder sb = new StringBuilder();
         * for (String str : parts) {
         * sb.append(str.replaceAll("(?m)(^ *| +(?= |$))",
         * "").replaceAll("(?m)^$([\r\n]+?)(^$[\r\n]+?^)+", "$1"));
         * sb.append(" ");
         * }
         *
         * contenu = sb.toString();
         *
         * } catch (Exception e1) {
         * e1.printStackTrace();
         * }
         * //exeption
         *
         * try {
         * String[] parts = contenu.split(" ");
         * for (int p = 0; p < parts.length; p++) {
         * try (BufferedReader br1 = new BufferedReader(new
         * InputStreamReader(new FileInputStream("./data/latainexeption.txt"),
         * "UTF8"))) {
         * for (String line; (line = br1.readLine()) != null;) {
         * if (parts[p].equals(line.substring(0, line.indexOf(" ")))) {
         * parts[p] = line.substring(line.indexOf(" ") + 1);
         * } else if ((parts[p].indexOf("w") == 0 || parts[p].indexOf("b") == 0
         * || parts[p].indexOf("l") == 0 || parts[p].indexOf("f") == 0 ||
         * parts[p].indexOf("k") == 0)
         * && parts[p].substring(1).equals(line.substring(0, line.indexOf("
         * ")))) {
         * String ch = parts[p].substring(0, 1);
         * parts[p] = ch + line.substring(line.indexOf(" ") + 1);
         * }
         * }
         * } catch (Exception e1) {
         * e1.printStackTrace();
         * }
         * }
         *
         * StringBuilder sb = new StringBuilder();
         * for (String str : parts) {
         * sb.append(str.replaceAll("(?m)(^ *| +(?= |$))",
         * "").replaceAll("(?m)^$([\r\n]+?)(^$[\r\n]+?^)+", "$1"));
         * sb.append(" ");
         * }
         *
         * contenu = sb.toString();
         *
         * } catch (Exception e1) {
         * e1.printStackTrace();
         * }
         *
         * //regle w et les hamza
         * try {
         * int i = 0;
         * char[] partsCatacter = contenu.toCharArray();
         * for (int k = 0; k < partsCatacter.length; k++) {
         * if ((k == 0 || partsCatacter[k - 1] == ' ') && partsCatacter[k] ==
         * 'w' && partsCatacter[k + 1] == ' ' && partsCatacter[k + 2] == 'w' &&
         * partsCatacter[k + 3] == ' ') {
         * partsCatacter[k] = '\u0000';
         * partsCatacter[k + 1] = '\u0000';
         * partsCatacter[k + 3] = '\u0000';
         *
         * } else if ((k == 0 || partsCatacter[k - 1] == ' ') &&
         * partsCatacter[k] == 'w' && partsCatacter[k + 1] == ' ') {
         * partsCatacter[k + 1] = '\u0000';
         * } else if ((k == 0 || partsCatacter[k - 1] == 'w') &&
         * partsCatacter[k] == 'w' && partsCatacter[k + 1] == ' ') {
         * partsCatacter[k] = ' ';
         * partsCatacter[k + 1] = 'w';
         * } else if (partsCatacter[k] == '|') {
         * nonNorm = String.valueOf(partsCatacter[k]);
         * norm = MethodeRegles.rempI(nonNorm);
         * partsCatacter[k] = norm.charAt(0);
         * } else if (partsCatacter[k] == '<') {
         * nonNorm = String.valueOf(partsCatacter[k]);
         * norm = MethodeRegles.rempHamza(nonNorm);
         * partsCatacter[k] = norm.charAt(0);
         * } else if (partsCatacter[k] == '>') {
         * nonNorm = String.valueOf(partsCatacter[k]);
         * norm = MethodeRegles.rempHamza(nonNorm);
         * partsCatacter[k] = norm.charAt(0);
         * } else if (partsCatacter[k] == '&') {
         * nonNorm = String.valueOf(partsCatacter[k]);
         * norm = MethodeRegles.rempHamzaWaw(nonNorm);
         * partsCatacter[k] = norm.charAt(0);
         *
         * } else if (partsCatacter[k] == '}') {
         * nonNorm = String.valueOf(partsCatacter[k]);
         * norm = MethodeRegles.rempHamzaYa(nonNorm);
         * partsCatacter[k] = norm.charAt(0);
         *
         * } else if (partsCatacter[k] == '\'') {
         * partsCatacter[k] = '\u0000';
         *
         * } else if (partsCatacter[k] == 'G') {
         * nonNorm = String.valueOf(partsCatacter[k]);
         * norm = MethodeRegles.rempG(nonNorm);
         * partsCatacter[k] = norm.charAt(0);
         *
         * }
         * }
         * StringBuilder sb = new StringBuilder();
         * for (char c : partsCatacter) {
         * if (c != '\u0000') {
         * sb.append(c);
         * }
         * }
         * contenu = sb.toString();
         * } catch (Exception e1) {
         * e1.printStackTrace();
         * }
         */
 /*
         * colorJTextPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
         * colorJTextPane.setFont(new Font("Segoe UI", Font.PLAIN, 16));
         * scrollPane_1.setViewportView(colorJTextPane);
         */
        textPane_1.setText(Plusieurs_fonctions.latinToArabic(contenu));
    }

    public static String normalize_text(String text) throws Exception {
        text=text.replace("ـ", "");
        String contenu = Plusieurs_fonctions.arabicToLatin(text.replace("پ", "ب")).replace("\n", "\n").replace("\r", "\r");
        if (text.equalsIgnoreCase("ym$y lswsp w ")) {
            System.out.println("ff");
        }
        //saveText_inFile(contenu, "textTestApp.txt");
        //String nonNorm = "";
        //String norm = "";
        /*ArrayList<String> wordArrayList = new ArrayList<String>();
        for(String word : contenu.split(" ")) {
            wordArrayList.add(word);
        }*/
        ModelKnn model = new ModelKnn();

        System.out.println("//////////////////////// Debut H");
        contenu = ModelKnn.classifier_h(contenu, model);
        System.out.println("//////////////////////// Fin H\n//////////////////////// Debut M");
        contenu = ModelKnn.classifier_m(contenu, model);
        System.out.println("//////////////////////// Fin M\n//////////////////////// Debut E");
        contenu = ModelKnn.classifier_e(contenu, model);
        System.out.println("//////////////////////// Fin E\n//////////////////////// Debut F");
        contenu = ModelKnn.classifier_f(contenu, model);

        //liste d'exceptions
        //MethodeRegles.db_files_loading();
        //System.out.println(contenu);
        //contenu = MethodeRegles.Code("E:\\Extractor\\Extractor\\code.pl", contenu);
        System.out.println(contenu);
        contenu = MethodeRegles.accentuation(contenu);
        System.out.println(contenu);
        /*contenu = MethodeRegles.split_list_function(contenu);
        System.out.println(contenu);*/
        contenu = MethodeRegles.First_A_function(contenu);
        System.out.println(contenu);
        contenu = MethodeRegles.clitic_l_function(contenu);
        System.out.println(contenu);
        contenu = MethodeRegles.NchAllah_function(contenu);
        System.out.println(contenu);
        contenu = MethodeRegles.MachAllah_function(contenu);
        System.out.println(contenu);
        contenu = MethodeRegles.AsmAllh_function(contenu);
        System.out.println(contenu);
        contenu = MethodeRegles.Hmd_function(contenu);
        System.out.println(contenu);
        contenu = MethodeRegles.exception_list_function(contenu);
        System.out.println(contenu);
        contenu = MethodeRegles.regle_verb_negation_function(contenu);
        System.out.println(contenu);
        contenu = MethodeRegles.verbe_negation_function(contenu);
        System.out.println(contenu);
        contenu = MethodeRegles.question_function(contenu);
        //question
        //regle mA+verbe
        System.out.println(contenu);
        contenu = MethodeRegles.mA_verb_function(contenu);
        //regle verbe +pronom
        System.out.println(contenu);
        contenu = MethodeRegles.verb_pronoun_function(contenu);
        System.out.println(contenu);
        contenu = MethodeRegles.trois_pers_sing_function(contenu); //3éme personne de singulier(liste des noms et seulement lw)
        System.out.println(contenu);
        contenu = MethodeRegles.trois_pers_sing_plur_function(contenu);        //3éme personne de singulier et pluriel
        System.out.println(contenu);
        contenu = MethodeRegles.yA_nouns_function(contenu);//regle ya+noms
        System.out.println(contenu);
        contenu = MethodeRegles.ElY_function(contenu);
        System.out.println(contenu);
        contenu = MethodeRegles.nombre_function(contenu);
        //nombre
        //regle pour h en p
        System.out.println(contenu);
        contenu = MethodeRegles.h_en_p_function(contenu);
        System.out.println(contenu);
        contenu = MethodeRegles.A_en_p_function(contenu);       //regle pour A en p
        //regle pour Aouy en Y
        System.out.println(contenu);
        contenu = MethodeRegles.Aouy_en_Y_function(contenu);
        //regle pour Yen y
        System.out.println(contenu);
        contenu = MethodeRegles.Y_en_y_function(contenu);
        //regle pour Sen s
        System.out.println(contenu);
        contenu = MethodeRegles.S_en_s_function(contenu);
        System.out.println(contenu);
        contenu = MethodeRegles.s_en_S_function(contenu);        //regle pour sen S
        System.out.println(contenu);
        contenu = MethodeRegles.Y_en_A_function(contenu);
        //regle pour Yen A
        //exeption
        System.out.println(contenu);
        contenu = MethodeRegles.w_Hamza_function(contenu);
        System.out.println(contenu);

        return Plusieurs_fonctions.latinToArabic(contenu);
    }

    public class Controle implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent evt) {
            new Thread(new Hilo()).start();
        }
    }

    public class Hilo implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i <= 100; i++) {
                progressBar.setValue(i);
                progressBar.repaint();
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                }
            }
        }
    }
}
