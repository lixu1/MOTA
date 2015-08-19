import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.DataOutputStream;
import java.io.FileOutputStream;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

//�浵�ļ���ʽ��
//�˵�4�����ԣ�¥����
//realMap����ֵ

public class saveRecord extends JFrame {
	FileOutputStream fout;
	DataOutputStream dfout;

	public saveRecord() {
		super("�浵");
		Container c = getContentPane();
		c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));

		// �浵�ĸ���ѡ��
		final JRadioButton[] choose = { new JRadioButton("����1�ŵ�"),
				new JRadioButton("����2�ŵ�"), new JRadioButton("����3�ŵ�"),
				new JRadioButton("����4�ŵ�"), new JRadioButton("����5�ŵ�"),
				new JRadioButton("����6�ŵ�"), new JRadioButton("����7�ŵ�"),
				new JRadioButton("����8�ŵ�"), new JRadioButton("����9�ŵ�"),
				new JRadioButton("����10�ŵ�"), };
		ButtonGroup rg = new ButtonGroup();
		for (int i = 0; i < choose.length; i++) {
			c.add(choose[i]);
			rg.add(choose[i]);
		}
		choose[0].setSelected(true);
		for (int i = 1; i < 10; i++)
			choose[i].setSelected(false);

		// ȷ����ť
		JButton confirmperson = new JButton("ȷ��");

		// ȷ����ť�¼�����
		confirmperson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// ����һ�ŵ�
					if (choose[0].isSelected()) {
						fout = new FileOutputStream("record/record1.txt");
						dfout = new DataOutputStream(fout);
						writeIntoRecord();
						JOptionPane.showMessageDialog(null, "���״̬�Ѿ��ɹ�����1�ŵ���\n"
								+ "���´�ֱ�Ӵ�1�ŵ�\n" + "������Ϸ");
					}

					// ������ŵ�
					else if (choose[1].isSelected()) {
						fout = new FileOutputStream("record/record2.txt");
						dfout = new DataOutputStream(fout);
						writeIntoRecord();
						JOptionPane.showMessageDialog(null, "���״̬�Ѿ��ɹ�����2�ŵ���\n"
								+ "���´�ֱ�Ӵ�2�ŵ�\n" + "������Ϸ");
					}

					// �������ŵ�
					else if (choose[2].isSelected()) {
						fout = new FileOutputStream("record/record3.txt");
						dfout = new DataOutputStream(fout);
						writeIntoRecord();
						JOptionPane.showMessageDialog(null, "���״̬�Ѿ��ɹ�����3�ŵ���\n"
								+ "���´�ֱ�Ӵ�3�ŵ�\n" + "������Ϸ");
					}

					// ����4�ŵ�
					else if (choose[3].isSelected()) {
						fout = new FileOutputStream("record/record4.txt");
						dfout = new DataOutputStream(fout);
						writeIntoRecord();
						JOptionPane.showMessageDialog(null, "���״̬�Ѿ��ɹ�����4�ŵ���\n"
								+ "���´�ֱ�Ӵ�4�ŵ�\n" + "������Ϸ");
					}

					// ����5�ŵ�
					else if (choose[4].isSelected()) {
						fout = new FileOutputStream("record/record5.txt");
						dfout = new DataOutputStream(fout);
						writeIntoRecord();
						JOptionPane.showMessageDialog(null, "���״̬�Ѿ��ɹ�����5�ŵ���\n"
								+ "���´�ֱ�Ӵ�5�ŵ�\n" + "������Ϸ");
					}

					// ����6�ŵ�
					else if (choose[5].isSelected()) {
						fout = new FileOutputStream("record/record6.txt");
						dfout = new DataOutputStream(fout);
						writeIntoRecord();
						JOptionPane.showMessageDialog(null, "���״̬�Ѿ��ɹ�����6�ŵ���\n"
								+ "���´�ֱ�Ӵ�6�ŵ�\n" + "������Ϸ");
					}

					// ����7�ŵ�
					else if (choose[6].isSelected()) {
						fout = new FileOutputStream("record/record7.txt");
						dfout = new DataOutputStream(fout);
						writeIntoRecord();
						JOptionPane.showMessageDialog(null, "���״̬�Ѿ��ɹ�����7�ŵ���\n"
								+ "���´�ֱ�Ӵ�7�ŵ�\n" + "������Ϸ");
					}

					// ����8�ŵ�
					else if (choose[7].isSelected()) {
						fout = new FileOutputStream("record/record8.txt");
						dfout = new DataOutputStream(fout);
						writeIntoRecord();
						JOptionPane.showMessageDialog(null, "���״̬�Ѿ��ɹ�����8�ŵ���\n"
								+ "���´�ֱ�Ӵ�8�ŵ�\n" + "������Ϸ");
					}

					// ����9�ŵ�
					else if (choose[8].isSelected()) {
						fout = new FileOutputStream("record/record9.txt");
						dfout = new DataOutputStream(fout);
						writeIntoRecord();
						JOptionPane.showMessageDialog(null, "���״̬�Ѿ��ɹ�����9�ŵ���\n"
								+ "���´�ֱ�Ӵ�9�ŵ�\n" + "������Ϸ");
					}

					// ����10�ŵ�
					else if (choose[9].isSelected()) {
						fout = new FileOutputStream("record/record10.txt");
						dfout = new DataOutputStream(fout);
						writeIntoRecord();
						JOptionPane.showMessageDialog(null, "���״̬�Ѿ��ɹ�����10�ŵ���\n"
								+ "���´�ֱ�Ӵ�10�ŵ�\n" + "������Ϸ");
					}
				}

				// �����쳣 ,�浵ʧ��
				catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "�浵ʧ��\n"
							+ "��������������ٴγ��Դ浵");
					System.err.println("�����쳣:" + e1);
					e1.printStackTrace();
				}
			}
		});
		c.add(confirmperson);
	}

	// �Ѵ浵д���ļ���¼
	void writeIntoRecord() {
		try {
			
			//д���˵Ĺ���������������HP����Ǯ����¥����
			dfout.writeInt(Main.person[1]);
			dfout.writeInt(Main.person[2]);
			dfout.writeInt(Main.person[3]);
			dfout.writeInt(Main.person[4]);
			dfout.writeInt(Main.person[5]);
			dfout.writeInt(Main.person[6]);
			dfout.writeInt(Main.person[7]);
			
			dfout.writeInt(Main.stairs);
			
			//���˵��������Ĳ��ͼд���ļ�
			for (int i = 1; i < 7; i++) {
				for (int j = 0; j < 100; j++)
					dfout.writeInt(Main.realMap[i][j]);
			}
			dfout.flush();
			dfout.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "�浵ʧ��\n" + "��������������ٴγ��Դ浵");
			System.err.println("�����쳣:" + e);
			e.printStackTrace();
		}
	}

}
