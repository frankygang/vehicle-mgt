package com.vehicle.project.vehicle.pojo;

import java.util.List;

/**
 * 钉钉用户详情
 * @author bobo
 */
public class DingDingUserDetail {
    /**
     * errcode : 0
     * errmsg : ok
     * name : 张三
     * unionid : PiiiPyQqBNBii0HnCJ3zljcxxxxxx
     * userid : zhangsan
     * isLeaderInDepts : {1:false}
     * isBoss : false
     * hiredDate : 1520265600000
     * isSenior : false
     * department : [1,2]
     * orderInDepts : {1:71738366882504}
     * active : false
     * avatar : xxx
     * isAdmin : false
     * isHide : false
     * jobnumber : 001
     * position : manager
     * roles : [{"id":149507744,"name":"总监","groupName":"职务"}]
     */
    /** 返回码 */
    private int errcode;
    /** 对返回码的文本描述内容 */
    private String errmsg;
    /** 员工在当前企业内的唯一标识，也称staffId */
    private String name;
    /** 员工在当前开发者企业账号范围内的唯一标识，系统生成，固定值，不会改变 */
    private String unionid;
    /** 员工名字 */
    private String userid;
    /** 在对应的部门中是否为主管：Map结构的json字符串，key是部门的Id，value是人员在这个部门中是否为主管，true表示是，false表示不是 */
    private String isLeaderInDepts;
    /** 是否为企业的老板，true表示是，false表示不是 */
    private boolean isBoss;
    /** 入职时间。Unix时间戳 （在OA后台通讯录中的员工基础信息中维护过入职时间才会返回) */
    private long hiredDate;
    /** 是否是高管 */
    private boolean isSenior;
    private String orderInDepts;
    /** 是否已经激活，true表示已激活，false表示未激活 */
    private boolean active;
    /** 头像url */
    private String avatar;
    /** 是否为企业的管理员，true表示是，false表示不是 */
    private boolean isAdmin;
    /** 是否号码隐藏，true表示隐藏，false表示不隐藏 */
    private boolean isHide;
    /** 员工工号 */
    private String jobnumber;
    /** 职位信息 */
    private String position;
    /** 成员所属部门id列表 */
    private List<Long> department;
    /** 用户所在角色列表 */
    private List<RolesBean> roles;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getIsLeaderInDepts() {
        return isLeaderInDepts;
    }

    public void setIsLeaderInDepts(String isLeaderInDepts) {
        this.isLeaderInDepts = isLeaderInDepts;
    }

    public boolean isIsBoss() {
        return isBoss;
    }

    public void setIsBoss(boolean isBoss) {
        this.isBoss = isBoss;
    }

    public long getHiredDate() {
        return hiredDate;
    }

    public void setHiredDate(long hiredDate) {
        this.hiredDate = hiredDate;
    }

    public boolean isIsSenior() {
        return isSenior;
    }

    public void setIsSenior(boolean isSenior) {
        this.isSenior = isSenior;
    }

    public String getOrderInDepts() {
        return orderInDepts;
    }

    public void setOrderInDepts(String orderInDepts) {
        this.orderInDepts = orderInDepts;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public boolean isIsHide() {
        return isHide;
    }

    public void setIsHide(boolean isHide) {
        this.isHide = isHide;
    }

    public String getJobnumber() {
        return jobnumber;
    }

    public void setJobnumber(String jobnumber) {
        this.jobnumber = jobnumber;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public List<Long> getDepartment() {
        return department;
    }

    public void setDepartment(List<Long> department) {
        this.department = department;
    }

    public List<RolesBean> getRoles() {
        return roles;
    }

    public void setRoles(List<RolesBean> roles) {
        this.roles = roles;
    }

    public static class RolesBean {
        /**
         * id : 149507744
         * name : 总监
         * groupName : 职务
         */

        /** 角色id*/
        private Long id;
        /** 角色名称 */
        private String name;
        /** 角色组名称 */
        private String groupName;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }
    }

}
