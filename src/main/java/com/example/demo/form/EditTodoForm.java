package com.example.demo.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EditTodoForm {
	
	//編集するtodoのid
	@NotNull
	private int id;
	
	//新規投稿された本文
	@NotNull
	@Size(min = 3, max = 150, message = "文字数は3〜150文字です。")
	private String updateTodo;
}
