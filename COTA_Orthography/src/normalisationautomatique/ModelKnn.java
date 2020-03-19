package normalisationautomatique;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import weka.classifiers.Classifier;
import weka.core.Instance;
import weka.core.Instances;

public class ModelKnn {

    public static String[] classes_k = {"supp_esp_supp_alif", "supp_esp", "supp_esp_supp_lam", "supp_esp_supp_lam_supp_alif", "supp_esp_ajout_alif", "ajout_alif"};
    public static String[] classes_m = {"supp_esp", "supp_esp_supp_lam", "supp_esp_ajout_alif", "ajout_alif"};
    public static String[] classes_f = {"supp_esp", "supp_esp_supp_lam", "supp_esp_ajout_alif", "ajout_alif"};
    public static String[] classes_E = {"supp_esp", "supp_esp_supp_lam", "supp_esp_ajout_alif", "ajout_alif"};
    public static String[] classes_h = {"supp_esp_supp_alif", "supp_esp", "supp_esp_supp_lam", "supp_esp_supp_lam_supp_alif", "supp_esp_ajout_alif", "ajout_alif"};
    static Classifier cls_m;
    static Classifier cls_k;
    static Classifier cls_h;
    static Classifier cls_f;
    static Classifier cls_E;
    public List<MotIndexe> listMots = new ArrayList<>();

    static String classifier_h(String contenu, ModelKnn model) throws Exception {
        model.listMots.clear();
        if (model.afficherh(contenu, "output_h.arff") == true) {
            Instances inta = model.modelKnn("output_h.arff", "./models/modelApph.model", cls_h);
            for (int index = 0; index < inta.numInstances(); index++) {
                Instance s = inta.get(index);
                String classe = classes_h[(int) s.classValue()];
                String nonNormalise = model.listMots.get(index).contenu;
                //System.out.println(nonNormalise + " : " + classe);
                String normalise = null;
                switch (classe) {
                    case "supp_esp_supp_alif":
                        normalise = Normalisation.supp_esp_supp_alif(nonNormalise, "h");
                        break;
                    case "supp_esp":
                        normalise = Normalisation.supp_esp(nonNormalise, "h");
                        break;
                    case "supp_esp_supp_lam":
                        normalise = Normalisation.supp_esp_supp_lam(nonNormalise, "h");
                        break;
                    case "supp_esp_supp_lam_supp_alif":
                        normalise = Normalisation.supp_esp_supp_lam_supp_alif(nonNormalise, "h");
                        break;
                    case "supp_esp_ajout_alif":
                        normalise = Normalisation.supp_esp_ajout_alif(nonNormalise, "h");
                        break;
                    case "ajout_alif":
                        normalise = Normalisation.ajout_alif(nonNormalise, "h");
                        break;
                    default:
                        break;
                }
                if (normalise != null) {
                    contenu = contenu.replaceAll(nonNormalise, normalise);
                }
            }
        }
        return contenu;
    }

    static String classifier_m(String contenu, ModelKnn model) throws Exception {
        model.listMots.clear();
        if (model.afficherM(contenu, "output_m.arff") == true) {
            Instances inta = model.modelKnn("output_m.arff", "./models/modelAppm.model", cls_m);
            for (int index = 0; index < inta.numInstances(); index++) {
                Instance s = inta.get(index);
                String classe = classes_m[(int) s.classValue()];
                String nonNormalise = model.listMots.get(index).contenu;
                //System.out.println(nonNormalise + " : " + classe);
                String normalise = null;
                switch (classe) {
                    case "supp_esp_supp_alif":
                        normalise = Normalisation.supp_esp_supp_alif(nonNormalise, "m");
                        break;
                    case "supp_esp":
                        normalise = Normalisation.supp_esp(nonNormalise, "m");
                        break;
                    case "supp_esp_supp_lam":
                        normalise = Normalisation.supp_esp_supp_lam(nonNormalise, "m");
                        break;
                    case "supp_esp_supp_lam_supp_alif":
                        normalise = Normalisation.supp_esp_supp_lam_supp_alif(nonNormalise, "m");
                        break;
                    case "supp_esp_ajout_alif":
                        normalise = Normalisation.supp_esp_ajout_alif(nonNormalise, "m");
                        break;
                    case "ajout_alif":
                        normalise = Normalisation.ajout_alif(nonNormalise, "m");
                        break;
                    default:
                        break;
                }
                if (normalise != null) {
                    contenu = contenu.replaceAll(nonNormalise, normalise);
                }
            }
        }
        return contenu;
    }

