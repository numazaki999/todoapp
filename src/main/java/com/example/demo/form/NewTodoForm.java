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
public class NewTodoForm {

	//新規投稿された本文
	@NotNull
	@Size(min = 4, max = 20, message = "文字数は4〜20文字です。")
	private String newTodo;
	private String deadline;
}
