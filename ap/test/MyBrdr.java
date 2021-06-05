 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

package ap.test; 

import java.awt.Color; 
import java.awt.GridLayout; 
import javax.swing.*; 
import javax.swing.border.*; 
 
public class MyBrdr { 
public static void main(String[] args) { 
FrameBordered fb = new FrameBordered(); 
fb.setVisible(true); 
} 
} 
 
class FrameBordered extends JFrame{ 
JLabel l1; 
JLabel l2; 
JLabel l3; 
JLabel l4; 
JLabel l5; 
JLabel l6; 
JLabel l7; 
JLabel l8; 
JPanel p; 
public FrameBordered(){ 
setTitle("??????? ?????"); 
setDefaultCloseOperation(EXIT_ON_CLOSE); 
 
 
p = new JPanel(); 
p.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15)); 
p.setLayout(new GridLayout(4,2,25,25)); 
 
l1 = new JLabel("???????? ?????(1)"); 
l1.setBorder(BorderFactory.createCompoundBorder( 
BorderFactory.createBevelBorder(BevelBorder.RAISED), 
BorderFactory.createEmptyBorder(25, 25, 25, 25))); 
 
l2 = new JLabel("???????? ?????(2)"); 
l2.setBorder(BorderFactory.createCompoundBorder( 
BorderFactory.createBevelBorder(BevelBorder.LOWERED), 
BorderFactory.createEmptyBorder(25, 25, 25, 25))); 
 
l3 = new JLabel("?????? ????? (1)"); 
l3.setBorder(BorderFactory.createCompoundBorder( 
BorderFactory.createEtchedBorder(EtchedBorder.RAISED), 
BorderFactory.createEmptyBorder(25, 25, 25, 25))); 
 
l4 = new JLabel("?????? ????? (2)"); 
l4.setBorder(BorderFactory.createCompoundBorder( 
BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), 
BorderFactory.createEmptyBorder(25, 25, 25, 25))); 
 
l5 = new JLabel("????? ????? "); 
l5.setBorder(BorderFactory.createCompoundBorder( 
BorderFactory.createLineBorder(Color.gray, 2), 
BorderFactory.createEmptyBorder(25, 25, 25, 25))); 
 
l6 = new JLabel("????? c ????? "); 
l6.setBorder(BorderFactory.createCompoundBorder( 
BorderFactory.createMatteBorder(5, 5, 5, 5, new ImageIcon("D:/Tests/bord.jpg")), 
BorderFactory.createEmptyBorder(20, 20, 20, 20))); 
 
l7 = new JLabel("????? c ??????????"); 
l7.setBorder(BorderFactory.createCompoundBorder( 
BorderFactory.createTitledBorder( 
BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), 
"title"), 
BorderFactory.createEmptyBorder(30, 30, 30, 30))); 
 
l8 = new JLabel("??????????? ????"); 
l8.setBorder(BorderFactory.createCompoundBorder( 
new LineBorder(Color.BLUE, 4, true), 
BorderFactory.createEmptyBorder(20, 20, 20, 20))); 
 
p.add(l1); 
p.add(l2); 
p.add(l3); 
p.add(l4); 
p.add(l5); 
p.add(l6); 
p.add(l7); 
p.add(l8); 
add(p); 
pack(); 
} 
} 
// Revision : 11.08.2017 17:33:39 
// Revision : 10.09.2018 12:49:16 
