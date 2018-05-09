package com.qst.scnt.model;

import java.util.List;

/**
 * @author sqrh
 *
 */
public class Menu {
    /**
     *主键（自增）
     */
    private Integer id;

    /**
     *菜单名称
     */
    private String menuName;

    /**
     *菜单图标路径
     */
    private String icon;

    /**
     *菜单URL
     */
    private String menuUrl;

    /**
     *父节点
     */
    private Integer parentID;
        
    /**
     *主键（自增）
     */
    public Integer getId() {
        return id;
    }

    /**
     *主键（自增）
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *菜单名称
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     *菜单名称
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    /**
     *菜单图标路径
     */
    public String getIcon() {
        return icon;
    }

    /**
     *菜单图标路径
     */
    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    /**
     *菜单URL
     */
    public String getMenuUrl() {
        return menuUrl;
    }

    /**
     *菜单URL
     */
    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl == null ? null : menuUrl.trim();
    }

    /**
     *父节点
     */
    public Integer getParentID() {
        return parentID;
    }

    /**
     *父节点
     */
    public void setParentID(Integer parentID) {
        this.parentID = parentID;
    }
   
    
}