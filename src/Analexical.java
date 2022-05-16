import java.awt.Color;
import java.awt.EventQueue;


import javax.swing.JFrame;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;

public class Analexical {

	private JFrame frame;
	private JTextField fileName;
	private JButton btnNewButton_1;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Analexical window = new Analexical();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	public static boolean majuscule(String ch) {
		{int i = 0 ;
		while (i<ch.length() && Character.isUpperCase(ch.charAt(i))==true){
			{i++;}
		}
		if (i == ch.length()) {
			return true;
		}
		else {return false;}
		}
				
	}
	
	
	public static void Tables_des_symboles(Vector <Integer> Cst,Vector <String> ID, String [][]Tables_des_symboles)
	{
	int p=0;
		int q=0;
		int nbr_ID=ID.size();
		int nbr_CST=Cst.size();
		int size=10+nbr_ID+9+nbr_CST; 

		//String[][]Tables_des_symboles =new String [size][2];
		Tables_des_symboles[0][0]="BEGIN";
		Tables_des_symboles[1][0]="PROGRAM";
		Tables_des_symboles[2][0]="IF";
		Tables_des_symboles[3][0]="THEN";
		Tables_des_symboles[4][0]="ELSE";
		Tables_des_symboles[5][0]="INT";
		Tables_des_symboles[6][0]="END";
		Tables_des_symboles[7][0]="FUNCTION";
		Tables_des_symboles[8][0]="VAR";
		Tables_des_symboles[9][0]="RETURN";
		
		for(p=0;p<10;p++)
		{ Tables_des_symboles[p][1]="Mot clé";}

		Tables_des_symboles[10][0]=":";
		Tables_des_symboles[11][0]="(";
		Tables_des_symboles[12][0]=")";
		Tables_des_symboles[13][0]=",";
		Tables_des_symboles[14][0]=";";
		Tables_des_symboles[15][0]=":=";
		Tables_des_symboles[16][0]="+";
		Tables_des_symboles[17][0]="-";
		Tables_des_symboles[18][0]="=";
		
		for(p=10;p<15;p++)
		{ Tables_des_symboles[p][1]="Séparateur";}
		
		for(p=15;p<19;p++)
		{ Tables_des_symboles[p][1]="Opérateur";}
		
		q=0; 	
		while(q<nbr_ID)
		{ Tables_des_symboles[p][0]=ID.elementAt(q);
		  Tables_des_symboles[p][1]="Identificateur";
		  q++;
		  p++;
		}
		q=0; 
		while(q<nbr_CST && q<size)
		{ Tables_des_symboles[p][0]=Integer.toString(Cst.elementAt(q));
		  Tables_des_symboles[p][1]="Constante";
		  q++;
		  p++;
		  
		}
	
		}
	
    /***********************Séparateur /Opérateur******************************/
	
static int colone_SepOp(char[] Tab ,char x) 
{ int i=0 ; int j=0 ;
while(i<Tab.length && Tab[j] != x )
{ i++;j++;}
return i ;}
       /*********************** Chiffres******************************/

static int colone_Chiffres(char x)
{
int c =Character.getNumericValue(x);
if (c<=9 && c>=0)
{return c ;}
else
{return 10 ;}
}

