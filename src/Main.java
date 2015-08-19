import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

public class Main {
	// �˵Ĵ��ţ�����������������Ѫ������Ǯ��5�ȼ���6����ֵ,7Կ����
	static int person[] = { 1, 10, 10, 1000, 0 ,0,0,0};
	// attack defense blood money rank experience

	// �����ڵ�¥����
	static int stairs = 1;
	// �˵�ͼ��
	static Icon defaultIcon;

	// �����
	static JFrame app = new JFrame("ħ��");
	// ����ĵ�ͼ���
	static JPanel map = new JPanel();
	// ��ͼ�ϵĸ���λ��
	static JButton site[] = new JButton[100];
	// ��ҵĵ�ͼ
	static int realMap[][] = new int[7][100];
	// չʾ������ĸ���ı�ǩ
	static JLabel show_stairs = new JLabel("��ħ��" + stairs + "��");
	// ��ʾ��Ϣ�ı���
	static JTextArea tip = new JTextArea("", 2, 5);
	static JScrollPane TIP = new JScrollPane(tip);

	// ������Ϣ�ı���
	static JTextArea help = new JTextArea("ħ��", 5, 10);

	// չʾ��ҹ�����,������,HP,��Ǯ�ı�ǩ
	static JLabel show_attack = new JLabel("������=" + person[1]);
	static JLabel show_defense = new JLabel("������=" + person[2]);
	static JLabel show_blood = new JLabel("HP=" + person[3]);
	static JLabel show_money = new JLabel("��Ǯ=" + person[4]);
	static JLabel show_rank=new JLabel("�ȼ�="+person[5]);
	static JLabel show_experience=new JLabel("����="+person[6]);
	static JLabel show_key=new JLabel("Կ��="+person[7]+"��");

	// �����˵���
	static JMenuItem[][] mI = {
			{ new JMenuItem("����Ϸ(N)"), new JMenuItem("Ӣ�۰�(Y)"),
					new JMenuItem("�˳�(X)") },
			{ new JMenuItem("�ı��ɫ(Q)"), new JMenuItem("�浵(W)"),
					new JMenuItem("�򿪴浵(E)") },
			{ new JMenuItem("�鿴���ߺ͹�����Ϣ(V)"), new JMenuItem("����ħ��(A)") } };

