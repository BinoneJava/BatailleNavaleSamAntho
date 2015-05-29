package ihm;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JpContact extends JPanel {

	private JPanel center;
	private GridBagConstraints c;
	private JButton val;
	private JButton retour;
	public JpContact(Ihm fenetre) {
		this.setLayout(new BorderLayout());
		retour = new JButton("Retour");
		center = new JPanel(new GridBagLayout());
		c = new GridBagConstraints();
		JComboBox<String> jcbox = new JComboBox<String>();
		val = new JButton("Valider");
		this.add(new JLabel("Voulez vous visiter notre site pour ainsi nous contacter?"), BorderLayout.NORTH);
		jcbox.addItem("Version Horsligne");
		jcbox.addItem("En ligne");
		center.add(jcbox,c);
		center.add(val,c);
		center.add(retour,c);
		
		this.add(center, BorderLayout.CENTER);
		
		val.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (jcbox.getSelectedIndex()) {
				case 0:
					URI uri = URI.create("http://sabbaye.noip.me/Formulaire.html");
					try {
						Desktop.getDesktop().browse(uri);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					break;
				case 1:URI uri2 = URI.create("site/index.html");
				try {
					Desktop.getDesktop().browse(uri2);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
					
				default:
					break;
				}
				
			}
		});
		
		retour.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fenetre.changerPage(0);
			}
		});
		
		
	}

}
