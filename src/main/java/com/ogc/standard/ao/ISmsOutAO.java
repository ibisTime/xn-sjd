package com.ogc.standard.ao;

public interface ISmsOutAO {
    /**
     * 发送短信验证码
     * @param mobile
     * @param bizType
     * @param companyCode
     * @param systemCode 
     * @create: 2016年12月15日 上午7:28:43 xieyj
     * @history:
     */
    public void sendSmsCaptcha(String mobile, String bizType,
            String companyCode, String systemCode);

    // 4位短信验证码
    public void sendInterSmsCaptcha(String interCode, String mobile,
            String bizType, String companyCode, String systemCode);

    // 4位邮箱验证码
    public void sendEmailCaptcha(String email, String bizType,
            String companyCode, String systemCode);

    // 6位短信验证码
    public void sendInterSmsCaptchaL6(String interCode, String mobile,
            String bizType, String companyCode, String systemCode);

    // 6位邮箱验证码
    public void sendEmailCaptchaL6(String email, String bizType,
            String companyCode, String systemCode);

}