    static String classifier_k(String contenu, ModelKnn model) throws Exception {
        //System.out.println("******************************" + contenu);
        model.listMots.clear();
        if (model.afficherK(contenu, "output_k.arff") == true) {
            Instances inta = model.modelKnn("output_k.arff", "./models/modelAppk.model", cls_k);
            for (int index = 0; index < inta.numInstances(); index++) {
                Instance s = inta.get(index);
                String classe = classes_k[(int) s.classValue()];
                String nonNormalise = model.listMots.get(index).contenu;
                System.out.println(nonNormalise + " : " + classe);
                String normalise = null;
                switch (classe) {
                    case "supp_esp_supp_alif":
                        normalise = Normalisation.supp_esp_supp_alif(nonNormalise, "k");
                        break;
                    case "supp_esp":
                        normalise = Normalisation.supp_esp(nonNormalise, "k");
                        break;
                    case "supp_esp_supp_lam":
                        normalise = Normalisation.supp_esp_supp_lam(nonNormalise, "k");
                        break;
                    case "supp_esp_supp_lam_supp_alif":
                        normalise = Normalisation.supp_esp_supp_lam_supp_alif(nonNormalise, "k");
                        break;
                    case "supp_esp_ajout_alif":
                        normalise = Normalisation.supp_esp_ajout_alif(nonNormalise, "k");
                        break;
                    case "ajout_alif":
                        normalise = Normalisation.ajout_alif(nonNormalise, "k");
                        break;
                    default:
                        break;
                }
                if (normalise != null) {
                    contenu = contenu.replaceAll(nonNormalise, normalise);
                }
            }
        }
        return contenu;
    }

    static String classifier_f(String contenu, ModelKnn model) throws Exception {
        model.listMots.clear();
        if (model.afficherF(contenu, "output_f.arff") == true) {
            Instances inta = model.modelKnn("output_f.arff", "./models/modelAppF.model", cls_f);
            for (int index = 0; index < inta.numInstances(); index++) {
                Instance s = inta.get(index);
                String classe = classes_f[(int) s.classValue()];
                String nonNormalise = model.listMots.get(index).contenu;
                //System.out.println(nonNormalise + " : " + classe);
                String normalise = null;
                switch (classe) {
                    case "supp_esp_supp_alif":
                        normalise = Normalisation.supp_esp_supp_alif(nonNormalise, "f");
                        break;
                    case "supp_esp":
                        normalise = Normalisation.supp_esp(nonNormalise, "f");
                        break;
                    case "supp_esp_supp_lam":
                        normalise = Normalisation.supp_esp_supp_lam(nonNormalise, "f");
                        break;
                    case "supp_esp_supp_lam_supp_alif":
                        normalise = Normalisation.supp_esp_supp_lam_supp_alif(nonNormalise, "f");
                        break;
                    case "supp_esp_ajout_alif":
                        normalise = Normalisation.supp_esp_ajout_alif(nonNormalise, "f");
                        break;
                    case "ajout_alif":
                        normalise = Normalisation.ajout_alif(nonNormalise, "f");
                        break;
                    default:
                        break;
                }
                if (normalise != null) {
                    contenu = contenu.replaceAll(nonNormalise, normalise);
                }
            }
        }
        return contenu;
    }

