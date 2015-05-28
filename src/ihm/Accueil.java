package ihm;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.File;

import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
/**
 * Panel qui contient la page acceuil.
 * @author Samuel
 *
 */
public class Accueil extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton jbJouer;
	private JButton jbCJ;
	private JButton jbContact;
	private JButton jbQuitter;
	private BufferedImage background;
	public Accueil(Ihm e) {
		super();
		try {
			background = ImageIO.read(new File("Images\\backAc.jpg"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		jbCJ = new JButton("Comment Jouer ? ");
		jbJouer = new JButton("Jouer");
		jbContact = new JButton("Contact");
		jbQuitter = new JButton("Quitter");
		this.setLayout(new GridLayout(4,0,50,50));

		this.setBackground(Color.BLACK);
		this.add(jbJouer);
		this.add(jbContact);
		this.add(jbQuitter);
		this.add(jbCJ);
		jbJouer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				e.changerPage(1);
			}
		});

	}

}
