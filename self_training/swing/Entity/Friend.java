package swing.Entity;

// 定义好友类
public class Friend
{
    //好友名称
    public String friendName;
    public String Account;
    public String nickName;
    public String avatarPath;

    public Friend(String Account, String nickName, String avatarPath)
    {
        this.Account = Account;
        this.nickName = nickName;
        this.avatarPath = avatarPath;
    }

    public Friend()
    {

    }
    // 添加好友
    public void Add(String qqNumber, String nickName, String avatarUrl)
    {
        this.Account = qqNumber;
        this.nickName = nickName;
        this.avatarPath = avatarUrl;
    }
    public String getQQNumber()
    {
        return Account;
    }

    public String getNickName()
    {
        return nickName;
    }

    public String getAvatarPath()
    {
        return avatarPath;
    }
}