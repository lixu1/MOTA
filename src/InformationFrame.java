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

//道具和怪物信息
public class InformationFrame extends JFrame {
	public InformationFrame() {
		super("道具和怪物信息");
		Container c = getContentPane();
		c.setLayout(new FlowLayout());

		// 小血瓶信息
		c.add(new JLabel("小血瓶,加血200", new ImageIcon(
				"picture/smallBloodBottle.png"), JLabel.RIGHT));

		// 大血瓶信息
		c.add(new JLabel("大血瓶,加血500", new ImageIcon(
				"picture/bigBloodBottle.png"), JLabel.RIGHT));

		c.add(new JLabel("红宝石,加攻击力5", new ImageIcon("picture/ruby.jpg"),
				JLabel.RIGHT));

		c.add(new JLabel("蓝宝石,加防御力5", new ImageIcon("picture/sapphire.jpg"),
				JLabel.RIGHT));

		c.add(new JLabel("黄金剑,加攻击力25",
				new ImageIcon("picture/goldenSword.png"), JLabel.RIGHT));

		c.add(new JLabel("黄金盾,加防御力25", new ImageIcon("picture/goldShield.png"),
				JLabel.RIGHT));

		c.add(new JLabel("小商店,可以使用10金钱买5攻击力,或5防御力,或500血量", new ImageIcon(
				"picture/smallShop.png"), JLabel.RIGHT));

		c.add(new JLabel("大商店,可以使用30 金钱买 20 攻击力,或20 防御力,或2000 血量",
				new ImageIcon("picture/bigShop.png"), JLabel.RIGHT));

		c.add(new JLabel("绿泡泡怪物,攻击力" + monster.greenBubble[1] + "，防御力"
				+ monster.greenBubble[2] + "，HP" + monster.greenBubble[3]
				+ "，打败它后获得的金钱数" + monster.greenBubble[4], new ImageIcon(
				"picture/greenBubble.png"), JLabel.RIGHT));

		c.add(new JLabel("蓝泡泡怪物,攻击力" + monster.blueBubble[1] + "，防御力"
				+ monster.blueBubble[2] + "，HP" + monster.blueBubble[3]
				+ "，打败它后获得的金钱数" + monster.blueBubble[4], new ImageIcon(
				"picture/blueBubble.png"), JLabel.RIGHT));

		c.add(new JLabel("红泡泡怪物,攻击力" + monster.redBubble[1] + "，防御力"
				+ monster.redBubble[2] + "，HP" + monster.redBubble[3]
				+ "，打败它后获得的金钱数" + monster.redBubble[4], new ImageIcon(
				"picture/redBubble.png"), JLabel.RIGHT));

		c.add(new JLabel("小蝙蝠怪物,攻击力" + monster.smallBat[1] + "，防御力"
				+ monster.smallBat[2] + "，HP" + monster.smallBat[3]
				+ "，打败它后获得的金钱" + monster.smallBat[4], new ImageIcon(
				"picture/smallBat.png"), JLabel.RIGHT));

		c.add(new JLabel("大蝙蝠怪物,攻击力" + monster.bigBat[1] + "，防御力"
				+ monster.bigBat[2] + "，HP" + monster.bigBat[3] + "，打败它后获得的金钱数"
				+ monster.bigBat[4], new ImageIcon("picture/bigBat.png"),
				JLabel.RIGHT));

		c.add(new JLabel("黑泡泡怪物,攻击力" + monster.blackBubble[1] + "，防御力"
				+ monster.blackBubble[2] + "HP" + monster.blackBubble[3]
				+ "，打败它后获得的金钱数" + monster.blackBubble[4], new ImageIcon(
				"picture/blackBubble.png"), JLabel.RIGHT));

		c.add(new JLabel("红蝙蝠怪物,攻击力" + monster.redBat[1] + "，防御力"
				+ monster.redBat[2] + "，HP" + monster.redBat[3] + "，打败它后获得的金钱数"
				+ monster.redBat[4], new ImageIcon("picture/redBat.png"),
				JLabel.RIGHT));

		c.add(new JLabel("巨人怪物,攻击力" + monster.strongMan[1] + "，防御力"
				+ monster.strongMan[2] + "，HP" + monster.strongMan[3]
				+ "，打败它后获得的金钱数" + monster.strongMan[4], new ImageIcon(
				"picture/strongMan.png"), JLabel.RIGHT));

		c.add(new JLabel("石头人怪物,攻击力" + monster.stoneMan[1] + "，防御力"
				+ monster.stoneMan[2] + "，HP" + monster.stoneMan[3]
				+ "，打败它后获得的金钱数" + monster.stoneMan[4], new ImageIcon(
				"picture/stoneMan.png"), JLabel.RIGHT));

		c.add(new JLabel("金卫士怪物,攻击力" + monster.goldDefender[1] + "，防御力"
				+ monster.goldDefender[2] + "，HP" + monster.goldDefender[3]
				+ "，打败它后获得的金钱数" + monster.goldDefender[4], new ImageIcon(
				"picture/goldDefender.png"), JLabel.RIGHT));

		c.add(new JLabel("大魔王怪物,攻击力" + monster.princeOfTheDevils[1] + "，防御力"
				+ monster.princeOfTheDevils[2] + "，HP"
				+ monster.princeOfTheDevils[3] + "，打败它后获得的金钱数"
				+ monster.princeOfTheDevils[4], new ImageIcon(
				"picture/princeOfTheDevils.png"), JLabel.RIGHT));

		c.add(new JLabel("魔女怪物,攻击力"+monster.MagicGirl[1]+"，防御力"+monster.MagicGirl[2]+
				"，HP"+monster.MagicGirl[3]+"，打败它后获得的金钱数"+monster.MagicGirl[4],
				new ImageIcon("picture/MagicGirl.jpg"), JLabel.RIGHT));

		c.add(new JLabel("终极魔王,攻击力"+monster.LastEvilSpirit[1]+"，防御力"+monster.LastEvilSpirit[2]+
				"，HP"+monster.LastEvilSpirit[3]+"，打败它后获得最后的胜利",
				new ImageIcon("picture/LastEvilSpirit.GIF"), JLabel.RIGHT));

	}

}
