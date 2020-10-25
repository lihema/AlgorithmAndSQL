package DesignPatten.anaemic.pojo;

/**
 * @author: LiJiShu
 * @date: 2020/5/22 14:44
 * @content:
 */
public class Applicant {

    private Long userId;
    private Long userDept;
    private Long userRole;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserDept() {
        return userDept;
    }

    public void setUserDept(Long userDept) {
        this.userDept = userDept;
    }

    public Long getUserRole() {
        return userRole;
    }

    public void setUserRole(Long userRole) {
        this.userRole = userRole;
    }

}
