import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

//���ߺ͹�����Ϣ
public class InformationFrame extends JFrame {
	public InformationFrame() {
		super("���ߺ͹�����Ϣ");
		Container c = getContentPane();
		c.setLayout(new FlowLayout());

		// СѪƿ��Ϣ
		c.add(new JLabel("СѪƿ,��Ѫ200", new ImageIcon(
				"picture/smallBloodBottle.png"), JLabel.RIGHT));

		// ��Ѫƿ��Ϣ
		c.add(new JLabel("��Ѫƿ,��Ѫ500", new ImageIcon(
				"picture/bigBloodBottle.png"), JLabel.RIGHT));

		c.add(new JLabel("�챦ʯ,�ӹ�����5", new ImageIcon("picture/ruby.jpg"),
				JLabel.RIGHT));

		c.add(new JLabel("����ʯ,�ӷ�����5", new ImageIcon("picture/sapphire.jpg"),
				JLabel.RIGHT));

		c.add(new JLabel("�ƽ�,�ӹ�����25",
				new ImageIcon("picture/goldenSword.png"), JLabel.RIGHT));

		c.add(new JLabel("�ƽ��,�ӷ�����25", new ImageIcon("picture/goldShield.png"),
				JLabel.RIGHT));

		c.add(new JLabel("С�̵�,����ʹ��10��Ǯ��5������,��5������,��500Ѫ��", new ImageIcon(
				"picture/smallShop.png"), JLabel.RIGHT));

		c.add(new JLabel("���̵�,����ʹ��30 ��Ǯ�� 20 ������,��20 ������,��2000 Ѫ��",
				new ImageIcon("picture/bigShop.png"), JLabel.RIGHT));

		c.add(new JLabel("�����ݹ���,������" + monster.greenBubble[1] + "��������"
				+ monster.greenBubble[2] + "��HP" + monster.greenBubble[3]
				+ "����������õĽ�Ǯ��" + monster.greenBubble[4], new ImageIcon(
				"picture/greenBubble.png"), JLabel.RIGHT));

		c.add(new JLabel("�����ݹ���,������" + monster.blueBubble[1] + "��������"
				+ monster.blueBubble[2] + "��HP" + monster.blueBubble[3]
				+ "����������õĽ�Ǯ��" + monster.blueBubble[4], new ImageIcon(
				"picture/blueBubble.png"), JLabel.RIGHT));

		c.add(new JLabel("�����ݹ���,������" + monster.redBubble[1] + "��������"
				+ monster.redBubble[2] + "��HP" + monster.redBubble[3]
				+ "����������õĽ�Ǯ��" + monster.redBubble[4], new ImageIcon(
				"picture/redBubble.png"), JLabel.RIGHT));

		c.add(new JLabel("С�������,������" + monster.smallBat[1] + "��������"
				+ monster.smallBat[2] + "��HP" + monster.smallBat[3]
				+ "����������õĽ�Ǯ" + monster.smallBat[4], new ImageIcon(
				"picture/smallBat.png"), JLabel.RIGHT));

		c.add(new JLabel("���������,������" + monster.bigBat[1] + "��������"
				+ monster.bigBat[2] + "��HP" + monster.bigBat[3] + "����������õĽ�Ǯ��"
				+ monster.bigBat[4], new ImageIcon("picture/bigBat.png"),
				JLabel.RIGHT));

		c.add(new JLabel("�����ݹ���,������" + monster.blackBubble[1] + "��������"
				+ monster.blackBubble[2] + "HP" + monster.blackBubble[3]
				+ "����������õĽ�Ǯ��" + monster.blackBubble[4], new ImageIcon(
				"picture/blackBubble.png"), JLabel.RIGHT));

		c.add(new JLabel("���������,������" + monster.redBat[1] + "��������"
				+ monster.redBat[2] + "��HP" + monster.redBat[3] + "����������õĽ�Ǯ��"
				+ monster.redBat[4], new ImageIcon("picture/redBat.png"),
				JLabel.RIGHT));

		c.add(new JLabel("���˹���,������" + monster.strongMan[1] + "��������"
				+ monster.strongMan[2] + "��HP" + monster.strongMan[3]
				+ "����������õĽ�Ǯ��" + monster.strongMan[4], new ImageIcon(
				"picture/strongMan.png"), JLabel.RIGHT));

		c.add(new JLabel("ʯͷ�˹���,������" + monster.stoneMan[1] + "��������"
				+ monster.stoneMan[2] + "��HP" + monster.stoneMan[3]
				+ "����������õĽ�Ǯ��" + monster.stoneMan[4], new ImageIcon(
				"picture/stoneMan.png"), JLabel.RIGHT));

		c.add(new JLabel("����ʿ����,������" + monster.goldDefender[1] + "��������"
				+ monster.goldDefender[2] + "��HP" + monster.goldDefender[3]
				+ "����������õĽ�Ǯ��" + monster.goldDefender[4], new ImageIcon(
				"picture/goldDefender.png"), JLabel.RIGHT));

		c.add(new JLabel("��ħ������,������" + monster.princeOfTheDevils[1] + "��������"
				+ monster.princeOfTheDevils[2] + "��HP"
				+ monster.princeOfTheDevils[3] + "����������õĽ�Ǯ��"
				+ monster.princeOfTheDevils[4], new ImageIcon(
				"picture/princeOfTheDevils.png"), JLabel.RIGHT));

		c.add(new JLabel("ħŮ����,������"+monster.MagicGirl[1]+"��������"+monster.MagicGirl[2]+
				"��HP"+monster.MagicGirl[3]+"����������õĽ�Ǯ��"+monster.MagicGirl[4],
				new ImageIcon("picture/MagicGirl.jpg"), JLabel.RIGHT));

		c.add(new JLabel("�ռ�ħ��,������"+monster.LastEvilSpirit[1]+"��������"+monster.LastEvilSpirit[2]+
				"��HP"+monster.LastEvilSpirit[3]+"���������������ʤ��",
				new ImageIcon("picture/LastEvilSpirit.GIF"), JLabel.RIGHT));

	}

}
