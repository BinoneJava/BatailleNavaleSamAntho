package ihm;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Panel qui contient la page acceuil.
 * 
 * @author Samuel
 *
 */
public class Accueil extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton jbJouer;
	private JButton jbContact;
	private JButton jbQuitter;
	private Image bg;

	public Accueil(Ihm e) {
		bg = new ImageIcon("Images\\backAc.jpg").getImage();

		jbJouer = new JButton("Jouer");
		jbContact = new JButton("Contact");
		jbQuitter = new JButton("Quitter");
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		this.add(jbJouer, c);
		c.gridy = 2;
		this.add(jbContact, c);

		c.gridy = 4;
		this.add(jbQuitter, c);

		jbJouer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				e.changerPage(1);
			}
		});
		jbContact.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent y) {
				e.changerPage(3);
				
			}
		});
		jbQuitter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent r) {
			}
		});

	}

	public void paint(Graphics g) {
		g.drawImage(bg, 0, 0, null);
	}
}
