package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.Todo;

@Mapper
public interface TodoRepository {
	
	/** Todoを1件保存する
	 * 
	 */
	void saveOne(Todo todo);
	
	/** 同じID名のTodoを検索する
	 * 
	 * @return Todo
	 */
	Optional<Todo> selectOne(Integer id);
	
	/** Todoを全件検索する
	 * 
	 * @return Todo
	 */
	List<Todo> selectAll();

	/** Todoを1件更新する
	 * 
	 */
	void updateOne(Todo todo);
	
	/** Todoを1件完了にする
	 * 
	 */
	void finishOne(Integer id);
	
	/** 同じID名のTodoを削除する
	 * 
	 */
	void deleteOne(Integer id);
}