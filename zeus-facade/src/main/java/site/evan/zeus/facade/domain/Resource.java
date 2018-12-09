package site.evan.zeus.facade.domain;

/**
 * @author : Administrator
 * @Date: 2018/9/17 21:05
 * @Description:
 */
public class Resource {

    private Long id;

    private Long roleId;

    private String resourceName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }
}
