import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ProcessBuilder.Redirect;
import java.nio.charset.StandardCharsets;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFileChooser;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;




import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


public class Normalisation_1 {

	private JFrame frame;
	private String contenu;
	JTextPane textPane_1  , textPane ;
	ColorJTextPane colorJTextPane;
	JScrollPane scrollPane ,  scrollPane_1;
	JProgressBar progressBar;
	JLabel labelNew , labelOpen , labelSave ;
	JButton btnNewButton ;
	public Controle controle ;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Normalisation_1 window = new Normalisation_1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Normalisation_1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		frame = new JFrame();
		
		Image  imgIcon = new ImageIcon(this.getClass().getResource("WM4R_icone.jpg")).getImage();
		
		frame.setIconImage(imgIcon);
		frame.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				
				int x_1 = (int ) ( e.getComponent().getWidth() /1.8);
				
			System.out.println( "w "+ e.getComponent().getWidth()+ "   h "+ e.getComponent().getHeight());
			
			int w = ( int ) (e.getComponent().getWidth() /2.5);
			int h = ( int ) (e.getComponent().getHeight() /1.25);
			
			scrollPane.setBounds(33,60 , w , h-20);
			scrollPane_1.setBounds(x_1,60 , w , h-20);
			
			int x_b = (int ) ( e.getComponent().getWidth() /2.3);
			 int wB = (int ) ( e.getComponent().getWidth() /8.7);
			
			btnNewButton.setBounds(x_b, 303,wB, 43);
			
			
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
		mntmNew.setIcon(new ImageIcon(Normalisation_1.class.getResource("/weka/gui/images/new.gif")));
		mntmNew.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnFile.add(mntmNew);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				
				
				
				TexteNO  textNo = new TexteNO();
				String s = textNo.openFile();
				textPane.setText(s);
				
				/*
				JFileChooser fc = new JFileChooser();
				String nomFic = "";
		        
		        FileNameExtensionFilter ff = new FileNameExtensionFilter("Fichiers texte", "txt");
		        fc.setFileFilter(ff);
		 
		        int returnVal = fc.showOpenDialog(getParent());
			
		        if (returnVal == JFileChooser.APPROVE_OPTION) 
		        {
		            nomFic = fc.getSelectedFile().getAbsolutePath();
		
				 
				        StringBuilder str=new StringBuilder();
				        try
				        {   BufferedReader br = new BufferedReader(new InputStreamReader(new java.io.FileInputStream(nomFic),StandardCharsets.UTF_8));
				              String ligne = null;
				 
				              while ((ligne = br.readLine()) != null)
				              {
				                  str.append(ligne);
				                  str.append( "\n" );
				              }
				 
				        }catch(Exception err)
				        { }
				        textPane.setText(str.toString());}*/
				
			}
		});
		mntmOpen.setIcon(new ImageIcon(Normalisation_1.class.getResource("/weka/gui/images/open.gif")));
		mntmOpen.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnFile.add(mntmOpen);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.setIcon(new ImageIcon(Normalisation_1.class.getResource("/weka/gui/images/save.gif")));
		mntmSave.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnFile.add(mntmSave);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
	
		mntmExit.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnFile.add(mntmExit);
		
		JMenu normalize = new JMenu("Normalize");
		normalize.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//normalize(e);
			}
		});
		normalize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{

				normalize(e);
			}

				
				
				
				
				
			
		});
		
		normalize.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(normalize);
		frame.getContentPane().setLayout(null);
		
	    textPane = new JTextPane();
	    textPane.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textPane.setBounds(326, 63, 550, 600);
		textPane .setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		//frame.getContentPane().add(textPane);
		
		
		 scrollPane = new JScrollPane();
		//scrollPane.setBounds(33, 60,536, 570);
		scrollPane.setViewportView(textPane);
		
		
		frame.getContentPane().add(scrollPane);
		
	 textPane_1 = new JTextPane();
	 textPane_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textPane_1.setBounds(1021, 249,550, 600);
		textPane_1 .setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		//frame.getContentPane().add(textPane_1);
		
		
         scrollPane_1 = new JScrollPane();
		//scrollPane_1.setBounds(757, 60, 536, 570);
		scrollPane_1.setViewportView(textPane_1);
		frame.getContentPane().add(scrollPane_1);
		
		controle=new Controle();
		
		 btnNewButton = new JButton("Normalize");
		btnNewButton.setForeground(new Color(60, 179, 113));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setIcon(new ImageIcon(Normalisation_1.class.getResource("/weka/gui/beans/icons/cog_go.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				controle.actionPerformed(e);
				normalize(e);
				
				
			}
		});
		//btnNewButton.setBounds(580, 303, 154, 43);
		frame.getContentPane().add(btnNewButton);
		
		 progressBar = new JProgressBar();
		
		progressBar.setBounds(589, 357, 146, 14);
		frame.getContentPane().add(progressBar);
	
		JLabel labelNew = new JLabel("");
		labelNew.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				// code of open file 
			}
		});
		labelNew.setIcon(new ImageIcon(Normalisation_1.class.getResource("/weka/gui/images/new.gif")));
		labelNew.setBounds(10, 0, 24, 24);
		frame.getContentPane().add(labelNew);
		
		labelOpen = new JLabel("");
		labelOpen.setIcon(new ImageIcon(Normalisation_1.class.getResource("/weka/gui/images/open.gif")));
		labelOpen.setBounds(36, 0, 24, 24);
		frame.getContentPane().add(labelOpen);
		
		 labelSave = new JLabel("");
		labelSave.setIcon(new ImageIcon(Normalisation_1.class.getResource("/weka/gui/images/save.gif")));
		labelSave.setBounds(67, 0, 24, 24);
		frame.getContentPane().add(labelSave);
		
	
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
			if (writer != null)
				writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}




