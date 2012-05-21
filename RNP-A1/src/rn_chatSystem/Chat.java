package rn_chatSystem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.ListModel;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class Chat extends javax.swing.JFrame {
	private JButton anmeldeBtn;
	private JTextField message;
	private JTextArea member;
	private JLabel error;
	private JButton send;
	private JTextArea messages;
	private JTextField chatNameText;
	private ClientSocket client = null;
	public static Chat inst;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				inst = new Chat();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public Chat() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			GroupLayout thisLayout = new GroupLayout((JComponent)getContentPane());
			getContentPane().setLayout(thisLayout);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				anmeldeBtn = new JButton();
				anmeldeBtn.setText("anmelden");
				anmeldeBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						System.out.println("anmeldeBtn.actionPerformed, event="+evt);
						anmeldeBtn.setEnabled(false);
						client = new ClientSocket(chatNameText.getText(), inst);
						
					}
				});
			}
			{
				chatNameText = new JTextField();
				chatNameText.setText("Test");
			}
			{
				error = new JLabel();
			}
			{
				member = new JTextArea();
			}
			{
				messages = new JTextArea();
				messages.setEditable(false);
			}
			{
				message = new JTextField();
			}
			{
				send = new JButton();
				send.setText("send");
				send.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						System.out.println("send.actionPerformed, event="+evt);
						String text = message.getText();
						client.send(message.getText());
						error.setText(text);
					}
				});
			}
			thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
				.addContainerGap()
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(anmeldeBtn, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(chatNameText, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(thisLayout.createParallelGroup()
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(messages, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 7, Short.MAX_VALUE))
				    .addComponent(member, GroupLayout.Alignment.LEADING, 0, 146, Short.MAX_VALUE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(message, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(send, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addComponent(error, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
				.addContainerGap());
			thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
				.addContainerGap(18, 18)
				.addGroup(thisLayout.createParallelGroup()
				    .addGroup(thisLayout.createSequentialGroup()
				        .addGroup(thisLayout.createParallelGroup()
				            .addComponent(message, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
				            .addComponent(messages, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE))
				        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				        .addGroup(thisLayout.createParallelGroup()
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addComponent(send, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
				                .addGap(0, 85, Short.MAX_VALUE))
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addPreferredGap(send, member, LayoutStyle.ComponentPlacement.INDENT)
				                .addComponent(member, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
				                .addGap(0, 0, Short.MAX_VALUE))))
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addGroup(thisLayout.createParallelGroup()
				            .addComponent(anmeldeBtn, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addComponent(error, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
				                .addGap(57)))
				        .addGap(21)
				        .addComponent(chatNameText, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 0, Short.MAX_VALUE)))
				.addContainerGap(22, 22));
			pack();
			setSize(400, 300);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	public JTextArea getMember() {
		return member;
	}
	
	public JTextArea getMessages() {
		return messages;
	}

}