    static String classifier_e(String contenu, ModelKnn model) throws Exception {
        model.listMots.clear();
        if (model.afficherE(contenu, "output_E.arff") == true) {
            Instances inta = model.modelKnn("output_E.arff", "./models/modelAppE.model", cls_E);
            for (int index = 0; index < inta.numInstances(); index++) {
                Instance s = inta.get(index);
                String classe = classes_E[(int) s.classValue()];
                String nonNormalise = model.listMots.get(index).contenu;
                //              System.out.println(nonNormalise + " : " + classe);
                String normalise = null;
                switch (classe) {
                    case "supp_esp_supp_alif":
                        normalise = Normalisation.supp_esp_supp_alif(nonNormalise, "E");
                        break;
                    case "supp_esp":
                        normalise = Normalisation.supp_esp(nonNormalise, "E");
                        break;
                    case "supp_esp_supp_lam":
                        normalise = Normalisation.supp_esp_supp_lam(nonNormalise, "E");
                        break;
                    case "supp_esp_supp_lam_supp_alif":
                        normalise = Normalisation.supp_esp_supp_lam_supp_alif(nonNormalise, "E");
                        break;
                    case "supp_esp_ajout_alif":
                        normalise = Normalisation.supp_esp_ajout_alif(nonNormalise, "E");
                        break;
                    case "ajout_alif":
                        normalise = Normalisation.ajout_alif(nonNormalise, "E");
                        break;
                    default:
                        break;
                }
                if (normalise != null) {
                    contenu = contenu.replaceAll(nonNormalise, normalise);
                }
            }
        }
        return contenu;
    }

