package site.evan.zeus.facade.domain;

import java.util.Set;

/**
 * @author : evan
 * @Date: 2018/9/17 21:02
 * @Description:
 */
public class Role {
    private Long id;

    private Long userId;

    private String roleName;

    private Set<Resource> resources;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<Resource> getResources() {
        return resources;
    }

    public void setResources(Set<Resource> resources) {
        this.resources = resources;
    }
}
