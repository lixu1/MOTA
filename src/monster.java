public class monster {

	// 各个道具，和怪物的信息
	// 编号，攻击力，防御力，血量，金钱,打败后获得的经验值
	// attack defense blood money

	static int wall[] = { -1 };// 墙壁

	static int openSpace[] = { 0 };// 空地

	static int smallBloodBottle[] = { 2, 0, 0, 200, 0 };// 小血瓶

	static int bigBloodBottle[] = { 3, 0, 0, 500, 0 };// 大血瓶

	static int ruby[] = { 4, 5, 0, 0, 0 };// 红宝石

	static int sapphire[] = { 5, 0, 5, 0, 0 };// 蓝宝石

	static int goldenSword[] = { 6, 25, 0, 0, 0 };// 黄金剑

	static int goldShield[] = { 7, 0, 25, 0, 0 };// 黄金盾

	static int smallShop[] = { 8, 5, 5, 500, 10 }; // 小商店

	static int bigShop[] = { 9, 20, 20, 2000, 30 };// 大商店

	static int greenBubble[] = { 10, 15, 0, 100, 1,1 };// 绿泡泡怪物

	static int blueBubble[] =  { 11, 20, 5, 150, 2,2 };// 蓝泡泡怪物

	static int redBubble[] =   { 12, 30, 0, 200, 3,3 };// 红泡泡怪物

	static int smallBat[] =    { 13, 40, 15, 300, 4,4 };// 小蝙蝠怪物

	static int bigBat[] =      { 14, 50, 60, 500, 7,7 };// 大蝙蝠怪物

	static int blackBubble[] = { 15, 60,35, 400, 6,6 };// 黑泡泡怪物

	static int redBat[] =      { 16, 85, 50, 800, 8 , 8};// 红蝙蝠怪物

	static int strongMan[] =   { 17, 100, 50, 1000, 10 , 10};// 巨人怪物

	static int stoneMan[] =    { 18, 80, 100, 500, 10 , 10};// 石头人怪物

	static int goldDefender[] ={ 19, 150, 130, 1500, 20 , 20};// 金卫士怪物

	static int princeOfTheDevils[] = { 20, 200, 120, 5000, 25, 25 };// 大魔王怪物

	static int goUpstairs[] = { 21 };// 上楼

	static int goDownstairs[] = { 22 };// 下楼
	
	static int door[]={23};//门
	
	static int key[]={24};//钥匙
	
	static int MysteriousBusinessman[]={25};//神秘商人
	
	static int MagicGirl[]={26,300,200,8000,30,30};//魔女
	
	static int LastEvilSpirit[]={27,500,300,10000,0,0};//终极魔王

}
