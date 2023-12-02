package swing.Entity;

// 定义群聊类
public class Group
{
    public String groupName;
    public String groupAccount;
    public String groupAvatar;

    public Group(String groupAccount, String groupName, String groupAvatar)
    {
        this.groupAccount=groupAccount;
        this.groupName=groupName;
        this.groupAvatar=groupAvatar;
    }

    public Group()
    {

    }
    // 添加群聊
    public void Add(String groupAccount, String groupName, String groupAvatar)
    {
        this.groupAccount=groupAccount;
        this.groupName=groupName;
        this.groupAvatar=groupAvatar;
    }

    public String getGroupName()
    {
        return groupName;
    }

    public String getGroupAccount()
    {
        return groupAccount;
    }

    public String getGroupAvatar()
    {
        return groupAvatar;
    }
}
