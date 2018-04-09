package com.qst.scnt.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.qst.scnt.model.UserInfo;

public interface BaseService<T> {

	/** 
     * ����ʵ�� 
     * @param entity 
     * @return Ӱ���¼���� 
     */  
    public abstract int insert(T entity);  
    
	/**  
	 * ��������  
	 * @param list  
	 */    
	public abstract int insertBatch(final List<T> list);   
      
    /**  
     * �޸�һ��ʵ�����UPDATEһ����¼��  
     * @param entity ʵ�����  
     * @return �޸ĵĶ���������������=1  
     */    
    public abstract int update(T entity);    
        
    /**  
     * �޸ķ��������ļ�¼  
     * <p>�˷����ر��ʺ���һ���԰Ѷ�����¼��ĳЩ�ֶ�ֵ����Ϊ��ֵ����ֵ��������������޸ķ��������ļ�¼��״̬�ֶ�</p>  
     * <p>�˷�������һ����;�ǰ�һ����¼�ĸ����ֶε�ֵ�޸�Ϊ��ֵ����ֵ������ʱҪ����������Ϊ�ü�¼������</p>  
     * @param param ���ڲ���SQL�Ĳ���ֵ������WHERE������Ŀ���ֶκ���ֵ��  
     * @return �޸ĵļ�¼�����������ж��޸��Ƿ�ɹ�  
     */    
    public abstract int updateFields(Map param);    
    
    /**  
     * �����޸�  
     * @param list  
     */    
    public abstract int updateBatch(final List<T> list); 
    
    /**  
     * ������ɾ����¼  
     * @param primaryKey ��������  
     * @return ɾ���Ķ���������������=1  
     */    
    public abstract int delete(int primaryKey);    
    
    /**  
     * ɾ�����������ļ�¼  
     * <p><strong>�˷���һ��Ҫ���ã�����������ò��������ܻ�ɾ�����õļ�¼��</strong></p>  
     * @param param ���ڲ���SQL�Ĳ���ֵ������WHERE�����������������ݲ������ã�  
     * @return  
     */    
    public abstract int deleteParam(Map param);
    
    /**  
     * ����ɾ��  
     * @param list  
     */    
    public abstract int deleteBatch(final List<Integer> list); 
        
    /**  
     * ��ձ���delete���и��ߵ�Ч�ʣ������Ǵ����ݿ�������ɾ����delete���߼�ɾ������ɾ���ļ�¼��Ȼռ�пռ䣩  
     * <p><strong>�˷���һ��Ҫ���ã�</strong></p>  
     * @return  
     */    
    public abstract int truncate();    
        
    /**  
     * ��ѯ�����ܼ�¼��  
     * @return �����ܼ�¼��  
     */    
    public abstract int count();    
        
    /**  
     * ��ѯ���������ļ�¼��  
     * @param param ��ѯ��������������WHERE�����������������ݲ������ã����˲�������Ϊnull�����൱��count()  
     * @return  
     */    
    public abstract int count(Object param);    
  
    /**  
     * ȡȫ����¼  
     * @return ȫ����¼ʵ������List  
     */    
    public abstract List<T> select();    
    
    /**  
     * ������ȡ��¼  
     * @param primaryKey ����ֵ  
     * @return ��¼ʵ��������û�з������������ļ�¼���򷵻�null  
     */    
    public abstract T selectPK(int primaryKey);    
          
    /**  
     * ��������ѯ��¼  
     * @param param ��ѯ��������������WHERE��������ҳ��������������  
     * @return ����������¼��ʵ������List  
     */    
    public abstract List<T> selectParam(Map param);   
    
    /**  
     * ��������ѯ��¼��ģ����ѯ��  
     * @param param ��ѯ���������� ��ѯ������װ��entity
     * @return ����������¼��ʵ������List  
     */
    public abstract List<T> selectParamFlexible(T entity);
        
    /**  
     * ��������ѯ��¼��������ɷ�ҳ���  
     * @param param ��ѯ��������������WHERE��������ҳ��������������  
     * @return PaginationResult���󣬰��������������ģ��ܼ�¼����ҳʵ�����List��  
     */    
    public abstract PageInfo<T> selectPagination(Map param,int pageNum,int pageSize);

    /**  
     * ��ȡ��ǰ�û�  
     * @param   
     * @return 
     */ 
    //public UserInfo getCurrentUser();
       
        
      
}