      /*********************** ID/MotCle ******************************/

static int colone_ID_MotCle(char[] Tab ,char x) 
{ 
int i=0  ;
while(i<26 && Tab[i] != Character.toLowerCase(x) ) //si c'est une lettre,on la rend miniscule et on compare
{ i++;}
if (i>25) {
	while(i<37 && Tab[i] != Character.toLowerCase(x)) {
		i++;
	}
}


return i ;} 

/*********************** Analyseur Lexical******************************/

public String[] lexicalanalyseur (String ch,char[] OpSep,int Mat_Transition_OpSep[][],Vector <Integer> Cst,char[] Id_MotClep, Vector <String> ID,Etat Mat_Transition_ID_MotCle[][], int Mat_Transition_Constantes[][], Etat S0) {
   String type= new String();
           int t=0; int k=0; int EC=0 ;



while ( (k < ch.length()) && (Mat_Transition_OpSep[EC][colone_SepOp(OpSep,ch.charAt(k))] != -1) && (t<10)  )
{ 
EC = Mat_Transition_OpSep[EC][colone_SepOp(OpSep,ch.charAt(k))];
k++ ; //on avance dans le mot
t++ ; //on incrémente la taille 

}
if ( (k==ch.length()) && (EC==1 || EC==2) ) //fin de chaine atteinte ET etats final
{
if( (ch.length()==2) || (colone_SepOp(OpSep,ch.charAt(0))>=5))
{type="Opérateur";}
else
{type="Séparateur";}
}else
{       t=0;  k=0;  EC=0 ;


while ( (k < ch.length()) && (Mat_Transition_Constantes[EC][colone_Chiffres(ch.charAt(k))] != -1) && (t<8)  )
{ 
EC = Mat_Transition_Constantes[EC][colone_Chiffres(ch.charAt(k))];
k++ ; //on avance dans le mot
t++ ; //on incrémente la taille 

}
if ( (k==ch.length()) && (EC==1) && (t<8) ) //fin de chaine atteinte ET etats final et taille <8
{if (Cst.contains(Integer.parseInt(ch)) == false)
{Cst.add(Integer.parseInt(ch));}
type="constante";
}else
{
t=0;  k=0; 

Etat Ec =  S0 ;



while ( (k < ch.length()) && (Mat_Transition_ID_MotCle[Ec.n][colone_ID_MotCle(Id_MotClep,ch.charAt(k))].n != -1) && (t<10)  )
{
Ec = Mat_Transition_ID_MotCle[Ec.n][colone_ID_MotCle(Id_MotClep,ch.charAt(k))];
k++ ; //on avance dans le mot
t++ ; //on incrémente la taille 

}

if ( (k==ch.length()) && (t<10) )//fin de chaine atteinte ET etats final
{ 
if(Ec.EF == true) //ET etats final
{
if (Ec.EF_Mot_Clep == true && majuscule(ch)==true)
	{type="Mot Cle";}
else{if (ID.contains(ch) == false)
   {ID.add(ch);}
	type="Identificateur";}
}
} 
else { type="erreur";}	
}

}
String[] results = new String[] {ch, type};
return results; 
}

	
	public Analexical() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 830);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 1000, 830);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		fileName = new JTextField();
		fileName.setBounds(40, 76, 673, 40);
		panel.add(fileName);
		fileName.setColumns(10);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 187, 264, 282);
		panel.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(40, 509, 264, 285);
		panel.add(scrollPane_2);
		
		JTextArea textArea_1 = new JTextArea();
		scrollPane_2.setViewportView(textArea_1);
		textArea_1.setLineWrap(true);
		textArea_1.setWrapStyleWord(true);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(338, 187, 564, 581);
		panel.add(scrollPane_1);
		
		JTextArea textArea_2 = new JTextArea();
		scrollPane_1.setViewportView(textArea_2);
		textArea_2.setLineWrap(true);
		textArea_2.setWrapStyleWord(true);
		
		
		
		
		
