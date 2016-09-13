package net.yaorugang.practice.chineseid;

import java.util.HashMap;

/**
 * Created by Rugang on 2016/9/13.
 */
public class ChineseIdChecker
{
    private static final int[] W = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2}; // 加权因子
    private static final HashMap<Integer, Character> Checksum = new HashMap<>(); // 模的校验码；

    static
    {
        Checksum.put(0, '1');
        Checksum.put(1, '0');
        Checksum.put(2, 'X');
        Checksum.put(3, '9');
        Checksum.put(4, '8');
        Checksum.put(5, '7');
        Checksum.put(6, '6');
        Checksum.put(7, '5');
        Checksum.put(8, '4');
        Checksum.put(9, '3');
        Checksum.put(10, '2');
    }


    public Character computeChecksum(String id)
    {
        // 检查输入的17位身份证ID长度是否有效。
        if (id == null || id.isEmpty() || id.length() > 17)
            return null;

        // Step 1: 对十七位数字本体码加权求和
        int sum = 0;
        for (int i = 0; i < id.length(); i++)
        {
            try
            {
                int Ai = Integer.parseInt(id.substring(i, i + 1));
                int Wi = W[i];
                sum += Ai * Wi;
            }
            catch (NumberFormatException e)
            {
                return null;    // 如果存在非数字字符，抛出异常，函数返回null。
            }
        }

        // Step 2: 将加权求和的值进行mod 11
        int Y = sum % 11;

        // Step 3: 获取对应的校验码
        Character cs = Checksum.get(Y);

        return cs;
    }
}
