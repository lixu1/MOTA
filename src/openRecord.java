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

//存档文件格式，
//人的4个属性，楼梯数
//realMap各个值

public class openRecord extends JFrame {
	FileInputStream fin;
	DataInputStream dfin;

	public openRecord() {
		super("打开存档");
		Container c = getContentPane();
		c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));

		// 打开文档选项
		final JRadioButton[] choose = { new JRadioButton("打开1号档"),
				new JRadioButton("打开2号档"), new JRadioButton("打开3号档"),
				new JRadioButton("打开4号档"), new JRadioButton("打开5号档"),
				new JRadioButton("打开6号档"), new JRadioButton("打开7号档"),
				new JRadioButton("打开8号档"), new JRadioButton("打开9号档"),
				new JRadioButton("打开10号档"), };
		ButtonGroup rg = new ButtonGroup();
		for (int i = 0; i < choose.length; i++) {
			c.add(choose[i]);
			rg.add(choose[i]);
		}
		choose[0].setSelected(true);
		for (int i = 1; i < 10; i++)
			choose[i].setSelected(false);

		// 确定按钮及事件处理
		JButton confirmperson = new JButton("确定");
		confirmperson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// 打开一号档
					if (choose[0].isSelected()) {
						fin = new FileInputStream("record/record1.txt");
						dfin = new DataInputStream(fin);
						recordIntoMap();
						JOptionPane.showMessageDialog(null, "成功打开1号档\n"
								+ "你的状态已经成功进入1号档所保存的状态，\n" + "请继续游戏\n");
						Main.tip.setText("成功打开1号档");
					}

					// 打开二号档
					else if (choose[1].isSelected()) {
						fin = new FileInputStream("record/record2.txt");
						dfin = new DataInputStream(fin);
						recordIntoMap();
						JOptionPane.showMessageDialog(null, "成功打开2号档\n"
								+ "你的状态已经成功进入2号档所保存的状态，\n" + "请继续游戏\n");
						Main.tip.setText("成功打开2号档");
					}

					// 打开三号档
					else if (choose[2].isSelected()) {
						fin = new FileInputStream("record/record3.txt");
						dfin = new DataInputStream(fin);
						recordIntoMap();
						JOptionPane.showMessageDialog(null, "成功打开3号档\n"
								+ "你的状态已经成功进入3号档所保存的状态，\n" + "请继续游戏\n");
						Main.tip.setText("成功打开3号档");
					}

					// 打开4号档
					else if (choose[3].isSelected()) {
						fin = new FileInputStream("record/record4.txt");
						dfin = new DataInputStream(fin);
						recordIntoMap();
						JOptionPane.showMessageDialog(null, "成功打开4号档\n"
								+ "你的状态已经成功进入4号档所保存的状态，\n" + "请继续游戏\n");
						Main.tip.setText("成功打开4号档");
					}

					// 打开5号档
					else if (choose[4].isSelected()) {
						fin = new FileInputStream("record/record5.txt");
						dfin = new DataInputStream(fin);
						recordIntoMap();
						JOptionPane.showMessageDialog(null, "成功打开5号档\n"
								+ "你的状态已经成功进入5号档所保存的状态，\n" + "请继续游戏\n");
						Main.tip.setText("成功打开5号档");
					}

					// 打开6号档
					else if (choose[5].isSelected()) {
						fin = new FileInputStream("record/record6.txt");
						dfin = new DataInputStream(fin);
						recordIntoMap();
						JOptionPane.showMessageDialog(null, "成功打开6号档\n"
								+ "你的状态已经成功进入6号档所保存的状态，\n" + "请继续游戏\n");
						Main.tip.setText("成功打开6号档");
					}

					// 打开7号档
					else if (choose[6].isSelected()) {
						fin = new FileInputStream("record/record7.txt");
						dfin = new DataInputStream(fin);
						recordIntoMap();
						JOptionPane.showMessageDialog(null, "成功打开7号档\n"
								+ "你的状态已经成功进入7号档所保存的状态，\n" + "请继续游戏\n");
						Main.tip.setText("成功打开7号档");
					}

					// 打开8号档
					else if (choose[7].isSelected()) {
						fin = new FileInputStream("record/record8.txt");
						dfin = new DataInputStream(fin);
						recordIntoMap();
						JOptionPane.showMessageDialog(null, "成功打开8号档\n"
								+ "你的状态已经成功进入8号档所保存的状态，\n" + "请继续游戏\n");
						Main.tip.setText("成功打开8号档");
					}

					// 打开9号档
					else if (choose[8].isSelected()) {
						fin = new FileInputStream("record/record9.txt");
						dfin = new DataInputStream(fin);
						recordIntoMap();
						JOptionPane.showMessageDialog(null, "成功打开9号档\n"
								+ "你的状态已经成功进入9号档所保存的状态，\n" + "请继续游戏\n");
						Main.tip.setText("成功打开9号档");
					}

					// 打开10号档
					else if (choose[9].isSelected()) {
						fin = new FileInputStream("record/record10.txt");
						dfin = new DataInputStream(fin);
						recordIntoMap();
						JOptionPane.showMessageDialog(null, "成功打开10号档\n"
								+ "你的状态已经成功进入10号档所保存的状态，\n" + "请继续游戏\n");
						Main.tip.setText("成功打开10号档");
					}

					// 打开档后，刷新界面上地图
					Main.UpAndDownStairsDrawMap();

					// 打开档后，刷新人的各个属性
					Main.show_stairs.setText("在魔塔" + Main.stairs + "层");
					Main.show_attack.setText("攻击力=" + Main.person[1]);
					Main.show_defense.setText("防御力=" + Main.person[2]);
					Main.show_blood.setText("HP=" + Main.person[3]);
					Main.show_money.setText("金钱=" + Main.person[4]);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "打开存档失败\n"
							+ "请确定你要打开的存档有效！");
					System.err.println("发生异常:" + e1);
					e1.printStackTrace();
				}
			}
		});
		c.add(confirmperson);
	}

	// 从文件读出记录
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
