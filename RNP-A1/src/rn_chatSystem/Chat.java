package rn_chatSystem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
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
	private JTextArea messages;
	private JScrollPane jScrollPane1;
	private JLabel error;
	private JButton send;
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
			this.addWindowListener(new WindowAdapter() {
				public void windowClosed(WindowEvent evt) {
					System.out.println("this.windowClosed, event="+evt);
					client.send("BYE");
				}
			});
			{
				anmeldeBtn = new JButton();
				anmeldeBtn.setText("anmelden");
				anmeldeBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						if (chatNameText.getText().length() <= 20){
							System.out.println("anmeldeBtn.actionPerformed, event="+evt);
							anmeldeBtn.setEnabled(false);
							client = new ClientSocket(chatNameText.getText(), inst);
							send.setEnabled(true);
							error.setText("");
						} else {
							error.setText("ERROR chatname can only contain 20 letters");
						}
						
					}
				});
			}
			{
				chatNameText = new JTextField();
				chatNameText.setText("Test");
			}
			{
				error = new JLabel();
				error.setFont(new java.awt.Font("Segoe UI",1,12));
			}
			{
				member = new JTextArea();
				member.setEditable(false);
			}
			{
				message = new JTextField();
			}
			{
				jScrollPane1 = new JScrollPane();
				{
					messages = new JTextArea();
					jScrollPane1.setViewportView(getMessages());
					messages.setEditable(false);
				}
			}
			{
				send = new JButton();
				send.setText("send");
				send.setEnabled(false);
				
send.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent evt) {
		System.out.println("send.actionPerformed, event="+evt);
		String text = message.getText();
		client.send(text);
		message.setText("");
//		error.setText(text);
	}
});
			}
			thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
				.addContainerGap()
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(anmeldeBtn, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(chatNameText, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(thisLayout.createParallelGroup()
				    .addComponent(member, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
				    .addComponent(jScrollPane1, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE))
				.addGap(0, 18, Short.MAX_VALUE)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(send, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(message, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addComponent(error, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE));
			thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
				.addContainerGap()
				.addGroup(thisLayout.createParallelGroup()
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(error, 0, 360, Short.MAX_VALUE)
				        .addGap(0, 12, GroupLayout.PREFERRED_SIZE))
				    .addGroup(thisLayout.createSequentialGroup()
				        .addPreferredGap(error, message, LayoutStyle.ComponentPlacement.INDENT)
				        .addGroup(thisLayout.createParallelGroup()
				            .addGroup(thisLayout.createSequentialGroup()
				                .addGroup(thisLayout.createParallelGroup()
				                    .addComponent(message, GroupLayout.Alignment.LEADING, 0, 205, Short.MAX_VALUE)
				                    .addComponent(jScrollPane1, GroupLayout.Alignment.LEADING, 0, 205, Short.MAX_VALUE))
				                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				                .addGroup(thisLayout.createParallelGroup()
				                    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                        .addComponent(send, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
				                        .addGap(73))
				                    .addComponent(member, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)))
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addComponent(anmeldeBtn, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
				                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				                .addComponent(chatNameText, 0, 255, Short.MAX_VALUE))))));
			pack();
			setSize(400, 300);
		} catch (Exception e) {
		    error.setText("ERROR Gui Problem");
		}
	}
	
	public JTextArea getMember() {
		return member;
	}
	
	public JTextArea getMessages() {
		return messages;
	}
	
	
	public JLabel getError() {
		return error;
	}

}