public void normalize (ActionEvent e ) {
	controle.actionPerformed(e);

	
	
	
	
	
    contenu = MethodeRegles.ArabeLatin(textPane.getText());
     
    colorJTextPane = new ColorJTextPane();
    
		stringToFile(ModelKnn.removeSpace(contenu), "textTestApp.txt");
		
		String  nonNorm=null;
		String norm=null;
		
		ModelKnn mm = new ModelKnn();
		
		
		System.out.println("//////////////////////// Debut H");
		
		try {
			
			mm.listH.clear();
			mm.afficherh("textTestApp.txt", "output_h.arff");
			mm.modelKnn("output_h.arff", "modelApph.model","resultat_h.arff");
			
			try(BufferedReader br = new BufferedReader(new FileReader("resultat_h.arff"))) {
				
				int index = 1;
			    for(String line; (line = br.readLine()) != null; ) {

			    	if(index > 15) {
			    		
			    		String data = line.substring(0, line.lastIndexOf(","));
			    		String classe = line.substring(line.lastIndexOf(",")+1);
			    		
			    		String nonNormalise = mm.listH.get(index -16).contenu;
			    		
			    		System.out.println(nonNormalise + " : " + classe + " : " + data);
			    		
			    		String normalise = null;
			    		
			    		switch (classe) {
							case "supp_esp_supp_alif":
								normalise = MethodeClass.supp_esp_supp_alif((nonNormalise), "h");
								break;
							case "supp_esp":
								normalise = MethodeClass.supp_esp((nonNormalise), "h");
								break;
							case "supp_esp_supp_lam":
								normalise = MethodeClass.supp_esp_supp_lam(nonNormalise, "h");
								break;
							case "supp_esp_supp_lam_supp_alif":
								normalise = MethodeClass.supp_esp_supp_lam_supp_alif(nonNormalise, "h");
								break;
							case "supp_esp_ajout_alif":
								normalise = MethodeClass.supp_esp_ajout_alif(nonNormalise, "h");
								break;
							case "ajout_alif":
								normalise = MethodeClass.ajout_alif(nonNormalise, "h");
								break;
							default:
								break;
						}
			    		
			    		
			    		if(normalise != null) {

			    			contenu = contenu.replaceAll(nonNormalise, normalise);
			    		}
			    		
			    	}
			    	index++;
			    }
			}
			
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		
		
		System.out.println("//////////////////////// Fin H");
		System.out.println("//////////////////////// Debut M");
		
		try {
			
			mm.listH.clear();
			mm.afficherm("textTestApp.txt", "output_m.arff");
			mm.modelKnn("output_m.arff", "modelAppm.model","resultat_m.arff");
			
			try(BufferedReader br = new BufferedReader(new FileReader("resultat_m.arff"))) {
				
				String linPrec;
				int index = 1;
			    for(String line; (line = br.readLine()) != null; ) {

			    	if(index > 15) {
			    		
			    		String classe = line.substring(line.lastIndexOf(",")+1);
			    		
			    		String nonNormalise = mm.listH.get(index -16).contenu;
			    		
			    		System.out.println(nonNormalise + " : " + classe);
			    		
			    		String normalise = null;
			    		
			    		switch (classe) {
							case "supp_esp_supp_alif":
								normalise = MethodeClass.supp_esp_supp_alif(nonNormalise, "m");
								break;
							case "supp_esp":
								normalise = MethodeClass.supp_esp(nonNormalise, "m");
								break;
							case "supp_esp_supp_lam":
								normalise = MethodeClass.supp_esp_supp_lam(nonNormalise, "m");
								break;
							case "supp_esp_supp_lam_supp_alif":
								normalise = MethodeClass.supp_esp_supp_lam_supp_alif(nonNormalise, "m");
								break;
							case "supp_esp_ajout_alif":
								normalise = MethodeClass.supp_esp_ajout_alif(nonNormalise, "m");
								break;
							case "ajout_alif":
								normalise = MethodeClass.ajout_alif(nonNormalise, "m");
								break;
							default:
								break;
						}
			    		
			    		
			    		if(normalise != null) {

			    			contenu = contenu.replaceAll(nonNormalise, normalise);
			    		}
			    		
			    	}
			    	index++;
			    }
			}
			
			
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		System.out.println("//////////////////////// Fin M");
		System.out.println("//////////////////////// Debut E");
		
			try {
			int cc=0;
			mm.listH.clear();
			mm.afficherE("textTestApp.txt", "output_E.arff");
			mm.modelKnn("output_E.arff", "modelAppE.model","resultat_E.arff");
			
			try(BufferedReader br = new BufferedReader(new FileReader("resultat_E.arff"))) {
				
				int index = 1;
			    for(String line; (line = br.readLine()) != null; ) {

			    	if(index > 15) {
			    		
			    		String classe = line.substring(line.lastIndexOf(",")+1);
			    		
			    		String nonNormalise = mm.listH.get(index -16).contenu;
			    		
			    		System.out.println(nonNormalise + " : " + classe);
			    		
			    		String normalise = null;
			    		
			    		switch (classe) {
							case "supp_esp_supp_alif":
								normalise = MethodeClass.supp_esp_supp_alif(nonNormalise, "E");
								break;
							case "supp_esp":
								normalise = MethodeClass.supp_esp(nonNormalise, "E");
								break;
							case "supp_esp_supp_lam":
								normalise = MethodeClass.supp_esp_supp_lam(nonNormalise, "E");
								break;
							case "supp_esp_supp_lam_supp_alif":
								normalise = MethodeClass.supp_esp_supp_lam_supp_alif(nonNormalise, "E");
								break;
							case "supp_esp_ajout_alif":
								normalise = MethodeClass.supp_esp_ajout_alif(nonNormalise, "E");
								break;
							case "ajout_alif":
								normalise = MethodeClass.ajout_alif(nonNormalise, "E");
								break;
							default:
								break;
						}
			    		
			    		
			    		if(normalise != null) {

			    			contenu = contenu.replaceAll(nonNormalise, normalise);
			    			/*cc++;
			    			System.out.println("E :"+cc+" :"+nonNormalise+" :"+ normalise);*/
			    		}
			    		
			    	}
			    	index++;
			    }
			}
			
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}

					System.out.println("//////////////////////// Fin E");
		System.out.println("//////////////////////// Debut F");
		
	   try {
			
			mm.listH.clear();
			mm.afficherf("textTestApp.txt", "output_f.arff");
			mm.modelKnn("output_f.arff", "modelAppF.model","resultat_f.arff");
			
			try(BufferedReader br = new BufferedReader(new FileReader("resultat_f.arff"))) {
				
				int index = 1;
			    for(String line; (line = br.readLine()) != null; ) {
	
			    	if(index > 15) {
			    		
			    		String classe = line.substring(line.lastIndexOf(",")+1);
			    		
			    		String nonNormalise = mm.listH.get(index -16).contenu;
			    		
			    		System.out.println(nonNormalise + " : " + classe);
			    		
			    		String normalise = null;
			    		
			    		switch (classe) {
							case "supp_esp_supp_alif":
								normalise = MethodeClass.supp_esp_supp_alif(nonNormalise, "f");
								break;
							case "supp_esp":
								normalise = MethodeClass.supp_esp(nonNormalise, "f");
								break;
							case "supp_esp_supp_lam":
								normalise = MethodeClass.supp_esp_supp_lam(nonNormalise, "f");
								break;
							case "supp_esp_supp_lam_supp_alif":
								normalise = MethodeClass.supp_esp_supp_lam_supp_alif(nonNormalise, "f");
								break;
							case "supp_esp_ajout_alif":
								normalise = MethodeClass.supp_esp_ajout_alif(nonNormalise, "f");
								break;
							case "ajout_alif":
								normalise = MethodeClass.ajout_alif(nonNormalise, "f");
								break;
							default:
								break;
						}
			    		
			    		
			    		if(normalise != null) {
	
			    			contenu = contenu.replaceAll(nonNormalise, normalise);
			    		}
			    		
			    	}
			    	index++;
			    }
			}
			
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		

	   	System.out.println("//////////////////////// Fin F");
		System.out.println("//////////////////////// Debut K");
		
	   try {
			
			mm.listH.clear();
			mm.afficherk("textTestApp.txt", "output_k.arff");
			mm.modelKnn("output_k.arff", "modelAppk.model","resultat_k.arff");
			
			try(BufferedReader br = new BufferedReader(new FileReader("resultat_k.arff"))) {
				
				int index = 1;
			    for(String line; (line = br.readLine()) != null; ) {
	
			    	if(index > 15) {
			    		
			    		String classe = line.substring(line.lastIndexOf(",")+1);
			    		
			    		String nonNormalise = mm.listH.get(index -16).contenu;
			    		
			    		System.out.println(nonNormalise + " : " + classe);
			    		
			    		String normalise = null;
			    		
			    		switch (classe) {
							case "supp_esp_supp_alif":
								normalise = MethodeClass.supp_esp_supp_alif(nonNormalise, "k");
								break;
							case "supp_esp":
								normalise = MethodeClass.supp_esp(nonNormalise, "k");
								break;
							case "supp_esp_supp_lam":
								normalise = MethodeClass.supp_esp_supp_lam(nonNormalise, "k");
								break;
							case "supp_esp_supp_lam_supp_alif":
								normalise = MethodeClass.supp_esp_supp_lam_supp_alif(nonNormalise, "k");
								break;
							case "supp_esp_ajout_alif":
								normalise = MethodeClass.supp_esp_ajout_alif(nonNormalise, "k");
								break;
							case "ajout_alif":
								normalise = MethodeClass.ajout_alif(nonNormalise, "k");
								break;
							default:
								break;
						}
			    		
			    		
			    		if(normalise != null) {
	
			    			contenu = contenu.replaceAll(nonNormalise, normalise);
			    		}
			    		
			    	}
			    	index++;
			    }
			}
			
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		
  //liste d'exeption
		
		try {
			String[] parts = contenu.split(" ");

			int i=0;
			for(int p=0;p< parts.length;p++)
			{
		    	try(BufferedReader br1 = new BufferedReader(new InputStreamReader( new FileInputStream("latainexeption.txt"), "UTF8"))) 
				{
			       for(String line; (line = br1.readLine()) != null; ) 
			       {
			    	   if(parts[p].equals(line.substring(0,line.indexOf(" "))))
			    	   {
			    		   parts[p]=line.substring(line.indexOf(" ")+1);
			    		   /*i++;
			    		   System.out.println("exp:"+i+parts[p]);*/
			    	   }
			    	   
			    	   else if ((parts[p].indexOf("w")==0||parts[p].indexOf("b")==0||parts[p].indexOf("l")==0||parts[p].indexOf("f")==0||parts[p].indexOf("k")==0)
			    			   && parts[p].substring(1).equals(line.substring(0,line.indexOf(" "))))
			    	   {
			    		   String ch=parts[p].substring(0,1);
			    		   parts[p]=ch+line.substring(line.indexOf(" ")+1);
			    		   /*i++;
			    		   System.out.println("exp:"+i+parts[p]);*/

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
		try
		{
      
		String[] parts = contenu.split(" ");

	    	int i=0;
			
			for(int p=0;p< parts.length;p++)
			{
		    	try(BufferedReader br1 = new BufferedReader(new InputStreamReader( new FileInputStream("lataiListeVerbes.txt"), "UTF8"))) 
				{
			       for(String line; (line = br1.readLine()) != null; ) 
			       {
			    	   int L=line.length();
			    	   // bA$ykH
			    	   
			    	   if(parts[p].indexOf("bA$")==0 &&  parts[p].substring(3).contains(line))
			    	   {
			    		    nonNorm=parts[p];
							String ch2=nonNorm.substring(3);
							String normaliseF="bA$"+" "+ch2;
							parts[p] = normaliseF;
							 /*i++;
				    		   System.out.println("futur:"+i+" "+parts[p]);*/
			    		   
			    	   }
			    	   else if(parts[p].indexOf("wbA$")==0 &&  parts[p].substring(4).contains(line))
			    	   {
			    		    nonNorm=parts[p];
							String ch2=nonNorm.substring(4);
							String normaliseF="wbA$"+" "+ch2;
							parts[p] = normaliseF;
							 /*i++;
				    		   System.out.println("futur:"+i+": "+parts[p]);*/
			    		   
			    		   
			    	   }
                   // b$ykH
			    	   
			    	   if(parts[p].indexOf("b$")==0 && parts[p].substring(2).contains(line) )
			    	   {
			    		    nonNorm=parts[p];
							String ch2=nonNorm.substring(2);
							String normaliseF="bA$"+" "+ch2;
							parts[p] = normaliseF;
							 /*i++;
				    		   System.out.println("futur:"+i+": "+parts[p]);*/
			    		   
			    		   
			    	   }
			    	   else if(parts[p].indexOf("wb$")==0 && parts[p].substring(3).contains(line) )
			    	   {
			    		    nonNorm=parts[p];
							String ch2=nonNorm.substring(3);
							String normaliseF="wbA$"+" "+ch2;
							parts[p] = normaliseF;
							/* i++;
				    		   System.out.println("futur:"+i+": "+parts[p]);*/
			    		   
			    		   
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
				int i=0;
	    	for (int p = 0; p < parts.length; p++)
			{ 	
		    	try(BufferedReader br1 = new BufferedReader(new InputStreamReader( new FileInputStream("lataiListeVerbes.txt"), "UTF8"))) 
				{
		    		
					for (String line; (line = br1.readLine()) != null;)
				    {
						
				    	int L = line.length();
				    	//mAykH$
				    	//„«‰Œ«·›‘
						if(parts[p].indexOf("mA")==0 && parts[p].contains(line)&& parts[p].indexOf("$")== L+2 && parts[p].lastIndexOf("$")==parts[p].length()-1)
						{
							nonNorm=parts[p];
							String normalise=MethodeRegles.negationmAVerbe(nonNorm);
							parts[p] = normalise;
							/* i++;
				    		   System.out.println("futur:"+i+": "+parts[p]);*/
							
						}
						//mAykHhA$
						else if(parts[p].indexOf("mA")==0 && parts[p].contains(line)&& (parts[p].indexOf("hA")==L+2 || parts[p].indexOf("hw")==L+2)&& parts[p].indexOf("$")== L+4 && parts[p].lastIndexOf("$")==parts[p].length()-1)
						{
							nonNorm=parts[p];
							String normalise="mA "+nonNorm.substring(2);
							parts[p] = normalise;
							/* i++;
				    		   System.out.println("futur:"+i+": "+parts[p]);*/
							
						}
						//wmAykH$
						else if(parts[p].indexOf("wmA")==0 && parts[p].contains(line)&& parts[p].indexOf("$")== L+3 && parts[p].lastIndexOf("$")==parts[p].length()-1)
						{
							nonNorm=parts[p];
							String normalise="wmA "+nonNorm.substring(3);
							parts[p] = normalise;
							 /*i++;
				    		   System.out.println("futur:"+i+": "+parts[p]);*/
							
						}
						//wmAykHhA$
						else if(parts[p].indexOf("wmA")==0 && parts[p].contains(line)&& (parts[p].indexOf("hA")==L+3 || parts[p].indexOf("hw")==L+3)&& parts[p].indexOf("$")== L+5 && parts[p].lastIndexOf("$")==parts[p].length()-1)
						{
							nonNorm=parts[p];
							String normalise="mA "+nonNorm.substring(3);
							parts[p] = normalise;
							/* i++;
				    		   System.out.println("futur:"+i+": "+parts[p]);*/
							
						}
						//mAykHlw$
						else if(parts[p].indexOf("mA")==0 && parts[p].contains(line)&& parts[p].indexOf("l")== L+2 &&line.endsWith("l")== false  && parts[p].lastIndexOf("$")==parts[p].length()-1)
						{
							nonNorm=parts[p];
							String ch1=nonNorm.substring(2,L+2);
							String ch2=nonNorm.substring(L+2);
							String normaliseF="mA"+" "+ch1+" "+ch2;
							parts[p] = normaliseF;
							/* i++;
				    		   System.out.println("futur:"+i+": "+parts[p]);*/
							
						}
						
						//mAykHhAlw$
						else if(parts[p].indexOf("mA")==0 && parts[p].contains(line)&& (parts[p].indexOf("hA")==L+2 || parts[p].indexOf("hw")==L+2) && line.endsWith("l")== false  && parts[p].lastIndexOf("$")==parts[p].length()-1)
						{
							nonNorm=parts[p];
							String ch1=nonNorm.substring(2,L+4);
							String ch2=nonNorm.substring(L+4);
							String normaliseF="mA"+" "+ch1+" "+ch2;
							parts[p] = normaliseF;
							/* i++;
				    		   System.out.println("futur:"+i+": "+parts[p]);*/
							
						}
						//wmAykHhAlw$
						else if(parts[p].indexOf("wmA")==0 && parts[p].contains(line)&& (parts[p].indexOf("hA")==L+3 || parts[p].indexOf("hw")==L+3) &&line.endsWith("l")== false  && parts[p].lastIndexOf("$")==parts[p].length()-1)
						{
							nonNorm=parts[p];
							String ch1=nonNorm.substring(3,L+5);
							String ch2=nonNorm.substring(L+5);
							String normaliseF="wmA"+" "+ch1+" "+ch2;
							parts[p] = normaliseF;
							/* i++;
				    		   System.out.println("futur:"+i+": "+parts[p]);*/
							
						}
					
						//wmAykHlw$
						else if(parts[p].indexOf("wmA")==0 && parts[p].contains(line)&& parts[p].indexOf("l")== L+3 && line.endsWith("l")== false  && parts[p].lastIndexOf("$")==parts[p].length()-1)
						{
							nonNorm=parts[p];
							String ch1=nonNorm.substring(3,L+3);
							String ch2=nonNorm.substring(L+3);
							String normaliseF="wmA"+" "+ch1+" "+ch2;
							parts[p] = normaliseF;
							/* i++;
				    		   System.out.println("futur:"+i+": "+parts[p]);*/
							
						}
						//mAqAllw$
						else if(parts[p].indexOf("mA")==0 && parts[p].contains(line)&& parts[p].lastIndexOf("l")== L+2 && line.endsWith("l")== true && parts[p].lastIndexOf("$")==parts[p].length()-1)
						{
							nonNorm=parts[p];
							String ch1=nonNorm.substring(2,L+2);
							String ch2=nonNorm.substring(L+2);
							String normaliseF="mA"+" "+ch1+" "+ch2;
							parts[p] = normaliseF;
							/* i++;
				    		   System.out.println("futur:"+i+": "+parts[p]);*/
							
						}
						//wmAqAllw$
						else if(parts[p].indexOf("wmA")==0 && parts[p].contains(line)&& parts[p].lastIndexOf("l")== L+3 && line.endsWith("l")== true && parts[p].lastIndexOf("$")==parts[p].length()-1)
						{
							nonNorm=parts[p];
							String ch1=nonNorm.substring(3,L+3);
							String ch2=nonNorm.substring(L+3);
							String normaliseF="mA"+" "+ch1+" "+ch2;
							parts[p] = normaliseF;
							/* i++;
				    		   System.out.println("futur:"+i+": "+parts[p]);*/
							
						}
						//mAqalw$
						else if(parts[p].indexOf("mA")==0 && parts[p].contains(line)&& line.endsWith("l")==true && parts[p].lastIndexOf("$")==parts[p].length()-1)
						{
							nonNorm=parts[p];
							String normalise=MethodeRegles.negationmAVerbe(nonNorm);
							parts[p] = normalise;
							/* i++;
				    		   System.out.println("futur:"+i+": "+parts[p]);*/
							
						}
						//wmAqAlw$

						else if(parts[p].indexOf("wmA")==0 && parts[p].contains(line)&& line.endsWith("l")== true && parts[p].lastIndexOf("$")==parts[p].length()-1)
						{
							nonNorm=parts[p];
							String normalise="wmA "+nonNorm.substring(3);									
							parts[p] = normalise;
							 /*i++;
				    		   System.out.println("futur:"+i+": "+parts[p]);*/
							
						}
						
						//mA ykHlw$ /kHl$

						else if(p != 0 && parts[p-1].equals("mA") && parts[p].indexOf(line)==0 && parts[p].indexOf("l")== L && line.endsWith("l")== false  && parts[p].lastIndexOf("$")==parts[p].length()-1)
						{
							nonNorm=parts[p];
							String ch1=nonNorm.substring(0,L);
							String ch2=nonNorm.substring(L);
							String normaliseF=ch1+" "+ch2;
							parts[p] = normaliseF;
							/* i++;
				    		   System.out.println("futur:"+i+": "+parts[p]);*/
						}
				
						//wmA ykHlw$ 

						else if(p != 0 && parts[p-1].equals("wmA") && parts[p].indexOf(line)==0 && parts[p].indexOf("l")== L && line.endsWith("l")== false  && parts[p].lastIndexOf("$")==parts[p].length()-1)
						{
							nonNorm=parts[p];
							String ch1=nonNorm.substring(0,L);
							String ch2=nonNorm.substring(L);
							String normaliseF=ch1+" "+ch2;
							parts[p] = normaliseF;
							 /*i++;
				    		   System.out.println("futur:"+i+": "+parts[p]);*/
						}
						
						//mA qAllw$ 

						else if(p != 0 && parts[p-1].equals("mA") && parts[p].indexOf(line)==0 && parts[p].lastIndexOf("l")== L && line.endsWith("l")== true && parts[p].lastIndexOf("$")==parts[p].length()-1)
						{
							nonNorm=parts[p];
							String ch1=nonNorm.substring(0,L);
							String ch2=nonNorm.substring(L);
							String normaliseF=ch1+" "+ch2;
							parts[p] = normaliseF;
							/* i++;
				    		   System.out.println("futur:"+i+": "+parts[p]);*/
						}
						//wmA qAllw$ 

						else if(p != 0 && parts[p-1].equals("wmA") && parts[p].indexOf(line)==0 && parts[p].lastIndexOf("l")== L && line.endsWith("l")== true && parts[p].lastIndexOf("$")==parts[p].length()-1)
						{
							nonNorm=parts[p];
							String ch1=nonNorm.substring(0,L);
							String ch2=nonNorm.substring(L);
							String normaliseF=ch1+" "+ch2;
							parts[p] = normaliseF;
							 /*i++;
				    		   System.out.println("futur:"+i+": "+parts[p]);*/
						}
				    	//mtkAfyny$/kHl/mykH$/„€‘‘
						else if(parts[p].indexOf("m") == 0 && parts[p].contains(line) && parts[p].lastIndexOf("$") == L+1 && parts[p].lastIndexOf("$")==parts[p].length()-1)
						{
							nonNorm = parts[p];
							String normalise = MethodeRegles.negationmVerbe(nonNorm);
							parts[p] = normalise;
							/* i++;
				    		   System.out.println("futur:"+i+": "+parts[p]);*/
															
						}
						//wmtkAfyny$
						else if(parts[p].indexOf("wm") == 0 && parts[p].contains(line) && parts[p].lastIndexOf("$") == L+2 && parts[p].lastIndexOf("$")==parts[p].length()-1)
						{
							nonNorm = parts[p];
							String normalise="wmA "+nonNorm.substring(2);									
							parts[p] = normalise;
							/* i++;
				    		   System.out.println("futur:"+i+": "+parts[p]);*/
															
						}
						//mykHlw$ //„« ‰Œ ·ÌÊ‘
						else if (parts[p].indexOf("m")== 0 && parts[p].contains(line) && parts[p].indexOf("l")== L+1 && line.endsWith("l")== false && parts[p].lastIndexOf("$")==parts[p].length()-1)
						{
							nonNorm=parts[p];
							String ch1=nonNorm.substring(1,L+1);
							String ch2=nonNorm.substring(L+1);
							String normaliseF="mA"+" "+ch1+" "+ch2;
							parts[p] = normaliseF;
							/*i++;
				    		   System.out.println("futur:"+i+": "+parts[p]);*/
						}
						//wmykHlw$
						else if (parts[p].indexOf("wm")== 0 && parts[p].contains(line) && parts[p].indexOf("l")== L+2 && line.endsWith("l")== false && parts[p].lastIndexOf("$")==parts[p].length()-1)
						{
							nonNorm=parts[p];
							String ch1=nonNorm.substring(2,L+2);
							String ch2=nonNorm.substring(L+2);
							String normaliseF="wmA"+" "+ch1+" "+ch2;
							parts[p] = normaliseF;
							/*i++;
				    		   System.out.println("futur:"+i+": "+parts[p]);*/
						}
						//mqAllw$
						else if (parts[p].indexOf("m")== 0 && parts[p].contains(line) && parts[p].lastIndexOf("l")== L+1 && line.endsWith("l")==true && parts[p].lastIndexOf("$")==parts[p].length()-1)
						{
							nonNorm=parts[p];
							String ch1=nonNorm.substring(1,L+1);
							String ch2=nonNorm.substring(L+1);
							String normaliseF="mA"+" "+ch1+" "+ch2;
							parts[p] = normaliseF;
							 /*i++;
				    		   System.out.println("futur:"+i+": "+parts[p]);*/
						}
						//wmqAllw$
						else if (parts[p].indexOf("wm")== 0 && parts[p].contains(line) && parts[p].lastIndexOf("l")== L+2 && line.endsWith("l")== true && parts[p].lastIndexOf("$")==parts[p].length()-1)
						{
							nonNorm=parts[p];
							String ch1=nonNorm.substring(2,L+2);
							String ch2=nonNorm.substring(L+2);
							String normaliseF="wmA"+" "+ch1+" "+ch2;
							parts[p] = normaliseF; 
							/*i++;
				    		   System.out.println("futur:"+i+": "+parts[p]);*/
						}
						//mqAlw$
						else if (parts[p].indexOf("m")== 0 && parts[p].contains(line) &&  line.endsWith("l")==true && parts[p].lastIndexOf("$")==parts[p].length()-1)
						{
							nonNorm = parts[p];
							String normalise = "mA "+ nonNorm.substring(1);
							parts[p] = normalise;
							 /*i++;
				    		   System.out.println("futur:"+i+": "+parts[p]);*/
						}
						//wmqAlw$
						else if (parts[p].indexOf("wm")== 0 && parts[p].contains(line) &&  line.endsWith("l")==true && parts[p].lastIndexOf("$")==parts[p].length()-1)
						{
							nonNorm = parts[p];
							String normalise="wmA "+nonNorm.substring(2);									
							parts[p] = normalise;
							 /*i++;
				    		   System.out.println("futur:"+i+": "+parts[p]);*/
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
		    	int i=0;
				for (int p = 0; p < parts.length; p++)
				{ 
		    		try(BufferedReader br1 = new BufferedReader(new InputStreamReader( new FileInputStream("lataiListeVerbes.txt"), "UTF8")))
					{
		    			for(String line; (line = br1.readLine()) != null; ) 
				        {
		    				int L=line.length();
		    					
		    				//A$gSyt/
				    	   if((parts[p].indexOf("A$")==0 ||parts[p].indexOf(">$")==0) && parts[p].substring(2).contains(line)&& parts[p].endsWith("p")==false)
				    	   {
								nonNorm=parts[p];
								String ch1=nonNorm.substring(2);
								String normalise= "A$ "+ch1;
								parts[p] = normalise;
								/* i++;
					    		   System.out.println("question:"+i+": "+parts[p]);*/
								

				    	   }
				    	 //wA$gSyt
				    	   if((parts[p].indexOf("wA$")==0 ||parts[p].indexOf("w>$")==0) && parts[p].substring(3).contains(line)&& parts[p].endsWith("p")==false)
				    	   {
								nonNorm=parts[p];
								String ch1=nonNorm.substring(3);
								String normalise= "wA$ "+ch1;
								parts[p] = normalise;
								/* i++;
					    		   System.out.println("question:"+i+": "+parts[p]);*/

				    	   }
		    				//$gSyt/$hwr

				    	   else if(parts[p].indexOf("$")==0 && parts[p].substring(1).contains(line) && parts[p].endsWith("p")==false )
				    	   {
								nonNorm=parts[p];
								String ch1=nonNorm.substring(1);
								String normalise= "A$ "+ch1;
								parts[p] = normalise;
								/* i++;
					    		   System.out.println("question:"+i+": "+parts[p]);*/
				    	   }
				    	   //w$gSyt
				    	   else if(parts[p].indexOf("w$")==0 && parts[p].substring(2).contains(line) && parts[p].endsWith("p")==false)
				    	   {
								nonNorm=parts[p];
								String ch1=nonNorm.substring(2);
								String normalise= "wA$ "+ch1;
								parts[p] = normalise;
								/* i++;
					    		   System.out.println("question:"+i+": "+parts[p]);*/
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
		    	
				int i=0;
				for(int p=0;p< parts.length;p++)
				{
			    	try(BufferedReader br1 = new BufferedReader(new InputStreamReader( new FileInputStream("Latain liste mA+verbe.txt"), "UTF8"))) 
					{
				       for(String line; (line = br1.readLine()) != null; ) 
				       { // mAkml
				    	   int L=line.length();
				    	   if(parts[p].indexOf("mA")==0  && parts[p].substring(2).equals(line))
				    	   {
				    		    nonNorm=parts[p];
								String ch1=nonNorm.substring(2);
					 			String normaliseF="mA"+" "+ch1;
								parts[p] = normaliseF;
								/* i++;
					    		   System.out.println("mA+verbe:"+i+": "+parts[p]);*/
							
				    		   
				    		   
				    	   }
				    	   else if(parts[p].indexOf("wmA")==0  && parts[p].substring(3).equals(line))
				    	   {
				    		    nonNorm=parts[p];
								String ch1=nonNorm.substring(3);
					 			String normaliseF="mA"+" "+ch1;
								parts[p] = normaliseF;
							
								/* i++;
					    		   System.out.println("mA+verbe:"+i+": "+parts[p]);*/
				    		   
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
		    	int i=0;
				
				for(int p=0;p< parts.length;p++)
				{
			    	try(BufferedReader br1 = new BufferedReader(new InputStreamReader( new FileInputStream("lataiListeVerbes.txt"), "UTF8"))) 
					{
				       for(String line; (line = br1.readLine()) != null; ) 
				       { // cas /xzrlhA /«·„·ÌÕ/  ⁄«„·
				    	   int L=line.length();
				    	   
				    	  if(parts[p].indexOf(line)==0 && parts[p].indexOf("l")==L && line.endsWith("l")==false && (parts[p].substring(L).equals("ly") ||parts[p].substring(L).equals("lnA")||
				    			  parts[p].substring(L).equals("lk") ||parts[p].substring(L).equals("lkm") ||parts[p].substring(L).equals("lhA") ||
				    			  parts[p].substring(L).equals("lhm") ||parts[p].substring(L).equals("lw") )&& parts[p].endsWith("l")==false)

				    	   {
				    		    nonNorm=parts[p];
								String ch1=nonNorm.substring(0,L);
								String ch2=nonNorm.substring(L);
					 			String normaliseF=ch1+" "+ch2;
								parts[p] = normaliseF;
							
								/*i++;
					    		   System.out.println("verbe+pronom:"+i+": "+parts[p]);*/
				    		   
				    	   }
				    	   // yEmlhAlhm/ 
				    	   else if(parts[p].indexOf(line)==0 && (parts[p].indexOf("hA")==L || parts[p].indexOf("hw")==L )&& parts[p].lastIndexOf("l")==L+2  )
				    	   {
				    		    nonNorm=parts[p];
								String ch1=nonNorm.substring(0,L+2);
								String ch2=nonNorm.substring(L+2);
					 			String normaliseF=ch1+" "+ch2;
								parts[p] = normaliseF;
								/*i++;
					    		   System.out.println("verbe+pronom:"+i+": "+parts[p]);*/
				    		   
				    		   
				    	   }
				    	   //qAllhm
				    	   else if(parts[p].indexOf(line)==0 &&(parts[p].substring(L).equals("ly") ||parts[p].substring(L).equals("lnA")||
					    			  parts[p].substring(L).equals("lk") ||parts[p].substring(L).equals("lkm") ||parts[p].substring(L).equals("lhA") ||
					    			  parts[p].substring(L).equals("lhm") ||parts[p].substring(L).equals("lw") )&& line.lastIndexOf("l") !=-1 && parts[p].lastIndexOf("l")==L  )
				    	   {
				    		    nonNorm=parts[p];
								String ch1=nonNorm.substring(0,L);
								String ch2=nonNorm.substring(L);
					 			String normaliseF=ch1+" "+ch2;
								parts[p] = normaliseF;
								/*i++;
					    		   System.out.println("verbe+pronom:"+i+": "+parts[p]);*/
				    		   
				    		   
				    	   }
				    	   
				    	   //wkHlw
				    	   else if( parts[p].indexOf("w")==0 &&parts[p].indexOf(line)==1 && parts[p].indexOf("l")==L+1 && line.endsWith("l")==false && (parts[p].substring(L+1).equals("ly") ||parts[p].substring(L+1).equals("lnA")||
					    			  parts[p].substring(L+1).equals("lk") ||parts[p].substring(L+1).equals("lkm") ||parts[p].substring(L+1).equals("lhA") ||
					    			  parts[p].substring(L+1).equals("lhm") ||parts[p].substring(L+1).equals("lw") )&& parts[p].substring(L+1).length()>1)
				    	   {
				    		    nonNorm=parts[p];
								String ch1=nonNorm.substring(0,L+1);
								String ch2=nonNorm.substring(L+1);
								String normaliseF=ch1+" "+ch2;
								parts[p] = normaliseF;
								/*i++;
					    		   System.out.println("verbe+pronom:"+i+": "+parts[p]);*/
							
				    		   
				    		   
				    	   }
				    	   // wyEmlhAlhm
				    	   else if(parts[p].indexOf("w")==0&&parts[p].indexOf(line)==1 && (parts[p].indexOf("hA")==L+1|| parts[p].indexOf("hw")==L+1) && parts[p].lastIndexOf("l")==L+3  )
				    	   {
				    		    nonNorm=parts[p];
								String ch1=nonNorm.substring(0,L+3);
								String ch2=nonNorm.substring(L+3);
					 			String normaliseF=ch1+" "+ch2;
								parts[p] = normaliseF;
								/*i++;
					    		   System.out.println("verbe+pronom:"+i+": "+parts[p]);*/
				    		   
				    		   
				    	   }
				    	   //wqAllhm
				    	   else if(parts[p].indexOf("w")==0&&parts[p].indexOf(line)==1 && line.endsWith("l")==true && parts[p].lastIndexOf("l")==L+1&&(parts[p].substring(L+1).equals("ly") ||parts[p].substring(L+1).equals("lnA")||
					    			  parts[p].substring(L+1).equals("lk") ||parts[p].substring(L+1).equals("lkm") ||parts[p].substring(L+1).equals("lhA") ||
					    			  parts[p].substring(L+1).equals("lhm") ||parts[p].substring(L+1).equals("lw") ) )
				    	   {
				    		    nonNorm=parts[p];
								String ch1=nonNorm.substring(0,L+1);
								String ch2=nonNorm.substring(L+1);
					 			String normaliseF=ch1+" "+ch2;
								parts[p] = normaliseF;
								/*i++;
					    		   System.out.println("verbe+pronom:"+i+": "+parts[p]);*/
				    		   
				    		   
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
			
			//3Ème personne de singulier(liste des noms et seulement lw)
			
			try {
				
				String[] parts = contenu.split(" ");
		    	
				int i=0;
				for(int p=0;p< parts.length;p++)
				{
			    	try(BufferedReader br1 = new BufferedReader(new InputStreamReader( new FileInputStream("latainlisteVerbe3Personne.txt"), "UTF8"))) 
					{
				       for(String line; (line = br1.readLine()) != null; ) 
				       {
				    	   int L=line.length();
				    	   int pa=parts[p].length();
				    	   // noms: ÊÕÿÊ/AmtyAzwA /Ìœ
				    	    if (parts[p].length()>L && parts[p].lastIndexOf("w")== pa-1  && parts[p].indexOf("w")==0 && parts[p].substring(1,pa-1).equals(line))
				    	   {
				    		   nonNorm=parts[p];
								String ch1=nonNorm.substring(0,L+1);
								String normalise= ch1+"h";
								parts[p] = normalise;
								/*i++;
					    		   System.out.println("3PerNoms:"+i+": "+parts[p]);*/
				    	   }
				    	    else if(parts[p].length()>L && parts[p].endsWith("wA")==true && parts[p].indexOf("w")==0 && parts[p].substring(1,pa-2).equals(line))
				    	    {
				    	    	nonNorm=parts[p];
								String ch1=nonNorm.substring(0,L+1);
								String normalise= ch1+"h";
								parts[p] = normalise;
								/*i++;
					    		   System.out.println("3PerNoms:"+i+": "+parts[p]);*/
				    	    
				    	    }
				    	    else if (parts[p].length()>L && parts[p].lastIndexOf("w")== pa-1 && parts[p].indexOf("l")==0 && parts[p].substring(1,pa-1).equals(line))
					    	   {
					    		   nonNorm=parts[p];
									String ch1=nonNorm.substring(0,L+1);
									String normalise= ch1+"h";
									parts[p] = normalise;
								/*	i++;
						    		   System.out.println("3PerNoms:"+i+": "+parts[p]);*/
					    	   }
				    	    else if(parts[p].length()>L && parts[p].endsWith("wA")==true && parts[p].indexOf("l")==0 && parts[p].substring(1,pa-2).equals(line))
				    	    {
				    	    	nonNorm=parts[p];
								String ch1=nonNorm.substring(0,L+1);
								String normalise= ch1+"h";
								parts[p] = normalise;
								/*i++;
					    		   System.out.println("3PerNoms:"+i+": "+parts[p]);*/
				    	    
				    	    }
				    	    else if (parts[p].length()>L && parts[p].lastIndexOf("w")== pa-1 && parts[p].indexOf("k")==0 && parts[p].substring(1,pa-1).equals(line))
					    	   {
					    		   nonNorm=parts[p];
									String ch1=nonNorm.substring(0,L+1);
									String normalise= ch1+"h";
									parts[p] = normalise;
									/*i++;
						    		   System.out.println("3PerNoms:"+i+": "+parts[p]);*/
					    	   }
				    	    else if(parts[p].length()>L && parts[p].endsWith("wA")==true && parts[p].indexOf("k")==0 && parts[p].substring(1,pa-2).equals(line))
				    	    {
				    	    	nonNorm=parts[p];
								String ch1=nonNorm.substring(0,L+1);
								String normalise= ch1+"h";
								parts[p] = normalise;
							/*	i++;
					    		   System.out.println("3PerNoms:"+i+": "+parts[p]);*/
				    	    
				    	    }
				    	    else if (parts[p].length()>L && parts[p].lastIndexOf("w")== pa-1 && parts[p].indexOf("b")==0 && parts[p].substring(1,pa-1).equals(line))
					    	   {
					    		   nonNorm=parts[p];
									String ch1=nonNorm.substring(0,L+1);
									String normalise= ch1+"h";
									parts[p] = normalise;
									/*i++;
						    		   System.out.println("3PerNoms:"+i+": "+parts[p]);*/
					    	   }
				    	    else if(parts[p].length()>L && parts[p].endsWith("wA")==true && parts[p].indexOf("b")==0 && parts[p].substring(1,pa-2).equals(line))
				    	    {
				    	    	nonNorm=parts[p];
								String ch1=nonNorm.substring(0,L+1);
								String normalise= ch1+"h";
								parts[p] = normalise;
								/*i++;
					    		   System.out.println("3PerNoms:"+i+": "+parts[p]);*/
				    	    
				    	    }
				    	    else if(parts[p].length()>L && parts[p].lastIndexOf("w")== pa-1 &&parts[p].substring(0,pa-1).equals(line) )
				    	   {
				    		    nonNorm=parts[p];
								String ch1=nonNorm.substring(0,L);
								String normalise= ch1+"h";
								parts[p] = normalise;
								/*i++;
					    		   System.out.println("3PerNoms:"+i+": "+parts[p]);*/
							
				    	   }
				    	    else if(parts[p].length()>L && parts[p].endsWith("wA")==true  && parts[p].substring(0,pa-2).equals(line))
				    	    {
				    	    	nonNorm=parts[p];
								String ch1=nonNorm.substring(0,L);
								String normalise= ch1+"h";
								parts[p] = normalise;
								/*i++;
					    		   System.out.println("3PerNoms:"+i+": "+parts[p]);*/
				    	    
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
			
           //3Ème personne de singulier et pluriel
			
			try {
				
				String[] parts = contenu.split(" ");
		    	int i=0;
				
				for(int p=0;p< parts.length;p++)
				{
			    	try(BufferedReader br1 = new BufferedReader(new InputStreamReader( new FileInputStream("lataiListeVerbes.txt"), "UTF8"))) 
					{
				       for(String line; (line = br1.readLine()) != null; ) 
				       {
				    	   int L=line.length();
				    	   int pa=parts[p].length();
				    	   // noms: ÊÕÿÊ
				    	    
				    	     if(parts[p].length()==L && parts[p].endsWith("Aw")== true  && parts[p].equals(line) )
					    	   {
									String normalise= parts[p]+"A";
									parts[p] = normalise;
								/*	i++;
						    		   System.out.println("3PerVerb:"+i+": "+parts[p]);*/
								
					    	   }
				    	     else if(parts[p].endsWith("yw")== true &&  parts[p].equals(line) && parts[p].length()==L )
					    	   {
									String normalise= parts[p]+"A";
									parts[p] = normalise;
									/*i++;
						    		   System.out.println("3PerVerb:"+i+": "+parts[p]);*/
								
					    	   }
				    	     else if(parts[p].length()>=L && (parts[p].endsWith("Aw")== true || parts[p].endsWith("yw")== true)&&parts[p].substring(1,pa).equals(line) )
					    	   {
									String normalise= parts[p]+"A";
									parts[p] = normalise;
									/*i++;
						    		   System.out.println("3PerVerb:"+i+": "+parts[p]);*/
								
					    	   }
				    	     else if (parts[p].length()>L && parts[p].lastIndexOf("w")== pa-1 && parts[p].substring(1,pa-1).equals(line))
				    	   {
				    		   nonNorm=parts[p];
								String ch1=nonNorm.substring(0,L+1);
								String normalise= ch1+"h"+"/"+parts[p]+"A";
								parts[p] = normalise;
								/*i++;
					    		   System.out.println("3PerVerb:"+i+": "+parts[p]);*/
				    	   }
				    	 
				    	    else if(parts[p].length()>L && parts[p].lastIndexOf("w")== pa-1 && parts[p].substring(0,pa-1).equals(line) )
				    	   {
				    		    nonNorm=parts[p];
								String ch1=nonNorm.substring(0,L);
								String normalise= ch1+"h"+"/"+parts[p]+"A";
								parts[p] = normalise;
								/*i++;
					    		   System.out.println("3PerVerb:"+i+": "+parts[p]);*/
							
				    	   }
				    	    else if (parts[p].equals("lw"))
				    	    {
								parts[p] = "lh";
								/*i++;
					    		   System.out.println("3PerVerb:"+i+": "+parts[p]);*/
				    	    	
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
	    	int i=0;
			
			for(int p=0;p< parts.length;p++)
			{
		    	try(BufferedReader br1 = new BufferedReader(new InputStreamReader( new FileInputStream("lataintNomPropre.txt"), "UTF8"))) 
				{
			       for(String line; (line = br1.readLine()) != null; ) 
			       {
			    	   
			    	   if(parts[p].indexOf("yA")==0 && parts[p].substring(2).equals(line))
			    	   {
							nonNorm=parts[p];
							String ch1=nonNorm.substring(2);
							String normalise= "yA "+ch1;
							parts[p] = normalise;
							  /*i++;
				    		   System.out.println("yA:"+i+": "+parts[p]);*/
			    	   }
			    	   
			    	   if(parts[p].indexOf("wyA")==0 && parts[p].substring(3).equals(line))
			    	   {
							nonNorm=parts[p];
							String ch1=nonNorm.substring(3);
							String normalise= "wyA "+ch1;
							parts[p] = normalise;
							/*  i++;
				    		   System.out.println("yA:"+i+": "+parts[p]);*/
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
	    	
			
			for(int p=0;p< parts.length;p++)
			{
		    	try(BufferedReader br1 = new BufferedReader(new InputStreamReader( new FileInputStream("LatainListNomArabe.txt"), "UTF8"))) 
				{
			       for(String line; (line = br1.readLine()) != null; ) 
			       {
			    	   
			    	   if(parts[p].equals("Ely") && parts[p+1].contains(line))
			    	   {
							
							parts[p] ="ElY";
			    	   }
			    	   
			    	   else if(parts[p].equals("wEly") && parts[p+1].contains(line))
			    	   {
							
						
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
	    	
			
			for(int p=0;p< parts.length;p++)
			{
				if(parts[p].equals("b") && parts[p+1]==" ")
				{
					parts[p+1]="";
					
		    
			        }
			}
		
				
		
			StringBuilder sb = new StringBuilder();
			for (String str : parts) {
				sb.append(str.replaceAll("(?m)(^ *| +(?= |$))", "").replaceAll("(?m)^$([\r\n]+?)(^$[\r\n]+?^)+", "$1"));
				sb.append(" ");
			}
			
			contenu = sb.toString();
			
			
		} catch (Exception e1) 
		{
			e1.printStackTrace();
		}
	
    //nombre
		
		try {

			String[] parts = contenu.split(" ");
			int i=0;

			String[] Nombre = { "HdA$", "vnA$", "vltTA$", "ArbETA$", "xmsTA$", "stTA$", "sbETA$", "vmnTA$", "tsETA$" };

			for (int p = 0; p < parts.length; p++) {
				
				if ((ConvNombre.isANumber(parts[p]) == true)) {
					parts[p] = ConvNombre.convnumber(parts[p]);
					
					//colorJTextPane.append(Color.green, parts[p]);
					/*i++;
		    		   System.out.println("nombre:"+i+": "+parts[p]);*/
				}
				/*else {
					colorJTextPane.append( Color.black,   MethodeRegles.LatinArabe(parts[p]));
				}*/

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
						
					//	colorJTextPane.append(Color.green, parts[p]);
						/*i++;
			    		   System.out.println("nombre:"+i+": "+parts[p]);*/

					}
					// cas : HdA$ n rAjl

					else if (parts[p].equals(Nombre[n])
							&& parts[p + 1].equals("n"))
					{
					nonNorm = parts[p] + parts[p + 1];

						String normalise = nonNorm;
						parts[p] = normalise;
						parts[p + 1] = "";
						/*i++;
			    		   System.out.println("nombre:"+i+": "+parts[p]);*/
					}
					// cas : HdA$ rAjl
					else if(parts[p].equals(Nombre[n]) && parts[p+1].equals("n") == false)

							{
						         parts[p] =parts[p]+"n";
						     	/*i++;
					    		   System.out.println("nombre:"+i+": "+parts[p]);*/
						
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
			//colorJTextPane.append(Color.black,contenu);

		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
      //regle pour h en p
       try {
			
			String[] parts = contenu.split(" ");
	    	int i=0;
			
			for(int p=0;p< parts.length;p++)
			{
		    	try(BufferedReader br1 = new BufferedReader(new InputStreamReader( new FileInputStream("latinhenp.txt"), "UTF8"))) 
				{
			       for(String line; (line = br1.readLine()) != null; ) 
			       {
			    	   if(parts[p].equals(line.substring(0,line.indexOf(" "))))
			    	   {
			    		   parts[p]=line.substring(line.indexOf(" ")+1);
			    		 /*  i++;
			    		   System.out.println("henP:"+i+": "+parts[p]);*/
			    	   }
			    	   
			    	 
			    	   else if ((parts[p].indexOf("w")==0||parts[p].indexOf("b")==0||parts[p].indexOf("l")==0||parts[p].indexOf("f")==0||parts[p].indexOf("k")==0)
			    			   && parts[p].substring(1).equals(line.substring(0,line.indexOf(" "))))
			    	   {
			    		   String ch=parts[p].substring(0,1);
			    		   parts[p]=ch+line.substring(line.indexOf(" ")+1);
			    		 /*  i++;
			    		   System.out.println("henP:"+i+": "+parts[p]);*/

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
	    	
			int i=0;
			for(int p=0;p< parts.length;p++)
			{
		    	try(BufferedReader br1 = new BufferedReader(new InputStreamReader( new FileInputStream("latinAenp.txt"), "UTF8"))) 
				{
			       for(String line; (line = br1.readLine()) != null; ) 
			       {
			    	   if(parts[p].equals(line.substring(0,line.indexOf(" "))))
			    	   {
			    		   parts[p]=line.substring(line.indexOf(" ")+1);
			    		  // i++;
			    		   //System.out.println("AenP:"+i+": "+parts[p]);

			    	   }
			    	   
			    	 
			    	   else if ((parts[p].indexOf("w")==0||parts[p].indexOf("b")==0||parts[p].indexOf("l")==0||parts[p].indexOf("f")==0||parts[p].indexOf("k")==0)
			    			   && parts[p].substring(1).equals(line.substring(0,line.indexOf(" "))))
			    	   {
			    		   String ch=parts[p].substring(0,1);
			    		   parts[p]=ch+line.substring(line.indexOf(" ")+1);

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
	    	int i=0;
			
			for(int p=0;p< parts.length;p++)
			{
		    	try(BufferedReader br1 = new BufferedReader(new InputStreamReader( new FileInputStream("latinyouAenY.txt"), "UTF8"))) 
				{
			       for(String line; (line = br1.readLine()) != null; ) 
			       {
			    	   if(parts[p].equals(line.substring(line.indexOf(" ")+1)))
			    	   {
			    		   parts[p]=line.substring(0,line.indexOf(" "));
			    		  i++;
			    		   System.out.println("AyenY:"+i+": "+parts[p]);
			    	   }
			    	   
			    
			    	   else if ((parts[p].indexOf("w")==0||parts[p].indexOf("b")==0||parts[p].indexOf("l")==0||parts[p].indexOf("f")==0||parts[p].indexOf("k")==0)
			    			   && parts[p].substring(1).equals(line.substring(line.indexOf(" ")+1)))
			    	   {
			    		   String ch=parts[p].substring(0,1);
			    		   parts[p]=ch+line.substring(0,line.indexOf(" "));
			    		  i++;
			    		   System.out.println("AyenY:"+i+": "+parts[p]);

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
	    	int i =0;
			
			for(int p=0;p< parts.length;p++)
			{
		    	try(BufferedReader br1 = new BufferedReader(new InputStreamReader( new FileInputStream("latainYeny.txt"), "UTF8"))) 
				{
			       for(String line; (line = br1.readLine()) != null; ) 
			       {
			    	   if(parts[p].equals(line.substring(line.indexOf(" ")+1)))
			    	   {
			    		   parts[p]=line.substring(0,line.indexOf(" "));
			    		/* i++;
			    		   System.out.println("Yeny:"+i+": "+parts[p]);*/
			    	   }
			    
			    	   else if ((parts[p].indexOf("w")==0||parts[p].indexOf("b")==0||parts[p].indexOf("l")==0||parts[p].indexOf("f")==0||parts[p].indexOf("k")==0)
			    			   && parts[p].substring(1).equals(line.substring(line.indexOf(" ")+1)))
			    	   {
			    		   String ch=parts[p].substring(0,1);
			    		   parts[p]=ch+line.substring(0,line.indexOf(" "));
			    		/* i++;
			    		   System.out.println("Yeny:"+i+": "+parts[p]);*/

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
	    	
			int i=0;
			for(int p=0;p< parts.length;p++)
			{
		    	try(BufferedReader br1 = new BufferedReader(new InputStreamReader( new FileInputStream("latain’en”.txt"), "UTF8"))) 
				{
			       for(String line; (line = br1.readLine()) != null; ) 
			       {
			    	   if(parts[p].equals(line.substring(line.indexOf(" ")+1)))
			    	   {
			    		   parts[p]=line.substring(0,line.indexOf(" "));
			    		/* i++;
			    		   System.out.println("Sens:"+i+": "+parts[p]);*/

			    	   }
			    	
			    	   else if ((parts[p].indexOf("w")==0||parts[p].indexOf("b")==0||parts[p].indexOf("l")==0||parts[p].indexOf("f")==0||parts[p].indexOf("k")==0)
			    			   && parts[p].substring(1).equals(line.substring(line.indexOf(" ")+1)))
			    	   {
			    		   String ch=parts[p].substring(0,1);
			    		   parts[p]=ch+line.substring(0,line.indexOf(" "));
			    		 /*i++;
			    		   System.out.println("Sens:"+i+": "+parts[p]);*/


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
	    	
			int i=0;
			for(int p=0;p< parts.length;p++)
			{
		    	try(BufferedReader br1 = new BufferedReader(new InputStreamReader( new FileInputStream("latain”en’.txt"), "UTF8"))) 
				{
			       for(String line; (line = br1.readLine()) != null; ) 
			       {
			    	   if(parts[p].equals(line.substring(line.indexOf(" ")+1)))
			    	   {
			    		   parts[p]=line.substring(0,line.indexOf(" "));
			    		/* i++;
			    		   System.out.println("senS:"+i+": "+parts[p]);*/
			    	   }
			    
			    	   else if ((parts[p].indexOf("w")==0||parts[p].indexOf("b")==0||parts[p].indexOf("l")==0||parts[p].indexOf("f")==0||parts[p].indexOf("k")==0)
			    			   && parts[p].substring(1).equals(line.substring(line.indexOf(" ")+1)))
			    	   {
			    		   String ch=parts[p].substring(0,1);
			    		   parts[p]=ch+line.substring(0,line.indexOf(" "));
			    		/* i++;
			    		   System.out.println("senS:"+i+": "+parts[p]);*/

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
	    	int i=0;
			
			for(int p=0;p< parts.length;p++)
			{
		    	try(BufferedReader br1 = new BufferedReader(new InputStreamReader( new FileInputStream("latainYenA.txt"), "UTF8"))) 
				{
			       for(String line; (line = br1.readLine()) != null; ) 
			       {
			    	   if(parts[p].equals(line.substring(line.indexOf(" ")+1)))
			    	   {
			    		   parts[p]=line.substring(0,line.indexOf(" "));
			    		/* i++;
			    		   System.out.println("Yen A:"+i+": "+parts[p]);*/
			    	   }
			    	
			    	   
			    	   else if ((parts[p].indexOf("w")==0||parts[p].indexOf("b")==0||parts[p].indexOf("l")==0||parts[p].indexOf("f")==0||parts[p].indexOf("k")==0)
			    			   && parts[p].substring(1).equals(line.substring(line.indexOf(" ")+1)))
			    	   {
			    		   String ch=parts[p].substring(0,1);
			    		   parts[p]=ch+line.substring(0,line.indexOf(" "));
			    		/* i++;
			    		   System.out.println("Yen A:"+i+": "+parts[p]);*/

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
				
				for(int p=0;p< parts.length;p++)
				{
			    	try(BufferedReader br1 = new BufferedReader(new InputStreamReader( new FileInputStream("latainexeption.txt"), "UTF8"))) 
					{
				       for(String line; (line = br1.readLine()) != null; ) 
				       {
				    	   if(parts[p].equals(line.substring(0,line.indexOf(" "))))
				    	   {
				    		   parts[p]=line.substring(line.indexOf(" ")+1);
				    	   }
				    	   
				    	   else if ((parts[p].indexOf("w")==0||parts[p].indexOf("b")==0||parts[p].indexOf("l")==0||parts[p].indexOf("f")==0||parts[p].indexOf("k")==0)
				    			   && parts[p].substring(1).equals(line.substring(0,line.indexOf(" "))))
				    	   {
				    		   String ch=parts[p].substring(0,1);
				    		   parts[p]=ch+line.substring(line.indexOf(" ")+1);

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

		

		

		//regle w et les hamza
		try {
			int i=0;
		char[] partsCatacter= contenu.toCharArray();
		for(int k=0;k<partsCatacter.length;k++)
		{
			if ( (k == 0 || partsCatacter[k-1] ==' ') && partsCatacter[k]=='w' && partsCatacter[k+1]== ' '&&partsCatacter[k+2]== 'w'&&partsCatacter[k+3]== ' ') 
		{
			partsCatacter[k]='\u0000';
			partsCatacter[k+1] ='\u0000';
			partsCatacter[k+3] ='\u0000';
			/* i++;
  		   System.out.println("w: "+i);*/

		}
			
			else if  ( (k == 0 || partsCatacter[k-1] ==' ') && partsCatacter[k]=='w' && partsCatacter[k+1]== ' ') 
			{
				partsCatacter[k+1] = '\u0000';
				 /*i++;
	    		   System.out.println("w: "+i);*/
			}
			else if ( (k == 0 || partsCatacter[k-1] =='w') && partsCatacter[k]=='w' && partsCatacter[k+1]== ' ') 
			{
				partsCatacter[k]=' ';
				partsCatacter[k+1] ='w';
				/* i++;
	    		   System.out.println("w: "+i);*/
			}
			
			
			
			else if(partsCatacter[k]=='|')
			{
				nonNorm = String.valueOf(partsCatacter[k]);
				norm = MethodeRegles.remp¬(nonNorm);
				partsCatacter[k] = norm.charAt(0);
				/*i++;
	    		   System.out.println("w: "+i);*/
			}
			else if(partsCatacter[k]=='<')
			{
				nonNorm=String.valueOf(partsCatacter[k]);
				norm=MethodeRegles.remp≈(nonNorm);
				partsCatacter[k] = norm.charAt(0);
				/*i++;
	    		   System.out.println("w: "+i);*/
				
			} else if(partsCatacter[k]=='>')
			{
				nonNorm=String.valueOf(partsCatacter[k]);

				norm=MethodeRegles.remp√(nonNorm);
				partsCatacter[k] = norm.charAt(0);
				/*i++;
	    		   System.out.println("w: "+i);*/
			}
			else if(partsCatacter[k]=='&')
			{
				nonNorm=String.valueOf(partsCatacter[k]);
				norm=MethodeRegles.rempƒ(nonNorm);
				partsCatacter[k] = norm.charAt(0);
			/*	i++;
	    		   System.out.println("w: "+i);*/
			}
			else if(partsCatacter[k]=='}')
			{
				nonNorm = String.valueOf(partsCatacter[k]);

				norm = MethodeRegles.remp∆(nonNorm);
				partsCatacter[k] = norm.charAt(0);
			/*	i++;
	    		   System.out.println("w: "+i);*/

			}
			else if(partsCatacter[k]=='\'')
			{
				partsCatacter[k] = '\u0000';
				/*i++;
	    		   System.out.println("w: "+i);*/
				
			}
			
			else if(partsCatacter[k]=='G')
			{
				nonNorm = String.valueOf(partsCatacter[k]);
				norm = MethodeRegles.rempG(nonNorm);
				partsCatacter[k] = norm.charAt(0);
				/*i++;
	    		   System.out.println("w: "+i);*/
				
			}
			
			
		}
		
		StringBuilder sb = new StringBuilder();
		for (char c : partsCatacter) {
			if(c != '\u0000') {
				sb.append(c);
			}
		}
		
		contenu = sb.toString();
	} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		
		
		
		/*colorJTextPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		colorJTextPane.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		scrollPane_1.setViewportView(colorJTextPane);*/
		
	
		textPane_1.setText(MethodeRegles.LatinArabe(contenu));
	
}
public class Controle implements ActionListener{
	
	public void actionPerformed (ActionEvent evt){
		
		
		new Thread ( new Hilo()).start() ;
	}
	
	
	
}

public class Hilo  implements Runnable {

	@Override
	public void run() {
	
		
		for (int i =0 ; i<100 ; i++)
		{
			progressBar.setValue(i);
			progressBar.repaint() ;
			
			try{
				
				Thread.sleep(50);
				
			}
			catch(Exception e)
			{
				
			}	
		}
		
				
		
	}
	
}

}
