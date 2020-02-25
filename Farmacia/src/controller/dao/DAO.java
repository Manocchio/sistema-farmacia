package controller.dao;

import java.util.List;

public interface DAO{
		
		public  void insert(Object o);
		public  void update(Object o);
		public  void delete(Object o);
		public  Object select(int i);
		public  List select();

	}

