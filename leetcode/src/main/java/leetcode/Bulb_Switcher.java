package leetcode;

/*Given n = 3. 



There are n bulbs that are initially off. 
You first turn on all the bulbs. Then, you turn off every second bulb. 
On the third round, you toggle every third bulb (turning on if it's off or turning off if it's on).
 For the nth round, you only toggle the last bulb. Find how many bulbs are on after n rounds.


At first, the three bulbs are [off, off, off].
After first round, the three bulbs are [on, on, on].
After second round, the three bulbs are [on, off, on].
After third round, the three bulbs are [on, off, off].
So you should return 1, because there is only one bulb is on.

    
0--> 关
1-－> 开
2———>2的倍数关闭
3-－>3的倍数取反
4-－>4的倍数关闭
5-－>5的倍数取反

*/

public class Bulb_Switcher {
	public int bulbSwitch(int n) {
		return (int) (n==0 ? 0 : Math.sqrt(n));
	}
}
