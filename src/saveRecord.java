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

//存档文件格式，
//人的4个属性，楼梯数
//realMap各个值

public class saveRecord extends JFrame {
	FileOutputStream fout;
	DataOutputStream dfout;

	public saveRecord() {
		super("存档");
		Container c = getContentPane();
		c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));

		// 存档的各个选项
		final JRadioButton[] choose = { new JRadioButton("存入1号档"),
				new JRadioButton("存入2号档"), new JRadioButton("存入3号档"),
				new JRadioButton("存入4号档"), new JRadioButton("存入5号档"),
				new JRadioButton("存入6号档"), new JRadioButton("存入7号档"),
				new JRadioButton("存入8号档"), new JRadioButton("存入9号档"),
				new JRadioButton("存入10号档"), };
		ButtonGroup rg = new ButtonGroup();
		for (int i = 0; i < choose.length; i++) {
			c.add(choose[i]);
			rg.add(choose[i]);
		}
		choose[0].setSelected(true);
		for (int i = 1; i < 10; i++)
			choose[i].setSelected(false);

		// 确定按钮
		JButton confirmperson = new JButton("确定");

		// 确定按钮事件处理
		confirmperson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// 存入一号档
					if (choose[0].isSelected()) {
						fout = new FileOutputStream("record/record1.txt");
						dfout = new DataOutputStream(fout);
						writeIntoRecord();
						JOptionPane.showMessageDialog(null, "你的状态已经成功存入1号档，\n"
								+ "请下次直接打开1号档\n" + "继续游戏");
					}

					// 存入二号档
					else if (choose[1].isSelected()) {
						fout = new FileOutputStream("record/record2.txt");
						dfout = new DataOutputStream(fout);
						writeIntoRecord();
						JOptionPane.showMessageDialog(null, "你的状态已经成功存入2号档，\n"
								+ "请下次直接打开2号档\n" + "继续游戏");
					}

					// 存入三号档
					else if (choose[2].isSelected()) {
						fout = new FileOutputStream("record/record3.txt");
						dfout = new DataOutputStream(fout);
						writeIntoRecord();
						JOptionPane.showMessageDialog(null, "你的状态已经成功存入3号档，\n"
								+ "请下次直接打开3号档\n" + "继续游戏");
					}

					// 存入4号档
					else if (choose[3].isSelected()) {
						fout = new FileOutputStream("record/record4.txt");
						dfout = new DataOutputStream(fout);
						writeIntoRecord();
						JOptionPane.showMessageDialog(null, "你的状态已经成功存入4号档，\n"
								+ "请下次直接打开4号档\n" + "继续游戏");
					}

					// 存入5号档
					else if (choose[4].isSelected()) {
						fout = new FileOutputStream("record/record5.txt");
						dfout = new DataOutputStream(fout);
						writeIntoRecord();
						JOptionPane.showMessageDialog(null, "你的状态已经成功存入5号档，\n"
								+ "请下次直接打开5号档\n" + "继续游戏");
					}

					// 存入6号档
					else if (choose[5].isSelected()) {
						fout = new FileOutputStream("record/record6.txt");
						dfout = new DataOutputStream(fout);
						writeIntoRecord();
						JOptionPane.showMessageDialog(null, "你的状态已经成功存入6号档，\n"
								+ "请下次直接打开6号档\n" + "继续游戏");
					}

					// 存入7号档
					else if (choose[6].isSelected()) {
						fout = new FileOutputStream("record/record7.txt");
						dfout = new DataOutputStream(fout);
						writeIntoRecord();
						JOptionPane.showMessageDialog(null, "你的状态已经成功存入7号档，\n"
								+ "请下次直接打开7号档\n" + "继续游戏");
					}

					// 存入8号档
					else if (choose[7].isSelected()) {
						fout = new FileOutputStream("record/record8.txt");
						dfout = new DataOutputStream(fout);
						writeIntoRecord();
						JOptionPane.showMessageDialog(null, "你的状态已经成功存入8号档，\n"
								+ "请下次直接打开8号档\n" + "继续游戏");
					}

					// 存入9号档
					else if (choose[8].isSelected()) {
						fout = new FileOutputStream("record/record9.txt");
						dfout = new DataOutputStream(fout);
						writeIntoRecord();
						JOptionPane.showMessageDialog(null, "你的状态已经成功存入9号档，\n"
								+ "请下次直接打开9号档\n" + "继续游戏");
					}

					// 存入10号档
					else if (choose[9].isSelected()) {
						fout = new FileOutputStream("record/record10.txt");
						dfout = new DataOutputStream(fout);
						writeIntoRecord();
						JOptionPane.showMessageDialog(null, "你的状态已经成功存入10号档，\n"
								+ "请下次直接打开10号档\n" + "继续游戏");
					}
				}

				// 发生异常 ,存档失败
				catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "存档失败\n"
							+ "请查找相关问题后，再次尝试存档");
					System.err.println("发生异常:" + e1);
					e1.printStackTrace();
				}
			}
		});
		c.add(confirmperson);
	}

	// 把存档写入文件记录
	void writeIntoRecord() {
		try {
			
			//写入人的攻击力，防御力，HP，金钱数，楼层数
			dfout.writeInt(Main.person[1]);
			dfout.writeInt(Main.person[2]);
			dfout.writeInt(Main.person[3]);
			dfout.writeInt(Main.person[4]);
			dfout.writeInt(Main.person[5]);
			dfout.writeInt(Main.person[6]);
			dfout.writeInt(Main.person[7]);
			
			dfout.writeInt(Main.stairs);
			
			//把人的真正的四层地图写入文件
			for (int i = 1; i < 7; i++) {
				for (int j = 0; j < 100; j++)
					dfout.writeInt(Main.realMap[i][j]);
			}
			dfout.flush();
			dfout.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "存档失败\n" + "请查找相关问题后，再次尝试存档");
			System.err.println("发生异常:" + e);
			e.printStackTrace();
		}
	}

}
