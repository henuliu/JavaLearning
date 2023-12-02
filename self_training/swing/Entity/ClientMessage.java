package swing.Entity;

public class ClientMessage
{
    private String type;
    private String content;
    private String avatarPath;
    private String from;
    private String to;

    private String account;
    private String verifyInfo;
    private String NickName;
    private String remark;

    private String groupAccount;
    private String groupName;
    private String groupAvatar;
    private String model;

    public String getModel()
    {
        return model;
    }

    public String getGroupAccount()
    {
        return groupAccount;
    }

    public String getGroupName()
    {
        return groupName;
    }

    public String getGroupAvatar()
    {
        return groupAvatar;
    }

    public String getRemark()
    {
        return remark;
    }

    public String getAccount()
    {
        return account;
    }

    public String getVerifyInfo()
    {
        return verifyInfo;
    }
    public String getType()
    {
        return type;
    }

    public String getContent()
    {
        return content;
    }

    public String getFrom()
    {
        return from;
    }
    public String getTo()
    {
        return to;
    }

    public String getAvatarPath()
    {
        return avatarPath;
    }

    public String getNickName()
    {
        return NickName;
    }
}
