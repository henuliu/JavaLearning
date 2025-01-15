package constantpool.connection;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 使用 Druid连接池
 */
public class DruidPool
{
    private static DataSource dataSource = null;
    String tableName = "user";

    // 加载参数
    static
    {
        Properties properties = new Properties();
        // 获取连接池配置
        InputStream inputStream = DruidPool.class.getClassLoader().getResourceAsStream("./druid.properties");
        try
        {
            properties.load(inputStream);
            // 初始化连接池
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // 插入数据
    public void insertData()
    {
        try (Connection connection = dataSource.getConnection())
        {
            String sql = "insert into " + tableName + "(id,name) values(?,?)";
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setString(1, "aa");
                ps.setString(2, "bb");
                int ret = ps.executeUpdate();
                System.out.println(ret);
            } catch (SQLException e)
            {
                throw new RuntimeException(e);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    // 查询数据
    public void selectData() throws SQLException
    {
        try (Connection connection = dataSource.getConnection())
        {
            String sql = "select * from " + tableName + " where id=1872889518075379714";
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setString(1, "aa");
                ResultSet rs = ps.executeQuery();
                while (rs.next())
                {
                    String id = rs.getString(1);
                    String name = rs.getString(2);
                    System.out.println("id=" + id);
                    System.out.println("name=" + name);
                }
            }
        }
    }
}