char[] OpSep = new char[] {':','(',')',',',';','=','+','-'} ; //vecteur contenant les opérateurs et les séparateurs
		
		int Mat_Transition_OpSep[][] = {{2,1,1,1,1,1,1,1,-1}, //Matrice de transition de séparateurs opérateurs
		                            {-1,-1,-1,-1,-1,-1,-1,-1,-1},
		                            {-1,-1,-1,-1,-1,1,-1,-1,-1}} ;
		

	            Vector <Integer> Cst = new Vector<>(); //pou stocker toutes les constantes trouvé dans le programme
		int Mat_Transition_Constantes[][] = {{1,1,1,1,1,1,1,1,1,1,-1}, //Matrice de transition des chiffres
		                                   {1,1,1,1,1,1,1,1,1,1,-1}};	                                   
		
		
	           
	char[] Id_MotClep = new char[] {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','0','1','2','3','4','5','6','7','8','9','_'};
	    Vector <String> ID = new Vector<>(); //pou stocker toutes les identificateurs trouvé dans le programme
	    Etat Mat_Transition_ID_MotCle[][] = new Etat[42][38] ;
		Etat S1 =new Etat(1,true,false);  //l'etat S1
		Etat S2 =new Etat(2,false,false);
		Etat S7 =new Etat(1,true,true);
		Etat Sphi =new Etat(-1,false,false);
	            Etat S0 =new Etat(0,false,false); // S0
	//remplissage de matrice
		int q ; int p ;
		for( p = 0 ; p<42 ; p++ ) // 
		 {for( q = 0 ; q<38 ; q++ )
		  { Mat_Transition_ID_MotCle[p][q] = S1 ;
		  }
		 } 
		q = 36; // la colone du tiret "_"
		for( p = 1 ; p<42 ; p++ )
		{ Mat_Transition_ID_MotCle[p][36] = S2 ;  // tout les etats atteint S2 à travers le tiret "_"(colone 36) sauf S0 et S2
		  Mat_Transition_ID_MotCle[p][37] =Sphi ; //37 singnifie autre que (lettre,chiffre,et "_")
		}
		
		 
		  Mat_Transition_ID_MotCle[2][36] = Sphi ; //pas de tirets consecutifs 
		  
		  p=0;
		  for( q=26 ; q<37 ; q++ )
			{ 
			  Mat_Transition_ID_MotCle[0][q] =Sphi ; //S0  chiffres ou S0 tiret ou S0 autre est un etat bloqué
			}
		  
		Mat_Transition_ID_MotCle[0][36] = Sphi ;//un mot ne peut pas commencer par un tiret "_",[S0, tiret]=Sphi 		  
		//S0 avec les premieres lettres de chaque mot clé
		Mat_Transition_ID_MotCle[0][1]  =new Etat(3,true,false) ; //S0 lettre b(ou B)(la colone 1) mène à S3 
	    Mat_Transition_ID_MotCle[0][21] =new Etat(8,true,false) ; //S0 lettre v(ou V)(la colone 21) mène à S8
	    Mat_Transition_ID_MotCle[0][8]  =new Etat(11,true,false);
	    Mat_Transition_ID_MotCle[0][5]  =new Etat(15,true,false);
	    Mat_Transition_ID_MotCle[0][15] =new Etat(22,true,false);
		Mat_Transition_ID_MotCle[0][19] =new Etat(29,true,false);
	    Mat_Transition_ID_MotCle[0][17] =new Etat(32,true,false);
	    Mat_Transition_ID_MotCle[0][4]  =new Etat(37,true,false);
	    
	    Mat_Transition_ID_MotCle[3][4] = new Etat(4,true,false); //S3 lettre e(ou E)(la colone 4) mène à S4
	    Mat_Transition_ID_MotCle[4][6] = new Etat(5,true,false);
	    Mat_Transition_ID_MotCle[5][8] = new Etat(6,true,false);
	    Mat_Transition_ID_MotCle[6][13]= S7; 
	    Mat_Transition_ID_MotCle[8][0] = new Etat(9,true,false);
	    Mat_Transition_ID_MotCle[9][17]= new Etat(10,true,true); //Etat final d'un mot clé
	    Mat_Transition_ID_MotCle[11][5]= new Etat(12,true,true); //Etat final d'un mot clé
	    Mat_Transition_ID_MotCle[11][13]=new Etat(13,true,false);
	    Mat_Transition_ID_MotCle[13][19]=new Etat(14,true,true); //Etat final d'un mot clé 
	    Mat_Transition_ID_MotCle[15][20]=new Etat(16,true,false);
	    Mat_Transition_ID_MotCle[16][13]=new Etat(17,true,false);
	    Mat_Transition_ID_MotCle[17][2] =new Etat(18,true,false);
	    Mat_Transition_ID_MotCle[18][19]=new Etat(19,true,false);
	    Mat_Transition_ID_MotCle[19][8] =new Etat(20,true,false);
	    Mat_Transition_ID_MotCle[20][14]=new Etat(21,true,false);
	    Mat_Transition_ID_MotCle[21][13]= S7; 
	    Mat_Transition_ID_MotCle[22][17]=new Etat(23,true,false);
	    Mat_Transition_ID_MotCle[23][14]=new Etat(24,true,false);
	    Mat_Transition_ID_MotCle[24][6] =new Etat(25,true,false);
	    Mat_Transition_ID_MotCle[25][17]=new Etat(26,true,false);
	    Mat_Transition_ID_MotCle[26][0] =new Etat(27,true,false);
	    Mat_Transition_ID_MotCle[27][12]=new Etat(28,true,true); //Etat final d'un mot clé
	    Mat_Transition_ID_MotCle[29][7] =new Etat(30,true,false);
	    Mat_Transition_ID_MotCle[30][4] =new Etat(31,true,false);
	    Mat_Transition_ID_MotCle[31][13]=S7;
	    Mat_Transition_ID_MotCle[32][4] =new Etat(33,true,false);
	    Mat_Transition_ID_MotCle[33][19]=new Etat(34,true,false);
	    Mat_Transition_ID_MotCle[34][20]=new Etat(35,true,false);
	    Mat_Transition_ID_MotCle[35][17]=new Etat(36,true,false);
	    Mat_Transition_ID_MotCle[36][13]= S7; 
	    Mat_Transition_ID_MotCle[37][11]=new Etat(38,true,false);
	    Mat_Transition_ID_MotCle[38][18]=new Etat(39,true,false);
	    Mat_Transition_ID_MotCle[39][4] =new Etat(40,true,true); //Etat final d'un mot clé
	    Mat_Transition_ID_MotCle[37][13]=new Etat(41,true,false);
	    Mat_Transition_ID_MotCle[41][3] =new Etat(42,true,true);// Etat final d'un mot clé
		
	
		
		JButton btnNewButton = new JButton("Attach");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(null);
				File f = chooser.getSelectedFile();
				String filename = f.getAbsolutePath();
				fileName.setText(filename);
				
			}
		});
		
		btnNewButton.setBounds(736, 72, 117, 51);
		btnNewButton.setBackground(Color.DARK_GRAY);
		btnNewButton.setOpaque(true);
		btnNewButton.setBorderPainted(false);
		panel.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Analyser");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String path = fileName.getText();
				File f = new File(path);
				int pos=1 ;

				try {
					Scanner scan = new Scanner(f);
					while(scan.hasNext()) {
						String word = scan.next();
						String[] splits = word.split("(?<=[-+*()=:,;])|(?=[-+*()=:,;])");
						
						
						int i=0;
						while(i < splits.length) {
						String wordf = splits[i] ;	
						if (lexicalanalyseur(wordf,OpSep,Mat_Transition_OpSep,Cst, Id_MotClep, ID, Mat_Transition_ID_MotCle, Mat_Transition_Constantes,  S0)[1] == "erreur") {
						textArea_1.append("Index :" + "[" + pos + "]" + " " +  lexicalanalyseur (wordf,OpSep,Mat_Transition_OpSep,Cst, Id_MotClep, ID, Mat_Transition_ID_MotCle, Mat_Transition_Constantes,S0) 
		    [0] + " : " + lexicalanalyseur(wordf,OpSep,Mat_Transition_OpSep,Cst, Id_MotClep, ID, Mat_Transition_ID_MotCle, Mat_Transition_Constantes,  S0)[1] + "\n");
						i++;
						pos++; 
						} 
						else {
							textArea.append("Index :" + "[" + pos + "]" + " " +  lexicalanalyseur (wordf,OpSep,Mat_Transition_OpSep,Cst, Id_MotClep, ID, Mat_Transition_ID_MotCle, Mat_Transition_Constantes,S0) 
						    [0] + " : " + lexicalanalyseur(wordf,OpSep,Mat_Transition_OpSep,Cst, Id_MotClep, ID, Mat_Transition_ID_MotCle, Mat_Transition_Constantes,  S0)[1] + "\n");
							i++;
							pos++;
						}
						}
						
						
					}
					int nbr_ID=ID.size();
					int nbr_CST=Cst.size();
					int size=10+nbr_ID+9+nbr_CST; 
					String sizee = Integer.toString(size);

					 String[][]Tables_des_symbole =new String [size][2];
					
					Tables_des_symboles( Cst, ID, Tables_des_symbole);
			
					
					textArea_2.append("____________________________________________"+"\n"+"|             MOT               |           TYPE                |"+"\n"+"|____________________|____________________|\n");

					for(int p = 0 ; p<size ; p++ ) // 
					{
					textArea_2.append("|"+ Tables_des_symbole[p][0] + "                               "+Tables_des_symbole[p][1]+"\n") ;
					
				
					
						
						
					}
					
					
				} 
				
				catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}});
		btnNewButton_1.setBounds(736, 135, 117, 47);
		btnNewButton_1.setBackground(Color.DARK_GRAY);
		btnNewButton_1.setOpaque(true);
		btnNewButton_1.setBorderPainted(false);
		panel.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("exAn");
		lblNewLabel.setFont(new Font("AppleGothic", Font.PLAIN, 16));
		lblNewLabel.setIcon(new ImageIcon("/Users/marouakhemissi/Downloads/Daco_5278182.png"));
		lblNewLabel.setBounds(378, 17, 86, 58);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Symboles :");
		lblNewLabel_1.setBounds(40, 159, 91, 16);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Erreurs :");
		lblNewLabel_2.setBounds(40, 481, 61, 16);
		panel.add(lblNewLabel_2);
		
		
		
		
		
		/*JTextArea textArea3 = new JTextArea();
		//scrollPane3.setViewportView(textArea3);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);*/
		
		
		
		JLabel lblNewLabel_3 = new JLabel("Table des Symboles :");
		lblNewLabel_3.setBounds(338, 159, 147, 16);
		panel.add(lblNewLabel_3);
		
		
		
		
		
		
		
	}
}