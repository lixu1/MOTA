import java.awt.Container;
import java.awt.FlowLayout;

import java.io.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.*;

//ħ��Ӣ�۰�
public class hero extends JFrame {
	String title[] = { "Ӣ�۰�","����", "������", "������", "HP", "��Ǯ��","�ȼ�","����ֵ" };
	// ǰʮ��Ӣ������
	static String[][] data = {
			// ����������������������HP����Ǯ��
			{ "��һ��","","0", "0", "0", "0", "0", "0"}, { "�ڶ���","", "0", "0", "0", "0","0", "0" },
			{ "������", "","0", "0", "0", "0","0", "0" }, { "������", "","0", "0", "0", "0","0", "0" },
			{ "������","", "0", "0", "0", "0","0", "0" }, { "������","", "0", "0", "0", "0","0", "0"  },
			{ "������", "","0", "0", "0", "0" ,"0", "0" }, { "�ڰ���", "","0", "0", "0", "0","0", "0"  },
			{ "�ھ���", "","0", "0", "0", "0","0", "0"  }, { "��ʮ��","", "0", "0", "0", "0","0", "0"  }, };

	// �Ȱ��մ�ܴ�ħ����Ľ�Ǯ�����ٰ���HP���������ʱ��˳������
	static int insert(int a, int b, int c, int d,int e,int f) {
		fileIntoData();
		int k = 0;
		while (k < 10
				&& ((d < Integer.valueOf(data[k][5])) || (d == Integer
						.valueOf(data[k][5]) && c < Integer.valueOf(data[k][4]))))
			k++;
		for (int i = 9; i > k; i--) {
			hero.data[i][1] = data[i - 1][1];
			hero.data[i][2] = data[i - 1][2];
			hero.data[i][3] = data[i - 1][3];
			hero.data[i][4] = data[i - 1][4];
			hero.data[i][5] = data[i - 1][5];
			hero.data[i][6] = data[i - 1][6];
			hero.data[i][7] = data[i - 1][7];
		}
		
		if (k <= 9) {
			hero.data[k][1] =JOptionPane.showInputDialog(null, "�����������");
			hero.data[k][2] = new String("" + a);
			hero.data[k][3] = new String("" + b);
			hero.data[k][4] = new String("" + c);
			hero.data[k][5] = new String("" + d);
			hero.data[k][6] = new String("" + e);
			hero.data[k][7] = new String("" + f);
		}
		dataIntoFile();
		return (k + 1);
	}

	// ���캯��
	public hero() {
		super("Ӣ�۰�");
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		DefaultTableModel m_data = new DefaultTableModel(10, 5);
		fileIntoData();
		m_data.setDataVector(data, title);
		JTable m_view = new JTable(m_data);
		m_view.setEnabled(false);

		JScrollPane sPane = new JScrollPane(m_view);
		c.add(sPane);

	}

	// ��moteHero.txt�ļ��е����¼
	static void fileIntoData() {
		try {
			FileInputStream fin = new FileInputStream("record/moteHero.txt");
			DataInputStream dfin = new DataInputStream(fin);
			for (int i = 0; i < 10; i++) {
				{
					data[i][1]=new String(dfin.readUTF());
					for (int j = 2; j < 8; j++) {
				
						data[i][j] = new String("" + dfin.readInt());
					}
				}
			}
			dfin.close();
		} catch (Exception e) {
			System.err.println("�����쳣:" + e);
			e.printStackTrace();
		}
	}

	// �Ѽ�¼����moteHero.txt
	static void dataIntoFile() {
		try {
			FileOutputStream fout = new FileOutputStream("record/moteHero.txt",false);
			DataOutputStream dfout = new DataOutputStream(fout);
			for (int i = 0; i < 10; i++) {
				{
					dfout.writeUTF(data[i][1]);
					for (int j = 2; j < 8; j++)
						dfout.writeInt(Integer.valueOf(data[i][j]));
				}
			}
			dfout.flush();
			dfout.close();

		} catch (Exception e) {
			System.err.println("�����쳣:" + e);
			e.printStackTrace();
		}
	}

}
