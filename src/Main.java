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
	// 人的代号，攻击力，防御力，血量，金钱，5等级，6经验值,7钥匙数
	static int person[] = { 1, 10, 10, 1000, 0 ,0,0,0};
	// attack defense blood money rank experience

	// 人现在的楼梯数
	static int stairs = 1;
	// 人的图标
	static Icon defaultIcon;

	// 主面板
	static JFrame app = new JFrame("魔塔");
	// 各层的地图面板
	static JPanel map = new JPanel();
	// 地图上的各个位置
	static JButton site[] = new JButton[100];
	// 玩家的地图
	static int realMap[][] = new int[7][100];
	// 展示玩家在哪个层的标签
	static JLabel show_stairs = new JLabel("在魔塔" + stairs + "层");
	// 提示信息文本域
	static JTextArea tip = new JTextArea("", 2, 5);
	static JScrollPane TIP = new JScrollPane(tip);

	// 帮助信息文本域
	static JTextArea help = new JTextArea("魔塔", 5, 10);

	// 展示玩家攻击力,防御力,HP,金钱的标签
	static JLabel show_attack = new JLabel("攻击力=" + person[1]);
	static JLabel show_defense = new JLabel("防御力=" + person[2]);
	static JLabel show_blood = new JLabel("HP=" + person[3]);
	static JLabel show_money = new JLabel("金钱=" + person[4]);
	static JLabel show_rank=new JLabel("等级="+person[5]);
	static JLabel show_experience=new JLabel("经验="+person[6]);
	static JLabel show_key=new JLabel("钥匙="+person[7]+"个");

	// 各个菜单项
	static JMenuItem[][] mI = {
			{ new JMenuItem("新游戏(N)"), new JMenuItem("英雄榜(Y)"),
					new JMenuItem("退出(X)") },
			{ new JMenuItem("改变角色(Q)"), new JMenuItem("存档(W)"),
					new JMenuItem("打开存档(E)") },
			{ new JMenuItem("查看道具和怪物信息(V)"), new JMenuItem("关于魔塔(A)") } };

	// 选择角色，即你自己的图片
	static void chooseyourself() {
		// 角色的四种选择
		JLabel chooseperson[] = {
				new JLabel("人物1", new ImageIcon("picture/person1.png"),
						JLabel.LEFT),
				new JLabel("人物2", new ImageIcon("picture/person2.png"),
						JLabel.LEFT),
				new JLabel("人物3", new ImageIcon("picture/person3.png"),
						JLabel.LEFT),
				new JLabel("人物4", new ImageIcon("picture/person4.png"),
						JLabel.LEFT), };
		// 选择角色对话框
		final JDialog d = new JDialog(app, "选择角色", true);
		Container c = d.getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.LEFT));
		for (int i = 0; i < chooseperson.length; i++)
			c.add(chooseperson[i]);
		JRadioButton[] choose = { new JRadioButton("选择人物1"),
				new JRadioButton("选择人物2"), new JRadioButton("选择人物3"),
				new JRadioButton("选择人物4") };
		ButtonGroup rg = new ButtonGroup();
		for (int i = 0; i < choose.length; i++) {
			c.add(choose[i]);
			rg.add(choose[i]);
		}
		choose[0].setSelected(true);
		choose[1].setSelected(false);
		choose[2].setSelected(false);
		choose[3].setSelected(false);

		JButton confirmperson = new JButton("确定");
		confirmperson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				d.dispose();
			}
		});

		c.add(confirmperson);

		d.setSize(500, 200);
		d.setVisible(true);

		// 根据用户的选择，改变玩家的图标
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

	// 初始化函数，包括初始化地图，绘制地图，初始化人的各个属性
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

		// 初始化各个状态标签
		show_stairs.setText("在魔塔" + stairs + "层");
		show_attack.setText("攻击力=" + person[1]);
		show_defense.setText("防御力=" + person[2]);
		show_blood.setText("HP=" + person[3]);
		show_money.setText("金钱=" + person[4]);
		show_rank.setText("等级="+person[5]);
		show_experience.setText("经验值="+person[6]);
		show_key.setText("钥匙="+person[7]+"个");
		
		tip.setText("现在开始");
		help.setText("这里是魔塔，共计四层。" + "\n击败四层最终的大魔王是你的使命。" + "\n鼠标点击任务周围的空地可"
				+ "\n控制人物移动，获取道具，击败怪物，" + "\n打败怪物获取金钱后去商店"
				+ "\n提升自己的攻击力，防御力与HP。" + "\n右上角是你的人物当前状态，" + "\n中间部分是当前提示信息。"
				+ "\n设置菜单内可以改变你的角色样貌，" + "\n也可以存档与读取。" + "\n怪物信息与道具信息"
				+ "\n可以在帮助菜单进行查看。" + "\n游戏菜单内设有英雄榜，" + "\n首先依据通关时金钱的多少，"
				+ "\n再根据血量的多少进行排名。" + "\n一路向前，需要你的智慧。" + "\n祝游戏愉快。\n");
	}

	// 为各个菜单项添加事件处理
	static void menuAddActionListener() {
		// 为新游戏添加事件处理
		mI[0][0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				init();
				tip.setText("游戏重新开始,\n如果画面有些问题，\n请改变一下画面大小\n刷新一下即可。");
			}
		});

		// 为英雄榜添加事件处理
		mI[0][1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hero app = new hero();
				app.setSize(500, 300);
				app.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				app.setVisible(true);
			}
		});

		// 为退出添加事件处理
		mI[0][2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		// 为改变角色添加事件处理
		mI[1][0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooseyourself();
				UpAndDownStairsDrawMap();
			}
		});

		// 为存档添加事件处理
		mI[1][1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveRecord a = new saveRecord();
				a.setSize(200, 400);
				a.setVisible(true);
				a.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});

		// 为打开存档添加事件处理
		mI[1][2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openRecord a = new openRecord();
				a.setSize(200, 400);
				a.setVisible(true);
				a.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});

		// 为查看道具和怪物信息(V)添加事件处理
		mI[2][0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InformationFrame a = new InformationFrame();
				a.setSize(1300, 800);
				a.setVisible(true);
				a.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});

		// 为关于魔塔(A)添加事件处理
		mI[2][1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "本游戏是北航软院学生团队做得JAVA项目\n"
						+ "成员有：李旭，袁张璋，王禹，曾喜滨\n" + "有什么问题，请联系我们\n" + "版权所有\n");
			}
		});
	}
	
	//增加经验
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
		
		show_experience.setText("经验值="+person[6]);
		
		int nowRank=judgeRank(person[6]);
		
		if(nowRank>previousRank)
		{
			person[5]=nowRank;
			show_rank.setText("等级="+person[5]);
			JOptionPane.showMessageDialog(null, "恭喜你升了"+(nowRank-previousRank)+
					"级，升到了"+person[5]+"级!" +
					"\n免费赠送你5战斗力，5防御力，500HP");
			person[1]+=(nowRank-previousRank)*5;
			person[2]+=(nowRank-previousRank)*5;
			person[3]+=(nowRank-previousRank)*500;
			show_attack.setText("攻击力=" + person[1]);
			show_defense.setText("防御力=" + person[2]);
			show_blood.setText("HP=" + person[3]);
		}
	}
	//判断等级
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

	// 主函数
	public static void main(String args[]) {
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setSize(800, 600);
		Container c = app.getContentPane();
		c.setLayout(new BorderLayout());

		// 菜单设置
		JMenuBar mBar = new JMenuBar();
		mBar.setBackground(Color.cyan);
		app.setJMenuBar(mBar);
		JMenu[] m = { new JMenu("游戏(G)"), new JMenu("设置(F)"),
				new JMenu("帮助(H)") };
		char[][] mC = { { 'G', 'F', 'H' }, { 'N', 'Y', 'X' },
				{ 'Q', 'W', 'E' }, { 'V', 'A' } };

		// 为菜单添加菜单项
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
		// 为菜单项添加事件处理
		menuAddActionListener();

		// 地图面板设置
		map.setLayout(new GridLayout(10, 10));
		init();
		c.add(map, BorderLayout.CENTER);

		// 控制面板设置
		JPanel Control = new JPanel();
		Control.setLayout(new BoxLayout(Control, BoxLayout.Y_AXIS));
		Control.add(new JLabel("你现在的状态："));
		Control.setBackground(Color.GREEN);

		Control.add(show_stairs);
		Control.add(show_attack);
		Control.add(show_defense);
		Control.add(show_blood);
		Control.add(show_money);
		Control.add(show_rank);
		Control.add(show_experience);
		Control.add(show_key);
		
		Control.add(new JLabel("提示信息:"));
		tip.setEditable(false);
		Control.add(TIP);
		Control.add(new JLabel("帮助:"));
		help.setEditable(false);
		JScrollPane HELP = new JScrollPane(help);
		Control.add(HELP);
		c.add(Control, BorderLayout.EAST);

		app.setVisible(true);
	}

	// 当你的攻击没有怪物防御高时 的处理函数
	static void attackUnderDefence() {
		tip.append("你的攻击还没他的防御高呢，\n" + "怎么可能打败他?\n" + "再修炼修炼再来吧！");
	}

	// 给每一个位置加上事件处理
	static void siteAddActionListener() {
		for (int i = 0; i < 100; i++) {
			final int j = i;
			site[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// 如果点击位置在人的旁边时，进行处理
					if (realMap[stairs][j] == 1
							|| ((j - 1) > 0 && (j - 1) % 10 < 9 && realMap[stairs][j - 1] == 1)
							|| ((j + 1) < 100 && (j + 1) % 10 > 0 && realMap[stairs][j + 1] == 1)
							|| ((j > 10) && (realMap[stairs][j - 10] == 1))
							|| (j < 90 && realMap[stairs][j + 10] == 1)) {
						// 确定人的位置
						int person_site = 0;// 人的位置
						if (((j - 1) > 0 && (j - 1) % 10 < 9 && realMap[stairs][j - 1] == 1))
							person_site = j - 1;
						else if (((j + 1) < 100 && (j + 1) % 10 > 0 && realMap[stairs][j + 1] == 1))
							person_site = j + 1;
						else if (((j > 10) && (realMap[stairs][j - 10] == 1)))
							person_site = j - 10;
						else if (j < 90 && realMap[stairs][j + 10] == 1)
							person_site = j + 10;

						// 确定点击位置的代号，分别处理
						int k = Integer.valueOf(site[j].getName());
						switch (k) {
						// 遇到墙
						case -1:
							tip.setText("这里是墙，不能通过");
							break;
						// 空地
						case 0:
							tip.setText("这里是空地，顺利通过");
							realMap[stairs][j] = 1;
							realMap[stairs][person_site] = 0;
							site[j].setName("1");
							site[j].setIcon(defaultIcon);
							site[person_site].setName("0");
							site[person_site].setIcon(null);
							break;
						// 点着自己
						case 1:
							tip.setText("你点着自己啦\n请选择你的下一个\n合适前进位置");
							break;
						// 得到一个小血瓶
						case 2:
							tip.setText("得到一个小血瓶，\nHP增加"
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
						// 得到一个大血瓶
						case 3:
							tip.setText("得到一个大血瓶，\nHP增加"
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
						// 得到一个红宝石
						case 4:
							tip.setText("得到一个红宝石，\n攻击力增加" + monster.ruby[1]);

							person[1] += monster.ruby[1];
							show_attack.setText("攻击力=" + person[1]);
							realMap[stairs][j] = 1;
							realMap[stairs][person_site] = 0;
							site[j].setName("1");
							site[j].setIcon(defaultIcon);
							site[person_site].setName("0");
							site[person_site].setIcon(null);
							break;
							
						// 得到一个蓝宝石
						case 5:
							tip.setText("得到一个蓝宝石，\n防御力增加" + monster.sapphire[2]);
							person[2] += monster.sapphire[2];
							show_defense.setText("防御力=" + person[2]);
							realMap[stairs][j] = 1;
							realMap[stairs][person_site] = 0;
							site[j].setName("1");
							site[j].setIcon(defaultIcon);
							site[person_site].setName("0");
							site[person_site].setIcon(null);
							break;
							
						// 得到一个黄金剑
						case 6:
							tip.setText("得到一个黄金剑，\n攻击力增加"
									+ monster.goldenSword[1]);
							person[1] += monster.goldenSword[1];
							show_attack.setText("攻击力=" + person[1]);
							realMap[stairs][j] = 1;
							realMap[stairs][person_site] = 0;
							site[j].setName("1");
							site[j].setIcon(defaultIcon);
							site[person_site].setName("0");
							site[person_site].setIcon(null);
							break;
						// 得到一个黄金盾
						case 7:
							tip.setText("得到一个黄金盾，\n攻击力增加"
									+ monster.goldShield[2]);
							person[2] += monster.goldShield[2];
							show_defense.setText("防御力=" + person[2]);
							realMap[stairs][j] = 1;
							realMap[stairs][person_site] = 0;
							site[j].setName("1");
							site[j].setIcon(defaultIcon);
							site[person_site].setName("0");
							site[person_site].setIcon(null);
							break;
						// 进入小商店买东西
						case 8:
							tip.setText("进入小商店，\n请选择你要买的东西\n");
							String[] choose = { "战斗力+" + monster.smallShop[1],
									"防御力+" + monster.smallShop[2],
									"HP+" + monster.smallShop[3], "下次再买" };

							// 买东西对话框
							String yourchoose = (String) JOptionPane
									.showInputDialog(null, "你期望使用"
											+ monster.smallShop[4]
											+ "个金钱，购买什么？", "小商店",
											JOptionPane.QUESTION_MESSAGE, null,
											choose, choose[0]);

							// 买战斗力
							if (yourchoose == choose[0]) {
								if (person[4] < monster.smallShop[4]) {
									tip.append("你的金钱数太少，\n" + "购买"
											+ monster.smallShop[1] + "战斗力失败\n"
											+ "接着打怪赚更多的钱吧!\n");
								} else {
									tip.append("你花费了" + monster.smallShop[4]
											+ "个金钱，\n" + "购买了"
											+ monster.smallShop[1] + "战斗力\n");
									person[1] += monster.smallShop[1];
									person[4] -= monster.smallShop[4];
									show_attack.setText("攻击力=" + person[1]);
									show_money.setText("金钱=" + person[4]);
								}
							}

							// 买防御力
							else if (yourchoose == choose[1]) {
								if (person[4] < monster.smallShop[4]) {
									tip.append("你的金钱数太少，\n" + "购买"
											+ monster.smallShop[2] + "防御力失败\n"
											+ "接着打怪赚更多的钱吧!");
								} else {
									tip.append("你花费了" + monster.smallShop[4]
											+ "个金钱，\n购买了"
											+ monster.smallShop[2] + "防御力");
									person[2] += monster.smallShop[2];
									person[4] -= monster.smallShop[4];
									show_defense.setText("防御力=" + person[2]);
									show_money.setText("金钱=" + person[4]);
								}
							}

							// 买HP
							else if (yourchoose == choose[2]) {
								if (person[4] < monster.smallShop[4]) {
									tip.append("你的金钱数太少，\n" + "购买"
											+ monster.smallShop[3] + "HP失败\n"
											+ "接着打怪赚更多的钱吧!");
								} else {
									tip.append("你花费了" + monster.smallShop[4]
											+ "个金钱，\n购买了"
											+ monster.smallShop[3] + "HP");
									person[3] += monster.smallShop[3];
									person[4] -= monster.smallShop[4];
									show_blood.setText("HP=" + person[3]);
									show_money.setText("金钱=" + person[4]);
								}
							}

							// 下次再买
							else if (yourchoose == choose[3]) {
								tip.append("你没有购买任何东西");
							}
							break;
						// 进入大商店买东西
						case 9:
							tip.setText("进入大商店，\n请选择你要买的东西\n");
							String[] choose1 = { "战斗力+" + monster.bigShop[1],
									"防御力+" + monster.bigShop[2],
									"HP+" + monster.bigShop[3], "下次再买" };

							// 买东西对话框
							String yourchoose1 = (String) JOptionPane
									.showInputDialog(null, "你期望使用"
											+ monster.bigShop[4] + "个金钱，购买什么？",
											"小商店",
											JOptionPane.QUESTION_MESSAGE, null,
											choose1, choose1[0]);

							// 买战斗力
							if (yourchoose1 == choose1[0]) {
								if (person[4] < monster.bigShop[4]) {
									tip.append("你的金钱数太少，\n" + "购买"
											+ monster.bigShop[1] + "战斗力失败\n"
											+ "接着打怪赚更多的钱吧!");
								} else {
									tip.append("你花费了" + monster.bigShop[4]
											+ "个金钱，\n购买了" + monster.bigShop[1]
											+ "战斗力\n");
									person[1] += monster.bigShop[1];
									person[4] -= monster.bigShop[4];
									show_attack.setText("攻击力=" + person[1]);
									show_money.setText("金钱=" + person[4]);
								}
							}

							// 买防御力
							else if (yourchoose1 == choose1[1]) {
								if (person[4] < monster.bigShop[4]) {
									tip.append("你的金钱数太少，\n" + "购买"
											+ monster.bigShop[2] + "防御力失败\n"
											+ "接着打怪赚更多的钱吧!");
								} else {
									tip.append("你花费了" + monster.bigShop[4]
											+ "个金钱，\n购买了" + monster.bigShop[2]
											+ "防御力\n");
									person[2] += monster.bigShop[2];
									person[4] -= monster.bigShop[4];
									show_defense.setText("防御力=" + person[2]);
									show_money.setText("金钱=" + person[4]);
								}
							}

							// 买HP
							else if (yourchoose1 == choose1[2]) {
								if (person[4] < monster.bigShop[4]) {
									tip.append("你的金钱数太少，\n" + "购买"
											+ monster.bigShop[3] + "HP失败\n"
											+ "接着打怪赚更多的钱吧!");
								} else {
									tip.append("你花费了" + monster.bigShop[4]
											+ "个金钱，\n购买了" + monster.bigShop[3]
											+ "HP\n");
									person[3] += monster.bigShop[3];
									person[4] -= monster.bigShop[4];
									show_blood.setText("HP=" + person[3]);
									show_money.setText("金钱=" + person[4]);
								}
							}

							// 下次再买
							else if (yourchoose1 == choose1[3]) {
								tip.append("你没有购买任何东西");
							}
							break;

						// 与怪物绿泡泡作战
						case 10:
							tip.setText("遇到怪物绿泡泡\n");
							int HP1_greenBubble = monster.greenBubble[3];
							int HP1_person = person[3];
							int time1 = 1;

							// 人的战斗力比绿泡泡防御力高时处理
							if (person[1] > monster.greenBubble[2]) {
								// 人与绿泡泡一块发出攻击，有一个死时退出循环
								while (HP1_greenBubble > 0 && HP1_person > 0) {
									tip.append("第" + time1 + "回合\n");
									if (monster.greenBubble[1] > person[2]) {
										HP1_person -= monster.greenBubble[1]
												- person[2];
										tip.append("你损失"
												+ (monster.greenBubble[1] - person[2])
												+ "滴血,\n");
										if (HP1_person > 0)
											tip.append("还有" + HP1_person
													+ "滴血\n");
										else
											tip.append("你已经死了\n");
									} else
										tip.append("你没有损失一滴血\n");
									if (person[1] > monster.greenBubble[2]) {
										HP1_greenBubble -= person[1]
												- monster.greenBubble[2];
										tip.append("怪物绿泡泡损失"
												+ (person[1] - monster.greenBubble[2])
												+ "滴血,\n");
										if (HP1_greenBubble > 0)
											tip.append("还有" + HP1_greenBubble
													+ "滴血\n");
										else
											tip.append("怪物绿泡泡已经死亡\n");
									} else
										tip.append("怪物绿泡泡没有损失一滴血\n");
									time1++;
								}
								// 如果人在战斗中死了
								if (HP1_person <= 0) {
									tip.append("在与怪物绿泡泡作战中你牺牲了\n"
											+ "幸好上天有好生之德\n"
											+ "你被救活，且回到打怪物绿泡泡之前的状态\n"
											+ "请明智选择合适的怪物，\n"
											+ "或加强自己的能力后再次尝试，\n"
											+ "否则下一次你可能真的死了。。。");
								}
								// 如果人战胜了绿泡泡
								else if (HP1_person > 0) {
									tip.append("恭喜你成功打败怪物绿泡泡\n" + "在这一次作战中\n"
											+ "你损失了" + (person[3] - HP1_person)
											+ "滴血\n" + "获得了"
											+ monster.greenBubble[4] + "金钱\n");
									person[3] = HP1_person;
									person[4] += monster.greenBubble[4];
									realMap[stairs][j] = 1;
									realMap[stairs][person_site] = 0;
									site[j].setName("1");
									site[j].setIcon(defaultIcon);
									site[person_site].setName("0");
									site[person_site].setIcon(null);
									show_blood.setText("HP=" + person[3]);
									show_money.setText("金钱=" + person[4]);
									addExperience(monster.greenBubble[5],time1);
								}
							}
							// 当人的战斗力低于怪物绿泡泡防御力时
							else
								attackUnderDefence();
							break;

						// 与怪物蓝泡泡作战
						case 11:
							tip.setText("遇到怪物蓝泡泡\n");
							int HP2_blueBubble = monster.blueBubble[3];
							int HP2_person = person[3];
							int time2 = 1;
							if (person[1] > monster.blueBubble[2]) {
								while (HP2_blueBubble > 0 && HP2_person > 0) {
									tip.append("第" + time2 + "回合\n");
									if (monster.blueBubble[1] > person[2]) {
										HP2_person -= monster.blueBubble[1]
												- person[2];
										tip.append("你损失"
												+ (monster.blueBubble[1] - person[2])
												+ "滴血,\n");
										if (HP2_person > 0)
											tip.append("还有" + HP2_person
													+ "滴血\n");
										else
											tip.append("你已经死了\n");
									} else
										tip.append("你没有损失一滴血\n");
									if (person[1] > monster.blueBubble[2]) {
										HP2_blueBubble -= person[1]
												- monster.blueBubble[2];
										tip.append("怪物蓝泡泡损失"
												+ (person[1] - monster.blueBubble[2])
												+ "滴血,\n" + "还有"
												+ HP2_blueBubble + "滴血\n");
									}
									time2++;
								}
								if (HP2_person <= 0) {
									tip.append("在与怪物蓝泡泡作战中你牺牲了\n"
											+ "幸好上天有好生之德\n"
											+ "你被救活，且回到打怪物蓝泡泡之前的状态\n"
											+ "请明智选择合适的怪物，\n"
											+ "或加强自己的能力后再次尝试，\n"
											+ "否则下一次你可能真的死了。。。");
								} else if (HP2_person > 0) {
									tip.append("恭喜你成功打败怪物蓝泡泡\n" + "在这一次作战中\n"
											+ "你损失了" + (person[3] - HP2_person)
											+ "滴血\n" + "获得了"
											+ monster.blueBubble[4] + "金钱\n");
									person[3] = HP2_person;
									person[4] += monster.blueBubble[4];
									realMap[stairs][j] = 1;
									realMap[stairs][person_site] = 0;
									site[j].setName("1");
									site[j].setIcon(defaultIcon);
									site[person_site].setName("0");
									site[person_site].setIcon(null);
									show_blood.setText("HP=" + person[3]);
									show_money.setText("金钱=" + person[4]);
									addExperience(monster.blueBubble[5],time2);
								}
							} else
								attackUnderDefence();
							break;

						// 与怪物红泡泡作战
						case 12:
							tip.setText("遇到怪物红泡泡\n");
							int HP3_redBubble = monster.redBubble[3];
							int HP3_person = person[3];
							int time3 = 1;
							if (person[1] > monster.redBubble[2]) {
								while (HP3_redBubble > 0 && HP3_person > 0) {
									tip.append("第" + time3 + "回合\n");
									if (monster.redBubble[1] > person[2]) {
										HP3_person -= monster.redBubble[1]
												- person[2];
										tip.append("你损失"
												+ (monster.redBubble[1] - person[2])
												+ "滴血\n");
										if (HP3_person > 0)
											tip.append("还有" + HP3_person
													+ "滴血\n");
										else
											tip.append("你已经死了\n");
									} else
										tip.append("你没有损失一滴血\n");
									if (person[1] > monster.redBubble[2]) {
										HP3_redBubble -= person[1]
												- monster.redBubble[2];
										tip.append("怪物红泡泡损失"
												+ (person[1] - monster.redBubble[2])
												+ "滴血,\n");
										if (HP3_redBubble > 0)
											tip.append("还有" + HP3_redBubble
													+ "滴血\n");
										else
											tip.append("怪物红泡泡已经死了\n");
									}
									time3++;
								}
								if (HP3_person <= 0) {
									tip.append("在与怪物红泡泡作战中你牺牲了\n"
											+ "幸好上天有好生之德\n"
											+ "你被救活，且回到打怪物红泡泡之前的状态\n"
											+ "请明智选择合适的怪物，\n"
											+ "或加强自己的能力后再次尝试，\n"
											+ "否则下一次你可能真的死了。。。\n");
								} else if (HP3_person > 0) {
									tip.append("恭喜你成功打败怪物红泡泡\n" + "在这一次作战中\n"
											+ "你损失了" + (person[3] - HP3_person)
											+ "滴血\n" + "获得了"
											+ monster.redBubble[4] + "金钱\n");
									person[3] = HP3_person;
									person[4] += monster.redBubble[4];
									realMap[stairs][j] = 1;
									realMap[stairs][person_site] = 0;
									site[j].setName("1");
									site[j].setIcon(defaultIcon);
									site[person_site].setName("0");
									site[person_site].setIcon(null);
									show_blood.setText("HP=" + person[3]);
									show_money.setText("金钱=" + person[4]);
									addExperience(monster.redBubble[5],time3);
								}
							} else
								attackUnderDefence();

							break;

						// 与怪物小蝙蝠作战
						case 13:
							tip.setText("遇到怪物小蝙蝠\n");
							int HP4_smallBat = monster.smallBat[3];
							int HP4_person = person[3];
							int time4 = 1;
							if (person[1] > monster.smallBat[2]) {
								while (HP4_smallBat > 0 && HP4_person > 0) {
									tip.append("第" + time4 + "回合\n");
									if (monster.smallBat[1] > person[2]) {
										HP4_person -= monster.smallBat[1]
												- person[2];
										tip.append("你损失"
												+ (monster.smallBat[1] - person[2])
												+ "滴血\n");
										if (HP4_person > 0)
											tip.append("还有" + HP4_person
													+ "滴血\n");
										else
											tip.append("你已经死了\n");
									} else
										tip.append("你没有损失一滴血\n");
									if (person[1] > monster.smallBat[2]) {
										HP4_smallBat -= person[1]
												- monster.smallBat[2];
										tip.append("怪物小蝙蝠损失"
												+ (person[1] - monster.smallBat[2])
												+ "滴血,\n");
										if (HP4_smallBat > 0)
											tip.append("还有" + HP4_smallBat
													+ "滴血\n");
										else
											tip.append("怪物小蝙蝠已经死了\n");
									}
									time4++;
								}
								if (HP4_person <= 0) {
									tip.append("在与怪物小蝙蝠作战中你牺牲了\n"
											+ "幸好上天有好生之德\n"
											+ "你被救活，且回到打怪物小蝙蝠之前的状态\n"
											+ "请明智选择合适的怪物，\n"
											+ "或加强自己的能力后再次尝试，\n"
											+ "否则下一次你可能真的死了。。。");
								} else if (HP4_person > 0) {
									tip.append("恭喜你成功打败怪物小蝙蝠\n" + "在这一次作战中\n"
											+ "你损失了" + (person[3] - HP4_person)
											+ "滴血\n" + "获得了"
											+ monster.smallBat[4] + "金钱\n");
									person[3] = HP4_person;
									person[4] += monster.smallBat[4];
									realMap[stairs][j] = 1;
									realMap[stairs][person_site] = 0;
									site[j].setName("1");
									site[j].setIcon(defaultIcon);
									site[person_site].setName("0");
									site[person_site].setIcon(null);
									show_blood.setText("HP=" + person[3]);
									show_money.setText("金钱=" + person[4]);
									addExperience(monster.redBubble[5],time4);
								}
							} else
								attackUnderDefence();

							break;

						// 与怪物大蝙蝠作战
						case 14:
							tip.setText("遇到怪物大蝙蝠 \n");
							int HP5_bigBat = monster.bigBat[3];
							int HP5_person = person[3];
							int time5 = 1;
							if (person[1] > monster.bigBat[2]) {
								while (HP5_bigBat > 0 && HP5_person > 0) {
									tip.append("第" + time5 + "回合\n");
									if (monster.bigBat[1] > person[2]) {
										HP5_person -= monster.bigBat[1]
												- person[2];
										tip.append("你损失"
												+ (monster.bigBat[1] - person[2])
												+ "滴血\n");
										if (HP5_person > 0)
											tip.append("还有" + HP5_person
													+ "滴血\n");
										else
											tip.append("你已经死了\n");
									} else
										tip.append("你没有损失一滴血\n");
									if (person[1] > monster.bigBat[2]) {
										HP5_bigBat -= person[1]
												- monster.bigBat[2];
										tip.append("怪物大蝙蝠损失"
												+ (person[1] - monster.bigBat[2])
												+ "滴血,\n");
										if (HP5_bigBat > 0)
											tip.append("还有" + HP5_bigBat
													+ "滴血\n");
										else
											tip.append("怪物大蝙蝠已经死了\n");
									}
									time5++;
								}
								if (HP5_person <= 0) {
									tip.append("在与怪物大蝙蝠作战中你牺牲了\n"
											+ "幸好上天有好生之德\n"
											+ "你被救活，且回到打怪物大蝙蝠之前的状态\n"
											+ "请明智选择合适的怪物，\n"
											+ "或加强自己的能力后再次尝试，\n"
											+ "否则下一次你可能真的死了。。。");
								} else if (HP5_person > 0) {
									tip.append("恭喜你成功打败怪物大蝙蝠\n" + "在这一次作战中\n"
											+ "你损失了" + (person[3] - HP5_person)
											+ "滴血\n" + "获得了"
											+ monster.smallBat[4] + "金钱\n");
									person[3] = HP5_person;
									person[4] += monster.bigBat[4];
									realMap[stairs][j] = 1;
									realMap[stairs][person_site] = 0;
									site[j].setName("1");
									site[j].setIcon(defaultIcon);
									site[person_site].setName("0");
									site[person_site].setIcon(null);
									show_blood.setText("HP=" + person[3]);
									show_money.setText("金钱=" + person[4]);
									addExperience(monster.bigBat[5],time5);
								}
							} else
								attackUnderDefence();

							break;

						// 与怪物黑泡泡作战
						case 15:
							tip.setText("遇到怪物黑泡泡 \n");
							int HP6_blackBubble = monster.blackBubble[3];
							int HP6_person = person[3];
							int time6 = 1;
							if (person[1] > monster.blackBubble[2]) {
								while (HP6_blackBubble > 0 && HP6_person > 0) {
									tip.append("第" + time6 + "回合\n");
									if (monster.blackBubble[1] > person[2]) {
										HP6_person -= monster.blackBubble[1]
												- person[2];
										tip.append("你损失"
												+ (monster.blackBubble[1] - person[2])
												+ "滴血," + "还有" + HP6_person
												+ "滴血\n");
									} else
										tip.append("你没有损失一滴血\n");
									if (person[1] > monster.blackBubble[2]) {
										HP6_blackBubble -= person[1]
												- monster.blackBubble[2];
										tip.append("怪物黑泡泡损失"
												+ (person[1] - monster.bigBat[2])
												+ "滴血,\n" + "还有"
												+ HP6_blackBubble + "滴血\n");
									}
									time6++;
								}
								if (HP6_person <= 0) {
									tip.append("在与怪物黑泡泡作战中你牺牲了\n"
											+ "幸好上天有好生之德\n"
											+ "你被救活，且回到打怪物黑泡泡之前的状态\n"
											+ "请明智选择合适的怪物，\n"
											+ "或加强自己的能力后再次尝试，\n"
											+ "否则下一次你可能真的死了。。。");
								} else if (HP6_person > 0) {
									tip.append("恭喜你成功打败怪物黑泡泡\n" + "在这一次作战中\n"
											+ "你损失了" + (person[3] - HP6_person)
											+ "滴血\n" + "获得了"
											+ monster.blackBubble[4] + "金钱\n");
									person[3] = HP6_person;
									person[4] += monster.blackBubble[4];
									realMap[stairs][j] = 1;
									realMap[stairs][person_site] = 0;
									site[j].setName("1");
									site[j].setIcon(defaultIcon);
									site[person_site].setName("0");
									site[person_site].setIcon(null);
									show_blood.setText("HP=" + person[3]);
									show_money.setText("金钱=" + person[4]);
									addExperience(monster.blackBubble[5],time6);
								}
							} else
								attackUnderDefence();

							break;

						// 与怪物红蝙蝠作战
						case 16:
							tip.setText("遇到怪物红蝙蝠 \n");
							int HP7_redBat = monster.redBat[3];
							int HP7_person = person[3];
							int time7 = 1;
							if (person[1] > monster.redBat[2]) {
								while (HP7_redBat > 0 && HP7_person > 0) {
									tip.append("第" + time7 + "回合\n");
									if (monster.redBat[1] > person[2]) {
										HP7_person -= monster.redBat[1]
												- person[2];
										tip.append("你损失"
												+ (monster.redBat[1] - person[2])
												+ "滴血\n");
										if (HP7_person > 0)
											tip.append("还有" + HP7_person
													+ "滴血\n");
										else
											tip.append("你已经死了\n");
									} else
										tip.append("你没有损失一滴血\n");
									if (person[1] > monster.redBat[2]) {
										HP7_redBat -= person[1]
												- monster.redBat[2];
										tip.append("怪物红蝙蝠损失"
												+ (person[1] - monster.redBat[2])
												+ "滴血,\n");
										if (HP7_redBat > 0)
											tip.append("还有" + HP7_redBat
													+ "滴血\n");
										else
											tip.append("怪物红蝙蝠已经死了\n");
									}
									time7++;
								}
								if (HP7_person <= 0) {
									tip.append("在与怪物红蝙蝠作战中你牺牲了\n"
											+ "幸好上天有好生之德\n"
											+ "你被救活，且回到打怪物红蝙蝠之前的状态\n"
											+ "请明智选择合适的怪物，\n"
											+ "或加强自己的能力后再次尝试，\n"
											+ "否则下一次你可能真的死了。。。");
								} else if (HP7_person > 0) {
									tip.append("恭喜你成功打败怪物红蝙蝠\n" + "在这一次作战中\n"
											+ "你损失了" + (person[3] - HP7_person)
											+ "滴血\n" + "获得了"
											+ monster.redBat[4] + "金钱\n");
									person[3] = HP7_person;
									person[4] += monster.redBat[4];
									realMap[stairs][j] = 1;
									realMap[stairs][person_site] = 0;
									site[j].setName("1");
									site[j].setIcon(defaultIcon);
									site[person_site].setName("0");
									site[person_site].setIcon(null);
									show_blood.setText("HP=" + person[3]);
									show_money.setText("金钱=" + person[4]);
									addExperience(monster.redBat[5],time7);
								}
							} else
								attackUnderDefence();

							break;

						// 与怪物大力士作战
						case 17:
							tip.setText("遇到怪物大力士 \n");
							int HP8_strongMan = monster.strongMan[3];
							int HP8_person = person[3];
							int time8 = 1;
							if (person[1] > monster.strongMan[2]) {
								while (HP8_strongMan > 0 && HP8_person > 0) {
									tip.append("第" + time8 + "回合\n");
									if (monster.strongMan[1] > person[2]) {
										HP8_person -= monster.strongMan[1]
												- person[2];
										tip.append("你损失"
												+ (monster.strongMan[1] - person[2])
												+ "滴血\n");
										if (HP8_person > 0)
											tip.append("还有" + HP8_person
													+ "滴血\n");
										else
											tip.append("你已经死了\n");
									} else
										tip.append("你没有损失一滴血\n");
									if (person[1] > monster.strongMan[2]) {
										HP8_strongMan -= person[1]
												- monster.strongMan[2];
										tip.append("怪物大力士损失"
												+ (person[1] - monster.strongMan[2])
												+ "滴血,\n");
										if (HP8_strongMan > 0)
											tip.append("还有" + HP8_strongMan
													+ "滴血\n");
										else
											tip.append("怪物大力士已经死了\n");
									}
									time8++;
								}
								if (HP8_person <= 0) {
									tip.append("在与怪物大力士作战中你牺牲了\n"
											+ "幸好上天有好生之德\n"
											+ "你被救活，且回到打怪物大力士之前的状态\n"
											+ "请明智选择合适的怪物，\n"
											+ "或加强自己的能力后再次尝试，\n"
											+ "否则下一次你可能真的死了。。。");
								} else if (HP8_person > 0) {
									tip.append("恭喜你成功打败怪物大力士\n" + "在这一次作战中\n"
											+ "你损失了" + (person[3] - HP8_person)
											+ "滴血\n" + "获得了"
											+ monster.strongMan[4] + "金钱\n");
									person[3] = HP8_person;
									person[4] += monster.strongMan[4];
									realMap[stairs][j] = 1;
									realMap[stairs][person_site] = 0;
									site[j].setName("1");
									site[j].setIcon(defaultIcon);
									site[person_site].setName("0");
									site[person_site].setIcon(null);
									show_blood.setText("HP=" + person[3]);
									show_money.setText("金钱=" + person[4]);
									addExperience(monster.strongMan[5],time8);
								}
							} else
								attackUnderDefence();

							break;

						// 与怪物石头人作战
						case 18:
							tip.setText("遇到怪物石头人 \n");
							int HP9_stoneMan = monster.stoneMan[3];
							int HP9_person = person[3];
							int time9 = 1;
							if (person[1] > monster.stoneMan[2]) {
								while (HP9_person > 0 && HP9_stoneMan > 0) {
									tip.append("第" + time9 + "回合\n");
									if (monster.stoneMan[1] > person[2]) {
										HP9_person -= monster.stoneMan[1]
												- person[2];
										tip.append("你损失"
												+ (monster.stoneMan[1] - person[2])
												+ "滴血\n");
										if (HP9_person > 0)
											tip.append("还有" + HP9_person
													+ "滴血\n");
										else
											tip.append("你已经死了\n");
									} else
										tip.append("你没有损失一滴血\n");
									if (person[1] > monster.stoneMan[2]) {
										HP9_stoneMan -= person[1]
												- monster.stoneMan[2];
										tip.append("怪物石头人损失"
												+ (person[1] - monster.stoneMan[2])
												+ "滴血,\n");
										if (HP9_stoneMan > 0)
											tip.append("还有" + HP9_stoneMan
													+ "滴血\n");
										else
											tip.append("怪物石头人已经死了\n");
									}
									time9++;
								}
								if (HP9_person <= 0) {
									tip.append("在与怪物石头人作战中你牺牲了\n"
											+ "幸好上天有好生之德\n"
											+ "你被救活，且回到打怪物石头人之前的状态\n"
											+ "请明智选择合适的怪物，\n"
											+ "或加强自己的能力后再次尝试，\n"
											+ "否则下一次你可能真的死了。。。");
								} else if (HP9_person > 0) {
									tip.append("恭喜你成功打败怪物石头人\n" + "在这一次作战中\n"
											+ "你共损失了"
											+ (person[3] - HP9_person) + "滴血\n"
											+ "获得了" + monster.stoneMan[4]
											+ "金钱\n");
									person[3] = HP9_person;
									person[4] += monster.stoneMan[4];
									realMap[stairs][j] = 1;
									realMap[stairs][person_site] = 0;
									site[j].setName("1");
									site[j].setIcon(defaultIcon);
									site[person_site].setName("0");
									site[person_site].setIcon(null);
									show_blood.setText("HP=" + person[3]);
									show_money.setText("金钱=" + person[4]);
									addExperience(monster.stoneMan[5],time9);
								}
							} else
								attackUnderDefence();
							break;
						// 与怪物金卫士作战
						case 19:
							tip.setText("遇到怪物金卫士 \n");
							int HP10_goldDefender = monster.goldDefender[3];
							int HP10_person = person[3];
							int time10 = 1;
							if (person[1] > monster.goldDefender[2]) {
								while (HP10_person > 0 && HP10_goldDefender > 0) {
									tip.append("第" + time10 + "回合\n");
									if (monster.goldDefender[1] > person[2]) {
										HP10_person -= monster.goldDefender[1]
												- person[2];
										tip.append("你损失"
												+ (monster.goldDefender[1] - person[2])
												+ "滴血\n");
										if (HP10_person > 0)
											tip.append("还有" + HP10_person
													+ "滴血\n");
										else
											tip.append("你已经死了\n");
									} else
										tip.append("你没有损失一滴血");
									if (person[1] > monster.goldDefender[2]) {
										HP10_goldDefender -= person[1]
												- monster.goldDefender[2];
										tip.append("怪物金卫士损失"
												+ (person[1] - monster.goldDefender[2])
												+ "滴血,\n");
										if (HP10_goldDefender > 0)
											tip.append("还有" + HP10_goldDefender
													+ "滴血\n");
										else
											tip.append("怪物金卫士已经死了\n");
									}
									time10++;
								}
								if (HP10_person <= 0) {
									tip.append("在与怪物金卫士作战中你牺牲了\n"
											+ "幸好上天有好生之德\n"
											+ "你被救活，且回到打怪物金卫士之前的状态\n"
											+ "请明智选择合适的怪物，\n"
											+ "或加强自己的能力后再次尝试，\n"
											+ "否则下一次你可能真的死了。。。");
								} else if (HP10_person > 0) {
									tip.append("恭喜你成功打败怪物金卫士\n" + "在这一次作战中\n"
											+ "你损失了"
											+ (person[3] - HP10_person)
											+ "滴血\n" + "获得了"
											+ monster.goldDefender[4] + "金钱\n");
									person[3] = HP10_person;
									person[4] += monster.goldDefender[4];
									realMap[stairs][j] = 1;
									realMap[stairs][person_site] = 0;
									site[j].setName("1");
									site[j].setIcon(defaultIcon);
									site[person_site].setName("0");
									site[person_site].setIcon(null);
									show_blood.setText("HP=" + person[3]);
									show_money.setText("金钱=" + person[4]);
									addExperience(monster.goldDefender[5],time10);
								}
							} else
								attackUnderDefence();

							break;

						// 与怪物大魔王作战
						case 20:
							tip.setText("遇到怪物大魔王 \n");
							int HP11_princeOfTheDevils = monster.princeOfTheDevils[3];
							int HP11_person = person[3];
							int time11 = 1;
							if (person[1] > monster.princeOfTheDevils[2]) {
								while (HP11_person > 0 && HP11_princeOfTheDevils > 0) {
									tip.append("第" + time11 + "回合\n");
									if (monster.princeOfTheDevils[1] > person[2]) {
										HP11_person -= monster.princeOfTheDevils[1]
												- person[2];
										tip.append("你损失"
												+ (monster.princeOfTheDevils[1] - person[2])
												+ "滴血\n");
										if (HP11_person > 0)
											tip.append("还有" + HP11_person
													+ "滴血\n");
										else
											tip.append("你已经死了\n");
									} else
										tip.append("你没有损失一滴血");
									if (person[1] > monster.princeOfTheDevils[2]) {
										HP11_princeOfTheDevils -= person[1]
												- monster.princeOfTheDevils[2];
										tip.append("怪物大魔王损失"
												+ (person[1] - monster.princeOfTheDevils[2])
												+ "滴血,\n");
										if (HP11_princeOfTheDevils > 0)
											tip.append("还有" + HP11_princeOfTheDevils
													+ "滴血\n");
										else
											tip.append("怪物金卫士已经死了\n");
									}
									time11++;
								}
								if (HP11_person <= 0) {
									tip.append("在与怪物大魔王作战中你牺牲了\n"
											+ "幸好上天有好生之德\n"
											+ "你被救活，且回到打怪物大魔王之前的状态\n"
											+ "请明智选择合适的怪物，\n"
											+ "或加强自己的能力后再次尝试，\n"
											+ "否则下一次你可能真的死了。。。");
								} else if (HP11_person > 0) {
									tip.append("恭喜你成功打败怪物大魔王\n" + "在这一次作战中\n"
											+ "你损失了"
											+ (person[3] - HP11_person)
											+ "滴血\n" + "获得了"
											+ monster.princeOfTheDevils[4] + "金钱\n");
									person[3] = HP11_person;
									person[4] += monster.princeOfTheDevils[4];
									realMap[stairs][j] = 1;
									realMap[stairs][person_site] = 0;
									site[j].setName("1");
									site[j].setIcon(defaultIcon);
									site[person_site].setName("0");
									site[person_site].setIcon(null);
									show_blood.setText("HP=" + person[3]);
									show_money.setText("金钱=" + person[4]);
									addExperience(monster.princeOfTheDevils[5],time11);
								}
							} else
								attackUnderDefence();

							break;
							
						// 上楼
						case 21:
							stairs++;
							show_stairs.setText("在魔塔" + stairs + "层");
							UpAndDownStairsDrawMap();
							break;

						// 下楼
						case 22:
							stairs--;
							show_stairs.setText("在魔塔" + stairs + "层");
							UpAndDownStairsDrawMap();
							break;
						
						//门
						case 23:
							if(person[7]>0)
							{
								JOptionPane.showMessageDialog(null, "花费一个钥匙打开门");
								tip.setText("成功打开门");
								person[7]--;
								show_key.setText("钥匙="+person[7]+"个");
								realMap[stairs][j] = 0;
								site[j].setName("0");
								site[j].setIcon(null);
							}
							else
							{
								JOptionPane.showMessageDialog(null, "你没有钥匙，没法打开门\n去收集钥匙吧");
							}
							break;
						case 24:
							tip.setText("得到一个钥匙");

							person[7]++;
							show_key.setText("钥匙="+person[7]+"个");
							realMap[stairs][j] = 1;
							realMap[stairs][person_site] = 0;
							site[j].setName("1");
							site[j].setIcon(defaultIcon);
							site[person_site].setName("0");
							site[person_site].setIcon(null);
							break;
							
							//遇到神秘商人
						case 25:
							tip.setText("遇到神秘商人");
							JOptionPane.showMessageDialog(null, "我是到处游玩的商人，\n" +
									"在我这你可以换到商店里没有的东西，而且会更便宜。\n" +
									"你要好好考虑一下，因为你很少有机会遇到我\n");
							String[] choose11 = { 
									"把你的钱给我，我送你同样数目的战斗力",
									"把你的钱给我，我送你同样数目的防御力",
									"把你的钱给我，我送你钱数100倍的血",
									//"给我10元,我送你5战斗力，5防御力",
									//"给我10元,我送你5战斗力，HP500",
									//"给我10元,我送你5防御力，500HP",
									"下次再说" };

							// 买东西对话框
							String yourchoose11 = (String) JOptionPane
									.showInputDialog(null, "你期望与我做那一个交易？",
											"神秘商人",
											JOptionPane.QUESTION_MESSAGE, null,
											choose11, choose11[0]);

							if (yourchoose11 == choose11[0])
							{
								person[1]+=person[4];
								person[4]=0;
								show_attack.setText("攻击力=" + person[1]);
								show_money.setText("金钱=" + person[4]);
							}
							else if(yourchoose11 == choose11[1])
							{
								person[2]+=person[4];
								person[4]=0;
								show_defense.setText("防御力=" + person[2]);
								show_money.setText("金钱=" + person[4]);
							}
							else if(yourchoose11 == choose11[2])
							{
								person[3]+=100*person[4];
								person[4]=0;
								show_blood.setText("HP=" + person[3]);
								show_money.setText("金钱=" + person[4]);
							}
							
							JOptionPane.showMessageDialog(null, "下次再见了");
							site[j].setName("0");
							site[j].setIcon(null);
							break;
							//遇到魔女
						case 26:
							tip.setText("遇到怪物魔女 \n");
							int HP12_MagicGirl = monster.MagicGirl[3];
							int HP12_person = person[3];
							int time12 = 1;
							if (person[1] > monster.MagicGirl[2]) {
								while (HP12_person > 0 && HP12_MagicGirl > 0) {
									tip.append("第" + time12 + "回合\n");
									if (monster.MagicGirl[1] > person[2]) {
										HP12_person -= monster.MagicGirl[1]
												- person[2];
										tip.append("你损失"
												+ (monster.MagicGirl[1] - person[2])
												+ "滴血\n");
										if (HP12_person > 0)
											tip.append("还有" + HP12_person
													+ "滴血\n");
										else
											tip.append("你已经死了\n");
									} else
										tip.append("你没有损失一滴血");
									if (person[1] > monster.MagicGirl[2]) {
										HP12_MagicGirl -= person[1]
												- monster.MagicGirl[2];
										tip.append("怪物魔女损失"
												+ (person[1] - monster.MagicGirl[2])
												+ "滴血,\n");
										if (HP12_MagicGirl > 0)
											tip.append("还有" + HP12_MagicGirl
													+ "滴血\n");
										else
											tip.append("怪物金卫士已经死了\n");
									}
									time12++;
								}
								if (HP12_person <= 0) {
									tip.append("在与怪物魔女作战中你牺牲了\n"
											+ "幸好上天有好生之德\n"
											+ "你被救活，且回到打怪物魔女之前的状态\n"
											+ "请明智选择合适的怪物，\n"
											+ "或加强自己的能力后再次尝试，\n"
											+ "否则下一次你可能真的死了。。。");
								} else if (HP12_person > 0) {
									tip.append("恭喜你成功打败怪物魔女\n" + "在这一次作战中\n"
											+ "你损失了"
											+ (person[3] - HP12_person)
											+ "滴血\n" + "获得了"
											+ monster.MagicGirl[4] + "金钱\n");
									person[3] = HP12_person;
									person[4] += monster.MagicGirl[4];
									realMap[stairs][j] = 1;
									realMap[stairs][person_site] = 0;
									site[j].setName("1");
									site[j].setIcon(defaultIcon);
									site[person_site].setName("0");
									site[person_site].setIcon(null);
									show_blood.setText("HP=" + person[3]);
									show_money.setText("金钱=" + person[4]);
									addExperience(monster.MagicGirl[5],time12);
								}
							} else
								attackUnderDefence();

							break;
							
						//遇到怪物终极魔王
						case 27:
							tip.setText("遇到怪物终极魔王 \n");
							int HP13_LastEvilSpirit = monster.LastEvilSpirit[3];
							int HP13_person = person[3];
							int time13 = 1;
							if (person[1] > monster.LastEvilSpirit[2]) {
								while (HP13_person > 0
										&& HP13_LastEvilSpirit > 0) {
									tip.append("第" + time13 + "回合\n");
									if (monster.LastEvilSpirit[1] > person[2]) {
										HP13_person -= monster.LastEvilSpirit[1]
												- person[2];
										tip.append("你损失"
												+ (monster.LastEvilSpirit[1] - person[2])
												+ "滴血\n");
										if (HP13_person > 0)
											tip.append("还有" + HP13_person
													+ "滴血\n");
										else
											tip.append("你已经死了\n");
									} else
										tip.append("你没有损失一滴血");
									if (person[1] > monster.LastEvilSpirit[2]) {
										HP13_LastEvilSpirit -= person[1]
												- monster.LastEvilSpirit[2];
										tip.append("怪物大魔王损失"
												+ (person[1] - monster.LastEvilSpirit[2])
												+ "滴血,\n");
										if (HP13_LastEvilSpirit > 0)
											tip.append("还有"
													+ HP13_LastEvilSpirit
													+ "滴血\n");
										else
											tip.append("怪物大魔王已经死了\n");
									}
									time13++;
								}
								if (HP13_person <= 0) {
									tip.append("在与怪物大魔王作战中你牺牲了\n"
											+ "幸好上天有好生之德\n"
											+ "你被救活，且回到打怪物大魔王之前的状态\n"
											+ "请明智选择合适的怪物，\n"
											+ "或加强自己的能力后再次尝试，\n"
											+ "否则下一次你可能真的死了。。。");
								} else if (HP13_person > 0) {
									tip.append("恭喜你成功打败怪物大魔王\n" + "在这一次作战中\n"
											+ "你损失了"
											+ (person[3] - HP13_person)
											+ "滴血\n" + "获得了"
											+ monster.LastEvilSpirit[4]
											+ "金钱\n");
									person[3] = HP13_person;
									person[4] += monster.LastEvilSpirit[4];
									int k1 = hero.insert(person[1], person[2],
											person[3], person[4],person[5],person[6]);
									if (k1 <= 10)
										JOptionPane.showMessageDialog(null,
												"恭喜你最后的胜利\n" + "并成为英雄榜第" + k1
														+ "名！！！");
									else
										JOptionPane.showMessageDialog(null,
												"恭喜你最后的胜利\n但可惜没进入英雄榜\n期望你下次努力");
									realMap[stairs][j] = 1;
									realMap[stairs][person_site] = 0;
									site[j].setName("1");
									site[j].setIcon(defaultIcon);
									site[person_site].setName("0");
									site[person_site].setIcon(null);
									show_blood.setText("HP=" + person[3]);
									show_money.setText("金钱=" + person[4]);
								}
							} else
								attackUnderDefence();
							break;
						}
							
					}

					// 如果点击位置不在人的旁边时
					else {
						tip.setText("请一步一步的走，\n不要贪快哦。。。");
					}
				}
			});
		}
	}

	// 当上楼时或下楼时重绘地图
	static void UpAndDownStairsDrawMap() {
		for (int i = 0; i < 100; i++) {
			// 根据地图的各个位置的代号，依次画出
			switch (realMap[stairs][i]) {
			// 墙壁
			case -1:
				site[i].setBackground(Color.BLACK);
				site[i].setIcon(null);
				site[i].setName("" + realMap[stairs][i]);
				break;

			// 空地
			case 0:
				site[i].setBackground(null);
				site[i].setIcon(null);
				site[i].setName("" + realMap[stairs][i]);
				break;

			// 人
			case 1:
				site[i].setBackground(null);
				site[i].setIcon(defaultIcon);
				site[i].setName("" + realMap[stairs][i]);
				break;

			// 小血瓶
			case 2:
				site[i].setBackground(null);
				site[i].setIcon(new ImageIcon("picture/smallBloodBottle.png"));
				site[i].setName("" + realMap[stairs][i]);
				break;

			// 大血瓶
			case 3:
				site[i].setBackground(null);
				site[i].setIcon(new ImageIcon("picture/bigBloodBottle.png"));
				site[i].setName("" + realMap[stairs][i]);
				break;

			// 红宝石
			case 4:
				site[i].setBackground(null);
				site[i].setIcon(new ImageIcon("picture/ruby.jpg"));
				site[i].setName("" + realMap[stairs][i]);
				break;

			// 蓝宝石
			case 5:
				site[i].setBackground(null);
				site[i].setIcon(new ImageIcon("picture/sapphire.jpg"));
				site[i].setName("" + realMap[stairs][i]);
				break;

			// 黄金剑
			case 6:
				site[i].setBackground(null);
				site[i].setIcon(new ImageIcon("picture/goldenSword.png"));
				site[i].setName("" + realMap[stairs][i]);
				break;

			// 黄金盾
			case 7:
				site[i].setBackground(null);
				site[i].setIcon(new ImageIcon("picture/goldShield.png"));
				site[i].setName("" + realMap[stairs][i]);
				break;

			// 小商店
			case 8:
				site[i].setBackground(null);
				site[i].setIcon(new ImageIcon("picture/smallShop.png"));
				site[i].setName("" + realMap[stairs][i]);
				break;

			// 大商店
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

	// 绘制地图
	static void DrawMap() {
		// 将地图中各个组件移除
		map.removeAll();
		for (int i = 0; i < 100; i++) {
			// 根据地图各个位置的代号画出地图
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
