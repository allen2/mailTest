package mail;

import java.util.Properties;
import javax.mail.*;
import com.sun.mail.imap.*;
/**
 *  使用IMAP协议接收邮件
 * @author WuYaShuan
 * @time Jan 17, 20111:29:21 PM
 */
public class ReciveIMAPmail {
    public static void main(String[] args) {
        String imapserver = "imap.sina.com"; // 邮件服务器
        String user = "lyih520";
        String pwd = "qwer4321";     // 根据自已的密码修改
        // 获取默认会话
        Properties prop = System.getProperties();
        prop.put("mail.imap.host",imapserver);

        prop.put("mail.imap.auth.plain.disable","true");
        Session mailsession=Session.getInstance(prop,null);
        mailsession.setDebug(false); //是否启用debug模式
        IMAPFolder folder= null;
        IMAPStore store=null;
        int total= 0;
        try{
            store=(IMAPStore)mailsession.getStore("imap");  // 使用imap会话机制，连接服务器
            store.connect(imapserver,user,pwd);
            folder=(IMAPFolder)store.getFolder("INBOX"); //收件箱
            // 使用只读方式打开收件箱
            folder.open(Folder.READ_WRITE);
            //获取总邮件数
            total = folder.getMessageCount();
            System.out.println("-----------------您的邮箱共有邮件：" + total+" 封--------------");
            // 得到收件箱文件夹信息，获取邮件列表
            Message[] msgs =folder.getMessages();
            System.out.println("\t收件箱的总邮件数：" + msgs.length);
            System.out.println("\t未读邮件数：" + folder.getUnreadMessageCount());
            System.out.println("\t新邮件数：" + folder.getNewMessageCount());
            System.out.println("----------------End------------------");
            for ( Message mes :msgs) {
                System.out.println(mes.getSubject());

            }
        }
        catch(MessagingException ex){
            System.err.println("不能以读写方式打开邮箱!");
            ex.printStackTrace();
        }finally {
            // 释放资源
            try{
                if(folder!=null)
                    folder.close(true); //退出收件箱时,删除做了删除标识的邮件
                if (store != null)
                    store.close();
            }catch(Exception bs){
                bs.printStackTrace();
            }
        }
    }
}