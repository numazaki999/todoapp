package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Todo;
import com.example.demo.form.EditTodoForm;
import com.example.demo.form.NewTodoForm;
import com.example.demo.service.TodoService;

@Controller
@RequestMapping("/")
public class TodoController {

	@Autowired
	TodoService todoServiceImpl;

	//todoの新規登録
	@PostMapping("add")
	//modelにnewTodoFormとbindingResultの値をSpringが裏でセットしている。
	public String add(@Validated NewTodoForm newTodoForm, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return summary(newTodoForm, model);
		}
		Todo todo = new Todo();
		//左のオブジェクトから右のオブジェクトに値をコピー
		BeanUtils.copyProperties(newTodoForm, todo);
		todo.setBodytext(newTodoForm.getNewTodo());
		todoServiceImpl.saveTodo(todo);
		//ブラウザでlocalhost8080/を記述した時と同じ動きになる。@GetMapping("/")が反応して一覧画面を表示。
		return "redirect:/";
	}

	//一覧画面表示
	@GetMapping("/")
	public String summary(@ModelAttribute NewTodoForm newTodoForm, Model model) {
		List<Todo> todos = todoServiceImpl.getTodos();
		model.addAttribute("todos", todos);
		return "summary";
	}

	//編集画面を表示する
	@GetMapping("edit/{id}")
	String edit(@PathVariable("id") Integer id, @ModelAttribute EditTodoForm editTodoForm, Model model) {
		Optional<Todo> todo = todoServiceImpl.getTodo(id);
	    //ifPresentはOptionalが持っているメゾット。
		todo.ifPresent(t -> editTodoForm.setUpdateTodo(t.getBodytext()));
		return "edit";
	}

	//todoを更新する
	@PostMapping("update")
	String update(@Validated EditTodoForm editTodoForm, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return edit(editTodoForm.getId(), editTodoForm, model);
		}
		Todo todo = new Todo();
		BeanUtils.copyProperties(editTodoForm, todo);
		todo.setBodytext(editTodoForm.getUpdateTodo());
		todoServiceImpl.updateTodo(todo);
		return "redirect:/";
	}
	
	//指定されたidのtodoを完了にする
	@PostMapping("finish")
	public String finish(@RequestParam Integer id) {
		todoServiceImpl.finishTodo(id);
		return "redirect:/";
	}
	
	//指定されたidのtodoを削除する
	@PostMapping("delete")
	public String delete(@RequestParam Integer id) {
		todoServiceImpl.deleteTodo(id);
		return "redirect:/";
	}
}