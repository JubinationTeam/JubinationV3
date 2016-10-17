/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jubination.model.dao;

import java.util.List;

/**
 *
 * @author Welcome
 */
public interface GenericDAO<T> {
    
  public T buildEntity(T entity);
  
  public T buildEntity(T entity, boolean coded);
  
  public boolean addPropertyList(T entity, T property,String listType);
  
  public T readProperty(T paramId);
  
 
  
  public T readPropertyList(T entity,String listType); 
  
  public boolean updateProperty(T entity,T paramVal, String paramType);
  
  public boolean updatePropertyList(T entity,T property,String listType);
  
  public boolean deleteEntity(T entity);
  
  public T deletePropertyList(T entity,T property,String listType);
  
  public List fetchEntities(String paramVal);
  
  public T fetchEntity(T property);
  
   public T buildInnerPropertyList(T entity, T property,String listType);
   
    public T readInnerPropertyList(T entity, String listType);
    
    public boolean deleteInnerPropertyList(T entity,T property,String listType);
}
