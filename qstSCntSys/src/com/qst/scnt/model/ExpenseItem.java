package com.qst.scnt.model;

public class ExpenseItem {
    /**
     *主键（自增）
     */
    private Integer id;

    /**
     *费用项目
     */
    private String expenseItem;

    /**
     *
     */
    private Integer parentID;

    /**
     *
     */
    private Integer level;

    /**
     *
     */
    private Integer isDelete;
    
    /**
     *上一级销售部名称
     */
    private String parentExpenseItem;
    
    /**
     *父节点
     */
    private Integer _parentId;

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
     *费用项目
     */
    public String getExpenseItem() {
        return expenseItem;
    }

    /**
     *费用项目
     */
    public void setExpenseItem(String expenseItem) {
        this.expenseItem = expenseItem == null ? null : expenseItem.trim();
    }

    /**
     *
     */
    public Integer getParentID() {
        return parentID;
    }

    /**
     *
     */
    public void setParentID(Integer parentID) {
        this.parentID = parentID;
    }

    /**
     *
     */
    public Integer getLevel() {
        return level;
    }

    /**
     *
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     *
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     *
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

	public String getParentExpenseItem() {
		return parentExpenseItem;
	}

	public void setParentExpenseItem(String parentExpenseItem) {
		this.parentExpenseItem = parentExpenseItem;
	}

	public Integer get_parentId() {
		return _parentId;
	}

	public void set_parentId(Integer _parentId) {
		this._parentId = _parentId;
	}
    
    
}