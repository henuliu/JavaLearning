package constantpool.connection;

import java.sql.SQLException;

/**
 * 这个类是：
 *
 * @author: liu jun hao
 * @date: 2025/2/16 18:29
 * @version: 1.0
 */
public class DruidPoolTest
{
    public static void main(String[] args) throws SQLException
    {
        DruidPool druidPool = new DruidPool();
        druidPool.selectData();
    }
}