	// ѡ���ɫ�������Լ���ͼƬ
	static void chooseyourself() {
		// ��ɫ������ѡ��
		JLabel chooseperson[] = {
				new JLabel("����1", new ImageIcon("picture/person1.png"),
						JLabel.LEFT),
				new JLabel("����2", new ImageIcon("picture/person2.png"),
						JLabel.LEFT),
				new JLabel("����3", new ImageIcon("picture/person3.png"),
						JLabel.LEFT),
				new JLabel("����4", new ImageIcon("picture/person4.png"),
						JLabel.LEFT), };
		// ѡ���ɫ�Ի���
		final JDialog d = new JDialog(app, "ѡ���ɫ", true);
		Container c = d.getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.LEFT));
		for (int i = 0; i < chooseperson.length; i++)
			c.add(chooseperson[i]);
		JRadioButton[] choose = { new JRadioButton("ѡ������1"),
				new JRadioButton("ѡ������2"), new JRadioButton("ѡ������3"),
				new JRadioButton("ѡ������4") };
		ButtonGroup rg = new ButtonGroup();
		for (int i = 0; i < choose.length; i++) {
			c.add(choose[i]);
			rg.add(choose[i]);
		}
		choose[0].setSelected(true);
		choose[1].setSelected(false);
		choose[2].setSelected(false);
		choose[3].setSelected(false);

		JButton confirmperson = new JButton("ȷ��");
		confirmperson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				d.dispose();
			}
		});

		c.add(confirmperson);

		d.setSize(500, 200);
		d.setVisible(true);

		// �����û���ѡ�񣬸ı���ҵ�ͼ��
		if (choose[0].isSelected()) {
			defaultIcon = new ImageIcon("picture/person1.png");
		} else if (choose[1].isSelected()) {
			defaultIcon = new ImageIcon("picture/person2.png");
		} else if (choose[2].isSelected()) {
			defaultIcon = new ImageIcon("picture/person3.png");
		} else if (choose[3].isSelected()) {
			defaultIcon = new ImageIcon("picture/person4.png");
		}
	}

	// ��ʼ��������������ʼ����ͼ�����Ƶ�ͼ����ʼ���˵ĸ�������
	static void init() {
		chooseyourself();
		for (int i = 1; i < 7; i++)
			for (int j = 0; j < 100; j++)
				realMap[i][j] = MAP.Map[i][j];
		stairs = 1;

		DrawMap();
		siteAddActionListener();
		person[0] = 1;
		person[1] = 10;
		person[2] = 10;
		person[3] = 1000;
		person[4] = 0;
		person[5]=0;
		person[6]=0;
		person[7]=0;

		// ��ʼ������״̬��ǩ
		show_stairs.setText("��ħ��" + stairs + "��");
		show_attack.setText("������=" + person[1]);
		show_defense.setText("������=" + person[2]);
		show_blood.setText("HP=" + person[3]);
		show_money.setText("��Ǯ=" + person[4]);
		show_rank.setText("�ȼ�="+person[5]);
		show_experience.setText("����ֵ="+person[6]);
		show_key.setText("Կ��="+person[7]+"��");
		
		tip.setText("���ڿ�ʼ");
		help.setText("������ħ���������Ĳ㡣" + "\n�����Ĳ����յĴ�ħ�������ʹ����" + "\n�����������Χ�Ŀյؿ�"
				+ "\n���������ƶ�����ȡ���ߣ����ܹ��" + "\n��ܹ����ȡ��Ǯ��ȥ�̵�"
				+ "\n�����Լ��Ĺ���������������HP��" + "\n���Ͻ���������ﵱǰ״̬��" + "\n�м䲿���ǵ�ǰ��ʾ��Ϣ��"
				+ "\n���ò˵��ڿ��Ըı���Ľ�ɫ��ò��" + "\nҲ���Դ浵���ȡ��" + "\n������Ϣ�������Ϣ"
				+ "\n�����ڰ����˵����в鿴��" + "\n��Ϸ�˵�������Ӣ�۰�" + "\n��������ͨ��ʱ��Ǯ�Ķ��٣�"
				+ "\n�ٸ���Ѫ���Ķ��ٽ���������" + "\nһ·��ǰ����Ҫ����ǻۡ�" + "\nף��Ϸ��졣\n");
	}

	// Ϊ�����˵�������¼�����
	static void menuAddActionListener() {
		// Ϊ����Ϸ����¼�����
		mI[0][0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				init();
				tip.setText("��Ϸ���¿�ʼ,\n���������Щ���⣬\n��ı�һ�»����С\nˢ��һ�¼��ɡ�");
			}
		});

		// ΪӢ�۰�����¼�����
		mI[0][1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hero app = new hero();
				app.setSize(500, 300);
				app.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				app.setVisible(true);
			}
		});

		// Ϊ�˳�����¼�����
		mI[0][2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		// Ϊ�ı��ɫ����¼�����
		mI[1][0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooseyourself();
				UpAndDownStairsDrawMap();
			}
		});

		// Ϊ�浵����¼�����
		mI[1][1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveRecord a = new saveRecord();
				a.setSize(200, 400);
				a.setVisible(true);
				a.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});

		// Ϊ�򿪴浵����¼�����
		mI[1][2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openRecord a = new openRecord();
				a.setSize(200, 400);
				a.setVisible(true);
				a.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});

		// Ϊ�鿴���ߺ͹�����Ϣ(V)����¼�����
		mI[2][0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InformationFrame a = new InformationFrame();
				a.setSize(1300, 800);
				a.setVisible(true);
				a.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});

		// Ϊ����ħ��(A)����¼�����
		mI[2][1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "����Ϸ�Ǳ�����Ժѧ���Ŷ�����JAVA��Ŀ\n"
						+ "��Ա�У�����Ԭ��谣�������ϲ��\n" + "��ʲô���⣬����ϵ����\n" + "��Ȩ����\n");
			}
		});
	}
	
	//���Ӿ���
	static void addExperience(int experience,int times)
	{
		
		int previousRank=person[5];

		if(times==1)
			person[6]+=6*experience;
		else if(times>1 &&times<=5)
			person[6]+=5*experience;
		else if(times>5&&times<=10)
			person[6]+=4*experience;
		else if(times>10&&times<=20)
			person[6]+=3*experience;
		else if(times>20&&times<=50)
			person[6]+=2*experience;
		else
			person[6]+=experience;
		
		show_experience.setText("����ֵ="+person[6]);
		
		int nowRank=judgeRank(person[6]);
		
		if(nowRank>previousRank)
		{
			person[5]=nowRank;
			show_rank.setText("�ȼ�="+person[5]);
			JOptionPane.showMessageDialog(null, "��ϲ������"+(nowRank-previousRank)+
					"����������"+person[5]+"��!" +
					"\n���������5ս������5��������500HP");
			person[1]+=(nowRank-previousRank)*5;
			person[2]+=(nowRank-previousRank)*5;
			person[3]+=(nowRank-previousRank)*500;
			show_attack.setText("������=" + person[1]);
			show_defense.setText("������=" + person[2]);
			show_blood.setText("HP=" + person[3]);
		}
	}
	//�жϵȼ�
	static int judgeRank(int experience)
	{
		int experienceRank[]={0,20,30,45,68,102,76,152,228,342,513,769,1154,1730};
		int answer=0;
		while(experience>experienceRank[answer])
		{
			experience=experience-experienceRank[answer];
			answer++;
		}
		answer--;
		return answer;
	}

	// ������
	public static void main(String args[]) {
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setSize(800, 600);
		Container c = app.getContentPane();
		c.setLayout(new BorderLayout());

		// �˵�����
		JMenuBar mBar = new JMenuBar();
		mBar.setBackground(Color.cyan);
		app.setJMenuBar(mBar);
		JMenu[] m = { new JMenu("��Ϸ(G)"), new JMenu("����(F)"),
				new JMenu("����(H)") };
		char[][] mC = { { 'G', 'F', 'H' }, { 'N', 'Y', 'X' },
				{ 'Q', 'W', 'E' }, { 'V', 'A' } };

		// Ϊ�˵���Ӳ˵���
		for (int i = 0; i < m.length; i++) {
			mBar.add(m[i]);
			m[i].setMnemonic(mC[0][i]);
			for (int j = 0; j < mI[i].length; j++) {
				m[i].add(mI[i][j]);
				mI[i][j].setMnemonic(mC[i + 1][j]);
				mI[i][j].setAccelerator(KeyStroke.getKeyStroke("ctrl "
						+ mC[i + 1][j]));
			}
		}
		// Ϊ�˵�������¼�����
		menuAddActionListener();

		// ��ͼ�������
		map.setLayout(new GridLayout(10, 10));
		init();
		c.add(map, BorderLayout.CENTER);

		// �����������
		JPanel Control = new JPanel();
		Control.setLayout(new BoxLayout(Control, BoxLayout.Y_AXIS));
		Control.add(new JLabel("�����ڵ�״̬��"));
		Control.setBackground(Color.GREEN);

		Control.add(show_stairs);
		Control.add(show_attack);
		Control.add(show_defense);
		Control.add(show_blood);
		Control.add(show_money);
		Control.add(show_rank);
		Control.add(show_experience);
		Control.add(show_key);
		
		Control.add(new JLabel("��ʾ��Ϣ:"));
		tip.setEditable(false);
		Control.add(TIP);
		Control.add(new JLabel("����:"));
		help.setEditable(false);
		JScrollPane HELP = new JScrollPane(help);
		Control.add(HELP);
		c.add(Control, BorderLayout.EAST);

		app.setVisible(true);
	}

	// ����Ĺ���û�й��������ʱ �Ĵ�����
	static void attackUnderDefence() {
		tip.append("��Ĺ�����û���ķ������أ�\n" + "��ô���ܴ����?\n" + "���������������ɣ�");
	}

	// ��ÿһ��λ�ü����¼�����
	static void siteAddActionListener() {
		for (int i = 0; i < 100; i++) {
			final int j = i;
			site[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// ������λ�����˵��Ա�ʱ�����д���
					if (realMap[stairs][j] == 1
							|| ((j - 1) > 0 && (j - 1) % 10 < 9 && realMap[stairs][j - 1] == 1)
							|| ((j + 1) < 100 && (j + 1) % 10 > 0 && realMap[stairs][j + 1] == 1)
							|| ((j > 10) && (realMap[stairs][j - 10] == 1))
							|| (j < 90 && realMap[stairs][j + 10] == 1)) {
						// ȷ���˵�λ��
						int person_site = 0;// �˵�λ��
						if (((j - 1) > 0 && (j - 1) % 10 < 9 && realMap[stairs][j - 1] == 1))
							person_site = j - 1;
						else if (((j + 1) < 100 && (j + 1) % 10 > 0 && realMap[stairs][j + 1] == 1))
							person_site = j + 1;
						else if (((j > 10) && (realMap[stairs][j - 10] == 1)))
							person_site = j - 10;
						else if (j < 90 && realMap[stairs][j + 10] == 1)
							person_site = j + 10;

						// ȷ�����λ�õĴ��ţ��ֱ���
						int k = Integer.valueOf(site[j].getName());
						switch (k) {
						// ����ǽ
						case -1:
							tip.setText("������ǽ������ͨ��");
							break;
						// �յ�
						case 0:
							tip.setText("�����ǿյأ�˳��ͨ��");
							realMap[stairs][j] = 1;
							realMap[stairs][person_site] = 0;
							site[j].setName("1");
							site[j].setIcon(defaultIcon);
							site[person_site].setName("0");
							site[person_site].setIcon(null);
							break;
						// �����Լ�
						case 1:
							tip.setText("������Լ���\n��ѡ�������һ��\n����ǰ��λ��");
							break;
						// �õ�һ��СѪƿ
						case 2:
							tip.setText("�õ�һ��СѪƿ��\nHP����"
									+ monster.smallBloodBottle[3]);

							person[3] = person[3] + monster.smallBloodBottle[3];
							show_blood.setText("HP=" + person[3]);
							realMap[stairs][j] = 1;
							realMap[stairs][person_site] = 0;
							site[j].setName("1");
							site[j].setIcon(defaultIcon);
							site[person_site].setName("0");
							site[person_site].setIcon(null);
							break;
						// �õ�һ����Ѫƿ
						case 3:
							tip.setText("�õ�һ����Ѫƿ��\nHP����"
									+ monster.bigBloodBottle[3]);

							person[3] += monster.bigBloodBottle[3];
							show_blood.setText("HP=" + person[3]);
							realMap[stairs][j] = 1;
							realMap[stairs][person_site] = 0;
							site[j].setName("1");
							site[j].setIcon(defaultIcon);
							site[person_site].setName("0");
							site[person_site].setIcon(null);
							break;
						// �õ�һ���챦ʯ
						case 4:
							tip.setText("�õ�һ���챦ʯ��\n����������" + monster.ruby[1]);

							person[1] += monster.ruby[1];
							show_attack.setText("������=" + person[1]);
							realMap[stairs][j] = 1;
							realMap[stairs][person_site] = 0;
							site[j].setName("1");
							site[j].setIcon(defaultIcon);
							site[person_site].setName("0");
							site[person_site].setIcon(null);
							break;
							
						// �õ�һ������ʯ
						case 5:
							tip.setText("�õ�һ������ʯ��\n����������" + monster.sapphire[2]);
							person[2] += monster.sapphire[2];
							show_defense.setText("������=" + person[2]);
							realMap[stairs][j] = 1;
							realMap[stairs][person_site] = 0;
							site[j].setName("1");
							site[j].setIcon(defaultIcon);
							site[person_site].setName("0");
							site[person_site].setIcon(null);
							break;
							
						// �õ�һ���ƽ�
						case 6:
							tip.setText("�õ�һ���ƽ𽣣�\n����������"
									+ monster.goldenSword[1]);
							person[1] += monster.goldenSword[1];
							show_attack.setText("������=" + person[1]);
							realMap[stairs][j] = 1;
							realMap[stairs][person_site] = 0;
							site[j].setName("1");
							site[j].setIcon(defaultIcon);
							site[person_site].setName("0");
							site[person_site].setIcon(null);
							break;
						// �õ�һ���ƽ��
						case 7:
							tip.setText("�õ�һ���ƽ�ܣ�\n����������"
									+ monster.goldShield[2]);
							person[2] += monster.goldShield[2];
							show_defense.setText("������=" + person[2]);
							realMap[stairs][j] = 1;
							realMap[stairs][person_site] = 0;
							site[j].setName("1");
							site[j].setIcon(defaultIcon);
							site[person_site].setName("0");
							site[person_site].setIcon(null);
							break;
						// ����С�̵�����
						case 8:
							tip.setText("����С�̵꣬\n��ѡ����Ҫ��Ķ���\n");
							String[] choose = { "ս����+" + monster.smallShop[1],
									"������+" + monster.smallShop[2],
									"HP+" + monster.smallShop[3], "�´�����" };

							// �����Ի���
							String yourchoose = (String) JOptionPane
									.showInputDialog(null, "������ʹ��"
											+ monster.smallShop[4]
											+ "����Ǯ������ʲô��", "С�̵�",
											JOptionPane.QUESTION_MESSAGE, null,
											choose, choose[0]);

							// ��ս����
							if (yourchoose == choose[0]) {
								if (person[4] < monster.smallShop[4]) {
									tip.append("��Ľ�Ǯ��̫�٣�\n" + "����"
											+ monster.smallShop[1] + "ս����ʧ��\n"
											+ "���Ŵ��׬�����Ǯ��!\n");
								} else {
									tip.append("�㻨����" + monster.smallShop[4]
											+ "����Ǯ��\n" + "������"
											+ monster.smallShop[1] + "ս����\n");
									person[1] += monster.smallShop[1];
									person[4] -= monster.smallShop[4];
									show_attack.setText("������=" + person[1]);
									show_money.setText("��Ǯ=" + person[4]);
								}
							}

							// �������
							else if (yourchoose == choose[1]) {
								if (person[4] < monster.smallShop[4]) {
									tip.append("��Ľ�Ǯ��̫�٣�\n" + "����"
											+ monster.smallShop[2] + "������ʧ��\n"
											+ "���Ŵ��׬�����Ǯ��!");
								} else {
									tip.append("�㻨����" + monster.smallShop[4]
											+ "����Ǯ��\n������"
											+ monster.smallShop[2] + "������");
									person[2] += monster.smallShop[2];
									person[4] -= monster.smallShop[4];
									show_defense.setText("������=" + person[2]);
									show_money.setText("��Ǯ=" + person[4]);
								}
							}

							// ��HP
							else if (yourchoose == choose[2]) {
								if (person[4] < monster.smallShop[4]) {
									tip.append("��Ľ�Ǯ��̫�٣�\n" + "����"
											+ monster.smallShop[3] + "HPʧ��\n"
											+ "���Ŵ��׬�����Ǯ��!");
								} else {
									tip.append("�㻨����" + monster.smallShop[4]
											+ "����Ǯ��\n������"
											+ monster.smallShop[3] + "HP");
									person[3] += monster.smallShop[3];
									person[4] -= monster.smallShop[4];
									show_blood.setText("HP=" + person[3]);
									show_money.setText("��Ǯ=" + person[4]);
								}
							}

							// �´�����
							else if (yourchoose == choose[3]) {
								tip.append("��û�й����κζ���");
							}
							break;
						// ������̵�����
						case 9:
							tip.setText("������̵꣬\n��ѡ����Ҫ��Ķ���\n");
							String[] choose1 = { "ս����+" + monster.bigShop[1],
									"������+" + monster.bigShop[2],
									"HP+" + monster.bigShop[3], "�´�����" };

							// �����Ի���
							String yourchoose1 = (String) JOptionPane
									.showInputDialog(null, "������ʹ��"
											+ monster.bigShop[4] + "����Ǯ������ʲô��",
											"С�̵�",
											JOptionPane.QUESTION_MESSAGE, null,
											choose1, choose1[0]);

							// ��ս����
							if (yourchoose1 == choose1[0]) {
								if (person[4] < monster.bigShop[4]) {
									tip.append("��Ľ�Ǯ��̫�٣�\n" + "����"
											+ monster.bigShop[1] + "ս����ʧ��\n"
											+ "���Ŵ��׬�����Ǯ��!");
								} else {
									tip.append("�㻨����" + monster.bigShop[4]
											+ "����Ǯ��\n������" + monster.bigShop[1]
											+ "ս����\n");
									person[1] += monster.bigShop[1];
									person[4] -= monster.bigShop[4];
									show_attack.setText("������=" + person[1]);
									show_money.setText("��Ǯ=" + person[4]);
								}
							}

							// �������
							else if (yourchoose1 == choose1[1]) {
								if (person[4] < monster.bigShop[4]) {
									tip.append("��Ľ�Ǯ��̫�٣�\n" + "����"
											+ monster.bigShop[2] + "������ʧ��\n"
											+ "���Ŵ��׬�����Ǯ��!");
								} else {
									tip.append("�㻨����" + monster.bigShop[4]
											+ "����Ǯ��\n������" + monster.bigShop[2]
											+ "������\n");
									person[2] += monster.bigShop[2];
									person[4] -= monster.bigShop[4];
									show_defense.setText("������=" + person[2]);
									show_money.setText("��Ǯ=" + person[4]);
								}
							}

							// ��HP
							else if (yourchoose1 == choose1[2]) {
								if (person[4] < monster.bigShop[4]) {
									tip.append("��Ľ�Ǯ��̫�٣�\n" + "����"
											+ monster.bigShop[3] + "HPʧ��\n"
											+ "���Ŵ��׬�����Ǯ��!");
								} else {
									tip.append("�㻨����" + monster.bigShop[4]
											+ "����Ǯ��\n������" + monster.bigShop[3]
											+ "HP\n");
									person[3] += monster.bigShop[3];
									person[4] -= monster.bigShop[4];
									show_blood.setText("HP=" + person[3]);
									show_money.setText("��Ǯ=" + person[4]);
								}
							}

							// �´�����
							else if (yourchoose1 == choose1[3]) {
								tip.append("��û�й����κζ���");
							}
							break;

						// �������������ս
						case 10:
							tip.setText("��������������\n");
							int HP1_greenBubble = monster.greenBubble[3];
							int HP1_person = person[3];
							int time1 = 1;

							// �˵�ս�����������ݷ�������ʱ����
							if (person[1] > monster.greenBubble[2]) {
								// ����������һ�鷢����������һ����ʱ�˳�ѭ��
								while (HP1_greenBubble > 0 && HP1_person > 0) {
									tip.append("��" + time1 + "�غ�\n");
									if (monster.greenBubble[1] > person[2]) {
										HP1_person -= monster.greenBubble[1]
												- person[2];
										tip.append("����ʧ"
												+ (monster.greenBubble[1] - person[2])
												+ "��Ѫ,\n");
										if (HP1_person > 0)
											tip.append("����" + HP1_person
													+ "��Ѫ\n");
										else
											tip.append("���Ѿ�����\n");
									} else
										tip.append("��û����ʧһ��Ѫ\n");
									if (person[1] > monster.greenBubble[2]) {
										HP1_greenBubble -= person[1]
												- monster.greenBubble[2];
										tip.append("������������ʧ"
												+ (person[1] - monster.greenBubble[2])
												+ "��Ѫ,\n");
										if (HP1_greenBubble > 0)
											tip.append("����" + HP1_greenBubble
													+ "��Ѫ\n");
										else
											tip.append("�����������Ѿ�����\n");
									} else
										tip.append("����������û����ʧһ��Ѫ\n");
									time1++;
								}
								// �������ս��������
								if (HP1_person <= 0) {
									tip.append("���������������ս����������\n"
											+ "�Һ������к���֮��\n"
											+ "�㱻�Ȼ�һص������������֮ǰ��״̬\n"
											+ "������ѡ����ʵĹ��\n"
											+ "���ǿ�Լ����������ٴγ��ԣ�\n"
											+ "������һ�������������ˡ�����");
								}
								// �����սʤ��������
								else if (HP1_person > 0) {
									tip.append("��ϲ��ɹ���ܹ���������\n" + "����һ����ս��\n"
											+ "����ʧ��" + (person[3] - HP1_person)
											+ "��Ѫ\n" + "�����"
											+ monster.greenBubble[4] + "��Ǯ\n");
									person[3] = HP1_person;
									person[4] += monster.greenBubble[4];
									realMap[stairs][j] = 1;
									realMap[stairs][person_site] = 0;
									site[j].setName("1");
									site[j].setIcon(defaultIcon);
									site[person_site].setName("0");
									site[person_site].setIcon(null);
									show_blood.setText("HP=" + person[3]);
									show_money.setText("��Ǯ=" + person[4]);
									addExperience(monster.greenBubble[5],time1);
								}
							}
							// ���˵�ս�������ڹ��������ݷ�����ʱ
							else
								attackUnderDefence();
							break;

						// �������������ս
						case 11:
							tip.setText("��������������\n");
							int HP2_blueBubble = monster.blueBubble[3];
							int HP2_person = person[3];
							int time2 = 1;
							if (person[1] > monster.blueBubble[2]) {
								while (HP2_blueBubble > 0 && HP2_person > 0) {
									tip.append("��" + time2 + "�غ�\n");
									if (monster.blueBubble[1] > person[2]) {
										HP2_person -= monster.blueBubble[1]
												- person[2];
										tip.append("����ʧ"
												+ (monster.blueBubble[1] - person[2])
												+ "��Ѫ,\n");
										if (HP2_person > 0)
											tip.append("����" + HP2_person
													+ "��Ѫ\n");
										else
											tip.append("���Ѿ�����\n");
									} else
										tip.append("��û����ʧһ��Ѫ\n");
									if (person[1] > monster.blueBubble[2]) {
										HP2_blueBubble -= person[1]
												- monster.blueBubble[2];
										tip.append("������������ʧ"
												+ (person[1] - monster.blueBubble[2])
												+ "��Ѫ,\n" + "����"
												+ HP2_blueBubble + "��Ѫ\n");
									}
									time2++;
								}
								if (HP2_person <= 0) {
									tip.append("���������������ս����������\n"
											+ "�Һ������к���֮��\n"
											+ "�㱻�Ȼ�һص������������֮ǰ��״̬\n"
											+ "������ѡ����ʵĹ��\n"
											+ "���ǿ�Լ����������ٴγ��ԣ�\n"
											+ "������һ�������������ˡ�����");
								} else if (HP2_person > 0) {
									tip.append("��ϲ��ɹ���ܹ���������\n" + "����һ����ս��\n"
											+ "����ʧ��" + (person[3] - HP2_person)
											+ "��Ѫ\n" + "�����"
											+ monster.blueBubble[4] + "��Ǯ\n");
									person[3] = HP2_person;
									person[4] += monster.blueBubble[4];
									realMap[stairs][j] = 1;
									realMap[stairs][person_site] = 0;
									site[j].setName("1");
									site[j].setIcon(defaultIcon);
									site[person_site].setName("0");
									site[person_site].setIcon(null);
									show_blood.setText("HP=" + person[3]);
									show_money.setText("��Ǯ=" + person[4]);
									addExperience(monster.blueBubble[5],time2);
								}
							} else
								attackUnderDefence();
							break;

						// ������������ս
						case 12:
							tip.setText("�������������\n");
							int HP3_redBubble = monster.redBubble[3];
							int HP3_person = person[3];
							int time3 = 1;
							if (person[1] > monster.redBubble[2]) {
								while (HP3_redBubble > 0 && HP3_person > 0) {
									tip.append("��" + time3 + "�غ�\n");
									if (monster.redBubble[1] > person[2]) {
										HP3_person -= monster.redBubble[1]
												- person[2];
										tip.append("����ʧ"
												+ (monster.redBubble[1] - person[2])
												+ "��Ѫ\n");
										if (HP3_person > 0)
											tip.append("����" + HP3_person
													+ "��Ѫ\n");
										else
											tip.append("���Ѿ�����\n");
									} else
										tip.append("��û����ʧһ��Ѫ\n");
									if (person[1] > monster.redBubble[2]) {
										HP3_redBubble -= person[1]
												- monster.redBubble[2];
										tip.append("�����������ʧ"
												+ (person[1] - monster.redBubble[2])
												+ "��Ѫ,\n");
										if (HP3_redBubble > 0)
											tip.append("����" + HP3_redBubble
													+ "��Ѫ\n");
										else
											tip.append("����������Ѿ�����\n");
									}
									time3++;
								}
								if (HP3_person <= 0) {
									tip.append("��������������ս����������\n"
											+ "�Һ������к���֮��\n"
											+ "�㱻�Ȼ�һص�����������֮ǰ��״̬\n"
											+ "������ѡ����ʵĹ��\n"
											+ "���ǿ�Լ����������ٴγ��ԣ�\n"
											+ "������һ�������������ˡ�����\n");
								} else if (HP3_person > 0) {
									tip.append("��ϲ��ɹ���ܹ��������\n" + "����һ����ս��\n"
											+ "����ʧ��" + (person[3] - HP3_person)
											+ "��Ѫ\n" + "�����"
											+ monster.redBubble[4] + "��Ǯ\n");
									person[3] = HP3_person;
									person[4] += monster.redBubble[4];
									realMap[stairs][j] = 1;
									realMap[stairs][person_site] = 0;
									site[j].setName("1");
									site[j].setIcon(defaultIcon);
									site[person_site].setName("0");
									site[person_site].setIcon(null);
									show_blood.setText("HP=" + person[3]);
									show_money.setText("��Ǯ=" + person[4]);
									addExperience(monster.redBubble[5],time3);
								}
							} else
								attackUnderDefence();

							break;

						// �����С������ս
						case 13:
							tip.setText("��������С����\n");
							int HP4_smallBat = monster.smallBat[3];
							int HP4_person = person[3];
							int time4 = 1;
							if (person[1] > monster.smallBat[2]) {
								while (HP4_smallBat > 0 && HP4_person > 0) {
									tip.append("��" + time4 + "�غ�\n");
									if (monster.smallBat[1] > person[2]) {
										HP4_person -= monster.smallBat[1]
												- person[2];
										tip.append("����ʧ"
												+ (monster.smallBat[1] - person[2])
												+ "��Ѫ\n");
										if (HP4_person > 0)
											tip.append("����" + HP4_person
													+ "��Ѫ\n");
										else
											tip.append("���Ѿ�����\n");
									} else
										tip.append("��û����ʧһ��Ѫ\n");
									if (person[1] > monster.smallBat[2]) {
										HP4_smallBat -= person[1]
												- monster.smallBat[2];
										tip.append("����С������ʧ"
												+ (person[1] - monster.smallBat[2])
												+ "��Ѫ,\n");
										if (HP4_smallBat > 0)
											tip.append("����" + HP4_smallBat
													+ "��Ѫ\n");
										else
											tip.append("����С�����Ѿ�����\n");
									}
									time4++;
								}
								if (HP4_person <= 0) {
									tip.append("�������С������ս����������\n"
											+ "�Һ������к���֮��\n"
											+ "�㱻�Ȼ�һص������С����֮ǰ��״̬\n"
											+ "������ѡ����ʵĹ��\n"
											+ "���ǿ�Լ����������ٴγ��ԣ�\n"
											+ "������һ�������������ˡ�����");
								} else if (HP4_person > 0) {
									tip.append("��ϲ��ɹ���ܹ���С����\n" + "����һ����ս��\n"
											+ "����ʧ��" + (person[3] - HP4_person)
											+ "��Ѫ\n" + "�����"
											+ monster.smallBat[4] + "��Ǯ\n");
									person[3] = HP4_person;
									person[4] += monster.smallBat[4];
									realMap[stairs][j] = 1;
									realMap[stairs][person_site] = 0;
									site[j].setName("1");
									site[j].setIcon(defaultIcon);
									site[person_site].setName("0");
									site[person_site].setIcon(null);
									show_blood.setText("HP=" + person[3]);
									show_money.setText("��Ǯ=" + person[4]);
									addExperience(monster.redBubble[5],time4);
								}
							} else
								attackUnderDefence();

							break;

						// ������������ս
						case 14:
							tip.setText("������������� \n");
							int HP5_bigBat = monster.bigBat[3];
							int HP5_person = person[3];
							int time5 = 1;
							if (person[1] > monster.bigBat[2]) {
								while (HP5_bigBat > 0 && HP5_person > 0) {
									tip.append("��" + time5 + "�غ�\n");
									if (monster.bigBat[1] > person[2]) {
										HP5_person -= monster.bigBat[1]
												- person[2];
										tip.append("����ʧ"
												+ (monster.bigBat[1] - person[2])
												+ "��Ѫ\n");
										if (HP5_person > 0)
											tip.append("����" + HP5_person
													+ "��Ѫ\n");
										else
											tip.append("���Ѿ�����\n");
									} else
										tip.append("��û����ʧһ��Ѫ\n");
									if (person[1] > monster.bigBat[2]) {
										HP5_bigBat -= person[1]
												- monster.bigBat[2];
										tip.append("�����������ʧ"
												+ (person[1] - monster.bigBat[2])
												+ "��Ѫ,\n");
										if (HP5_bigBat > 0)
											tip.append("����" + HP5_bigBat
													+ "��Ѫ\n");
										else
											tip.append("����������Ѿ�����\n");
									}
									time5++;
								}
								if (HP5_person <= 0) {
									tip.append("��������������ս����������\n"
											+ "�Һ������к���֮��\n"
											+ "�㱻�Ȼ�һص�����������֮ǰ��״̬\n"
											+ "������ѡ����ʵĹ��\n"
											+ "���ǿ�Լ����������ٴγ��ԣ�\n"
											+ "������һ�������������ˡ�����");
								} else if (HP5_person > 0) {
									tip.append("��ϲ��ɹ���ܹ��������\n" + "����һ����ս��\n"
											+ "����ʧ��" + (person[3] - HP5_person)
											+ "��Ѫ\n" + "�����"
											+ monster.smallBat[4] + "��Ǯ\n");
									person[3] = HP5_person;
									person[4] += monster.bigBat[4];
									realMap[stairs][j] = 1;
									realMap[stairs][person_site] = 0;
									site[j].setName("1");
									site[j].setIcon(defaultIcon);
									site[person_site].setName("0");
									site[person_site].setIcon(null);
									show_blood.setText("HP=" + person[3]);
									show_money.setText("��Ǯ=" + person[4]);
									addExperience(monster.bigBat[5],time5);
								}
							} else
								attackUnderDefence();

							break;

						// ������������ս
						case 15:
							tip.setText("������������� \n");
							int HP6_blackBubble = monster.blackBubble[3];
							int HP6_person = person[3];
							int time6 = 1;
							if (person[1] > monster.blackBubble[2]) {
								while (HP6_blackBubble > 0 && HP6_person > 0) {
									tip.append("��" + time6 + "�غ�\n");
									if (monster.blackBubble[1] > person[2]) {
										HP6_person -= monster.blackBubble[1]
												- person[2];
										tip.append("����ʧ"
												+ (monster.blackBubble[1] - person[2])
												+ "��Ѫ," + "����" + HP6_person
												+ "��Ѫ\n");
									} else
										tip.append("��û����ʧһ��Ѫ\n");
									if (person[1] > monster.blackBubble[2]) {
										HP6_blackBubble -= person[1]
												- monster.blackBubble[2];
										tip.append("�����������ʧ"
												+ (person[1] - monster.bigBat[2])
												+ "��Ѫ,\n" + "����"
												+ HP6_blackBubble + "��Ѫ\n");
									}
									time6++;
								}
								if (HP6_person <= 0) {
									tip.append("��������������ս����������\n"
											+ "�Һ������к���֮��\n"
											+ "�㱻�Ȼ�һص�����������֮ǰ��״̬\n"
											+ "������ѡ����ʵĹ��\n"
											+ "���ǿ�Լ����������ٴγ��ԣ�\n"
											+ "������һ�������������ˡ�����");
								} else if (HP6_person > 0) {
									tip.append("��ϲ��ɹ���ܹ��������\n" + "����һ����ս��\n"
											+ "����ʧ��" + (person[3] - HP6_person)
											+ "��Ѫ\n" + "�����"
											+ monster.blackBubble[4] + "��Ǯ\n");
									person[3] = HP6_person;
									person[4] += monster.blackBubble[4];
									realMap[stairs][j] = 1;
									realMap[stairs][person_site] = 0;
									site[j].setName("1");
									site[j].setIcon(defaultIcon);
									site[person_site].setName("0");
									site[person_site].setIcon(null);
									show_blood.setText("HP=" + person[3]);
									show_money.setText("��Ǯ=" + person[4]);
									addExperience(monster.blackBubble[5],time6);
								}
							} else
								attackUnderDefence();

							break;

						// ������������ս
						case 16:
							tip.setText("������������� \n");
							int HP7_redBat = monster.redBat[3];
							int HP7_person = person[3];
							int time7 = 1;
							if (person[1] > monster.redBat[2]) {
								while (HP7_redBat > 0 && HP7_person > 0) {
									tip.append("��" + time7 + "�غ�\n");
									if (monster.redBat[1] > person[2]) {
										HP7_person -= monster.redBat[1]
												- person[2];
										tip.append("����ʧ"
												+ (monster.redBat[1] - person[2])
												+ "��Ѫ\n");
										if (HP7_person > 0)
											tip.append("����" + HP7_person
													+ "��Ѫ\n");
										else
											tip.append("���Ѿ�����\n");
									} else
										tip.append("��û����ʧһ��Ѫ\n");
									if (person[1] > monster.redBat[2]) {
										HP7_redBat -= person[1]
												- monster.redBat[2];
										tip.append("�����������ʧ"
												+ (person[1] - monster.redBat[2])
												+ "��Ѫ,\n");
										if (HP7_redBat > 0)
											tip.append("����" + HP7_redBat
													+ "��Ѫ\n");
										else
											tip.append("����������Ѿ�����\n");
									}
									time7++;
								}
								if (HP7_person <= 0) {
									tip.append("��������������ս����������\n"
											+ "�Һ������к���֮��\n"
											+ "�㱻�Ȼ�һص�����������֮ǰ��״̬\n"
											+ "������ѡ����ʵĹ��\n"
											+ "���ǿ�Լ����������ٴγ��ԣ�\n"
											+ "������һ�������������ˡ�����");
								} else if (HP7_person > 0) {
									tip.append("��ϲ��ɹ���ܹ��������\n" + "����һ����ս��\n"
											+ "����ʧ��" + (person[3] - HP7_person)
											+ "��Ѫ\n" + "�����"
											+ monster.redBat[4] + "��Ǯ\n");
									person[3] = HP7_person;
									person[4] += monster.redBat[4];
									realMap[stairs][j] = 1;
									realMap[stairs][person_site] = 0;
									site[j].setName("1");
									site[j].setIcon(defaultIcon);
									site[person_site].setName("0");
									site[person_site].setIcon(null);
									show_blood.setText("HP=" + person[3]);
									show_money.setText("��Ǯ=" + person[4]);
									addExperience(monster.redBat[5],time7);
								}
							} else
								attackUnderDefence();

							break;

						// ��������ʿ��ս
						case 17:
							tip.setText("�����������ʿ \n");
							int HP8_strongMan = monster.strongMan[3];
							int HP8_person = person[3];
							int time8 = 1;
							if (person[1] > monster.strongMan[2]) {
								while (HP8_strongMan > 0 && HP8_person > 0) {
									tip.append("��" + time8 + "�غ�\n");
									if (monster.strongMan[1] > person[2]) {
										HP8_person -= monster.strongMan[1]
												- person[2];
										tip.append("����ʧ"
												+ (monster.strongMan[1] - person[2])
												+ "��Ѫ\n");
										if (HP8_person > 0)
											tip.append("����" + HP8_person
													+ "��Ѫ\n");
										else
											tip.append("���Ѿ�����\n");
									} else
										tip.append("��û����ʧһ��Ѫ\n");
									if (person[1] > monster.strongMan[2]) {
										HP8_strongMan -= person[1]
												- monster.strongMan[2];
										tip.append("�������ʿ��ʧ"
												+ (person[1] - monster.strongMan[2])
												+ "��Ѫ,\n");
										if (HP8_strongMan > 0)
											tip.append("����" + HP8_strongMan
													+ "��Ѫ\n");
										else
											tip.append("�������ʿ�Ѿ�����\n");
									}
									time8++;
								}
								if (HP8_person <= 0) {
									tip.append("����������ʿ��ս����������\n"
											+ "�Һ������к���֮��\n"
											+ "�㱻�Ȼ�һص���������ʿ֮ǰ��״̬\n"
											+ "������ѡ����ʵĹ��\n"
											+ "���ǿ�Լ����������ٴγ��ԣ�\n"
											+ "������һ�������������ˡ�����");
								} else if (HP8_person > 0) {
									tip.append("��ϲ��ɹ���ܹ������ʿ\n" + "����һ����ս��\n"
											+ "����ʧ��" + (person[3] - HP8_person)
											+ "��Ѫ\n" + "�����"
											+ monster.strongMan[4] + "��Ǯ\n");
									person[3] = HP8_person;
									person[4] += monster.strongMan[4];
									realMap[stairs][j] = 1;
									realMap[stairs][person_site] = 0;
									site[j].setName("1");
									site[j].setIcon(defaultIcon);
									site[person_site].setName("0");
									site[person_site].setIcon(null);
									show_blood.setText("HP=" + person[3]);
									show_money.setText("��Ǯ=" + person[4]);
									addExperience(monster.strongMan[5],time8);
								}
							} else
								attackUnderDefence();

							break;

						// �����ʯͷ����ս
						case 18:
							tip.setText("��������ʯͷ�� \n");
							int HP9_stoneMan = monster.stoneMan[3];
							int HP9_person = person[3];
							int time9 = 1;
							if (person[1] > monster.stoneMan[2]) {
								while (HP9_person > 0 && HP9_stoneMan > 0) {
									tip.append("��" + time9 + "�غ�\n");
									if (monster.stoneMan[1] > person[2]) {
										HP9_person -= monster.stoneMan[1]
												- person[2];
										tip.append("����ʧ"
												+ (monster.stoneMan[1] - person[2])
												+ "��Ѫ\n");
										if (HP9_person > 0)
											tip.append("����" + HP9_person
													+ "��Ѫ\n");
										else
											tip.append("���Ѿ�����\n");
									} else
										tip.append("��û����ʧһ��Ѫ\n");
									if (person[1] > monster.stoneMan[2]) {
										HP9_stoneMan -= person[1]
												- monster.stoneMan[2];
										tip.append("����ʯͷ����ʧ"
												+ (person[1] - monster.stoneMan[2])
												+ "��Ѫ,\n");
										if (HP9_stoneMan > 0)
											tip.append("����" + HP9_stoneMan
													+ "��Ѫ\n");
										else
											tip.append("����ʯͷ���Ѿ�����\n");
									}
									time9++;
								}
								if (HP9_person <= 0) {
									tip.append("�������ʯͷ����ս����������\n"
											+ "�Һ������к���֮��\n"
											+ "�㱻�Ȼ�һص������ʯͷ��֮ǰ��״̬\n"
											+ "������ѡ����ʵĹ��\n"
											+ "���ǿ�Լ����������ٴγ��ԣ�\n"
											+ "������һ�������������ˡ�����");
								} else if (HP9_person > 0) {
									tip.append("��ϲ��ɹ���ܹ���ʯͷ��\n" + "����һ����ս��\n"
											+ "�㹲��ʧ��"
											+ (person[3] - HP9_person) + "��Ѫ\n"
											+ "�����" + monster.stoneMan[4]
											+ "��Ǯ\n");
									person[3] = HP9_person;
									person[4] += monster.stoneMan[4];
									realMap[stairs][j] = 1;
									realMap[stairs][person_site] = 0;
									site[j].setName("1");
									site[j].setIcon(defaultIcon);
									site[person_site].setName("0");
									site[person_site].setIcon(null);
									show_blood.setText("HP=" + person[3]);
									show_money.setText("��Ǯ=" + person[4]);
									addExperience(monster.stoneMan[5],time9);
								}
							} else
								attackUnderDefence();
							break;
						// ��������ʿ��ս
						case 19:
							tip.setText("�����������ʿ \n");
							int HP10_goldDefender = monster.goldDefender[3];
							int HP10_person = person[3];
							int time10 = 1;
							if (person[1] > monster.goldDefender[2]) {
								while (HP10_person > 0 && HP10_goldDefender > 0) {
									tip.append("��" + time10 + "�غ�\n");
									if (monster.goldDefender[1] > person[2]) {
										HP10_person -= monster.goldDefender[1]
												- person[2];
										tip.append("����ʧ"
												+ (monster.goldDefender[1] - person[2])
												+ "��Ѫ\n");
										if (HP10_person > 0)
											tip.append("����" + HP10_person
													+ "��Ѫ\n");
										else
											tip.append("���Ѿ�����\n");
									} else
										tip.append("��û����ʧһ��Ѫ");
									if (person[1] > monster.goldDefender[2]) {
										HP10_goldDefender -= person[1]
												- monster.goldDefender[2];
										tip.append("�������ʿ��ʧ"
												+ (person[1] - monster.goldDefender[2])
												+ "��Ѫ,\n");
										if (HP10_goldDefender > 0)
											tip.append("����" + HP10_goldDefender
													+ "��Ѫ\n");
										else
											tip.append("�������ʿ�Ѿ�����\n");
									}
									time10++;
								}
								if (HP10_person <= 0) {
									tip.append("����������ʿ��ս����������\n"
											+ "�Һ������к���֮��\n"
											+ "�㱻�Ȼ�һص���������ʿ֮ǰ��״̬\n"
											+ "������ѡ����ʵĹ��\n"
											+ "���ǿ�Լ����������ٴγ��ԣ�\n"
											+ "������һ�������������ˡ�����");
								} else if (HP10_person > 0) {
									tip.append("��ϲ��ɹ���ܹ������ʿ\n" + "����һ����ս��\n"
											+ "����ʧ��"
											+ (person[3] - HP10_person)
											+ "��Ѫ\n" + "�����"
											+ monster.goldDefender[4] + "��Ǯ\n");
									person[3] = HP10_person;
									person[4] += monster.goldDefender[4];
									realMap[stairs][j] = 1;
									realMap[stairs][person_site] = 0;
									site[j].setName("1");
									site[j].setIcon(defaultIcon);
									site[person_site].setName("0");
									site[person_site].setIcon(null);
									show_blood.setText("HP=" + person[3]);
									show_money.setText("��Ǯ=" + person[4]);
									addExperience(monster.goldDefender[5],time10);
								}
							} else
								attackUnderDefence();

							break;

						// ������ħ����ս
						case 20:
							tip.setText("���������ħ�� \n");
							int HP11_princeOfTheDevils = monster.princeOfTheDevils[3];
							int HP11_person = person[3];
							int time11 = 1;
							if (person[1] > monster.princeOfTheDevils[2]) {
								while (HP11_person > 0 && HP11_princeOfTheDevils > 0) {
									tip.append("��" + time11 + "�غ�\n");
									if (monster.princeOfTheDevils[1] > person[2]) {
										HP11_person -= monster.princeOfTheDevils[1]
												- person[2];
										tip.append("����ʧ"
												+ (monster.princeOfTheDevils[1] - person[2])
												+ "��Ѫ\n");
										if (HP11_person > 0)
											tip.append("����" + HP11_person
													+ "��Ѫ\n");
										else
											tip.append("���Ѿ�����\n");
									} else
										tip.append("��û����ʧһ��Ѫ");
									if (person[1] > monster.princeOfTheDevils[2]) {
										HP11_princeOfTheDevils -= person[1]
												- monster.princeOfTheDevils[2];
										tip.append("�����ħ����ʧ"
												+ (person[1] - monster.princeOfTheDevils[2])
												+ "��Ѫ,\n");
										if (HP11_princeOfTheDevils > 0)
											tip.append("����" + HP11_princeOfTheDevils
													+ "��Ѫ\n");
										else
											tip.append("�������ʿ�Ѿ�����\n");
									}
									time11++;
								}
								if (HP11_person <= 0) {
									tip.append("��������ħ����ս����������\n"
											+ "�Һ������к���֮��\n"
											+ "�㱻�Ȼ�һص�������ħ��֮ǰ��״̬\n"
											+ "������ѡ����ʵĹ��\n"
											+ "���ǿ�Լ����������ٴγ��ԣ�\n"
											+ "������һ�������������ˡ�����");
								} else if (HP11_person > 0) {
									tip.append("��ϲ��ɹ���ܹ����ħ��\n" + "����һ����ս��\n"
											+ "����ʧ��"
											+ (person[3] - HP11_person)
											+ "��Ѫ\n" + "�����"
											+ monster.princeOfTheDevils[4] + "��Ǯ\n");
									person[3] = HP11_person;
									person[4] += monster.princeOfTheDevils[4];
									realMap[stairs][j] = 1;
									realMap[stairs][person_site] = 0;
									site[j].setName("1");
									site[j].setIcon(defaultIcon);
									site[person_site].setName("0");
									site[person_site].setIcon(null);
									show_blood.setText("HP=" + person[3]);
									show_money.setText("��Ǯ=" + person[4]);
									addExperience(monster.princeOfTheDevils[5],time11);
								}
							} else
								attackUnderDefence();

							break;
							
						// ��¥
						case 21:
							stairs++;
							show_stairs.setText("��ħ��" + stairs + "��");
							UpAndDownStairsDrawMap();
							break;

						// ��¥
						case 22:
							stairs--;
							show_stairs.setText("��ħ��" + stairs + "��");
							UpAndDownStairsDrawMap();
							break;
						
						//��
						case 23:
							if(person[7]>0)
							{
								JOptionPane.showMessageDialog(null, "����һ��Կ�״���");
								tip.setText("�ɹ�����");
								person[7]--;
								show_key.setText("Կ��="+person[7]+"��");
								realMap[stairs][j] = 0;
								site[j].setName("0");
								site[j].setIcon(null);
							}
							else
							{
								JOptionPane.showMessageDialog(null, "��û��Կ�ף�û������\nȥ�ռ�Կ�װ�");
							}
							break;
						case 24:
							tip.setText("�õ�һ��Կ��");

							person[7]++;
							show_key.setText("Կ��="+person[7]+"��");
							realMap[stairs][j] = 1;
							realMap[stairs][person_site] = 0;
							site[j].setName("1");
							site[j].setIcon(defaultIcon);
							site[person_site].setName("0");
							site[person_site].setIcon(null);
							break;
							
							//������������
						case 25:
							tip.setText("������������");
							JOptionPane.showMessageDialog(null, "���ǵ�����������ˣ�\n" +
									"����������Ի����̵���û�еĶ��������һ�����ˡ�\n" +
									"��Ҫ�úÿ���һ�£���Ϊ������л���������\n");
							String[] choose11 = { 
									"�����Ǯ���ң�������ͬ����Ŀ��ս����",
									"�����Ǯ���ң�������ͬ����Ŀ�ķ�����",
									"�����Ǯ���ң�������Ǯ��100����Ѫ",
									//"����10Ԫ,������5ս������5������",
									//"����10Ԫ,������5ս������HP500",
									//"����10Ԫ,������5��������500HP",
									"�´���˵" };

							// �����Ի���
							String yourchoose11 = (String) JOptionPane
									.showInputDialog(null, "��������������һ�����ף�",
											"��������",
											JOptionPane.QUESTION_MESSAGE, null,
											choose11, choose11[0]);

							if (yourchoose11 == choose11[0])
							{
								person[1]+=person[4];
								person[4]=0;
								show_attack.setText("������=" + person[1]);
								show_money.setText("��Ǯ=" + person[4]);
							}
							else if(yourchoose11 == choose11[1])
							{
								person[2]+=person[4];
								person[4]=0;
								show_defense.setText("������=" + person[2]);
								show_money.setText("��Ǯ=" + person[4]);
							}
							else if(yourchoose11 == choose11[2])
							{
								person[3]+=100*person[4];
								person[4]=0;
								show_blood.setText("HP=" + person[3]);
								show_money.setText("��Ǯ=" + person[4]);
							}
							
							JOptionPane.showMessageDialog(null, "�´��ټ���");
							site[j].setName("0");
							site[j].setIcon(null);
							break;
							//����ħŮ
						case 26:
							tip.setText("��������ħŮ \n");
							int HP12_MagicGirl = monster.MagicGirl[3];
							int HP12_person = person[3];
							int time12 = 1;
							if (person[1] > monster.MagicGirl[2]) {
								while (HP12_person > 0 && HP12_MagicGirl > 0) {
									tip.append("��" + time12 + "�غ�\n");
									if (monster.MagicGirl[1] > person[2]) {
										HP12_person -= monster.MagicGirl[1]
												- person[2];
										tip.append("����ʧ"
												+ (monster.MagicGirl[1] - person[2])
												+ "��Ѫ\n");
										if (HP12_person > 0)
											tip.append("����" + HP12_person
													+ "��Ѫ\n");
										else
											tip.append("���Ѿ�����\n");
									} else
										tip.append("��û����ʧһ��Ѫ");
									if (person[1] > monster.MagicGirl[2]) {
										HP12_MagicGirl -= person[1]
												- monster.MagicGirl[2];
										tip.append("����ħŮ��ʧ"
												+ (person[1] - monster.MagicGirl[2])
												+ "��Ѫ,\n");
										if (HP12_MagicGirl > 0)
											tip.append("����" + HP12_MagicGirl
													+ "��Ѫ\n");
										else
											tip.append("�������ʿ�Ѿ�����\n");
									}
									time12++;
								}
								if (HP12_person <= 0) {
									tip.append("�������ħŮ��ս����������\n"
											+ "�Һ������к���֮��\n"
											+ "�㱻�Ȼ�һص������ħŮ֮ǰ��״̬\n"
											+ "������ѡ����ʵĹ��\n"
											+ "���ǿ�Լ����������ٴγ��ԣ�\n"
											+ "������һ�������������ˡ�����");
								} else if (HP12_person > 0) {
									tip.append("��ϲ��ɹ���ܹ���ħŮ\n" + "����һ����ս��\n"
											+ "����ʧ��"
											+ (person[3] - HP12_person)
											+ "��Ѫ\n" + "�����"
											+ monster.MagicGirl[4] + "��Ǯ\n");
									person[3] = HP12_person;
									person[4] += monster.MagicGirl[4];
									realMap[stairs][j] = 1;
									realMap[stairs][person_site] = 0;
									site[j].setName("1");
									site[j].setIcon(defaultIcon);
									site[person_site].setName("0");
									site[person_site].setIcon(null);
									show_blood.setText("HP=" + person[3]);
									show_money.setText("��Ǯ=" + person[4]);
									addExperience(monster.MagicGirl[5],time12);
								}
							} else
								attackUnderDefence();

							break;
							
						//���������ռ�ħ��
						case 27:
							tip.setText("���������ռ�ħ�� \n");
							int HP13_LastEvilSpirit = monster.LastEvilSpirit[3];
							int HP13_person = person[3];
							int time13 = 1;
							if (person[1] > monster.LastEvilSpirit[2]) {
								while (HP13_person > 0
										&& HP13_LastEvilSpirit > 0) {
									tip.append("��" + time13 + "�غ�\n");
									if (monster.LastEvilSpirit[1] > person[2]) {
										HP13_person -= monster.LastEvilSpirit[1]
												- person[2];
										tip.append("����ʧ"
												+ (monster.LastEvilSpirit[1] - person[2])
												+ "��Ѫ\n");
										if (HP13_person > 0)
											tip.append("����" + HP13_person
													+ "��Ѫ\n");
										else
											tip.append("���Ѿ�����\n");
									} else
										tip.append("��û����ʧһ��Ѫ");
									if (person[1] > monster.LastEvilSpirit[2]) {
										HP13_LastEvilSpirit -= person[1]
												- monster.LastEvilSpirit[2];
										tip.append("�����ħ����ʧ"
												+ (person[1] - monster.LastEvilSpirit[2])
												+ "��Ѫ,\n");
										if (HP13_LastEvilSpirit > 0)
											tip.append("����"
													+ HP13_LastEvilSpirit
													+ "��Ѫ\n");
										else
											tip.append("�����ħ���Ѿ�����\n");
									}
									time13++;
								}
								if (HP13_person <= 0) {
									tip.append("��������ħ����ս����������\n"
											+ "�Һ������к���֮��\n"
											+ "�㱻�Ȼ�һص�������ħ��֮ǰ��״̬\n"
											+ "������ѡ����ʵĹ��\n"
											+ "���ǿ�Լ����������ٴγ��ԣ�\n"
											+ "������һ�������������ˡ�����");
								} else if (HP13_person > 0) {
									tip.append("��ϲ��ɹ���ܹ����ħ��\n" + "����һ����ս��\n"
											+ "����ʧ��"
											+ (person[3] - HP13_person)
											+ "��Ѫ\n" + "�����"
											+ monster.LastEvilSpirit[4]
											+ "��Ǯ\n");
									person[3] = HP13_person;
									person[4] += monster.LastEvilSpirit[4];
									int k1 = hero.insert(person[1], person[2],
											person[3], person[4],person[5],person[6]);
									if (k1 <= 10)
										JOptionPane.showMessageDialog(null,
												"��ϲ������ʤ��\n" + "����ΪӢ�۰��" + k1
														+ "��������");
									else
										JOptionPane.showMessageDialog(null,
												"��ϲ������ʤ��\n����ϧû����Ӣ�۰�\n�������´�Ŭ��");
									realMap[stairs][j] = 1;
									realMap[stairs][person_site] = 0;
									site[j].setName("1");
									site[j].setIcon(defaultIcon);
									site[person_site].setName("0");
									site[person_site].setIcon(null);
									show_blood.setText("HP=" + person[3]);
									show_money.setText("��Ǯ=" + person[4]);
								}
							} else
								attackUnderDefence();
							break;
						}
							
					}

					// ������λ�ò����˵��Ա�ʱ
					else {
						tip.setText("��һ��һ�����ߣ�\n��Ҫ̰��Ŷ������");
					}
				}
			});
		}
	}

	// ����¥ʱ����¥ʱ�ػ��ͼ
	static void UpAndDownStairsDrawMap() {
		for (int i = 0; i < 100; i++) {
			// ���ݵ�ͼ�ĸ���λ�õĴ��ţ����λ���
			switch (realMap[stairs][i]) {
			// ǽ��
			case -1:
				site[i].setBackground(Color.BLACK);
				site[i].setIcon(null);
				site[i].setName("" + realMap[stairs][i]);
				break;

			// �յ�
			case 0:
				site[i].setBackground(null);
				site[i].setIcon(null);
				site[i].setName("" + realMap[stairs][i]);
				break;

			// ��
			case 1:
				site[i].setBackground(null);
				site[i].setIcon(defaultIcon);
				site[i].setName("" + realMap[stairs][i]);
				break;

			// СѪƿ
			case 2:
				site[i].setBackground(null);
				site[i].setIcon(new ImageIcon("picture/smallBloodBottle.png"));
				site[i].setName("" + realMap[stairs][i]);
				break;

			// ��Ѫƿ
			case 3:
				site[i].setBackground(null);
				site[i].setIcon(new ImageIcon("picture/bigBloodBottle.png"));
				site[i].setName("" + realMap[stairs][i]);
				break;

			// �챦ʯ
			case 4:
				site[i].setBackground(null);
				site[i].setIcon(new ImageIcon("picture/ruby.jpg"));
				site[i].setName("" + realMap[stairs][i]);
				break;

			// ����ʯ
			case 5:
				site[i].setBackground(null);
				site[i].setIcon(new ImageIcon("picture/sapphire.jpg"));
				site[i].setName("" + realMap[stairs][i]);
				break;

			// �ƽ�
			case 6:
				site[i].setBackground(null);
				site[i].setIcon(new ImageIcon("picture/goldenSword.png"));
				site[i].setName("" + realMap[stairs][i]);
				break;

			// �ƽ��
			case 7:
				site[i].setBackground(null);
				site[i].setIcon(new ImageIcon("picture/goldShield.png"));
				site[i].setName("" + realMap[stairs][i]);
				break;

			// С�̵�
			case 8:
				site[i].setBackground(null);
				site[i].setIcon(new ImageIcon("picture/smallShop.png"));
				site[i].setName("" + realMap[stairs][i]);
				break;

			// ���̵�
			case 9:
				site[i].setBackground(null);
				site[i].setIcon(new ImageIcon("picture/bigShop.png"));
				site[i].setName("" + realMap[stairs][i]);
				break;
			case 10:
				site[i].setBackground(null);
				site[i].setIcon(new ImageIcon("picture/greenBubble.png"));
				site[i].setName("" + realMap[stairs][i]);
				break;
			case 11:
				site[i].setBackground(null);
				site[i].setIcon(new ImageIcon("picture/blueBubble.png"));
				site[i].setName("" + realMap[stairs][i]);
				break;
			case 12:
				site[i].setBackground(null);
				site[i].setIcon(new ImageIcon("picture/redBubble.png"));
				site[i].setName("" + realMap[stairs][i]);
				break;
			case 13:
				site[i].setBackground(null);
				site[i].setIcon(new ImageIcon("picture/smallBat.png"));
				site[i].setName("" + realMap[stairs][i]);
				break;
			case 14:
				site[i].setBackground(null);
				site[i].setIcon(new ImageIcon("picture/bigBat.png"));
				site[i].setName("" + realMap[stairs][i]);
				break;
			case 15:
				site[i].setBackground(null);
				site[i].setIcon(new ImageIcon("picture/blackBubble.png"));
				site[i].setName("" + realMap[stairs][i]);
				break;
			case 16:
				site[i].setBackground(null);
				site[i].setIcon(new ImageIcon("picture/redBat.png"));
				site[i].setName("" + realMap[stairs][i]);
				break;
			case 17:
				site[i].setBackground(null);
				site[i].setIcon(new ImageIcon("picture/strongMan.png"));
				site[i].setName("" + realMap[stairs][i]);
				break;
			case 18:
				site[i].setBackground(null);
				site[i].setIcon(new ImageIcon("picture/stoneMan.png"));
				site[i].setName("" + realMap[stairs][i]);
				break;
			case 19:
				site[i].setBackground(null);
				site[i].setIcon(new ImageIcon("picture/goldDefender.png"));
				site[i].setName("" + realMap[stairs][i]);
				break;
			case 20:
				site[i].setBackground(null);
				site[i].setIcon(new ImageIcon("picture/princeOfTheDevils.png"));
				site[i].setName("" + realMap[stairs][i]);
				break;
			case 21:
				site[i].setBackground(null);
				site[i].setIcon(new ImageIcon("picture/goUpstairs.png"));
				site[i].setName("" + realMap[stairs][i]);
				break;
			case 22:
				site[i].setBackground(null);
				site[i].setIcon(new ImageIcon("picture/goDownstairs.png"));
				site[i].setName("" + realMap[stairs][i]);
				break;
			case 23:
				site[i].setBackground(null);
				site[i].setIcon(new ImageIcon("picture/door.png"));
				site[i].setName("" + realMap[stairs][i]);
				break;
			case 24:
				site[i].setBackground(null);
				site[i].setIcon(new ImageIcon("picture/key.png"));
				site[i].setName("" + realMap[stairs][i]);
				break;
			case 25:
				site[i].setBackground(null);
				site[i].setIcon(new ImageIcon("picture/MysteriousBusinessman.png"));
				site[i].setName("" + realMap[stairs][i]);
				break;
			case 26:
				site[i].setBackground(null);
				site[i].setIcon(new ImageIcon("picture/MagicGirl.jpg"));
				site[i].setName("" + realMap[stairs][i]);
				break;
			case 27:
				site[i].setBackground(null);
				site[i].setIcon(new ImageIcon("picture/LastEvilSpirit.GIF"));
				site[i].setName("" + realMap[stairs][i]);
				break;
			}
		}
	}

	// ���Ƶ�ͼ
	static void DrawMap() {
		// ����ͼ�и�������Ƴ�
		map.removeAll();
		for (int i = 0; i < 100; i++) {
			// ���ݵ�ͼ����λ�õĴ��Ż�����ͼ
			switch (realMap[stairs][i]) {
			case -1:
				site[i] = new JButton();
				site[i].setBackground(Color.BLACK);
				site[i].setName("" + realMap[stairs][i]);
				break;
			case 0:
				site[i] = new JButton();
				site[i].setName("" + realMap[stairs][i]);
				break;
			case 1:
				site[i] = new JButton(defaultIcon);
				site[i].setName("" + realMap[stairs][i]);
				break;
			case 2:
				site[i] = new JButton(new ImageIcon(
						"picture/smallBloodBottle.png"));
				site[i].setName("" + realMap[stairs][i]);
				break;
			case 3:
				site[i] = new JButton(new ImageIcon(
						"picture/bigBloodBottle.png"));
				site[i].setName("" + realMap[stairs][i]);
				break;
			case 4:
				site[i] = new JButton(new ImageIcon("picture/ruby.jpg"));
				site[i].setName("" + realMap[stairs][i]);
				break;
			case 5:
				site[i] = new JButton(new ImageIcon("picture/sapphire.jpg"));
				site[i].setName("" + realMap[stairs][i]);
				break;
			case 6:
				site[i] = new JButton(new ImageIcon("picture/goldenSword.png"));
				site[i].setName("" + realMap[stairs][i]);
				break;
			case 7:
				site[i] = new JButton(new ImageIcon("picture/goldShield.png"));
				site[i].setName("" + realMap[stairs][i]);
				break;
			case 8:
				site[i] = new JButton(new ImageIcon("picture/smallShop.png"));
				site[i].setName("" + realMap[stairs][i]);
				break;
			case 9:
				site[i] = new JButton(new ImageIcon("picture/bigShop.png"));
				site[i].setName("" + realMap[stairs][i]);
				break;
			case 10:
				site[i] = new JButton(new ImageIcon("picture/greenBubble.png"));
				site[i].setName("" + realMap[stairs][i]);
				break;
			case 11:
				site[i] = new JButton(new ImageIcon("picture/blueBubble.png"));
				site[i].setName("" + realMap[stairs][i]);
				break;
			case 12:
				site[i] = new JButton(new ImageIcon("picture/redBubble.png"));
				site[i].setName("" + realMap[stairs][i]);
				break;
			case 13:
				site[i] = new JButton(new ImageIcon("picture/smallBat.png"));
				site[i].setName("" + realMap[stairs][i]);
				break;
			case 14:
				site[i] = new JButton(new ImageIcon("picture/bigBat.png"));
				site[i].setName("" + realMap[stairs][i]);
				break;
			case 15:
				site[i] = new JButton(new ImageIcon("picture/blackBubble.png"));
				site[i].setName("" + realMap[stairs][i]);
				break;
			case 16:
				site[i] = new JButton(new ImageIcon("picture/redBat.png"));
				site[i].setName("" + realMap[stairs][i]);
				break;
			case 17:
				site[i] = new JButton(new ImageIcon("picture/strongMan.png"));
				site[i].setName("" + realMap[stairs][i]);
				break;
			case 18:
				site[i] = new JButton(new ImageIcon("picture/stoneMan.png"));
				site[i].setName("" + realMap[stairs][i]);
				break;
			case 19:
				site[i] = new JButton(new ImageIcon("picture/goldDefender.png"));
				site[i].setName("" + realMap[stairs][i]);
				break;
			case 20:
				site[i] = new JButton(new ImageIcon(
						"picture/princeOfTheDevils.png"));
				site[i].setName("" + realMap[stairs][i]);
				break;
			case 21:
				site[i] = new JButton(new ImageIcon("picture/goUpstairs.png"));
				site[i].setName("" + realMap[stairs][i]);
				break;
			case 22:
				site[i] = new JButton(new ImageIcon("picture/goDownstairs.png"));
				site[i].setName("" + realMap[stairs][i]);
				break;
			case 23:
				site[i]=new JButton(new ImageIcon("picture/door.png"));
				site[i].setName(""+realMap[stairs][i]);
				break;
			case 24:
				site[i]=new JButton(new ImageIcon("picture/key.png"));
				site[i].setName(""+realMap[stairs][i]);
				break;
			case 25:
				site[i]=new JButton(new ImageIcon("pisture/MysteriousBusinessman.png"));
				site[i].setName(""+realMap[stairs][i]);
				break;
			case 26:
				site[i]=new JButton(new ImageIcon("picture/MagicGirl.jpg"));
				site[i].setName(""+realMap[stairs][i]);
				break;
			case 27:
				site[i]=new JButton(new ImageIcon("picture/LastEvilSpirit.GIF"));
				site[i].setName(""+realMap[stairs][i]);
				break;
			}
			map.add(site[i]);

		}
	}

}
