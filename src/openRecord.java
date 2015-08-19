import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

//�浵�ļ���ʽ��
//�˵�4�����ԣ�¥����
//realMap����ֵ

public class openRecord extends JFrame {
	FileInputStream fin;
	DataInputStream dfin;

	public openRecord() {
		super("�򿪴浵");
		Container c = getContentPane();
		c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));

		// ���ĵ�ѡ��
		final JRadioButton[] choose = { new JRadioButton("��1�ŵ�"),
				new JRadioButton("��2�ŵ�"), new JRadioButton("��3�ŵ�"),
				new JRadioButton("��4�ŵ�"), new JRadioButton("��5�ŵ�"),
				new JRadioButton("��6�ŵ�"), new JRadioButton("��7�ŵ�"),
				new JRadioButton("��8�ŵ�"), new JRadioButton("��9�ŵ�"),
				new JRadioButton("��10�ŵ�"), };
		ButtonGroup rg = new ButtonGroup();
		for (int i = 0; i < choose.length; i++) {
			c.add(choose[i]);
			rg.add(choose[i]);
		}
		choose[0].setSelected(true);
		for (int i = 1; i < 10; i++)
			choose[i].setSelected(false);

		// ȷ����ť���¼�����
		JButton confirmperson = new JButton("ȷ��");
		confirmperson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// ��һ�ŵ�
					if (choose[0].isSelected()) {
						fin = new FileInputStream("record/record1.txt");
						dfin = new DataInputStream(fin);
						recordIntoMap();
						JOptionPane.showMessageDialog(null, "�ɹ���1�ŵ�\n"
								+ "���״̬�Ѿ��ɹ�����1�ŵ��������״̬��\n" + "�������Ϸ\n");
						Main.tip.setText("�ɹ���1�ŵ�");
					}

					// �򿪶��ŵ�
					else if (choose[1].isSelected()) {
						fin = new FileInputStream("record/record2.txt");
						dfin = new DataInputStream(fin);
						recordIntoMap();
						JOptionPane.showMessageDialog(null, "�ɹ���2�ŵ�\n"
								+ "���״̬�Ѿ��ɹ�����2�ŵ��������״̬��\n" + "�������Ϸ\n");
						Main.tip.setText("�ɹ���2�ŵ�");
					}

					// �����ŵ�
					else if (choose[2].isSelected()) {
						fin = new FileInputStream("record/record3.txt");
						dfin = new DataInputStream(fin);
						recordIntoMap();
						JOptionPane.showMessageDialog(null, "�ɹ���3�ŵ�\n"
								+ "���״̬�Ѿ��ɹ�����3�ŵ��������״̬��\n" + "�������Ϸ\n");
						Main.tip.setText("�ɹ���3�ŵ�");
					}

					// ��4�ŵ�
					else if (choose[3].isSelected()) {
						fin = new FileInputStream("record/record4.txt");
						dfin = new DataInputStream(fin);
						recordIntoMap();
						JOptionPane.showMessageDialog(null, "�ɹ���4�ŵ�\n"
								+ "���״̬�Ѿ��ɹ�����4�ŵ��������״̬��\n" + "�������Ϸ\n");
						Main.tip.setText("�ɹ���4�ŵ�");
					}

					// ��5�ŵ�
					else if (choose[4].isSelected()) {
						fin = new FileInputStream("record/record5.txt");
						dfin = new DataInputStream(fin);
						recordIntoMap();
						JOptionPane.showMessageDialog(null, "�ɹ���5�ŵ�\n"
								+ "���״̬�Ѿ��ɹ�����5�ŵ��������״̬��\n" + "�������Ϸ\n");
						Main.tip.setText("�ɹ���5�ŵ�");
					}

					// ��6�ŵ�
					else if (choose[5].isSelected()) {
						fin = new FileInputStream("record/record6.txt");
						dfin = new DataInputStream(fin);
						recordIntoMap();
						JOptionPane.showMessageDialog(null, "�ɹ���6�ŵ�\n"
								+ "���״̬�Ѿ��ɹ�����6�ŵ��������״̬��\n" + "�������Ϸ\n");
						Main.tip.setText("�ɹ���6�ŵ�");
					}

					// ��7�ŵ�
					else if (choose[6].isSelected()) {
						fin = new FileInputStream("record/record7.txt");
						dfin = new DataInputStream(fin);
						recordIntoMap();
						JOptionPane.showMessageDialog(null, "�ɹ���7�ŵ�\n"
								+ "���״̬�Ѿ��ɹ�����7�ŵ��������״̬��\n" + "�������Ϸ\n");
						Main.tip.setText("�ɹ���7�ŵ�");
					}

					// ��8�ŵ�
					else if (choose[7].isSelected()) {
						fin = new FileInputStream("record/record8.txt");
						dfin = new DataInputStream(fin);
						recordIntoMap();
						JOptionPane.showMessageDialog(null, "�ɹ���8�ŵ�\n"
								+ "���״̬�Ѿ��ɹ�����8�ŵ��������״̬��\n" + "�������Ϸ\n");
						Main.tip.setText("�ɹ���8�ŵ�");
					}

					// ��9�ŵ�
					else if (choose[8].isSelected()) {
						fin = new FileInputStream("record/record9.txt");
						dfin = new DataInputStream(fin);
						recordIntoMap();
						JOptionPane.showMessageDialog(null, "�ɹ���9�ŵ�\n"
								+ "���״̬�Ѿ��ɹ�����9�ŵ��������״̬��\n" + "�������Ϸ\n");
						Main.tip.setText("�ɹ���9�ŵ�");
					}

					// ��10�ŵ�
					else if (choose[9].isSelected()) {
						fin = new FileInputStream("record/record10.txt");
						dfin = new DataInputStream(fin);
						recordIntoMap();
						JOptionPane.showMessageDialog(null, "�ɹ���10�ŵ�\n"
								+ "���״̬�Ѿ��ɹ�����10�ŵ��������״̬��\n" + "�������Ϸ\n");
						Main.tip.setText("�ɹ���10�ŵ�");
					}

					// �򿪵���ˢ�½����ϵ�ͼ
					Main.UpAndDownStairsDrawMap();

					// �򿪵���ˢ���˵ĸ�������
					Main.show_stairs.setText("��ħ��" + Main.stairs + "��");
					Main.show_attack.setText("������=" + Main.person[1]);
					Main.show_defense.setText("������=" + Main.person[2]);
					Main.show_blood.setText("HP=" + Main.person[3]);
					Main.show_money.setText("��Ǯ=" + Main.person[4]);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "�򿪴浵ʧ��\n"
							+ "��ȷ����Ҫ�򿪵Ĵ浵��Ч��");
					System.err.println("�����쳣:" + e1);
					e1.printStackTrace();
				}
			}
		});
		c.add(confirmperson);
	}

	// ���ļ�������¼
	void recordIntoMap() throws IOException {
		try {
			Main.person[1] = dfin.readInt();
			Main.person[2] = dfin.readInt();
			Main.person[3] = dfin.readInt();
			Main.person[4] = dfin.readInt();
			Main.person[5] = dfin.readInt();
			Main.person[6] = dfin.readInt();
			Main.person[7] = dfin.readInt();
			
			Main.stairs = dfin.readInt();
			for (int i = 1; i < 7; i++) {
				for (int j = 0; j < 100; j++)
					Main.realMap[i][j] = dfin.readInt();
			}
			dfin.close();
		} finally {

		}
	}

}
