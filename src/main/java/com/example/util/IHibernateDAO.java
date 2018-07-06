package com.example.util;

import java.io.Serializable;
import java.util.List;

public interface IHibernateDAO {
	public <T> T saveRecord(T value) throws Exception;

	public <T> List<T> getRecords(String query);

	public <T> void deleteRecord(Class<?> c, Serializable id) throws Exception;

	public <T> T getRecordOne(Class<?> c, Serializable id) throws Exception;

	public <T> void updateRecord(T obj) throws Exception;

}
