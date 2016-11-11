package mail;




class Test {
        public static void main(String[] args) {
                MailSendServer ms = new MailSendServer("smtp.sina.com", 25, "lyih520", "qwer4321", "lyih520@sina.com");
                String [] to = {"303970912@qq.com"};
                MailSendInfo msi = new  MailSendInfo("subject", "content", to , null);
                ms.sendTextInfo(msi,true);

        }
}