    public boolean afficherh(String text, String outputfileName) throws Exception {
        StringBuilder arff_file_out = new StringBuilder();
        String a = "hA";
        String b = "hl";
        String c = "hAl";
        String d = "h";
        String oo = "whA";
        String f = "wh";
        String g = "whl";
        String h = "whAl";
        String k = "bhA";
        String l = "bh";
        String m = "bhl";
        String n = "bhAl";
        String o = "bhA";
        String p = "bh";
        String q = "bhl";
        String r = "bhAl";
        String s = "wfhA";
        String t = "wfh";
        String u = "wfhl";
        String w = "wfhAl";
        String aa = "wbhA";
        String bb = "wbh";
        String cc = "wbhl";
        String dd = "wbhAl";
        String ee = "fhA";
        String ff = "fh";
        String jj = "fhl";
        String kk = "fhAl";
        /*
         * String u1 = "hl"; String u2 = "whl"; String u3 = "bhl"; String u4 =
         * "wbhl"; String u5 = "fhl"; String u6 = "wfhl";
         */
        //byte[] encodedText = Files.readAllBytes(Paths.get(fileName));
        //String allText = new String(encodedText, StandardCharsets.UTF_8);
        int indiceCourant = 0;
        String[] parts = text.split(" ");//allText.split(" ");
        for (int i = 0; i < parts.length; i++) {
            if (parts[i].equals(a) || parts[i].equals(b) || parts[i].equals(c) || parts[i].equals(d) || parts[i].equals(oo) || parts[i].equals(f) || parts[i].equals(g) || parts[i].equals(h)
                    || parts[i].equals(k) || parts[i].equals(l) || parts[i].equals(m) || parts[i].equals(n) || parts[i].equals(o) || parts[i].equals(p) || parts[i].equals(q) || parts[i].equals(r) || parts[i].equals(s) || parts[i].equals(t) || parts[i].equals(u) || parts[i].equals(w) || parts[i].equals(aa) || parts[i].equals(bb) || parts[i].equals(cc) || parts[i].equals(dd) || parts[i].equals(ee) || parts[i].equals(ff) || parts[i].equals(jj) || parts[i].equals(kk)) {
                if (i + 1 < parts.length && i - 1 >= 0) {
                    String formattedPart = getFormattedPart("h", parts, i);
                    arff_file_out.append(formattedPart);
                    arff_file_out.append("\n");
                    MotIndexe mi = new MotIndexe(Plusieurs_fonctions.removeSpace(parts[i]) + " " + Plusieurs_fonctions.removeSpace(parts[i + 1]), indiceCourant);
                    listMots.add(mi);
                    //System.out.println("=>" + indiceCourant + " => " + formattedPart + " => " + mi.contenu);
                }
            }
            Iterator<String> it = MethodeRegles.NomArabeSansAL_List.iterator();
            while (it.hasNext()) {
                String line1 = it.next();
                if (parts[i].length() >= 4 && (parts[i].startsWith("hl") || parts[i].startsWith("whl") || parts[i].startsWith("bhl") || parts[i].startsWith("wbhl") || parts[i].startsWith("fhl") || parts[i].startsWith("wfhl")) && parts[i].contains(line1)) {
                    if (i + 1 < parts.length && i - 1 >= 0) {
                        String formattedPart = getFormattedPartUnique("h", parts, i);
                        arff_file_out.append(formattedPart);
                        arff_file_out.append("\n");
                        MotIndexe mi = new MotIndexe(Plusieurs_fonctions.removeSpace(parts[i]) + " " + Plusieurs_fonctions.removeSpace(parts[i + 1]), indiceCourant);
                        listMots.add(mi);
                        System.out.println("=>" + indiceCourant + " => " + formattedPart + " => " + mi.contenu);
                    }
                }
            }
            indiceCourant += parts[i].length() + 1;
        }
        if (!arff_file_out.toString().isEmpty()) {
            byte[] encoded = Files.readAllBytes(Paths.get("./entetes_arff/entete_h.txt"));
            String entete = new String(encoded, StandardCharsets.UTF_8);
            try (Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputfileName), StandardCharsets.UTF_8))) {
                out.write(entete);
                out.write(arff_file_out.toString());
            }
        }
        return !arff_file_out.toString().isEmpty();
    }

    public boolean afficherM(String text, String outputfileName) throws Exception {
        StringBuilder arff_file_out = new StringBuilder();
        String a = "m";
        String b = "ml";
        String c = "mAl";
        String d = "wm";
        String g = "wml";
        String f = "wmAl";
        //byte[] encodedText = Files.readAllBytes(Paths.get(fileName));
        //String allText = new String(encodedText, StandardCharsets.UTF_8);
        String[] parts = text.split(" ");
        int indiceCourant = 0;
        for (int i = 0; i < parts.length; i++) {
            if (i + 1 < parts.length) {
                if (parts[i].equals(a)
                        || parts[i].equals(b) || parts[i].equals(c) || parts[i].equals(d) || parts[i].equals(g) || parts[i].equals(f)) {
                    if (i + 1 < parts.length && i - 1 >= 0) {
                        arff_file_out.append(getFormattedPart("m", parts, i));
                        arff_file_out.append("\n");
                        listMots.add(new MotIndexe(Plusieurs_fonctions.removeSpace(parts[i]) + " " + Plusieurs_fonctions.removeSpace(parts[i + 1]), 0));
                    }
                }
            }
            Iterator<String> it = MethodeRegles.NomArabeSansAL_List.iterator();
            while (it.hasNext()) {
                String line1 = it.next();
                if (parts[i].length() >= 4 && (parts[i].startsWith("ml")
                        || parts[i].startsWith("wml") || parts[i].startsWith("bml") || parts[i].startsWith("wbml")
                        || parts[i].startsWith("fml") || parts[i].startsWith("wfml")) && parts[i].contains(line1)) {
                    if (i + 1 < parts.length && i - 1 >= 0) {
                        String formattedPart = getFormattedPartUnique("m", parts, i);
                        arff_file_out.append(formattedPart);
                        arff_file_out.append("\n");
                        MotIndexe mi = new MotIndexe(Plusieurs_fonctions.removeSpace(parts[i]) + " " + Plusieurs_fonctions.removeSpace(parts[i + 1]), indiceCourant);
                        listMots.add(mi);
                        System.out.println("=>" + indiceCourant + " => " + formattedPart + " => " + mi.contenu);
                    }
                }
            }
            indiceCourant += parts[i].length() + 1;
        }
        if (!arff_file_out.toString().isEmpty()) {
            byte[] encoded = Files.readAllBytes(Paths.get("entetes_arff/entete_m.txt"));
            String entete = new String(encoded, StandardCharsets.UTF_8);
            try (Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputfileName), StandardCharsets.UTF_8))) {
                out.write(entete);
                out.write(arff_file_out.toString());
            }
        }
        return !arff_file_out.toString().isEmpty();
    }

    public boolean afficherE(String text, String outputfileName) throws Exception {
        StringBuilder arff_file_out = new StringBuilder();
        String a = "E";
        String b = "El";
        String c = "EAl";
        String d = "wE";
        String g = "wEl";
        String f = "wEAl";
        //byte[] encodedText = Files.readAllBytes(Paths.get(fileName));
        //String allText = new String(encodedText, StandardCharsets.UTF_8);
        String[] parts = text.split(" ");
        int indiceCourant = 0;
        for (int i = 0; i < parts.length; i++) {
            if ((parts[i].equals(a) || parts[i].equals(b) || parts[i].equals(c) || parts[i].equals(d) || parts[i].equals(g) || parts[i].equals(f))) {
                if (i + 1 < parts.length && i - 1 >= 0) {
                    arff_file_out.append(getFormattedPart("E", parts, i));
                    arff_file_out.append("\n");
                    listMots.add(new MotIndexe(Plusieurs_fonctions.removeSpace(parts[i]) + " " + Plusieurs_fonctions.removeSpace(parts[i + 1]), 0));
                }
            }
            Iterator<String> it = MethodeRegles.NomArabeSansAL_List.iterator();
            while (it.hasNext()) {
                String line1 = it.next();
                if (parts[i].length() >= 4 && (parts[i].startsWith("El") || parts[i].startsWith("wEl") || parts[i].startsWith("bEl") || parts[i].startsWith("wbEl")
                        || parts[i].startsWith("fEl") || parts[i].startsWith("wfEl")) && parts[i].contains(line1)) {
                    if (i + 1 < parts.length && i - 1 >= 0) {
                        String formattedPart = getFormattedPartUnique("E", parts, i);
                        arff_file_out.append(formattedPart);
                        arff_file_out.append("\n");
                        MotIndexe mi = new MotIndexe(Plusieurs_fonctions.removeSpace(parts[i]) + " " + Plusieurs_fonctions.removeSpace(parts[i + 1]), indiceCourant);
                        listMots.add(mi);
                    }
                }
            }

            indiceCourant += parts[i].length() + 1;
        }
        if (!arff_file_out.toString().isEmpty()) {
            byte[] encoded = Files.readAllBytes(Paths.get("entetes_arff/entete_E.txt"));
            String entete = new String(encoded, StandardCharsets.UTF_8);
            try (Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputfileName), StandardCharsets.UTF_8))) {
                out.write(entete);
                out.write(arff_file_out.toString());
            }
        }
        return !arff_file_out.toString().isEmpty();
    }

    public boolean afficherF(String text, String outputfileName) throws Exception {
        StringBuilder arff_file_out = new StringBuilder();
        String a = "f";
        String b = "fl";
        String c = "fAl";
        String d = "wf";
        String g = "wfl";
        String f = "wfAl";
        //byte[] encodedText = Files.readAllBytes(Paths.get(fileName));
        //String allText = new String(encodedText, StandardCharsets.UTF_8);
        String[] parts = text.split(" ");
        int indiceCourant = 0;
        for (int i = 0; i < parts.length; i++) {
            if (i + 1 < parts.length) {
                if (parts[i].equals(a) || parts[i].equals(b)
                        || parts[i].equals(c) || parts[i].equals(d)
                        || parts[i].equals(g) || parts[i].equals(f)) {
                    if (i + 1 < parts.length && i - 1 >= 0) {
                        arff_file_out.append(getFormattedPart("f", parts, i));
                        arff_file_out.append("\n");
                        listMots.add(new MotIndexe(Plusieurs_fonctions.removeSpace(parts[i]) + " " + Plusieurs_fonctions.removeSpace(parts[i + 1]), 0));
                    }
                }
                Iterator<String> it = MethodeRegles.NomArabeSansAL_List.iterator();
                while (it.hasNext()) {
                    String line1 = it.next();
                    if (parts[i].length() >= 4 && (parts[i].startsWith("fl") || parts[i].startsWith("wfl"))
                            && parts[i].contains(line1)) {
                        if (i + 1 < parts.length && i - 1 >= 0) {
                            String formattedPart = getFormattedPartUnique("f", parts, i);
                            arff_file_out.append(formattedPart);
                            arff_file_out.append("\n");
                            MotIndexe mi = new MotIndexe(Plusieurs_fonctions.removeSpace(parts[i]) + " " + Plusieurs_fonctions.removeSpace(parts[i + 1]), indiceCourant);
                            listMots.add(mi);
                            System.out.println("=>" + indiceCourant + " => " + formattedPart + " => " + mi.contenu);
                        }
                    }
                }
            }
            indiceCourant += parts[i].length() + 1;

        }
        if (!arff_file_out.toString().isEmpty()) {
            byte[] encoded = Files.readAllBytes(Paths.get("entetes_arff/entete_f.txt"));
            String entete = new String(encoded, StandardCharsets.UTF_8);
            try (Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputfileName), StandardCharsets.UTF_8))) {
                out.write(entete);
                out.write(arff_file_out.toString());
            }
        }
        return !arff_file_out.toString().isEmpty();
    }

    public boolean afficherK(String text, String outputfileName) throws Exception {
        StringBuilder arff_file_out = new StringBuilder();
        String a = "kA";
        String b = "k";
        String c = "kl";
        String d = "kAl";
        String g = "wkA";
        String f = "wk";
        String h = "wkl";
        String k = "wkAl";
        //byte[] encodedText = Files.readAllBytes(Paths.get(fileName));
        //String allText = new String(encodedText, StandardCharsets.UTF_8);
        String[] parts = text.split(" ");
        int indiceCourant = 0;
        for (int i = 0; i < parts.length; i++) {
            if (i + 1 < parts.length) {
                if (parts[i].equals(a) || parts[i].equals(b)
                        || parts[i].equals(c) || parts[i].equals(d)
                        || parts[i].equals(g) || parts[i].equals(f)
                        || parts[i].equals(h) || parts[i].equals(k)) {
                    if (i + 1 < parts.length && i - 1 >= 0) {
                        arff_file_out.append(getFormattedPart("k", parts, i));
                        arff_file_out.append("\n");
                        listMots.add(new MotIndexe(Plusieurs_fonctions.removeSpace(parts[i]) + " " + Plusieurs_fonctions.removeSpace(parts[i + 1]), 0));
                    }
                }
                Iterator<String> it = MethodeRegles.NomArabeSansAL_List.iterator();
                while (it.hasNext()) {
                    String line1 = it.next();
                    if (parts[i].length() >= 4 && (parts[i].startsWith("kl") || parts[i].startsWith("wkl"))
                            && parts[i].contains(line1)) {
                        if (i + 1 < parts.length && i - 1 >= 0) {
                            String formattedPart = getFormattedPartUnique("k", parts, i);
                            arff_file_out.append(formattedPart);
                            arff_file_out.append("\n");
                            MotIndexe mi = new MotIndexe(Plusieurs_fonctions.removeSpace(parts[i])
                                    + " " + Plusieurs_fonctions.removeSpace(parts[i + 1]), indiceCourant);
                            listMots.add(mi);
                            System.out.println("=>" + indiceCourant + " => " + formattedPart + " => " + mi.contenu);
                        }
                    }
                }
            }
            indiceCourant += parts[i].length() + 1;
        }
        if (!arff_file_out.toString().isEmpty()) {
            byte[] encoded = Files.readAllBytes(Paths.get("entetes_arff/entete_k.txt"));
            String entete = new String(encoded, StandardCharsets.UTF_8);
            try (Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputfileName), StandardCharsets.UTF_8))) {
                out.write(entete);
                out.write(arff_file_out.toString());
            }
        }
        return !arff_file_out.toString().isEmpty();
    }

    public Instances modelKnn(String filetest, String filemm, Classifier cls) throws Exception {
        // train classifier
        // Classifier cls = (IBk) (new ObjectInputStream(new FileInputStream(filemm))).readObject();
        Instances dataset_test = new Instances(new BufferedReader(new FileReader(filetest)));
        dataset_test.setClassIndex(dataset_test.numAttributes() - 1);
        for (int cpt = 0; cpt < dataset_test.numInstances(); cpt++) {
            Instance element = dataset_test.instance(cpt);
            double classe = cls.classifyInstance(element);
            element.setClassValue(classe);
        }
        // tu dois maintenant enregistrer dataset_test dans un fichier .arff.
        /*
         * try (BufferedWriter writer = new BufferedWriter(new
         * OutputStreamWriter(new FileOutputStream(new File(outputArff)),
         * "UTF8"))) {
         * writer.write(dataset_test.toString());
         * writer.flush();
         * writer.close();
         * } catch (IOException e) {
         * e.printStackTrace();
         * }
         */
        return dataset_test;
    }

    private String getFormattedPart(String caractere, String[] parts, int i) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(caractere).append(",");
        if (i == 0) {
            int caracterPosition = parts[i].indexOf(caractere);
            String partAllThree = parts[i] + " " + parts[i + 1];
            char[] part1 = partAllThree.toCharArray();
            sb.append("-").append(",");
            sb.append("-").append(",");
            sb.append(String.valueOf(part1.length >= caracterPosition + 2 ? part1[caracterPosition + 1] : "-").replace("\'", "'\\''").replace("}", "'}'").replace("{", "'{'").replace(" ", "_")).append(",");
            sb.append(String.valueOf(part1.length >= caracterPosition + 3 ? part1[caracterPosition + 2] : "-").replace("\'", "'\\''").replace("}", "'}'").replace("{", "'{'").replace(" ", "_")).append(",");
            sb.append(String.valueOf(part1.length >= caracterPosition + 4 ? part1[caracterPosition + 3] : "-").replace("\'", "'\\''").replace("}", "'}'").replace("{", "'{'").replace(" ", "_")).append(",");
            sb.append(String.valueOf(part1.length >= 1 ? part1[0] : "-").replace("\'", "'\\''").replace("}", "'}'").replace("{", "'{'").replace(" ", "_")).append(",");
            sb.append(String.valueOf(part1.length >= 2 ? part1[1] : "-").replace("\'", "'\\''").replace("}", "'}'").replace("{", "'{'").replace(" ", "_")).append(",");
            sb.append(String.valueOf(part1[partAllThree.length() - 2]).replace("\'", "'\\''").replace("}", "'}'").replace("{", "'{'").replace(" ", "_")).append(",");
            sb.append(String.valueOf(part1[partAllThree.length() - 1]).replace("\'", "'\\''").replace("}", "'}'").replace("{", "'{'").replace(" ", "_")).append(",");
        } else {
            int caracterPosition = parts[i - 1].length() + 1 + parts[i].indexOf(caractere);
            String partAllThree = parts[i - 1] + " " + parts[i] + " " + parts[i + 1];
            char[] part1 = partAllThree.toCharArray();
            int deb1 = parts[i - 1].length() + 1;
            int deb2 = parts[i - 1].length() + 2;
            sb.append(String.valueOf(caracterPosition == 0 ? '-' : part1[caracterPosition - 1]).replace("\'", "'\\''").replace("}", "'}'").replace("{", "'{'").replace(" ", "_")).append(",");
            sb.append(String.valueOf(caracterPosition == 1 ? '-' : part1[caracterPosition - 2]).replace("\'", "'\\''").replace("{", "'{'").replace(" ", "_")).append(",");
            sb.append(String.valueOf(part1.length >= caracterPosition + 2 ? part1[caracterPosition + 1] : "-").replace("\'", "'\\''").replace("}", "'}'").replace("{", "'{'").replace(" ", "_")).append(",");
            sb.append(String.valueOf(part1.length >= caracterPosition + 3 ? part1[caracterPosition + 2] : "-").replace("\'", "'\\''").replace("}", "'}'").replace("{", "'{'").replace(" ", "_")).append(",");
            sb.append(String.valueOf(part1.length >= caracterPosition + 4 ? part1[caracterPosition + 3] : "-").replace("\'", "'\\''").replace("}", "'}'").replace("{", "'{'").replace(" ", "_")).append(",");
            sb.append(String.valueOf(part1.length >= 1 ? part1[deb1] : "-").replace("\'", "'\\''").replace("}", "'}'").replace("{", "'{'").replace(" ", "_")).append(",");
            sb.append(String.valueOf(part1.length >= 2 ? part1[deb2] : "-").replace("\'", "'\\''").replace("}", "'}'").replace("{", "'{'").replace(" ", "_")).append(",");
            sb.append(String.valueOf(part1[partAllThree.length() - 2]).replace("\'", "'\\''").replace("}", "'}'").replace("{", "'{'").replace(" ", "_")).append(",");
            sb.append(String.valueOf(part1[partAllThree.length() - 1]).replace("\'", "'\\''").replace("}", "'}'").replace("{", "'{'").replace(" ", "_")).append(",");
        }
        sb.append("?");
        return sb.toString();
    }

    private String getFormattedPartUnique(String caractere, String[] parts, int i) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(caractere).append(",");

        int caracterPosition = parts[i - 1].length() + 1 + parts[i].indexOf(caractere);
        String partAllToo = parts[i - 1] + " " + parts[i];
        char[] part1 = partAllToo.toCharArray();
        if (i == 0) {
            // precedent 1
            sb.append(String.valueOf(parts[i].indexOf(caractere) == 0 ? '-' : parts[i].charAt(0)).replace("\'", "'\\''").replace(" ", "_")).append(",");
            // precedent 2
            sb.append("-").append(",");
        } else {
            // precedent 1
            sb.append(String.valueOf(caracterPosition == 0 ? '-' : part1[caracterPosition - 1]).replace("\'", "'\\''").replace("}", "'}'").replace("{", "'{'").replace(" ", "_")).append(",");
            // precedent 2
            sb.append(String.valueOf(caracterPosition == 1 ? '-' : part1[caracterPosition - 2]).replace("\'", "'\\''").replace("}", "'}'").replace("{", "'{'").replace(" ", "_")).append(",");
        }
        // suivant 1
        sb.append(String.valueOf(parts[i].charAt(parts[i].indexOf(caractere) + 1)).replace("\'", "'\\''").replace("}", "'}'").replace("{", "'{'").replace(" ", "_")).append(",");
        // suivant 2
        sb.append(String.valueOf(parts[i].charAt(parts[i].indexOf(caractere) + 2)).replace("\'", "'\\''").replace("}", "'}'").replace("{", "'{'").replace(" ", "_")).append(",");
        // suivant 3
        sb.append(String.valueOf(parts[i].charAt(parts[i].indexOf(caractere) + 3)).replace("\'", "'\\''").replace("}", "'}'").replace("{", "'{'").replace(" ", "_")).append(",");
        // debut 1
        sb.append(String.valueOf(parts[i].charAt(0)).replace("\'", "'\\''").replace("}", "'}'").replace("{", "'{'").replace(" ", "_")).append(",");
        // debut 2
        sb.append(String.valueOf(parts[i].charAt(1)).replace("\'", "'\\''").replace("}", "'}'").replace("{", "'{'").replace(" ", "_")).append(",");
        // fin 1
        sb.append(String.valueOf(parts[i].charAt(parts[i].length() - 2)).replace("\'", "'\\''").replace("}", "'}'").replace("{", "'{'").replace(" ", "_")).append(",");
        // fin 2
        sb.append(String.valueOf(parts[i].charAt(parts[i].length() - 1)).replace("\'", "'\\''").replace("}", "'}'").replace("{", "'{'").replace(" ", "_")).append(",");
        sb.append("?");
        // System.out.println(sb.toString());

        return sb.toString();
    }

    /*
     * public static void main ( String[] args ) throws Exception {
     * ModelKnn mm = new ModelKnn();
     * // mm.afficherh("textTestApp.txt", "output_h1.arff");
     * //mm.afficherM("textTestApp.txt", "output_m.arff");
     * //mm.afficherE("textTestApp.txt", "output_E.arff");
     * //mm.afficherF("textTestApp.txt", "output_f.arff");
     * //mm.afficherK("textTestApp.txt", "output_k.arff");
     *
     * // mm.modelKnn("output_h.arff", "modelApph.model","resultat_h1.arff");
     * //mm.modelKnn("output_m.arff", "modelAppm.model","resultat_m.arff");
     * // mm.modelKnn("output_E.arff", "modelAppE.model","resultat_E.arff");
     * //mm.modelKnn("output_f.arff", "modelAppf.model","resultat_f.arff");
     * //mm.modelKnn("output_k.arff", "modelAppk.model","resultat_k.arff");
     * }
     */
}
