package swing.Entity;

// 服务端消息类
public class ServerMessage
{
    // 接受客户端消息的类型
    private String type;
    // 头像
    private String avatarPath;

    // 验证信息
    private String verifyInfo;

    // 消息内容
    private String content;
    // 消息发送方
    private String from;
    // 消息接收方
    private String to;

    // 账号
    private String account;
    // 密码
    private String password;
    // 昵称
    private String NickName;
    // 签名
    private String signature;

    private String applyAvatarPath;
    private String applyNickName;

    private String appliedAvatarPath;
    private String appliedNickName;

    private String groupAccount;
    private String groupName;
    private String groupAvatar;
    // 备注
    private String remark;

    private String groupMember;

    // 客户端最后一次发送心跳包的时间戳
    private long lastHeartbeatTime;

    private String model;


    //====================获取消息类属性方法======================

    public String getModel()
    {
        return model;
    }
    public String getGroupMember()
    {
        return groupMember;
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

    public String getApplyAvatarPath()
    {
        return applyAvatarPath;
    }

    public String getApplyNickName()
    {
        return applyNickName;
    }

    public String getAppliedAvatarPath()
    {
        return appliedAvatarPath;
    }

    public String getAppliedNickName()
    {
        return appliedNickName;
    }

    public String getRemark()
    {
        return remark;
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
    public long getLastHeartbeatTime()
    {
        return lastHeartbeatTime;
    }
    public String getAccount()
    {
        return account;
    }
    public String getPassword()
    {
        return password;
    }
    public String getNickName()
    {
        return NickName;
    }
    public String getSignature()
    {
        return signature;
    }
    public String getAvatarPath()
    {
        return avatarPath;
    }

    public String getVerifyInfo()
    {
        return verifyInfo;
    }
}