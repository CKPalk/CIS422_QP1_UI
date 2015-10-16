package virtualpondgui;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import addressbook.Field;

/**
 * A JLabel + JTextField pair used to display and edit a contact field.
 * 
 * @author atleebrink
 *
 */
public class EntryFieldEditor extends JPanel {
	private static final long serialVersionUID = 1L;

	private JTextField field;
	
	public EntryFieldEditor(String name, String initialContent, Field contactField) {
		super(new FlowLayout(FlowLayout.CENTER));
		add(new JLabel(name));
		field = new JTextField(initialContent);
		Dimension d = field.getPreferredSize();
		// TODO: change width to fit container;
		// make sure the label is wide enough,
		// then size the textfield to take up available space or something.
		d.width = 100;
		field.setPreferredSize(d);
		field.setInputVerifier(new FieldVerifier(contactField));
		add(field);
	}
	
	public String getFieldValue() {
		return field.getText();
	}
	
	public class FieldVerifier extends InputVerifier {
		private Field contactField;
		
		public FieldVerifier(Field contactField) {
			this.contactField = contactField;
		}
		
		@Override
		public boolean verify(JComponent input) {
			JTextField textField = (JTextField)input;
			return contactField.validate(textField.getText());
		}
	}
}
